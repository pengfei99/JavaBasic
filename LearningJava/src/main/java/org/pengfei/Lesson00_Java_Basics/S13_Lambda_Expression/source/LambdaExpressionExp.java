package org.pengfei.Lesson00_Java_Basics.S13_Lambda_Expression.source;

import static java.lang.Math.*;

public class LambdaExpressionExp {

    public static void exp1(){
        // create a reference to a MyValue instance
        MyValue myValue;

        // use a LE in an assignment context, this works because, LE takes no parameter and return double
        myValue=()->88.8;

        // we can also write all in one line
        MyValue myValue1=()->88.8;

        System.out.println("First constant value: "+myValue.getValue());
        System.out.println("Second constant value: "+myValue1.getValue());
    }

    public static void exp2(double arg){
        // Use a LE with one parameter
        MyParamValue myValue=(n)-> 1.0/n;
        // We call the getValue with one arg, this arg will be passed to n in LE
        System.out.println("Reciprocal of "+arg+" is "+myValue.getValue(arg));
    }

    public static void exp3(int arg1, int arg2){

        // 1st LE
        NumericTest isFactor=(int a, int b)->{if ((a % b)==0) return true; else return false;};
        System.out.println("4 can be evenly divided by 2 is "+isFactor.test(4,2));

        // 2nd LE
        NumericTest lessThan=(a,b)->a<b?true:false;
        System.out.println(arg1+" is less than "+arg2+ " is "+lessThan.test(arg1,arg2));

        // 3rd LE
        NumericTest absEqual=(a,b)->abs(a)==abs(b);
        int argAbs1=-8, argAbs2=4;
        System.out.println(argAbs1+" absolute value is equal to "+argAbs2+" is "+absEqual.test(argAbs1,argAbs2));
    }
}
