package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.executor;

import java.util.concurrent.CountDownLatch;

public class MySimpleWorker implements Runnable  {
    private final String name;
    private final CountDownLatch latch;

    public MySimpleWorker(String name, CountDownLatch latch){
        this.name=name;
        this.latch=latch;
    }

    @Override
    public void run() {
           for(int i=0;i<3;i++){
               System.out.println(name+ ": "+i);
               latch.countDown();
           }
    }
}
