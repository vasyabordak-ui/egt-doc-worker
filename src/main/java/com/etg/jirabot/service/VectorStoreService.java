package com.etg.jirabot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
     * Find top-K most relevant FILENAMES for the query.
     * Uses cosine distance to rank chunks, then deduplicates by filename.
     * Returns filenames ordered by their best chunk score.
     */
    public List<String> findRelevantFilenames(float[] queryEmbedding, int maxFiles) {
        String vectorStr = toVectorString(queryEmbedding);
        String sql = """
                SELECT DISTINCT ON (filename) filename,
                       embedding <=> ?::vector AS distance
                FROM doc_chunks
                ORDER BY filename, embedding <=> ?::vector
                LIMIT ?
                """;

        // Get best-matching chunk per file, then sort by distance and take top N files
        String outerSql = """
                SELECT filename FROM (
                    SELECT DISTINCT ON (filename) filename,
                           embedding <=> ?::vector AS distance
                    FROM doc_chunks
                    ORDER BY filename, embedding <=> ?::vector
                ) ranked
                ORDER BY distance
                LIMIT ?
                """;

        return jdbc.query(outerSql,
                (rs, rowNum) -> rs.getString("filename"),
                vectorStr, vectorStr, maxFiles
        );
    }

    /**
     * Get all chunk contents for a specific file, ordered by chunk index.
     */
    public String getFileContent(String filename) {
        List<String> chunks = jdbc.query(
                "SELECT content FROM doc_chunks WHERE filename = ? ORDER BY chunk_index",
                (rs, rowNum) -> rs.getString("content"),
                filename
        );
        return String.join("\n\n", chunks);
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
