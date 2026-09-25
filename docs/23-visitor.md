# 23. Visitor

[← 22. Template Method](22-template-method.md) · [Mündəricat](README.md) · Növbəti: [Sonsöz: Hansı problemə hansı pattern? →](24-secim-beledcisi.md)

**Qrup:** Behavioral · **Kod:** [`behavioral/visitor`](../src/main/java/com/company/design_patterns/behavioral/visitor)

---

## Həyatdan analogiya

Sığorta agenti bir məhəlləni gəzir. Yaşayış evinə gələndə yanğın sığortası, banka gələndə oğurluq sığortası, fabrikə gələndə istehsalat qəzası sığortası təklif edir. Binalar dəyişmir, onlar sadəcə agenti **qəbul edir**. Sabah vergi müfəttişi eyni məhəlləni gəzəcək və hər binaya öz yanaşmasını tətbiq edəcək. Binaların planını dəyişmək lazım gəlmir.

## Problem

Səbətdə üç növ məhsul var: `Book`, `Electronics`, `Food`. Onlar üzərində əməliyyatlar lazımdır: vergi hesabla, çatdırılma qiymətini hesabla. Sabah isə "XML-ə eksport et", "anbar etiketi çap et" tələb olunacaq.

Hər əməliyyatı məhsul siniflərinə metod kimi əlavə etsək:

```java
record Book(...) {
    BigDecimal tax()      { ... }
    BigDecimal shipping() { ... }
    String toXml()        { ... }
    String label()        { ... }
    // hər yeni tələb → 3 sinfə yeni metod
}
```

Məhsul sinifləri vergi, logistika və XML qaydaları ilə dolur, hər yeni tələb bütün məhsul siniflərini dəyişdirir. Alternativ `instanceof` zənciridir, bu isə yeni məhsul növü əlavə olunanda səssizcə sınır.

## Həll addım-addım

**Addım 1.** Əməliyyatı ayrıca **visitor** sinfinə çıxarırıq. Onun hər element növü üçün ayrıca metodu var:

```java
public interface ItemVisitor<R> {
    R visitBook(Book book);
    R visitElectronics(Electronics electronics);
    R visitFood(Food food);
}
```

**Addım 2.** Hər element bir metod alır: `accept`. Bu metod visitor-a **özünün kim olduğunu** deyir:

```java
public record Book(String title, BigDecimal price) implements Item {
    @Override
    public <R> R accept(ItemVisitor<R> visitor) {
        return visitor.visitBook(this);      // "Mən kitabam"
    }
}
```

**Addım 3.** Hər əməliyyat ayrıca visitor-dur və bütün məntiqi bir yerdə saxlayır:

```java
public class TaxVisitor implements ItemVisitor<BigDecimal> {
    public BigDecimal visitBook(Book book)              { return new BigDecimal("0.00"); }
    public BigDecimal visitElectronics(Electronics e)   { return percent(e.price(), 18); }
    public BigDecimal visitFood(Food food)              { return percent(food.price(), 5); }
}
```

**İstifadə:**

```java
for (Item item : cart) {
    BigDecimal t = item.accept(new TaxVisitor());
    BigDecimal s = item.accept(new ShippingCostVisitor());
}
```

Yeni əməliyyat (məsələn, XML eksportu) = yeni `XmlExportVisitor` sinfi. Məhsul siniflərinə **toxunulmur**.

## "Double dispatch": sehr haradadır?

Java metodun hansı versiyasının çağırılacağını adətən **bir** obyektin tipinə görə seçir (`item.accept(...)` üçün `item`-in real tipi). Bizə isə **iki** tipdən asılı seçim lazımdır: həm məhsul (Book? Food?), həm əməliyyat (Tax? Shipping?).

Visitor bunu iki addımda edir:
1. `item.accept(taxVisitor)`: Java `item`-in real tipinə görə `Book.accept`-i seçir.
2. `Book.accept` içində `visitor.visitBook(this)`: Java visitor-un real tipinə görə `TaxVisitor.visitBook`-u seçir.

Nəticədə iki "dispatch" olur və düzgün kombinasiya tapılır.

## Pattern-in trade-off-u

| Əlavə etmək istədiyiniz | Asanlıq |
|---|---|
| Yeni **əməliyyat** (visitor) | Asan: bir yeni sinif |
| Yeni **element** növü (məs. `Clothing`) | Çətin: interfeysə `visitClothing` əlavə olunur və **bütün** visitor-lar dəyişir |

Ona görə Visitor element növləri **stabil**, əməliyyatlar isə tez-tez əlavə olunan sistemlər üçündür. Klassik nümunə kompilyatorlardır: dilin sintaksis növləri nadir hallarda dəyişir, amma onların üzərində onlarla analiz aparılır.

## Müasir Java: alternativ

Java 21-də `sealed` interfeys və `switch` pattern matching çox vaxt eyni problemi daha az kodla həll edir:

```java
sealed interface Item permits Book, Electronics, Food {}

BigDecimal tax(Item item) {
    return switch (item) {
        case Book b        -> BigDecimal.ZERO;
        case Electronics e -> percent(e.price(), 18);
        case Food f        -> percent(f.price(), 5);
    };   // yeni növ əlavə olunsa, kompilyator xəta verir
}
```

Visitor-u bilmək yenə də vacibdir: köhnə kod bazalarında, framework-lərdə və müsahibələrdə hər yerdə qarşınıza çıxacaq.

## Real həyatda

- `Files.walkFileTree(path, FileVisitor)`: fayl ağacını gəzərkən hər fayl və qovluq üçün sizin visitor-unuz çağırılır.
- Kompilyatorlar və statik analiz alətləri (javac, ESLint, SonarQube) AST üzərində visitor-lar işlədir.

## Yadda saxla

- Visitor siniflərə toxunmadan onlara **yeni əməliyyat** əlavə edir.
- `accept`/`visit` cütü double dispatch yaradır.
- Yeni əməliyyat əlavə etmək asandır, yeni element növü əlavə etmək çətindir.
- Java 21+ versiyada `sealed` + `switch` çox vaxt daha sadə alternativdir.

## Tapşırıq

1. `LabelVisitor implements ItemVisitor<String>` yazın: hər məhsul üçün anbar etiketi qaytarsın, məsələn `"FOOD | Ice cream | KEEP COLD"`.
2. `Clothing` məhsul növünü əlavə edin. Neçə faylı dəyişmək lazım gəldi? Bu, Visitor-un zəif tərəfini göstərir.

---

[← 22. Template Method](22-template-method.md) · [Mündəricat](README.md) · Növbəti: [Sonsöz: Hansı problemə hansı pattern? →](24-secim-beledcisi.md)
