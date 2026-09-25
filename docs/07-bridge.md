# 7. Bridge

[← 6. Adapter](06-adapter.md) · [Mündəricat](README.md) · Növbəti: [8. Composite →](08-composite.md)

**Qrup:** Structural · **Kod:** [`structural/bridge`](../src/main/java/com/company/design_patterns/structural/bridge)

---

## Həyatdan analogiya

Televizor pultu ilə televizoru düşünün. Pult (*abstraksiya*) "kanalı dəyiş", "səsi artır" deyir. Televizor (*implementasiya*) bunu necə edəcəyini bilir. İstənilən pultu istənilən televizorla işlətmək olar: sadə pult Samsung ilə, "ağıllı" pult LG ilə. Hər pult-televizor cütü üçün ayrıca cihaz istehsal olunmur.

## Problem

Mesaj göndərmə sistemi yazırıq. Mesajların **növləri** var (adi və təcili), göndərmənin də **kanalları** var (Email, SMS, Telegram). Varislik ilə həll etməyə çalışaq:

```
Message
├── TextEmailMessage
├── TextSmsMessage
├── TextTelegramMessage
├── UrgentEmailMessage
├── UrgentSmsMessage
└── UrgentTelegramMessage
```

2 növ × 3 kanal = **6 sinif**. WhatsApp əlavə etsək 8, "Reklam" mesaj növü əlavə etsək 12 olur. Siniflərin sayı **vurma** ilə artır. Buna "sinif partlayışı" deyilir.

Problemin kökü odur ki, bir iyerarxiyaya iki **müstəqil** dəyişən ölçünü yığmışıq.

## Həll addım-addım

**Addım 1.** İki ölçünü ayırırıq. "Necə göndərilir" ölçüsü (*implementation*) ayrıca interfeysə çıxır:

```java
public interface MessageSender {
    void sendMessage(String subject, String body);
}
```

`EmailSender`, `SmsSender`, `TelegramSender` bunu implement edir.

**Addım 2.** "Nə göndərilir" ölçüsü (*abstraction*) öz iyerarxiyasında qalır və sender-ə **istinad** saxlayır. Bu istinad körpünün (bridge) özüdür:

```java
public abstract class Message {
    protected final MessageSender sender;   // ← körpü

    protected Message(MessageSender sender) {
        this.sender = sender;
    }

    public abstract void send(String text);
}
```

**Addım 3.** Mesaj növləri öz məntiqini yazır və göndərmə işini sender-ə ötürür:

```java
public class UrgentMessage extends Message {
    @Override
    public void send(String text) {
        sender.sendMessage("URGENT", text.toUpperCase() + " (reply ASAP)");
    }
}
```

**İstifadə:** kombinasiyanı proqram işləyərkən qurursunuz:

```java
new TextMessage(new EmailSender()).send("Weekly report is ready");
new UrgentMessage(new SmsSender()).send("Server is down");
new UrgentMessage(new TelegramSender()).send("Disk usage 95%");
```

## Əvvəl və sonra

```
ƏVVƏL (N × M):                    SONRA (N + M):
6 sinif, hər yeni kanal +2         Message ◆────────► «MessageSender»
                                   ├ TextMessage       ├ EmailSender
                                   └ UrgentMessage     ├ SmsSender
                                                       └ TelegramSender
```

İndi yeni kanal **1** sinifdir, yeni mesaj növü də **1** sinifdir.

## Adapter ilə fərq

İkisi də "bir obyekt başqasını içində saxlayır" kimi görünür. Fərq niyyətdədir:
- **Adapter** sonradan tətbiq olunur: artıq mövcud olan, uyğunsuz kodu birləşdirir.
- **Bridge** əvvəlcədən düşünülür: sistemi müstəqil inkişaf edə bilən iki hissəyə bölür.

## Real həyatda

JDBC bunun ən məşhur nümunəsidir. Sizin kodunuz `Connection`, `Statement` interfeysləri ilə işləyir (abstraksiya). PostgreSQL, MySQL, Oracle driver-ləri isə implementasiyadır. Hər biri ayrıca inkişaf edir.

## Yadda saxla

- İki müstəqil ölçü varsa, varislik əvəzinə onları **kompozisiya** ilə birləşdirin.
- Siniflərin sayı vurma əvəzinə **toplama** ilə artır.
- İmplementasiyanı runtime-da dəyişmək mümkün olur.

## Tapşırıq

1. `WhatsAppSender` əlavə edin. Neçə fayl yaratdınız, neçəsini dəyişdiniz?
2. `ScheduledMessage` növü əlavə edin: mesajın əvvəlinə `[Planned for 09:00]` yazsın.

---

[← 6. Adapter](06-adapter.md) · [Mündəricat](README.md) · Növbəti: [8. Composite →](08-composite.md)
