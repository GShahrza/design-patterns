package com.company.design_patterns.structural.decorator.example1;

public class Main {
    public static void main(String[] args) {
        Phone base = new Iphone();
        Phone pro = new Iphone11Pro(base);
        Phone proMax = new Iphone11ProMax(new Iphone11Pro(new Iphone()));

        print(base);
        print(pro);
        print(proMax);
    }

    private static void print(Phone phone) {
        System.out.printf("%-22s | cameras: %d | price: %.2f%n",
                phone.getName(), phone.cameraCount(), phone.getPrice());
    }
}
