package org.pengfei.Lesson00_Java_Basics.S06_Inheritance.source;

public class Rectangle extends TwoDimensionShape {

    public Rectangle(){
        super();
    }

    public Rectangle(double width, double height){
        super(width,height);
    }

    public double area(){
        return width*height;
    }
}
