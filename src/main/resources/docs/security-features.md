---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/fundamentals/security-features/
tags:
- etg-docs
- fundamentals
- security-features
title: Security features
---

# Security features

ETG API V3

Fundamentals

Security features

# Security features

#fundamentals

## URLs

The ETG API requires you to provide the URLs from your server side.

### General

The host URL from your account settings.

### Card payments

The return path URL form the `return_path` field in the Start booking process call (B2B, Affiliate).

This ensures the safety of card data transfer during the booking process:

1. You send a booking request to ETG API.
2. The ETG API extracts the domain with the HTTPS scheme from the `return_path` field.
3. The ETG API extracts the host (without the HTTPS scheme).
4. The ETG API checks compatibility of the host and domain:
   1. If they match, the ETG API redirects the user to the provided URL.
   2. If they don’t match:
      1. The ETG API marks the booking attempt as failed.
      2. The ETG API may redirect the user to the ETG site.
