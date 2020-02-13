package org.pengfei.Lesson01_Java_Standard_API.S04_Exploring_Java_io.source.serialization;

import java.io.Serializable;

public class MySerializedClass implements Serializable {
    String name;
    int age;
    double height;
    public MySerializedClass(String name,int age,double height){
        this.name=name;
        this.age=age;
        this.height=height;
    }
    public String toString(){
        return "My name is: "+name+"\nMy age is: "+age+"\nMy height is: "+height;
    }
}
