# Abstract Factory

**Kateqoriya:** Creational

> Konkret siniflərini göstərmədən bir-biri ilə **əlaqəli obyektlər ailəsini** yaratmağa imkan verir.

## Problem

UI-da Light və Dark tema var. Hər temanın öz Button və Checkbox-u olmalıdır. Əgər client hər komponenti ayrıca `new` ilə yaratsa, səhvən `DarkButton` + `LightCheckbox` kimi uyğunsuz qarışıq yarana bilər.

## Həll

`UiFactory` interfeysi ailənin bütün üzvlərini yaradan metodlara malikdir (`createButton`, `createCheckbox`). Hər tema üçün bir factory (`LightThemeFactory`, `DarkThemeFactory`). Client (`SettingsDialog`) bir factory alır və bütün komponentlər avtomatik uyğun olur.

Paketdə iki nümunə var:
- `gui/` — **əsas nümunə**: məhsul ailəsi (Button + Checkbox).
- kök paket (`factory/`, `model/`) — köhnə nümunə. Burada factory yalnız bir növ məhsul (`Shape`) qaytarır, ona görə bu əslində Abstract Factory-dən çox "iki Simple Factory"-yə bənzəyir. Müqayisə üçün saxlanılıb.

## Struktur

| Rol | Bu nümunədə |
|---|---|
| Abstract factory | `UiFactory` |
| Concrete factories | `LightThemeFactory, DarkThemeFactory` |
| Abstract products | `Button, Checkbox` |
| Concrete products | `LightButton, DarkButton, LightCheckbox, DarkCheckbox` |
| Client | `SettingsDialog` |

## Nə vaxt istifadə etməli

- Sistem bir neçə məhsul ailəsindən biri ilə işləməlidirsə (tema, OS, verilənlər bazası driver-i).
- Ailə üzvlərinin birlikdə istifadəsinə zəmanət lazımdırsa.

## Üstünlüklər

- Məhsulların uyğunluğu zəmanətlidir
- Client konkret siniflərdən asılı deyil
- Ailəni dəyişmək = bir sətir (factory-ni dəyişmək)

## Çatışmazlıqlar

- Ailəyə yeni **məhsul növü** (məs. `Slider`) əlavə etmək bütün factory-ləri dəyişdirir

## Java / real həyatda

- `javax.xml.parsers.DocumentBuilderFactory`
- `javax.xml.transform.TransformerFactory`

## İşə salmaq

```bash
mvn -q compile
java -cp target/classes com.company.design_patterns.creational.abstract_factory.Main
java -cp target/classes com.company.design_patterns.creational.abstract_factory.gui.Main
```
