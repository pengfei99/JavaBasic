package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.atomic.fizzpuzz;

public class ThreadForFizzBuzz implements Runnable {

    private String threadName;
    private FizzBuzz fizzBuzz;

    public ThreadForFizzBuzz(String threadName, FizzBuzz fizzBuzz) {
        this.threadName = threadName;
        this.fizzBuzz = fizzBuzz;
    }

    @Override
    public void run() {
       // System.out.println("Starting "+threadName);
        if (threadName.equals("fizz")) {
    fizzBuzz.fizz();
        }
        else if (threadName.equals("buzz")){
            fizzBuzz.buzz();
        }else if (threadName.equals("fizzbuzz")){
            fizzBuzz.fizzBuzz();
        }else if(threadName.equals("number")){
            fizzBuzz.number();
        }else throw new IllegalArgumentException("Wrong thread Number");
        //System.out.println("Stopping "+threadName);
    }
}
