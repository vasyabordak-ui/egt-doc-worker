---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/b2b-api/order-groups/make-order-group-overpay/
tags:
- etg-docs
- b2b-api
- order-groups
title: Make order group overpay
---

# Make order group overpay

ETG API V3

B2B API

Order groups

Make order group overpay

# Make order group overpay

#b2b

```
https://api.worldota.net/api/b2b/v3/ordergroup/pay/overpay/ HTTP/1.1
```

The call makes the payment for the existing order group from the overpay.

## Request example

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/ordergroup/pay/overpay/?data={%22invoice_id%22%3A%2212980-00236%22%2C%22amount%22%3A%22314.15%22}'
```

## Parameters

Expand this|
Collapse this

amount
String, Query
required

The order group amount.

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

### `invoice_already_paid`

The order group is already paid for.

### `overpay_not_enough`

The overpay amount is less than the pay amount for the order group.

### `invoice_not_found`

The order group with the `invoice_id` field value isn’t found.

### `payment_amount_discrepancy`

The order group amount differs from the order group needed amount.

### `ordergroup_is_being_paid`

The order group is currently being paid for. The payment is in processing.
