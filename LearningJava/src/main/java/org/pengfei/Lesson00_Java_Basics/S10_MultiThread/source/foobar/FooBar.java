package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source.foobar;

public class FooBar {
    //if flag is true, all threads who call foo should wait
    //if flag is false, all threads who call bar should wait
    private boolean flag=false;
    private int maxNum;

    public FooBar(int maxNum){
        this.maxNum=maxNum;
    }

    public synchronized void foo() throws InterruptedException{
        for (int i = 0; i < maxNum; i++) {

            //
            while (flag){
                wait();
            }

            System.out.println("Foo");
            // after print foo, change flag to true
            flag=true;

            notifyAll();
        }
    }

    public synchronized void bar() throws InterruptedException{
        for (int i = 0; i < maxNum; i++) {
            while (!flag){
                wait();
            }
            System.out.println("Bar");
            // after print bar, change flag to false
            flag=false;

            notifyAll();
        }
    }
}
