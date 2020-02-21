package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.coutdownlatch;

import java.util.concurrent.CountDownLatch;

public class SystemHealthChecker implements Runnable {
    private final String name;
    private final CountDownLatch latch;
    private boolean healthSig;
    public SystemHealthChecker(String name, boolean signal, CountDownLatch latch){

        this.name=name;
        healthSig=signal;
        this.latch=latch;
    }
    @Override
    public void run() {
            System.out.println("System checker: "+name+" check starting");
            while (true){
            if(healthSig) {
                System.out.println(name+ " check is successful");
                latch.countDown();
                break;
            }
            else {
                System.out.println(name+ " check is failed. Retrying");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            }
    }

    public void setHealthSig(boolean healthSig) {
        this.healthSig = healthSig;
    }
}
