---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/fundamentals/authorization/
tags:
- etg-docs
- fundamentals
- authorization
title: Authorization
---

# Authorization

ETG API V3

Fundamentals

Authorization

# Authorization

#fundamentals

The **sandbox** host: `https://api-sandbox.worldota.net`.

The **test and production** host: `https://api.worldota.net`.

The API key is the sequence of characters used to send API requests.

The ETG:

- Requires HTTP Basic Authentication via the API key.
- Grants the following API key types:
  - `sandbox` — the sandbox envitonment.
  - `test`— the test environment.
  - `production`— the production environment.

## Sandbox environment

Use the sandbox API keys to safely test accommodation search, booking, and cancellation without impacting real data or financial transactions.

The sandbox environment lets you explore various scenarios using sample properties before working in production.

## Test environment

Use test API keys only to book the test hotel with `hid` - `8473727` or `id` - `test_hotel_do_not_book`.

⚠️

The test hotel implies real bookings with all financial responsibilities.

## Production environment

Use the production API keys to book hotels available via the ETG API.

You may have several production API keys across different contracts.

## API key in account

You can find the created API keys in the **API** section of your contract settings. Access to this section is provided for the Master account only.

## API key structure

The API key structure consists of the two values: `<KEY_ID>:<API_KEY>`.

### `KEY_ID`

API key ID. Use it as an HTTP Basic Authentication username.

### `API_KEY`

API key access token. Use it as an HTTP Basic Authentication password.

Keep this value confidential. Otherwise, a third party may gain access to and misuse your data.

## Request limits

Each endpoint has a limit on the number of requests. To discuss changes to these limits, contact your account manager.

The limit is specified in the corresponding header of the call response. The ETG API provides:

- The maximum number of requests allowed per specified period.
- The number of remaining requests in the current period.
- The timestamp indicating the expiration of the limitation period.

### `X-RateLimit-SecondsNumber`

The number of seconds during which requests can be executed within the `X-RateLimit-RequestsNumber`.

### `X-RateLimit-RequestsNumber`

The maximum number of requests that can be executed within the `X-RateLimit-SecondsNumber`.

### `X-RateLimit-Remaining`

The number of remaining requests in the current period:

- Within the `X-RateLimit-SecondsNumber`.
- Out of the `X-RateLimit-RequestsNumber`.

### `X-RateLimit-Reset`

The string with the date and time when the `X-RateLimit-SecondsNumber` expires. The format is `YYYY-MM-DDTHH:MM:SS` in the UTC+0 time zone.

### Limits example

```
"X-RateLimit-SecondsNumber": 1
"X-RateLimit-RequestsNumber": 10
"X-RateLimit-Remaining": 9
"X-RateLimit-Reset": "2018-08-14T08:54:11"
```
