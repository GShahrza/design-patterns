# Facade

**Kateqoriya:** Structural

> Mürəkkəb alt sistemlər toplusuna sadə, vahid interfeys verir.

## Problem

Sifariş vermək üçün client anbarı yoxlamalı, ödəniş almalı, anbarda rezerv etməli, çatdırılma yaratmalı və email göndərməlidir — düzgün ardıcıllıqla və xətaları idarə edərək. Hər client bu 4 servisi tanımalı olur.

## Həll

`OrderFacade.placeOrder(...)` bütün iş axınını özündə gizlədir. Client yalnız bir metod çağırır. Alt sistemlər hələ də mövcuddur və lazım olsa birbaşa istifadə edilə bilər — facade onları bağlamır, sadəcə rahat giriş verir.

## Struktur

| Rol | Bu nümunədə |
|---|---|
| Facade | `OrderFacade` |
| Subsystems | `InventoryService, PaymentService, ShippingService, EmailService` |
| Client | `Main` |

## Nə vaxt istifadə etməli

- Mürəkkəb alt sistemə sadə giriş nöqtəsi lazımdırsa.
- Sistemi qatlara (layers) bölmək istəyirsinizsə.

## Üstünlüklər

- Client alt sistemlərin detallarından təcrid olunur
- Asılılıqlar azalır

## Çatışmazlıqlar

- Facade hər şeyi bilən "god object"-ə çevrilə bilər

## Java / real həyatda

- `javax.faces.context.FacesContext`
- Spring-də `JdbcTemplate` (JDBC-nin mürəkkəbliyini gizlədir)

## İşə salmaq

```bash
mvn -q compile
java -cp target/classes com.company.design_patterns.structural.facade.Main
```
