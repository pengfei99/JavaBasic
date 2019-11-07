package org.pengfei.Lesson00_Java_Basics.S12_Generics.source;

public class GenericConstructorDemo {

    private int sum;

    public <T extends Number> GenericConstructorDemo(T arg){
        sum=0;
        for(int i=0;i<=arg.intValue();i++){
            sum+=i;
        }
    }

    public int getSum(){
        return sum;
    }
}
