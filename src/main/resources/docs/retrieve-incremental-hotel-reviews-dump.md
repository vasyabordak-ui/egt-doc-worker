---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/affiliate-api/static-content/retrieve-incremental-hotel-reviews-dump/
tags:
- etg-docs
- affiliate-api
- static-content
title: Retrieve hotel reviews’ incremental dump
---

# Retrieve hotel reviews’ incremental dump

ETG API V3

Affiliate API

Static content

Retrieve hotel reviews’ incremental dump

# Retrieve hotel reviews’ incremental dump

#affiliate

Sandbox Production

```
https://api-sandbox.worldota.net/api/b2b/v3/hotel/incremental_reviews/dump/
```

```
https://api.worldota.net/api/b2b/v3/hotel/incremental_reviews/dump/
```

The call gets the dump with the hotel reviews of the ETG clients whose reviews have been added from the previous update.

ℹ️

The dump:

- Is incremental.
- Should be updated every week.

The ETG generates **each archive for a single language**.

⚠️

- The dump download link you receive from the API is temporary and will expire **1 hour after being issued**. To download the dump, you must call the API method each time to obtain a fresh, valid link.
- The ETG can’t share the TripAdvisor reviews via the API.
- **Reviews indexing isn’t allowed.**

## Sandbox limitations

⚠️

Use only field values, IDs, API keys, and any static content from the sandbox environment within the sandbox. **Do not use sandbox data in test or production environments, and do not mix data or configuration between different environments.**

- The value of the `language` field is always set to `en`.
- The maximum number of items in the dump is `1000`.
- For all objects containing the `currency_code` field, its value is always `EUR`.

## Request example

Sandbox Production

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api-sandbox.worldota.net/api/b2b/v3/hotel/incremental_reviews/dump/' \
--header 'Content-Type: application/json' \
--data '{
  "language": "en"
}'
```

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/hotel/incremental_reviews/dump/' \
--header 'Content-Type: application/json' \
--data '{
  "language": "en"
}'
```

## Request body

Expand this|
Collapse this

language
String
required

The language.

ℹ️

The possible values:

- `ar` — Arabic.
- `bg` — Bulgarian.
- `cs` — Czech.
- `da` — Danish.
- `de` — German.
- `el` — Greek.
- `en` — English.
- `es` — Spanish.
- `fi` — Finnish.
- `fr` — French.
- `he` — Hebrew.
- `hu` — Hungarian.
- `it` — Italian.
- `ja` — Japanese.
- `kk` — Kazakh.
- `ko` — Korean.
- `nl` — Dutch.
- `no` — Norwegian Bokmål.
- `pl` — Polish.
- `pt` — Portuguese.
- `pt_PT` — European Portuguese.
- `ro` — Romanian.
- `ru` — Russian.
- `sq` — Albanian.
- `sr` — Serbian.
- `sv` — Swedish.
- `th` — Thai.
- `tr` — Turkish.
- `uk` — Ukrainian.
- `vi` — Vietnamese.
- `zh_CN` — Simplified Chinese.
- `zh_TW` — Traditional Chinese.

## Response

Expand this|
Collapse this

url
String

A direct link to download the dump file.

last\_update
DateTime

The date and time when this dump was last updated (in UTC, ISO 8601 format).

## Response example

```
{
  "data": {
    "last_update": "2020-07-01T00:38:13Z",
    "url": "https://.../partner_feed_v3_en.jsonl.zst?..." // link valid for 1 hour
  },
  "debug": null,
  "error": null,
  "status": "ok"
}
```

## Errors

The `error` field has the value specified in the headers below.

### `dump_not_ready`

The dump is in processing. Try to send the request later.
