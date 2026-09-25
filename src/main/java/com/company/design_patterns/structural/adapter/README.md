# Adapter

**Kateqoriya:** Structural

> Uyğun olmayan interfeysi olan sinfi client-in gözlədiyi interfeysə çevirir.

## Problem

Sistemimiz `Crypt` interfeysi (`encrypt/decrypt`) ilə işləyir. Üçüncü tərəf kitabxanası `CodeX` isə eyni işi `textToCode/codeToText` metodları ilə görür. `CodeX`-i dəyişə bilmərik (kodu bizim deyil).

## Həll

`CodeXAdapter` `Crypt` interfeysini implement edir və çağırışları daxilində saxladığı `CodeX` obyektinə ötürür (object adapter — kompozisiya ilə).

## Struktur

| Rol | Bu nümunədə |
|---|---|
| Target | `Crypt` |
| Adaptee | `CodeX` |
| Adapter | `CodeXAdapter` |
| Client | `Main` |

## Nə vaxt istifadə etməli

- Mövcud sinfi istifadə etmək istəyirsiniz, amma interfeysi uyğun gəlmir.
- Legacy kodu və ya 3rd-party kitabxananı yeni sistemə inteqrasiya edirsiniz.

## Üstünlüklər

- Mövcud kodu dəyişmədən təkrar istifadə
- Konversiya məntiqi ayrı sinifdə

## Çatışmazlıqlar

- Əlavə sinif/qat

## Java / real həyatda

- `java.util.Arrays.asList()`
- `java.io.InputStreamReader` (byte stream → char stream)

## İşə salmaq

```bash
mvn -q compile
java -cp target/classes com.company.design_patterns.structural.adapter.Main
```
