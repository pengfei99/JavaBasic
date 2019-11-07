package org.pengfei.Lesson00_Java_Basics.S12_Generics;

import org.pengfei.Lesson00_Java_Basics.S12_Generics.source.GenericExp;

public class S12_Generics {
    /******************************************** 12. Introduction *******************************************/

    /*
     * In this section, we will see the generics feature in Java. We will learn the following key concepts:
     * - Understand the benefits of generics
     * - Create a generic class
     * - Apply bounded type parameters
     * - Use wildcard arguments
     * - Apply bounded wildcards
     * - Create a generic method
     * - Create a generic constructor
     * - Create a generic interface
     * - Utilize raw types
     * - Apply type inference with the diamond operator
     * - Understand erasure
     * - Avoid ambiguity errors
     * - Know generics restrictions
     * */

    /****************************************** 12.1 Generics fundamentals ****************************************/

    /*
     * At its core, the term generics means parameterized types. Parameterized types are important because they
     * enable you to create classes, interfaces, and methods in which the type of data upon which they operate is
     * specified as a parameter. A class, interface, or method that operates on a type parameter is called generic,
     * as in generic class or generic method.
     *
     * A principal advantage of generic code is that it will automatically work with the type of data passed to its
     * type parameter. Many algorithms are logically the same no matter what type of data they are being applied to.
     * For example, a Quicksort is the same whether it is sorting items of type Integer, String, Object, or Thread.
     * With generics, you can define an algorithm once, independently of any specific type of data, and then
     * apply that algorithm to a wide variety of data types without any additional effort.
     *
     * It is important to understand that Java has always given you the ability to create generalized classes,
     * interfaces, and methods by operating through references of type Object. Because Object is the superclass of
     * all other classes, an Object reference can refer to any type of object. Thus, in pre­generics code, generalized
     * classes, interfaces, and methods used Object references to operate on various types of data. The problem
     * was that they could not do so with type safety because casts were needed to explicitly convert from Object to
     * the actual type of data being operated upon. Thus, it was possible to accidentally create type mismatches.
     * Generics add the type safety that was lacking because they make these casts automatic and implicit. In short,
     * generics expand your ability to reuse code and let you do so safely and reliably.
     * */

/** 12.1.1 A simple generics example
 *
 * Check GenericExp.exp1(), We created a generic class "SimpleGen<T>". T is the name of a type parameter. This name is
 * used as a placeholder for the actual type that will be passed to SimpleGen when an object is created. Thus, T is
 * used within Class SimpleGen whenever the type parameter is needed. Notice that T is contained within < >.
 * This syntax can be generalized. Whenever a type parameter is being declared, it is specified within angle brackets.
 * Because Gen uses a type parameter, Gen is a generic class.
 *
 * We used T as the generic type identifier, but there is no obligation, any valid identifier could have been used. By
 * tradition, we use T. Furthermore, it is recommended that type parameter names be single­character, capital letters.
 * Other commonly used type parameter names are V and E. One other point about type parameter names: Beginning with
 * JDK 10, you cannot use var as the name of a type parameter.
 *
 * In the GenericExp.exp1, first we create SimpleGen<String> stringSimpleGen, then we create SimpleGen<Integer> integerSimpleGen
 * Notice the primitive types can not be used to parameterize a generic class. Thus, SimpleGen<int> is not accepted.
 * String and Integer is a type argument that is passed to SimpleGen's type parameter T. You can imagine that all T in
 * SimpleGen have been replaced by String or Integer.
 *
 * Before moving on, it’s necessary to state that the Java compiler does not actually create different versions of
 * SimpleGen, or of any other generic class. Although it’s helpful to think in these terms, it is not what actually
 * happens. Instead, the compiler removes all generic type information, substituting the necessary casts, to make your
 * code behave as if a specific version of SimpleGen was created. Thus, there is really only one version of Gen that
 * actually exists in your program. The process of removing generic type information is called erasure, which is
 * discussed later in this section.
 *
 * */

/** 12.1.2. Generics work only with reference(Object) types
 * When declaring an instance of a generic type, the type argument passed to the type parameter must be a reference
 * type. You cannot use a primitive type, such as int or char.
 * */

/** 12.1.3 Generic types differ based on their type arguments
 * A key point to understand about generic types is that a reference of one specific version of a generic type is
 * not type­compatible with another version of the same generic type. For example, stringSimpleGen are not compatible
 * with integerSimpleGen, even though both are of type SimpleGen<T>. They are references to different types because
 * their type arguments differ (String vs Integer). This is part of the way that generics add type safety and prevent
 * errors.
 * */

    /** 12.1.4 Generic class with multiple type parameters
     * You can declare more than one type parameter in a generic type. To specify two or more type parameters, simply
     * use a comma­separated list. Check GenericExp.exp2(), we created a TwoGen class which take two generic type
     * parameters, T and V. They are separated by a comma. Because it has two type arguments, when we create an object
     * of TwoGen, we need to give two specific types. In our example, TwoGen<String,Integer> twoGen=new TwoGen<>("test",1);
     * we use String and Integer, it's possible for both types to be the same. For example, the following is correct too
     * TwoGen<String,String> twoGen1=new TwoGen<>("test","test);
     */

    /** 12.1.5 The general form of a Generic Class
     * Here is the syntax for declaring a generic class:
     * class class­name<type­param­list> { // ...
     * Here is the full syntax for declaring a reference to a generic class and creating a generic instance:
     * class-name<type-arg-list> varName= new class-name<>(arg-list)
     * */

    /** 12.1.6 Cast one instance of a generic class into another
     *
     * You can cast one instance of a generic class into another, only if the two are compatible and their type
     * arguments are the same. Check GenericExp.exp7();
     * */

    /****************************************** 12.2 Bounded types ****************************************/

    /*
    * In the preceding examples, the type parameters could be replaced by any class type. This is fine for many
    * purposes, but sometimes it is useful to limit the types that can be passed to a type parameter. For example,
    * assume that you want to create a generic class that stores a numeric value and is capable of performing various
    * mathematical functions, such as computing the reciprocal or obtaining the fractional component. Furthermore,
    * you want to use the class to compute these quantities for any type of number, including integers, floats, and
    * doubles. Thus, you want to specify the type of the numbers generically, using a type parameter.
    *
    * Check GenericExp.exp3(); In generic class NumericFunction, we define T extends Number to set the upper bound of
    * the generic type T. Because the type T is now bounded by Number, the Java compiler knows that all objects of
    * type T can call methods declared in Number(e.g. doubleValue(),intValue). However, as an added bonus, the bounding
    * of T also prevents non-numeric NumericFunctions objects from being created. Uncomment the String instantiation
    * of NumericFunctions in GenericExp.exp3(), and check the error message.
    * */

    /** 12.2.1 Bound between generic types
     *
     * Bounded types are especially useful when you need to ensure that one type parameter is compatible with another.
     * Check the class "Pair", it uses two type parameters, T and V, and that V extends T. This means that V will
     * either be the same as T or a subclass of T. This ensures that the two arguments to Pair’s constructor will be
     * objects of the same type or of related types. For example, check GenericExp.exp4().
     *
     * */

    /****************************************** 12.3 Using wildcard arguments ****************************************/

    /*
    * As we explained before, after different type instantiation, the generic type is not compatible,
    * NumericFunctions<Double> is not compatible with NumericFunctions<Float>. But sometimes we need to compare them,
    * for example we want to add a generic method in NumericFunctions called absEqual(). If one object of
    * NumericFunctions<Double> contains the Double value 1.25 and the other object contains the Float value -1.25, then
    * absEqual() would return true.
    *
    * The first thought on absEqual implementation is : boolean absEqual(NumericFunctions<T> ob). But this won't work,
    * For example, if we call this method inside object NumericFunctions<Double>, in the method absEqual will require
    * ob also has type NumericFunctions<Double>.
    *
    * To overcome this problem, we need to use wildcard arguments in the absEqual. The wildcard argument is specified
    * by the ?, and it represents an unknown type. Check GenericExp.exp5(), we have implemented absEqual in
    * NumericFunctions.
    *
    * One last point: It is important to understand that the wildcard does not affect what type of NumericFunctions
    * objects can be created. This is governed by the extends clause in the NumericFunctions declaration. The wildcard
    * simply matches any valid NumericFunctions object.
    * */

    /** 12.3.1 Bounded wildcards
     *
     * Wildcard arguments can be bounded in much the same way that a type parameter can be bounded. A bounded wildcard
     * is especially important when you are creating a method that is designed to operate only on objects that are
     * subclasses of a specific superclass. Check GenericExp.exp6();, we added a method boundedWildcardsArgsTest() in
     * class simpleGen, this method takes an object with a wildcards argument. This argument extends a class "SuperClass"
     *
     * Notice, if a class is not a subclass of a specific superclass, it can't be used in this method. In another word,
     * we set a upper bound for the wildcards argument
     *
     * We can also set a lower bound for a wildcard by adding a "supper" keyword. Check GenericExp.exp8(), We added
     * a method lowerBoundWildArgsTest in class SimpleGen, we use keyword "supper" set a lower bound for wildcards
     * argument.
     * */

    /****************************************** 12.4 Generic methods ****************************************/

    /*
    * Methods inside a generic class can make use of a class’ type parameter and are, therefore, automatically generic
    * relative to the type parameter. However, it is possible to declare a generic method that uses one or more type
    * parameters of its own. Furthermore, it is possible to create a generic method that is enclosed within a non
    * generic class. Check GenericExp.exp9(), In class GenericMethodDemo, we created a genetic method arraysEqual().
    *
    * The syntax for a generic method: <generic-type-param-list> return-type method-name(param-list)
    * In all cases, generic-type­param­list is a comma­separated list of type parameters. Notice that for a generic method,
    * the type parameter list precedes the return type. For each generic type, we can use extends(upper bound),
    * super(lower bound) to set bound of the generic type. Check SimpleGen class to see examples.
    * */

    /****************************************** 12.5 Generic constructors ****************************************/

    /*
    * A constructor can be generic, even if its class is not. For example, in the following program, the class
    * GenericConsDemo is not generic, but its constructor is. Check GenericExp.exp10(); The class GenericConstructorDemo
    * has a constructor which takes a generic type argument. The generic type extends Number. As a result, all numeric
    * types which are subclass of Number can be used to construct the Object.
    * */

    /****************************************** 12.6 Generic interfaces ****************************************/

    /*
    * An interface can be generic. In GenericMethodDemo, we have used one(i.e. Comparable<T>) to ensure that elements
    * of two arrays could be compared. Check GenericExp.exp11(), we created an generic interface Containment and a
    * generic class MyArray which implements the interface. The general form of a generic interface:
    * interface interface-name<generic-type-param-list>{...}
    * Here, generic-type­param­list is a comma­separated list of type parameters.
    *
    * In general, if a class implements a generic interface, then that class must also be generic, at least to the
    * extent that it takes a type parameter that is passed to the interface. Check the MyArray class declaration:
    * class MyArray<T> implements Containment<T>
    * If We remove the first <T>, we will have compile error.
    *
    * Of course, if a class implements a specific type of generic interface, such as shown here:
    * class MyArray implements Containment<Double>
    * Then the implementing class does not need to be generic.
    *
    * Just as generic class, the type parameters in generic interface can also be bounded with keyword extends(upper)
    * and super(lower). For example:
    * interface Test<T extends Number, V super T>
    * Note, any class which implements the interface must has the same bound. For example, MyClass implements Test:
    * class MyClass<T extends Number, V super T> implements Test<T,V>{...}
    * Notice, the type bound is established in the class definition, there is no need to specify it again in the
    * implements Test clause. And it will generate compile time error.
    * */

    /****************************************** 12.7 RAW types and legacy code *************************************/

    /*
    * Because support for generics did not exist prior to JDK 5, it was necessary for Java to provide some transition
    * path from old, pre­generics code. Simply put, pre­generics legacy code had to remain both functional and
    * compatible with generics. This meant that pre­generics code must be able to work with generics, and generic
    * code must be able to work with pre­generics code.
    *
    * To handle the transition to generics, Java allows a generic class to be used without any type arguments.
    * This creates a raw type for the class. This raw type is compatible with legacy code, which has no knowledge of
    * generics. The main drawback to using the raw type is that the type safety of generics is lost.
    *
    * Check GenericExp.exp12(); We created an rawType with : SimpleGen rawType=new SimpleGen(8);
    * Notice that no type argument is given, the compile automatically assign Object as the type. So when we call the
    * rawType.getObject(); it will return an Object type.
    *
    * A raw type is not type safe. Thus, a variable of a raw type can be assigned a reference to any type of SimpleGen
    * object. The reverse is also allowed, in which a variable of a specific SimpleGen type can be assigned a
    * reference to a raw SimpleGen object. However, both operations are potentially unsafe because the type checking
    * mechanism of generics is circumvented.
    *
    * One final point: You should limit the use of raw types to those cases in which you must mix legacy code with
    * modern, generic code. Raw types are simply a transitional feature and not something that should be used for
    * new code.
    * */

    /********************************** 12.8 TYPE INFERENCE WITH THE DIAMOND OPERATOR ********************************/

    /*
    * For version of Java prior to JDK 7, to create an instance of generic class, you must write the full type :
    * SimpleGen<String> strOb=new SimpleGen<String>("Test");
    * Notice we specify two times the generic type String, there is no need. JDK 7 added a syntactic element that lets
    * you avoid the second specification. So we can write :
    * SimpleGen<String> strOb=new SimpleGen<>("Test");
    * The general form is :
    * class-name<generic-type-arg-list> var-name= new class-name<>(cons-arg-list)
    *
    * Although the type inference are mostly used in variable declaration statement, we can also use it in parameter
    * passing. Check GenericExp.exp13(); The if(ob1.isSame(new TwoGen<>(88,"test"))) does not have type declaration.
    * It's the compiler which infer 88 to integer, and "test" to String.
    * */

    /******************************** 12.9 Local variable TYPE INFERENCE and GENERICS ********************************/

    /*
    * As just explained, type inference is already supported for generics through the use of the diamond operator.
    * However, you can also use the new local variable type inference feature added by JDK 10 with a generic class.
    * For example, we can write the following declaration
    * var strOb=new SimpleGen<String>("Test");
    * or
    * var strOb2=new SimpleGen<>("test");
    * In this case, the type of strOb is inferred to be SimpleGen<String>
    * */

    /******************************** 12.10 Erasure ********************************/

    /**
     *
     * An important constraint that governed the way generics were added to Java was the
     * need for compatibility with previous versions of Java. Simply put: generic code had to
     * be compatible with preexisting, nongeneric code. Thus, any changes to the syntax of the
     * Java language, or to the JVM, had to avoid breaking older code. The way Java
     * implements generics while satisfying this constraint is through the use of "erasure".
     *
     * In general, here is how erasure works. When your Java code is compiled, all generic
     * type information is removed (erased). This means replacing type parameters with their
     * bound type, which is Object if no explicit bound is specified, and then applying the
     * appropriate casts (as determined by the type arguments) to maintain type compatibility
     * with the types specified by the type arguments. The compiler also enforces this type
     * compatibility. This approach to generics means that no type parameters exist at run
     * time. They are simply a source­code mechanism
     * */

    /************************************** 12.11 Ambiguity errors ******************************************/

    /**
    * The inclusion of generics gives rise to a new type of error that you must guard against:
     * ambiguity. Ambiguity errors occur when erasure causes two seemingly distinct generic
     * declarations to resolve to the same erased type, causing a conflict.
     * Check class AmbiguityDemo, it declares two generic types: T and V. Inside AmbiguityDemo,
     * an attempt is made to overload set( ) based on parameters of type T and V. This looks
     * reasonable because T and V appear to be different types. However, there are two
     * ambiguity problems here:
     * 1. There is no requirement that T and V actually be different types. For example, at run time T,V can be both
     *    String.
     * 2. The type erasure of method set() effectively reduces both version to : void set(Object o)
     *
     * Thus, the overloading of set() is inherently ambiguous. The solution is two use two different method names
     * or different numbers of argument.
     *
     * Check GenericExp.exp14();
     * */

    /************************************** 12.12 Some generic restrictions ******************************************/

    /*
    * There are a few restrictions that you need to keep in mind when using generics. They involve creating objects
    * of a type parameter, static members, exceptions, and arrays.
    * */

    /** 12.12.1 Type parameters can't be instantiated
     *
     * It is not possible to create an instance of a type parameter. For example:
     *
     * class Test<T>{
     *     T ob;
     *     Test(){
     *     // It's not possible to creat an instance of T
     *         ob=new T();
     *     }
     * }
     *
     * The reason is easy to understand: the compiler has no way to know what type of object to create. T is simply
     * a placeholder.
     * */


    /** 12.12.2 Restrictions on Static members
     *
     * No static member can use a type parameter declared by the enclosing class. For example, both of the following
     * static members of this class are illegal:
     * class Wrong<T>{
     *  static T ob;
     *
     *  static T getOb(){
     *      return ob;
     *  }
     * }
     *
     * Although you can’t declare static members that use a type parameter declared by the
     * enclosing class, you can declare static generic methods, which define their own type
     * parameters, as was done in class GenericMethodDemo. The reason is that, we can't know T
     * without creating an instance. In GenericMethodDemo, because the generic type are arguments
     * So when we call the method, the arguments are already instantiated.
     * */

    /** 12.12.3 Generic Array Restrictions
     * There are two important generics restrictions that apply to arrays:
     * 1. You cannot instantiate an array whose element type is a type parameter.
     * 2. You cannot create an array of type­specific generic references.
     *
     * Check GenericExp.exp14() and class GenericRestriction
     * */

    /** 12.12.4 Generic Exception Restriction
     * A generic class cannot extend Throwable. This means that you cannot create generic exception classes.
     * */

    /************************************** 12.13 Future works ******************************************/

/*
* As mentioned at the start, this section gives you sufficient knowledge to use generics effectively in your own
* programs. However, there are many side issues and special cases that are not covered here. Readers especially
* interested in generics will want to learn about how generics affect class hierarchies, run­time type comparisons,
* and overriding, for example. Discussions of these and other topics are found in book "Java: The Complete Reference,
* Eleventh Edition".
* */
    public static void main(String args[]) {
        /** 12.1.1 A simple generics example*/
       // GenericExp.exp1();

        /** 12.1.4 Generic class with multiple type parameters*/
       // GenericExp.exp2();

        /** 12.1.6 Cast between generic class object */
       // GenericExp.exp7();

        /** 12.2 Bounded types*/
       // GenericExp.exp3();

        /** 12.3 Using wildcard arguments*/
       // GenericExp.exp5();

        /** 12.3.1 Bounded wildcards*/
       // GenericExp.exp6();
      // GenericExp.exp8();

       /** 12.4 Generic methods*/
       // GenericExp.exp9();

       /** 12.5 Generic constructors*/
      //  GenericExp.exp10();

        /** 12.6 Generic interfaces*/
      //  GenericExp.exp11();

        /** 12.7 Raw type*/
       // GenericExp.exp12();

        /** 12.8 type inference*/
        GenericExp.exp13();
    }
}
