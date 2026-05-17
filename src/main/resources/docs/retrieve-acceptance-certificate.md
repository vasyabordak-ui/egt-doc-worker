---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/affiliate-api/documents/retrieve-acceptance-certificate/
tags:
- etg-docs
- affiliate-api
- documents
title: Retrieve acceptance certificate
---

# Retrieve acceptance certificate

ETG API V3

Affiliate API

Documents

Retrieve acceptance certificate

# Retrieve acceptance certificate

#affiliate

```
https://api.worldota.net/api/b2b/v3/general/document/acceptance_certificate/download/
```

The call gets the acceptance certificate file of the month. The file is provided in PDF format.

## Request example

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/general/document/acceptance_certificate/download/?data={%22date%22%3A%222020-03-31%22}'
```

## Parameters

Expand this|
Collapse this

date
String, Query
required

The month of the acceptance certificate for generating.

## Errors

The `error` field has the value specified in the headers below.

### `failed_to_generate_document`

The acceptance certificate is in generating. Try to request later.

### `invalid_report_date`

The `date` field value is invalid. The valid date is in the past or today.

### `not_available`

No acceptance certificate is available for your contract. Contact your account manager to create a certificate.
