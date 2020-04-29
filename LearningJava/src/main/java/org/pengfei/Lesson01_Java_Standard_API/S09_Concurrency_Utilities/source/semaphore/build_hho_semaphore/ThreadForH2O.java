package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.semaphore.build_hho_semaphore;

public class ThreadForH2O extends Thread {
    private String threadName;
    private H2O h2o;

    public ThreadForH2O(String threadName,H2O h2o) {
        super(threadName);
        this.threadName = threadName;
        this.h2o=h2o;
    }

    public void run(){
        if(threadName.equals("h")){
            try {
                h2o.hydrogen();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if(threadName.equals("o")){
            try {
                h2o.oxygen();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else throw new IllegalArgumentException("wrong thread name");
    }
}
