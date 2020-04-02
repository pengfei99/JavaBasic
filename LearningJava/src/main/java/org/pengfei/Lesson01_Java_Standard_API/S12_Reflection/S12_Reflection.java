package org.pengfei.Lesson01_Java_Standard_API.S12_Reflection;

import org.pengfei.Lesson01_Java_Standard_API.S12_Reflection.source.ReflectionApplicationExample;

public class S12_Reflection {
    /********************************************* 12.1 Introduction ***********************************************/
    /*
    * Reflection is the ability of software to analyze itself. This is provided by the java.lang.reflect package and
    * elements in Class. Beginning with JDK 9, java.lang.reflect is part of the java.base module. Reflection is an
    * important capability, especially when using components called Java Beans. It allows you to analyze a software
    * component and describe its capabilities dynamically, at run time rather than at compile time. For example, by
    * using reflection, you can determine what methods, constructors, and fields a class supports.
    *
    * The package java.lang.reflect includes several interfaces:
    * - AnnotatedArrayType: AnnotatedArrayType represents the potentially annotated use of an array type, whose
    *                       component type may itself represent the annotated use of a type.
    * - AnnotatedElement: Represents an annotated element of the program currently running in this VM.
    * - AnnotatedParameterizedType: AnnotatedParameterizedType represents the potentially annotated use of a
    *             parameterized type, whose type arguments may themselves represent annotated uses of types.
    * - AnnotatedType: AnnotatedType represents the potentially annotated use of a type in the program currently
    *             running in this VM.
    * - AnnotatedTypeVariable: AnnotatedTypeVariable represents the potentially annotated use of a type variable,
    *             whose declaration may have bounds which themselves represent annotated uses of types.
    * - AnnotatedWildcardType: AnnotatedWildcardType represents the potentially annotated use of a wildcard type
    *             argument, whose upper or lower bounds may themselves represent annotated uses of types.
    * - GenericArrayType: GenericArrayType represents an array type whose component type is either a parameterized
    *             type or a type variable.
    * - GenericDeclaration: A common interface for all entities that declare type variables.
    * - InvocationHandler: InvocationHandler is the interface implemented by the invocation handler of a proxy instance.
    * - Member: Member is an interface that reflects identifying information about a single member (a field or a method)
    *           or a constructor.
    * - ParameterizedType: ParameterizedType represents a parameterized type such as Collection<String>.
    * - Type: Type is the common superinterface for all types in the Java programming language.
    * - TypeVariable<D extends GenericDeclaration>: TypeVariable is the common superinterface for type variables of kinds.
    * - WildcardType: WildcardType represents a wildcard type expression, such as ?, ? extends Number, or ? super
    *        Integer.
    *
    * Pay special interest of interface Member, which defines methods that allow you to get information about a field,
    * constructor, or method of a class. There are also ten classes in this package:
    * - AccessibleObject: The AccessibleObject class is the base class for Field, Method and Constructor objects. It
    *                   allows you to bypass the default access control checks
    * - Array: The Array class provides static methods to dynamically create and access Java arrays.
    * - Constructor<T>: Constructor provides information about, and access to, a single constructor for a class.
    * - Executable: A shared superclass for the common functionality of Method and Constructor.
    * - Field: A Field provides information about, and dynamic access to, a single field of a class or an interface.
    * - Method: A Method provides information about, and access to, a single method on a class or interface.
    * - Modifier: The Modifier class provides static methods and constants to decode class and member access modifiers.
    * - Parameter: Information about method parameters.
    * - Proxy: Proxy provides static methods for creating dynamic proxy classes and instances, and it is also the
    *          superclass of all dynamic proxy classes created by those methods.
    * - ReflectPermission: The Permission class for reflective operations.
    *
    * */

    /*************************** 12.2 A simple application of reflection ****************************************/

    /*
    * check ReflectionApplicationExample.exp1(); It prints the constructors, fields, and methods of the class
    * java.awt.Dimension.
    *
    * Check ReflectionApplicationExample.exp2(); We get class based on an object. Then we get the name and modifier
    * of fields and methods.
    *
    * Check ReflectionApplicationExample.exp3(); We use reflection to create method object and field object. These two object
    * can bypass the modifier of the origin instance's class. In this example, field name is private and final. By
    * using reflection, we can modify the value of name even after object creation.
    *
    * Check ReflectionApplicationExample.exp4(); We use reflection to get package, interface, super class info
    * */

    /*************************** 12.3 Java Reflection Use Cases ****************************************/

    /*
    * One useful real-world use of reflection is when writing a framework that has to inter-operate with user-defined
    * classes, where the framework author doesn't know what the members (or even the classes) will be. Reflection
    * allows them to deal with any class without knowing it in advance. For instance, I don't think it would be
    * possible to write a complex aspect-oriented library without reflection.
    *
    * Another useful case, you can retrieve the definition of a private protected or final member(e.g. fields, methods),
    * remove the protection and manipulate it as if it had been declared public and mutable. However, this subverts
    * many of the guarantees the language normally makes for your programs. It can be very, very dangerous.
    *
    * Marshalling and unmarshalling to some other format. For example, mapping an object with getters and settings that
    * follow the bean convention to JSON and back again. You don't actually know the names of the fields or the methods
    * of the mapping object, we use reflection to examine the class of the mapping object and get those information.
    *
    * Reflection is much slower than just calling methods by their name, because it has to inspect the metadata in
    * the bytecode instead of just using precompiled addresses and constants. As a result, don't use reflection until
    * you have no other options.
    * */

    /*************************** 12.4 Java Reflection Advantages and drawbacks ****************************************/
    /*
    * Advantages of Using Reflection:
    * Extensibility Features: An application may make use of external, user-defined classes by creating instances of
    *                         extensibility objects using their fully-qualified names.
    * Debugging and testing tools: Debuggers use the property of reflection to examine private members on classes.
    *
    * Drawbacks:
    * Performance Overhead: Reflective operations have slower performance than their non-reflective counterparts,
    *                       and should be avoided in sections of code which are called frequently in
    *                       performance-sensitive applications.
    * Exposure of Internals: Reflective code breaks abstractions and therefore may change behavior with upgrades
    *                       of the platform.
    * */

    public static void main(String[] args){
        /** A simple application of reflection*/
       // ReflectionApplicationExample.exp1();
       // ReflectionApplicationExample.exp2();

        /* Use reflection to invoke method and change field value of an object*/
        // ReflectionApplicationExample.exp3();

        /* Use reflection to get package, interface, super class info*/
        ReflectionApplicationExample.exp4();
    }

}
