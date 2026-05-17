---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/b2b-api/documents/retrieve-single-act/
tags:
- etg-docs
- b2b-api
- documents
title: Retrieve single act
---

# Retrieve single act

ETG API V3

B2B API

Documents

Retrieve single act

# Retrieve single act

#b2b

```
https://api.worldota.net/api/b2b/v3/hotel/order/document/single_act/download/
```

The call gets the order act. The act is provided in PDF format.

## Request example

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/hotel/order/document/single_act/download/?data={%22partner_order_id%22%3A%22asd123%22%2C%22show_b2b2c_price%22%3Atrue%2C%22add_commission%22%3Atrue%2C%22seal%22%3Atrue}'
```

## Parameters

Expand this|
Collapse this

add\_commission
String, Query
optional

Whether the commission information is needed or not.

partner\_order\_id
String, Query
required

The external order ID.

ℹ️

- The minimum length is `1` character.

seal
String, Query
optional

Whether the seal is needed or not.

show\_b2b2c\_price
String, Query
optional

Whether the resale price needs to be shown or not. The price is taken instead of the `amount_sell` field value.

## Errors

The `error` field has the value specified in the headers below.

### `failed_to_generate_document`

The act is in generating. Try to request later.

### `order_not_found`

The order with the `partner_order_id` field value isn’t found.

### `order_not_assigned`

No contract is assigned to the order.

### `single_act_is_not_downloadable`

The act is in processing. Try to request with the following field values in the past or today:

- Amount payable.
- Contract kind.
- Legal entity.
- Checkout date.
