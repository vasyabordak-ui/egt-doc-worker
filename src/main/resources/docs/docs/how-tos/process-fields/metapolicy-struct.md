---
scraped_at: '2026-05-06'
source: https://docs.emergingtravel.com/docs/how-tos/process-fields/metapolicy-struct/
tags:
- etg-docs
- how-tos
- process-fields
title: Metapolicy_struct
---

# Metapolicy_struct

ETG API V3

How-tos

Process fields

Metapolicy\_struct

# Metapolicy\_struct

#how-tos

ℹ️

This page describes displaying the `metapolicy_struct` object in Node.js and Python.

Read the dump line by line and find the wanted hotel.

1. Read the objects.
2. Change the objects’ values if necessary.
3. Make sure you process nullable or empty objects.
4. Show the values to the user. For example, use the strings below:
   - `deposit` — the deposit information.

     Node.jsPython

     ```
     1console.log('Deposit is %s to have. %s %s %s The price is %s %s %s.',
     2  deposit.availability, deposit.type, deposit.payment_type, deposit.pricing_method, deposit.price, deposit.currency, deposit.price_unit);
     ```

     ```
     print(f"Deposit is {deposit.availability} to have." +
              f" {deposit.type} {deposit.payment_type} {deposit.pricing_method}" +
              f" The price is {deposit.price} {deposit.currency} {deposit.price_unit}."
     )
     ```
   - `internet` — the internet policy.

     Node.jsPython

     ```
     1console.log('%s is %s to the overall price. The price is %s %s %s. %s.',
     2  internet.type, internet.inclusion, internet.price, internet.currency, internet.price_unit, internet.work_area);
     ```

     ```
     print(f"{internet.type} is {internet.inclusion} to the overall price. " +
              f"The price is {internet.price} {internet.currency} {internet.price_unit}. {internet.work_area}."
     )
     ```
   - `meal` — the adult meals’ policy.

     Node.jsPython

     ```
     1console.log('%s is %s to the overall price. The price is %s %s per a person.',
     2  meal.type, meal.inclusion, meal.price, meal.currency);
     ```

     ```
     print(f"{meal.type} is {meal.inclusion} to the overall price. " +
              f"The price is {meal.price} {meal.currency} per a person."
     )
     ```
   - `children_meal` — the children meals’ policy.

     Node.jsPython

     ```
     1console.log('%s is %s to the overall price. For children of %s–%s y.o. The price is %s %s per a child.',
     2 children_meal.type, children_meal.inclusion, children_meal.age_start, children_meal.age_end, children_meal.price, children_meal.currency);
     ```

     ```
     print(f"{children_meal.type} is {children_meal.inclusion} to the overall price. " +
              f"For children of {children_meal.age_start}–{children_meal.age_end} y.o." +
              f" The price is {children_meal.price} {children_meal.currency} per a child."
     )
     ```
   - `extra_bed` — the adult extra beds’ policy.

     Node.jsPython

     ```
     1console.log('%s bed(s). %s to the overall price. The price is %s %s %s.',
     2  extra_bed.amount, extra_bed.inclusion, extra_bed.price, extra_bed.currency, extra_bed.price_unit);
     ```

     ```
     print(f"{extra_bed.amount} bed(s). {extra_bed.inclusion} to the overall price. " +
              f"The price is {extra_bed.price} {extra_bed.currency} {extra_bed.price_unit}."
     )
     ```
   - `cot` — the cots’ policy.

     Node.jsPython

     ```
     1console.log('%s cot(s). %s to the overall price. The price is %s %s %s.',
     2  cot.amount, cot.inclusion, cot.price, cot.currency, cot.price_unit);
     ```

     ```
     print(f"{cot.amount} cot(s). {cot.inclusion} to the overall price. " +
              f"The price is {cot.price} {cot.currency} {cot.price_unit}."
     )
     ```
   - `pets` — the pets’ accommodation policy.

     Node.jsPython

     ```
     1console.log('Pet weight is %s is %s to the overall price. The price is %s %s %s.',
     2  pets.pets_type, pets.inclusion, pets.price, pets.currency, pets.price_unit);
     ```

     ```
     print(f"Pet weight is {pets.pets_type} is {pets.inclusion} to the overall price. " +
              f"The price is {pets.price} {pets.currency} {pets.price_unit}."
     )
     ```
   - `shuttle` — the shuttles’ policy.

     Node.jsPython

     ```
     1console.log('Shuttle is %s to %s. %s to the overall price. The price is %s %s.',
     2  shuttle.shuttle_type, shuttle.destination_type, shuttle.inclusion, shuttle.price, shuttle.currency);
     ```

     ```
     print(f"Shuttle is {shuttle.shuttle_type} to {shuttle.destination_type}. " +
              f"{shuttle.inclusion} to the overall price. " +
              f"The price is {shuttle.price} {shuttle.currency}."
     )
     ```
   - `parking` — the parking policy.

     Node.jsPython

     ```
     1console.log('Parking is %s and %s to the overall price. The price is %s %s %s.',
     2  parking.territory_type, parking.inclusion, parking.price, parking.currency, parking.price_unit);
     ```

     ```
     print(f"Parking is {parking.territory_type} and {parking.inclusion} to the overall price. " +
            f"The price is {parking.price} {parking.currency} {parking.price_unit}."
     )
     ```
   - `children` — the children extra beds’ policy.

     Node.jsPython

     ```
     1console.log('Bed for a child is %s. For children of %s–%s y.o. The price is %s %s.',
     2   children.extra_bed, children.age_start, children.age_end, children.price, children.currency);
     ```

     ```
     print(f"Bed for a child is {children.extra_bed}. " +
              f"For children of {children.age_start}–{children.age_end} y.o. " +
              f"The price is {children.price} {children.currency}."
     )
     ```
   - `visa` — the visa support policy.

     Node.jsPython

     ```
     1console.log('Visa support for the embassy is %s.',
     2  visa.visa_support);
     ```

     ```
     print(f"Visa support for the embassy is {visa.visa_support}.")
     ```
   - `no_show` — the no-show policy.

     Node.jsPython

     ```
     1console.log('No-show is %s to have %s at %s.',
     2  no_show.availability, no_show.day_period, no_show.time);
     ```

     ```
     print(f"No-show is {no_show.availability} to have {
        no_show.day_period} at {no_show.time}."
     )
     ```
   - `add_fee` — the additional services, fees, and taxes.

     Node.jsPython

     ```
     1console.log('Additional service is for %s. The price is %s %s %s.',
     2  add_fee.fee_type, add_fee.price, add_fee.currency, add_fee.price_unit);
     ```

     ```
     print(f"Additional service is for {add_fee.fee_type}. " +
              f"The price is {add_fee.price} {add_fee.currency} {add_fee.price_unit}."
     )
     ```
   - `check_in_check_out` — the check-in and check-out policies.

     Node.jsPython

     ```
     1console.log('%s is %s to the overall price. The price is %s %s.',
     2  check_in_check_out.check_in_check_out_type, check_in_check_out.inclusion, check_in_check_out.price, check_in_check_out.currency);
     ```

     ```
     print(f"{check_in_check_out.check_in_check_out_type} is {check_in_check_out.inclusion} to the overall price." +
              f" The price is {check_in_check_out.price} {check_in_check_out.currency}."
     )
     ```
