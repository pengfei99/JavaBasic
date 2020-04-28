package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source;

public class ThreadForTicToc extends Thread {

    TickTock ttObj;

    private ThreadForTicToc(String name, TickTock tickTockObj){
        super(name);
        this.ttObj=tickTockObj;
    }

    public static ThreadForTicToc createAndStart(String name, TickTock tickTockOb){
        ThreadForTicToc thread=new ThreadForTicToc(name,tickTockOb);
        thread.start();
        return thread;
    }

    /** In one thread, we run 5 times tick or tock, depends on the name of the thread
     * It's the wait and notify make sure that Tick tock print in patterns*/
    public void run(){
        String threadName=this.getName();
        if(threadName.equals("Tick")){
            for(int i=0; i<5;i++){
                ttObj.tick(true);
            }
            ttObj.tick(false);
        }
        if(threadName.equals("Tock")){
            for(int i=0; i<5;i++){
                ttObj.tock(true);
            }
            ttObj.tock(false);
        }


    }
}
