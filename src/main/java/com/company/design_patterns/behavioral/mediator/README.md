# Mediator

**Kateqoriya:** Behavioral

> Obyektlər arasındakı birbaşa əlaqələri azaldır — onları yalnız vasitəçi (mediator) obyekt üzərindən danışmağa məcbur edir.

## Problem

Chat-da hər istifadəçi digər bütün istifadəçiləri tanısa, N istifadəçi üçün N×N əlaqə yaranır. Moderasiya, şəxsi mesaj kimi qaydaları hər istifadəçi sinfinə yazmaq lazım gəlir.

## Həll

`User` yalnız `ChatMediator`-u tanıyır. Mesaj göndərəndə `chat.send()` çağırır; kimə çatacağı, qadağan olunmuş sözlərin yoxlanması, şəxsi mesajlar — hamısı `ChatRoom`-da cəmlənib.

## Struktur

| Rol | Bu nümunədə |
|---|---|
| Mediator | `ChatMediator` |
| Concrete mediator | `ChatRoom` |
| Colleague | `User` |

## Nə vaxt istifadə etməli

- Obyektlər arasında çoxlu və qarışıq əlaqələr varsa (UI formu komponentləri, chat, hava limanı dispetçeri).

## Üstünlüklər

- Loose coupling
- Qarşılıqlı əlaqə məntiqi bir yerdə

## Çatışmazlıqlar

- Mediator özü "god object"-ə çevrilə bilər

## Java / real həyatda

- `java.util.concurrent.Executor`
- `java.util.Timer`
- MVC-də Controller

## İşə salmaq

```bash
mvn -q compile
java -cp target/classes com.company.design_patterns.behavioral.mediator.Main
```
