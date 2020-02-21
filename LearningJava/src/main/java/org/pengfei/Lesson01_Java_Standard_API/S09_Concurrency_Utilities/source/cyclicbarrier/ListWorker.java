package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.cyclicbarrier;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ListWorker implements Runnable {
    private final String name;
    private final CyclicBarrier barrier;

    public ListWorker(String name, CyclicBarrier barrier){
        this.name=name;
        this.barrier=barrier;
    }
    @Override
    public void run() {
              System.out.println("Starting "+ name);
              //each worker add 0 to 4 values into the share list
              for(int i=1;i<4;i++){
                  SharedList.data.add(i);
              }

              //now wait for all workers to insert values
        try{
            int order=barrier.await();
            System.out.println(name+ " reached barrier at order: "+order);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        //when all worker reaches the barrier, print the list and sum
        System.out.println("List contents: "+ Arrays.toString(SharedList.data.toArray()));
        System.out.println("Sum: "+SharedList.sum);
        System.out.println("Terminating "+name);
    }
}
