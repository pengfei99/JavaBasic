package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source.zero_even_odd;


public class ZeroEvenOddMain {

    public static void main(String[] args){
        var t1=new ThreadForZeroEvenOdd("zero");
        var t2=new ThreadForZeroEvenOdd("even");
        var t3=new ThreadForZeroEvenOdd("odd");
        t3.start();
        t1.start();
        t2.start();

    }
}
