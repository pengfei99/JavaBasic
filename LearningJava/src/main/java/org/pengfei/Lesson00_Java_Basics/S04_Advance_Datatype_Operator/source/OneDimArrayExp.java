package org.pengfei.Lesson00_Java_Basics.S04_Advance_Datatype_Operator.source;

import java.util.Arrays;
import java.util.Random;

public class OneDimArrayExp {

    public static void exp1(){
        int test[]=new int[10];
        int i;

        // populate the array
        for(i=0;i<10;i++) test[i]=i;

        // print all element of the array
        for(i=0;i<10;i++) System.out.println("This is the "+i + " element of the arrty, It has vale "+test[i]);
    }

    // get the min, max of an array
    public static void exp2(){
        int nums[]= new int[10];
        int i,min,max;


        /* populate the array with random int between 0 and 100*/
        Random random=new Random();
        for(i=0;i<10;i++) {
            nums[i]=random.nextInt(100);
        }

         // Simple way to print array values is to use the Arrays package.
        System.out.println("Array contents: "+ Arrays.toString(nums));

        // get the min
        min=nums[0];
        max=nums[0];
        for(i=1;i<10;i++) {
            if(nums[i]>max) max=nums[i];
            if(nums[i]<min) min=nums[i];
        }

        System.out.println("The min is "+min+ " The max is "+max);
    }

    public static void exp3(){
        /* We can populate the array with one line, we replace new int[10] by {}*/
        int nums[]={99,-10,100,23,12,54,75,94,287,34};

        // reinitialization is not allowed
        // nums={99,-10,100,23,12,54,75,94,287,34, 10};


        System.out.println("Array contents: "+ Arrays.toString(nums));
    }

    public static void exp4(){
        int test[]=new int[5];
        int i;
        for(i=0;i<10;i++){
            test[i]=i;
        }
    }

    public static void bubbleSort(){
        int size=10;
        int nums[]= new int[size];
        int i;


        /* populate the array with random int between 0 and 100*/
        Random random=new Random();
        for(i=0;i<10;i++) {
            nums[i]=random.nextInt(100);
        }

        // Simple way to print array values is to use the Arrays package.
        System.out.println("Array contents: "+ Arrays.toString(nums));

        // use reverse index in second loop
        for (i=1;i<size;i++) {
            for (int reversIndex = size - 1; reversIndex >= i; reversIndex--) {
                if (nums[reversIndex - 1] > nums[reversIndex]) {
                    int tmp = nums[reversIndex - 1];
                    nums[reversIndex - 1] = nums[reversIndex];
                    nums[reversIndex] = tmp;

                }
            }
        }


  System.out.println(" Sorted Array contents: "+Arrays.toString(nums));
    }
}
