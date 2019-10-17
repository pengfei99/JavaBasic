package org.pengfei.Lesson00_Java_Basics.S02_Program_Control.source;

public class ContinueStatementExp {

    public static void exp1(){
        int i;
        for(i=0;i<=10;i++){
            // continue to next iteration without execution of the rest statement in the for loop
            // for example, i=1 i%2 =1, continue is activated, so print is not executed
            // i=2, i%2=0, continue is deactivated, so print is executed.
            if((i%2)!=0) continue;
            System.out.println(i);
        }
    }

    public static void exp2(){
        outerloop: for (int i=1; i<10;i++){
        System.out.println("\n Outer loop pass " +i + ", Inner loop: ");
             for (int j=1;j<10;j++){
                 // exit second loop for (j loop), continue with outerloop,
                 // loop for will first run i++, then evaluate i<1 to check if we start the iteration or not
                 // When continue is called, the print statement is skipped
                 if (j == 3) continue outerloop;
                 System.out.println(j);
             }
        }

    }
}
