package org.pengfei.Lesson00_Java_Basics.S04_Advance_Datatype_Operator.source;

import java.util.Arrays;

public class ForEachExp {

    public static void exp1(){
        int nums[]={1,2,3,4,5,6,7,8,9};
        for(int x: nums){
            System.out.print(x);
        }
    }

    public static void exp2(){
        int nums[] = new int[10];

        for(int i=0;i<10;i++){
            nums[i]=i;
        }

        for(int i:nums){
            if(i==5) break;
            System.out.println("loop iteration"+i);
        }
    }

    public static void exp3(){
        int nums[]={1,2,3,4,5,6,7,8,9};

        // in for each loop, iteration variable is read only
        for(int x: nums){
            x=x*x;
        }
        System.out.println("For each loop is read only, no modification: "+ Arrays.toString(nums));

        // if we want to change value of the array, we need to use the origin for loop.
        for(int i=0;i<nums.length;i++){
            nums[i]=nums[i]*nums[i];
        }
        System.out.println("Origin for loop can modify,"+Arrays.toString(nums));
    }

    public static void exp4(){
        int[][] table={
                {0,1,2,3,4,5},
                {0,1,2,3,4},
                {0,1,2,3}
        };

        int i=0;
        for(int[] x:table){

            System.out.print("line "+i+" : ");
            for(int y: x){
                System.out.print(y);
            }
            i++;
            System.out.println();
        }
    }

    public static boolean Search(int value){
        boolean result=false;
        int[] nums={1,2,3,4,5,6,7,8,9};
        for(int x: nums){
            if(x==value) {
                result=true;
                break;
            }
        }
        return result;
    }
}
