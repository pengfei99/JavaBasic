package org.pengfei.pattern.absfactory;

import org.pengfei.pattern.absfactory.color.Color;
import org.pengfei.pattern.absfactory.color.ColorFactory;
import org.pengfei.pattern.absfactory.shape.Shape;
import org.pengfei.pattern.absfactory.shape.ShapeFactory;

public class FactoryProducer {
    public static AbstractFactory getFactory(String choise){
        if(choise.equalsIgnoreCase("SHAPE")){
            return new ShapeFactory();
        }
        else if(choise.equalsIgnoreCase("COLOR")){
            return new ColorFactory();
        }
        else throw new IllegalArgumentException("The argument must be shape or color");
    }

}
