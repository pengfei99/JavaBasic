package org.pengfei.Lesson00_Java_Basics.S01_Data_Types_Operators.source;

public class ShortCircuitOPTester {

    public static void tester(){
        int x,y,z;
        x=10;
        y=0;

        // In the shortCircuit and, if the first operand is false, it will return false without evaluating the second
        // operand, so the x%y will not be evaluated, thus no division zero exception
        if( y!=0 && (x%y)==0)
             System.out.println("You can't see this, it's never true");
            else
            System.out.println("I'm ok, do zero division reported");

        // The and operator will evaluate both operand, so a division zero exception will be raised.
        if( y!=0 & (x%y)==0) System.out.println("You can't see this, because zero division");


    }

    public static void sideEffects(){
        int i=0;

        if(false& (++i<100)) System.out.println("You will not see this");
        System.out.println("Second operand is executed, see the i value "+i);

        if(false&& (++i<100)) System.out.println("You will not see this");
        System.out.println("Second operand is not executed, see the i value "+i);
    }
}
