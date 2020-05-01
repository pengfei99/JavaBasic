package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source;

import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.Lock.LockThread;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.Lock.Reader;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.Lock.WriterEven;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.Lock.WriterOdd;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.collections.ListReader;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.collections.ListWriter;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockExample {


    /*
    * It creates two threads that access a shared resource called Shared.count.	Before a thread can access Shared.count,
    * it must obtain a lock. After obtaining the lock, Shared.count is incremented and then, before releasing the lock,
    * the thread sleeps. This causes the second thread to attempt to obtain the lock. However, because the lock is
    * still held by the first thread, the second thread must wait until the first thread stops sleeping	and	releases
    * the lock.	The	output shows that access to Shared.count is, indeed, synchronized by the lock.
* */
    public static void exp1(){
        ReentrantLock lock=new ReentrantLock();

        new Thread(new LockThread("worker1",lock)).start();
        new Thread(new LockThread("worker2",lock)).start();
    }


    public static void exp2(){
        System.out.println("Program starting");
        ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
        Thread w1 = new Thread(new WriterEven("WriterEven", lock));
        Thread w2 = new Thread(new WriterOdd("WriterOdd", lock));
        Thread r1 = new Thread(new Reader("Reader1", lock));
        Thread r2=new Thread(new Reader("Reader2",lock));

        w1.start();
        w2.start();
        r1.start();
        r2.start();

        try {
            w1.join();
            w2.join();
            r1.join();
            r2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
