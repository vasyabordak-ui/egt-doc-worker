---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/b2b-api/profiles/edit-profile/
tags:
- etg-docs
- b2b-api
- profiles
title: Edit profile
---

# Edit profile

ETG API V3

B2B API

Profiles

Edit profile

# Edit profile

#b2b

```
https://api.worldota.net/api/b2b/v3/profiles/edit/
```

The call edits a contract profile.

## Request example

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/profiles/edit/' \
--header 'Content-Type: application/json' \
--data-raw '{
  "email": "[email protected]",
  "first_name": "John",
  "last_name": "Smith",
  "phone": "+4412344567899"
  "type": "manager"
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

phone
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

## Response

Expand this|
Collapse this

user
[Object]

The profile list.

email
String

The profile email address.

first\_name
String

The profile first name.

ℹ️

The value matches the pattern: `^[^\W\d_]+([^\W\d_]*[\u0590-\u05FF\u0900-\u097F\u0980-\u09FF\u0E00-\u0E7F'\-,.’\s]*)*$`.

last\_name
String

The profile last name.

ℹ️

The value matches the pattern: `^[^\W\d_]+([^\W\d_]*[\u0590-\u05FF\u0900-\u097F\u0980-\u09FF\u0E00-\u0E7F'\-,.’\s]*)*$`.

middle\_name
String

The profile middle name.

ℹ️

The value matches the pattern: `^[^\W\d_]+([^\W\d_]*[\u0590-\u05FF\u0900-\u097F\u0980-\u09FF\u0E00-\u0E7F'\-,.’\s]*)*$`.

phone
String

The profile phone number.

ℹ️

- The minimum length is `5` characters.
- The maximum length is `35` characters.

status
String

The profile status.

ℹ️

- The possible values:
  - `active`.
  - `awaiting_confirmation`.
  - `deleted`.
  - `disabled`.

type
String

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
  "data":{
    "user":{
      "email":"[email protected]",
      "first_name":"John",
      "last_name":"Smith",
      "middle_name":null,
      "phone":"+4412344567899",
      "status":"awaiting_confirmation",
      "type":"manager"
    }
  },
  "debug":null,
  "error":null,
  "status":"ok"
}
```

## Errors

The `error` field has the value specified in the headers below.

### `users_profile_not_found`

The profile isn’t found.
