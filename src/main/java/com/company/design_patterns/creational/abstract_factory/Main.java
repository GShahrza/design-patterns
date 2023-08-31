package com.company.design_patterns.creational.abstract_factory;

import com.company.design_patterns.creational.abstract_factory.factory.AbstractFactory;
import com.company.design_patterns.creational.abstract_factory.factory.FactoryProducer;
import com.company.design_patterns.creational.abstract_factory.model.Shape;

public class Main {
    public static void main(String[] args) {
        AbstractFactory shapeFactory = FactoryProducer.getFactory(false);

        Shape shape1 = shapeFactory.getShape("RECTANGLE");
        shape1.draw();

        Shape shape2 = shapeFactory.getShape("SQUARE");
        shape2.draw();

        AbstractFactory roundedShapeFactory = FactoryProducer.getFactory(true);

        Shape roundedShape1 = roundedShapeFactory.getShape("RECTANGLE");
        roundedShape1.draw();

        Shape roundedShape2 = roundedShapeFactory.getShape("SQUARE");
        roundedShape2.draw();
    }
}
