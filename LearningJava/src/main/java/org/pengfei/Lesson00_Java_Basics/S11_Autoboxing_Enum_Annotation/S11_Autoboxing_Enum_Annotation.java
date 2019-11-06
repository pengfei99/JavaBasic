package org.pengfei.Lesson00_Java_Basics.S11_Autoboxing_Enum_Annotation;

import org.pengfei.Lesson00_Java_Basics.S11_Autoboxing_Enum_Annotation.source.*;

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

    /** 11.5.2 Autoboxing and methods
     *
     * In addition to the simple case of assignments, autoboxing automatically occurs whenever a primitive type must
     * be converted into an object, and auto­unboxing takes place whenever an object must be converted into a primitive
     * type. Thus, autoboxing/unboxing might occur when an argument is passed to a method or when a value is returned
     * by a method.
     *
     * Check AutoboxingExp.exp3(); to see how method return value and argument are converted via autoboxing.
     * */

    /** 11.5.3 Autoboxing in expressions
     *
     * In general, autoboxing and unboxing take place whenever a conversion into an object or from an object is
     * required. This applies to expressions. Within an expression, a numeric object is automatically unboxed. The
     * outcome of the expression is reboxed, if necessary.
     *
     * Check AutoboxingExp.exp4(); to see the autoboxing in expressions. As the examples in the program show, because
     * of autoboxing/unboxing, using numeric objects in an expression is both intuitive and easy. With early versions
     * of Java, such code would have involved casts and calls to methods such as valueOf(), intValue().
     * */

    /** 11.5.4 A word of warning
     *
     * Because of autoboxing and auto­unboxing, one might be tempted to use objects such as Integer or Double
     * exclusively, abandoning primitives altogether. Note that we use primitive types for their efficiency. If we have
     * choice, we will always use primitive types. We use the wrap Object, because many data structure does not support
     * primitive types.
     * */

    /***************************************** 11.6 Static import ***************************************/

    /*
    * Java supports an expanded use of the import keyword. By following import with the keyword static, an import
    * statement can be used to import the static members of a class or interface. This is called static import. When
    * using static import, it is possible to refer to static members directly by their names, without having to
    * qualify them with the name of their class. This simplifies and shortens the syntax required to use a static member.
    *
    * Check StaticImportExp.exp1(), in this method, we used two static methods(i.e. pow(), sqrt()) of Class Math. To
    * use them, we need to call className.methodName. Static import can simplify this syntax.
    * Check StaticImportExp.exp2(), with static import, the code is more streamline.
    *
    * */

    /** 11.6.1 General forms of static import
     * There are two general forms of the import static statement.
     * 1. import static pkg.type-name.static-member-name : type-name is the name of a class or interface that contains
     *                          the desired static member. Its full package name is specified by pkg. The name of the
     *                          member is specified by static­member­name.
     * 2. import static pkg.type-name.* : This form will import all static members of the class or interface. If you
     *                         will be using many static members, then this form lets you bring them into view without
     *                         having to specify each individually.
     *
     * The static import works on all Java classes and interfaces. Check StaticImportExp.exp3(), we import the
     * out which is a static field of the class System. So we can write out.print(), instead of System.out.print()
     * */

    /** 11.6.2 Restrictions
     * As convenient as static import can be, it is important not to abuse it. Remember, one reason that Java organizes
     * its libraries into packages is to avoid namespace collisions. When you import static members, you are bringing
     * those members into the current namespace. Thus, you are increasing the potential for namespace conflicts and
     * inadvertent name hiding. If you are using a static member once or twice in the program, it’s best not to import
     * it. Also, some static names, such as System.out, are so recognizable that you might not want to import them.
     * Static import is designed for those situations in which you are using a static member repeatedly, such as when
     * performing a series of mathematical computations. In essence, you should use, but not abuse, this feature.
     * */

    /** 11.6.3 Static import on custom classes
     *
     * We can absolutely use static import to import the static members of classes and interfaces which we create.
     * Doing so is especially convenient when you define several static members that are used frequently throughout a
     * large program. For example, if a class defines a number of static final constants that define various limits,
     * then using static import to bring them into view will save you a lot of tedious typing.
     * */


    /***************************************** 11.7 Annotations (metadata) ***************************************/

    /*
    * Java provides a feature that enables you to embed supplemental information into a source file. This information,
    * called an annotation, does not change the actions of a program. However, this information can be used by various
    * tools, during both development and deployment. For example, an annotation might be processed by a source­code
    * generator, by the compiler, or by a deployment tool. The term metadata is also used to refer to this feature,
    * but the term annotation is the most descriptive, and more commonly used.
    *
    * We will not cover this topic in details in this sections. We only give you an overview so that you will be
    * familiar the concept. Check the book "Java: The complete Reference, Eleventh Edition"
    *
    * An annotation is created through a mechanism based on the interface. Check MyAnnotation class as our first
    * annotation declaration example.
    *
    * Note that All annotation types automatically extend the Annotation interface. Thus, Annotation is a
    * super­interface of all annotations. It is declared within the java.lang.annotation package.
    *
    * Originally, annotations were used to annotate only declarations. In this usage, any type of declaration can
    * have an annotation associated with it. For example, classes, methods, fields, parameters, and enum constants
    * can be annotated. Even an annotation can be annotated. In such cases, the annotation precedes the rest of the
    * declaration. Beginning with JDK 8, you can also annotate a type use, such as a cast or a method return type.
    * */

    /** 11.7.1 Use annotation to annotate class members
     *
     * Check AnnotationExp.exp1(); We use MyAnnotation to annotate a method exp1(). We use @MyAnnotation to start the
     * annotation instantiation, we assign values to the annotation members(e.g description, priority). Notice that
     * no parentheses follow description or priority in these assignments. When an annotation member is given a value,
     * only its name is used. Thus, annotation members look like fields in this context.
     *
     * Annotations that don’t have parameters are called "marker annotations". These are specified without passing any
     * arguments and without using parentheses. Their sole purpose is to mark an item with some attribute
     * */

    /** 11.7.2 Java built-in annotations
     *
     * Java defines many built­in annotations. Most are specialized, but nine are general purpose. Four are imported
     * from java.lang.annotation:
     * - @Retention : Specifies the retention policy that will associated with the annotation. The retention policy
     *                determines how long an annotation is present during the compilation and deployment process.
     * - @Documented : A marker annotation that tells a tool that an annotation is to be documented. It is designed
     *                 to be used only as an annotation to an annotation declaration.
     * - @Target : Specifies the types of items to which an annotation can be applied. It is designed to be used only
     *             as an annotation to another annotation. It takes one argument which must be a constant or array
     *             of constants from the ElementType enumeration, which defines various constants, such as CONSTRUCTORS,
     *             FIELD, and METHOD. The argument determines the types of items to which the annotation can be applied.
     *             If @Target is not specified, the annotation can be used on any members.
     * - @Inherited : A marker annotation that causes the annotation for a superclass to be inherited by a subclass.
     * - @Repeatable : It supports repeatable annotations, which are annotations that can be applied more than once to
     *                 a single item. (Added in JDK8)
     * - @Native: It is used to annotate a constant field accessed by executable (i.e., native) code. (Added in JDK8).
     *
     *
     * Five are included in java.lang:
     * - @Override : is used above methods that override methods in a superclass. If the method does not match a
     *               method in the superclass, the compiler will give you an error. The @Override annotation is not
     *               necessary in order to override a method in a superclass. It is a good idea to use it still, though.
     *               In case someone changed the name of the overridden method in the superclass, your subclass method
     *               would no longer override it. Without the @Override annotation you would not find out. With the
     *               @Override annotation the compiler would tell you that the method in the subclass is not overriding
     *               any method in the superclass.
     * - @Deprecated : is used to mark a class, method or field as deprecated, meaning it should no longer be used.
     *               If your code uses deprecated classes, methods or fields, the compiler will give you a warning.
     * - @SafeVarargs : A marker annotation that indicates that no unsafe actions related to a varargs parameter in
     *                  a method or constructor occur. Can be applied to method and constructors, with various
     *                  restrictions.
     * - @FunctionalInterface : A marker annotation that is used to annotate an interface declaration. It indicates
     *                  that the annotated interface is a functional interface, which is an interface that contains
     *                   one and only one abstract method. Functional interfaces are used by lambda expressions.
     *                   Note @FunctionalInterface is purely informational. Any interface with exactly one abstract
     *                   method is, by definition, a functional interface.
     * - @SuppressWarnings : Makes the compiler suppress warnings for a given method. For instance, if a method calls
     *                    a deprecated method, or makes an insecure type cast, the compiler may generate a warning.
     *                    You can suppress these warnings by annotating the method containing the code with the
     *                    @SuppressWarnings annotation.
     *
     * Check AnnotationExp.exp2(); to see an example of deprecated annotation.
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
        // AutoboxingExp.exp2();

        /** 11.5.2 autoboxing in methods*/
       // AutoboxingExp.exp3();

        /** 11.5.3 autoboxing in expressions*/
        // AutoboxingExp.exp4();

        /** 11.6 Static import*/
      //  StaticImportExp.exp1();
      //  StaticImportExp.exp2();
      //  StaticImportExp.exp3();

        /** 11.7 Annotation example */
        AnnotationExp.exp1();
        AnnotationExp.exp2();
    }



}
