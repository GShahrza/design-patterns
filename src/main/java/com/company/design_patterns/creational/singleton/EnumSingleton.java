package com.company.design_patterns.creational.singleton;

/**
 * Enum singleton (Joshua Bloch, "Effective Java"): ən sadə və ən etibarlı üsul.
 * JVM tərəfindən thread-safe-dir, serialization və reflection ilə ikinci obyekt yaratmaq olmur.
 */
public enum EnumSingleton {
    INSTANCE;

    private int counter;

    public synchronized int incrementAndGet() {
        return ++counter;
    }

    public void singletonTest() {
        System.out.println("Enum Singleton method");
    }
}
