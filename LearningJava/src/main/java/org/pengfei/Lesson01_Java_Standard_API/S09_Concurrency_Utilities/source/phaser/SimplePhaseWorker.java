package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.phaser;

import java.util.concurrent.Phaser;

public class SimplePhaseWorker implements Runnable {
    private final String name;
    private final Phaser phaser;

    public SimplePhaseWorker(String name, Phaser phaser){
        this.name=name;
        this.phaser=phaser;
        phaser.register();
    }
    @Override
    public void run() {

        // as we don't use thread to control the number of phase anymore, so Thread does not know how many phase
        // it will do, so we use a loop to repeat the below action, which represent completion of one phase

        while (!phaser.isTerminated()) {
            //get phase number
            System.out.println("Starting " + name + " beginning phase " + phaser.getPhase());

            //finish phase and wait
            phaser.arriveAndAwaitAdvance();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
