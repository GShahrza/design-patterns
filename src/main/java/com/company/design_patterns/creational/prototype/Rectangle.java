package com.company.design_patterns.creational.prototype;

public class Rectangle extends Shape{

    public Rectangle(){
        type = "Rectangle";
    }

    @Override
    public void draw(){
        System.out.println("Inside Rectangle::draw() method.");
    }
}
