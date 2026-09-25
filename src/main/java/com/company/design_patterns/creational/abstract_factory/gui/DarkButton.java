package com.company.design_patterns.creational.abstract_factory.gui;

public class DarkButton implements Button {
    @Override
    public String render() {
        return "▐█ Dark Button █▌";
    }
}
