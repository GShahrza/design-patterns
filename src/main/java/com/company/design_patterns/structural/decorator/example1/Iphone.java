package com.company.design_patterns.structural.decorator.example1;

/** Concrete component: bazaya aid telefon, dekoratorlar bunun üzərinə xüsusiyyət əlavə edir. */
public class Iphone implements Phone {

    @Override
    public String getName() {
        return "iPhone 11";
    }

    @Override
    public int cameraCount() {
        return 2;
    }

    @Override
    public double getPrice() {
        return 699.99;
    }
}
