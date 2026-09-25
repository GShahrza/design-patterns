# 9. Decorator

[← 8. Composite](08-composite.md) · [Mündəricat](README.md) · Növbəti: [10. Facade →](10-facade.md)

**Qrup:** Structural · **Kod:** [`structural/decorator`](../src/main/java/com/company/design_patterns/structural/decorator)

---

## Həyatdan analogiya

Soyuq gündə əvvəl köynək geyinirsiniz. Üşüyürsünüzsə, üstündən sviter, yağış yağırsa, onun da üstündən plaş geyinirsiniz. Hər qat sizə yeni xüsusiyyət verir (istilik, suya davamlılıq), amma siz yenə **eyni insansınız**. Qatları istədiyiniz sırada geyinib çıxara bilərsiniz.

## Problem

Telefon mağazası üçün kod yazırıq. Baza model var, Pro versiyası (+1 kamera, +300 AZN) və Max versiyası (+ekran, +100 AZN). Varislik ilə:

```
Iphone
└── IphonePro
    └── IphoneProMax
```

Bəs "Max, amma Pro olmayan" model lazım olsa? Və ya əlavə opsiyalar gəlsə: "256GB", "Qızılı rəng"? Hər kombinasiya üçün ayrıca sinif yazmaq lazım gəlir: `IphoneMax256Gold`, `IphonePro256`... Varislik statikdir, xüsusiyyətləri runtime-da birləşdirmək olmur.

## Həll addım-addım

**Addım 1.** Ümumi interfeys (*Component*):

```java
public interface Phone {
    String getName();
    int cameraCount();
    double getPrice();
}
```

**Addım 2.** Baza obyekt (*Concrete component*):

```java
public class Iphone implements Phone {
    public String getName()  { return "iPhone 11"; }
    public int cameraCount() { return 2; }
    public double getPrice() { return 699.99; }
}
```

**Addım 3.** Baza decorator. O da `Phone`-dur, amma içində **başqa bir** `Phone` saxlayır və hər çağırışı ona ötürür:

```java
public abstract class PhoneDecorator implements Phone {
    protected final Phone basicPhone;

    public String getName()  { return basicPhone.getName(); }
    public int cameraCount() { return basicPhone.cameraCount(); }
    public double getPrice() { return basicPhone.getPrice(); }
}
```

**Addım 4.** Konkret decorator-lar yalnız dəyişdirmək istədikləri metodu override edir və nəticəyə **öz əlavəsini** edir:

```java
public class Iphone11Pro extends PhoneDecorator {
    public String getName()  { return super.getName() + " Pro"; }
    public int cameraCount() { return super.cameraCount() + 1; }
    public double getPrice() { return super.getPrice() + 300; }
}
```

**İstifadə:** qatları bir-birinin içinə geyindiririk:

```java
Phone proMax = new Iphone11ProMax(new Iphone11Pro(new Iphone()));
proMax.getName();   // "iPhone 11 Pro Max"
proMax.getPrice();  // 699.99 + 300 + 100 = 1099.99
```

## Çağırış necə axır

```
proMax.getPrice()
  └─► Iphone11ProMax: super.getPrice() + 100
        └─► Iphone11Pro: super.getPrice() + 300
              └─► Iphone: 699.99
              ◄── 699.99
        ◄── 999.99
  ◄── 1099.99
```

Hər qat çağırışı içəriyə ötürür, cavab qayıdanda isə öz əlavəsini edir.

## İkinci nümunə

`example2`-də `RedShapeDecorator` istənilən fiqurun çəkilişinə qırmızı çərçivə əlavə edir. `Circle` və `Rectangle` siniflərinin özü dəyişmir.

## JDK-da, gündəlik istifadə etdiyiniz yerdə

```java
Reader reader = new BufferedReader(          // + buferləmə
                  new InputStreamReader(     // + byte → char
                    new FileInputStream("data.txt")));   // baza
```

Java I/O kitabxanası tamamilə decorator üzərində qurulub. `Collections.unmodifiableList(list)` də bir decorator-dur: siyahıya "dəyişdirilə bilməz" xüsusiyyəti əlavə edir.

## Yadda saxla

- Decorator obyekti **eyni interfeysli** sarğıya bükür.
- Xüsusiyyətlər runtime-da istənilən kombinasiyada əlavə olunur.
- Varislikdən daha çevikdir, amma çoxlu kiçik obyekt yaradır.

## Tapşırıq

1. `Storage256` decorator-u yazın: adın sonuna `" 256GB"` əlavə etsin, qiymətə +150.
2. `new Storage256(new Iphone())` və `new Iphone11Pro(new Storage256(new Iphone()))` üçün adları müqayisə edin. Sıra önəmlidirmi?

---

[← 8. Composite](08-composite.md) · [Mündəricat](README.md) · Növbəti: [10. Facade →](10-facade.md)
