# 6. Adapter

[← 5. Prototype](05-prototype.md) · [Mündəricat](README.md) · Növbəti: [7. Bridge →](07-bridge.md)

**Qrup:** Structural · **Kod:** [`structural/adapter`](../src/main/java/com/company/design_patterns/structural/adapter)

---

## Həyatdan analogiya

Avropadan aldığınız noutbukun ştepseli iki yumru çubuqludur, Britaniyadakı otelin rozetkası isə üç düzbucaqlı dəlikli. Noutbuku sındırıb yenisini almırsınız, rozetkanı da sökmürsünüz. Araya kiçik bir **adapter** taxırsınız: bir tərəfi rozetkaya uyğundur, o biri tərəfi ştepselə.

## Problem

Sistemimiz şifrələmə üçün öz interfeysimizi istifadə edir:

```java
public interface Crypt {
    void encrypt(String text);
    void decrypt(String text);
}
```

`CryptA` və `CryptB` bunu implement edir, hər şey qaydasındadır. Sonra rəhbərlik deyir: "CodeX adlı xarici kitabxananı istifadə edək, o daha güclüdür". Amma CodeX-in metodları başqa cür adlanır:

```java
public class CodeX {                      // bu kod bizim deyil, dəyişə bilmərik
    public void textToCode(String text) { ... }
    public void codeToText(String text) { ... }
}
```

`Crypt crypt = new CodeX();` kompilyasiya olunmur. Sistemin `Crypt` gözləyən bütün yerlərini dəyişmək də istəmirik.

## Həll addım-addım

**Addım 1.** Hədəf interfeysi (`Crypt`) implement edən yeni sinif yaradırıq.

**Addım 2.** İçində uyğunlaşdırılacaq obyekti (`CodeX`, *adaptee*) saxlayırıq.

**Addım 3.** Hər metodda çağırışı uyğun metoda "tərcümə" edirik:

```java
public class CodeXAdapter implements Crypt {

    private final CodeX codeX;

    public CodeXAdapter(CodeX codeX) {
        this.codeX = codeX;
    }

    @Override
    public void encrypt(String text) {
        codeX.textToCode(text);     // encrypt → textToCode
    }

    @Override
    public void decrypt(String text) {
        codeX.codeToText(text);     // decrypt → codeToText
    }
}
```

**İstifadə:**

```java
Crypt crypt = new CodeXAdapter(new CodeX());
crypt.encrypt("Ahmet");   // sistem fərqi bilmir
```

Sistemin qalan hissəsi yalnız `Crypt`-i tanıyır və heç nə dəyişmədi.

## Real adapter-lər çox vaxt daha çox iş görür

Bu nümunədə adapter sadəcə metod adlarını dəyişir. Real layihədə adapter adətən bunları da edir:
- **tipləri çevirir**, məsələn `double` manat → `long` qəpik;
- **parametrlərin sırasını** dəyişir;
- xarici kitabxananın exception-larını öz exception-larınıza çevirir.

Adapter bütün bu "tərcümə" məntiqini bir yerdə saxlayır və o xarici kodun sistemə yayılmasına imkan vermir.

## Object adapter və class adapter

Bizim nümunə **object adapter**-dir: `CodeX`-i içində saxlayır (kompozisiya). Alternativ **class adapter**-dir:

```java
class CodeXAdapter extends CodeX implements Crypt { ... }
```

Java-da bir sinifdən yalnız bir sinfi extend etmək olar, ona görə object adapter daha çevikdir və adətən o seçilir.

## JDK-da

- `Arrays.asList(array)` massivi `List` interfeysinə uyğunlaşdırır.
- `InputStreamReader` byte axınını (`InputStream`) simvol axınına (`Reader`) çevirir.

## Yadda saxla

- Adapter mövcud kodu **dəyişmədən** yeni interfeysə uyğunlaşdırır.
- Xarici və ya köhnə (legacy) kodu sistemə qoşmaq üçün idealdır.
- Kompozisiya ilə (object adapter) yazmaq daha yaxşıdır.

## Tapşırıq

Xarici bir ödəniş kitabxanası təsəvvür edin: `LegacyBank.pay(long amountInCents, String currencyCode)`. Sizin interfeysiniz isə `PaymentGateway.charge(BigDecimal amountAzn)`-dir. `LegacyBankAdapter` yazın və manatı qəpiyə çevirməyi unutmayın.

---

[← 5. Prototype](05-prototype.md) · [Mündəricat](README.md) · Növbəti: [7. Bridge →](07-bridge.md)
