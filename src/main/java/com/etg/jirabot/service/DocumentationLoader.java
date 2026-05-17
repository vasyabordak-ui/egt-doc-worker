package com.etg.jirabot.service;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Loads ETG API documentation from /docs/*.md files at startup and provides
 * a single concatenated string for use in the Claude system prompt.
 *
 * Place all .md documentation files in src/main/resources/docs/
 */
@Component
public class DocumentationLoader {

    private static final Logger log = LoggerFactory.getLogger(DocumentationLoader.class);
    private String documentation;

    @PostConstruct
    public void load() throws IOException {
        log.info("Loading ETG API documentation from classpath:docs/");

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:docs/*.md");

        if (resources.length == 0) {
            log.warn("No .md files found in classpath:docs/ — documentation will be empty!");
            documentation = "";
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Resource resource : resources) {
            String filename = resource.getFilename();
            String content = resource.getContentAsString(StandardCharsets.UTF_8);
            sb.append("### ").append(filename).append("\n\n");
            sb.append(content).append("\n\n---\n\n");
        }

        documentation = sb.toString();
        log.info("Loaded {} documentation files, total {} characters",
                resources.length, documentation.length());
    }

    public String getDocumentation() {
        return documentation;
    }
}
