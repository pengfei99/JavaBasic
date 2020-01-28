package org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util;

import org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source.UtilityClassesExp;

public class S03_Exploring_Java_util_utility_classes {

    /************************************ 3.2 More Utility classes in java.util *************************************/

    /*
    * java.util contains not only the Collection framework, but also many other utility classes. In this section
    * we will see these classes in details.
    *
    * */

    /************************************ 3.2.1 StringTokenizer *************************************/

    /*
    * The processing of text often consists of parsing a formatted input string. Parsing is the division of
    * text into a set of discrete parts, aka. tokens, which in a certain sequence can convey a semantic meaning.
    *
    * The StringTokenizer class provides the first step in this parsing process, often called lexer (lexical analyzer)
    * or scanner. StringTokenizer implements the Enumeration interface. As Enumeration, StringTokenizer exists
    * only in legacy code. For new code, use regular expressions.
    *
    * StringTokenizer has three constructors:
    * - 
    * */

    /***********************************************************************************************************
     * ******************************************* Code example ***********************************************
     * **********************************************************************************************************/
    public static void main(String[] args){

        //StringTokenizer
        UtilityClassesExp.exp1();
    }
}
