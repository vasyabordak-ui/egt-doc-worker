---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/b2b-api/documents/retrieve-closing-documents/
tags:
- etg-docs
- b2b-api
- documents
title: Retrieve closing documents
---

# Retrieve closing documents

ETG API V3

B2B API

Documents

Retrieve closing documents

# Retrieve closing documents

#b2b

```
https://api.worldota.net/api/b2b/v3/general/document/closing_documents/download/
```

The call gets the confirmatory accounting document. The document is provided in PDF format.

## Request example

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/general/document/closing_documents/download/?data={%22package_id%22%3A55225%2C%22seal%22%3Atrue}'
```

## Parameters

Expand this|
Collapse this

package\_id
String, Query
required

The confirmatory accounting document ID.

seal
String, Query
required

Whether the seal is needed or not.

## Errors

The `error` field has the value specified in the headers below.

### `failed_to_generate_document`

The confirmatory accounting document is in generating. Try to request later.
