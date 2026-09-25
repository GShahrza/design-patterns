# 1. Singleton

[← Giriş: Design pattern nədir?](00-giris.md) · [Mündəricat](README.md) · Növbəti: [2. Factory Method →](02-factory-method.md)

**Qrup:** Creational · **Kod:** [`creational/singleton`](../src/main/java/com/company/design_patterns/creational/singleton)

---

## Həyatdan analogiya

Bir ölkənin yalnız bir prezidenti olur. Kim "prezidentlə danışmaq istəyirəm" desə, eyni adamın yanına gedir. Hər müraciət edənə yeni prezident seçilmir.

## Problem

Tətbiqin konfiqurasiyası fayldan oxunur. Bu yavaş əməliyyatdır. Əgər hər sinif `new AppConfig()` yazsa:

```java
class OrderService   { AppConfig config = new AppConfig(); } // faylı oxudu
class PaymentService { AppConfig config = new AppConfig(); } // yenə oxudu
class EmailService   { AppConfig config = new AppConfig(); } // yenə...
```

Burada iki problem var:
- Fayl dəfələrlə oxunur, yəni resurs israf olunur.
- Bir servis konfiqurasiyanı dəyişsə, digərləri bunu görmür, çünki onların öz nüsxələri var.

Bizə **tək bir obyekt** və ona **hər yerdən eyni yolla çatmaq** lazımdır.

## Həll addım-addım

**Addım 1.** Konstruktoru `private` edirik. Artıq kənardan heç kim `new` yaza bilməz.

**Addım 2.** Sinfin içində tək nüsxəni saxlayan `static` sahə yaradırıq.

**Addım 3.** Həmin nüsxəni qaytaran `static` metod yazırıq.

Ən sadə forma, `EagerInitializationSingleton` belədir:

```java
public class EagerInitializationSingleton {

    private static final EagerInitializationSingleton INSTANCE = new EagerInitializationSingleton();

    private EagerInitializationSingleton() { }   // Addım 1

    public static EagerInitializationSingleton getInstance() {   // Addım 3
        return INSTANCE;
    }
}
```

Obyekt sinif yaddaşa yüklənəndə yaranır. JVM bunu thread-safe edir. Çatışmazlığı budur: obyekt heç istifadə olunmasa belə yaradılır.

### "Lazy" etmək istəsək: tələ

```java
public static LazySingleton getInstance() {
    if (instance == null) {          // Thread A burada yoxlayır: null
        instance = new LazySingleton();  // Thread B də eyni anda yoxlayıb: null
    }
    return instance;
}
```

İki thread eyni anda `if`-ə girsə, **iki obyekt** yaranır və Singleton pozulur. Ona görə `LazySingleton` yalnız tək thread-li proqram üçün yararlıdır.

### Düzgün lazy variantlar

**Double-checked locking.** Kilid yalnız ilk dəfə alınır:

```java
private static volatile DoubleCheckedLockingSingleton instance;

public static DoubleCheckedLockingSingleton getInstance() {
    DoubleCheckedLockingSingleton result = instance;
    if (result == null) {                                   // 1-ci yoxlama (kilidsiz, sürətli)
        synchronized (DoubleCheckedLockingSingleton.class) {
            result = instance;
            if (result == null) {                           // 2-ci yoxlama (kilidin içində)
                instance = result = new DoubleCheckedLockingSingleton();
            }
        }
    }
    return result;
}
```

`volatile` sözü burada mütləqdir. Onsuz bir thread obyekti "yarımçıq qurulmuş" halda görə bilər.

**Bill Pugh üsulu.** Java-nın öz qaydasından istifadə edir: daxili sinif yalnız ona ilk dəfə müraciət olunanda yüklənir.

```java
public static BillPughSingleton getInstance() {
    return SingletonHelper.INSTANCE;
}

private static class SingletonHelper {
    private static final BillPughSingleton INSTANCE = new BillPughSingleton();
}
```

Kilid yoxdur, kod sadədir, həm lazy, həm də thread-safe-dir.

**Enum.** Joshua Bloch "Effective Java" kitabında bunu ən yaxşı üsul adlandırır:

```java
public enum EnumSingleton {
    INSTANCE;
    public void singletonTest() { ... }
}
```

Enum-u nə reflection, nə də serialization ilə ikinci dəfə yaratmaq mümkündür.

## Hansını seçim?

| Vəziyyət | Seçim |
|---|---|
| Sadəcə işləsin, ən etibarlısı olsun | `EnumSingleton` |
| Lazy lazımdır, enum uyğun gəlmir (məs. başqa sinfi extend etmək lazımdır) | `BillPughSingleton` |
| Tək thread-li kiçik skript | `LazySingleton` də olar |
| Heç vaxt | Hər çağırışda `synchronized` (`ThreadSafeSingleton`), çünki lazımsız yavaşdır |

## Nə vaxt istifadə ETMƏMƏLİ

Singleton tez-tez "qlobal dəyişən"in gözəl adı kimi istifadə olunur. Bu isə testləri çətinləşdirir: `OrderService` içində `Database.getInstance()` çağırırsa, testdə onu saxta (mock) verilənlər bazası ilə əvəz edə bilmirsiniz.

Spring kimi framework-lərdə bean-lər default olaraq onsuz da tək nüsxədə olur. Orada Singleton-u əllə yazmaq əvəzinə obyekti konstruktor vasitəsilə ötürün (dependency injection).

## Yadda saxla

- `private` konstruktor, `static` nüsxə və `static` giriş metodu.
- Sadə lazy variant thread-safe deyil.
- Ən etibarlı variant `enum`-dur.
- Singleton qlobal vəziyyətdir, ona görə ehtiyatla istifadə edin.

## Tapşırıq

1. `Main`-də `LazySingleton.getInstance()`-i 100 thread-dən çağırın. `System.identityHashCode()` ilə neçə fərqli obyekt yarandığını sayın. Bir neçə dəfə işə salın.
2. Eyni testi `BillPughSingleton` ilə təkrarlayın.
3. `EnumSingleton.INSTANCE.incrementAndGet()` metodunu müxtəlif siniflərdən çağırın və sayğacın ortaq olduğunu yoxlayın.

---

[← Giriş: Design pattern nədir?](00-giris.md) · [Mündəricat](README.md) · Növbəti: [2. Factory Method →](02-factory-method.md)
