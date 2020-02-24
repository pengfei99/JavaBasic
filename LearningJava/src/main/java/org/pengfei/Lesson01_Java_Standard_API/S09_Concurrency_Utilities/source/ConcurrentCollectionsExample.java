package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source;

import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.collections.ListReader;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.collections.ListWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ConcurrentCollectionsExample {

    /*In this example, we have three thread writes elements to a list, and a thread
    * print the elements in the list.*/
    public static void exp1(){
        List<Integer> list=new ArrayList<>();
        List<Integer> safeList= Collections.synchronizedList(list);
        System.out.println("Program starting");
        Thread t1 = new Thread(new ListWriter("worker1", safeList));
        Thread t2 = new Thread(new ListWriter("worker2", safeList));
        Thread t3 = new Thread(new ListWriter("worker3", safeList));
        Thread reader=new Thread(new ListReader(safeList));

        t1.start();
        t2.start();
        t3.start();
        reader.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            reader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Program terminating");

    }

    /*In this example, we have three thread writes elements to a list, and a thread
     * print the elements in the list.*/
    public static void exp2(){
        List<Integer> list=new ArrayList<>();

        System.out.println("Program starting");
        Thread t1 = new Thread(new ListWriter("worker1", list));
        Thread t2 = new Thread(new ListWriter("worker2", list));
        Thread t3 = new Thread(new ListWriter("worker3", list));
        Thread reader=new Thread(new ListReader(list));

        t1.start();
        t2.start();
        t3.start();
        reader.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            reader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Program terminating");

    }
}
