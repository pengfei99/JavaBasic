package org.pengfei.Lesson00_Java_Basics.S06_Inheritance.source;

public abstract class AbstractTwoDShape {
    private double width;
    private double height;
    private  String name;

     AbstractTwoDShape(){
        width=height=0.0;
        name="none";
    }

    AbstractTwoDShape(double width,double height,String name){
        this.width=width;
        this.height=height;
        this.name=name;
    }

    AbstractTwoDShape(AbstractTwoDShape ob){
         this.width=ob.width;
         this.height=ob.height;
         this.name=ob.name;
    }

   public final void showDim(){
        System.out.println("Width is : "+width+" Height is : "+height);
    }

    /** declare an abstract method */
    abstract double area();

    /*Getter and setter methods for private fields*/
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
