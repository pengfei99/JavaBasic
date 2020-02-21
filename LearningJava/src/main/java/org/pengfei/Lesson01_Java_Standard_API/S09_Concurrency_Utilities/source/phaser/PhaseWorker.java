package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.phaser;

import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.exchanger.StringReader;

import java.util.concurrent.Phaser;

public class PhaseWorker implements Runnable {
    private final String name;
    private final Phaser phaser;

    public PhaseWorker(String name, Phaser phaser) {
        this.name = name;
        this.phaser = phaser;
        //register this thread
        phaser.register();

    }

    /** Note, we specify the number of phase in the thread. We could say each time the thread calls arrive() is a
     * phase. In this example, we called 4 times arrive(), then we have 4 phase. If we call 3 times, we will have
     * 3 phase.*/
    @Override
    public void run() {
       System.out.println("Thread "+name+" Beginning Phase one");
       //Signal arrival and wait for other parties
       phaser.arriveAndAwaitAdvance();

       // Pause a bit, for friendly print out only, no need in real world case
        try{
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread "+name+" Beginning Phase two");
        phaser.arriveAndAwaitAdvance();

        try{
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread "+name+" Beginning Phase three");
        phaser.arriveAndAwaitAdvance();

        try{
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // In the last phase, we must deregister the thread
        System.out.println("Thread "+name+" Beginning Phase four");
        phaser.arriveAndDeregister();
    }
}
