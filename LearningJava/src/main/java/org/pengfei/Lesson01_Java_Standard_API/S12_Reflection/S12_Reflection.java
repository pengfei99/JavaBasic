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
    * */

    public static void main(String[] args){
        /** A simple application of reflection*/
       // ReflectionApplicationExample.exp1();
        ReflectionApplicationExample.exp2();
    }

}
