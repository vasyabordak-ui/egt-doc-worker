---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/affiliate-api/static-content/retrieve-hotel-dump/
tags:
- etg-docs
- affiliate-api
- static-content
title: Retrieve hotel dump
---

# Retrieve hotel dump

ETG API V3

Affiliate API

Static content

Retrieve hotel dump

# Retrieve hotel dump

#affiliate

Sandbox Production

```
https://api-sandbox.worldota.net/api/b2b/v3/hotel/info/dump/
```

```
https://api.worldota.net/api/b2b/v3/hotel/info/dump/
```

ℹ️

**The call is required**.

The call gets the dump of all available ETG hotels as a **single archive for a single language**. The link to the archive is passed in the `url` response field. You must open the link to start the downloading. The dump format is the Zstd.

The ETG updates the data every week. To renew your local dump, use the Retrieve hotel incremental dump call.

⚠️

- The dump download link you receive from the API is temporary and will expire **1 hour after being issued**. To download the dump, you must call the API method each time to obtain a fresh, valid link.
- Any internal content such as photos, descriptions, and others **can’t be indexed**. It isn’t allowed.
- Any public content such as name, address, amenities, and policies **can be indexed**.

## Sandbox limitations

- The value of the `language` field is always set to `en`.
- The `inventory` field supports the following values: `all`, `direct`, `preferable`, and `direct_fast`.
- In the sandbox dump, the number of items is always `1000`.
- For all objects containing the `currency_code` field, its value is always `EUR`.

## Request example

Sandbox Production

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api-sandbox.worldota.net/api/b2b/v3/hotel/info/dump/' \
--header 'Content-Type: application/json' \
--data '{
  "inventory": "all",
  "language": "en"
}'
```

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/hotel/info/dump/' \
--header 'Content-Type: application/json' \
--data '{
  "inventory": "all",
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

inventory
String
optional

The hotel list to get.

ℹ️

- The possible values:
  - `all` — all available suppliers.
  - `direct` — Chains, Extranet, DMC, Switch, Consolidator, Wholesaler.
  - `direct_fast` — Chains, Extranet.
  - `direct_fast_extended` — Chains, Extranet, DMC, Switch.
  - `preferable` — all sellable hotels that had at least one available rate in the last 6 months.
  - `top` — the 500k top hotels by their paid bookings.
- The default value is `all`.

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
    "last_update": "2024-07-01T00:38:13Z",
    "url": "https://.../partner_feed_v3_en.jsonl.zst?..." // link valid for 1 hour
  },
  "debug": null,
  "error": null,
  "status": "ok"
}
```

## Dump file structure

The dump is a Zstd archive that contains a file with a list of objects line by line:

```
{
  "address": "Gabriele-Tergit-Promenade 19, Berlin",
  "amenity_groups": [
    {
      "amenities": [
        "Common kitchen",
        "Kitchen"
      ]
    }
  ]
},
{
  "address": "Passeig De Gracia, 68, Barcelona",
  "amenity_groups": [
    {
      "amenities": [
        "Common kitchen",
        "Kitchen"
      ]
    }
  ]
},
{
  "address": "Great Cumberland Place, London",
  "amenity_groups": [
    {
     "amenities": [
        "Cable TV",
        "Locker"
      ]
    }
  ]
}
```

### Field details

The entire dump structure is described in the response of the Retrieve hotel content call.

To decompress the archive you can use, for example, a Zstandard plugin or 7-zip archive manager with Zstandard.

## Errors

The `error` field has the value specified in the headers below.

### `dump_not_ready`

The dump is being updated. Try to download the archive later.
