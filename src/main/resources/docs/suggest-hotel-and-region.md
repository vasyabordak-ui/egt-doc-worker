---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/affiliate-api/hotel-search/suggest-hotel-and-region/
tags:
- etg-docs
- affiliate-api
- hotel-search
title: Suggest hotel and region
---

# Suggest hotel and region

ETG API V3

Affiliate API

Hotel search

Suggest hotel and region

# Suggest hotel and region

#affiliate

Sandbox Production

```
https://api-sandbox.worldota.net/api/b2b/v3/search/multicomplete/
```

```
https://api.worldota.net/api/b2b/v3/search/multicomplete/
```

This call enables autocomplete: you can find a region or hotel by entering just part of its name. The response returns no more than 5 results per category.

## Sandbox limitations

⚠️

Use only field values, IDs, API keys, and any static content from the sandbox environment within the sandbox. **Do not use sandbox data in test or production environments, and do not mix data or configuration between different environments.**

- The input value for the `language` field is ignored; it is always set to `en`.
- The maximum number of items in `hotels` is `5`.
- The possible values for `hotels.region_id` are: `2011`, `2395`, `2734`, and `6053839`.
- The `regions` field is always `null`.

## Request example

Sandbox Production

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api-sandbox.worldota.net/api/b2b/v3/search/multicomplete/' \
--header 'Content-Type: application/json' \
--data '{
  "query": "Ber",
  "language": "en"
}'
```

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/search/multicomplete/' \
--header 'Content-Type: application/json' \
--data '{
  "query": "Ber",
  "language": "en"
}'
```

## Request body

Expand this|
Collapse this

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

query
String
required

The part of the hotel or region name.

## Response

Expand this|
Collapse this

hotels
Object

The hotel list.

ℹ️

- The minimum items’ number is `5`.

id
String

deprecated

The unique hotel ID in the legacy string format.

ℹ️

- Either this field or the `hid` field is required.

hid
Int

The unique hotel ID in the new numeric format.

ℹ️

- Each ID is an integer no longer than 10 digits.
- We are gradually migrating all clients to use this format.

name
String

Hotel’s name.

region\_id
Int

The region ID.

regions
Object

The region list.

ℹ️

- The maximum items’ number is `5`.

id
String

deprecated

The unique region ID.

name
String

The region name.

type
String

The region type.

ℹ️

- The possible values:
  - `Airport`.
  - `Bus Station`.
  - `City`.
  - `Continent`.
  - `Country`.
  - `Multi-City`.
  - `Multi-Railway Station`.
  - `Multi-Region`.
  - `Neighborhood`.
  - `Point of Interest`.
  - `Province`.
  - `Railway Station`.
  - `Street`.
  - `Subway`.

country\_code
String

The region country code in the ISO 3166-1 alpha-2 format.

## Response example

```
{
  "data": {
    "hotels": [
      {
        "id": "gostinitsa_berlin",
        "hid": 8066022,
        "name": "Berlin Hotel",
        "region_id": 1798
      },
      {
        "id": "bergs_hotel_2",
        "hid": 9974510,
        "name": "Bergs Spa and Сonference Hotel",
        "region_id": 5580
      },
      {
        "id": "rp_bergamo_hotel",
        "hid": 10575679,
        "name": "Bergamo Eco Hotel",
        "region_id": 6057804
      },
      {
        "id": "hotel_dacha_na_berezovoy",
        "hid": 6344433,
        "name": "Dacha Na Berezovoi Apartments",
        "region_id": 965821558
      },
      {
        "id": "travel_hotels_bereg_hotel",
        "hid": 10211188,
        "name": "Hotel Priboi by Sun City Hotels",
        "region_id": 299
      }
    ],
    "regions": [
      {
        "id": 536,
        "name": "Berlin",
        "type": "City",
        "country_code": "DE"
      },
      {
        "id": 965888340,
        "name": "Saint-Philbert-de-Grand-Lieu",
        "type": "City",
        "country_code": "FR"
      },
      {
        "id": 6340759,
        "name": "Montalbert",
        "type": "City",
        "country_code": "FR"
      },
      {
        "id": 965850065,
        "name": "Cote d'Azur",
        "type": "Multi-Region (within a country)",
        "country_code": "FR"
      },
      {
        "id": 966047519,
        "name": "Saint-Philbert-sur-Orne, Normandy",
        "type": "City",
        "country_code": "FR"
      }
    ]
  },
  "debug": {
    "request": {
      "query": "Ber",
      "language": "en"
    },
    "key_id": 7705,
    "validation_error": null
  },
  "status": "ok",
  "error": null
}
```

## Errors

The `error` field has the value specified in the headers below.

### `invalid_params`

The `query` field is required.

### `core_search_error`

An internal search error. Has `500` status code.
