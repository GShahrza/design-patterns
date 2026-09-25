# 20. State

[← 19. Observer](19-observer.md) · [Mündəricat](README.md) · Növbəti: [21. Strategy →](21-strategy.md)

**Qrup:** Behavioral · **Kod:** [`behavioral/state`](../src/main/java/com/company/design_patterns/behavioral/state)

---

## Həyatdan analogiya

Telefonunuzdakı düymə vəziyyətə görə fərqli işləyir. Ekran kilidlidirsə, basanda kilid ekranı açılır. Ekran açıqdırsa, ekran söndürülür. Batareya bitibsə, heç nə olmur. Düymə eynidir, amma **davranış telefonun vəziyyətindən asılıdır**.

## Problem

Sifarişin həyat dövrü belədir:

```
NEW ──pay──► PAID ──ship──► SHIPPED ──deliver──► DELIVERED
 │            │
 └──cancel──► CANCELLED ◄──cancel (pul qaytarılır)
```

Hər metodda `switch` yazmağa başlayırıq:

```java
public void ship() {
    switch (status) {
        case NEW:       throw new IllegalStateException("Not paid yet");
        case PAID:      status = SHIPPED; break;
        case SHIPPED:   throw new IllegalStateException("Already shipped");
        case DELIVERED: throw new IllegalStateException("Already delivered");
        case CANCELLED: throw new IllegalStateException("Cancelled");
    }
}
// pay(), deliver(), cancel()... hər birində eyni switch
```

4 metod × 5 vəziyyət = 20 hal, səpələnmiş şəkildə. "RETURNED" vəziyyəti əlavə etsəniz, bütün switch-ləri tapıb dəyişməlisiniz. Birini unutsanız, bug yaranır.

## Həll addım-addım

**Addım 1.** Hər vəziyyət ayrıca sinif olur. Ümumi interfeysdə bütün əməliyyatlar **default olaraq qadağandır**:

```java
public interface OrderState {
    String name();

    default void pay(Order order)     { reject("pay"); }
    default void ship(Order order)    { reject("ship"); }
    default void deliver(Order order) { reject("deliver"); }
    default void cancel(Order order)  { reject("cancel"); }

    private void reject(String action) {
        throw new IllegalStateException("Cannot " + action + " order in state " + name());
    }
}
```

**Addım 2.** Hər vəziyyət **yalnız icazə verilən** keçidləri override edir:

```java
public class PaidState implements OrderState {
    public String name() { return "PAID"; }

    @Override
    public void ship(Order order) {
        order.setState(new ShippedState());
    }

    @Override
    public void cancel(Order order) {
        System.out.println("  refunding payment...");
        order.setState(new CancelledState());
    }
}
```

`PaidState` faylını oxuyan hər kəs bir baxışda görür: ödənilmiş sifarişi göndərmək və ya ləğv etmək olar, başqa heç nə.

**Addım 3.** Context (`Order`) işi sadəcə cari vəziyyətə ötürür:

```java
public class Order {
    private OrderState state = new NewState();

    public void ship()   { state.ship(this); }
    public void cancel() { state.cancel(this); }
    // ...
}
```

`Order`-də bir dənə də olsun `if` yoxdur.

## Nəticə

```
  ORD-1: NEW -> PAID
  ORD-1: PAID -> SHIPPED
  ORD-1: SHIPPED -> DELIVERED
  ORD-2: NEW -> PAID
  refunding payment...
  ORD-2: PAID -> CANCELLED
  error: Cannot ship order in state NEW
```

## Strategy ilə fərq

Struktur baxımından ikisi demək olar ki, eynidir: context interfeysə malik obyekti saxlayır və işi ona ötürür. Fərq belədir:

| Strategy | State |
|---|---|
| Strategiyanı **client** seçir | Vəziyyət **özü** növbəti vəziyyətə keçir |
| Strategiyalar bir-birindən xəbərsizdir | Vəziyyətlər bir-birini tanıyır (`new ShippedState()`) |
| "Bu işi **necə** edim?" | "İndi **nə edə bilərəm**?" |

## Real həyatda

- Sifariş, ödəniş, sənəd təsdiqi kimi iş axınları (workflow).
- TCP bağlantısı: `LISTEN`, `ESTABLISHED`, `CLOSED`...
- Oyun personajı: `Idle`, `Running`, `Jumping`, `Dead`.
- Spring State Machine kitabxanası.

## Nə vaxt ETMƏMƏLİ

Cəmi 2-3 vəziyyət və 1-2 metod varsa, sadə `enum` və `switch` daha oxunaqlıdır. State pattern vəziyyət və keçidlər çox olanda özünü doğruldur.

## Yadda saxla

- Hər vəziyyət ayrıca sinifdir və öz davranışını bilir.
- Context vəziyyətə görə `if` yazmır, işi cari vəziyyətə ötürür.
- Yanlış keçid təbii şəkildə qadağandır: vəziyyət metodu override etməyibsə, keçid mümkün deyil.

## Tapşırıq

1. `RETURNED` vəziyyəti əlavə edin: yalnız `DELIVERED`-dən keçmək mümkün olsun (`order.returnOrder()`). Neçə fayla toxundunuz?
2. Vəziyyətlərin hər dəfə `new` ilə yaradılmasına ehtiyac varmı? Onların sahəsi yoxdur. Onları singleton-a necə çevirmək olar?

---

[← 19. Observer](19-observer.md) · [Mündəricat](README.md) · Növbəti: [21. Strategy →](21-strategy.md)
