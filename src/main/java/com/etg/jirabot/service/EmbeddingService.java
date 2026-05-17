package com.etg.jirabot.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

/**
 * Generates text embeddings using OpenAI text-embedding-3-small.
 * Cost: $0.02 per 1M tokens — essentially free for this use case.
 */
@Service
public class EmbeddingService {

    private static final Logger log = LoggerFactory.getLogger(EmbeddingService.class);
    public static final int EMBEDDING_DIM = 1536;

    private final WebClient openAiClient;

    @Value("${openai.embedding-model:text-embedding-3-small}")
    private String embeddingModel;

    public EmbeddingService(@Value("${openai.api-key}") String apiKey) {
        this.openAiClient = WebClient.builder()
                .baseUrl("https://api.openai.com")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Content-Type", "application/json")
                .codecs(c -> c.defaultCodecs().maxInMemorySize(10 * 1024 * 1024))
                .build();
    }

    public float[] embed(String text) {
        JsonNode response = openAiClient.post()
                .uri("/v1/embeddings")
                .bodyValue(Map.of("model", embeddingModel, "input", text))
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        if (response == null || !response.has("data")) {
            throw new RuntimeException("Invalid response from OpenAI embeddings API");
        }

        JsonNode arr = response.get("data").get(0).get("embedding");
        float[] result = new float[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            result[i] = (float) arr.get(i).asDouble();
        }
        log.debug("Generated embedding dim={}", result.length);
        return result;
    }
}
