package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.executor;

import java.util.concurrent.Callable;

public class Sum implements Callable<Integer> {

    private final int stop;

    public Sum(int stop){
        this.stop=stop;
    }

    @Override
    public Integer call() throws Exception {
        int sum=0;
        for(int i=0;i<stop;i++) sum+=i;
        return sum;
    }
}
