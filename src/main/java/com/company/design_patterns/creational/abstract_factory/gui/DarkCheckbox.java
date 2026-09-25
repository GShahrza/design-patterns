package com.company.design_patterns.creational.abstract_factory.gui;

public class DarkCheckbox implements Checkbox {
    @Override
    public String render(boolean checked) {
        return (checked ? "■" : "□") + " dark checkbox";
    }
}
