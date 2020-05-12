package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.thread_pool;

import java.util.concurrent.BlockingQueue;

public class ThreadForMyThreadPool extends Thread{
    //blocking queue is thread safe
    private final BlockingQueue<Runnable> taskQueue;
    private boolean stopped=false;

    public ThreadForMyThreadPool(BlockingQueue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void run(){
        //when thread is not stopped, take a task from the queue and run it
        while(!stopped){
            //get the first task of the queue (FIFO)
            Runnable task = null;
            try {
                // must use take() method to get and remove element from element, because other
                task = taskQueue.take();
            } catch (InterruptedException e) {
             stopped=true;
             System.out.println("Thread pool shuts down, thread shuts down");
            }
            // run the task
            task.run();

        }
    }

    public synchronized void stopThread(){
        // stop thread run loop
        stopped=true;
        // kill the thread
        this.interrupt();
    }

    // This is also need to be synchronized, imagine when a thread check this thread is alive or not, other threads try
    // to shut it down.
    public synchronized boolean isStopped(){
        return stopped;
    }
}
