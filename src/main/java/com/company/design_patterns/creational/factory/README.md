# Simple Factory

**Kateqoriya:** Creational (GoF-a daxil deyil)

> Obyekt yaratma məntiqini bir statik metodda toplayır; client `new` yazmır, sadəcə nə istədiyini deyir.

## Problem

Client kodu hər yerdə `if (type == PDF) new PdfExporter() else ...` yazırsa, yeni format əlavə etdikdə bütün bu yerləri tapıb dəyişmək lazımdır.

## Həll

`FileExporterFactory.getInstance(FileType)` konkret sinfi seçir və `FileExporter` interfeysi qaytarır. Client yalnız interfeysi tanıyır.

> Qeyd: Bu **Factory Method** pattern-i deyil (tez-tez qarışdırılır). Əsl Factory Method üçün `../factory_method` paketinə baxın. Simple Factory-də yeni növ əlavə etmək üçün factory-nin özünü (switch-i) dəyişmək lazımdır.

## Struktur

| Rol | Bu nümunədə |
|---|---|
| Product | `FileExporter` |
| Concrete products | `PdfExporter, ExcelExporter` |
| Factory | `FileExporterFactory` |

## Nə vaxt istifadə etməli

- Yaratma məntiqi sadədirsə və bir yerdə cəmləmək kifayətdirsə.

## Üstünlüklər

- Sadədir
- Client konkret siniflərdən asılı deyil

## Çatışmazlıqlar

- Yeni növ = factory-də dəyişiklik (Open/Closed prinsipi pozulur)

## Java / real həyatda

- `java.util.Calendar.getInstance()`
- `java.nio.charset.Charset.forName()`

## İşə salmaq

```bash
mvn -q compile
java -cp target/classes com.company.design_patterns.creational.factory.Main
```
