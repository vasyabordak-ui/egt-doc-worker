---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/affiliate-api/hotel-search/search-by-region/
tags:
- etg-docs
- affiliate-api
- hotel-search
title: Search by region
---

# Search by region

ETG API V3

Affiliate API

Hotel search

Search by region

# Search by region

#affiliate

Sandbox Production

```
https://api-sandbox.worldota.net/api/b2b/v3/search/serp/region/
```

```
https://api.worldota.net/api/b2b/v3/search/serp/region/
```

The call gets hotels’ rates by the SERP mechanism and the given region.

⚠️

- Let users choose the rates from this call response isn’t recommended.
- A full match with a response from the Retrieve hotelpage request isn’t expected.

## Sandbox limitations

⚠️

Use only field values, IDs, API keys, and any static content from the sandbox environment within the sandbox. **Do not use sandbox data in test or production environments, and do not mix data or configuration between different environments.**

- The input value for the `language` field is ignored; it is always set to `en`.
- The possible values for the `region_id` field are: `2011`, `2395`, `2734`, and `6053839`.
- The value of the `currency` field is always set to `USD`.
- The maximum number of items in `hotels.rates` is `5`.
- The following fields are not supported:
  - `hotels.rates.payment_options.payment_types.cancellation_penalties.policies.commission_info.charge.amount_gross`
  - `hotels.rates.payment_options.payment_types.cancellation_penalties.policies.commission_info.charge.amount_commission`
- Internal search errors are not supported.

## Request example

Sandbox Production

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api-sandbox.worldota.net/api/b2b/v3/search/serp/region/' \
--header 'Content-Type: application/json' \
--data '{
  "checkin": "2026-10-22",
  "checkout": "2026-10-25",
  "residency": "gb",
  "language": "en",
  "guests": [
    {
      "adults": 2,
      "children": []
    }
  ],
  "region_id": 6053839,
  "currency": "EUR",
  "filter": {
    "star_rating": [],
    "kind": [],
    "meal_type": [
      "all-inclusive"
    ]
  }
}'
```

```
curl --user '<KEY_ID>:<API_KEY>' 'https://api.worldota.net/api/b2b/v3/search/serp/region/' \
--header 'Content-Type: application/json' \
--data '{
  "checkin": "2026-10-22",
  "checkout": "2026-10-25",
  "residency": "gb",
  "language": "en",
  "guests": [
    {
      "adults": 2,
      "children": []
    }
  ],
  "region_id": 6053839,
  "currency": "EUR",
  "filter": {
    "star_rating": [],
    "kind": [],
    "meal_type": [
      "all-inclusive"
    ]
  }
}'
```

## Request body

Expand this|
Collapse this

checkin
String
required

The check-in date. The value must be not later than 730 days from the day when the request is made.

checkout
String
required

The check-out date. The value must be not later than 30 days from the checkin field value.

currency
String
optional

The currency of all rooms’ prices.

If passed, the `show_amount` and `show_currency_amount` fields will have the currency of this field.

ℹ️

- The default value is the contract currency for prepaid rates.

hotels\_limit
Int
optional

The maximum number of the available hotels in the response.

language
String
optional

The hotel data language.

ℹ️

- The default value is the contract language.
- The possible values:
  - `ar` — Arabic.
  - `bg` — Bulgarian.
  - `cs` — Czech.
  - `da` — Danish.
  - `de` — German.
  - `el` — Greek.
  - `en` — English.
  - `es` — Spanish.
  - `fi` — Finnish.
  - `fr` — French.
  - `he` — Hebrew.
  - `hu` — Hungarian.
  - `it` — Italian.
  - `ja` — Japanese.
  - `kk` — Kazakh.
  - `ko` — Korean.
  - `nl` — Dutch.
  - `no` — Norwegian.
  - `pl` — Polish.
  - `pt` — Portuguese.
  - `pt_PT` — Portuguese (Portugal).
  - `ro` — Romanian.
  - `ru` — Russian.
  - `sq` — Albanian.
  - `sr` — Serbian.
  - `sv` — Swedish.
  - `th` — Thai.
  - `tr` — Turkish.
  - `uk` — Ukrainian.
  - `vi` — Vietnamese.
  - `zh_CN` — Simplified Chinese.
  - `zh_TW` — Traditional Chinese.

region\_id
Int
required

The region ID.

residency
String
required or optional

The guests’ citizenship in the ISO 3166-1 alpha-2 format.

Use this field if there are doubts regarding the country or the hotel policy towards citizens.

ℹ️

The possible values:

- `ad` — Andorra.
- `ae` — United Arab Emirates.
- `af` — Afghanistan.
- `ag` — Antigua and Barbuda.
- `ai` — Anguilla.
- `al` — Albania.
- `am` — Armenia.
- `ao` — Angola.
- `aq` — Antarctica.
- `ar` — Argentina.
- `as` — American Samoa.
- `at` — Austria.
- `au` — Australia.
- `aw` — Aruba.
- `ax` — Åland Islands.
- `az` — Azerbaijan.
- `ba` — Bosnia and Herzegovina.
- `bb` — Barbados.
- `bd` — Bangladesh.
- `be` — Belgium.
- `bf` — Burkina Faso.
- `bg` — Bulgaria.
- `bh` — Bahrain.
- `bi` — Burundi.
- `bj` — Benin.
- `bl` — Saint Barthélemy.
- `bm` — Bermuda.
- `bn` — Brunei Darussalam.
- `bo` — Bolivia.
- `bq` — Bonaire, Sint Eustatius and Saba.
- `br` — Brazil.
- `bs` — Bahamas.
- `bt` — Bhutan.
- `bv` — Bouvet Island.
- `bw` — Botswana.
- `by` — Belarus.
- `bz` — Belize.
- `ca` — Canada.
- `cc` — Cocos (Keeling) Islands.
- `cd` — Democratic Republic of the Congo, also known as Congo-Kinshasa.
- `cf` — Central African Republic.
- `cg` — The Republic of the Congo, also known as Congo-Brazzaville.
- `ch` — Switzerland.
- `ci` — Côte d’Ivoire.
- `ck` — Cook Islands.
- `cl` — Chile.
- `cm` — Cameroon.
- `cn` — China.
- `co` — Colombia.
- `cr` — Costa Rica.
- `cu` — Cuba.
- `cv` — Cabo Verde.
- `cw` — Curaçao.
- `cx` — Christmas Island.
- `cy` — Cyprus.
- `cz` — Czechia.
- `de` — Germany.
- `dj` — Djibouti.
- `dk` — Denmark.
- `dm` — Dominica.
- `do` — Dominican Republic.
- `dz` — Algeria.
- `ec` — Ecuador.
- `ee` — Estonia.
- `eg` — Egypt.
- `eh` — Western Sahara.
- `er` — Eritrea.
- `es` — Spain.
- `et` — Ethiopia.
- `fi` — Finland.
- `fj` — Fiji.
- `fk` — Falkland Islands (Malvinas).
- `fm` — Micronesia.
- `fo` — Faroe Islands.
- `fr` — France.
- `ga` — Gabon.
- `gb` — United Kingdom of Great Britain and Northern Ireland.
- `gd` — Grenada.
- `ge` — Georgia.
- `gf` — French Guiana.
- `gg` — Guernsey.
- `gh` — Ghana.
- `gi` — Gibraltar.
- `gl` — Greenland.
- `gm` — Gambia.
- `gn` — Guinea.
- `gp` — Guadeloupe.
- `gq` — Equatorial Guinea.
- `gr` — Greece.
- `gs` — South Georgia and the South Sandwich Islands.
- `gt` — Guatemala.
- `gu` — Guam.
- `gw` — Guinea-Bissau.
- `gy` — Guyana.
- `hk` — Hong Kong.
- `hm` — Heard Island and McDonald Islands.
- `hn` — Honduras.
- `hr` — Croatia.
- `ht` — Haiti.
- `hu` — Hungary.
- `id` — Indonesia.
- `ie` — Ireland.
- `il` — Israel.
- `im` — Isle of Man.
- `in` — India.
- `io` — British Indian Ocean Territory.
- `iq` — Iraq.
- `ir` — Iran.
- `is` — Iceland.
- `it` — Italy.
- `je` — Bailiwick of Jersey.
- `jm` — Jamaica.
- `jo` — Jordan.
- `jp` — Japan.
- `ke` — Kenya.
- `kg` — Kyrgyzstan.
- `kh` — Cambodia.
- `ki` — Kiribati.
- `km` — Comoros.
- `kn` — Saint Kitts and Nevis.
- `kp` — North Korea.
- `kr` — South Korea.
- `kw` — Kuwait.
- `ky` — Cayman Islands.
- `kz` — Kazakhstan.
- `la` — Laos.
- `lb` — Lebanon.
- `lc` — Saint Lucia.
- `li` — Liechtenstein.
- `lk` — Sri Lanka.
- `lr` — Liberia.
- `ls` — Lesotho.
- `lt` — Lithuania.
- `lu` — Luxembourg.
- `lv` — Latvia.
- `ly` — Libya.
- `ma` — Morocco.
- `mc` — Monaco.
- `md` — Moldova.
- `me` — Montenegro.
- `mf` — Saint Martin.
- `mg` — Madagascar.
- `mh` — Marshall Islands.
- `mk` — North Macedonia.
- `ml` — Mali.
- `mm` — Myanmar.
- `mn` — Mongolia.
- `mo` — Macao.
- `mp` — Northern Mariana Islands.
- `mq` — Martinique.
- `mr` — Mauritania.
- `ms` — Montserrat.
- `mt` — Malta.
- `mu` — Mauritius.
- `mv` — Maldives.
- `mw` — Malawi.
- `mx` — Mexico.
- `my` — Malaysia.
- `mz` — Mozambique.
- `na` — Namibia.
- `nc` — New Caledonia.
- `ne` — Niger.
- `nf` — Norfolk Island.
- `ng` — Nigeria.
- `ni` — Nicaragua.
- `nl` — Netherlands.
- `no` — Norway.
- `np` — Nepal.
- `nr` — Nauru.
- `nu` — Niue.
- `nz` — New Zealand.
- `om` — Oman.
- `pa` — Panama.
- `pe` — Peru.
- `pf` — French Polynesia.
- `pg` — Papua New Guinea.
- `ph` — Philippines.
- `pk` — Pakistan.
- `pl` — Poland.
- `pm` — Saint Pierre and Miquelon.
- `pn` — Pitcairn.
- `pr` — Puerto Rico.
- `ps` — Palestine.
- `pt` — Portugal.
- `pw` — Palau.
- `py` — Paraguay.
- `qa` — Qatar.
- `re` — Réunion.
- `ro` — Romania.
- `rs` — Serbia.
- `ru` — Russian Federation.
- `rw` — Rwanda.
- `sa` — Saudi Arabia.
- `sb` — Solomon Islands.
- `sc` — Seychelles.
- `sd` — Sudan.
- `se` — Sweden.
- `sg` — Singapore.
- `sh` — Saint Helena, Ascension and Tristan da Cunha.
- `si` — Slovenia.
- `sj` — Svalbard and Jan Mayen.
- `sk` — Slovakia.
- `sl` — Sierra Leone.
- `sm` — San Marino.
- `sn` — Senegal.
- `so` — Somalia.
- `sr` — Suriname.
- `ss` — South Sudan.
- `st` — Sao Tome and Principe.
- `sv` — El Salvador.
- `sx` — Sint Maarten.
- `sy` — Syrian Arab Republic.
- `sz` — Eswatini.
- `tc` — Turks and Caicos Islands.
- `td` — Chad.
- `tf` — French Southern Territories.
- `tg` — Togo.
- `th` — Thailand.
- `tj` — Tajikistan.
- `tk` — Tokelau.
- `tl` — Timor-Leste.
- `tm` — Turkmenistan.
- `tn` — Tunisia.
- `to` — Tonga.
- `tr` — Turkey.
- `tt` — Trinidad and Tobago.
- `tv` — Tuvalu.
- `tw` — Taiwan.
- `tz` — Tanzania.
- `ua` — Ukraine.
- `ug` — Uganda.
- `um` — United States Minor Outlying Islands.
- `us` — United States of America.
- `uy` — Uruguay.
- `uz` — Uzbekistan.
- `va` — Holy See.
- `vc` — Saint Vincent and the Grenadines.
- `ve` — Venezuela.
- `vg` — Virgin Islands (British).
- `vi` — Virgin Islands (U.S.).
- `vn` — Vietnam.
- `vu` — Vanuatu.
- `wf` — Wallis and Futuna.
- `ws` — Samoa.
- `xk` — Kosovo.
- `ye` — Yemen.
- `yt` — Mayotte.
- `za` — South Africa.
- `zm` — Zambia.
- `zw` — Zimbabwe.

timeout
Float
optional

The maximum amount of time in seconds within which searching for rates will be executed.

ℹ️

- The maximum value is `100`.

guests
[Object]
required

The list of guests in the rooms.

ℹ️

- One list item represents one room.
- The maximum number of guests per room is `10`:

  - Maximum number of adults per room is `6`.
  - Maximum number of children per room is `4`.
- The maximum number of rooms per request is `9`.
- The multiroom search:

  - Each request should have from `2` to `9` rooms.
  - A request may contain rooms with different number of guests.
  - Once the request is sent, you can’t:
    - Change the number of guests or rooms.
    - Cancel one of the guests or rooms.

  The ETG API returns rooms suitable for the maximum number of the requested guests.

adults
Int
required

The number of adult guests in one room.

ℹ️

- The minimum value is `1`.
- The maximum value is `6`.

children
[Int]
optional

The age list of the children who will stay in the room.

ℹ️

- The maximum age per child is `17` years.
- The maximum number of children in the room is `4`.

filter
[Object]
optional

Filter parameters for the search request. Allows you to specify additional conditions to refine the search results. Each field represents a particular filter criterion.

star\_rating
[Int]
optional

The hotel rating on a scale from `1` to `5`. Has the `0` value for no available rating.

ℹ️

The list of possible `star_rating` field values is obtained from the call Retrieve filter values.

kind
[String]
optional

The hotel type.

ℹ️

The list of possible `kind` field values is obtained from the call Retrieve filter values.

meal\_type
String
optional

Meal type included in the rate. You can specify multiple values to return rates that match any of the provided meal types.

ℹ️

The list of possible `meal_type` field values is obtained from the call Retrieve hotel static data.

price\_from
Int
optional

Minimum rate price. The value must be an integer greater than `0` and less than the value of `price_to`.

Filtering is applied to rate prices in the search currency (`show_amount`).

price\_to
Int
optional

Maximum rate price. The value must be an integer greater than `0` and greater than the value of `price_from`.

Filtering is applied to rate prices in the search currency (`show_amount`).

## Response

Expand this|
Collapse this

total\_hotels
Int

The total number of unique hotels.

hotels
[Object]

The list of hotels and their rates.

id
String

deprecated

The unique hotel ID in the legacy string format.

ℹ️

- Either this field or the `hid` field is required.

hid
Int

The unique hotel ID in the new numeric format.

ℹ️

- Each ID is an integer no longer than 10 digits.
- We are gradually migrating all clients to use this format.

rates
[Object]

The list of available hotel rates.

allotment
Int

The number of rooms available for the rate.

amenities\_data
[String]

The room amenity list.

To get all available room amenities and their definitions, use the `room_amenities` field from the Retrieve hotel static data call.

any\_residency
Boolean

Whether the rate is allowed to be booked by the guest with any kind of residency or not.

Use it if you don’t collect the guests’ residency.

search\_hash
String

The unique search rate ID used to identify the selected rate.

ℹ️

- Use this value in the `hash` field of the Prebook rate from search step call.
- The value lifetime is 38 minutes after you have got the rate from the search results.
- If an outdated or invalid hash is used, an error will be returned.

daily\_prices
[String]

The list of daily rate prices breakdown in the request currency.

deposit
Object

The deposit information of the order.

Has a value if the rate `payment_types.type` field has the `hotel` value.

amount
String

The deposit amount in the currency specified by the `currency_code` field.

currency\_code
String

The deposit amount currency code. Is the same as the charged (hotel) currency code.

is\_refundable
Boolean

Whether the deposit is refundable or not.

match\_hash
String

The `match_hash` field of the rate from the call made by the SERP mechanism.

Use this field if you are:

- Showing rates to the users from the call made by the SERP mechanism.
- Making a booking from these rates.
- Using this call response as actual data on the rate.
- Using this call response to actualize the exact rate from the calls made by the SERP mechanism.

Usage of this field can help the ETG collect analytics for SERP-HP matching.

meal
String

deprecated

⚠️

Use the `meal_data` field instead.

The meal type.

If there is no meal type provided by the rate source, the `nomeal` value is returned.

To get all available meals and their definitions, use the `meals` field from the Retrieve hotel static data call.

meal\_data
Object

The rate meals information.

value
String

The meal type in the rate.

To get all available meal types and their definitions, use the `meals` field from the Retrieve hotel static data call.

Has the `nomeal` value if no meal type is provided.

has\_breakfast
Boolean

Whether breakfast is included to the rate or not.

no\_child\_meal
Boolean

Whether the children meal is absent in the rate or not.

no\_show
Object

The no-show penalty information.

amount
String

The no-show penalty amount in the currency specified by the `currency_code` field.

currency\_code
String

The no-show penalty amount currency code. Is the same as the charged (hotel) currency code.

from\_time
String

The time in the hotel timezone from which the no-show penalty is charged, in `HH:MM:SS` format (24-hour).

payment\_options
Object

The accepted payment options with the specified amount to be charged.

For a booking, this amount in the requested currency should be paid.

payment\_types
String

The list with accepted payment options.

amount
String

The amount to be charged for the booking in the contract currency code.

by
String

Whether the booking can be paid by a card or not.

ℹ️

- The possible values:
  - `credit_card`
  - `null`

cancellation\_penalties
String

The cancellation rules and commission information.

free\_cancellation\_before
String

The date and time when the free cancellation policy expires.

Has the `null` value, if there is no free cancellation.

The timezone is in UTC±0.

policies
String

The cancellation policies breakdown by periods.

amount\_charge
String

The cancellation penalty amount in the contract currency.

amount\_show
String

The cancellation penalty amount in the request currency.

end\_at
String

The date and time when this cancellation policy expires.

Has the `null` value, if it is in the time from the `start_at` field value till check-in.

If the `start_at` and `end_at` fields have the `null` value, this particular cancellation policy:

- Has no time restrictions.
- Is in effect all the time.

The timezone is in UTC±0.

start\_at
String

The date and time when this cancellation policy takes effect.

Has the `null` value, if it is in effect till the `end_at` field value.

The timezone is in UTC±0.

currency\_code
String

The amount currency code. Is the same as the charged (contract) currency code.

ℹ️

If the `payment_types` is `hotel`, the hotel `currency_code` will be used here.

is\_need\_credit\_card\_data
Boolean

Whether the credit card information is needed or not.

is\_need\_cvc
Boolean

Whether the CVC is needed or not.

type
String

The payment type.

ℹ️

- The possible values:
  - `now`. Use it to allow the user to pay for the booking via the ETG payment system:
    1. Request the Create booking process call and get the card details.
    2. Request the Create credit card token call with the card details.
  - `hotel`. Use it to allow the user to pay for the booking upon check-in at the hotel. The user won’t be charged now.
  - `deposit`. Use it to allow you to charge the user bank card for the booking by yourself:
    1. You need to keep your ETG deposit sufficient. To increase funds, contact your account manager.
    2. The user makes a booking.
    3. You charge the user bank card.
    4. The ETG writes funds from your deposit by themselves during the reporting period.

show\_amount
String

The rate price in the request currency code of this object `show_currency_code` field value.

Isn’t necessarily the sum in the charged or payment currency code.

show\_currency\_code
String

The currency code in the request body.

tax\_data
Object

The tax information.

taxes
Object

The taxes list.

amount
String

The tax amount in the currency specified by the `currency_code` field.

currency\_code
String

The tax amount currency code in the ISO 4217 format.

ℹ️

- The length is `3` characters.
- The possible values:
  - `AED`.
  - `AFN`.
  - `ALL`.
  - `AMD`.
  - `ANG`.
  - `AOA`.
  - `ARS`.
  - `AUD`.
  - `AWG`.
  - `AZN`.
  - `BAM`.
  - `BBD`.
  - `BDT`.
  - `BGN`.
  - `BHD`.
  - `BIF`.
  - `BMD`.
  - `BND`.
  - `BOB`.
  - `BOV`.
  - `BRL`.
  - `BSD`.
  - `BTN`.
  - `BWP`.
  - `BYR`.
  - `BYN`.
  - `BZD`.
  - `CAD`.
  - `CDF`.
  - `CHE`.
  - `CHF`.
  - `CHW`.
  - `CLF`.
  - `CLP`.
  - `CNY`.
  - `COP`.
  - `COU`.
  - `CRC`.
  - `CUC`.
  - `CUP`.
  - `CVE`.
  - `CZK`.
  - `DJF`.
  - `DKK`.
  - `DOP`.
  - `DZD`.
  - `EGP`.
  - `ERN`.
  - `ETB`.
  - `EUR`.
  - `FJD`.
  - `FKP`.
  - `GBP`.
  - `GEL`.
  - `GHS`.
  - `GIP`.
  - `GMD`.
  - `GNF`.
  - `GTQ`.
  - `GYD`.
  - `HKD`.
  - `HNL`.
  - `HRK`.
  - `HTG`.
  - `HUF`.
  - `IDR`.
  - `ILS`.
  - `INR`.
  - `IQD`.
  - `IRR`.
  - `ISK`.
  - `JMD`.
  - `JOD`.
  - `JPY`.
  - `KES`.
  - `KGS`.
  - `KHR`.
  - `KMF`.
  - `KPW`.
  - `KRW`.
  - `KWD`.
  - `KYD`.
  - `KZT`.
  - `LAK`.
  - `LBP`.
  - `LKR`.
  - `LRD`.
  - `LSL`.
  - `LTL`.
  - `LVL`.
  - `LYD`.
  - `MAD`.
  - `MDL`.
  - `MGA`.
  - `MKD`.
  - `MMK`.
  - `MNT`.
  - `MOP`.
  - `MRO`.
  - `MUR`.
  - `MVR`.
  - `MWK`.
  - `MXN`.
  - `MXV`.
  - `MYR`.
  - `MZN`.
  - `NAD`.
  - `NGN`.
  - `NIO`.
  - `NOK`.
  - `NPR`.
  - `NZD`.
  - `OMR`.
  - `PAB`.
  - `PEN`.
  - `PGK`.
  - `PHP`.
  - `PKR`.
  - `PLN`.
  - `PYG`.
  - `QAR`.
  - `RON`.
  - `RSD`.
  - `RUB`.
  - `RWF`.
  - `SAR`.
  - `SBD`.
  - `SCR`.
  - `SDG`.
  - `SEK`.
  - `SGD`.
  - `SHP`.
  - `SLL`.
  - `SOS`.
  - `SRD`.
  - `SSP`.
  - `STD`.
  - `SVC`.
  - `SYP`.
  - `SZL`.
  - `THB`.
  - `TJS`.
  - `TMT`.
  - `TND`.
  - `TOP`.
  - `TRY`.
  - `TTD`.
  - `TWD`.
  - `TZS`.
  - `UAH`.
  - `UGX`.
  - `USD`.
  - `USN`.
  - `USS`.
  - `UYI`.
  - `UYU`.
  - `UZS`.
  - `VEF`.
  - `VND`.
  - `VUV`.
  - `WST`.
  - `XAF`.
  - `XAG`.
  - `XAU`.
  - `XBA`.
  - `XBB`.
  - `XBC`.
  - `XBD`.
  - `XCD`.
  - `XDR`.
  - `XFU`.
  - `XOF`.
  - `XPD`.
  - `XPF`.
  - `XPT`.
  - `XSU`.
  - `XTS`.
  - `XUA`.
  - `YER`.
  - `ZAR`.
  - `ZMW`.
  - `ZWL`.

included\_by\_supplier
Boolean

Whether the tax is included by the supplier or not.

When it has:

- The `false` value, the tax is supposed to be paid at the hotel in this object currency.
- The `true` value, the tax is included in the price.

name
String

The ETG tax ID.

To get all available tax IDs and their definitions, use the `taxes` field from the Retrieve hotel static data call.

rg\_ext
Object

The hotel room type.

Use this field to get extra data on the room from the hotel static data. For example, room images, descriptions.

balcony
Int

Whether there is a balcony or not.

ℹ️

The possible values:

- `0` — no balcony.
- `1` — a balcony.

bathroom
Int

The room bathroom information.

ℹ️

The possible values:

- `0` — undefined.
- `1` — a shared bathroom.
- `2` — a private bathroom.
- `3` — an external private bathroom.

bedding
Int

The room bedding information.

ℹ️

The possible values:

- `0` — undefined.
- `1` — a bunk bed.
- `2` — a single bed.
- `3` — a double bed.
- `4` — a twin bed.
- `7` — multiple beds.

bedrooms
Int

The bedroom number.

ℹ️

The possible values:

- `0` — undefined.
- `1` — 1 bedroom.
- `2` — 2 bedrooms.
- `3` — 3 bedrooms.
- `4` — 4 bedrooms.
- `5` — 5 bedrooms.
- `6` — 6 bedrooms.

capacity
Int

The maximum number of main bed places without additional charges and excluding extra beds, cots, etc.

ℹ️

The possible values:

- `0` — undefined.
- `1` — single.
- `2` — double.
- `3` — triple.
- `4` — quadruple.
- `5` — quintuple.
- `6` — sextuplet.

class
Int

The room class information.

ℹ️

The possible values:

- `0` — run of house.
- `1` — dorm.
- `2` — capsule.
- `3` — room.
- `4` — junior suite.
- `5` — suite.
- `6` — apartment.
- `7` — studio.
- `8` — villa.
- `9` — cottage.
- `17` — bungalow.
- `18` — chalet.
- `19` — camping.
- `20` — tent.

club
Int

Whether it is a club room or not.

ℹ️

The possible values:

- `0` — not a club room.
- `1` — a club room.

family
Int

Whether it is a family room or not.

ℹ️

The possible values:

- `0` — not a family room.
- `1` — a family room.

floor
Int

The room floor Information.

ℹ️

The possible values:

- `0` — undefined.
- `1` — a penthouse floor.
- `2` — a duplex floor.
- `3` — a basement floor.
- `4` — an attic floor.

quality
Int

The room quality information.

ℹ️

The possible values:

- `0` — undefined.
- `1` — economy.
- `2` — standard.
- `3` — comfort.
- `4` — business.
- `5` — superior.
- `6` — deluxe.
- `7` — premier.
- `8` — executive.
- `9` — presidential.
- `17` — premium.
- `18` — classic.
- `19` — ambassador.
- `20` — grand.
- `21` — luxury.
- `22` — platinum.
- `23` — prestige.
- `24` — privilege.
- `25` — royal.

sex
Int

The room gender restrictions.

ℹ️

The possible values:

- `0` — undefined.
- `1` — male.
- `2` — female.
- `3` — mixed.

view
Int

The room view information.

ℹ️

The possible values:

- `0` — undefined.
- `1` — bay view.
- `2` — bosphorus view.
- `3` — burj-khalifa view.
- `4` — canal view.
- `5` — city view.
- `6` — courtyard view.
- `7` — dubai-marina view.
- `8` — garden view.
- `9` — golf view.
- `17` — harbour view.
- `18` — inland view.
- `19` — kremlin view.
- `20` — lake view.
- `21` — land view.
- `22` — mountain view.
- `23` — ocean view.
- `24` — panoramic view.
- `25` — park view.
- `26` — partial-ocean view.
- `27` — partial-sea view.
- `28` — partial view.
- `29` — pool view.
- `30` — river view.
- `31` — sea view.
- `32` — sheikh-zayed view.
- `33` — street view.
- `34` — sunrise view.
- `35` — sunset view.
- `36` — water view.
- `37` — with view.
- `38` — beachfront.
- `39` — ocean front.
- `40` — sea front.

room\_data\_trans
Object

The room information in the request language.

bathroom
String

The room bathroom information.

Has the `null` value, if it is a private bathroom.

bedding\_type
String

The room bedding information.

main\_name
String

The room name.

main\_room\_type
String

The room type.

misc\_room\_type
String

The room additional information.

beds
Object

An array listing the types and quantities of beds available in the room.

ℹ️

This field and its nested fields are **not available in the Sandbox environment**.

bed
String

Type of bed.

count
String

Quantity of this bed type.

legal\_info
Object

The hotel and service provider legal information.

ℹ️

The information provided depends on the supplier of the specific rate.

Has the value different from `null` for only countries where it is mandatory to have this information.

Has the `null` value for the calls made by the SERP mechanism.

hotel
Object

The hotel legal information.

name
String

The hotel legal name.

address
String

The hotel legal address.

taxpayer\_number
String

The Taxpayer Personal Identification Number (INN) of the hotel.

ℹ️

- The length is `10` characters.

state\_registration\_number
String

The State Registration Number for Companies (OGRN) of the hotel.

ℹ️

- The length is `13` characters.

work\_time
String

The hotel legal address working hours.

provider
Object

The service provider legal information.

name
String

The service provider legal name.

address
String

The service provider legal address.

taxpayer\_number
String

The Taxpayer Personal Identification Number (INN) of the service provider.

ℹ️

- The length is `10` characters.

state\_registration\_number
String

The State Registration Number for Companies (OGRN) of the service provider.

ℹ️

- The length is `13` characters.

is\_package
Boolean

Rates marked with `is_package = true` should be sold as a part of package, and the price for the hotel should not be shown separately.

room\_name
String

The room name in the request language.

room\_name\_info
Object

The optional object that may help to resolve certain matching problems. To get access to the object, contact your account manager.

serp\_filters
[String]

deprecated

The list of hotel amenities. Accepts a list of limited hotel amenities of amenity values. This limit is not stable and may vary between different rates within the same hotel — some values may be applicable for one rate but not considered for another.

ℹ️

Supported values:

- has\_airport\_transfer.
- has\_parking.
- air-conditioning.
- has\_internet.
- has\_breakfast.

## Response example

```
{
  "data": {
    "hotels": [
      {
        "id": "aloft_palm_jumeirah",
        "hid": 8746275,
        "rates": [
          {
            "match_hash": "m-df3c0498-97e0-52de-a524-32c39ca8dfff",
            "search_hash": null,
            "daily_prices": [
              "340.00",
              "340.00",
              "340.00"
            ],
            "meal": "all-inclusive",
            "meal_data": {
              "value": "all-inclusive",
              "has_breakfast": true,
              "no_child_meal": false
            },
            "payment_options": {
              "payment_types": [
                {
                  "amount": "91817.00",
                  "show_amount": "1020.00",
                  "currency_code": "EUR",
                  "show_currency_code": "EUR",
                  "by": "credit_card",
                  "is_need_credit_card_data": true,
                  "is_need_cvc": true,
                  "type": "now",
                  "tax_data": {
                    "taxes": [
                      {
                        "name": "city_tax",
                        "included_by_supplier": false,
                        "amount": "45.00",
                        "currency_code": "AED"
                      },
                      {
                        "name": "occupancy_tax",
                        "included_by_supplier": true,
                        "amount": "58.04",
                        "currency_code": "EUR"
                      },
                      {
                        "name": "service_fee",
                        "included_by_supplier": true,
                        "amount": "91.41",
                        "currency_code": "EUR"
                      },
                      {
                        "name": "vat",
                        "included_by_supplier": true,
                        "amount": "41.45",
                        "currency_code": "EUR"
                      }
                    ]
                  },
                  "cancellation_penalties": {
                    "policies": [
                      {
                        "start_at": null,
                        "end_at": "2026-10-11T20:00:00",
                        "amount_charge": "0.00",
                        "amount_show": "0.00"
                      },
                      {
                        "start_at": "2026-10-11T20:00:00",
                        "end_at": null,
                        "amount_charge": "91817.00",
                        "amount_show": "1020.00"
                      }
                    ],
                    "free_cancellation_before": "2026-10-11T20:00:00"
                  }
                }
              ]
            },
            "rg_ext": {
              "class": 3,
              "quality": 2,
              "sex": 0,
              "bathroom": 2,
              "bedding": 3,
              "family": 0,
              "capacity": 2,
              "club": 0,
              "bedrooms": 0,
              "balcony": 0,
              "view": 0,
              "floor": 0
            },
            "room_name": "Aloft Double room (full double bed) (bed type is subject to availability)",
            "room_name_info": null,
            "serp_filters": [
              "has_bathroom"
            ],
            "allotment": 99,
            "amenities_data": [
              "not-guaranteed",
              "non-smoking"
            ],
            "any_residency": false,
            "deposit": null,
            "no_show": null,
            "room_data_trans": {
              "main_room_type": "Aloft Double room",
              "main_name": "Aloft Double room",
              "bathroom": null,
              "bedding_type": "full double bed",
              "misc_room_type": "bed type is subject to availability",
              "beds": [
                {
                  "bed": "double",
                  "count": 1
                }
              ]
            },
            "legal_info": null,
            "is_package": false
          }
        ]
      },
      {
        "id": "moevenpick_hotel_jumeirah_beach",
        "hid": 7459783,
        "rates": [
          {
            "match_hash": "m-71b1ec5c-046c-5108-899a-e924d938888f",
            "search_hash": null,
            "daily_prices": [
              "438.33",
              "438.33",
              "438.33"
            ],
            "meal": "all-inclusive",
            "meal_data": {
              "value": "all-inclusive",
              "has_breakfast": true,
              "no_child_meal": false
            },
            "payment_options": {
              "payment_types": [
                {
                  "amount": "118329.00",
                  "show_amount": "1315.00",
                  "currency_code": "EUR",
                  "show_currency_code": "EUR",
                  "by": "credit_card",
                  "is_need_credit_card_data": true,
                  "is_need_cvc": true,
                  "type": "now",
                  "tax_data": {
                    "taxes": [
                      {
                        "name": "city_tax",
                        "included_by_supplier": false,
                        "amount": "60.00",
                        "currency_code": "AED"
                      },
                      {
                        "name": "occupancy_tax",
                        "included_by_supplier": true,
                        "amount": "74.82",
                        "currency_code": "EUR"
                      },
                      {
                        "name": "service_fee",
                        "included_by_supplier": true,
                        "amount": "117.84",
                        "currency_code": "EUR"
                      },
                      {
                        "name": "vat",
                        "included_by_supplier": true,
                        "amount": "53.44",
                        "currency_code": "EUR"
                      }
                    ]
                  },
                  "cancellation_penalties": {
                    "policies": [
                      {
                        "start_at": null,
                        "end_at": null,
                        "amount_charge": "118329.00",
                        "amount_show": "1315.00"
                      }
                    ],
                    "free_cancellation_before": null
                  }
                }
              ]
            },
            "rg_ext": {
              "class": 3,
              "quality": 5,
              "sex": 0,
              "bathroom": 2,
              "bedding": 3,
              "family": 0,
              "capacity": 2,
              "club": 0,
              "bedrooms": 0,
              "balcony": 1,
              "view": 0,
              "floor": 0
            },
            "room_name": "Superior Double room with balcony (full double bed) (bed type is subject to availability)",
            "room_name_info": null,
            "serp_filters": [
              "has_bathroom",
              "has_breakfast"
            ],
            "allotment": 30,
            "amenities_data": [
              "non-smoking",
              "not-guaranteed"
            ],
            "any_residency": false,
            "deposit": null,
            "no_show": null,
            "room_data_trans": {
              "main_room_type": "Superior Double room with balcony",
              "main_name": "Superior Double room with balcony",
              "bathroom": null,
              "bedding_type": "full double bed",
              "misc_room_type": "bed type is subject to availability",
              "beds": [
                {
                  "bed": "double",
                  "count": 1
                }
              ]
            },
            "legal_info": null,
            "is_package": false
          }
        ]
      }
    ],
    "total_hotels": 2
  },
  "debug": {
    "api_endpoint": {
      "endpoint": "api/b2b/v3/search/serp/region",
      "is_active": true,
      "is_limited": true,
      "remaining": 9,
      "requests_number": 10,
      "reset": "2026-04-16T20:42:00",
      "seconds_number": 60
    },
    "request": {
      "checkin": "2026-10-22",
      "checkout": "2026-10-25",
      "residency": "gb",
      "language": "en",
      "guests": [
        {
          "adults": 2,
          "children": []
        }
      ],
      "region_id": 6053839,
      "currency": "EUR",
      "filter": {
        "star_rating": [],
        "kind": [],
        "meal_type": [
          "all-inclusive"
        ]
      }
    },
    "method": "POST",
    "real_ip": "104.30.161.77",
    "request_id": "e2e7c2d267ae2dc6038f5bf886d71e8c",
    "key_id": 1234,
    "api_key_id": 1234,
    "utcnow": "2026-04-16T20:41:17.490485"
  },
  "status": "ok",
  "error": null
}
```

## Errors

### Invalid JSON

The `error` field has the `invalid_json_format` value. The `debug.validation_error` field has the value specified in the headers below.

#### `body_must_be_json`

The passed JSON isn’t valid.

### Invalid parameters or request body

For these, the `error` field has the `invalid_params` value. The `debug.validation_error` field has the value specified in the headers below.

#### `date should be ISO8601 (YYYY-MM-DD)`

The passed `checkin` and `checkout` fields have an invalid format. The valid format is `YYYY-MM-DD`.

#### `checkin date must be current or future date`

The `checkin` field value is in the past. Provide the current or future date.

#### `checkin date must be less than or equal to 730 days since today`

The `checkin` field value should be no later than 730 days from the day of the request.

#### `checkout date must be less than or equal to %d days since today`

The `checkout` field value should be no later than 30 days from the `checkin` field value.

#### `checkout date must be after checkin date`

The `checkout` field value should be later than the `checkin` field value.

#### `checkout cannot be later than X days since checkin`

The `checkout` field value should be no later than 30 days from the `checkin` field value.

#### `residency is required`

The `residency` field is required.

#### `residency must be one of X`

The `residency` field has an invalid format. The valid format is in the ISO 3166-1 alpha-2.

#### `unknown currency`

The currency field has an invalid format. The valid format is in the ISO 4217.

#### `early checkin date must be equal checkin date`

The `upsells.early_checkin.time` field value should be the same as the `checkin` field value.

#### `late checkout date must be equal checkout date`

The `upsells.late_checkout.time` field value should be the same as the `checkout` field value.

#### `early checkin must be in datetime format`

The `checkin` field has an invalid format. The valid formats are:

- `2006-01-02`.
- `2006-01-02 15:04`.
- `2006-01-02T15:04`.
- `2006-01-02 15:04:05`.
- `2006-01-02T15:04:05`.
- `2006-01-02T15:04:05-07:00`.
- `2006-01-02T15:04:05Z07:00`.

#### `late checkout must be in datetime format`

The `checkout` field has an invalid format. The valid formats are:

- `2006-01-02`.
- `2006-01-02 15:04`.
- `2006-01-02T15:04`.
- `2006-01-02 15:04:05`.
- `2006-01-02T15:04:05`.
- `2006-01-02T15:04:05-07:00`.
- `2006-01-02T15:04:05Z07:00`.

#### `guests number must be positive`

The `guests` field value must be positive.

#### `too many rooms, value should be no more than X`

The item number of the `guests` field must be between `1` and `9`.

#### `adults number per room must be in range 1 to X inc`

The `adults` field value must be between `1` and `6`.

#### `children number per room must be not greater then X`

The `children` field value must be between `1` and `4`.

#### `child's age must be in range 0 to X incl`

The `age` field value must be between `0` and `17`.

#### `region_id value should be greater than 0`

The `region_id` field value is invalid. The valid value is equal to or greater than `0`.

#### `hotels_limit value should be greater than or equal to 0`

The `hotels_limit` field value is invalid. The valid value is equal to or greater than `0`.

#### `hotels_not_found`

The hotels aren’t found or an internal error has occurred.

#### `invalid region_id field, this region cannot be searched`

The `region_id` field value is invalid. The valid values are in the Retrieve regions’ dump call response.

### Internal search error

The `error` field has the `core_search_error` value. The `debug.validation_error` field has the `null` value.

The error occurs if the ETG has:

- An internal search error.
- A region search error.

The number of recurrent identical requests must be limited.
