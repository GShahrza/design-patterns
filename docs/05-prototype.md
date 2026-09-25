# 5. Prototype

[← 4. Builder](04-builder.md) · [Mündəricat](README.md) · Növbəti: [6. Adapter →](06-adapter.md)

**Qrup:** Creational · **Kod:** [`creational/prototype`](../src/main/java/com/company/design_patterns/creational/prototype)

---

## Həyatdan analogiya

Rəsmi sənədin surətini çıxarmaq üçün onu yenidən əllə yazmırsınız, surətçıxarma aparatına qoyursunuz. Əsl sənəd arxivdə qalır, siz isə istədiyiniz qədər surət alıb onların üzərində qeydlər edə bilirsiniz. Orijinal toxunulmaz qalır.

## Problem

Qrafik redaktorda fiqurların hazır şablonları var. Onların parametrləri məlumat bazasından yüklənir və bu yavaşdır. İstifadəçi hər dəfə "dairə əlavə et" deyəndə bazaya getmək istəmirik.

İkinci problem: bəzən əlinizdə bir `Shape` obyekti var, amma onun konkret sinfini (`Circle`? `Square`?) bilmirsiniz, çünki yalnız interfeysi tanıyırsınız. `new ???()` yazmaq mümkün deyil. Obyekt özünü kopyalamağı bacarmalıdır.

## Həll addım-addım

**Addım 1.** Əsas sinif `Cloneable` interfeysini implement edir və `clone()` metodunu açıq edir:

```java
public abstract class Shape implements Cloneable {

    @Override
    public Shape clone() {                    // qaytarılan tip Shape-dir, Object yox
        try {
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Shape implements Cloneable", e);
        }
    }
}
```

Diqqət edin: qaytarılan tip `Shape`-dir. Buna *covariant return* deyilir və çağıran tərəf cast yazmaq məcburiyyətində qalmır.

**Addım 2.** Prototipləri saxlayan reyestr (*registry*) yaradırıq:

```java
public class ShapeCache {
    private static final Map<String, Shape> SHAPES = new HashMap<>();

    public static void loadCache() {        // real həyatda: bazadan, bir dəfə
        register("1", new Circle());
        register("2", new Square());
        register("3", new Rectangle());
    }

    public static Shape getShape(String shapeId) {
        Shape cachedShape = SHAPES.get(shapeId);
        if (cachedShape == null) {
            throw new IllegalArgumentException("Unknown shape id: " + shapeId);
        }
        return cachedShape.clone();          // ← orijinalı yox, surəti veririk
    }
}
```

Surət verməyimizin səbəbi var. Orijinalı versəydik, kimsə onu dəyişəndə keşdəki şablon da korlanardı.

## Vacib incəlik: shallow və deep copy

`super.clone()` **shallow copy** edir: sahələrin dəyərlərini kopyalayır. Sahə obyekt istinadıdırsa, **istinadı** kopyalayır, obyektin özünü yox.

```java
class Drawing implements Cloneable {
    List<Shape> shapes = new ArrayList<>();
}

Drawing copy = original.clone();
copy.shapes.add(new Circle());   // original.shapes-ə də əlavə olundu!
```

Hər iki obyekt eyni `List`-ə baxır. Düzgün həll **deep copy**-dir:

```java
@Override
public Drawing clone() {
    Drawing copy = (Drawing) super.clone();
    copy.shapes = new ArrayList<>(this.shapes);   // öz siyahısı
    return copy;
}
```

Bizim `Shape` sinfində yalnız `String` sahələr var. `String` dəyişməz olduğu üçün shallow copy kifayətdir.

## Praktik məsləhət

Java-nın `Cloneable` mexanizmi köhnə və problemli sayılır: interfeysdə metod yoxdur, exception yoxlanılandır (checked). Bir çox komanda əvəzində **copy constructor** yazır:

```java
public Circle(Circle other) {
    this.id = other.id;
    this.radius = other.radius;
}
```

Fikir eyni qalır: mövcud obyektdən yenisi yaranır.

## Yadda saxla

- Prototype yaradılması bahalı olan obyektləri surətini çıxarmaqla alır.
- Registry hazır şablonları saxlayır və hər dəfə **surət** qaytarır.
- Shallow copy dəyişkən sahələr üçün təhlükəlidir. Lazım olduqda deep copy edin.

## Tapşırıq

1. `Shape`-ə `List<String> tags` sahəsi əlavə edin. Klonun tag-ını dəyişəndə orijinalın da dəyişdiyini görün. Sonra `clone()`-u deep copy edəcək şəkildə düzəldin.
2. `Main`-də `a == b` yoxlaması `false` verir. `a.getId().equals(b.getId())` nə verəcək? Niyə?

---

[← 4. Builder](04-builder.md) · [Mündəricat](README.md) · Növbəti: [6. Adapter →](06-adapter.md)
