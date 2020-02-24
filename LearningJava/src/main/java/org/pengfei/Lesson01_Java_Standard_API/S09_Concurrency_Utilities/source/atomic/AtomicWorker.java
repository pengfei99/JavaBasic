package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.atomic;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AtomicWorker implements Runnable {
    private final String name;


    public AtomicWorker(String name) {
        this.name = name;

    }

    @Override
    public void run() {
        System.out.println("Starting " + name);
        for (int i = 0; i < 3; i++) {
            // this method returns the previous value and then set new value which is passed by the argument.
            int previousValue = SharedAtomicInt.number.getAndSet(i);
            System.out.println(name + " got previous value: " + previousValue + " and set new value: " + i);

            //sleep for changing context
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Terminating " + name);
    }
}
