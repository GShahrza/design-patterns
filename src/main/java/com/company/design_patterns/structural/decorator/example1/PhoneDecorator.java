package com.company.design_patterns.structural.decorator.example1;

/** Base decorator: bütün çağırışları bükdüyü (wrap) obyektə ötürür. */
public abstract class PhoneDecorator implements Phone {

    protected final Phone basicPhone;

    protected PhoneDecorator(Phone basicPhone) {
        this.basicPhone = basicPhone;
    }

    @Override
    public String getName() {
        return basicPhone.getName();
    }

    @Override
    public int cameraCount() {
        return basicPhone.cameraCount();
    }

    @Override
    public double getPrice() {
        return basicPhone.getPrice();
    }
}
