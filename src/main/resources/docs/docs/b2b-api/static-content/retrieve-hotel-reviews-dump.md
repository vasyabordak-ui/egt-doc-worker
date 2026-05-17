---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/b2b-api/static-content/retrieve-hotel-reviews-dump/
tags:
- etg-docs
- b2b-api
- static-content
title: Retrieve hotel reviews’ dump
---

# Retrieve hotel reviews’ dump

ETG API V3

B2B API

Static content

Retrieve hotel reviews’ dump

# Retrieve hotel reviews’ dump

#b2b

Sandbox Production

```
https://api-sandbox.worldota.net/api/b2b/v3/hotel/reviews/dump/
```

```
https://api.worldota.net/api/b2b/v3/hotel/reviews/dump/
```

The call gets the dump with the hotels reviews of the ETG clients.

ℹ️

The dump should be updated every week.

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
curl --user '<KEY_ID>:<API_KEY>' 'https://api-sandbox.worldota.net/api/b2b/v3/hotel/reviews/dump/' \
--header 'Content-Type: application/json' \
--data '{
  "language": "en"
}'
```

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/hotel/reviews/dump/' \
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

## Dump file structure

The dump is a GZIP archive that contains a file with hotel IDs and objects with theirs reviews.

```
{
  "corendon_vitality_hotel_amsterdam": {
    "rating": 9,
    "detailed_ratings": {
      "cleanness": 8.6,
      "location": 7.9,
      "price": 8.6,
      "services": 8.4,
      "room": 9.5,
      "meal": 10,
      "wifi": 10,
      "hygiene": null
    },
    "reviews": [
      {
        "review_plus": "It is near the metro m51 which is good for reaching any place of city. Breakfast is really good. Clean confortable and with great view",
        "review_minus": "Nothing all good",
        "created": "2022-05-17",
        "author": "Alban",
        "adults": 2,
        "children": 0,
        "room_name": "Standard Double room (full double bed) (bed type is subject to availability)",
        "nights": 2,
        "images": [],
        "detailed": {
          "cleanness": 10,
          "location": 10,
          "price": 8,
          "services": 10,
          "room": 10,
          "meal": 10,
          "wifi": "perfect",
          "hygiene": "unspecified"
        },
        "traveller_type": "couple",
        "trip_type": "leisure",
        "rating": 9
      }
    ]
  }
}
```

### Field details

The entire dump structure is described in the response of the Retrieve hotels review by IDs call.

## Errors

The `error` field has the value specified in the headers below.

### `dump_not_ready`

The dump is in processing. Try to send the request later.
