package org.pengfei.Lesson00_Java_Basics.S02_Program_Control.source;

import java.io.IOException;

public class BreakStatementExp {
    public static void exp1() {
        int num = 100;
        for (int i = 0; i < num; i++) {
            // stop loop if i*i>=100, it should stop when i=10
            if (i * i >= num) break;
            // The print will not be executed when break is called, so we will see the print from 0 to 9

            System.out.println("Value of i: " + i);
        }
        System.out.println("The loop is completed");
    }

    public static void exp2() throws IOException {
        char ch;
        for(;;){
            System.out.println("Press a key followed by enter" );
            ch=(char)System.in.read();
            if(ch=='q') break;
            System.out.println("The vaule which you entered is: "+ ch );
        }
    }

    public static void exp3(){
        for (int i=0; i<3;i++) {
            System.out.println("Outer loop count: " + i);
            System.out.print("   Inner loop count: ");
            int t = 0;
            while (t < 100) {
                if (t == 5) break;
                System.out.print(t + " ");
                t++;
            }
            System.out.println();
        }
        System.out.println("Loops complete");
    }

    public static void exp4(){
        int i;
        for(i=1;i<4;i++){
            // break one will get us out of the block one, as a result it print after block one
            one: {
            // break two will get us out of the block two, as a result it print first "after block two", then "after one"
             two: {
                // break three,
                three: {
                  System.out.println("\n i is "+i);
                  if(i==1) break one;
                  if(i==2) break two;
                  if(i==3) break three;
                  // this is never reached
                    System.out.println("won't print");
                }
                System.out.println("After block three.");
             }
                System.out.println("After block two.");
            }
            System.out.println("After block one.");
        }
        System.out.println("After for loop.");
    }

    public static void exp5(){
        done: for(int i=0; i<10;i++){
                 for (int j=0; j<10; i++){
                     for(int k=0; k<10; k++){
                         System.out.println(k + " ");
                         // jump out of done block which is the for loop
                         if (k==3) break done;
                     }
                     System.out.println("After k loop");
                 }
            System.out.println("After j loop");
        }
        System.out.println("After i loop");
    }


}
