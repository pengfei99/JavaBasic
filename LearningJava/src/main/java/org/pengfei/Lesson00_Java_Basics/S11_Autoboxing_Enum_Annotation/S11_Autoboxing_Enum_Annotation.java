package org.pengfei.Lesson00_Java_Basics.S11_Autoboxing_Enum_Annotation;

import org.pengfei.Lesson00_Java_Basics.S11_Autoboxing_Enum_Annotation.source.AutoboxingExp;
import org.pengfei.Lesson00_Java_Basics.S11_Autoboxing_Enum_Annotation.source.EnumerationExp;
import org.pengfei.Lesson00_Java_Basics.S11_Autoboxing_Enum_Annotation.source.Transport;

public class S11_Autoboxing_Enum_Annotation {

    /******************************************** 11. Introduction *******************************************/

    /*
    * In this section, we will discuss enumerations, autoboxing, static import, and annotations. These features have
    * been added by JDK 5. We will learn the following key Concepts:
    * - Understand enumeration fundamentals
    * - Use the class­based features of enumerations
    * - Apply the values() and valueof() methods to enumerations
    * - Create enumerations that have constructors, instance variables, and methods
    * - Employ the ordinal() and compareTo() methods that enumerations inherit from Enum
    * - Use Java’s type wrappers
    * - Know the basics of autoboxing and auto­unboxing
    * - Use autoboxing with methods
    * - Understand how autoboxing works with expressions
    * - Apply static import
    * - Gain an overview of annotations
    * */

    /******************************************** 11.1 Enumerations *******************************************/

    /*
    * An enumeration is a list of named constants that define a new data type. An object of an enumeration type can
    * hold only the values that are defined by the list. Thus, an enumeration gives you a way to precisely define
    * a new type of data that has a fixed number of valid values.
    *
    * Enumerations are common in everyday life. For example, an enumeration of the coins used in the United States
    * is penny, nickel, dime, quarter, half­dollar, and dollar. An enumeration of the months in the year consists of
    * the names January through December. An enumeration of the days of the week is Sunday, Monday, Tuesday, Wednesday,
    * Thursday, Friday, and Saturday.
    *
    * From a programming perspective, enumerations are useful whenever you need to define a set of values that
    * represent a collection of items. For example, you might use an enumeration to represent a set of status codes,
    * such as success, waiting, failed, and retrying, which indicate the progress of some action. In the past, such
    * values were defined as final variables, but enumerations offer a more structured approach.
    * */

    /** 11.1.1 Enumeration fundamentals
     *
     * In java, An enumeration is created using the enum keyword. Check enumeration class transport, which identifies
     * CAR, TRUCK, etc. These values are called enumeration constants. Each is implicitly declared as a public, static
     * member of Transport. Furthermore, the enumeration constants' type is the type of the enumeration in which the
     * constants are declared, which is Transport in this case. Thus, in the language of Java, these constants are
     * called self­typed, where "self" refers to the enclosing enumeration.
     *
     * Once you have defined an enumeration, you can create a variable of that type. However, even though enumerations
     * define a class type, you do not instantiate an enum using new. Instead, you declare and use an enumeration
     * variable in much the same way that you do one of the primitive types. check code fragment 11.1.1, this declares
     * tp as a variable of enumeration type Transport, and assigns tp the value AIRPLANE. Notice that the symbol
     * AIRPLANE is qualified by Transport.
     *
     * Two enumeration constants can be compared for equality by using the == relation operator. check code
     * fragment 11.1.1.
     *
     * An enumeration value can also be used to control a switch statement. Of course, all of the case statements must
     * use constants from the same enum as that used by the switch expression. check code fragment 11.1.1
     *
     * Notice that in the case statements, the names of the enumeration constants are used without being qualified by
     * their enumeration type name. That is, TRUCK, not Transport.TRUCK, is used. This is because the type of the
     * enumeration in the switch expression has already implicitly specified the enum type of the case
     * constants. There is no need to qualify the constants in the case statements with their enum type name.
     * In fact, attempting to do so will cause a compilation error. Uncomment the line, see the message error.
     *
     * When an enumeration constant is displayed, such as in a println( ) statement, its name is output. check code
     * fragment 11.1.1
     *
     * Notice, the constants in Transport use uppercase. However, the use of uppercase is not required. Because
     * enumerations often replace final variables, which have traditionally uses uppercase, some programmers believe
     * that uppercasing enumeration constants is also appropriate.
     * */

    /** 11.1.2 Java Enumerations are class types
     *
     * Unlike the way enumerations are implemented in some other languages, Java implements enumerations as class types.
     * Although you don’t instantiate an enum using new, it otherwise acts much like other classes. The fact that enum
     * defines a class enables the Java enumeration to have powers that enumerations in some other languages do not.
     * For example, you can give it constructors, add instance variables and methods, and even implement interfaces
     *
     * */

    /** 11.1.3 THE VALUES() AND VALUEOF() METHODS
     *
     * All enumerations automatically have two predefined methods: values() and valueOf(). Their general forms are
     * shown here:
     * public static enum­type[] values(): Returns an array that contains a list of the enumeration constant.
     * public static enum­type valueOf(String str): Returns the enum constant whose value corresponds to the string
     *                                    input.
     * */

    /** 11.1.4 Constructors, methods, instances variables
     *
     * It is important to understand that each enumeration constant is an object of its enumeration type. Thus, an
     * enumeration can define constructors, add methods, and have instance variables. When you define a constructor
     * for an enum, the constructor is called when each enumeration constant is created. Each enumeration constant can
     * call any method defined by the enumeration. Each enumeration constant has its own copy of any instance variables
     * defined by the enumeration.
     *
     * Check EnumerationExp.exp3(); We added instance variable "speedLimit", constructor and method getSpeedLimit
     * in class AdvTransport. Notice how the argument to the constructor of the enumeration constants are specified,
     * by putting them inside parentheses, after each constant(e.g. CAR(88)). Unlike simple enum, the enum with
     * other members, the enumeration list must end in a semicolon.
     *
     * The instance variables can be accessed via method such as getSpeedLimit. As each enumeration constants has
     * its own copy of speedLimit, when we use constant to call getSpeedLimit, we will get the speedLimit of each
     * constant.
     *
     * Two important restrictions
     * There are two restrictions that apply to enumerations:
     * 1. An enumerations can't inherit another class.
     * 2. An enumeration can not be a superclass, which means that an enum can't be extended.
     * The key point to remember is that each enumeration constants is an Object of the enum class in which it's defined.
     *
     * */

    /** 11.1.5 Enumeration vs Final variable
     *
     * Enumerations are appropriate when you are working with lists of items
     * that must be represented by identifiers. A final variable is appropriate when you
     * have a constant value, such as an array size, that will be used in many places. Thus,
     * each has its own use. The advantage of enumerations is that final variables don’t
     * have to be pressed into service for a job for which they are not ideally suited.
     * */

    /***************************************** 11.2 Enumerations inherit ENUM ***************************************/

    /*
    * Although you can’t inherit a superclass when declaring an enum, all enumerations automatically inherit one:
    * java.lang.Enum. This class defines several methods that are available for use by all enumerations. Most often,
    * you won’t need to use these methods, but there are two that you may occasionally employ: ordinal() and compareTo().
    * */

    /** 11.2.1 Ordinal
     * The ordinal( ) method obtains a value that indicates an enumeration constant’s position in the list of
     * constants. This is called its ordinal value. Check EnumerationExp.exp4();
     * */

    /** 11.2.2 CompareTo
     * You can compare the ordinal value of two constants of the same enumeration by using the compareTo() method.
     * It has this general form : final int compareTo(enum­type e)
     * Here, enum­type is the type of the enumeration and e is the constant being compared to the invoking constant.
     * Remember, both the invoking constant and e must be of the same enumeration. If the invoking constant has an
     * ordinal value less than e’s, then compareTo( ) returns a negative value. If the two ordinal values are the same,
     * then zero is returned. If the invoking constant has an ordinal value greater than e’s, then a positive value is
     * returned.
     * */

    /***************************************** 11.3 Traffic light exercise ***************************************/

    /*
    * Enumerations are particularly useful when your program needs a set of constants, but the actual values of the
    * constants are arbitrary, as long as all differ. This type of situation comes up quite often when programming.
    * One common instance involves handling the states in which some device can exist.
    *
    * In this exercise, we will write a program which controls traffic light. The traffic light has three stages: green
    * yellow, and red.
    * */

    /***************************************** 11.4 Autoboxing ***************************************/

    /*
    * Beginning with JDK 5, Java has included two very helpful features:
    * - autoboxing
    * - auto­unboxing.
    * Autoboxing/unboxing greatly simplifies and streamlines code that must convert primitive types into objects,
    * and vice versa.
    *
    * Autoboxing/unboxing is directly related to Java’s type wrappers, and to the way that values are moved into and
    * out of an instance of a wrapper. For this reason, we will begin with an overview of the type wrappers and the
    * process of manually boxing and unboxing values
    * */

    /** 11.4.1 Type Wrappers
     *  As we explained in Section_01 and Section_04, Java uses primitive types, such as int or double, to hold the
     *  basic data types. In java, primitive types are used for its performance. Using type wrappers for these basic
     *  types would add an unacceptable overhead. Thus, the primitive types are not part of the object hierarchy, and
     *  they do not inherit Object.
     *
     *  Despite the performance benefit offered by primitive types, there are times when you will need an object
     *  representation. For example, you can't pass a primitive type by reference to a method. Also, many of the
     *  standard data structures implemented by java operate only on objects, which means that you can't use these
     *  data structures to store primitive types. Fortunately, Java provides "type wrappers", which are classes that
     *  encapsulate a primitive type within an objects
     *
     *  The type wrappers are : Double, Float, Long, Integer, Short, Byte, Character, and Boolean, which are packaged
     *  in java.lang. Byte, Short, Integer, Long, Float, and Double are the numeric type wrappers which inherit the
     *  abstract class Number. Number declares methods that return the value of an object in each of the different
     *  numeric types. These methods are shown here:
     *  - byte byteValue( )
     *  - double doubleValue( )
     *  - float floatValue( )
     *  - int intValue( )
     *  - long longValue( )
     *  - short shortValue( )
     *  For example, doubleValue() returns the value of an object as a double, floatValue() returns the value as a
     *  float, and so on. These methods are implemented by each of the numeric type wrappers.
     *
     * All of the numeric type wrappers define constructors that allow an object to be constructed from a given value,
     * or a string representation of that value. However, beginning with JDK 9, the type­wrapper constructors have been
     * deprecated. Today, it is recommended that you use one of the valueOf() methods to obtain a wrapper object. The
     * valueOf() method is a static member of all of the wrapper classes and all numeric classes support forms that
     * convert a numeric value or a string into an object. For example, here are two forms supported by Integer:
     * - static Integer valueOf(int val)
     * - static Integer valueOf(String valStr) throws NumberFormatException
     *
     * All of the type wrappers override toString( ). It returns the human­readable form of the value contained
     * within the wrapper. This allows you to output the value by passing a type wrapper object to println(),
     * Check AutoboxingExp.exp1(); for creating an integer object and print it as an object
     *
     * The process of encapsulating a value within an object is called boxing.
     * The process of extracting a value from a type wrapper is called unboxing.
     *
     * Prior to JDK 5, all boxing and unboxing took place manually,
     * */

    /***************************************** 11.5 Autoboxing fundamentals ***************************************/

    /*
    * Autoboxing is the process by which a primitive type is automatically encapsulated (boxed) into its equivalent
    * type wrapper whenever an object of that type is needed. There is no need to explicitly obtain an object.
    * Auto­unboxing is the process by which the value of a boxed object is automatically extracted (unboxed) from a
    * type wrapper when its value is needed. There is no need to call a method such as intValue() or doubleValue().
    * Check AutoboxingExp.exp2(); to see what is autoboxing in Java.
    * */

    /** 11.5.1 Autoboxing advantage
     * 1. It greatly streamlines the coding of several algorithms, removing the tedium of manually boxing and unboxing
     *    values.
     * 2. It also helps prevent errors. With autoboxing it is not necessary to manually construct an object inorder to
     *    wrap a primitive type. You need only assign that value to a type­wrapper reference.
     *
     * */

    public static void main(String args[]){

        /** 11.1.1 Enumeration fundamentals */
        // EnumerationExp.exp1();

        /** 11.1.3 Values and ValueOf*/
       // EnumerationExp.exp2();

        /** 11.1.4 constructors, methods, instances variables*/
        // EnumerationExp.exp3();

        /** 11.2.1 Ordianl */
     //   EnumerationExp.exp4();

        /** 11.2.2 CompareTo*/
       // EnumerationExp.exp5();

        /** 11.3 Traffic light exercise*/
       //  EnumerationExp.exp6();

        /** 11.4.1 type wrapper*/
       //   AutoboxingExp.exp1();

        /** 11.5 autoboxing*/
        AutoboxingExp.exp2();
    }



}
