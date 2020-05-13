package org.pengfei.Lesson13_Common_Data_Structure.L13_S4_Queues.source.blocking_queue;

import java.util.concurrent.BlockingQueue;

public class MessageConsumer implements Runnable {
    private String threadName;
    private BlockingQueue messageQueue;

    public MessageConsumer(String threadName, BlockingQueue messageQueue) {
        this.threadName = threadName;
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        try {
            String message=messageQueue.take().toString();
            System.out.println("Get message: "+message);
        } catch (InterruptedException e) {
            System.out.println("Consumer interrupted by the threadPool shutdown: ");
        }
    }
}
