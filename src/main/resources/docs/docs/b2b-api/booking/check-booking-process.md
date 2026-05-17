---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/b2b-api/booking/check-booking-process/
tags:
- etg-docs
- b2b-api
- booking
title: Check booking process
---

# Check booking process

ETG API V3

B2B API

Booking

Check booking process

# Check booking process

#b2b

Sandbox Production

```
https://api-sandbox.worldota.net/api/b2b/v3/hotel/order/booking/finish/status/
```

```
https://api.worldota.net/api/b2b/v3/hotel/order/booking/finish/status/
```

ℹ️

- **This call is required** if you don’t use the Receive booking status webhook call.
- As the booking process is made asynchronously, repeatedly request this call to know the status.

The call checks the booking process status.

## Sandbox limitations

⚠️

Use only field values, IDs, API keys, and any static content from the sandbox environment within the sandbox. **Do not use sandbox data in test or production environments, and do not mix data or configuration between different environments.**

- The following features are not supported: 3D Secure check and cut-off logic.
- The `data_3ds` field is not supported.
- The possible values for the `status` field are: `ok`, `processing`, and `error`.
- The following errors are not supported: `block`, `charge`, `3ds`, and `not_allowed`.

## Usage scenarios

The only possible call requesting scenarios:

- General. No need for you to make additional steps except for receiving the final status.
- 3D Secure check. Needs you to make additional steps.

## 3D Secure check

ℹ️

**The scenario is required to be completed** if the rate you are booking has the `payment_types` field with the `now` value.

To complete the scenario, make the following steps:

1. Request this call and receive the check details.
2. Make the 3D Secure check request.
3. After the 3D Secure check is completed, the payment gateway will redirect the user to the URL passed in the `return_path` field value of the Start booking process call.
4. Request this call again and receive the final status.

### 3D Secure check details

If the issuing bank supports 3D Secure cards, as the response to this call you will get:

- The `status` field with the `3ds` value.
- The special data in `data.data_3ds` field.

The response example:

```
{
  "data": {
    "data_3ds": {
      "action_url": "https://example.com/ACS/auth/start.do",
      "data": {
        "MD": "94cf25b2-aa6d-4204-83e4-acf036d263f6",
        "PaReq": "eJxVkt1ygjAQhV/F4R7zIwI6azptqa2dAR0L0+s0RKAV0ADVvn0TC33833+zObs5G7g5l/vRp1RNUVcLi4yxNZKVqNOiyhZWEi9t37phEOdKyuBFik5JBqFsGp6JUZEurBl358xfpLbvSWw7U05sHzvU3hGyS92Z8DyfWww2t1t5ZNA3YrrPmAIaUCsqkfOqZcDF8W4VMYd6LsaAeoRSqlXACJ04U9fzAf0wVLyUTPFW5vz0AeiCIOquatUXc20JoAGgU3uWt+1hjtBwYSzqEpA5APQ3waYzUaOFzkXKwvfwHMXZaR08kHWQOGH8cIqC5BTGyQKQqYBUyzGKKcYOpiPszbEzx1NAlzzw0kzACJmYXE9wME1ur47+p0AbrfQehkcMBPJ8qCupK7R7vzGgv3nvn4yHotXuLPPZIY2ecbeLGlI8bulbkh2zzRZvXvfG2UuRUSy0P5Rg9yJpAJCRQf3SUL9vHV39g287E7qa",
        "TermUrl": "https://example.com/rebpayment/rest/finish3ds.do?ret_path=finish"
      },
      "method": "post"
    },
    "partner_order_id": "asd123",
    "percent": 66
  },
  "debug": null,
  "error": null,
  "status": "3ds"
}
```

### 3D Secure check request

Send the request with received data for the 3D Secure check:

- Via the GET method if the `data.data_3ds.method` field has the `get` value.
- Or via the POST method if the `data.data_3ds.method` field has the `post` value.

The request example:

```
curl -d '{"PaReq":"eJxVkt1ygjAQhV/F4R7zIwI6azptqa2dAR0L0+s0RKAV0ADVvn0TC33833+zObs5G7g5l/vRp1RNUVcLi4yxNZKVqNOiyhZWEi9t37phEOdKyuBFik5JBqFsGp6JUZEurBl358xfpLbvSWw7U05sHzvU3hGyS92Z8DyfWww2t1t5ZNA3YrrPmAIaUCsqkfOqZcDF8W4VMYd6LsaAeoRSqlXACJ04U9fzAf0wVLyUTPFW5vz0AeiCIOquatUXc20JoAGgU3uWt+1hjtBwYSzqEpA5APQ3waYzUaOFzkXKwvfwHMXZaR08kHWQOGH8cIqC5BTGyQKQqYBUyzGKKcYOpiPszbEzx1NAlzzw0kzACJmYXE9wME1ur47+p0AbrfQehkcMBPJ8qCupK7R7vzGgv3nvn4yHotXuLPPZIY2ecbeLGlI8bulbkh2zzRZvXvfG2UuRUSy0P5Rg9yJpAJCRQf3SUL9vHV39g287E7qa","termurl":"https://example.com/rebpayment/rest/finish3ds.do?ret_path=finish","MD":"94cf25b2-aa6d-4204-83e4-acf036d263f6}"' https://example.com/ACS/auth/start.do
```

## Result interpretation

The result is described in the `status` response field. The possible values:

- `ok` — the booking finishing has ended with success.
- `processing` — the booking finishing is in progress. Request the status change every second until you get the `ok` or `error` value.
- `3ds` — the booking finishing needs to complete the 3D Secure check.
- `error` — the booking finishing has ended with an error.

⚠️

Don’t forget that you might have the `timeout`, `unknown`, and `5xx` error.

## Retry logic

During the maximum booking time request this call any time you like. The recommended time is once per 5 seconds.

⚠️

Always send a final status request at the last second before the booking timeout, even if you check every 5 seconds. This reduces the risk of missing the correct status, especially with short timeouts.

## Request example

Sandbox Production

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api-sandbox.worldota.net/api/b2b/v3/hotel/order/booking/finish/status/' \
--header 'Content-Type: application/json' \
--data '{
  "partner_order_id": "0b370500-5321-4046-92c5-5982f1a64fc6"
}'
```

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/hotel/order/booking/finish/status/' \
--header 'Content-Type: application/json' \
--data '{
  "partner_order_id": "0b370500-5321-4046-92c5-5982f1a64fc6"
}'
```

## Request body

Expand this|
Collapse this

partner\_order\_id
String
required

The unique partner booking ID.

ℹ️

- The minimum length is `1` character.
- The maximum length is `256` characters.

## Response

Expand this|
Collapse this

data\_3ds
Object

The 3D Secure check information.

action\_url
String

The URL of a new request for the 3D Secure check.

Must have the `data` object field as the query parameter.

data
Object
optional

The 3D Secure check information that should be sent back.

MD
String
optional

The payment session ID returned by the card issuer.

PaReq
String
optional

The 3D Secure request data for the issuer.

ℹ️

The line breaks in the value must be retained in all requests. Otherwise, the transaction will be declined.

TermUrl
String
optional

The URL of the merchant site page to redirect the user to after completing 3D Secure check on the card issuer site.

ℹ️

- The maximum length is `1024` characters.

method
String

The HTTP method of sending the information.

ℹ️

- The possible values:
  - `get`.
  - `post`.

partner\_order\_id
String

The external booking ID in the UUID format.

The ID remains the same if you cancel a booking that:

- Is successful.
- Is failed.
- Has the fail status response from the Check booking process call.

Use this field for the rest of the booking calls.

ℹ️

- The value should be unique for the order within the same contact.
- The minimum length is `3` character.
- The maximum length is `256` characters.

percent
Int

deprecated

The order confirmation percentage.

All current statuses are described in the Result interpretation section.

The `ok` status in the response indicates that the order is confirmed.

## Response example

```
{
  "data": {
    "data_3ds": null,
    "partner_order_id": "0b370500-5321-4046-92c5-5982f1a64fc6",
    "percent": 100
  },
  "debug": null,
  "error": null,
  "status": "ok"
}
```

## Errors

The `error` field has the value specified in the headers below.

### `block`

The card funds can’t be frozen (blocked) for the booking payment.

### `charge`

The card funds can’t be withdrawn for the booking payment due to:

- A failed freeze.
- Another reason.

### `3ds`

The `MD` field value is invalid.

### `soldout`

The rate is no longer available as its rooms are sold out.

### `book_limit`

The cut-off logic limit for the booking finishing is reached.

### `not_allowed`

There is no permission to use this call for this contract:

1. Contact the API support team.
2. Tell the user the booking error has occurred.

When contacting the API support team, provide at least:

- The hotel name where the booking is in process.
- The `order_id` field with the value from the Create booking process call.
- The `user` and `rooms` fields with the values from the Start booking process call.

This information will help to identify the request attempt.

### `provider`

A technical error at the rate provider side.

### `order_not_found`

The order with the `partner_order_id` field value isn’t found. Try to change the value.

### `booking_finish_did_not_succeed`

An attempt to request this call without a successful response from the Start booking process call.

### `timeout`, `unknown`, and `5xx`

An internal error. Continue to request this call until you get the following response:

- The `status` field has the `ok` value.
- The `error` field has one of the values:
  - `3ds`.
  - `block`.
  - `book_limit`.
  - `booking_finish_did_not_succeed`.
  - `charge`.
  - `decoding_json`.
  - `endpoint_exceeded_limit`.
  - `endpoint_not_active`.
  - `endpoint_not_found`.
  - `incorrect_credentials`.
  - `invalid_auth_header`.
  - `invalid_params`.
  - `lock`.
  - `no_auth_header`.
  - `not_allowed`.
  - `not_allowed_host`.
  - `order_not_found`.
  - `overdue_debt`.
  - `provider`.
  - `soldout`.
  - `unexpected_method`.

ℹ️

The requests should be sent within the agreed booking timeout.

## Error Handling Diagram

Below is a sequence diagram describing how to handle errors during the booking check process:

```
sequenceDiagram
    participant User
    participant API

    User->>API: Check booking process
    alt status == "ok"
        API-->>User: Booking is successfully confirmed
    else status == "processing" or error is (timeout/unknown) or HTTP status code 5xx
        loop Wait and poll (until status is "ok", final error, or booking timeout reached)
            User->>API: Check Booking Process
            alt status == "ok"
                API-->>User: Booking is successfully confirmed
                User->>User: Stop polling
            else status == "processing" or error is (timeout/unknown) or HTTP status code 5xx
                API-->>User: Still processing or transient error, continue polling
            else status is final error (e.g., soldout, provider, book_limit) or booking timeout reached
                API-->>User: Return final error or booking timeout
                User->>User: Stop polling, show error
            end
        end
    else status is final error (e.g., soldout, provider, book_limit) or booking timeout reached
        API-->>User: Return final error or booking timeout
        User->>User: Stop, show error
    end
```
