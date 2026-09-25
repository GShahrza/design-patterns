# 13. Chain of Responsibility

[← 12. Proxy](12-proxy.md) · [Mündəricat](README.md) · Növbəti: [14. Command →](14-command.md)

**Qrup:** Behavioral · **Kod:** [`behavioral/chain_of_responsibility`](../src/main/java/com/company/design_patterns/behavioral/chain_of_responsibility)

---

## Həyatdan analogiya

Hava limanında təyyarəyə minməzdən əvvəl bir neçə məntəqədən keçirsiniz: bilet yoxlaması, pasport nəzarəti, təhlükəsizlik skaneri, gate. Hər məntəqə yalnız öz işinə baxır. Problem varsa, sizi saxlayır, yoxdursa, növbəti məntəqəyə buraxır. Pasport işçisi baqajınızı yoxlamır, bu, növbəti məntəqənin işidir.

## Problem

HTTP sorğusu controller-ə çatmazdan əvvəl yoxlanmalıdır:

```java
boolean handle(Request r) {
    if (tooManyRequests(r.clientIp()))                          return false; // 429
    if (r.token() == null)                                      return false; // 401
    if (r.path().startsWith("/admin") && !r.role().equals("ADMIN")) return false; // 403
    // ... sabah: CORS, loglama, IP qara siyahısı...
    return true;
}
```

Metod hər yeni yoxlama ilə böyüyür. Yoxlamaların sırasını dəyişmək, birini söndürmək və ya bir yoxlamanı ayrıca test etmək çətinləşir.

## Həll addım-addım

**Addım 1.** Hər handler-in **növbətiyə** istinadı olan baza sinif yaradırıq:

```java
public abstract class Handler {
    private Handler next;

    public Handler linkWith(Handler next) {
        this.next = next;
        return next;          // zənciri rahat qurmaq üçün
    }

    public abstract boolean handle(Request request);

    protected boolean handleNext(Request request) {
        return next == null || next.handle(request);   // sonuncudursa: uğur
    }
}
```

**Addım 2.** Hər yoxlama ayrıca, kiçik sinifdir və yalnız bir işi görür:

```java
public class AuthenticationHandler extends Handler {
    @Override
    public boolean handle(Request request) {
        if (request.token() == null || request.token().isBlank()) {
            System.out.println("  401 Unauthorized: missing token");
            return false;              // ← zənciri dayandır
        }
        return handleNext(request);    // ← növbətiyə ötür
    }
}
```

**Addım 3.** Zənciri qururuq:

```java
Handler chain = new RateLimitHandler(2);
chain.linkWith(new AuthenticationHandler())
     .linkWith(new AuthorizationHandler());

chain.handle(request);
```

## Nəticə

```
10.0.0.2 /admin/users
  rate limit OK (1/2)
  auth OK
  403 Forbidden: admin role required     ← burada dayandı
  => rejected
```

## Sıra önəmlidir

Niyə `RateLimitHandler` birincidir? Çünki ən ucuz yoxlamadır və hücum zamanı bahalı token yoxlamasına çatmadan sorğuları dayandırır. Zəncirin sırası həm performansa, həm də təhlükəsizliyə təsir edir.

## İki variant

1. **"Dayan və ya ötür"** (bizim nümunə): handler ya sorğunu rədd edir, ya da növbətiyə ötürür. Middleware və filtrlər belə işləyir.
2. **"Kim bacarırsa, o emal etsin"**: sorğu emal edə biləcək birinci handler-ə çatana qədər ötürülür. Klassik nümunə dəstək xidmətidir: operator → mütəxəssis → menecer.

## Real həyatda

- Servlet `Filter` / `FilterChain`: `chain.doFilter(request, response)` növbəti filtri çağırır.
- Spring Security bütünlüklə filtr zənciri üzərində qurulub.
- Java-da exception-ların yayılması da bir növ zəncirdir: `catch` tapılana qədər stack boyunca yuxarı qalxır.

## Yadda saxla

- Hər handler bir işi görür, sonra ya zənciri dayandırır, ya da sorğunu ötürür.
- Göndərən tərəf sorğunu kimin emal edəcəyini bilmir.
- Handler əlavə etmək, silmək və sırasını dəyişmək asandır.

## Tapşırıq

1. `IpBlacklistHandler` yazın və onu zəncirin **əvvəlinə** əlavə edin.
2. `LoggingHandler` yazın: heç vaxt sorğunu dayandırmasın, yalnız çap edib ötürsün. Onu zəncirin ortasına qoyun.

---

[← 12. Proxy](12-proxy.md) · [Mündəricat](README.md) · Növbəti: [14. Command →](14-command.md)
