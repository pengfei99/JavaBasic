package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.semaphore.build_hho_semaphore;

import java.util.concurrent.Semaphore;

public class H2O {
    public Semaphore hSem;
    public Semaphore oSem;
    int n=0;
    public H2O() {
        hSem= new Semaphore(2);
        oSem= new Semaphore(0);
    }

    public void hydrogen() throws InterruptedException {
        hSem.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        System.out.println("H");

        /*n++;
        if(n>1){
            oSem.release();
            n=0;
        }*/
        // we use availablePermits() method to check the number of permit of a semaphore
        if(hSem.availablePermits()==0){
            oSem.release();
        }
    }

    public void oxygen() throws InterruptedException {
        oSem.acquire();
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        System.out.println("O");
        hSem.release(2);
    }

    public static void main(String[] args){
        int num=4;
        H2O h2o=new H2O();
        for(int i=0;i<num;i++){
            new ThreadForH2O("o",h2o).start();
            new ThreadForH2O("h",h2o).start();



            new ThreadForH2O("h",h2o).start();
        }
    }
}
