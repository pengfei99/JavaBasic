package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source;

import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.executor.Factorial;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.executor.Hypotenuse;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.executor.MySimpleWorker;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.executor.Sum;
import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.thread_pool.MyTasks;

import java.util.concurrent.*;

public class ExecutorExample {

    public static void exp1(){
        CountDownLatch cdl1=new CountDownLatch(3);
        CountDownLatch cdl2=new CountDownLatch(3);
        CountDownLatch cdl3=new CountDownLatch(3);
        CountDownLatch cdl4=new CountDownLatch(3);

        // Get a executor of type FixedThreadPool which contains two thread
        ExecutorService es= Executors.newFixedThreadPool(2);

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
            System.out.println("Sum returns: "+f1.get(10, TimeUnit.MILLISECONDS));
            System.out.println("Hypotenuse returns: "+f2.get(10, TimeUnit.MILLISECONDS));
            System.out.println("Factorial returns: "+f3.get(10, TimeUnit.MILLISECONDS));
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

    /* fork/join Pool*/
    public static void exp6(){}
    public static void exp7(){}

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
}
