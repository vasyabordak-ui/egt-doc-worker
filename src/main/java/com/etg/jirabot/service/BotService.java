package com.etg.jirabot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BotService {

    private static final Logger log = LoggerFactory.getLogger(BotService.class);

    private final JiraService jiraService;
    private final AnthropicService anthropicService;

    public BotService(JiraService jiraService, AnthropicService anthropicService) {
        this.jiraService = jiraService;
        this.anthropicService = anthropicService;
    }

    /**
     * Full pipeline:
     * 1. Fetch issue text from Jira
     * 2. Ask Claude for an answer
     * 3. Post the answer as an internal comment back to Jira
     */
    public void processIssue(String issueKey) {
        log.info("Starting pipeline for issue: {}", issueKey);

        // Step 1: Get issue content from Jira
        String issueText = jiraService.getIssueText(issueKey);

        // Step 2: Ask Claude
        String answer = anthropicService.askClaude(issueText);

        // Step 3: Post as internal comment in Jira
        // Clean up any markdown artifacts Claude adds around links
        String cleanAnswer = answer
                .replaceAll("__\\[", "[")
                .replaceAll("\\]__", "]")
                .replaceAll("\*\*\\[", "[")
                .replaceAll("\\]\*\*", "]");
        String commentWithFooter = cleanAnswer + "\n\n---\n_This answer was generated automatically based on ETG API documentation._";
        jiraService.postInternalComment(issueKey, commentWithFooter);

        log.info("Pipeline completed for issue: {}", issueKey);
    }
}