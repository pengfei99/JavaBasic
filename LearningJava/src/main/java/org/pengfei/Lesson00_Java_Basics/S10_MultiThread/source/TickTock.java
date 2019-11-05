package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source;

public class TickTock {
    // shared resource
    String state;

    synchronized void tick(boolean running){
        // clock stopped
        if(!running){
            state="ticked";
            // notify any waiting threads
            notify();
            return;
        }
        System.out.print("Tick ");
        state="ticked";
        //tick notify tock
        notify();
        try{
            while (!state.equals("tocked"))
                // tick waits for tock
                wait();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
    }

    synchronized void tock(boolean running){

        if(!running){
            state ="tocked";
            notify();
            return;
        }
        System.out.println("Tock ");
        state="tocked";
        //tock notify tick
        notify();
        try{
            while (!state.equals("ticked"))
                // tock waits for tick
                wait();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
    }


}
