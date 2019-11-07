package org.pengfei.Lesson00_Java_Basics.S12_Generics.source;

// A generic interface that implies if a class contains one or more specific values
// Notice T is the type identifier which specifies the type of objects.
public interface Containment<T> {

    // The contains method test if a specific item is contained within an object that implements interface Containment
    boolean contains(T o);
}
