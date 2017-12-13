package org.pengfei.pattern.absfactory;

import org.pengfei.pattern.absfactory.color.Color;
import org.pengfei.pattern.absfactory.shape.Shape;

public class main {
    public static void main(String [] args) throws NoSuchMethodException {
        AbstractFactory shapFactory = FactoryProducer.getFactory("Shape");
        AbstractFactory colorFactory = FactoryProducer.getFactory("color");

        Color green = colorFactory.getColor("green");
        green.fill();
        Shape circle = shapFactory.getShape("circle");
        circle.draw();


    }
}
