---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/how-tos/process-fields/sell_price_limits/
tags:
- etg-docs
- how-tos
- process-fields
title: Sell_price_limits
---

# Sell_price_limits

ETG API V3

How-tos

Process fields

Sell\_price\_limits

# Sell\_price\_limits

#how-tos

ℹ️

This page describes more information about `sell_price_limits` field and gives examples of filling it.

`sell_price_limits` is an object that contains restrictions on the final selling price for the client on open sales channels. Open channels refer to those intended for end customers (B2C, individuals).

```
{
  "sell_price_limits": {
    "min_price": "480.00",
    "max_price": "750.00"
  }
}
```

- If the field is present and populated, you **must not sell the selected rate at a price lower than** `min_price` or higher than `max_price`.
- The currency matches the search currency used for the rate request.
- If the field is missing or `null`, there are no restrictions on the selling price from our side.

Fields can be provided separately or together.

⚠️

If MSP (`sell_price_limits`) is not enabled for your API key, but it is active on the supplier’s side, the price will be automatically adjusted by RH. This may result in a non-competitive price and can lead to missed bookings.

We strongly recommend supporting MSP in your integration, especially if you have a B2C distribution model. Contact your account manager for activation details.

## Why is it needed?

Some hotel suppliers require the enforcement of minimum/maximum selling prices on open markets. We simply pass these restrictions on to you.

- Violation of the limits will lead to a violation incident and may affect our cooperation.
- If your contract does not allow working under such restrictions, we will not send you this field.

## How to get access

To receive data via the `sell_price_limits` field:

1. Contact your account manager.
2. Sign the additional agreement.
3. After access is configured, we will start sending this field in the search results.

## How to use the field

1. **Apply the restriction when calculating the final selling price for the client**.
   - For each rate, if there is a `min_price`, your selling price must not be lower than this value.
   - If there is a `max_price`, your price must not be higher than this value.
2. **Display and use the prices in the search currency**.
3. **Not receiving this field?**
   - We will not send this restriction, but if we receive such a price restriction from the supplier, we will adjust the rate’s selling price for your channel accordingly.

## Examples

**Example 1: Minimum limit only**

```
{
  "sell_price_limits": {
    "min_price": "480.00"
  }
}
```

Your client price must be **at least 480.00** (in the search currency).

**Example 2: Both minimum and maximum limits**

```
{
  "sell_price_limits": {
    "min_price": "480.00",
    "max_price": "750.00"
  }
}
```

The client price must be **between 480.00 and 750.00** (search currency).

**Example 3: No limit provided**

```
{
  "sell_price_limits": null
}
```

Follow the general rules; no pricing restriction applies.

## Field structure

| Field | Type | Description |
| --- | --- | --- |
| `min_price` | String | The minimum final selling price for the client in the search currency. |
| `max_price` | String | The maximum final selling price for the client in the search currency. |

## FAQ

**Q:** What if the hotel supplier sends a restriction, but I do not receive it?  
**A:** In this case, we will automatically adjust the price in your search response; the restriction itself is not sent to you.

**Q:** Can I sell below the minimum price if the field is not present and I have not signed the amendment?  
**A:** No, in this case, you will not see the field and will receive the already adjusted price.

**Q:** What happens if we don’t support MSP in our integration?  
**A:** If MSP is active on the supplier side and not on your API key, prices will be adjusted automatically by RH, which might make them less attractive for end users. To avoid this, please enable MSP in your integration.

## Responsibility

- **You must observe these conditions.** Violation incidents will negatively affect our business relationship.
- If your systems are unable to process this field, do not activate this option.

## Contacts and support

For connection questions or clarifications, please contact your account manager.
