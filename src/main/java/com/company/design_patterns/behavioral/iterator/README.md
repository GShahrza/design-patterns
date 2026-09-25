# Iterator

**Kateqoriya:** Behavioral

> Kolleksiyanın daxili strukturunu açmadan onun elementlərini ardıcıl gəzməyə imkan verir.

## Problem

Playlist mahnıları list-də saxlayır. Client-in `songs.get(i)` ilə birbaşa list-ə girişi olsa, daxili struktur dəyişdikdə client də dəyişməlidir. Həmçinin fərqli keçid üsulları (tərsinə, qarışıq) lazımdır.

## Həll

`Playlist` `Iterable<Song>` implement edir və müxtəlif iterator-lar qaytarır: `SequentialIterator`, `ReverseIterator`, shuffle. Keçidin vəziyyəti (index) iterator-da saxlanılır, kolleksiyada yox — ona görə bir kolleksiya üzərində eyni anda bir neçə keçid mümkündür.

## Struktur

| Rol | Bu nümunədə |
|---|---|
| Iterator | `java.util.Iterator` |
| Concrete iterators | `SequentialIterator, ReverseIterator` |
| Aggregate | `Iterable / Playlist` |

## Nə vaxt istifadə etməli

- Mürəkkəb strukturun (ağac, qraf, səhifələnmiş API) daxilini gizlətmək istəyirsinizsə.
- Eyni kolleksiya üçün bir neçə keçid üsulu lazımdırsa.

## Üstünlüklər

- Keçid məntiqi ayrı sinifdə (SRP)
- for-each ilə işləyir

## Çatışmazlıqlar

- Sadə list üçün artıq ola bilər

## Java / real həyatda

- `java.util.Iterator`, `Iterable` — bütün Collection-lar
- `java.util.Scanner`

## İşə salmaq

```bash
mvn -q compile
java -cp target/classes com.company.design_patterns.behavioral.iterator.Main
```
