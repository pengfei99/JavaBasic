package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source;

public class ThreadWithPriority extends Thread{
    private int count;

    // all thread of this class will share this value
    private static boolean stop=false;

    private ThreadWithPriority(String name, int priority){
        super(name);
        this.setPriority(priority);
    }

    public static ThreadWithPriority createAndRun(String name, int priority){
        ThreadWithPriority thread=new ThreadWithPriority(name,priority);
        thread.start();
        return thread;
    }

    public void run(){
        String threadName = this.getName();
        System.out.println(threadName+" starting ");
        // the do while loop will stop while the first thread arrives 1000, then it will end all other thread
        do{
            count ++;
            System.out.println("In "+threadName+ " counting "+ count);
        }while (stop==false && count < 1000);
        stop=true;

        System.out.println(threadName+ " terminating ");
    }

    public int getCount() {
        return count;
    }
}
