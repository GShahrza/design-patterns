# 18. Memento

[← 17. Mediator](17-mediator.md) · [Mündəricat](README.md) · Növbəti: [19. Observer →](19-observer.md)

**Qrup:** Behavioral · **Kod:** [`behavioral/memento`](../src/main/java/com/company/design_patterns/behavioral/memento)

---

## Həyatdan analogiya

Video oyunda boss-a hücum etməzdən əvvəl "Save" edirsiniz. Məğlub olsanız, "Load" edib həmin anı bərpa edirsiniz. Save faylının içində nə olduğunu (hansı formatda, hansı sahələr) bilmirsiniz və bilməyə ehtiyac da yoxdur. Sadəcə "bu anı saxla" və "həmin ana qayıt" deyirsiniz.

## Problem

`SaveManager` personajın vəziyyətini yadda saxlamalıdır. Sadəlövh yanaşma:

```java
class SaveManager {
    int savedLevel, savedHealth;
    String savedLocation;

    void save(GameCharacter c) {
        savedLevel = c.level;          // sahələr public olmalıdır!
        savedHealth = c.health;
        savedLocation = c.location;
    }
}
```

Problemlər:
- `GameCharacter`-in sahələrini açmaq məcburiyyətində qalırıq, yəni inkapsulyasiya pozulur.
- Personaja yeni sahə (`inventory`) əlavə olunsa, `SaveManager`-i də dəyişmək lazımdır.
- `SaveManager` saxlanmış vəziyyəti istədiyi kimi dəyişə bilər (`savedHealth = 9999`).

## Həll addım-addım

Üç rol var:

| Rol | Sinif | İşi |
|---|---|---|
| **Originator** | `GameCharacter` | Vəziyyətin sahibi. Snapshot yaradır və ondan bərpa olunur |
| **Memento** | `GameCharacter.Memento` | Vəziyyətin dəyişməz surəti |
| **Caretaker** | `SaveManager` | Memento-ları saxlayır, amma içinə baxa bilmir |

**Addım 1.** Snapshot yaratmaq və bərpa etmək işini **vəziyyətin sahibi özü** görür:

```java
public Memento save() {
    return new Memento(level, health, location);
}

public void restore(Memento memento) {
    this.level = memento.level;
    this.health = memento.health;
    this.location = memento.location;
}
```

**Addım 2.** Memento-nu elə yazırıq ki, onu yalnız `GameCharacter` oxuya bilsin:

```java
public class GameCharacter {
    // ...
    public static final class Memento {
        private final int level;          // private!
        private final int health;
        private final String location;

        private Memento(...) { ... }      // yalnız GameCharacter yarada bilər
    }
}
```

Bu Java-nın bir xüsusiyyətinə söykənir: xarici sinif daxili sinfin `private` sahələrini görür, başqa heç kim görmür. `SaveManager` üçün `Memento` qapalı qutudur. Onu saxlaya və geri verə bilər, amma içini aça bilməz.

**Addım 3.** Caretaker sadəcə saxlayır:

```java
public void save(String slot, GameCharacter character) {
    slots.put(slot, character.save());
}

public void load(String slot, GameCharacter character) {
    character.restore(slots.get(slot));
}
```

## Nəticə

```
Before boss: level=2, health=100, location=Forest
  saved to slot 'before-boss'
After boss:  level=2, health=0, location=Dragon Cave  -> GAME OVER
  loaded slot 'before-boss'
Restored:    level=2, health=100, location=Forest
```

## Command ilə fərq

İkisi də "undo" üçün istifadə olunur, amma fərqli yolla:

| Command | Memento |
|---|---|
| Əməliyyatın **əksini** icra edir | Əvvəlki **vəziyyətin şəklini** bərpa edir |
| Yaddaşa qənaət edir, çünki yalnız dəyişiklik saxlanılır | Hər snapshot tam surətdir |
| Hər əməliyyat üçün undo məntiqi yazılmalıdır | Undo məntiqi universaldır |

Praktikada ikisi tez-tez birlikdə istifadə olunur: command icradan əvvəl memento götürür, undo-da isə onu bərpa edir.

## Diqqət: yaddaş

Hər snapshot tam surətdir. Böyük obyektin hər dəqiqə snapshot-unu götürsəniz, yaddaş tez dolur. Həll yolları: snapshot sayını məhdudlaşdırmaq (məsələn, son 50) və ya yalnız dəyişən hissələri saxlamaq.

## Yadda saxla

- Memento vəziyyəti **inkapsulyasiyanı pozmadan** saxlayır.
- Snapshot-u yalnız originator yaradır və oxuyur.
- Caretaker snapshot-ları saxlayır, amma onların içini bilmir.

## Tapşırıq

1. Personaja `List<String> inventory` əlavə edin. Memento-da siyahının **surətini** saxlamağı unutmayın. Unutsanız nə olacaq?
2. `SaveManager`-ə `autosave` əlavə edin: son 3 avtomatik save saxlansın, köhnələri silinsin.

---

[← 17. Mediator](17-mediator.md) · [Mündəricat](README.md) · Növbəti: [19. Observer →](19-observer.md)
