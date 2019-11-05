package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source;


/**
 * In this variation, we creates a Thread obj when MyThreadVar constructor is called
 * and stores it in an instance variable thread. It provides a factory method
 * to create and start a thread*/
public class MyThreadVar1 implements Runnable {

    Thread thread;

    // The constructor is private, so only the factory method can call it
    private MyThreadVar1(String name){
     thread=new Thread(this,name);
    }

    // A factory method that creates and starts a thread
    public static MyThreadVar1 createAndStart(String name){
        MyThreadVar1 myVar1=new MyThreadVar1(name);
        myVar1.thread.start();
        return myVar1;
    }

    // Entry point of thread.
    @Override
    public void run() {
        String threadName = thread.getName();
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
