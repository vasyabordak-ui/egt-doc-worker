package com.etg.jirabot.controller;

import com.etg.jirabot.service.BotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class BotController {

    private static final Logger log = LoggerFactory.getLogger(BotController.class);

    private final BotService botService;

    @Value("${app.secret-token}")
    private String secretToken;

    public BotController(BotService botService) {
        this.botService = botService;
    }

    /**
     * POST /api/answer-ticket
     *
     * Called by Jira Automation or manually.
     * Body: { "issueKey": "PROJ-123" }
     * Header: X-Secret-Token: <your token>
     *
     * Returns 200 OK on success.
     */
    @PostMapping("/answer-ticket")
    public ResponseEntity<Map<String, String>> answerTicket(
            @RequestHeader(value = "X-Secret-Token", required = false) String token,
            @RequestBody Map<String, String> body) {

        // Simple token auth to prevent unauthorized calls
        if (!secretToken.equals(token)) {
            log.warn("Unauthorized request — invalid or missing X-Secret-Token");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid or missing X-Secret-Token header"));
        }

        String issueKey = body.get("issueKey");
        if (issueKey == null || issueKey.isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Missing 'issueKey' in request body"));
        }

        issueKey = issueKey.trim().toUpperCase();

        try {
            log.info("Received request to process issue: {}", issueKey);
            botService.processIssue(issueKey);
            return ResponseEntity.ok(Map.of(
                    "status", "ok",
                    "issueKey", issueKey,
                    "message", "Answer posted as internal comment"
            ));
        } catch (Exception e) {
            log.error("Failed to process issue {}: {}", issueKey, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * GET /api/health
     * Health check for Railway.
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "ok"));
    }
}
