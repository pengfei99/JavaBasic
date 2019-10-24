package org.pengfei.Lesson00_Java_Basics.S04_Advance_Datatype_Operator.source;

import java.util.Scanner;

public class StringExp {
    public static String str1="Java is great, Java is number one";
    public static String str2=new String(str1);
    public static String str3="toto";

    public static void exp1(){
       // Check string length
       System.out.println("Length of str1 is: "+str1.length());

       // get all char of a string
        System.out.print("Str1 char list is: ");
        for(int i=0;i< str1.length();i++){
            System.out.print(str1.charAt(i));
        }
        //End of charAt
        System.out.println(" ");

        // check equals of String
        if(str1.equals(str2)) System.out.println("str1 equals str2");
        else System.out.println("str1 does not equal str2");

        if(str1.equals(str3)) System.out.println("str1 equals str3");
        else System.out.println("str1 does not equal str3");

        // Compare strings
        int compareResult= str1.compareTo(str3);
        if(compareResult==0) System.out.println("Str1 equals str3");
        else if(compareResult<0) System.out.println("Str1 is less than str3");
        else System.out.println("Str1 is greater than str3");
    }

    public static void exp2(){
        // get the index of one in str1
        int index=str1.indexOf("one");
        System.out.println("index of one in str1 is: "+index);

        // get last index of Java in str1
        int lastIndex=str1.lastIndexOf("Java");
        System.out.println("Last index of Java in str1 is: "+lastIndex);

        // get substring of str1
        String subStr1=str1.substring(15,20);
        System.out.println("Sub string of str1 is: "+subStr1);

    }

    public static void exp3(){
        StringBuffer strBuffer=new StringBuffer("Java is great");
        System.out.println("Before change: "+strBuffer);
        strBuffer.setCharAt(0,'j');
        System.out.println("After change: "+strBuffer);

        StringBuilder strBuilder=new StringBuilder("Java is great");
        System.out.println("Before change: "+strBuilder);
        strBuilder.setCharAt(0,'j');
        System.out.println("After change: "+strBuilder);
    }

    public static void exp4(){
        Scanner scan=new Scanner(System.in);
        System.out.println("Please enter a command (cancel/connect/disconnect) followed by Enter.");
        String command = scan.nextLine();
        switch (command) {
            case "connect": System.out.println("Connecting"); break;
            case "disconnect": System.out.println("Disconnecting"); break;
            case "cancel": System.out.println("Canceling"); break;
            default: System.out.println("Unknown command"); break;
        }
    }
}
