package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source;

import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.semaphore.*;

import java.util.concurrent.Semaphore;

public class SynchronizationObjExample {

    /*In this example, we use one semaphore, two threads to access one resources*/
    public static void exp1(){
        Semaphore sem= new Semaphore(1,true);
        new Thread(new IncThread("Thread Inc",sem)).start();
        new Thread(new DecThread("Thread Dec",sem)).start();
    }

    /*In this example, we have two producer and one consumer which access one SynchronizedQueue*/
    public static void exp2(){
        SynchronizedQueue queue= new SynchronizedQueue();
        // create two Producer, one writes 1, the other writes 2 to the queue
        new Thread(new Producer("P1",queue,1)).start();
        new Thread(new Producer("P2",queue,2)).start();

        // create one consumer
        new Thread(new Consumer(queue)).start();
    }
}
