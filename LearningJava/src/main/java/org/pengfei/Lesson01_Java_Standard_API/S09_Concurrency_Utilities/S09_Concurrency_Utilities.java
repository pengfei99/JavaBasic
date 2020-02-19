package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities;

import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.SynchronizationObjExample;

public class S09_Concurrency_Utilities {

    /********************************************* 9.1 Introduction **************************************************/

    /* At old times, Java does not support:
    * - semaphores
    * - thread pools
    * - execution managers.
    *
    * In this section, we will discuss the programs that make "extensive integral" use of concurrently executing
    * threads. For example, a program that coordinates the activities of several threads, each thread seeks access
    * to information in a database. In this case, read-only accesses might be handled differently from those that
    * require read/write capabilities.
    *
    * To begin to handle the needs of a concurrent program, JDK 5 added the concurrency utilities, also commonly
    * referred to as the concurrent API. The original set of concurrency utilities supplied many features that had
    * long been wanted by programmers who develop concurrent applications. For example, it offered:
    * - synchronizers (such as the semaphore)
    * - thread pools
    * - execution managers
    * - locks
    * - several concurrent collections
    * -	a streamlined way to use threads to obtain computational results.
    *
    * Beginning with JDK 7, the concurrent API was significantly expanded. It added the Fork/Join Framework, which
    * facilitates the creation of programs that make use of multiple processors. Thus, it streamlines the development
    * of program in which two or more pieces execute with true simultaneity, not just time-slicing.
    *
    * JDK 8 also enhanced the Fork/Join Framework. JDK 9 also added features to other parts of the concurrent API.
    * This section only presents an overview of several core features defined by the concurrency utilities. We will
    * start a new Lesson which focus on the Concurrent programming.
    *
    * */

    /********************************************* 9.2 The Java Concurrent API ***************************************/

    /*
    * The concurrency utilities are contained in the java.util.concurrent package and in its two sub-packages:
    * - java.util.concurrent.atomic
    * - java.util.concurrent.locks
    * Beginning with JDK9, all are in the java.base module.
    * */

    /** 9.2.1 java.util.concurrent
     *
     * java.util.concurrent defines the core features that support alternatives to the built-in approaches to
     * synchronization and inter-thread communication. These includes:
     * - Synchronizers: It offers high-level ways of synchronizing the interactions between multiple threads. It has
     *                  the following classes:
     *                  -- Semaphore: Implements the classic semaphore.
     *                  -- CountDownLatch: Waits until a specified number of events have occurred.
     *                  -- CyclicBarrier: Enables a group of threads to wait at a predefined execution point.
     *                  -- Exchanger: Exchanges data between two threads.
     *                  -- Phaser: Synchronizes threads that advance through multiple phases of an operation.
     *                  The above synchronizer provides a solution to a specific type of synchronization problem, this
     *                  enables each synchronizer to be optimized for its intended use.
     * - Executors: It manage thread execution. At the top of the executor hierarchy is the Executor interface, which
     *              is used to initiate a thread. ExecutorService extends Executor and provides methods that manage
     *              execution. There are three implementation of ExecutorService:
     *              -- ThreadPoolExecutor:
     *              -- ScheduledThreadPoolExecutor:
     *              -- ForkJoinPool.
     *              java.util.concurrent also defines the Executors utility class, which includes a number of static
     *              methods that simplify the creation of various executors.
     *              Future and Callable interfaces are strongly related to executors. A Future contains a value that
     *              is returned by a thread after it executes. Thus, its value becomes defined "in the future", when
     *              thread terminates. Callable defines a thread that returns a value.
     * - Concurrent collections: It includes ConcurrentHashMap, ConcurrentLinkedQueue, and CopyOnWriteArrayList
     * - The Fork/Join Framework: It supports parallel programing. Its main classes are ForkJoinTask, ForkJoinPool,
     *              RecursiveTask, and RecursiveAction.
     *
     * To better handle thread timing, java.util.concurrent defines the TimeUnit enumeration.
     *
     * Beginning with JDK 9, java.util.concurrent also includes a subsystem that controls data flow. Its provided by
     * Flow class and nested interfaces such as Flow.Subscriber, Flow.Publisher, Flow.Processor, and Flow.Subscription.
     * Flow and its nested interfaces support the "reactive streams" specification, which means a consumer of data
     * can prevent the producer of the data from overrunning the consumer's ability to process the data.
     *
     * */

    /** 9.2.2 java.util.concurrent.atomic
     *
     * java.util.concurrent.atomic facilitates the use of variables in a concurrent environment. It provides a means
     * of efficiently updating the value of a variable without the use of locks. This is accomplished through
     * the use of classes, such as AtomicInteger and AtomicLong, and methods, such as compareAndSet(), decrementAndGet(),
     * and getAndSet(). These methods execute as a single, non-interruptible operation.
     *
     * */

    /** 9.2.3 java.util.concurrent.locks
     * java.util.concurrent.locks provides an alternative to the use of synchronized methods. At the core
     * of this alternative is the Lock interface, which defines the basic mechanism used to acquire and relinquish
     * access to an object. The key methods are lock(), tryLock(), and unlock(). The advantage to using these
     * methods is greater control over synchronization.
     * */

    /***************************************** 9.3 Using Synchronization Objects **********************************/
    /*
    * Synchronization objects are supported by:
    * - Semaphore
    * - CountDownLatch
    * - CyclicBarrier
    * - Exchanger
    * - Phaser
    * Collectively,	they enable you to handle several formerly difficult synchronization situations with ease. They are
    * also applicable to a wide range of programs, even those that contain only limited concurrency
    * */

    /** 9.3.1 Semaphore
     *
     * A semaphore controls access to a shared resource through the use of a counter. If the counter is greater than
     * zero, then access is allowed. If it is zero, then access is denied. What the counter is counting are permits
     * that allow access to the shared resource. Thus, to access the resource, a thread must be granted a permit
     * from the semaphore.
     *
     * In general, to use a semaphore, the thread that wants access to the shared resource tries to acquire a permit.
     * If the semaphore’s count is greater than zero, then the thread acquires a permit, which causes the semaphore’s
     * count to be decremented. Otherwise, the thread will be blocked until a permit can be acquired. When the thread
     * no longer needs access to the shared resource, it releases the permit, which causes the semaphore’s count to be
     * incremented.	If there is another thread waiting for a permit, then that thread will acquire a permit at that
     * time. Java’s Semaphore class implements this mechanism.
     *
     * Semaphore has two constructors:
     * - Semaphore(int num): num specifies the initial permit count. In other words, it specifies the number of threads
     *                that can access a share resource at any one time. By default, waiting thread are granted a
     *                permit in an undefined order.
     * - Semaphore(int num, boolean how): By setting how to true, you can ensure that waiting threads are granted a
     *               permit in the order in which they requested access.(FIFO).
     *
     * Semaphore has two mainly used methods:
     * - void acquire(int num) throws InterruptedException: It acquires num permits, num is optional, if not specified
     *              one permit is acquired. If the permit cannot be granted at the time of the call, then the invoking
     *              thread suspends until the permit is available.
     * - void release(int num): It release num permits, num is optional, if not specified, one permit is released.
     *
     * To use a semaphore to control access to a resource, each thread that wants to use that resource must first call
     * acquire() before accessing the resource. When the thread is done with the resource, it must call release().
     * Check SynchronizationObjExample.exp1(); Note that the sleep() method can't release the context, because other
     * thread is blocked by the semaphore, if we remove the semaphore, you will see the two threads intermix, and the
     * SharedObj.count are accessed by two threads simultaneously are increased or decreased.
     *
     * */



    public static void main(String[] args){
        /** Semaphore */
        // SynchronizationObjExample.exp1();
        SynchronizationObjExample.exp2();
    }
}
