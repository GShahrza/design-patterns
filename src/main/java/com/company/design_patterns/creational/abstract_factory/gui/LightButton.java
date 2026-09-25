package com.company.design_patterns.creational.abstract_factory.gui;

public class LightButton implements Button {
    @Override
    public String render() {
        return "[ Light Button ]";
    }
}
