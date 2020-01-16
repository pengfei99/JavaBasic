package org.pengfei.Lesson01_Java_Standard_API.S02_Exploring_Java_lang;

import org.pengfei.Lesson01_Java_Standard_API.S02_Exploring_Java_lang.source.*;

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

/** 2.2 Character
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
 * static char forDigit(int num, int radix): It returns the character associated with the value of num. The radix of
 *        the conversion is specified by radix. If the value of radix is not a valid radix, or the value of digit is
 *        not a valid digit in the specified radix, the null character ('\u0000') is returned. The digit argument is
 *        valid if 0 ≤ digit < radix. You can consider radix as the digit base. if num= 9, radix=10 (decimal), forDigit
 *        returns 9. if num=10, radix=16(hex), it returns a.
 * static int digit(char digit, int radix): It returns the integer value associated with the specified character
 *        according to the specified radix.
 *
 * Two other character-related classes are:
 * - Character.Subset: We use it to describe a subset of Unicode.
 * - Character.UnicodeBlock: It contains unicode character blocks.
 *
 * Additions to Character for Unicode Code Point Support
 * Beginning with JDK 5, the Character class has included support for 32-bit Unicode characters(ranged from 0 to10FFFF).
 * Because the old unicode character (16bits, ranged from 0 to FFFF) has been expanded.
 *
 * Three important terms:
 * A code point: is a character in the range 0 to 10FFFF.
 * A supplemental character: is a character that have value greater than FFFF.
 * The basic multilingual plane(BMP): are those characters between 0 and FFFF.
 *
 * Java addressed this problem in two ways:
 * 1. Java uses two chars to represent a supplemental character. The first char is called the high surrogate, and the
 *    second char is called low surrogate. Methods, such as codePointAt(), were provided to translate code points and
 *    supplemental characters.
 * 2. Java overloaded several preexisting methods in the Character class. The overloaded forms use int rather than char
 *    data. Because an int is large enough to hold any character as a single value. For example:
 *    static boolean isDigit(int cp)
 *    static boolean isLetter(int cp)
 *    static int toLowerCase(int cp)
 * */

/** 2.3 Boolean
 * Boolean is a very thin wrapper around boolean values, which is useful mostly when you want to pass a boolean variable
 * by reference. It contains the constants TRUE and FALSE. Boolean also defines the TYPE field, which is the Class
 * object for Boolean. To construct a Boolean object, use method valueOf().
 *
 * Check PrimitiveTypeWrapper.exp6();
 *
 * Useful method:
 * - int compareTo(Boolean b): returns 0 if a=b, returns a positive value if a is true, b is false (true > false), returns
 *                       negative value, if a is false, and b is true.
 * - static int compare(boolean a, boolean b):  returns 0 if a=b, returns a positive value if a is true, b is false,
 *                    returns negative value, if a is false, and b is true.
 * - static boolean parseBoolean(String str): unlike valueOf(), it returns a primitive type boolean, Case is not
 *                   significant for str (e.g. "True","trUE", etc. works). otherwise returns false.
 * */

    /***************************************** 03 Void *******************************************/
/*
 * The Void class has one static field, "TYPE", which holds	a reference	to the Class object for type void. You do not
 * create instances	of this class.
 * */

    /***************************************** 04 Process *******************************************/
/*
 * The abstract Process class encapsulates a process-that is, an executing program, It is used primarily as a superclass
 * for the type of objects created by exec() in the Runtime class, or by start() in the ProcessBuilder class.
 *
 * Beginning with JDK 9, you can obtain a handle to the process in the form of a ProcessHandle instance, and that you
 * can obtain information about the process encapsulated in a ProcessHandle.Info instance. These offer additional
 * control and information about a process. For example, totalCpuDuration() method of ProcessHandle.Info will return
 * the amount of CPU time that a process receives. The isAlive() method of ProcessHandle will return true if the
 * process is still executing.
 *
 * useful method:
 *
 * Stream<ProcessHandle> children(): Returns a stream that contains ProcessHandle objects that represent the immediate
 *                        children of the invoking process.
 * Stream<ProcessHandle> descendants(): Returns a stream that contains ProcessHandle objects that represent the immediate
 *                        children of the invoking process, plus all of their descendants.
 * void destroy(): terminates the process
 * Process destroyForcibly(): Forces termination of the invoking process. Returns a reference to the process.
 * int exitValue(): Returns an exit code obtained from a sub-process.
 * Full method list on table18-10 (p777)
 *
 * */

    /***************************************** 05 Runtime *******************************************/
/*
 *
 * The Runtime class encapsulates the run-time environment. You cannot instantiate a Runtime object. However, you can
 * get a reference to the current Runtime object by calling the static method Runtime.getRuntime(). Once you obtain
 * a reference to the current Runtime object, you can call several methods that control the state and behavior of the
 * JVM. Untrusted code typically cannot call any of the Runtime methods without raising a SecurityException.
 *
 * Useful methods:
 * long totalMemory(): Returns the total number of bytes of memory available to the program. The value returned by this
 *            method may vary over time, depending on the host environment.
 * long freeMemory(): Returns the approximate number of bytes of free memory available to the JVM.
 * Full method list in table 18-11(p778)
 */

/** 5.1 Runtime Memory Management
 * Although Java provides automatic garbage collection, sometimes you will want to know how large the object head is
 * and how much of it is left. You can use this information to check your code for efficiency or to approximate
 * how many more objects of a certain type can be instantiated.
 *
 * First we can call gc()(garbage collection) method and then call freeMemory() method to get a baseline memory usage.
 * Second execute your code and call freeMemory() again to see how much memory it is allocating. Check RuntimeExp.exp1();
 * Note, I can't figure out why total memory of JVM change during the execution of the code sample on centos7.
 */

/** 5.2 Use runtime to execute other programs
 * In safe environment, you can use Java to execute other heavyweight processes(e.g. programs) on your multitasking
 * operating system. Runtime object provide several overloaded version of exec() method. The Process object returned
 * by exec() can be manipulated by Process' methods after the new program starts running. You can kill the subprocess
 * with the destroy() method. The waitFor() method causes your program to wait until the subprocess finishes. The
 * exitValue() method returns the value returned by the subprocess when it is finished. This value is typically 0 if
 * no problems occur.
 *
 * Check RuntimeExp.exp2(); we use java to call terminator and wait until it ends.
 *
 * */

/** 5.3 Runtime.Version
 *
 * Runtime.Version encapsulates version information(which includes the version number) pertaining to the Java
 * environment. You can obtain an instance of Runtime.Version for teh current platform by calling Runtime.version().
 *
 * Beginning with JDK 10, Runtime.Version was updated to include the following methods that support the	new feature,
 * interim, update, and patch counter values:
 * - int feature()
 * - int interim()
 * - int update()
 * - int patch()
 *
 * As a result of the change to time-based release, the following methods in Runtime.Version have been deprecated:
 * - major()
 * - minor()
 * - security()
 * They have been replaced by feature, interim, update, patch. For example, jdk-11.0.4.0 has feature counter:11,
 * interim counter: 0, update : 4 and patch :0.
 * */

    /***************************************** 06 ProcessBuilder *******************************************/
/*
 * ProcessBuilder provides another way to start and manage os processes(that is programs). As we mentioned, all processes
 * are represented by the Process class, and a process can be started by Runtime.exec(). ProcessBuilder offers
 * more control over the processes. It can set the following attributes:
 * - command: check ProcessBuilderExp.exp1();
 * - environment: check ProcessBuilderExp.exp4();
 * - working directory: check ProcessBuilderExp.exp5();
 * - source of input: ProcessBuilderExp.exp2();
 * - destination for standard output and standard error output: ProcessBuilderExp.exp3();
 * - redirectErrorStream
 * - inheritIO(): sets the source and destination for subprocess standard I/O to be the same as those of the current
 *          java process.
 * */

 /***************************************** 07 System *******************************************/

/*  The system class holds a collection of static methods and variables. The standard input, output, and error output
 * of the Java run time are stored in the "in", "out", and "err" variables.
 *
 * Useful methods:
 * - static void arraycopy(Object source, int sourceStart, Object target, int targetStart, int size): Copies source
 *             array's element starts at index sourceStart to target array. size defines how many elements to be copied.
 * - static long currentTimeMillis(): It returns the current time in terms of milliseconds since midnight, January 1, 1970.
 *
 *
 * */

/** 7.1 Using currentTimeMillis() method to time program execution time
 * To time a section of your program, store the value return by currentTimeMillis() before beginning the program section
 * Just after the end of the program, call it again to get ending time. You can get the program elapsed time by using
 * endTime-startTime. Check SystemExp.exp1(); We can also use method nanoTime() to do the same. But
 * not all OS support nanoTime()
 * */

/** 7.2 Using arraycopy() method
 * The arraycopy() method can be used to copy quickly an array of any type from one place to another. This is much
 * faster than the equivalent loop written out longhand	in Java. check SystemExp.exp2();
 * */

/** 7.3 Environment Properties
 * You can obtain the values of various environment variables by calling the System.getProperty() method. For example:
 * java.class.path: It contains the class path of the current running program.
 * java.library.path: It contains the lib path
 * java.version:
 *
 * The full list is on page 793.
 * */

/** 7.4 System log
 * The System.logger interface System.LoggerFinder class support a program log. A logger can be returned by user of
 * System.getLogger(). System.logger is the interface which the logger implements.
 *
 * Check https://www.baeldung.com/java-9-logging-api for a full example.
 * */

    /***************************************** 08 Object *******************************************/

    /* Object is a superclass of all other classes. Object defines the methods shown below, which are available to
    * every object.
    * - Object clone(): throws CloneNotSupportedException: Creates a new object that is the same as the invoking object.
    * - boolean equals(Object obj): Returns true if the invoking object is equivalent to obj.
    * - void finalize() throws Throwable: Default finalize() method. Its called before an unused object is recycled.
    *                   (deprecated by JDK 9.)
    * - final Class<?> getClass(): Obtains a Class object that describes the invoking object.
    * - int hashCode(): returns the has code associated with the invoking object.
    * - final void notifyAll(): Notifies all threads waiting on the invoking object.
    * - final void notify(): Notifies a thread waiting on the invoking object.
    * - String toString(): Returns a string that describes the object.
    * - final void wait() throws InterruptedException: Waits on another thread of execution.
    * - final void wait(long milliseconds) throws InterruptedException: Waits up to the specified number of milliseconds
    *             on another thread of execution.
    * - final void wait(long milliseconds, int nanoseconds) throws InterruptedException: Waits up to the specified number
    *               of milliseconds+nanoseconds on another thread of execution.
    * */

    /** 8.1 clone object
     *
     * The clone() method generates a duplicate copy of the object on which it is called. Only classes that implement
     * the Cloneable interface can be cloned.
     *
     * The Cloneable interface defines no members. It is used to indicates that a class allows an exact copy of an
     * object(i.e. clone) to be made.If you try to call clone() on a class that does not implement Cloneable, a
     * CloneNotSupportedException is thrown. When a clone is made, the constructor for the object being cloned is not called.
     * A clone is simply an exact copy of the original.
     *
     * Cloning is a potentially dangerous action, because it can cause unintended side effects. For example, if the
     * object being cloned contains a reference variable called obRef. The clone and original object will both have this
     * reference which pointed at the same object. So if the clone do some modification, the original object is also
     * modified. Another example, if an object opens an I/O stream, its clone operates also on the same stream. If clone
     * closes the stream, and the original still try to write on the stream, this will cause an error.
     *
     * Because cloning can cause problems, clone() is declared as protected	inside Object. This means that it must
     * either be called from within a method defined by	the	class that implements Cloneable, or it must be explicitly
     * overridden by that class so that it is public.
     *
     * Note: The side effects caused by	cloning	are	sometimes difficult	to see at first. It	is easy	to think that
     * a class is safe for cloning when	it actually	is	not. In	general, you should	not	implement Cloneable	for
     * any class without good reason.
     * */

    /***************************************** 09 Class *******************************************/

    /*
    * Class encapsulates the run-time state of a class or interface. Objects of type Class are created automatically,
    * when classes are loaded. You cannot explicitly declare a Class object. Generally, you can obtain a Class object
    * by calling the getClass() method defined by Object. Class is a generic type that is declared as class Class<T>.
    * Here, T is the type of the class or interface represented. A sampling of methods defined by Class  is shown in
    * table 18-15(P798).
    *
    * The methods defined by Class are often useful in situations where run-time type information about an object is
    * required. These methods allow you to determine additional information about a particular class, such as public
    * constructors, fields, and methods. This is important for the Java Beans functionality.
    *
    * Before moving	on,	it is useful to	mention	a new Class	capability that	you	may find interesting. Beginning	with
    * JDK 11(LTS), Class provides three	methods	that relate	to a nest. A nest is a group of	classes	and/or interfaces
    * nested within	an outer class or interface. The nest concept enables the JVM to more efficiently handle certain
    * situations involving access between nest members.	It is important to state that a nest is	not	a source code
    * mechanism, and it	does not change	the Java language or how it	defines	accessibility.
    *
    * Nests	relate specifically	to how the compiler	and	JVM	work. However, it is now possible to obtain	a nest’s
    * top-level class/interface, which is called the nest host,	by use of getNestHost(). You can determine if
    * one class/interface is a member of the same nest as another by use of	isNestMateOf().
    *
    * Finally, you can get an array	containing a list of the nest members by calling getNestMembers().	You	may	find
    * these	methods	useful when	using reflection.
    * */

    public static void main(String[] args){
 /** Primitive type wrapper */

 // Float double
 // PrimitiveTypeWrapper.exp1();
 // PrimitiveTypeWrapper.exp2();

 // byte, short, integer, long
// PrimitiveTypeWrapper.exp3();

 // character
 // PrimitiveTypeWrapper.exp4();
  //  PrimitiveTypeWrapper.exp5();

    // Boolean
  //  PrimitiveTypeWrapper.exp6();

    // Runtime
   // RuntimeExp.exp1();
   // RuntimeExp.exp2();
   // RuntimeExp.exp3();

    //ProcessBuilder
   // ProcessBuilderExp.exp1();
   // ProcessBuilderExp.exp2();
   // ProcessBuilderExp.exp3();
   // ProcessBuilderExp.exp4();
   // ProcessBuilderExp.exp5();
   // ProcessBuilderExp.exp6();

    //System
   //  SystemExp.exp1();
   // SystemExp.exp2();
   // SystemExp.exp3();

    // Object
   // ObjectExp.exp1();

        // class
        ClassExp.exp1();
}
 }
