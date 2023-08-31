package com.company.design_patterns.creational.abstract_factory.model;

public class RoundedSquare implements Shape{
    @Override
    public void draw() {
        System.out.println("Inside RoundedSquare::draw() method.");
    }
}
