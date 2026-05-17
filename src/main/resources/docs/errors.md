---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/fundamentals/errors/
tags:
- etg-docs
- fundamentals
- errors
title: Errors
---

# Errors

ETG API V3

Fundamentals

Errors

# Errors

#fundamentals

## Error fields

The `debug` response field has information on your error.

```
{
  "data": null,
  "debug": {
    "api_endpoint": {
      "endpoint": "api/b2b/v3/hotel/info/",
      "is_active": true,
      "is_limited": true,
      "remaining": 9,
      "requests_number": 10,
      "reset": "2018-08-16T12:12:37",
      "seconds_number": 1
    },
    "api_key_id": 1304,
    "method": "GET",
    "real_ip": "62.76.100.3",
    "request_id": "68c1141361db8c82835ea268486d7c6f",
    "utcnow": "2018-08-16T12:12:36.328390"
  },
  "error": "decoding_json",
  "status": "error"
}
```

## 400

### Invalid parameters

The error occurs if all required data isn’t passed. Check the `validation_error` field and pass the required data.

```
{
  "data": null,
  "debug": {
    "api_endpoint": {
      "endpoint": "api/b2b/v3/hotel/order/info/",
      "is_active": true,
      "is_limited": true,
      "remaining": 9,
      "requests_number": 10,
      "reset": "2018-08-23T09:59:38",
      "seconds_number": 1
    },
    "api_key_id": 1304,
    "method": "POST",
    "real_ip": "52.29.104.100",
    "request_id": "7f4937bdabe8bb13d4980dcd77a16117",
    "utcnow": "2018-08-23T09:59:37.495757",
    "validation_error": "value \"page_number\" is required but None"
  },
  "error": "invalid_params",
  "status": "error"
}
```

### Not allowed host

The error occurs if it is forbidden to request your host or IP.

ℹ️

To discuss a change to the permission, contact the API support team.

```
{
  "data": null,
  "debug": {
    "real_ip": "52.29.104.100",
    "request_id": "7b76e52a742f714e6686761fab48cfa3",
    "utcnow": "2018-08-23T11:31:44.235203"
  },
  "error": "not_allowed_host",
  "status": "error"
}
```

### Unexpected method

The error occurs if the request was made using an unsupported HTTP method. Use the POST and GET methods instead.

```
{
  "data": null,
  "debug": {
    "api_key_id": 1304,
    "real_ip": "52.29.104.100",
    "request_id": "19925f65c2763212bbe37294178b6fa6",
    "utcnow": "2018-08-23T09:56:37.677774"
  },
  "error": "unexpected_method",
  "status": "error"
}
```

### Invalid Json body type

The request body must be a JSON object ({}), but received a different JSON type (e.g., array, string, number).

```
{
    "data": null,
    "debug": {
        "api_endpoint": {
            "endpoint": "api/b2b/v3/hotel/order/booking/form/",
            "is_active": true,
            "is_limited": true,
            "remaining": 289,
            "requests_number": 290,
            "reset": "2025-08-05T15:20:00",
            "seconds_number": 600
        },
        "api_key_id": 1304,
        "method": "POST",
        "real_ip": "10.8.43.143",
        "request_id": "4f75423b026798027165be29ff56141b",
        "utcnow": "2025-08-05T15:12:00.046036"
    },
    "error": "body_must_be_json_object",
    "status": "error"
}
```

## 402

### Overdue debt

The error occurs if you have an overdue debt.

ℹ️

Contact your account manager.

```
{
  "data": null,
  "debug": {
    "real_ip": "52.29.104.100",
    "request_id": "36f4a2ae479cc0eba8e3ddf7d09bba14",
    "utcnow": "2018-08-23T10:11:08.045023"
  },
  "error": "overdue_debt",
  "status": "error"
}
```

## 403

### Authorization header

The error occurs if the request:

- Has no authorization header.
- Has invalid data in the authorization header.

The `error` response field can have the following values:

- `no_auth_header`.
- `invalid_auth_header`.

ℹ️

Check the Authorization section.

```
{
  "data": null,
  "debug": {
    "real_ip": "52.29.104.100",
    "request_id": "563c919d77000963a4ef9ade0988b21b",
    "utcnow": "2018-08-23T10:03:28.794712"
  },
  "error": "no_auth_header",
  "status": "error"
}
```

### Endpoint is not found

The error occurs if you don’t have permission to use the call.

ℹ️

To discuss a change to the permission, contact your account manager.

```
{
  "data": null,
  "debug": {
    "api_key_id": 1775,
    "method": "POST",
    "real_ip": "10.11.0.23",
    "request_id": "45141c733a1f4d534a0518c513d3641b",
    "status": 403,
    "utcnow": "2018-08-14T12:51:58.715639"
  },
  "error": "endpoint_not_found",
  "status": "error"
}
```

## Authentication and Authorization Errors

ℹ️

In case of recurring unauthorized requests over a considerable period, the ETG may ban the IP address of sent requests.

### Incorrect API credentials

This error occurs if you have used incorrect API credentials.

ℹ️

Make sure your API key and other authentication details are valid. For additional help, contact your account manager.

```
{
  "data": null,
  "debug": {
    "real_ip": "52.29.104.100",
    "request_id": "e6ff36a9e635825743e7ded8c05739a0",
    "utcnow": "2018-08-23T09:34:43.673713"
  },
  "error": "incorrect_credentials",
  "status": "error"
}
```

### API access disabled

API access is disabled for your account.

ℹ️

For inquiries about enabling API access, please contact your account manager.

```
{
  "data": null,
  "debug": {
    "request": {
      "checkin": "2025-07-18",
      "checkout": "2025-07-19",
      "residency": "KW",
      "language": "en",
      "guests": [
        {
          "adults": 2,
          "children": []
        }
      ],
      "id": "test_hotel_do_not_book",
      "currency": "KWD"
    },
    "key_id": 0,
    "validation_error": null
  },
  "status": "error",
  "error": "api_access_disabled"
}
```

### Not allowed

The error occurs if your API key doesn’t have permission to use this API call.

ℹ️

To discuss a change to the permission, contact your account manager.

```
{
  "data": null,
  "debug": {
    "real_ip": "52.29.104.100",
    "request_id": "7b76e52a742f714e6686761fab48cfa3",
    "utcnow": "2018-08-23T11:31:44.235203"
  },
  "error": "not_allowed",
  "status": "error"
}
```

## Endpoint is not active

The error occurs if the endpoint is deactivated.

ℹ️

To discuss a change to the activation, contact your account manager.

```
{
  "data": null,
  "debug": {
    "api_endpoint": {
      "endpoint": "api/b2b/v3/hotel/info/",
      "is_active": false,
      "is_limited": true,
      "requests_number": 1,
      "seconds_number": 20
    },
    "api_key_id": 496,
    "method": "GET",
    "real_ip": "10.11.0.23",
    "request_id": "61c015d15d325f0bb945db5d027fc35c",
    "utcnow": "2018-08-16T12:38:38.481018"
  },
  "error": "endpoint_not_active",
  "status": "error"
}
```

## 429

### Limit is exceeded

The error occurs if you have exceeded the number of requests for an endpoint.

ℹ️

To discuss a change in the limit, contact your account manager.

```
{
  "data": null,
  "debug": {
    "api_endpoint": {
      "endpoint": "api/b2b/v3/hotel/info/",
      "is_active": true,
      "is_limited": true,
      "remaining": -1,
      "requests_number": 1,
      "reset": "2018-08-16T12:32:40",
      "seconds_number": 20
    },
    "api_key_id": 496,
    "method": "GET",
    "real_ip": "10.11.0.23",
    "request_id": "8dd3efd24bdd6a9272770936e5108f8c",
    "utcnow": "2018-08-16T12:32:26.161565"
  },
  "error": "endpoint_exceeded_limit",
  "status": "error"
}
```

### Endpoint is locked

This error occurs if requests for an endpoint meet both conditions:

- Several requests use the same `partner_order_id` field value.
- One request is sent twice within a short amount of time.

```
{
  "data": null,
  "debug": {
    "real_ip": "52.29.104.100",
    "request_id": "2c2264a2f3f64b9032624e99ddd49976",
    "utcnow": "2018-08-23T11:43:27.101799"
  },
  "error": "lock",
  "status": "error"
}
```

## 5xx errors

Errors with the `5xx` HTTP status code may occur. Typically due to a timeout in the ETG services. In this case, do both:

- Retry the request.
- Limit the number of repeated identical requests.

For these calls, refer to the descriptions of the call errors:

- Create booking process (B2B, Affiliate).
- Start booking process (B2B, Affiliate).
- Check booking process (B2B, Affiliate).

## Example of unknown error

The error occurs if something else has gone wrong.

```
{
  "data": null,
  "debug": null,
  "error": "unknown",
  "status": "error"
}
```
