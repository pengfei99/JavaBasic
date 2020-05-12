package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.thread_pool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MySaturationPolicy implements RejectedExecutionHandler {

    private final Lock lock=new ReentrantLock();
    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
        lock.lock();
        try {
            executor.setMaximumPoolSize(executor.getMaximumPoolSize() + 1);
        } finally {
            lock.unlock();
        }

        executor.submit(runnable);
    }
}
