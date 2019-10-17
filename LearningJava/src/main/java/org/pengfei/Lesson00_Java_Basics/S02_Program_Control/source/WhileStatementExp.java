package org.pengfei.Lesson00_Java_Basics.S02_Program_Control.source;

public class WhileStatementExp {

    public static void exp1(){
        char ch='a';
        while(ch<='z'){
            System.out.println(ch);
            ch++;
        }
    }

    public static void exp2(){
        int power, result;
        for(int i=0;i<10;i++){
            result=1;
            power=i;
            // we know that the first for loop, i=0, the while loop will not be executed at all
            while(power>0){
                result=result*2;
                power--;
            }
            System.out.println("2 to the " + i+ " power is "+ result);
        }
    }
}
