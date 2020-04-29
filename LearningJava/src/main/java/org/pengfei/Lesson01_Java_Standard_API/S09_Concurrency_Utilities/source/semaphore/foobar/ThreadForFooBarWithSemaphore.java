package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.semaphore.foobar;


public class ThreadForFooBarWithSemaphore extends Thread {
    private String threadName;
    private FooBarWithSemaphore foobar;
    public ThreadForFooBarWithSemaphore(String threadName,FooBarWithSemaphore foobar){
        super(threadName);
        this.threadName=threadName;
        this.foobar=foobar;
    }

    public void run(){
        for(int i=0;i<5;i++){
            if(threadName.equals("foo")){
                foobar.foo();
            }
            else if(threadName.equals("bar")){
                foobar.bar();
            }
            else throw new IllegalArgumentException("wrong thread name");
        }

    }
}
