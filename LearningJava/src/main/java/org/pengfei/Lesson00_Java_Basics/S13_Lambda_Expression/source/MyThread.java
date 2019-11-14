package org.pengfei.Lesson00_Java_Basics.S13_Lambda_Expression.source;

public class MyThread implements Runnable {
private String threadName;

public MyThread(String threadName){
    this.threadName=threadName;
}

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
