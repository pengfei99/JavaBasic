package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source;

public class ThreadSuspend implements Runnable {
    private Thread thread;

    //thread state variable for suspend
    private boolean suspended;
    private boolean stopped;

    private ThreadSuspend(String name){
        // create a Thread object which take the ThreadSuspend obj as argument
        thread=new Thread(this,name);
        suspended=false;
        stopped=false;
    }

    public static ThreadSuspend createAndStart(String name){
        ThreadSuspend ts=new ThreadSuspend(name);
        ts.thread.start();
        return ts;
    }

    //Entry point of thread
    @Override
    public void run() {
        String threadName=thread.getName();
        System.out.println(threadName+" starting");
        try{
            // Business logic
            for(int i=1;i<1000;i++){
                System.out.print(i + " ");
                if((i%10)==0){
                    System.out.println();
                    Thread.sleep(250);
                }

                // Use synchronized block to check suspended and stopped state of the thread
                // This block will be checked during each iteration.
                synchronized (this){
                    // if thread state is suspended, thread wait to be notified.
                    // use while loop to avoid spurious-wakeup, even notify once, if the state is still suspended,
                    // it will reenter into the loop, and wait again.
                    while (suspended){
                        wait();
                    }

                    // if thread state is stopped, stop the thread
                    if(stopped) break;
                }
            }
        } catch (InterruptedException e) {
            System.out.println(threadName+ " interrupted");
        }
    }

    // method to stop the thread, it must be synchronized, because state of this thread is a unique resource
    // if many process try to modify it in the same time, it will cause error, with synchronized method, only one
    // process can modify it in the same time
    public synchronized void stopThread(){
        stopped =true;

        // make sure a suspended thread can be stopped too. Without this, a thread can be in state suspend and never
        // stops.
        suspended=false;
        notify();
    }

    // suspend the thread
   public synchronized void suspendThread(){
        suspended=true;
   }

   // resume the thread
   public synchronized void resumeThread(){
        suspended=false;
        notify();
   }

   public void join() throws InterruptedException {
        this.thread.join();
   }
}
