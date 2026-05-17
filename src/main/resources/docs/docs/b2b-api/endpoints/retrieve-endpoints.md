---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/b2b-api/endpoints/retrieve-endpoints/
tags:
- etg-docs
- b2b-api
- endpoints
title: Retrieve endpoints
---

# Retrieve endpoints

ETG API V3

B2B API

Endpoints

Retrieve endpoints

# Retrieve endpoints

Sandbox Production

```
https://api-sandbox.worldota.net/api/b2b/v3/overview/
```

```
https://api.worldota.net/api/b2b/v3/overview/
```

The call gets the list of the ETG API endpoints to which your API key has permission.

ℹ️

- Increasing limits is possible for a live key after separate approval, depending on the expected sales volume. Please contact our API support team for this.
- Limits are increased only after certification is completed.

## Sandbox limitations

⚠️

Use only field values, IDs, API keys, and any static content from the sandbox environment within the sandbox. **Do not use sandbox data in test or production environments, and do not mix data or configuration between different environments.**

Not every call is supported. The available call list depends on a sandbox API key.

## Request example

Sandbox Production

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api-sandbox.worldota.net/api/b2b/v3/overview/'
```

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/overview/'
```

## Response

Expand this|
Collapse this

endpoint
String

The endpoint URI.

is\_active
Boolean

Whether the endpoint is active or not.

is\_debug\_mode
Boolean

Whether the debug mode is available or not.

is\_limited
Boolean

Whether the endpoint has a limit for the request in seconds or not.

requests\_number
Int

The maximum number of requests that can be executed within seconds specified in the `seconds_number` field.

seconds\_number
Int

The number of seconds within the request can be executed. Shouldn’t exceed the value of the `requests_number` field.

## Response example

```
{
  "data": [
    {
      "endpoint": "api/b2b/v3/hotel/incremental_reviews/dump/",
      "is_active": true,
      "is_debug_mode": false,
      "is_limited": true,
      "requests_number": 100,
      "seconds_number": 86400
    },
    {
      "endpoint": "api/b2b/v3/general/contract/data/info/",
      "is_active": true,
      "is_debug_mode": false,
      "is_limited": true,
      "requests_number": 30,
      "seconds_number": 60
    },
    {
      "endpoint": "api/b2b/v3/ordergroup/create/",
      "is_active": true,
      "is_debug_mode": false,
      "is_limited": true,
      "requests_number": 30,
      "seconds_number": 60
    },
    {
      "endpoint": "api/b2b/v3/ordergroup/order/add/",
      "is_active": true,
      "is_debug_mode": false,
      "is_limited": true,
      "requests_number": 30,
      "seconds_number": 60
    },
    {
      "endpoint": "api/b2b/v3/ordergroup/order/remove/",
      "is_active": true,
      "is_debug_mode": false,
      "is_limited": true,
      "requests_number": 30,
      "seconds_number": 60
    },
    {
      "endpoint": "api/b2b/v3/ordergroup/disband/",
      "is_active": true,
      "is_debug_mode": false,
      "is_limited": true,
      "requests_number": 30,
      "seconds_number": 60
    },
    {
      "endpoint": "api/b2b/v3/ordergroup/info/",
      "is_active": true,
      "is_debug_mode": false,
      "is_limited": true,
      "requests_number": 30,
      "seconds_number": 60
    }
  ],
  "debug": null,
  "error": null,
  "status": "ok"
}
```
