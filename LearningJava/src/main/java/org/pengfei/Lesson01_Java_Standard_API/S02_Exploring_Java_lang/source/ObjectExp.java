package org.pengfei.Lesson01_Java_Standard_API.S02_Exploring_Java_lang.source;

public class ObjectExp {

    public static void exp1(){
        TestClone x1,x2;
        // create one instance
        x1=new TestClone();
        x1.a=8;
        x1.b=88.88;

        // clone x1 to x2
        x2= (TestClone) x1.clone();

        System.out.println("x1: "+x1.a+" "+ x1.b);
        System.out.println("x2: "+x2.a+" "+ x2.b);
    }
}
