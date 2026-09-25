# Giriş: Design pattern nədir?

[Mündəricat](README.md) · Növbəti: [1. Singleton →](01-singleton.md)

---

Təsəvvür edin ki, ev tikirsiniz. Hər dəfə qapını necə quraşdıracağınızı sıfırdan düşünmürsünüz. Usta artıq bilir: çərçivə, menteşə, kilid. Bu hazır həll nəsillər boyu yoxlanılıb.

Proqramlaşdırmada da eynidir. Bəzi problemlər təkrar-təkrar qarşımıza çıxır:

- "Bu obyektdən yalnız bir dənə olmalıdır."
- "Köhnə kitabxananın interfeysi yenisinə uyğun gəlmir."
- "Bir şey dəyişəndə başqa beş yer bundan xəbər tutmalıdır."

**Design pattern** belə təkrarlanan problemlərin sınaqdan keçmiş həll şablonudur. Pattern hazır kod deyil, onu kopyalayıb yapışdırmırsınız. O, bir **fikirdir**: problemi hansı siniflərə bölmək, onlar bir-biri ilə necə danışmalıdır.

## Haradan gəlir?

1994-cü ildə dörd müəllif, Erich Gamma, Richard Helm, Ralph Johnson və John Vlissides, *"Design Patterns: Elements of Reusable Object-Oriented Software"* kitabını yazdılar. Müəllifləri qısaca **Gang of Four (GoF)**, yəni "Dördlər dəstəsi" adlandırırlar. Kitabda 23 pattern var və bu bələdçi də məhz həmin 23 pattern-i izah edir.

## Üç qrup

| Qrup | Sual | Pattern-lər |
|---|---|---|
| **Creational** (yaradıcı) | Obyekti *necə yaradaq*? | Singleton, Factory Method, Abstract Factory, Builder, Prototype |
| **Structural** (struktur) | Obyektləri *necə birləşdirək*? | Adapter, Bridge, Composite, Decorator, Facade, Flyweight, Proxy |
| **Behavioral** (davranış) | Obyektlər *necə danışsın*, məsuliyyəti necə bölüşsünlər? | Chain of Responsibility, Command, Interpreter, Iterator, Mediator, Memento, Observer, State, Strategy, Template Method, Visitor |

## Pattern-ləri anlamaq üçün lazım olan üç prinsip

Demək olar ki, bütün pattern-lər bir neçə əsas prinsipə söykənir. Bunları bilsəniz, pattern-lər əzbər deyil, *məntiqli* görünəcək.

### 1. İnterfeysə proqramlaşdırın, implementasiyaya yox

```java
// Pis: konkret sinifdən asılıyıq
ArrayList<String> names = new ArrayList<>();

// Yaxşı: yalnız "bu bir List-dir" bilirik
List<String> names = new ArrayList<>();
```

İkinci halda sabah `ArrayList`-i `LinkedList` ilə əvəz etsəniz, kodun qalan hissəsi dəyişmir. Pattern-lərin əksəriyyəti bu fikrin müxtəlif formalarıdır.

### 2. Varislik (inheritance) əvəzinə kompozisiya

Varislik "X **bir növ** Y-dir" deyir və bu əlaqə kompilyasiya zamanı bərkidilir. Kompozisiya isə "X-in **içində** Y var" deyir və onu proqram işləyərkən də dəyişmək olar. Decorator, Strategy, Bridge, State kimi pattern-lər varislik əvəzinə kompozisiyadan istifadə etdiyi üçün bu qədər çevikdir.

### 3. Open/Closed prinsipi

> Kod **genişlənməyə açıq**, **dəyişikliyə qapalı** olmalıdır.

Yəni yeni funksionallıq əlavə edəndə köhnə, işləyən kodu redaktə etmək yox, yeni sinif əlavə etmək istəyirik. Bu bələdçidə tez-tez görəcəksiniz: "yeni növ = yeni sinif, köhnə kod dəyişmir".

## Xəbərdarlıq: pattern məqsəd deyil

Yeni öyrənən proqramçıların ən çox etdiyi səhv hər yerə pattern tıxmaqdır. Pattern **problemə cavabdır**. Problem yoxdursa, pattern yalnız əlavə mürəkkəblik gətirir. Hər fəsildə "nə vaxt istifadə etməməli" hissəsinə xüsusi diqqət edin.

## Bu bələdçini necə oxumalı

Hər fəsil eyni quruluşdadır:

1. **Həyatdan analogiya**: kodsuz, sadə bir misal.
2. **Problem**: pattern olmadan kod necə görünür və niyə pisdir.
3. **Həll addım-addım**: repodakı real kodla.
4. **Əvvəl və sonra**: fərqi bir baxışda görmək üçün.
5. **Yadda saxla**: əsas fikirlər.
6. **Tapşırıq**: öyrəndiyinizi yoxlamaq üçün.

Hər fəsildəki kod bu repoda var və işləyir. Oxuyarkən kodu IDE-də açın, `Main`-i işə salın, bir şeyi dəyişib nəticəyə baxın. Pattern-lər oxumaqla yox, **əllə toxunmaqla** öyrənilir.

---

[Mündəricat](README.md) · Növbəti: [1. Singleton →](01-singleton.md)
