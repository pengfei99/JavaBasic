package org.pengfei.Lesson00_Java_Basics.S01_Data_Types_Operators.source;

public class TypeConversion {

    public static void typeConvertTest(){
        byte b;
        int i;

        b=10;
        i=b*b;

        b=10;
        // b is byte, but * convert b to int. Java can't assign int to byte automatically, so we need to do a
        // explicit cast.
        b= (byte) (b*b);
    }

    public static void typeConvertTest1(){
       char ch1='a', ch2='b';


        // ch1 and ch2 are char, but + convert ch1 and ch2 to int within the expression. Outside the expression, ch1
        // still has char type. Java can't assign int to char automatically, so we need to do explicit cast to char.
        ch1= (char) (ch1+ch2);
    }

    public static void typeConvertTest2(){
        int i;
        for(i=0;i<5;i++){
            System.out.println(i + " /3: " + i/3);
            System.out.println(i + " /3 with fractions: " + (double)i/3);
        }
    }
}
