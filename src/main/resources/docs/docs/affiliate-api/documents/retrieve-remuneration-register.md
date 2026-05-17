---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/affiliate-api/documents/retrieve-remuneration-register/
tags:
- etg-docs
- affiliate-api
- documents
title: Retrieve remuneration register
---

# Retrieve remuneration register

ETG API V3

Affiliate API

Documents

Retrieve remuneration register

# Retrieve remuneration register

#affiliate

```
https://api.worldota.net/api/b2b/v3/general/document/remuneration_register/download/
```

The call gets the remuneration register file of the month. The file is provided in PDF format.

## Request example

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/general/document/remuneration_register/download/?data={%22date%22%3A%222020-03-31%22}'
```

## Parameters

Expand this|
Collapse this

date
String, Query
required

The month of the remuneration register for generating.

## Errors

The `error` field has the value specified in the headers below.

### `failed_to_generate_document`

The remuneration register is in generating. Try to request later.

### `invalid_report_date`

The `date` field value is invalid. The valid date is in the past or today.

### `not_available`

No remuneration register is available for your contract. Contact your account manager to create a register.
