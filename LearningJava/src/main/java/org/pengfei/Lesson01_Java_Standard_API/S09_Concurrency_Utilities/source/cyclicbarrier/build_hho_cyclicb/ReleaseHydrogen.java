package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.cyclicbarrier.build_hho_cyclicb;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ReleaseHydrogen extends Thread {
  private CyclicBarrier cyclicBarrier;
    public ReleaseHydrogen(String threadName, CyclicBarrier cyclicBarrier){
        super(threadName);
        this.cyclicBarrier=cyclicBarrier;
    }

    public void run(){
        System.out.println("Number of parties waiting at the barrier "+
                "at this point = " + cyclicBarrier.getNumberWaiting());
        System.out.println("H");
        try {
            //If we don't add time out, we may enter deadlock. For example, only one thread of h arrives, this thread
            //is blocked
            cyclicBarrier.await(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
