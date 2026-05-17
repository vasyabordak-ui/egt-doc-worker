---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/b2b-api/profiles/restore-profile/
tags:
- etg-docs
- b2b-api
- profiles
title: Restore profile
---

# Restore profile

ETG API V3

B2B API

Profiles

Restore profile

# Restore profile

#b2b

```
https://api.worldota.net/api/b2b/v3/profiles/restore/
```

The call restores a contract profile.

## Request example

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/profiles/restore/' \
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

### Response example

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

### `profile_is_already_restored`

The profile with such an email address is already restored.

### `restore_users_profile_error`

An unknown error. To eliminate the error, contact the API support team.
