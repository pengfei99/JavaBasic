package org.pengfei.Lesson00_Java_Basics.S04_Advance_Datatype_Operator.source;

public class QuestionMarkOperatorExp {

    public static void exp1(){
        int result;
        for(int i=-3;i<3;i++){
            result= i!=0?100/i:0;
            System.out.println("!00/"+i+" is "+result);
        }
    }

    public static void exp2(){

        for(int i=-3; i<3; i++){
            // only for demonstration purpose, you dont need the ? here
            if(i!=0?true:false)
                System.out.println("!00/"+i+" is "+ 100/i);
        }
    }
}
