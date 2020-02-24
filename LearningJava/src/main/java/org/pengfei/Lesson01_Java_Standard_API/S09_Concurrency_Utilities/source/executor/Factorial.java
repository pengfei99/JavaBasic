package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.executor;

import java.util.concurrent.Callable;

public class Factorial implements Callable<Integer> {
    private final int stop;
    public Factorial(int stop){
        this.stop=stop;
    }

    @Override
    public Integer call() throws Exception {
        int result=1;
        for(int i=2;i<=stop;i++) result = result * i;
        return result;
    }
}
