# 14. Command

[← 13. Chain of Responsibility](13-chain-of-responsibility.md) · [Mündəricat](README.md) · Növbəti: [15. Interpreter →](15-interpreter.md)

**Qrup:** Behavioral · **Kod:** [`behavioral/command`](../src/main/java/com/company/design_patterns/behavioral/command)

---

## Həyatdan analogiya

Restoranda ofisiant sifarişinizi kağıza yazır və mətbəxə verir. Bu kağız **əmrdir**: nə edilməli olduğunu özündə saxlayır. Onu növbəyə qoymaq, aşpazlar arasında paylamaq, sonra ləğv etmək və ya hesabatda göstərmək olar. Ofisiant plov bişirməyi bilmir, sadəcə əmri ötürür.

## Problem

Mətn redaktoru yazırıq. Düymələr birbaşa redaktoru çağırır:

```java
saveButton.onClick(() -> editor.insert(editor.length(), "Hello"));
```

`Ctrl+Z` basanda nə baş verməlidir? Nə edildiyini heç kim yadda saxlamayıb. Əməliyyat sadəcə bir metod çağırışı idi, icra olundu və izi qalmadı.

## Həll addım-addım

**Addım 1.** Hər əməliyyatı **obyektə** çeviririk. Obyekt həm icra etməyi, həm də geri qaytarmağı bilir:

```java
public interface Command {
    void execute();
    void undo();
}
```

**Addım 2.** Konkret əmrlər. Diqqət edin: əmr geri qaytarmaq üçün lazım olan məlumatı **özündə saxlayır**:

```java
public class DeleteLastCommand implements Command {
    private final TextEditor editor;
    private final int count;
    private String deleted = "";    // ← undo üçün yadda saxlayırıq
    private int from;

    @Override
    public void execute() {
        from = Math.max(0, editor.length() - count);
        deleted = editor.delete(from, editor.length());
    }

    @Override
    public void undo() {
        editor.insert(from, deleted);   // silinəni geri qoy
    }
}
```

**Addım 3.** *Invoker*, yəni əmrləri icra edən və tarixçəni saxlayan sinif, iki stack istifadə edir:

```java
public void execute(Command command) {
    command.execute();
    undoStack.push(command);
    redoStack.clear();          // yeni əməliyyat redo tarixçəsini silir
}

public boolean undo() {
    Command command = undoStack.pop();
    command.undo();
    redoStack.push(command);
    return true;
}
```

## Nəticə

```
After typing:  Hello, World!!!
After delete:  Hello, World!
Undo:          Hello, World!!!
Undo:          Hello, World
Redo:          Hello, World!!!
```

## Rollar

| Rol | Sinif | İşi |
|---|---|---|
| Command | `Command` | "Nə edilməli" |
| Concrete command | `AppendCommand`, `DeleteLastCommand` | Konkret əməliyyat + undo məlumatı |
| Receiver | `TextEditor` | Real işi görən |
| Invoker | `CommandHistory` | Əmrləri icra edir və yadda saxlayır |

## Əmr obyekt olanda başqa nələr mümkündür

- **Növbə**: əmrləri siyahıya yığıb sonra və ya başqa thread-də icra etmək.
- **Makro**: `MacroCommand` bir neçə əmri bir əmr kimi icra edir.
- **Log**: əmrləri diskə yazıb, çökmədən sonra yenidən icra etmək. Verilənlər bazaları belə işləyir.
- **Şəbəkə**: əmri serialize edib başqa serverə göndərmək.

## JDK-da

`Runnable` ən sadə command-dır: "icra olunacaq iş" obyektdir. `ExecutorService.submit(runnable)` onu növbəyə qoyur, başqa thread icra edir.

## Yadda saxla

- Command əməliyyatı obyektə çevirir.
- Undo məlumatını əmrin özü saxlayır.
- Invoker əmrlərin nə etdiyini bilmir, yalnız `execute()` və `undo()` çağırır.

## Tapşırıq

1. `UpperCaseCommand` yazın: bütün mətni böyük hərflə yazsın, undo isə əvvəlki mətni bərpa etsin.
2. `MacroCommand(List<Command>)` yazın. `undo()` edərkən əmrləri **tərs** sırada geri qaytarmaq lazımdır. Niyə?

---

[← 13. Chain of Responsibility](13-chain-of-responsibility.md) · [Mündəricat](README.md) · Növbəti: [15. Interpreter →](15-interpreter.md)
