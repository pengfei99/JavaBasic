package org.pengfei.Lesson00_Java_Basics.S02_Program_Control.source;

import java.util.Scanner;

public class SwitchExp {

    public static void exp1(){
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter a int value between 0 and 5 :");
        int i=scan.nextInt();
       /* break is very important in switch, for example, without break statement, If I enter 1, it will
       * return the following output:
       * i is one
       * i is two
       * i is three
       * i is four
       * i is five
       * i is not between zero to five
       *
       * It means all statements followed behind the right case will be executed.
       * So don't forget the break statement!!!
       * */
        switch (i){
            case 0: System.out.println("i is zero"); break;
            case 1: System.out.println("i is one"); break;
            case 2: System.out.println("i is two"); break;
            case 3: System.out.println("i is three"); break;
            case 4: System.out.println("i is four"); break;
            case 5: System.out.println("i is five"); break;
            default: System.out.println("i is not between zero to five"); break;
        }
    }

    public static void exp2(){
        char ch1='A';
        switch(ch1){
            case 'A': System.out.println("This is the part of outer switch");
                      char ch2=ch1;
                      switch (ch2){
                          case 'A': System.out.println("This is the part of inner switch");
                          break;
                      }
            case 'B': break;
        }
    }
}
