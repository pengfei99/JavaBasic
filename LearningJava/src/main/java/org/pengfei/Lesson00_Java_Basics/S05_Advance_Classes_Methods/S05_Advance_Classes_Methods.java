package org.pengfei.Lesson00_Java_Basics.S05_Advance_Classes_Methods;

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
    * - static : Check S00_StaticKeyword for more example on static nested class
    *
    * An inner class is often used to provide a set of services that is used only by its enclosing class.
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
        RecursionExp.factIteration(5);
        RecursionExp.factIteration(5);

    }

}
