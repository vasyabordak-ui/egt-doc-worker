---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/content-api/retrieve-hotel-ids-by-filter/
tags:
- etg-docs
- content-api
- retrieve-hotel-ids-by-filter
title: Retrieve hotel IDs by filter
---

# Retrieve hotel IDs by filter

ETG API V3

Content API

Retrieve hotel IDs by filter

# Retrieve hotel IDs by filter

#content-api

Sandbox Production

```
https://api-sandbox.worldota.net/api/content/v1/hotel_ids_by_filter/
```

```
https://api.worldota.net/api/content/v1/hotel_ids_by_filter/
```

The call gets the identifiers for all required hotels. Use fields to search only for the desired hotels.

ℹ️

- Use this call instead of call Retrieve hotel dump.
- Use this call after Retrieve filter values call.
- Call is limited to 60 requests per minute (QPM).

## Sandbox limitations

⚠️

Use only field values, IDs, API keys, and any static content from the sandbox environment within the sandbox. **Do not use sandbox data in test or production environments, and do not mix data or configuration between different environments.**

The value of the `country` filter can only be one of the following:

- `59`,
- `153`,
- `189`,
- `201`.

## Request example

Sandbox Production

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api-sandbox.worldota.net/content/v1/hotel_ids_by_filter/' \
--header 'Content-Type: application/json' \
--data '{
  "serp_filter": [
    "air_conditioning",
    "has_airport_transfer",
    "beach",
    "has_internet",
    "has_kids",
    "kitchen"
  ],
  "kind": [
    "Mini-hotel"
  ],
  "country": [
    153
  ],
  "updated_since": "2006-01-02 15:04:05"
}'
```

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/content/v1/hotel_ids_by_filter/' \
--header 'Content-Type: application/json' \
--data '{
  "serp_filter": [
    "air_conditioning",
    "has_airport_transfer",
    "beach",
    "has_internet",
    "has_kids",
    "kitchen"
  ],
  "kind": [
    "Mini-hotel"
  ],
  "country": [
    153
  ],
  "updated_since": "2006-01-02 15:04:05"
}'
```

## Request body

Expand this|
Collapse this

country
[Int]
optional

The country.

ℹ️

- Use the `value` field from the call Retrieve filter values.

kind
[String]
optional

The hotel type.

ℹ️

- The list of possible `kind` field values is obtained from the call Retrieve filter values.

star\_rating
[Int]
optional

The hotel rating on a scale from `1` to `5`. Has the `0` value for no available rating.

ℹ️

- The list of possible `star_rating` field values is obtained from the call Retrieve filter values.

serp\_filters
[Srting]
optional

The list of hotel amenities. Accepts a list of limited hotel amenities of amenity values. This limit is not stable and may vary between different rates within the same hotel — some values may be applicable for one rate but not considered for another.

ℹ️

Supported values:

- has\_airport\_transfer.
- has\_parking.
- air-conditioning.
- has\_internet.
- has\_breakfast.

ℹ️

- Use the `value` field from the call Retrieve filter values.

updated\_since
String
optional

Hotels where the information has been updated.

ℹ️

The format of this field is: “2006-01-02 15:04:05”.

supplier\_type
String
optional

Which suppliers to search for.

ℹ️

- The possible values:
  - `all` — all available suppliers.
  - `direct` — Chains, Extranet, DMC, Switch, Consolidator, Wholesaler.
  - `direct_fast` — Chains, Extranet.
  - `direct_fast_extended` — Chains, Extranet, DMC, Switch.
- The default value is `all`.

preferable
Boolean
optional

All sellable hotels that had at least one available rate in the last 6 months.

top
Boolean
optional

500k best hotels by their paid bookings.

## Response

Expand this|
Collapse this

hids
[Int]

A list of unique hotel IDs in the new numeric format.

ℹ️

- Each ID is an integer no longer than 10 digits.
- We are gradually migrating all clients to use this format.

ids
[String]

A list of unique hotel IDs in the legacy string format.

## Response example

```
{
  "data": {
    "hids": [
      6325458,
      6493239,
      6637691,
      7817631,
      7834638,
      7887172,
      8384504,
      8476876,
      8718392,
      8733342,
      8745197,
      8745735,
      8752061,
      8851791,
      8873623,
      8879233,
      8889019,
      9086579,
      9091658,
      9775775,
      9989310,
      10005806,
      10011381,
      10012664,
      10013571,
      10016126,
      10052869,
      10218662,
      11237355,
      13108680,
      13350497,
      13427279
    ],
    "ids": [
      "afrodita_hills",
      "aist_9",
      "amiko_club_minihotel",
      "anapa_city",
      "anna_hotel_3",
      "apartotel_more_v",
      "beryozka_yug_mini_hotel",
      "bliss_guest_house_minihotel",
      "bulgakov_mini_hotel_2",
      "complex_of_rooms_zoloto_azarta",
      "delfiniya",
      "flamingo_hotel_12",
      "katrane_minihotel",
      "labirint_mini_hotel",
      "lavanda_13",
      "lavanda_13",
      "lazurnyij_bereg_2",
      "mechta_minihotel",
      "minihotel_hotel_yurzuf",
      "minihotel_olimpijskij_vizit",
      "minihotel_slavyanskij",
      "miniotel_foros",
      "more_i_gory_minihotel",
      "na_more_minihotel",
      "planeta_7",
      "priliv_hostel",
      "semeinyi_2",
      "semeinyi_rai",
      "skazka_3",
      "villa_lana_hotel",
      "volgavolga",
      "zdorove_minihotel"
    ]
  },
  "debug": {
    "api_endpoint": {
      "endpoint": "api/content/v1/hotel_ids_by_filter",
      "is_active": true,
      "is_limited": true,
      "remaining": 9,
      "requests_number": 10,
      "reset": "2026-01-22T13:33:00",
      "seconds_number": 60
    },
    "request": {
      "serp_filter": [
        "air_conditioning",
        "has_airport_transfer",
        "beach",
        "has_internet",
        "has_kids",
        "kitchen"
      ],
      "kind": [
        "Mini-hotel"
      ],
      "country": [
        153
      ],
      "updated_since": "2006-01-02 15:04:05"
    },
    "method": "POST",
    "real_ip": "104.30.161.77",
    "request_id": "a67fbfc9680969b458a2b23411a810c1",
    "key_id": 1234,
    "api_key_id": 1234,
    "utcnow": "2026-01-22T13:32:35.487010"
  },
  "status": "ok",
  "error": null
}
```

## Errors

The `error` field has the value specified in the headers below.

### `invalid_params`

One or more input parameters are incorrect. For more details, see the response field debug validation\_error.

### `no_hotel_ids`

An internal search error. Has `500` status code.
