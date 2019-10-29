package org.pengfei.Lesson00_Java_Basics.S07_Interfaces_Packages.source;

public interface B extends A {

    public default void reset(){
        System.out.println("This is the default method of B");

    }

    public default void getAReset(){
        A.super.reset();
    }
}
