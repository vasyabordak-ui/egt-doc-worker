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
    private final ObjectMapper objectMapper;

    @Value("${anthropic.model}")
    private String model;

    @Value("${anthropic.max-tokens}")
    private int maxTokens;

    @Value("${rag.top-files:5}")
    private int topFiles;

    private static final int MAX_RETRIES = 3;
    private static final long RETRY_DELAY_MS = 60_000;

    private static final String DECOMPOSE_PROMPT = """
            Extract a list of distinct search queries from the question below.
            Each query should capture one specific topic or sub-question.
            Return ONLY a JSON array of strings, nothing else. No markdown, no explanation.
            Maximum 8 queries.
            
            Question:
            %s
            """;

    private static final String ANSWER_PROMPT = """
            You are a technical support assistant for the ETG (Emerging Travel Group) API.
            Answer the question below based strictly on the provided documentation.
            
            Rules:
            - Answer directly and concisely. State facts without phrases like "the documentation says" or "based on the docs".
            - If a specific question cannot be answered from the provided docs, say: "This is not covered in the available documentation. Please contact ETG support."
            - Always respond in the same language as the question.
            - Do not mention that you are Claude or an AI.
            - If the question has multiple sub-questions, answer each one with a clear bold heading.
            - After each answer section, add a reference link to the relevant doc page. Use this format: [docs.emergingtravel.com/docs/FILENAME](https://docs.emergingtravel.com/docs/FILENAME) where FILENAME is the source file name without .md extension.
            - End with a *Summary* section if there are multiple questions.
            
            FORMATTING — use Jira Wiki Markup only:
            - Headings: use *bold text* (single asterisk each side, NOT double **)
            - Bullet lists: start each item with - (dash space)
            - Numbered lists: 1. 2. 3.
            - Field/code names: use {{monospace}} (double curly braces each side)
            - Do NOT use ## headings
            - Do NOT use ** for bold
            - Do NOT use | pipe tables — use bullet lists instead
            - Do NOT use ``` code blocks — use {{field}} inline notation
            
            Documentation:
            %s
            
            Question:
            %s
            """;

    public AnthropicService(@Qualifier("anthropicWebClient") WebClient anthropicWebClient,
                            EmbeddingService embeddingService,
                            VectorStoreService vectorStoreService,
                            ObjectMapper objectMapper) {
        this.anthropicWebClient = anthropicWebClient;
        this.embeddingService = embeddingService;
        this.vectorStoreService = vectorStoreService;
        this.objectMapper = objectMapper;
    }

    public String askClaude(String issueText) {
        // Step 1: Decompose question into sub-queries
        List<String> subQueries = decomposeQuestion(issueText);
        log.info("Decomposed into {} sub-queries", subQueries.size());

        // Step 2: Find relevant FILES for each sub-query
        Set<String> relevantFiles = new LinkedHashSet<>();
        for (String query : subQueries) {
            log.info("Searching files for: {}", query.substring(0, Math.min(60, query.length())));
            float[] embedding = embeddingService.embed(query);
            List<String> files = vectorStoreService.findRelevantFilenames(embedding, topFiles);
            relevantFiles.addAll(files);
        }
        log.info("Found {} unique relevant files: {}", relevantFiles.size(), relevantFiles);

        // Step 3: Load FULL content of each relevant file
        StringBuilder context = new StringBuilder();
        for (String filename : relevantFiles) {
            String content = vectorStoreService.getFileContent(filename);
            context.append("### ").append(filename).append("\n\n");
            context.append(content).append("\n\n---\n\n");
        }

        log.info("Total context size: {} chars from {} files", context.length(), relevantFiles.size());

        // Step 4: Ask Claude with full file content
        String userMessage = ANSWER_PROMPT.formatted(context.toString(), issueText);

        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("model", model);
        requestBody.put("max_tokens", maxTokens);
        requestBody.put("messages", List.of(
                Map.of("role", "user", "content", userMessage)
        ));

        return callWithRetry(requestBody, 0);
    }

    private List<String> decomposeQuestion(String question) {
        try {
            Map<String, Object> requestBody = new LinkedHashMap<>();
            requestBody.put("model", model);
            requestBody.put("max_tokens", 500);
            requestBody.put("messages", List.of(
                    Map.of("role", "user", "content", DECOMPOSE_PROMPT.formatted(question))
            ));

            JsonNode response = anthropicWebClient.post()
                    .uri("/v1/messages")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();

            if (response == null || !response.has("content")) return List.of(question);

            String text = response.get("content").get(0).get("text").asText().trim();
            text = text.replaceAll("```json|```", "").trim();

            JsonNode arr = objectMapper.readTree(text);
            List<String> queries = new ArrayList<>();
            for (JsonNode node : arr) queries.add(node.asText());
            return queries.isEmpty() ? List.of(question) : queries;

        } catch (Exception e) {
            log.warn("Failed to decompose question: {}", e.getMessage());
            return List.of(question);
        }
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
