package com.company.design_patterns.creational.singleton;

/**
 * Lazy initialization: obyekt ilk dəfə lazım olanda yaradılır.
 * DİQQƏT: thread-safe deyil — iki thread eyni anda getInstance() çağırsa, iki obyekt yarana bilər.
 * Yalnız tək thread-li mühit üçün uyğundur.
 */
public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public void singletonTest() {
        System.out.println("Lazy Singleton method");
    }
}
