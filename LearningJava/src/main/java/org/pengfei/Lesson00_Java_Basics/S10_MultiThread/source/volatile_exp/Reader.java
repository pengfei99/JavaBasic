package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source.volatile_exp;

public class Reader implements Runnable {
    private String threadName;


    public Reader(String threadName) {
        this.threadName = threadName;

    }

    @Override
    public void run() {
        while (SharedObject.count<10){
            System.out.println("Reader "+threadName+" print count value: "+SharedObject.count);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
