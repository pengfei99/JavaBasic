package org.pengfei.Lesson00_Java_Basics.S11_Autoboxing_Enum_Annotation.source;

public class AutoboxingExp {

    public static void exp1(){
        //create an object of Integer with a value, aka. boxing
        Integer iOb=Integer.valueOf(100);
        // extract the value from an object, aka. unboxing
        int a = iOb.intValue();
        System.out.println(a+" "+iOb);
    }


    public static void exp2(){
        //autoboxing
        Integer iOb=100;

        //autounboxing
        int a=iOb;

        System.out.println(a+" "+iOb);

    }
}
