package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source;

/** This class test if the thread is daemon thread or not*/
public class DaemonTesterThread extends Thread {
    public DaemonTesterThread(String threadName) {
        this.threadName = threadName;
    }

    private final String threadName;

    public void run() {

        // Checking whether the thread is Daemon or not
        if (Thread.currentThread().isDaemon()) {
            System.out.println("Thread "+ threadName+" is executing as daemon thread");
        } else {
            System.out.println("Thread "+ threadName+" is executing as user(normal) thread");
        }
    }
}
