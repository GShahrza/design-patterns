package com.company.design_patterns.creational.abstract_factory.gui;

public class LightThemeFactory implements UiFactory {
    @Override
    public Button createButton() {
        return new LightButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new LightCheckbox();
    }
}
