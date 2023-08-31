package com.company.design_patterns.creational.abstract_factory.factory;

import com.company.design_patterns.creational.abstract_factory.model.Rectangle;
import com.company.design_patterns.creational.abstract_factory.model.RoundedRectangle;
import com.company.design_patterns.creational.abstract_factory.model.RoundedSquare;
import com.company.design_patterns.creational.abstract_factory.model.Shape;

public class RoundedShapeFactory extends AbstractFactory{
    @Override
    public Shape getShape(String shapeType) {
        if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new RoundedRectangle();
        }
        else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new RoundedSquare();
        }
        return null;
    }
}
