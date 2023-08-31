package com.company.design_patterns.creational.singleton;

public class EagerInitializationSingleton {

    private static final EagerInitializationSingleton INSTANCE = new EagerInitializationSingleton();

    private EagerInitializationSingleton(){

    }

    public static EagerInitializationSingleton getInstance(){
        return INSTANCE;
    }

    public void singletonTest(){
        System.out.println("Eager Singleton method");
    }
}
