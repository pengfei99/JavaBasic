package org.pengfei.Lesson00_Java_Basics.S01_Data_Types_Operators.source;

public class ArithmeticOpTester {
    public static void showReminder(){
        int intResult, intReminder;
        double dResult, dReminder;

        intResult=10/3;
        intReminder=10%3;

        dResult=10.0/3.0;
        dReminder=10.0%3.0;

        System.out.println("Result and remainder of 10/3 is : "+intResult+", "+intReminder);
        System.out.println("Result and remainder of 10.0/3.0 is : "+dResult+", "+dReminder);

    }
}
