---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/b2b-api/hotel-search/prebook-rate-from-hotelpage-step/
tags:
- etg-docs
- b2b-api
- hotel-search
title: Prebook rate from hotelpage step
---

# Prebook rate from hotelpage step

ETG API V3

B2B API

Hotel search

Prebook rate from hotelpage step

# Prebook rate from hotelpage step

#b2b

Sandbox Production

```
https://api-sandbox.worldota.net/api/b2b/v3/hotel/prebook/
```

```
https://api.worldota.net/api/b2b/v3/hotel/prebook/
```

ℹ️

This call is recommended.
If method is not used:

- The risk of receiving a soldout error at the booking stage increases.
- The order confirmation process will take longer.

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
- The following errors are not supported: `no_available_rates`, `unknown`, and `contract_mismatch`.

## Request example

Sandbox Production

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api-sandbox.worldota.net/api/b2b/v3/hotel/prebook/' \
--header 'Content-Type: application/json' \
--data '{
  "hash": "h-027a29bc-eb54-5a23-a73c-07261af22c1b",
  "price_increase_percent": 20
}'
```

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/hotel/prebook/' \
--header 'Content-Type: application/json' \
--data '{
  "hash": "h-d01a824f-dcce-5932-af8d-8bafc6badc3b",
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

Use this value within 24 hours after the Retrieve hotelpage call returns a relevant rate.

ℹ️

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

The room amenities list.

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

commission\_info
[Object]

The commission information.

charge
[Object]

The commission information in the charge (contract) currency.

amount\_commission
String

The commission amount.

amount\_gross
String

The gross price.

amount\_net
String

The net price.

show
[Object]

The commission information in the requested currency.

amount\_commission
String

The commission amount.

amount\_gross
String

The gross price.

amount\_net
String

The net price.

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

commission\_info
[Object]

The commission information.

charge
[Object]

The commission information in the charge (contract) currency.

amount\_commission
String

The commission amount.

amount\_gross
String

The gross price.

amount\_net
String

The net price.

show
[Object]

The commission information in the requested currency.

amount\_commission
String

The commission amount.

amount\_gross
String

The gross price.

amount\_net
String

The net price.

is\_need\_credit\_card\_data
Boolean

Whether the credit card information is needed or not.

is\_need\_cvc
Boolean

Whether the CVC is needed or not.

show\_amount
String

The rate price in the request currency code of this object `show_currency_code` field value.

Isn’t necessarily the sum in the charged or payment currency code.

show\_currency\_code
String

The currency code in the request body.

recommended\_price
String

⚠️

This parameter is currently under development and will be available later. It can be ignored at the moment.

The price below which the rate can’t be sold on B2C website.

amount
String

The deposit amount in the currency specified by the `currency_code` field.

currency\_code
String

The deposit amount currency code. Is the same as the charged (hotel) currency code.

show\_amount
String

The rate price in the request currency code of this object `show_currency_code` field value.

Isn’t necessarily the sum in the charged or payment currency code.

show\_currency\_code
String

The request currency code.

Isn’t necessarily in the charged or payment currency code.

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

    Every payment goes under a credit limit for the deposit.

vat\_data
Object

The rate VAT information.

amount
String

The VAT amount in the currency specified by the `currency_code` field.

If the value is `0`, it means that the rate is without VAT. Otherwise, the rate includes VAT.

applied
Boolean

Whether the VAT is applied or not.

currency\_code
String

The amount currency code in the ISO 4217 format.

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

included
Boolean

Whether the VAT is included or not.

If the value is `false`, it means that the rate is without VAT. Otherwise, the rate includes VAT.

value
Float

deprecated

The VAT amount in the currency of this object `currency_code` field value.

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

sell\_price\_limits
Object

Information on the restrictions on the final selling price for the client.

ℹ️

This field is available only upon request. Please contact your account manager if you need access to this data.

If not `null`, the final selling price should be:

- Within the `min_price` and `max_price` field values.
- In the search currency.

min\_price
String

The minimum final selling price for the client in the search currency.

max\_price
String

The maximum final selling price for the client in the search currency.

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
        "id": "conrad_los_angeles",
        "hid": 10004834,
        "rates": [
          {
            "book_hash": "p-e7029d6b-7b28-4e99-893f-3434d5065f33",
            "match_hash": "m-afd54df0-5f86-5b45-b011-4a94ccd81e43",
            "daily_prices": [
              "329.61",
              "329.61",
              "329.61"
            ],
            "meal": "nomeal",
            "meal_data": {
              "value": "nomeal",
              "has_breakfast": false,
              "no_child_meal": true
            },
            "payment_options": {
              "payment_types": [
                {
                  "amount": "75446.28",
                  "show_amount": "988.82",
                  "currency_code": "USD",
                  "show_currency_code": "USD",
                  "by": null,
                  "is_need_credit_card_data": false,
                  "is_need_cvc": false,
                  "type": "deposit",
                  "vat_data": {
                    "included": false,
                    "applied": false,
                    "amount": "0.00",
                    "currency_code": "USD",
                    "value": "0.00"
                  },
                  "tax_data": {
                    "taxes": [
                      {
                        "name": "occupancy_tax",
                        "included_by_supplier": true,
                        "amount": "136.39",
                        "currency_code": "USD"
                      },
                      {
                        "name": "resort_fee",
                        "included_by_supplier": false,
                        "amount": "135.00",
                        "currency_code": "USD"
                      }
                    ]
                  },
                  "perks": {},
                  "commission_info": {
                    "show": {
                      "amount_gross": "1009.00",
                      "amount_net": "988.82",
                      "amount_commission": "20.18"
                    },
                    "charge": {
                      "amount_gross": "76986.00",
                      "amount_net": "75446.28",
                      "amount_commission": "1539.72"
                    }
                  },
                  "cancellation_penalties": {
                    "policies": [
                      {
                        "start_at": null,
                        "end_at": null,
                        "amount_charge": "75446.28",
                        "amount_show": "988.82",
                        "commission_info": {
                          "show": {
                            "amount_gross": "1009.00",
                            "amount_net": "988.82",
                            "amount_commission": "20.18"
                          },
                          "charge": {
                            "amount_gross": "76986.00",
                            "amount_net": "75446.28",
                            "amount_commission": "1539.72"
                          }
                        }
                      }
                    ],
                    "free_cancellation_before": null
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
            "room_name": "Premium Double room (full double bed) (king size bed)",
            "room_name_info": {
              "original_rate_name": "Premium One King Bed"
            },
            "serp_filters": [
              "has_bathroom"
            ],
            "sell_price_limits": null,
            "allotment": 1,
            "amenities_data": [
              "non-smoking",
              "king-bed"
            ],
            "any_residency": false,
            "deposit": null,
            "no_show": null,
            "room_data_trans": {
              "main_room_type": "Premium Double room",
              "main_name": "Premium Double room",
              "bathroom": null,
              "bedding_type": "full double bed",
              "misc_room_type": "king size bed",
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
                "name": "Conrad Los Angeles",
                "address": "100 South Grand Avenue, Лос-Анджелес",
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
      "endpoint": "api/b2b/v3/hotel/prebook",
      "is_active": true,
      "is_limited": true,
      "remaining": 29,
      "requests_number": 30,
      "reset": "2026-04-16T20:19:00",
      "seconds_number": 60
    },
    "request": {
      "hash": "h-d01a824f-dcce-5932-af8d-8bafc6badc3b",
      "price_increase_percent": 20
    },
    "method": "POST",
    "real_ip": "104.30.161.77",
    "request_id": "969d452ece3347081865c46265cae077",
    "key_id": 1234,
    "api_key_id": 1234,
    "utcnow": "2026-04-16T20:18:14.564703"
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

### `prebook_disabled`

There is no permission to use this call for this contract. Contact the API support team.

### `contract_mismatch`

The pre-book contract differs from the rate contract. They should be the same.
