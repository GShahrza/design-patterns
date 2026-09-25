# Singleton

**Kateqoriya:** Creational

> Sinifin yalnız **bir** obyektinin olmasını təmin edir və ona qlobal giriş nöqtəsi verir.

## Problem

Bəzi resurslar (konfiqurasiya, connection pool, logger, cache) proqramda tək nüsxədə olmalıdır. `new` ilə hər yerdə yeni obyekt yaratmaq həm yaddaşı israf edir, həm də vəziyyətin (state) parçalanmasına səbəb olur.

## Həll

Konstruktor `private` edilir, obyekt isə sinfin statik metodu/sahəsi ilə verilir. Bu paketdə 7 üsul var:

| Sinif | Lazy? | Thread-safe? | Qeyd |
|---|---|---|---|
| `EagerInitializationSingleton` | Xeyr | Bəli | Sinif yüklənəndə yaranır, istifadə olunmasa belə |
| `StaticBlockSingleton` | Xeyr | Bəli | Eager + exception handling imkanı |
| `LazySingleton` | Bəli | **Xeyr** | Çox thread-li mühitdə iki obyekt yarana bilər |
| `ThreadSafeSingleton` | Bəli | Bəli | Hər çağırışda `synchronized` — yavaş |
| `DoubleCheckedLockingSingleton` | Bəli | Bəli | `volatile` + kilid yalnız ilk dəfə |
| `BillPughSingleton` | Bəli | Bəli | Daxili statik helper sinfi — tövsiyə olunur |
| `EnumSingleton` | Xeyr | Bəli | Serialization/reflection-a qarşı da qorunur — ən etibarlısı |

## Struktur

| Rol | Bu nümunədə |
|---|---|
| Singleton | hər biri öz-özünə |

## Nə vaxt istifadə etməli

- Obyekt həqiqətən tək olmalıdırsa (məs. hardware resursu, qlobal konfiqurasiya).
- Spring kimi DI framework istifadə edirsinizsə, adətən özünüz Singleton yazmağa ehtiyac yoxdur — bean-lər default olaraq singleton-dur.

## Üstünlüklər

- Tək nüsxə zəmanəti
- Lazy variantlarda resurs yalnız lazım olanda yaranır

## Çatışmazlıqlar

- Qlobal vəziyyət — kodu test etmək çətinləşir (mock etmək çətindir)
- Single Responsibility prinsipini pozur (həm iş görür, həm öz həyat dövrünü idarə edir)
- Səhv yazılsa (məs. `LazySingleton`) multi-thread-də bug verir

## Java / real həyatda

- `java.lang.Runtime.getRuntime()`
- `java.awt.Desktop.getDesktop()`

## İşə salmaq

```bash
mvn -q compile
java -cp target/classes com.company.design_patterns.creational.singleton.Main
```
