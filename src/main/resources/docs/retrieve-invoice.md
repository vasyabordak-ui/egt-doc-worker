---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/b2b-api/documents/retrieve-invoice/
tags:
- etg-docs
- b2b-api
- documents
title: Retrieve invoice
---

# Retrieve invoice

ETG API V3

B2B API

Documents

Retrieve invoice

# Retrieve invoice

#b2b

```
https://api.worldota.net/api/b2b/v3/ordergroup/document/invoice/download/
```

The call gets the order group invoice. The invoice is provided in PDF format.

## Request example

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/ordergroup/document/invoice/download/?data={%22invoice_id%22%3A%2212345-67890%22}'
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

## Errors

The `error` field has the value specified in the headers below.

### `failed_to_generate_document`

The invoice is in generating. Try to request later.

### `invoice_not_found`

The order group with the `invoice_id` field value isn’t found.

### `terminal_invoice`

The group order has the `payment_options.payment_types.later` field or is paid for by a terminal.
