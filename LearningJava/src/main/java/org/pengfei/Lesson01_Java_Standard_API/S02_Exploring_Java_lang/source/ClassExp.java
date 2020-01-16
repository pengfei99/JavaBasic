package org.pengfei.Lesson01_Java_Standard_API.S02_Exploring_Java_lang.source;

import java.lang.reflect.Field;

public class ClassExp {
    public static void exp1(){
        X x=new X();
        Y y=new Y();

        Class<? extends X> xcl = x.getClass();
        System.out.println("x is object of type: "+xcl.getName());

        for(Field f:xcl.getFields()) {System.out.println("x has field: "+f.toString());}

        Class<? extends Y> ycl = y.getClass();
        System.out.println("y is object of type: "+ycl.getName());
        System.out.println("y's superclass is object of type: "+ycl.getSuperclass().getName());
    }
}
