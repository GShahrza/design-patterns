# 12. Proxy

[← 11. Flyweight](11-flyweight.md) · [Mündəricat](README.md) · Növbəti: [13. Chain of Responsibility →](13-chain-of-responsibility.md)

**Qrup:** Structural · **Kod:** [`structural/proxy`](../src/main/java/com/company/design_patterns/structural/proxy)

---

## Həyatdan analogiya

Bank kartı nağd pulun "proxy"sidir. Mağazada pul kisəsi əvəzinə kart verirsiniz. Satıcı üçün nəticə eynidir: ödəniş alındı. Amma kart arxada əlavə işlər görür: balansı yoxlayır (icazə), əməliyyatı qeyd edir (log), PIN soruşur (təhlükəsizlik). Siz bunları görmürsünüz.

## Problem

Real obyektə birbaşa müraciət bəzən arzuolunmazdır, çünki:
- **bahalıdır**: 10 MB-lıq şəkli diskdən yükləmək, istifadəçi ona heç baxmasa belə;
- **yavaşdır**: eyni videonu şəbəkədən dəfələrlə yükləmək;
- **təhlükəlidir**: şirkətin maliyyə məlumatını hər işçiyə göstərmək.

Real sinfin özünə bu yoxlamaları əlavə etmək istəmirik. Həm onun əsas işi başqadır, həm də çox vaxt kod bizim deyil.

## Həll: eyni interfeysli "nümayəndə"

Proxy real obyektlə **eyni interfeysi** implement edir. Client proxy ilə danışdığını bilmir. Proxy çağırışı real obyektə ötürür, amma **öncəsində və ya sonrasında** öz işini görür.

Repoda üç növ proxy var.

### Növ 1: Virtual proxy (lazy yükləmə), `example1`

```java
public class ProxyImage implements Image {
    private RealImage realImage;          // hələ yoxdur
    private final String fileName;

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);   // yalnız ilk dəfə yüklə
        }
        realImage.display();
    }
}
```

```
Loading test_10mb.jpg       ← ilk display()
Displaying test_10mb.jpg

Displaying test_10mb.jpg    ← ikinci: yükləmə yoxdur
```

### Növ 2: Caching proxy, `example2`

```java
public Video getVideo(String videoId) {
    Video video = cacheAll.get(videoId);
    if (video == null) {
        video = youtubeService.getVideo(videoId);   // yavaş şəbəkə
        cacheAll.put(videoId, video);
    } else {
        System.out.println("Retrieved video '" + videoId + "' from cache.");
    }
    return video;
}
```

`Main` iki downloader-i müqayisə edir və keşin neçə millisaniyə qənaət etdiyini göstərir.

### Növ 3: Protection proxy, `example3`

```java
@Override
public BigDecimal getCiro() {
    if (!isUserManager()) {
        throw new SecurityException("Access denied: '" + username + "' is not a manager");
    }
    return truthManager.getCiro();
}
```

Real `TruthManager` icazələrdən xəbərsizdir. Təhlükəsizlik tamamilə proxy-dədir.

## Decorator ilə fərq

Kod səviyyəsində ikisi demək olar ki, eynidir: eyni interfeys və içəridə başqa obyekt. Fərq **niyyətdədir**:

| Decorator | Proxy |
|---|---|
| Yeni **funksionallıq əlavə edir** | Mövcuda **girişə nəzarət edir** |
| Adətən zəncir qurulur (`A(B(C(x)))`) | Adətən bir qat olur |
| İçindəki obyekti client verir | Proxy real obyekti çox vaxt özü yaradır və idarə edir |

## Real həyatda: bilmədən hər gün istifadə edirsiniz

- **Spring `@Transactional`**: Spring sizin servisinizin proxy-sini yaradır. Metod çağırılanda proxy tranzaksiyanı açır, metodu çağırır, sonra commit və ya rollback edir.
- **Hibernate lazy loading**: `order.getCustomer()` əvvəlcə boş proxy qaytarır, bazaya yalnız sahəyə ilk müraciətdə gedir.
- `java.lang.reflect.Proxy`: runtime-da istənilən interfeys üçün proxy yaratmaq imkanı verir.

## Yadda saxla

- Proxy real obyektin yerini tutur və eyni interfeysə malikdir.
- Əsas növləri: virtual (lazy), caching, protection, logging, remote.
- Client proxy ilə danışdığını bilmir.

## Tapşırıq

1. `LoggingImageProxy` yazın: hər `display()` çağırışının vaxtını çap etsin.
2. `example2`-də keşə müddət əlavə edin: 5 saniyədən köhnə video yenidən yüklənsin.

---

[← 11. Flyweight](11-flyweight.md) · [Mündəricat](README.md) · Növbəti: [13. Chain of Responsibility →](13-chain-of-responsibility.md)
