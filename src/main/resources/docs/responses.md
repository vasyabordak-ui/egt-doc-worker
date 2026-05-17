---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/fundamentals/responses/
tags:
- etg-docs
- fundamentals
- responses
title: Responses
---

# Responses

ETG API V3

Fundamentals

Responses

# Responses

#fundamentals

The response is an object in JSON format. Its fields are listed below.

The example:

```
{
  "data": {
    "data_3ds": null,
    "partner_order_id": "asd123",
    "percent": 100
  },
  "debug": null,
  "error": null,
  "status": "ok"
}
```

## `data`

The response main data. Required field.

ℹ️

You might get a response with empty availability. It means that the request is correct but no data matches the request. The empty availability indicators:

- The `null` value.
- A value of the successful `status`.

## `debug`

Additional information about the response. Contains:

- The initial request parameters in JSON format.
- And/or the HTTP status code.

Can have the `null` value if the request was made without warnings or errors.

### Detailed structure of debug

Each API response may include the optional debug field, which contains technical and diagnostic information.

This field is intended primarily for debugging, development, and troubleshooting.

Structure example:

```
"debug": {
  "api_endpoint": {
    "endpoint": "api/b2b/v3/search/serp/hotels",
    "is_active": true,
    "is_limited": true,
    "remaining": 997,
    "requests_number": 1000,
    "reset": "2025-09-18T07:34:00",
    "seconds_number": 60
  },
  "request": {
    "checkin": "2025-10-25",
    "checkout": "2025-10-26",
    "residency": "us",
    "language": "en",
    "guests": [
      {
        "adults": 2,
        "children": []
      }
    ],
    "hids": [
    8659684
    ],
    "currency": "USD",
    "timeout": 50
  },
  "method": "POST",
  "real_ip": "2a09:bac1:6f20:8::81:41",
  "request_id": "0908428fac35a5e10b424af54234825d",
  "key_id": 1234,
  "api_key_id": 1234,
  "utcnow": "2025-09-18T07:33:31.840932",
  "validation_error": "checkin date must be current or future date"
}
```

Description of fields:

- **api\_endpoint**: Info about rate limits and endpoint activity (url, is\_active, is\_limited, remaining, requests\_number, reset, seconds\_number).
- **request**: The full structure of the partner’s request as received.
- **method**: HTTP request method (e.g. POST, GET).
- **real\_ip**: The detected client IP.
- **request\_id**: Unique internal request ID.
- **key\_id, api\_key\_id**: Identifiers of your API key.
- **utcnow**: UTC timestamp of request handling start.
- **validation\_error**: Error description (present only for requests with invalid parameters).

ℹ️

The debug field is not intended for production monitoring. Its structure may change without notice.

## `error`

The error description.

Can have the `null` value if the request was made without errors.

## `status`

The HTTP status code description.
