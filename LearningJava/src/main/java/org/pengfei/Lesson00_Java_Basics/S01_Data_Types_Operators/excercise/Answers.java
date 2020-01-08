package org.pengfei.Lesson00_Java_Basics.S01_Data_Types_Operators.excercise;

import org.pengfei.Lesson00_Java_Basics.S07_Interfaces_Packages.source.A;

public class Answers {

    public static void exp4() {
        System.out.println("one\ntwo\nthree\n");
    }

    public static void exp5() {
        int sum = 0;

        for (int i = 0; i < 10; i++) {
            sum = sum + i;
        }
        System.out.println("Sum is: " + sum);
    }

    public static void exp6() {
        int x = 10;
        int y1 = x++;
        int y2 = ++x;
        System.out.println("x has value: " + x);
        System.out.println("y1 has value: " + y1);
        System.out.println("y2 has value: " + y2);
    }

    public static void exp7(int b) {
        int a = 38;
        int c;
        if ((b != 0) && (c = a / b) > 0)
            System.out.println("C has value: " + c);
        else
            System.out.println("B has value: " + b);
    }

    public static void exp10() {
        for (int i = 2; i < 101; i++) {
            boolean isPrime = true;
            for (int j = 2; j <= i/j; j++) {
                if ((i % j) == 0) {
                    isPrime = false;
                    break;
                }
            }
            if(isPrime==true&&i!=2) System.out.println(i+" is a Prime Number");
        }
    }

    public static void main(String[] args) {
       // Answers.exp4();
       // Answers.exp5();
       // Answers.exp6();
       // Answers.exp7(4);
        Answers.exp10();
    }
}
