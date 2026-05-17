---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/affiliate-api/static-content/retrieve-hotel-custom-dump/
tags:
- etg-docs
- affiliate-api
- static-content
title: Retrieve hotel custom dump
---

# Retrieve hotel custom dump

ETG API V3

Affiliate API

Static content

Retrieve hotel custom dump

# Retrieve hotel custom dump

#affiliate

```
https://api.worldota.net/api/b2b/v3/hotel/custom/dump/
```

The call gets the dump customized by the request body.

Custom dumps (e.g., `ski`, `usa`, etc.) are curated manually or defined by internal business rules. Their content and criteria may change without notice and are not exhaustive.

For a comprehensive hotel selection by location or attributes, use the Retrieve hotel dump and apply your own filters.

⚠️

The dump download link you receive from the API is temporary and will expire **1 hour after being issued**.

To download the dump, you must call the API method each time to obtain a fresh, valid link.

## Request example

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/hotel/custom/dump/' \
--header 'Content-Type: application/json'
--data '{
  "type": "ski",
  "language": "en"
}'
```

## Request body

Expand this|
Collapse this

type
String
required

Type of the custom hotel dump.

ℹ️

The possible values:

- `ski`.
- `apartments`.
- `usa`.
- `rome`.
- `london_apartments`.
- `uk`.
- `africa_preferable`.
- `asia_preferable`.
- `south_europe_cis_preferable`.
- `latin_america_preferable`.
- `middle_east_preferable`.
- `north_america_preferable`.
- `oceania_preferable`.
- `west_europe_preferable`.

language
String
optional

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
