package org.pengfei.Lesson00_Java_Basics.A01_Microbenchmarking;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.pengfei.Lesson00_Java_Basics.A01_Microbenchmarking.source.JMHTest01;
import org.pengfei.Lesson00_Java_Basics.A01_Microbenchmarking.source.JMHTest02;
import org.pengfei.Lesson00_Java_Basics.A01_Microbenchmarking.source.JMHTest04;

import java.io.IOException;

public class A01_Microbenchmarking {

    /********************************************************************************************************
     * ********************************** A01. Micro-benchmarking with Java *******************************
     * ***************************************************************************************************/

    /**************************************** 1.1 Introduction ********************************************/

    /*
    * In this section, we will learn how to use JMH(the Java Microbenchmark Harness) to benchmark java program. The JMH
    * has been added to the JDK starting with JDK 12. For earlier versions, we need to add the dependencies explicitly
    * to our projects.
    *
    * JMH is a toolkit that helps you implement Java microbenchmarks correctly. JMH is developed by the same people
    * who implement the Java virtual machine, so these guys know what they are doing.
    *
    * Microbenchmarking is hard
    * Writing benchmarks that correctly measure the performance of a small part of a larger application is hard.
    * There are many optimizations that the JVM or underlying hardware may apply to your component when the benchmark
    * executes that component in isolation. These optimizations may not be possible to apply when the component is
    * running as part of a larger application. Badly implemented microbenchmarks may thus make you believe that your
    * component's performance is better than it will be in reality.
    *
    * Writing a correct Java microbenchmark typically entails preventing the optimizations the JVM and hardware may
    * apply during microbenchmark execution which could not have been applied in a real production system. That is
    * what JMH - the Java Microbenchmark Harness - is helping you do.
    * */

    /************************************ 1.2 Add JMH dependencies ********************************/

    /*
    * In this example, we use maven as dependencies manager. In maven central, you can find config for Ivy,sbt, etc.
    * (e.g. https://search.maven.org/classic/#artifactdetails%7Corg.openjdk.jmh%7Cjmh-core%7C1.21%7Cjar)
    * To use of JMH, we need two lib, jmh-core and jmh annotation processor.
    *
    * <dependency>
    * <groupId>org.openjdk.jmh</groupId>
    * <artifactId>jmh-core</artifactId>
    * <version>1.19</version>
    * </dependency>
    * <dependency>
    * <groupId>org.openjdk.jmh</groupId>
    * <artifactId>jmh-generator-annprocess</artifactId>
    * <version>1.19</version>
    * </dependency>
    * */

    /********************************** 1.3 First Example ******************************************/

    /*
    * Check the JMHTest01 class, which does nothing, You can notice that we can only benchmark methods of a class
    * Constructor, class field can't be benchmarked.
    *
    * The output of the benchmark looks like:
    * Benchmark                                                               Mode  Cnt       Score       Error  Units
    * Lesson00_Java_Basics.A01_Microbenchmarking.source.JMHTest01.doNothing  thrpt    5  238158.915 ± 95640.656  ops/s
    * */

    /******************************* 1.4 Benchmark mode ******************************************/

    /* In the output of the benchmark, the first value is mode. JMH supports some possible benchmarks:
    * - Throughput : Measures the number of operations per second, meaning the number of times per second your
    *                benchmark method could be executed. (Default benchmark mode)
    * - AverageTime : Measures the average time it takes for the benchmark method to execute (a single execution).
    * - SampleTime : Measures how long time it takes for the benchmark method to execute, including max, min time etc.
    * - SingleShotTime : Measures how long time a single benchmark method execution takes to run. This is good to test
    *                    how it performs under a cold start (no JVM warm up)
    * - All : Measures all of the above
    *
    * These can be configured via @BenchmarkMode annotation, for example:
    * @BenchmarkMode(Mode.AverageTime)
    * You need to put this just above the method which you want to test
    *  */

    /******************************* 1.5 Benchmark Time Units ******************************************/

    /*
    * JMH enables you to specify what time units you want the benchmark results printed in. The time unit will be
    * used for all benchmark modes your benchmark is executed in.
    *
    * You specify the benchmark time unit using the JMH annotation @OutputTimeUnit. The @OutputTimeUnit annotation
    * takes a java.util.concurrent.TimeUnit as parameter to specify the actual time unit to use. Check JMHTest01.exp1()
    * to see a JMH @OutputTimeUnit annotation example:
    *
    * The TimeUnit class contains the following time unit constants:
    * - NANOSECONDS
    * - MICROSECONDS
    * - MILLISECONDS
    * - SECONDS
    * - MINUTES
    * - HOURS
    * - DAYS
    * */

    /******************************* 1.6 Benchmark State ******************************************/

    /*
    * Sometimes you way want to initialize some variables that your benchmark code needs, but which you do not want to
    * be part of the code your benchmark measures. Such variables are called "state" variables. State variables are
    * declared in special state classes, and an instance of that state class can then be provided as parameter to
    * the benchmark method. This may sound a bit complicated. Check JMHTest02 to see a JMH benchmark state example:
    *
    * In this example I have added a nested static class named MyState. The MyState class is annotated with the JMH
    * @State annotation. This signals to JMH that this is a state class. Notice that the testMethod() benchmark method
    * now takes an instance of MyState as parameter.
    *
    * Notice also that the testMethod() body has now been changed to use the MyState object when performing its
    * sum calculation.
    *
    * State Scope
    * A state object can be reused across multiple calls to your benchmark method. JMH provides different "scopes"
    * that the state object can be reused in. There state scope is specified in the parameter of the @State annotation.
    * In the example above the scope chosen was Scope.Thread
    * The Scope class contains the following scope constants:
    * - Thread:	Each thread running the benchmark will create its own instance of the state object.
    * - Group:	Each thread group running the benchmark will create its own instance of the state object.
    * - Benchmark:	All threads running the benchmark share the same state object.
    *
    * State Class requirements
    * A JMH state class must obey the following rules:
    * - The class must be declared public
    * - If the class is a nested class, it must be declared static
    * - The class must have a public no-arg constructor
    *
    * State Object @Setup and @TearDown
    * You can annotate methods in your state class with the @Setup and @TearDown annotations. The @Setup annotation
    * tell JMH that this method should be called to setup the state object before it is passed to the benchmark method.
    * The @TearDown annotation tells JMH that this method should be called to clean up ("tear down") the state object
    * after the benchmark has been executed.
    *
    * The setup and tear down execution time is not included in the benchmark runtime measurements.
    * Check JMHTest03 to see an example of the @Setup and @TearDown
    *
    * Notice that the @Setup and @TearDown annotations take a parameter. This parameter instructs JMH about when the
    * method should be called. The possible value are:
    * - Level.Trial : The method is called once for each time for each full run of the benchmark. A full run means a
    *                 full "fork" including all warmup and benchmark iterations.
    * - Level.Iteration : The method is called once for each iteration of the benchmark.
    * - Level.Invocation : The method is called once for each call to the benchmark method.
    *
    * If you have any doubts about when a setup or tear down method is called, try inserting a System.out.println()
    * statement in the method. Then you will see. Then you can change the @Setup and @TearDown() parameter values until
    * your setup and tear down methods are called at the right time.
    * */

    /******************************* 1.7 Writing Good Benchmarks ******************************************/

    /*
    * Now that you have seen how to use JMH to write benchmarks, it is time to discuss how to write good benchmarks.
    * As mentioned in the beginning of this JMH tutorial there are a couple of pitfalls that you can easily fall into
    * when implementing benchmarks. I will discuss some of these pitfalls in the following sections.
    *
    * One common pitfall is that the JVM may apply optimizations to your components when executed inside the benchmark
    * which could not have been applied if the component was executed inside your real application. Such optimizations
    * will make your code look faster than it will be in reality. I will discuss some of these optimizations later.
    *
    * */

    /** 1.7.1 Loop optimizations
     * It is tempting to put your benchmark code inside a loop in your benchmark methods, in order to repeat it more
     * times per call to the benchmark method (to reduce the overhead of the benchmark method call). However, the JVM
     * is very good at optimizing loops, so you may end up with a different result than what you expected. In general
     * you should avoid loops in your benchmark methods, unless they are part of the code you want to measure (and not
     * around the code you want to measure).
     * */

    /** 1.7.2 Dead Code Elimination
     * One of the JVM optimizations to avoid when implementing performance benchmarks is dead code elimination. If the
     * JVM detects that the result of some computation is never used, the JVM may consider this computation dead code
     * and eliminate it. Look at this benchmark example:
     * @Benchmark
     *     public void testMethod() {
     *         int a = 1;
     *         int b = 2;
     *         int sum = a + b;
     *     }
     * The JVM can detect that the calculation of a+b which is assigned to sum is never used. Therefore the JVM can
     * remove the calculation of a+b altogether. It's considered dead code. Then as a+b is never used, JVM can determine
     * a, b are never used. So they can be removed too. In the end, there is no code left in the benchmark.
     *
     * The results from running this benchmark are thus highly misleading. The benchmarks do not actually measure the
     * time of adding two variables and assigning the value to a third variable. The benchmarks measure nothing at all.
     *
     * */

    /** 1.7.3 Avoiding Dead Code Elimination
     * To avoid dead code elimination you must make sure that the code you want to measure does not look like dead code
     * to the JVM. There are two ways to do that:
     * - Return/print the result of your code from the benchmark method.
     * - Pass the calculated value into a "black hole" provided by JMH.
     *
     * In JMHTest01 class, we have seen that I use println to print the result, you can also modify the method to return
     * the sum. It works too.
     *
     * An alternative to returning a combined value is to pass the calculated values (or returned / generated objects
     * or whatever the result of your benchmark is) into a JMH black hole. Here is how passing values into a black hole
     * looks:
     *  @Benchmark
     *    public void testMethod(Blackhole blackhole) {
     *         int a = 1;
     *         int b = 2;
     *         int sum = a + b;
     *         blackhole.consume(sum);
     *     }
     *
     * Notice how the testMethod() benchmark method now takes a Blackhole object as parameter. This will be provided to
     * the test method by JMH when called.
     *
     * Notice also how the calculated sum in the sum variable is now passed to the consume() method of the
     * Blackhole instance. This will fool the JVM into thinking that the sum variable is actually being used.
     *
     * If your benchmark method produces multiple results you can pass each of these results to a black hole, meaning
     * calling consume() on the Blackhole instance for each value.
     * */

    /** 1.7.4 Constant folding
     * Constant folding is another common JVM optimization. A calculation which is based on constants will often
     * result in the exact same result, regardless of how many times the calculation is performed. The JVM may detect
     * that, and replace the calculation with the result of the calculation. For example,
     * public int testMethod(){
     * int a = 1;
     * int b = 2;
     * int sum = a + b;
     * return sum;}
     *
     * the above code will be replaced by JVM to the following code
     * public int testMethod(){
     * int sum = 3;
     * return sum;}
     * The JVM could even continue and never call the testMethod() because it knows it always returns 3, and just
     * inline the constant 3 wherever the testMethod() was to be called.
     *
     * To avoid constant folding, you must not hardcode constants into your benchmark methods. Instead, the input to
     * your calculations should come from a state object.
     * */

    /******************************* 1.8 Advance benchmark example ******************************************/

    /*
    * In JMHTest04, we create a arraylist of string, then we use four different ways to loop this list. You could see
    * something new, we did not create a state class, the test class can now be a state class. The @Param can
    * initialize variables. The @Setup indicates run this method before benchmarks.
    * @Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"}) means use two process with jvm config to run the benchmark.
    * Warmup iteration and measuring iteration are configurable : for example
    * @Warmup(iterations = 3) // Warmup Iteration = 3
    * @Measurement(iterations = 8) 	// Iteration = 8
    *
    * We even can warm up the entire fork, before started the real fork for measuring.
    * @Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"}, warmups = 2)
    *
    * You can notice, we can set global config at class level, which apply to all method. We can also use local config
    * at method level which apply only to the method. In JMHTest04, @OutputTimeUnit(TimeUnit.MILLISECONDS) is the
    * global config, @BenchmarkMode(Mode.AverageTime) in each method is the local config.
    *
    * Benchmark                                                                   (N)  Mode  Cnt  Score   Error  Units
    * Lesson00_Java_Basics.A01_Microbenchmarking.source.JMHTest04.loopFor       10000  avgt    5  0.034 ± 0.001  ms/op
    * Lesson00_Java_Basics.A01_Microbenchmarking.source.JMHTest04.loopForEach   10000  avgt    5  0.044 ± 0.010  ms/op
    * Lesson00_Java_Basics.A01_Microbenchmarking.source.JMHTest04.loopIterator  10000  avgt    5  0.040 ± 0.001  ms/op
    * Lesson00_Java_Basics.A01_Microbenchmarking.source.JMHTest04.loopWhile     10000  avgt    5  0.034 ± 0.001  ms/op
    *
    * We can notice, the primitive for and while loop is quicker thant the iterator > for each loop. But the difference
    * is eligible.
     * */

    public static void main(String[] args) throws IOException, RunnerException {

    /** 1.3 Use JMH runner to run the benchmark for JMHTest01 */
   /* Options opt1 = new OptionsBuilder()
            .include(JMHTest01.class.getSimpleName())
            .forks(1)
            .build();

    new Runner(opt1).run();*/

    /** 1.6 Benchmark state*/
    /*Options opt2 = new OptionsBuilder()
            .include(JMHTest02.class.getSimpleName())
            .forks(1)
            .build();

    new Runner(opt2).run();*/

    /** 1.8 Advance benchmark example */
        Options opt3 = new OptionsBuilder()
                .include(JMHTest04.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt3).run();
}
}
