package org.pengfei.pattern.absfactory.shape;

import org.pengfei.pattern.absfactory.AbstractFactory;
import org.pengfei.pattern.absfactory.color.Color;

public class ShapeFactory extends AbstractFactory{

    @Override
    public Color getColor(String color) throws NoSuchMethodException {
        throw new NoSuchMethodException("Shape factory does not support getColor");
    }

    @Override
    public Shape getShape(String shapeType){
        if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        else if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        }
        else if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        }
        else throw new IllegalArgumentException("Shape must be a square, circle or rectangle");
    }

}
