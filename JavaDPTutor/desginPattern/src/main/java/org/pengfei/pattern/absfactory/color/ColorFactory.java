package org.pengfei.pattern.absfactory.color;

import org.pengfei.pattern.absfactory.AbstractFactory;
import org.pengfei.pattern.absfactory.shape.Shape;

public class ColorFactory extends AbstractFactory{
    public Color getColor(String color){
        if(color.equalsIgnoreCase("RED")){
            return new Red();
        }
        else if(color.equalsIgnoreCase("GREEN")){
            return new Green();
        }
        else if(color.equalsIgnoreCase("BLUE")){
            return new Blue();
        }
        else throw new IllegalArgumentException("Color must be red, green or blue");
    }

    public Shape getShape(String Shape) throws NoSuchMethodException {
        throw new NoSuchMethodException("Color factory does not support getShape");
    }
}
