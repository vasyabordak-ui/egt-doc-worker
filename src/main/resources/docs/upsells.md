---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/how-tos/process-fields/upsells/
tags:
- etg-docs
- how-tos
- process-fields
title: Upsells
---

# Upsells

ETG API V3

How-tos

Process fields

Upsells

# Upsells

#how-tos

ℹ️

This page describes more information about `upsells` and `upsell_data` fields and gives examples of filling them.

**Upsell parameters** and the `upsell_data` object are used to include information about additional services — such as early check-in or late check-out — in a booking request. Using these fields helps to tailor booking offers to specific user needs, ensuring both service accuracy and a better user experience.

Upsells can be specified in the requests in the Search by hotel IDs and Retrieve hotelpage calls.

## Parameters Description

- `early_checkin` indicates whether early check-in is required.
  - `time` — specifies the desired early check-in time.
- `late_checkout` indicates whether late check-out is needed.
  - `time` specifies the desired late check-out time.
- `only_eclc`when is set to `true`, only rates offering early check-in or late check-out will be considered.
- `upsell_data` provides detailed information about the requested upsell.
  - `attributes` upsell check-in and check-out dates as a string.
  - `checkin_datetime` the date and time of the upsell check-in.
  - `checkout_datetime` the date and time of the upsell check-out.
  - `name` the name of the upsell (e.g., `early_checkin`, `late_checkout`).
  - `rule_id` the upsell rule ID.
  - `uid` the unique upsell ID (use the value returned from the Create booking call.

## Usage Recommendations

- Use upsell parameters and the `upsell_data` object when the user requires additional services.
- If a specific time is needed for early check-in or late check-out, set it explicitly using the `time`, `checkin_datetime`, or `checkout_datetime` fields.
- Only one early check-in and one late check-out should be requested and selected.
- To filter search results to only those that support early check-in or late check-out, set `only_eclc` to `true`.
- Provide the unique upsell ID (`uid`) and rule ID if available from previous steps in the booking process.

## Examples

ℹ️

- Always use the `uid` value returned from the create booking process when referencing an upsell in a subsequent call.
- Dates and times should be in format:
  - `2006-01-02`.
  - `2006-01-02 15:04`.
  - `2006-01-02T15:04`.
  - `2006-01-02 15:04:05`.
  - `2006-01-02T15:04:05`.
  - `2006-01-02T15:04:05-07:00`.
  - `2006-01-02T15:04:05Z07:00`.

### Request With Early Check-in and Late Check-out

In this example, the user requests early check-in at 10:00 and late check-out at 16:00.

```
{
  "checkin_date": "2024-06-01",
  "checkout_date": "2024-06-05",
  "upsells": {
    "early_checkin": {
      "time": "10:00"
    },
    "late_checkout": {
      "time": "16:00"
    }
  }
}
```

### Request With Only Early or Late Check-in/Out Rates

Only rates that include either early check-in or late check-out will be returned.

```
{
  "checkin_date": "2024-06-01",
  "checkout_date": "2024-06-05",
  "upsells": {
    "only_eclc": true
  }
}
```

### Without Specific Time

User requests an early check-in, but without specifying the exact time.

```
{
  "checkin_date": "2024-06-01",
  "checkout_date": "2024-06-05",
  "upsells": {
    "early_checkin": {}
  }
}
```

### Combined Usage

Requests late check-out at 18:00 and wants to see only those rates that include either early check-in or late check-out.

```
{
  "checkin_date": "2024-06-01",
  "checkout_date": "2024-06-05",
  "upsells": {
    "late_checkout": {
      "time": "18:00"
    },
    "only_eclc": true
  }
}
```

### Adding Upsell Data to an Order

This order includes an upsell for early check-in, with check-in at 09:00, and all relevant identifiers for the chosen upsell option.

```
{
  "order_id": "ORD-123456",
  "upsell_data": {
    "attributes": "Check-in: 2024-07-05 09:00, Check-out: 2024-07-10 18:00",
    "checkin_datetime": "2024-07-05T09:00:00Z",
    "checkout_datetime": "2024-07-10T18:00:00Z",
    "name": "early_checkin",
    "rule_id": 47,
    "uid": "upsell-7890"
  }
}
```

### Multiple Upsell Data Entries

If you need to specify both early check-in and late check-out upsells in one request, you can provide multiple `upsell_data` objects:

```
{
  "order_id": "ORD-123457",
  "upsell_data": [
    {
      "name": "early_checkin",
      "checkin_datetime": "2024-07-05T08:00:00Z",
      "rule_id": 47,
      "uid": "upsell-1234"
    },
    {
      "name": "late_checkout",
      "checkout_datetime": "2024-07-10T19:00:00Z",
      "rule_id": 48,
      "uid": "upsell-5678"
    }
  ]
}
```

Adds both early check-in and late check-out upsells to the order, with times and corresponding IDs.
