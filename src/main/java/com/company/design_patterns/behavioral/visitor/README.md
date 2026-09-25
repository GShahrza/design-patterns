# Visitor

**Kateqoriya:** Behavioral

> Obyekt strukturuna, onun siniflərini dəyişmədən **yeni əməliyyatlar** əlavə etməyə imkan verir.

## Problem

Səbətdə Book, Electronics, Food var. Vergi, çatdırılma qiyməti, eksport kimi əməliyyatları hər sinfə metod kimi əlavə etsək, hər yeni əməliyyat üçün bütün məhsul siniflərini dəyişmək lazımdır.

## Həll

Hər əməliyyat ayrıca `ItemVisitor` sinfidir (`TaxVisitor`, `ShippingCostVisitor`), hər məhsul növü üçün bir `visitXxx` metodu var. Məhsul `accept(visitor)` metodunda `visitor.visitBook(this)` çağırır — bu **double dispatch** adlanır: hansı metodun çağırılacağı həm məhsulun, həm visitor-un tipindən asılıdır.

> Java 21+ da `sealed interface` + `switch` pattern matching bəzən Visitor-un daha sadə alternatividir.

## Struktur

| Rol | Bu nümunədə |
|---|---|
| Visitor | `ItemVisitor<R>` |
| Concrete visitors | `TaxVisitor, ShippingCostVisitor` |
| Element | `Item` |
| Concrete elements | `Book, Electronics, Food` |

## Nə vaxt istifadə etməli

- Element növləri stabil, əməliyyatlar isə tez-tez əlavə olunursa (compiler AST, hesabatlar).

## Üstünlüklər

- Yeni əməliyyat = yeni sinif (OCP)
- Əlaqəli məntiq bir visitor-da cəmlənir

## Çatışmazlıqlar

- Yeni **element** növü əlavə etmək bütün visitor-ları dəyişdirir
- Elementlərin sahələri visitor üçün açıq olmalıdır

## Java / real həyatda

- `java.nio.file.FileVisitor` / `Files.walkFileTree()`
- `javax.lang.model.element.ElementVisitor`

## İşə salmaq

```bash
mvn -q compile
java -cp target/classes com.company.design_patterns.behavioral.visitor.Main
```
