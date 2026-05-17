package com.etg.jirabot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VectorStoreService {

    private static final Logger log = LoggerFactory.getLogger(VectorStoreService.class);

    private final JdbcTemplate jdbc;

    @Value("${rag.top-k}")
    private int topK;

    public VectorStoreService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Transactional
    public void saveChunk(String filename, int chunkIndex, String content, float[] embedding) {
        String vectorStr = toVectorString(embedding);
        jdbc.update(
                "INSERT INTO doc_chunks (filename, chunk_index, content, embedding) VALUES (?, ?, ?, ?::vector)",
                filename, chunkIndex, content, vectorStr
        );
    }

    @Transactional
    public void deleteAll() {
        int deleted = jdbc.update("DELETE FROM doc_chunks");
        log.info("Deleted {} chunks from vector store", deleted);
    }

    public long countChunks() {
        Long count = jdbc.queryForObject("SELECT COUNT(*) FROM doc_chunks", Long.class);
        return count != null ? count : 0;
    }

    /**
     * Find top-K most similar chunks to the query embedding using cosine distance.
     */
    public List<String> findSimilarChunks(float[] queryEmbedding) {
        String vectorStr = toVectorString(queryEmbedding);
        String sql = """
                SELECT content
                FROM doc_chunks
                ORDER BY embedding <=> ?::vector
                LIMIT ?
                """;

        return jdbc.query(sql,
                (rs, rowNum) -> rs.getString("content"),
                vectorStr, topK
        );
    }

    private String toVectorString(float[] embedding) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < embedding.length; i++) {
            if (i > 0) sb.append(",");
            sb.append(embedding[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}
