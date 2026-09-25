package com.company.design_patterns.creational.singleton;

/**
 * Bütün metod synchronized-dir: thread-safe-dir, amma hər çağırışda kilid (lock) alındığı üçün
 * yavaşdır. Daha yaxşı variantlar: {@link DoubleCheckedLockingSingleton}, {@link BillPughSingleton},
 * {@link EnumSingleton}.
 */
public class ThreadSafeSingleton {

    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {
    }

    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }

    public void singletonTest() {
        System.out.println("Thread Safe Singleton method");
    }
}
