package com.company.design_patterns.creational.abstract_factory.gui;

/** Client: yalnız abstrakt interfeyslərlə işləyir, konkret temadan xəbəri yoxdur. */
public class SettingsDialog {

    private final Button saveButton;
    private final Checkbox notificationsCheckbox;

    public SettingsDialog(UiFactory factory) {
        this.saveButton = factory.createButton();
        this.notificationsCheckbox = factory.createCheckbox();
    }

    public String render() {
        return notificationsCheckbox.render(true) + System.lineSeparator() + saveButton.render();
    }
}
