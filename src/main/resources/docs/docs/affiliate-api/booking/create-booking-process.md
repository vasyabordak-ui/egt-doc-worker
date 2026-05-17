---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/affiliate-api/booking/create-booking-process/
tags:
- etg-docs
- affiliate-api
- booking
title: Create booking process
---

# Create booking process

ETG API V3

Affiliate API

Booking

Create booking process

# Create booking process

#affiliate

Sandbox Production

```
https://api-sandbox.worldota.net/api/b2b/v3/hotel/order/booking/form/
```

```
https://api.worldota.net/api/b2b/v3/hotel/order/booking/form/
```

ℹ️

- **This call is required**.
- Request this call right after the calls:
  - Retrieve hotelpage.
  - Prebook rate from hotelpage step.
  - Prebook rate from search step.
- After requesting this call, you must send the Start booking process request.

The call creates a booking process. The booking process includes several stages. Their number depends on whether there are 3D Secure and fraud checks.

ℹ️

The limitations:

- The booking form lifetime is 60 minutes.

⚠️

The following applies only when using a test API key:

- All bookings for the test hotel (use `hid` = `8473727`or `id` = `test_hotel_do_not_book`) will be real with all financial responsibilities. Nevertheless, giveaway prices are available within their rates for testing purposes.
- All test bookings must be canceled.

## Sandbox limitations

⚠️

Use only field values, IDs, API keys, and any static content from the sandbox environment within the sandbox. **Do not use sandbox data in test or production environments, and do not mix data or configuration between different environments.**

- The 3D Secure check is not supported in the sandbox.
- The input value for the `language` field is ignored and is always set to `en`.
- The following fields are not supported: `item_id`, `payment_type.type`, and `payment_types.recommended_price.show_currency_code`.
- For all objects with the `currency_code` field, the value is always `EUR`.

## Request example

Sandbox Production

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api-sandbox.worldota.net/api/b2b/v3/hotel/order/booking/form/' \
--header 'Content-Type: application/json' \
--data '{{
    "partner_order_id": "asd12399235",
    "book_hash": "h-372e7fa4-3a85-5a09-9f14-867766abf26c",
    "language": "en",
    "user_ip": "82.29.0.86"
}'
```

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/hotel/order/booking/form/' \
--header 'Content-Type: application/json' \
--data '{
    "partner_order_id": "asd12399235",
    "book_hash": "h-372e7fa4-3a85-5a09-9f14-867766abf26c",
    "language": "en",
    "user_ip": "82.29.0.86"
}'
```

## Request body

Expand this|
Collapse this

partner\_order\_id
String
required

The external booking ID in the UUID format.

The ID remains the same if you cancel a booking that:

- Is successful.
- Is failed.
- Has the fail status response from the Check booking process call.

Use this field value for the rest of the booking calls.

ℹ️

- The value should be unique for the order within the same contact.
- The minimum length is `3` character.
- The maximum length is `256` characters.

book\_hash
String
required

The unique rate ID used to identify the selected rate.

ℹ️

Use the value of the `book_hash` field from one of the calls:

- Prebook rate from hotelpage step.
- Prebook rate from search step.

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

user\_ip
String
required

The end user IP address.

Will be used for credit card processing if you use payment type `now`.

## Response

Expand this|
Collapse this

order\_id
Int

The order ID created by the ETG.

ℹ️

- The minimum value is `1`.

partner\_order\_id
String

The external booking ID in the UUID format.

The ID remains the same if you cancel a booking that:

- Is successful.
- Is failed.
- Has the fail status response from the Check booking process call.

Use this field value for the rest of the booking calls.

ℹ️

- The value should be unique for the order within the same contact.
- The minimum length is `3` character.
- The maximum length is `256` characters.

item\_id
Int

The order item ID.

Use this field value in the Create credit card token call to allow the user pay with a bank card.

is\_gender\_specification\_required
Boolean

Whether the guests’ gender is required by the hotel or not.

upsell\_data
[Object]

The order upsell information.

⚠️

- Set these parameters only when the user really needs additional services.
- Only one early check-in and one late check-out should be requested and selected.

charge\_price
Object

The upsell price information.

amount
String

The upsell amount in the currency specified by the `currency_code` field.

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

name
String

The upsell name.

ℹ️

- The possible values:
  - `early_checkin`.
  - `late_checkout`.

rule\_id
Int

The upsell rule ID.

uid
String

The upsell ID.

data
Object

The upsell time. The local time in the `HH:MM:SS` format.

payment\_types
[Object]

The order payment information.

amount
String

The booking amount in the currency specified by the `currency_code` field.

currency\_code
String

The amount currency code. Is the same as the charged (contract) currency code.

ℹ️

If the `payment_types` is `hotel`, the hotel `currency_code` will be used here.

is\_need\_credit\_card\_data
Boolean

Whether the credit card information is needed or not.

is\_need\_cvc
Boolean

Whether the CVC is needed or not.

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

## Response example

```
{
    "data": {
        "order_id": 559350847,
        "partner_order_id": "asd12399235",
        "item_id": 128903852,
        "payment_types": [
            {
                "amount": "2000.00",
                "currency_code": "INR",
                "is_need_credit_card_data": false,
                "is_need_cvc": false,
                "type": "hotel",
                "recommended_price": null
            }
        ],
        "is_gender_specification_required": false,
        "upsell_data": []
    },
    "debug": {
        "api_endpoint": {
            "endpoint": "api/b2b/v3/hotel/order/booking/form",
            "is_active": true,
            "is_limited": true,
            "remaining": 29,
            "requests_number": 30,
            "reset": "2025-10-21T12:22:00",
            "seconds_number": 60
        },
        "request": {
            "partner_order_id": "asd12399235",
            "book_hash": "h-372e7fa4-3a85-5a09-9f14-867766abf26c",
            "language": "en",
            "user_ip": "82.29.0.86"
        },
        "method": "POST",
        "real_ip": "104.30.161.77",
        "request_id": "26f502336760ea6e5a71ed6beae781f5",
        "key_id": 1234,
        "api_key_id": 1234,
        "utcnow": "2025-10-21T12:21:01.572537"
    },
    "status": "ok",
    "error": null
}
```

## Errors

The `error` field has the value specified in the headers below.

### `contract_mismatch`

An attempt to make the booking with a rate found with the different contract.

### `double_booking_form`

An attempt to make a new booking with the `partner_order_id` that is already used for the API key contract and isn’t completed yet.

Make another request with a new `partner_order_id`.

### `duplicate_reservation`

An attempt to make a new booking with the `partner_order_id` that is already used for the API key contract and is already completed with a successful or error status.

Make another request with a new `partner_order_id`.

### `hotel_not_found`

The hotel isn’t found.

### `insufficient_b2b_balance`

In case the credit limit is not sufficient for the reservation.
To discuss changes to this limit please contact your account manager.

### `reservation_is_not_allowed`

There is no permission to use this call for this contract. Contact your account manager.

### `rate_not_found`

- The rate with the `book_hash` field value isn’t found.
- The `book_hash` field value has expired.

Send another search request and change the `book_hash` field value.

### `sandbox_restriction`

An attempt to book the real hotel in the test environment.

- To book the real hotel, use the production environment.
- To book the test hotel, use the test environment.

### `timeout`, `unknown`, and `5xx`

If you get errors `timeout`, `unknown`, or the `5xx` status code from this call:

- Make another request with a new `partner_order_id`.
- The number of calls should be limited to 10.
- If you get this error more than 10 times in a row, the issue is probably in automatically changed settings of your contract. Contact your account manager to resolve the issue. Otherwise, the ETG has temporary technical issues.

## Error Handling Diagram

Below is a sequence diagram describing how to handle errors during the booking creation process:

ℹ️

The number of retries should be limited to 10.

```
sequenceDiagram
    participant User
    participant API

   User->>API: Create booking process request
alt status == "ok"
    API-->>User: Response: "ok"
    User->>User: Go to Start booking process
    else error in (duplicate_reservation, double_booking_form, unknown, timeout) or HTTP status code 5xx
    loop Retry with new partner_order_id (until status == "ok" or other error)
        User->>API: Retry Create booking process with new partner_order_id
        API-->>User: Response
        alt status == "ok"
            User->>User: Go to Start booking process
          else error in retryable set
            User->>User: Continue retry
        else other error
            User->>User: Show error and stop
        end
    end
else other error
    API-->>User: Response error
    User->>User: Show error and stop
end
```
