package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities;

import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.*;

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
     * Check SynchronizationObjExample.exp2(); we use two semaphores to regulate the producers and
     * consumers threads.
     *
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
 * To wait on the lactch, a thread calls await() methods:
 * - void await() throws InterruptedException: It waits until the count associated with the invoking CountDownLatch
 *                       reaches zero.
 * - boolean await(long wait, TimeUnit tu) throws InterruptedException: It waits only only for the period of time
 *                     specified by wait. The tu represents the units. It returns false if the time limit is
 *                     reached and true if the countdown reaches zero.
 *
 * To signal an event, call the countDown() method, each call to countDown() decrements the count associated with the
 * invoking object.
 *
 *
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
 * */

    /***************************************** 9.3 Using an Executor **********************************/

    /*
    * An executor initiates and controls the execution of threads, it offers an alternative to managing threads
    * through the Thread class. The core of executor is the Executor interface, it defines one method:
    * - void execute(Runnable thread): It executes the thread.
    *
    * The ExecutorService interface extends Executor() by adding methods that help manage and control the execution of
    * threads. It defines the following methods:
    * - boolean awaitTermination​(long timeout, TimeUnit unit): Blocks until all tasks have completed execution after
    *          a shutdown request, or the timeout occurs, or the current thread is interrupted, whichever happens first.
    * - <T> List<Future<T>> invokeAll​(Collection<? extends Callable<T>> tasks): Executes the given tasks, returning a
    *          list of Futures holding their status and results when all complete.
    * - <T> List<Future<T>> invokeAll​(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit): Executes
    *          the given tasks, returning a list of Futures holding their status and results when all complete or
    *          the timeout expires, whichever happens first.
    * - <T> T invokeAny​(Collection<? extends Callable<T>> tasks): Executes the given tasks, returning the result of
    *          one that has completed successfully (i.e., without throwing an exception), if any do.
    * - <T> T invokeAny​(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit): Executes the given
    *          tasks, returning the result of one that has completed successfully (i.e., without throwing an exception),
    *          if any do before the given timeout elapses.
    * - boolean isShutdown​(): Returns true if this executor has been shut down.
    * - boolean	isTerminated​(): Returns true if all tasks have completed following shut down.
    * - void shutdown​(): Initiates an orderly shutdown in which previously submitted tasks are executed, but no
    *          new tasks will be accepted.
    * - List<Runnable> shutdownNow​(): Attempts to stop all actively executing tasks, halts the processing of waiting
    *          tasks, and returns a list of the tasks that were awaiting execution.
    * - Future<?> submit​(Runnable task): Submits a Runnable task for execution and returns a Future representing that
    *          task.
    * - <T> Future<T> submit​(Runnable task, T result): Submits a Runnable task for execution and returns a Future
    *          representing that task.
    * - <T> Future<T> submit​(Callable<T> task): Submits a value-returning task for execution and returns a Future
    *           representing the pending results of the task.
    *
    * The ScheduledExecutorService interface extends ExecutorService to support the scheduling of threads.
    *
    * There are three predefined executor classes:
    * - ThreadPoolExecutor: It implements ExecutorService, Executor interface and provides support for a managed pool
    *                 of threads.
    * - ScheduledThreadPoolExecutor: It implements ScheduledExecutorService interface to allow a pool of threads
    *                  to be scheduled.
    * - ForkJoinPool: It implements ExecutorService, Executor interface and is used by the Fork/Join Framework.
    *
    * A thread pool provides a set of threads that is used to execute various tasks. Instead of each task using its
    * own thread, the threads in the pool are used. This reduces the overhead associated with creating many separate
    * threads.
    *
    * Although you can use ThreadPoolExecutor and ScheduledThreadPoolExecutor directly, most often you will want to
    * obtain an executor by calling one of the static factory methods defined by the Executors utility class. Here
    * are some examples:
    * - static ExecutorService newCachedThreadPool(): It creates a thread pool that adds threads as needed but reuses
    *            thread if possible. It returns a reference to an ExecutorService that can be used to manage the pool.
    * - static ExecutorService newFixedThreadPool(int numThreads): It creates a thread pool that consists of a
    *            specific number of threads. It returns a reference to an ExecutorService that can be used to manage
    *            the pool.
    * - static ScheduledExecutorService newScheduledThreadPool(int numThreads): It creates a thread pool that supports
    *            thread scheduling. It returns a reference to an ExecutorService that can be used to manage the pool.
    * */

    /** 9.3.1 A Simple Executor Example
     *
     * Check ExecutorExample.exp1(); We use a fixed thread pool that contains two threads to run four workers. Thus
     * two workers will run simultaneously, the others must wait until one of the pooled threads is available for
     * use. The call to shutdown() is important, without it the program would not terminate because the executor
     * would remain active
     * */

    /** 9.3.2 Using Callable and Future
     *
     * The Callable interface represents a thread that returns a value. An application can use Callable objects to
     * compute results that are then returned to the invoking thread. This is a powerful mechanism because it
     * facilitates the coding of many types of numerical computations in which partial results are computed
     * simultaneously. It can also be used to run a thread that returns a status code that indicates the
     * successful completion of the thread.
     *
     * Callable interface is a generic interface which defines only one method:
     * - V call() throws Exception: V indicates the type of data returned by the task. In side this method, we define
     *             the task that you want performed. After that task completes, you return the result. If the result
     *             cannot be computed, call() must throw an exception.
     * A Callable task is executed by an ExecutorService, by calling its submit() method. There are three forms of
     * submit(), but only one is used to execute a Callable:
     * - <T> Future<T> submit(Callable<T> task): task is the Callable object that will be executed in its own thread.
     *              The result is returned through an object of type Future.
     *
     * Future is a generic interface that represents the value that will be returned by a Callable object. Because
     * this value is obtained at some future time, the name Future is appropriate. To obtain values, we need to call
     * get() methods, it has two forms:
     * - V get(): It waits for the result indefinitely.
     * - V get(long wait, TimeUnit tu): It waits for the result for wait time with tu as time unit.
     * */

    /***************************************** 9.4 The TimeUnit Enumeration **********************************/

    /*
     * The concurrent API defines several methods that take an argument of type TimeUnit, which is an enumeration
     * that is used to specify the granularity(or resolution) of the timing. It can have following values:
     * - DAYS
     * - HOURS
     * - MINUTES
     * - SECONDS
     * - MICROSECONDS
     * - MILLISECONDS
     * - NANOSECONDS
     * Note, there is no guarantee that the system of the running environment is capable of the specific resolution.
     * Check ExecutorExample.exp3(); we rewrite the get() method by adding waiting time.
     *
     * The TimeUnit enumeration defines various methods that convert between units:
     * - long convert(long val, TimeUnit tu): It converts val to specific time unit of tu.
     * - long toMicros(long val): It converts val to micro seconds.
     * - long toMillis(long val): It converts val to millis seconds.
     * - long toNanos(long val): It converts val to nano seconds.
     * - long toSeconds(long val): It converts val to seconds.
     * - long toDays(long val): It converts val to days
     * - long toHours(long val): It converts val to hours.
     * - long toMinutes(long val): It converts val to minutes.
     *
     * The TimeUnit also defines the following timing methods:
     * - void sleep(long delay): It pauses execution for the specified delay period, which is specified in terms of the
     *               invoking enumeration constant. It translates into a call to Thread.sleep().
     * - timedJoin(Thread thrd, long delay): It pauses thrd for the time period specified by delay.
     * - timedWait(Object obj, long delay): it is a specialized version of Object.wait, in which, obj is waited on for
     *                the time period specified by delay
     * */

    /***************************************** 9.5 The Concurrent Collections **********************************/

    /*
     *
     * As explained, the concurrent API defines several collection classes that have been engineered for concurrent
     * operation. They include:
     * - ArrayBlockingQueue
     * - ConcurrentHashMap
     * - ConcurrentLinkedDeque/ConcurrentLinkedQueue
     * - ConcurrentSkipListMap
     * - ConcurrentSkipListSet
     * - CopyOnWriteArrayList
     * - CopyOnWriteArraySet
     * - DelayQueue
     * - LinkedBlockingDeque
     * - LinkedBlockingQueue
     * - LinkedTransferQueue/PriorityBlockingQueue
     * - SynchronousQueue
     *
     * Thread safety: We safe a program is thread safe if different threads in this program can access the same
     * resources without exposing erroneous behavior or producing unpredictable results.
     */

     /** 9.5.1 Concurrent collections VS Synchronized Collection (created via Synchronized Wrappers)
     * As we explained in L01-S3.3, the Java Collections Framework provides factory methods for creating thread-safe
     * collections. These methods are in the following form: Collections.synchronizedXXX(collection).
     * Check ConcurrentCollectionsExample.exp1(), we use synchronized wrappers to create a list. When using
     * the iterator of a synchronized collection, we should use synchronized block to safeguard the iteration code
     * because the iterator itself is not thread-safe.
      * Check ConcurrentCollectionsExample.exp2(); If we use a not thread safe list. The listReader thread throws
      * ConcurrentModificationException
     *
     * A drawback of synchronized collections is that their synchronization mechanism uses the collection object itself
      * as the lock object. That means when a thread is iterating over elements in a collection, all other collection’s
      * methods block, causing other threads having to wait. This causes overhead and reduces performance.
     * */

     /** 9.5.2 Concurrent collections thread safety mechanisms
      *
      * Concurrent collection classes can be categorized into 3 groups based on their thread safety mechanisms:
      * - Copy-on-write collections: This kind of thread-safe collections stores values in an immutable array;
      *              any change to the value of the collection results in a new array being created to reflect
      *              the new values. These collections are designed for situations in which read operations
      *              greatly predominate write operations. CopyOnWriteArrayList and CopyOnWriteArraySet are
      *              in this group.
      *              Note that copy-on-write collections have snapshot iterators which do not throw
      *              ConcurrentModificationException. Since these collections are backed by immutable arrays, an
      *              iterator can read the values in one of these arrays (but never modify them) without danger of
      *              them being changed by another thread.
      * - Compare-And-Swap or CAS collections: the collections in this group implement thread safety using an
      *              algorithm called Compare-And-Swap (CAS) which can be understood like this:
      *              To perform calculation and update value of a variable, it makes a local copy of the variable
      *              and performs the calculation without getting exclusive access. When it is ready to update the
      *              variable, it compares the variable’s value with its value at the start and, if they are the same,
      *              updates it with the new value.
      *              If they are not the same, the variable must have been changed by another thread. In this situation,
      *              the CAS thread can try the whole computation again using the new value, or give up, or continue.
      *              ConcurrentLinkedQueue and ConcurrentSkipListMap are in this group.
      *              Note that the CAS collections have weakly consistent iterators, which reflect some but not
      *              necessarily all of the changes that have been made to their backing collection since they were
      *              created. Weakly consistent iterators do not throw ConcurrentModificationException
      * - Using a special lock object: This mechanism is more flexible than classical synchronization. This can be
      *              understood as following:
      *              A lock has the same basic behavior as classical synchronization object but a thread can also
      *              acquire it under special conditions: only if the lock is not currently held, or with a timeout,
      *              or if the thread is not interrupted.
      *              Unlike synchronization code, in which an object lock is held while a code block or a method is
      *              executed, a Lock is held until its unlock() method is called. Some implementations make use of
      *              this mechanism to divide the collection into parts that can be separately locked, giving improved
      *              concurrency. For example, LinkedBlockingQueue has separate locks for the head and the tail ends
      *              of the queue, so that elements can be added and removed in parallel.
      *              Other collections using this lock include ConcurrentHashMap and most of the implementations of
      *              BlockingQueue.
      *              Collections in this group also have weakly consistent iterators and do not throw
      *              ConcurrentModificationException.
      * */

    /************************************************* 9.6 Locks **********************************************/

    /*
    * The java.util.concurrent.locks package provides support for locks, which are objects that offer an alternative
    * to using synchronized to control access to a shared resource. In general, a lock works like this. Before access
    * to the resource is complete, the lock that protects that resource is acquired. When access to the resource is
    * complete , the lock is released. If a second thread try to access the resource, the second thread will suspend
    * until the lock is released. Locks ar particularly useful when multiple threads need to access the value of
    * shared data. For example, an inventory of an online store, the number of items decrease whenever sales occurs.
    * If multiple sales happens at same time, one thread thinks there are still items, but its already been used by
    * other threads. In this type of situation, a lock offers a convenient means of handling the needed synchronization.
    *
    * The Lock interface defines the following methods:
    * - void lock​(): Waits until the invoking lock can be acquired.
    * - void lockInterruptibly​(): Acquires the lock unless the current thread is interrupted.
    * - Condition newCondition​(): Returns a new Condition instance that is bound to this Lock instance. Using a
    *            Condition, you gain detailed control of the lock through methods such as await() and signal(), which
    *            similar to Object.wait() and Object.notify().
    * - boolean tryLock​(): Attempts to acquire the lock. This method will not wait if the lock is unavailable. Instead
    *             it returns true if the lock has been acquired and false if the lock is currently in use by other
    *             thread.
    * - boolean	tryLock​(long time, TimeUnit unit): Attempts to acquire the lock. If the lock is unavailable, this
    *             method will wait no longer than time in timeUnit unit. it returns true if the lock has been acquired
    *             and false if the lock is currently in use by other thread.
    * - void unlock​(): Releases the lock.
    *
    * */

    /** 9.6.1 ReentrantLock
     *
     * java.util.concurrent.locks supplies an implementation of Lock interface called ReentrantLock. It is a lock
     * that can be repeatedly entered by the thread that currently holds the lock. Of course, in the case of a thread
     * reentering a lock, all calls to lock() must be offset by an equal number of calls to unlock(). Otherwise, a
     * thread seeking to acquire the lock will suspend until the lock is not in use.
     *
     * Check LockExample.exp1();
     * */

    /** 9.6.2 ReentrantReadWriteLock
     * java.util.concurrent.locks also defines the ReadWriteLock interface. This interface specifies a lock that
     * maintains separate locks for read and write access. This enables multiple locks to be granted for readers
     * of a resource as long as the resource is not being written. ReentrantReadWriteLock provides an
     * implementation of ReadWriteLock.
     * Check LockExample.exp2(); we use read write lock to separately.
     * */

    /** 9.6.3 StampedLock
     * It does not implement the Lock or ReadWriteLock interfaces. However, It provides a mechanism that enables
     * aspects of it to be used like a Lock or ReadWriteLock.
     * */

    /********************************************* 9.7 Atomic Operations ******************************************/

    /*
    * java.util.concurrent.atomic offers an alternative to the other synchronization features when reading or writing
    * the value of some types of variables.	This package offers methods that get, set, or compare the value of a
    * variable	in	one uninterruptible	(that is, atomic) operation. This means that no lock or other
    * synchronization mechanism is required.
    *
    * Atomic operations are accomplished through the use of classes, such as AtomicInteger and AtomicLong, and methods
    * such as get(), set(), compareAndSet(), decrementAndGet(), and getAndSet().
    * Check  AtomicExample.exp1();
    *
    * In general, the atomic operations offer a convenient(and possibly more efficient) alternative to the other
    * synchronization mechanism when only a single value is involved. Among other features, java.util.concurrent.atomic
    * also provides four classes that support lock-free cumulative operations:
    * - DoubleAccumulator: It supports a series of user specified operations.
    * - DoubleAdder: It maintains a cumulative sum.
    * - LongAccumulator
    * - LongAdder
    * */

    /************************ 9.8 Parallel Programming via the Fork/Join Framework *********************************/

    /*
    * Parallel programming is the name commonly given to the techniques that take advantage of computers that contain
    * two or more processors(multicore). JDK 7 added new classes and interfaces that support parallel programming.
    * They are commonly referred to as the Fork/Join Framework which is defined in the java.util.concurrent package.
    *
    * The Fork/Join framework enhances multithreaded programming in two important ways:
    * 1. It simplifies the creation and use of multiple threads.
    * 2. It automatically makes use of multiple processors.
    * As a result, Fork/join is recommended for multithreading when parallel processing is desired in a multicore
    * running environment.
    *
    * It's important to point out the distinction between traditional multithreading and parallel programming. In the
    * past, most computers has a single CPU and multithreading was primarily used to take advantage of cpu idle time.
    * On a single-CPU system, multithreading is used to allow two or more tasks to share the CPU, so one can execute
    * while another is waiting for input. When multicore cpus are present, it is possible to execute portions
    * of a program simultaneously, with each part executing on its own CPU. This can significantly speed up the
    * execution of some types of operations, such as sorting, transforming, or searching in large array.
    *
    *
    * */

    /** 9.8.1 The Main Fork/Join Classes
     *
     * The core of Fork/Join framework are the following four classes:
     * - ForkJoinTask<V>: An abstract class that defines a task that can be managed by a ForkJoinPool. It differs from
     *                    Thread in that ForkJoinTask represents lightweight abstraction of a task, rather than a
     *                    thread of execution. ForkJoinPool mechanism allows a large number of tasks to be managed
     *                    by a small number of actual threads. Thus, ForkJoinTasks are very efficient when compared
     *                    to threads. It defines many methods, the two core methods are
     *                    -- final ForkJoinTask<V> fork(): It submits the invoking task for asynchronous execution
     *                                of the invoking task. This means that the thread that calls fork() continues
     *                                to run. The fork() method returns this(the invoking task) after the task is
     *                                scheduled for execution. Before JDK 8, fork() could be executed only from within
     *                                the computational portion of another ForkJoinTask, which is running within a
     *                                ForkJoinPool. After JDK 8, if fork() is not called while executing within a
     *                                ForkJoinPool, then a common pool is automatically used.
     *                    -- final V join(): It waits until the task on which it is called terminates. The result of the
     *                               task  is returned. Thus you can start one or more new tasks and then wait for
     *                               them to finish.
     *                    -- final V invoke(): It combines the fork and join operations into a single call because it
     *                               begins a task and then waits for it to end. The result of the invoking task
     *                               is returned.
     *                    -- static void invokeAll(ForkJoinTask<?> taskA, ForkJoinTask<?> taskB): It invokes two tasks
     *                                A and B at same time. The calling thread waits until all of the specified tasks
     *                                have terminated.
     *                    -- static void invokeAll(ForkJoinTask<?> ... taskList): It invokes all tasks in the list.
     *                                The calling thread waits until all of the specified tasks have terminated.
     *                                Note Before JDK 8, invokeAll() could be executed only from within the
     *                                computational portion of another ForkJoinTask, which is running within a
     *                                ForkJoinPool. After JDK 8, if fork() is not called while executing within a
     *                                ForkJoinPool, then a common pool is automatically used.
     *
     * - ForkJoinPool: Manages the execution of ForkJoinTasks. Beginning with JDK 8, there are two ways to acquire
     *                 a ForkJoinPool:
     *                 1. By using a ForJoinPool constructor:
     *                         -- ForkJoinPool(): It creates a default pool that supports a level of parallelism equal
     *                                          to the number of processors available in the system.
     *                         -- ForkJoinPool(int pLevel): pLevel specifies the level of parallelism. Its value must
     *                                          be grater than 0 and not more than the limits of the implementation.
     *                                          The level of parallelism determines the number of threads that can
     *                                          execute concurrently. As a result, the level of parallelism
     *                                          effectively determines the number of tasks that can be executed
     *                                          simultaneously which can not exceed the number of processors. But,
     *                                          pLevel does not limit the number of tasks that can be managed by the
     *                                          pool. And pLevel is only a target, not a guarantee.
     *                 2. By using the common pool, which (was added by JDK 8) is a static ForkJoinPool that is
     *                    automatically available for your use.
     *                 It can start a task in a number of different ways.The first task is often thought of as the main
     *                 task, which begins sub-tasks that are also managed by the pool. To begin a main task we can use
     *                 the following methods on the ForJoinPool class
     *                 -- <T> T invoke(ForkJoinTask<T> task): It begins the tasks specified by task, and returns the
     *                             result of the task. This means that the calling code waits until invoke() returns
     *                 -- void execute(ForkJoinTask<?> task): It starts a task without waiting for its completion, and
     *                             the calling code continues its executions asynchronously.
     *
     *                  Beginning with JDK8, we don't need to explicitly construct a ForkJoinPool, because the common
     *                  pool will automatically be used, if no pool is explicitly created. You can get a reference to
     *                  the common pool by calling
     *                  -- static ForkJoinPool commonPool(): A static method of ForkJoinPool class. The common pool
     *                             provides a default level o parallelism. It can be set by use of a system property.
     *                  There are two basic ways to start a task using the common pool:
     *                  1. Once you have the reference of the common pool, you can use that reference to call
     *                     invoke() or execute() to run tasks
     *                  2. You can call ForkJoinTask methods such as fork() or invoke() on the task from outside its
     *                     computational portion. In this case, the common poll will automatically be used. It means
     *                     fork() and invoke() will start a task using the common pool if the task is not already
     *                     running within a ForkJoinPool.
     *
     *                  ForkJoinPool manages the execution of its threads using an approach called work-stealing. Each
     *                  worker thread maintains a queue of tasks. If one worker thread’s queue is empty, it will take
     *                  a task from another worker thread. This adds to overall efficiency and help maintain a
     *                  balanced load. (Because of demands on CPU time by other processes in the system, even two
     *                  worker threads with identical tasks in their respective queues may not complete at the same time.)
     *
     *                  One	other point: ForkJoinPool uses daemon threads. A daemon thread is automatically terminated
     *                  when all user threads have terminated. Thus, there is no need to explicitly shut down a
     *                  ForkJoinPool. However, with the exception of the common pool, it is possible to do so by
     *                  calling shutdown().	The	shutdown() method has no effect on the common pool.
     *
     * - RecursiveAction: A subclass of ForkJoinTask<V> for tasks that do not return values. Typically, your code will
     *                    extend RecursiveAction to create a task that has a void return type. In general,
     *                    RecursiveAction is used to implement a recursive, divide-and-conquer strategy for tasks that
     *                    don't return return results.
     *                    It specifies four methods:
     *                    -- protected abstract void compute(): It contains the main computation code performed by
     *                                  this task. In another words, it represents the "computational portion" of
     *                                  the task. Note its protected and abstract, thus it must be implemented by
     *                                  a subclass(unless that subclass is also abstract).
     *                    -- protected boolean exec(): Implements execution conventions for RecursiveActions.
     *                    -- Void getRawResult(): Always returns null.
     *                    -- protected void setRawResult(Void mustBeNull): Requires null completion value.
     * - RecursiveTask: A subclass of ForkJoinTask<V> for tasks that return values. Like RecursiveAction, it's used
     *                   to implement recursive, divide-and-conquer strategy for tasks that return results.
     *                   It also has:
     *                   -- protected abstract V compute(): it represents the computational portion of the task. When
     *                      you extend RecursiveTask<V> to create a concrete class, put the code that represents
     *                      the task inside compute(). This code must also return the result of the task.
     *
     * A general architecture on how they work together:
     * 1. A ForkJoinPool (executor) manages the execution of ForkJoinTasks.
     * 2. ForkJoinTask is an abstract class that is extended by the abstract classes RecursiveAction and RecursiveTask.
     *    Typically, a program will extend theses classes to create a task.
     *
     *
     * */

    /** 9.8.2 Divide and Conquer Strategy
     *
     * The divide-and-conquer strategy is based on recursively dividing a task into smaller subtasks until the size
     * of a subtask is small enough to be handled sequentially. For example, a task that applies a transform to each
     * element in an array of N integers can be broken down into two sub-tasks in which each transforms half the
     * elements in the array. That is, one subtask transforms the elements 0 to N/2, and the other transforms the
     * elements N/2 to N. In turn, each subtask can be reduced to another set of sub-tasks, each transforming half of
     * the remaining elements. This process of dividing the array will continue until a threshold is reached in which
     * a sequential solution is faster than creating another division.
     *
     * The advantage of the divide-and-conquer strategy is that the processing can occur in parallel. Therefore,
     * instead of cycling through an entire array using a single thread, pieces of the array can be processed
     * simultaneously.
     *
     * One of the keys to best employing the divide-and-conquer strategy is correctly selecting the threshold at
     * which sequential processing (rather than further division) is used. Typically, an optimal threshold is
     * obtained through profiling the execution characteristics. However, very significant speed-ups will
     * still occur even when a less-than-optimal threshold is used. It is, however, best to avoid overly
     * large or overly small thresholds. The Java API documentation for ForkJoinTask<T> states that, as a rule-of-thumb,
     * a task should perform somewhere between 100 and 10,000 computational	steps.
     *
     * It is also important to understand that the optimal threshold value is also affected by how much time the
     * computation takes. If each computational step is fairly long, then smaller thresholds might be better.
     * Conversely, if each computational step is quite short, then larger thresholds could yield better results. If you
     * know the number of processors, you can used it to make informed decisions.
     *
     * One other point: Although multiple processors may be available on a system, other tasks (and the operating
     * system, itself) will be competing with your application for CPU time. Thus, it is important not to assume
     * that your program will have unrestricted access to all CPUs. Furthermore, different runs of the same program
     * may display different run time characteristics because of varying task loads.
     * */

    /** 9.8.3. A Fork/Join Example
     * Check ForkJoinExample.exp1(); and ForkJoinExample.exp2(); We use a ForkJoinAction to transform an array of
     * doubles into their square root.
     * */

    /** 9.8.4 The impact of the level of Parallelism
     *
     * Before moving on, it is important to understand the impact that the level of parallelism has on the
     * performance of a fork/join task and how the parallelism and the threshold interact.
     *
     * Check ForkJoinExample.exp3(1,1000); We use ForkJoinPool(int pLevel) to creates a pool with different parallelism
     * level, and we can set different threshold in the transform class. After test, we can notice with 4 parallelism
     * level and 2000 threshold, the speed is much quicker than 1, 1000. With a machine of 12 core, we set the pLevel
     * to 12, se see a great decrease the elapsed time.
     *
     * check ForkJoinExample.exp4(0); We can get the parallelism level and available core number of the current pool
     * and system.
     * */

    /** 9.8.5 A RecursiveTask example
     *
     * To create a task that returns a result, we need to use RecursiveTask. It works as RecursiveAction, the key
     * difference is the compute() method will return a result. Thus, you must aggregate the results, so that
     * the first invocation finishes, it returns the overall result. Another difference, you will typically start
     * a subtask by calling fork() and join() explicitly.
     *
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

        /** Executor*/
        /*FixedThreadPool executor*/
        // ExecutorExample.exp1();
        // ExecutorExample.exp2();
       // ExecutorExample.exp3();

        /** ConcurrentCollection */
        // thread safe collection
         // ConcurrentCollectionsExample.exp1();
        // not safe list
      //  ConcurrentCollectionsExample.exp2();

        /** Lock*/
        //reentrantLock
        // LockExample.exp1();

        //reentrantReadWriteLock
       // LockExample.exp2();

        /** Atomic operation*/
       // AtomicExample.exp1();

        /** fork join framework*/

        // run task with an explicit pool
        // ForkJoinExample.exp1();

        // run tank without explicit pool
        //ForkJoinExample.exp2();

        // test time with different parallelism level and threshold
       // ForkJoinExample.exp3(1,1000);
        // ForkJoinExample.exp3(12,2000);
        // we can notice with 4 parallelism level and 2000 threshold, the speed is much quicker

        // get parallelism level and available core number
        ForkJoinExample.exp4(0);
        ForkJoinExample.exp4(12);
    }
}
