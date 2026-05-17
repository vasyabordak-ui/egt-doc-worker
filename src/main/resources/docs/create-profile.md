---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/b2b-api/profiles/create-profile/
tags:
- etg-docs
- b2b-api
- profiles
title: Create profile
---

# Create profile

ETG API V3

B2B API

Profiles

Create profile

# Create profile

#b2b

```
https://api.worldota.net/api/b2b/v3/profiles/create/
```

The call creates a contract profile.

## Request example

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/profiles/create/' \
--header 'Content-Type: application/json' \
--data-raw '{
  "email": "[email protected]",
  "first_name": "John",
  "last_name": "Smith",
  "phone": "+4412344567899",
  "type": "master",
}'
```

## Request body

Expand this|
Collapse this

email
String
required

The profile email address.

first\_name
String
required

The profile first name.

ℹ️

The value matches the pattern: `^[^\W\d_]+([^\W\d_]*[\u0590-\u05FF\u0900-\u097F\u0980-\u09FF\u0E00-\u0E7F'\-,.’\s]*)*$`.

last\_name
String
required

The profile last name.

ℹ️

The value matches the pattern: `^[^\W\d_]+([^\W\d_]*[\u0590-\u05FF\u0900-\u097F\u0980-\u09FF\u0E00-\u0E7F'\-,.’\s]*)*$`.

middle\_name
String
optional

The profile middle name.

ℹ️

The value matches the pattern: `^[^\W\d_]+([^\W\d_]*[\u0590-\u05FF\u0900-\u097F\u0980-\u09FF\u0E00-\u0E7F'\-,.’\s]*)*$`.

type
String
optional

The profile phone number.

ℹ️

- The minimum length is `5` characters.
- The maximum length is `35` characters.

type
String
required

The profile type.

ℹ️

- The possible values:
  - `csbt_admin`.
  - `employee`.
  - `finance`.
  - `manager`.
  - `master`.
  - `self_booker`.
  - `sub_agent_supervisor`.
  - `supervisor`.
  - `travel_manager`.

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

### `user_already_exists`

The profile with such an email address already exists.
