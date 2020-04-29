package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.semaphore.foobar;

public class FooBarWithSemaphoreMain {

    public static void main(String[] args){
        FooBarWithSemaphore foobar=new FooBarWithSemaphore();
        ThreadForFooBarWithSemaphore t1=new ThreadForFooBarWithSemaphore("foo",foobar);
        ThreadForFooBarWithSemaphore t2=new ThreadForFooBarWithSemaphore("bar",foobar);

        t2.start();
        t1.start();

    }
}
