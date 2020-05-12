package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.thread_pool;

import java.util.Objects;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadFactory implements ThreadFactory {

    private String poolName;
    private ThreadGroup group;

    //id of thread, increment after each new thread creation
    private AtomicInteger atomicInteger = new AtomicInteger();

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
        SecurityManager s = System.getSecurityManager();
        group = Objects.nonNull(s) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread t = new ThreadFactoryThread(group, runnable, poolName, atomicInteger.incrementAndGet());

        // check if the thread is daemon thread, then we turn it to normal
        if(t.isDaemon()) {
            t.setDaemon(false);
        }
        // if the thread priority is not normal, then we change it to normal.
        if(t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }

}
