package org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming;

import org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source.AsyncProgrammingExample;

public class S15_Asynchronous_Programming {

public static void main(String[] args){

    /******************************************** 15. Introduction *******************************************/

    /*
    * What is Asynchronous Programming?
    * Asynchronous programming provides a non-blocking, event-driven programming model. This programming model
    * leverages the multiple cores in your system to provide parallelization by using multiple CPU cores to execute
    * the tasks, thus increasing the application's throughput. Note that throughput is a measure of the amount of
    * work done in unit time. In this programming paradigm, a unit of work would execute separately from the main
    * application thread and notify the calling thread about its execution state: success, in progress or failure.
    * 异步编程提供了一个非阻塞的，事件驱动的编程模型。 这种编程模型利用系统中多核执行任务来提供并行，因此提供了应用的吞吐率。此处吞吐率是指在
    * 单位时间内所做任务的数量。 在这种编程方式下， 一个工作单元将独立于主应用线程而执行， 并且会将它的状态通知调用线程：成功，处理中或者失败。
    *
    * Why we need it?
    * We need asynchronous programming model to eliminate the blocking model. In essence, the asynchronous
    * programming model can use the same thread to process multiple requests without any request blocking the thread.
    * Imagine a threading model in which an application executes a task and then waits for the task to complete
    * before proceeding. A good example of this can be logging frameworks: You typically would want to log exceptions
    * and errors into your log targets; in other words, file, database, or something similar. There is no point for
    * your application to wait till the logging tasks are over. In doing so, the application's responsiveness would
    * be affected. On the contrary, if the call to the logging framework can be made asynchronously, the application
    * can proceed with other tasks concurrently, without having to wait. This is an example of a non-blocking mode
    * of execution.
    * 我们需要异步来消除阻塞模型。其实异步编程模型可以使用同样的线程来处理多个请求， 这些请求不会阻塞这个线程。想象一个应用正在使用的线程正在执
    * 行任务， 然后等待任务完成才进行下一步。 log框架就是一个很好的例子：典型地你想将异常和错误日志记录到一个目标中， 比如文件，数据库或者其它
    * 类似地方。你不会让你的程序等待日志写完才执行，否则程序的响应就会受到影响。 相反，如果对log框架的调用是异步地，应用就可以并发执行其它任务
    * 而无需等待。这是一个非阻塞执行的例子。
    *
    * What you should do and should not do
    *
    * 1. To facilitate testing, you should separate or isolate functionality from multi-threading in your code. When
    *    writing asynchronous code in Java, you should always follow the "asynchronous callback model" so that the
    *    calling thread is not blocked.
    *    为了方便测试， 你应该在代码中将功能从多线程中隔离出来。当在Java中编写异步代码时，你应该遵循异步模型，这样调用线程就不会被阻塞。
    *
    * 2. Constructors cannot be asynchronous and you should not call asynchronous methods inside a
    *    constructor. Asynchronous programming model is particularly useful when the tasks executed asynchronously
    *    are not dependent on one another. You should not use async model when the invoking task depends on the called
    *    task (asynchronous method) for it to proceed.
    *    注意构造函数不能是异步的，你不应该在构造函数中调用异步方法。当任务互相不依赖时异步方式尤其有用。当调用任务依赖被调用任务时不应该使用异步
    *    (译者按：这对异步来说无意义，因为业务上调用线程被阻塞了).
    *
    * 3. You should handle exceptions inside the asynchronous methods (if they are prone to raise or throw exceptions).
    *    You should not implement asynchronous methods for long, time-consuming tasks. A long running task, if executed
    *    asynchronously, might take a longer time than the same task executed synchronously because the runtime would
    *    perform context switches, thread state storage, and so forth for methods that execute asynchronously. You
    *    also should note the difference between synchronous and asynchronous exceptions. the former implies
    *    exceptions that would occur at a particular program statement every time the program is executed; asynchronous
    *    exceptions are those that are much more difficult to handle and can occur anywhere in your code. Here,
    *    synchronous and asynchronous exceptions imply synchronous or asynchronous code in your program that might
    *    raise exceptions.
    *    你应该在异步方法中处理异常. 你不应该为长时间的task做异步实现. 一个长时间运行的任务，如果异步执行的话, 可能会比同步执行耗费更长的时间，
    *    因为运行时要为异步执行的方法执行线程上下文的切换， 线程状态的存储等. 你也应该注意同步的异常和异步的异常有所不同。 同步异常暗示 每次程序
    *    执行到那个程序特殊状态时就会抛出异常；异步异常的跟踪则困难的多。所以同步和异步异常暗示同步或异步代码可能抛出异常.
    * */

    /************************* 15.1 Asynchronous and Synchronous Callbacks in Java **********************************/

    /** 15.1 CallBack Function
     * A CallBack Function is a function that is passed into another function as an argument and is expected to
     * execute after some kind of event. The purpose of the callback function is to inform a class Sync/Async if
     * some work in another class is done. This is very useful when working with Asynchronous tasks. Suppose we want
     * to perform some routine tasks like perform some operation or display content after clicking a button, or
     * fetching data from internet. This is also used in event handling, as we get notified when a button is clicked
     * via callback function.
     *
     * This type of design pattern is used in "Observer Design Pattern". The observer pattern is a software design
     * pattern in which an object, called the subject, maintains a list of its dependent, called observers, and
     * notifies them automatically of any state changes, usually by calling one of their methods. More details
     * (https://en.wikipedia.org/wiki/Observer_pattern)
     *
     * In Java, Callbacks can be implemented using an interface. The general procedure for implementation is given below.
     * 1. Define the methods in an interface that we want to invoke after callback.
     * 2. Define a class that will implement the callback methods of the interface.
     * 3. Define a reference in other class to register the callback interface.
     * 4. Use that reference to invoke the callback method.
     * */

    /** 15.1.1 A synchronous call back example
     *
     *  In AsyncProgrammingExample.exp1(); we have a mouse, which can register mouseClickedListener. When mouse clicked,
     *  mouseClickedListener is notified via a call back function. Based on the mouseClickedListener implementation,
     *  some action will be done.
     *
     *  It's a synchronous call because, the call back of listener blocks the mouse execution
     *
     * */
   // AsyncProgrammingExample.exp1();

    /** 15.1.2 A asynchronous call back example
     *
     *  In AsyncProgrammingExample.exp2(); we have a mouse, which can register mouseClickedListener. When mouse clicked,
     *  the call back function is executed in a new thread, not in the mouse thread.
     *
     * So the call back of listener does not block the mouse execution anymore.
     *
     * */
   AsyncProgrammingExample.exp2();
}

}
