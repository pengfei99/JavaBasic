package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source;

public class MultiThreadExp {

    public static void exp1(){
        //First, construct a MyThread object
        MyThread myThread=new MyThread("Thread #1");

        //Second, construct a Thread object from myThread
        Thread t=new Thread(myThread);

        //Finally, run the thread which contains myThread object.
        t.start();

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
}
