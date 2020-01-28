package org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source;

import java.util.Arrays;

public class ArraysAlgoExp {
    public static void exp1(){
        int a[] =new int[10];

        for(int i=0;i<10;i++) a[i]=-3*i;

        System.out.println("Original contents: ");
        display(a);

        // Sort the array
        Arrays.sort(a);

        System.out.println("Contents after sorting: ");
        display(a);

        // parallelPrefix with function x+y, it's like we do sum of the array.
         Arrays.parallelPrefix(a,((x,y)-> (x+y)));

        System.out.println("Contents after parallePrefix: ");
        display(a);

        // fill the array with start index 2, end index 6 and replace value -1
        Arrays.fill(a,2,6,-1);
        System.out.println("Contents after fill: ");
        display1(a);

        // binary search for -132
        int index=Arrays.binarySearch(a,-132);
        System.out.println("The value -132 is at location: "+index);
    }
    private static void display(int array[]){
        for(int i: array) System.out.print(i+" ");
        System.out.println();
    }

    // we can print the array by using stream.
    private static void display1(int array[]){
        Arrays.stream(array).forEach(e -> System.out.print(e + " "));
        System.out.println();
    }
}
