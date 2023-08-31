package com.company.design_patterns.creational.abstract_factory.model;

public class RoundedRectangle implements Shape{
    @Override
    public void draw() {
        System.out.println("Inside RoundedRectangle::draw() method.");
    }
}
