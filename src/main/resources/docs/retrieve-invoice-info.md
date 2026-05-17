---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/b2b-api/documents/retrieve-invoice-info/
tags:
- etg-docs
- b2b-api
- documents
title: Retrieve invoice info
---

# Retrieve invoice info

ETG API V3

B2B API

Documents

Retrieve invoice info

# Retrieve invoice info

#b2b

```
https://api.worldota.net/api/b2b/v3/hotel/order/document/info_invoice/download/
```

The call gets the order invoice. The invoice is provided in PDF format.

## Request example

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/hotel/order/document/info_invoice/download/?data={%22partner_order_id%22%3A%22asd123%22}'
```

## Parameters

Expand this|
Collapse this

partner\_order\_id
String, Query
required

The external order ID.

ℹ️

- The minimum length is `1` character.

## Errors

The `error` field has the value specified in the headers below.

### `failed_to_generate_document`

The invoice is in generating. Try to request later.

### `order_not_found`

The order with the `partner_order_id` field value isn’t found.

### `invoice_not_available`

No invoice is available for the order. Check the payable amount, contract kind, legal entity, and contract data.
