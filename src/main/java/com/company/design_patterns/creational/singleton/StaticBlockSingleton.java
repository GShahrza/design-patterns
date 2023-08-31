package com.company.design_patterns.creational.singleton;

public class StaticBlockSingleton {

    private static final StaticBlockSingleton instance;

    static {
        try {
            instance = new StaticBlockSingleton();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private StaticBlockSingleton(){

    }

    public static  StaticBlockSingleton getInstance(){
        return instance;
    }

    public void singletonTest(){
        System.out.println("Static Block Singleton method");
    }
}
