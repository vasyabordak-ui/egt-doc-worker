---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/b2b-api/order-groups/remove-from-order-group/
tags:
- etg-docs
- b2b-api
- order-groups
title: Remove order group
---

# Remove order group

ETG API V3

B2B API

Order groups

Remove order group

# Remove order group

#b2b

```
https://api.worldota.net/api/b2b/v3/ordergroup/order/remove/ HTTP/1.1
```

The call deletes the order from the existing order group.

## Request example

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/ordergroup/order/remove/?data={%22invoice_id%22%3A%2212980-00259%22%2C%22orders%22%3A[{%22order_id%22%3A197205577%2C%22order_type%22%3A%22hotel%22}]}'
```

## Parameters

Expand this|
Collapse this

orders
String, Query
required

The bookings’ information.

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

### `invoice_not_found`

The order group with the `invoice_id` field value isn’t found.

### `orders_are_blocked`

The order is in processing.

### `orders_not_found`

The order with the `order_id` field value isn’t found.
