package org.pengfei.Lesson00_Java_Basics.S06_Inheritance.source;

public class ColorTriangle extends Triangle{

    private String color;

    public ColorTriangle(double width, double height, String style, String color) {
        super(width, height, style);
        this.color=color;
    }

    public ColorTriangle(String style, String color) {
        super(style);
        this.color=color;
    }

    public String getColor() {
        return color;
    }

    public void showColor() {
        System.out.println("Color of the triangle is : "+this.color);
    }

    public void showMessage(){
        System.out.println("This is printed by ColorTriangle");
    }
}
