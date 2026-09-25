# 11. Flyweight

[← 10. Facade](10-facade.md) · [Mündəricat](README.md) · Növbəti: [12. Proxy →](12-proxy.md)

**Qrup:** Structural · **Kod:** [`structural/flyweight`](../src/main/java/com/company/design_patterns/structural/flyweight)

---

## Həyatdan analogiya

Kitab çap olunanda "a" hərfi 50 000 dəfə təkrarlanır. Mətbəə hər "a" üçün ayrıca metal hərf tökmür. Bir "a" qəlibi var, o, səhifənin müxtəlif yerlərinə vurulur. Hərfin **forması** ortaqdır, **yeri** isə hər dəfə fərqlidir.

## Problem

Oyunda meşə çəkirik, 100 000 ağac. Sadəlövh yanaşma belədir:

```java
class Tree {
    int x, y;
    String name;       // "Oak"
    String color;      // "green"
    byte[] texture;    // 2 MB şəkil!
}
```

100 000 × 2 MB = **200 GB yaddaş**. Oyun açılmayacaq.

Amma diqqət edin: meşədə cəmi **3 növ** ağac var (palıd, şam, ağcaqayın). 100 000 ağacın hər biri eyni 3 teksturadan birini saxlayır, yəni böyük hissəsi eyni məlumatın nüsxələridir.

## Həll addım-addım

**Addım 1.** Obyektin vəziyyətini ikiyə bölürük:

| | Intrinsic (daxili) | Extrinsic (xarici) |
|---|---|---|
| Nədir? | Çoxlu obyekt arasında **eyni** olan | Hər obyekt üçün **fərqli** olan |
| Nümunə | ad, rəng, tekstura | x, y koordinatları |
| Dəyişirmi? | Xeyr (immutable) | Bəli |

**Addım 2.** Ortaq hissəni ayrıca, dəyişməz sinfə (*Flyweight*) çıxarırıq:

```java
public record TreeType(String name, String color, String texture) {
    public void draw(int x, int y) { ... }   // koordinatlar kənardan gəlir
}
```

**Addım 3.** Ağac artıq yalnız öz koordinatlarını və ortaq tipə **istinadı** saxlayır:

```java
public record Tree(int x, int y, TreeType type) {
    public void draw() {
        type.draw(x, y);
    }
}
```

**Addım 4.** Eyni tipin iki dəfə yaradılmamasını *factory* təmin edir:

```java
public static TreeType get(String name, String color, String texture) {
    String key = name + "|" + color + "|" + texture;
    return TYPES.computeIfAbsent(key, k -> new TreeType(name, color, texture));
}
```

`computeIfAbsent` belə işləyir: açar varsa, köhnə obyekti qaytarır, yoxdursa, yenisini yaradıb yadda saxlayır.

## Nəticə

```
Trees planted: 100000
TreeType objects in memory: 3
```

100 000 kiçik `Tree` obyekti (hər biri iki `int` və bir istinad) və cəmi 3 ağır `TreeType`. 200 GB əvəzinə təxminən 6 MB.

## Niyə flyweight mütləq immutable olmalıdır?

Bir `TreeType` 30 000 ağac tərəfindən paylaşılır. Kimsə `oak.color = "red"` etsə, **30 000 ağac** birdən qırmızı olar. Ona görə flyweight dəyişməz olmalıdır. Nümunədə `record` istifadə olunub, çünki record-lar avtomatik immutable-dır.

## JDK-da, hər gün istifadə etdiyiniz yerdə

```java
Integer a = Integer.valueOf(100);
Integer b = Integer.valueOf(100);
a == b;   // true! -128..127 aralığı keşlənir (flyweight)

Integer c = Integer.valueOf(1000);
Integer d = Integer.valueOf(1000);
c == d;   // false, keşdən kənardır
```

Ona görə `Integer`-ləri həmişə `equals()` ilə müqayisə edin. String pool (`"abc" == "abc"`) da flyweight-dir.

## Yadda saxla

- Flyweight çoxlu oxşar obyekt yaddaşı doldurduqda istifadə olunur.
- Ortaq (intrinsic) və fərdi (extrinsic) vəziyyəti ayırın.
- Ortaq hissə immutable olmalıdır və factory vasitəsilə paylaşılmalıdır.
- Obyekt azdırsa, bu pattern lazımsız mürəkkəblikdir.

## Tapşırıq

1. Flyweight olmadan versiya yazın: `Tree` sinfi `name/color/texture`-ı özündə saxlasın. `Runtime.getRuntime().totalMemory() - freeMemory()` ilə hər iki versiyanın yaddaşını müqayisə edin (teksturanı `new byte[10_000]` edin).
2. `Integer` keşi ilə yuxarıdakı nümunəni özünüz yoxlayın.

---

[← 10. Facade](10-facade.md) · [Mündəricat](README.md) · Növbəti: [12. Proxy →](12-proxy.md)
