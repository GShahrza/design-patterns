# Bridge

**Kateqoriya:** Structural

> Abstraksiyanı onun implementasiyasından ayırır ki, hər ikisi müstəqil dəyişə bilsin.

## Problem

Mesajların növləri var (adi, təcili) və göndərmə kanalları var (Email, SMS, Telegram). Hər kombinasiya üçün sinif yazsaq: `TextEmailMessage`, `UrgentSmsMessage`, ... — 2 × 3 = 6 sinif, yeni kanal əlavə etdikdə daha 2 sinif. Siniflər **N × M** artır.

## Həll

İerarxiyanı ikiyə bölürük: `Message` (NƏ göndərilir) və `MessageSender` (NECƏ göndərilir). `Message` daxilində `MessageSender`-ə istinad (körpü) saxlayır. İndi siniflər **N + M** artır və istənilən kombinasiya runtime-da qurulur: `new UrgentMessage(new TelegramSender())`.

## Struktur

| Rol | Bu nümunədə |
|---|---|
| Abstraction | `Message` |
| Refined abstractions | `TextMessage, UrgentMessage` |
| Implementor | `MessageSender` |
| Concrete implementors | `EmailSender, SmsSender, TelegramSender` |

## Nə vaxt istifadə etməli

- Sinfin iki (və ya daha çox) müstəqil dəyişən ölçüsü varsa.
- Implementasiyanı runtime-da dəyişmək lazımdırsa.

## Üstünlüklər

- Siniflərin partlayışının qarşısını alır
- Open/Closed: hər iki tərəf ayrıca genişlənir

## Çatışmazlıqlar

- İlk baxışda dizaynı mürəkkəbləşdirir

## Java / real həyatda

- JDBC: `java.sql.DriverManager` + müxtəlif driver-lər
- SLF4J API + Logback/Log4j implementasiyası

## İşə salmaq

```bash
mvn -q compile
java -cp target/classes com.company.design_patterns.structural.bridge.Main
```
