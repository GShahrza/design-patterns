# 4. Builder

[← 3. Abstract Factory](03-abstract-factory.md) · [Mündəricat](README.md) · Növbəti: [5. Prototype →](05-prototype.md)

**Qrup:** Creational · **Kod:** [`creational/builder`](../src/main/java/com/company/design_patterns/creational/builder)

---

## Həyatdan analogiya

Burger restoranında sifariş verirsiniz: "Çörək kunjutlu olsun, iki kotlet, pendir, soğan olmasın, sous əlavə". Aşpaz burgeri addım-addım yığır, sonda sizə hazır məhsul verir. Heç kim sizdən 15 parametri düzgün sırayla bir nəfəsdə deməyi tələb etmir.

## Problem

`Product` sinfinin 6 sahəsi var. İlk ağlagələn həll böyük konstruktordur:

```java
new Product(2L, "Example", null, true, "Worked!", null);
```

Bu sətri oxuyun. `true` nədir? Hansı `null` tarixdir, hansı qiymətdir? Parametrlərin yerini səhvən dəyişsəniz (eyni tipli iki `String`), kompilyator heç nə deməyəcək. Buna **telescoping constructor** problemi deyilir.

İkinci həll setter-lərdir:

```java
Product p = new Product();
p.setId(2L);
p.setName("Example");
// ... name-i unutsaq? p yarımçıq, amma istifadə oluna bilir
```

İndi obyekt **dəyişkəndir** (mutable). Həm də qurulma zamanı yarımçıq vəziyyətdə olur və heç kim məcburi sahələri yoxlamır.

## Həll addım-addım

**Addım 1.** `Product`-u immutable edirik: bütün sahələr `final`, setter yoxdur, konstruktor `private`.

**Addım 2.** İçində statik `ProductBuilder` sinfi yaradırıq. Onun sahələri eynidir, amma hər biri üçün `this` qaytaran metod var:

```java
public ProductBuilder name(String name) {
    this.name = name;
    return this;          // ← zəncir (fluent) üçün
}
```

**Addım 3.** Optional sahələrə default dəyər veririk:

```java
private LocalDateTime date = LocalDateTime.now();
private boolean inStock = true;
private BigDecimal price = BigDecimal.ZERO;
```

**Addım 4.** Validasiyanı bir yerdə, `build()`-də edirik:

```java
public Product build() {
    Objects.requireNonNull(name, "name is required");
    if (price.signum() < 0) {
        throw new IllegalStateException("price must not be negative");
    }
    return new Product(this);
}
```

**Nəticə:**

```java
Product product = Product.builder()
        .id(2L)
        .name("Example")
        .description("Worked!")
        .price(new BigDecimal("19.90"))
        .build();
```

Hər dəyərin yanında adı yazılıb. Sıra önəmli deyil, lazımsız sahələri yazmırsınız. Səhv vəziyyətdə olan obyekt heç vaxt yaranmır.

## Əvvəl və sonra

| | Böyük konstruktor | Setter-lər | Builder |
|---|---|---|---|
| Oxunaqlılıq | Pis | Yaxşı | Yaxşı |
| Immutable | Bəli | Xeyr | Bəli |
| Validasiya | Konstruktorda | Heç yerdə | `build()`-də |
| Optional parametrlər | Çoxlu konstruktor | Asan | Asan |

## Praktikada

Real layihələrdə builder-i əllə nadir hallarda yazırlar. Lombok-un `@Builder` annotasiyası eyni kodu avtomatik yaradır. Amma onun arxasında nə olduğunu bilmək vacibdir, çünki müsahibələrdə tez-tez soruşulur.

## JDK-da

- `StringBuilder`: mətni addım-addım yığırsınız, sonda `toString()`.
- `HttpRequest.newBuilder().uri(...).header(...).GET().build()`.
- `Stream.builder()`.

## Yadda saxla

- Builder çoxlu (xüsusən optional) parametr üçündür.
- Hər setter `this` qaytarır, `build()` isə yoxlayıb obyekti yaradır.
- Məqsəd oxunaqlı kod və immutable, həmişə düzgün vəziyyətdə olan obyektdir.

## Tapşırıq

1. `Product`-a `List<String> tags` sahəsi əlavə edin və `builder.tag("new").tag("sale")` kimi işləsin. Diqqət: `Product` immutable qalmalıdır, `List.copyOf` istifadə edin.
2. `build()`-ə qayda əlavə edin: `inStock == false` olarsa `price` sıfır olmalıdır.

---

[← 3. Abstract Factory](03-abstract-factory.md) · [Mündəricat](README.md) · Növbəti: [5. Prototype →](05-prototype.md)
