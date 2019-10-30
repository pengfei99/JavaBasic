package org.pengfei.Lesson00_Java_Basics.S01_Data_Types_Operators;

import org.pengfei.Lesson00_Java_Basics.S01_Data_Types_Operators.source.ArithmeticOpTester;
import org.pengfei.Lesson00_Java_Basics.S01_Data_Types_Operators.source.LightningDistance;
import org.pengfei.Lesson00_Java_Basics.S01_Data_Types_Operators.source.ShortCircuitOPTester;
import org.pengfei.Lesson00_Java_Basics.S01_Data_Types_Operators.source.TypeConversion;

public class S01_DataTypes_Operators {
    /******************************************** 1. Introduction *******************************************/

    /*
    * Java Data types are divided into two groups:
    * - Primitive data types - includes byte, short, int, long, float, double, boolean and char
    * - Non-primitive data types - such as String, Arrays and Classes.
    *
    * In this section, we will learn the following skills
    * - Know Java’s primitive types
    * - Use literals
    * - Initialize variables
    * - Know the scope rules of variables within a method
    * - Use the arithmetic operators
    * - Use the relational and logical operators
    * - Understand the assignment operators
    * - Use shorthand assignments
    * - Understand type conversion in assignments
    * - Cast incompatible types
    * - Understand type conversion in expressions
    * */

    /***************************************** 1.1 Java's primitive types ***********************************/
    /*
    * Data types are especially important in Java because it is a strongly typed language. This means that all
    * operations are type­checked by the compiler for type compatibility. Illegal operations will not be compiled.
    * Thus, strong type checking helps prevent errors and enhances reliability. To enable strong type checking,
    * all variables, expressions, and values have a type. There is no concept of a “type­less” variable, for example.
    * Furthermore, the type of a value determines what operations are allowed on it. An operation allowed on one
    * type might not be allowed on another
    * */

    /*
    * Java contains two general categories of built­in data types:
    * - object­oriented
    * - non­object­oriented.
    *
    * Java’s object­oriented types are defined by classes, and a discussion of classes is deferred until later.
    * However, at the core of Java are eight primitive (also called elemental or simple) types of data:
    * - boolean
    * - byte: is an 8-bit signed two's complement integer. It has a minimum value of -128 and a maximum value of 127
    *         The byte data type can be useful for saving memory in large arrays, where the memory savings actually
    *         matters. They can also be used in place of int where their limits help to clarify your code; the fact
    *         that a variable's range is limited can serve as a form of documentation.
    * - char: Character
    * - double: Double-precision floating point
    * - float: Single-precision floating point
    * - int: Integer
    * - long: Long
    *
    * The term primitive is used here to indicate that these types are not objects in an object­oriented sense, but
    * rather, normal "binary values". These primitive types are not objects because Java strictly specifies a range
    * and behavior for each primitive types unlike class, which user can customize them by using constructors or
    * sub-class.
    *
    * */

    /** 1.1.1 Integers
     * Java defines four integer types:
     * - byte: 8 bits, value ranged from -128 to 127
     * - short: 16 bits, value ranged from -32768 to 32767
     * - int: 32 bits, value ranged from -2147483648 to 2147483647
     * - long: 64 bits, value ranged from -92233720368544775808 to 9223372036854775807
     *
     * As you can noticed, all integer types are signed, Java does not support unsigned(positive-only) integers.
     * */

    /** 1.1.2 Floating point types
     * The floating point types can represent numbers that have fractional components. In java, we use
     * - float: 32 bits
     * - double: 64 bits
     *
     * Many of the math functions in Java's class library use double values, such as Math.sqrt()(It takes double as
     * argument, and returns double as result)
     * */

    /** 1.1.3 Character
     * - char: unsigned 16 bits (char in binary is unsigned, which only represent positive value, each value represent
     *         a mapping with a character in all the language of the worlds, aka. unicode)
     *
     *  As ASCII (8 bits) is a subset of Unicode. Thus, the ASCII characters are still valid java characters
     *  */

    /** 1.1.4 Boolean
     * The boolean type represents true/false values. Java defines the values true and false
     * using the reserved words true and false. Thus, a variable or expression of type
     * boolean will be one of these two values.
     * */


    /** 1.1.5 Literals
     * In java, "literals" refer to fixed values that are represented in their human-readable form (.aka constants).
     * Java Literals can be of any of the primitive data types. The way each literal is represented depends upon its
     * type.
     * - char: '' , e.g. 'a', '$', '12'(is a character, not int)
     * - int: 12, 34,
     * - long: By default, integer literals are of type int. If you want to specify a long literal, append an l or an L.
     *         For example, 12 is an int, but 12L is a long.
     * - double: 12.30
     * - float: By default, floating­point literals are of type double. To specify a float literal, append an F or f
     *          to the constant. For example, 10.19F is of type float.
     *
     * You can embed one or more underscores into an integer or floating­point literal. Doing
     * so can make it easier to read values consisting of many digits. When the literal is
     * compiled, the underscores are simply discarded. Here is an example: 123_45_678 is an int with value 12345678.
     *
     * - Hexadecimal: must begin with 0x/0X (zero followed by x or X), e.g. 0xFF (255 in decimal)
     * - Octal: must begin with 0, e.g. 011 (9 in decimal)
     * - binary: must begin with 0b/0B(zero followed by b or B), e.g. 0b1100 (12 in decimal)
     * */

    /** 1.1.6 Character escape sequences
     * Enclosing character constants in single quotes works for most printing characters, but a few characters,
     * such as the carriage return, pose a special problem when a text editor is used. In addition, certain other
     * characters, such as the single and double quotes, have special meaning in Java, so you cannot use them
     * directly. For these reasons, Java provides special escape sequences, sometimes referred to as backslash
     * character constants. See the following example:
     * - '\'': single quote as a character
     * - '\\': backslash as a character
     * - '\n': new line
     * - '\r': Carriage return
     * - '\t': Horizontal tab
     * - '\' : Octal constant
     * - '\XXXX' : Hexadecimal constant (where XXXX is a hexadecimal constant)
     *
     * */

    /** 1.1.7 String Literals
     * Java also supports one other type of literal: String. A string is a set of characters enclosed by double quotes.
     * A string literal can contain one or more of the escape sequences just described in 1.1.6.
     *
     * 'k' is the same as "k"? No, because 'k' is type char, "k" is type string, even there is only one character.*/


    /***************************************** 1.2 Java's variables ***********************************/

    /* In Java, all variable declaration is a type followed by variable name. The type can be a primitive type or
    * a class. Thus, the capabilities or functionality of a variable are determined by its type. For example, a variable
    * of type boolean cannot store character values or do arithmetic operations. And the "type of a variable cannot
    * change during its lifetime".
    *
    *  */

    /* 1.2.1 Variable initialization
    * type var_name=value;
    * For example:

    * char ch='foobar';
    * float toto=6.8F;
    *
    * If replace value by a variable, in Java, we call it dynamic initialization. For example,
    * int x=10; int y= 5; int z=x+y; We say z is dynamically initialized at run time.
    *
    * */

    /* 1.2.2 The Scope and lifetime of variables
    * Java allows variables to be declared within any block (a block is begun with an opening curly brace and ended by
    * a closing curly brace). A block defines a scope, A scope determines what objects are visible to other parts of
    * your program. It also determines the lifetime of those objects.
    *
    * As every variables in Java are inside a block, it must have a scope. Two of the most common scopes in Java are
    * Class and method. As a general rule, variables declared inside a scope are not visible (accessible) to code that
    * is defined outside that scope. Indeed, the scope rules provide teh foundation for encapsulation.
    *
    * Scope hierarchy, as a scope (block) can be nested by other scopes (blocks), the outer scope enclose the inner
    * scope. This means that objects declared in the outer scope will be visible to code within the inner scope. Objects
    * declared within the inner scope will not be visible outside it.
    * */

    /***************************************** 1.3 Java's operators ***********************************/

    /*
    * In Java, an operator is a symbol that tells the compiler to perform a specific mathematical or logical
    * manipulation. Java has four general classes of operators:
    * - arithmetic,
    * - bitwise
    * - relational
    * - logical */

    /** 1.3.1 Arithmetic operators
     * + , -, *, / are the basic operators, % means modulus, ++ increment, -- decrement. These operators can be applied
     * to any built-in numeric data type.
     *
     * They can also be used on objects of type char.
     *
     * Remember that the return type of Arithmetic operators depends on the arguments of the operator. For example,
     * when / is applied to an integer, 10/3 will equal 3(not 3.33333~, because return type is int), and the remainder
     * will be truncated. The reminder of 10%3 is 1, the reminder of 10.0%3.0 is 1.0
     *
     * */

/** 1.3.2 Increment and Decrement
 *
 *  The increment operator adds 1 to its operand, and the decrement subtracts 1. Therefore:
 *  x=x+1 is the same as x++
 *  x=x-1 is the same as x--
 *  Both the increment and decrement operators can either prefix or postfix. Therefore:
 *  x++ is the same as ++x, and x-- is the same as --x.
 *
 *  But, if an increment or decrement is used as part of a larger expression, then there is an important difference.
 *  For example, x=10; y=++x; x,y will be assigned to 11, if we do y = x++; then x is 11, but y will be 10.
 *  So we know if the arguments is before operator, Java will get the arg value before incrementing or decrementing.
 *  If the arguments is after operator, Java will get result value of the increment or decrement.
 *
 * */

/** 1.3.3. Relational and logical operators
 * Relation operators return the relationships that values/variables can have with one another, which includes:
 * - ==: equal to
 * - !=: Not equal to
 * - >: Greater than
 * - <: Less than
 * - >= Greater to or equal to
 * - <= Less than or equal to
 * Relation operators always return a boolean value. In Java, all objects can be compared for equality or inequality
 * using == and !=. However, the comparison operators, <,>,<=,>=, can be applied only to those types that support an
 * "ordering relationship". Therefore, all of the relational operators can be applied to all numeric types and to type
 * char. Boolean type is not ordered, so it can only use ==, and !=. In Java, true > false has no meaning.
 *
 * Logical operators connect true or/and false values together and return their combined value (boolean), which includes:
 * - & : AND
 * - | : OR
 * - ^ : XOR (exclusive OR)
 * - || : Short-circuit OR
 * - && : Short-circuit AND
 * - ! : NOT
 *
 * The following truth table shows the operation evaluation result:
 *  p    |    q   |   p&q  |  p|q  |  p^q  | !p
 *   F       F         F       F       F      True
 *   T       F         F       T       T      False
 *   F       T         F       T       T      True
 *   T       T         T       T       F      False
 *
 * Short-circuit AND/OR are used to produce more efficient code. For example, in an AND operation, if the first operand
 * is false, the outcome is false no matter what value the second operand has. In an OR operation, if the first operand
 * is true, the outcome is true no matter what value the second operand has. Thus, in these two cases, there is no need
 * to evaluate the second operand. As a result, the execution time can be saved, if we don't evaluate them.
 *
 * Unlike &, | the short-circuit and/or will evaluate the second operand only when its necessary. Check the
 * ShortCircuitOPTester.tester class for examples.
 *
 * We still need the normal and/or operator. In some cases you will want both operands of an AND or OR operation to be
 * evaluated. For example, if in the second operand, there are some operation which must be executed no matter what.
 * Check the  ShortCircuitOPTester.sideEffect method, you will notice, the output of the two i are both 1.
 * */

/** 1.3.4 The assignment operator
 * The assignment operator is the single equal sign, =. It has this general form: var = expression; Note that the type
 * of var must be compatible with the type of expression.
 *
 * Assignment operator authorize the chain of assignments. For example, we can have x=y=z=100;
 *
 * Shorthand Assignments
 * In java, x=x+100 is the same as x+=100, and x=x-100 is the same as x-=100; The operator pair += tells the compiler
 * to assign to x the value of x plus 100. We can apply this to all arithmetic and logical operators:
 * - +=
 * - -=
 * - *=
 * - /=
 * - %=
 * - &=
 * - |=
 * - ^=
 * The compound assignment are more compact, and in some cases, more efficient.
 * */

/** 1.3.4 Type conversion in assignments
 * In programming, it is common to assign one type of variable to another. For example, you might want to assign an
 * int value to a float variable, int i; float f; i=10; f=i; (we assign an int ot a float)
 *
 * When compatible types are mixed in an assignment, the value of the right side is automatically converted to the type
 * of the left side. But not all types are compatible, and thus, not all type conversions are implicitly allowed. For
 * example, boolean and int are not compatible.
 *
 * When one type of data is assigned to another type of variable, an automatic type conve1.3.6 Order of operationrsion will take place if
 * - The two types are compatible
 * - The destination type is larger than the source type.
 *
 * When these two conditions are met, a widening conversion takes place. For example, the int type is always large
 * enough to hold all valid byte values, and both int and byte are integer types, so an automatic conversion from byte
 * to int can be applied.
 *
 * All the numeric types, including integer and floating­point types, are compatible with each other. So we need to
 * only check the size of type. For example, long to double is automatic, but double to long is not a widening
 * conversion. Because double is larger than long.
 *
 * There are no automatic conversions from the numeric types to char or boolean. Also, char and boolean are not
 * compatible with each other. However, an integer literal can be assigned to char.
 * */

/** 1.3.5 Casting incompatible types
 * The automatic widening conversions only work for compatible types. For all other incompatible types, we need to do a
 * type casting. A cast is an instruction to the compiler to convert one type into another. Thus, it requests an
 * explicit type conversion. A cast has this general form: (target-type) expression.
 *
 * For example, double x, y; int z= (int) (x/y). The second parentheses surrounding x/y are necessary. Otherwise, the
 * cast will only convert x to int.
 *
 * Note that, when a cast is a "narrowing conversion" (e.g. Long to int), information might be lost. When a floating
 * point value is cast to an integer type, the fractional component will also be lost due to truncation.
 *
 * */

/** 1.3.6 Order of operation
 * In an expression that contains multiple operators, Java uses a number of rules to decide the order in which the
 * operators are evaluated. The first and most important rule is called "operator precedence". Operators in an expression
 * that have higher precedence are executed before operators with lower precedence. For example, multiplication has a
 * higher precedence than addition. In the expression 2+3*4, the multiplication is done before the addition, producing
 * a result of 14.
 *
 * If consecutive operators in an expression have the same precedence, a rule called associativity is used to decide
 * the order in which those operators are evaluated. An operator can be
 * - left-associative,
 * - right-associative,
 * - non-associative.
 *
 * Left-associative
 * Operators of the left-associative with same precedence are evaluated in order from left to right. For example,
 * addition and subtraction have the same precedence and they are left-associative. In the expression 10-4+2,
 * the subtraction is done first because it is to the left of the addition, producing a value of 8.
 *
 * Right-associative
 * Operators of the right-associative with same precedence are evaluated in order from right to left. For example,
 * assignment is right-associative. Consider the following code fragment:
 * int a = 3;
 * int b = 4;
 * a = b = 5;
 * After the code has been evaluated, both a and b contain 5 because the assignments are evaluated from right to left.
 *
 * A non-associative operator cannot be combined with other operators of the same precedence.
 *
 * The following table shows the operator with precedence and associativity
 *
 * Precedence  |      Operator     |         Associativity
 * 1                   (), []            non-associative
 * 2                    new              non-associative
 * 3                     .               left-associative
 * 4                   ++, --            non-associative
 * 5         - (unary), + (unary), !, ~, ++, - -, (type)        right-associative
 * 6                 *, /, %               left-associative
 * 7                    +, -              left-associative
 * 8                <<, >>, >>>           left-associative
 * 9            <, >, <=, >=, instanceof     non-associative
 * 10                  ==, !=                left-associative
 * 11                    &                   left-associative
 * 12                   ^                    left-associative
 * 13                    |                   left-associative
 * 14                   &&                   left-associative
 * 15                   ||                   left-associative
 * 16                   ?:                   right-associative
 * 17          =, *=, /=, %=, -=, <<=, >>=, >>>=, &=, ^=, |=       right-associative
 *
 * */

/***************************************** 1.4 Java Expression ***********************************/


/**1.4.1 Type Conversion in Expression
 * Within an expression, it is possible to mix two or more different types of data as long as they are compatible
 * with each other. For example, you can mix short and long within an expression because they are both numeric types.
 * When different types of data are mixed within an expression, they are all converted to the same type. This is
 * accomplished through the use of Java’s "type promotion rules".
 *
 * The Java's type promotion rules will always convert all operands to the highest type. For example, if one operand is
 * a long, all char, byte, int and short values are promoted to long. If one operand is a float operand, the
 * entire expression is promoted to float. If any of the operands is double, the result is double. The conversion can
 * be triggered not only by operand of different type, but also by operators. See the example of
 * TypeConversion.typeConvertTest() .
 *
 * This same sort of situation also occurs when performing operations on chars. For example, in the
 * TypeConversion.typeConvertTest1(), the cast back to char is needed because of the promotion of ch1 and ch2 to int
 * within the expression
 *
 * TypeConversion.typeConvertTest2() shows we get precision with a type cast
 *
 * It is important to understand that type promotions apply only to the values operated upon when an expression is
 * evaluated. For example, if the value of a byte variable is promoted to int inside an expression, outside the
 * expression, the variable is still a byte. Type promotion only affects the evaluation of an expression
 * */

/** 1.4.2 Spacing and Parentheses
 * An expression in Java may have tabs and spaces in it to make it more readable. For example, the following two
 * expressions are the same, but the second is easier to read:
 * 1. x=10/y+(127/x);
 * 2. x=(10/y) + (127/x);
 *
 * Parentheses increase the precedence of the operations contained within them. If we change the 2 to
 * x=10 / (y + (127/x)), + will be executed before /.
 * */
    public static void main(String args[]){

        //primitive data types
        double distance = LightningDistance.getLightningDistance(7.2);
         System.out.println("The lightning distance is "+ distance+" feet away");

    // Arithmetic operators
        ArithmeticOpTester.showReminder();

        // short-circuit and test
       // ShortCircuitOPTester.tester();

        // short-circuit sideEffect
        ShortCircuitOPTester.sideEffects();

        // Type conversion in expression
        TypeConversion.typeConvertTest();
        TypeConversion.typeConvertTest1();
        TypeConversion.typeConvertTest2();
    }
}
