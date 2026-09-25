# 2. Factory Method

[← 1. Singleton](01-singleton.md) · [Mündəricat](README.md) · Növbəti: [3. Abstract Factory →](03-abstract-factory.md)

**Qrup:** Creational · **Kod:** [`creational/factory_method`](../src/main/java/com/company/design_patterns/creational/factory_method), müqayisə üçün [`creational/factory`](../src/main/java/com/company/design_patterns/creational/factory)

---

## Həyatdan analogiya

Bir logistika şirkətinin baş ofisi "yükü çatdır" qaydasını yazır: yükü qəbul et, qablaşdır, göndər, müştəriyə xəbər ver. Amma **nə ilə** göndəriləcəyini hər filial özü seçir. Bakı filialı yük maşını, Sumqayıt filialı qatar, Lənkəran filialı gəmi istifadə edir. Qayda eynidir, nəqliyyat vasitəsini isə filial "yaradır".

## Problem

Sifariş göndəriləndə müştəriyə bildiriş getməlidir:

```java
public class NotificationService {
    public void notifyUser(String recipient, String orderId) {
        EmailNotifier notifier = new EmailNotifier();   // bərk bağlılıq!
        notifier.send(recipient, "Your order " + orderId + " has been shipped");
    }
}
```

Sabah SMS lazım olur. Sonra Telegram. Hər dəfə `NotificationService`-in içinə girib `if/else` əlavə etməli olursunuz. Servis bütün bildiriş növlərini tanımağa məcbur qalır.

## Həll addım-addım

**Addım 1.** Bütün bildirişlər üçün ümumi interfeys (*Product*) yaradırıq:

```java
public interface Notifier {
    void send(String recipient, String message);
}
```

**Addım 2.** Konkret məhsulları yazırıq: `EmailNotifier`, `SmsNotifier`.

**Addım 3.** Əsas sinifdə (*Creator*) `new` əvəzinə **abstrakt metod** çağırırıq. Bu metod elə *factory method*-un özüdür:

```java
public abstract class NotificationService {

    protected abstract Notifier createNotifier();   // ← factory method

    public void notifyUser(String recipient, String orderId) {
        Notifier notifier = createNotifier();       // hansı? alt sinif bilir
        notifier.send(recipient, "Your order " + orderId + " has been shipped and will arrive soon");
    }
}
```

`notifyUser` bir dəfə yazılıb və bir daha dəyişməyəcək. O, hansı notifier-lə işlədiyini bilmir, yalnız interfeysi tanıyır.

**Addım 4.** Alt siniflər ancaq bir işi görür: hansı məhsulun yaradılacağını seçir.

```java
public class SmsNotificationService extends NotificationService {
    @Override
    protected Notifier createNotifier() {
        return new SmsNotifier();
    }
}
```

Telegram lazım olsa, `TelegramNotifier` və `TelegramNotificationService` əlavə edirsiniz. Köhnə siniflərin heç birinə toxunmursunuz.

## Simple Factory ilə fərq

Repoda `creational/factory` paketində başqa bir yanaşma var:

```java
public static FileExporter getInstance(FileType fileType) {
    switch (fileType) {
        case EXCEL: return new ExcelExporter();
        case PDF:   return new PdfExporter();
        default: throw new UnsupportedOperationException();
    }
}
```

Bu **Simple Factory**-dir. Faydalıdır, amma GoF pattern-i deyil. Fərq:

| | Simple Factory | Factory Method |
|---|---|---|
| Harada qərar verilir? | Bir `switch`-də | Alt siniflərdə (polimorfizm) |
| Yeni növ əlavə etmək | `switch`-i redaktə etmək | Yeni alt sinif yazmaq |
| Open/Closed | Pozulur | Qorunur |
| Sadəlik | Çox sadə | Bir az çox sinif |

Kiçik layihədə Simple Factory tamamilə normaldır. Kitabxana və ya framework yazırsınızsa və istifadəçilər öz növlərini əlavə etməlidirsə, Factory Method seçin.

## Əvvəl və sonra

```
ƏVVƏL:  NotificationService ──new──► EmailNotifier
                            ──new──► SmsNotifier        (hamısını tanıyır)

SONRA:  NotificationService ──uses──► «Notifier»
            ▲                               ▲
   SmsNotificationService ─creates─► SmsNotifier
   EmailNotificationService ─creates─► EmailNotifier
```

## JDK-da

`Collection.iterator()` klassik nümunədir. `ArrayList` bir növ iterator, `HashSet` başqa növ iterator yaradır, amma siz hər ikisini eyni `Iterator` kimi istifadə edirsiniz.

## Yadda saxla

- Əsas sinif alqoritmi yazır, `new` isə alt sinifə həvalə olunur.
- Client yalnız interfeysi (`Notifier`) tanıyır.
- Simple Factory bir `switch`-dir, Factory Method isə polimorfizmdir.

## Tapşırıq

1. `TelegramNotifier` və `TelegramNotificationService` əlavə edin. Köhnə fayllardan heç birini dəyişmədən `Main`-dən istifadə edin.
2. `NotificationService`-ə `notifyUser`-dən başqa `remindUser` metodu əlavə edin. Bütün kanalların onu avtomatik "qazandığını" görün.

---

[← 1. Singleton](01-singleton.md) · [Mündəricat](README.md) · Növbəti: [3. Abstract Factory →](03-abstract-factory.md)
