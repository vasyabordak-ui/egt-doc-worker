package com.etg.jirabot.controller;

import com.etg.jirabot.service.IndexingService;
import com.etg.jirabot.service.VectorStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class IndexingController {

    private static final Logger log = LoggerFactory.getLogger(IndexingController.class);

    private final IndexingService indexingService;
    private final VectorStoreService vectorStoreService;

    @Value("${app.secret-token}")
    private String secretToken;

    public IndexingController(IndexingService indexingService,
                               VectorStoreService vectorStoreService) {
        this.indexingService = indexingService;
        this.vectorStoreService = vectorStoreService;
    }

    /**
     * POST /api/index
     * Triggers full re-indexing of all documentation files.
     * Call this once after deploy, and whenever docs are updated.
     */
    @PostMapping("/index")
    public ResponseEntity<Map<String, Object>> index(
            @RequestHeader(value = "X-Secret-Token", required = false) String token) {

        if (!secretToken.equals(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid or missing X-Secret-Token"));
        }

        try {
            log.info("Starting documentation indexing...");
            long before = vectorStoreService.countChunks();
            IndexingService.IndexingResult result = indexingService.indexAll();

            return ResponseEntity.ok(Map.of(
                    "status", "ok",
                    "files", result.files(),
                    "chunks", result.chunks(),
                    "previousChunks", before
            ));
        } catch (Exception e) {
            log.error("Indexing failed: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * GET /api/index/status
     * Returns current number of indexed chunks.
     */
    @GetMapping("/index/status")
    public ResponseEntity<Map<String, Object>> status() {
        long count = vectorStoreService.countChunks();
        return ResponseEntity.ok(Map.of(
                "status", count > 0 ? "indexed" : "empty",
                "chunks", count
        ));
    }
}
