---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/content-api/retrieve-hotels-review-by-ids/
tags:
- etg-docs
- content-api
- retrieve-hotels-review-by-ids
title: Retrieve hotels review by IDs
---

# Retrieve hotels review by IDs

ETG API V3

Content API

Retrieve hotels review by IDs

# Retrieve hotels review by IDs

#content-api

Sandbox Production

```
https://api-sandbox.worldota.net/api/content/v1/hotel_reviews_by_ids
```

```
https://api.worldota.net/api/content/v1/hotel_reviews_by_ids/
```

In this call can get reviews on the requested hotel IDs.

ℹ️

- Use this call instead of call Retrieve hotel reviews’ dump.
- Use this call after Retrieve hotels content by IDs.
- Call is limited to 1200 requests per minute (QPM).

## Sandbox limitations

⚠️

Use only field values, IDs, API keys, and any static content from the sandbox environment within the sandbox. **Do not use sandbox data in test or production environments, and do not mix data or configuration between different environments.**

## Request example

Sandbox Production

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api-sandbox.worldota.net/api/content/v1/hotel_reviews_by_ids/' \
--header 'Content-Type: application/json' \
--data '{
    "hids": [6291688,
            6296387,
            6296716,
            6303545,
            6303742,
            6304320,
            6304603,
            6304886,
            6304887,
            6314064,
            6314532,
            6315546,
            6317902,
            6333750,
            6334068,
            6344525,
            6352359,
            6353673,
            6374316],
    "language": "en"
}'
```

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/content/v1/hotel_reviews_by_ids/' \
--header 'Content-Type: application/json' \
--data '{
    "hids": [6291688,
            6296387,
            6296716,
            6303545,
            6303742,
            6304320,
            6304603,
            6304886,
            6304887,
            6314064,
            6314532,
            6315546,
            6317902,
            6333750,
            6334068,
            6344525,
            6352359,
            6353673,
            6374316],
    "language": "en"
}'
```

## Request body

Expand this|
Collapse this

hids
[Int]

A list of unique hotel IDs in the new numeric format.

ℹ️

- Each ID is an integer no longer than 10 digits.
- We are gradually migrating all clients to use this format.
- Use the `hids` field from the call Retrieve hotel IDs by filter.

ids
[String]

deprecated

A list of unique hotel IDs in the legacy string format.

language
String
required

The language.

ℹ️

- Use the `value` field from the call Retrieve filter values.

## Response

Expand this|
Collapse this

id
String

deprecated

The unique hotel ID in the legacy string format.

hid
Int

The unique hotel IDs in the new numeric format.

review
[Object]

A list of user reviews about the hotel.

id
int

deprecated

Unique review identifier.

review\_plus
String

Positive comment, highlighting what the guest liked during their stay.

review\_minus
String

Negative comment, describing what the guest disliked during their stay.

created
String

Date and time when the review was submitted.

author
String

Name of the guest who left the review.

adults
Int

Number of adults staying in the room for the reviewed booking.

children
Int

Number of children staying in the room for the reviewed booking.

room\_name
String

The name and description of the room that was booked.

nights
Int

Number of nights the guest stayed.

images
[String]

The list of the URLs for the hotel images.

⚠️

Please use the `images_ext` field instead of this one.

ℹ️

- The recommended method for displaying these images in our UI (e.g., direct linking to the provided URLs).
- We do not block users arbitrarily; restrictions are only applied in case of suspected DDoS attacks.
- Each URL has the `{size}` placeholder. Meaning, the size of the image you can get.
  - The possible size meanings:
    - `crop` — the image is fit by the width and is cut equally from the bottom and top till the middle part of the height.
    - `fit-h` — the image is fit into the rectangle by the height.
    - `fit-w` — the image is scaled so that its width fits the given value, and the height is adjusted proportionally.
    - `fit-w-min` — the image is scaled so that its width is at least the specified value (if it’s smaller, it is enlarged; if it’s larger, it is reduced), while the height is adjusted proportionally. No cropping occurs.
    - `fit` — the image is fit into the rectangle by the size in question.
  - The possible values:
    - `1024x768` — fit.
    - `x220` — fit-h.
    - `x500` — fit-h.
    - `x768` — fit-h.
    - `40x40` — crop.
    - `80x80` — crop.
    - `100x100` — crop.
    - `120x120` — crop.
    - `241x241` — crop.
    - `240x240` — crop.
    - `154x105` — crop.
    - `170x154` — crop.
    - `640x350` — crop.
    - `320x175` — crop.
    - `200x200` — crop.
    - `1080x522` — crop.
    - `750x400` — crop.
    - `640x400` — crop.
    - `120x90` — crop.
    - `90x75` — crop.
    - `x300` — fit-h.
    - `x600` — fit-h.
    - `100x130` — crop.
    - `x296` — fit-h.
    - `100x50` — crop.
    - `x100` — fit-h.
    - `100x` — fit-w.
    - `640x230` — crop.
    - `370x` — fit-w.
    - `645x255` — crop.
    - `450x161` — crop.
    - `x102` — fit-h.
    - `225x60` — fit.
    - `828x560` — fit.
    - `640x640` — crop.
    - `196x196` — crop.
    - `1298x` — fit-w.
    - `295x220` — crop.
    - `2048x2048` — crop.
    - `304x` — fit-w.
    - `304x140` — crop.
    - `x700` — fit-h.
    - `112x112` — crop.
    - `695x` — fit-w-min.
    - `1300x620` — fit-w.
    - `2600x1240` — fit-w.
    - `600x313` — fit-w.
    - `1200x616` — fit-w.
    - `326x220` — fit-w.
    - `768x1024` — fit-h.
    - `900x900` — crop.
    - `1920x1080` — fit.
    - `1080x1920` — fit-h.
    - `x1080` — fit-h.
    - `x1920` — fit-h.
    - `1920x` — fit-w.
    - `1080x` — fit-w.

detailed\_review
[Object]

Detailed ratings for different aspects of the stay (see subfields below).

cleanness
Int

Guest rating of the cleanliness.

ℹ️

- The minimum value is `0`.
- The maximum value is `10`.

location
Int

Guest rating of the location.

ℹ️

- The minimum value is `0`.
- The maximum value is `10`.

price
Int

Value for money rating.

ℹ️

- The minimum value is `0`.
- The maximum value is `10`.

services
Int

Rating of the provided services.

ℹ️

- The minimum value is `0`.
- The maximum value is `10`.

room
Int

Rating of the room itself.

ℹ️

- The minimum value is `0`.
- The maximum value is `10`.

meal
Int

Rating of the meals.

ℹ️

- The minimum value is `0`.
- The maximum value is `10`.

wifi
String

Guest’s feedback on the Wi-Fi; may be `unspecified` if not reviewed.

hygiene
String

Guest’s feedback on hygiene; may be `unspecified` if not reviewed.

traveller\_type
String

Type of traveller (e.g., `solo`, `couple`, `family`, or `unspecified` if not provided).

trip\_type
String

Type of trip, such as `leisure` or `business`.

rating
Int

The hotel rating on a scale from `1` to `5`. Has the `0` value for no available rating.

ℹ️

- The minimum value is `0`.
- The maximum value is `5`.

## Response example

```
{
  "data": [
    {
      "id": "black_shrimps_house",
      "hid": 6314532,
      "reviews": []
    },
    {
      "id": "jaz_fanara_residence_2",
      "hid": 6374316,
      "reviews": []
    },
    {
      "id": "aladdin_beach_resort_",
      "hid": 6353673,
      "reviews": []
    },
    {
      "id": "le_mirage_new_tower",
      "hid": 6296716,
      "reviews": []
    },
    {
      "id": "les_appartements_de_la_concorde",
      "hid": 6317902,
      "reviews": []
    },
    {
      "id": "chalets_in_porto_sokhna_resort_units__20117__12018__20411",
      "hid": 6296387,
      "reviews": []
    },
    {
      "id": "fleur_du_nil_2",
      "hid": 6303742,
      "reviews": []
    },
    {
      "id": "dessole_royal_rojana_resort_",
      "hid": 6333750,
      "reviews": []
    },
    {
      "id": "dessole_pyramisa_beach_resort_y_sahl_hasheesh_",
      "hid": 6304886,
      "reviews": []
    },
    {
      "id": "bedouin_house",
      "hid": 6303545,
      "reviews": []
    },
    {
      "id": "dahab_sea_view_apartment",
      "hid": 6304320,
      "reviews": []
    },
    {
      "id": "dahab_beach_homes",
      "hid": 6314064,
      "reviews": []
    },
    {
      "id": "senmut_luxor_hotel",
      "hid": 6334068,
      "reviews": []
    },
    {
      "id": "aliyah_lodge",
      "hid": 6304887,
      "reviews": []
    },
    {
      "id": "lagoon_hotel_and_spa_alexandria",
      "hid": 6291688,
      "reviews": []
    },
    {
      "id": "dreamers_hotel",
      "hid": 6344525,
      "reviews": []
    },
    {
      "id": "amenophis_hotel",
      "hid": 6352359,
      "reviews": []
    },
    {
      "id": "el_gouna_hill_villa",
      "hid": 6304603,
      "reviews": []
    }
  ],
  "debug": {
    "api_endpoint": {
      "endpoint": "api/content/v1/hotel_reviews_by_ids",
      "is_active": true,
      "is_limited": true,
      "remaining": 9,
      "requests_number": 10,
      "reset": "2026-01-30T10:13:00",
      "seconds_number": 60
    },
    "request": {
      "hids": [
        6291688,
        6296387,
        6296716,
        6303545,
        6303742,
        6304320,
        6304603,
        6304886,
        6304887,
        6314064,
        6314532,
        6315546,
        6317902,
        6333750,
        6334068,
        6344525,
        6352359,
        6353673,
        6374316
      ],
      "language": "en"
    },
    "method": "POST",
    "real_ip": "104.30.161.77",
    "request_id": "19600da4bbe2928a60c854ab27fe5e70",
    "key_id": 1234,
    "api_key_id": 1234,
    "utcnow": "2026-01-30T10:12:38.305275"
  },
  "status": "ok",
  "error": null
}
```

## Errors

The `error` field has the value specified in the headers below.

### `invalid_params`

One or more input parameters are incorrect. For more details, see the response field debug validation\_error.

### `no_hotel_reviews`

An internal search error. Has `500` status code.
