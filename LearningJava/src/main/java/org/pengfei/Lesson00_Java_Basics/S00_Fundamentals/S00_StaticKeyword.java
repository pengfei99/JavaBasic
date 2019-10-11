package org.pengfei.Lesson00_Java_Basics.S00_Fundamentals;

public class S00_StaticKeyword {

    /************************************** Introduction ***********************************************/

    /*
    * In this section, we will see the static keyword of the Java language in detail. We'll find how we can apply
    * keyword static to variables, methods, blocks, nested classes and what difference it makes.*/

    /* The "static" keyword indicates that the particular member (in our case, it's the main method) belongs to a
     * type(Class) itself, rather than to an instance of that type(Class). This means that only one instance of
     * that static member is created which is shared across all instances of the class.
     *
     * The "static" keyword can be applied to variables, methods, blocks and nested class.
     * */

   /*************************************** 1. Static fields inside a class *******************************/

   /*
   * In Java, if a field is declared static, then exactly a single copy of that field is created and shared among
   * all instances of that class
   *
   * It doesn't matter how many times we initialize a class; there will always be only one copy of static field
   * belonging to it. The value of this static field will be shared across all object of either same of any different
   * class.
   *
   * From the memory perspective, "static variables go in a particular pool in JVM memory called Metaspace" (before
   * Java 8, this pool was called Permanent Generation or PermGen, which was completely removed and replaced with
   * Metaspace).
   *
   * */

   /* Example of the static Field
   * Suppose we have a Car class with several attributes (instance variables). Whenever new objects are initialized
   * from this Car blueprint, each new object will have its distinct copy of these instance variables.
   *
   * However, suppose we are looking for a variable that holds the count of the number of Car objects that are
   * initialized and is shared across all instances such that they can access it and increment it upon their
   * initialization.
   *
   * Check the Car in the source package,  you can notice how objects share and increments the static field.
   * */

/***************************************** Key points to Remember of static fields ***************************************/

/** When we need static fields:
 * - The value of variable is independent of objects
 * - The value is supposed to be shared across all objects
 **/

/** Static fields key points:
 * - static fields can only be declared at the class level, (not possible inside a method)
 * - static fields can be accessed without object initialization
 * - Since static variables belong to a class, they can be accessed directly using class name and don't need
 *   any object reference.*/

/****************************************** 2. Static methods **********************************************/

/*
* The static methods also belong to a class instead of the object, and so they can be called without creating the
* object of the class in which they reside. They're meant to be used without creating objects of the class.
*
* The static methods are generally used to perform an operation that is not dependent upon instance creation.
* Check the setNumberOfCars method inside the Car class.
*
* */

/************************************** Key points to Remember of static fields ************************************/

/** When we use static methods:
 * - To access/manipulate static variables and other static methods that don't depend upon objects
 * - static methods are widely used in utility and helper classes (e.g. Collection.sort() is a typical example)
 * */

/** Static fields key points:
 * - static methods in Java are resolved at compile time. Since method overriding is part of Runtime Polymorphism,
 *   so static methods can't be OVERRIDDEN.
 * - abstract methods can't be static
 * - static methods can't use "this" or "super" keywords
 * - The following combinations of the instance, class methods and variables are valid:
 *    1. Instance methods can directly access both instance methods and instance variables
 *    2. Instance methods can also access static variables and static methods directly
 *    3. static methods can access all static variables and other static methods
 *    4. static methods cannot access instance variables and instance methods directly; they need some object
 *       reference to do so
 * */

/*********************************************** 3. Static Block **************************************************/

/*
* A static block is used for initializing static variables. Although static variables can be initialized directly
* during declaration, there are situations when we're required to do the multiline processing.
* In such cases, static blocks come in handy.
*
* If static variables require additional, multi-statement logic while initialization, then a static block can be used.
*
* Check the Car class, we use a static block to initialize the engine List
* */

/************************************** Key points to Remember of static blocks ************************************/

/** When we use static blocks:
 * - If initialization of static variables requires some additional logic except the assignment
 * - If the initialization of static variables is error-prone and requires exception handling
 * */

/** Static fields key points:
 * - A class can have multiple static blocks
 * - static fields and static blocks are resolved and executed in the same order as they are present in the class
 * */


/*********************************************** 4. Static Class **************************************************/

/*
* Java programming language allows us to create a class within a class. It provides a compelling way of grouping
* elements that are only going to be used in one place, this helps to keep our code more organized and readable.
* The nested class architecture is divided into two:
* - nested classes that are declared static are called static nested classes whereas,
* - nested classes that are non-static are called inner classes
*
* The main difference between these two is that the inner classes have access to all member of the enclosing class
* (including private), whereas the static nested classes only have access to static members of the outer class.
*
* In fact, static nested classes behaved exactly like any other top-level class but enclosed in the only class which
* will access it, to provide better packaging convenience.
* */

/************************************** Key points to Remember of static Class ************************************/

/** When we use static Class:
 * - Grouping classes that will be used only in one place increases encapsulation
 * - The code is brought closer to the place that will be only one to use it; this increases readability and
 *   code is more maintainable
 * - If nested class doesn't require any access to it's enclosing class instance members, then it's better to declare
 *   it as static because this way, it won't be coupled to the outer class and hence will be more optimal as they
 *   won't require any heap or stack memory
 * */

/** Static Class key points:
 * - static nested classes do not have access to any instance members of the enclosing outer class; it can only
 *   access them through an object's reference
 * - static nested classes can access all static members of the enclosing class, including private ones
 * - Java programming specification doesn't allow us to declare the top-level class as static; only nested classes
 *   can be made as static
 * */
}

