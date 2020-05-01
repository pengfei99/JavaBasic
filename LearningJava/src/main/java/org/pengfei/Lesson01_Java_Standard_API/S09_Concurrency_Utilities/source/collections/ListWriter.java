package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.collections;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ListWriter implements Runnable {
    private final String name;
    private final List<Integer> list;
    private final CountDownLatch countDownLatch;
    public ListWriter(String name, List<Integer> list, CountDownLatch countDownLatch){
        this.name=name;
        this.list=list;
        this.countDownLatch=countDownLatch;
    }
    @Override
    public void run() {
           for(int i=0;i<5;i++) {
               System.out.println(name+" adding "+i);
               list.add(i);
               try {
                   Thread.sleep(100);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        countDownLatch.countDown();
    }
}
