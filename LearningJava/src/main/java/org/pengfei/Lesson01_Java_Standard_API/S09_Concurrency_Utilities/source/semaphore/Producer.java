package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.semaphore;

public class Producer implements Runnable {
    private final String name;
    private final SynchronizedQueue queue;
    private final int data;

    public Producer(String name, SynchronizedQueue queue, int data) {
        this.name = name;
        this.queue = queue;
        this.data = data;
    }

    @Override
    public void run() {
        System.out.println("Creating producer: " + name);
        for (int i = 0; i < 10; i++) {
            queue.put(data);
            //System.out.println(name + " writes " + data + " on queue");
        }
    }
}
