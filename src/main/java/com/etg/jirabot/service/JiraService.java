package com.etg.jirabot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.regex.*;

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

    public String getIssueText(String issueKey) {
        log.info("Fetching Jira issue: {}", issueKey);

        JsonNode issue = jiraWebClient.get()
                .uri("/rest/api/3/issue/{issueKey}?fields=summary,description", issueKey)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        if (issue == null) throw new RuntimeException("Jira returned null for issue: " + issueKey);

        JsonNode fields = issue.get("fields");
        String summary = fields.has("summary") ? fields.get("summary").asText("") : "";
        String description = extractTextFromAdf(fields.get("description"));

        log.info("Fetched issue '{}': summary length={}, description length={}",
                issueKey, summary.length(), description.length());

        return "Summary: " + summary + "\n\nDescription:\n" + description;
    }

    public void postInternalComment(String issueKey, String text) {
        log.info("Posting comment to issue: {}", issueKey);

        List<Map<String, Object>> adfContent = buildAdf(text);

        Map<String, Object> body = Map.of(
                "body", Map.of(
                        "type", "doc",
                        "version", 1,
                        "content", adfContent
                )
        );

        jiraWebClient.post()
                .uri("/rest/api/3/issue/{issueKey}/comment", issueKey)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info("Comment posted successfully to: {}", issueKey);
    }

    /**
     * Builds ADF from structured text.
     * Supports:
     * - ## Heading → heading node
     * - **bold** text → strong marks
     * - `code` → code marks
     * - [text](url) → link marks
     * - - bullet items → bulletList
     * - 1. numbered items → orderedList
     * - blank lines → paragraph separator
     */
    private List<Map<String, Object>> buildAdf(String text) {
        List<Map<String, Object>> blocks = new ArrayList<>();
        String[] lines = text.split("\n");
        int i = 0;

        while (i < lines.length) {
            String line = lines[i];

            if (line.isBlank()) { i++; continue; }

            // Heading ## or bold heading *text*
            if (line.startsWith("## ") || line.startsWith("# ")) {
                int level = line.startsWith("## ") ? 2 : 1;
                String headingText = line.replaceFirst("^#+\\s+", "");
                blocks.add(Map.of(
                    "type", "heading",
                    "attrs", Map.of("level", level),
                    "content", parseInline(headingText)
                ));
                i++; continue;
            }

            // Bullet list
            if (line.matches("^[-*] .+")) {
                List<Map<String, Object>> items = new ArrayList<>();
                while (i < lines.length && lines[i].matches("^[-*] .+")) {
                    String itemText = lines[i].replaceFirst("^[-*] ", "");
                    items.add(Map.of(
                        "type", "listItem",
                        "content", List.of(Map.of(
                            "type", "paragraph",
                            "content", parseInline(itemText)
                        ))
                    ));
                    i++;
                }
                blocks.add(Map.of("type", "bulletList", "content", items));
                continue;
            }

            // Numbered list
            if (line.matches("^\\d+\\. .+")) {
                List<Map<String, Object>> items = new ArrayList<>();
                while (i < lines.length && lines[i].matches("^\\d+\\. .+")) {
                    String itemText = lines[i].replaceFirst("^\\d+\\.\\s+", "");
                    items.add(Map.of(
                        "type", "listItem",
                        "content", List.of(Map.of(
                            "type", "paragraph",
                            "content", parseInline(itemText)
                        ))
                    ));
                    i++;
                }
                blocks.add(Map.of("type", "orderedList", "content", items));
                continue;
            }

            // Horizontal rule
            if (line.matches("^---+\\s*$")) {
                blocks.add(Map.of("type", "rule"));
                i++; continue;
            }

            // Regular paragraph — collect consecutive non-special lines
            StringBuilder para = new StringBuilder(line);
            i++;
            while (i < lines.length
                    && !lines[i].isBlank()
                    && !lines[i].startsWith("#")
                    && !lines[i].matches("^[-*] .+")
                    && !lines[i].matches("^\\d+\\. .+")
                    && !lines[i].matches("^---+\\s*$")) {
                para.append(" ").append(lines[i].trim());
                i++;
            }

            blocks.add(Map.of(
                "type", "paragraph",
                "content", parseInline(para.toString())
            ));
        }

        return blocks;
    }

    /**
     * Parses inline markdown: **bold**, `code`, [text](url), plain text
     */
    private List<Map<String, Object>> parseInline(String text) {
        List<Map<String, Object>> nodes = new ArrayList<>();
        // Order matters: links first, then bold, then code, then plain
        Pattern pattern = Pattern.compile(
            "\\[([^\\]]+)\\]\\(([^)]+)\\)" +  // [text](url)
            "|\\*\\*([^*]+)\\*\\*" +            // **bold**
            "|`([^`]+)`" +                       // `code`
            "|([^\\[`*]+)"                       // plain text
        );
        Matcher m = pattern.matcher(text);

        while (m.find()) {
            if (m.group(1) != null) {
                // Link: [text](url)
                Map<String, Object> node = new LinkedHashMap<>();
                node.put("type", "text");
                node.put("text", m.group(1));
                node.put("marks", List.of(Map.of(
                    "type", "link",
                    "attrs", Map.of("href", m.group(2))
                )));
                nodes.add(node);
            } else if (m.group(3) != null) {
                // Bold
                Map<String, Object> node = new LinkedHashMap<>();
                node.put("type", "text");
                node.put("text", m.group(3));
                node.put("marks", List.of(Map.of("type", "strong")));
                nodes.add(node);
            } else if (m.group(4) != null) {
                // Code
                Map<String, Object> node = new LinkedHashMap<>();
                node.put("type", "text");
                node.put("text", m.group(4));
                node.put("marks", List.of(Map.of("type", "code")));
                nodes.add(node);
            } else if (m.group(5) != null && !m.group(5).isEmpty()) {
                // Plain text
                nodes.add(Map.of("type", "text", "text", m.group(5)));
            }
        }

        if (nodes.isEmpty()) nodes.add(Map.of("type", "text", "text", text));
        return nodes;
    }

    private String extractTextFromAdf(JsonNode node) {
        if (node == null || node.isNull()) return "";
        StringBuilder sb = new StringBuilder();
        if (node.isTextual()) return node.asText();
        if (node.has("type") && "text".equals(node.get("type").asText())) {
            if (node.has("text")) return node.get("text").asText();
        }
        if (node.has("content") && node.get("content").isArray()) {
            for (JsonNode child : node.get("content")) {
                String childText = extractTextFromAdf(child);
                if (!childText.isEmpty()) sb.append(childText);
            }
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
