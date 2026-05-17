---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/b2b-api/booking/create-credit-card-token/
tags:
- etg-docs
- b2b-api
- booking
title: Create credit card token
---

# Create credit card token

ETG API V3

B2B API

Booking

Create credit card token

# Create credit card token

#b2b

```
https://api.payota.net/api/public/v1/manage/init_partners
```

ℹ️

The call is **required** for bookings where the `is_need_credit_card_data` field was passed with the `true` value.

The call creates and returns a credit card token for the order payment. The PCI DSS standard guarantees safe and secure processing.

## Request example

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.payota.net/api/public/v1/manage/init_partners' \
--header 'Content-Type: application/json' \
--data '{
  "object_id": "32165487",
  "pay_uuid": "797870e3-e1f0-470a-87b3-38694f58bed1",
  "init_uuid": "c44ef1ba-595b-437f-ad14-74ce39a0f9ad",
  "user_last_name": "LastName",
  "cvc": "123",
  "is_cvc_required": true,
  "credit_card_data_core": {
    "year": "18",
    "card_number": "4111111111111111",
    "card_holder": "TEST",
    "month": "01"
  },
  "user_first_name": "Name"
}'
```

## Request body

Expand this|
Collapse this

object\_id
String
required

The booking order ID.

You can find this value of the `item_id` field in the response to the Create booking process call.

ℹ️

- The minimum length is `1` character.
- The maximum length is `20` characters.

pay\_uuid
String
required

The external booking payment ID in the UUID format.

ℹ️

- The length is `36` characters.
- Must be unique for every payment. Otherwise, the ETG will return an error.
- The value matches the pattern: `^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$`.

init\_uuid
String
required

The external booking token ID in the UUID format.

ℹ️

- The length is `36` characters.
- Must be unique for every payment. Otherwise, the ETG will return an error.
- The value matches the pattern: `^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$`.

user\_first\_name
String
required

The guest first name.

ℹ️

- The minimum length is `1` character.

user\_last\_name
String
required

The guest last name.

ℹ️

- The minimum length is `1` character.

cvc
String
optional

The CVC.

ℹ️

- The length is `3` characters.

is\_cvc\_required
Boolean
required

Whether the CVC is required or not.

credit\_card\_data\_core
Object
required

The credit card information.

year
String
required

The valid thru year.

ℹ️

- The length is `2` characters.

card\_number
String
required

The credit card number without spaces.

ℹ️

- The minimum length is `13` characters.
- The maximum length is `19` characters.

card\_holder
String
required

The cardholder name.

ℹ️

- The minimum length is `1` character.

month
String
required

The valid thru month.

ℹ️

- The length is `2` characters.

## Response example

```
{
  "status": "ok"
}
```

## Errors

The `error` field has the value specified in the headers below.

### `body_error`

The passed JSON isn’t valid.

### `validation_error`

Not all required field values are passed.

### `invalid_pay_uuid`

The `invalid_pay_uuid` field value doesn’t match the pattern.

### `invalid_init_uuid`

The `invalid_init_uuid` field value doesn’t match the pattern.

### `invalid_month`

The month is invalid. Try a different one.

### `invalid_year`

The year is invalid. Try a different one.

### `invalid_cvc`

Incorrect CVC code.

### `invalid_card_number`

The credit card data is invalid. Try a different one.

### `invalid_card_holder`

The cardholder name is invalid. Try a different one.

### `invalid_is_cvc_required`

The `is_cvc_required` field value is required.

### `luhn_algorithm_error`

The credit card number hasn’t passed the Luhn algorithm check.
