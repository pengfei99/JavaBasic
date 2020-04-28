package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source.zero_even_odd;


public class ThreadForZeroEvenOdd extends Thread {

    private String threadName;

    // A static Foobar object which is shared by all instance of ThreadForFooBar
    private static ZeroEvenOdd zeo = new ZeroEvenOdd(1);

    public ThreadForZeroEvenOdd(String threadName) {
        super(threadName);
        this.threadName = threadName;
    }

    @Override
    public void run() {
       // System.out.println("Thread: "+threadName+" starts");
        if (threadName.equals("zero")) {
            try {
                zeo.zero();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if(threadName.equals("even")) {
            try {
                zeo.even();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if(threadName.equals("odd")) {
            try {
                zeo.odd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else throw new IllegalArgumentException("Wrong thread name");
      //  System.out.println("Thread: "+threadName+" ends");
    }
}
