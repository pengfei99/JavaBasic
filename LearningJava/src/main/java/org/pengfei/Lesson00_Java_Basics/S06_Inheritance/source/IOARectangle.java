package org.pengfei.Lesson00_Java_Basics.S06_Inheritance.source;

public class IOARectangle extends AbstractTwoDShape {

    public IOARectangle(){
        super();
    }

    public IOARectangle(double width, double height){
        super(width,height,"Rectangle");
    }

    @Override
    public double area() {
        return getWidth()*getHeight();
    }


}
