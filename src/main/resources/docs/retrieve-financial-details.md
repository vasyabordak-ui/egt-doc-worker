---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/b2b-api/contracts/retrieve-financial-details/
tags:
- etg-docs
- b2b-api
- contracts
title: Retrieve financial details
---

# Retrieve financial details

ETG API V3

B2B API

Contracts

Retrieve financial details

# Retrieve financial details

#b2b

```
https://api.worldota.net/api/b2b/v3/general/financial/info/
```

The call gets the contract financial details.

## Request example

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/general/financial/info/'
```

## Response

Expand this|
Collapse this

contract
Object

The contract general financial information.

contract\_overpay
Float

The amount of money that could be used for bookings.

Consists of:

- Orders refundable money.
- Balances from previous transfers.
- And money deposited in advance on the contract.

credit\_limit
Float

The contract credit limit.

deposit
Float

The contract deposit amount.

max\_booking\_price
String

The limit for new bookings.

overdue\_debt
Float

The contract overdue debt.

reporting\_currency
String

The contract reporting currency in the ISO 4217 format.

ℹ️

- The possible values:
  - `AED`.
  - `AUD`.
  - `BGN`.
  - `BRL`.
  - `BYN`.
  - `CAD`.
  - `CHF`.
  - `CNY`.
  - `DKK`.
  - `EUR`.
  - `GBP`.
  - `HKD`.
  - `IDR`.
  - `KZT`.
  - `MYR`.
  - `NOK`.
  - `PLN`.
  - `QAR`.
  - `RON`.
  - `RUB`.
  - `SEK`.
  - `SGD`.
  - `THB`.
  - `TRY`.
  - `USD`.
  - `VND`.
  - `ZAR`.

unpaid\_non\_ref\_orders\_sum
Float

The unpaid non-refundable bookings’ amount.

unpaid\_orders\_sum
Float

The amount of:

- All bookings that are not paid including overdue payment.
- Or the cost of its cancellation, if the booking is canceled.

unpaid\_ref\_orders\_sum
Float

The unpaid refundable bookings’ amount.

terminated\_at
Object

The partner contract list.

agreement\_number
String

The contract agreement number.

overdue\_debt
Float

The contract data overdue debt.

overpay
Float

The amount of money that could be used for bookings.

Consists of:

- Orders refundable money.
- Balances from previous transfers.
- And money deposited in advance on the contract.

unpaid\_non\_ref\_orders\_sum
Float

The unpaid non-refundable bookings’ amount.

unpaid\_orders\_sum
Float

The amount of:

- All bookings that are not paid including overdue payment.
- Or the cost of its cancellation, if the booking is canceled.

unpaid\_ref\_orders\_sum
Float

The unpaid refundable bookings’ amount.

## Response example

```
{
  "data": {
    "contract": {
      "contract_overpay": "3000.98",
      "credit_limit": "1451.00",
      "deposit": "1000.00",
      "max_booking_price": "0",
      "overdue_debt": "7762.48",
      "reporting_currency": "EUR",
      "unpaid_non_ref_orders_sum": "9454.93",
      "unpaid_orders_sum": "9507.93",
      "unpaid_ref_orders_sum": "53.00"
    },
    "contract_datas": [
      {
        "agreement_number": "B2B-12980/2",
        "overdue_debt": "0.00",
        "overpay": "0",
        "unpaid_non_ref_orders_sum": "0.00",
        "unpaid_orders_sum": "0.00",
        "unpaid_ref_orders_sum": "0.00"
      },
      {
        "agreement_number": "B2B-12980",
        "overdue_debt": "187.12",
        "overpay": "130.31",
        "unpaid_non_ref_orders_sum": "187.12",
        "unpaid_orders_sum": "204.12",
        "unpaid_ref_orders_sum": "17.00"
      },
      {
        "agreement_number": "B2B-12980/1",
        "overdue_debt": "7574.36",
        "overpay": "2870.67",
        "unpaid_non_ref_orders_sum": "9258.01",
        "unpaid_orders_sum": "9258.01",
        "unpaid_ref_orders_sum": "0.00"
      },
      {
        "agreement_number": "B2B-12980/3",
        "overdue_debt": "1.00",
        "overpay": "0",
        "unpaid_non_ref_orders_sum": "1.00",
        "unpaid_orders_sum": "1.00",
        "unpaid_ref_orders_sum": "0.00"
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
