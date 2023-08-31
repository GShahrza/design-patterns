package com.company.design_patterns.creational.abstract_factory.model;

public class Square implements Shape{
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
