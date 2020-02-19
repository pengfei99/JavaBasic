package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.semaphore;

import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.SynchronizationObjExample;

import java.util.concurrent.Semaphore;

/*This class illustrate one synchronized queue which host only one integer. It starts with
 * one permit for producer, zero permit for consumer. So no consumer can reads it, and
 * one producer can write it.
 * After one producer writes data on it, it sets a permit for consumer. remove a permit
 * for producer, so consumer can get the data, and no producer can overwrite the data
 * before a consumer reads the data. */
public class SynchronizedQueue {
    int data;

    //create two semaphore, one for producer with one initial permit, one for consumer with zero initial permit
    static Semaphore prodSema = new Semaphore(1);
    static Semaphore conSema = new Semaphore(0);

    //method for consumer to read data
    public void get(){
        try {
            // first get a permit from consumer semaphore
            conSema.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //consume a data
        System.out.println("Got: "+data);

        //release producer semaphore to notify producer that it can write new data now.
        prodSema.release();
    }

    //method for producer to write data
    public void put(int data){

        try {
            // get a permit from producer semaphore
            prodSema.acquire();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.data=data;
        System.out.println("Put: "+data);

        //release consumer semaphore to notify consumer that it can read data now
        conSema.release();
    }


}
