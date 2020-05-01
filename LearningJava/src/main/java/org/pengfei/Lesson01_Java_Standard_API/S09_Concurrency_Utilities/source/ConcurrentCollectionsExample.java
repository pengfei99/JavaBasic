package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source;

import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.collections.ListReader;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.collections.ListWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentCollectionsExample {

    /*In this example, we have three thread writes elements to a list, and a thread
    * print the elements in the list.*/
    public static void exp1(){
        List<Integer> list=new ArrayList<>();
        List<Integer> safeList= Collections.synchronizedList(list);
        System.out.println("Program starting");
        //Reader starts to read after 3 writer finished.
        CountDownLatch cdl=new CountDownLatch(3);
        ExecutorService es= Executors.newFixedThreadPool(2);
        es.execute(new ListWriter("worker1", safeList,cdl));
        es.execute(new ListWriter("worker2", safeList,cdl));
        es.execute(new ListWriter("worker3", safeList,cdl));
        //wait countdown latch
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        es.execute(new ListReader(safeList));

        //executor must be shutdown explicitly, otherwise it will continue running non stop
        es.shutdown();
        System.out.println("Program terminating");

    }

    /*In this example, we have three thread writes elements to a list, and a thread
     * print the elements in the list.*/
    public static void exp2(){
        List<Integer> list=new ArrayList<>();

        System.out.println("Program starting");
        CountDownLatch cdl=new CountDownLatch(3);
        Thread t1 = new Thread(new ListWriter("worker1", list,cdl));
        Thread t2 = new Thread(new ListWriter("worker2", list,cdl));
        Thread t3 = new Thread(new ListWriter("worker3", list,cdl));
        Thread reader=new Thread(new ListReader(list));

        t1.start();
        t2.start();
        t3.start();
        reader.start();

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Program terminating");

    }
}
