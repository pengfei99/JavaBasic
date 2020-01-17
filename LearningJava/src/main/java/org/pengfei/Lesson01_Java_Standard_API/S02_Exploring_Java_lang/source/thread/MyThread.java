package org.pengfei.Lesson01_Java_Standard_API.S02_Exploring_Java_lang.source.thread;

public class MyThread extends Thread {
    boolean suspendFlag;

    public MyThread(String threadName, ThreadGroup tgOb){
        super(tgOb,threadName);
        System.out.println("New thread created: "+this);
        suspendFlag = false;
    }

    // entry point for thread

    public void run(){
        try{
            for(int i=5; i>0; i--){
                System.out.println(getName()+": "+i);
                Thread.sleep(1000);
                synchronized (this){
                    while (suspendFlag){
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(getName()+" exiting.");
    }

    synchronized void mySuspend(){
        suspendFlag=true;
    }
    synchronized void myResume(){
        suspendFlag=false;
        notify();
    }
}
