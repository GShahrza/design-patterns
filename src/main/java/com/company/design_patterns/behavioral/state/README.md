# State

**Kateqoriya:** Behavioral

> Obyektin daxili vəziyyəti dəyişəndə davranışını da dəyişməsinə imkan verir — sanki sinfini dəyişib.

## Problem

Sifarişin vəziyyətləri var: NEW → PAID → SHIPPED → DELIVERED və ya CANCELLED. Hər metodda (`pay`, `ship`, `cancel`) böyük `switch (status)` yazmaq lazım gəlir və yeni vəziyyət əlavə etdikdə bütün switch-ləri dəyişmək lazımdır.

## Həll

Hər vəziyyət `OrderState`-i implement edən ayrıca sinifdir və yalnız ona icazə verilən keçidləri override edir (qalanları default olaraq `IllegalStateException` atır). `Order` (context) işi cari state-ə ötürür, state isə `order.setState(...)` ilə keçid edir.

## Struktur

| Rol | Bu nümunədə |
|---|---|
| Context | `Order` |
| State | `OrderState` |
| Concrete states | `NewState, PaidState, ShippedState, DeliveredState, CancelledState` |

## Nə vaxt istifadə etməli

- Obyektin davranışı vəziyyətdən asılıdır və vəziyyətlər çoxdur.
- Kodda vəziyyətə görə böyük şərt blokları var.

## Üstünlüklər

- Hər vəziyyətin məntiqi ayrı sinifdə (SRP)
- Yanlış keçidlər təbii şəkildə qadağandır

## Çatışmazlıqlar

- Vəziyyət az olduqda artıq mürəkkəblikdir

## Java / real həyatda

- TCP bağlantı vəziyyətləri, workflow mühərrikləri
- Spring State Machine

## İşə salmaq

```bash
mvn -q compile
java -cp target/classes com.company.design_patterns.behavioral.state.Main
```
