---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/b2b-api/documents/retrieve-closing-documents-info/
tags:
- etg-docs
- b2b-api
- documents
title: Retrieve closing documents info
---

# Retrieve closing documents info

ETG API V3

B2B API

Documents

Retrieve closing documents info

# Retrieve closing documents info

#b2b

```
https://api.worldota.net/api/b2b/v3/general/document/closing_documents/info/
```

The call gets the confirmatory accounting documents details.

Use the call to:

- Check the details on confirmatory accounting documents.
- And get a `package_id` field value for the Retrieve closing documents call.

## Request example

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/general/document/closing_documents/info/?data={%22order_ids%22%3A55225%2C%22seal%22%3Atrue}'
```

## Parameters

ℹ️

The following fields are **required**:

- The `agreement_numbers` and `issue_date`.
- Or the `order_ids`.

Expand this|
Collapse this

agreement\_numbers
String, Query
optional

The contract agreement numbers.

issue\_date
String, Query
optional

The confirmatory accounting documents’ issue date.

order\_ids
String, Query
optional

The order ID.

## Response

Expand this|
Collapse this

packages
Object

The confirmatory accounting document list.

agreement\_number
String

The contract agreement number.

order\_ids
[Int]

The order ID.

package\_id
Int

The confirmatory accounting document ID.

package\_issue\_date
String

The confirmatory accounting document issue date.

package\_number
String

The confirmatory accounting document number.

reporting\_month
Int

The confirmatory accounting document reporting month.

reporting\_year
Int

The confirmatory accounting document reporting year.

total\_commission
Float

The agent remuneration during the reporting month.

total\_sum
Float

The bookings total amount during the reporting month.

total\_vat
Float

The bookings total VAT during the reporting month.

## Response example

```
{
  "data": {
    "packages": [
      {
        "agreement_number": "B2B-1234",
        "order_ids": [
          314159265,
          100666001
        ],
        "package_id": 55225,
        "package_issue_date": "2018-09-30",
        "package_number": "АТBP-083391",
        "reporting_month": 9,
        "reporting_year": 2018,
        "total_commission": "300.00",
        "total_sum": "314660.00",
        "total_vat": "0.00"
      }
    ]
  },
  "debug": null,
  "error": null,
  "status": "ok"
}
```

## Errors

The `error` field has the value specified in the headers below.

### `invalid_params`

The call is requested with the incorrect order of parameters. The correct order is:

- The `agreement_numbers` and `issue_date`.
- Or the `order_ids`.

### `invalid_params_issue_date_agreement_numbers`

The `agreement_numbers` and `issue_date` fields should be requested together.

### `orders_not_found`

The order with the `order_id` field value isn’t found.

### `agreements_not_found`

The documents with the `agreement_numbers` field value aren’t found.
