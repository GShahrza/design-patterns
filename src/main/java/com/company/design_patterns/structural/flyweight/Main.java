package com.company.design_patterns.structural.flyweight;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String[][] kinds = {
                {"Oak", "green", "oak.png"},
                {"Pine", "dark-green", "pine.png"},
                {"Birch", "white", "birch.png"}
        };

        Forest forest = new Forest();
        Random random = new Random(42);
        for (int i = 0; i < 100_000; i++) {
            String[] k = kinds[random.nextInt(kinds.length)];
            forest.plant(random.nextInt(1000), random.nextInt(1000), k[0], k[1], k[2]);
        }

        forest.draw(3);
        System.out.println("...");
        System.out.println("Trees planted: " + forest.size());
        System.out.println("TreeType objects in memory: " + TreeTypeFactory.cachedTypes());
    }
}
