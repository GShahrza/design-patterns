package com.company.design_patterns.creational.singleton;

public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton(){

    }

    public static LazySingleton getInstance(){
        return instance == null ?
                instance = new LazySingleton() : instance;
    }

    public void singletonTest(){
        System.out.println("Lazy Singleton method");
    }
}
