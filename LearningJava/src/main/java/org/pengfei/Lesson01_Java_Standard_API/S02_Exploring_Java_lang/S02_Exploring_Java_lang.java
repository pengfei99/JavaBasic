package org.pengfei.Lesson01_Java_Standard_API.S02_Exploring_Java_lang;

import org.pengfei.Lesson01_Java_Standard_API.S02_Exploring_Java_lang.source.PrimitiveTypeWrapper;

public class S02_Exploring_Java_lang {
    /********************************************* 01 Introduction **************************************************/

    /*
    * As we explained before, java.lang is the package which is automatically imported into all programs. It contains
    * classes and interfaces that are fundamental to virtually all of Java programming. It's Java's most widely
    * used package. Beginning with JDK 9, all of java.lang is part of the java.base module.*/

    /** 1.1 java.lang interfaces and classes
     * It contains the following interfaces:
     * - Appendable
     * - AutoCloseable
     * - CharSequence
     * - Cloneable
     * - Comparable
     * - Iterable
     * - ProcessHandle
     * - ProcessHandle.Info
     * - Readable
     * - Runnable
     * - StackWalker.StackFrame
     * - System.Logger
     * - Thread.UncaughtExceptionHandler
     *
     * java.lang package contains the following classes(abstract or not);
     * - Boolean, Byte, Integer, Long, Short, Float, Double (Primitive types)
     * - Character, Character.Subset, Character.UnicodeBlock (character types)
     * - Class, ClassLoader, ClassValue
     * - Compiler
     * - Enum
     * - InheritableThreadLocal
     * - Math, StrictMath
     * - Module, ModuleLayer, ModuleLayer.Controller
     * - Number
     * - Object
     * - Package
     * - Process, ProcessBuilder, ProcessBuilder.Redirect
     * - Runtime, RuntimePermission, Runtime.Version
     * - SecurityManager
     * - StackFramePermission, StackTraceElement, StackWalker
     * - String, StringBuffer, StringBuilder
     * - System, System.LoggerFinder
     * - Thread, ThreadGroup, ThreadLocal
     * - Throwable
     * - Void
     *
     * Note: Several of the classes in java.lang contain deprecated methods, many dating back to Java 1.0. Deprecated
     * methods are still provided by Java to support legacy code but are not recommended for new code.
     * */

    /***************************************** 02 Primitive Type Wrappers *******************************************/


/*
 * As we explained before, Java uses primitive types to increase performance. They are not part of the object hierarchy.
 * They are passed by value to methods and cannot be directly passed by reference(Check lesson00-section5.2). Also,
 * there is no way for two methods to refer to the same instance of an int.
 *
 * At times, we will need to create an object representation of primitive types. For example, there are collection
 * classes can only deal with objects. To store primitive types in collection, you need to wrap the primitive types
 * in a class. Another situation, when you pass a argument to a method, and you want the method modify the argument
 * value. As primitive types are passed by value, so method can't modify argument value. You also need to wrap the
 * primitive types in a class (passed by reference, value can be modified).
 *
 * We have seen the primitive type wrapper in Lesson00.section9.15. In this chapter, we will examined they in detail.
*/

/** 2.1 Number
 * The abstract class Number defines a superclass that is implemented by the classes that wrap the numeric types
 * byte, short, int, long, float, and double. Number has abstract method that return the value of the object,
 * for example:
 * - byte byteValue()
 * - double doubleValue()
 * - float floatValue()
 * - int intValue()
 * - long longValue()
 * - short shortValue()
 *
 * The values returned by these methods might be rounded, truncated, or result in a "garbage" value due to the effects
 * of a narrowing conversions.
 *
 * Number has concrete subclasses that hold explicit values of each primitive numeric type:
 * - Double
 * - Float
 * - Byte
 * - Short
 * - Integer
 * - Long
 *
 * */

/** 2.1.1 Double and Float
 *
 * The constructor for Float:
 * - Float(double num)
 * - Float(float num)
 * - Float(String str) throws NumberFormatException
 * Beginning with JDK 9, these constructors have been deprecated. Use valueOf() method is recommended.
 * Check PrimitiveTypeWrapper.exp1();
 *
 * Useful method in Float:
 * static int compare(float num1, float num2): It compares the value of num1 and num2. Return 0 if equal, negative if
 *                  num1 < num2. Return positive if num1> num2
 * int compareTo(Float f): It compares f with the invoking object.
 * static boolean isNaN(float num): Returns true if num specifies a value that is not a number.
 * boolean isNaN(): Returns true if the invoking object contains a value that is not a number. Otherwise, it returns false
 * static boolean isFinite(float num): Returns true if num is not NaN and is infinitely large or small.
 * boolean isInfinite(float num): Returns true if the invoking object contains a value that is not a number.
 *
 * Float and Double provide the methods isInfinite and isNaN, which help us when manipulating two special double and
 * float values. These methods test for two unique values defined by the IEEE floating-point specification: infinity
 * and NaN (not a number).
 * */

/** 2.1.2 Byte, Short, Integer, Long
 * They are similar to Double and Float, we can use old constructor to build new objects. Beginning with JDK 9, use
 * valueOf().
 *
 * All classes has the following constants:
 * - BYTES: The width of the integer type in bytes
 * - MIN_VALUE: Minimum value
 * - MAX_VALUE: Maximum value
 * - SIZE: The bit width of the wrapped value
 * - TYPE: The Class object for byte, short, int or long
 *
 * The most used method are the methods which convert Number to and from strings. The Byte, Short, Integer, and Long
 * classes provide the parseByte(), parseShort(), parseInt(), and parseLong(). These methods return the byte, short,
 * int, or long (primitive types).
 *
 * To convert a whole number into a decimal string, use the toString() defined in the Byte, ... classes. The Integer and
 * Long classes also provide:
 * - toBinaryString():
 * - toHexString():
 * - toOctalString():
 * */

/** 2.1.3 Character
 *
 * Character is a simple wrapper around a char. The deprecated constructor for Character is Character(char ch). Use
 * valueOf() method is recommended.
 *
 * Useful method:
 * char charValue(): It returns the character
 * static boolean isDigit(char c): It returns true if c is digit
 * static boolean isLetter(char c): It returns true if c is letter
 * static boolean isWhitespace(char c): It returns true if c is whitespace
 * static boolean isUpperCase(char c): It returns true if c is upper case
 * static boolean isLowerCase(char c): It returns true if c is lower case
 * */

public static void main(String[] args){
 /** Primitive type wrapper */

 // Float double
 // PrimitiveTypeWrapper.exp1();
 // PrimitiveTypeWrapper.exp2();

 // byte, short, integer, long
// PrimitiveTypeWrapper.exp3();

 // character
 PrimitiveTypeWrapper.exp4();
}
 }
