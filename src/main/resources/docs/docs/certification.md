---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/certification/
tags:
- etg-docs
- certification
title: Certification and Production access
---

# Certification and Production access

ETG API V3

Certification and Production access

# Certification and Production access

Each product requires an independent certification. Certification of one product does not automatically apply to other products. Products may be certified sequentially or in parallel and can be launched independently once certified.

The certification process approximately takes **14 to 30 days** and is conducted in **written form** to allow partners to implement required changes asynchronously. The certification timeline depends on the current state of the integration, the number of review iterations, and whether identified issues require changes or are accepted by the partner with financial responsibility.

ℹ️

Partners must design and implement their integration in strict accordance with the Integration Guide and Best Practices before starting the certification process.

## Certification Flow

### Step 1. Execute Certification Test Cases

Executing certification test cases is a mandatory requirement.

Without completed test cases, ETG cannot verify correct integration behavior and finalize certification.

Test cases depend on the API key type used for certification and are described below.

#### Certification Test Cases — Sandbox API Keys

The following test cases are **mandatory for partners using Sandbox API keys**.

They validate correct booking logic, error handling, and edge-case processing. All scenarios are reproducible using the Sandbox environment. Detailed instructions for reproducing specific errors are provided in the Sandbox documentation.

| Test Scenario | Validation logic | Mandatory for |
| --- | --- | --- |
| Multiroom booking with mixed adults and children | • Confirm correct request creation for multibooking feature  • Confirm child age logic • Confirm final booking status | Partners supporting multiroom booking |
| Booking with children | Confirm child age logic | All partners |
| Booking with specifying the Uzbekistan guest’s citizenship | • Confirm correct request creation during search | All partners |
| Rate price increase at the prebook step | • Confirm `price_increase_percent` logic • Confirm `book_hash` logic in Prebook | Partners allowing price change during Prebook |
| Successful booking after unknown errors | • System continues polling `/booking/finish/status` until receiving status `ok` | All partners |
| Failed booking resulting in `soldout` error after `unknown` error | • System keeps treating booking as in progress and continues polling until `soldout`  • Booking is then failed | All partners |
| Failed booking resulting in `book_limit` error after `unknown` error | • System correctly identifies the `book_limit` error and fails the booking due to timeout expiry | All partners |

#### Certification Test Cases — Test API Keys

The following test cases are **mandatory for partners using Test API keys**.

They must be executed using a test hotel.

| Test case description | Test case purpose |
| --- | --- |
| Create a successful single-room booking: 2 adults + 1 child (5 y.o) **with citizenship Uzbekistan** | Validate correct handling of guest composition and residency logic |
| Create a successful multi-room booking (if supported): Room 1 — 2 adults + 1 child (5 y.o); Room 2 — 2 adults. | Validate multi-room booking support and correct room-level guest distribution |

If multi-room or bookings with children are not supported, partners must explicitly confirm this limitation.

### Step 2. Provide Access and Required Materials

Depending on the product type, the following information must be provided.

#### Website or Mobile App

Partners must provide access to test search and booking functionality where **ETG is activated as a Provider.**

One of the following options is required:

- Access to the website has been provided.
- Access to the website cannot be granted — a video recording or step-by-step screenshots covering the full flow (search → booking → cancellation) must be provided.
- The installation file of the mobile application is provided.

#### API

Applicable where partners are activated as a Provider for third-party integrations.

- API documentation used by partner’s clients.
- Logs for **completed test booking (described below in the step 3)**, including:
  - Partner API request and response.
  - ETG API request and response.
  - Preferably in JSON format.

### Step 3. Complete the Pre-Certification Checklist

Partners must complete the Pre-certification Checklist applicable to their integration type. Launch member will be sent to you upon request.

- The checklist is completed **by the partner**.
- Submission of the completed checklist is a **mandatory requirement** to start certification.
- The checklist format and content may differ depending on the integration type.
- To receive the relevant checklist, please contact our API support team.

### Step 4. Review and Feedback

ETG reviews the integration based on:

- Compliance with Best Practices.
- Provided access and materials.
- Pre-certification Checklist.
- Test case results.

If required, ETG may request clarifications or changes to ensure correct implementation, alignment with platform standards, and to minimize potential operational and financial risks. Communication is conducted **via email**.

### Step 5. Certification Approval

Certification is considered completed when:

- All mandatory checklist items are approved.
- No blocking issues remain, or remaining issues are accepted by the partner as known limitations with associated financial responsibility.
- ETG QA team confirms certification completion after final verification.

Once approved, the product may be launched independently, even if other products are still undergoing certification.

### Step 6. Production Access

Certification confirms that the integration is technically ready to work with real hotel inventory.

To receive a Production API key and access real bookings, partners must complete the API partnership formalisation process after certification.

## Support

If you have any questions regarding the certification process, please contact us.
