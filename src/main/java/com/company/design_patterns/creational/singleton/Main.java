package com.company.design_patterns.creational.singleton;

public class Main {
    public static void main(String[] args) {
        EagerInitializationSingleton.getInstance().singletonTest();
        StaticBlockSingleton.getInstance().singletonTest();
        LazySingleton.getInstance().singletonTest();
        ThreadSafeSingleton.getInstance().singletonTest();
        DoubleCheckedLockingSingleton.getInstance().singletonTest();
        BillPughSingleton.getInstance().singletonTest();
        EnumSingleton.INSTANCE.singletonTest();

        System.out.println("Same instance? "
                + (BillPughSingleton.getInstance() == BillPughSingleton.getInstance()));
    }
}
