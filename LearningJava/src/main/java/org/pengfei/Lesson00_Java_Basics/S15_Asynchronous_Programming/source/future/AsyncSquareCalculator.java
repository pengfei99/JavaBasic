package org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AsyncSquareCalculator {

    private ExecutorService es;

    public AsyncSquareCalculator(ExecutorService es) {
        this.es = es;
    }

    public AsyncSquareCalculator(){
        this.es= Executors.newSingleThreadExecutor();
    }

    //We use
    public Future<Integer> calculateSquare(long value,long timeCost){
        System.out.println("calculateSquare method is called");

        //We use lambda expression to write a anonymous callable
        Future<?> result = es.submit(() -> {
            try {

                Thread.sleep(timeCost);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Math.sqrt(value);
        });

        System.out.println("calculateSquare returns a Future");
        return (Future<Integer>) result;
    }

    public void shutdown(){
        this.es.shutdown();
    }
}
