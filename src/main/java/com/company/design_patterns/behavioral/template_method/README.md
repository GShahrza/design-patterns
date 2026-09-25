# Template Method

**Kateqoriya:** Behavioral

> Alqoritmin skeletini üst sinifdə təyin edir, bəzi addımları isə alt siniflərə buraxır — skeleti dəyişmədən.

## Problem

CSV və HTML hesabatları eyni addımlardan ibarətdir: filtrlə → başlıq → sətirlər → cəm. Hər formatda bu axını təkrar yazsaq, kod dublikat olur və axını dəyişmək üçün hər yerə toxunmaq lazımdır.

## Həll

`ReportGenerator.generate()` `final` metoddur — axını müəyyən edir. `header()`, `row()`, `footer()` abstraktdır (alt sinif mütləq yazmalıdır). `filter()` isə **hook**-dur: default davranışı var, `HtmlReport` onu override edib yalnız bir regionu göstərir.

## Struktur

| Rol | Bu nümunədə |
|---|---|
| Abstract class (template method) | `ReportGenerator.generate()` |
| Primitive operations | `header(), row(), footer()` |
| Hook | `filter()` |
| Concrete classes | `CsvReport, HtmlReport` |

## Nə vaxt istifadə etməli

- Bir neçə sinif eyni alqoritmi yalnız bəzi addımlarda fərqli icra edirsə.

## Üstünlüklər

- Kod dublikatı aradan qalxır
- Axın bir yerdə idarə olunur

## Çatışmazlıqlar

- Varisliyə əsaslanır (kompozisiyadan az çevik)
- Liskov prinsipini pozmaq asandır

## Java / real həyatda

- `java.io.InputStream.read(byte[])` → `read()`
- `java.util.AbstractList`
- Spring `JdbcTemplate`, `AbstractController`

## İşə salmaq

```bash
mvn -q compile
java -cp target/classes com.company.design_patterns.behavioral.template_method.Main
```
