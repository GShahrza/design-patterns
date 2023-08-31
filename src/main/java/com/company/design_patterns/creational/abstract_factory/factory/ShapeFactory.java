package com.company.design_patterns.creational.abstract_factory.factory;

import com.company.design_patterns.creational.abstract_factory.model.Rectangle;
import com.company.design_patterns.creational.abstract_factory.model.Shape;
import com.company.design_patterns.creational.abstract_factory.model.Square;

public class ShapeFactory extends AbstractFactory{
    @Override
    public Shape getShape(String shapeType) {
        if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        }
        else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }
}
