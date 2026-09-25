package com.company.design_patterns.structural.decorator.example1;

/** Decorator: "Pro" versiyası — +1 kamera və qiymət artımı. */
public class Iphone11Pro extends PhoneDecorator {

    public Iphone11Pro(Phone basicPhone) {
        super(basicPhone);
    }

    @Override
    public String getName() {
        return super.getName() + " Pro";
    }

    @Override
    public int cameraCount() {
        return super.cameraCount() + 1;
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 300;
    }
}
