package org.pengfei.Lesson00_Java_Basics.S08_Exception.source;

/** Define a custom exception class which extends Exception which is the subclass of Throwable */
public class NonIntResultException extends Exception {
    int nominator;
    int denominator;
    NonIntResultException(int i, int j){
        this.nominator=i;
        this.denominator=j;
    }
    public String toString(){
        return "Result of "+nominator+" / "+denominator+ " is non integer";
    }
}
