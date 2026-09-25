package com.company.design_patterns.creational.abstract_factory.gui;

public class Main {
    public static void main(String[] args) {
        boolean darkModeEnabled = true;
        UiFactory factory = darkModeEnabled ? new DarkThemeFactory() : new LightThemeFactory();
        System.out.println(new SettingsDialog(factory).render());

        System.out.println();
        System.out.println(new SettingsDialog(new LightThemeFactory()).render());
    }
}
