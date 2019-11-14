package org.pengfei.Lesson00_Java_Basics.S13_Lambda_Expression;

import org.pengfei.Lesson00_Java_Basics.S13_Lambda_Expression.source.LambdaExpressionExp;

public class S13_Lambda_Expression {

    /******************************************** 13. Introduction *******************************************/
    /*
    * Beginning with JDK 8, Lambda Expression was added to Java that profoundly enhanced the expressive power of the
    * language. It add new syntax elements to the language which can streamline the way that certain common constructs
    * are implemented.
    *
    * We will also see another feature Method Reference, which lets you refer to a method without executing it.
    *
    * In this section, we will learn the following key point:
    * - Know the general form of a lambda expression
    * - Understand the definition of a functional interface
    * - Use expression lambdas
    * - Use block lambdas
    * - Use generic functional interfaces
    * - Understand variable capture in a lambda expression
    * - Throw an exception from a lambda expression
    * - Understand the method reference
    * - Understand the constructor reference
    * - Know about the predefined functional interfaces in java.util.function
    * */

    /************************************ 13.1 Introducing lambda expressions ***************************************/

    /*
    * Key to understand the lambda expression are two constructs:
    * 1. The lambda expression: A lambda expresion is, essentially, an anonymous(unnamed) method. However, this method
    *           is not executed on its own. Instead, it's used to implement a method defined by a functional interface.
    *           Thus, a lambda expression results in a form of anonymous class. Lambda expressions are also commonly
    *           referred to as closures.
    * 2. The functional interface: A functional interface is an interface that contains one and only one abstract
    *           method. Normally, this method specifies the intended purpose of the interface. Thus, a functional
    *           interface typically represents a single action. For example, the interface Runnable is a functional
    *           interface because it defines only one method: run. Therefore, run() defines the action of Runnable.
    *           Furthermore, a functional interface defines the target type of a lambda expression.
    *
    * Note:
    * 1. A lambda expression can be used only in a context in which a target type is specified.
    * 2. A functional interface is sometimes referred to as a SAM type, where SAM stands for Single Abstract Method.
    * */

    /************************************ 13.2 Lambda expression Fundamentals ***************************************/

    /*
    * The lambda expression relies on a syntax element and operator "->", it's also referred as the lambda operator or
    * the narrow operator. It divides a lambda expression into two parts:
    * - Left side specifies any parameters required by the lambda expression.
    * - Right side specifies the actions of lambda expression. It's also called lambda body.
    *
    * Java defines two types of lambda bodies:
    * 1. A single expression
    * 2. A block of code.
    * */

    /** 13.2.1 First lambda expression examples
     *
     * 1. Consider the following LE(Lambda Expression):
     * ()-> 88.8
     * Here, it takes no parameter, it returns the constant value 88.8. The return type is inferred to be double. We
     * can write a equivalent method as followed:
     * double myMeth() {return 88.8;}
     * As we explained before, a LE can be considered as a anonymous method.
     *
     * 2. Consider the following LE:
     * (n) -> (n%2) ==0
     * Here, it takes one parameter n, and return a boolean. Notice we did not specify the type of n. It's inferred by
     * JDK. But nothing prevents us to do (int n)-> .... Other important point, just as any method, LE can return any
     * valid type.
     *
     * */

    /** 13.2.2 Functional Interfaces
     *
     * As stated, a FI(Functional Interface) is an interface that specifies only one abstract method. Note, in section 7,
     * we have learned, beginning with JDK 8, methods in interface are not all abstract. For example, default, static,
     * private methods in Interface are not abstract. This means that a FI can include many default, static or private
     * methods, but in all cases it must have one and only one abstract method. Note in a interface, if one method
     * is non-default, non-static, non-private, its implicitly abstract. Therefore, there is no need to add the
     * abstract keyword(Although you can add it, if you like)
     *
     * A simple example of FI:
     * interface MyValue{
     *     double getValue();
     * }
     *
     * Here, getValue() is implicitly abstract. As it's the only one abstract method, MyValue is a FI.
     *
     * */

    /** 13.2.3 FI example
     * Check LambdaExpressionExp.exp1(); We first create a reference myValue, then assign it to a LE. Check the code
     * MyValue myValue1=()->88.8;
     * Notice, no class is created which implements the Interface MyValue, and no object is created explicitly. Thus
     * we can call the abstract method of the Interface getValue to return the constant 88.8.
     *
     * This works, because When a lambda expression occurs in a target type context, an instance of a class is
     * automatically created that implements the functional interface, with the lambda expression defining the
     * behavior of the abstract method declared by the functional interface. When that method is called through
     * the target, the lambda expression is executed. Thus, a lambda expression gives us a way to transform a code
     * segment into an object. In this example, the LE becomes the implementation for the getValue method.
     *
     * Check LambdaExpressionExp.exp2(4.0); We changed the interface and name the new version as MyParamValue, which
     * lets you pass a value to getValue(). The LE MyParamValue myValue=(n)-> 1.0/n; takes a parameter also.
     * Note : The abstract method in the FI and LE which implements it must have the same number and same type of
     * parameters. In another word, For a lambda expression to be used in a target type context, the type of the
     * abstract method and the type of the lambda expression must be compatible.
     *
     * Notice that the type of n is not specified. Its inferred from the context. In this example, the type of n is
     * inferred from the parameter type of getValue() as defined by the Interface getParamValue. We can also defines
     * it explicitly in the LE such as MyParamValue myValue=(double n)-> 1.0/n;
     *
     * Beginning with JDK 11, you can also explicitly indicate type inference for a lambda expression parameter by
     * use of var. For example MyParamValue myValue=(var n)-> 1.0/n; works too.
     * */

    /** 13.2.4 Lambda Expressions in Action
     *
     * In this example, we write the following program. It defines a functional interface called "NumericTest" that
     * declares the abstract method test(). This method has two int parameters and returns a boolean result. Its
     * purpose is to determine if the two arguments passed to test() satisfy some condition. It returns the
     * result of the test. In main(), three different tests are created through the use of lambda expressions.
     * 1. It tests if the first argument can be evenly divided by the second.
     * 2. It determines if the first argument is less than the second.
     * 3. It returns true if the absolute values of the arguments are equal.
     * Notice that the lambda expressions that implement these tests have two parameters and return a boolean
     * result. This is, of course, necessary since test() has two parameters and returns a boolean result
     *
     * Check LambdaExpressionExp.exp3(1,4);, we use three different LE to implement the FI NumericTest. Notice, the
     * left side of LE must correspond the parameters of the FI, the right side of the LE has two possibilities:
     * 1. Expression: the type of the expression must be the return type of the FI
     * 2. Code block: it must return the same return type of the FI.
     *
     * If you don't specify the type of argument in a LE, it's type is inferred by a target type context. The target
     * type context is constituted by variable initialization, assignment, argument passing, type casts, the ? operator,
     * array initializers, return statements, and lambda expressions, themselves, can also serve as target type contexts.
     * */

    /** 13.2.5 Declare Types in LE
     * In cases in which you need to explicitly declare the type of a parameter, then all
     * of the parameters in the list must have declared types. For example, this is correct:
     * (int a, int b) -> a>b
     *
     * The following two is wrong:
     * (int a, b) -> a>b
     * (a, int b) -> a>b
     * */

    /** 13.2.5 Block in LE
     *
     * As we explained before, a single expression in lambda bodies are referred to as expression bodies. This single
     * expression will be evaluated and become the lambda's value. Java also supports a block of code that contain more
     * than one statement in LE, which is called block body.
     *
     * In a block body, you can declare local variables, use loops, specify if and switch, create nested blocks, and so
     * on. The key is that you must explicitly use a return statement to return a value.
     * Check LambdaExpressionExp.exp5();
     * */

    /** 13.2.6 Generic Functional interfaces
     *
     * A lambda expression, itself, cannot specify type parameters. Thus, a lambda expression cannot be generic.
     * (Of course, because of type inference, all lambda expressions exhibit some “generic­like” qualities.) However,
     * the functional interface associated with a lambda expression can be generic. In this case, the target type of
     * the lambda expression is determined, in part, by the type argument or arguments specified when a functional
     * interface reference is declared.
     *
     * Check LambdaExpressionExp.exp6(), We declare a generic FI GenericTest, then we implement this FI with a Integer
     * LE and a String LE.
     *
     * */

    /** 13.2.7 Passing a LE as an argument
     *
     * A lambda expression can be used in any context that provides a target type. The target contexts used by the
     * preceding examples are assignment and initialization. Another one is when a lambda expression is passed as
     * an argument. In fact, passing a lambda expression as an argument is a common use of lambdas. Moreover, it is a
     * very powerful use because it gives you a way to pass executable code as an argument to a method. This greatly
     * enhances the expressive power of Java.
     *
     * Check LambdaExpressionExp.exp7(); to see how we use LE as argument in a method.
     * */

    /** 13.2.8 Lambda expressions and variable capture
     *
     * Variables defined by the enclosing scope of a lambda expression are accessible within the lambda expression.
     * For example, a lambda expression can use an instance variable or static variable defined by its enclosing class.
     * A lambda expression also has access to this (both explicitly and implicitly), which refers to the invoking
     * instance of the lambda expression’s enclosing class. Thus, a lambda expression can obtain or set the value of
     * an instance variable or static variable and call a method defined by its enclosing class.
     *
     * However, when a lambda expression uses a local variable from its enclosing scope, a special situation is
     * created that is referred to as a variable capture. In this case, a lambda expression may only use local
     * variables that are effectively final. An effectively final variable is one whose value does not change after
     * it is first assigned. There is no need to explicitly declare such a variable as final, although doing so would
     * not be an error. (The this parameter of an enclosing scope is automatically effectively final, and lambda
     * expressions do not have a this of their own.)
     *
     * It is important to understand that a local variable of the enclosing scope cannot be modified by the lambda
     * expression. Doing so would remove its effectively final status, thus rendering it illegal for capture.
     *
     * Check LambdaExpressionExp.exp8();
     * */

    /** 13.2.9 Throw an exception from within a lambda expression
     *
     * A lambda expression can throw an exception. If it throws a checked exception, then that exception must be
     * compatible with the exception(s) listed in the throws clause of the abstract method in the functional interface.
     * Check LambdaExpressionExp.exp9(), in this LE, we use a bufferReader to read user input. The readLine method might
     * throw an exception. As a result in the FI, the abstract method compareInput must throws the same "Exception".
     * Without it, the program will not compile because the lambda expression will no longer be compatible with the
     * Functional Interface.
     * */

    /** 13.2.10 Use array as parameter in a LE
     *
     * We can use array as parameter in a LE. However, when the type of the parameter is inferred, the parameter to the
     * lambda expression is not specified using the normal array syntax. Rather, the parameter is specified as a simple
     * name, such as n, not as n[]. Remember, the type of a lambda expression parameter will be inferred from the
     * target context. Thus, if the target context requires an array, then the parameter’s type will
     * automatically be inferred as an array.
     *
     * Check LambdaExpressionExp.exp10(), we did not use n[] or Double[] to define the parameter is an array in square.
     *
     * It's legal to declare the parameter type explicitly(e.g. in power LE). But we gain nothing by doing so.
     *
     * Conclusion, use type inference in LE is recommended.
     *
     * */


    /************************************ 13.3 Method references ***************************************/

    /*
    * There is an important feature related to lambda expressions called the method reference. A method reference
    * provides a way to refer to a method without executing it. It relates to lambda expressions because it, too,
    * requires a target type context that consists of a compatible functional interface. When evaluated, a method
    * reference also creates an instance of a functional interface. There are different types of method references.
    * We will begin with method references to static methods.
    * */

    /** 13.3.1 Method references to static methods
     *
     * A method reference to a static method is created by specifying the method name preceded by its class name, using
     * this general syntax:  ClassName::methodName
     *
     * Notice that the class name is separated from the method name by a double colon. The :: is a separator that was
     * added to Java by JDK 8 expressly for this purpose. This method reference can be used anywhere in which it is
     * compatible with its target type.
     *
     * Check LambdaExpressionExp.exp11(); We declare an Interface IntPredicate (FI). We then declare three static method
     * which implements the IntPredicate's abstract method (without using keyword implements). At last, we declare a
     * method(numTest) which use IntPredicate as parameter. In this example, when we call numTest(LambdaExpressionExp::isEven, 5)
     * The method isEven is passed to the method numTest, and numTest will use isEven to determine 5 is even or not.
     * This works because isEven is compatible with the IntPredicate functional interface. Thus, the expression
     * LambdaExpressionExp::isEven evaluates to a reference to an object in which isEven() provides the implementation
     * of test() in IntPredicate. The other two calls to numTest() work in the same way.
     * */

    /** 13.3.2 Method references to Instance methods
     *
     * A reference to an instance method on a specific object is created by this basic syntax: objRef::methodName
     * As you can see, the syntax is similar to that used for a static method, except that an object reference is
     * used instead of a class name. Thus, the method referred to by the method reference operates relative to objRef.
     *
     * Check LambdaExpressionExp.exp12(). Because the method is no longer static, so we need to create a class(MyIntNum) to
     * hold the method(isFactor), and create instances myNum1 and myNum2. Pay special attention to the line:
     * IntPredicate ip=myNum1::isFactor;
     * Here, the method reference assigned to ip refers to an instance method isFactor() on myNum1. Thus, when test()
     * is called through that reference, as shown here: ip.test(n)
     *
     * It is also possible to handle a situation in which you want to specify an instance method that can be used with
     * any object of a given class—not just a specified object. In this case, you will create a method reference as
     * shown here:  ClassName::instanceMethodName
     *
     * Here, the name of the class is used instead of a specific object, even though an instance method is specified.
     * With this form, the first parameter of the functional interface method matches the invoking object and the
     * second parameter matches the parameter (if any) specified by the instance method.
     *
     * Check LambdaExpressionExp.exp13(). You can notice the first difference is in the FI MyIntNumPredicate. The first
     * parameter to test() is of type MyIntNum. It will be used to receive the object being operated upon. This allows
     * the program to create a method reference to the instance method isFactor() that can be used with any MyIntNum
     * object. Second difference is the method reference declaration, we don't use the objectRef anymore, we use the
     * classname(i.e. MyIntNumPredicate inp=MyIntNum::isFactor;). The third difference is when we call method test().
     * Notice, we specify the objectRef, when we call test()(i.e. inp.test(myNum1,n);)
     *
     * */

    /** 13.3.3. Method reference to a generic method
     *
     * Often, because of type inference, you won’t need to explicitly specify a type argument to a generic method when
     * obtaining its method reference, but Java does include a syntax to handle those cases in which you do.
     *
     * Check LambdaExpressionExp.exp14(), we declare a generic FI NumberPredicate, then we declare a generic method
     * isEqual(we use static method for simplicity, for object method reference, just put this method in a class, then
     * create an object of this class).
     *
     * Notice NumberPredicate<Integer> np=LambdaExpressionExp::isEqual; we just specify the generic type in the left
     * part, NumberPredicate<Integer> np=LambdaExpressionExp::<Integer>isEqual; is also correct.
     * */

    /** 13.3.4 keyword Super in method reference
     *
     * A method reference can use the keyword super to refer to a superclass version of a method. The general forms
     * of the syntax are :
     * 1. super::methodName
     * 2. typeName.super::methodName.
     * In the second form, typeName must refer to the enclosing class or a superinterface.
     * */

    /************************************ 13.4 Constructor references ***************************************/

    /*
    * Similar to the way that you can create references to methods, you can also create references to constructors.
    * Here is the general form of the syntax that you will use: classname::new
    * This reference can be assigned to any functional interface reference that defines a method compatible with the
    * constructor.
    *
    * Check LambdaExpressionExp.exp15(), First, we create a FI called ConstructorRefDemo, the only one abstract method
    * func() returns a reference of type ConstructorRefDemoClass. Note, this class is the type of Object which our
    * constructor reference wants to construct. Second, we create the class which we want to construct, in this class
    * we implement the constructor. You can notice we have two constructors: 1. takes zero parameter, 2. takes a string
    * parameter. Check the func() method, it takes also a String as parameter. So when we call
    * ConstructorRefDemo crd=ConstructorRefDemoClass::new, it's the second constructor which will be referred.
    * When we call ConstructorRefDemoClass constructorRefDemoClassObj = crd.func("Testing"); we create an instance
    * of Class ConstructorRefDemoClass. In another word, crd(constructor reference) provide another way to call
    * the second constructor to build object.
    *
    * Imagine if we build another FI which has the following abstract method:
    *  ConstructorRefDemoClass func();
    * when we construct the constructor reference with this FI, it will call the first constructor which takes no
    * parameter.
    *
    * In general, the constructor that will be used when ::new is specified is the one whose parameters match those
    * specified by the functional interface.
    * */

    /** 13.4.1 Constructor reference with array
     *
     * To create a constructor reference for an array, use this construct: type[]::new
     * Here, type specifies the class of object being created.
     *
     * Check LambdaExpressionExp.exp16(), check the comments carefully, it's quite different compare to simple
     * constructor reference
     *
     * */

    /** 13.4.2 Generics in array constructor reference
     * The constructor reference can be generic too. Check LambdaExpressionExp.exp17(); We created a generic FI. Then
     * we use a Thread constructor reference to create an Array of Thread. At last we create each Thread with a distinct
     * Thread name and put them in the Array.
     *
     * */

    /************************************ 13.5 Predefined functional interfaces ************************************/

    /*
    * In this section, we have seen how to define our own FI, and how to implement them by using LE, method reference
    * and constructor reference.
    *
    * In many cases, you won’t need to define your own functional interface because the package java.util.function
    * provides several predefined ones. Here are some examples(1st element is the interface name, 2nd is the description):
    * - UnaryOperator<T> : Apply a unary operation to an object of type T and return the result, which is also of type
    *                      T. Its method is called apply().
    * - BinaryOperator<T> : Apply an operation to two objects of type T and return the result of type T. Its method is
    *                     called apply().
    * - Consumer<T> : Apply an operation on an object of type T. Its method is called accept().
    * - Supplier<T> : Return an object of type T. Its method is called get().
    * - Function<T,R> : Apply an operation to an object of type T and return the result as an object of type R. Its
    *                  method is called apply().
    * - Predicate<T> : Determine if an object of type T fulfills some constraint. Returns a boolean value that indicates
    *                  the outcome. Its method is called test(T val).
    *
    * Check LambdaExpressionExp.exp18(), we use lambda expression and static method reference to implement the FI
    * Predicate.
    * */

    /************************************ 13.6 LE application example  ************************************/

    /*
    * One example is the stream package java.util.stream. This package defines several stream interfaces, the most
    * general of which is Stream. As it relates to java.util.stream, a stream is a conduit for data. Thus, a stream
    * represents a sequence of objects. Furthermore, a stream supports many types of operations that let you create a
    * pipeline that performs a series of actions on the data. Often, these actions are represented by lambda
    * expressions. For example, using the stream API, you can construct sequences of actions that resemble, in concept,
    * the type of database queries for which you might use SQL. Furthermore, in many cases, such actions can be
    * performed in parallel, thus providing a high level of efficiency, especially when large data sets are involved.
    * Put simply, the stream API provides a powerful means of handling data in an efficient, yet easy to use way.
    * One last point: although the streams supported by the new stream API have some similarities with the I/O streams
    * described in Section 9, they are not the same.
    * */

    public static void main(String[] args){
        /** 13.2.3 FI example*/
       // LambdaExpressionExp.exp1();
       // LambdaExpressionExp.exp2(4.0);

        /** 13.2.4 Lambda expressions in Action*/
        // LambdaExpressionExp.exp3(1,4);
        // LambdaExpressionExp.exp4();
        // LambdaExpressionExp.exp5();

        /** 13.2.6 Generic Functional interfaces*/
       //  LambdaExpressionExp.exp6();

        /** 13.2.7 Passing LE as argument*/
       // LambdaExpressionExp.exp7();

        /** 13.2.8 variable capture*/
       // LambdaExpressionExp.exp8();

        /** 13.2.9 Exception in a lambda expression*/
       // LambdaExpressionExp.exp9();

        /** 13.2.10 Arrays in lambda expression*/
       // LambdaExpressionExp.exp10();

        /** 13.3.1 Static method reference*/
        // LambdaExpressionExp.exp11();

        /** 13.3.2 Object method reference*/
        // LambdaExpressionExp.exp12();
       // LambdaExpressionExp.exp13();

        /** 13.3.3 generic method reference*/
       // LambdaExpressionExp.exp14();

        /** 13.4 Constructor reference*/
         // LambdaExpressionExp.exp15();
       // LambdaExpressionExp.exp16();
       // LambdaExpressionExp.exp17();

        /** 13.5 Predefined FI*/
        LambdaExpressionExp.exp18();
    }
}
