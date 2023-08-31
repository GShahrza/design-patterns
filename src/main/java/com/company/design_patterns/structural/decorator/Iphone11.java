package com.company.design_patterns.structural.decorator;

public class Iphone11 extends  PhoneDecorator{

    public Iphone11(Iphone basicPhone) {
        super(basicPhone);
    }

    @Override
    public String getName() {
        return super.getName() + " 11";
    }
}
