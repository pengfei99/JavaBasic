package org.pengfei.Lesson00_Java_Basics.S12_Generics.source;

public class GenericRestriction<T extends Number> {

    T ob;

    T values[];

    GenericRestriction(T o, T[] nums){
        ob=o;
        // This works, because nums is an argument, which is a reference point to an existent array
        values=nums;
        // can't create an instance of array of type T. compile error
        // The reason you can’t create an array of T is that there is no way for the compiler to
        // know what type of array to actually create.
        // values=new T[10];
    }
}
