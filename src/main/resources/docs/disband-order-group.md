---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/b2b-api/order-groups/disband-order-group/
tags:
- etg-docs
- b2b-api
- order-groups
title: Disband order group
---

# Disband order group

ETG API V3

B2B API

Order groups

Disband order group

# Disband order group

#b2b

```
https://api.worldota.net/api/b2b/v3/ordergroup/disband/ HTTP/1.1
```

The call disbands the existing order group.

## Request example

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/ordergroup/disband/?data={%22invoice_id%22%3A%2212980-00236%22}'
```

## Parameters

Expand this|
Collapse this

invoice\_id
String, Query
required

The order group ID.

ℹ️

- The minimum length is `1` character.

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

### `invoice_not_disbandable`

The order group can’t be disbanded. It is already paid for.

### `invoice_not_found`

The order group with the `invoice_id` field value isn’t found.
