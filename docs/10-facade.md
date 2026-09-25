# 10. Facade

[← 9. Decorator](09-decorator.md) · [Mündəricat](README.md) · Növbəti: [11. Flyweight →](11-flyweight.md)

**Qrup:** Structural · **Kod:** [`structural/facade`](../src/main/java/com/company/design_patterns/structural/facade)

---

## Həyatdan analogiya

Restoranda sifariş verəndə mətbəxə girib aşpaza nə bişirəcəyini, qabyuyana hansı boşqabı yuyacağını, anbardara nə çıxaracağını demirsiniz. Ofisianta "bir plov" deyirsiniz. Arxada onlarla iş görülür, amma sizin üçün **bir giriş nöqtəsi** var: ofisiant.

## Problem

Onlayn mağazada sifariş vermək üçün dörd servislə düzgün ardıcıllıqla işləmək lazımdır:

```java
// Bu kod Controller-də, mobil API-də, admin panelində... hər yerdə təkrarlanır
if (!inventory.isAvailable(product)) { email.send(...); return; }
if (!payment.charge(card, price))    { email.send(...); return; }
inventory.reserve(product);
String tracking = shipping.ship(product, address);
email.send(customerEmail, "Order confirmed! " + tracking);
```

Burada bir neçə problem var:
- Hər client dörd servisi tanımalı və ardıcıllığı bilməlidir.
- Ardıcıllıq dəyişsə (məsələn, əvvəl rezerv, sonra ödəniş), hər yerdə dəyişmək lazımdır.
- Səhv etmək asandır: kimsə `reserve()`-u unudar.

## Həll addım-addım

**Addım 1.** Alt sistemləri olduğu kimi saxlayırıq: `InventoryService`, `PaymentService`, `ShippingService`, `EmailService`. Onlara toxunmuruq.

**Addım 2.** Onları birləşdirən yeni sinif (*Facade*) yaradırıq:

```java
public class OrderFacade {
    private final InventoryService inventory = new InventoryService();
    private final PaymentService payment = new PaymentService();
    private final ShippingService shipping = new ShippingService();
    private final EmailService email = new EmailService();

    public boolean placeOrder(String product, BigDecimal price, String card,
                              String address, String customerEmail) {
        if (!inventory.isAvailable(product)) {
            email.send(customerEmail, "Sorry, " + product + " is out of stock");
            return false;
        }
        if (!payment.charge(card, price)) {
            email.send(customerEmail, "Payment failed for " + product);
            return false;
        }
        inventory.reserve(product);
        String tracking = shipping.ship(product, address);
        email.send(customerEmail, "Order confirmed! Tracking number: " + tracking);
        return true;
    }
}
```

**Addım 3.** Client-in işi bir sətirdir:

```java
shop.placeOrder("laptop", new BigDecimal("1899.00"), card, "Baku, Nizami 10", "ali@example.com");
```

## Vacib: Facade qapını bağlamır

Facade alt sistemləri **gizlətmir**, sadəcə rahat yol təklif edir. Admin panelinə yalnız `PaymentService.refund()` lazımdırsa, ona birbaşa müraciət edə bilər. Facade yalnız ən çox istifadə olunan ssenarini sadələşdirir.

## Adapter ilə fərq

| Adapter | Facade |
|---|---|
| **Bir** sinfin interfeysini **başqa, mövcud** interfeysə çevirir | **Çox** sinif üçün **yeni, sadə** interfeys yaradır |
| Məqsəd: uyğunluq | Məqsəd: sadəlik |

## Tələ: "God object"

Facade-ə hər şeyi yığmaq cazibədardır. `OrderFacade` sifariş, qaytarma, hesabat, istifadəçi idarəsi... Tezliklə 3000 sətirlik sinif alınır. Bir facade bir işə cavabdeh olmalıdır. Lazım gəlsə, bir neçə facade yaradın: `OrderFacade`, `ReturnFacade`.

## Real həyatda

Spring-in `JdbcTemplate` sinfi JDBC üçün facade-dir. Connection açmaq, statement yaratmaq, resultset-i gəzmək, exception-ları tutmaq və resursları bağlamaq işlərini bir `query()` çağırışına sığışdırır.

Tipik Spring tətbiqində `Service` qatı da çox vaxt bir neçə repository və xarici API üçün facade rolunu oynayır.

## Yadda saxla

- Facade mürəkkəb alt sistemə **bir sadə giriş** verir.
- Alt sistemlər dəyişmir və lazım olsa birbaşa istifadə oluna bilər.
- Facade-i "hər şeyi bilən" sinfə çevirməyin.

## Tapşırıq

1. `OrderFacade`-ə `cancelOrder(String trackingNumber)` əlavə edin. Pulu geri qaytarsın, anbara qaytarsın, email göndərsin.
2. Servisləri `new` ilə yaratmaq əvəzinə konstruktor vasitəsilə ötürün. Bu, testlərdə saxta (fake) servis istifadə etməyə imkan verir.

---

[← 9. Decorator](09-decorator.md) · [Mündəricat](README.md) · Növbəti: [11. Flyweight →](11-flyweight.md)
