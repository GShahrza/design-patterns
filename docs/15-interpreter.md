# 15. Interpreter

[← 14. Command](14-command.md) · [Mündəricat](README.md) · Növbəti: [16. Iterator →](16-iterator.md)

**Qrup:** Behavioral · **Kod:** [`behavioral/interpreter`](../src/main/java/com/company/design_patterns/behavioral/interpreter)

---

## Həyatdan analogiya

Musiqi notlarını düşünün. Not yazısı kiçik bir dildir: hər simvolun mənası var (do, re, uzun, qısa), simvollar birləşib melodiya yaradır. Musiqiçi bu "dili" oxuyur, yəni **interpret edir** və səsə çevirir. Eyni notları fərqli alətdə, fərqli tempdə ifa etmək olar.

## Problem

İstifadəçi düstur yazır: `(x + 2) * (y - 1)`. Proqram onu müxtəlif `x`, `y` dəyərləri ilə dəfələrlə hesablamalıdır. Məsələn, qiymət düsturları, endirim qaydaları, hesabat filtrləri.

Hər dəfə sətri `if`-lərlə parçalamaq həm yavaş, həm də səhvə meyilli olardı.

## Həll addım-addım

İfadəni **ağac** kimi təsvir edirik. Qrammatikanın hər qaydası bir sinifdir.

**Addım 1.** Ümumi interfeys:

```java
public interface Expression {
    int interpret(Map<String, Integer> context);
}
```

`context` dəyişənlərin dəyərləridir: `{x=3, y=5}`.

**Addım 2.** *Terminal* ifadələr, yəni ağacın yarpaqları, özləri dəyər qaytarır:

```java
public record NumberExpression(int value) implements Expression {
    public int interpret(Map<String, Integer> ctx) { return value; }
}

public record VariableExpression(String name) implements Expression {
    public int interpret(Map<String, Integer> ctx) { return ctx.get(name); }
}
```

**Addım 3.** *Non-terminal* ifadələr digər ifadələrdən ibarətdir və onları rekursiv hesablayır:

```java
public record AddExpression(Expression left, Expression right) implements Expression {
    public int interpret(Map<String, Integer> ctx) {
        return left.interpret(ctx) + right.interpret(ctx);
    }
}
```

**Addım 4.** Ağac `(x + 2) * (y - 1)` üçün belə görünür:

```
          Multiply
         /        \
      Add          Subtract
     /   \         /      \
   Var    Num    Var      Num
   "x"     2     "y"       1
```

Ağacın kökünə `interpret({x=3, y=5})` deyirik. O da övladlarından soruşur, övladlar da öz övladlarından. Nəticə: `(3 + 2) * (5 - 1) = 20`.

## Ağacı kim qurur?

Ağacı əllə qurmaq mümkündür:

```java
new MultiplyExpression(
    new AddExpression(new VariableExpression("x"), new NumberExpression(2)),
    new SubtractExpression(new VariableExpression("y"), new NumberExpression(1)));
```

Amma bu yorucudur. `RpnParser` mətni ağaca çevirir. Sadəlik üçün **RPN** (Reverse Polish Notation) istifadə olunur: `x 2 + y 1 - *`. RPN-də mötərizə yoxdur və operator operandlardan sonra gəlir, ona görə bir stack ilə asanca parse olunur.

Vacib qeyd: **parser pattern-in hissəsi deyil**. Interpreter pattern ağacın özü və `interpret()` metodudur.

## Bir dəfə qur, çox dəfə hesabla

```java
Expression expression = RpnParser.parse("x 2 + y 1 - *");   // bir dəfə
expression.interpret(Map.of("x", 3, "y", 5));    // 20
expression.interpret(Map.of("x", 10, "y", 2));   // 12
```

## Nə vaxt istifadə ETMƏMƏLİ

Qrammatika mürəkkəbdirsə (onlarla qayda, prioritetlər, funksiyalar), hər qayda üçün sinif yazmaq idarəolunmaz hala gəlir. Belə hallarda ANTLR kimi parser generatorları və ya hazır ifadə kitabxanaları istifadə edin.

## Real həyatda

- `java.util.regex.Pattern`: regex ifadəsi daxildə ağaca çevrilir və mətnə tətbiq olunur.
- Spring Expression Language (SpEL): `#{user.age > 18}`.
- SQL-in `WHERE` hissəsi, qaydalar mühərrikləri (rule engine).

## Yadda saxla

- Interpreter kiçik dil üçündür, hər qrammatika qaydası bir sinifdir.
- İfadə ağac kimi qurulur və rekursiv hesablanır.
- Sadə qrammatika üçün əladır, mürəkkəb qrammatika üçün uyğun deyil.

## Tapşırıq

1. `DivideExpression` əlavə edin, sıfıra bölmədə aydın xəta atsın. `RpnParser`-ə `/` operatorunu əlavə edin.
2. `String toInfix()` metodu əlavə edin: ağacı yenidən `((x + 2) * (y - 1))` şəklində yazsın.

---

[← 14. Command](14-command.md) · [Mündəricat](README.md) · Növbəti: [16. Iterator →](16-iterator.md)
