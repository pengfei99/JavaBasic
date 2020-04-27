package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.excercise;

public class MyThreadWithThreadObj implements Runnable{
    private String threadName;

    private MyThreadWithThreadObj(String threadName){
        this.threadName=threadName;
    }

    @Override
    public void run() {

    }

    public static void createAndStart(String threadName){

        MyThreadWithThreadObj myThreadObj=new MyThreadWithThreadObj(threadName);
        Thread thread=new Thread(myThreadObj);
        thread.start();
    }


}
