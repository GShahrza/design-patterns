package com.company.design_patterns.creational.prototype;

import java.util.HashMap;
import java.util.Map;

/** Prototype registry: hazır prototipləri saxlayır və hər sorğuda onların klonunu qaytarır. */
public class ShapeCache {

    private static final Map<String, Shape> SHAPES = new HashMap<>();

    private ShapeCache() {
    }

    public static Shape getShape(String shapeId) {
        Shape cachedShape = SHAPES.get(shapeId);
        if (cachedShape == null) {
            throw new IllegalArgumentException("Unknown shape id: " + shapeId);
        }
        return cachedShape.clone();
    }

    // Real layihədə bu məlumat bazadan (baha əməliyyat) yüklənərdi.
    public static void loadCache() {
        register("1", new Circle());
        register("2", new Square());
        register("3", new Rectangle());
    }

    private static void register(String id, Shape shape) {
        shape.setId(id);
        SHAPES.put(id, shape);
    }
}
