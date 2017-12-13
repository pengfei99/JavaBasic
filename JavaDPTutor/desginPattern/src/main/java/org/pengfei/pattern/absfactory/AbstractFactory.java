package org.pengfei.pattern.absfactory;

import org.pengfei.pattern.absfactory.color.Color;
import org.pengfei.pattern.absfactory.shape.Shape;

public abstract class AbstractFactory {
    public abstract Color getColor(String color) throws NoSuchMethodException;
    public abstract Shape getShape(String Shape) throws NoSuchMethodException;
}
