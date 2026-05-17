---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/b2b-api/order-groups/create-order-group/
tags:
- etg-docs
- b2b-api
- order-groups
title: Create order group
---

# Create order group

ETG API V3

B2B API

Order groups

Create order group

# Create order group

#b2b

```
https://api.worldota.net/api/b2b/v3/ordergroup/create/ HTTP/1.1
```

The call creates an order group. Each order should be in one order group only.

## Request example

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/ordergroup/create/?data={%22orders%22%3A[{%22order_id%22%3A197205577%2C%22order_type%22%3A%22hotel%22}]}'
```

## Parameters

Expand this|
Collapse this

orders
String, Query
required

The bookings’ information.

## Response

Expand this|
Collapse this

invoice\_id
String, Query

The order group ID.

ℹ️

- The minimum length is `1` character.

## Response example

```
{
  "data": {
    "invoice_id": "12980-00259"
  },
  "debug": null,
  "error": null,
  "status": "ok"
}
```

## Errors

The `error` field has the value specified in the headers below.

### `orders_not_found`

The order group with the `order_id` field value isn’t found.

### `orders_already_added`

The order is already added to this or another order group.

### `orders_are_blocked`

The order is in processing.

### `order_not_white_b2b_invoiceable`

The order group couldn’t be created for the requested order.

### `different_contract_data`

The bookings have different contract data.
