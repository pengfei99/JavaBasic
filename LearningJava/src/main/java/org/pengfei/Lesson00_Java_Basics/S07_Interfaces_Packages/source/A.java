package org.pengfei.Lesson00_Java_Basics.S07_Interfaces_Packages.source;

public interface A {

    // define a default method
     default void reset(){
        System.out.println("This is the default method of A");
    }

    // define a static method
    static int getInterfaceId(){
         return 88;
    }
}
