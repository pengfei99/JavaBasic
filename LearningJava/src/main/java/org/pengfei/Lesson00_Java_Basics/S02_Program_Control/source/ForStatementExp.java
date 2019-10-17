package org.pengfei.Lesson00_Java_Basics.S02_Program_Control.source;

import java.io.IOException;

public class ForStatementExp {
    public static void exp1(){
        double i,sroot,rerr;
        for(i=1.0; i<100.0;i++){
            sroot = Math.sqrt(i);
            System.out.println("Square root of"+i+" is"+ sroot);

            //compute rounding error
            rerr=i-(sroot*sroot);
            System.out.println("Rounding error is: "+rerr);
        }
    }
    public static void exp2(){
        for(int i=100; i >0;i=i-5){
            System.out.println("The value of i "+i);
        }
    }

    public static void exp3(){
        int i,j;
        for(i=0,j=10;i<j;i++,j--){
            System.out.println("i and j: "+i+" "+j);
        }
    }
    public static void exp4() throws IOException {
        for (int i=0; (char) (System.in.read()) !='S';i++){
            System.out.println(" I value "+ i);
        }
    }

    public static void exp5(){
        for(int i=0;i<10;){
            System.out.println("Value of i "+i);
            // we increment the loop control var, otherwise it become an infinite loop
            i++;
        }
    }

    public static void exp6(){
        int i,sum=0;

        for(i=1;i<=5;sum+=i++);

        System.out.println("Value of sum is: "+sum);
    }
}
