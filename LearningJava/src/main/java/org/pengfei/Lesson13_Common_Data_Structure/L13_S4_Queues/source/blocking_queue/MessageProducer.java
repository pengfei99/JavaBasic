package org.pengfei.Lesson13_Common_Data_Structure.L13_S4_Queues.source.blocking_queue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class MessageProducer implements Runnable {

    private String threadName;

    private BlockingQueue messageQueue;

    private Object payLoad=null;

    public MessageProducer(String threadName, BlockingQueue messageQueue) {
        this.threadName=threadName;
        this.messageQueue = messageQueue;
    }

    public MessageProducer(String threadName, BlockingQueue messageQueue, Object payLoad) {
        this.threadName = threadName;
        this.messageQueue = messageQueue;
        this.payLoad = payLoad;
    }

    @Override
    public void run() {
        if(payLoad==null){
        String message=threadName+" adds"+new Random().nextInt(10);
        try {
            messageQueue.put(message);
            System.out.println("Put message: "+message);
        } catch (InterruptedException e) {
            System.out.println("Producer interrupted by the threadPool shutdown: ");
        }}
        else {
            try {
                messageQueue.put(payLoad);
            } catch (InterruptedException e) {
                System.out.println("Producer interrupted by the threadPool shutdown: ");
            }
        }
    }
}
