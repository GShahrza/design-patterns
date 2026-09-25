# 22. Template Method

[← 21. Strategy](21-strategy.md) · [Mündəricat](README.md) · Növbəti: [23. Visitor →](23-visitor.md)

**Qrup:** Behavioral · **Kod:** [`behavioral/template_method`](../src/main/java/com/company/design_patterns/behavioral/template_method)

---

## Həyatdan analogiya

Çay və qəhvə hazırlamaq demək olar ki, eyni prosesdir: suyu qaynat, əsas maddəni dəm et, fincana tök, əlavə qat. Fərq yalnız iki addımdadır: çayı dəmləyirsiniz, qəhvəni süzürsünüz; çaya limon qatırsınız, qəhvəyə süd. Resept **skeleti** eynidir, bəzi **addımlar** dəyişir.

## Problem

Satış hesabatları iki formatda lazımdır, CSV və HTML. Hər ikisi eyni ardıcıllıqla qurulur:

1. Məlumatı filtrlə.
2. Başlıq yaz.
3. Hər satış üçün sətir yaz.
4. Cəmi hesabla və yekun sətrini yaz.

İki ayrı sinif yazsaq, 1-ci və 4-cü addımlar (filtr, cəm hesablamaq) iki yerdə təkrarlanır. Sabah "cəmə ƏDV əlavə et" desələr, iki yerdə dəyişmək lazımdır, üçüncü format (PDF) gəlsə, üç yerdə.

## Həll addım-addım

**Addım 1.** Abstrakt sinifdə alqoritmin **skeletini** yazırıq. Bu, *template method*-dur və `final`-dır, alt siniflər onu dəyişə bilməz:

```java
public abstract class ReportGenerator {

    public final String generate(List<Sale> sales) {
        List<Sale> filtered = filter(sales);                // hook
        StringBuilder out = new StringBuilder();
        out.append(header());                               // abstrakt
        for (Sale sale : filtered) {
            out.append(row(sale));                          // abstrakt
        }
        BigDecimal total = filtered.stream().map(Sale::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);  // ümumi məntiq
        out.append(footer(total));                          // abstrakt
        return out.toString();
    }

    protected abstract String header();
    protected abstract String row(Sale sale);
    protected abstract String footer(BigDecimal total);

    protected List<Sale> filter(List<Sale> sales) {         // hook: default var
        return sales;
    }
}
```

**Addım 2.** Alt siniflər yalnız fərqli olan addımları yazır:

```java
public class CsvReport extends ReportGenerator {
    protected String header()          { return "region,product,amount\n"; }
    protected String row(Sale s)       { return s.region() + "," + s.product() + "," + s.amount() + "\n"; }
    protected String footer(BigDecimal t) { return "TOTAL,," + t + "\n"; }
}
```

**Addım 3.** *Hook* metodu. `filter()` abstrakt deyil, default davranışı var ("heç nəyi filtrləmə"). Alt sinif istəsə onu override edir:

```java
public class HtmlReport extends ReportGenerator {
    @Override
    protected List<Sale> filter(List<Sale> sales) {
        return sales.stream().filter(s -> s.region().equals(region)).toList();
    }
    // header, row, footer ...
}
```

## İki növ addım

| Növ | Nümunə | Alt sinif |
|---|---|---|
| **Abstrakt addım** | `header()`, `row()`, `footer()` | **Mütləq** yazmalıdır |
| **Hook** | `filter()` | **İstəsə** override edir |
| **Template method** | `generate()` | Dəyişə **bilməz** (`final`) |

## Hollywood prinsipi

> "Don't call us, we'll call you." (Bizə zəng etməyin, biz sizə zəng edəcəyik.)

Alt sinif heç nəyi çağırmır. O, sadəcə metodlarını təqdim edir, onları **üst sinif** lazım olan anda çağırır. İdarəetmə üst sinifdədir. Framework-lər məhz belə işləyir.

## Strategy ilə müqayisə

Eyni problemi Strategy ilə də həll etmək olardı: `header/row/footer`-i `ReportFormat` interfeysinə çıxarıb generator-a ötürmək.

| Template Method | Strategy |
|---|---|
| **Varislik** | **Kompozisiya** |
| Addımlar kompilyasiya zamanı seçilir | Runtime-da dəyişmək olar |
| Az kod | Daha çevik |

Müasir Java-da çox vaxt kompozisiyaya (Strategy) üstünlük verilir. Amma Template Method framework-lərdə hələ də geniş istifadə olunur.

## Real həyatda

- `InputStream.read(byte[])` sizin yazdığınız `read()` metodunu dövr içində çağırır.
- `AbstractList`: siz yalnız `get()` və `size()` yazırsınız, `iterator()`, `contains()`, `indexOf()` isə hazır gəlir.
- JUnit: `@BeforeEach` → test → `@AfterEach` ardıcıllığını framework idarə edir.

## Yadda saxla

- Alqoritmin skeleti üst sinifdə (`final`), dəyişən addımlar alt siniflərdədir.
- Abstrakt addımlar məcburidir, hook-lar isə istəyə bağlıdır.
- Kod təkrarını aradan qaldırır, amma varisliyə bağlıdır.

## Tapşırıq

1. `MarkdownReport` yazın (`| region | product | amount |` cədvəli).
2. `ReportGenerator`-ə `boolean includeTotal()` hook-u əlavə edin, default `true` olsun. `CsvReport`-da onu `false` edin ki, cəm sətri çıxmasın.

---

[← 21. Strategy](21-strategy.md) · [Mündəricat](README.md) · Növbəti: [23. Visitor →](23-visitor.md)
