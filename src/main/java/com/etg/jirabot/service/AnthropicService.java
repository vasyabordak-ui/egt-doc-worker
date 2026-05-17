package com.etg.jirabot.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class AnthropicService {

    private static final Logger log = LoggerFactory.getLogger(AnthropicService.class);

    private final WebClient anthropicWebClient;
    private final DocumentationLoader documentationLoader;

    @Value("${anthropic.model}")
    private String model;

    @Value("${anthropic.max-tokens}")
    private int maxTokens;

    private static final String SYSTEM_PROMPT_TEMPLATE = """
            You are a technical support assistant for the ETG (Emerging Travel Group) API.
            You answer questions from integration partners based strictly on the official ETG API documentation provided below.
            
            Rules:
            - Answer only based on the documentation below. If the answer is not in the documentation, say so clearly.
            - Be concise, precise, and technical. Use bullet points and code examples from the docs where relevant.
            - Always respond in the same language the question was asked in.
            - Do not mention that you are Claude or an AI — just answer the question.
            - Format your answer clearly so it reads well as a Jira internal comment.
            
            --- ETG API DOCUMENTATION START ---
            
            %s
            
            --- ETG API DOCUMENTATION END ---
            """;

    public AnthropicService(@Qualifier("anthropicWebClient") WebClient anthropicWebClient,
                            DocumentationLoader documentationLoader) {
        this.anthropicWebClient = anthropicWebClient;
        this.documentationLoader = documentationLoader;
    }

    /**
     * Sends the issue text to Claude and returns the answer.
     */
    public String askClaude(String issueText) {
        log.info("Calling Claude API with issue text length={}", issueText.length());

        String systemPrompt = String.format(SYSTEM_PROMPT_TEMPLATE, documentationLoader.getDocumentation());

        Map<String, Object> requestBody = Map.of(
                "model", model,
                "max_tokens", maxTokens,
                "system", systemPrompt,
                "messages", List.of(
                        Map.of("role", "user", "content", issueText)
                )
        );

        JsonNode response = anthropicWebClient.post()
                .uri("/v1/messages")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        if (response == null || !response.has("content")) {
            throw new RuntimeException("Empty or invalid response from Anthropic API");
        }

        // Extract text from first content block
        JsonNode contentArray = response.get("content");
        if (contentArray.isEmpty()) {
            throw new RuntimeException("Anthropic returned empty content array");
        }

        String answer = contentArray.get(0).get("text").asText();
        log.info("Claude answered with {} characters", answer.length());
        return answer;
    }
}
