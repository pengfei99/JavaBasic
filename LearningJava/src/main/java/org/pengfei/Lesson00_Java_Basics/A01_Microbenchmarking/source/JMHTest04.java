package org.pengfei.Lesson00_Java_Basics.A01_Microbenchmarking.source;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2,jvmArgs = {"-Xms2G", "-Xmx2G"})
public class JMHTest04 {

    @Param({"10000"})
    private int N;
    private List<String> DATA_FOR_TESTING;

    @Setup
    public void setup() {
        DATA_FOR_TESTING = createData();
    }

    private List<String> createData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            data.add("Number : " + i);
        }
        return data;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void loopFor(Blackhole bh) {
        for (int i = 0; i < DATA_FOR_TESTING.size(); i++) {
            String s = DATA_FOR_TESTING.get(i); //take out n consume, fair with foreach
            bh.consume(s);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
   // @OutputTimeUnit(TimeUnit.SECONDS)
    public void loopWhile(Blackhole bh) {
        int i = 0;
        while (i < DATA_FOR_TESTING.size()) {
            String s = DATA_FOR_TESTING.get(i);
            bh.consume(s);
            i++;
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
   // @OutputTimeUnit(TimeUnit.SECONDS)
    public void loopForEach(Blackhole bh) {
        for (String s : DATA_FOR_TESTING) {
            bh.consume(s);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
   // @OutputTimeUnit(TimeUnit.SECONDS)
    public void loopIterator(Blackhole bh) {
        Iterator<String> iterator = DATA_FOR_TESTING.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            bh.consume(s);
        }
    }
}
