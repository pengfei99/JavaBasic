package org.pengfei.Lesson00_Java_Basics.S05_Advance_Classes_Methods.source;


public class AccessControlExp {
    private static int a=1;
    public static int b=1;
    // no modifier,
    static int c=1;
    protected static int d=1;

    public static void setA(int arg) throws IllegalArgumentException {
        if(arg>0 && arg<5) {a=arg;}
        else {
            throw new IllegalArgumentException("a is not in range 0 to 5");
        }
    }

    public static int getA() {
        return a;
    }
}
