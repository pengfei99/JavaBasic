package org.pengfei.pattern.factory;

public class ShapeFactory {
    public Shape getShape(String shapeType){
        if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        else if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        }
        else if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        }
        else return null;
    }

    public static void main(String[] args){
        ShapeFactory shapeFactory=new ShapeFactory();
        Shape rectangle=shapeFactory.getShape("rectangle");
        rectangle.draw();
        Shape circle=shapeFactory.getShape("circle");
        circle.draw();
        Shape square=shapeFactory.getShape("square");
        square.draw();
    }
}
