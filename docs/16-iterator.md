# 16. Iterator

[← 15. Interpreter](15-interpreter.md) · [Mündəricat](README.md) · Növbəti: [17. Mediator →](17-mediator.md)

**Qrup:** Behavioral · **Kod:** [`behavioral/iterator`](../src/main/java/com/company/design_patterns/behavioral/iterator)

---

## Həyatdan analogiya

Televizorun pultunda "növbəti kanal" düyməsi var. Siz kanalların televizorun yaddaşında necə saxlandığını (siyahı? cədvəl?) bilmirsiniz və bilməyə də ehtiyacınız yoxdur. Sadəcə "növbəti" basırsınız. Bəzi pultlarda "əvvəlki" və "təsadüfi" düymələri də var: eyni kanallar, fərqli gəzmə üsulu.

## Problem

`Playlist` mahnıları saxlayır. Client-lər onları gəzmək istəyir. Ən sadə yol daxili siyahını açmaqdır:

```java
public List<Song> getSongs() { return songs; }

// client:
for (int i = 0; i < playlist.getSongs().size(); i++) { ... }
```

Bu yanaşmanın problemləri:
- Client daxili strukturu bilir. Sabah `List`-i `Song[]` massivi və ya verilənlər bazası ilə əvəz etsəniz, bütün client-lər sınır.
- Client siyahını dəyişə bilər (`getSongs().clear()`).
- Tərsinə və ya qarışıq gəzmək lazımdırsa, hər client bu məntiqi özü yazmalıdır.

## Həll addım-addım

**Addım 1.** Gəzmənin vəziyyətini (hazırda harada olduğumuzu) ayrıca **iterator** obyektinə çıxarırıq. Java-da bunun üçün hazır interfeys var:

```java
public interface Iterator<T> {
    boolean hasNext();
    T next();
}
```

**Addım 2.** Kolleksiyanın içində iterator sinifləri yazırıq:

```java
private class ReverseIterator implements Iterator<Song> {
    private int index = songs.size() - 1;

    public boolean hasNext() { return index >= 0; }

    public Song next() {
        if (!hasNext()) throw new NoSuchElementException();
        return songs.get(index--);
    }
}
```

İterator `private` daxili sinifdir, ona görə `songs`-u görür. Client isə yalnız `Iterator` interfeysini görür.

**Addım 3.** `Playlist` `Iterable<Song>`-u implement edir və müxtəlif iterator-lar təqdim edir:

```java
public class Playlist implements Iterable<Song> {
    public Iterator<Song> iterator()        { return new SequentialIterator(); }
    public Iterator<Song> reverseIterator() { return new ReverseIterator(); }
    public Iterator<Song> shuffleIterator(long seed) { ... }
}
```

**Nəticə:** `Iterable` olduğu üçün for-each işləyir:

```java
for (Song song : playlist) {     // Java arxada iterator() çağırır
    System.out.println(song.title());
}
```

## Niyə vəziyyət iterator-dadır, kolleksiyada yox?

`index` `Playlist`-də saxlansaydı, eyni anda iki gəzmə mümkün olmazdı. Məsələn, iç-içə dövr:

```java
for (Song a : playlist)
    for (Song b : playlist)   // hər biri öz iterator-u, öz index-i
        compare(a, b);
```

Hər `iterator()` çağırışı yeni obyekt yaradır və onun öz `index`-i olur.

## Tələ: gəzərkən kolleksiyanı dəyişmək

```java
for (String s : list) {
    if (s.isEmpty()) list.remove(s);   // ConcurrentModificationException!
}
```

Düzgün yol iterator-un öz `remove()` metodudur, və ya daha sadəsi: `list.removeIf(String::isEmpty)`.

## JDK-da

Bütün `Collection`-lar `Iterable`-dır. `Scanner` sətirlər üzərində iterator-dur. Java Stream API də daxildə iterator ideyasına (`Spliterator`) söykənir.

## Yadda saxla

- Iterator kolleksiyanın daxilini gizlədir və vahid gəzmə interfeysi verir.
- Gəzmənin vəziyyəti iterator-da saxlanılır, ona görə eyni anda bir neçə gəzmə mümkündür.
- `Iterable` implement etsəniz, for-each pulsuz gəlir.

## Tapşırıq

1. `artistIterator(String artist)` yazın: yalnız həmin ifaçının mahnılarını qaytarsın.
2. Sonsuz `repeatIterator()` yazın: siyahı bitəndə əvvələ qayıtsın. `hasNext()` nə qaytarmalıdır?

---

[← 15. Interpreter](15-interpreter.md) · [Mündəricat](README.md) · Növbəti: [17. Mediator →](17-mediator.md)
