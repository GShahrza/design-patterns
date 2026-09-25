package com.company.design_patterns.creational.prototype;

public class Main {
    public static void main(String[] args) {
        ShapeCache.loadCache();

        for (String id : new String[]{"1", "2", "3"}) {
            Shape clonedShape = ShapeCache.getShape(id);
            System.out.print("Shape : " + clonedShape.getType() + " -> ");
            clonedShape.draw();
        }

        // Klon ayrı obyektdir: onu dəyişmək keşdəki prototipə təsir etmir.
        Shape a = ShapeCache.getShape("1");
        Shape b = ShapeCache.getShape("1");
        System.out.println("Same object? " + (a == b));
    }
}
