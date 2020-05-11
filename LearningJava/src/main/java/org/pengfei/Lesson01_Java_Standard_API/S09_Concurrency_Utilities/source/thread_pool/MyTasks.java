package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.thread_pool;

public class MyTasks implements Runnable {
    private final String threadName;

    public MyTasks(String threadName){
        this.threadName=threadName;
    }
    @Override
    public void run() {
        System.out.println("Start executing " + threadName);
        try {
            Thread.sleep(1000);
            System.out.println("Executing " + threadName);
        } catch (InterruptedException e) {
            // skipping the catch as of now
            e.printStackTrace();
        }
        System.out.println("Finished execution " + threadName);
        System.out.println();
    }
}
