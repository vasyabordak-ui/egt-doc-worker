---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/b2b-api/documents/retrieve-voucher/
tags:
- etg-docs
- b2b-api
- documents
title: Retrieve voucher
---

# Retrieve voucher

ETG API V3

B2B API

Documents

Retrieve voucher

# Retrieve voucher

#b2b

```
https://api.worldota.net/api/b2b/v3/hotel/order/document/voucher/download/
```

The call gets the order voucher file. The file is provided in PDF format.

## Request example

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/hotel/order/document/voucher/download/?data={%22partner_order_id%22%3A%22asd123%22%2C%22language%22%3A%22en%22}'
```

## Parameters

Expand this|
Collapse this

language
String, Query
required

The language.

ℹ️

The possible values:

- `ar` — Arabic.
- `bg` — Bulgarian.
- `cs` — Czech.
- `da` — Danish.
- `de` — German.
- `el` — Greek.
- `en` — English.
- `es` — Spanish.
- `fi` — Finnish.
- `fr` — French.
- `he` — Hebrew.
- `hu` — Hungarian.
- `it` — Italian.
- `ja` — Japanese.
- `kk` — Kazakh.
- `ko` — Korean.
- `nl` — Dutch.
- `no` — Norwegian Bokmål.
- `pl` — Polish.
- `pt` — Portuguese.
- `pt_PT` — European Portuguese.
- `ro` — Romanian.
- `ru` — Russian.
- `sq` — Albanian.
- `sr` — Serbian.
- `sv` — Swedish.
- `th` — Thai.
- `tr` — Turkish.
- `uk` — Ukrainian.
- `vi` — Vietnamese.
- `zh_CN` — Simplified Chinese.
- `zh_TW` — Traditional Chinese.

partner\_order\_id
String, Query
required

The external order ID.

ℹ️

- The minimum length is `1` character.

## Errors

The `error` field has the value specified in the headers below.

### `failed_to_generate_document`

The voucher is in generating. Try to request later.

### `order_not_found`

The order with the `partner_order_id` field value isn’t found.

### `pending`

The voucher is in processing. Try to request later.

### `voucher_is_not_downloadable`

The order is in processing. Try to request when the order will be completed and its cost will be paid.
