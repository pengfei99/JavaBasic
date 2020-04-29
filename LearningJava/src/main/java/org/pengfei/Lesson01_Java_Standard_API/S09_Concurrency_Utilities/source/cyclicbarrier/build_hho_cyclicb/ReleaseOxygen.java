package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.cyclicbarrier.build_hho_cyclicb;

public class ReleaseOxygen extends Thread {

    public ReleaseOxygen(String threadName){
        super(threadName);
    }

    public void run(){
        System.out.println("O");
    }
}
