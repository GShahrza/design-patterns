package com.company.design_patterns.structural.decorator.example1;

/** Decorator: "Max" — daha böyük ekran, qiymət artımı. */
public class Iphone11ProMax extends PhoneDecorator {

    public Iphone11ProMax(Phone basicPhone) {
        super(basicPhone);
    }

    @Override
    public String getName() {
        return super.getName() + " Max";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 100;
    }
}
