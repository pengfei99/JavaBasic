package org.pengfei.pattern.prototype;

public class Circle extends Shape {
    public Circle(){
        type="Circle";
    }
    void draw() {
        System.out.println("Draw circle");
    }
}
