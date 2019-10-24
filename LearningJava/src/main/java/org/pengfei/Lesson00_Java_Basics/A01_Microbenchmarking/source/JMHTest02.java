package org.pengfei.Lesson00_Java_Basics.A01_Microbenchmarking.source;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;


public class JMHTest02 {

    @State(Scope.Thread)
    public static class MyState{
        public int a=1;
        public int b=2;
        public int sum;
    }


   @Benchmark
   @BenchmarkMode(Mode.All)
   @OutputTimeUnit(TimeUnit.SECONDS)
    public void sumTest(MyState state) {
        state.sum=state.a+state.b;
    }


}
