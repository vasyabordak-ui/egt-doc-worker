#!/usr/bin/env python3
"""
Uploads all .md files from src/main/resources/docs/ to Anthropic Files API.
Run once, then paste the output into your application.properties.

Usage:
    pip install anthropic
    ANTHROPIC_API_KEY=sk-ant-... python3 upload_docs.py
"""

import os
import json
from pathlib import Path

import anthropic

API_KEY = os.environ.get("ANTHROPIC_API_KEY")
if not API_KEY:
    raise ValueError("Set ANTHROPIC_API_KEY environment variable")

DOCS_DIR = Path("src/main/resources/docs")
if not DOCS_DIR.exists():
    raise FileNotFoundError(f"Docs directory not found: {DOCS_DIR}")

client = anthropic.Anthropic(api_key=API_KEY)

file_ids = {}
md_files = sorted(DOCS_DIR.glob("*.md"))

print(f"Found {len(md_files)} .md files. Uploading...\n")

for md_file in md_files:
    print(f"Uploading {md_file.name}...", end=" ")
    with open(md_file, "rb") as f:
        response = client.beta.files.upload(
            file=(md_file.name, f, "text/plain"),
        )
    file_ids[md_file.name] = response.id
    print(f"✓ {response.id}")

# Save to JSON file
output_path = Path("file_ids.json")
with open(output_path, "w") as f:
    json.dump(file_ids, f, indent=2)

print(f"\n✅ Done! Uploaded {len(file_ids)} files.")
print(f"📄 File IDs saved to: {output_path.absolute()}")
print("\nPaste this into your Railway environment variables:")
print(f"ANTHROPIC_FILE_IDS={json.dumps(file_ids)}")
