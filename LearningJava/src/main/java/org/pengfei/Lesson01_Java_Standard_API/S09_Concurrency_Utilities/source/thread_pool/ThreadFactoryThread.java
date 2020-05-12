package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.thread_pool;

public class ThreadFactoryThread extends Thread {

    private static final String POOL_DELIMITER = "-";


    /**
     * This thread constructor will build a name based on thread group and thread id
     *
     * @param runnable, tasks to be processed
     * @param pool, name of the pool
     * @param id, Identifier for the thread
     */
    public ThreadFactoryThread(ThreadGroup group, Runnable runnable, String pool, int id) {
        super(group, runnable, String.format("%s%s%d",pool, POOL_DELIMITER, id));
    }
}
