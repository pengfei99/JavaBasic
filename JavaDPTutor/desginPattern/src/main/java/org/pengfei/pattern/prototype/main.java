package org.pengfei.pattern.prototype;

/*
* Prototype pattern refers to creating duplicate object while keeping performance in mind.
* This type of design pattern comes under creational pattern as this pattern provides one of
* the best ways to create an object.
* This pattern involves implementing a prototype interface which tells to create a clone of
* the current object. This pattern is used when creation of object directly is costly.
* For example, an object is to be created after a costly database operation.
* We can cache the object, returns its clone on next request and update the database as
* and when needed thus reducing database calls.
* */

public class main {

    public static void main(String[] args) throws CloneNotSupportedException {
        ShapeCache.loadCache();
        Shape clonedShape = (Shape) ShapeCache.getShape("1");
        System.out.println("Shape 1: " + clonedShape.getType());

        Shape clonedShape2 = (Shape) ShapeCache.getShape("2");
        System.out.println("Shape 2: " + clonedShape2.getType());

        Shape clonedShape3 = (Shape) ShapeCache.getShape("3");
        System.out.println("Shape 3: " + clonedShape3.getType());

        Shape clonedShape4 = (Shape) ShapeCache.getShape("1");
        System.out.println("Shape 4: " + clonedShape4.getType());

    }
}
