package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source;

import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.fork.*;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinExample {

    public static void exp1(){
    // create a task pool.
        ForkJoinPool pool=new ForkJoinPool();

        // we can get the reference of commonPool with the following code, if we don't want to create a ForkJoinPool
        //ForkJoinPool commonPool=ForkJoinPool.commonPool();

        // create array data set for transform
        double[] data=new double[1000];
        for(int i=0;i<data.length;i++) data[i]=(double) i;

        System.out.println("The first 10 element of the array: ");
        for(int i=0;i<10;i++)
        System.out.print(data[i]+" ");

        //create the main task, and
        SquareTransform task=new SquareTransform(data,0,data.length);
        // start the main task with ForkJoinPool
        pool.invoke(task);

        System.out.println("The first 10 element of the array after transformation: ");
        for(int i=0;i<10;i++)
            System.out.printf("%.4f ", data[i]);
    }

    public static void exp2(){
        // create array data set for transform.
        // ps: if the size of array is bigger than 100000, all elements in array will be assigned 1.0. bug in
        //     the array implementation?
        double[] data=new double[10];
        for(int i=0;i<data.length;i++) data[i]=(double)i;

        System.out.println("The first 10 element of the array: ");
        for(int i=0;i<10;i++)
            System.out.print(data[i]+" ");

        //create the main task, and
        SquareTransform task=new SquareTransform(data,0,data.length);
        // start the main task by calling the invoke() method of the ForkJoinTask class, this will start the common
        // pool mechanism and run the task with the common pool
        task.invoke();

        System.out.println("The first 10 element of the array after transformation: ");
        for(int i=0;i<10;i++)
            System.out.printf("%.4f ", data[i]);
    }

    public static void exp3(int parallelismLevel,int threshold){

        long startTime, endTime;

        // create a task pool with a parallelism level
        ForkJoinPool pool=new ForkJoinPool(parallelismLevel);

        double[] data=new double[10000000];
        for(int i=0;i<data.length;i++) data[i]=(double)i;

        System.out.println("The first 10 element of the array: ");
        for(int i=0;i<10;i++)
            System.out.print(data[i]+" ");

        //create the main task
        FlexibleThresholdTransform task=new FlexibleThresholdTransform(data,threshold,0,data.length);

        // get the start time of the task
        startTime=System.nanoTime();

        // run the main task
        pool.invoke(task);

        // get the end time of the task
        endTime=System.nanoTime();

        System.out.println("Level of parallelism: "+parallelismLevel);
        System.out.println("Sequential threshold: "+threshold);
        System.out.println("Elapsed time in nanoseconds: "+(endTime-startTime));
        System.out.println();

    }

    public static void exp4(int parallelismLevel){
        ForkJoinPool pool;
        if (parallelismLevel==0){
            pool=new ForkJoinPool();
        }
        else pool=new ForkJoinPool(parallelismLevel);

        // get parallelism level of current pool, if its a common pool, you can use ForkJoinPool.getCommonPoolParallelism();
        System.out.println("Level of parallelism: "+pool.getParallelism());

        // get the processor number of current system.
        System.out.println("Available processor: "+Runtime.getRuntime().availableProcessors());


    }

    public static void exp5(){
        int threshold=1000;

        ForkJoinPool pool=new ForkJoinPool();
        double[] data=new double[6000];
        for(int i=0;i<data.length;i++) data[i]=(double)i;

        System.out.println("The first 10 element of the array: ");
        for(int i=0;i<10;i++)
            System.out.print(data[i]+" ");

        // create sum task
        SumCalculator task=new SumCalculator(data,threshold,0,data.length);

        // start the task by calling invoke, which returns a result.
        double result=pool.invoke(task);

        System.out.print("Summation: "+result);

    }

    public static void exp6(){
        int threshold=1000;

        ForkJoinPool pool=new ForkJoinPool();
        double[] data=new double[6000];
        for(int i=0;i<data.length;i++) data[i]=(double)i;

        System.out.println("The first 10 element of the array: ");
        for(int i=0;i<10;i++)
            System.out.print(data[i]+" ");

        // create sum task
        SumCalculatorBis task=new SumCalculatorBis(data,threshold,0,data.length);

        // start the task by calling invoke, which returns a result.
        double result=pool.invoke(task);

        System.out.print("Summation: "+result);

    }

    public static void exp7(){
        int threshold=100;

        ForkJoinPool pool=new ForkJoinPool();
        double[] data=new double[600];
        for(int i=0;i<data.length;i++) data[i]=(double)i;

        System.out.println("The first 10 element of the array: ");
        for(int i=0;i<10;i++)
            System.out.print(data[i]+" ");
        System.out.println();

        // create sum task
        SumCalculatorSleep task=new SumCalculatorSleep(data,threshold,0,data.length);

        // start the task by calling execute, so the main thread is not blocked by the task
        pool.execute(task);

        // get fork join pool state
        while(!task.isDone()){
            System.out.println("Pool state: "+pool);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        double result=task.join();

        System.out.print("Summation: "+result);

    }
}
