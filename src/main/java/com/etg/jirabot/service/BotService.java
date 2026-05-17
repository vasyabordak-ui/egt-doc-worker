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

    public void processIssue(String issueKey) {
        log.info("Starting pipeline for issue: {}", issueKey);

        String issueText = jiraService.getIssueText(issueKey);
        String answer = anthropicService.askClaude(issueText);

        // Remove all double-underscores (Claude sometimes wraps links in __..__)
        String cleanAnswer = answer.replace("__", "");

        String commentWithFooter = cleanAnswer
                + "\n\n---\n_This answer was generated automatically based on ETG API documentation._";

        jiraService.postInternalComment(issueKey, commentWithFooter);

        log.info("Pipeline completed for issue: {}", issueKey);
    }
}
