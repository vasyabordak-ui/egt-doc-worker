---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/integration-guide/
tags:
- etg-docs
- integration-guide
title: Integration Guide
---

# Integration Guide

ETG API V3

Integration Guide

# Integration Guide

This document will guide you through implementing ETG API v3 and provide you with an action plan for upcoming months.

## Integration Stages Overview

The integration consists of 3 stages:

1. Before starting the integration, make sure you have an account in our system. If you’re new to ETG and don’t have an account yet, complete the Registration Stage on our website. If you’re already registered and have an API key, skip this step.

   ℹ️

   Access to the API and technical support is available only for registered partners.
2. **The Integration Stage** is where you develop the integration independently.
   Before you start building, we recommend:

- reviewing this page for a summary of the key integration steps,
- read the ETG Best Practices for API to ensure efficient implementation, and explore the ETG API documentation for detailed endpoint descriptions and parameters.

Please feel free to ask any questions about the API logic during this stage — we’re happy to help.

3. **The Certification Stage** is where API Launch team reviews your implementation to verify that the integration follows the required API workflow and complies with ETG integration requirements.

Before the certification review begins, you must provide the required certification information, including the Pre-certification Checklist and additional integration details requested by the ETG team.

For a detailed description of the process, see Certification and Production access section.

Below, you will find the recommended API workflow with endpoints to be integrated. Make sure to integrate all required endpoints.

## Recommended Workflow

Please find the recommended workflow diagram with mandatory and required endpoints listed below:

Recommended Flow (Prebook from Hotelpage)Alternative Flow (Prebook from Search)

sequenceDiagram
participant C as Client
participant YS as Your System
participant ETG as ETG API
Note over C, ETG: Search Step
C->>YS: Requests city search
YS->>YS: Convert city to ETG hotel ids, region or geo search
YS->>ETG: Search by Hotel IDs, Region or Geo request
ETG-->>YS: Search by Hotel IDs, Region or Geo response
YS-->>C: Show city search results
C->>YS: Choose the hotel and moves to hotel page
YS->>ETG: Retrieve hotelpage request
ETG-->>YS: Retrieve hotelpage response
YS-->>C: Show hotelpage with room prices
C->>YS: Choose the rate
Note over C, ETG: Prebook from hotelpage step
C->>YS: Start the Prebook
YS->>ETG: Prebook request with 'price\_increase\_percent'
alt AVAILABLE
ETG-->>YS: AVAILABLE
YS-->>C: Continue booking process by opening the booking form
alt AVAILABLE (Price changed)
YS-->>C: Show new price to client and ask - continue booking with new price (Y/N)?
C->>YS: Yes, continue with new price
end
Note over C, ETG: Booking Step
C->>YS: Open the booking form
YS->>ETG: Create booking process request
ETG-->>YS: Create booking process response
YS-->>C: Show the booking form
C->>YS: Fills all necessary data
C->>YS: Starts the booking process
YS->>ETG: Start booking process request
ETG-->>YS: Start booking process response
loop Every second, until the status is 'ok'
YS->>ETG: Check booking process request
ETG-->>YS: Check booking process response
end
YS-->>C: Show the success page
else NOT AVAILABLE
ETG-->>YS: NOT AVAILABLE (Error no\_available\_rates)
YS-->>C: Show that rate is not available and cannot be booked
end

sequenceDiagram
participant C as Client
participant YS as Your System
participant ETG as ETG API
Note over C, ETG: Search Step
C->>YS: Requests city search
YS->>YS: Convert city to ETG hotel ids, region or geo search
YS->>ETG: Search by Hotel IDs, Region or Geo request
ETG-->>YS: Search by Hotel IDs, Region or Geo response
YS-->>C: Show city search results
C->>YS: Choose the hotel and room with specific rate
Note over C, ETG: Prebook from search step
YS->>YS: Start the Prebook search step
YS->>ETG: Prebook request with 'price\_increase\_percent'
alt AVAILABLE
ETG-->>YS: AVAILABLE
YS-->>C: Continue booking process by opening the booking form
alt Price changed
YS-->>C: Show new price to client and ask - continue booking with new price (Y/N)?
C->>YS: Yes, continue with new price
end
Note over C, ETG: Booking Step
C->>YS: Open the booking form
YS->>ETG: Create booking process request
ETG-->>YS: Create booking process response
YS-->>C: Show the booking form
C->>YS: Fills all necessary data
C->>YS: Starts the booking process
YS->>ETG: Start booking process request
ETG-->>YS: Start booking process response
loop Every 3s, until the status is 'ok' or failure
YS->>ETG: Check booking process request
ETG-->>YS: Check booking process response
end
YS-->>C: Show the success or failed page
else NOT AVAILABLE
ETG-->>YS: NOT AVAILABLE (Error no\_available\_rates)
YS-->>C: Show that rate is not available and cannot be booked
end

Each API method has request and response samples, field descriptions and corresponding error codes.
You can find them in the documentation section corresponding to your API type: https://docs.emergingtravel.com/

## 1. Static Data Step

Hotel static data stands for all information about the hotels and includes full content like images and additional content data.

### 1.1 Retrieve hotel dump

**Required**

**Endpoint Sandbox:** https://api-sandbox.worldota.net/api/b2b/v3/hotel/info/dump/

**Endpoint Production:** https://api.worldota.net/api/b2b/v3/hotel/info/dump/

This tool should be used to download content and hotel mapping information. It is updated weekly.

⚠️

The dump download link you receive from the API is temporary and will expire **1 hour after being issued**. To download the dump, you must call the API method each time to obtain a fresh, valid link.

### 1.2 Retrieve hotel content

**Recommended**

**Endpoint Sandbox:** https://api-sandbox.worldota.net/api/b2b/v3/hotel/info/

**Endpoint Production:** https://api.worldota.net/api/b2b/v3/hotel/info/

It is used only in cases when an available hotel is not included in the downloaded hotel data dump. It can happen to new hotels in the ETG inventory.

### 1.3 Retrieve hotel incremental dump

**Recommended**

**Endpoint Sandbox:** https://api-sandbox.worldota.net/api/b2b/v3/hotel/info/incremental\_dump/

**Endpoint Production:** https://api.worldota.net/api/b2b/v3/hotel/info/incremental\_dump/

It is used to update the Retrieve hotel dump (B2B, Affiliate) static information with changes applied to hotels on the previous day. This is updated daily.

This method should be used daily to get the most up-to-date information. For example, if hotels make an important announcement, this method will provide updates.

⚠️

The dump download link you receive from the API is temporary and will expire **1 hour after being issued**. To download the dump, you must call the API method each time to obtain a fresh, valid link.

## 2. Search

### 2.1 **Search by** Methods

**One of the endpoints is required**

**Endpoint Sandbox:**

- https://api-sandbox.worldota.net/api/b2b/v3/search/serp/region/ - calling this endpoint will return a list of all available hotels within the specified region.
- https://api-sandbox.worldota.net/api/b2b/v3/search/serp/hotels/ - calling this endpoint will return a list of specific hotels (max 300 in one request; it is advisable to send 300 hotels in one SERP request).
- https://api-sandbox.worldota.net/api/b2b/v3/search/serp/geo/ - calling this endpoint will return a list of all hotels within a specified radius of a particular longitude and latitude.

**Endpoint Production:**

- https://api.worldota.net/api/b2b/v3/search/serp/region/
- https://api.worldota.net/api/b2b/v3/search/serp/hotels/
- https://api.worldota.net/api/b2b/v3/search/serp/geo/

**Search by** methods are used to search for hotels with available accommodations that match specified search conditions. However, allowing users to select rates at this stage is not advisable, as the rates may not align with those presented in the subsequent `single hotel search` on the **Retrieve hotelpage** (B2B, Affiliate) and/or **Prebook rate from hotelpage step** (B2B, Affiliate) request.

During this first search step using **Search by** methods, we recommend displaying one or two of the lowest rates for each hotel. In the second step, when using **Retrieve hotelpage** (B2B, Affiliate) and/or **Prebook rate from hotelpage step** (B2B, Affiliate) for a single hotel search, all available rates for the selected hotel should be displayed.

### 2.2 Retrieve hotelpage

**Required**

**Endpoint Sandbox:** https://api-sandbox.worldota.net/api/b2b/v3/search/hp/

**Endpoint Production:** https://api.worldota.net/api/b2b/v3/search/hp/

**Retrieve hotelpage** (B2B, Affiliate) should be used exclusively for hotels the end user has shown interest in. Requests should not be made for every hotel retrieved by region ID or hotel IDs; requests should only be made when the user selects a specific hotel.

The recommended storage duration for retrieved rates is up to 1 hour. The availability window for booking requests using this method may extend beyond this timeframe, varying based on internal factors.

This endpoint will provide detailed information on a particular hotel with all the prices for rates.

We do not recommend matching rates between one of the **Search by** methods and **Retrieve hotelpage** (B2B, Affiliate) responses. However, if your logic requires such matching, please contact our API support team — we will advise you on the recommended implementation approach.

## 3. Prebook

Prebook updates the availability of the requested rate. This method attempts to find a comparable or similar rate if the original rate is unavailable. Implementing the Prebook method is recommended. If method is not used:

- The risk of receiving a soldout error at the booking stage increases.
- The order confirmation process will take longer.

The `price_increase_percent` (B2B, Affiliate) feature allows ETG to identify the best alternative rate within the price increase range. If this feature is implemented, your clients should be notified of any price changes before proceeding to the booking step.

Please note that you can specify `price_increase_percent` and choose from 0 to 100. If that’s the case, you must notify the end user of the price change.

The nuances of integrating the Prebook has been elaborated in the Best Practices.

There are two Prebooks available, depending on the partners’ product workflow as it was highlighted in the visual diagrams above:

### 3.1 Prebook rate from hotelpage step

**Recommended**

**Endpoint Sandbox:** https://api-sandbox.worldota.net/api/b2b/v3/hotel/prebook/

**Endpoint Production:** https://api.worldota.net/api/b2b/v3/hotel/prebook

**Prebook rate from hotelpage step** (B2B, Affiliate) should be implemented after **Retrieve hotelpage** (B2B, Affiliate), and must be excluded from the booking flow.

### 3.2 Prebook rate from search step

**Recommended**

**Endpoint Sandbox:** https://api-sandbox.worldota.net/api/b2b/v3/serp/prebook/

**Endpoint Production:** https://api.worldota.net/api/b2b/v3/serp/prebook/

**Prebook rate from search step** (B2B, Affiliate) should be implemented if partner does not use **Retrieve hotelpage** (B2B, Affiliate), and wants to start booking on the search step. This call must be excluded from the booking flow.

If you need this call, please contact our API support team and include a workflow diagram plus the details below (use case, workflow description, expected traffic, and calling strategy). This helps us validate capacity and recommend the safest implementation approach.

## 4. Booking step

ℹ️

All endpoints are required.

### 4.1. Create booking process

**Required**

**Endpoint Sandbox:** https://api-sandbox.worldota.net/api/b2b/v3/hotel/order/booking/form/

**Endpoint Production:** https://api.worldota.net/api/b2b/v3/hotel/order/booking/form/

This endpoint is called to create a booking on the ETG end and link that order with the order in your system.

**Create Booking Process — Error Handling**

ℹ️

The number of retries should be limited to 10.

```
sequenceDiagram
    participant User
    participant API

   User->>API: Create booking process request
alt status == "ok"
    API-->>User: Response: "ok"
    User->>User: Go to Start booking process
    else error in (duplicate_reservation, double_booking_form, unknown, timeout) or HTTP status code 5xx
    loop Retry with new partner_order_id (until status == "ok" or other error)
        User->>API: Retry Create booking process with new partner_order_id
        API-->>User: Response
        alt status == "ok"
            User->>User: Go to Start booking process
          else error in retryable set
            User->>User: Continue retry
        else other error
            User->>User: Show error and stop
        end
    end
else other error
    API-->>User: Response error
    User->>User: Show error and stop
end
```

### 4.2. Start booking process

**Required**

**Endpoint Sandbox:** https://api-sandbox.worldota.net/api/b2b/v3/hotel/order/booking/finish/

**Endpoint Production:** https://api.worldota.net/api/b2b/v3/hotel/order/booking/finish/

This endpoint is called to start the booking process. During this step, we are sending the booking request to our suppliers and direct hotel partners to make a booking. Hence, the booking status will be `in progress`. Consequently, you are required to implement the last endpoint to get the final booking status.

The last endpoint is either the **Check booking process** (B2B, Affiliate) or **Receive booking status webhook** (B2B, Affiliate). Please note that you can implement them both, but **one of them is required**.

**Start Booking Process — Error Handling**

```
sequenceDiagram
    participant User
    participant API

    User->>API: Start booking process
    alt status == "ok" or error is timeout/unknown or HTTP status code 5xx
        API-->>User: Response: status == "ok" or error is timeout/unknown or HTTP status code 5xx
        User->>User: Proceed to Check Booking Process
    else
        API-->>User: Response: other error
        User->>User: Show error and stop
    end
```

### 4.3. Check booking process

**Recommended**

**Endpoint Sandbox:** https://api-sandbox.worldota.net/api/b2b/v3/hotel/order/booking/finish/status/

**Endpoint Production:** https://api.worldota.net/api/b2b/v3/hotel/order/booking/finish/status/

This endpoint is used to receive the final booking status. One should keep calling this endpoint until the status `processing` changes either to `ok` or final failure status.

Please note that the status `ok` during the **Check booking process** (B2B, Affiliate) **only** indicates a successful booking.

**Check Booking Process — Error Handling**

```
sequenceDiagram
    participant User
    participant API

    User->>API: Check booking process
    alt status == "ok"
        API-->>User: Booking is successfully confirmed
    else status == "processing" or error is (timeout/unknown) or HTTP status code 5xx
        loop Wait and poll (until status is "ok", final error, or booking timeout reached)
            User->>API: Check Booking Process
            alt status == "ok"
                API-->>User: Booking is successfully confirmed
                User->>User: Stop polling
            else status == "processing" or error is (timeout/unknown) or HTTP status code 5xx
                API-->>User: Still processing or transient error, continue polling
            else status is final error (e.g., soldout, provider, book_limit) or booking timeout reached
                API-->>User: Return final error or booking timeout
                User->>User: Stop polling, show error
            end
        end
    else status is final error (e.g., soldout, provider, book_limit) or booking timeout reached
        API-->>User: Return final error or booking timeout
        User->>User: Stop, show error
    end
```

### 4.4 Receive booking status webhook

If you decide to work with the **Receive booking status webhook** (B2B, Affiliate), ETG will send you the final booking status, and you will be only responsible for correctly responding to our webhooks.

If you would like to work with **Receive booking status webhook** (B2B, Affiliate), please provide us with a callback URL.

The detailed logic of how to work with webhooks can be found in Best Practices for API and API documentation.

The differences between “Check booking process” and “Receive booking status webhook” are:

| **“Check booking process”** | **“Receive booking status webhook”** |
| --- | --- |
| To obtain the final status, the partner must perform a series of requests in accordance with **Retry logic** (B2B, Affiliate).  The statuses will either indicate status `ok`, or include a list of final failure error codes. | ETG will send the final status right after receiving the booking status from the supplier.  These statuses will be either confirmed or failed. |
| It will provide the reason for the booking failure. | No specific reason for booking failure will be provided. |

Please also find our Best Practices for API for the integration, which will give more exact recommendations and outline integration expectations.

## 5. Post-Booking

### 5.1 Retrieve bookings

**Recommended**

**Endpoint Sandbox:** https://api-sandbox.worldota.net/api/b2b/v3/hotel/order/info/

**Endpoint Production:** https://api.worldota.net/api/b2b/v3/hotel/order/info/

This can be used to receive details about a successful booking. However, it should not be initiated immediately after booking completion.

⚠️

**Warning:**

It is important to note that:

- This endpoint should not be used to check the final statuses of the bookings. For this purpose, please use: https://api.worldota.net/api/b2b/v3/hotel/order/booking/finish/status/
- We do not change rate details after bookings are made. The details you book are final and will not be subject to unexpected changes.

### 5.2 Cancel booking

**Recommended**

**Endpoint Sandbox:** https://api-sandbox.worldota.net/api/b2b/v3/hotel/order/cancel/

**Endpoint Production:** https://api.worldota.net/api/b2b/v3/hotel/order/cancel/

This should be used if a booking needs to be canceled.
