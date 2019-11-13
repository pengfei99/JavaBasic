package org.pengfei.Lesson00_Java_Basics.S13_Lambda_Expression.source;

public class MyIntNum {
    private int number;

    //Constructor
    MyIntNum(int number){
        this.number=number;
    }

    public int getNumber() {
        return number;
    }

    // return true if arg is a factor of number
    public boolean isFactor(int arg){
        return (number % arg) ==0;
    }

}
