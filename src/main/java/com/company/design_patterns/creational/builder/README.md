# Builder

**Kateqoriya:** Creational

> Mürəkkəb obyekti addım-addım qurmağa imkan verir; eyni prosesdən müxtəlif konfiqurasiyalar alına bilər.

## Problem

`Product`-un 6 sahəsi var, bəziləri məcburi, bəziləri yox. Konstruktor `new Product(1L, "x", null, true, null, null)` oxunaqsızdır ("telescoping constructor"), setter-lər isə obyekti dəyişən (mutable) və yarımçıq vəziyyətdə edir.

## Həll

`Product.builder().name(...).price(...).build()` — hər parametr adı ilə verilir, optional sahələrin default dəyəri var, `build()` validasiyanı bir yerdə edir. `Product` immutable-dır: konstruktoru `private`, setter-i yoxdur.

## Struktur

| Rol | Bu nümunədə |
|---|---|
| Product | `Product` |
| Builder | `Product.ProductBuilder` |

## Nə vaxt istifadə etməli

- Konstruktorun 4-5-dən çox parametri varsa, xüsusən optional olanlar.
- Immutable obyektlər yaratmaq istəyirsinizsə.

## Üstünlüklər

- Oxunaqlı kod
- Immutable obyekt + mərkəzləşdirilmiş validasiya
- Yarımçıq obyekt heç vaxt görünmür

## Çatışmazlıqlar

- Əlavə kod (Lombok `@Builder` bunu avtomatlaşdırır)

## Java / real həyatda

- `java.lang.StringBuilder`
- `java.net.http.HttpRequest.newBuilder()`
- `java.util.stream.Stream.builder()`

## İşə salmaq

```bash
mvn -q compile
java -cp target/classes com.company.design_patterns.creational.builder.Main
```
