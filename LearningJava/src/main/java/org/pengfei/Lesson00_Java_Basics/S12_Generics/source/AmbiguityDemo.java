package org.pengfei.Lesson00_Java_Basics.S12_Generics.source;

public class AmbiguityDemo<T,V> {
    T ob1;
    V ob2;

    public AmbiguityDemo(T ob1,V ob2){
        this.ob1=ob1;
        this.ob2=ob2;
    }
    // we can't override a method with "different" generic type. because at run time T, V may
    // be the same type. This will cause ambiguity for JDK.
   // public void set(T o){}

    public void set(V o){}
}
