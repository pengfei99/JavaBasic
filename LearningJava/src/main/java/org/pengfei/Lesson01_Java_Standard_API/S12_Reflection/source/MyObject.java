package org.pengfei.Lesson01_Java_Standard_API.S12_Reflection.source;

public class MyObject {

    private int age;
    private final String name;

    public MyObject(String name, int age){
        this.name=name;
        this.age=age;
    }

    private void changeAge(int age){
        //System.out.println("This is public method changeAge");
        this.age=age;
    }

    private int m2(){
        System.out.println("This is public method m2");
        return 1;
    }

    protected void showAll(){
        System.out.println("Name: "+this.name+", Age: "+this.age);
    }

    protected void showAge(){
      //  System.out.println("This is protected method showAge");
        System.out.println("Age: " + this.age);
    }

}
