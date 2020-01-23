package org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class CollectionsAlgoExp {

    public static void exp1() {
        LinkedList<Integer> l1 = new LinkedList<>();
        LinkedList<Integer> l2 = new LinkedList<>();
        l1.add(-8);
        l1.add(20);
        l1.add(-20);
        l1.add(8);

        l2.add(20);
        l2.add(8);

        System.out.println("Initial content of l1: "+l1);

        // create an reverse order comparator.
        Comparator<Integer> c= Collections.reverseOrder();

        // sort the l1 with c, l1 must be modifiable
        Collections.sort(l1,c);

        System.out.println("Content of l1 after sort with comparator: "+l1);

        // get index of sub list
        System.out.println("index of l2 in l1: "+Collections.indexOfSubList(l1,l2));
        System.out.println("last index of l2 in l1: "+Collections.lastIndexOfSubList(l1,l2));

        //Shuffle l1 with default randomness
        Collections.shuffle(l1);
        System.out.println("Content of l1 after shuffle: "+l1);

        //get the min and max
        System.out.println("Min in l1: "+Collections.min(l1));
        System.out.println("Max in l1: "+Collections.max(l1));



    }
}
