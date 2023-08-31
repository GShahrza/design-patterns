package com.company.design_patterns.creational.abstract_factory.factory;

public class FactoryProducer {

    public static AbstractFactory getFactory(boolean rounded){
        if(rounded){
            return new RoundedShapeFactory();
        }
        else {
            return new ShapeFactory();
        }
    }
}
