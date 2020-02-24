package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.Lock;

import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.LockExample;

import java.util.concurrent.locks.ReentrantLock;

public class LockThread implements Runnable {
    private final String name;
    private final ReentrantLock lock;

    public LockThread(String name, ReentrantLock lock) {
        this.name = name;
        this.lock = lock;
    }

    @Override
    public void run() {
        System.out.println("Starting "+name);
        //First, lock count
        System.out.println(name + " is waiting to lock count.");
        lock.lock();
        System.out.println(name + " is locking count");
        Shared.count++;
        System.out.println(name + ": " + Shared.count);

        // now, allow a context switch if possible
        System.out.println(name + " is sleeping count");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //unlock
            System.out.println(name + " is unlocking count");
            lock.unlock();
        }

    }
}
