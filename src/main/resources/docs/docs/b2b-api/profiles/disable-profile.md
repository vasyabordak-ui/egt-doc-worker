---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/b2b-api/profiles/disable-profile/
tags:
- etg-docs
- b2b-api
- profiles
title: Disable profile
---

# Disable profile

ETG API V3

B2B API

Profiles

Disable profile

# Disable profile

#b2b

```
https://api.worldota.net/api/b2b/v3/profiles/disable/
```

The call disables a contract profile.

## Request example

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/profiles/disable/' \
--header 'Content-Type: application/json' \
--data-raw '{
  "email": "[email protected]"
}'
```

## Request body

Expand this|
Collapse this

email
String
required

The profile email address.

## Request example

```
{"data":null,"debug":null,"error":"profile_is_already_disabled","status":"error"}
```

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

### `users_profile_not_found`

The profile isn’t found.

### `profile_is_already_disabled`

The profile with such an email address is already disabled.

### `disable_users_profile_error`

An unknown error. To eliminate the error, contact the API support team.
