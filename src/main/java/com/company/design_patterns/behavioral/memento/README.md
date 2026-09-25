# Memento

**Kateqoriya:** Behavioral

> Obyektin daxili vəziyyətini **inkapsulyasiyanı pozmadan** saxlamağa və sonra bərpa etməyə imkan verir.

## Problem

Oyunda "save/load" lazımdır. Əgər `SaveManager` personajın bütün sahələrini birbaşa oxuyub yazsa, sahələri public etmək lazım gələr və personajın daxili strukturu dəyişdikdə `SaveManager` da dəyişməlidir.

## Həll

`GameCharacter` (originator) öz vəziyyətini `Memento` obyektinə yazır. `Memento`-nun sahələri `private`-dır, yalnız `GameCharacter` (xarici sinif) oxuya bilir. `SaveManager` (caretaker) memento-ları saxlayır, amma içinə baxa bilmir.

## Struktur

| Rol | Bu nümunədə |
|---|---|
| Originator | `GameCharacter` |
| Memento | `GameCharacter.Memento` |
| Caretaker | `SaveManager` |

## Nə vaxt istifadə etməli

- Undo, snapshot, checkpoint, tranzaksiyanın geri qaytarılması.

## Üstünlüklər

- İnkapsulyasiya qorunur
- Originator-un kodu sadə qalır

## Çatışmazlıqlar

- Tez-tez snapshot çox yaddaş tuta bilər

## Java / real həyatda

- Oyunlarda save/load, redaktorlarda undo snapshot-ları
- Verilənlər bazasında tranzaksiya rollback-i (konseptual olaraq)

## İşə salmaq

```bash
mvn -q compile
java -cp target/classes com.company.design_patterns.behavioral.memento.Main
```
