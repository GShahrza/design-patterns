package com.company.design_patterns.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/** Flyweight factory: eyni parametrlərlə soruşulan TreeType-ı yenidən yaratmır, keşdən qaytarır. */
public final class TreeTypeFactory {

    private static final Map<String, TreeType> TYPES = new HashMap<>();

    private TreeTypeFactory() {
    }

    public static TreeType get(String name, String color, String texture) {
        String key = name + "|" + color + "|" + texture;
        return TYPES.computeIfAbsent(key, k -> new TreeType(name, color, texture));
    }

    public static int cachedTypes() {
        return TYPES.size();
    }
}
