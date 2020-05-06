package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source.volatile_exp;

import java.util.Random;

public class ThreadForSharedDate extends Thread {
    private String threadName;
    private MySharedDate date;

    public ThreadForSharedDate(String threadName, MySharedDate date) {
        super(threadName);
        this.threadName = threadName;
        this.date = date;
    }

    public void run(){
        if(threadName.equals("reader")){
            for(int i=0;i<5;i++){
                System.out.println("Reader return total days: "+date.totalDays());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(threadName.equals("writer")){
            for(int i=0;i<5;i++){
                date.update(i,i,i);
                int totalDay=365*i+30*i+i;
                System.out.println("Writer add new days: "+totalDay);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
