# Command

**Kateqoriya:** Behavioral

> Sorğunu (əməliyyatı) ayrıca obyektə çevirir — onu saxlamaq, növbəyə qoymaq, loglamaq və **geri almaq (undo)** mümkün olur.

## Problem

Mətn redaktorunda Undo/Redo lazımdır. Əgər düymə birbaşa `editor.insert(...)` çağırırsa, nə edildiyini yadda saxlamaq və geri qaytarmaq mümkün deyil.

## Həll

Hər əməliyyat `execute()` və `undo()` metodları olan `Command` obyektidir və geri qaytarmaq üçün lazım olan məlumatı özü saxlayır. `CommandHistory` (invoker) icra olunan command-ları stack-də saxlayır: undo stack-dən çıxarıb `undo()` çağırır, redo əksini edir.

## Struktur

| Rol | Bu nümunədə |
|---|---|
| Command | `Command` |
| Concrete commands | `AppendCommand, DeleteLastCommand` |
| Receiver | `TextEditor` |
| Invoker | `CommandHistory` |

## Nə vaxt istifadə etməli

- Undo/redo, əməliyyatların növbəsi, gecikdirilmiş icra, makrolar, tranzaksiyalar.

## Üstünlüklər

- Əməliyyatı çağıran və icra edən ayrılır
- Undo/redo asan realizə olunur
- Command-ları birləşdirmək (macro) olar

## Çatışmazlıqlar

- Hər əməliyyat üçün yeni sinif

## Java / real həyatda

- `java.lang.Runnable`
- `javax.swing.Action`

## İşə salmaq

```bash
mvn -q compile
java -cp target/classes com.company.design_patterns.behavioral.command.Main
```
