---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/content-api/retrieve-hotels-content-by-ids/
tags:
- etg-docs
- content-api
- retrieve-hotels-content-by-ids
title: Retrieve hotels content by IDs
---

# Retrieve hotels content by IDs

ETG API V3

Content API

Retrieve hotels content by IDs

# Retrieve hotels content by IDs

#content-api

Sandbox Production

```
https://api-sandbox.worldota.net/api/content/v1/hotel_content_by_ids/
```

```
https://api.worldota.net/api/content/v1/hotel_content_by_ids/
```

The call allow to obtain a required hotel content by id.
Use the call in cases:

- Instead of hotel static data
- You want to check the content before making a booking with a possible update.
- Use this call after the Retrieve hotel IDs by filter call.

ℹ️

Call is limited to 1200 requests per minute (QPM).

## Sandbox limitations

⚠️

Use only field values, IDs, API keys, and any static content from the sandbox environment within the sandbox. **Do not use sandbox data in test or production environments, and do not mix data or configuration between different environments.**

## Request example

Sandbox Production

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api-sandbox.worldota.net/api/content/v1/hotel_content_by_ids' \
--header 'Content-Type: application/json' \
--data '{
  "hids": [9852019],
  "language": "en"
}'
```

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/content/v1/hotel_content_by_ids/' \
--header 'Content-Type: application/json' \
--data '{
  "hids": [9852019],
  "language": "en"
}'
```

## Request body

Expand this|
Collapse this

hids
[Int]

The unique hotel IDs in the new numeric format.

ℹ️

- Each ID is an integer no longer than 10 digits.
- We are gradually migrating all clients to use this format.
- Use the values from the call Retrieve hotel IDs by filter.

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

address
String

The hotel physical address.

amenity\_groups
[Object]

The hotel amenities list divided in groups. For example, Health and Safety Measures, Beauty and wellness.

ℹ️

The full list of supported group values is available in the `amenity_translations` directory.

amenities
[String]

The list of amenities inside the group.

group\_name
String

The amenity group name.

non\_free\_amenities
[String]

The list of additional non-free services.

check\_in\_time
String

The check-in time. Local time in `HH:MM:SS` format (24-hour).

check\_in\_time\_end
String

The latest allowed check-in time in 24-hour format, or empty if unspecified.

check\_out\_time
String

The check-out time. The local time in the `HH:MM:SS` format (24-hour).

description\_struct
Object

The list of hotel descriptions divided into paragraphs.

ℹ️

Store and display it in the UI, but do not index it or use it for internal search queries or SEO.

paragraphs
[String]

The description itself.

title
String

The description title.

email
String

The hotel email address.

hotel\_chain
String

The hotel chain name.

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

images\_ext
[Object]

Information about the hotel images divided into categories.

category\_slug
String

The hotel image category.

ℹ️

- The possible values:
  - `unspecified` — unspecified.
  - `balcony` — balconies.
  - `bathroom` — bathrooms.
  - `beach` — beaches.
  - `business` — business zone.
  - `entertainment` — entertainment zones.
  - `exterior` — hotel exterior.
  - `guest_rooms` — guest rooms.
  - `hotel_front` — hotel front.
  - `hotel_rooms` — hotel rooms.
  - `lobby` — lobby.
  - `meal` — meals.
  - `outside` — hotel outside view.
  - `pool` — pools.
  - `spa` — SPA zones.
  - `sports` — sport zones.
  - `children` — children zones.
  - `miscellaneous` — other.

url
String

The URL for the hotel image.

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

kind
String

The hotel type.

ℹ️

The possible values:

- `Unspecified`,
- `Resort`.
- `Sanatorium`.
- `Guesthouse`.
- `Mini-hotel`.
- `Castle`.
- `Hotel`.
- `Boutique_and_Design`.
- `Apartment`.
- `Cottages_and_Houses`.
- `Farm`.
- `Villas_and_Bungalows`.
- `Camping`.
- `Hostel`.
- `BNB`.
- `Glamping`.
- `Apart-hotel`.

latitude
Float

The hotel geographical latitude.

longitude
Float

The hotel geographical longitude.

name
String

The hotel name.

metapolicy\_struct
Object

The additional accommodation conditions. Is the same as the section “Hotel policies” from hotel pages on the core website.

In the field, you can find taxes and fees not included in the booking price.

**The data from this param must be shown.**

ℹ️

For step-by-step instructions and code examples on processing this field, see Metapolicy\_struct.

add\_fee
[Object]

The additional services, fees, and taxes information.

currency
String

The additional services, fees, and taxes price currency. Is the same as the hotel currency.

fee\_type
String

The additional service type.

ℹ️

The possible values:

- `unspecified`.
- `television`.
- `towels`.
- `conditioning`.
- `housekeeping`.
- `heating`.
- `refrigerator`.
- `utility`.
- `safe`.
- `microwave`.
- `luggage_storage`.
- `tour_guide`.
- `bicycle_rental`.
- `baby_highchair`.
- `bed_linen`.
- `towels_only`.
- `luggage_storage_apartment`.
- `luggage_storage_office`.

price
String

The additional services, fees, and taxes price in the hotel currency.

price\_unit
String

The additional service price unit.

ℹ️

The possible values:

- `unspecified`.
- `per_guest_per_night`.
- `per_guest_per_stay`.
- `per_room_per_night`.
- `per_room_per_stay`.
- `per_hour`.
- `per_week`.

check\_in\_check\_out
[Object]

The check-in and check-out policies’ information.

check\_in\_check\_out\_type
String

The check-in or check-out type.

ℹ️

The possible values:

- `unspecified`.
- `early_checkin`.
- `late_checkout`.
- `holiday_checkin`.
- `holiday_checkout`.

currency
String

The check-in and check-out price currency. Is the same as the hotel currency.

inclusion
String

The check-in and check-out inclusion.

ℹ️

The possible values:

- `unspecified`.
- `included`.
- `not_included`.

price
String

The check-in or check-out price in the hotel currency.

children
[Object]

The children extra beds’ policy information.

age\_start
Int

The minimum child age for the children extra bed policy being applied.

age\_end
Int

The maximum child age for the children extra bed policy being applied.

currency
String

The child extra bed price currency. Is the same as the hotel currency.

extra\_bed
String

The extra bed for a child availability.

ℹ️

The possible values:

- `unspecified`.
- `available`.
- `unavailable`.

price
String

The child extra bed price in the hotel currency.

children\_meal
[Object]

The children meals’ policy information.

age\_start
Int

The minimum child age for the children meal policy being applied.

age\_end
Int

The maximum child age for the children meal policy being applied.

currency
String

The children meal price currency. Is the same as the hotel currency.

inclusion
String

The children meal inclusion.

ℹ️

The possible values:

- `unspecified`.
- `included`.
- `not_included`.

meal\_type
String

The children meal type.

ℹ️

The possible values:

- `unspecified`.
- `all-inclusive`.
- `breakfast`.
- `breakfast-buffet`.
- `continental-breakfast`.
- `dinner`.
- `full-board`.
- `half-board`.
- `lunch`.
- `nomeal`.
- `some-meal`.
- `english-breakfast`.
- `american-breakfast`.
- `asian-breakfast`.
- `chinese-breakfast`.
- `israeli-breakfast`.
- `japanese-breakfast`.
- `scandinavian-breakfast`.
- `scottish-breakfast`.
- `breakfast-for-1`.
- `breakfast-for-2`.
- `super-all-inclusive`.
- `soft-all-inclusive`.
- `ultra-all-inclusive`.
- `half-board-lunch`.
- `half-board-dinner`.

price
String

The children meal price in the hotel currency.

cot
[Object]

The cots’ policy information.

amount
Int

The available cot number.

currency
String

The cot price currency. Is the same as the hotel currency.

inclusion
String

The cot inclusion.

ℹ️

The possible values:

- `unspecified`.
- `included`.
- `not_included`.

price
String

The cot price in the hotel currency.

price\_unit
String

The cot price unit.

ℹ️

The possible values:

- `unspecified`.
- `per_guest_per_night`.
- `per_guest_per_stay`.
- `per_room_per_night`.
- `per_room_per_stay`.
- `per_hour`.
- `per_week`.

deposit
[Object]

The deposit information.

availability
String

The deposit availability.

ℹ️

The possible values:

- `unspecified`.
- `available`.
- `unavailable`.

currency
String

The deposit amount currency. Is the same as the hotel currency.

deposit\_type
String

The deposit type.

ℹ️

The possible values:

- `unspecified`.
- `pet`.
- `breakage`.
- `keys`.

payment\_type
String

The deposit payment type.

ℹ️

The possible values:

- `unspecified`.
- `cash`.
- `card`.

price
String

The deposit amount in the hotel currency.

price\_unit
String

The deposit amount unit.

ℹ️

The possible values:

- `unspecified`.
- `per_guest_per_night`.
- `per_guest_per_stay`.
- `per_room_per_night`.
- `per_room_per_stay`.
- `per_hour`.
- `per_week`.

pricing\_method
String

The deposit pricing method.

ℹ️

The possible values:

- `unspecified`.
- `percent`.
- `fixed`.

extra\_bed
[Object]

The adult extra beds’ policy information.

amount
Int

The available extra bed number.

currency
String

The extra bed price currency. Is the same as the hotel currency.

inclusion
String

The extra bed inclusion.

ℹ️

The possible values:

- `unspecified`.
- `included`.
- `not_included`.

price
String

The extra bed price in the hotel currency.

price\_unit
String

The extra bed price unit.

ℹ️

The possible values:

- `unspecified`.
- `per_guest_per_night`.
- `per_guest_per_stay`.
- `per_room_per_night`.
- `per_room_per_stay`.
- `per_hour`.
- `per_week`.

internet
[Object]

The internet policy information.

currency
String

The internet price currency. Is the same as the hotel currency.

inclusion
String

The internet inclusion.

ℹ️

The possible values:

- `unspecified`.
- `included`.
- `not_included`.

internet\_type
String

The internet type.

ℹ️

The possible values:

- `unspecified`.
- `wireless`.
- `wired`.

price
String

The internet price in the hotel currency.

price\_unit
String

The internet price unit.

ℹ️

The possible values:

- `unspecified`.
- `per_guest_per_night`.
- `per_guest_per_stay`.
- `per_room_per_night`.
- `per_room_per_stay`.
- `per_hour`.
- `per_week`.

work\_area
String

The internet coverage area.

ℹ️

The possible values:

- `unspecified`.
- `hotel`.
- `room`.

meal
[Object]

The adult meals’ policy information.

currency
String

The meal price currency. Is the same as the hotel currency.

inclusion
String

The meal inclusion.

ℹ️

The possible values:

- `unspecified`.
- `included`.
- `not_included`.

meal\_type
String

The meal type.

ℹ️

The possible values:

- `unspecified`.
- `all-inclusive`.
- `breakfast`.
- `breakfast-buffet`.
- `continental-breakfast`.
- `dinner`.
- `full-board`.
- `half-board`.
- `lunch`.
- `nomeal`.
- `some-meal`.
- `english-breakfast`.
- `american-breakfast`.
- `asian-breakfast`.
- `chinese-breakfast`.
- `israeli-breakfast`.
- `japanese-breakfast`.
- `scandinavian-breakfast`.
- `scottish-breakfast`.
- `breakfast-for-1`.
- `breakfast-for-2`.
- `super-all-inclusive`.
- `soft-all-inclusive`.
- `ultra-all-inclusive`.
- `half-board-lunch`.
- `half-board-dinner`.

price
String

The meal price in the hotel currency.

no\_show
[Object]

The no-show policy information.

availability
String

The no-show availability.

ℹ️

The possible values:

- `unspecified`.
- `available`.
- `unavailable`.

day\_period
String

The time period related to the time value, used for fields with a 12-hour format.

ℹ️

The possible values:

- `unspecified` — period is not specified; use with caution.
- `before_midday` — AM (before 12:00 noon).
- `after_midday` — PM (after 12:00 noon).

time
String

The applicable time for no-show. The local time in the `HH:MM:SS` format (24-hour).

parking
Object

The parking policy information.

currency
String

The parking price currency. Is the same as the hotel currency.

inclusion
String

The parking inclusion.

ℹ️

The possible values:

- `unspecified`.
- `included`.
- `not_included`.

price
String

The parking price in the hotel currency.

price\_unit
String

The parking price unit.

ℹ️

The possible values:

- `unspecified`.
- `per_car_per_night`.
- `per_car_per_stay`.
- `per_guest_per_night`.
- `per_guest_per_stay`.
- `per_room_per_night`.
- `per_room_per_stay`.
- `per_hour`.
- `per_week`.

territory\_type
String

The parking territory type.

ℹ️

The possible values:

- `unspecified`.
- `on_side`.
- `off_side`.

pets
[Object]

The pets’ accommodation policy information.

currency
String

The pet accommodation price currency. Is the same as the hotel currency.

inclusion
String

The pet accommodation inclusion.

ℹ️

The possible values:

- `unspecified`.
- `included`.
- `not_included`.

pets\_type
String

The pet weight type.

ℹ️

The possible values:

- `unspecified` — no information on the pet weight.
- `lt_5kg` — the pet weight is less than 5 kg.
- `gt_5kg` — the pet weight is greater than 5 kg.

price
String

The pet accommodation price in the hotel currency.

price\_unit
String

The pet accommodation price unit.

ℹ️

The possible values:

- `unspecified`.
- `per_guest_per_night`.
- `per_guest_per_stay`.
- `per_room_per_night`.
- `per_room_per_stay`.
- `per_hour`.
- `per_week`.

shuttle
[Object]

The shuttles’ policy information.

currency
String

The shuttle price currency. Is the same as the hotel currency.

destination\_type
String

The shuttle destination type.

ℹ️

The possible values:

- `unspecified`.
- `airport`.
- `train`.
- `ship`.
- `airport_train`.

inclusion
String

The shuttle inclusion.

ℹ️

The possible values:

- `unspecified`.
- `included`.
- `not_included`.

shuttle\_type
String

The shuttle type.

ℹ️

The possible values:

- `unspecified`.
- `one_way`.
- `two_ways`.

price
String

The shuttle price in the hotel currency.

visa
[Object]

The visa support policy information.

visa\_support
String

The visa support for the embassy of the hotel country.

ℹ️

The possible values:

- `unspecified` — no information on the visa support.
- `support_enable` — the visa supported is available.

metapolicy\_extra\_info
String

The additional hotel information. Is the same as the section “Extra info” from hotel pages on the core website.

In the field, you can find taxes and fees not included in the booking price.

The data from the field must be shown to the end user.

phone
String

The hotel phone number.

policy\_struct
[Object]

deprecated

The additional accommodation conditions. Is the same as the section “Important - Please Note” from hotel pages on the core website.

In the field, you can find taxes and fees not included in the booking price.

The data from the field is obligatory for showing to the end user.

paragraphs
[String]

The policy itself.

title
String

The policy title.

postal\_code
String

The hotel postal code.

region
Object

The region where the hotel is located.

country\_code
String

Hotel’s country code (capital Latin letters, according to ISO 3166-1 alpha-2 standard).

iata
String

The region airport IATA code.

id
String

deprecated

The unique ID of the region where the hotel is located.

name
String

The name of the region where the hotel is located.

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

room\_groups
Object

The list of the hotel room groups.

images
[String]

deprecated

The list of the URLs for the room images.

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

images\_ext
[Object]

Information about the room images divided into categories.

category\_slug
String

The room image category.

ℹ️

- The possible values:
  - `unspecified` — unspecified.
  - `balcony` — balconies.
  - `bathroom` — bathrooms.
  - `beach` — beaches.
  - `business` — business zone.
  - `entertainment` — entertainment zones.
  - `exterior` — hotel exterior.
  - `guest_rooms` — guest rooms.
  - `hotel_front` — hotel front.
  - `hotel_rooms` — hotel rooms.
  - `lobby` — lobby.
  - `meal` — meals.
  - `outside` — hotel outside view.
  - `pool` — pools.
  - `spa` — SPA zones.
  - `sports` — sport zones.
  - `children` — children zones.
  - `miscellaneous` — other.

url
String

The URL for the room image.

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

name
String

The full room name.

room\_amenities
[String]

The room amenity list.

To get all available room amenities and their definitions, use the `room_amenities` field from the Retrieve hotel static data call.

room\_group\_id
Int

deprecated

The room ID.

rg\_ext
Object

The room ID.

class
Int

The room class information.

ℹ️

The possible values:

- `0` — run of house.
- `1` — dorm.
- `2` — capsule.
- `3` — room.
- `4` — junior suite.
- `5` — suite.
- `6` — apartment.
- `7` — studio.
- `8` — villa.
- `9` — cottage.
- `17` — bungalow.
- `18` — chalet.
- `19` — camping.
- `20` — tent.

quality
Int

The room quality information.

ℹ️

The possible values:

- `0` — undefined.
- `1` — economy.
- `2` — standard.
- `3` — comfort.
- `4` — business.
- `5` — superior.
- `6` — deluxe.
- `7` — premier.
- `8` — executive.
- `9` — presidential.
- `17` — premium.
- `18` — classic.
- `19` — ambassador.
- `20` — grand.
- `21` — luxury.
- `22` — platinum.
- `23` — prestige.
- `24` — privilege.
- `25` — royal.

sex
Int

The room gender restrictions.

ℹ️

The possible values:

- `0` — undefined.
- `1` — male.
- `2` — female.
- `3` — mixed.

bathroom
Int

The room bathroom information.

ℹ️

The possible values:

- `0` — undefined.
- `1` — a shared bathroom.
- `2` — a private bathroom
- `3` — an external private bathroom.

bedding
Int

The room bedding information.

ℹ️

The possible values:

- `0` — undefined.
- `1` — a bunk bed.
- `2` — a single bed.
- `3` — a double bed.
- `4` — a twin bed.
- `7` — multiple beds.
- `8` — chair-bed.
- `9` — sofa.

family
Int

Whether it is a family room or not.

ℹ️

The possible values:

- `0` — not a family room.
- `1` — a family room.

capacity
Int

The maximum number of main bed places without additional charges and excluding extra beds, cots, etc.

ℹ️

The possible values:

- `0` — undefined.
- `1` — single.
- `2` — double.
- `3` — triple.
- `4` — quadruple.
- `5` — quintuple.
- `6` — sextuple.

club
Int

Whether it is a club room or not.

ℹ️

The possible values:

- `0` — not a club room.
- `1` — a club room.

bedrooms
Int

The bedroom number.

ℹ️

The possible values:

- `0` — undefined.
- `1` — 1 bedroom.
- `2` — 2 bedrooms.
- `3` — 3 bedrooms.
- `4` — 4 bedrooms.
- `5` — 5 bedrooms.
- `6` — 6 bedrooms.

balcony
Int

Whether there is a balcony or not.

ℹ️

The possible values:

- `0` — no balcony.
- `1` — a balcony.

view
Int

The room view information.

ℹ️

The possible values:

- `0` — undefined.
- `1` — bay view.
- `2` — bosphorus view.
- `3` — burj-khalifa view.
- `4` — canal view.
- `5` — city view.
- `6` — courtyard view.
- `7` — dubai-marina view.
- `8` — garden view.
- `9` — golf view.
- `17` — harbour view.
- `18` — inland view.
- `19` — kremlin view.
- `20` — lake view.
- `21` — land view.
- `22` — mountain view.
- `23` — ocean view.
- `24` — panoramic view.
- `25` — park view.
- `26` — partial-ocean view.
- `27` — partial-sea view.
- `28` — partial view.
- `29` — pool view.
- `30` — river view.
- `31` — sea view.
- `32` — sheikh-zayed view.
- `33` — street view.
- `34` — sunrise view.
- `35` — sunset view.
- `36` — water view.
- `37` — with view.
- `38` — beachfront.
- `39` — ocean front.
- `40` — sea front.

floor
Int

The room floor Information.

ℹ️

The possible values:

- `0` — undefined.
- `1` — a penthouse floor.
- `2` — a duplex floor.
- `3` — a basement floor.
- `4` — an attic floor.

size
Number

beta

Room area in square meters.

⚠️

This field is in beta and may be changed or removed in the future.

name\_struct
Object

The structured room name.

bathroom
String

The room bathroom information.

Has the `null` value, if it is a private bathroom.

bedding\_type
String

The room bedding information.

main\_name
String

The room name.

star\_rating
Int

The hotel rating on a scale from `1` to `5`. Has the `0` value for no available rating.

ℹ️

- The minimum value is `0`.
- The maximum value is `5`.

serp\_filters
[String]

The list of amenities for the hotel selection based on a cumulative set of features from:

- All the hotels’ rates.
- And common hotels’ features.

To get all available room amenities and their definitions, use the `serp_filters` field from the Retrieve hotel static data call.

star\_certificate
Object

Information about the hotel star certificate.

Applicable only for hotels in some specific countries.

Has the `null` value if there is no information about the star certificate.

valid\_to
String

The valid true date.

certificate\_id
String

The certificate ID.

Has the `null` value if there is no information about the certificate ID.

is\_closed
Boolean

Whether the hotel property is closed or not.

keys\_pickup
Object

Information about how to pick up the hotel room keys.

type
String

The picking-up type.

ℹ️

The possible values:

- `unspecified` — no instructions.
- `phone` — get the keys with a call. To get the instructions, call the phone number in the `phone` field of this object.
- `address` — get the keys on the address specified in the `apartment_office_address` field of this object.
- `smartlock` — get the keys in a smart lock. Can be opened with an app or code.
- `keypad` — get the keys in a lock with a keypad.
- `lockbox` — get the keys in a lock box.
- `reception` — get the keys at the 24-hour reception.

phone
String

The phone number for contacting purposes. The phone number must be valid.

ℹ️

- The minimum length is `5` characters.
- The maximum length is `35` characters.

is\_contactless
Boolean

Whether the hotel room keys’ pick-up is contactless or not.

email
String

The email address for contacting purposes. The email address must be valid.

apartment\_office\_address
String

The actual pick-up reception address.

apartment\_extra\_information
String

The additional pick-up information.

distance\_center
Number

beta

Distance from the property to the city center, in meters.

⚠️

This field is in beta and may be changed or removed in the future.

facts
Object

The hotel information.

floors\_number
Int

The floors number.

register
Object

Information about the hotel registration in the FSA register.

Applicable only for hotels in some specific countries.

Has the `null` value if there is no information about the registration.

record
String

Registration number in the FSA register.

link
String

Link to the FSA register.

email
String

The hotel email address in the FSA register.

phone
String

The hotel phone number in the FSA register.

status
String

Status of the object in the FSA register. Determines whether the object can be displayed and sold. Possible values:

- `Active` — the object can be both displayed and sold.
- `Suspended` — the object can be displayed, but selling is not available.
- `Terminated` — the object cannot be displayed or sold.
- `Unspecified` — status is unknown, used for unexpected values from an external source.

kind
String

The hotel type in the FSA register.

ℹ️

The possible values:

- `unspecified` — hotel type is not specified.
- `hotel` — classic hotel.
- `camping` — camping, accommodation in nature (tents, cabins, etc.).
- `resort` — resort hotel, often with leisure or recreational facilities.
- `sanatorium` — sanatorium, health and wellness facility.
- `guesthouse` — guesthouse, small private accommodation.

name
String

Name of the hotel in the FSA register.

address
String

The hotel address in the FSA register.

status\_end\_date
String

Expiry date of the object in the FSA register.

rooms
Object

Information about hotel rooms from the FSA register.

rooms\_count
Int

Number of rooms by category.

category\_type
String

Name of room categories.

fsa\_kind
String

cancelled

The hotel type in the FSA register.

ℹ️

The possible values:

- `unspecified` — hotel type is not specified.
- `hotel` — classic hotel.
- `camping` — camping, accommodation in nature (tents, cabins, etc.).
- `resort` — resort hotel, often with leisure or recreational facilities.
- `sanatorium` — sanatorium, health and wellness facility.
- `guesthouse` — guesthouse, small private accommodation.

fsa\_name
String

cancelled

Name of the hotel in the FSA register.

rooms\_number
Int

The rooms number.

year\_built
Int

The construction year.

year\_renovated
Int

The renovation year.

electricity
Object

The socket type.

frequency
[Int]

The sockets’ frequency.

voltage
[Int]

The sockets’ voltage.

sockets
[String]

The socket types.

To get all available socket types and their definitions, use the `socket_types` field from the Retrieve hotel static data call.

payment\_methods
[String]

The card types accepted at the hotel when pay there.

ℹ️

The possible values:

- `unspecified` — unspecified.
- `american_express` — American Express.
- `cash` — cash.
- `china_unionpay` — China UnionPay.
- `diners_club` — Diners Club International.
- `euro_mastercard` — Euro/Mastercard.
- `jcb` — JCB.
- `maestro` — Maestro.
- `master_card` — Mastercard.
- `switch_maestro` — Switch (Maestro).
- `visa` — Visa.
- `visa_debit` — Visa Debit.
- `vise_delta` — Visa Delta.
- `visa_electron` — Visa Electron.
- `pro100` — Pro100.

front\_desk\_time\_start
String

The reception opening time. The local time in the `HH:MM:SS` format.

front\_desk\_time\_end
String

The reception closing time. The local time in the `HH:MM:SS` format.

is\_gender\_specification\_required
Boolean

Whether the guests’ gender is required by the hotel or not.

## Response example

```
{
 "data": [
   {
     "address": "Katiet Bosua,South Sipora,Mentawai, Katiet",
     "amenity_groups": [],
     "check_in_time": "14:00:00",
     "check_in_time_end": "",
     "check_out_time": "12:00:00",
     "description_struct": [
       {
         "paragraphs": [
           "You’ll be sleeping tight! Hotel «Lances Right Villas Katiet» is located in Katiet. This hotel is located minutes away from the city center."
         ],
         "title": "Location"
       }
     ],
     "email": "",
     "hotel_chain": "No chain",
     "hid": 9852019,
     "id": "lances_right_villas_katiet",
     "images_ext": [],
     "kind": "Hotel",
     "latitude": -2.37579,
     "longitude": 99.84819,
     "name": "Lances Right Villas Katiet",
     "metapolicy_struct": {
       "add_fee": [],
       "check_in_check_out": [],
       "children": [],
       "children_meal": [],
       "cot": [],
       "deposit": [],
       "extra_bed": [],
       "internet": [],
       "meal": [],
       "no_show": {
         "availability": "unspecified",
         "day_period": "unspecified",
         "time": ""
       },
       "parking": [],
       "pets": [],
       "shuttle": [],
       "visa": {
         "visa_support": "unspecified"
       }
     },
     "metapolicy_extra_info": "To prevent the spread of the Mpox virus, the Indonesian Ministry of Health has resumed health screening for international travelers (both Indonesian and foreign nationals) entering Indonesia from August 27, 2024. This includes completing an electronic self-declaration form called SATUSEHAT Health Pass, which must be completed on a dedicated web platform.\n<ul>  <li>There is no hot water on site. </li> </ul>",
     "phone": "62-08126626702",
     "policy_struct": [
       {
         "paragraphs": [
           "Information about the type of meals included in the price is indicated in the rate details.",
           "Meals may be limited during Ramadan."
         ],
         "title": "Meals"
       },
       {
         "paragraphs": [
           "Couples checking into the same room must present a valid marriage certificate upon check-in. Otherwise, the property may reject the booking or request that a second room be booked."
         ],
         "title": "Special living conditions"
       },
       {
         "paragraphs": [
           "To prevent the spread of the Mpox virus, the Indonesian Ministry of Health has resumed health screening for international travelers (both Indonesian and foreign nationals) entering Indonesia from August 27, 2024. This includes completing an electronic self-declaration form called SATUSEHAT Health Pass, which must be completed on a dedicated web platform.",
           "<ul>  <li>There is no hot water on site. </li> </ul>"
         ],
         "title": "Extra info"
       }
     ],
     "postal_code": "25972",
     "region": {
       "country_code": "ID",
       "iata": "",
       "id": 965851524,
       "name": "Katiet",
       "type": "City"
     },
     "room_groups": [],
     "star_rating": 2,
     "serp_filters": null,
     "star_certificate": {
       "valid_to": "",
       "certificate_id": ""
     },
     "is_closed": false,
     "facts": {
       "electricity": {
         "frequency": [
           50
         ],
         "voltage": [
           230
         ],
         "sockets": [
           "c",
           "f"
         ]
       },
       "type": "",
       "floors_number": 0,
       "rooms_number": 0,
       "year_built": 0,
       "year_renovated": 0,
       "register": {
         "record": "",
         "link": "",
         "email": "",
         "phone": "",
         "status": "",
         "kind": "unspecified",
         "name": "",
         "address": "",
         "status_end_date": "",
         "rooms": []
       }
     },
     "payment_methods": null,
     "front_desk_time_start": "",
     "front_desk_time_end": "",
     "is_gender_specification_required": false,
     "deleted": false,
     "keys_pickup": {
       "type": "unspecified",
       "phone": "",
       "is_contactless": false,
       "email": "",
       "apartment_office_address": "",
       "apartment_extra_information": ""
     },
     "distance_center": 974.3,
     "giata_code": "1087889"
   }
 ],
 "debug": {
   "api_endpoint": {
     "endpoint": "api/content/v1/hotel_content_by_ids",
     "is_active": true,
     "is_limited": true,
     "remaining": 99999,
     "requests_number": 100000,
     "reset": "2026-04-13T16:21:00",
     "seconds_number": 60
   },
   "request": {
     "hids": [
       9852019
     ],
     "language": "en",
     "check_in_time_end": "23:59:00"
   },
   "method": "POST",
   "real_ip": "104.30.161.77",
   "request_id": "70f39e5f8f696faf56d9eac2aeb34a12",
   "key_id": 1234,
   "api_key_id": 1234,
   "utcnow": "2026-04-13T16:20:53.532594"
 },
 "status": "ok",
 "error": null
}
```

## Errors

The `error` field has the value specified in the headers below.

### `invalid_params`

One or more input parameters are incorrect. For more details, see the response field debug validation\_error.

### `no_content`

An internal search error. Has `500` status code.
