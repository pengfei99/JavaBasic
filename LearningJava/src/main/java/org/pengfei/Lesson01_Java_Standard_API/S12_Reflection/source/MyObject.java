package org.pengfei.Lesson01_Java_Standard_API.S12_Reflection.source;

public class MyObject {

    public void m1(){
        System.out.println("This is public method m1");
    }

    public int m2(){
        System.out.println("This is public method m2");
        return 1;
    }

    private void m3(){
        System.out.println("This is private method m3");
    }

    protected void m4(){
        System.out.println("This is protected method m4");
    }

}
