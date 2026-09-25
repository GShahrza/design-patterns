# 19. Observer

[← 18. Memento](18-memento.md) · [Mündəricat](README.md) · Növbəti: [20. State →](20-state.md)

**Qrup:** Behavioral · **Kod:** [`behavioral/observer`](../src/main/java/com/company/design_patterns/behavioral/observer)

---

## Həyatdan analogiya

YouTube kanalına abunə olursunuz. Yeni video çıxanda bildiriş gəlir. Hər gün kanala girib "yeni video var?" deyə yoxlamırsınız. Kanal da abunəçilərinin kim olduğunu bilmir. O, sadəcə "yeni video" elan edir, abunə olan hər kəs xəbər tutur. İstəsəniz abunəlikdən çıxırsınız.

## Problem

Birjada qiymət dəyişəndə bir neçə şey baş verməlidir: log yazılmalı, hədd keçilibsə xəbərdarlıq edilməli, portfelin dəyəri yenilənməlidir.

```java
public void updatePrice(String symbol, BigDecimal price) {
    prices.put(symbol, price);
    logger.log(...);            // birja loggeri tanıyır
    alertService.check(...);    // alert servisini tanıyır
    portfolio.recalculate(...); // portfeli tanıyır
    // sabah: SMS, dashboard, analitika... hər biri üçün burada dəyişiklik
}
```

`StockExchange` ondan asılı olan hər şeyi tanımağa məcburdur. Yeni reaksiya əlavə etmək birjanın kodunu dəyişmək deməkdir.

Alternativ olaraq hər komponent hər saniyə "qiymət dəyişdi?" deyə soruşa bilər (*polling*). Amma bu, resurs israfıdır.

## Həll addım-addım

**Addım 1.** Dinləyici interfeysi (*Observer*):

```java
@FunctionalInterface
public interface StockObserver {
    void onPriceChanged(String symbol, BigDecimal oldPrice, BigDecimal newPrice);
}
```

**Addım 2.** Mənbə (*Subject*) abunəçilərin siyahısını saxlayır və abunə olmaq/çıxmaq imkanı verir:

```java
public class StockExchange {
    private final List<StockObserver> observers = new ArrayList<>();

    public void subscribe(StockObserver o)   { observers.add(o); }
    public void unsubscribe(StockObserver o) { observers.remove(o); }

    public void updatePrice(String symbol, BigDecimal newPrice) {
        BigDecimal oldPrice = prices.put(symbol, newPrice);
        if (oldPrice != null && oldPrice.compareTo(newPrice) == 0) {
            return;                                   // dəyişiklik yoxdur
        }
        for (StockObserver observer : List.copyOf(observers)) {
            observer.onPriceChanged(symbol, oldPrice, newPrice);
        }
    }
}
```

Birja yalnız interfeysi tanıyır. Kimin abunə olduğu onu maraqlandırmır.

**Addım 3.** Hər reaksiya ayrıca observer-dir:

```java
exchange.subscribe(new PriceAlert("AAPL", new BigDecimal("200")));
exchange.subscribe(new Portfolio(Map.of("AAPL", 10, "TSLA", 5)));

// Funksional interfeys olduğu üçün lambda da observer-dir:
exchange.subscribe((symbol, oldP, newP) ->
        System.out.println("[LOG] " + symbol + ": " + oldP + " -> " + newP));
```

## İki incə detal

**1. Niyə `List.copyOf(observers)` üzərində dövr edirik?**
Observer bildiriş alanda özünü abunəlikdən çıxara bilər (`exchange.unsubscribe(this)`). Əgər orijinal siyahını gəzirsinizsə, bu `ConcurrentModificationException` verər. Surət bu problemi aradan qaldırır.

**2. Niyə dəyişiklik yoxdursa bildiriş göndərmirik?**
Lazımsız bildirişlər hər observer-də lazımsız iş deməkdir. Pis halda isə sonsuz dövr yarada bilər: A dəyişir → B-yə xəbər gedir → B A-nı yeniləyir → A yenə xəbər verir...

## Tələ: memory leak

Observer abunə olub, amma sonra lazımsız olubsa və `unsubscribe` edilməyibsə, subject ona istinad saxlamaqda davam edir. Garbage collector onu silə bilmir. Bu, Java-da ən çox rast gəlinən yaddaş sızmalarından biridir. Qayda: abunə olan, işini bitirəndə abunəlikdən çıxmalıdır.

## Real həyatda

- UI hadisələri: `button.addActionListener(...)`.
- Spring `ApplicationEventPublisher` və `@EventListener`.
- Reactive proqramlaşdırma: RxJava, Project Reactor, `java.util.concurrent.Flow`. Bunlar Observer-in "steroid" versiyasıdır.
- Mesaj növbələri (Kafka topic-ləri) paylanmış Observer-dir.

## Yadda saxla

- Subject dəyişiklikləri elan edir, observer-lər reaksiya verir.
- Subject observer-lərin konkret sinfini bilmir (loose coupling).
- Abunəlikdən çıxmağı unutmayın.

## Tapşırıq

1. `PriceDropAlert` yazın: qiymət 10%-dən çox düşəndə xəbərdarlıq etsin.
2. `PriceAlert` bir dəfə işlədikdən sonra özünü abunəlikdən çıxarsın. Bunun üçün ona `StockExchange` istinadı lazım olacaq. `List.copyOf` olmasa, nə baş verərdi? Yoxlayın.

---

[← 18. Memento](18-memento.md) · [Mündəricat](README.md) · Növbəti: [20. State →](20-state.md)
