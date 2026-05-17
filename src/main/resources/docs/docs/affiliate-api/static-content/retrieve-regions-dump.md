---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/affiliate-api/static-content/retrieve-regions-dump/
tags:
- etg-docs
- affiliate-api
- static-content
title: Retrieve regions’ dump
---

# Retrieve regions’ dump

ETG API V3

Affiliate API

Static content

Retrieve regions’ dump

# Retrieve regions’ dump

#affiliate

Sandbox Production

```
https://api-sandbox.worldota.net/api/b2b/v3/hotel/region/dump/
```

```
https://api.worldota.net/api/b2b/v3/hotel/region/dump/
```

The call gets the dump with the hotels of all available ETG regions.

ℹ️

The dump should be updated every week.

The ETG generates **one archive only**.

⚠️

- The dump download link you receive from the API is temporary and will expire **1 hour after being issued**. To download the dump, you must call the API method each time to obtain a fresh, valid link.
- **Content indexing isn’t allowed.**

## Sandbox limitations

⚠️

Use only field values, IDs, API keys, and any static content from the sandbox environment within the sandbox. **Do not use sandbox data in test or production environments, and do not mix data or configuration between different environments.**

- In the dump, the number of items is `1000`.
- For all objects with the `region_id` field, the possible values are: `2011`, `2395`, `2734`, and `6053839`.
- For all objects containing the `currency_code` field, its value is always `EUR`.

## Request example

Sandbox Production

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api-sandbox.worldota.net/api/b2b/v3/hotel/region/dump/'
```

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/hotel/region/dump/'
```

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

The dump is a Zstd archive that contains a file with a list of objects line by line:

```
[
  {
    "country_name": {
      "ar": null,
      "bg": "Полша"
      // other languages...
    }
  },
  {
    "country_name": {
      "ar": "الولايات المتحدة الأمريكية",
      "bg": "Съединени щати"
      // other languages...
    }
  }
]
```

Each object in its turn contains a region’s description (each line is a single region in `json` format).

### Structure of the region

```
{
  "country_name": {
    "ar": "باربادوس",
    "bg": "Барбадос",
    "de": "Barbados",
    "el": "Μπαρμπάντος",
    "en": "Barbados",
    "es": "Barbados",
    "fr": "Barbade",
    "hu": "Barbados",
    "it": "Barbados",
    "pl": "Barbados",
    "pt": "Barbados",
    "ro": "Barbados",
    "sq": "Barbadosi",
    "sr": "Барбадос",
    "tr": "Barbados"
  },
  "country_code": "BB",
  "center": {
    "longitude": -59.618847,
    "latitude": 13.101827
  },
  "hids": [
    101,
    102,
    103
  ],
  "hotels": [
    "radisson_aquatica_resort_barbados",
    "spacious_colonial",
    "malfranza_apartments",
    "island_inn_all_inclusive_hotel",
    "hilton_barbados_resort",
    "sweetfield_manor_historic_inn_",
    "bellevue_plantation",
    "nautilus_beach_apartments",
    "walmer_lodge_apartments",
    "melbourne_apartments_3",
    "the_barbados_chi_centre",
    "paradise_villas_2",
    "beach_nest",
    "3_angels",
    "believe_caribbean_apartments",
    "bellevue_plantation_polo_club"
  ],
  "iata": "BGI",
  "id": 554,
  "type": "City",
  "name": {
    "ar": "بريدج تاون",
    "bg": null,
    "de": "Bridgetown",
    "el": "Bridgetown",
    "en": "Bridgetown",
    "es": "Bridgetown",
    "fr": "Bridgetown",
    "hu": null,
    "it": "Bridgetown",
    "pl": "Bridgetown",
    "pt": "Bridgetown",
    "ro": null,
    "sq": null,
    "sr": null,
    "tr": null
  }
}
```

### Field details

Expand this|
Collapse this

country\_name
Object

The name of the region’s country in all languages available at Emerging Travel Group.

country\_code
String

The region country code in the ISO 3166-1 alpha-2 format.

center
[Object]

Geographical coordinates of the region’s center.

longitude
Float

The region’s center geographical longitude.

latitude
Float

The region’s center geographical latitude.

hids
Object

Array of internal hotel IDs.

ℹ️

Field may be missing, null, or [ ] (empty array) if there are no items.

hotels
Object

The list of hotel IDs that are located in the region.

iata
String

Three-letter IATA code.

id
String

The unique region ID.

type
String

The type of the region where the hotel is located.

ℹ️

- The possible values:
  - `Airport`.
  - `Bus Station`.
  - `City`.
  - `Continent`.
  - `Country`.
  - `Multi-City (Vicinity)`.
  - `Multi-Railway Station`.
  - `Multi-Region (within a country)`.
  - `Neighborhood`.
  - `Point of Interest`.
  - `Province (State)`.
  - `Railway Station`.
  - `Street`.
  - `Subway (Entrace)`.

name
Object

The name of the region in all languages available at Emerging Travel Group.

To decompress the archive you can use, for example, a Zstandard plugin or 7-zip archive manager with Zstandard.

## Errors

The `error` field has the value specified in the headers below.

### `dump_not_ready`

The dump is in processing. Try to send the request later.
