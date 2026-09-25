package com.company.design_patterns.creational.abstract_factory.gui;

/**
 * Abstract Factory: bir-biri ilə uyğun gələn məhsullar AİLƏSİNİ yaradır.
 * Client bir factory seçir və bütün komponentlər avtomatik eyni temada olur —
 * "dark button + light checkbox" kimi uyğunsuz qarışıq yarana bilməz.
 */
public interface UiFactory {
    Button createButton();

    Checkbox createCheckbox();
}
