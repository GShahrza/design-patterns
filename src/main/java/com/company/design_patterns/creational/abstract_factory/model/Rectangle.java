package com.company.design_patterns.creational.abstract_factory.model;

public class Rectangle implements Shape{
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
