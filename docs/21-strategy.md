# 21. Strategy

[← 20. State](20-state.md) · [Mündəricat](README.md) · Növbəti: [22. Template Method →](22-template-method.md)

**Qrup:** Behavioral · **Kod:** [`behavioral/strategy`](../src/main/java/com/company/design_patterns/behavioral/strategy)

---

## Həyatdan analogiya

Hava limanına getməlisiniz. Yol seçimləri var: taksi (sürətli, baha), avtobus (ucuz, yavaş), metro və ekspres (orta). Məqsəd eynidir, **üsul** fərqlidir. Vaxtınıza və büdcənizə görə seçim edirsiniz, sabah başqasını seçə bilərsiniz. Hava limanı sizin necə gəldiyinizlə maraqlanmır.

## Problem

Səbətdə yekun məbləği hesablayırıq, müxtəlif endirim növləri var:

```java
public BigDecimal total(String discountType) {
    BigDecimal sum = subtotal();
    if (discountType.equals("PERCENT_10")) {
        return sum.multiply(new BigDecimal("0.9"));
    } else if (discountType.equals("MINUS_20")) {
        return sum.compareTo(new BigDecimal("100")) >= 0 ? sum.subtract(new BigDecimal("20")) : sum;
    } else if (discountType.equals("BLACK_FRIDAY")) {
        // ...
    }
    return sum;
}
```

Marketinq komandası hər həftə yeni kampaniya düşünür və hər dəfə `ShoppingCart` sinfini dəyişmək lazım gəlir. Endirim düsturlarını ayrıca test etmək də çətindir.

## Həll addım-addım

**Addım 1.** Bütün alqoritmlər üçün ümumi interfeys (*Strategy*):

```java
@FunctionalInterface
public interface DiscountStrategy {
    BigDecimal apply(BigDecimal total);
}
```

**Addım 2.** Hər alqoritm ayrıca sinifdir:

```java
public class PercentageDiscount implements DiscountStrategy {
    private final int percent;

    @Override
    public BigDecimal apply(BigDecimal total) {
        BigDecimal factor = BigDecimal.valueOf(100 - percent).divide(BigDecimal.valueOf(100));
        return total.multiply(factor).setScale(2, RoundingMode.HALF_UP);
    }
}
```

**Addım 3.** Context (`ShoppingCart`) strategiyanı saxlayır və hesablamanı ona həvalə edir:

```java
public class ShoppingCart {
    private DiscountStrategy discount = new NoDiscount();

    public void setDiscount(DiscountStrategy discount) {
        this.discount = discount;
    }

    public BigDecimal total() {
        return discount.apply(subtotal());   // necə hesablanacağını bilmir
    }
}
```

**Addım 4.** Strategiyanı runtime-da dəyişirik:

```java
cart.setDiscount(new PercentageDiscount(10));    // 103.95
cart.setDiscount(new FixedAmountDiscount(...));  // 95.50
```

## Müasir Java: lambda strategiyadır

`DiscountStrategy` bir metodlu (funksional) interfeys olduğu üçün sadə strategiyaları sinif yazmadan, lambda ilə vermək olar:

```java
cart.setDiscount(total ->
        total.compareTo(new BigDecimal("100")) > 0 ? new BigDecimal("99.99") : total);
```

Java 8-dən sonra Strategy pattern-in böyük hissəsi elə budur: davranışı **funksiya kimi ötürmək**.

## JDK-da, hər gün istifadə etdiyiniz yerdə

```java
list.sort(Comparator.comparing(Person::getAge));        // strategiya: yaşa görə
list.sort(Comparator.comparing(Person::getName));       // strategiya: ada görə
```

`sort` sıralama alqoritmini bilir, amma **müqayisə** strategiyasını siz verirsiniz. `Comparator` klassik Strategy-dir.

## State ilə fərqi

Keçən fəslə baxın. Qısaca: Strategy-ni kənardan client seçir, State isə özü dəyişir.

## Yadda saxla

- Strategy bir işin bir neçə üsulunu ayrıca siniflərə çıxarır.
- Context yalnız interfeysi tanıyır, strategiya isə runtime-da dəyişir.
- Böyük `if/else` alqoritm seçimini görəndə Strategy haqqında düşünün.
- Java 8+ versiyada sadə strategiyalar lambda ilə yazılır.

## Tapşırıq

1. `BuyTwoGetOneFreeDiscount` yazın. Bunun üçün strategiyaya bütün məhsulların siyahısı lazım olacaq. İnterfeysi necə dəyişərdiniz?
2. Bir neçə endirimi ardıcıl tətbiq edən `CompositeDiscount(List<DiscountStrategy>)` yazın.

---

[← 20. State](20-state.md) · [Mündəricat](README.md) · Növbəti: [22. Template Method →](22-template-method.md)
