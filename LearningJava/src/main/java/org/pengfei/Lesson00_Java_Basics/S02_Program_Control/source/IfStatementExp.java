package org.pengfei.Lesson00_Java_Basics.S02_Program_Control.source;

import java.io.IOException;
import java.util.Scanner;

public class IfStatementExp {

    public static void nestedIfExp() throws IOException {
        int i,j,k;
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter the value of i:");
        i = scan.nextInt();

        if (i==10){
            // statement1
            System.out.println("Your input of i is: " + i + " the i==10 is true, so you are in statement1");
            System.out.println("Enter the value of j:");
            j = scan.nextInt();
           if(j<20)
               //statement3
               System.out.println("Your input of j is: " + j + " the j<10 is true, so you are in statement3");
           System.out.println("Enter the value of k:");
           k = scan.nextInt();
           if(k>50)
               //statement4
               System.out.println("Your input of k is: " + k + " the k>50 is true, so you are in statement4");
           else
               //statement5 is the else which refers to if(k>50)
               System.out.println("Your input of k is: " + k + " the k>50 is false, so you are in statement5");
        }
        // statement2
        else System.out.println("Your input of i is: " + i + " the i==10 is false, so you are in statement2");
    }

    public static void ifElseLadder(){
        Scanner scan= new Scanner(System.in);
        System.out.println("Enter a int value between 0 and 5 :");
        int i = scan.nextInt();
        if(i==0) System.out.println("i is zero");
        else if(i==1) System.out.println("i is one");
        else if(i==2) System.out.println("i is two");
        else if(i==3) System.out.println("i is three");
        else if(i==4) System.out.println("i is four");
        else if(i==5) System.out.println("i is five");
        else System.out.println("The input i is not between 0 and 5");



    }
}
