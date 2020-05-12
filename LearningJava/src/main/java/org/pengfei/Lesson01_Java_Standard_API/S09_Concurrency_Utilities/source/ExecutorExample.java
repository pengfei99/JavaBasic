package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source;

import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.executor.Factorial;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.executor.Hypotenuse;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.executor.MySimpleWorker;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.executor.Sum;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.fork.SquareTransform;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.thread_pool.MyTasks;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.thread_pool.MyThreadFactory;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.thread_pool.MyThreadPool;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.thread_pool.ThreadForMyThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class ExecutorExample {

    public static void exp1(){
        CountDownLatch cdl1=new CountDownLatch(3);
        CountDownLatch cdl2=new CountDownLatch(3);
        CountDownLatch cdl3=new CountDownLatch(3);
        CountDownLatch cdl4=new CountDownLatch(3);

        // Get a executor of type FixedThreadPool which contains two thread
        ExecutorService es= Executors.newFixedThreadPool(2);

        /** The second static method takes an extra argument, ThreadFactory
        *  The custom ThreadFactory implementation can customize the thread creation. This version is useful when
         *  user wants to customize certain aspects of thread creation. For example, user would like to change the
         *  thread group, thread pool naming patterns, thread priorities and so on.
         *
         *  In the following example, we use a custom thread(it builds a thread name based on the id and thread group
         *  name which threadFactory provides) and a custom threadFactory (it builds thread with thread group, poolname,
         *  id, etc. and it checks the priority and isDaemon or not.)
         *
         * */

        ExecutorService es1=Executors.newFixedThreadPool(2,new MyThreadFactory("thread_Test"));

        System.out.println("Starting ...");

        //Use executor to run the workers
        es.execute(new MySimpleWorker("worker1",cdl1));
        es.execute(new MySimpleWorker("worker2",cdl2));
        es.execute(new MySimpleWorker("worker3",cdl3));
        es.execute(new MySimpleWorker("worker4",cdl4));

        try{
            // wait the above four latch to be released to continue the program
            cdl1.await();
            cdl2.await();
            cdl3.await();
            cdl4.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Without it the thread pool is active forever, and program never stops
        es.shutdown();
        System.out.println("Terminating ...");

    }

    /*In this example, we use a fixed thread pool to run three callable, each callable returns its result as Future*/
    public static void exp2(){
        ExecutorService es=Executors.newFixedThreadPool(3);
        Future<Integer> f1;
        Future<Double> f2;
        Future<Integer> f3;
        System.out.println("Starting...");

        //unlike runnable are called by execute(), callable are called by submit()
        f1=es.submit(new Sum(10));
        f2=es.submit(new Hypotenuse(3,4));
        f3=es.submit(new Factorial(5));

        // because before get() gets its value, it will block the program from continuing.
        // so the second print will not run until first get its value.
        try{
            System.out.println("Sum returns: "+f1.get());
            System.out.println("Hypotenuse returns: "+f2.get());
            System.out.println("Factorial returns: "+f3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        es.shutdown();
        System.out.println("Done");

    }

    /*In this example, we use a fixed thread pool to run three callable, each callable returns its result as Future*/
    public static void exp3(){
        ExecutorService es=Executors.newFixedThreadPool(3);
        Future<Integer> f1;
        Future<Double> f2;
        Future<Integer> f3;
        System.out.println("Starting...");

        //unlike runnable are called by execute(), callable are called by submit()
        f1=es.submit(new Sum(10));
        f2=es.submit(new Hypotenuse(3,4));
        f3=es.submit(new Factorial(5));

        // because before get() gets its value, it will block the program from continuing.
        // so the second print will not run until first get its value.
        try{
            System.out.println("Sum returns: "+f1.get(10, MILLISECONDS));
            System.out.println("Hypotenuse returns: "+f2.get(10, MILLISECONDS));
            System.out.println("Factorial returns: "+f3.get(10, MILLISECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        es.shutdown();
        System.out.println("Done");

    }

    /* singleThreadExecutor*/
    public static void exp4(){

        //We can also use executor directly instead of executor service. Because Executor is the father interface
        // of the ExecutorService interface. But it does not have shutdown() to close the executor pool properly.
       // Executor executor=Executors.newSingleThreadExecutor();
        ExecutorService es=Executors.newSingleThreadExecutor();
       // ExecutorService es=Executors.newFixedThreadPool(1);


        MyTasks t1=new MyTasks("t1");
        MyTasks t2=new MyTasks("t2");
        MyTasks t3=new MyTasks("t3");

       /* executor.execute(t1);
        executor.execute(t2);
        executor.execute(t3);*/


        es.execute(t1);
        es.execute(t2);
        es.execute(t3);
        es.shutdown();

    }

    /*cachedThreadPool*/
    public static void exp5(){
        ExecutorService es=Executors.newCachedThreadPool();

        //submit 10 tasks to the thread pool
        for(int i=0;i<10;i++){
            es.execute(new MyTasks("t"+i));

            //note the casting of the ExectuorService to a ThreadPoolExecutor is not guaranteed all implementations of Java.
            // Because it returned by newCachedThreadPoo. In my java 11 running env, it failed. so I cant see the
            // current pool size
            ThreadPoolExecutor tpe= (ThreadPoolExecutor) es;
            System.out.println("current pool size: "+tpe.getPoolSize());

        }

        es.shutdown();
    }

    /* self implementation of a thread Pool*/
    public static void exp6(){
        //
        MyThreadPool threadPool=new MyThreadPool(1,20);
        //submit 10 tasks to the thread pool
        for(int i=0;i<10;i++){
            try {
                threadPool.execute(new MyTasks("t"+i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // We can't shut down the pool, because the unfinished tasks will be interrupted.
       // threadPool.shutDown();
    }

    /* workStealingThreadPool*/
    public static void exp7(){
        ExecutorService es = Executors.newWorkStealingPool();
        ForkJoinPool pool=(ForkJoinPool) es;

        /*run a runnable, no need to use WorkStealingPool*/
        es.submit(new MyTasks("t1"));
        es.submit(new MyTasks("t2"));

        /* run a forkJoinTask*/
        // create array data set for transform
        double[] data=new double[1000];
        for(int i=0;i<data.length;i++) data[i]=(double) i;

        System.out.println("The first 10 element of the array: ");
        for(int i=0;i<10;i++)
            System.out.print(data[i]+" ");

        //create the main task
        SquareTransform task=new SquareTransform(data,0,data.length);
         pool.invoke(task);

        System.out.println("The first 10 element of the array after transformation: ");
        for(int i=0;i<10;i++)
            System.out.printf("%.4f ", data[i]);
    }

    /* scheduledThreadPool*/
    public static void exp8(){
        ScheduledExecutorService ses=Executors.newScheduledThreadPool(3);

        //task 1 only a 10 sec delay
        ses.schedule(new MyTasks("t1"),10,TimeUnit.SECONDS);

        //task 2 repeat every 15 sec after a init delay of 5 sec
        ses.scheduleAtFixedRate(new MyTasks("t2"),5,15,TimeUnit.SECONDS);

        //task 3 repeat after the previous task finished with 30 sec, the first task has 10 sec initial delay
        ses.scheduleWithFixedDelay(new MyTasks("t3"),10,30,TimeUnit.SECONDS);

        //As we run the task repeatly, so we can't shutdown the pool, if we shutdown, all tasks ends with the threadPool
       // ses.shutdown();
    }

    /* a custom build thread pool by using threadPoolExecutor constructor*/
    public static void exp9(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0, MILLISECONDS,
                new ArrayBlockingQueue<>(2),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        executor.execute(() -> waitFor(100));

        BlockingQueue<String> queue = new LinkedBlockingDeque<>();
        // we have three tasks to put a string in a blocking queue, as we have only 1 thread, they will be put in
        // task waiting queue, and waiting queue size is 2, we reached the saturation when we submit the third task
        // the discardOldestPolicy removes the oldest task.
        executor.execute(() -> queue.offer("First"));
        executor.execute(() -> queue.offer("Second"));
        executor.execute(() -> queue.offer("Third"));
        waitFor(150);

        List<String> results = new ArrayList<>();
        queue.drainTo(results);

        System.out.println(results);
        executor.shutdown();
    }

    private static void waitFor(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
