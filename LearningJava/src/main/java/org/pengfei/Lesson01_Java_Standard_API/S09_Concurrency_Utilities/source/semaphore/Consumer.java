package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.semaphore;

public class Consumer implements Runnable {

    private final SynchronizedQueue queue;
    public Consumer(SynchronizedQueue queue){
        this.queue=queue;
    }
    @Override
    public void run() {
        for(int i=0;i<20;i++) queue.get();
    }
}
