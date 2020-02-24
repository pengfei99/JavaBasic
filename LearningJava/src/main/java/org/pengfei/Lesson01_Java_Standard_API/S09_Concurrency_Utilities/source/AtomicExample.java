package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source;

import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.atomic.AtomicWorker;

public class AtomicExample {
    //In this example we start three thread to get and set an atomic integer value simultaneously.
    public static void exp1(){
        new Thread(new AtomicWorker("worker1")).start();
        new Thread(new AtomicWorker("worker2")).start();
        new Thread(new AtomicWorker("worker3")).start();
    }
}
