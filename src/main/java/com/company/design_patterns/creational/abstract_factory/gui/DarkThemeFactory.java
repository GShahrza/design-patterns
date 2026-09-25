package com.company.design_patterns.creational.abstract_factory.gui;

public class DarkThemeFactory implements UiFactory {
    @Override
    public Button createButton() {
        return new DarkButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new DarkCheckbox();
    }
}
