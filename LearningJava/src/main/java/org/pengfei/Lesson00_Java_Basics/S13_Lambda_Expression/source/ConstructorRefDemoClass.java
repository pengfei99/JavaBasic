package org.pengfei.Lesson00_Java_Basics.S13_Lambda_Expression.source;

public class ConstructorRefDemoClass {
    private String str;

    ConstructorRefDemoClass(){
        this.str="";
    }

    ConstructorRefDemoClass(String str){
        this.str=str;
    }

    public String getStr() {
        return str;
    }
}
