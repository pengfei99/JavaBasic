package org.pengfei.Lesson00_Java_Basics.S11_Autoboxing_Enum_Annotation.source;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
import static java.lang.System.out;

public class StaticImportExp {

    public static void exp1(){
        /* In this example, we try to resolve the quadratic equation, which has the following form:
        * a(x^2)+bx+c=0, given the value of a,b,and c, we need to determine the possible value of x
        */
        // define variable
        double a,b,c,x1,x2;
        a=4;
        b=1;
        c=-3;

        // We need to call the static method with their class name
        var tmp=Math.sqrt(Math.pow(b,2)-4*a*c);
        // Find first solution
        x1=(-b+tmp)/(2*a);
        System.out.println("First solution: "+x1);

        // Find second solution
        x2=(-b-tmp)/(2*a);
        System.out.println("First solution: "+x2);
    }

    public static void exp2(){
        double a,b,c,x1,x2;
        a=4;
        b=1;
        c=-3;

        // With static import, we don't need to specify the class name anymore.
        var tmp=sqrt(pow(b,2)-4*a*c);
        // Find first solution
        x1=(-b+tmp)/(2*a);
        System.out.println("First solution: "+x1);

        // Find second solution
        x2=(-b-tmp)/(2*a);
        System.out.println("First solution: "+x2);
    }

    public static void exp3(){
        out.println("NO need to type System anymore");
    }
}
