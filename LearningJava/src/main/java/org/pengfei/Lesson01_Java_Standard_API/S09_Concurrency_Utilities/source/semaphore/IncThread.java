package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.semaphore;

import java.util.concurrent.Semaphore;

public class IncThread implements Runnable {

    private final String name;
    private final Semaphore sem;

    public IncThread(String threadName, Semaphore sem) {
        this.name = threadName;
        this.sem = sem;
    }

    @Override
    public void run() {
        System.out.println("Starting Thread: " + name);
        try {
            //get a permit.
            System.out.println(name + " is waiting for a permit");
            // acquire is a blocking method. It will block this thread, until a permit is acquire
            sem.acquire();
            System.out.println(name + " gets a permit");

            //Now, access the shared resource.
            for (int i = 0; i < 5; i++) {
                //static field no need to get an instance
                SharedObj.count++;
                System.out.println(name + ": " + SharedObj.count);

                // allow a context switch, if possible
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//finish the job, release the permit
        System.out.println(name + " release the permit.");
        sem.release();
    }
}
