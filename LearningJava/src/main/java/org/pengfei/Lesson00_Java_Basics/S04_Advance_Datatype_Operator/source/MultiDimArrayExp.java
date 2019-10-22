package org.pengfei.Lesson00_Java_Basics.S04_Advance_Datatype_Operator.source;

import java.util.Arrays;
import java.util.Random;

public class MultiDimArrayExp {

    public static void exp1(){
        int i,j;
        Random random= new Random();
        int table[][]=new int[3][4];
        for(i=0;i<3;i++){
            for(j=0;j<4;j++){
                table[i][j]=random.nextInt(100);
            }
        }
        for(i=0;i<3;i++){
            int[] tmp = table[i];
        System.out.println("Line "+i+" has contents: "+ Arrays.toString(tmp));
        }
    }

    public static void exp2(){
        Random random=new Random();
        int i,j;
        int table[][]=new int[3][];
        table[0]=new int[4];
        table[1]=new int[2]; // saved 2 slot
        table[2]=new int[1]; // saved 3 slot

        for(j=0;j<4;j++){
            table[0][j]=random.nextInt(100);
        }
        for(j=0;j<2;j++){
            table[1][j]=random.nextInt(100);
        }
        table[2][0]=random.nextInt(100);

        for(i=0;i<3;i++){
            int[] tmp=table[i];
            System.out.println("table "+i+" has contents"+Arrays.toString(tmp));
        }
    }

    public static void exp3(){
        int i;
        int table[][]={
                {0,1,2},
                {2,1,0},
                {0,0,0}
        };
        for(i=0;i<3;i++){
            int[] tmp=table[i];
            System.out.println("table "+i+" has contents"+Arrays.toString(tmp));
        }
    }
}
