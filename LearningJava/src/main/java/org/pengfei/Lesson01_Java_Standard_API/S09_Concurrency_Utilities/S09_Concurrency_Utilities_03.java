package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities;

import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.ExecutorExample;

public class S09_Concurrency_Utilities_03 {


    /************************ 9.9 Parallel Programming via the Fork/Join Framework *********************************/

    /* Important note, there are many critiques on the Fork/Join performance. Think carefully, if you want to use it.
     * Read this http://coopsoft.com/ar/CalamityArticle.html. The work-stealing principal is inefficient
     * compare to scatter-gather principal.
     *
     * */

    /*
    * Parallel programming is the name commonly given to the techniques that take advantage of computers that contain
    * two or more processors(multi-core). JDK 7 added new classes and interfaces that support parallel programming.
    * They are commonly referred to as the Fork/Join Framework which is defined in the java.util.concurrent package.
    *
    * We call this framework Fork/Join, because it uses the fork and join principle, which consists of two steps
    * which are performed recursively. These two steps are the fork step and the join step.
    *
    * The fork step can fork (split) a task into smaller sub-tasks recursively, if the splitting of sub-tasks
    * reached a point(threshold), it stops splitting ans start to compute each sub-task which can be executed
    * concurrently. There is an overhead to splitting up a task into sub-tasks, so for small amounts of work this
    * overhead may be greater than the speedup achieved by executing sub-tasks concurrently.
    *
    * The join step waits and joins(merge) the result of sub-tasks to the final result. Once the sub-tasks have finished
    * executing, the task may join all the results into one result. Of course, not all types of tasks may return a
    * result. If the tasks do not return a result then a task just waits for its sub-tasks to complete. No result
    * merging takes place then.
    *
    * The Fork/Join framework enhances multi-threaded programming in two important ways:
    * 1. It simplifies the creation and use of multiple threads.
    * 2. It automatically makes use of multiple processors.
    * As a result, Fork/join is recommended for multi-threading when parallel processing is desired in a multi-core
    * running environment.
    *
    * It's important to point out the distinction between traditional multi-threading and parallel programming. In the
    * past, most computers has a single CPU and multi-threading was primarily used to take advantage of cpu idle time.
    * On a single-CPU system, multi-threading is used to allow two or more tasks to share the CPU, so one can execute
    * while another is waiting for input. When multi-core cpus are present, it is possible to execute portions
    * of a program simultaneously, with each part executing on its own CPU. This can significantly speed up the
    * execution of some types of operations, such as sorting, transforming, or searching in large array.
    *
    *
    * */

    /** 9.9.1 The Main Fork/Join Classes
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
     *
     *                  ForkJoinPool manages the execution of its threads using an approach called work-stealing. Each
     *                  worker thread maintains a queue of tasks. A worker thread gets tasks from the head of its own
     *                  deque. When it is empty, the thread takes a task from the tail of the deque of another busy
     *                  thread or from the global entry queue. This adds to overall efficiency and help maintain a
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
     * 9.9.1.1 A general architecture on how they work together:
     * 1. Initiate a ForkJoinPool (executor) which will manage the execution of ForkJoinTasks.
     * 2. Create a ForkJoinTask which can resolve your problem in a parallel way. ForkJoinTask is an abstract class
     *    that is extended by the abstract classes RecursiveAction and RecursiveTask.
     * 3. Submit the ForkJoinTask to the ForkJoinPool
     *
     * 9.9.1.2 Submitting tasks to the ForkJoinPool:
     * - submit(task) and execute(task): submit and execute are called by using the reference of the threadPoll. After
     *      this call, the threadPool will run the task. But to get the result, we need a manual joining. For example
     *      forkJoinPool.execute(customRecursiveTask);
     *      int result = customRecursiveTask.join();
     * - invoke() and invokeAll(): The invoke() method forks the task and waits for the result, and does not need any
     *      manual joining. The invokeAll() method is the most convenient way to submit a sequence of ForkJoinTasks
     *      to the ForkJoinPool. It takes tasks as parameters (two tasks, var args, or a collection), forks then
     *      returns a collection of Future objects in the order in which they were produced.
     * - fork() and join(): The fork() method submits a task to a pool, but it doesn't trigger its execution. The
     *      join() method must be used for this purpose. In the case of RecursiveAction, the join() returns nothing
     *      but null; for RecursiveTask<V>, it returns the result of the task's execution. For example,
     *      customRecursiveTaskFirst.fork();
     *      result = customRecursiveTaskLast.join();
     *
     * You can call ForkJoinTask methods such as fork() or invoke() on the task from outside its computational portion.
     * In this case, the common poll will automatically be used. It means fork() and invoke() will start a task using
     * the common pool if the task is not already running within a ForkJoinPool.
     * */

    /** 9.9.2 Divide and Conquer Strategy
     *
     * This strategy breaks down problems or tasks into sub-tasks recursively. The sub-tasks are then solved
     * individually, then the sub-results are combined to form the result.
     *
     * Result solve(Problem problem) {
     *     if (problem is small)
     *         directly solve problem
     *     else {
     *         split problem into independent parts
     *         fork new subtasks to solve each part
     *         join all subtasks
     *         compose result from subresults
     *     }
     * }
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

    /** 9.9.3. The Fork/Join Pool executor and a Fork/Join Example
     * To run a fork/join task(e.g. RecursiveAction, RecursiveTask), we need a ForkJoinPool executor. We can build
     * a new one by using its constructor. Or call the static factory method to return the commonPool. As mentioned
     * above, After JDK8, no need to get the commonPool explicitly. When we call task.invoke(), the commonPool is
     * used automatically.
     * Check ForkJoinExample.exp1(); We used the constructor to create one forkJoinPool and static factory method to
     * get commonPool.
     * In ForkJoinExample.exp2(); We use a ForkJoinAction to transform an array of doubles into their square root. We
     * used the task.invoke() method to use the commonPool implicitly.
     *
     * Note ForkJoinPool.commonPool uses a last-in, first-out (LIFO) queue configuration
     * */

    /** 9.9.4 The impact of the level of Parallelism
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

    /** 9.9.5 A RecursiveTask example
     *
     * To create a task that returns a result, we need to use RecursiveTask. It works as RecursiveAction, the key
     * difference is the compute() method will return a result. Thus, you must aggregate the results, so that
     * the first invocation finishes, it returns the overall result. Another difference, you will typically start
     * a subtask by calling fork() and join() explicitly.
     *
     * check  ForkJoinExample.exp5(); Notice, we use fork to start the subtask, and join to wait the task to finish
     * and return the result.
     *
     * Check ForkJoinExample.exp6(), we can use invoke or compute to replace fork and join.
     *
     * */

    /** 9.9.6 Executing a Task Asynchronously
     *
     * To start a task asynchronously, use execute(), which is also defined by ForkJoinPool. It has the following two
     * forms:
     * - void execute(ForkJoinTask<?> task):
     * - void execute(Runnable task):
     *
     * Notice that the second form lets you specify a Runnable rather than a ForkJoinTask task. Thus, it forms a
     * bridge between Java’s traditional approach to multithreading and the Fork/Join Framework. It is important
     * to remember that the threads used by a ForkJoinPool are daemon. Thus, they will end when the main thread
     * ends. As a result, you may need to keep the main thread alive until the tasks have finished.
     * */

    /** 9.9.7 Cancelling a Task
     *
     * A task can be cancelled by calling boolean cancel(boolean interruptOK), which is defined by ForkJoinTask.
     * It returns true if the invoking task is cancelled. It returns false if the task has ended or can't be cancelled.
     * At this time, the interruptOK parameter is not used by the default implementation. In general, cancel() is
     * intended	to be called from code outside the task because a task can easily cancel itself by returning.
     *
     * You can determine if a task has been cancelled by calling final boolean isCancelled(). It returns true if
     * the invoking task has been cancelled prior to completion and false otherwise.
     * */

    /** 9.9.8 Determining a Task's completion status
     *
     * In addition to isCancelled(), which was just described, ForkJoinTask includes two other methods that you can use
     * to determine a task’s completion status:
     * - final boolean isCompletedNormally(): It returns true if the invoking task completed normally, that is, if
     *                it did not throw an exception and it was not cancelled via a call to cancel().
     * - final boolean isCompletedAbnormally(): It returns true if the invoking task completed because it was
     *                cancelled or threw an exception.
     * */

    /** 9.9.9 Restarting a Task
     *
     * Normally, you cannot return a task. In other words, once a task completes, it cannot be restarted. However,
     * you can reinitialize the state of the task (after it has completed) so it can be run again. This is done by
     * calling reinitialize(). This method resets the state of the invoking task. However, any modification
     * made to any persistent data that is operated upon by the task will not be undone.
     * */

    /** 9.9.10 More Features of ForkJoinTask
     *
     * In this section, we just see some basics of the ForkJoin Framework, it has many additional capacities that
     * give you extended control over concurrency. I will list some of these features here.
     *
     * ForkJoinTask class features
     * - You can determine if your code is executing inside a task by calling inForkJoinPool().
     * - You can convert a Runnable or Callable object into a ForkJoinTask by using the adapt() method defined by
     *   ForkJoinTask.
     * - You can obtain an approximate count of the number of tasks that are in the queue of the invoking thread by
     *   calling getQueuedTaskCount().
     * - You can obtain an approximate count of	how	many tasks the invoking thread has in its queue that are in excess
     *   of the number of other threads in the pool that might "steal" them, by calling getSurplusQueuedTaskCount()
     *   Remember, in the Fork/Join Framework, work-stealing is one way in which a high level of efficiency is obtained.
     * - To avoid task returning value or throwing exceptions, we can use quietlyJoin() and quietlyInvoke(). They works
     *   like normal join() and invoke() except they don't return values or throw exceptions.
     * - You can attempt to "un-invoke" (in other words, unschedule) a task by calling tryUnfork().
     * - We can use a short integer value to tag a task(setForkJoinTaskTag()), and find a task by using its tag(
     *   getForkJoinTaskTag())
     * - ForkJoinTask implements Serializable. Thus, it can be serialized. However, serialization is not used during
     *    execution.
     *
     * ForkJoinPool class features
     * - The ForkJoinPool's toString() method prints a "user-friendly" synopsis of the state of the pool. Check
     *   ForkJoinExample.exp7();
     * - You can determine if a pool is currently idle by calling isQuiescent(). It returns true if the pool has
     *   no active threads.
     * - You can obtain the number of worker threads currently in the pool by calling getPoolSize().
     * - You can obtain an approximate count of the active threads in the pool by calling getActiveThreadCount().
     * - To shut down a pool, call shutdown(). Currently active tasks will still be executed, but no new tasks can
     *   be started.
     * - To	stop a pool immediately, call shutdownNow(). In this case, an attempt is made to cancel currently active
     *   tasks. (It is important to point out, however, that neither of	the two shutdown() methods affects
     *   the common pool.)
     * - You can determine if a pool is shut down by calling isShutdown(). It returns true if the pool has been shut
     *   down and false otherwise.
     * - To	determine if the pool has been shut down and all tasks have been completed, call isTerminated().
     * */

    /** 9.9.11 Some Fork/Join Tips
     *
     * Here are a few tips to help you avoid some of the more troublesome pitfalls associated with using the
     * Fork/Join Framework:
     * 1. Avoid using a sequential threshold that is too low. In general, erring on the high side is better than erring
     *    on the low side. If the threshold is too low, more time can be consumed generating and switching tasks
     *    than in processing the tasks.
     * 2. Usually it is best to use the default level of parallelism(number of core in your current system.). If you
     *    specify a smaller number, it may significantly reduce the benefits of using the Fork/Join Framework.
     * 3. In general, a ForkJoinTask should not use synchronized methods or synchronized blocks of code. Also,
     *    you will not normally want to have the compute() method use other types of synchronization, such as a
     *    semaphore. (The Phaser can, however, be used when appropriate because it is compatible with the
     *    fork/join mechanism.) Remember, the main idea behind a ForkJoinTask is the divide-and-conquer strategy. Such
     *    an approach does not normally lend itself to situations in which outside synchronization is needed.
     * 4. Also, avoid situations in which substantial blocking will occur through I/O. Therefore, in general, a
     *    ForkJoinTask will not perform I/O. Simply put, to best utilize the Fork/Join Framework, a task should
     *    perform a computation that can run without outside blocking or synchronization.
     * 5. Except under unusual circumstances, do not make assumptions about the execution environment that your code
     *    will run in. This means you should not assume that some specific number of processors will be available, or
     *    that the execution characteristics of your program won’t be affected by other processes running at the same
     *    time.
     * */

    /************************ 9.10 Concurrency Utilities VS Java's Traditional Approach  *****************************/

    /*
    * Given the	power and flexibility found in the concurrency utilities, it is natural to ask the following question:
    * Do they replace Java’s traditional approach to multithreading and synchronization?
    *
    * The answer is a resounding no! The original support for multithreading and the built-in synchronization
    * features are still the mechanism that should be employed for many, many Java programs. For example, synchronized,
    * wait(), and notify() offer elegant solutions to a wide range of problems. However, when extra control is needed,
    * the concurrency utilities are available to handle the chore. Furthermore, the Fork/Join Framework offers a
    * powerful way to integrate parallel programming techniques into your more sophisticated applications.
    * */


    public static void main(String[] args){

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
       // ForkJoinExample.exp4(0);
        // ForkJoinExample.exp4(12);

        // use RecursiveAction
       // ForkJoinExample.exp5();

        // use invoke or compute to start the task
       // ForkJoinExample.exp6();

        // get ForkJoinPool state
        // ForkJoinExample.exp7();
    }
}
