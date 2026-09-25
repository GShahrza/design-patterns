package com.company.design_patterns.creational.singleton;

/**
 * Double-checked locking: kilid yalnız ilk yaradılma zamanı alınır.
 * {@code volatile} mütləqdir — onsuz başqa thread yarımçıq qurulmuş obyekti görə bilər.
 */
public class DoubleCheckedLockingSingleton {

    private static volatile DoubleCheckedLockingSingleton instance;

    private DoubleCheckedLockingSingleton() {
    }

    public static DoubleCheckedLockingSingleton getInstance() {
        DoubleCheckedLockingSingleton result = instance;
        if (result == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                result = instance;
                if (result == null) {
                    instance = result = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return result;
    }

    public void singletonTest() {
        System.out.println("Double-Checked Locking Singleton method");
    }
}
