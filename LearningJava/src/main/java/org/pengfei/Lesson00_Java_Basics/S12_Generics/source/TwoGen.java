package org.pengfei.Lesson00_Java_Basics.S12_Generics.source;

// A simple generic class with two types, we use T and V as type parameter
public class TwoGen<T,V> {
    T ob1;
    V ob2;

    public TwoGen(T ob1,V ob2){
        this.ob1=ob1;
        this.ob2=ob2;
    }

    public void showType(){
        System.out.println("Type of T is: "+ob1.getClass().getName());
        System.out.println("Type of V is: "+ob2.getClass().getName());
    }

    public T getOb1() {
        return ob1;
    }

    public V getOb2() {
        return ob2;
    }

    public boolean isSame(TwoGen<T,V> o){
        if(ob1==o.ob1&&ob2==o.ob2) return true;
        else return false;
    }
}
