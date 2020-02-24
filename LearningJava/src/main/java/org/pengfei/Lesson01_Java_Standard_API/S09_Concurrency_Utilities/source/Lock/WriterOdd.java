package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.Lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/* This class writes odd numbers between 1 and 10 into the shared list*/
public class WriterOdd implements Runnable {
    private final String name;
    private final ReentrantReadWriteLock lock;

    public WriterOdd(String name, ReentrantReadWriteLock lock) {
        this.name = name;
        this.lock = lock;
    }

    @Override
    public void run() {
        System.out.println("Starting "+name);
   for(int i=1;i<10;i++){
       //acquire write lock
       lock.writeLock().lock();
       if((i%2)!=0) {
           Shared.list.add(i);
           System.out.println("Writing "+i);
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
