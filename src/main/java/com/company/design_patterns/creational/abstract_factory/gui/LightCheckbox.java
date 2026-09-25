package com.company.design_patterns.creational.abstract_factory.gui;

public class LightCheckbox implements Checkbox {
    @Override
    public String render(boolean checked) {
        return (checked ? "[x]" : "[ ]") + " light checkbox";
    }
}
