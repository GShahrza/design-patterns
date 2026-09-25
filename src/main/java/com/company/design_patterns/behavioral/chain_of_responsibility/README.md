# Chain of Responsibility

**Kateqoriya:** Behavioral

> Sorğunu handler-lər zənciri boyunca ötürür; hər handler ya sorğunu emal edir, ya da növbətiyə ötürür.

## Problem

HTTP sorğusu controller-ə çatmazdan əvvəl bir neçə yoxlamadan keçməlidir: rate limit, autentifikasiya, avtorizasiya. Hamısını bir metodda `if`-lərlə yazsaq, metod böyüyür, yoxlamaları əlavə etmək/sırasını dəyişmək çətinləşir.

## Həll

Hər yoxlama ayrıca `Handler` sinfidir. Handler-lər `linkWith()` ilə zəncirə bağlanır. Handler yoxlamadan keçməsə zənciri dayandırır (`false`), keçsə `handleNext()` çağırır.

## Struktur

| Rol | Bu nümunədə |
|---|---|
| Handler | `Handler` |
| Concrete handlers | `RateLimitHandler, AuthenticationHandler, AuthorizationHandler` |
| Request | `Request` |

## Nə vaxt istifadə etməli

- Sorğunu bir neçə obyekt emal edə bilər və ardıcıllıq dəyişə bilər.
- Middleware, filter, support ticket eskalasiyası.

## Üstünlüklər

- Göndərən və qəbul edən ayrılır
- Handler-ləri əlavə etmək/sıralamaq asandır (SRP, OCP)

## Çatışmazlıqlar

- Sorğunun heç kim tərəfindən emal olunmaması mümkündür
- Debug çətinləşə bilər

## Java / real həyatda

- `javax.servlet.Filter` / `FilterChain`
- Spring Security filter chain
- `java.util.logging.Logger` (parent handler-lər)

## İşə salmaq

```bash
mvn -q compile
java -cp target/classes com.company.design_patterns.behavioral.chain_of_responsibility.Main
```
