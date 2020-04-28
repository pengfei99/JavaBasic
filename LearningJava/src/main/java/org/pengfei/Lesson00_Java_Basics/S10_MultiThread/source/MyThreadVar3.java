package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source;

public class MyThreadVar3  extends Thread {

    private String threadName;

    public MyThreadVar3(String threadName){
        super(threadName);
        this.threadName=threadName;

    }



    @Override
    public void run() {
        System.out.println("Current thread name: "
                + Thread.currentThread().getName());

        System.out.println("run() method called");
    }
}
