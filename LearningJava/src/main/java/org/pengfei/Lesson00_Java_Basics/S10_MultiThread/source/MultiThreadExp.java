package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source;

import org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source.volatile_exp.MySharedDate;
import org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source.volatile_exp.Reader;
import org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source.volatile_exp.ThreadForSharedDate;
import org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source.volatile_exp.Writer;

public class MultiThreadExp {

    public static void exp1(){
        //First, construct a MyThread object
        MyThread myThread=new MyThread("Thread #1");

        //Second, construct a Thread object from myThread
        Thread t=new Thread(myThread);

        //Finally, run the thread which contains myThread object.
        t.start();


        //Put main thread into Sleep to wait the child Thread to finish
        for(int i=0; i<50; i++){
            System.out.print(".");
            try{
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted");
            }
        }
        System.out.println("Main thread ending");
    }

    public static void exp2(){
        // As the constructor is private, we can't use new to call it.
        // MyThreadVar1 test=new MyThreadVar1();
        // construct and run the thread using the factory method
       // MyThreadVar1 myThreadVar1=MyThreadVar1.createAndStart("Thead #1");


    }

    public static void exp3(){
        MyThreadVar2 myThreadVar2=MyThreadVar2.createAndRun("Thread #1");
    }


    public static void exp4(){
        MyThreadVar2 mt1=MyThreadVar2.createAndRun("Thread #1");
        MyThreadVar2 mt2=MyThreadVar2.createAndRun("Thread #2");
        MyThreadVar2 mt3=MyThreadVar2.createAndRun("Thread #3");
    }

    public static void exp5(){
        MyThreadVar2 mt1=MyThreadVar2.createAndRun("Thread #1");
        MyThreadVar2 mt2=MyThreadVar2.createAndRun("Thread #2");
        MyThreadVar2 mt3=MyThreadVar2.createAndRun("Thread #3");

        do{
           // System.out.println(".");
            try{
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted");
            }
        }while(mt1.isAlive()||mt2.isAlive()||mt3.isAlive());
    }

    public static void exp6(){
        MyThreadVar2 child1=MyThreadVar2.createAndRun("Thread #1");
        MyThreadVar2 child2=MyThreadVar2.createAndRun("Thread #2");
        MyThreadVar2 child3=MyThreadVar2.createAndRun("Thread #3");
        try{
            child1.join();
            System.out.println("Child #1 joined");

            child2.join();
            System.out.println("Child #2 joined");

            child3.join();
            System.out.println("Child #3 joined");
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
    }

    public static void exp7(){
        // The priority value must between 1 to 10
        ThreadWithPriority tp1=ThreadWithPriority.createAndRun("Thread High Priority", Thread.MAX_PRIORITY);
        ThreadWithPriority tp2=ThreadWithPriority.createAndRun("Thread Normal Priority", Thread.NORM_PRIORITY);
        ThreadWithPriority tp3=ThreadWithPriority.createAndRun("Thread Low Priority", Thread.MIN_PRIORITY);

        try {
            tp1.join();
            tp2.join();
            tp3.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }

        System.out.println(tp1.getName()+" has priority "+tp1.getPriority()+" has count "+tp1.getCount());
        System.out.println(tp2.getName()+" has priority "+tp2.getPriority()+" has count "+tp2.getCount());
        System.out.println(tp3.getName()+" has priority "+tp3.getPriority()+" has count "+tp3.getCount());
    }

    public static void exp8(){
        int nums[]={1,2,3,4,5};
        //In ThreadSynchronizedMethod, we have a static field SumArray. As its static, its shared by all instances
        // of its class. Therefore, its a shared object by all threads. That's why we need SumArray methods to be
        // synchronized. If each thread has its own instance of SumArray, no need to synchronized the method of
        // SumArray.
        ThreadSynchronizedMethod ts1= ThreadSynchronizedMethod.createAndRun("Thread #1", nums);
        ThreadSynchronizedMethod ts2= ThreadSynchronizedMethod.createAndRun("Thread #2", nums);
        try{
            ts1.join();
            ts2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
    }

    public static void exp9(){
        int[] nums={1,2,3,4,5};
        ThreadSynchronizedBlock tb1=ThreadSynchronizedBlock.createAndStart("Thread #1",nums);
        ThreadSynchronizedBlock tb2=ThreadSynchronizedBlock.createAndStart("Thread #2",nums);

        try{
            tb1.join();
            tb2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }

    }

    public static void exp10(){
        TickTock ttOb=new TickTock();
        ThreadForTicToc ttt1=ThreadForTicToc.createAndStart("Tick",ttOb);
        ThreadForTicToc ttt2=ThreadForTicToc.createAndStart("Tock",ttOb);

        try{
            ttt1.join();
            ttt2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }

    }

    public static void exp11(){
        ThreadSuspend ts=ThreadSuspend.createAndStart("Thread #1 for suspending test");
        try{
            // let the thread ts start executing for 1000 millis
            Thread.sleep(5000);

            // suspend thread and wait 1000 millis, then check what is the output
            ts.suspendThread();
            System.out.println("Suspending thread");
            Thread.sleep(5000);

            // resume thread and wait 1000 millis
            ts.resumeThread();
            System.out.println("Resuming thread");
            Thread.sleep(5000);

            // suspend thread and wait 1000 millis, then check what is the output
            ts.suspendThread();
            System.out.println("Suspending thread");
            Thread.sleep(5000);

            // resume thread and wait 1000 millis
            ts.resumeThread();
            System.out.println("Resuming thread");
            Thread.sleep(5000);

            // suspend thread and stop thread
            ts.suspendThread();
            System.out.println("Stopping thread");
            ts.stopThread();

        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }

        try{
            // this join is not the method provided by the class Thread
            ts.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
    }

    public static void exp12(){
        MyThreadVar3 myThreadVar3=new MyThreadVar3("First Child Thread");

        //only call run no thread will be created
        myThreadVar3.run();


    }

    public static void exp13(){
        MyThreadVar3 myThreadVar3=new MyThreadVar3("First Child Thread");

        //all start(), a child thread will be created
        myThreadVar3.start();


    }

    public static void exp14(){
        MyThreadVar3 myThreadVar3=new MyThreadVar3("First Child Thread");

        //try to call start() twice for a thread, and see what happens
        myThreadVar3.start();
        myThreadVar3.start();


    }

    //volatile is enough for this situation
    public static void exp15(){
        Thread r1=new Thread(new Reader("r1"));
        Thread w1=new Thread(new Writer("w1"));
       r1.start();
       w1.start();

        try {
            r1.join();
            w1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //volatile is not enough for this situation

    public static void exp16(){
        Thread w1=new Thread(new Writer("w1"));
        Thread w2=new Thread(new Writer("w2"));
        Thread w3=new Thread(new Writer("w3"));
        w1.start();
        w2.start();
        w3.start();


    }

    public static void exp17(){
        MySharedDate date=new MySharedDate();
        Thread reader=new ThreadForSharedDate("reader",date);
        Thread writer=new ThreadForSharedDate("writer",date);

        writer.start();
        reader.start();

        try {
            writer.join();
            reader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void exp18(){
        DaemonTesterThread t1=new DaemonTesterThread("t1");
        DaemonTesterThread t2=new DaemonTesterThread("t2");

        //set t1 as daemon thread
        t1.setDaemon(true);

        // start t1, t2
        t1.start();
        t2.start();

        //set t2 as daemon thread after start, not allowed and exception will be thrown
        //
        //t2.setDaemon(true);
    }

}
