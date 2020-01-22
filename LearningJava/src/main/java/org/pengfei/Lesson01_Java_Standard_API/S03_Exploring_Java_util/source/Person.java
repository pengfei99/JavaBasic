package org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age){
        this.name=name;
        this.age=age;
    }

    public String toString(){
        return "My name is: "+name+" I'm "+age;
    }
}
