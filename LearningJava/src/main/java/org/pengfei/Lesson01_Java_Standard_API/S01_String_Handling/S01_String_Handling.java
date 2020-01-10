package org.pengfei.Lesson01_Java_Standard_API.S01_String_Handling;

import org.pengfei.Lesson01_Java_Standard_API.S01_String_Handling.source.StringBufferOperation;
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
     * char[] toCharArray(): It converts all the character in a String object into character array. You can do the same
     *        with getChars with the sourceStart=0 and sourceEnd=string.length.
     *
     *
     * */

    /** 3.6 String comparison
     *
     * boolean equals(Object str): It compares two strings for equality.
     *
     * boolean equalsIgnoreCase(String str) : It compares two string that ignores case differences.
     *
     * boolean regionMatches(int s1StartIndex, String s2, int s2StartIndex, int numChars): It compares a specific region
     *     inside a string with another specific region in another string.
     *
     * boolean regionMatches(boolean ignoreCase, int s1StartIndex, String s2, int s2StartIndex, int numChars): overload
     *     version to ignore case in comparisons.
     *
     * boolean startsWith(String str): It determines whether a given String begins with a specific string
     * boolean endsWith(String str): It determines whether a given String ends with a specific string
     *
     * int compareTo(String str): For sorting applications,	you	need to	know which is less than, equal to, or greater
     *                         than the next. A string is less than	another	if it comes	before the other in dictionary
     *                         order. A string is greater than another if it comes after the other in dictionary order.
     *                         The method compareTo() serves this purpose. It is specified by the Comparable<T>
     *                         interface, which	String implements. The return value is int, if <0, the invoking string
     *                         is less than str, if>0, greater than, if=0, equal.
     *                         In the demo code( StringOperation.exp6();), you can notice "hello" > "Hello", because
     *                         uppercase letter has a lower value in the ASCII character set.
     *
     * Note: "==" compares two string objects references to see whether they refer to the same instance. equals
     *        compares the characters inside the string objects.
     * */

    /** 3.7 Searching Strings
     *
     * int indexOf(int ch/String str): Searches for the first occurrence of a character or substring
     * int lastIndexOf(int ch/String str): Searches for the last occurrence of a character or substring
     * int indexOf(int ch/String str, int startIndex): we can also specifies the index at which point the search begins.
     *                         the search runs from startIndex to the end of the string
     * int lastIndexOf(int ch/String str, int startIndex): the search runs from startIndex to 0 (head of the string)
     *
     * check StringOperation.exp7();
     * */

    /** 3.8 Modifying a String
     * Because String objects are immutable, whenever you want to modify a String, you must either copy it into
     * a StringBuffer or StringBuilder, or use a String method that constructs a new copy of the string with your
     * modifications complete.
     *
     * String substring(int startIndex, int endIndex): the endIndex is optional, without it substring will return
     *         a sub string that begins at startIndex and runs to the end of the invoking string.
     * String concat(String str): It creates a new String object that contains the invoking string with the contents
     *          of str appended to the end. It's the same as "+"
     * String replace(char/CharSequence original, char/CharSequence replacement): It replaces one char or charSequence
     *      by the character specified by replacement.
     * String trim(): It returns a copy of the invoking string from which any leading and trailing spaces. This method
     *           is quite useful when we process user commands.
     * String strip(): Beginning with JDK 11, Java provides strip() method, which removes all whitespace characters
     *               (e.g. tabs, carriage returns, line feeds, etc.) from the beginning to the end of the invoking
     *               string. stripLeading() only removes the head spaces, stripTrailing() only removes the tail spaces.
     *
     * */


    /** 3.9 Data conversion using valueOf()
     *
     * The valueOf() method converts data from its internal format into a human-readable form. It is a static method
     * that is overloaded within String for all Java's built-in types so that each type can be converted properly into
     * a string. It's also overloaded for type Object.
     * */

    /** 3.10 Changing the case of character
     *
     * String toLowerCase(): It converts all the characters in a string from uppercase to lowercase.
     * String toUpperCase(): It converts all the characters in a string from lowercase to uppercase.
     * */

    /** 3.11 Joining Strings
     *
     * static String join(CharSequence delimiter, CharSequence ... strs): It is used to concatenate two or more strings,
     *       separating each string with a delimiter(e.g. " ", ",", etc.). String class implements the CharSequence
     *       interface. So delimiter, and strs can be strings.
     * static String join(CharSequence delimiter, Iterable<? extends CharSequence> strList): It is a overload version
     *       of join method, which take a list of charSequence object that implements Iterable interface.
     *
     *  Go check StringOperation.exp13();
     * */

    /**************************************** 04 String buffer *****************************************/
    /*
    * StringBuffer supports a modifiable string. As we said before, String represents a fixed-length, immutable
    * character sequence. StringBuffer represents growable and writable character sequences.*/

    /** 4.1 StringBuffer constructor
     *
     * StringBuffer has four constructors:
     * - StringBuffer() : it reserves room for 16 characters without reallocation.
     * - StringBuffer(int size): size defines the string buffer size
     * - StringBuffer(String str): It takes str as initial value and reserve another 16 characters space without
     *                  reallocation. Java reserves 16 more space to avoid reallocation, because it's a costly process
     *                  in terms of time. Also, frequent reallocation can fragment memory.
     * - StringBuffer(charSequence chars): It takes chars as initial value and reserve another 16 characters space without
     *      *                  reallocation.
     *
     *
     * */

    /** 4.2 Useful method
     * int length(): It returns the current character length of the invoking stringBuffer.
     * int capacity(): It returns the total allocated capacity of the invoking stringBuffer.
     * void ensureCapacity(int minCapacity): It sets the size of the buffer. This is useful if you know in advance that
     *             you will be appending a large number of small strings to a StringBuffer. Here minCapacity specifies
     *             the minimum size of the buffer. If a buffer is larger than minCapacity, it may be reallocated for
     *             reasons of efficiency. Note Java will set the final value of the minCapacity of the buffer which is
     *             >= of your input. check StringBufferOperation.exp01();
     * void setLength(int len): It sets the length of string within a StringBuffer object. len must be non-negative,
     *             If len is > the string value length, null character will be added to the end of the string, if <,
     *             the characters beyond the new length will be lost.
     * char charAt(int position): It returns the character at position. position's value must between 0 and string length.
     * void setCharAt(int position, char newCh): It sets the character at position with the newCh value.
     *             Check StringBufferOperation.exp02();
     *
     * void getChars(int sourceStart,int sourceEnd, char target[], int targetStart): similar to getChars of String.
     *
     * StringBuffer append(String str/int num/Object obj): It concatenates the string representation of any other type
     *            of data to the end of the invoking StringBuffer object. StringBufferOperation.exp03();
     *
     * StringBuffer insert(int index, String str/char ch/Object obj): It inserts one string(allow all primitive types)
     *              into the invoking stringBuffer. StringBufferOperation.exp04();
     *
     * StringBuffer reverse() : It returns the reverse of the invoking string buffer. StringBufferOperation.exp05();
     *
     * StringBuffer delete(int startIndex, int endIndex): It deletes a sequence of characters from the invoking object.
     *                The substring	deleted	runs from startIndex to endIndex–1.
     *
     * StringBuffer deleteCharAt(int loc): It deletes a character at position loc. StringBufferOperation.exp06();
     *
     * StringBuffer replace(int startIndex, int endIndex, String str): It replace the sub string specified between
     *                 startIndex and endIndex-1 with the str.
     *
     * String substring(int startIndex, int endIndex): It returns a substring between startIndex and endIndex-1,
     *             if endIndex is missing, it takes the end of the StringBuffer.
     *
     * int indexOf(String str, int startIndex): It searches the invoking StringBuffer for the first occurrence of str,
     *                      beginning at startIndex, if startIndex is missing, it starts from the beginning of the str.
     *                      It returns the index of the match, or -1 if no match
     *
     * int lastIndexOf(String str, int startIndex) : It searches the invoking StringBuffer for the last occurrence of str,
     *                   beginning at startIndex, if startIndex is missing, it starts from the beginning of the str.
     *                   It returns the index of the match, or -1 if no match.
     *
     * void trimToSize(): It requests that the size of the buffer for the invoking object be reduced to better fit the
     *                  current contents.
     *
     * */

    /***************************************** 05 String Builder ******************************************/

    /*
    * StringBuilder is similar to StringBuffer. But it's not synchronized, which means that it's not thread-safe.
    * The advantage of String Builder is faster performance. However, in cases in which a mutable string will be
    * accessed by multiple threads, and no external synchronization is employed, you must use StringBuffer rather
    * than StringBuilder.
    * */

    public static void main(String args[]){

     /** 02 The String constructors*/
     // StringConstructor.exp01();
     // StringConstructor.exp02();
     // StringConstructor.exp03();

     /** 03 String operation*/
     // string length demo
     // StringOperation.exp1();

     // string concatenation demo
    // StringOperation.exp2();

     // string conversion demo
    // StringOperation.exp3();

     // toString implementation demo
    // StringOperation.exp4();

     // Character extraction
    // StringOperation.exp5();

     // string comparision
     // StringOperation.exp6();

     // search string
    // StringOperation.exp7();

     // substring demo
    // StringOperation.exp8();

     // concat and replace
    // StringOperation.exp9();

     // trim, strip string
    // StringOperation.exp10();

     // valueOf
     // StringOperation.exp11();

     // change case
     // StringOperation.exp12();

     // Joining strings
     //StringOperation.exp13();

        /** 04 StringBuffer operation*/
        // StringBufferOperation.exp01();

        //charAt, setCharAt, setLength
      //  StringBufferOperation.exp02();

        // append
        // StringBufferOperation.exp03();

        // insert
        // StringBufferOperation.exp04();

        //reverse
       // StringBufferOperation.exp05();

        // delete
       // StringBufferOperation.exp06();

        // replace
       // StringBufferOperation.exp07();
 }
}
