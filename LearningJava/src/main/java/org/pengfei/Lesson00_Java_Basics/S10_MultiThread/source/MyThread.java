package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source;


/** This thread just run a loop, each iteration of the loop, it will sleep 400 milli-second.*/
public class MyThread implements Runnable {
    String threadName;

    public MyThread(String name) {
        threadName = name;
    }

    // Entry point of the thread
    @Override
    public void run() {
        System.out.println(threadName + " starting.");
        try {
            for (int count = 0; count < 10; count++) {
                Thread.sleep(400);
                System.out.println("In "+threadName+", count is "+count);
            }
        } catch (InterruptedException e) {
            System.out.println(threadName+" interrupted");
        }
        System.out.println(threadName+" terminating");
    }
}
