---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/b2b-api/profiles/retrieve-profiles/
tags:
- etg-docs
- b2b-api
- profiles
title: Retrieve profiles
---

# Retrieve profiles

ETG API V3

B2B API

Profiles

Retrieve profiles

# Retrieve profiles

#b2b

```
https://api.worldota.net/api/b2b/v3/profiles/list/
```

The call gets a contract profiles list.

## Request example

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/profiles/list/'
```

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
    "users":[
      {
        "email":"[email protected]",
        "first_name":"Eliot",
        "last_name":"White",
        "middle_name":null,
        "phone":"+38112344567888",
        "status":"awaiting_confirmation",
        "type":"master"
      },
      {
        "email":"[email protected]",
        "first_name":"John",
        "last_name":"Smith",
        "middle_name":null,
        "phone":"+4412344567899",
        "status":"awaiting_confirmation",
        "type":"manager"
      }
    ]
  },
  "debug":null,
  "error":null,
  "status":"ok"
}
```
