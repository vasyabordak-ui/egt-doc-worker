package com.etg.jirabot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "doc_chunks")
public class DocChunk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "filename", nullable = false)
    private String filename;

    @Column(name = "chunk_index", nullable = false)
    private int chunkIndex;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    // Stored as float array, mapped manually via JDBC
    @Column(name = "embedding", columnDefinition = "vector(1536)")
    private String embedding; // placeholder — actual writes done via JDBC

    public DocChunk() {}

    public DocChunk(String filename, int chunkIndex, String content) {
        this.filename = filename;
        this.chunkIndex = chunkIndex;
        this.content = content;
    }

    public Long getId() { return id; }
    public String getFilename() { return filename; }
    public int getChunkIndex() { return chunkIndex; }
    public String getContent() { return content; }
    public void setId(Long id) { this.id = id; }
    public void setFilename(String filename) { this.filename = filename; }
    public void setChunkIndex(int chunkIndex) { this.chunkIndex = chunkIndex; }
    public void setContent(String content) { this.content = content; }
}
