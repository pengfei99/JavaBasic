package org.pengfei.Lesson01_Java_Standard_API.S02_Exploring_Java_lang.source.thread;

public class ThreadGroupExp {

    public static void exp1(){

        // create threadGroup and thread
        ThreadGroup groupA=new ThreadGroup("Group A");
        ThreadGroup groupB=new ThreadGroup("Group B");

        MyThread t1= new MyThread("T1",groupA);
        MyThread t2= new MyThread("T2",groupA);
        MyThread t3= new MyThread("T3",groupB);
        MyThread t4= new MyThread("T4",groupB);

        // start thread
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // display base info about thread group
        System.out.println("Group A thread information: ");
        groupA.list();

        System.out.println("Group B thread information: ");
        groupB.list();
        System.out.println();

        // suspend thread in group a
        System.out.println("Suspending Group A");
        // create an empty thread array. activeCount() method returns the thread count
        Thread tga[]= new Thread[groupA.activeCount()];

        // populate the thread array.
        groupA.enumerate(tga);
        for(int i=0;i<tga.length;i++){
            ((MyThread)tga[i]).mySuspend();
        }

        try{
            //Main thread sleep 4000 millis
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }

        // resuming group A
        System.out.println("Resuming group A");
        for(int i=0;i<tga.length;i++){
            ((MyThread)tga[i]).myResume();
        }

        // main thread wait for sub threads to finish
        try{
            System.out.println("Waiting for threads to finish");
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            System.out.println(" Exception in main thread");
        }

        System.out.println("Main thread exiting.");
    }
}
