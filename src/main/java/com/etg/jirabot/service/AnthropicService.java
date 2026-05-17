package com.etg.jirabot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.*;

@Service
public class AnthropicService {

    private static final Logger log = LoggerFactory.getLogger(AnthropicService.class);

    private final WebClient anthropicWebClient;
    private final EmbeddingService embeddingService;
    private final VectorStoreService vectorStoreService;

    @Value("${anthropic.model}")
    private String model;

    @Value("${anthropic.max-tokens}")
    private int maxTokens;

    private static final int MAX_RETRIES = 3;
    private static final long RETRY_DELAY_MS = 60_000;

    private static final String SYSTEM_PROMPT = """
            You are a technical support assistant for the ETG (Emerging Travel Group) API.
            You answer questions from integration partners based strictly on the official ETG API documentation provided below.
            
            Rules:
            - Answer only based on the documentation provided. If the answer is not in the documentation, say so clearly.
            - Be concise, precise, and technical. Use bullet points and code examples from the docs where relevant.
            - Always respond in the same language the question was asked in.
            - Do not mention that you are Claude or an AI — just answer the question.
            - Format your answer in Markdown: use ## headings, **bold**, bullet lists, and code blocks where appropriate.
            """;

    public AnthropicService(@Qualifier("anthropicWebClient") WebClient anthropicWebClient,
                            EmbeddingService embeddingService,
                            VectorStoreService vectorStoreService) {
        this.anthropicWebClient = anthropicWebClient;
        this.embeddingService = embeddingService;
        this.vectorStoreService = vectorStoreService;
    }

    public String askClaude(String issueText) {
        log.info("RAG: generating embedding for question...");

        // Step 1: embed the question
        float[] queryEmbedding = embeddingService.embed(issueText);

        // Step 2: find relevant chunks
        List<String> relevantChunks = vectorStoreService.findSimilarChunks(queryEmbedding);
        log.info("RAG: found {} relevant chunks", relevantChunks.size());

        // Step 3: build context from chunks
        String context = String.join("\n\n---\n\n", relevantChunks);

        // Step 4: call Claude with only relevant context
        String userMessage = """
                Here is the relevant ETG API documentation:
                
                %s
                
                ---
                
                Question from integration partner:
                %s
                """.formatted(context, issueText);

        log.info("Calling Claude with context size={} chars", context.length());

        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("model", model);
        requestBody.put("max_tokens", maxTokens);
        requestBody.put("system", SYSTEM_PROMPT);
        requestBody.put("messages", List.of(
                Map.of("role", "user", "content", userMessage)
        ));

        return callWithRetry(requestBody, 0);
    }

    private String callWithRetry(Map<String, Object> requestBody, int attempt) {
        try {
            JsonNode response = anthropicWebClient.post()
                    .uri("/v1/messages")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();

            if (response == null || !response.has("content")) {
                throw new RuntimeException("Empty response from Anthropic API");
            }

            String answer = response.get("content").get(0).get("text").asText();
            log.info("Claude answered with {} characters", answer.length());
            return answer;

        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS && attempt < MAX_RETRIES) {
                log.warn("Rate limit hit (429). Attempt {}/{}. Waiting {}s...",
                        attempt + 1, MAX_RETRIES, RETRY_DELAY_MS / 1000);
                try {
                    Thread.sleep(RETRY_DELAY_MS);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Interrupted during retry", ie);
                }
                return callWithRetry(requestBody, attempt + 1);
            }
            throw e;
        }
    }
}
