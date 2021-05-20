package org.pengfei.Lesson00_Java_Basics.S10_MultiThread;

import org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source.MultiThreadExp;

public class S10_MultiThread {
    /******************************************** 10. Introduction *******************************************/

    /*
    * A multi-threaded program contains two or more parts that can run concurrently. Each part of such a program is
    * called a thread, and each thread defines a separate path of execution. In this section, we will learn the
    * fundamentals of multi-threading. We will use another full lesson to learn "Java concurrency programing". In this
    * section, we will see the following key concepts:
    * - Understand multi-threading fundamentals
    * - Know the Thread class and the Runnable interface
    * - Create a thread
    * - Create multiple threads
    * - Determine when a thread ends
    * - Use thread priorities
    * - Understand thread synchronization
    * - Use synchronized methods
    * - Use synchronized blocks
    * - Communicate between threads
    * - Suspend, resume, and stop threads
    * - Java Volatile Keyword
    *
    * If you don't know the two major problems caused by multi-thread
    * - Visibility of Shared Objects: It occurs if thread A reads shared data which is later changed by thread B and
    *            thread A is unaware of this change.
    * - Race condition: It occurs if several thread access and change the same shared data at the same time.
    *
    * These two problems can lead to:
    * - Liveness failure: The program does not react anymore due to problems in the concurrent access of data,
    *          e.g. deadlocks.
    * - Safety failure: The program creates incorrect data.
    *
    * Java provide locks and synchronization to resolve the above problems.
    *
    * Read my wiki (id=employes:pengfei.liu:java:java_memory_model) for more details
    * */

    /*********************************** 10.1 Multi-threading fundamentals *******************************************/

    /*
    * There are two distinct types of multitasking: process­based and thread­based. It is important to understand
    * the difference between the two. A process is, in essence, a program that is executing. Thus, process­based
    * multitasking is the feature that allows your computer to run two or more programs concurrently. For example,
    * it is process­based multitasking that allows you to run the Java compiler at the same time you are using a text
    * editor or browsing the Internet. In process­based multitasking, a program is the smallest unit of code that can
    * be dispatched by the scheduler. A process runs independently and isolated of other processes. It cannot directly
    * access shared data in other processes. The resources of the process, e.g. memory and CPU time, are allocated to
    * it via the operating system.
    *
    * In a thread­based multitasking environment, the thread is the smallest unit of dispatchable code. This means
    * that a single program can perform two or more tasks at once. For instance, a text editor can be formatting
    * text at the same time that it is printing, as long as these two actions are being performed by two separate
    * threads. Although Java programs make use of process­based multitasking environments, process­based multitasking
    * is not under the control of Java. Multi-threaded multitasking is. A thread is a so called lightweight process.
    * It has its own call stack, but can access shared data of other threads in the same process. Every thread has
    * its own memory cache. If a thread reads shared data it stores this data in its own memory cache. A thread can
    * re-read the shared data.
    *
    * A principal advantage of multi-threading is that it enables you to write very efficient programs because it lets
    * you utilize the idle time that is present in most programs. As you probably know, most I/O devices, whether they
    * be network ports, disk drives, or the keyboard, are much slower than the CPU. Thus, a program will often spend a
    * majority of its execution time waiting to send or receive information to or from a device. By using
    * multi-threading, your program can execute another task during this idle time. For example, while one part of
    * your program is sending a file over the Internet, another part can be reading keyboard input, and still another
    * can be buffering the next block of data to send.
    *
    * Java’s multi-threading features work in both multicore and single core system. In a single­core system,
    * concurrently executing threads share the CPU, with each thread receiving a slice of CPU time. Therefore,
    * in a single­core system, two or more threads do not actually run at the same time, but idle CPU time is utilized.
    * However, in multi-processor/multi-core systems, it is possible for two or more threads to actually execute
    * simultaneously. In many cases, this can further improve program efficiency and increase the speed of certain
    * operations.
    *
    * A thread can be in one of several states:
    * - Running: Thread is under execution.
    * - Ready: It's ready to run as soon as it gets CPU time.
    * - Suspended: A running thread can be suspended, which is a temporary halt to its execution.
    * - Resumed: A suspended thread can be resumed.
    * - Blocked: A thread can be blocked when waiting for a resource.
    * - Terminated: A thread can be terminated, in which case its execution ends and cannot be resumed.
    *
    * Along with thread­based multitasking comes the need for a special type of feature called synchronization,
    * which allows the execution of threads to be coordinated in certain well­defined ways. Java has a complete
    * subsystem devoted to synchronization, we will also discuss this key features in this section.
    * */
    /******************************* 10.2 The thread class and runnable interface ************************************/

    /*
    * Java’s multi-threading system is built upon the Thread class and its companion interface, Runnable. Both are
    * packaged in java.lang. Thread encapsulates a thread of execution. To create a new thread, your program will
    * either extend Thread or implement the Runnable interface.
    *
    * The Thread class defines several methods that help manage threads. Here are some of the more commonly used ones:
    * - final String getName(): Obtains a thread's name
    * - final int getPriority(): Obtains a thread's priority
    * - final boolean isAlive(): Determines whether a thread is still running.
    * - final void join(): Waits for a thread to terminate.
    * - void run(): Entry point for the thread.
    * - static void sleep(long milliseconds): Suspends a thread for a specified period of milliseconds.
    * - void start(): Starts a thread by calling its run() method.
    *
    * All processes have at least one thread of execution, which is usually called the main thread, because it is the
    * one that is executed when your program begins. Thus, the main thread is the thread that all of the preceding
    * example programs in this lesson have been using. From the main thread, you can create other threads.
    * */

    /************************************** 10.3 Creating a thread ************************************/
    /*
    * You create a thread by instantiating an object of type Thread. The Thread class encapsulates an object that is
    * runnable. As mentioned, Java defines two ways in which you can create a runnable object:
    * - You can implement the Runnable interface.
    * - You can extend the Thread class.
    *
    * Remember: Both approaches still use the Thread class to instantiate, access, and control the thread. The only
    * difference is how a thread­enabled class is created.
    *
    * The Runnable interface abstracts a unit of executable code. You can construct a thread on any object that
    * implements the Runnable interface. Runnable defines only one method called run(), which is declared like this:
    * public void run(). Inside run() method, you will define the code that constitutes the new thread. It is important
    * to understand that run() can call all other methods, use other classes, and declare local variables just like
    * the main thread. The only difference is that run() establishes the entry point for another, concurrent thread
    * of execution within your program. This thread will end when run() returns.
    *
    * After you have created a class that implements Runnable, you will instantiate an object of type Thread on an
    * object of that class. Thread defines several constructors. The one that we will use first is:
    * Thread(Runnable threadOb)
    * In this constructor, threadOb is an instance of a class that implements the Runnable interface. This defines
    * where execution of the thread will begin.
    *
    * Once created, the new thread will not start running until you call its start() method, which is declared within
    * Thread. In essence, start() executes a call to run().
    *
    * Check MultiThreadExp.exp1();, First MyThread implements Runnable. This means that an object of type MyThread is
    * suitable for use as a thread and can be passed to the Thread constructor. Notice the call to sleep(). The sleep()
    * method causes the thread from which it is called to suspend execution for the specified period of milliseconds.
    *
    * There is another important point, we must make sure the method who create the thread terminates after the child
    * thread terminates. In our example, we use sleep time to make sure of that. Later in this section, we will see
    * a better way for one thread to wait until another completes. Remove the main thread sleep code, you will see
    * main thread ends before child thread.
    *
    * One other point: In a multi-threaded program, you often will want the main thread to be the last thread to
    * finish running. As a general rule, a program continues to run until all of its threads have ended. Thus,
    * having the main thread finish last is not a requirement. It is, however, often a good practice to follow,
    * especially when you are first learning about threads.
    * */

    /** 10.3.1 Why main thread often finish last?
     * The main thread is a convenient place to perform the orderly shutdown of your
     * program, such as the closing of files. It also provides a well­defined exit point for
     * your program. Therefore, it often makes sense for it to finish last. Fortunately, as
     * you will soon see, it is trivially easy for the main thread to wait until the child
     * threads have completed.
     * */

    /** 10.3.2 Improve our first thread example
     * In the MyThread class, we create a field called "threadName" to hold the name of the thread. It's possible to
     * give a name to a thread when it is created, Thread class provide the following constructor:
     * Thread(Runnable threadObj, String name)
     * This constructor will build the thread with name as the name of the Thread. You can get the name of the Thread by
     * using the method final String getName()
     *
     * Giving a name provides two advantages:
     * 1. There is no need for you to use a separate variable to hold the name.
     * 2. The name of the thread will be available to any code that holds a reference to the thread.
     * A thread name can be modified via the method final void setName(String threadName)
     * */

    /** 10.3.3 Variation 1 :Create a thread with Thread obj inside
     * This variations can be more convenient to use. Because this kind of thread is ready to start as soon as the
     * MyThread constructor returns. You simply call start() on the Thread instance encapsulated by the Thead.
     * Check MultiThreadExp.exp2(); You can notice that the code to create and run MyThread is simpler.
     *
     * Factory Method
     * Check Lesson02_Effective_java_programming->S01_Creating_And_Destroying_Objects->staticFactoryMethod
     * */

    /** 10.3.4 Variation 2 : Extends Thread
     * In this variation, we extends the class Thread. You could notice, we don't need to create a Thread obj with
     * the runnable object inside. We just create a plain Thread obj.
     * */

    /** 10.3.5 Why Java provide two ways to create thread (Runnable vs Thread)
     * There are two reasons:
     * 1. Some Java programmers feel that classes should be extended only when they are being expanded or customized
     *    in some way. So, if you will not be overriding any of Thread’s other methods, it is probably best to simply
     *    implement Runnable.
     * 2. By Implementing Runnable, you enable your thread to inherit a class other than Thread
     * */

    /************************************** 10.4 Creating Multiple threads ************************************/

    /*
    * We can spawn as many threads as we need in a program. Check MultiThreadExp.exp4(); As you can see, once started,
    * all three child threads share the CPU. Notice that in this run the threads are started in the order in which
    * they are created. However, this may not always be the case. Java is free to schedule the execution of threads
    * in its own way. Of course, because of differences in timing or environment, the precise output from the program
    * may differ, so don’t be surprised if you see slightly different results when you try the program.
    * */

    /** 10.4.1 Start() vs Run()
     *
     * In Java’s multi-threading concept, start() and run() are the two most important methods. Below are the two of the
     * differences between the Thread.start() and Thread.run() methods:
     * 1. New Thread creation: When a program calls the start() method, a new thread is created and then the run()
     *                         method is executed. But if we directly call the run() method then no new thread will be
     *                         created and run() method will be executed as a normal method call on the current calling
     *                         thread itself and no multi-threading will take place.
     * 2. Multiple invocation: In Java’s multi-threading concept, another most important difference between start()
     *                         and run() method is that we can’t call the start() method twice otherwise it will throw
     *                         an IllegalStateException whereas run() method can be called multiple times as it is just
     *                         a normal method calling.
     *
     * Check MultiThreadExp.exp12(), when we call run(), no new sub thread is created, the code in run() is executed
     * in main thread.
     * Check MultiThreadExp.exp13(), when we call start(), a new sub thread is created, the code in run() is executed
     * in the newly created child thread.
     * Check MultiThreadExp.exp14(), we try to call start() twice for a thread. An exception occurred when we start
     * the second call of start().
     * */

    /************************************** 10.5 Determining when a thread ends ************************************/
    /*
    * It is often useful to know when a thread has ended. For example, in the preceding examples, for the sake of
    * illustration, we used println to show when thread starts and ends. However, in real world situation, we need
    * better solutions, Fortunately, Thread provides two means by which you can determine if a thread has ended:
    * 1. Call isAlive() on the thread: It returns true if the thread is still running, returns false otherwise.
    * 2. Call join(): It suspend the execution of a thread until the joined thread execution finished. For example
    *                 MultiThreadExp.exp6();
    * */

    /** 10.5.1 isAlive example
     * Check MultiThreadExp.exp5(); We use isAlive to check if all three child threads ended or not. If not we use the
     * Thread.sleep to wait them.
     *
     * */

    /** 10.5.2 Join example
     * Another way to wait for a thread to finish is to call join. This method waits until the thread on which it is
     * called terminates. Its names comes from the concept of the calling thread waiting until the specified thread
     * joins it. Additional forms of join() allow you to specify maximum amount of time that you want to wait for
     * the specified thread to terminate.
     *
     * Check MultiThreadExp.exp6(); In the main thread, we create three child thread. Without the child.join(). The
     * main thread will finish without waiting for child. After adding child.join(). The main thread are suspended.
     * You can noticed in the outputs, the print statements are executed  after the calls to child.join() return,
     * which means child threads have finished executing.
     * */

    /************************************** 10.6  Thread priorities ************************************/

    /*
    * Each thread has associated with it a priority setting. A thread’s priority determines, in part, how much CPU
    * time a thread receives relative to the other active threads. In general, over a given period of time, low­priority
    * threads receive little. High­priority threads receive a lot. As you might expect, how much CPU time a thread
    * receives has profound impact on its execution characteristics and its interaction with other threads currently
    * executing in the system.
    *
    * It is important to understand that factors other than a thread’s priority also affect how much CPU time a thread
    * receives. For example, if a high­priority thread is waiting on some resource, perhaps for keyboard input, then
    * it will be blocked, and a lower­priority thread will run. However, when that high­priority thread gains access
    * to the resource, it can preempt the low­priority thread and resume execution. Another factor that affects the
    * scheduling of threads is the way the operating system implements multitasking(see 10.6.1). Thus, just because
    * you give one thread a high priority and another a low priority does not necessarily mean that one thread
    * will run faster or more often than the other. It’s just that the high­priority thread has greater potential
    * access to the CPU.
    *
    * When a child thread is started, its priority setting is equal to that of its parent thread. You can change a
    * thread’s priority by calling final void setPriority(int level), which is a method of Thread.
    *
    * Here, level specifies the new priority setting for the calling thread. The value of level must be within the
    * range MIN_PRIORITY and MAX_PRIORITY. Currently, these values are 1 and 10, respectively. To return a thread to
    * default priority, specify NORM_PRIORITY, which is currently 5. These priorities are defined as static final
    * variables within Thread.
    *
    * You can obtain the current priority setting by calling the final int getPriority() method of Thread.
    * Check MultiThreadExp.exp7(), you could notice, the high priority thread has highest count(not always true).
    * The exact output produced by this program will depend upon a number of factors, including the speed of your CPU,
    * the number of CPUs in your system, the operating system you are using, and the number and nature of other tasks
    * running in the system. Thus, it is actually possible for the low­priority thread to get the most CPU time if the
    * circumstances are right
    * */

    /** 10.6.1 Does the OS implementation of multitasking affect how much CPU time a thread receives?
     * Aside from a thread’s priority setting, the most important factor affecting thread execution is the way the
     * operating system implements multitasking and scheduling. Some operating systems use preemptive multitasking in
     * which each thread receives a time slice, at least occasionally. Other systems use non preemptive scheduling in
     * which one thread must yield execution before another thread will execute. In non preemptive systems, it is easy
     * for one thread to dominate, preventing others from running.
     * */

    /** 10.6.2 Daemon thread
     *
     * It is a low priority thread (in context of JVM) that runs in background to perform tasks such as
     * garbage collection (gc) etc., they do not prevent the JVM from exiting (even if the daemon thread itself is running)
     * when all the user threads (non-daemon threads) finish their execution. JVM terminates itself when all user threads
     * (non-daemon threads) finish their execution, JVM does not care whether Daemon thread is running or not,
     * if JVM finds running daemon thread (upon completion of user threads), it terminates the thread and after that
     * shutdown itself.
     *
     *
     * Properties of Daemon threads:
     * 1. A newly created thread inherits the daemon status of its parent. That’s the reason all threads created
     *    inside main method (child threads of main thread) are non-daemon by default, because main thread is
     *    non-daemon. However you can make a user thread to Daemon by using setDaemon() method of thread class.
     *
     *    Just a quick note on main thread: When the JVM starts, it creates a thread called “Main”. Your program will
     *    run on this thread, unless you create additional threads yourself. The first thing the “Main” thread does
     *    is to look for your static void main (String args[]) method and invoke it. That is the entry-point to your
     *    program. If you create additional threads in the main method those threads would be the child threads of
     *    main thread.
     * 2. Methods of Thread class that are related to Daemon threads:
     *      -- public void setDaemon(boolean status): This method is used for making a user thread to Daemon
     *              thread or vice versa. For example if I have a user thread t then t.setDaemon(true) would make
     *              it Daemon thread. On the other hand if I have a Daemon thread td then by calling
     *              td.setDaemon(false) would make it normal thread(user thread/non-daemon thread).
     *      -- public boolean isDaemon(): This method is used for checking the status of a thread. It returns true
     *              if the thread is Daemon else it returns false.
     * 3. setDaemon() method can only be called before starting the thread. This method would throw
     *        IllegalThreadStateException if you call this method after Thread.start() method.
     *
     *  Check MultiThreadExp.exp18(); We can set user defined thread to daemon thread.
     * */

        /************************************** 10.7 Synchronization and Locks ************************************/

        /*
        * When using multiple threads, it is sometimes necessary to coordinate the activities of two or more. The
        * process by which this is achieved is called synchronization. The most common reason for synchronization
        * is when two or more threads need access to a shared resource that can be used by only one thread at a time.
        * For example, when one thread is writing to a file, a second thread must be prevented from doing so at the
        * same time. Another reason for synchronization is when one thread is waiting for an event that is caused by
        * another thread. In this case, there must be some means by which the first thread is held in a suspended
        * state until the event has occurred. Then, the waiting thread must resume execution.
        *
        * Key to synchronization in Java is the concept of the "monitor", which controls access to an object. A monitor
        * works by implementing the concept of a lock. When an object is locked by one thread, no other thread can
        * gain access to the object. When the thread exits, the object is unlocked and is available for use by another
        * thread.
        *
        * All objects in Java have a monitor(lock). This feature is built into the Java language itself. Locks can
        * protect certain parts of the code to be executed by several threads at the same time. The simplest way of
        * locking a certain method or Java class is to define the method or class with the synchronized keyword.
        * The synchronized keyword in Java ensures:
        * - that only a single thread can execute a block of code at the same time
        * - that each thread entering a synchronized block of code sees the effects of all previous modifications
        *   that were guarded by the same lock.
        *
        *
        * */

        /** 10.7.1 Using synchronized methods
         * You can synchronize access to a method by modifying it with the synchronized keyword. When that method is
         * called, the calling thread enters the object’s monitor, which then locks the object. While locked, no other
         * thread can enter the method, or enter any other synchronized method defined by the object’s class. When
         * the thread returns from the method, the monitor unlocks the object, allowing it to be used by the
         * next thread. Thus, synchronization is achieved with virtually no programming effort on your part.
         *
         * Check MultiThreadExp.exp8(); We have two thread of class ThreadSynchronization which calls an object of
         * Class SumArray which contains a synchronized method sumArray(). In the output of this program, you can notice
         * Thread1 starts and terminates, then Thread2 starts and terminates. It means only one thread can access the
         * object "sa"'s synchronized method at one time. Other threads try to access are suspended util the lock is
         * released.
         *
         * Inside class ThreadSynchronization, we declare "sa" (object of class SumArray) as static, which means all
         * object of class ThreadSynchronization shared the unique "sa" object. Inside "sa", the method sumArray() is
         * synchronized and contains a sleep()(This is called to purposely allow a task switch to occur, if possible).
         * Because sumArray() is synchronized, it can be used by only one thread at a time. Thus, when the second child
         * thread begins execution, it can't enter sumArray() until after the first child thread is done with it. This
         * ensures that the correct result is produced.
         *
         * Try to remove the keyword synchronized from method sumArray(), and see what happen. In the output, you can
         * notice sumArray() is used concurrently by two threads, which causes it returns a wrong sum. The problem with
         * this is that the running total is stored in sum in the object "sa", which will be changed by each thread
         * that calls sa.sumArray(). Thus, when two threads call sa.sumArray( ) at the same time, incorrect results
         * are produced because sum reflects the summation of both threads, mixed together.
         *
         * Key points of a synchronized method:
         * - A synchronized method is created by using keyword "synchronized"
         * - For any given object, once a synchronized method has been called, the object is locked and no synchronized
         *   methods on the same object can be used by another thread of execution.
         * - Other threads trying to call an in-use synchronized object will enter a wait state until the object is
         *   unlocked
         * - When a thread leaves the synchronized method, the object is unlocked.
         * */

    /** 10.7.2 The synchronized block
     *
     * Although creating synchronized methods within classes that you create is an easy and effective means of
     * achieving synchronization, it will not work in all cases. For example, you might want to synchronize access
     * to some method that is not modified by synchronized. This can occur because you want to use a class that was
     * not created by you but by a third party, and you do not have access to the source code. Thus, it is not
     * possible for you to add synchronized to the appropriate methods within the class.
     *
     * To resolve synchronization problem in this kind of situation, we use the synchronized block. It has the
     * following general form:
     * synchronized(object-reference){
     *     // statement to be synchronized, it must contains the key method which you want to add the lock to
     *     // prevent concurrent access.
     * }
     * Here, object-reference is a reference to the object being synchronized. Once a synchronized block has been
     * entered, no other thread can call a synchronized method on the object referred to by object-reference until
     * the block has been exited.
     *
     * Check MultiThreadExp.exp9(); We use a SumArray without synchronized method. So SumArray can no longer control
     * the concurrent access of threads. We need to control the concurrent access inside each thread. Check the
     * ThreadSynchronizedBlock class. In the run method, we have a synchronized block which synchronize on a SumArray
     * object. This block encapsulates all statements which we need to synchronize of the SumArray.
     */

    /** 10.7.3 Concurrency utilities and Fork/Join framework
     *
     * The concurrency utilities, which are packaged in java.util.concurrent (and its subpackages), support concurrent
     * programming. Among several other items, they offer:
     * - synchronizers
     * - thread pools
     * - execution managers
     * - locks
     * that expand your control over thread execution. One of the most exciting features of the concurrent API is the
     * Fork/Join Framework.
     *
     * The Fork/Join Framework supports what is often termed parallel programming. This is the name commonly given to
     * the techniques that take advantage of computers that contain two or more processors (including multi-core systems)
     * by subdividing a task into sub-tasks, with each subtask executing on its own processor. As you can imagine,
     * such an approach can lead to significantly higher throughput and performance. The key advantage of the Fork/Join
     * Framework is ease of use; it streamlines the development of multi-threaded code that automatically scales to
     * utilize the number of processors in a system. Thus, it facilitates the creation of concurrent solutions to
     * some common programming tasks, such as performing operations on the elements of an array. The concurrency
     * utilities in general, and the Fork/Join Framework specifically, are features that you will want to explore
     * after you have become more experienced with multi-threading.
     * */

    /************************************** 10.8  Thread communication  ************************************/

    /*
    *
    * Consider the following situation. A thread called T is executing inside a synchronized method and needs access
    * to a resource called R that is temporarily unavailable. What should T do? If T enters some form of polling loop
    * that waits for R, T ties up the object, preventing other threads’ access to it. This is a less than optimal
    * solution because it partially defeats the advantages of programming for a multi-threaded environment. A better
    * solution is to have T temporarily relinquish control of the object, allowing another thread to run. When R
    * becomes available, T can be notified and resume execution. Such an approach relies upon some form of inter thread
    * communication in which one thread can notify another that it is blocked and be notified that it can resume
    * execution. Java supports inter thread communication with the wait(), notify(), and notifyAll() methods.
    *
    * The wait(), notify(), and notifyAll() methods are part of all objects because they are implemented by the
    * Object class. These methods should be called only from within a synchronized method or block.
    *
    * For example, When a thread is temporarily blocked from running, it calls wait(). This causes the thread to go
    * to sleep and the monitor for that object to be released, allowing another thread to use the object. At a
    * later point, the sleeping thread is awakened when some other thread enters the same monitor and calls notify(),
    * or notifyAll().
    *
    * Simply put, when we call wait() in a synchronized method or block, this forces the current thread to wait until
    * some other thread invokes notify() or notifyAll() on the same object. For this, the current thread must own the
    * object's monitor(lock). According to Javadocs, this can happen when:
    * - we've executed synchronized instance method for the given object
    * - we've executed the body of a synchronized block on the given object
    * - by executing synchronized static methods for objects of type Class
    *
    * Note that only one active thread can own an object's monitor at a time.
    *
    * wait() has the following variations:
    * - final void wait( ) throws InterruptedException : waits until notified.
    * - final void wait(long timeout) throws InterruptedException : waits until notified or until the specified period
    *                       of milliseconds has expired.
    * - final void wait(long timeout, int nanos) throws InterruptedException : waits until notified or until the timeout
    *                                          in milliseconds(1st arg) or nanoseconds(2nd arg).
    *
    * General forms ofr notify() and notifyAll()
    * final void notify(): resumes one waiting thread.
    * final void notifyAll(): notifies all threads, with the scheduler determining which thread gains access to the
    *                         object.
    *
    * In general, wait() normally waits until notify() or notifyAll() is called. There is a rare possibility that
    * the waiting thread could be awakened due to a spurious wakeup. We will not discuss this in this section.
    * Check https://en.wikipedia.org/wiki/Spurious_wakeup for spurious-wakeup
    * However, the Java API documentation recommends that because of the remote possibility of a spurious wakeup,
    * calls to wait() should take place within a loop that checks the condition on which the thread is waiting.
    * The following example shows this technique.
    * */

    /** 10.8.1 Wait, notify example
     * To understand the need for and the application of wait() and notify(), we will create a program that simulates
     * the ticking of a clock by displaying the words Tick and Tock on the screen. To accomplish this, we will create
     * a class called TickTock that contains two methods: tick() and tock(). The tick() method displays the word
     * "Tick", and tock() displays "Tock". To run the clock, two threads are created, one that calls tick() and one
     * that calls tock(). The goal is to make the two threads execute in a way that the output from the program
     * displays a consistent "Tick Tock"—that is, a repeated pattern of one tick followed by one tock.
     *
     * Check MultiThreadExp.exp10. The heart of this example is the TickTock class. It contains two synchronized method:
     * - tick(): prints "Tick", change state to ticked, notify tock, if state is ticked, thread waits
     * - tock(): prints "Tock", change state to tocked, notify tick, if state is tocked, thread waits.
     *
     * The running check code in these two methods is to provide a clean shutdown to the clock. The notify inside this
     * code is to notify the final call to wait() of tick or tock.
     *
     * The while loop that calls wait() checks the value of state, waiting for it to equal "tocked", which will be
     * the case only after the tock() method executes. As explained, using a while loop to check this condition
     * prevents a spurious wakeup from incorrectly restarting the thread. If state does not equal "tocked" when wait()
     * returns, it means that a spurious wakeup occurred, and wait( ) is simply called again.
     *
     * Try to remove all wait() and notify(), and check the output.
     *
     * We also add more examples. The FooBar example print FooBar Alternately by using two methods foo()(print foo)
     * and bar()(print bar). They need to be synchronized to avoid foofoo or barbar.
     *
     * In the sender_receiver example. The Sender is supposed to send a data packet to the Receiver. The Receiver
     * cannot process the data packet until the Sender is finished sending it. Similarly, the Sender mustn't attempt
     * to send another packet unless the Receiver has already processed the previous packet.
     *
     * In the printer_order example, we print first second third in order, no matter in which order the thread is
     * started.
     *
     * Note, in these examples,  we enclose wait() in a while Loop. Since notify() and notifyAll() randomly wakes up
     * threads that are waiting on this object's monitor, and not always appropriate threads are wake. Sometimes it
     * can happen that the thread is woken up, but the condition isn't actually satisfied yet. We need to put thread
     * in wait again. We can also define a check to save us from spurious wakeups – where a thread can wake up from
     * waiting without ever having received a notification.
     *
     *
     * */

    /** 10.8.2 Deadlock and race condition in multi-threaded program
     *
     * Deadlock is, as the name implies, a situation in which one thread is waiting for another thread to do something,
     * but that other thread is waiting on the first. Thus, both threads are suspended, waiting on each other, and
     * neither executes. This situation is analogous to two overly polite people, both insisting that the other step
     * through a door first!
     *
     * Avoiding deadlock seems easy, but it’s not. For example, deadlock can occur in roundabout ways. The cause of
     * the deadlock often is not readily understood just by looking at the source code to the program because
     * concurrently executing threads can interact in complex ways at run time. To avoid deadlock, careful programming
     * and thorough testing is required. Remember, if a multi-threaded program occasionally “hangs,” deadlock is the
     * likely cause. Check the printer_order example, try to modify the boolean first, second, third all to false.
     * The printer example enters a dead lock
     *
     *
     * A race condition occurs when two (or more) threads attempt to access a shared resource at the same time,
     * without proper synchronization. For example, one thread may be writing a new value to a variable while another
     * thread is incrementing the variable’s current value. Without synchronization, the new value of the variable will
     * depend upon the order in which the threads execute. (Does the second thread increment the original value or
     * the new value written by the first thread?) In situations like this, the two threads are said to be
     * “racing each other,” with the final outcome determined by which thread finishes first. Like deadlock, a
     * race condition can occur in difficult­to­discover ways. The solution is prevention: careful programming that
     * properly synchronizes access to shared resources.
     * Check MultiThreadExp.exp9(); if you remove the synchronized block, the sumArray enters a race condition.
     * */

    /************************** 10.9  Suspending, Resuming, and stopping a thread  ********************************/

    /*
    * It is sometimes useful to suspend execution of a thread. For example, a separate thread can be used to
    * display the time of day. If the user does not desire a clock, then its thread can be suspended. Whatever
    * the case, it is a simple matter to suspend a thread. Once suspended, it is also a simple matter to restart
    * the thread.
    *
    * The mechanisms to suspend, stop, and resume threads differ between early versions of Java and more modern
    * versions, beginning with Java 2. Prior to Java 2, a program used suspend(), resume(), and stop(), which are
    * methods defined by Thread, to pause, restart, and stop the execution of a thread. Suspend() was deprecated
    * by Java 2, because it can cause serious deadlock problems. As a result, resume() and stop() were also deprecated.
    *
    * Since you cannot now use the suspend(), resume(), or stop() methods to control a thread, you might at first be
    * thinking that there is no way to pause, restart, or terminate a thread. But, fortunately, this is not true.
    * Instead, a thread must be designed so that the run() method periodically checks to determine if that thread
    * should suspend, resume, or stop its own execution. Typically, this is accomplished by establishing two flag
    * variables: one for suspend and resume, and one for stop. For suspend and resume, as long as the flag is set to
    * “running,” the run() method must continue to let the thread execute. If this variable is set to “suspend,” the
    * thread must pause. For the stop flag, if it is set to “stop,” the thread must terminate.
    *
    * Check MultiThreadExp.exp11(); In the class ThreadSuspend, we use method suspendThread(), resumeThread(),
    * stopThread() to demonstrate how to suspend, resume and stop a thread. The key point is in the run() method of
    * your Thread, you need to test the state of your Thread, if state is suspend, then thread wait. if state is stopped
    * then end the run method(Thread ends after run ends)
    *  */

    /************************************* 10.10  Java Volatile Keyword  *****************************************/

    /*
    * The problem of variable visibility need to be resolved, if we have threads read and write a shared variable at
    * same time. Read my wiki (id=employes:pengfei.liu:java:java_memory_model), if you don't know why.
    *
    * The Java volatile keyword guarantees visibility of changes to variables across threads. In a multi-threaded
    * application where the threads operate on non-volatile variables, each thread may copy variables from main
    * memory into a CPU cache while working on them, for performance reasons. If your computer contains more than
    * one CPU, each thread may run on a different CPU. That means, that each thread may copy the variables into
    * the CPU cache of different CPUs.
    *
    * In a simple phrase, if a variable is declared with the volatile keyword then it is guaranteed that any
    * thread that reads the field will see the most recently written value. The volatile keyword will not perform any
    * mutual exclusive lock on the variable.
    *
    * With non-volatile variables there are no guarantees about when the Java Virtual Machine (JVM) reads data from
    * main memory into CPU caches, or writes data from CPU caches to main memory.
    *
    * Check MultiThreadExp.exp15(); We have a shared object which can be incremented by the Writer. The reader thread
    * reads the value of shared object. Try to remove the key word volatile of the shared object. Sometimes, the reader
    * does not get the latest updated value.
    *
    * In the scenario given above, where one thread (T1) modifies the counter, and another thread (T2) reads the
    * counter (but never modifies it), declaring the counter variable volatile is enough to guarantee visibility for
    * T2 of writes to the counter variable.
    *
    * But volatile is not enough in some situation. For example, if we have two writer which increment the counter
    * variable, then declaring the counter variable volatile would not have been enough. Check MultiThreadExp.exp16();
    *
    * The volatile keyword is guaranteed to work on 32 bit and 64 variables.
     * */

    /** 10.10.1 Full volatile Visibility Guarantee
     *
     * Actually, the visibility guarantee of Java volatile goes beyond the volatile variable itself. The visibility
     * guarantee is as follows:
     * - If Thread A writes to a volatile variable and Thread B subsequently reads the same volatile variable, then
     *   all variables visible to Thread A before writing the volatile variable, will also be visible to Thread B
     *   after it has read the volatile variable.
     * - If Thread A reads a volatile variable, then all all variables visible to Thread A when reading the volatile
     *   variable will also be re-read from main memory.
     *
     * Check MultiThreadExp.exp15(); Even only one field day is volatile, with full volatile visibility guarantee, the
     * year and month is treated also as volatile
     * */

    /** 10.10.2 The Java volatile Happens-Before Guarantee
     *
     * The JVM and the CPU are allowed to reorder instructions in the program for performance reasons, as long as the
     * semantic meaning of the instructions remain the same. However, instruction reordering present a challenge when
     * one of the variables is a volatile variable.
     *
     * For example, in method update() of class MyShareDate, if the reordering makes the  this.days   = days; happens
     * before year and month. According to full volatile visibility guarantee, the new values of year and month are
     * thus not properly made visible to other threads.
     *
     * To address the instruction reordering challenge, the Java volatile keyword gives a "happens-before" guarantee,
     * in addition to the visibility guarantee. The happens-before guarantee guarantees that:
     * - Reads from and writes to other variables cannot be reordered to occur after a write to a volatile variable,
     *   if the reads/writes originally occurred before the write to the volatile variable.
     * - The reads/writes before a write to a volatile variable are guaranteed to "happen before" the write to the
     *   volatile variable. Notice that it is still possible for e.g. reads/writes of other variables located
     *   after a write to a volatile to be reordered to occur before that write to the volatile. Just not the other
     *   way around. From after to before is allowed, but from before to after is not allowed.
     *
     * The happens-before guarantee assures that the visibility guarantee of the volatile keyword are being enforced.
     * */

    /** 10.10.3 The Java volatile limitation
     *
     * If a thread needs to first read the value of a volatile variable, and based on that value generate a new
     * value for the shared volatile variable, a volatile variable is no longer enough to guarantee correct
     * visibility. The short time gap in between the reading of the volatile variable and the writing of its
     * new value, creates an race condition where multiple threads might read the same value of the volatile
     * variable, generate a new value for the variable, and when writing the value back to main memory - overwrite
     * each other's values.
     *
     * The situation where multiple threads are incrementing the same counter is exactly such a situation where a
     * volatile variable is not enough. Check MultiThreadExp.exp16();
     *
     * When threads are both reading and writing to a shared variable, then using the volatile keyword for that is
     * not enough. You need to use a synchronized block in that case to guarantee that the reading and writing of the
     * variable is atomic. Reading or writing a volatile variable does not block threads reading or writing. For this
     * to happen you must use the synchronized keyword around critical sections.
     *
     * As an alternative to a synchronized block you could also use one of the many atomic data types found in the
     * java.util.concurrent package. For instance, the AtomicLong or AtomicReference or one of the others. See Lesson01_
     * Section 09 Concurrency_utilities for more details.
     *
     *
     * */

    /** 10.10.4 Performance Considerations of volatile
     *
     * Reading and writing of volatile variables causes the variable to be read or written to main memory.
     * Reading from and writing to main memory is more expensive than accessing the CPU cache. Accessing volatile
     * variables also prevent instruction reordering which is a normal performance enhancement technique. Thus, you
     * should only use volatile variables when you really need to enforce visibility of variables.
     * */

    /******************** 10.11  How to use multi-threading to improve efficiency of your program  *******************/

    /**
     * The key to effectively utilizing multi-threading is to think concurrently rather than serially. For example,
     * when you have two subsystems within a program that are fully independent of each other, consider making them
     * into individual threads. A word of caution is in order, however. If you create too many threads, you can
     * actually degrade the performance of your program rather than enhance it. Remember, overhead is associated with
     * context switching. If you create too many threads, more CPU time will be spent changing contexts than in
     * executing your program!
     *  */

    /************************** 10.12  Understand and control the main thread  ********************************/

    /**
     * All Java programs have at least one thread of execution, called the main thread, which is given to the program
     * automatically when it begins running. So far, we have been taking the main thread for granted. In the code
     * fragment 10.11, you will see that the main thread can be handled just like all other threads.
     *
     * To access the main thread, you must obtain a Thread object that refers to it. You do this by calling the
     * currentThread( ) method, which is a static member of Thread. Its general form is shown here:
     * static Thread currentThread( )
     * This method returns a reference to the thread in which it is called. Therefore, if you call currentThread()
     * while execution is inside the main thread, you will obtain a reference to the main thread. Once you have this
     * reference, you can control the main thread just like any other thread.
     *
     * Deadlock issue : If you try to do mainThread.join() inside main thread, the program will never end. Because it
     * wait itself to finish to start running.
     * */

    /************************** 10.13 Limits of concurrency gains ********************************/

    /*
    * Concurrency promises to perform certain task faster as these tasks can be divided into sub-tasks and these
    * sub-tasks can be executed in parallel. Of course the runtime is limited by parts of the task which can be
    * performed in parallel.
    *
    * The theoretical possible performance gain can be calculated by the following rule which is referred to as
    * "Amdahl’s Law". If F is the percentage of the program which can not run in parallel and N is the number of
    * processes, then the maximum performance gain is 1 / (F+ ((1-F)/n)).
    *
    * For example, if F=0.5 (half the application can be executed in parallel). And we have five threads (i.e. n=5)
    * The gain of running in parallel is 1/(0.5+0.1)=1.67. You can notice that, even we have unlimited resource to run
    * the application in parallel, the limit of the gain of this application si 1/0.5=2. In practice, we will never
    * reach this gain.
    *
    * */

    /************************** 10.14 Immutability and Defensive Copies ********************************/

    /*
    * The simplest way to avoid problems with concurrency is to share only immutable data(i.e. data which cannot changed)
    * between threads.
    *
    * To make a class immutable, you need to make:
    * - all its fields final
    * - the class declared as final
    * - the "this" reference is not allowed to escape during construction
    * - Any fields which refer to mutable data objects are private
    * - have no setter method
    * - they are never directly returned of otherwise exposed to a caller
    * - if they are changed internally in the class this change is not visible and has no effect outside of the class
    *
    * For all mutable fields, e.g. Arrays, that are passed from the outside to the class during the construction phase,
    * the class needs to make a defensive-copy of the elements to make sure that no other object from the outside
    * still can change the data
    * */

    /** 10.14.1 Defensive copies
     *
     * You must protect your immutable classes from calling code. Assume that calling code will do its best to change
     * your data in a way you did not expect it. While this is especially true in case of immutable data it is also
     * true for non-immutable data which you still not expect that this data is changed outside your class.
     *
     * To protect your class against all external modifications. You should
     * 1. Copy data that you receive
     * 2. Return copies of data to calling code.
     *
     * The Planet class in defensive_copies package shows an example of immutable classes which uses defensive copies.
     * */

    public static void main(String[] args){

        System.out.println("Main Thread starting ...");

        /** 10.3 Creating a thread*/
       // MultiThreadExp.exp1();

        /** 10.3.3 Variation 1 of MyThread*/
        // MultiThreadExp.exp2();

        /** 10.3.4 Variation 2 of MyThread*/
      //  MultiThreadExp.exp3();

        /** 10.4 Multiple threads*/
       //  MultiThreadExp.exp4();
        /** 10.4.1 start() vs run() */
        //call run()
      //  MultiThreadExp.exp12();
        // call start()
       // MultiThreadExp.exp13();
        // multiple call on start() generate exception
       // MultiThreadExp.exp14();

        /** 10.5.1 isAlive example*/
        // MultiThreadExp.exp5();

        /** 10.5.2 Join example*/
        //  MultiThreadExp.exp6();

         /** 10.6 thread priority */
         // MultiThreadExp.exp7();

        // daemon thread
        MultiThreadExp.exp18();

        /** 10.7.1 Synchronized methods*/
        // MultiThreadExp.exp8();

        /** 10.7.2 synchronized block*/
        // MultiThreadExp.exp9();

        /** 10.8.1 wait, notify example*/
        // MultiThreadExp.exp10();

        /** 10.9 Suspending, stopping , resuming a thread*/
       // MultiThreadExp.exp11();

        /** volatile*/
        // MultiThreadExp.exp15();

        // volatile fails in some condition
       // MultiThreadExp.exp16();

        //Full volatile Visibility Guarantee
       // MultiThreadExp.exp17();

        /** 10.12 Control the main thread*/

       /* // get main thread name
        Thread mainThread=Thread.currentThread();
        System.out.println("Main thread is called "+mainThread.getName());

        // display main thread priority
        System.out.println("Main thread priority is:  "+mainThread.getPriority());

        // set the name and priority
        System.out.println("Setting Main thread name and priority ");
        mainThread.setName("Pengfei test");
        mainThread.setPriority(Thread.NORM_PRIORITY+3);
        System.out.println("Main thread is called "+mainThread.getName());
        System.out.println("Main thread priority is:  "+mainThread.getPriority());

         System.out.println("Main Thread ending ...");*/
    }
}
