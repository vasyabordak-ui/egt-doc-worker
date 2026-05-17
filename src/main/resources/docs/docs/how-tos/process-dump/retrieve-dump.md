---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/how-tos/process-dump/retrieve-dump/
tags:
- etg-docs
- how-tos
- process-dump
title: Retrieve dump
---

# Retrieve dump

ETG API V3

How-tos

Process dump

Retrieve dump

# Retrieve dump

#how-tos

⚠️

The dump size can exceed 2 GB.

## Request a dump

About a dump.

Make one of the calls:

- Retrieve hotel dump (B2B, Affiliate).
- Retrieve hotel custom dump (B2B, Affiliate).
- Retrieve hotel incremental dump (B2B, Affiliate).
- Retrieve hotel reviews’ dump (B2B, Affiliate).
- Retrieve regions’ dump (B2B, Affiliate).
- Retrieve hotel reviews’ incremental dump (B2B, Affiliate).

## Extract the `url` field value from the call response

The `url` field value has the download link for the dump.

## Full code

Node.jsPython

```
 1import fetch from 'node-fetch';
 2
 3// keyId is the API key ID
 4// apiKey is the API key access token
 5async function retrieveDump(keyId, apiKey) {
 6  let dumpResponse = await fetch('https://api.worldota.net/api/b2b/v3/hotel/info/dump/', {
 7    method: 'POST',
 8    headers: {
 9      'Content-Type': 'application/json',
10      'Authorization': 'Basic ' + Buffer.from(keyId + ':' + apiKey).toString('base64')
11    },
12    body: JSON.stringify({
13      'inventory': 'all',
14      'language': 'en'
15    })
16  });
17  const json = await dumpResponse.json();
18  return json.data.url;
19}
20
21await retrieveDump(keyId, apiKey);
```

```
 1import base64
 2import json
 3import requests
 4
 5# keyId is the API key ID
 6# apiKey is the API key access token
 7def retrieve_dump(key_id, api_key):
 8    encoded_credentials = base64.b64encode(f"{key_id}:{api_key}".encode("ascii")).decode("ascii")
 9    r = requests.post(
10        url="https://api.worldota.net/api/b2b/v3/hotel/info/dump/",
11        json={"inventory": "all", "language": "en"},
12        headers={
13            "Content-Type": "application/json",
14            "Authorization": f"Basic {encoded_credentials}"
15        }
16    )
17    return r.json()["data"]["url"]
18
19
20if __name__ == "__main__":
21    url = retrieve_dump(key_id, api_key)
```
