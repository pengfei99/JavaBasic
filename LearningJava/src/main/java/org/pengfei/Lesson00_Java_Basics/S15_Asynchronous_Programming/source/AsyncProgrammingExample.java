package org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source;

import org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source.callback.MouseClickedListener;
import org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source.callback.MyAsyncMouse;
import org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source.callback.MySyncMouse;
import org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source.callback.OpenNewWindow;
import org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source.completable_future.RemoteUserInfo;
import org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source.completable_future.WebScrapingService;
import org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source.future.AsyncSquareCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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

    public static void exp8() {
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

        while (!(f1.isDone() && f2.isDone())) {
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

    public static void exp9() {
        // Create a CompletableFuture
        CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "World";
        });

// Attach a callback to the Future using thenApply()
        CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(name -> {
            return "Hello " + name;
        });

// Block and get the result of the future.
        try {
            System.out.println(greetingFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /*
     * In this example, the task inside thenApply() is executed in the same thread where the supplyAsync() task is
     * executed, or in the main thread if the supplyAsync() task completes immediately
     * (try removing sleep() call to verify).
     * */
    public static void exp10() {
        CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "foobar";
        }).thenApply(name -> {
            return "hello " + name;
        }).thenApply(text -> {
            return text + " welcome to java world";
        });

        try {
            System.out.println(welcomeText.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void exp11() {
        CompletableFuture<Void> text = CompletableFuture.supplyAsync(() -> {
            return "foobar";
        }).thenAccept((name) -> {
            System.out.println("Hello " + name);
        });

        //Without this blocking get. the main finish before the future, and commonPool is closed with main thread.
        // Future will never finish

        try {
            text.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void exp12() {
        CompletableFuture<Void> myFuture = CompletableFuture.runAsync(() -> {
            System.out.println("First Future finished");
        }).thenRun(() -> {
            System.out.println("Second future finished");
        }).thenRun(() -> {
            System.out.println("Third future finished");
        });

        try {
            myFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void exp13() {
        ExecutorService es = Executors.newFixedThreadPool(2);

        // FinalFuture is the result returned by the last thenRunAsync
        CompletableFuture<Void> finalFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("First Future finished");
            // this task will run in my custom es not in common pool
        }).thenRunAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Second future finished");
            // this task will run in my custom es not in common pool
        }, es).thenRunAsync(() -> {
            System.out.println("Third future finished");
        }, es);

        while (!finalFuture.isDone()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Final Future not done");
        }
        es.shutdown();
    }

    public static void exp14() {
        String userId = "alice";
        // if we use thenApply to run the second task, you can notice the returned result is a Future of Future
        CompletableFuture<CompletableFuture<Double>> result1 = RemoteUserInfo.getUsersDetail(userId)
                .thenApply(user -> RemoteUserInfo.getCreditRating(user));


        // If we use thenCompose to run the second task, you can notice the returned result is a simple Future
        CompletableFuture<Double> result2 = RemoteUserInfo.getUsersDetail(userId)
                .thenCompose(user -> RemoteUserInfo.getCreditRating(user));

        try {
            System.out.println("result1: " + result1.get().get());
            System.out.println("result2: " + result2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void exp15() {
        String userId = "alice";
        CompletableFuture<Double> userHeight = RemoteUserInfo.getUserHeight(userId);
        CompletableFuture<Double> userWeight = RemoteUserInfo.getUserWeight(userId);


        //note, the first argument in the lambda expression is the return result of the caller of thenCombine
        // the second argument in  the lambda is the return result of the first argument of thenCombine.
        CompletableFuture<Double> bmi = userWeight.thenCombine(userHeight, (weight, height) -> {
            System.out.println("height: " + height);
            System.out.println("weight: " + weight);
            Double heightInMeter = height / 100;
            return weight / (heightInMeter * heightInMeter);
        });

        try {
            System.out.println("User: " + userId + " has bmi: " + bmi.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void exp16() {
        List<String> urls = new ArrayList<>();
        urls.add("toto.org/text.sql");
        urls.add("google.org/text.sql");
        urls.add("facebook.org/text.sql");
        urls.add("alibaba.org/text.sql");

        //download all pages async
        List<CompletableFuture<String>> webPages = urls
                //convert list to stream
                .stream()
                //transform each element of the stream with the given function
                .map(url -> WebScrapingService.downloadWebPage(url))
                // covert stream to list
                .collect(Collectors.toList());

        //allOf here works like a phaser or countdownLatch, it will wait all tasks to finish
        //allOf takes an array of CompletableFuture as argument. So we use the .toArray(T[] ts) to return an array of
        //CompletableFuture

        //note it returns a Void, to get the returned result of the tasks, we need to do it manually

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(webPages.toArray(new CompletableFuture[webPages.size()]));

        // here, also allFutures works like a countdownLatch, run the given function when allFutures finish his tasks.
        // as allFutures returns a Void, so v in the lambda expression is useless. And webPages is the List of Future
        // which we have declared before which has nothing to do with the lambda function's argument.
        CompletableFuture<List<String>> allPagesContentFuture = allFutures.thenApply(v -> {
            return webPages
                    // transform list to stream
                    .stream()
                    // for each element(CompletableFuture<String>), get the value by using .join()
                    // the difference between join() and get() is simply how the handle the exception
                    // .get() throw a checked exception which must be handled  by the code(try-catch).
                    // .join() throw an unchecked exception, the code can ignore it.
                    .map(webPage -> webPage.join())
                    // transform stream to list
                    .collect(Collectors.toList());
        });

        try {
            List<String> allPagesContent = allPagesContentFuture.get();
            for(String page:allPagesContent){
                System.out.println(page);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void exp17(){
        CompletableFuture future1=CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task1 finishes";});

        CompletableFuture future2=CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task2 finishes";});

        CompletableFuture future3=CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task3 finishes";});

        CompletableFuture<Object> fastestTask = CompletableFuture.anyOf(future1, future2, future3);

        try {
            System.out.println("First arrived task: "+fastestTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    public static void exp18(){
        Integer age=17;

        CompletableFuture<String> welcome = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 1 finishes");
            if (age < 0) {
                throw new IllegalArgumentException("Age can't be negative");
            } else if (age < 18) {
                return "young boy";
            } else return "unknown";
        }).thenApply((title) -> {
            System.out.println("Task 2 finishes");
            if (title.equals("unknown")) throw new IllegalArgumentException("Age unkonwn");
            else return title;
        }).thenApply((title) -> {
                    System.out.println("Task 3 finishes");
                    return "hello " + title;
                }
        )
                //with exception handling
                .exceptionally(ex -> {
                    System.out.println("Oops! We have an exception - " + ex.getMessage());
                    return "Unknown!";
                })

                ;

        try {
            System.out.println(welcome.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public static void exp19(){
        Integer age=38;

        CompletableFuture<String> welcome = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 1 finishes");
            if (age < 0) {
                throw new IllegalArgumentException("Age can't be negative");
            } else if (age < 18) {
                return "young boy";
            } else return "unknown";
        }).thenApply((title) -> {
            System.out.println("Task 2 finishes");
            if (title.equals("unknown")) throw new IllegalArgumentException("Age unkonwn");
            else return title;
        }).thenApply((title) -> {
                    System.out.println("Task 3 finishes");
                    return "hello " + title;
                }
        )
                //with exception handling
                .handle((result,exception) -> {
                    if(exception!=null) {
                        System.out.println("Oops! We have an exception - " + exception.getMessage());
                        return "Hello unknown";
                    }

                    return result;
                })

                ;

        try {
            System.out.println(welcome.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
