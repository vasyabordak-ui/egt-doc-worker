package com.etg.jirabot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class JiraService {

    private static final Logger log = LoggerFactory.getLogger(JiraService.class);
    private final WebClient jiraWebClient;
    private final ObjectMapper objectMapper;

    public JiraService(@Qualifier("jiraWebClient") WebClient jiraWebClient,
                       ObjectMapper objectMapper) {
        this.jiraWebClient = jiraWebClient;
        this.objectMapper = objectMapper;
    }

    /**
     * Fetches the issue summary + description from Jira.
     * Returns a combined string: "Summary: ...\n\nDescription: ..."
     */
    public String getIssueText(String issueKey) {
        log.info("Fetching Jira issue: {}", issueKey);

        JsonNode issue = jiraWebClient.get()
                .uri("/rest/api/3/issue/{issueKey}?fields=summary,description", issueKey)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        if (issue == null) {
            throw new RuntimeException("Jira returned null for issue: " + issueKey);
        }

        JsonNode fields = issue.get("fields");
        String summary = fields.has("summary") ? fields.get("summary").asText("") : "";

        // Description is Atlassian Document Format (ADF) — we extract plain text
        String description = extractTextFromAdf(fields.get("description"));

        log.info("Fetched issue '{}': summary length={}, description length={}",
                issueKey, summary.length(), description.length());

        return "Summary: " + summary + "\n\nDescription:\n" + description;
    }

    /**
     * Posts an internal comment to a Jira issue using ADF format.
     */
    public void postInternalComment(String issueKey, String commentText) {
        log.info("Posting internal comment to issue: {}", issueKey);

        // Jira internal comments use the "visibility" field with role "Service Desk Team"
        // For Jira Software, use visibility type "role" with value "Service Desk Team"
        // For plain internal note, set visibility to null and use the internal flag
        Map<String, Object> body = Map.of(
                "body", Map.of(
                        "type", "doc",
                        "version", 1,
                        "content", new Object[]{
                                Map.of(
                                        "type", "paragraph",
                                        "content", new Object[]{
                                                Map.of(
                                                        "type", "text",
                                                        "text", commentText
                                                )
                                        }
                                )
                        }
                )
        );

        jiraWebClient.post()
                .uri("/rest/api/3/issue/{issueKey}/comment", issueKey)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info("Internal comment posted successfully to: {}", issueKey);
    }

    /**
     * Recursively extracts plain text from Atlassian Document Format (ADF).
     */
    private String extractTextFromAdf(JsonNode node) {
        if (node == null || node.isNull()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        if (node.isTextual()) {
            return node.asText();
        }

        // If it's a "text" type node
        if (node.has("type") && "text".equals(node.get("type").asText())) {
            if (node.has("text")) {
                return node.get("text").asText();
            }
        }

        // Recurse into "content" array
        if (node.has("content") && node.get("content").isArray()) {
            for (JsonNode child : node.get("content")) {
                String childText = extractTextFromAdf(child);
                if (!childText.isEmpty()) {
                    sb.append(childText);
                }
            }
            // Add newline after block-level nodes
            if (node.has("type")) {
                String type = node.get("type").asText();
                if (type.equals("paragraph") || type.equals("heading") || type.startsWith("bullet")) {
                    sb.append("\n");
                }
            }
        }

        return sb.toString();
    }
}
