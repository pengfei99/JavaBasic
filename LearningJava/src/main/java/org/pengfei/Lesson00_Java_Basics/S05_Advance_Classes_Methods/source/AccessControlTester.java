package org.pengfei.Lesson00_Java_Basics.S05_Advance_Classes_Methods.source;

public class AccessControlTester extends AccessControlExp {

    public static  void exp1(){
        int b = AccessControlExp.b;
        int c = AccessControlExp.c;
        int d= AccessControlExp.d;

        System.out.println("b value: "+b+" c value: "+c+" d value"+d);
    }
}
