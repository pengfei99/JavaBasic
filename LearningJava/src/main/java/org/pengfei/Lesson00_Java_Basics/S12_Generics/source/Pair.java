package org.pengfei.Lesson00_Java_Basics.S12_Generics.source;

public class Pair<T, V extends T> {
    T var1;
    V var2;

    Pair(T var1,V var2){
        this.var1=var1;
        this.var2=var2;
    }
}
