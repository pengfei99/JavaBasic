package org.pengfei.Lesson00_Java_Basics.S06_Inheritance.source;

public class Triangle extends TwoDimensionShape {
    public String style;
    public static final String className="Triangle";


    public Triangle(double width, double height, String style) {
        super(width, height);
        this.style=style;
    }

    public Triangle(String style){
        // this super calls the TwoDimensionShape constructor without parameters.
        super();
        this.style=style;
    }

    double area(){
        // All members of super class are available to sub class. Triangle inherited
        return  width*height/2;
    }

    void showStyle(){
        System.out.println("Triangle is "+style);
    }

    public void showClassName(){
        System.out.println("The class name of the current class is :"+this.className);
    }
    public void showSuperClassName(){
        System.out.println("The class name of the current class is :"+super.className);
    }

    public void showMessage(){
        System.out.println("This is printed by Triangle");

    }
    //call the super class showMessage method
    public void showSuperMessage(){
        super.showMessage();
    }
}
