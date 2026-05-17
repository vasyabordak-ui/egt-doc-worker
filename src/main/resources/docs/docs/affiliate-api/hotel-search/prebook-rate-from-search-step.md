---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/affiliate-api/hotel-search/prebook-rate-from-search-step/
tags:
- etg-docs
- affiliate-api
- hotel-search
title: Prebook rate from search step
---

# Prebook rate from search step

ETG API V3

Affiliate API

Hotel search

Prebook rate from search step

# Prebook rate from search step

#affiliate

This call is available upon request. Please contact our API support team to gain access.

Sandbox Production

```
https://api-sandbox.worldota.net/api/b2b/v3/serp/prebook/
```

```
https://api.worldota.net/api/b2b/v3/serp/prebook/
```

ℹ️

- This call is recommended.
- If method is not used:
  - The risk of receiving a soldout error at the booking stage increases.
  - The order confirmation process will take longer.
- Request this call right after the SERP calls withing 38 minutes.

The call checks if the requested rate is still available before it can be booked and improves the booking success rate:

- When the original rate is not available, the system searches only for alternative rates of the same room type and board type (e.g., switching between refundable and non-refundable conditions within the `price_increase_percent` limit). The search does not extend to different room types or board types. If an alternative rate is found, new `book_hash` and `match_hash` values are returned.
- In cases where the same rate cannot be found and a price increase is permitted via the `price_increase_percent` field. The call will attempt to find the same room and board type at an increased price, but not exceeding the allowed percentage. The value in the `payment_options` field may change.

ℹ️

The limitations:

- The recommended call timeout is 60 seconds.
- The minimum call timeout is 30 seconds.

## Sandbox limitations

⚠️

Use only field values, IDs, API keys, and any static content from the sandbox environment within the sandbox. **Do not use sandbox data in test or production environments, and do not mix data or configuration between different environments.**

- The maximum number of items in `hotels.rates` is `5`.
- The following fields are not supported:
  - `hotels.rates.payment_options.payment_types.cancellation_penalties.policies.commission_info.charge.amount_gross`
  - `hotels.rates.payment_options.payment_types.cancellation_penalties.policies.commission_info.charge.amount_commission`
- The following errors are not supported: `no_available_rates`, `unknown`.

## Request example

Sandbox Production

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api-sandbox.worldota.net/api/b2b/v3/serp/prebook/' \
--header 'Content-Type: application/json' \
--data '{
    "hash": "sr-019a3ae6-0160-7945-9f6d-516791a39260",
    "price_increase_percent": 20
}'
```

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/serp/prebook/' \
--header 'Content-Type: application/json' \
--data '{
    "hash": "sr-019a3ae6-0160-7945-9f6d-516791a39260",
    "price_increase_percent": 20
}'
```

## Request body

Expand this|
Collapse this

hash
String
required

The unique rate ID.

ℹ️

- Get this value from the `search_hash` field of the calls:
  - Search by hotel IDs.
  - Search by geo coordinates.
  - Search by region.
- Use this value within 6 hours after you have got the rate.
- The minimum length is `1` character.
- The maximum length is `256` characters.

price\_increase\_percent
Int
optional

The percentage by which the new price can be higher than the original price.

For example, if you send `price_increase_percent=20` and the starting price is 1,000, the maximum allowed price for this pre-book call is 1,200.

If the value isn’t provided, the API will try to rebook with the same price or lower.

ℹ️

- The minimum value is `0`.
- The maximum value is `100`.

## Response

Expand this|
Collapse this

total\_hotels
Int

The total number of unique hotels.

changes
Object

The rate price change information.

price\_changed
Boolean

Whether the rate price is changed or not.

hotels
[Object]

The list of hotels and their rates.

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

rates
[Object]

The list of available hotel rates.

allotment
Int

The number of rooms available for the rate.

amenities\_data
[String]

The room amenity list.

To get all available room amenities and their definitions, use the `room_amenities` field from the Retrieve hotel static data call.

any\_residency
Boolean

Whether the rate is allowed to be booked by the guest with any kind of residency or not.

Use it if you don’t collect the guests’ residency.

book\_hash
String

The unique rate ID used to identify the selected rate.

ℹ️

- Use this value in the `book_hash` field of the Create booking process call.
- The value lifetime is 6 hours after you have got the rate from the search results.

daily\_prices
[String]

The list of daily rate prices breakdown in the request currency.

deposit
Object

The deposit information of the order.

Has a value if the rate `payment_types.type` field has the `hotel` value.

amount
String

The deposit amount in the currency specified by the `currency_code` field.

currency\_code
String

The deposit amount currency code. Is the same as the charged (hotel) currency code.

is\_refundable
Boolean

Whether the deposit is refundable or not.

match\_hash
String

The `match_hash` field of the rate from the call made by the SERP mechanism.

Use this field if you are:

- Showing rates to the users from the call made by the SERP mechanism.
- Making a booking from these rates.
- Using this call response as actual data on the rate.
- Using this call response to actualize the exact rate from the calls made by the SERP mechanism.

Usage of this field can help the ETG collect analytics for SERP-HP matching.

meal
String

deprecated

⚠️

Use the `meal_data` field instead.

The meal type.

If there is no meal type provided by the rate source, the `nomeal` value is returned.

To get all available meals and their definitions, use the `meals` field from the Retrieve hotel static data call.

meal\_data
Object

The rate meals information.

value
String

The meal type in the rate.

To get all available meal types and their definitions, use the `meals` field from the Retrieve hotel static data call.

Has the `nomeal` value if no meal type is provided.

has\_breakfast
Boolean

Whether breakfast is included to the rate or not.

no\_child\_meal
Boolean

Whether the children meal is absent in the rate or not.

no\_show
Object

The no-show penalty information.

amount
String

The no-show penalty amount in the currency specified by the `currency_code` field.

currency\_code
String

The no-show penalty amount currency code. Is the same as the charged (hotel) currency code.

from\_time
String

The time in the hotel timezone from which the no-show penalty is charged, in `HH:MM:SS` format (24-hour).

payment\_options
Object

The accepted payment options with the specified amount to be charged.

For a booking, this amount in the requested currency should be paid.

payment\_types
String

The list with accepted payment options.

amount
String

The amount to be charged for the booking in the contract currency code.

currency\_code
String

The amount currency code. Is the same as the charged (contract) currency code.

ℹ️

If the `payment_types` is `hotel`, the hotel `currency_code` will be used here.

by
String

Whether the booking can be paid by a card or not.

ℹ️

- The possible values:
  - `credit_card`
  - `null`

cancellation\_penalties
String

The cancellation rules and commission information.

free\_cancellation\_before
String

The date and time when the free cancellation policy expires.

Has the `null` value, if there is no free cancellation.

The timezone is in UTC±0.

policies
String

The cancellation policies breakdown by periods.

amount\_charge
String

The cancellation penalty amount in the contract currency.

amount\_show
String

The cancellation penalty amount in the request currency.

end\_at
String

The date and time when this cancellation policy expires.

Has the `null` value, if it is in the time from the `start_at` field value till check-in.

If the `start_at` and `end_at` fields have the `null` value, this particular cancellation policy:

- Has no time restrictions.
- Is in effect all the time.

The timezone is in UTC±0.

start\_at
String

The date and time when this cancellation policy takes effect.

Has the `null` value, if it is in effect till the `end_at` field value.

The timezone is in UTC±0.

is\_need\_credit\_card\_data
Boolean

Whether the credit card information is needed or not.

is\_need\_cvc
Boolean

Whether the CVC is needed or not.

type
String

The payment type.

ℹ️

- The possible values:
  - `now`. Use it to allow the user to pay for the booking via the ETG payment system:
    1. Request the Create booking process call and get the card details.
    2. Request the Create credit card token call with the card details.
  - `hotel`. Use it to allow the user to pay for the booking upon check-in at the hotel. The user won’t be charged now.
  - `deposit`. Use it to allow you to charge the user bank card for the booking by yourself:
    1. You need to keep your ETG deposit sufficient. To increase funds, contact your account manager.
    2. The user makes a booking.
    3. You charge the user bank card.
    4. The ETG writes funds from your deposit by themselves during the reporting period.

show\_amount
String

The rate price in the request currency code of this object `show_currency_code` field value.

Isn’t necessarily the sum in the charged or payment currency code.

show\_currency\_code
String

The request currency code.

Isn’t necessarily in the charged or payment currency code.

currency\_code
String

The amount currency code. Is the same as the charged (contract) currency code.

ℹ️

If the `payment_types` is `hotel`, the hotel `currency_code` will be used here.

tax\_data
Object

The tax information.

taxes
Object

The taxes list.

amount
String

The tax amount in the currency specified by the `currency_code` field.

currency\_code
String

The tax amount currency code in the ISO 4217 format.

ℹ️

- The length is `3` characters.
- The possible values:
  - `AED`.
  - `AFN`.
  - `ALL`.
  - `AMD`.
  - `ANG`.
  - `AOA`.
  - `ARS`.
  - `AUD`.
  - `AWG`.
  - `AZN`.
  - `BAM`.
  - `BBD`.
  - `BDT`.
  - `BGN`.
  - `BHD`.
  - `BIF`.
  - `BMD`.
  - `BND`.
  - `BOB`.
  - `BOV`.
  - `BRL`.
  - `BSD`.
  - `BTN`.
  - `BWP`.
  - `BYR`.
  - `BYN`.
  - `BZD`.
  - `CAD`.
  - `CDF`.
  - `CHE`.
  - `CHF`.
  - `CHW`.
  - `CLF`.
  - `CLP`.
  - `CNY`.
  - `COP`.
  - `COU`.
  - `CRC`.
  - `CUC`.
  - `CUP`.
  - `CVE`.
  - `CZK`.
  - `DJF`.
  - `DKK`.
  - `DOP`.
  - `DZD`.
  - `EGP`.
  - `ERN`.
  - `ETB`.
  - `EUR`.
  - `FJD`.
  - `FKP`.
  - `GBP`.
  - `GEL`.
  - `GHS`.
  - `GIP`.
  - `GMD`.
  - `GNF`.
  - `GTQ`.
  - `GYD`.
  - `HKD`.
  - `HNL`.
  - `HRK`.
  - `HTG`.
  - `HUF`.
  - `IDR`.
  - `ILS`.
  - `INR`.
  - `IQD`.
  - `IRR`.
  - `ISK`.
  - `JMD`.
  - `JOD`.
  - `JPY`.
  - `KES`.
  - `KGS`.
  - `KHR`.
  - `KMF`.
  - `KPW`.
  - `KRW`.
  - `KWD`.
  - `KYD`.
  - `KZT`.
  - `LAK`.
  - `LBP`.
  - `LKR`.
  - `LRD`.
  - `LSL`.
  - `LTL`.
  - `LVL`.
  - `LYD`.
  - `MAD`.
  - `MDL`.
  - `MGA`.
  - `MKD`.
  - `MMK`.
  - `MNT`.
  - `MOP`.
  - `MRO`.
  - `MUR`.
  - `MVR`.
  - `MWK`.
  - `MXN`.
  - `MXV`.
  - `MYR`.
  - `MZN`.
  - `NAD`.
  - `NGN`.
  - `NIO`.
  - `NOK`.
  - `NPR`.
  - `NZD`.
  - `OMR`.
  - `PAB`.
  - `PEN`.
  - `PGK`.
  - `PHP`.
  - `PKR`.
  - `PLN`.
  - `PYG`.
  - `QAR`.
  - `RON`.
  - `RSD`.
  - `RUB`.
  - `RWF`.
  - `SAR`.
  - `SBD`.
  - `SCR`.
  - `SDG`.
  - `SEK`.
  - `SGD`.
  - `SHP`.
  - `SLL`.
  - `SOS`.
  - `SRD`.
  - `SSP`.
  - `STD`.
  - `SVC`.
  - `SYP`.
  - `SZL`.
  - `THB`.
  - `TJS`.
  - `TMT`.
  - `TND`.
  - `TOP`.
  - `TRY`.
  - `TTD`.
  - `TWD`.
  - `TZS`.
  - `UAH`.
  - `UGX`.
  - `USD`.
  - `USN`.
  - `USS`.
  - `UYI`.
  - `UYU`.
  - `UZS`.
  - `VEF`.
  - `VND`.
  - `VUV`.
  - `WST`.
  - `XAF`.
  - `XAG`.
  - `XAU`.
  - `XBA`.
  - `XBB`.
  - `XBC`.
  - `XBD`.
  - `XCD`.
  - `XDR`.
  - `XFU`.
  - `XOF`.
  - `XPD`.
  - `XPF`.
  - `XPT`.
  - `XSU`.
  - `XTS`.
  - `XUA`.
  - `YER`.
  - `ZAR`.
  - `ZMW`.
  - `ZWL`.

included\_by\_supplier
Boolean

Whether the tax is included by the supplier or not.

When it has:

- The `false` value, the tax is supposed to be paid at the hotel in this object currency.
- The `true` value, the tax is included in the price.

name
String

The ETG tax ID.

To get all available tax IDs and their definitions, use the `taxes` field from the Retrieve hotel static data call.

rg\_ext
Object

The hotel room type.

Use this field to get extra data on the room from the hotel static data. For example, room images, descriptions.

balcony
Int

Whether there is a balcony or not.

ℹ️

The possible values:

- `0` — no balcony.
- `1` — a balcony.

bathroom
Int

The room bathroom information.

ℹ️

The possible values:

- `0` — undefined.
- `1` — a shared bathroom.
- `2` — a private bathroom.
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
- `6` — sextuplet.

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

club
Int

Whether it is a club room or not.

ℹ️

The possible values:

- `0` — not a club room.
- `1` — a club room.

family
Int

Whether it is a family room or not.

ℹ️

The possible values:

- `0` — not a family room.
- `1` — a family room.

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

room\_data\_trans
Object

The room information in the request language.

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

main\_room\_type
String

The room type.

misc\_room\_type
String

The room additional information.

beds
Object

An array listing the types and quantities of beds available in the room.

ℹ️

This field and its nested fields are **not available in the Sandbox environment**.

bed
String

Type of bed.

count
String

Quantity of this bed type.

legal\_info
Object

The hotel and service provider legal information.

ℹ️

The information provided depends on the supplier of the specific rate.

Has the value different from `null` for only countries where it is mandatory to have this information.

Has the `null` value for the calls made by the SERP mechanism.

hotel
Object

The hotel legal information.

name
String

The hotel legal name.

address
String

The hotel legal address.

taxpayer\_number
String

The Taxpayer Personal Identification Number (INN) of the hotel.

ℹ️

- The length is `10` characters.

state\_registration\_number
String

The State Registration Number for Companies (OGRN) of the hotel.

ℹ️

- The length is `13` characters.

work\_time
String

The hotel legal address working hours.

provider
Object

The service provider legal information.

name
String

The service provider legal name.

address
String

The service provider legal address.

taxpayer\_number
String

The Taxpayer Personal Identification Number (INN) of the service provider.

ℹ️

- The length is `10` characters.

state\_registration\_number
String

The State Registration Number for Companies (OGRN) of the service provider.

ℹ️

- The length is `13` characters.

room\_name
String

The room name in the request language.

room\_name\_info
Object

The optional object that may help to resolve certain matching problems. To get access to the object, contact your account manager.

serp\_filters
[String]

deprecated

The list of hotel amenities. Accepts a list of limited hotel amenities of amenity values. This limit is not stable and may vary between different rates within the same hotel — some values may be applicable for one rate but not considered for another.

ℹ️

Supported values:

- has\_airport\_transfer.
- has\_parking.
- air-conditioning.
- has\_internet.
- has\_breakfast.

is\_package
Boolean

Rates marked with `is_package = true` should be sold as a part of package, and the price for the hotel should not be shown separately.

## Response example

```
{
  "data": {
    "hotels": [
      {
        "id": "dukes_dubai",
        "hid": 8663536,
        "rates": [
          {
            "book_hash": "p-ca1f9fef-bcf7-461e-a0d1-00850ffd10bb",
            "match_hash": "m-d0b85aee-3e6e-556b-a9b6-d98b084764ff",
            "daily_prices": [
              "605.97",
              "605.97",
              "605.97"
            ],
            "meal": "all-inclusive",
            "meal_data": {
              "value": "all-inclusive",
              "has_breakfast": true,
              "no_child_meal": false
            },
            "payment_options": {
              "payment_types": [
                {
                  "amount": "163377.76",
                  "show_amount": "1817.90",
                  "currency_code": "EUR",
                  "show_currency_code": "EUR",
                  "by": null,
                  "is_need_credit_card_data": false,
                  "is_need_cvc": false,
                  "type": "deposit",
                  "vat_data": {
                    "included": false,
                    "applied": false,
                    "amount": "0.00",
                    "currency_code": "EUR",
                    "value": "0.00"
                  },
                  "tax_data": {
                    "taxes": [
                      {
                        "name": "city_tax",
                        "included_by_supplier": false,
                        "amount": "60.00",
                        "currency_code": "AED"
                      },
                      {
                        "name": "occupancy_tax",
                        "included_by_supplier": true,
                        "amount": "103.44",
                        "currency_code": "EUR"
                      },
                      {
                        "name": "service_fee",
                        "included_by_supplier": true,
                        "amount": "162.91",
                        "currency_code": "EUR"
                      },
                      {
                        "name": "vat",
                        "included_by_supplier": true,
                        "amount": "73.88",
                        "currency_code": "EUR"
                      }
                    ]
                  },
                  "perks": {},
                  "commission_info": {
                    "show": {
                      "amount_gross": "1855.00",
                      "amount_net": "1817.90",
                      "amount_commission": "37.10"
                    },
                    "charge": {
                      "amount_gross": "166712.00",
                      "amount_net": "163377.76",
                      "amount_commission": "3334.24"
                    }
                  },
                  "cancellation_penalties": {
                    "policies": [
                      {
                        "start_at": null,
                        "end_at": "2026-10-08T22:00:00",
                        "amount_charge": "0.00",
                        "amount_show": "0.00",
                        "commission_info": {
                          "show": {
                            "amount_gross": "0.00",
                            "amount_net": "0.00",
                            "amount_commission": "0.00"
                          },
                          "charge": {
                            "amount_gross": "0.00",
                            "amount_net": "0.00",
                            "amount_commission": "0.00"
                          }
                        }
                      },
                      {
                        "start_at": "2026-10-08T22:00:00",
                        "end_at": "2026-10-14T22:00:00",
                        "amount_charge": "54393.92",
                        "amount_show": "605.64",
                        "commission_info": {
                          "show": {
                            "amount_gross": "618.00",
                            "amount_net": "605.64",
                            "amount_commission": "12.36"
                          },
                          "charge": {
                            "amount_gross": "55504.00",
                            "amount_net": "54393.92",
                            "amount_commission": "1110.08"
                          }
                        }
                      },
                      {
                        "start_at": "2026-10-14T22:00:00",
                        "end_at": null,
                        "amount_charge": "163377.76",
                        "amount_show": "1817.90",
                        "commission_info": {
                          "show": {
                            "amount_gross": "1855.00",
                            "amount_net": "1817.90",
                            "amount_commission": "37.10"
                          },
                          "charge": {
                            "amount_gross": "166712.00",
                            "amount_net": "163377.76",
                            "amount_commission": "3334.24"
                          }
                        }
                      }
                    ],
                    "free_cancellation_before": "2026-10-08T22:00:00"
                  },
                  "recommended_price": null
                }
              ]
            },
            "bar_rate_price_data": null,
            "rg_ext": {
              "class": 3,
              "quality": 17,
              "sex": 0,
              "bathroom": 2,
              "bedding": 3,
              "family": 0,
              "capacity": 2,
              "club": 0,
              "bedrooms": 0,
              "balcony": 0,
              "view": 0,
              "floor": 0
            },
            "room_name": "Premium Double room (full double bed) (bed type is subject to availability)",
            "room_name_info": {
              "original_rate_name": "Premium Room "
            },
            "serp_filters": [
              "has_bathroom"
            ],
            "sell_price_limits": null,
            "allotment": 99,
            "amenities_data": [
              "not-guaranteed",
              "non-smoking"
            ],
            "any_residency": false,
            "deposit": null,
            "no_show": null,
            "room_data_trans": {
              "main_room_type": "Premium Double room",
              "main_name": "Premium Double room",
              "bathroom": null,
              "bedding_type": "full double bed",
              "misc_room_type": "bed type is subject to availability",
              "beds": [
                {
                  "bed": "double",
                  "count": 1
                }
              ]
            },
            "legal_info": {
              "provider": {
                "name": "",
                "address": "",
                "taxpayer_number": "0000000000",
                "state_registration_number": "0000000000000"
              },
              "hotel": {
                "name": "Отель Dukes The Palm Dubai",
                "address": "Palm Jumeirah, Dubai, Дубай",
                "taxpayer_number": "0000000000",
                "state_registration_number": "0000000000000",
                "work_time": "с 9 до 18 по местному времени"
              }
            },
            "is_package": false
          }
        ],
        "bar_price_data": null
      }
    ],
    "changes": {
      "price_changed": false
    }
  },
  "debug": {
    "api_endpoint": {
      "endpoint": "api/b2b/v3/serp/prebook",
      "is_active": true,
      "is_limited": true,
      "remaining": 29,
      "requests_number": 30,
      "reset": "2026-04-16T20:00:00",
      "seconds_number": 60
    },
    "request": {
      "hash": "sr-019d97d3-f8ef-70e7-b3c8-d9886d2164b0",
      "price_increase_percent": 20
    },
    "method": "POST",
    "real_ip": "104.30.161.77",
    "request_id": "386c74eff7c700ac70c55ab501d97faf",
    "key_id": 1234,
    "api_key_id": 1234,
    "utcnow": "2026-04-16T19:59:32.918838"
  },
  "status": "ok",
  "error": null
}
```

## Errors

The `error` field has the value specified in the headers below.

### `no_available_rates`

The rate with the `price_increase_percent` field value isn’t found. Try to change the value.

### `rate_not_found`

- The rate with the `hash` field value isn’t found.
- The `hash` field value has expired.

Send another search request and change the `hash` field value.

### `invalid_params`

- The `hash` field is required.
- The `hash` field value is incorrect.
- The `price_increase_percent` field value is less than `0`.

### `unknown`

The internal ETG services’ timeout.

### `prebook_from_serp_disabled`

There is no permission to use this call for this contract. Contact the API support team.

### `contract_mismatch`

The pre-book contract differs from the rate contract. They should be the same.
