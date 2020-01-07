package org.pengfei.Lesson01_Java_Standard_API.S01_String_Handling;

import org.pengfei.Lesson01_Java_Standard_API.S01_String_Handling.source.StringConstructor;

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

 public static void main(String args[]){

     /** 02 The String constructors*/
     // StringConstructor.exp01();
     //StringConstructor.exp02();
     StringConstructor.exp03();
 }
}
