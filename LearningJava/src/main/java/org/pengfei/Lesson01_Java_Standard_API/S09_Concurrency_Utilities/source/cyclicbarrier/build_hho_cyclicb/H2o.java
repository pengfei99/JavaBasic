package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.cyclicbarrier.build_hho_cyclicb;

import java.util.concurrent.CyclicBarrier;

public class H2o {


    public static void main(String[] args){
        ReleaseOxygen oxygen=new ReleaseOxygen("oxygen");
        CyclicBarrier cyclicBarrier=new CyclicBarrier(2,oxygen);
        ReleaseHydrogen h1=new ReleaseHydrogen("hydrogen",cyclicBarrier);
        ReleaseHydrogen h2=new ReleaseHydrogen("hydrogen",cyclicBarrier);
        h1.start();
        h2.start();

        //note if
        for(int i=0;i<5;i++){
            new ReleaseHydrogen("hydrogen"+i,cyclicBarrier).start();
        }
    }
}
