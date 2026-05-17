---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/b2b-api/hotel-search/sort-hotels/
tags:
- etg-docs
- b2b-api
- hotel-search
title: Sort hotels
---

# Sort hotels

ETG API V3

B2B API

Hotel search

Sort hotels

# Sort hotels

#b2b

Sandbox Production

```
https://api-sandbox.worldota.net/api/b2b/v3/search/hotelsort/
```

```
https://api.worldota.net/api/b2b/v3/search/hotelsort/
```

The call ranks the hotels in the region. The ranking is made the internal ETG algorithm.

💡

Recommendations

Use the call when you want to determine the order of the bookings to display in a third-party product.

## Sandbox limitations

⚠️

Use only field values, IDs, API keys, and any static content from the sandbox environment within the sandbox. **Do not use sandbox data in test or production environments, and do not mix data or configuration between different environments.**

- The possible values for the `region_id` field are: `2011`, `2395`, `2734`, and `6053839`.
- The maximum value for the `hotels_limit` field is `250`.
- The maximum number of items in `hotels` is `250`.

## Request example

Sandbox Production

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api-sandbox.worldota.net/api/b2b/v3/search/hotelsort/' \
--header 'Content-Type: application/json' \
--data '{
  "region_id": 536,
  "sort_type": "b2b",
  "hotels_limit": 5
}'
```

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/search/hotelsort/' \
--header 'Content-Type: application/json' \
--data '{
  "region_id": 536,
  "sort_type": "b2b",
  "hotels_limit": 5
}'
```

## Request body

Expand this|
Collapse this

hotels\_limit
Int
optional

The maximum number of the available hotels in the response.

region\_id
Int
required

The region ID.

sort\_type
String
optional

The sort order type. Is different for the B2B and B2C clients.

ℹ️

- The possible values:
  - `b2b`.
  - `b2c`.

## Response

Expand this|
Collapse this

hotels
[String]

The hotel ID list that matches the request body.

## Response example

```
{
  "data": {
    "hotels": [
      "concorde_am_studio",
      "berlin_marriott_hotel_2",
      "novum_hotel_aldea_berlin_centrum",
      "nh_berlin_mitte",
      "best_western_premier_hotel_moa_berlin"
    ]
  },
  "debug": {
    "request": {
      "region_id": 536,
      "sort_type": "b2b",
      "hotels_limit": 5
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

- The `region_id` field is required.
- The `region_id` field is equal or greater than `0`.
- The `hotels_limit` field is equal or greater than `0`.
- The `sort_type` field is incorrect.

### `hotels_not_found`

An internal search error. Has `500` status code.
