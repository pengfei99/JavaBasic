package org.pengfei.Lesson13_Common_Data_Structure.L13_S4_Queues.source.blocking_queue;

import java.util.concurrent.*;

public class BlockingQueueExample {

    //Array based blocking queue
    public static void exp1(){

        // this array blocking queue has 50 capacity
        // BlockingQueue<String> messageQueue=new ArrayBlockingQueue<>(50);

        // Linked blocking queue
        BlockingQueue<String> messageQueue=new LinkedBlockingQueue<>();

        //create a thread pool with 2 producer and 1 consumer
        ScheduledExecutorService es= Executors.newScheduledThreadPool(3);

        //two producer which repeats each 2 second. first has 1 sec initial delay. second has 2 sec.
        es.scheduleAtFixedRate(new MessageProducer("p1",messageQueue),1,2, TimeUnit.SECONDS);
        es.scheduleAtFixedRate(new MessageProducer("p2",messageQueue),2,2, TimeUnit.SECONDS);

        // one consumer which repeats after 1 second it finish its previous job. It has 3 sec initial delay
        es.scheduleWithFixedDelay(new MessageConsumer("c1",messageQueue),3,1, TimeUnit.SECONDS);

        // Close the pool after 10 sec
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        es.shutdownNow();
    }

    /* PriorityQueue */
    public static void exp2(){
        PriorityBlockingQueue queue=new PriorityBlockingQueue();

        //create a thread pool with 2 producer and 1 consumer
        ScheduledExecutorService es= Executors.newScheduledThreadPool(3);

        // first insert p1,p2,p3
        MessageProducer p1 = new MessageProducer("p1", queue, new PriorityQueueElement("p1", 1));
        es.schedule(p1,0, TimeUnit.SECONDS);

        MessageProducer p2 = new MessageProducer("p2", queue, new PriorityQueueElement("p2", 2));
        es.schedule(p2,0, TimeUnit.SECONDS);

        MessageProducer p3 = new MessageProducer("p3", queue, new PriorityQueueElement("p3", 3));
        es.schedule(p3,0, TimeUnit.SECONDS);

        // second insert p4, p5, p6

        MessageProducer p4 = new MessageProducer("p4", queue, new PriorityQueueElement("p4", 8));
        es.schedule(p4,1, TimeUnit.SECONDS);

        MessageProducer p5 = new MessageProducer("p5", queue, new PriorityQueueElement("p5", 7));
        es.schedule(p5,1, TimeUnit.SECONDS);

        MessageProducer p6 = new MessageProducer("p6", queue, new PriorityQueueElement("p6", 0));
        es.schedule(p6,1, TimeUnit.SECONDS);

        // one consumer which repeats after 1 second it finish its previous job. It has 3 sec initial delay
        es.scheduleWithFixedDelay(new MessageConsumer("c1",queue),0,1, TimeUnit.SECONDS);

        /* With 3 sec as initial delay, the output with priority is 8,7,..,0. Because all elements have been inserted
        * before consumer thread starts. Try to change initial delay to 0, and check the result. */
        // Close the pool after 10 sec
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        es.shutdownNow();
    }

    /* Delayed queue*/
    public static void exp3(){


        DelayQueue<DelayedQueueElement> dq=new DelayQueue<>();
        ScheduledExecutorService es=new ScheduledThreadPoolExecutor(1);

        // add elements to the queue
        for(int i=1;i<6;i++){
            dq.add(new DelayedQueueElement("e"+String.valueOf(i),5*i));
        }

       // add a special element with 0 delay, it should be consumed first
       dq.add(new DelayedQueueElement("e6",0));
        // run a consumer which repeats 1 sec after it finishes. with initial 1 sec delay
        es.scheduleWithFixedDelay(new MessageConsumer("c1",dq),1,1,TimeUnit.SECONDS);

        // Close the pool after 10 sec
        try {
            Thread.sleep(10000);
            //Use shutdown may not work, because shutdown wait the last submitted task to finish. As the consumer is
            //blocked by the blocking queue, so it never finishes.
            //When use shutdownNow(), you receive InterruptedException, because the blocked consumer is interrupted
            //by the shutdownNow
            es.shutdownNow();
        } catch (InterruptedException e) {
            System.out.println("Main Thread Sleep interrupted");
        }
        es.shutdown();

    }

    /*synchronous queue*/
public static void exp4(){

    SynchronousQueue messageRDVPoint=new SynchronousQueue();
    //create a thread pool with 2 producer and 1 consumer
    ScheduledExecutorService es= Executors.newScheduledThreadPool(3);

    //two producer which repeats each 2 second. first has 1 sec initial delay. second has 2 sec.
    es.scheduleAtFixedRate(new MessageProducer("p1",messageRDVPoint),1,2, TimeUnit.SECONDS);
    es.scheduleAtFixedRate(new MessageProducer("p2",messageRDVPoint),2,2, TimeUnit.SECONDS);

    // one consumer which repeats after 1 second it finish its previous job. It has 3 sec initial delay
    es.scheduleWithFixedDelay(new MessageConsumer("c1",messageRDVPoint),3,1, TimeUnit.SECONDS);

    // Close the pool after 10 sec
    try {
        Thread.sleep(10000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    es.shutdownNow();
}
}
