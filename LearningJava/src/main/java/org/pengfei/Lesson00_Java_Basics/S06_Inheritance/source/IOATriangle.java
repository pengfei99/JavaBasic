package org.pengfei.Lesson00_Java_Basics.S06_Inheritance.source;

public class IOATriangle extends AbstractTwoDShape {

    private String style;

    public IOATriangle(String style){
        super();
        this.style=style;
    }

    public IOATriangle(double width,double height,String style){
        super(width,height,"Triangle");
        this.style=style;
    }

    @Override
    /** Implementation of the abstract method, as the field is private, need to use getter method of the
     * super class to access these fields.*/
    public double area() {

        return getWidth()*getHeight()/2;
    }

    /** can't override final method, compile-time error*/
    //public void showDim(){}
}
