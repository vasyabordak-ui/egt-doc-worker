DROP TABLE IF EXISTS doc_chunks;

CREATE TABLE doc_chunks (
    id          BIGSERIAL PRIMARY KEY,
    filename    VARCHAR(255) NOT NULL,
    chunk_index INT NOT NULL,
    content     TEXT NOT NULL,
    embedding   vector(1536),
    created_at  TIMESTAMP DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS doc_chunks_embedding_idx
    ON doc_chunks USING ivfflat (embedding vector_cosine_ops)
    WITH (lists = 50);
