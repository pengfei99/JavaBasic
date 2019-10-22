package org.pengfei.Lesson00_Java_Basics.S04_Advance_Datatype_Operator.source;

import java.util.Arrays;

public class ArrayLengthExp {

    public static void exp1(){
        int list[]=new int[10];
        int nums[]={1,2,3};
        int table[][]={
                {1,2,3},
                {4,5},
                {6,7,8,9}
        };
        System.out.println("Length of list is "+list.length);
        System.out.println("Length of nums is "+nums.length);
        // length of the table is the size of the first index,
        System.out.println("Length of table is "+table.length);
        // length of second index
        System.out.println("Length of table[0] is "+table[0].length);
        System.out.println("Length of table[1] is "+table[1].length);
        System.out.println("Length of table[2] is "+table[2].length);

        // use length to initialize list
        for(int i=0; i<list.length;i++) list[i]=i*i;

        System.out.print("Here is list: ");
        // use length to loop list, this is easier and safer, can avoid outOfBound exception
        for(int i=0; i<list.length;i++)
            System.out.print(list[i]+" ");
        System.out.println();
    }

    public static void exp2(){
        int i,length;
        int list1[]=new int[5];
        int list2[]=new int[10];

        for(i=0;i<list1.length;i++){
            list1[i]=i;
        }

        //We copy list1 to list2
        if(list1.length> list2.length){
            System.out.println("The length of list1 is bigger than list2, data will be lost during copy ");
            length=list2.length;
        }
        else {
            length=list1.length;
        }
        for(i=0;i<length;i++){
            list2[i]=list1[i];
        }

        System.out.println("list1 has contents "+ Arrays.toString(list1));
        System.out.println("list2 has contents "+ Arrays.toString(list2));
    }
}
