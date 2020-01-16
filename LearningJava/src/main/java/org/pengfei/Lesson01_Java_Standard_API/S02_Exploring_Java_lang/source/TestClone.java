package org.pengfei.Lesson01_Java_Standard_API.S02_Exploring_Java_lang.source;

public class TestClone implements Cloneable {
    int a;
    double b;

    // override clone() method to make it public
    public Object clone(){
        try{
            // as we call super, we actually use the implementation of Object
            return super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Cloning not allowed");
            return this;
        }
    }
}
