package com.company.design_patterns.structural.decorator.example1;

public class Iphone implements Phone{

    @Override
    public String getName() {
        return "Iphone 11 ";
    }

    @Override
    public int cameraCount() {
        return 2;
    }

    @Override
    public double getPrice() {
        return 999.99;
    }
}
