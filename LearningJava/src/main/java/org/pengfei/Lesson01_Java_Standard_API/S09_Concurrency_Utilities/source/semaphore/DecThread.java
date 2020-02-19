package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.semaphore;

import org.pengfei.Lesson01_Java_Standard_API.S02_Exploring_Java_lang.source.thread.ThreadGroupExp;

import java.util.concurrent.Semaphore;

public class DecThread implements Runnable {
    private final String name;
    private final Semaphore sem;

    public DecThread(String threadName, Semaphore sem){
        this.name=threadName;
        this.sem=sem;
    }

    @Override
    public void run() {


        try {
            //first acquire a permit
            System.out.println(name+" waiting for a permit.");
            sem.acquire();
            System.out.println(name+" gets a permit.");

            //access shared resource
            for(int i=0;i<5;i++){
                SharedObj.count--;
                System.out.println(name+": " +SharedObj.count);

                //allow a context switch, if possible
                Thread.sleep(10);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //release permit
        System.out.println(name+" releases the permit.");
        sem.release();
    }
}
