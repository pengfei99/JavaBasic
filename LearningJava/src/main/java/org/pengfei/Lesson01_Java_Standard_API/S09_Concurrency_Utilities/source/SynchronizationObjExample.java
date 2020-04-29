package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source;

import org.pengfei.Lesson01_Java_Standard_API.S02_Exploring_Java_lang.source.thread.MyThread;
import org.pengfei.Lesson01_Java_Standard_API.S02_Exploring_Java_lang.source.thread.ThreadGroupExp;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.coutdownlatch.SystemHealthChecker;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.cyclicbarrier.ListReducer;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.cyclicbarrier.ListWorker;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.exchanger.StringMaker;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.exchanger.StringReader;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.phaser.MyPhaser;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.phaser.PhaseWorker;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.phaser.SimplePhaseWorker;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.semaphore.*;

import java.util.concurrent.*;

public class SynchronizationObjExample {

    /*In this example, we use one semaphore, two threads to access one resources*/
    public static void exp1(){
        Semaphore sem= new Semaphore(1,true);

        new Thread(new DecThread("Thread Dec",sem)).start();

        new Thread(new IncThread("Thread Inc",sem)).start();
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

    /* In this example, we have three system health checker, if all checks are ok, we start the
    * system, otherwise we wait. You will see if we set all three to true, It will start the
    * system and end. If one failed, it will retry, and system never start*/
    public static void exp3(){
        CountDownLatch latch=new CountDownLatch(3);
        // create three health checker, if all good Start system
        new Thread(new SystemHealthChecker("CPU",true,latch)).start();
        new Thread(new SystemHealthChecker("DISK",true,latch)).start();
        var memory=new SystemHealthChecker("MEMORY",false,latch);
        new Thread(memory).start();

        //wait latch to be released to start system
        try{
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Starting the system");

    }

    public static void exp4(){
        // create a barrier, and specifies when all threads reach the barrier then run the
        // listReducer thread
        CyclicBarrier barrier=new CyclicBarrier(3,new ListReducer("ListReducer"));
        new Thread(new ListWorker("worker1",barrier)).start();
        new Thread(new ListWorker("worker2",barrier)).start();
        new Thread(new ListWorker("worker3",barrier)).start();

    }

    public static void exp5(){
        // create a barrier, and specifies when all threads reach the barrier then run the
        // listReducer thread
        CyclicBarrier barrier=new CyclicBarrier(3,new ListReducer("ListReducer"));
        new Thread(new ListWorker("worker1",barrier)).start();
        new Thread(new ListWorker("worker2",barrier)).start();
        new Thread(new ListWorker("worker3",barrier)).start();
        new Thread(new ListWorker("worker4",barrier)).start();
        new Thread(new ListWorker("worker5",barrier)).start();
        new Thread(new ListWorker("worker6",barrier)).start();

    }

    // use exchanger to chang string between two string
    public static void exp6(){
        Exchanger<String> ex= new Exchanger<>();

        new Thread(new StringMaker(ex)).start();
        new Thread(new StringReader(ex)).start();
    }

    // use phaser to make a four phase execution
    public static void exp7(){
        // create a phaser with one register party which is the calling program itself (main)
        Phaser phaser=new Phaser(1);

        int currentPhase;
        System.out.println("Starting...");

        //Create three phaser worker
        new Thread(new PhaseWorker("Worker1",phaser)).start();
        new Thread(new PhaseWorker("Worker2",phaser)).start();
        new Thread(new PhaseWorker("Worker3",phaser)).start();

        // Wait for all threads to complete phase one.
        currentPhase=phaser.getPhase();
        // the below arrive suspend the main and wait until phase complete
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase "+currentPhase+" Complete");

        // Wait for all threads to complete phase two.
        currentPhase=phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase "+currentPhase+" Complete");

        // Wait for all threads to complete phase three.
        currentPhase=phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase "+currentPhase+" Complete");

        // Wait for all threads to complete phase four.
        currentPhase=phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase "+currentPhase+" Complete");

        // we can add a phase five, as all PhaseWorker deregister at phase 4. So here, if main(only party) calls arrive, the
        // phase is complete.
        currentPhase=phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase "+currentPhase+" Complete");
    }

    /*In exp7, we use thread to control how many phase we do, in exp8, we create a custom phaser which can control
    * the number of phase. It means we set a number of phase when we create the phaser, after the number is reached
    * the phaser is automatically closed.
    * */

    public static void exp8(){
        // we create a phaser with 1 party(for main), thread will register automatically, no need to specify party
        // number here, phase count is 4, it means it will have 4 phase
        Phaser myPhaser=new MyPhaser(1, 4);

        System.out.println("Program starting\n");
        new Thread(new SimplePhaseWorker("worker1",myPhaser)).start();
        new Thread(new SimplePhaseWorker("worker2",myPhaser)).start();
        new Thread(new SimplePhaseWorker("worker3",myPhaser)).start();

        // wait for the specified number of phases to complete
        while(!myPhaser.isTerminated()){
            myPhaser.arriveAndAwaitAdvance();
        }
        System.out.println("The phaser is terminated");
    }


    public static void exp9(){
        /*
         * Creates a new phaser with no registered unArrived parties.
         */
        Phaser parentPhaser = new Phaser();

        /*
         * Creates a new phaser with the given parent &
         * no registered unArrived parties.
         */
        Phaser childPhaser = new Phaser(parentPhaser,0);

        childPhaser.register();

        System.out.println("parentPhaser isTerminated : "+parentPhaser.isTerminated());
        System.out.println("childPhaser isTerminated : "+childPhaser.isTerminated());

        childPhaser.arriveAndDeregister();
        System.out.println("\n--childPhaser has called arriveAndDeregister()-- \n");

        System.out.println("parentPhaser isTerminated : "+parentPhaser.isTerminated());
        System.out.println("childPhaser isTerminated : "+childPhaser.isTerminated());
    }
}
