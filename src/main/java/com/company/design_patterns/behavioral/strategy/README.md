# Strategy

**Kateqoriya:** Behavioral

> Alqoritmlər ailəsini təyin edir, hər birini ayrıca sinfə çıxarır və onları **runtime-da** dəyişdirilə bilən edir.

## Problem

Səbətdə endirim hesablanmasının müxtəlif üsulları var (faiz, sabit məbləğ, kampaniya). Hamısını `ShoppingCart`-da `if/else` ilə yazsaq, hər yeni kampaniya üçün səbəti dəyişmək lazımdır.

## Həll

Hər alqoritm `DiscountStrategy`-ni implement edir. `ShoppingCart` (context) strategiyanı saxlayır və hesablamanı ona həvalə edir; `setDiscount()` ilə strategiya dəyişdirilir. Java 8+ da funksional interfeys olduğu üçün sadə strategiyalar lambda ilə yazıla bilər.

> **State vs Strategy:** strukturları oxşardır. Fərq: Strategy-ni client seçir və strategiyalar bir-birini tanımır; State-də isə state-lər özləri keçid edir.

## Struktur

| Rol | Bu nümunədə |
|---|---|
| Strategy | `DiscountStrategy` |
| Concrete strategies | `NoDiscount, PercentageDiscount, FixedAmountDiscount, lambda` |
| Context | `ShoppingCart` |

## Nə vaxt istifadə etməli

- Eyni işin bir neçə variantı varsa və runtime-da seçilməlidirsə.
- Böyük `if/else`/`switch` alqoritm seçimi üçün istifadə olunursa.

## Üstünlüklər

- Open/Closed
- Alqoritmlər ayrıca test olunur
- Runtime-da dəyişmək olar

## Çatışmazlıqlar

- Client strategiyalar arasındakı fərqi bilməlidir

## Java / real həyatda

- `java.util.Comparator`
- `java.util.concurrent.ThreadPoolExecutor` + `RejectedExecutionHandler`

## İşə salmaq

```bash
mvn -q compile
java -cp target/classes com.company.design_patterns.behavioral.strategy.Main
```
