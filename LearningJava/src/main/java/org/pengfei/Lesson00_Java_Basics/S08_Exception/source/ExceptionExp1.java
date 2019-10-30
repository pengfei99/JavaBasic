package org.pengfei.Lesson00_Java_Basics.S08_Exception.source;

import java.io.*;
import java.util.Scanner;

public class ExceptionExp1<staict> {

    public static void exp1() {
        int nums[] = new int[5];
        try {
            System.out.println("Before exception is generated");
            // Jvm will generate an index out of bounds exception
            //nums[7]=10;
            nums[0] = 10;
            System.out.println("After exception is generated, this won't be executed.");
        } catch (ArrayIndexOutOfBoundsException exc) {
            // catch the exception
            System.out.println("Exception message: " + exc.toString());
        }

        System.out.println("Executed after catch statement");
    }

    public static void exp2() {
        try {
            setArray(10);
        } catch (ArrayIndexOutOfBoundsException exc) {
            System.out.println("Exception message: " + exc.toString());
        }
    }


    public static void exp3() {
        setArray(10);
        System.out.println("After exception is generated, this won't be executed.");

    }

    public static void exp4() {
        try {
            setArray(10);
        } catch (IllegalArgumentException exc) {
            System.out.println("This won't be executed.");
        }
        System.out.println("This won't be executed.");
    }

    public static void exp5() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a number followed by Enter");
        int result, n;
        while (true) {
            n = scan.nextInt();
            if (n == 999) break;
            try {
                result = 100 / n;
                System.out.println("100 divide " + n + " is : " + result);
            } catch (ArithmeticException exc) {
                System.out.println("Can't divide by Zero, " + exc.toString());
            }
        }
    }


    public static void exp6() {
        int number[] = {3, 4, 5, 6, 7, 23, 34435, 4545, 645, 452};
        int denom[] = {2, 0, 4, 4, 0, 8};

        for (int i = 0; i < number.length; i++) {
            try {
                System.out.println(number[i] + " / " + denom[i] + " equals" + number[i] / denom[i]);
            }
            // catch subclass exception
            catch (ArrayIndexOutOfBoundsException exc) {
                System.out.println(exc.toString());
            }
            // catch superclass, in our case it catch arithmetic exception
            catch (Throwable exc) {
                System.out.println(exc.toString());
            }
        }
    }

    public static void exp7() {
        int number[] = {3, 4, 5, 6, 7, 23, 34435, 4545, 645, 452};
        int denom[] = {2, 0, 4, 4, 0, 8};

        // outer try
        try {
            for (int i = 0; i < number.length; i++) {
                //inner try
                try {
                    System.out.println(number[i] + " / " + denom[i] + " equals" + number[i] / denom[i]);
                } catch (ArithmeticException exc) {
                    System.out.println(exc.toString());
                }
            }
        } catch (ArrayIndexOutOfBoundsException exc) {
            System.out.println(exc.toString());
        }
    }

    public static void exp8(int n) {
        if (n > 10) throw new IllegalArgumentException("The number " + n + " is bigger than 10");
    }

    public static void exp9(int n) {
        try {
            ExceptionExp1.exp8(12);
        } catch (IllegalArgumentException exc) {
            System.out.println("I will do something here, but I have other things to do in the upper try catch");
            throw exc;
        }
    }

    public static void exp10() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a number followed by Enter");
        int result = 0, n;
        while (true) {
            n = scan.nextInt();
            if (n == 999) break;
            try {
                result = 100 / n;
                System.out.println("100 divide " + n + " is : " + result);
                // return String.valueOf(result);
            } catch (ArithmeticException exc) {
                System.out.println("Can't divide by Zero, " + exc.toString());
                // return "0";
            } finally {
                System.out.println("This will be executed no matter what");
                // return String.valueOf(result);
            }
        }
        // return String.valueOf(result);
    }

    public static void exp11() {
        int number[] = {3, 4, 5, 6, 7, 23, 34435, 4545, 645, 452};
        int denom[] = {2, 0, 4, 4, 0, 8};

        for (int i = 0; i < number.length; i++) {
            try {
                System.out.println(number[i] + " / " + denom[i] + " equals" + number[i] / denom[i]);
            }
            // multi catch, exc is final here, we can't reassign it to another value
            catch (ArrayIndexOutOfBoundsException | ArithmeticException exc) {
                System.out.println(exc.toString());
                // compile error
                // exc=new ArithmeticException("test");
            }
        }

    }

    public static void exp12() throws IOException {
        File f = new File("/tmp/toto");

        BufferedReader br = null;
        String st;
        try {
            br = new BufferedReader(new FileReader(f));
            while (true) {
                if (!((st = br.readLine()) != null)) break;
            }
        } catch (final Throwable e) {
            System.out.println("Do something and rethrow the exception");
            throw e;
        }
    }


    /** Use custom exception class*/
    public static void exp13() throws NonIntResultException {
        int[] nums={4,8,15,32,111,256};
        for(int i=0;i<nums.length;i++){
            if ((nums[i]%2) !=0) throw new NonIntResultException(nums[i],2);
        }
    }

    /** Use custom exception class*/
    public static void exp14(){
        int[] nums={4,8,15,32,111,256};
        for(int i=0;i<nums.length;i++){
            if ((nums[i]%2) !=0) try {
                throw new NonIntResultException(nums[i],2);
            } catch (NonIntResultException e) {
                System.out.println(e);
            }
        }
    }
    private static void setArray(int n) {
        int nums[] = new int[5];
        nums[n] = 10;
    }
}
