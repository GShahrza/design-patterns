package com.company.design_patterns.creational.abstract_factory.factory;

import com.company.design_patterns.creational.abstract_factory.model.Shape;

public abstract class AbstractFactory {
    public abstract Shape getShape(String shapeType);
}
