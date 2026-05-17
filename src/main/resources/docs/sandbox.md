---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/fundamentals/sandbox/
tags:
- etg-docs
- fundamentals
- sandbox
title: Sandbox
---

# Sandbox

ETG API V3

Fundamentals

Sandbox

# Sandbox

## What is Sandbox?

The Sandbox is a test environment where you can make API calls using your regular credentials and sample properties, without affecting real accommodations, bookings, or financial transactions.

With the Demand API sandbox, you can:

- Safely test API calls for accommodation search, availability, reservations, and cancellations.
- Explore various test accommodation scenarios without impacting live production data.
- Before using the sandbox environment and test properties, make sure you meet all the requirements for accessing our endpoints.

## Sandbox limitations

- Use only API keys and field values obtained in the sandbox environment; do not mix with production or other environments.
- Not all API features and methods are supported; availability depends on your sandbox API key.
- Many request and response fields have fixed or limited values (for example, language is always `en`, and currency is typically `USD` or `EUR`).
- The number of results per request is limited (commonly up to `5` or `1000` items, depending on the method).
- Some fields and error scenarios are not supported or always return default/test values.
- Payment-related scenarios (such as 3D Secure and credit card flows) are not available in the sandbox.
- The sandbox is intended for safe testing only; no real bookings, charges, or data changes occur.
- For detailed information on method-specific limitations, refer to the documentation for each API endpoint.

## Sandbox properties

| Feature/Case | HID | ID |
| --- | --- | --- |
| Hotels with `metapolicy_struct` and `metapolicy_extra_info` | 6362880 6682380 | pullman\_dubai\_jumeirah\_lakes\_towers\_\_hotel\_and\_residence hotel\_monsieur |
| Hotels with `tax_data` | 10595223 10654204 | key\_view\_the\_residences staycae\_upper\_crest\_downtown\_view |
| Hotel with `vat_data` | 10678836 | lux\_the\_pad\_executive\_suite\_burj\_khalifa\_view\_4 |
| Hotel with 10% prebook price increase | 8819557 | rosa\_bell\_motel\_los\_angeles |
| Hotel with 20% prebook price increase | 9744270 | aparthotel\_adagio\_paris\_montmartre\_by\_pierre\_vacances |
| Hotel with all available meals | 10047711 | downtown\_la\_vacation\_apartments\_by\_stay\_city\_rentals\_3 |
| Hotel with no availability for HN residency | 8142632 | apartamenty\_sadovoe\_koltso\_paveletskaia |
| Hotel with double price for HN residency | 6471709 | sadovoye\_koltso\_apartment\_1905\_goda |
| Hotels with match\_hash difference between SERP and HP | 8608790 10724071 | coeur\_de\_paris\_\_pompidou silkhaus\_private\_beach\_unique\_1bdr\_in\_emaar\_beachfront |
| Hotel with taxes and VAT, all meal types, static room groups and metapolicy info | 10004834 | conrad\_los\_angeles |

### Test hotels

In addition to the hotels listed in the “Feature/Case” table, the sandbox contains over 2,000 additional hotels. These hotels do not have any special properties and are intended for partners who need to perform tests involving large data sets (for example, availability or search requests). Booking can also be performed with these hotels. The hotel IDs for these properties are not listed here, as they have standard, non-specific characteristics.

ℹ️

Test hotel data should be accessed exclusively by making appropriate API requests to the sandbox environment. Direct downloads or references to internal services or URLs are not supported and should not be used.

### Test cases

| Test Scenario | How to reproduce |
| --- | --- |
| Multiroom booking with mixed adults and children | Create a successful multi-room booking with the following criteria: • Hotel with hid 10004834 • 2 rooms • In 1 room: 2 adults and a child (3 y.o.) • In 2nd room: 2 adults and 3 children (1 y.o., 5 y.o., 17 y.o.) |
| Booking with children | Create a successful booking with the following criteria: • Hotel with hid 10004834 • 1 room • 2 adults and 2 children (0 and 17 y.o.) |
| Booking with specifying the Uzbekistan guest’s citizenship | Create a successful booking with the following criteria: • Hotel with hid 10004834 • Citizenship (residency): Uzbekistan • 1 room • 2 adults |
| Rate price increase at the prebook step | Create a successful booking and trigger a rate price increase: • Hotel with hid 8819557 • 1 room • 10% Prebook increase |
| Successful booking after unknown errors | Create a successful booking with `unknown` error in the `/booking/finish` and `/booking/finish/status` endpoint in any hotel  In the booking request use `partner_order_id` which ends `unknown_success` |
| Failed booking resulting in `soldout` error after `unknown` error | Create a failed booking with `unknown` error in `/booking/finish` and the `/booking/finish/status` endpoints resulting in `soldout` error in any hotel  In the booking request use `partner_order_id` which ends `unknown_soldout` |
| Failed booking resulting in `book_limit` error after `unknown` error | Create a failed booking with `unknown` error in both `/booking/finish` and `/booking/finish/status` endpoints, resulting in `book_limit` error in any hotel  In the booking request use `partner_order_id` which ends `unknown_book_limit` |

## Errors reproducing

Use the following test accommodation IDs in your requests to reproduce error scenarios with the endpoints. Each property is a mock hotel or apartment created exclusively for testing and simulating various error responses.

### Dump endpoints

| Error code | How to reproduce |
| --- | --- |
| `dump_not_ready` | Request dump with any valid language except `en` |

---

### Retrieve hotel content

| Error code | How to reproduce |
| --- | --- |
| `hotel_not_found` | Request nonexistent hotel slug |

---

### Search methods (SERP/HP)

| Error code | How to reproduce |
| --- | --- |
| 5xx, unknown, etc. | N/A (unable to reproduce in sandbox) |
| validation errors | Handled like in production (send invalid data in according field) |

---

### Prebook (HP/SERP)

| Error code | How to reproduce |
| --- | --- |
| 5xx, unknown, etc. | N/A (unable to reproduce in sandbox) |
| `invalid_params` | Handled like in production (send invalid data in according field) |
| `prebook_disabled` / `prebook_from_serp_disabled` | Contract’s setting |
| `rate_not_found` | Request nonexistent rate hash |
| `contract_mismatch` | Request with hash `h-00000000-0000-0000-0000-000000000000` |
| `no_available_rates` | N/A (unable to reproduce in sandbox) |

---

### Create booking process

| Error code | How to reproduce |
| --- | --- |
| `contract_mismatch` | Request with hash `h-00000000-0000-0000-0000-000000000000` |
| `double_booking_form` | Request multiple times with same `partner_order_id` |
| `insufficient_b2b_balance` | Unable to reproduce |
| `duplicate_reservation` | Request with previously used for booking `partner_order_id` (unable to reproduce both in sandbox and prod, requires manual orderlog deletion to prevent `double_booking_form` error) |
| hotel\_not\_found | Use rate from Hotelpage with `book_hash` starting with `e0000001` (e.g. `h-e0000001-8beb-43c4-b2d9-7e698efac087`) |
| `reservation_is_not_allowed` | Setting on API key |
| `rate_not_found` | Request with random hash in valid format |
| `sandbox_restriction` | Setting on API key |

---

### Start booking process

| Error code | How to reproduce |
| --- | --- |
| `book_hash_not_found` | N/A (unable to reproduce in sandbox) |
| `booking_form_expired` | Use `partner_order_id` field ending with `booking_form_expired` text on Booking Form stage |
| `chosen_payment_type_was_not_available_on_booking_form` | Send payment type which was not present in Booking Form response |
| `credit_card_required` | N/A (cc payments not supported yet) |
| `double_booking_finish` | Call Booking Finish method twice for same `partner_order_id` |
| `email` | Use `partner_order_id` field ending with `email` text on Booking Form stage |
| `incorrect_chosen_payment_type` | Send invalid `currency_code` (not applicable to hotel payment type) |
| `incorrect_guests_number` | Send invalid guests number |
| `incorrect_children_data` | Send invalid children data |
| `incorrect_rooms_number` | Send invalid rooms number |
| `insufficient_b2b_balance` | Use `partner_order_id` field ending with `insufficient_b2b_balance` text on Booking Form stage |
| `order_not_found` | Send non-existing `partner_order_id` |
| `rate_not_found` | N/A (unable to reproduce in sandbox) |
| `return_path_required` | N/A (cc payments not supported yet) |
| `unauthorized_group_booking` | Send invalid rooms number (>9 rooms) |
| `arrival_date_differs_from_checkin_date` | Send invalid `arrival_date` |
| `not_enough_credit_card_data` | N/A (cc payments not supported yet) |
| `incorrect_init_uuid_format` | N/A (cc payments not supported yet) |
| `incorrect_pay_uuid_format` | N/A (cc payments not supported yet) |
| `sandbox_restriction` | Try booking non-test hotel with sandbox (`is_production=false`) key |
| `supplier_data_required` | Send empty object `supplier_data` field |
| `unknown` | Any `unknown_*` case from Booking Finish Status will return `unknown` on Booking Finish request |

---

### Check booking process

| Error code | How to reproduce |
| --- | --- |
| `block` | N/A (cc payments not supported yet) |
| `charge` | N/A (cc payments not supported yet) |
| `3ds` | N/A (cc payments not supported yet) |
| `not_allowed` | N/A (cc payments not supported yet) |
| `soldout` | Use `partner_order_id` field ending with `soldout` text on Booking Form stage |
| `book_limit` | Use `partner_order_id` field ending with `book_limit` text on Booking Form stage |
| `provider` | Use `partner_order_id` field ending with `provider` text on Booking Form stage |
| `order_not_found` | Request nonexistent `partner_order_id` |
| `booking_finish_did_not_succeed` | Request after calling Booking Finish with error result |

### `Unknown`

Use `partner_order_id` ending with following strings to reproduce different `unknown` error behaviors

| Error code | How to reproduce |
| --- | --- |
| `unknown_success` | Status request returns `unknown` error, after some time it returns `ok` as final status |
| `unknown_provider` | Status request returns `unknown` error, after some time it returns `provider` error as final status |
| `unknown_soldout` | Status request returns `unknown` error, after some time it returns `soldout` error as final status |
| `unknown_book_limit` | Status request returns `unknown` error, after some time it returns `book_limit` error as final status |

---

### Cancel booking

| Error code | How to reproduce |
| --- | --- |
| `order_not_found` | Request nonexistent `partner_order_id` |
| `order_not_cancellable` | Use `partner_order_id` ending with `order_not_cancellable` text on Booking Form stage |
