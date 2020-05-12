package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.thread_pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class MyThreadPool {

    // A thread pool has a queue to store the submitted tasks
   private BlockingQueue<Runnable> taskQueue;
    //A thread pool has a list of thread
   private List<ThreadForMyThreadPool> threadList;

   private boolean stopped=false;

    public MyThreadPool(int coreThreadNum, int maxTaskNum){

        //create list of thread
        threadList=new ArrayList<>(coreThreadNum);

        // create task queue with maxTaskNum, unlike the FixedPoolExecutor uses a LinkedBlockingQueue(has no size limit)
        this.taskQueue=new ArrayBlockingQueue<Runnable>(maxTaskNum);

        //populate the thread list
        for(int i=0;i<coreThreadNum;i++){
            threadList.add(new ThreadForMyThreadPool(taskQueue));
        }

        //start all thread
        for(ThreadForMyThreadPool thread:threadList){
            thread.start();
        }
    }

    public synchronized void  execute(Runnable task) throws Exception{
        if(this.stopped) throw
                new IllegalStateException("ThreadPool is stopped");
        if(taskQueue==null) {
            stopped=true;
            System.out.println("Task queue is null, stop thread Pool");
        }
        this.taskQueue.put(task);
    }

    public synchronized void shutDown(){
        this.stopped = true;
        for(ThreadForMyThreadPool thread : threadList){
            thread.stopThread();
        }
    }
}
