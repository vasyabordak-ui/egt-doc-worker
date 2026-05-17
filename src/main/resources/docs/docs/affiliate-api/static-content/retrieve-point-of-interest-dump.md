---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/affiliate-api/static-content/retrieve-point-of-interest-dump/
tags:
- etg-docs
- affiliate-api
- static-content
title: Retrieve point of interest dump
---

# Retrieve point of interest dump

ETG API V3

Affiliate API

Static content

Retrieve point of interest dump

# Retrieve point of interest dump

#affiliate

```
https://api.worldota.net/api/b2b/v3/hotel/poi/dump
```

The call returns a dump with a list of hotels by various locations along with nearby points of interest (POI).

⚠️

- The dump download link you receive from the API is temporary and will expire **1 hour after being issued**. To download the dump, you must call the API method each time to obtain a fresh, valid link.
- **Content indexing isn’t allowed.**

## Request example

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/hotel/poi/dump' \
--header 'Content-Type: application/json' \
--data '{
  "language": "en"
}'
```

## Response

Expand this|
Collapse this

language
Int
required or optional

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
    "url": "https://.../partner_feed_v3_en.jsonl.zst?..." // link valid for 1 hour
    "last_update": "2025-09-15T04:47:20Z"
  },
  "debug": {
    "api_endpoint": {
      "endpoint": "api/b2b/v3/hotel/poi/dump",
      "is_active": true,
      "is_limited": true,
      "remaining": 98,
      "requests_number": 100,
      "reset": "2025-09-17T00:00:00",
      "seconds_number": 86400
    },
    "request": {
      "language": "en"
    },
    "method": "POST",
    "real_ip": "104.30.161.77",
    "request_id": "3234913e4c9dc04f843410ecd098a596",
    "key_id": 1234,
    "api_key_id": 1234,
    "utcnow": "2025-09-16T16:14:50.755175"
  },
  "status": "ok",
  "error": null
}
```

## Dump file structure

The file available at the `url` contains the following structure:

```
[
  {
    "id": "string",
    "hid": 12345,
    "pois": [
      {
        "poi_name": "string",
        "poi_name_en": "string",
        "poi_type": "string",
        "poi_subtype": "string",
        "distance": 321
      }
    ]
  }
]
```

### Field details

Expand this|
Collapse this

hotel
[Object]

Describes a hotel, providing its unique identifiers together with a list of associated points of interest (POI).

id
[String]

The unique hotel ID in the legacy string format.

ℹ️

- Either this field or the `hid` field is required.

hid
Int

The unique hotel ID in the new numeric format.

ℹ️

- Each ID is an integer no longer than 10 digits.
- We are gradually migrating all clients to use this format.

pois
[Object]

The list of nearby points of interest (POI) associated with this hotel.

poi\_name
String

The name of the point of interest in the local language.

poi\_name\_en
String

The name of the point of interest in English.

poi\_type
String

The main category or type of the point of interest (e.g., beach, airport, attraction).

ℹ️

The possible values:

- `Unspecified`,
- `Continent`.
- `Country`.
- `Multi-Region (within a country)`.
- `Province (State)`.
- `Multi-City (Vicinity)`.
- `City`.
- `Airport`.
- `Bus Station`.
- `Railway Station`.
- `Subway (Entrace)`.
- `Neighborhood`.
- `Point of Interest`.
- `Multi-Railway Station`.
- `Main Railway Station`.

poi\_subtype
String

A more specific category or subtype of the point of interest.

ℹ️

The possible values:

- `unspecified`,
- `administrative_center`.
- `arenas_and_stadiums`.
- `bars_and_restaurants`.
- `beach`.
- `buddist_temple`.
- `business_center`.
- `cableway`.
- `casino_and_gambling`.
- `church`.
- `concerts_and_performances`.
- `educational_objects`.
- `entertainment_and_games`.
- `harbor`.
- `historical_poi`.
- `hospital`.
- `library`.
- `mosque`.
- `museum`.
- `park`.
- `shopping`.
- `ski`.
- `theater`.
- `viewpoint`.
- `water_parks_and_amusement_parks`.
- `zoos_and_aquariums`.

distance
Int

The distance from the hotel to the point of interest, in meters.

## Errors

The `error` field has the value specified in the headers below.

### `dump_not_ready`

The dump is in processing. Try to send the request later.
