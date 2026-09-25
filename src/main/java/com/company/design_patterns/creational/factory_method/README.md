# Factory Method

**Kateqoriya:** Creational

> Obyekt yaratmaq üçün interfeys (metod) təyin edir, amma **hansı** sinfin yaradılacağına alt siniflər qərar verir.

## Problem

`NotificationService` sifariş göndərildikdə istifadəçini xəbərdar etməlidir. Əgər o `new EmailNotifier()` yazsa, SMS və ya Telegram əlavə etmək üçün servisin özünü dəyişmək lazım gələcək.

## Həll

Əsas sinif (creator) `createNotifier()` adlı abstrakt metod təyin edir və biznes məntiqini (`notifyUser`) bu metod üzərində qurur. Alt siniflər (`EmailNotificationService`, `SmsNotificationService`) yalnız hansı notifier-in yaradılacağını müəyyən edir. Yeni kanal = yeni alt sinif, köhnə kod dəyişmir.

## Struktur

| Rol | Bu nümunədə |
|---|---|
| Product | `Notifier` |
| Concrete products | `EmailNotifier, SmsNotifier` |
| Creator (factory method) | `NotificationService.createNotifier()` |
| Concrete creators | `EmailNotificationService, SmsNotificationService` |

## Nə vaxt istifadə etməli

- Sinif əvvəlcədən hansı obyektlərlə işləyəcəyini bilmirsə.
- Framework/kitabxana yazırsınız və istifadəçilərə daxili komponentləri genişləndirmək imkanı vermək istəyirsiniz.

## Üstünlüklər

- Open/Closed: yeni məhsul növü köhnə kodu dəyişmədən əlavə olunur
- Yaratma və istifadə bir-birindən ayrılır

## Çatışmazlıqlar

- Hər məhsul üçün yeni creator alt sinfi — siniflərin sayı artır

## Java / real həyatda

- `java.util.Collection.iterator()` — hər kolleksiya öz iterator-unu yaradır
- `java.util.concurrent.ThreadFactory.newThread()`

## İşə salmaq

```bash
mvn -q compile
java -cp target/classes com.company.design_patterns.creational.factory_method.Main
```
