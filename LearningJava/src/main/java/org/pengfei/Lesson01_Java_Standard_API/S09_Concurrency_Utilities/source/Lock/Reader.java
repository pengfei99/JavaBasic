package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.Lock;


import java.util.Arrays;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Reader implements Runnable {
    private final String name;
    private final ReentrantReadWriteLock lock;

    public Reader(String name, ReentrantReadWriteLock lock) {
        this.name = name;
        this.lock = lock;
    }
    @Override
    public void run() {
        System.out.println("Starting "+name);

      for(int i=0;i<10;i++){
          //acquire the read lock and read list content
          lock.readLock().lock();
          System.out.println(name+" reads list contents: "+ Arrays.toString(Shared.list.toArray()));
          //release the lock
          lock.readLock().unlock();
          //sleep for changing the context
          try {
              Thread.sleep(100);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
    }
}
