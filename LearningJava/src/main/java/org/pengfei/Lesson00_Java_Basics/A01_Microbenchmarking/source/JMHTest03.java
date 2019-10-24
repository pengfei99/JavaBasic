package org.pengfei.Lesson00_Java_Basics.A01_Microbenchmarking.source;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

public class JMHTest03 {

    @State(Scope.Thread)
    public static class MyState{
        public int a = 1;
        public int b = 2;
        public int sum ;

        @Setup(Level.Trial)
        public void doSetup(){
            sum=0;
        }

        @TearDown(Level.Trial)
        public void doTearDown() {
            System.out.println("Do TearDown");
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void testSum(MyState state){
        state.sum=state.a+state.b;
    }
}
