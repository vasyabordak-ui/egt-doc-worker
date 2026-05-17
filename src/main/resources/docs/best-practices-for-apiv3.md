---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/best-practices-for-apiv3/
tags:
- etg-docs
- best-practices-for-apiv3
title: Best Practices for API
---

# Best Practices for API

ETG API V3

Best Practices for API

# Best Practices for API

This document highlights the best practices and recommendations for integration. It aims to provide crucial information to consider when developing an integration and addresses frequently asked questions.

⚠️

Use only field values, IDs, API keys, and any static content provided for a specific environment (sandbox, testing, or production) within that environment. For example, use sandbox data only in the sandbox environment. Do not use sandbox data in test or production environments, and do not mix data or configuration between different environments.

## 1. General

### 1.1 Products and Certifications

Our API can be integrated with different products and methods for reselling rates. The main focuses are on:

- **Website or Mobile app**, where ETG rates are presented on a website or mobile application you developed.
- **API**, where ETG rates are being sold via your API, which your end-users integrate into their product.

Each product requires its independent certification, which can occur simultaneously or sequentially. More information is available in Certification and Production access in the Certification section.

### 1.2 Payment Types

Depending on the partnership model, ETG provides different `payment_type` (B2B, Affiliate).

*Affiliate*

`now` - ETG charges the card provided during the booking process. Please integrate Create credit card token for this payment type, as ETG charges the end-user or your corporate card.

The `now` payment method is currently unavailable in the Sandbox environment.

`hotel` - payment at the hotel: some of the rates will require credit card data, some not. Refer to the API response below to identify such rates:

```
"is_need_credit_card_data": true,
    "is_need_cvc": true,
    "type": "hotel",
```

Please integrate **Create credit card token** (B2B, Affiliate) for this payment type.

*For B2B API*

`deposit` - the payment comes from your deposit. The partner is responsible for charging the end-user.

### 1.3 Workflow

Please find the recommended workflow with endpoints in the Integration Guide. Also, please refer to ETG API documentation for request and response sample as well as error descriptions.

Note that we expect the diagram of your workflow during the Certification with mentioned ETG endpoints integrated to our workflow.

### 1.4 IPs Whitelist

**Justification: To ensure the security of system credentials and safeguard access, clients integrating the ETG APIv3 are required to provide their IP addresses for whitelisting**

Please provide the list of the IP addresses that must be whitelisted on our end. In addition, please specify if you need our IP addresses to be whitelisted on your end so we can provide our list.

### 1.5 Test environment

For testing and getting acquainted with our functionality, we provide two separate environments: sandbox and test. However, all actual bookings are processed exclusively in the production environment.

Please note that the test API key allows you to book rooms only in our demo hotel (‘hid’ = ‘8473727’ or ‘id’ = ’test\_hotel\_do\_not\_book’). It cannot be used for booking real hotels. If you try to book a real hotel with this key, you will encounter a `sandbox_restriction` error.

Please make bookings with refundable rates containing data such as `free_cancellation_before`: “2024-12-05T20:00:00”. Otherwise, you may receive an `insufficient_b2b_balance` error.

## 2. Hotel Static

### 2.1 Hotel Static Data upload and updates

We expect you to update the hotel static data as frequently as possible, preferably daily. To do this, you need to work with two endpoints: Retrieve hotel dump, Retrieve hotel incremental dump and Retrieve regions’ dump. You will need to download, store, and maintain this file on your end.

The download URL returned by these endpoints is **temporary** and is only valid for **1 hour** from the time it is issued. Each time you need to access or download the dump, you must call the relevant API method again to get a fresh, valid link. Hardcoded or previously saved URLs will stop working after expiration.

Please note that **Retrieve hotel dump** (B2B, Affiliate) is updated weekly. Please count seven days from the date indicated in the `last_update` parameter (B2B, Affiliate). If the `last_update` value is not updated, please call this endpoint one more time later. Keep repeating this logic until you get the updated file.

The **Retrieve hotel incremental dump** (B2B, Affiliate) is updated daily. Please count 24 hours from the date indicated in the `last_update` parameter (B2B, Affiliate). If the `last_update` value is not updated, please call this endpoint one more time later. Keep repeating this logic until you get the updated file.

ℹ️

If you miss updating the Retrieve hotel incremental dump for a day, please continue updating subsequent days using the Retrieve hotel incremental dump. To retrieve the missed data, you can use the Retrieve hotel dump during its next update cycle.

**Retrieve regions’ dump** (B2B, Affiliate). Provides data on the hotels in all regions available in the ETG API. Each archive is meant for a single language. Update weekly.

Please note that this file might be big; hence we offer how to decompress the file in Process dump in Golang and Python. These methods will allow you to parse data line by line for interested hotels.

We also offer region/destination and review dumps. More information is available in **Retrieve hotel reviews’ dump** (B2B, Affiliate) and **Retrieve regions’ dump** (B2B, Affiliate).

### 2.2 Mapping logic and mapping updates

We expect you to map our hotels, IDs, and regions with the hotels and regions in your system.

Mapping is the process of matching each supplier’s hotel content to the right hotel location and choosing which content to display to the end user.

Mapping exists because each supplier’s information (pictures, descriptions, contact information, etc.) on a given hotel is slightly different from information provided by other suppliers. Each supplier took their own pictures of the hotel, its rooms, and its amenities, and each supplier wrote their own description of the hotel. They can even have different addresses — one supplier may have spelled out `Street` or `Avenue` while another supplier may have abbreviated those words. Mapping is merging these differences and picking the best representation of the hotel data, pictures, and amenities to show the end customer.

We expect the mapping to occur as frequently as possible. During certification, we expect you to elaborate on mapping logic. We recommend using `hid` from static data as the hotel identifier instead of `id`.

### 2.3 Hotel important information

In hotel static data Retrieve hotel dump (B2B, Affiliate), Retrieve hotel incremental dump (B2B, Affiliate), Retrieve hotel content (B2B, Affiliate), one can find parameters:

- `metapolicy_struct` (B2B, Affiliate) — additional accommodation conditions.
- `metapolicy_extra_info` (B2B, Affiliate) — additional hotel information.

ℹ️

If you don’t have a similar structure and want to present information in a clear, user-friendly format, follow our implementation guidance and practical examples, see Metapolicy\_struct.

We expect the information in these parameters to be parsed and displayed to the end-user since it contains essential data related to hotel rules, additional fees, or any other important information. For example, some hotels expect guests to provide their passport only and not their other IDs.

### 2.4 Room Static data

To use room static data like images and amenities, you must use `rg_ext` (B2B, Affiliate) fields. The `rg_ext` is a group of parameters that are unique room identifiers in the hotel; it can be found in the search and hotel static responses.

Room images and descriptions can be obtained only by matching all `rg_ext` fields from the search with `rg_ext` from the hotel static.

## 3. Search Step

This section is related to the following methods: **Search by region** (B2B, Affiliate), **Search by geo coordinates** (B2B, Affiliate), **Search by hotel IDs** (B2B, Affiliate), **Retrieve hotelpage** (B2B, Affiliate), and **Prebook rate from hotelpage step** (B2B, Affiliate), **Prebook rate from search step** (B2B, Affiliate).

### 3.1 Prebook

**Prebook** confirms availability of a specific rate. If rate is no longer available, propose a substitute within defined `price_increase_percent`.

The `price_increase_percent` parameter can be from 0% to 100%. If you work with `price_increase_percent`, then it is mandatory to display that the price has changed to the end-user.

⚠️

The Prebook method recommended timeout is 60 seconds, with a minimum of 30 seconds (which may lead to a decrease in availability).

Please note that the Prebook method does not support incoming search timeouts. The timeout can only be adjusted on the ETG side.

Search logic depends on the partner’s product. There are two types of search logic possible:

**Recommended Flow (Prebook from Hotelpage):**

One of the **Search by** methods - **Retrieve hotelpage** (B2B, Affiliate) - **Prebook rate from hotelpage step** (B2B, Affiliate).

Prebook rate from hotelpage step should be implemented after Retrieve hotelpage (B2B, Affiliate), and must be excluded from the booking flow.

The logic of working with book hashes during the Prebook rate from hotelpage step is the following:

1. You get the book hash that starts with “h-…” during **Retrieve hotelpage**.
2. Use that book hash that starts with “h-…” in the **Prebook rate from hotelpage step** request. In return, the **Prebook rate from hotelpage step** will give you a book hash that starts with “p-….”.
3. That book hash that starts from “p-….” should be used during **Create booking process** (B2B, Affiliate).

**Alternative Flow (Prebook from Search)**

One of the **Search by** methods - **Prebook rate from search step** (B2B, Affiliate), where due to the absence of the hotel page, the search\_hash from the SERP calls is required to proceed with the booking when specified as the hash for the Prebook rate from search call.

Prebook rate from search step should be implemented after one of SERP calls and must be excluded from the booking flow.

The logic of working with book hashes during the Prebook rate from search step is the following:

1. You get the search hash that starts with “sr-…” during one of SERP calls.
2. Use that search hash that starts with “sr-…” in the Prebook rate from search step request. In return, the Prebook rate from search step will give you a book hash that starts with “p-….”.
3. That book hash that starts from “p-….” should be used during Create booking process (B2B, Affiliate).

If you need this call, please contact us.

### 3.2 Cache

The caching recommendations depend on your product’s logic. We usually don’t recommend caching and encourage working with real, live data. The longer a rate is stored in the cache, the higher the possibility that it may no longer be available later.

If you provide approximate prices during the first search step (which refers to the SERP, using any of the three available methods) and then update the prices during the second search step (Hotelpage), then it doesn’t matter how long you cache the first step results.

If you match **Search by** methods and **Retrieve hotelpage** (B2B, Affiliate) by `match_hash` then we don’t recommend caching at all.

Caching the responses of **Retrieve hotelpage** (B2B, Affiliate) and **Prebook rate from hotelpage step** (B2B, Affiliate) is prohibited.

### 3.3 Children Logic

The child’s age should be specified in the **Search by** and **Retrieve hotelpage** (B2B, Affiliate) requests in brackets [15].

Moreover, our system supports stays of up to 30 nights, with up to 6 adults and 4 children in one room. Individuals aged 17 years and below are considered children. The check-in date must be less than or equal to 730 days from today.

### 3.4 Multiroom booking

ETG supports up to 9 rooms at one rate, but **does not support different room types at one rate**. To be able to book multiple rooms, please specify it in the **Search by** and **Retrieve hotelpage** (B2B, Affiliate) requests in the `guests` parameter.

Please find the example for 2 rooms, where 1 room has 2 guests, and 1 room has 3 guests (two adults and one 7-years old child).

```
"guests": [
  {
    "adults": 2,
    "children": []
  },
  {
    "adults": 2,
    "children": [7]
  },
],
```

### 3.5 Taxes and fees

Taxes and fees must be excluded from the final price. ETG API sends both included and non-included taxes. Non-included taxes must be displayed to the end-user separately. To find non-included taxes, please refer to `tax_data.taxes` parameter and search for taxes with **`included_by_supplier`: false** data. For example,

```
"id": "test_hotel_do_not_book",
"hid": 8473727,
"rates": [
  {
    "match_hash": "m-5ab93916-be48-5bc0-953f-69eba410b3af",
    "search_hash": null,
    "daily_prices": [
          "0.80"
    ],
    "meal": "nomeal",
    "meal_data": {
      "value": "nomeal",
      "has_breakfast": false,
      "no_child_meal": true
    },
    "payment_options": {
      "payment_types": [
        {
          "amount": "75.20",
          "show_amount": "0.80",
          "currency_code": "EUR",
          "show_currency_code": "EUR",
          "by": null,
          "is_need_credit_card_data": false,
          "is_need_cvc": false,
          "type": "deposit",
          "vat_data": {
            "included": false,
            "applied": false,
            "amount": "0.00",
            "currency_code": "USD",
            "value": "0.00"
          },
          "tax_data": {
            "taxes": [
              {
                "name": "city_tax",
                "included_by_supplier": false, // - this tax shouldn’t be added to the total price and should be excluded and shown separately
                "amount": "1130.10",
                "currency_code": "HNL"
              },
              {
                "name": "electricity_fee",
                "included_by_supplier": true, // - this price is included in the price sent in “amount”, “show_amount”, “commission_info.charge.amount_net”, and “commision_info.charge.amount_gross”
                "amount": "0.02",
                "currency_code": "EUR"
              },
              {
                "name": "service_fee",
                "included_by_supplier": false, // - this tax shouldn’t be added to the total price and should be excluded and shown separately
                "amount": "3.70",
                "currency_code": "HNL"
              },
              {
                "name": "vat",
                "included_by_supplier": false, // - this tax shouldn’t be added to the total price and should be excluded and shown separately
                "amount": "679.74",
                "currency_code": "HNL"
              }
```

If your local legislation requires the final price to include taxes, reach out to our API support team for guidance on how to present taxes to end users and calculate the final price correctly.

### 3.6 Search timeouts

Our system can work with dynamic timeouts during searches (**Search by** methods and **Retrieve hotelpage** (B2B, Affiliate). To do so, please send a `timeout` parameter with an integer value.

The logic of the dynamic timeout is below:

- If you send us the desired `timeout` in the API requests, our system will follow that timeout and provide responses within the requested timeout.
- If you don’t send us the desired `timeout` in the API requests, then our system will follow the timeout configured on your Production Key.

The recommended timeout for **Search by** methods and **Retrieve hotelpage** is 30s. We are open to discussing search timeouts during certification.

### 3.7 Cancellation policies

Please parse cancellation policies from **Search by** methods and **Retrieve hotelpage** (B2B, Affiliate) from the parameter `cancellation_penalties.policies`. The cancellation policies might have three layers:

```
"start_at": null,
"end_at": "2025-10-21T08:59:00" // from the moment of booking until this time, the booking is refundable without penalty 
    "amount_charge": "0.00",
    "amount_show": "0.00",
    "commission_info": {
      "show": {
        "amount_gross": "0.00",
        "amount_net": "0.00",
        "amount_commission": "0.00"
      },
      "charge": {
        "amount_gross": "0.00",
        "amount_net": "0.00",
        "amount_commission": "0.00"
      }
    }
  },
  {
"start_at": "2025-10-21T08:59:00" // - within this timeframe, the cancellation is possible with a partial penalty 
"end_at": "2025-10-21T08:59:00",
    "amount_charge": "116.80",
    "amount_show": "1.60",
    "commission_info": {
      "show": {
        "amount_gross": "2.00",
        "amount_net": "1.60",
        "amount_commission": "0.40"
      },
      "charge": {
        "amount_gross": "146.00",
        "amount_net": "116.80",
        "amount_commission": "29.20"
      }
    }
},
{
"start_at": "2025-10-21T08:59:00" // - from this moment, the cancellation is possible with a full penalty
"end_at": null,
  "amount_charge": "241.60",
  "amount_show": "2.40",
  "commission_info": {
    "show": {
      "amount_gross": "3.00",
      "amount_net": "2.40",
      "amount_commission": "0.60"
    },
    "charge": {
      "amount_gross": "302.00",
      "amount_net": "241.60",
      "amount_commission": "60.40"
    }
  }
}
],
"free_cancellation_before": "2025-10-21T08:59:00"
},
```

**`free_cancellation_before`: “2025-10-21T08:59:00”** - means that end-users are allowed to cancel the booking without penalties before 2025-10-21T08:59:00. After that, full or partial cancellation penalties will apply.
The rate with **free\_cancellation\_before": null** - means that there is no free cancellation. The penalties will apply immediately when the user decides to cancel.

It is important to note that we send the cancellation policies via API in the UTC+0.

### 3.8 Citizenship/residency

Justification: If the passport country is not sent in the API requests, and the hotel in a specific country has citizenship based price adjustments, then the customer will have to pay additionally at the front desk, which ultimately will result in bad customer experience or even a dispute.

We expect the `residency` parameter to be sent in the **Search by** and **Retrieve hotelpage** (B2B, Affiliate) requests. Please note that by residency, we mean passport country.

We require this information because prices may change for specific regions based on this residency parameter.

The rates in the search response are provided according to the citizenship of the first guest provided in the search request. Citizenship should be the same for each guest in every search request. Provide the real guests’ citizenship in the `residency` field.

### 3.9 Meal types

We expect our partners to match our meal types with those in their systems. Please don’t make the meal type better than the one we’ve sent. If you don’t find the corresponding meal type on your end, please create one and worsen the meal type in your system.

Please use `meal_data.value` to display meals specific to rates.

To get the full list of meals, please call the endpoint:
**Retrieve hotel static data** (B2B, Affiliate)

All possible options will be listed under the `meals` parameter.

### 3.10 Final price / Commission

ETG v3 API works with both net and gross prices. Please parse the corresponding parameter depending on your type of prices.

**Net Prices**

ℹ️

Applicable only to B2B-API.

Please work with `amount` or `show_amount`. The difference between `amount` and `show_amount` is that **`amount`** stands for the price of your selected currency for our reconciliation, meanwhile **`show_amount`** is the currency requested in **Search by** (B2B, Affiliate) or **Retrieve hotelpage** (B2B, Affiliate) request.

Please note that if you work with net prices, then you are responsible for calculating your commission or markup.

**Gross Prices**

ℹ️

Applicable only to Affiliate-API.

Please work with `amount` or `show_amount`. The difference between `amount` and `show_amount` is that **`amount`** stands for the price of your selected currency for our reconciliation, meanwhile **`show_amount`** is the currency requested in the **Search by** (B2B, Affiliate) or **Retrieve hotelpage** (B2B, Affiliate) request.

Please note that if you work with gross prices, then ETG is responsible for calculating the commission, and our API sends prices with your commission included.

**Fake Gross Prices**

ℹ️

Applicable only to B2B-API.

With this commission model you display to the end-user commission\_info.charge.amount\_gross or commission\_info.show.amount\_gross but pay to ETG the price specified in `commission_info.charge.amount_net` in the currency specified in your contract.

Please note that if you work with fake gross prices, the ETG is responsible for calculating the agreed commission. Still ETG will charge a net price from you.

Please do not add excluded taxes to the final price; they should be displayed separately. If you need to change your commission model, please contact your Account Manager or Sales Manager.

### 3.11 Room name reflection

Please be informed to parse the `room_name` parameter and display it to your client.

### 3.12 Early check-in / Late check-out

The `Early check-in` / `Late check-out` is currently unavailable in the Sandbox environment.

ETG v3 API supports early check-in and late check-out. Guests can ask for early check-in and late check-out options, which allow checking in before the usual time and leaving after the regular check-out time, depending on agreement and availability, and often with an extra fee.

ℹ️

When working with upsells, ensure clarity for the end-user and avoid overwhelming them with options, follow our implementation guidance and practical examples, see  Upsells.

### 3.13 Hotel chunk size

Our system supports up to 300 hotels in 1 request for **Search by hotel IDs** (B2B, Affiliate). The recommended amount of hotels in a chunk depends on the **Search by hotel IDs** timeout.

## 4 Card Payments

The `Card Payments` is currently unavailable in the Sandbox environment.

This information is applicable to affiliate contracts that are using card payments, where ETG charges end-users on partner’s behalf. To work with card payments, we expect you to provide the `return_path` URL.

### 4.1 Endpoints Logic

*Create credit card token*

Since ETG will be charging the end-user, we expect you to send us the credit card data using the **Create credit card token** (B2B) endpoint. The endpoint request sample body can be found in our **Create credit card token** (Affiliate).

We expect you to create `pay_uuid` and `init_uuid` independently.

*Start booking process*

Please ensure to include `pay_uuid`, `init_uuid` and `return_path` in your request for **Start booking process** (B2B, Affiliate). The sample request should look like this:

```
    "user": {
        "email": "[email protected]",
        "comment": "comment",
        "phone": "12312321"
    },
    "partner": {
        "partner_order_id": "asd123",
        "comment": "partner_comment",
        "amount_sell_b2b2c": "10"
    },
    "language": "en",
    "rooms": [
        {
            "guests": [
                {
                    "first_name": "Marty",
                    "last_name": "Quatro"
                },
                {
                    "first_name": "Marta",
                    "last_name": "Quatro"
                }
            ]
        }
    ],
    "upsell_data": [
        {
            "name": "early_checkin",
            "uid": "d7b56e81-b874-40ee-b195-e2f73d1ec714"
        },
        {
            "name": "late_checkout",
            "uid": "c4013ea8-3ffd-4eee-bbbc-37693670031e"
        }
    ],
    "payment_type": {
        "type": "now",
        "amount": "8",
        "currency_code": "EUR",
        "pay_uuid": "797870e3-e1f0-470a-87b3-38694f58bed1",
        "init_uuid": "c44ef1ba-595b-437f-ad14-74ce39a0f9ad"
    },
    "return_path": "https//api.hotel.com/return_path/v1/hotel-booking/3ds"
}
```

### 4.2 How to work with 3ds

We might send the address for completing 3ds authentication with GET or POST method during **Check booking process** (B2B, Affiliate). Please refer to the endpoint page for examples.

Please find the logic of working with them in our ETG API documentation.

Please note that you can encounter `error`: `3ds` and `status`: `3ds`. The difference is:

- `Error`: `3ds` is the error indicating that the authorization has failed
- Status `3ds` is the status indicating that the end-user needs to proceed with 3ds authentication

Please note that the validation process is mandatory. We will check how you work with card payment logic to ensure everything is correct.

### 4.3 Requirements on cards used for testing purposes

1. Once you reach the **Start booking process** (B2B, Affiliate), kindly provide the actual first name and last name. Kindly do not place any unsupported symbols, special characters, or numbers within the name field.
2. The guest’s email and phone number should be real (otherwise, our anti-fraud system may block such a booking attempt as suspicious). Do not provide any test email addresses such as ([email protected] or [email protected]).
3. When using Affiliate API, please do not use your corporate email (e.g., @corporateemail.com) (because our system recognizes it as a b2b account email, and this may block the booking).
4. Please don’t use different emails in combination with different bank cards. For example, if you used a specific card with a certain email for your initial booking, ensure that subsequent bookings are made using the same email and card combination.
5. Please use a real bank card with a sufficient balance, and ensure to enter the card details without errors.

## 5. Booking Step

### 5.1 Receiving the final booking status

You can use the **Check booking process** (B2B, Affiliate) endpoint AND / OR **Receive booking status webhook** (B2B, Affiliate) to get the final booking status. One of these options is **mandatory** for the implementation.

Order can be shown as confirmed only after receiving `ok` status from the **Check booking process** response or `confirmed` from the **Receive booking status webhook**.

If you use the **Check booking process** (B2B, Affiliate) call, follow the recommendations **Retry logic** (B2B, Affiliate).

If you have any limitations for this step, contact the API support team.

### 5.2 Booking cut-off

The booking cut-off (also known as booking timeout) is a specific timeframe within which ETG must complete the booking. If the booking is not completed by the end of the cut-off period, the order will be automatically marked as failed.

This booking cut-off is configured manually after negotiating with the partner during certification. Please note that the ETG v3 API doesn’t support dynamic booking timeouts.

### 5.3 Errors and Statuses Processing Logic

Each booking endpoint has a unique logic of processing statuses and errors as it impacts the final booking status. The each logic for both front-end and backend is provided and elaborated in the table below. Please note that the logic for each endpoint should be integrated strictly as it’s presented.

**Endpoint:** https://api.worldota.net/api/b2b/v3/hotel/order/booking/form/

| **Status or Error** | **Logic on FE** | **Logic on BE** |
| --- | --- | --- |
| Status `ok` | In Progress or waiting for status | Proceed to call the `/order/booking/finish/` (B2B, Affiliate) endpoint |
| 5xx status code | In Progress or waiting for status | Proceed to call the `/order/booking/form/` (B2B, Affiliate) endpoint until you receive status `ok`. Allowed to make up to 10 repeated requests. If within these 10 requests you haven’t received the status `ok`, restart the search process. |
| Error `timeout` | In Progress or waiting for status | Proceed to call the `/order/booking/form/` endpoint until you receive status `ok`. Allowed to make up to 10 repeated requests. If within these 10 requests you haven’t received the status `ok`, restart the search process. |
| Error `unknown` | In Progress or waiting for status | Proceed to call the `/order/booking/form/` endpoint until you receive status `ok`. Allowed to make up to 10 repeated requests. If within these 10 requests you haven’t received the status `ok`, restart the search process. |
| Error `contract_mismatch` | Failed | Stop the booking process and show the user that the booking has failed. Refer to the documentation to learn what to do in this case. |
| Error `double_booking_form` | In Progress or waiting for status | Change the `partner_order_id` and retry the call again. |
| Error `duplicate_reservation` | In Progress or waiting for status | Change the `partner_order_id` and retry the call again. |
| Error `hotel_not_found` | Failed | Stop the booking process and show the user that the booking has failed. Refer to the documentation to learn what to do in this case. |
| Error `insufficient_b2b_balance` | Failed | Stop the booking process and show the user that the booking has failed. Refer to the documentation to learn what to do in this case. |
| Error `reservation_is_not_allowed` | Failed | Stop the booking process and show the user that the booking has failed. Refer to the documentation to learn what to do in this case. |
| Error `rate_not_found` | Failed | Stop the booking process and show the user that the booking has failed. Refer to the documentation to learn what to do in this case. |
| Error `sandbox_restriction` | Failed | Stop the booking process and show the user that the booking has failed. Refer to the documentation to learn what to do in this case. |

**Endpoint:** https://api.worldota.net/api/b2b/v3/hotel/order/booking/finish/

| **Status or Error** | **Logic on FE** | **Logic on BE** |
| --- | --- | --- |
| Status `ok` | In Progress or waiting for status | Proceed to call the “/order/booking/finish/status/” (B2B, Affiliate) endpoint. |
| 5xx status code | In Progress or waiting for status | Proceed to call the “/order/booking/finish/status/” endpoint. |
| Error `timeout` | In Progress or waiting for status | Proceed to call the “/order/booking/finish/status/” endpoint. |
| Error `unknown` | In Progress or waiting for status | Proceed to call the “/order/booking/finish/status/” endpoint. |
| Error `booking_form_expired` | Failed | Stop the booking process and show the user that the booking has failed. |
| Error `rate_not_found` | Failed | Stop the booking process and show the user that the booking has failed. |
| Error `return_path_required` | Failed | Stop the booking process and show the user that the booking has failed. |

**Endpoint:** https://api.worldota.net/api/b2b/v3/hotel/order/booking/finish/status/

| **Status or Error** | **Logic on FE** | **Logic on BE** |
| --- | --- | --- |
| Status `ok` | Success | Stop calling the `/order/booking/finish/status/` (B2B, Affiliate) and show the user that the booking is successful. |
| Status `processing` | In Progress or waiting for status | Continue calling the `/order/booking/finish/status/` at regular intervals within the booking timeout. |
| Error `timeout` | In Progress or waiting for status | Continue calling the `/order/booking/finish/status/` at regular intervals within the booking timeout. |
| Error `unknown` | In Progress or waiting for status | Continue calling the `/order/booking/finish/status/` at regular intervals within the booking timeout. |
| 5xx status code | In Progress or waiting for status | Continue calling the `/order/booking/finish/status/` at regular intervals within the booking timeout. |
| Error `block` | Failed | Stop calling the `/order/booking/finish/status/` and show the user that the booking has failed. |
| Error `charge` | Failed | Stop calling the `/order/booking/finish/status/` and show the user that the booking has failed. |
| Error `3ds` | Failed | Stop calling the `/order/booking/finish/status/` and show the user that the booking has failed. |
| Error `soldout` | Failed | Stop calling the `/order/booking/finish/status/` and show the user that the booking has failed. |
| Error `provider` | Failed | Stop calling the `/order/booking/finish/status/` and show the user that the booking has failed. |
| Error `book_limit` | Failed | Stop calling the `/order/booking/finish/status/` and show the user that the booking has failed. |
| Error `not_allowed` | Failed | Stop calling the `/order/booking/finish/status/` and show the user that the booking has failed. |
| Error `booking_finish_did_not_succeed` | Failed | Stop calling the `/order/booking/finish/status/` and show the user that the booking has failed. |

### 5.4 Known errors

*Only applicable to the **Check booking process***

In **Check booking process** (B2B, Affiliate), if you encounter 3ds, block, book\_limit, booking\_finish\_did\_not\_succeed, provider, or soldout errors, please consider the booking failed. These are the final failure errors.

### 5.5 Webhooks

*Only applicable to **Receive booking status webhook***

If you choose to integrate the **Receive booking status webhook** (B2B, Affiliate) alone, please integrate the **Create booking process** and **Start booking process** methods. Handle our callbacks properly and respond with a 200 OK status when you receive them.

Errors such as **timeout, unknown, and the 5хх status** code do not indicate the booking failure. The logic of working with these errors is explained below:

| **Create booking process** (B2B, Affiliate) | **Start booking process** (B2B, Affiliate) |
| --- | --- |
| If you get errors such as **timeout, unknown, or the 5хх status code**, you should make another request with a new partner\_order\_id. The maximum number of calls is limited to 10. | If you get errors such as **timeout, unknown, or other 5хх status code**, you should wait for the webhook notification within the predefined timeout period to confirm the booking status.   1. The failure booking status will come as `failed` in webhook notification.   2. If no webhook notification is received within this time, the booking should be treated as unsuccessful. |

In case of doubt, please check the order status in Ratehawk Backoffice.

### 5.6 Booking statuses on the partner/platform side

Here is the list of ETG statuses: some are present during the booking step, and some are applicable during the post-booking step.

Please map the booking flow statuses according to the table below:

| **Booking Status** | **Corresponding status or error Check booking process** | **Corresponding status or error in webhooks** |
| --- | --- | --- |
| Success (required) | Status `Ok` | Confirmed |
| Failed (required) | Errors 3ds, block, book\_limit, booking\_finish\_did\_not\_succeed, charge, decoding\_json, endpoint\_exceeded\_limit, endpoint\_not\_active, endpoint\_not\_found, incorrect\_credentials, invalid\_auth\_header, invalid\_params, lock, no\_auth\_header, not\_allowed, not\_allowed\_host, order\_not\_found, overdue\_debt, provider, soldout, unexpected\_method | Failed |
| In progress (required) | `Processing`, `timeout`, `unknown`, and `5xx` status code | Not applicable as webhook informs confirmed and failed statuses only. |
| Cancelled (optional) | Not present during the booking flow, can be seen in Order Information endpoint | Not present during the booking flow, can be seen in Order Information endpoint |
| No-show (optional) | Not present during the booking flow | Not present during the booking flow |

### 5.7 Confirmation e-mails

When calling **Start booking process** (B2B, Affiliate), please follow the below logic of emails:

**For B2B API**

```
      "user": {
      "email": "[email protected]", //- we expect to receive one fixed corporate email for B2B API with your domain
      "comment": "comment",
      "phone": "12312321" 
  },
```

**For Affiliate**

```
    "user": {
    "email": "[email protected]" // - we expect to receive the end-user email here 
    "comment": "comment",
    "phone": "12312321"
},
```

## 6. Post Booking Step

### 6.1 Retrieve bookings

The **Retrieve bookings** (B2B, Affiliate) endpoint shouldn’t be used for checking the booking final status and should be excluded from the booking flow.

If you are using the **Retrieve bookings** endpoint, please wait some time after receiving the successful response from **Check booking process** (B2B, Affiliate) or **Receive booking status webhook** (B2B, Affiliate) before calling **Retrieve bookings**. The order data might take some time to synchronize and appear in this endpoint API response.

Please be informed that we don’t change the rate details during the booking process, so there is no need to check that and reconfirm if the details have changed.

### 6.2 Cancel booking

If you received the `timeout` error when canceling the order, please call the **Cancel booking** (B2B, Affiliate) endpoint one more time.
