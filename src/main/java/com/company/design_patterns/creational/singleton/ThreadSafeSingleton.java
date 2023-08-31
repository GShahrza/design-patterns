package com.company.design_patterns.creational.singleton;

public class ThreadSafeSingleton {

    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton(){

    }

    public static synchronized ThreadSafeSingleton getInstance(){
        return instance == null ?
                instance = new ThreadSafeSingleton() : instance;
    }

    public void singletonTest(){
        System.out.println("Thread Safe Singleton method");
    }
}
