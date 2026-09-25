# 3. Abstract Factory

[← 2. Factory Method](02-factory-method.md) · [Mündəricat](README.md) · Növbəti: [4. Builder →](04-builder.md)

**Qrup:** Creational · **Kod:** [`creational/abstract_factory/gui`](../src/main/java/com/company/design_patterns/creational/abstract_factory/gui)

---

## Həyatdan analogiya

Mebel mağazasına gedirsiniz. Divan, kreslo və jurnal masası alırsınız, hamısı eyni kolleksiyadan: "Klassik" və ya "Modern". Klassik divanın yanına modern kreslo qoysanız, otaq qəribə görünər. Mağaza sizə **dəst** satır, hər əşyanı ayrıca seçmirsiniz.

## Problem

Tətbiqin açıq (Light) və qaranlıq (Dark) teması var. Hər temanın öz düyməsi və checkbox-u var:

```java
Button button = darkMode ? new DarkButton() : new LightButton();
Checkbox checkbox = darkMode ? new DarkCheckbox() : new LightCheckbox();
// ... 20 komponent, 20 dəfə eyni if
```

Hər komponentdə eyni yoxlamanı təkrarlamaq lazımdır. Bir yerdə səhv etsəniz, qaranlıq düymə ilə açıq checkbox yan-yana düşür. Üçüncü tema ("High Contrast") əlavə etsəniz, bütün `if`-ləri tapıb dəyişməlisiniz.

## Həll addım-addım

**Addım 1.** Hər məhsul növü üçün interfeys yaradırıq: `Button`, `Checkbox`.

**Addım 2.** Hər tema üçün konkret məhsullar yazırıq: `LightButton`, `DarkButton`, `LightCheckbox`, `DarkCheckbox`.

**Addım 3.** **Bütün ailəni** yaradan interfeys (*Abstract Factory*) təyin edirik:

```java
public interface UiFactory {
    Button createButton();
    Checkbox createCheckbox();
}
```

**Addım 4.** Hər tema üçün bir factory yazırıq:

```java
public class DarkThemeFactory implements UiFactory {
    public Button createButton()     { return new DarkButton(); }
    public Checkbox createCheckbox() { return new DarkCheckbox(); }
}
```

**Addım 5.** Client yalnız factory-ni alır və heç bir temanın adını bilmir:

```java
public class SettingsDialog {
    private final Button saveButton;
    private final Checkbox notificationsCheckbox;

    public SettingsDialog(UiFactory factory) {
        this.saveButton = factory.createButton();
        this.notificationsCheckbox = factory.createCheckbox();
    }
}
```

Tema seçimi proqramda **bir dəfə**, bir sətirdə olur:

```java
UiFactory factory = darkModeEnabled ? new DarkThemeFactory() : new LightThemeFactory();
new SettingsDialog(factory);
```

Artıq qarışıq dəst yaratmaq **mümkün deyil**, çünki bir factory yalnız öz ailəsinin məhsullarını qaytarır.

## Factory Method ilə fərq

| Factory Method | Abstract Factory |
|---|---|
| **Bir** məhsul yaradır | **Ailə** (bir neçə əlaqəli məhsul) yaradır |
| Varislik: alt sinif metodu override edir | Kompozisiya: factory obyekti ötürülür |

Əslində Abstract Factory-nin hər metodu (`createButton`) özü bir factory method-dur.

## Zəif tərəf

Yeni **tema** əlavə etmək asandır: yeni factory və yeni məhsullar yazırsınız. Amma ailəyə yeni **məhsul növü** (məsələn, `Slider`) əlavə etsəniz, `UiFactory` interfeysi dəyişir və **bütün** factory-ləri yeniləməlisiniz.

## Repodakı köhnə nümunə haqqında

`abstract_factory` paketinin kökündə `ShapeFactory` və `RoundedShapeFactory` var. Onlar yalnız bir növ məhsul (`Shape`) qaytarır, ona görə əslində "iki Simple Factory"-dir. Müqayisə üçün saxlanılıb: ailə olmayanda Abstract Factory-nin mənası azalır.

## JDK-da

`DocumentBuilderFactory.newInstance()`. Hansı XML parser implementasiyasının istifadə olunacağını mühit seçir, siz isə yalnız interfeyslərlə işləyirsiniz.

## Yadda saxla

- Abstract Factory **əlaqəli obyektlər ailəsi** üçündür.
- Uyğunsuz qarışıqların qarşısını alır.
- Yeni ailə əlavə etmək asandır, yeni məhsul növü əlavə etmək çətindir.

## Tapşırıq

1. `HighContrastThemeFactory` əlavə edin (məsələn, `[[ BUTTON ]]` kimi göstərsin).
2. Ailəyə `TextField` əlavə edin. Neçə faylı dəyişmək lazım gəldi? Bu, pattern-in zəif tərəfini göstərir.

---

[← 2. Factory Method](02-factory-method.md) · [Mündəricat](README.md) · Növbəti: [4. Builder →](04-builder.md)
