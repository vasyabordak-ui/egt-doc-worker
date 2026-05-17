---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/fundamentals/requests/
tags:
- etg-docs
- fundamentals
- requests
title: Requests
---

# Requests

ETG API V3

Fundamentals

Requests

# Requests

#fundamentals

You should send requests via the HTTPS protocol. There are requests with:

- GET method—for obtaining data.
- POST method—for creating, updating, and deleting.

The request body and the `data` field are in the JSON format.

The GET example:

```
curl -g --user '<KEY_ID>:<KEY>' 'https://api.worldota.net/api/b2b/v3/hotel/info/?data={"id":"city_hotel_berlin_east","language":"en"}'
```

The POST example:

```
curl --user '<KEY_ID>:<KEY>' --data '{"id":"city_hotel_berlin_east","language":"en"}' "https://api.worldota.net/api/b2b/v3/hotel/info/"
```

ℹ️

- To view your request limit, use the **Retrieve endpoints** call:
  - B2B.
  - Affiliate.
- Increasing limits is possible for a live key after separate approval, depending on the expected sales volume.
- Limits are increased only after certification is completed.
