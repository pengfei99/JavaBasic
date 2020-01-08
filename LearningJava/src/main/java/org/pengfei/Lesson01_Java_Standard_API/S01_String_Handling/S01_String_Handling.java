package org.pengfei.Lesson01_Java_Standard_API.S01_String_Handling;

import org.pengfei.Lesson01_Java_Standard_API.S01_String_Handling.source.StringConstructor;
import org.pengfei.Lesson01_Java_Standard_API.S01_String_Handling.source.StringOperation;

public class S01_String_Handling {

    /********************************************* 01 Introduction **************************************************/
/*
* A string is a sequence of characters. But, unlike other languages which implement strings as character arrays, Java
* implements strings as objects of type String. This makes Java extremely strong on handling string.
*
* For example, Java has methods:
* - compare two string
* - search for a substring inside a string
* - concatenate two strings
* - change case of letters within a string
*
* Also String object can be constructed with different ways, making it easy to obtain a string when needed. Note, when
* you create a String object, you are creating a string that cannot be changed, which means you cannot change the
* characters that comprise that string. When you perform all the string operations, instead of modify the existing
* string, a new String object is created. This approach is used	because	fixed, immutable strings can be	implemented	more
* efficiently than changeable ones.	For those cases in which a modifiable string is	desired, Java provides two options:
* - StringBuffer
* - StringBuilder.
* The String, StringBuffer,	and	StringBuilder classes are defined in java.lang.	Thus, they are available to	all	programs
* automatically. All are declared final, which means that none of these	classes	may	be subclassed. This allows certain
* optimizations	that increase performance to take place	on common string operations. All three	implement the
* CharSequence interface.
*
* Note, when we say a String object is unchangeable, it means the content of the String instance cannot be changed.
* A variable declared as a String reference can be changed to point at any other string object at any time.
* */

 /******************************************** 02 The String constructors *****************************************/

 /*
 * The String class supports several constructors.
 *
 * 1. The String object default constructor
 *   String s = new String("toto");
 * Here "toto" is not a String object, its a String literal, because of java autoboxing mechanism, if we do
 * String s = "toto"; Java will create a String object by using the default constructor.
 *
 * 2. String constructor takes an array of characters
 *  char chars[]={'a','b','c','d','e','f'};
 *  String s = new String(chars)
 *  The full version of this constructor is String(char chars[], int startIndex, int numChars), here, the startIndex
 * specify the position of start, the numChars specifies the number of character to use. For example,
 * new String(chars,2,3) will build a String "cde", because c is at position 2 (index starts with 0), and take 3
 * characters.
 *
 * 3. Create a String with another String object
 * String(String obj)
 *
 * 4. Create a String with StringBuffer/StringBuilder
 * String(StringBuffer/StringBuilder obj)
 *
 * 5. Create a String with byte(8 bit ASCII char)
 * String(byte chrs[], int startIndex, int numChars)
 *
 * 6. Create a String with unicode character
 * String(int codePoints[], int startIndex, int numChars)
 *
 *
 * Check 02 in the code example section
 * */

    /**************************************** 03 The Basic String operations *****************************************/

    /** 3.1 String length
     * The length of a string is the number of characters that it contains. To obtain this value, call the length()
     * method: int length(). Note space ' ' is also counted as a character.
     * Check StringOperation.exp1();
     *
     * */

    /** 3.2 String literals
     * For each	string literal in your program,	Java automatically constructs a	String object. Thus, you can use a
     * string literal to initialize	a String object. You can also use string literals to replace any String object.
     * For example, System.out.println("abc".length()); will print 3.
     *
     * */

    /** 3.3 String concatenation
     * In general, Java does not allow operators to be applied to String objects. But "+" is the exception, we use it
     * to concatenate String objects. Check StringOperation.exp2();
     *
     * We can also concatenate strings with other types of data. check  StringOperation.exp3(); for examples.
     *
     * */

    /** 3.4 String conversion and toString()
     * One way to convert data into its string representation is by calling one the overloaded versions of the string
     * conversion method valueOf() defined by String class. valueOf() is overloaded for all the primitive types and for
     * type Object.
     *
     * For primitive types, valueOf() returns a string that contains the human-readable form of the value.
     * For objects, valueOf() calls the toString() method on the object.
     *
     * Every class implements toString(), because it is defined by Object. However, the default implementation is
     * seldom sufficient. For most important classes that you create, you will want to override toString().
     * Check StringOperation.exp4(); you can notice an object's toString() method is automatically invoked when the
     * object is used in a concatenation or in a println() call.
     * */

    /** 3.5 Character Extraction
     *
     * The String class	provides a number of ways in which characters can be extracted from a String object.
     *
     * char charAt(int offset): It extract a single character from a String. Here the offset is the index of the
     *          character that you want to obtain, and it starts with 0.
     * void getChars(int sourceStart, int sourceEnd, char target[], int targetStart): It extract multiple chars. Here
     *       sourceStart/End specifies the char location in source string. target will hold the returned char array.
     *       targetStart specifies the start position of the copy.
     * byte[] getBytes(): It's an alternative to getChars(), which stores the characters in an array of bytes(8 bit
     *        ASCII code). It uses the default character-to-byte conversions provided by the java api. It's often used
     *        to export a string value into an environment that does not support 16-bit Unicode characters.
     * toCharArray()
     *
     *
     * */

 public static void main(String args[]){

     /** 02 The String constructors*/
     // StringConstructor.exp01();
     // StringConstructor.exp02();
     // StringConstructor.exp03();

     /** 03 String operation*/
     // StringOperation.exp1();
    // StringOperation.exp2();
    // StringOperation.exp3();
    // StringOperation.exp4();
     StringOperation.exp5();
 }
}
