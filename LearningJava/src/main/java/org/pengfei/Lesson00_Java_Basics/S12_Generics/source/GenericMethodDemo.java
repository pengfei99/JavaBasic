package org.pengfei.Lesson00_Java_Basics.S12_Generics.source;

public class GenericMethodDemo {

    /*
    * Notice the generic type definition is declared before method return type. Here, we define T
    * extends Comparable<T>(an interface in java.lang which takes generic type). In another word,
    * Comparable interface is the upper bound of T. All object of class which implements Comparable
    * interface can be used here as arguments. Comparable is generic, and its type parameter specifies
    * the type of objects that it compares.
    *
    * Next, notice that the type V is upper­bounded by T. Thus, V must be either the same as type T or a
    * subclass of T. This relationship enforces that arraysEqual( ) can be called only with arguments that
    * are comparable with each other.
    * */
    public static <T extends Comparable<T>, V extends T> boolean arraysEqual(T[] x, V[] y){
       //1. check two arrays length, if different, then two arrays differs
       if(x.length!=y.length) return false;

       //2. check equality of each element in the array
        for(int i=0;i<x.length;i++){
            // don't need break here, return will break the loop automatically
            if (!x[i].equals(y[i])) return false;
        }
        // if 1, 2 pass, then two array is equal.
        return true;
    }
}
