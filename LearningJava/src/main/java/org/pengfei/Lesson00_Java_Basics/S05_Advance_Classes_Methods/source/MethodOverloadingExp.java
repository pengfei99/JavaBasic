package org.pengfei.Lesson00_Java_Basics.S05_Advance_Classes_Methods.source;

public class MethodOverloadingExp {

    public static void myPrint(String s){
        System.out.println(s);
    }

    public static void myPrint(int i){
        System.out.println(i);
    }

    public static void myPrint(float f){
        System.out.println(f);
    }

    public static void myPrint(String msg,int severity){
        System.out.println("My msg is "+msg+" and has severity "+severity);
    }

}
