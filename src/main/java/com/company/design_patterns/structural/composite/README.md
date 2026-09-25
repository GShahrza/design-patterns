# Composite

**Kateqoriya:** Structural

> Obyektləri ağac strukturunda birləşdirir və client-ə tək obyektlə də, qrupla da **eyni cür** işləməyə imkan verir.

## Problem

Fayl sistemində qovluğun ölçüsü içindəki fayl və alt-qovluqların ölçüsünün cəmidir. Client hər yerdə `if (node instanceof Directory) ... else ...` yazmalı olsa, kod mürəkkəbləşir.

## Həll

`FileSystemNode` ümumi interfeysdir. `File` (leaf) öz ölçüsünü qaytarır, `Directory` (composite) isə `getSize()`-i rekursiv olaraq övladlarına ötürüb cəmləyir. Client sadəcə `root.getSize()` çağırır.

## Struktur

| Rol | Bu nümunədə |
|---|---|
| Component | `FileSystemNode` |
| Leaf | `File` |
| Composite | `Directory` |

## Nə vaxt istifadə etməli

- Ağac strukturu (menyu, təşkilat strukturu, UI komponentləri, fayl sistemi).
- Client-in tək element ilə qrup arasındakı fərqi bilməməsi istənirsə.

## Üstünlüklər

- Rekursiv strukturlarla sadə iş
- Yeni element növü əlavə etmək asandır

## Çatışmazlıqlar

- Ümumi interfeys bəzən çox ümumi olur (məs. leaf üçün `add()` mənasızdır)

## Java / real həyatda

- `java.awt.Container` / `Component`
- `javax.swing.JComponent`

## İşə salmaq

```bash
mvn -q compile
java -cp target/classes com.company.design_patterns.structural.composite.Main
```
