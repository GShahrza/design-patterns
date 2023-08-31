package com.company.design_patterns.creational.singleton;

public class Main {
    public static void main(String[] args) {
        EagerInitializationSingleton.getInstance().singletonTest();

        StaticBlockSingleton.getInstance().singletonTest();

        LazySingleton.getInstance().singletonTest();

        ThreadSafeSingleton.getInstance().singletonTest();

        BillPughSingleton.getInstance().singletonTest();
    }
}
