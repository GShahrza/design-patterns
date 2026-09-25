# Design Patterns — Java nümunələri

Gang of Four (GoF) kitabındakı **23 design pattern**-in hamısı üçün Java 17 ilə işlək nümunələr.
Hər pattern-in öz paketi, `Main` sinfi (işə salmaq üçün) və Azərbaycan dilində `README.md` təsviri var:
problem, həll, struktur, nə vaxt istifadə etməli, üstünlüklər/çatışmazlıqlar və JDK-dan real nümunələr.

## İşə salmaq

```bash
mvn test                      # bütün testlər
mvn -q compile
java -cp target/classes com.company.design_patterns.behavioral.observer.Main
```

Və ya IntelliJ IDEA-da istənilən `Main` sinfini birbaşa işə salın.

## Pattern-lər

Kök qovluq: `src/main/java/com/company/design_patterns/`

### Creational — obyektlərin yaradılması

| # | Pattern | Qısa təsvir | Nümunə |
|---|---|---|---|
| 1 | [Singleton](src/main/java/com/company/design_patterns/creational/singleton) | Sinfin yalnız bir obyekti olur | 7 üsul: eager, lazy, double-checked, Bill Pugh, enum... |
| 2 | [Factory Method](src/main/java/com/company/design_patterns/creational/factory_method) | Hansı obyektin yaradılacağına alt sinif qərar verir | Email / SMS bildiriş servisi |
| — | [Simple Factory](src/main/java/com/company/design_patterns/creational/factory) | Yaratma məntiqi bir statik metodda (GoF-a daxil deyil) | PDF / Excel exporter |
| 3 | [Abstract Factory](src/main/java/com/company/design_patterns/creational/abstract_factory) | Əlaqəli obyektlər ailəsini yaradır | Light / Dark UI teması |
| 4 | [Builder](src/main/java/com/company/design_patterns/creational/builder) | Mürəkkəb obyekti addım-addım qurur | Immutable `Product` + validasiya |
| 5 | [Prototype](src/main/java/com/company/design_patterns/creational/prototype) | Obyekti klonlayaraq yaradır | Fiqur keşi (registry) |

### Structural — obyektlərin birləşdirilməsi

| # | Pattern | Qısa təsvir | Nümunə |
|---|---|---|---|
| 6 | [Adapter](src/main/java/com/company/design_patterns/structural/adapter) | Uyğunsuz interfeysi uyğunlaşdırır | 3rd-party şifrələmə kitabxanası |
| 7 | [Bridge](src/main/java/com/company/design_patterns/structural/bridge) | Abstraksiyanı implementasiyadan ayırır | Mesaj növü × göndərmə kanalı |
| 8 | [Composite](src/main/java/com/company/design_patterns/structural/composite) | Ağac strukturu; tək və qrup eyni cür | Fayl sistemi (qovluq ölçüsü) |
| 9 | [Decorator](src/main/java/com/company/design_patterns/structural/decorator) | Runtime-da davranış əlavə edir | Telefon versiyaları, çərçivəli fiqur |
| 10 | [Facade](src/main/java/com/company/design_patterns/structural/facade) | Mürəkkəb sistemə sadə giriş | Sifariş: anbar + ödəniş + çatdırılma |
| 11 | [Flyweight](src/main/java/com/company/design_patterns/structural/flyweight) | Ümumi vəziyyəti paylaşıb yaddaşa qənaət | 100 000 ağac, 3 `TreeType` |
| 12 | [Proxy](src/main/java/com/company/design_patterns/structural/proxy) | Obyektə girişə nəzarət | Lazy şəkil, YouTube keşi, icazə yoxlaması |

### Behavioral — obyektlər arasında davranış və məsuliyyət

| # | Pattern | Qısa təsvir | Nümunə |
|---|---|---|---|
| 13 | [Chain of Responsibility](src/main/java/com/company/design_patterns/behavioral/chain_of_responsibility) | Sorğu handler zəncirindən keçir | HTTP middleware: rate limit → auth → role |
| 14 | [Command](src/main/java/com/company/design_patterns/behavioral/command) | Əməliyyat obyektə çevrilir | Mətn redaktoru, undo/redo |
| 15 | [Interpreter](src/main/java/com/company/design_patterns/behavioral/interpreter) | Sadə dilin ifadələrini hesablayır | `(x + 2) * (y - 1)` kalkulyatoru |
| 16 | [Iterator](src/main/java/com/company/design_patterns/behavioral/iterator) | Kolleksiyanı daxilini açmadan gəzir | Playlist: ardıcıl, tərs, qarışıq |
| 17 | [Mediator](src/main/java/com/company/design_patterns/behavioral/mediator) | Obyektlər vasitəçi üzərindən danışır | Chat otağı + moderasiya |
| 18 | [Memento](src/main/java/com/company/design_patterns/behavioral/memento) | Vəziyyəti saxlayıb bərpa edir | Oyunda save / load |
| 19 | [Observer](src/main/java/com/company/design_patterns/behavioral/observer) | Dəyişiklikdən abunəçilər xəbər tutur | Birja qiyməti → alert, portfel |
| 20 | [State](src/main/java/com/company/design_patterns/behavioral/state) | Davranış vəziyyətə görə dəyişir | Sifariş: NEW → PAID → SHIPPED |
| 21 | [Strategy](src/main/java/com/company/design_patterns/behavioral/strategy) | Alqoritmi runtime-da dəyişmək | Səbətdə endirim növləri |
| 22 | [Template Method](src/main/java/com/company/design_patterns/behavioral/template_method) | Alqoritmin skeleti sabit, addımlar dəyişir | CSV / HTML hesabat |
| 23 | [Visitor](src/main/java/com/company/design_patterns/behavioral/visitor) | Siniflərə toxunmadan yeni əməliyyat | Vergi və çatdırılma hesablanması |

## Tez-tez qarışdırılan pattern-lər

| Cüt | Fərq |
|---|---|
| Simple Factory vs Factory Method | Simple Factory — bir statik metod + `switch`. Factory Method — alt siniflər override edir, yeni növ üçün köhnə kod dəyişmir. |
| Factory Method vs Abstract Factory | Factory Method bir məhsul yaradır; Abstract Factory uyğun məhsullar **ailəsini**. |
| Decorator vs Proxy | İkisi də eyni interfeysi bükür. Decorator **funksionallıq əlavə edir** və zəncir qurulur; Proxy **girişə nəzarət edir** (lazy, keş, icazə). |
| Adapter vs Facade | Adapter mövcud interfeysi **başqa** interfeysə çevirir; Facade alt sistemlər üçün **yeni, sadə** interfeys yaradır. |
| State vs Strategy | Strategy-ni client seçir; State-lər isə keçidləri özləri edir. |
| Command vs Memento | İkisi də undo üçün istifadə olunur: Command *əməliyyatı* geri qaytarır, Memento *vəziyyətin snapshot*-ını bərpa edir. |

## Layihə strukturu

```
src/
├── main/java/com/company/design_patterns/
│   ├── creational/   singleton, factory, factory_method, abstract_factory, builder, prototype
│   ├── structural/   adapter, bridge, composite, decorator, facade, flyweight, proxy
│   └── behavioral/   chain_of_responsibility, command, interpreter, iterator, mediator,
│                     memento, observer, state, strategy, template_method, visitor
└── test/java/...     hər kateqoriya üçün JUnit 5 testləri
```
