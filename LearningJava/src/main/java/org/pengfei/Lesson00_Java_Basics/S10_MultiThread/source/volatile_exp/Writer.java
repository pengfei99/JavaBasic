package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source.volatile_exp;

public class Writer implements Runnable {
    private String threadName;

    public Writer(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {

        for(int i=0;i<10;i++){
            System.out.println("Before writer " +threadName+" increment shared object, count value: "+SharedObject.count);
            SharedObject.count++;
            System.out.println("After writer " +threadName+" increment shared object, count value: "+SharedObject.count);

            // The sleep will give enough time for volatile to work well, even volatile is not enough to guarantee
            // for MultiThreadExp.exp16(), you need to comment the sleep to see the volatile fails.
           /* try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }

    }
}
