package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.atomic.fizzpuzz;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/*
* Write a program that outputs the string representation of numbers from 1 to n, however:
* - If the number is divisible by 3, output "fizz".
* - If the number is divisible by 5, output "buzz".
* - If the number is divisible by both 3 and 5, output "fizzbuzz".
*
* For example, for n = 15, we output: 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz.
* Implement a multi-threaded version of FizzBuzz with four threads. The same instance of FizzBuzz will be passed
* to four different threads:
* - Thread A will call fizz() to check for divisibility of 3 and outputs fizz.
* - Thread B will call buzz() to check for divisibility of 5 and outputs buzz.
* - Thread C will call fizzbuzz() to check for divisibility of 3 and 5 and outputs fizzbuzz.
* - Thread D will call number() which should only output the numbers.
* */
public class FizzBuzz {
    private int n;
    private AtomicInteger sharedIndex;
    public FizzBuzz(int n, AtomicInteger sharedIndex){
        this.n=n;
        this.sharedIndex=sharedIndex;
    }

    public synchronized void fizz(){


        while(sharedIndex.get()<n){
            int currentIndex=sharedIndex.get();
            //current index is divisible by 3, print fizz, update index and notify all
            if((currentIndex%3)==0&&((currentIndex%5)!=0)){
                System.out.println("fizz");
                sharedIndex.set(currentIndex+1);
                notifyAll();
            }
            // if not do nothing and wait()
            else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void buzz(){


        while(sharedIndex.get()<n){
            int currentIndex=sharedIndex.get();
            //current index is divisible by 5, print buzz, update index and notify all
            if((currentIndex%3)!=0&&(currentIndex%5)==0){
                System.out.println("buzz");
                sharedIndex.set(currentIndex+1);
                notifyAll();
            }
            // if not do nothing and wait()
            else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void fizzBuzz(){


        while(sharedIndex.get()<n){
            int currentIndex=sharedIndex.get();
            //current index is divisible by 3 and 5, print fizzbuzz, update index and notify all
            if(((currentIndex%3)==0)&&((currentIndex%5)==0)){
                System.out.println("fizzbuzz");
                sharedIndex.set(currentIndex+1);
                notifyAll();
            }
            // if not do nothing and wait()
            else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public synchronized void number(){

        while(sharedIndex.get()<n){
            int currentIndex=sharedIndex.get();
            //current index is not divisible by 3 and 5, print fizzbuzz, update index and notify all
            if(((currentIndex%3)!=0)&&((currentIndex%5)!=0)){
                System.out.println(currentIndex);
                sharedIndex.set(currentIndex+1);
                notifyAll();
            }
            // if not do nothing and wait()
            else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args){
        AtomicInteger sharedIndex=new AtomicInteger(1);
        FizzBuzz fizzBuzz=new FizzBuzz(21,sharedIndex);

        ExecutorService es= Executors.newFixedThreadPool(4);
        es.execute(new ThreadForFizzBuzz("fizz",fizzBuzz));
        es.execute(new ThreadForFizzBuzz("buzz",fizzBuzz));
        es.execute(new ThreadForFizzBuzz("fizzbuzz",fizzBuzz));
        es.execute(new ThreadForFizzBuzz("number",fizzBuzz));

        es.shutdown();
    }
}
