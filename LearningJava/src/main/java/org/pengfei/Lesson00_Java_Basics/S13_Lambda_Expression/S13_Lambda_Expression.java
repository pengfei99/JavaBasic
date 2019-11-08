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
     * Check LambdaExpressionExp.exp3(1,4);, we use three different LE to implement the FI NumericTest
     * */
    public static void main(String[] args){
        /** 13.2.3 FI example*/
       // LambdaExpressionExp.exp1();
       // LambdaExpressionExp.exp2(4.0);

        /** 13.2.4 Lambda expressions in Action*/
        LambdaExpressionExp.exp3(1,4);
    }
}
