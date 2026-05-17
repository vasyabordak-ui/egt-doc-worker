---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/affiliate-api/booking/start-booking-process/
tags:
- etg-docs
- affiliate-api
- booking
title: Start booking process
---

# Start booking process

ETG API V3

Affiliate API

Booking

Start booking process

# Start booking process

#affiliate

Sandbox Production

```
https://api-sandbox.worldota.net/api/b2b/v3/hotel/order/booking/finish/
```

```
https://api.worldota.net/api/b2b/v3/hotel/order/booking/finish/
```

ℹ️

- **This call is required**.
- Request this call right after the Create booking process call.
- As the booking process is made asynchronously, repeatedly request the Check booking process call to know the status.

The call starts the booking process.

⚠️

If invalid user data is used, the data may be added to the stop list.

## Sandbox limitations

⚠️

Use only field values, IDs, API keys, and any static content from the sandbox environment within the sandbox. **Do not use sandbox data in test or production environments, and do not mix data or configuration between different environments.**

- The possible values for the `payment_type.type` field are `hotel` and `deposit`.
- The value of the `payment_type.currency_code` field is always `USD`.
- The following fields are not supported: `payment_type.init_uuid`, `payment_type.pay_uuid`, `upsell_data`, and `return_path`.
- For all objects with the `currency_code` field the value is always `EUR`.
- The following errors are not supported: `book_hash_not_found`, `rate_not_found`, `return_path_required`, `not_enough_credit_card_data`, `incorrect_init_uuid_format`, and `incorrect_pay_uuid_format`.

## Request example

Sandbox Production

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api-sandbox.worldota.net/api/b2b/v3/hotel/order/booking/finish/' \
--header 'Content-Type: application/json' \
--data '{
  "user": {
    "email": "[email protected]",
    "comment": "The user comment.",
    "phone": "12124567899"
  },
  "supplier_data": {
    "first_name_original": "Peter",
    "last_name_original": "Collins",
    "phone": "12124567880",
    "email": "[email protected]"
  },
  "partner": {
    "partner_order_id": "0b370500-5321-4046-92c5-5982f1a64fc6",
    "comment": "The partner comment.",
    "amount_sell_b2b2c": "10"
  },
  "language": "en",
  "rooms": [
    {
      "guests": [
        {
          "first_name": "Martin",
          "last_name": "Smith"
        },
        {
          "first_name": "Eliot",
          "last_name": "Smith"
        }
      ]
    }
  ],
  "payment_type": {
    "type": "deposit",
    "amount": "9",
    "currency_code": "EUR"
  },
  "return_path": "https://example.com"
}'
```

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/hotel/order/booking/finish/' \
--header 'Content-Type: application/json' \
--data '{
  "user": {
    "email": "[email protected]",
    "comment": "The user comment.",
    "phone": "12124567899"
  },
  "supplier_data": {
    "first_name_original": "Peter",
    "last_name_original": "Collins",
    "phone": "12124567880",
    "email": "[email protected]"
  },
  "partner": {
    "partner_order_id": "0b370500-5321-4046-92c5-5982f1a64fc6",
    "comment": "The partner comment.",
    "amount_sell_b2b2c": "10"
  },
  "language": "en",
  "rooms": [
    {
      "guests": [
        {
          "first_name": "Martin",
          "last_name": "Smith"
        },
        {
          "first_name": "Eliot",
          "last_name": "Smith"
        }
      ]
    }
  ],
  "payment_type": {
    "type": "deposit",
    "amount": "9",
    "currency_code": "EUR"
  },
  "return_path": "https://example.com"
}'
```

## Request body

Expand this|
Collapse this

arrival\_datetime
DateTime
optional

The estimated arrival time to the hotel (12-hour format with `*_day_period`; otherwise 24-hour format).

book\_timeout
Int
optional

The booking timeout in seconds.

ℹ️

- The minimum value is `10`.
- The maximum value is `600`.

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

payment\_type
Object
required

The order payment information.

type
String
required

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

amount
String
required

The booking amount in the currency specified by the `currency_code` field.

currency\_code
String
required

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

init\_uuid
String
optional

The external booking token ID in the UUID format.

ℹ️

- The length is `36` characters.
- Must be unique for every payment. Otherwise, the ETG will return an error.
- The value matches the pattern: `^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$`.
- Is required for bookings where the `is_need_credit_card_data` field was passed with the `true` value.
- Should be the same with the `init_uuid` field value from the Create credit card token) call.

pay\_uuid
String
optional

The external booking payment ID in the UUID format.

ℹ️

- The length is `36` characters.
- Must be unique for every payment. Otherwise, the ETG will return an error.
- The value matches the pattern: `^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$`.
- Is required for bookings where the `is_need_credit_card_data` field was passed with the `true` value.
- Should be the same with the `pay_uuid` field value from the Create credit card token) call.

return\_path
String
optional

The URL at your side to redirect the user to. It is done by the payment gateway after 3D Secure check. The reason the ETG needs it.

**This field is required** if the rate you are booking has the `payment_types` field with the `now` value.

The field `data_3ds` of the Check booking process call will have the `null` value if:

- You don’t pass this field.
- The issuing bank requires 3D Secure check.

ℹ️

- Must have the HTTPS scheme.
- Might have GET parameters.
- The minimum length is `1` character.
- The maximum length is `256` character.

rooms
[Object]
required

The guests’ information for the rooms.

guests
Object
required

The guests’ information.

first\_name
String
optional

The guest first name.

ℹ️

- It is mandatory to provide the first name for at least one guest in each booked room.
- The minimum length is `1` character.
- The maximum length is `50` characters.
- Digits and non-word symbols are prohibited, field should contain only unicode letters (Latin, Cyrillic, Hebrew, Hindi, Bengali, Thai etc.), spaces or following special characters: `'-,.’`.

last\_name
String
optional

The guest last name. For test purposes use the `Ratehawk` value.

ℹ️

- It is mandatory to provide the last name for at least one guest in each booked room.
- The minimum length is `1` character.
- The maximum length is `50` characters.
- Digits and non-word symbols are prohibited, field should contain only unicode letters (Latin, Cyrillic, Hebrew, Hindi, Bengali, Thai etc.), spaces or following special characters: `'-,.’`.

is\_child
Boolean
required or optional

Whether the guest is a child or not.

ℹ️

Guests are considered adults unless `is_child` = `true`, even if their `age` is that of a child.

age
Int
required or optional

The child age in years.

ℹ️

- Specify the child’s age if there are children in the booking request (`is_child` = `true`).
- The minimum value is `0`.
- The maximum value is `17`.

gender
String
optional

The guest gender.

ℹ️

- Is required if the hotel you intend to book has the `is_gender_specification_required` field with the `true` value. The field appears only in the Retrieve hotel content call response.
- The possible values:
  - `male`.
  - `female`.
  - `unknown`.

user
Object
required

The guest additional information.

comment
String
optional

The guest comments sent to the hotel.

ℹ️

- The minimum length is `1` character.
- The maximum length is `256` character.

email
String
required

The guest email address for contacting purposes. The email address must be valid.

phone
String
required

The guest phone number for contacting purposes. The phone number must be valid.

ℹ️

- The minimum length is `5` characters.
- The maximum length is `35` characters.

supplier\_data
Object
required or optional

The contact details of the user who initiated the booking.

ℹ️

- To know if the field is required for you, contact your account manager.

first\_name\_original
String
required

The first name of the user who initiated the booking.

ℹ️

- To know if the field is required for you, contact your account manager.

last\_name\_original
String
required

The last name of the user who initiated the booking.

ℹ️

- To know if the field is required for you, contact your account manager.

email
String
required

The email address of the user who initiated the booking.

ℹ️

- To know if the field is required for you, contact your account manager.

phone
String
required

The phone number of the user who initiated the booking.

ℹ️

- To know if the field is required for you, contact your account manager.
- The minimum length is `5` characters.
- The maximum length is `35` characters.

partner
Object
required

The partner information.

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

comment
String
optional

The partner booking internal comment.

The comment isn’t sent to the hotel and not processed by the ETG API support team. It is visible only to the partner itself.

ℹ️

- The minimum length is `1` character.
- The maximum length is `256` character.

amount\_sell\_b2b2c
String
optional

The resale price for the user in the contract currency.

The value is accepted if transferred (even if automatic completion of the resale price value is activated) and is displayed in the confirmatory accounting documents for the partners.

ℹ️

- The minimum value is `1`.

## Response example

```
{
  "data": null,
  "debug": null,
  "error": null,
  "status": "ok"
}
```

## Errors

The `error` field has the value specified in the headers below.

### `book_hash_not_found`

A rare internal error when the ETG couldn’t recognize the `book_hash` used in the booking.

After getting the error, the only option is to find another rate and continue with it.

### `booking_form_expired`

In case Create booking process has been expired.

Request a new Create booking process with a different `partner_order_id`.

### `chosen_payment_type_was_not_available_on_booking_form`

The `payment_type` field values don’t match any of the `payment_types` field values in the response of the Create booking process call.

Try to change the `payment_type` field value according to the `payment_types` field values in the response.

### `double_booking_finish`

An attempt to finish the booking for the second time while the status of the first attempt isn’t an error.

### `email`

The provided email address isn’t valid.

### `incorrect_chosen_payment_type`

Invalid data provided in the `payment_type` field of the call request. The supplied `type`, `amount`, and/or `currency_code` values do not match any of the `payment_type` options received from the Create booking process call.

### `incorrect_guests_number`

The adult guest number doesn’t match the adult guest number in the request of the Retrieve hotelpage call.

Try to change the `rooms` field item number according to the `guests` field values in the request.

The total number of adults derived from rooms[].guests[] does not match the search query. Any guest without `is_child` = `true` is treated as an adult (the `age` field alone does not mark a guest as a child).

### `incorrect_children_data`

- The children guest number doesn’t match the children guest number in the request of the Retrieve hotelpage call.
- The children age is incorrect.

Try to change:

- The `rooms` field item number according to the `guests` field values in the request.
- The `age` field value.

### `incorrect_rooms_number`

The room number doesn’t match the room number in the request of the Retrieve hotelpage call.

Try to change the `rooms` field item number according to the `guests` field values in the request.

### `insufficient_b2b_balance`

In case the credit limit is not sufficient for the reservation.
To discuss changes to this limit please contact your account manager.

### `order_not_found`

The order with the `partner_order_id` field value isn’t found.

Send another booking creation request and change the `partner_order_id` field value.

### `rate_not_found`

The rate isn’t found. Send another search request.

### `return_path_required`

The `return_path` field is required if the rate you are booking has the `payment_types` field with the `now` value.

### `unauthorized_group_booking`

An attempt to make a request with the conditions:

- More than 9 bookings in the same hotel.
- More than 9 bookings for the same dates.
- In one request.

To make a group booking:

- Contact the API support team.
- Fill in the group booking form on the site.

### `invalid_upsell_attributes`

Invalid name and attributes combination in `upsell_data` field.

### `invalid_upsell_uid`

Invalid upsell uid in `upsell_data` field.

### `arrival_date_differs_from_checkin_date`

The check-in date should be equal to or the day after the check-in date in the request of the Retrieve hotelpage call.

Try to change the `checkin_datetime` field value according to the `checkin` field value in the request.

### `not_enough_credit_card_data`

The `init_uuid` and `pay_uuid` fields are required if the rate you are booking has the `is_need_credit_card_data` field with the `true` value.

### `incorrect_init_uuid_format`

The `init_uuid` field values don’t match the regular expression.

### `incorrect_pay_uuid_format`

The `pay_uuid` field values don’t match the regular expression.

### `sandbox_restriction`

An attempt to book the test hotel with the `hid` = `6291619` or `id` = `test_hotel_do_not_book` in the production environment.

To book the test hotel, use the test environment.

### `supplier_data_required`

The `supplier_data` field is required for your integration. Contact your account manager.

### `timeout`, `unknown`, and `5xx`

If you get the error, it doesn’t necessarily mean the booking isn’t created. These errors may occur because of the ETG services’ timeout. To check the booking finishing, send the Check booking process request.

## Error Handling Diagram

Below is a sequence diagram describing how to handle errors during the start booking process:

```
sequenceDiagram
    participant User
    participant API

    User->>API: Start booking process
    alt status == "ok" or error is timeout/unknown or HTTP status code 5xx
        API-->>User: Response: status == "ok" or error is timeout/unknown or HTTP status code 5xx
        User->>User: Proceed to Check Booking Process
    else
        API-->>User: Response: other error
        User->>User: Show error and stop
    end
```
