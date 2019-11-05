package org.pengfei.Lesson00_Java_Basics.S10_MultiThread;

import org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source.MultiThreadExp;

public class S10_MultiThread {
    /******************************************** 10. Introduction *******************************************/

    /*
    * A multithreaded program contains two or more parts that can run concurrently. Each part of such a program is
    * called a thread, and each thread defines a separate path of execution. In this section, we will learn the
    * fundamentals of multithreading. We will use another full lesson to learn "Java concurrency programing". In this
    * section, we will see the following key concepts:
    * - Understand multithreading fundamentals
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
    * */

    /*********************************** 10.1 Multithreading fundamentals *******************************************/

    /*
    * There are two distinct types of multitasking: process­based and thread­based. It is important to understand
    * the difference between the two. A process is, in essence, a program that is executing. Thus, process­based
    * multitasking is the feature that allows your computer to run two or more programs concurrently. For example,
    * it is process­based multitasking that allows you to run the Java compiler at the same time you are using a text
    * editor or browsing the Internet. In process­based multitasking, a program is the smallest unit of code that can
    * be dispatched by the scheduler.
    *
    * In a thread­based multitasking environment, the thread is the smallest unit of dispatchable code. This means
    * that a single program can perform two or more tasks at once. For instance, a text editor can be formatting
    * text at the same time that it is printing, as long as these two actions are being performed by two separate
    * threads. Although Java programs make use of process­based multitasking environments, process­based multitasking
    * is not under the control of Java. Multithreaded multitasking is.
    *
    * A principal advantage of multithreading is that it enables you to write very efficient programs because it lets
    * you utilize the idle time that is present in most programs. As you probably know, most I/O devices, whether they
    * be network ports, disk drives, or the keyboard, are much slower than the CPU. Thus, a program will often spend a
    * majority of its execution time waiting to send or receive information to or from a device. By using
    * multithreading, your program can execute another task during this idle time. For example, while one part of
    * your program is sending a file over the Internet, another part can be reading keyboard input, and still another
    * can be buffering the next block of data to send.
    *
    *  Java’s multithreading features work in both multicore and single core system. In a single­core system,
    * concurrently executing threads share the CPU, with each thread receiving a slice of CPU time. Therefore,
    * in a single­core system, two or more threads do not actually run at the same time, but idle CPU time is utilized.
    * However, in multiprocessor/multicore systems, it is possible for two or more threads to actually execute
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
    * Java’s multithreading system is built upon the Thread class and its companion interface, Runnable. Both are
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
    * implements the Runnable interface. Runnable defines only one method called run( ), which is declared like this:
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
    * One other point: In a multithreaded program, you often will want the main thread to be the last thread to
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
     * Check Lesson01_Creating_And_Destroying_Objects->S01_staticFactoryMethod
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

    /************************************** 10.5 Determining when a thread ends ************************************/
    /*
    * It is often useful to know when a thread has ended. For example, in the preceding examples, for the sake of
    * illustration, we used println to show when thread starts and ends. However, in real world situation, we need
    * better solutions, Fortunately, Thread provides two means by which you can determine if a thread has ended:
    * 1. Call isAlive() on the thread: It returns true if the thread is still running, returns false otherwise.
    * 2. Call join():
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
     * You can noticed in the outputs, after the calls to join() return, the threads have stopped executing.
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

        /************************************** 10.7  Synchronization ************************************/

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
        * All objects in Java have a monitor. This feature is built into the Java language itself. Thus, all objects
        * can be synchronized. Synchronization is supported by the keyword synchronized and a few well­defined methods
        * that all objects have. Since synchronization was designed into Java from the start, it is much easier to
        * use than you might first expect. In fact, for many programs, the synchronization of objects is almost
        * transparent.
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
         * Thread1 starts and terminates, then Thread2 starts and terminates.
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
     *     // statement to be synchronized
     * }
     * Here, object-reference is a reference to the object being synchronized. Once a synchronized block has been
     * entered, no other thread can call a synchronized method on the object referred to by object-reference until
     * the block has been exited.
     */

    /** 10.7.3 Concurrency utilities and Fork/Join framework
     *
     * The concurrency utilities, which are packaged in java.util.concurrent (and its subpackages), support concurrent
     * programming. Among several other items, they offer synchronizers, thread pools, execution managers, and locks
     * that expand your control over thread execution. One of the most exciting features of the concurrent API is the
     * Fork/Join Framework.
     *
     * The Fork/Join Framework supports what is often termed parallel programming. This is the name commonly given to
     * the techniques that take advantage of computers that contain two or more processors (including multicore systems)
     * by subdividing a task into subtasks, with each subtask executing on its own processor. As you can imagine,
     * such an approach can lead to significantly higher throughput and performance. The key advantage of the Fork/Join
     * Framework is ease of use; it streamlines the development of multithreaded code that automatically scales to
     * utilize the number of processors in a system. Thus, it facilitates the creation of concurrent solutions to
     * some common programming tasks, such as performing operations on the elements of an array. The concurrency
     * utilities in general, and the Fork/Join Framework specifically, are features that you will want to explore
     * after you have become more experienced with multithreading.
     * */

    /************************************** 10.8  Thread communication  ************************************/

    /*
    *
    * Consider the following situation. A thread called T is executing inside a synchronized method and needs access
    * to a resource called R that is temporarily unavailable. What should T do? If T enters some form of polling loop
    * that waits for R, T ties up the object, preventing other threads’ access to it. This is a less than optimal
    * solution because it partially defeats the advantages of programming for a multithreaded environment. A better
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
     * */

    /** 10.8.2 Deadlock and race condition in multithreaded program
     *
     * Deadlock is, as the name implies, a situation in which one thread is waiting for another thread to do something,
     * but that other thread is waiting on the first. Thus, both threads are suspended, waiting on each other, and
     * neither executes. This situation is analogous to two overly polite people, both insisting that the other step
     * through a door first!
     *
     * Avoiding deadlock seems easy, but it’s not. For example, deadlock can occur in roundabout ways. The cause of
     * the deadlock often is not readily understood just by looking at the source code to the program because
     * concurrently executing threads can interact in complex ways at run time. To avoid deadlock, careful programming
     * and thorough testing is required. Remember, if a multithreaded program occasionally “hangs,” deadlock is the
     * likely cause.
     *
     * A race condition occurs when two (or more) threads attempt to access a shared resource at the same time,
     * without proper synchronization. For example, one thread may be writing a new value to a variable while another
     * thread is incrementing the variable’s current value. Without synchronization, the new value of the variable will
     * depend upon the order in which the threads execute. (Does the second thread increment the original value or
     * the new value written by the first thread?) In situations like this, the two threads are said to be
     * “racing each other,” with the final outcome determined by which thread finishes first. Like deadlock, a
     * race condition can occur in difficult­to­discover ways. The solution is prevention: careful programming that
     * properly synchronizes access to shared resources.
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

    /******************** 10.10  How to use multithreading to improve efficiency of your program  *******************/

    /**
     * The key to effectively utilizing multithreading is to think concurrently rather than serially. For example,
     * when you have two subsystems within a program that are fully independent of each other, consider making them
     * into individual threads. A word of caution is in order, however. If you create too many threads, you can
     * actually degrade the performance of your program rather than enhance it. Remember, overhead is associated with
     * context switching. If you create too many threads, more CPU time will be spent changing contexts than in
     * executing your program!
     *  */

    /************************** 10.11  Understand and control the main thread  ********************************/

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

        /** 10.5.1 isAlive example*/
        // MultiThreadExp.exp5();

        /** 10.5.2 Join example*/
          // MultiThreadExp.exp6();

         /** 10.6 thread priority */
         // MultiThreadExp.exp7();

        /** 10.7.1 Synchronized methods*/
        // MultiThreadExp.exp8();

        /** 10.7.2 synchronized block*/
        // MultiThreadExp.exp9();

        /** 10.8.1 wait, notify example*/
        // MultiThreadExp.exp10();

        /** 10.9 Suspending, stopping , resuming a thread*/
       // MultiThreadExp.exp11();

        /** 10.11 Control the main thread*/

        // get main thread name
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

         System.out.println("Main Thread ending ...");
    }
}
