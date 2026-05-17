import os
from huggingface_hub import snapshot_download

print("Downloading model from HuggingFace...")
snapshot_download(
    repo_id="sentence-transformers/all-MiniLM-L6-v2",
    local_dir="/app/model",
    ignore_patterns=["*.msgpack", "*.h5", "flax_model*", "tf_model*", "rust_model*", "onnx*"]
)

print("Converting to TorchScript (.pt) format...")
import torch
from transformers import AutoModel, AutoTokenizer

tokenizer = AutoTokenizer.from_pretrained("/app/model")
model = AutoModel.from_pretrained("/app/model")
model.eval()

# Trace with dummy input
dummy_input = tokenizer("hello world", return_tensors="pt")
with torch.no_grad():
    traced = torch.jit.trace(
        model,
        (dummy_input["input_ids"], dummy_input["attention_mask"], dummy_input["token_type_ids"])
    )

traced.save("/app/model/pytorch_model.pt")
print("Model saved as pytorch_model.pt")
