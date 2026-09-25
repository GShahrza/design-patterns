# Sonsöz: Hansı problemə hansı pattern?

[← 23. Visitor](23-visitor.md) · [Mündəricat](README.md)

---

Pattern-ləri öyrəndiniz. Real layihədə isə sual belə qoyulur: "Qarşımdakı problemi hansı pattern həll edir?" Bu cədvəl problemdən pattern-ə getməyə kömək edir.

## Problemdən pattern-ə

| Problem, yəni "Mənə lazımdır ki..." | Pattern |
|---|---|
| ...obyektdən yalnız bir dənə olsun | [Singleton](01-singleton.md) |
| ...hansı sinfin yaradılacağına alt siniflər qərar versin | [Factory Method](02-factory-method.md) |
| ...bir-birinə uyğun obyektlər **dəst** halında yaradılsın | [Abstract Factory](03-abstract-factory.md) |
| ...çoxlu parametrli obyekt oxunaqlı şəkildə qurulsun | [Builder](04-builder.md) |
| ...bahalı obyekt yenidən yaradılmasın, surəti çıxarılsın | [Prototype](05-prototype.md) |
| ...uyğunsuz interfeysli köhnə və ya xarici kod sistemə qoşulsun | [Adapter](06-adapter.md) |
| ...iki müstəqil ölçü siniflərin sayını vurma ilə artırmasın | [Bridge](07-bridge.md) |
| ...ağac strukturunda tək elementlə qrup eyni cür işlənsin | [Composite](08-composite.md) |
| ...obyektə runtime-da, qat-qat yeni xüsusiyyət əlavə olunsun | [Decorator](09-decorator.md) |
| ...mürəkkəb alt sistemə bir sadə giriş olsun | [Facade](10-facade.md) |
| ...milyonlarla oxşar obyekt yaddaşı doldurmasın | [Flyweight](11-flyweight.md) |
| ...obyektə giriş lazy, keşlənmiş və ya icazəli olsun | [Proxy](12-proxy.md) |
| ...sorğu bir neçə yoxlamadan ardıcıl keçsin | [Chain of Responsibility](13-chain-of-responsibility.md) |
| ...əməliyyatlar geri alınsın (undo), növbəyə qoyulsun | [Command](14-command.md) |
| ...sadə dilin (düstur, qayda) ifadələri hesablansın | [Interpreter](15-interpreter.md) |
| ...kolleksiya daxilini açmadan müxtəlif üsullarla gəzilsin | [Iterator](16-iterator.md) |
| ...çoxlu obyekt bir-birini tanımadan əlaqə saxlasın | [Mediator](17-mediator.md) |
| ...vəziyyət saxlanıb sonra bərpa olunsun (save/load) | [Memento](18-memento.md) |
| ...bir dəyişiklikdən çoxlu komponent xəbər tutsun | [Observer](19-observer.md) |
| ...davranış obyektin vəziyyətinə görə dəyişsin, switch-lər olmadan | [State](20-state.md) |
| ...bir işin bir neçə üsulu olsun və runtime-da seçilsin | [Strategy](21-strategy.md) |
| ...alqoritmin skeleti sabit qalsın, bəzi addımlar dəyişsin | [Template Method](22-template-method.md) |
| ...siniflərə toxunmadan onlara yeni əməliyyatlar əlavə olunsun | [Visitor](23-visitor.md) |

## Kod "iylərindən" pattern-ə

Bəzən problem özünü kodda müəyyən əlamətlərlə büruzə verir:

| Kodda görürsünüz | Düşünün |
|---|---|
| Eyni `switch (type)` bir neçə yerdə təkrarlanır | Factory Method, Strategy, State |
| `switch (status)` hər metodda var | State |
| `if (x instanceof A) ... else if (x instanceof B)` | Composite, Visitor, polimorfizm |
| 6+ parametrli konstruktor | Builder |
| Sinfin adı iki anlayışı birləşdirir (`UrgentSmsMessage`) | Bridge, Decorator |
| Bir metod 5 servisi ardıcıl çağırır və bu kod təkrarlanır | Facade |
| Sinif dəyişəndə başqa 5 sinfi də çağırır | Observer, Mediator |
| İki sinifdə 80% eyni metod, 20% fərqli | Template Method, Strategy |

## Qarışdırılan cütlər: xülasə

| Cüt | Bir cümləlik fərq |
|---|---|
| Factory Method və Abstract Factory | Bir məhsul və məhsul ailəsi |
| Adapter və Facade | Mövcud interfeysə uyğunlaşdırmaq və yeni sadə interfeys yaratmaq |
| Adapter və Bridge | Sonradan yamaq və əvvəlcədən dizayn |
| Decorator və Proxy | Funksionallıq əlavə etmək və girişə nəzarət etmək |
| Strategy və State | Client seçir və vəziyyət özü dəyişir |
| Command və Memento | Əməliyyatı geri almaq və vəziyyəti bərpa etmək |
| Observer və Mediator | Bir mənbədən çox dinləyiciyə və çoxdan çoxa vasitəçi ilə |
| Template Method və Strategy | Varislik və kompozisiya |

## Son məsləhət

1. **Əvvəl sadə yazın.** Kod işləsin. Pattern lazım olsa, refaktorinq zamanı özü görünəcək.
2. **Pattern adını kodda istifadə edin.** `DiscountStrategy`, `OrderFacade`, `ShapeCache`. Ad komanda üzvlərinə dərhal nə baş verdiyini deyir.
3. **Framework-ləri oxuyun.** Spring, Hibernate, JDK: pattern-lərin ən yaxşı real nümunələri oradadır. Hər dəfə `@Transactional` yazanda arxada Proxy olduğunu xatırlayın.
4. **Tapşırıqları edin.** Hər fəslin sonundakı tapşırıqlar oxuduğunuzu bacarığa çevirir.

Uğurlar!

---

[← 23. Visitor](23-visitor.md) · [Mündəricat](README.md)
