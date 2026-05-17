---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/b2b-api/profiles/delete-profile/
tags:
- etg-docs
- b2b-api
- profiles
title: Delete profile
---

# Delete profile

ETG API V3

B2B API

Profiles

Delete profile

# Delete profile

#b2b

```
https://api.worldota.net/api/b2b/v3/profiles/delete/
```

The call deletes a contract profile.

## Request example

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/profiles/delete/' \
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

## Response example

```
{
  "data":null,
  "debug":null,
  "error":null,
  "status":"ok"
}
```

## Errors

The `error` field has the value specified in the headers below.

### `users_profile_not_found`

The profile isn’t found.

### `delete_users_profile_error`

An unknown error. To eliminate the error, contact the API support team.
