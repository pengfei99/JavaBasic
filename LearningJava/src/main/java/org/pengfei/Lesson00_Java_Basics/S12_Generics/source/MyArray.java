package org.pengfei.Lesson00_Java_Basics.S12_Generics.source;

public class MyArray<T> implements Containment<T> {

    T[] array;

    public MyArray(T[] array){
        this.array=array;
    }

    @Override
    public boolean contains(T o) {
        for (int i=0;i<array.length;i++){
            if (array[i].equals(o)) {return true;}
        }
        return false;
    }
}
