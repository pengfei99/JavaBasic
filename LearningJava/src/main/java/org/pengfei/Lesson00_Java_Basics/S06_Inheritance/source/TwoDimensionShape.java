package org.pengfei.Lesson00_Java_Basics.S06_Inheritance.source;

public class TwoDimensionShape {
    public double width;
    public double height;
    public static final String className="TwoDimensionShape";

    public TwoDimensionShape(double width, double height){
        this.width=width;
        this.height=height;
    }
    // constructor override, with no parameter, set default value for width, and height
    public TwoDimensionShape(){
        this.width=this.height=0.0;
    }
    void showDim(){
        System.out.println("Width is : "+width+" Height is : "+height);
    }

    public void showMessage(){
        System.out.println("This is printed by TwoDimensionShape");
    }
}
