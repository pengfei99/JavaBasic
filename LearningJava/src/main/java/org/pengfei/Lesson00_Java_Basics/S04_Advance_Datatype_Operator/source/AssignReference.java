package org.pengfei.Lesson00_Java_Basics.S04_Advance_Datatype_Operator.source;

import java.util.Arrays;

public class AssignReference {
    public static void exp1(){
        int i,size=5;
        int nums1[]= new int[size];
        int nums2[]= new int[size];

        for(i=0;i<size;i++){
            nums1[i]=i;
            nums2[i]=-i;
        }

        System.out.println("Here is nums1: "+ Arrays.toString(nums1));
        System.out.println("Here is nums2: "+ Arrays.toString(nums2));

        // assign an array reference, after this assignment, nums1 and nums2 refer to the same object.
        nums2=nums1;
        System.out.println("Here is nums2 after assignment: "+ Arrays.toString(nums2));

        nums2[3]=99;
        System.out.println("Here is nums1 after change through nums2: "+ Arrays.toString(nums1));
    }
}
