package org.pengfei.Lesson13_Common_Data_Structure.L13_S6_List;

import java.util.Iterator;
import java.util.Random;

public class SortingPositionalList {
    public static void main(String[] args){
    /***************************** 13.6.6 Sorting a positional list  ***********************/
    /* In this lesson, we will use a sorting algo know as insertion-sort to sort a Positional list.*/

    /***************************** 13.6.6.1 The insertion-sort algo  ***********************/

    /* The algorithm proceeds by considering one element at a time, placing the element in the correct order
     * relative to those before it. We start with the first element in the array, which is trivially sorted by
     * itself. When considering the next element in the array, if it is smaller than the first, we swap them. Next
     * we consider the third element in the array, swapping it leftward until it is in its proper order relative to
     * the first two elements. We continue in this manner with the fourth element, the fifth, and so on, until the
     * whole array is sorted. We can express the insertion-sort algorithm in pseudocode, as shown below:
     *
     * Algorithm InsertionSort(A):
     * Input: An array A of n comparable elements
     * Output: The array A with elements rearranged in nondecreasing order
     * for k from 1 to n−1 do
     * Insert A[k] at its proper location within A[0], A[1], . . ., A[k].*/

    /********************* 13.6.6.2 The insertion-sort algo example on an array of characters ******************/
    char[] data=new char[5];
        Random r = new Random();
        for(int i=0;i<data.length;i++){
            char c = (char)(r.nextInt(26) + 'a');
            data[i]=c;
        }
        System.out.println("before sorting, data value is: ");
        /* We can't use foreach on Array, because array is not implementing the collection interface. As a result, the
        * iteralbe method is not implemented, so no foreach support.
        *
        * Why Array is not collection?
        *
        * Array is a primitive data structure which can only store primitive element type (e.g. int, string, etc.). The
        * class which implements collection can only store elements of object types. For the primitive types, we have to
        * use their object mapper type (e.g. Integer, String, etc.)
        *
        * Array has fixed size, collection has dynamic size.
        * */
        for(int i=0;i<data.length;i++){
            System.out.print(data[i]+",");

        }
        System.out.println(" ");
        insertionSortOnCharacterArray(data);

        System.out.println("After sorting, data value is: ");

        for(int i=0;i<data.length;i++){
            System.out.print(data[i]+",");

        }
        System.out.println(" ");
        /********************* 13.6.6.3 The insertion-sort algo example on positional list ******************/
        /* The difference between an array and a positional list is that the addBetween method save us from swapping
         * the element before the current element.  */

        LinkedPositionalList<Integer> list=new LinkedPositionalList<>();

        for(int i=0;i<5;i++){
            list.addFirst(i);
        }
        System.out.println("before sorting, the positional list value is: ");
        Iterator<Integer> it = list.iterator();
         while (it.hasNext()){
           System.out.print(it.next()+",");

       }
        System.out.println(" ");
         insertionSortOnPostionalList(list);
        System.out.println("After sorting, the positional list value is: ");
       /* Position<Integer> currentP=list.last();
        while (currentP!=list.first()){
            Integer currentVal=currentP.getElement();
            System.out.println("currentValue :"+currentVal);
            currentP=list.before(currentP);
        }
     Integer first=list.first().getElement();
        System.out.println("first Value :"+first);*/
        Iterable<Position<Integer>> positions = list.positions();
        Iterator<Position<Integer>> it1 = positions.iterator();
        while (it1.hasNext()){
            System.out.print(it1.next().getElement()+",");
        }
        System.out.println(" ");
    }

    public static void insertionSortOnCharacterArray(char[] data){
        /*we suppose Z > B > A*/
        int n=data.length;
        /* The first loop will loop through all elements of the list*/
        for(int k=1;k<n;k++){
            char currentChar=data[k];
            int j=k-1;
            /* The second loop will compare the current element with all elements before it. If the current element is
            * bigger, the loop will stop*/
            while(j>0&& data[j-1]>currentChar){
                data[j]=data[j-1];
                j--;
            }
            data[j]=currentChar;
        }
    }

    public static void insertionSortOnPostionalList(LinkedPositionalList<Integer> list){
        // marker is the last position of the sub sorted list, it starts on the first element
        Position<Integer> marker=list.first();

        while (marker!=list.last()){
        // pivot is the first un sorted position next to marker
        Position<Integer> pivot=list.after(marker);

        // walker is a iterator will loop through all element before pivot, until pivot is greater than the walker

            Integer currentValue=pivot.getElement();
        if(currentValue>marker.getElement())
            marker=pivot;
        else {
            Position<Integer> walker = marker;
            //while walker is greater than pivot, we need to go before walker to find the good position for pivot to insert
            while (walker!=list.first()&&list.before(walker).getElement()>currentValue){
                walker=list.before(walker);
            }
            // when we are out of the while loop, we know before(walker)>pivot>worker,
            list.remove(pivot);
            list.addBefore(walker, currentValue);

        }

        }
    }

}
