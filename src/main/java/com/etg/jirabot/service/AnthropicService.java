package com.etg.jirabot.service;

import com.fasterxml.jackson.core.type.TypeReference;
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
    private final ObjectMapper objectMapper;

    @Value("${anthropic.model}")
    private String model;

    @Value("${anthropic.max-tokens}")
    private int maxTokens;

    @Value("${anthropic.file-ids-json}")
    private String fileIdsJson;

    private static final int MAX_RETRIES = 3;
    private static final long RETRY_DELAY_MS = 60_000; // 60 seconds

    private static final String SYSTEM_PROMPT = """
            You are a technical support assistant for the ETG (Emerging Travel Group) API.
            You answer questions from integration partners based strictly on the official ETG API documentation provided as files.
            
            Rules:
            - Answer only based on the documentation files attached. If the answer is not in the documentation, say so clearly.
            - Be concise, precise, and technical. Use bullet points and code examples from the docs where relevant.
            - Always respond in the same language the question was asked in.
            - Do not mention that you are Claude or an AI — just answer the question.
            - Format your answer in Markdown: use ## headings, **bold**, bullet lists, and code blocks where appropriate.
            """;

    public AnthropicService(@Qualifier("anthropicWebClient") WebClient anthropicWebClient,
                            ObjectMapper objectMapper) {
        this.anthropicWebClient = anthropicWebClient;
        this.objectMapper = objectMapper;
    }

    public String askClaude(String issueText) {
        log.info("Calling Claude API with Files API, issue text length={}", issueText.length());

        List<Map<String, Object>> userContent = new ArrayList<>();

        List<String> fileIds = getFileIds();
        log.info("Using {} documentation files", fileIds.size());

        for (String fileId : fileIds) {
            userContent.add(Map.of(
                    "type", "document",
                    "source", Map.of(
                            "type", "file",
                            "file_id", fileId
                    )
            ));
        }

        userContent.add(Map.of("type", "text", "text", issueText));

        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("model", model);
        requestBody.put("max_tokens", maxTokens);
        requestBody.put("system", SYSTEM_PROMPT);
        requestBody.put("messages", List.of(
                Map.of("role", "user", "content", userContent)
        ));

        return callWithRetry(requestBody, 0);
    }

    private String callWithRetry(Map<String, Object> requestBody, int attempt) {
        try {
            JsonNode response = anthropicWebClient.post()
                    .uri("/v1/messages")
                    .header("anthropic-beta", "files-api-2025-04-14")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();

            if (response == null || !response.has("content")) {
                throw new RuntimeException("Empty or invalid response from Anthropic API");
            }

            JsonNode contentArray = response.get("content");
            if (contentArray.isEmpty()) {
                throw new RuntimeException("Anthropic returned empty content array");
            }

            String answer = contentArray.get(0).get("text").asText();
            log.info("Claude answered with {} characters", answer.length());
            return answer;

        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS && attempt < MAX_RETRIES) {
                log.warn("Rate limit hit (429). Attempt {}/{}. Waiting {}s before retry...",
                        attempt + 1, MAX_RETRIES, RETRY_DELAY_MS / 1000);
                try {
                    Thread.sleep(RETRY_DELAY_MS);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Interrupted during retry wait", ie);
                }
                return callWithRetry(requestBody, attempt + 1);
            }
            throw e;
        }
    }

    private List<String> getFileIds() {
        try {
            Map<String, String> fileMap = objectMapper.readValue(
                    fileIdsJson, new TypeReference<Map<String, String>>() {}
            );
            return new ArrayList<>(fileMap.values());
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse ANTHROPIC_FILE_IDS: " + e.getMessage(), e);
        }
    }
}