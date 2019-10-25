package org.pengfei.Lesson00_Java_Basics.S05_Advance_Classes_Methods;

import com.google.errorprone.annotations.Var;
import org.pengfei.Lesson00_Java_Basics.S05_Advance_Classes_Methods.source.*;

public class S05_Advance_Classes_Methods {

    /******************************************** 5. Introduction *******************************************/

    /*
    * In this section, we will examine Classes and method with more details, we will learn the following key concepts:
    * - Control access to members
    * - Pass objects to a method
    * - Return objects from a method
    * - Overload methods
    * - Overload constructors
    * - Use recursion
    * - Apply static
    * - Use inner classes
    * - Use varargs
    *  */

    /*********************************** 5.1 Controlling access to class members ************************************/

    /*
    * Class provides two major benefits:
    * - It links data with the code that manipulates it (Encapsulation)
    * - It provides access control on class members
    *
    * Restricting access to a class’ members is a fundamental part of object­oriented programming because it helps
    * prevent the misuse of an object. By allowing access to private data only through a well­defined set of methods,
    * you can prevent improper values from being assigned to that data—by performing a range check, for example. It is
    * not possible for code outside the class to set the value of a private member directly. You can also control
    * precisely how and when the data within an object is used. Thus, when correctly implemented, a class creates a
    * “black box” that can be used, but the inner workings of which are not open to tampering.
    * */

    /** 5.1.1 Java's access modifiers
     * Class member access control is achieved through the use of four access modifiers:
     * - public: fields/methods can be accessed by code outside this class.
     * - no modifier: fields/methods can be accessed by code inside the package.
     * - private: fields/methods can't be accessed by code outside this class.
     * - protected: fields/methods can be accessed by code inside the package, and by a subclass of its class in other
     *              packages.
     *
     * Check AccessControlExp and AccessControlTester to see how access control is done on class members.
     * */

    /*********************************** 5.2 Pass objects to methods ************************************/

    /*
    * Method can take any data type as arguments, (e.g. primitive type, user-define class, etc.). To pass objects to
    * methods, in the method definition, method-name(class-name arg-name). when you call this method, method-name(val)
    * note that the val object must belong to the same class-name in the method definition.
    *
    * In certain cases, the effects of passing an object will be different from passing primitive types. To see why,
    * you need to understand in a general sense the two ways in which an argument can be passed to a subroutine.
    * - Call-by-value: This approach copies the value of an argument into the formal parameter of the subroutine.
    *                  Therefore, changes made to the parameter of the subroutine have no effect on the argument in
    *                  the call.
    * - Call-by-reference: In this approach, a reference to an argument(not the value of the argument) is passed to
    *                   the parameter. Inside the subroutine, this reference is used to access the actual argument.
    *                   This means changes made to the parameter will affect the argument used to call the subroutine.
    * Although Java uses call-by-value to pass arguments, the precise effect differs between whether a primitive type
    * or a reference type is passed.
    *
    * For primitive type, it's call-by-value. Check  PassObjToMethods.exp1();
    * For object, it's call-by-reference.  PassObjToMethods.exp2();
    *
    * Remember, when an object reference is passed to a method, the reference itself is passed by use of call­by­value.
    * However, since the value being passed refers to an object, the copy of that value will still refer to the same
    * object referred to by its corresponding argument.
    * */

    /** 5.2.1 Pass primitive type by reference
     *
     * Java defines a set of classes that wrap the primitive types into objects. These are:
     * - Double (object)- double (primitive)
     * - Float
     * - Byte
     * - Short
     * - Integer
     * - Long
     * - Character
     * In addition to allowing a primitive type to be passed by reference, these wrapper classes define several
     * methods that enable you to manipulate their values. For example, the numeric type wrappers include methods
     * that convert a numeric value from its binary form into its human­readable String form, and vice versa.
     * */

    /***************************************** 5.3 Returning objects  **********************************************/

    /*
    * A method can return any type of data, including class types. Check ReturningObjExp.exp1();
    * */

    /***************************************** 5.4 Method overloading  **********************************************/

    /*
    * In java, many method in the same class can share the same name, as long as their parameter declaration are
    * different. This is called Method overloading (polymorphism implementation)
    *
    * It has one important restriction: the type/number of the parameters of each overload method must differ. It's
    * not sufficient for two methods to differ only in their return types.
    *
    * Check MethodOverloadingExp, we have four different implementation of method myPrint.
    * */

    /************************************* 5.5 Constructor overloading  *****************************************/

    /*
    * Like methods, constructors can also be overloaded. With multiple constructors, we can build objects in a variety
    * ways. */

    /********************************************** 5.6 Recursion  **********************************************/
   /*
    * In Java, a method can call itself. This process is called recursion, and a method that calls itself is said
    * to be recursive. In general, recursion is the process of defining something in terms of itself and is somewhat
    * similar to a circular definition. The key component of a recursive method is a statement that executes a call
    * to itself. Recursion is a powerful control mechanism.
    *
    * The classic example of recursion is the computation of the factorial of a number. The factorial of a number N is
    * the product of all the whole numbers between 1 and N. For example, 3 factorial is 1 × 2 × 3
    * Check  RecursionExp.factIteration(5); and RecursionExp.factIteration(5);
    * */

    /****************************************** 5.7 Understanding static  ****************************************/

    /* Check S00_StaticKeyword for more example */

    /**************************** 5.8 Introducing nested and inner classes  ****************************************/

    /*
    * A nested class does not exist independently of its enclosing class. Thus, the scope of a nested class is
    * bounded by its outer class. A nested class that is declared directly within its enclosing class scope is
    * a member of its enclosing class. It is also possible to declare a nested class that is local to a block.
    *
    * There are two general types of nested classes:
    * - not static : Non-static nested class is also called inner class. It has access to all of the variables and
    *                methods of its outer class and may refer to them directly in the same way that other non-static
    *                members of the outer class do.
    * - static : Check S00_StaticKeyword for more example on static nested class. The major difference is that a nested
    *            static class can only access the static member of the outer class. Because static member can be called
    *            without creating the object of the class, if a static class calls non static member which does not
    *            exist without object. It will raise errors.
    *
    * An inner class is often used to provide a set of services that is used only by its enclosing class. Check
    * the NestedClassExp, we use a inner class to group all arithmetic operation, such as min, max , avg. And it
    * can access directly the field nums in the outer class. But when outer class wants to call inner class method,
    * we need to create an object of the inner class in the outer class.
    *
    * Note that a inner class definition can also be located within a block scope(e.g. method, for-loop, etc.)
    * */

    /**************************** 5.9 VarArgs: Variable-length arguments  ****************************************/

    /*
    * In some case, your methods may take no or more arguments, For example, a method that opens an database connection
    * you need to specify the hostname, portNumber, uid, pwd, etc. But some of these arguments can be omitted.
    *
    * In the past, we have two ways to deal this situation:
    * - 1. If the maximum number of arguments was small and known, then you could create overloaded versions of the
    *      method with different number of arguments.
    * - 2. If the maximum number of arguments was large or unknowable, then the method can take an array as arguments.
    *
    * These two works, but it creates many problems too. Solution 1. you need to duplicate code and write a lot of code.
    * Solution 2, the user of the method has to create an array which contains all the arguments.
    *
    * Beginning with JDK 5, this need was addressed by the inclusion of a feature that simplified the creation of
    * methods that require a variable number of arguments. This feature is called varargs, which is short for
    * variable­length arguments. A method that takes a variable number of arguments is called a variable­arity method,
    * or simply a varargs method. The parameter list for a varargs method is not fixed, but rather variable in length.
    * Thus, a varargs method can take a variable number of arguments
    *
    * */

    /** 5.9.1  Varargs basics
     * A variable­length argument is specified by three periods (...). For example, check the  VarArgsExp.vaTest method
     * it takes a variable number of arguments: int ... v . This syntax tells the compiler that vaTest() can be called
     * with zero or more arguments. Furthermore, it causes v to be implicitly declared as an array of type int[ ].
     * Thus, inside vaTest( ), v is accessed using the normal array syntax
     *
     * Notice two important thing:
     * - Varargs v is an array, so it's operated on as an array. The ... indicates v is a Varargs.
     * - When vaTest() is called with different numbers of arguments, including no arguments at all. The arguments
     *   are automatically put in an array and passed to v. In the case of no arguments, the length of the array is zero.
     * */

    /** 5.9.2 Use Varargs with other normal parameters
     * A method can have “normal” parameters along with a variable­length parameter. However, the variable­length
     * parameter must be the "last parameter" declared by the method. For example, check VarArgsExp.vaTest1.
     *
     * Notice there are two restriction on varargs:
     * - Must be the last parameter declared by the method
     * - Must be only one varargs in one method
     * */

    /** 5.9.3 Overloading varargs methods
     * You can overload a method that takes a variable-length argument. As you can't do overload by the number of
     * varargs, you can do the overload with different types. Check VarArgsExp.vaTest(double ... v). which overload the
     * vaTest by replacing int by double.
     *
     * You can also consider adding normal parameter into vaTest as another way to overload it. In this case, Java uses
     * both the number of arguments and the type of the arguments to determine which method to call.
     * */

    /** 5.9.4 Varargs and ambiguity
     * When we overload a method by using the different types of varargs. If the types can be cast implicitly, and when
     * we call it with no argument, it creates an ambiguity. Check VarArgsExp.vaTest(), if we just do overload with
     * type int and double, there is no error. But If I add the boolean overload, it does not compile at all, when I
     * call VarArgsExp.vaTest();
     *
     * There is another another ambiguity. If the normal parameter and varargs have the same type, the compile can't
     * resolve which one to call. Check the last vaTest overload method at line 44 of the class VarArgsExp. If I add
     * this method, we can't call VarArgsExp.vaTest(1,2,3,4,5); anymore. Does this translate into a call to
     * vaTest(int ...), with one varargs argument, or into a call to vaTest(int, int ...) with no varargs arguments?
     * There is no way for the compiler to answer this question. Thus, the situation is ambiguous.
     *
     * Because of ambiguity errors like those just shown, sometimes you will need to forget about the
     * overloading and simply use two different method names. Also, in some cases,
     * ambiguity errors expose a conceptual flaw in your code, which you can remedy by more
     * carefully crafting a solution.
     * */
    public static void main(String[] args){

        /** Control access to class members */
       /* // Can only access public member, because not in the same package
        int b=AccessControlExp.b;
        // it can access b, c, d. because it's in the same package.
        AccessControlTester.exp1();
        // As a is private, other program can access it only through AccessControlExp method. This allows
        // us to setup control, for example the setA method only allows value from 0 to 5.
        AccessControlExp.setA(2);
        System.out.println(" a value : "+AccessControlExp.getA());
        AccessControlExp.setA(10);
        System.out.println(" a value : "+AccessControlExp.getA());*/


        /** Pass objects to methods*/
        // PassObjToMethods.exp1();
        // PassObjToMethods.exp2();
        // PassObjToMethods.exp3();

        /** Returning objects*/
       //  ReturningObjExp.exp1();

        /** Method overloading */
       // MethodOverloadingExp.myPrint("Java is good",2);
       // MethodOverloadingExp.myPrint("Java is good");

        /** Recursive*/
       // RecursionExp.factIteration(5);
       // RecursionExp.factIteration(5);

        /** Nested class */
        /*int[] nums={3,4,5,1,2,3,9,10};
        NestedClassExp nestedExp=new NestedClassExp(nums);
        nestedExp.analyze();*/

        /** Variable length argument */
        /* when we call the method, the argument is separated by "," just like other normal method.*/
        // with 5 args
         VarArgsExp.vaTest(1,2,3,4,5);
        // with 0 args, the for loop does not raise error even if the array is empty.
        //VarArgsExp.vaTest();
        // mixed with other parameter
       // VarArgsExp.vaTest1("pengfei",3000,4000,5000,6000,7000,8000);
         VarArgsExp.vaTest(10.0,2.0,3.4,4.6);
        //VarArgsExp.vaTest();
    }

}
