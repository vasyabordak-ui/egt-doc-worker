---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/affiliate-api/static-content/retrieve-hotel-static-data/
tags:
- etg-docs
- affiliate-api
- static-content
title: Retrieve hotel static data
---

# Retrieve hotel static data

ETG API V3

Affiliate API

Static content

Retrieve hotel static data

# Retrieve hotel static data

#affiliate

Sandbox Production

```
https://api-sandbox.worldota.net/api/b2b/v3/hotel/static/
```

```
https://api.worldota.net/api/b2b/v3/hotel/static/
```

The call gets descriptions and translations for the static parameters of the hotels and rooms. Renew the dump every month.

## Sandbox limitations

No changes.

## Response

Expand this|
Collapse this

beddings
[Object]

The list of room beddings.

locale
Object

The translation item.

ar
String

The Arabic translation.

bg
String

The Bulgarian translation.

cs
String

The Czech translation.

de
String

The German translation.

el
String

The Greek translation.

en
String

The English translation.

es
String

The Spanish translation.

fr
String

The French translation.

he
String

The Hebrew translation.

hu
String

The Hungarian translation.

it
String

The Italian translation.

ja
String

The Japanese translation.

kk
String

The Kazakh translation.

ko
String

The Korean translation.

nl
String

The Dutch translation.

pl
String

The Polish translation.

pt
String

The Portuguese translation.

pt\_PT
String

The Portuguese (Portugal) translation.

ro
String

The Romanian translation.

ru
String

The Russian translation.

sq
String

The Albanian translation.

sr
String

The Serbian translation.

th
String

The Thai translation.

tr
String

The Turkish translation.

uk
String

The Ukrainian translation.

vi
String

The Vietnamese translation.

zh\_CN
String

The Simplified Chinese translation.

name
String

The internal item name.

meals
[Object]

The list of the hotel meal types.

locale
Object

The translation item.

ar
String

The Arabic translation.

bg
String

The Bulgarian translation.

cs
String

The Czech translation.

de
String

The German translation.

el
String

The Greek translation.

en
String

The English translation.

es
String

The Spanish translation.

fr
String

The French translation.

he
String

The Hebrew translation.

hu
String

The Hungarian translation.

it
String

The Italian translation.

ja
String

The Japanese translation.

kk
String

The Kazakh translation.

ko
String

The Korean translation.

nl
String

The Dutch translation.

pl
String

The Polish translation.

pt
String

The Portuguese translation.

pt\_PT
String

The Portuguese (Portugal) translation.

ro
String

The Romanian translation.

ru
String

The Russian translation.

sq
String

The Albanian translation.

sr
String

The Serbian translation.

th
String

The Thai translation.

tr
String

The Turkish translation.

uk
String

The Ukrainian translation.

vi
String

The Vietnamese translation.

zh\_CN
String

The Simplified Chinese translation.

name
String

The internal item name.

room\_amenities
[Object]

The list of room amenities.

locale
Object

The translation item.

ar
String

The Arabic translation.

bg
String

The Bulgarian translation.

cs
String

The Czech translation.

de
String

The German translation.

el
String

The Greek translation.

en
String

The English translation.

es
String

The Spanish translation.

fr
String

The French translation.

he
String

The Hebrew translation.

hu
String

The Hungarian translation.

it
String

The Italian translation.

ja
String

The Japanese translation.

kk
String

The Kazakh translation.

ko
String

The Korean translation.

nl
String

The Dutch translation.

pl
String

The Polish translation.

pt
String

The Portuguese translation.

pt\_PT
String

The Portuguese (Portugal) translation.

ro
String

The Romanian translation.

ru
String

The Russian translation.

sq
String

The Albanian translation.

sr
String

The Serbian translation.

th
String

The Thai translation.

tr
String

The Turkish translation.

uk
String

The Ukrainian translation.

vi
String

The Vietnamese translation.

zh\_CN
String

The Simplified Chinese translation.

name
String

The internal item name.

serp\_filters
[Object]

The list of hotel amenities.

locale
Object

The translation item.

ar
String

The Arabic translation.

bg
String

The Bulgarian translation.

cs
String

The Czech translation.

de
String

The German translation.

el
String

The Greek translation.

en
String

The English translation.

es
String

The Spanish translation.

fr
String

The French translation.

he
String

The Hebrew translation.

hu
String

The Hungarian translation.

it
String

The Italian translation.

ja
String

The Japanese translation.

kk
String

The Kazakh translation.

ko
String

The Korean translation.

nl
String

The Dutch translation.

pl
String

The Polish translation.

pt
String

The Portuguese translation.

pt\_PT
String

The Portuguese (Portugal) translation.

ro
String

The Romanian translation.

ru
String

The Russian translation.

sq
String

The Albanian translation.

sr
String

The Serbian translation.

th
String

The Thai translation.

tr
String

The Turkish translation.

uk
String

The Ukrainian translation.

vi
String

The Vietnamese translation.

zh\_CN
String

The Simplified Chinese translation.

name
String

The internal item name.

socket\_types
[Object]

The reference list of all supported socket types.

ℹ️

Use this field to map the socket type codes to their human-readable names and translations.

locale
Object

The translation item.

ar
String

The Arabic translation.

bg
String

The Bulgarian translation.

cs
String

The Czech translation.

de
String

The German translation.

el
String

The Greek translation.

en
String

The English translation.

es
String

The Spanish translation.

fr
String

The French translation.

he
String

The Hebrew translation.

hu
String

The Hungarian translation.

it
String

The Italian translation.

ja
String

The Japanese translation.

kk
String

The Kazakh translation.

ko
String

The Korean translation.

nl
String

The Dutch translation.

pl
String

The Polish translation.

pt
String

The Portuguese translation.

pt\_PT
String

The Portuguese (Portugal) translation.

ro
String

The Romanian translation.

ru
String

The Russian translation.

sq
String

The Albanian translation.

sr
String

The Serbian translation.

th
String

The Thai translation.

tr
String

The Turkish translation.

uk
String

The Ukrainian translation.

vi
String

The Vietnamese translation.

zh\_CN
String

The Simplified Chinese translation.

name
String

The internal item name.

taxes
[Object]

The list of rate taxes.

locale
Object

The translation item.

ar
String

The Arabic translation.

bg
String

The Bulgarian translation.

cs
String

The Czech translation.

de
String

The German translation.

el
String

The Greek translation.

en
String

The English translation.

es
String

The Spanish translation.

fr
String

The French translation.

he
String

The Hebrew translation.

hu
String

The Hungarian translation.

it
String

The Italian translation.

ja
String

The Japanese translation.

kk
String

The Kazakh translation.

ko
String

The Korean translation.

nl
String

The Dutch translation.

pl
String

The Polish translation.

pt
String

The Portuguese translation.

pt\_PT
String

The Portuguese (Portugal) translation.

ro
String

The Romanian translation.

ru
String

The Russian translation.

sq
String

The Albanian translation.

sr
String

The Serbian translation.

th
String

The Thai translation.

tr
String

The Turkish translation.

uk
String

The Ukrainian translation.

vi
String

The Vietnamese translation.

zh\_CN
String

The Simplified Chinese translation.

name
String

The internal item name.

amenity\_translations
[Object]

Amenity names translated into different languages for various types of accommodation (e.g., apartments, hotels, etc.).

slug
String

Amenity identifier used as a key for integration.

kind
String

Type of accommodation for which this amenity is relevant (e.g., APARTMENT).

name
[Object]

List of translations of the amenity name in different languages.

lang
String

Language code according to ISO 639-1.

value
String

Translation of the amenity name in the specified language.

amenities
[Object]

A list with detailed translations of descriptive amenity information for various languages. Provides more specific information in addition to `amenity_translations`.

name
[Object]

List of translations of the amenity name in different languages.

lang
String

Language code according to ISO 639-1.

value
String

Translation of the detailed amenity description in the specified language.

## Response example

```
{
  "data": {
    "beddings": [
      {
        "locale": {
          "ar": "سريرين مفرّدين",
          "bg": "2 единични легла",
          "cs": "2 samostatné postele"
        },
        "name": "twin"
      },
      {
        "locale": {
          "ar": "سرير بطابقين",
          "bg": "Двуетажно легло",
          "cs": "Patrová postel"
        },
        "name": "sofa-bed"
      }
    ],
    "meals": [
      {
        "locale": {
          "ar": "العشاء",
          "bg": "Вечеря",
          "cs": "Večeře"
        },
        "name": "dinner"
      },
      {
        "locale": {
          "ar": "وجبات الإفطار والغداء والعشاء مشمولة",
          "bg": "С включени закуска, обяд и вечеря",
          "cs": "Snídaně, oběd a večeře v ceně"
        },
        "name": "breakfast"
      }
    ],
    "room_amenities": [
      {
        "locale": {
          "ar": "سرير الأطفال مشمول",
          "bg": "С включено детско легло",
          "cs": "Dětská postel je zahrnuta v ceně"
        },
        "name": "child-bed"
      },
      {
        "locale": {
          "ar": "سرير الأطفال غير مشمول",
          "bg": "Не е включено детско легло",
          "cs": "Dětská postel není zahrnuta v ceně"
        }
      }
    ],
    "taxes": [
      {
        "locale": {
          "ar": "ضريبة القيمة المضافة",
          "bg": "ДДС",
          "cs": "DPH"
        },
        "name": "vat"
      },
      {
        "locale": {
          "ar": "رسوم المنتجع",
          "bg": "Курортна такса",
          "cs": "Rekreační poplatek"
        }
      }
    ],
    "serp_filters": [
      {
        "locale": {
          "ar": "الانتقال بمصعد التزلج",
          "bg": "Трансфер до ски лифта",
          "cs": "Transfer k lyžařskému vleku"
        },
        "name": "has_ski_shuttle"
      },
      {
        "locale": {
          "ar": "مطبخ",
          "bg": "Кухня",
          "cs": "Kuchyň"
        }
      }
    ],
    "socket_types": [
      {
        "locale": {
          "ar": "مقبس أمريكي (غير مؤرض)",
          "bg": "американски контакт (заземен)",
          "cs": "Americká zásuvka (bez uzemnění)"
        },
        "name": "a"
      },
      {
        "locale": {
          "ar": "مقبس أمريكي (مؤرض)",
          "bg": "американски контакт (заземен)",
          "cs": "Americká zásuvka (s uzemněním)"
        }
      }
    ],
    "amenity_translations": [
      {
        "slug": "has_parking",
        "kind": "APARTMENT",
        "name": [
          {
            "lang": "tr",
            "value": "Parking"
          },
          {
            "lang": "fi",
            "value": "Parking"
          },
          {
            "lang": "fr",
            "value": "Parking"
          }
        ]
      },
      {
        "slug": "has_disabled_support",
        "kind": "APARTMENT",
        "name": [
          {
            "lang": "kk",
            "value": "Инклюзивтілік"
          },
          {
            "lang": "uk",
            "value": "Інклюзивність"
          },
          {
            "lang": "sq",
            "value": "Inklusivitet"
          }
        ]
      }
    ],
    "amenities": [
      {
        "name": [
          {
            "lang": "he",
            "value": "חניה מחוץ לאתר בחינם"
          },
          {
            "lang": "hu",
            "value": "Ingyenes parkolás a helyszínen kívül"
          },
          {
            "lang": "kk",
            "value": "Қонақ үйден тыс тегін тұрақ"
          }
        ]
      }
    ]
  },
  "debug": {
    "api_endpoint": {
      "endpoint": "api/b2b/v3/hotel/static",
      "is_active": true,
      "is_limited": true,
      "remaining": 98,
      "requests_number": 100,
      "reset": "2026-03-03T00:00:00",
      "seconds_number": 86400
    },
    "request": {},
    "method": "GET",
    "real_ip": "104.30.161.77",
    "request_id": "e65b239ba9daff01d9b0f37a021a8a66",
    "key_id": 1234,
    "api_key_id": 1234,
    "utcnow": "2026-03-02T11:51:20.794630"
  },
  "status": "ok",
  "error": null
}
```
