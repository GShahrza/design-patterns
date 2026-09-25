# 8. Composite

[← 7. Bridge](07-bridge.md) · [Mündəricat](README.md) · Növbəti: [9. Decorator →](09-decorator.md)

**Qrup:** Structural · **Kod:** [`structural/composite`](../src/main/java/com/company/design_patterns/structural/composite)

---

## Həyatdan analogiya

Ordu strukturunu düşünün. General əmri diviziyalara verir, diviziya komandiri alaylara, alay komandiri bölüklərə və nəhayət, əsgərlərə. General hər əsgərə ayrıca zəng vurmur. O, "sənin tabeliyindəkilər hücuma keçsin" deyir və əmr ağac boyunca aşağı yayılır. Əmri alan kəs tək əsgərdirsə, özü icra edir, komandirdirsə, tabeliyindəkilərə ötürür.

## Problem

Fayl sistemində qovluğun ölçüsünü hesablamaq lazımdır. Qovluğun içində fayllar və başqa qovluqlar ola bilər, onların içində də yenə qovluqlar.

```java
long size(Object node) {
    if (node instanceof File f) {
        return f.size;
    } else if (node instanceof Directory d) {
        long total = 0;
        for (Object child : d.children) total += size(child);
        return total;
    }
    throw new IllegalArgumentException();
}
```

Hər əməliyyat üçün (ölçü, çap, axtarış) bu `instanceof` yoxlamasını təkrarlamaq lazımdır. Yeni element növü (məsələn, `Shortcut`) əlavə etsəniz, hamısını tapıb dəyişməlisiniz.

## Həll addım-addım

**Addım 1.** Həm fayl, həm qovluq üçün **ümumi interfeys** yaradırıq:

```java
public interface FileSystemNode {
    String getName();
    long getSize();
    void print(String indent);
}
```

**Addım 2.** *Leaf* (yarpaq), yəni övladı olmayan element, işi özü görür:

```java
public class File implements FileSystemNode {
    public long getSize() {
        return size;
    }
}
```

**Addım 3.** *Composite*, yəni övladları olan element, işi övladlarına ötürür və nəticəni birləşdirir:

```java
public class Directory implements FileSystemNode {
    private final List<FileSystemNode> children = new ArrayList<>();

    public long getSize() {
        return children.stream().mapToLong(FileSystemNode::getSize).sum();
    }
}
```

Burada sehr var: `Directory` övladının fayl, yoxsa qovluq olduğunu **bilmir və bilməli də deyil**. O, sadəcə `getSize()` çağırır. Övlad qovluqdursa, o da öz növbəsində övladlarına müraciət edir. Rekursiya özü-özünə baş verir.

**İstifadə:**

```java
Directory root = new Directory("project")
        .add(new File("README.md", 4))
        .add(new Directory("src")
                .add(new File("Main.java", 12))
                .add(new File("Util.java", 8)));

root.getSize();   // 24 KB, client üçün bir çağırış
```

## Nəticə

```
+ project/ (277 KB)
   - README.md (4 KB)
   + src/ (20 KB)
      - Main.java (12 KB)
      - Util.java (8 KB)
   + assets/ (253 KB)
      ...
```

## Dizayn dilemması: `add()` harada olmalıdır?

`add()` metodunu `FileSystemNode` interfeysinə qoysaq, client hamı ilə tam eyni cür işləyə bilər. Amma o zaman `File.add()` nə etməlidir? Exception atmalıdır, bu isə qəribədir. Bizim nümunədə `add()` yalnız `Directory`-dədir. Bu variant daha təhlükəsizdir, amma bir az daha az "şəffafdır". Hər iki yanaşma rast gəlinir.

## Harada görəcəksiniz

- UI: `JPanel` içində düymələr və başqa panellər. Hamısı `Component`-dir.
- Menyu: bəndlər və alt menyular.
- Təşkilat strukturu: işçilər və şöbələr.
- HTML DOM ağacı.

## Yadda saxla

- Composite ağac strukturları üçündür.
- Leaf ilə Composite eyni interfeysə malikdir, client onları fərqləndirmir.
- Composite əməliyyatı övladlarına ötürür, nəticədə rekursiya təbii şəkildə baş verir.

## Tapşırıq

1. `FileSystemNode`-a `int countFiles()` metodu əlavə edin.
2. `List<File> find(String extension)` yazın: məsələn, bütün `.java` fayllarını qaytarsın.

---

[← 7. Bridge](07-bridge.md) · [Mündəricat](README.md) · Növbəti: [9. Decorator →](09-decorator.md)
