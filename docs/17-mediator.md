# 17. Mediator

[← 16. Iterator](16-iterator.md) · [Mündəricat](README.md) · Növbəti: [18. Memento →](18-memento.md)

**Qrup:** Behavioral · **Kod:** [`behavioral/mediator`](../src/main/java/com/company/design_patterns/behavioral/mediator)

---

## Həyatdan analogiya

Hava limanı yaxınlığında onlarla təyyarə uçur. Pilotlar bir-biri ilə birbaşa danışsaydı ("Mən enirəm, sən gözlə", "Yox, mən enirəm!"), xaos yaranardı. Ona görə hamı yalnız **dispetçer qülləsi** ilə danışır. Kimin nə vaxt enəcəyinə dispetçer qərar verir. Pilotlar bir-birini tanımır, yalnız qülləni tanıyır.

## Problem

Chat tətbiqində hər istifadəçi digərlərinə mesaj göndərir:

```java
class User {
    List<User> friends;   // hamını tanıyır
    void send(String msg) {
        for (User u : friends) u.receive(msg);
    }
}
```

5 istifadəçi arasında 20 əlaqə, 100 istifadəçi arasında 9900 əlaqə yaranır. İndi qaydalar əlavə edək: "spam sözləri bloklansın", "şəxsi mesajlar yalnız alana getsin", "yeni gələn haqqında hamıya xəbər verilsin". Bu qaydaları haraya yazmalı? Hər `User`-ə? O zaman `User` həm istifadəçidir, həm moderator, həm də marşrutlaşdırıcı.

## Həll addım-addım

**Addım 1.** Vasitəçi interfeysi (*Mediator*):

```java
public interface ChatMediator {
    void join(User user);
    void send(String message, User from);
    void sendPrivate(String message, User from, String toName);
}
```

**Addım 2.** İstifadəçi (*Colleague*) **yalnız mediator-u tanıyır**:

```java
public class User {
    private final ChatMediator chat;

    public void send(String message) {
        chat.send(message, this);      // kimə çatacağını bilmir
    }

    void receive(String formatted) { ... }
}
```

**Addım 3.** Bütün qaydalar bir yerdə, konkret mediator-dadır:

```java
public class ChatRoom implements ChatMediator {
    private final Map<String, User> users = new LinkedHashMap<>();
    private final Set<String> bannedWords = Set.of("spam");

    @Override
    public void send(String message, User from) {
        if (bannedWords.stream().anyMatch(message.toLowerCase()::contains)) {
            from.receive("*** message blocked by moderator ***");
            return;
        }
        users.values().stream()
                .filter(u -> u != from)                // özünə göndərmə
                .forEach(u -> u.receive(from.getName() + ": " + message));
    }
}
```

## Əvvəl və sonra

```
ƏVVƏL: hamı hamı ilə           SONRA: hamı yalnız mediator ilə
  A ─── B                          A   B
  │ ╲ ╱ │                           ╲ ╱
  │ ╱ ╲ │                         ChatRoom
  C ─── D                           ╱ ╲
                                   C   D
  N×(N-1) əlaqə                   N əlaqə
```

## Observer ilə fərq

İkisi də "obyektlər bir-biri haqqında birbaşa bilmir" deyir. Amma:
- **Observer** birtərəflidir: bir mənbə, çoxlu dinləyici. Mənbə sadəcə elan edir.
- **Mediator** çoxtərəflidir: hamı bir-biri ilə əlaqədədir, amma vasitəçi vasitəsilə, və vasitəçi **qərar verir** (blokla, yönləndir, filtrlə).

## Tələ

Mediator bütün qarşılıqlı əlaqə məntiqini özünə yığır. Diqqətli olmasanız, 2000 sətirlik "hər şeyi bilən" sinif alınır. Əgər mediator çox böyüyürsə, onu məsuliyyətlərə görə bölün.

## Real həyatda

- MVC-də **Controller**: View və Model bir-birini birbaşa tanımır.
- UI formaları: "Ölkə" seçiləndə "Şəhər" siyahısı yenilənir, "Göndər" düyməsi aktivləşir. Bu əlaqələri forma (mediator) idarə edir, komponentlər bir-birini tanımır.
- Mesaj brokerləri (Kafka, RabbitMQ) servislər arasında mediator rolunu oynayır.

## Yadda saxla

- Mediator obyektlər arasındakı birbaşa əlaqələri kəsir.
- Bütün qarşılıqlı əlaqə qaydaları bir yerdə olur.
- Mediator-un "god object"-ə çevrilməsinə diqqət edin.

## Tapşırıq

1. `ChatRoom`-a `leave(User)` əlavə edin: çıxan barədə digərlərinə xəbər getsin.
2. `mute(String name)` əlavə edin: səssizə alınmış istifadəçinin mesajları heç kimə çatmasın. Bu dəyişiklik üçün `User` sinfinə toxunmaq lazım gəldimi?

---

[← 16. Iterator](16-iterator.md) · [Mündəricat](README.md) · Növbəti: [18. Memento →](18-memento.md)
