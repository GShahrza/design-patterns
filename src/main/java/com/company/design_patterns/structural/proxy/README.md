# Proxy

**Kateqoriya:** Structural

> Başqa obyektin əvəzedicisini (nümayəndəsini) təqdim edir və ona girişə nəzarət edir.

## Problem

Real obyektə müraciət bahalı (disk, şəbəkə), təhlükəli (icazə tələb edir) və ya gecikdirilməli ola bilər. Real sinfi dəyişmədən bu nəzarəti əlavə etmək istəyirik.

## Həll

Proxy real obyektlə eyni interfeysi implement edir, client fərqi bilmir. Üç növ proxy göstərilib:

- `example1` — **Virtual proxy**: `ProxyImage` şəkli yalnız ilk `display()` çağırışında yükləyir (lazy loading).
- `example2` — **Caching proxy**: `YoutubeCacheProxy` eyni videonu ikinci dəfə şəbəkədən yükləmir.
- `example3` — **Protection proxy**: `ManagerProxy` dövriyyə məlumatını yalnız menecerlərə verir, digərlərinə `SecurityException` atır.

## Struktur

| Rol | Bu nümunədə |
|---|---|
| Subject | `Image / ThirdPartyYouTubeLib / CompanyInformation` |
| Real subject | `RealImage / ThirdPartyYoutubeClass / TruthManager` |
| Proxy | `ProxyImage / YoutubeCacheProxy / ManagerProxy` |

## Nə vaxt istifadə etməli

- Lazy initialization, keşləmə, giriş nəzarəti, loglama, uzaq obyekt (remote proxy).

## Üstünlüklər

- Client-dən xəbərsiz nəzarət
- Real obyekti dəyişmək lazım deyil

## Çatışmazlıqlar

- Cavab gecikə bilər
- Əlavə sinif

## Java / real həyatda

- `java.lang.reflect.Proxy`
- Spring AOP / `@Transactional`
- Hibernate lazy loading

## İşə salmaq

```bash
mvn -q compile
java -cp target/classes com.company.design_patterns.structural.proxy.example1.Main
java -cp target/classes com.company.design_patterns.structural.proxy.example2.Main
java -cp target/classes com.company.design_patterns.structural.proxy.example3.Main
```
