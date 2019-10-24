package org.pengfei.Lesson00_Java_Basics.S04_Advance_Datatype_Operator;

import org.pengfei.Lesson00_Java_Basics.S04_Advance_Datatype_Operator.source.*;

public class S04_Advance_Datatype_Operator {

    /******************************************** 4. Introduction *******************************************/

    /*
    * In this section, we will discuss advance data types and operators, such as arrays, String, etc. Key skills and
    * Concepts:
    * - Understand and create arrays, create multidimensional arrays, create irregular arrays
    * - know the alternative array declaration syntax
    * - Assign array references
    * - Use the length array number
    * - Use the for-each style for loop
    * - Work with strings
    * - Apply command-line arguments
    * - Use type inference with local variables
    * - Use the bitwise operators
    * - Apply the ? operator
    * */

    /************************************************ 4.1 Arrays ********************************************/

    /*
    * An array is a collection of variables of the same type, referred to by a common name. In java, arrays can
    * have one or more dimensions, although the one-dimensional array is the most common. The principal advantage
    * of an array is that it organizes data in such a way that it can be easily manipulated. For example, an array
    * holds the salary of all employers, it's easy to find the average, or sort the salary.
    *
    * Array in Java is implemented as Objects, as a result, we have all advantages of Objects. For example,
    * unused arrays can be garbage collected.
    * */

    /** 4.1.1 One-dimensional Arrays
     * To declare a one-dimensional array, you can use this general form:
     * type array-name[] = new type[size]
     * where - type is the element type in the array,
     *       - size will determine how many element the array can hold
     * As array is an object, we need to use new operator to allocate memory to create and hold data of this array.
     * And array-name is a variable which holds a reference to this memory.
     *
     * Example, an int array with 10 element
     * int test[] = new int[10];
     *
     * An element of array can be accessed by use of an index. An index describes the position of an element within an
     * array. In java, the index starts with 0 which is the first element of array. Thus to access all elements in the
     * test array, we will use index between 0(test[0]) and 9(test[9]).
     * Check OneDimArrayExp.exp1() for a simple int array example
     * Check  OneDimArrayExp.exp2() for get min, max value of an array.
     *
     * We can initialize the array with an array of values, it has the following general form
     * type array-name[] = {val1,val2,val3,...,valN};, Note that we can't reinitialize after first initialization.
     * check OneDimArrayExp.exp3();
     *
     * Array boundaries are strictly enforced in Java, it's a run-time error to overrun or underrun the end of an
     * array. Check OneDimArrayExp.exp4();
     *
     * We implement the bubble sort with array. Check OneDimArrayExp.bubbleSort();
     * */

/** 4.1.2 Multidimensional arrays
 * In java, a multidimensional array is an array of arrays. The simplest form of the multidimensional array is the
 * two-dimensional arrays. To declare a two dimensional integer array table of size 10,20 you would write:
 * int table[][] = new int[10][20];
 * Check the  MultiDimArrayExp.exp1(); for a simple example
 * */

/** 4.1.3 Irregular Arrays
 * When you allocate memory for a multidimensional array, you need to specify only the memory for the first (leftmost)
 * dimension. You can allocate the remaining dimensions separately. The advantage of this, the second dimensions do not
 * need to be the same. Check MultiDimArrayExp.exp2();
 *
 * The use of irregular multidimensional arrays does not, obviously, apply to all cases. But sometimes, it can be quite
 * effective. For example, if you need a very large two­dimensional array that is sparsely populated (that is, one in
 * which not all of the elements will be used), an irregular array might be a perfect solution.
 * */

/** 4.1.4 Arrays of Three or more Dimensions
 * Java allows arrays with more than two dimensions. Here is the general form of a multidimensional array declaration:
 * type name[ ][ ]...[ ] = new type[size1][size2]...[sizeN];
 *
 * For example, the following declaration creates a 4 × 10 × 3 three­dimensional integer array:
 * int multidim[][][]=new int[3][10][3];
 *
 * */

/** 4.1.5 Initializing multidimensional Arrays
 * A multidimensional array can be initialized by enclosing each dimension’s initializer list
 * within its own set of curly braces. For example, the general form of array initialization
 * for a two­dimensional array is shown here:
 * type­specifier array_name[ ] [ ] = {
 * { val, val, val, ..., val },
 * { val, val, val, ..., val },
 * ...
 * };
 *
 * Check MultiDimArrayExp.exp3();
 * */

/** 4.1.6 Alternative array declaration syntax
 * There is a second form that can be used to declare an array:
 * type[] var-name;
 *
 * For example, the following two declaration are equivalent:
 * int counter[]=new int[3]
 * int[] counter=new int[3]
 * This alternative declaration form offers convenience when declaring several arrays at the same time. For example,
 * int[] num1,num2,num3; is more effective than int num1[],num2[],num3[]
 *
 * The alternative declaration form is also useful when specifying an array as a return type for a method, For example,
 * the following method getEmployeeSalary returns a int array, so we can write :
 * public int[] getEmployeeSalary(){}
 *
 * */

    /*************************************** 4.2 Assigning Array references ************************************/

    /*
    * As with other objects, when you assign one array reference variable to another, you are simply changing what
    * object that variable refers to. You are not causing a copy of the array to be made, nor are you causing the
    * contents of one array to be copied to the other. Check AssignReference.exp1();
    * */

    /*************************************** 4.3 Using the length member ************************************/

    /*
    * As arrays are implemented as objects. One benefit is that each array has associated with it a length instance
    * variable that contains the number of elements that the array can hold.
    * Check ArrayLengthExp.exp1(); and ArrayLengthExp.exp2();
    * */

    /*************************************  4.4 Queue class **************************************************/
    /* Array is one of the simplest data structure, which is a linear list that supports random access to its elements.
     * But if we insert a new element in the middle of the array, we have to swap the rest element of the array. To
     * avoid this, we can use a linked list. In a linked list, every element has two attribute : next, prev which
     * refers to the element next/prev to current element. If we add order to access these element based on the
     * insertion history. A stack is a linked list in which element can be accessed in first-in,last out (FILO) order
     * only. A queue is a list in which elements can be accessed in first-in, first-out (FIFO) order. For more details
     * check L13_S3_Stacks, and L13_S4_Queues. They are many ways to implement a queue, but we will use array. In this
     * example, we assume all data has type char. We can use generic type to store all type, we will discuss this in
     * Section 12.
     *
     * Check QueueExp to see how we implement a simple queue with array
     * */
    /*************************************  4.5 For each **************************************************/

    /*
    * When working with arrays, it is common to examined all elements in an array. Java offered us a for each style
    * for:
    * for(type iterationVar: collectionName) {
    * statement
    * }
    *
    * The for-each loop works for all collection implementations in Java Collections Framework such as ArrayList,
    * linkedList, etc.
    *
    * The advantage of the for-each loop is it eliminates the loop counter, and the specification of its starting and
    * ending value. Check  ForEachExp.exp1(); To stop the for-each loop, we need to use break statement.
    * Check ForEachExp.exp2(); we stop at iteration 5.
    *
    * Note that, in the for-each loop, the iteration variable is "read-only" as it relates to the underlying array.
    * An assignment to the iteration variable has no effect on the underlying array. In other words, you can't change
    * the contents of the array by assigning the iteration variable a new value. Check ForEachExp.exp3(); we assign the
    * x with x*x. But the arrays is not changing.
    * */

    /** 4.5.1 Iterating Over multi-dimensional array with for-each
     * The for-each loop also works on multidimensional array. In java, multidimensional arrays consist of arrays of
     * array. The first iteration var is an array of objects not a single object. In general, when using the for­each
     * for to iterate over an array of N dimensions, the objects obtained will be arrays of N–1 dimensions.
     * Check ForEachExp.exp4(); to see how we do it
     * **/

    /** 4.5.2 for-each application
     * Check ForEachExp.Search, we use the for-each loop to find a specific value
     *
     * */

    /*************************************  4.6 Strings **************************************************/

    /*
    * In java, String is the most used data types. String defines and supports character strings. Unlike other
    * programming (array of characters), strings are objects.*/

    /** 4.6.1 String construction
     * You can construct a String like you construct any other object : by using new operator and calling the String
     * constructor. For example:
     * String tmp= new String("Toto");
     * This creates a String object called tmp that contains the character "Toto". You can also construct a String
     * from another String. For example:
     * String tmp1=new String(tmp);
     * You can also create a string by using "", for example:
     * String tmp2="hello world"
     *
     * Note that a quoted string is an object of type String.
     * Note, we prefer to use String literal "", it's more effective than new String. We can check their performance
     * by using JMH(Java Microbenchmarking harness). See A01_Microbenchmarking.
     *
     * */

    /** 4.6.2 String Operators
     * The String class contains several methods that operate on strings. Here is the most common:
     * - boolean equals(str) : Returns true if the invoking string contains the same character sequence as str.
     * - char charAt(index) : Obtains the character at the specified index
     * - int length(): Obtains the length of a string.
     * - int compareTo(str): Returns less than zero if the invoking string is less than str, returns greater than zero
     *                       if greater than str, and zero if the strings are equal.
     * - int indexOf(str): Searches the invoking string for the substring specified by str. Returns the index of the
     *                     first match or -1 on failure
     * - int lastIndexOf(str): Searches the invoking string for the substring specified by str. Returns the index of the
     *                         last match or -1 on failure
     * - String substring(int startIndex, int endIndex): Returns the substring of the invoking string with the given
     *                              startIndex(inclusive), and endIndex(exclusive,optional). If endIndex is absent, the
     *                              substring ends at the end of the invoking string.
     *
     * Notice that compare two string equality, we did not use ==. Applying the == to two String references simply
     * determines whether the two references refer to the same object. But equals will return true if two string has
     * the same value
     * */

    /** 4.6.3 Strings are immutable
     * The contents of a String object are immutable. That is, once created, the character
     * sequence that makes up the string cannot be altered. This restriction allows Java to
     * implement strings more efficiently. Even though this probably sounds like a serious
     * drawback, it isn’t. When you need a string that is a variation on one that already exists,
     * simply create a new string that contains the desired changes. Since unused String
     * objects are automatically garbage collected, you don’t even need to worry about what
     * happens to the discarded strings. It must be made clear, however, that String
     * reference variables may, of course, change the object to which they refer. It is just that
     * the contents of a specific String object cannot be changed after it is created.
     *
     * Java offers a class called StringBuffer, which creates string objects that can be modified. For example, the
     * StringBuffer class provide setCharAt() method, which allows you to set char value within the string.
     * StringBuilder, which is related to StringBuffer, also supports string value modification.
     *
     * StringBuilder is design to replace the StringBuffer. StringBuffer is synchronized, StringBuilder is not and more
     * effective. Check StringExp.exp3(); to see example.
     * */

    /** 4.6.4 Using a String to Control a switch statement
     * Being able to use strings in a switch statement can be very convenient and can improve the readability of
     * some code. StringExp.exp4() for example.
     * */

    /*************************************  4.7 Type inference with local variables************************************/

    /*
    * Beginning with JDK 10, it's now possible to let the compiler infer the type of a local variable based on the
    * type of its initializer. Local variable type inference offers a number of advantage.
    * - Simplify variable declaration with long class name
    * - Eliminating redundant variable type declaration
    * It can also be helpful when a type is difficult to discern or cannot be denoted.
    *
    * To support local variable type inference, the context­sensitive identifier "var" was added to Java as a reserved
    * type name. To use local variable type inference, the variable must be declared with var as the type name and it
    * must include an initializer.
    *
    * Check  LocalVarTypeInferExp.exp1(); you can see the variable avg has type double which is inferred by JVM.
    *
    * As mentioned, var was added as a context­sensitive identifier. When it is used as the type name in the context
    * of a local variable declaration, it tells the compiler to use type inference to determine the type of the
    * variable being declared based on the type of the initializer. Thus, in a local variable declaration, "var" is
    * a placeholder for the actual inferred type. However, when used in most other places, var is simply a user­defined
    * identifier with no special meaning. For example, the following declaration is still valid:
    * int var = 1 ;
    *
*  */

    /** 4.7.1 Use type inference for array declaration
     * Check LocalVarTypeInferExp.exp2(); We don't use brackets in the declaration, because the type is
     * inferred from the type of the initializer.
     * */

    /** 4.7.2 Local Variable Type Inference with Reference Types
     * We have seen the type inference with primitive type. It also works for user defined class.
     * Check LocalVarTypeInferExp.exp3();
     * */

    /** 4.7.3 Using Local variable type inference in a for loop
     * Another place that local variable type inference can used is in a for-each loop. Check LocalVarTypeInferExp.exp4();
     * In the for-each loop, we don't need to give the type of the loop variable explicitly.
     * */

    /** 4.7.4 Some var Restrictions
     * There are several other restrictions apply to the use of var. Only one variable can be declared at a time;
     * a variable cannot use null as an initializer; and the variable being declared cannot be used by the
     * initializer expression. Although you can declare an array type using var, you cannot use var with an array
     * initializer.
     *
     * For example, var myArray=new int[10] is valid, var myArray = {1,2,3} is wrong.
     *
     * As mentioned earlier, var cannot be used as the name of a class. It also cannot be used
     * as the name of other reference types, including an interface, enumeration, or
     * annotation, which are described later in other section. Here are two other restrictions that
     * relate to Java features also described later, but mentioned here in the interest of
     * completeness. Local variable type inference cannot be used to declare the exception
     * type caught by a catch statement. Also, neither lambda expressions nor method
     * references can be used as initializers.
     * */

    /*************************************  4.8 The Bitwise operators ************************************/

    /*
    * The bitwise operators can be used on values of type long, int, short, char, or byte. Bitwise operations
    * cannot be used on boolean, float, or double, or class types. They are called the bitwise operators because
    * they are used to test, set, or shift the individual bits that make up a value.
    *
    * Bitwise operations are important to a wide variety of systems­level programming tasks in which status
    * information from a device must be interrogated or constructed. The bitwise operators has the following:
    * - & : Bitwise AND
    * - | : Bitwise OR
    * - ^ : Bitwise exclusive OR
    * - >> : Shift right
    * - >>> : Unsigned shift right
    * - << : Shift left
    * ~ : One's complement(unary NOT)
    *
    * The bitwise operators perform the same operations as Boolean logical equivalents. The difference is that the
    * bitwise operators work on a bit-by-bit basis. The following shows the outcome of each operations:
    * p        |     q     |   p&q      |     p|q    |       p^q    |  ~ p
    * 0              0           0             0              0          1
    * 1              0           0             1              1          0
    * 0              1           0             1              1          1
    * 1              1           1             1              0          0
    *
    * I do not want to spent time on this, check P215 (Java_A_beginner's Guide) for more information
    * */

    /*************************************  4.9 The ? operator ************************************/

    /*
    * One of Java's most fascinating operators is the ?. The ? operator is often used to replace if-else statements
    * of this general form:
    * if(condition)
    *     statement1;
    * else
    *     statement2;
    *
    * The ? operator is called a ternary operator, because it requires three operands. The above if can be replaced
    * by: condition?statement1:statement2;
    * The condition must be a boolean expression, and statement1/2 can be expressions of any type other than void. The
    * type of statement1 and statement2 must be the same (or compatible). If condition is true then statement1 is
    * evaluated and becomes the value of the entire ? expression. Otherwise, statement2 is evaluated.
    *
    * For example, the following expression gets the absolute value of val.
    * absval= (val<0?-val:val;) is the same as if(val<0) absval=-val; else absval=val;
    * Check QuestionMarkOperatorExp.exp1(); and QuestionMarkOperatorExp.exp2(); to see an example
    * */

    public static void main(String args[]){
        /** one dimensional array*/
         // OneDimArrayExp.exp1();
       // OneDimArrayExp.exp2();
       // OneDimArrayExp.exp3();
       //  OneDimArrayExp.exp4();
        // OneDimArrayExp.bubbleSort();

        /** Multi-dimensional array*/
        // MultiDimArrayExp.exp1();
        // MultiDimArrayExp.exp2();
       // MultiDimArrayExp.exp3();

        /** Assign reference*/
       // AssignReference.exp1();

        /** Array length exp*/
        // ArrayLengthExp.exp1();
       //  ArrayLengthExp.exp2();

        /** queue example */
        /*QueueExp queue=new QueueExp(10);
        for(int i=0;i<10;i++) queue.put('a');
        queue.showQueue();
        queue.get();
        queue.get();
        queue.put('b');
        queue.showQueue();*/

        /** for-each example*/
        // ForEachExp.exp1();
        // ForEachExp.exp2();
        // ForEachExp.exp3();
        // ForEachExp.exp4();
        /*boolean found = ForEachExp.Search(12);
        System.out.println(found);*/

       /** String example*/
       // StringExp.exp1();
       // StringExp.exp2();
        // StringExp.exp3();
        // StringExp.exp4();

        /** Type inference example*/
       // LocalVarTypeInferExp.exp1();
       // LocalVarTypeInferExp.exp2();
       // LocalVarTypeInferExp.exp3();
       // LocalVarTypeInferExp.exp4();

        /** ? operator*/
        QuestionMarkOperatorExp.exp1();
        QuestionMarkOperatorExp.exp2();
    }
}
