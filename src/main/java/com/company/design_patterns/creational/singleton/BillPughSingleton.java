package com.company.design_patterns.creational.singleton;

public class BillPughSingleton {

    private BillPughSingleton(){

    }

    public static BillPughSingleton getInstance(){
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public void singletonTest(){
        System.out.println("BillPugh Singleton method");
    }
}
