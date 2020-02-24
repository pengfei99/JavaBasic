package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.Lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WriterEven implements Runnable{
    private final String name;
    private final ReentrantReadWriteLock lock;

    public WriterEven(String name, ReentrantReadWriteLock lock) {
        this.name = name;
        this.lock = lock;
    }

    @Override
    public void run() {
        System.out.println("Starting "+name);
        for(int i=1;i<10;i++){
            //acquire write lock
            lock.writeLock().lock();
            if((i%2)==0) {
                System.out.println("Writing "+i);
                Shared.list.add(i);
            }
            //release write lock
            lock.writeLock().unlock();

            //sleep 50 millis for context changing.
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
