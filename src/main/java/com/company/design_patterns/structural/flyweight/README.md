# Flyweight

**Kateqoriya:** Structural

> Çox sayda oxşar obyekt arasında ümumi vəziyyəti **paylaşaraq** yaddaşa qənaət edir.

## Problem

Oyunda 100 000 ağac var. Hər ağac obyekti ad, rəng və tekstura (meqabaytlarla şəkil) saxlasa, yaddaş tükənər. Halbuki cəmi 3 növ ağac var.

## Həll

Vəziyyəti ikiyə bölürük:
- **Intrinsic** (daxili, paylaşılan, dəyişməz): ad, rəng, tekstura → `TreeType`.
- **Extrinsic** (xarici, hər obyektə məxsus): koordinatlar → `Tree`.

`TreeTypeFactory` eyni parametrli `TreeType`-ı bir dəfə yaradır və keşdən qaytarır. Nəticə: 100 000 `Tree`, amma cəmi **3** `TreeType`.

## Struktur

| Rol | Bu nümunədə |
|---|---|
| Flyweight | `TreeType` |
| Context | `Tree` |
| Flyweight factory | `TreeTypeFactory` |
| Client | `Forest` |

## Nə vaxt istifadə etməli

- Proqram çox sayda oxşar obyekt yaradır və yaddaş problemi var.
- Obyektlərin vəziyyətinin böyük hissəsi ümumidir.

## Üstünlüklər

- Yaddaşa böyük qənaət

## Çatışmazlıqlar

- Kod mürəkkəbləşir
- Flyweight mütləq immutable olmalıdır

## Java / real həyatda

- `Integer.valueOf()` (-128..127 keşi)
- `String` pool (`String.intern()`)

## İşə salmaq

```bash
mvn -q compile
java -cp target/classes com.company.design_patterns.structural.flyweight.Main
```
