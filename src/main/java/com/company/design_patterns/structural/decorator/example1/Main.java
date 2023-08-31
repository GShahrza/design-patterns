package com.company.design_patterns.structural.decorator.example1;

public class Main {
    public static void main(String[] args) {

        Phone phone = new Iphone11ProMax(new Iphone());

        System.out.println(" Name : " + phone.getName());
        System.out.println(" Price : " + phone.getPrice());
        System.out.println(" Camera count : " + phone.cameraCount());


    }
}
