package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source;

public class ThreadSynchronizedBlock extends Thread {
    private final static SumArrayWithoutSynchronizedMethod sa = new SumArrayWithoutSynchronizedMethod();
    int sums[];
    int answer;

    private ThreadSynchronizedBlock(String name,int sums[]){
        super(name);
        this.sums=sums;
    }

    public static ThreadSynchronizedBlock createAndStart(String name,int nums[]){
        ThreadSynchronizedBlock thread=new ThreadSynchronizedBlock(name,nums);
        thread.start();
        return thread;
    }

    public void run(){
        var threadName=this.getName();
        System.out.println(threadName+" starting.");

        // synchronize calls to sumArray(), which means all thread of this class will be synchronized on sa object
        synchronized (sa){
            answer=sa.sumArray(sums);
        }
        System.out.println("Sum for "+threadName+ " is "+answer);
        System.out.println(threadName+" terminating.");
    }
}
