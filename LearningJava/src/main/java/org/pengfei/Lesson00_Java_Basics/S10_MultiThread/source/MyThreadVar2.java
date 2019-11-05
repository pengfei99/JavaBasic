package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source;

public class MyThreadVar2 extends Thread{

    private MyThreadVar2(String name){
        super(name);
    }

    public static MyThreadVar2 createAndRun(String name){
        MyThreadVar2 myVar2=new MyThreadVar2(name);
        myVar2.start();
        return myVar2;
    }

    public void run(){
        String threadName = this.getName();
        System.out.println(threadName + " starting.");
        try {
            for (int count = 0; count < 10; count++) {
                Thread.sleep(400);
                System.out.println("In "+threadName+", count is "+count);
            }
        } catch (InterruptedException e) {
            System.out.println(threadName+" interrupted");
        }
        System.out.println(threadName+" terminating");
    }
}
