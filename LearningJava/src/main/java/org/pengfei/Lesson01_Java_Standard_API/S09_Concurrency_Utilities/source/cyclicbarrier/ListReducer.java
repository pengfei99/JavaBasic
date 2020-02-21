package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

//When all workers finish adding elements into the list, the reducer calculates the sum of the list
public class ListReducer implements Runnable {
    private final String name;
    public ListReducer(String name){
        this.name=name;
    }
    @Override
    public void run() {
        System.out.println("Stating "+name);
        int result=0;
         for(int i: SharedList.data){
             result+=i;
         }
         SharedList.sum=result;
         System.out.println("Sum: "+SharedList.sum);
        System.out.println("Terminating "+name);
    }
}
