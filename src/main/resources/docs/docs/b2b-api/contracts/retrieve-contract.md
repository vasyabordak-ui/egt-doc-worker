---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/b2b-api/contracts/retrieve-contract/
tags:
- etg-docs
- b2b-api
- contracts
title: Retrieve contract
---

# Retrieve contract

ETG API V3

B2B API

Contracts

Retrieve contract

# Retrieve contract

#b2b

```
https://api.worldota.net/api/b2b/v3/general/contract/data/info/
```

The call gets contracts information.

## Request example

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/general/contract/data/info/'
```

## Response

Expand this|
Collapse this

contract\_datas
Object

The partner contract list.

active\_from
String

The contract commencement date.

agreement\_date
String

The contract agreement date.

agreement\_number
String

The contract agreement number.

closing\_documents\_issuance\_type
String

The contract confirmatory accounting documents issuance type.

ℹ️

- The possible values:
  - `half_monthly`.
  - `monthly`.
  - `single_order`.
  - `weekly`.
  - `decadly`.

kind
String

The contract kind.

ℹ️

- The possible values:
  - `informational-services`.
  - `agency`.
  - `services`.
  - `corp`.
  - `individual-contractor`.

legal\_entity
Object

The contract legal entity information.

address\_actual
String

The actual address.

address\_legal
String

The legal (registered) address.

name
String

The legal entity name.

taxpayer\_id
String

The taxpayer identification number.

terminated\_at
String

The contract termination date.

## Response example

```
{
  "data": {
    "contract_datas": [
      {
        "active_from": "2018-07-02",
        "agreement_date": "2018-06-29",
        "agreement_number": "AFF-12980",
        "closing_documents_issuance_type": "monthly",
        "kind": "informational-services",
        "legal_entity": {
          "address_actual": "123 N Columbia Blvd",
          "address_legal": "113 N Columbia Blvd",
          "name": "123onetwothree",
          "taxpayer_id": "1231231231"
        },
        "terminated_at": null
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

### `unauthorized`

The authorization error. Ensure your API key and credentials are correct.

### `unknown`

The internal ETG services’ timeout.
