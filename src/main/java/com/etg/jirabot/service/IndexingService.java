package com.etg.jirabot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class IndexingService {

    private static final Logger log = LoggerFactory.getLogger(IndexingService.class);

    private final EmbeddingService embeddingService;
    private final VectorStoreService vectorStoreService;

    @Value("${rag.chunk-size}")
    private int chunkSize;

    @Value("${rag.chunk-overlap}")
    private int chunkOverlap;

    public IndexingService(EmbeddingService embeddingService,
                           VectorStoreService vectorStoreService) {
        this.embeddingService = embeddingService;
        this.vectorStoreService = vectorStoreService;
    }

    /**
     * Indexes all .md files from classpath:docs/ into pgvector.
     * Clears existing data first.
     */
    public IndexingResult indexAll() throws IOException {
        log.info("Starting full re-indexing of documentation...");

        vectorStoreService.deleteAll();

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:docs/*.md");

        int totalChunks = 0;
        int totalFiles = 0;

        for (Resource resource : resources) {
            String filename = resource.getFilename();
            String content = resource.getContentAsString(StandardCharsets.UTF_8);

            List<String> chunks = splitIntoChunks(content);
            log.info("Indexing {} → {} chunks", filename, chunks.size());

            for (int i = 0; i < chunks.size(); i++) {
                String chunk = chunks.get(i);
                float[] embedding = embeddingService.embed(chunk);
                vectorStoreService.saveChunk(filename, i, chunk, embedding);
                totalChunks++;
            }
            totalFiles++;
        }

        log.info("Indexing complete: {} files, {} chunks total", totalFiles, totalChunks);
        return new IndexingResult(totalFiles, totalChunks);
    }

    /**
     * Splits text into overlapping chunks of roughly chunkSize characters.
     */
    private List<String> splitIntoChunks(String text) {
        List<String> chunks = new ArrayList<>();
        String[] paragraphs = text.split("\n\n");

        StringBuilder current = new StringBuilder();

        for (String paragraph : paragraphs) {
            if (current.length() + paragraph.length() > chunkSize && current.length() > 0) {
                chunks.add(current.toString().trim());

                // Keep overlap — last N characters
                String overlap = current.length() > chunkOverlap
                        ? current.substring(current.length() - chunkOverlap)
                        : current.toString();
                current = new StringBuilder(overlap);
            }
            current.append(paragraph).append("\n\n");
        }

        if (!current.isEmpty()) {
            chunks.add(current.toString().trim());
        }

        return chunks;
    }

    public record IndexingResult(int files, int chunks) {}
}
