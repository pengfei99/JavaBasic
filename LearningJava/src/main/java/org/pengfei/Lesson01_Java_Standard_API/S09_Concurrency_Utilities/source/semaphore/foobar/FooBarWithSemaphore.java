package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.semaphore.foobar;

import java.util.concurrent.Semaphore;

public class FooBarWithSemaphore {
    //Thread prints Foo starts first, so init permit of semaphore is 1, FiFo set true
    Semaphore fooSemaphore= new Semaphore(1,true);

    //Thread prints Bar starts second, init permit set to 0
    Semaphore barSemaphore=new Semaphore(0,true);

    public void foo(){
        try {
            //before print foo, check if semaphore has the permit or not. if not, the thread which calls this method
            // is suspended
            fooSemaphore.acquire();
            System.out.println("foo");
            // after print, add permit to bar semaphore to allow thread to print bar. foo semaphore has no permit to run
            barSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void bar(){
        try {
            barSemaphore.acquire();
            System.out.println("bar");
            fooSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
