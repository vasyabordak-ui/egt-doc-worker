from huggingface_hub import snapshot_download

snapshot_download(
    repo_id="sentence-transformers/all-MiniLM-L6-v2",
    local_dir="/app/model",
    ignore_patterns=["*.msgpack", "*.h5", "flax_model*", "tf_model*", "rust_model*"]
)
print("Model downloaded successfully")
