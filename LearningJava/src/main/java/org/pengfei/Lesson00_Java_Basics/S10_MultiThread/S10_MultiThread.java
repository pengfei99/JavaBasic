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
    * a better way for one thread to wait until another completes.
    *
    * One other point: In a multithreaded program, you often will want the main thread to be the last thread to
    * finish running. As a general rule, a program continues to run until all of its threads have ended. Thus,
    * having the main thread finish last is not a requirement. It is, however, often a good practice to follow,
    * especially when you are first learning about threads.
    * */

    public static void main(String[] args){

        /** 10.3 Creating a thread*/
        MultiThreadExp.exp1();
    }
}
