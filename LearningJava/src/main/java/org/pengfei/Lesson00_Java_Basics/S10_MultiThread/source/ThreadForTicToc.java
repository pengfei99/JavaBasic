package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source;

public class ThreadForTicToc extends Thread {

    TickTock ttOb;

    private ThreadForTicToc(String name, TickTock tickTockOb){
        super(name);
        this.ttOb=tickTockOb;
    }

    public static ThreadForTicToc createAndStart(String name, TickTock tickTockOb){
        ThreadForTicToc thread=new ThreadForTicToc(name,tickTockOb);
        thread.start();
        return thread;
    }

    /** In one thread, we run 5 times tick or tock, depends on the name of the thread
     * It's the wait and nofity make sure that Tick tock print in patterns*/
    public void run(){
        String threadName=this.getName();
        if(threadName.equals("Tick")){
            for(int i=0; i<5;i++){
                ttOb.tick(true);
            }
            ttOb.tick(false);
        }
        if(threadName.equals("Tock")){
            for(int i=0; i<5;i++){
                ttOb.tock(true);
            }
            ttOb.tock(false);
        }


    }
}
