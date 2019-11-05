package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source;

public class ThreadSynchronizedMethod extends Thread {

    // all threads of this class share the same SumArray object
    private final static SumArray sa= new SumArray();
    int nums[];
    int answer;

    // Thread constructor takes a thread name and a list of int
    private ThreadSynchronizedMethod(String name, int nums[]){
        super(name);
        this.nums=nums;
    }

    //factory method
    public static ThreadSynchronizedMethod createAndRun(String name, int nums[]){
        ThreadSynchronizedMethod thread=new ThreadSynchronizedMethod(name,nums);
        thread.start();
        return thread;
    }

    // Entry point of thread
    public void run(){
        final String threadName=this.getName();

        System.out.println(threadName+" starting.");
        answer=sa.sumArray(nums);
        System.out.println("Sum for "+threadName+ " is "+answer);
        System.out.println(threadName+" terminating.");
    }
}
