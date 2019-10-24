package org.pengfei.Lesson00_Java_Basics.A01_Microbenchmarking.source;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;

public class JMHTest01 {

    @Benchmark
    public void doNothing(){
        System.out.println("Doing nothing");
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void exp1(){
        int a=1;
        int b=2;
        int sum=a+b;
        System.out.println("Sum value: "+sum);
    }



}
