package org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source;

import org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source.callback.MouseClickedListener;
import org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source.callback.MyAsyncMouse;
import org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source.callback.MySyncMouse;
import org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source.callback.OpenNewWindow;
import org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source.future.AsyncSquareCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class AsyncProgrammingExample {

    /*synchronous call back*/
    public static void exp1() {
        //create a mouse listener which open a new window
        MouseClickedListener mcListener = new OpenNewWindow();

        // create a mouse
        MySyncMouse mouse = new MySyncMouse();

        // register the listener to the mouse
        mouse.registerMouseClickedLister(mcListener);
        // click the mouse, generate an event, which starts the call back.
        mouse.doClick();
    }

    /*Asynchronous call back*/
    public static void exp2() {
        //create a mouse listener which open a new window
        MouseClickedListener mcListener = new OpenNewWindow();

        // create a async mouse
        MyAsyncMouse mouse = new MyAsyncMouse();

        // register the listener to the mouse
        mouse.registerMouseClickedLister(mcListener);
        // click the mouse, generate an event, which starts the call back.
        mouse.doClick();
        mouse.doClick();

        // we need to stop the mouse internal thread pool
        mouse.stop();
    }

    /*future*/
    public static void exp3() {
        AsyncSquareCalculator asCalculator = new AsyncSquareCalculator();
        List<Future<Integer>> resultList = new ArrayList<>(5);
        System.out.println("Start the calculateSquare call");
        for (int i = 1; i < 5; i++) {
            Future<Integer> result = asCalculator.calculateSquare(i, 1000);
            resultList.add(result);
        }

        System.out.println("Finish the calculateSquare call");
        int count = 0;

        // We check the four returned future's value, you can noticed we use .isDone() to check the status
        // before we call .get() to avoid blocking of the main thread.
        while (count < 4) {
            Future<Integer> result = resultList.get(count);
            if (result.isDone()) {
                try {
                    System.out.println("value: " + result.get());
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Calculation is not finished");
            }
        }

        //When all finished, shutdown the pool
        asCalculator.shutdown();
    }

    public static void exp4() {
        ExecutorService es = Executors.newFixedThreadPool(4);
        // we use a thread pool with 4 thread to replace the default single thread pool to see some parallel behaviour.
        AsyncSquareCalculator calculator = new AsyncSquareCalculator(es);

        Future<Integer> r1 = calculator.calculateSquare(9, 1000);
        Future<Integer> r2 = calculator.calculateSquare(100, 2000);
        Future<Integer> r3 = calculator.calculateSquare(4, 500);

        //we can cancel a task, if it's not finished yet.
        boolean canceled = r3.cancel(true);

        System.out.println("R3 is canceled: " + canceled);

        //if task1 and task2 is not done, print their status
        while (!(r1.isDone() && r2.isDone())) {
            System.out.println(
                    String.format(
                            "r1 is %s and r2 is %s",
                            r1.isDone() ? "done" : "not done",
                            r2.isDone() ? "done" : "not done"
                    )
            );

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // after all finished get value
        try {
            System.out.println("r1 value: " + r1.get());
            System.out.println("r2 value: " + r2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        calculator.shutdown();
    }

    /*In this example, we have two threads waiting for value of a completeFuture, and one thread
     * complete it manually with method complete. */
    public static void exp5() {
        ExecutorService es = Executors.newFixedThreadPool(3);

        CompletableFuture<String> cFuture = new CompletableFuture<>();
        //run first thread which reads the future value
        es.submit(() -> {
            while (!cFuture.isDone()) {
                System.out.println("Thread 1 waits future value");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                System.out.println("Thread 1 return future value" + cFuture.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        //run second thread which reads the future value
        es.submit(() -> {
            while (!cFuture.isDone()) {
                System.out.println("Thread 2 waits future value");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                System.out.println("Thread 2 return future value" + cFuture.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        //run third thread which complete future manually
        es.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cFuture.complete("Hello World");
        });

        es.shutdown();
    }

    /**
     * use ForkJoin.commonPool Executor
     */
    public static void exp6() {
        //run first thread which print hello word every sec five times
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("Thread 1 prints: Hello World");
                        }

                    }
                });

        //run second thread which print foobar every 2 sec five times
        CompletableFuture<Void> f2 = CompletableFuture.runAsync(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("Thread 2 prints: Foo bar");
                        }

                    }
                });

        //wait two thread complete, without this, main finish before f1,f2, and nothing will be done.
        try {
            f1.get();
            f2.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    /**
     * use custom Executor
     */
    public static void exp7() {

        ExecutorService es = Executors.newFixedThreadPool(2);

        //run first thread which print hello word every sec five times
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("Thread 1 prints: Hello World");
                        }

                    }
                }
                , es);

        //run second thread which print foobar every 2 sec five times
        CompletableFuture<Void> f2 = CompletableFuture.runAsync(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("Thread 2 prints: Foo bar");
                        }

                    }
                }
                , es);

        // here shutDown allow the submitted tasks to finish, so no interrupted exception are thrown.
        es.shutdown();
    }

    public static void exp8()  {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                return "Result of the asynchronous task1";
            }
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                return "Result of the asynchronous task2";
            }
        });

        while(!(f1.isDone()&&f2.isDone())){
            System.out.println(
                    String.format(
                            "f1 is %s and f2 is %s",
                            f1.isDone() ? "done" : "not done",
                            f2.isDone() ? "done" : "not done"
                    ));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            System.out.println(f1.get());
            System.out.println(f2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
