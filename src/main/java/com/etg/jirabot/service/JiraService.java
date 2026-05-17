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

        if (issue == null) {
            throw new RuntimeException("Jira returned null for issue: " + issueKey);
        }

        JsonNode fields = issue.get("fields");
        String summary = fields.has("summary") ? fields.get("summary").asText("") : "";
        String description = extractTextFromAdf(fields.get("description"));

        log.info("Fetched issue '{}': summary length={}, description length={}",
                issueKey, summary.length(), description.length());

        return "Summary: " + summary + "\n\nDescription:\n" + description;
    }

    public void postInternalComment(String issueKey, String markdownText) {
        log.info("Posting comment to issue: {}", issueKey);

        List<Map<String, Object>> adfContent = textToAdf(markdownText);

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

    private List<Map<String, Object>> markdownToAdf(String markdown) {
        List<Map<String, Object>> blocks = new ArrayList<>();
        String[] lines = markdown.split("\n");

        int i = 0;
        while (i < lines.length) {
            String line = lines[i];

            if (line.isBlank()) { i++; continue; }

            // Code block
            if (line.startsWith("```")) {
                String language = line.substring(3).trim();
                StringBuilder code = new StringBuilder();
                i++;
                while (i < lines.length && !lines[i].startsWith("```")) {
                    code.append(lines[i]).append("\n");
                    i++;
                }
                i++;
                Map<String, Object> codeBlock = new LinkedHashMap<>();
                codeBlock.put("type", "codeBlock");
                if (!language.isEmpty()) codeBlock.put("attrs", Map.of("language", language));
                codeBlock.put("content", List.of(Map.of("type", "text", "text", code.toString())));
                blocks.add(codeBlock);
                continue;
            }

            // Horizontal rule
            if (line.matches("^[-*_]{3,}\\s*$")) {
                blocks.add(Map.of("type", "rule"));
                i++; continue;
            }

            // Headings
            if (line.startsWith("#")) {
                int level = 0;
                while (level < line.length() && line.charAt(level) == '#') level++;
                level = Math.min(level, 6);
                String text = line.substring(level).trim();
                blocks.add(Map.of(
                        "type", "heading",
                        "attrs", Map.of("level", level),
                        "content", parseInline(text)
                ));
                i++; continue;
            }

            // Bullet list
            if (line.matches("^[\\-*+] .+")) {
                List<Map<String, Object>> items = new ArrayList<>();
                while (i < lines.length && lines[i].matches("^[\\-*+] .+")) {
                    String itemText = lines[i].replaceFirst("^[\\-*+] ", "");
                    items.add(Map.of("type", "listItem",
                            "content", List.of(Map.of("type", "paragraph", "content", parseInline(itemText)))));
                    i++;
                }
                blocks.add(Map.of("type", "bulletList", "content", items));
                continue;
            }

            // Numbered list
            if (line.matches("^\\d+\\. .+")) {
                List<Map<String, Object>> items = new ArrayList<>();
                while (i < lines.length && lines[i].matches("^\\d+\\. .+")) {
                    String itemText = lines[i].replaceFirst("^\\d+\\. ", "");
                    items.add(Map.of("type", "listItem",
                            "content", List.of(Map.of("type", "paragraph", "content", parseInline(itemText)))));
                    i++;
                }
                blocks.add(Map.of("type", "orderedList", "content", items));
                continue;
            }

            // Paragraph
            StringBuilder paraText = new StringBuilder(line);
            i++;
            while (i < lines.length
                    && !lines[i].isBlank()
                    && !lines[i].startsWith("#")
                    && !lines[i].startsWith("```")
                    && !lines[i].matches("^[\\-*+] .+")
                    && !lines[i].matches("^\\d+\\. .+")
                    && !lines[i].matches("^[-*_]{3,}\\s*$")) {
                paraText.append(" ").append(lines[i]);
                i++;
            }
            blocks.add(Map.of("type", "paragraph", "content", parseInline(paraText.toString())));
        }

        return blocks;
    }

    private List<Map<String, Object>> parseInline(String text) {
        List<Map<String, Object>> nodes = new ArrayList<>();
        Pattern pattern = Pattern.compile("`([^`]+)`|\\*\\*(.+?)\\*\\*|\\*(.+?)\\*|([^`*]+)");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                Map<String, Object> node = new LinkedHashMap<>();
                node.put("type", "text");
                node.put("text", matcher.group(1));
                node.put("marks", List.of(Map.of("type", "code")));
                nodes.add(node);
            } else if (matcher.group(2) != null) {
                Map<String, Object> node = new LinkedHashMap<>();
                node.put("type", "text");
                node.put("text", matcher.group(2));
                node.put("marks", List.of(Map.of("type", "strong")));
                nodes.add(node);
            } else if (matcher.group(3) != null) {
                Map<String, Object> node = new LinkedHashMap<>();
                node.put("type", "text");
                node.put("text", matcher.group(3));
                node.put("marks", List.of(Map.of("type", "em")));
                nodes.add(node);
            } else if (matcher.group(4) != null) {
                nodes.add(Map.of("type", "text", "text", matcher.group(4)));
            }
        }

        if (nodes.isEmpty()) nodes.add(Map.of("type", "text", "text", text));
        return nodes;
    }

    /**
     * Converts plain text to ADF paragraphs.
     * Splits on double newlines for paragraph breaks, single newlines within paragraphs.
     */
    private List<Map<String, Object>> textToAdf(String text) {
        List<Map<String, Object>> blocks = new ArrayList<>();
        String[] paragraphs = text.split("\n\n+");

        for (String para : paragraphs) {
            if (para.isBlank()) continue;
            blocks.add(Map.of(
                "type", "paragraph",
                "content", List.of(Map.of("type", "text", "text", para.trim()))
            ));
        }

        if (blocks.isEmpty()) {
            blocks.add(Map.of(
                "type", "paragraph",
                "content", List.of(Map.of("type", "text", "text", text))
            ));
        }

        return blocks;
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
