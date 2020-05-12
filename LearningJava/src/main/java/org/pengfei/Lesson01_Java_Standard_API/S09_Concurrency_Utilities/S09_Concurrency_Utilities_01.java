package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities;

import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.*;

public class S09_Concurrency_Utilities_01 {

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
     *
     * Check SynchronizationObjExample.exp1(); Note that the sleep() method can't release the context, because other
     * thread is blocked by the semaphore, if we remove the semaphore, you will see the two threads intermix, and the
     * SharedObj.count are accessed by two threads simultaneously are increased or decreased. Note we also set access
     * order of the semaphore as FIFO. try to change the order of the two threads. See what happens.
     *
     * Check SynchronizationObjExample.exp2(); we use two semaphores to regulate the producers and consumers threads.
     * We can use the default synchronized method, wait(), notify() to resolve the same problem. But semaphore just
     * makes more clear for human reader. Check the foobar example (Lesson0_S10MultiThread), we rewrite it with
     * two semaphores. In old version, in the shared object, we set all method which can be invoked by thread to
     * synchronized, and use wait() and notify() to control the synchronization. In new version, with semaphore, no
     * need to set method to synchronized, no need to suspend thread and wake them up by yourself. Its controlled
     * automatically by semaphore.
     * In example build_hho_semaphore, we demonstrate, we can release 2 or more permits at one time. We can also use
     * availablePermits() method to check the number of permit of a semaphore.
     * */

/** 9.3.2 CountDownLatch
 *
 * Sometimes you will want a thread to wait until one or more events have occurred. In this kind of situation, we need
 * to use CountDownLatch. It is initially created with a count of the number of events that must occur before the
 * latch is released. Each time an event happens, the count is decremented. When the count reaches zero, the latch
 * opens. It has one constructor:
 * - CountDownLatch(int num): num specifies the number of events that must occur in order the latch to open. Note This
 *           num value can be set only once, and CountDownLatch provides no other mechanism to reset this count.
 *
 * To wait on the latch, a thread calls await() methods:
 * - void await() throws InterruptedException: It waits until the count associated with the invoking CountDownLatch
 *                       reaches zero.
 * - boolean await(long wait, TimeUnit tu) throws InterruptedException: It waits only only for the period of time
 *                     specified by wait. The tu represents the units. It returns false if the time limit is
 *                     reached and true if the countdown reaches zero.
 *
 * To signal an event, call the countDown() method, each call to countDown() decrements the count associated with the
 * invoking object.
 *
 * Check SynchronizationObjExample.exp3(); We use a countDownLatch to count three sub thread, If all three ended, then
 * the main thread continues, otherwise it waits. If we use the java default multi-thread feature, we could use join()
 * to make the main thread to wait.
 * */

/** 9.3.3 CyclicBarrier
 *
 * A situation not uncommon in concurrent programming occurs when a set of two or more threads must wait at a
 * predetermined execution point until all threads in the set have reached that point. To handle such a situation, we
 * use CyclicBarrier class. It enables you to define a synchronization object that suspends until the specified
 * number of threads has reached the barrier point. It has the following two constructors:
 * - CyclicBarrier(int numThreads): numThreads specifies the number of threads that must reach the barrier before
 *              execution continues.
 * - CyclicBarrier(int numThreads, Runnable action): action specifies a thread that will be executed when the barrier
 *              is reached.
 *
 * The general procedure for using CyclicBarrier:
 * 1. Create a CyclicBarrier object, and specify the number of threads that you will be waiting for.
 * 2. When each thread reaches the barrier, have it call await() on that CyclicBarrier. This will pause execution
 *    of the thread until all of the other threads also call await().
 * 3. Once the specified number of thread has reached the barrier, if you specified an action in the CyclicBarrier,
 *    then that thread is executed. Finally await() will return and the waiting thread will resume.
 *
 * await() has two forms:
 * - int await() throws InterruptedException, BrokenBarrierException: It waits until all the threads have reached
 *                  the barrier point.
 * - int await(long wait, TimeUnit tu) throws InterruptedException, BrokenBarrierException, TimeoutException: It waits
 *                  only for the period of time specified by wait with unit tu.
 * Both forms returns a int value which indicates the order that the threads arrive at the barrier point. It's
 * calculated with value=numThreads-i, where i is the arrive order starts at 1. So If the numThread =3, the first
 * arrived thread returns 2, the last thread returns 0.
 *
 * Check SynchronizationObjExample.exp4(); we use three worker to add elements in a list, after all three workers
 * reach the barrier, we have a thread in the barrier which calculates the sum of all inserted elements.
 *
 * A CyclicBarrier can be reused because it will release waiting threads each time the specified number of threads
 * calls await(). check SynchronizationObjExample.exp5();
 *
 * In example build_hho_cyclicB, we require two h to reach the barrier to produce a o, in order to build a h2o.
 * */

/** 9.3.4 Exchanger
 *
 * Exchanger is designed to simplify the exchange of data between two threads. It waits until two separate threads
 * call its exchange() method. When that occurs, it exchanges the data supplied by the threads.
 *
 * Exchanger is a generic class that is declared as Exchanger<V>, V is the type of data being exchanged. It has only
 * one method exchange(), which has two forms:
 * - V exchange(V objRef) throws InterruptedException: objRef is a reference to the data to exchange. The data received
 *                   from the other thread is returned.
 * - V exchange(V objRef, long wait, TimeUnit tu) throws InterruptedException, TimeoutException: It allows a time-out
 *                   period to be specified.
 *
 * The key point about exchange() is that it won't succeed until it has been called on the same Exchanger object by
 * two separate threads. Thus, exchange() synchronizes the exchange of the data.
 * Check  SynchronizationObjExample.exp6();
 * */

/** 9.3.5 Phaser
 * To enable the synchronization of threads that represent one or more phases of activity, we need to call Phaser class.
 * Phaser lets you define a synchronization object that waits until a specific phase has completed. It then advances to
 * the next phase, again waiting until that phase concludes. It is important to understand that Phaser can also be used
 * to synchronize only a single phase. In this regard, it acts much like a CyclicBarrier.
 *
 * Phaser defines four constructors:
 * - Phaser(): Creates a new phaser with no initially registered parties, no parent, and initial phase number 0.
 * - Phaser(int numParties): Creates a new phaser with the given number of registered unarrived parties, no parent,
 *             and initial phase number 0.
 * - Phaser(Phaser parent): Equivalent to Phaser(parent, 0).
 * - Phaser(Phaser parent, int parties): Creates a new phaser with the given parent and number of registered
 *             unarrived parties.
 *
 * The term party is often applied to the objects that register with a phaser. General procedure to use Phaser:
 * 1. Create a new instance of Phaser.
 * 2. Register one or more parties with the phaser, either by calling register() or by specifying the number of
 *    parties in the constructor. For each registerd party, have the phaser wait until all registered parities
 *    complete a phase. A party signals this by calling one of a variety of methods supplied by Phaser, such as
 *    arrive() or arriveAndAwaitAdvance().
 * 3. After all parties have arrived, the phase is complete, and the phaser can move on to the next
 *    phase(if there is one), or terminate.
 *
 * It has the following method
 * - int register(): It registers parties after a Phaser has been constructed. It returns the phase number of the phase
 *                   to which it is registered.
 * - int arrive(): It signals that a party has completed a task. When the number of arrivals equals the number of
 *                 registered parties, the phase is completed. It returns the current phase number. If the phaser has
 *                 been terminated, then it returns a negative value. The arrive() method does not suspend execution
 *                 of the calling thread. This means that it does not wait for the phase to be completed. This method
 *                 should be called only by registered parties.
 * - int arriveAndAwaitAdvance(): Similar to arrive. But it will suspend the thread and wait until all other registrants
 *                 have also completed that phase. It returns the next phase number or a negative value if the phaser
 *                 has been terminated.
 * - int arriveAndDeregister(): A thread can arrive and then deregister itself. It returns the current phase number
 *                 or a negative value. It does not suspend the calling thread. Thus it does not wait until the
 *                 phase is complete.
 * - final int getPhase(): It returns the current phase number. When a Phase is created, the first phase will be 0,
 *                 Second phase 1, and so on. It returns a negative value if the invoking Phaser has been terminated.
 * - protected boolean onAdvance(int phase, int numParties): phase is the current phase number prior to be incremented
 *                  and numParties will contain the number of registered parties. This method should be override, if
 *                  you want to control precisely what happens when a phase advance occurs. It should return false to
 *                  keep the phaser alive, or true to terminate phaser. The default version is also implemented like
 *                  this.
 *                  One reason to override onAdvance() is to enable a phaser to execute a specific number of phases
 *                  and then stop.
 * - int awaitAdvance(int phase): phase indicates the number on which awaitAdvance() will wait until a transition to
 *                  the next phase take place. It will return immediately if the argument passed to phase is not
 *                  equal to the current phase or the phaser is terminated. But, if phase is the current phase,
 *                  then it will wait until the phase increments.
 * - int awaitAdvanceInterruptibly(int phase): It is the interruptible version of awaitAdvance.
 *
 * There are also some other interesting method. For example:
 * - bulkRegister(): It registers more than one party.
 * - getRegisteredParties(): It obtains the number of registered parties
 * - getArrivedParties(): It returns the number of arrived parties
 * - getUnarrivedParties(): It returns the number of unarrived parties
 * - forceTermination(): It forces the phasers to enter a terminated state.
 *
 * We can create a tree of phasers with the two constructor which takes a parent phaser.
 *
 * Check SynchronizationObjExample.exp7(); We have three threads registered to a Phaser. During the execution, we
 * have four phase. In this example, all three threads are all of the same type, this is not a requirement.
 * Each party that uses a phaser can be unique.
 *
 * Check SynchronizationObjExample.exp8(); We create a custom phaser by extending phaser class. By overriding the
 * onAdvance method, we control how a phaser ends. To avoid explicitly extends Phaser class, you can create an
 * anonymous inner class to override onAdvance.
 *
 * Check SynchronizationObjExample.exp9(); We creat a parent phraser which has one child phaser. The parent will not
 * terminated until the child terminated.
 * */

/**
 * Check example build_molecule, we mix semaphore, cyclicBarrier and phaser to build any molecule.
 * */


    public static void main(String[] args){

        /** Synchronization object*/
        // Semaphore
        // SynchronizationObjExample.exp1();
       // SynchronizationObjExample.exp2();

        // CountDownLatch
       // SynchronizationObjExample.exp3();

        // cyclic barrier
        // SynchronizationObjExample.exp4();

        // notice that the reducer finish its job before the worker, even the barrier has been reached twice.
       // SynchronizationObjExample.exp5();

        // Exchanger
       // SynchronizationObjExample.exp6();

        // Phaser
        // SynchronizationObjExample.exp7();
       // SynchronizationObjExample.exp8();
       // SynchronizationObjExample.exp9();

    }
}
