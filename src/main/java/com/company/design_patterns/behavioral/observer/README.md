# Observer

**Kateqoriya:** Behavioral

> Bir obyektin vəziyyəti dəyişəndə ondan asılı bütün obyektlərin avtomatik xəbərdar olunduğu abunəlik mexanizmi.

## Problem

Birjada qiymət dəyişəndə bir neçə komponent (logger, alert, portfel) reaksiya verməlidir. `StockExchange` onların hamısını birbaşa çağırsa, hər yeni komponent üçün birjanın kodunu dəyişmək lazımdır.

## Həll

`StockExchange` (subject) `StockObserver` siyahısı saxlayır; `subscribe/unsubscribe` metodları var. Qiymət dəyişəndə siyahıdakı hər kəsə `onPriceChanged()` göndərir. Subject konkret observer siniflərini tanımır. `StockObserver` funksional interfeys olduğu üçün lambda ilə də abunə olmaq olar.

## Struktur

| Rol | Bu nümunədə |
|---|---|
| Subject | `StockExchange` |
| Observer | `StockObserver` |
| Concrete observers | `PriceAlert, Portfolio, lambda logger` |

## Nə vaxt istifadə etməli

- Bir obyektin dəyişikliyi başqalarına təsir edir və onların sayı/tipi əvvəlcədən məlum deyil.
- Event-driven sistemlər, UI hadisələri.

## Üstünlüklər

- Open/Closed: yeni observer subject-i dəyişmədən əlavə olunur
- Runtime-da abunə olmaq/çıxmaq

## Çatışmazlıqlar

- Bildiriş sırası zəmanətli deyil
- Abunəlikdən çıxmağı unutmaq memory leak yarada bilər

## Java / real həyatda

- `java.beans.PropertyChangeListener`
- Swing `ActionListener`
- Spring `ApplicationEvent`, `java.util.concurrent.Flow`

## İşə salmaq

```bash
mvn -q compile
java -cp target/classes com.company.design_patterns.behavioral.observer.Main
```
