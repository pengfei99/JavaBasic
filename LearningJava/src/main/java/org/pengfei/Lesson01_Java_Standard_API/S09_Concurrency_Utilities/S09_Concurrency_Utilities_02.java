package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities;

import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.ExecutorExample;

public class S09_Concurrency_Utilities_02 {


    /***************************************** 9.4 Using an Executor **********************************/

    /*
    * An Executor is an object that is responsible for threads management and execution of Runnable tasks submitted
    * from the client code. It decouples the details of thread creation, scheduling, etc from the task submission
    * so you can focus on developing the task’s business logic without caring about the thread management details.
    * It offers an alternative to managing threads
    * through the Thread class. The core of executor is the Executor interface, it defines one method:
    * - void execute(Runnable thread): It executes the thread.
    *
    * ExecutorService interface
    * It extends Executor interface by adding methods that help manage and control the execution of
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
    * ScheduledExecutorService interface
    * It extends ExecutorService to support the scheduling of threads. It can schedule tasks to execute after a
    * given delay, or to execute periodically. Its key methods are schedule(), scheduleAtFixedRate() and
    * scheduleWithFixedDelay()
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

    /** 9.4.1 A Simple Executor Example
     *
     * Check ExecutorExample.exp1(); We use a fixed thread pool that contains two threads to run four workers. Thus
     * two workers will run simultaneously, the others must wait until one of the pooled threads is available for
     * use. The call to shutdown() is important, without it the program would not terminate because the executor
     * would remain active
     * */

    /** 9.4.2 Using Callable and Future
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
     *
     * Check ExecutorExample.exp2(); We use a fixed thread pool to run three callable, each callable returns its result
     * as Future. Note an executor runs a Runnable with method execute(), a Callable with method submit(). The return
     * value of a Callable must be a Future. Check Sum.java implements Callable, the call() method returns Integer.
     * As the Callable is generic, we can't use primitive types in the call() return type. We must use the wrap object
     * type of primitive types.
     * */

    /** 9.4.3 Thread pools
     *
     * You can notice that in ExecutorExample.exp1() and exp2(). The ExecutorServices points to an object called
     * FixedThreadPool. Java Thread pool represents a group of worker threads that are waiting for the job and reuse
     * many times.
     *
     * Thread Pools are useful when you need to limit the number of threads running in your application at the same
     * time. There is a performance overhead associated with starting a new thread, and each thread is also allocated
     * some memory for its stack etc. As a result, thread pool has better performance. It saves time because there
     * is no need to create new thread.
     *
     * In practice thread pool is used for large-scale applications that launch a lot of short-lived threads in order
     * to utilize resources efficiently and increase performance. For example, it is used in Servlet and JSP where
     * container creates a thread pool to process the request.
     *
     * The thread pool implementation consists of two parts. A ThreadPool class which is the public interface to the
     * thread pool, and a PoolThread class which implements the threads that execute the tasks. The ThreadPool class
     * use a queue to store tasks to be executed. Different thread pool implementation(type) use different types of
     * queues. There are four thread pool types (We will discuss them one by one) in Java:
     * - SingleThreadPool: 适用于需要保证顺序地执行各个任务并且在任意时间点，不会有多个线程是活动的应用场景
     * - FixedThreadPool: 适用于为了满足资源管理需求，而需要限制当前线程数量的应用场景。它适用于负载比较重的服务器；
     * - CachedThreadPool: 适用于执行很多的短期异步任务的小程序，或者是负载较轻的服务器
     * - Fork/joinPool: It's used to optimize big computational recursive tasks
     * - WorkStealingPool(since JDK8): It's used to optimize big computational recursive tasks
     * - ScheduledThreadPoolExecutor： 适用于需要多个后台执行周期任务，同时为了满足资源管理需求而需要限制后台线程的数量的应用场景
     * - SingleThreadScheduledExecutor： 适用于需要单个后台线程执行周期任务，同时保证顺序地执行各个任务的应用场景
     * */

    /** 9.4.3.1 SingleThreadPool executor,
     *
     * It creates a thread pool executor with one thread. So all the submitted tasks will be executed sequentially. The
     * SingleThreadPool is often used when we want to run task one after another.
     *
     * Check ExecutorExample.exp4().
     * Note, if we use Executors.newFixedThreadPool(1), we have exactly the same result
     * */

    /** 9.4.3.2 FixedThreadPool executor,
     *
     * It creates an executor with a fixed number of threads in the pool. This executor ensures that there are no more
     * than n concurrent threads at any time. If additional tasks are submitted when all threads are active, they will
     * wait in the queue until a thread becomes available. If any thread terminates due to failure during execution,
     * it will be replaced by a new one. The threads in the pool will exist until it is explicitly shutdown. Use this
     * executor if you want to limit the maximum number of concurrent threads.
     *
     * It uses a LinkedBlockingQueue to store tasks. A blocking Queue supports operations that wait for the queue to
     * become non-empty when retrieving and removing an element, and wait for space to become available in the queue
     * when adding an element. It does not accept null values and throw NullPointerException if you try to store null
     * value in the queue. It’s primarily used for implementing producer consumer problem. We don’t need to worry
     * about waiting for the space to be available for producer or object to be available for consumer in
     * BlockingQueue because it’s handled by implementation classes of BlockingQueue.
     *
     * Check ExecutorExample.exp1(), we use the two static factory method to create a fixedThreadPool. The second uses
     * a custom ThreadFactory
     *
     *
     */


    /** 9.4.3.3 CachedThreadPool executor,
     * It creates an expandable thread pool executor. New threads are created as needed, and previously constructed
     * threads are reused when they are available. Idle threads are kept in the pool for one minute. After
     * the one minute wait, the idle threads are removed from the pool. This executor
     * is suitable for applications that launch many short-lived concurrent tasks.
     *
     * It uses a SynchronousQueue to store tasks to be executed in the pool. The basic idea of synchronous handoff is
     * simple and yet counter-intuitive: One can queue an item if and only if another thread takes that item at the
     * same time. In other words, the SynchronousQueue can not hold any tasks whatsoever. Suppose a new task comes in.
     * If there is an idle thread waiting on the queue, then the task producer hands off the task to that thread.
     * Otherwise, since the queue is always full, the executor creates a new thread to handle that task.
     *
     * The cached pool starts with zero threads and can potentially grow to have Integer.MAX_VALUE threads.
     * Practically, the only limitation for a cached thread pool is the available system resources.
     *
     * SynchronousQueue can be seen as a special blocking queue with size of 1. For more details on their difference
     * https://stackoverflow.com/questions/5102570/implementation-of-blockingqueue-what-are-the-differences-between-synchronousque
     *
     * Check ExecutorExample.exp5()
     */

    /** 9.4.3.4 Fork/JoinPool executor,
     *
     * The ForkJoinPool is a special thread pool which is designed to work well with fork-and-join task splitting. To
     * understand better Fork/Join Pool executor, we need to know first how fork/join works. So this paragraph will
     * be discussed in 9.9.3
     *
     */

    /** 9.4.3.5 Schedule thread executors
     *
     * These executors use the four thread pool model which we described above, they just allow us to add scheduling
     * features.
     *
     * They use DelayQueue to store tasks. DelayQueue is a blocking queue that could be used in producer-consumer
     * programs. It has a very useful characteristic, when the consumer wants to take an element from the queue,
     * they can take it only when the delay for that particular element has expired.
     *
     * For example, newSingleThreadScheduleExecutor() creates a single-threaded executor that can schedule tasks to
     * execute after a given delay, or to execute periodically. Consider using this executor if you want to schedule
     * tasks to execute sequentially.
     *
     * newScheduledThreadPool(int corePoolSize) creates an executor that can schedule tasks to execute after
     * a given delay, or to execute periodically. Consider using this executor if you want to schedule tasks to
     * execute concurrently.
     *
     * Check ExecutorExample.exp8(); We create a scheduledThreadPool with 3 thread.
     * */

    /** 9.4.3.6 WorkStealingPool executor
     *
     * Since Java 8, Executors adds a new static factory method newWorkStealingPool(int pLevel), it creates a work-stealing
     * thread pool and returns it. The pLevel(optional) is the parallelism level of the pool. If not specified, it will
     * use the number of current available processors as its target parallelism level.
     *
     * If you check the source code in Executor
     * public static ExecutorService newWorkStealingPool(int parallelism) {
     *     return new ForkJoinPool(parallelism,
     *                                                     ForkJoinPool.defaultForkJoinWorkerThreadFactory,
     *                                                     null, true);
     * }
     * You can notice, this method will create a forkJoinPool with some parameters
     * - parallelism is the parallelism level of this forkJoinPool, if you don't specify it, it will be
     *        replaced by the default value Runtime.getRuntime().availableProcessors().
     * - defaultForkJoinWorkerThreadFactory is the default factory for creating new threads in forkJoinPool
     * - null is the default value for handler(the handler for internal worker threads that terminate due to
     *        unrecoverable errors encountered while executing tasks.
     * - true sets this pool in asyncMode(It establishes local first-in-first-out scheduling mode for forked tasks
     *        that are never joined. This mode may be more appropriate than default locally stack-based mode in
     *        applications in which worker threads only process event-style asynchronous tasks.
     *        For default value, use false.
     *
     * So to conclude, the newWorkStealingPool provided in Java 8 is not new at all — it just provides a level
     * of abstraction over ForkJoinPool. As ForkJoinPool, workStealing pool is used to optimize big computational
     * recursive tasks.
     *
     * Important Note:
     * 1. workStealingPool uses first-in, first-out approach (FIFO) queue configuration. Fork/Join common pool uses LIFO
     * 2. workStealingPool cannot run recursiveTask/Action. To do that, we need to cast it back to ForkJoin pool
     * 3. Can't find the reason why we need this pool.
     *
     * Check ExecutorExample.exp7(); we create a workStealingThread pool, use it to run a normal runnable task, then
     * cast it back to a ForkJoinPool to run ForkJoinTask.
     * */

    /** 9.4.3.7 Customize thread executors
     *
     * All the example above, we have used the static factory methods to create thread pool executors. These methods
     * call the real threadPoolExecutor constructors with some default parameters, for example the newFixedThreadPool
     * method in the Executors class are shown in the following code.
     * public static ExecutorService newFixedThreadPool(int nThreads) {
     *     return new ThreadPoolExecutor(nThreads, nThreads,
     *                                   0L, TimeUnit.MILLISECONDS,
     *                                   new LinkedBlockingQueue<Runnable>());
     * }
     *
     * In case the factory methods do not meet your need, you can construct an executor directly as an instance of
     * either ThreadPoolExecutor or ScheduledThreadPoolExecutor, which gives you additional options such as pool size,
     * on-demand construction, keep-alive times, and the tasks queue implementation.
     *
     * Another reason we need to build our own threadPool is that, in some situation CachedThreadPool will creat thread
     * without stop as more tasks come in. For FixedThreadPool, the tasks will be stored in the blocking queue. Many
     * threads or many tasks in queue, they will consume a lot of memory for creating threads or queuing tasks.
     * Cached thread pools will also incur a lot of processor context switches.
     *
     * */

    /** 9.4.4 ThreadPoolExecutor
     *
     * ThreadPoolExecutor is essential for building your own thread pool implementation. Most of the existing thread
     * pool implementation use it as the base. So it's important to know how it works
     * You can find full doc https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ThreadPoolExecutor.html#allowCoreThreadTimeOut-boolean
     *
     * We only highlight some key parameters.
     *
     * The ThreadPoolExecutor's constructor can take following parameters
     *      * public ThreadPoolExecutor(
     *      *   int corePoolSize,
     *      *   int maximumPoolSize,
     *      *   long keepAliveTime,
     *      *   TimeUnit unit,
     *      *   BlockingQueue<Runnable> workQueue,
     *      *   RejectedExecutionHandler handler
     *      * )
     *
     * - corePoolSize: It determines the initial size of the thread pool. Usually, the executor makes sure that the
     *               thread pool contains at least corePoolSize number of threads. However, it's possible to have
     *               fewer threads if we enable the allowCoreThreadTimeOut parameter
     * - maximumPoolSize: When all threads are busy and task queue becomes full, the executor can add more threads to
     *               the thread pool. The maximumPoolSize puts an upper bound on the number of threads a thread pool
     *               can potentially contain. When those threads remain idle for some time, the executor can remove
     *               them from the pool. Hence, the pool size can shrink back to its core size.
     * - keepAliveTime: When the number of threads is greater than the core, this is the maximum time that excess
     *               idle threads will wait for new tasks before terminating. A time value of zero will cause excess
     *               threads to terminate immediately after executing tasks.
     * - unit: The time unit for the keepAliveTime argument
     * - workQueue: When all core threads are busy, the executor adds the new tasks to a queue. There are three
     *              different approaches for queueing:
     *              1. Unbounded Queue: The queue can hold an unlimited number of tasks. Since this queue never fills
     *                 up, the executor ignores the maximum size. The fixed size and single thread executors both use
     *                 this approach.
     *              2. Bounded Queue: As its name suggests, the queue can only hold a limited number of tasks. As a
     *                 result, the thread pool would grow when a bounded queue fills up.
     *              3. Synchronous Handoff: Quite surprisingly, this queue can't hold any tasks! With this approach,
     *                 we can queue a task if and only if there is another thread picking the same task on the other
     *                 side at the same time. The cached thread pool executor uses this approach internally.
     * - handler: When all threads are busy, and the internal queue fills up, the executor becomes saturated. An
     *            instance of RejectedExecutionHandler can define thread pool actions once it hits saturation.
     *            Java provides some predefined policies:
     *            1. Abort policy: It's the default policy of a thread pool executor. It causes the executor to
     *                             throw a RejectedExecutionException when saturation reached.
     *            2. Caller-Runs Policy: Instead of running a task asynchronously in another thread, this policy makes
     *                             the caller thread execute the task
     *            3. Discard Policy: It silently discards the new task when it fails to submit it
     *            4. Discard-Oldest Policy: It first removes a task from the head of the queue, then re-submits the
     *                             new task.
     *            5. Custom Policy: It's also possible to provide a custom saturation policy just by implementing
     *                            the RejectedExecutionHandler interface. In MySaturationPolicy class, we implement
     *                            a policy which we will increase the size of queue by 1 and add the new tasks
     * ThreadPool behaviour:
     * -when the number of thread in the thread pool is less than the corePoolSize，新提交任务将创建一个新线程执行任务，
     *   即使此时线程池中存在空闲线程。
     * - when the number of thread in the thread pool is equal to the corePoolSize，新提交任务将被放入workQueue中，
     *   等待线程池中任务调度执行
     * - 当workQueue已满，且maximumPoolSize>corePoolSize时，新提交任务会创建新线程执行任务
     * - 当提交任务数超过maximumPoolSize时，新提交任务由RejectedExecutionHandler处理, based on the default policy(can be changed)
     * - 当线程池中超过corePoolSize线程，空闲时间达到keepAliveTime时，关闭空闲线程
     * - 当设置allowCoreThreadTimeOut(true)时，线程池中corePoolSize线程空闲时间达到keepAliveTime也将关闭
     *
     * Check ExecutorExample.exp9(); We create a custom thread pool with threadPoolExecutor, it has one initial thread,
     * the max thread number is 1, discard oldest policy.
     *
     * You can also implement your own thread pool executors.
     * Check ExecutorExample.exp6(); we use a self implemented thread pool to run 10 tasks. The core of the thread pool
     * is a blocking queue which stores the submitted tasks, and a list of thread(ie. ThreadForMyThreadPool)
     * */

    /** 9.4.5 Shut down an executor service
     *
     * There are two methods which we can use to shutdown an executor service.
     * - shutdown(): will just tell the executor service that it can't accept new tasks, but the already submitted
     *         tasks continue to run
     * - shutdownNow(): will do the same AND will try to cancel the already submitted tasks by interrupting the
     *         relevant threads. Note that if your tasks ignore the interruption, shutdownNow will behave exactly
     *         the same way as shutdown.
     *
     * */

    /***************************************** 9.5 The TimeUnit Enumeration **********************************/

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

    /***************************************** 9.6 The Concurrent Collections **********************************/

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

     /** 9.6.1 Concurrent collections VS Synchronized Collection (created via Synchronized Wrappers)
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

     /** 9.6.2 Concurrent collections thread safety mechanisms
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

    /************************************************* 9.7 Locks **********************************************/

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

    /** 9.7.1 ReentrantLock
     *
     * java.util.concurrent.locks supplies an implementation of Lock interface called ReentrantLock. It is a lock
     * that can be repeatedly entered by the thread that currently holds the lock. Of course, in the case of a thread
     * reentering a lock, all calls to lock() must be offset by an equal number of calls to unlock(). Otherwise, a
     * thread seeking to acquire the lock will suspend until the lock is not in use.
     *
     * Check LockExample.exp1();
     * */

    /** 9.7.2 ReentrantReadWriteLock
     * java.util.concurrent.locks also defines the ReadWriteLock interface. This interface specifies a lock that
     * maintains separate locks for read and write access. This enables multiple locks to be granted for readers
     * of a resource as long as the resource is not being written. ReentrantReadWriteLock provides an
     * implementation of ReadWriteLock.
     * Check LockExample.exp2(); we use read write lock to separately.
     * */

    /** 9.7.3 StampedLock
     * It does not implement the Lock or ReadWriteLock interfaces. However, It provides a mechanism that enables
     * aspects of it to be used like a Lock or ReadWriteLock.
     * */

    /********************************************* 9.8 Atomic Operations ******************************************/

    /*
    * An atomic operation is an operation which is performed as a single unit of work without the possibility of
    * interference from other operations. The Java language specification guarantees that reading or writing a
    * variable is an atomic operation(unless the variable is of type long or double ). Operations variables of type
    * long or double are only atomic if they declared with the volatile keyword.
    *
    * Assume i is defined as int. The i++ (increment) operation it not an atomic operation in Java. The i++ operation
    * first reads the value which is currently stored in i (atomic operations) and then it adds one to it
    * (atomic operation). But between the read and the write the value of i might have changed.
    *
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
    *
    * Check FizzBuzz example in atomic package, we use atomic integer as a shared index which four threads can update it.
    * */


    public static void main(String[] args){

        /** Executor*/
        /*FixedThreadPool executor*/
         // ExecutorExample.exp1();
       // ExecutorExample.exp2();
       // ExecutorExample.exp3();

        /* SingleThreadPool*/
       // ExecutorExample.exp4();

        /* CachedThreadPool*/
       // ExecutorExample.exp5();

        /* ScheduledThreadPool*/
       // ExecutorExample.exp8();

        /* custom build Thread pool*/
       // ExecutorExample.exp9();

        /* self implemented thread pool*/
       // ExecutorExample.exp6();

        /* workStealingThreadPool*/
       ExecutorExample.exp7();

        /** ConcurrentCollection */
        // thread safe collection
        //  ConcurrentCollectionsExample.exp1();
        // not safe list
      //  ConcurrentCollectionsExample.exp2();

        /** Lock*/
        //reentrantLock
         //LockExample.exp1();

        //reentrantReadWriteLock
       // LockExample.exp2();

        /** Atomic operation*/
       // AtomicExample.exp1();

    }
}
