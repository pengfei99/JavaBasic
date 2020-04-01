package org.pengfei.Lesson01_Java_Standard_API.S11_Regular_Expression;

import org.pengfei.Lesson01_Java_Standard_API.S11_Regular_Expression.source.PatternMatchingExample;

public class S11_Regular_Expression {

    /********************************************* 11.1 Introduction ***********************************************/

    /*
    * The regular expression package lets you perform sophisticated pattern matching operations. This section provides
    * an introduction to this package along with extensive examples
    *
    * The java.util.regex package supports regular expression processing. Beginning with JDK 9, java.util.regex is in
    * the java.base module. As the term is used here, a regular expression is a string of characters that describes a
    * character sequence. This general description, called a "pattern", can then be used to find matches in other
    * character sequences.
    *
    * There are two classes that support regular expression processing: Pattern and Matcher. These classes work
    * together. Use Pattern to define a regular expression. Match the pattern against another sequence using Matcher.
    * */


    /********************************************* 11.2 Pattern ***********************************************/

    /*
    * The Pattern class defines no constructors. Instead, a pattern is created by calling the compile() factory method.
    * One of its forms is shown here:
    * - static Pattern compile(String patternStr): patternStr is the regular expression that you want to use. The
    *                compile() method transforms the patternStr into a pattern that can be used for pattern matching
    *                by the Matcher class. It returns a Pattern object that contains the pattern.
    *
    * Once you have created a Pattern object, you will use it to create a Matcher. This is done by calling the matcher()
    * method defined by Pattern. It is shown here:
    * - Matcher matcher(CharSequence str): str is the character sequence that the pattern will be matched against. This
    *                is called the input sequence. CharSequence is an interface that defines a read-only set of
    *                characters. It is implemented by the String class, among others.
    * */

    /********************************************* 11.3 Matcher ***********************************************/


    /*
    * The Matcher class has no constructors. Instead, you create a Matcher by calling the matcher() factory method
    * defined by Pattern, as just explained. Once you have created a Matcher, you will use its methods to perform
    * various pattern matching operations. Several are described here.
    * - boolean matches(): It determines whether the character sequence matches the pattern. It returns true if the
    *                      sequence and the pattern match, and false otherwise. Understand that the entire sequence
    *                      must match the pattern, not just a subsequence of it.
    * - boolean find(): It determines if a subsequence of the input sequence matches the pattern. It returns true if
    *                   there is a matching subsequence and false otherwise. This method can be called repeatedly,
    *                   allowing it to find all matching subsequences. Each call to find() begins where the previous
    *                   one left off.
    * - String group(): It can obtain a string containing the last matching sequence. The matching string is returned.
    *                   If no match exists, then an IllegalStateException is thrown.
    * - int start(): It can obtain the index within the input sequence of the current match. It throws
    *                IllegalStateException if no match exists
    * - int end(): It can obtain the index that past the end of the current match. For example, the last index of the
    *              current match is i. It will return i+1. It throws IllegalStateException if no match exists
    * - String replaceAll(String newStr): It replaces all occurrences of a matching sequence with another sequence.
    *              newStr specifies the new character sequence that will replace the ones that match the pattern.
    *              The updated input sequence is returned as a string.
    *
    * */

    /*************************************** 11.4 Regular Expression Syntax *****************************************/

    /*
    * Before demonstrating Pattern and Matcher, it is necessary to explain how to construct a regular expression. In
    * general, a regular expression is comprised of
    * - normal characters
    * - character classes (sets of characters),
    * - wildcard
    * - quantifiers.
    *
    * In general, if you specify an invalid expression, a PatternSyntaxException will be thrown.
    * */

    /** 11.4.1 Normal characters
     *
     * A normal character is matched as-is. Thus, if a pattern consists of "xy", then the only input sequence that
     * will match it is "xy". Characters such as newline and tab are specified using the standard escape sequences,
     * which begin with a \ . For example, a newline is specified by \n. In the language of regular expressions, a
     * normal character is also called a "literal".
     * */

    /** 11.4.2 Character classes
     *
     * A character class is a set of characters. A character class is specified by putting the characters in the class
     * between brackets. For example, the class [wxyz] matches w, x, y, or z. To specify an inverted set, precede the
     * characters with a ^. For example, [^wxyz] matches any character except w, x, y, or z.
     *
     * You can specify a range of characters using a hyphen. For example, to specify a character class that will match
     * the digits 1 through 9, use [1-9].
     * */

    /** 11.4.3 Wildcard
     *
     * The wildcard character is the . (dot) and it matches any character. Thus, a pattern that consists of "." will
     * match these (and other) input sequences: "A", "a", "x", and so on.
     *
     * */

    /** 11.4.4 Quantifiers
     *
     * A quantifier determines how many times an expression is matched. The basic quantifiers are shown here:
     * - + : match one or more
     * - * : match zero or more
     * - ? : match one or none
     *
     * For example, the pattern "x+" will match "x", "xx", and "xxx", among others.
     * */

    /*************************************** 11.5 Pattern Matching Examples *****************************************/

    /*
    * Check PatternMatchingExample.exp1(); it shows a simple example on pattern, matcher, and methods on matcher(e.g.
    * matches, find).
    *
    * Check PatternMatchingExample.exp2(); find() can be called multiple times. We can use start() and end() to get the
    *     matched subsequence starting and ending index.
    *
    * PatternMatchingExample.exp3(); We use quantifier to specify the number of characters in a sequence.
    * PatternMatchingExample.exp4(); We use ? and {min, max} to specify the number of characters in a sequence
    *
    * PatternMatchingExample.exp5(); we use [a-zA-Z] to match all characters.
    *
    * PatternMatchingExample.exp6(); replace pattern with another string.
    *
    *  PatternMatchingExample.exp7(); split string with separators.
    *
    *
    * */

    /*************************************** 11.6 Conclusion *****************************************/
    /*
    * We presented in this section only hints of regular expression's power. Since text parsing, manipulation, and
    * tokenization are a large part of programming, you will likely find Java’s regular expression subsystem a
    * powerful tool that you can use to your advantage. It is, therefore, wise to explore the capabilities of regular
    * expressions. Experiment with several different types of patterns and input sequences. Once you understand how
    * regular expression pattern matching works, you will find it useful in many of your programming endeavors.
    * */

    public static void main(String[] args){
        /** Simple pattern matching*/
       // PatternMatchingExample.exp1();

        /** find and start of Matcher*/
        // PatternMatchingExample.exp2();

        /** quantifier */
        // PatternMatchingExample.exp3();

        // PatternMatchingExample.exp4();

        /** character class*/
       // PatternMatchingExample.exp5();

        /** replace all*/
       // PatternMatchingExample.exp6();

        /** split */
       //  PatternMatchingExample.exp7();

        /** other matching possibilities*/
        PatternMatchingExample.exp8();
    }
}
