# Decorator

**Kateqoriya:** Structural

> Obyektə dinamik olaraq yeni davranış əlavə edir — onu eyni interfeysli "sarğıya" (wrapper) bükməklə.

## Problem

Telefonun Pro, Max və s. versiyaları var. Hər kombinasiya üçün alt sinif yaratmaq (`Iphone11ProMax extends Iphone11Pro extends Iphone`) çevik deyil, xüsusiyyətləri runtime-da birləşdirmək olmur.

## Həll

Decorator komponentlə eyni interfeysi implement edir və daxilində başqa komponenti saxlayır. Hər decorator çağırışı daxili obyektə ötürür və öz əlavəsini edir. Decorator-ları bir-birinin içinə istənilən sırada bükmək olar: `new Iphone11ProMax(new Iphone11Pro(new Iphone()))`.

- `example1` — telefon: hər qat ad, qiymət və kamera sayını dəyişir.
- `example2` — fiqur: `RedShapeDecorator` çəkilişə qırmızı çərçivə əlavə edir.

## Struktur

| Rol | Bu nümunədə |
|---|---|
| Component | `Phone / Shape` |
| Concrete component | `Iphone / Circle, Rectangle` |
| Base decorator | `PhoneDecorator / ShapeDecorator` |
| Concrete decorators | `Iphone11Pro, Iphone11ProMax / RedShapeDecorator` |

## Nə vaxt istifadə etməli

- Obyektlərə runtime-da, digər obyektlərə təsir etmədən funksionallıq əlavə etmək lazımdırsa.
- Varislik (inheritance) ilə kombinasiyaların sayı çox olursa.

## Üstünlüklər

- Varislikdən daha çevikdir
- Hər decorator bir məsuliyyət daşıyır (SRP)
- Kombinasiyalar runtime-da qurulur

## Çatışmazlıqlar

- Çoxlu kiçik obyekt; debug etmək çətinləşə bilər
- Sıralama bəzən vacibdir

## Java / real həyatda

- `java.io.BufferedInputStream(new FileInputStream(...))`
- `java.util.Collections.unmodifiableList()`, `synchronizedList()`

## İşə salmaq

```bash
mvn -q compile
java -cp target/classes com.company.design_patterns.structural.decorator.example1.Main
java -cp target/classes com.company.design_patterns.structural.decorator.example2.Main
```
