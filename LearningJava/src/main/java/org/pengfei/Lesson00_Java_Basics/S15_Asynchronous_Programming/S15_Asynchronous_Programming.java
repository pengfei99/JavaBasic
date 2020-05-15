package org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming;

import org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source.AsyncProgrammingExample;

public class S15_Asynchronous_Programming {

public static void main(String[] args){

    /******************************************** 15.0 Introduction *******************************************/

    /*
    * What is Asynchronous Programming?
    *
    * According to Wikipedia, Asynchronous programming is a means of parallel programming in which a unit of work
    * runs separately from the main application thread and notifies the calling thread of its completion, failure
    * or progress.
    *
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
    *
    * Difference between concurrency and parallelism
    * Concurrency is essentially applicable when we talk about two tasks or more. When an application is capable of
    * executing two tasks virtually at same time, we call it concurrent application. Though here tasks run looks
    * like simultaneously, but essentially they may not. They take advantage of CPU time-slicing feature of operating
    * system where each task run part of its task and then go to waiting state. When first task is in waiting state,
    * CPU is assigned to second task to complete it’s part of task.
    *
    * Parallelism does not require two tasks to exist. It literally physically run parts of tasks OR multiple tasks,
    * at the same time using multi-core infrastructure of CPU, by assigning one core to each task or sub-task.
    * Parallelism requires hardware with multiple processing units, essentially. In single core CPU, you may get
    * concurrency but NOT parallelism.
    *
    * For example, suppose we have a two core cpu, Application1 and Application2 both has two tasks(e.g. task1, task2).
    * In Application1, task1 runs first, then task2 runs, they both run on the same core. In Application2, task1(core1)
    * and task2(core2) runs at same time. We call Application1 runs concurrently, App2 runs parallel.
    *
    * Note, if you are not familiar with ThreadPool, please check Lesson1_Section09
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
   // AsyncProgrammingExample.exp2();

   /** 15.1.3 When To Use What
    *
    * Synchronous Callback : Any process having multiple tasks where the tasks must be executed in sequence and
    *                       does not occupy much time should use synchronous Callbacks. For example, you’re in a
    *                       movie queue for ticket you can’t get one until everyone in front of you gets one.
    *
    * Asynchronous Callback : When the tasks are not dependent on each other and may take some time for execution
    *                        we should use Asynchronous callbacks. For example, when you order your food other
    *                        people can also order their food in the restaurant. They don’t have to wait for your
    *                        order to finish. If you’re downloading a song from internet, Getting an API response.
    * */

   /************************* 15.2 Asynchronous Programming with Future Interface **********************************/

   /*
   * Future is a interface in java.util.concurrent. A Future represents the result of an asynchronous computation.
   * Methods are provided to check if the computation is complete, to wait for its completion, and to retrieve the
   * result of the computation. The result can only be retrieved using method get() when the computation has
   * completed, blocking if necessary until it is ready. Cancellation is performed by the cancel() method.
   * Additional methods are provided to determine if the task completed normally or was cancelled. Once a
   * computation has completed, the computation cannot be cancelled. If you would like to use a Future for the
   * sake of cancellability but not provide a usable result, you can declare types of the form Future<?> and return
   * null as a result of the underlying task.
   *
   * In asynchronous programming, main thread does not wait for any task to finished, rather it hand over the task
   * to workers and move on. One way of doing asynchronous processing is using callback methods. Future is another
   * way to write asynchronous code. By using Future you can write a method which does long computation but returns
   * immediately. Those methods, instead of returning a result, return a Future object. You can later get the
   * result by calling Future.get() method, which will return an object of type T, where T is what Future object is
   * holding.
   *
   * Note returning a Future object is not blocking, but Future.get() method may block the main thread, if the
   * asynchronous process does not finish.
   **/

   /** 15.2.1 FutureTask
    *
    * FutureTask is a cancellable asynchronous computation. This class provides a base implementation of Future,
    * with methods to start and cancel a computation, query to see if the computation is complete, and retrieve the
    * result of the computation. The result can only be retrieved when the computation has completed; the get methods
    * will block if the computation has not yet completed. Once the computation has completed, the computation cannot
    * be restarted or cancelled (unless the computation is invoked using runAndReset()).
    *
    * A FutureTask can be used to wrap a Callable or Runnable object. Because FutureTask implements Runnable, a
    * FutureTask can be submitted to an Executor for execution.
    *
    * In AsyncProgrammingExample.exp3(); We implement a class AsyncSquareCalculator by using async programming. It uses
    * a SingleThreadPool to run a callable and returns a Future. We can notice the return of Future does not block the
    * main thread at all. But Future.get() does. And to avoid that, we can use Future.isDone() to check the status of
    * Future.
    *
    * In AsyncProgrammingExample.exp4(); We showed how to cancel a task, and the behaviour of asynchronous task in multi
    * thread pool.
    *
    *
    * */

  // AsyncProgrammingExample.exp3();
  //  AsyncProgrammingExample.exp4();

    /** 15.2.2 ForkJoinTask
     *
     * ForkJoinTask is an abstract class which implements Future and is capable of running a large number of tasks
     * hosted by a small number of actual threads in ForkJoinPool.
     *
     * There are two abstract classes that implement ForkJoinTask: RecursiveTask which returns a value upon
     * completion, and RecursiveAction which doesn't return anything.
     *
     * For ForkJoinTask example, please check Lesson01_Section09.
     * */

    /** 15.2.3 Future Limitations
     *
     * The features of Future are not enough to let you write concise concurrent code. For example, it’s difficult
     * to express dependencies between results of a Future. In order to get result from future, we need to call get
     * method which is blocking. It also has the following problems:
     * 1. It cannot be manually completed : Let’s say that you’ve written a function to fetch the latest price of
     *              an e-commerce product from a remote API. Since this API call is time-consuming, you’re running
     *              it in a separate thread and returning a Future from your function. Now, let’s say that If the
     *              remote API service is down, then you want to complete the Future manually by the last cached
     *              price of the product. Can you do this with Future? No!
     *
     * 2. You cannot perform further action on a Future’s result without blocking: Future does not notify you of
     *               its completion. It provides a get() method which blocks until the result is available. You don’t
     *               have the ability to attach a callback function to the Future and have it get called automatically
     *               when the Future’s result is available.
     * 3. Multiple Futures cannot be chained together : Sometimes you need to execute a long-running computation
     *               and when the computation is done, you need to send its result to another long-running
     *               computation, and so on. You can not create such asynchronous workflow with Futures.
     * 4. You can not combine multiple Futures together : Let’s say that you have 10 different Futures that you want
     *                to run in parallel and then run some function after all of them completes. You can’t do this
     *                as well with Future.
     * 5. No Exception Handling : Future API does not have any exception handling construct.
     *
     * */

    /************************* 15.3  CompletionStage and CompletableFuture from Java 8 ********************************/

    /*
    * To overcome the limitations of Future, since java8, CompletionStage interface and CompletableFuture class are
    * introduced.
    *
    * CompletionStage represents(abstracts) a stage of a possibly async computation, that performs an action or computes a
    * value when another CompletionStage completes. A stage completes upon termination of its computation, but this
    * may in turn trigger other dependent stages.
    *
    * CompletionStage can abstract an asynchronous task and also you can pipe many asynchronous outcome in completion
    * stage which lays the foundation of a reactive result processing which can have a valid use-case in virtually any
    * area, from Gateways to Clients to Enterprise Apps to Cloud Solutions. Furthermore potentially, this reduces
    * superfluous polling checks for the availability of result and/or blocking calls on futuristic results.
    *
    * CompletableFuture implements Future and CompletionStage. It provides abstraction for async tasks in event driven
    * programming. It s designated for executing long running operations (http requests, database queries, file
    * operations or complicated computations).
    *
    * CompletableFuture has 2 main benefits compare to FutureTask:
    * - It can be explicitly completed by calling the complete() method without any synchronous wait. It allows
    *   values of any type to be available in the future with default return values, even if the computation did
    *   not complete, using default / intermediate results.
    * - With tens of new methods, it also allows you to build a pipeline data process in a series of actions. You can
    *   find a number of patterns for CompletableFutures such as creating a CompletableFuture from a task, or building
    *   a CompletableFuture chain. The full list is available via Oracle’s CompletableFuture documentation.
    * https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletionStage.html
    * https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html
    *
    * CompletableFuture is at the same time a building block and a framework with about 50 different methods for
    * composing, combining, executing asynchronous computation steps and handling errors. It has several main use
    * cases:
    * -
    *
     * */

    /** 15.3.1 CompletableFuture: complete it manually using complete()
     *
     * You can use CompletableFuture.complete() method to manually complete a Future. All the clients waiting for
     * this Future will get the specified result. And, Subsequent calls to completableFuture.complete() will be
     * ignored.
     *
     * In AsyncProgrammingExample.exp5();, we have two threads waiting for value of a completeFuture, and one thread
     * complete it manually with method complete. You can notice when we complete the future, two reading threads
     * get notify and .get() returns the value just like old Future.
     * */

    // AsyncProgrammingExample.exp5();

    /** 15.3.2 CompletableFuture: Running asynchronous computation using runAsync()
     *
     * CompletableFuture provide a static method runAsync(). It has two overload version:
     * - CompletableFuture<Void> runAsync(Runnable runnable): It returns a new CompletableFuture that is
     *              asynchronously completed by a task running in the ForkJoinPool.commonPool() after it runs the
     *              given action.
     * - CompletableFuture<Void> runAsync(Runnable runnable, Executor executor): It Returns a new CompletableFuture
     *              that is asynchronously completed by a task running in the given executor after it runs the given
     *              action.
     *
     * If you want to run some background task asynchronously and don’t want to return anything from the task,
     * then you can use it.
     *
     * In AsyncProgrammingExample.exp6(); we use two CompletableFuture to print some text. As we don't specify the
     * Executor, it uses the commonPool. As a result, if the main finishes first, the commonPool closes. The tasks
     * of two CompletableFuture will never finish.
     *
     * In AsyncProgrammingExample.exp7(); we use a custom Executor to run the tasks.
     * */

      //AsyncProgrammingExample.exp6();
   //  AsyncProgrammingExample.exp7();

    /** 15.3.3 CompletableFuture: Run a task asynchronously and return the result using supplyAsync()
     *
     * static <U> CompletableFuture<U>	supplyAsync(Supplier<U> supplier): It returns a new CompletableFuture that
     *                    is asynchronously completed by a task running in the ForkJoinPool.commonPool() with the
     *                    value obtained by calling the given Supplier.
     * static <U> CompletableFuture<U>	supplyAsync(Supplier<U> supplier, Executor executor): It returns a new
     *                    CompletableFuture that is asynchronously completed by a task running in the given executor
     *                    with the value obtained by calling the given Supplier.
     *
     * Supplier is a functional interface and can therefore be used as the assignment target for a lambda expression
     * or method reference. You can view Supplier as a Runnable, but it returns a value after execution.
     *
     * In AsyncProgrammingExample.exp8(); we use two Supplier which are run by supplyAsync in the commonPool.
     * */
   // AsyncProgrammingExample.exp8();

    /** 15.3.4 CompletableFuture: Transforming and acting on a CompletableFuture
     *
     * For building asynchronous systems we should be able to attach a callback to the CompletableFuture which
     * should automatically get called when the Future completes.
     *
     * That way, we won’t need to wait for the result, and we can write the logic that needs to be executed after
     * the completion of the Future inside our callback function.
     *
     * You can attach a callback to the CompletableFuture using thenApply(), thenAccept() and thenRun() methods
     *
     * - thenApply(): The thenApply() method can process and transform the result of a CompletableFuture when it
     *            arrives. It takes a Function<T,R> as an argument. Function<T,R> is a simple functional interface
     *            representing a function that accepts an argument of type T and produces a result of type R.
     *            In AsyncProgrammingExample.exp9(), we first create a CompletableFuture which returns a String,
     *            then we attached a callback function which takes the String returned by the first CompletableFuture
     *            and build a new String with it.
     *            We can also connect many thenApply() together to build a sequence of transformation.
     *            In AsyncProgrammingExample.exp10(); we showed how to connect them together.
     * - thenAccept(): The thenAccept() method can process and transform the result of a CompletableFuture when it
     *            arrives. It takes a Consumer<T> and returns CompletableFuture<Void>. It does not return a result
     *            which can be used again by another thenApply(). So it runs some piece of code after the
     *            completion of the Future. It's often used as the last callback in the callback chain.
     *            AsyncProgrammingExample.exp11(); shows an example
     * - thenRun(): It takes a Runnable and returns CompletableFuture<Void>. So it does not retrieve the result from
     *            previous CompletableFuture and return a result. It just runs the given task after previous task
     *            finish.
     *
     * Async version of thenApply(), thenAccept(), thenRun() help you further parallelize your computations by
     * executing the callback tasks in a separate thread. For example, in AsyncProgrammingExample.exp10();
     * the task inside thenApply() is executed in the same thread where the supplyAsync() task is
     * executed, or in the main thread if the supplyAsync() task completes immediately
     * (try removing sleep() call to verify).
     *
     * For example:
     * - <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor): executor is
     *                     optional, if executor is not present, it will be executed in a different thread which is
     *                     obtained from the ForkJoinPool.commonPool()( default asynchronous execution facility).
     * - CompletableFuture<Void> thenRunAsync(Runnable action, Executor executor): Returns a new CompletionStage
     *                     that, when this stage completes normally, executes the given action using a different thread
     *                     which is obtained from the supplied Executor.
     *
     * In AsyncProgrammingExample.exp13(); we run the second and third task by using thenRunAsync on a custom threadPool
     */

    /*thenApply*/
    // AsyncProgrammingExample.exp9();
   // AsyncProgrammingExample.exp10();
    /*thenAccept*/
    //AsyncProgrammingExample.exp11();
    /*thenRun*/
   // AsyncProgrammingExample.exp12();
    /*thenRunAsync*/
    //AsyncProgrammingExample.exp13();

    /** 15.3.5 CompletableFuture: Combining two CompletableFutures together
     *
     * The thenApply(supplier) works well, when the call back function(i.e. supplier) returns a normal type. If it
     * returns also a completableFuture. The final result will be a Future of Future. To get the result value, you need
     * to call twice the get(). To avoid this, you can use the thenCompose() method.
     *
     * In AsyncProgrammingExample.exp14(); we use both thenApply and thenCompose() to demonstrate the difference.
     *
     * Note the return result of the first task is used as argument for the second task. We call them dependant future.
     * Because the result of task2 depends on task1.
     *
     * */

   // AsyncProgrammingExample.exp14();

    /** 15.3.6 CompletableFuture: Combine two independent futures using thenCombine()
     *
     * Unlike the use case of thenApply() or thenCompose(). If I have two task which are not dependant, and we
     * need the two returned future to calculate a new future. In this case, we can use thenCombine().
     * public <U,V> CompletableFuture<V> thenCombine(CompletionStage<? extends U> other,
     *               BiFunction<? super T,? super U,? extends V> fn): It returns a new CompletionStage that, when the
     *               invoking and the "other" given stage (first argument) both complete normally, is executed with
     *               the two results as arguments to the supplied function.
     * We can consider the invoking CompletionStage as task1, the first argument as task2, and the supplied function
     * as task3. If task1 and task2 finished, task3 takes the returned result of task1 and task2 as argument. Finally
     * task3 returns the final result
     *
     * Check  AsyncProgrammingExample.exp15();
     * */

   // AsyncProgrammingExample.exp15();

    /** 15.3.7 CompletableFuture Combining multiple CompletableFutures
     *
     * - static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs): It returns a new CompletableFuture
     *   that is completed when all of the given CompletableFutures complete. If any of the given CompletableFutures
     *   complete exceptionally, then the returned CompletableFuture also does so, with a CompletionException
     *   holding this exception as its cause. Otherwise, the results, if any, of the given CompletableFutures are
     *   not reflected in the returned CompletableFuture, but may be obtained by inspecting them individually. If
     *   no CompletableFutures are provided, returns a CompletableFuture completed with the value null.
     *
     * CompletableFuture.allOf() is used in scenarios when you have a List of independent futures that you want to run
     * in parallel and do something after all of them are complete.
     *
     * Let’s say that you want to download the contents of 100 different web pages of a website. You can do this
     * operation sequentially but this will take a lot of time. So, you have written a function which takes a web
     * page link, and returns a CompletableFuture, i.e. It downloads the web page’s content asynchronously
     *
     * Note allOf() returns a Void, so it works more like a Phaser or countDownLatch, just makes sure all tasks in its
     * arguments finishes. To get the result, we need to work on the returned result of each task in the argument.
     *
     * AsyncProgrammingExample.exp16(); shows an example of a list of tasks download contents from web. And we use
     * .allOf() to wait all tasks to finish. Then we get the result of each tasks.
     *
     * - public static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs): It returns a new CompletableFuture
     *            that is completed when any of the given CompletableFutures complete, with the same result. Otherwise,
     *            if it completed exceptionally, the returned CompletableFuture also does so, with a
     *            CompletionException holding this exception as its cause. If no CompletableFutures are provided,
     *            returns an incomplete CompletableFuture.
     *
     * CompletableFuture.anyOf() as the name suggests, returns a new CompletableFuture which is completed when any of
     * the given CompletableFutures complete, with the same result.
     *
     * In AsyncProgrammingExample.exp17(); we have three tasks which run async, we use anyOf() to get the fastest task.
     *
     * Note if these tasks returns different types, when you use .get(), you may not know the exact return type of the
     * anyOf. Because anyOf() returns an Object type.
     * */

   // AsyncProgrammingExample.exp16();
   // AsyncProgrammingExample.exp17();

    /** 15.3.8 CompletableFuture Exception Handling
     *
     * We explored How to create CompletableFuture, transform them, and combine multiple CompletableFutures. Now
     * let’s understand what to do when anything goes wrong. Let’s first understand how errors are propagated in
     * a callback chain. Consider the following CompletableFuture callback chain
     * CompletableFuture.supplyAsync(() -> {
     * 	// Code which might throw an exception
     * 	return "first task";
     * }).thenApply(result -> {
     * 	return "second task";
     * }).thenApply(result -> {
     * 	return "third task";
     * }).thenAccept(result -> {
     * 	// do something with the final result
     * 	return "fourth task"
     * });
     * If an error occurs in the original supplyAsync() task(1st task), then none of the thenApply() callbacks
     * will be called and future will be resolved with the exception occurred. If an error occurs in first thenApply()
     * callback then 2nd and 3rd callbacks won’t be called and the future will be resolved with the exception
     * occurred, and so on.
     *
     * Handle exceptions using exceptionally().
     * The exceptionally() callback gives you a chance to recover from errors generated from the original Future.
     * You can log the exception here and return a default value.
     *
     * In AsyncProgrammingExample.exp18(), we have 3 tasks. With different value of age, task1 and task2 can throw
     * exceptions. Try to change the age with -1, 16, and 38. then see what happens.
     * Try to remove the exceptionally() part and see what happens.
     *
     * Handle exceptions using handle().
     * The API also provides a more generic method - handle() to recover from exceptions. It is called whether or
     * not an exception occurs. As a result, it takes two argument, one is the normal returned result of previous tasks.
     * second argument is the exception.
     *
     * In AsyncProgrammingExample.exp19(); we rewrite the AsyncProgrammingExample.exp18(); with handle() to manage the
     * exception. The major difference is that handle() will always be executed with or without exception.
     */

    //AsyncProgrammingExample.exp18();
    AsyncProgrammingExample.exp19();
}

}
