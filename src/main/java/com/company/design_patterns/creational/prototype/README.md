# Prototype

**Kateqoriya:** Creational

> Yeni obyektləri mövcud obyektin (prototipin) **klonlanması** ilə yaradır.

## Problem

Obyekt yaratmaq bahalıdırsa (məs. məlumat bazadan yüklənir) və ya konkret sinfi bilmədən obyektin surətini almaq lazımdırsa.

## Həll

`Shape` `Cloneable`-ı implement edir və `clone()` metodunu təqdim edir. `ShapeCache` (prototype registry) hazır prototipləri bir dəfə yükləyir və hər sorğuda **klon** qaytarır — beləliklə client keşdəki orijinalı dəyişə bilmir.

> **Shallow vs deep copy:** `super.clone()` yalnız sahələrin istinadını kopyalayır. Əgər obyektin dəyişən (mutable) sahələri (List, Date və s.) varsa, onları da əl ilə kopyalamaq lazımdır. Praktikada çox vaxt `Cloneable` əvəzinə copy-constructor (`new Circle(other)`) tövsiyə olunur.

## Struktur

| Rol | Bu nümunədə |
|---|---|
| Prototype | `Shape` |
| Concrete prototypes | `Circle, Square, Rectangle` |
| Registry | `ShapeCache` |

## Nə vaxt istifadə etməli

- Obyekt yaratmaq bahalıdır, amma kopyalamaq ucuzdur.
- Runtime-da hansı sinfin lazım olduğunu bilmədən obyektin surəti lazımdır.

## Üstünlüklər

- Bahalı inisializasiyanı təkrarlamır
- Konkret sinifdən asılılıq yoxdur

## Çatışmazlıqlar

- Dairəvi istinadları olan obyektləri klonlamaq çətindir
- `Cloneable` Java-da problemli dizayna malikdir

## Java / real həyatda

- `Object.clone()`
- `java.util.ArrayList.clone()`

## İşə salmaq

```bash
mvn -q compile
java -cp target/classes com.company.design_patterns.creational.prototype.Main
```
