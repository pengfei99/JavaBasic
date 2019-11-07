package org.pengfei.Lesson00_Java_Basics.S12_Generics.source;

public class SimpleGen<T> {
    // declare an object of type T, and T will be specified when a SimpleGen object is created. Thus object will have
    // that specified type T. For example, if T is passed to String, then in that instance, object will be String too.
    T object;

    // Constructor is generic, it only knows the actual type of T after a SimpleGen object is created.
    public SimpleGen(T object){
        this.object=object;
    }

    // the return type is generic T
    public T getObject(){
        return object;
    }

    // This method displays the type of T by calling object.getClass().getName. getClass is defined in Class "Object" and
    // returns a "Class" object. This "Class" object corresponds to the class type of the object on which it is called.
    // "Class" is a class defined within java.lang that encapsulates information about a class. Class defines several
    // methods that can be used to obtain information about a class at run time. Among these is the getName( ) method,
    // which returns a string representation of the class name.
    public void showType(){
        System.out.println("Type of T is: "+object.getClass().getName());
    }

    // this method takes a SimpleGen ob as argument, the generic type is a bounded wildcard, which is a subclass
    // of class A
    public void boundedWildcardsArgsTest(SimpleGen<? extends SuperClass> ob){
        System.out.println("In method boundedWildcardsArgsTest");
    }

    public void lowerBoundWildArgsTest(SimpleGen<? super SubClass1> ob){
        System.out.println("lowerBoundWildArgsTest");

    }
}
