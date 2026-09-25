# Interpreter

**Kateqoriya:** Behavioral

> Sadə dilin qrammatikasını siniflər ierarxiyası kimi təsvir edir və həmin dildə yazılmış ifadələri hesablayır.

## Problem

`(x + 2) * (y - 1)` kimi ifadəni müxtəlif dəyişən dəyərləri ilə dəfələrlə hesablamaq lazımdır.

## Həll

Qrammatikanın hər qaydası bir sinifdir: `NumberExpression`, `VariableExpression` (terminal) və `Add/Subtract/MultiplyExpression` (non-terminal). İfadə bu obyektlərdən ağac kimi qurulur və `interpret(context)` rekursiv hesablanır. `RpnParser` mətni (`"x 2 + y 1 - *"`) ağaca çevirir.

## Struktur

| Rol | Bu nümunədə |
|---|---|
| Abstract expression | `Expression` |
| Terminal expressions | `NumberExpression, VariableExpression` |
| Non-terminal expressions | `AddExpression, SubtractExpression, MultiplyExpression` |
| Context | `Map<String, Integer>` |

## Nə vaxt istifadə etməli

- Sadə və stabil qrammatikası olan DSL (qaydalar mühərriki, filtr ifadələri, kalkulyator).

## Üstünlüklər

- Qrammatikanı genişləndirmək asandır — yeni qayda = yeni sinif

## Çatışmazlıqlar

- Mürəkkəb qrammatika üçün uyğun deyil (ANTLR kimi parser generatoru daha yaxşıdır)

## Java / real həyatda

- `java.util.regex.Pattern`
- Spring Expression Language (SpEL)

## İşə salmaq

```bash
mvn -q compile
java -cp target/classes com.company.design_patterns.behavioral.interpreter.Main
```
