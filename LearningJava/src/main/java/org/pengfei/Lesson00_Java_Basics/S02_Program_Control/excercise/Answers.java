package org.pengfei.Lesson00_Java_Basics.S02_Program_Control.excercise;

import java.io.IOException;

public class Answers {


    public static void exp1(){
        System.out.println("Please enter some character!");
        int count=0;
        while (true){
        try {
            char ch = (char)System.in.read();
            if(ch=='.') break;
            else if(ch==' ') count++;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
        System.out.println("Total space count is: "+count);
    }

    public static void exp4(){
        for(int i=1000;i>0;i-=2){
            System.out.println(i);
        }
    }

    public static void exp8(){
        for(int i=0; i<10;i++){
            System.out.print(i+ " ");
            if((i%2)==0) continue;
            // If continue is called, all statement after continue are not executed, the loop start over with the next index
            System.out.println(" ");

        }
    }

    public static void exp9(){
        for(int i=1;i<1000;i*=2){
            System.out.println(i);
        }
    }

    public static void exp10(){
        System.out.println("please enter a character");

        while (true){
            try {
                int ch=System.in.read();
                if((char)ch=='.') break;
                else if(ch>96&&ch<123){
                    ch=ch-32;
                }
                char out=(char) ch;
                System.out.println(out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
       // Answers.exp1();
       // Answers.exp4();
       // Answers.exp8();
       // Answers.exp9();
        Answers.exp10();
    }
}
