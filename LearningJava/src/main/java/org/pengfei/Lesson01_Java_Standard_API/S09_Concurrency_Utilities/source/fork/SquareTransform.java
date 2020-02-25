package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.fork;

import java.nio.MappedByteBuffer;
import java.util.concurrent.RecursiveAction;

/* This class is forkjoin task which transforms the elements of an array of doubles into their square roots. As
* this class only transform element of an array, and does not return any value so we use RecursiveAction, not
* RecursiveTask.
* */
public class SquareTransform extends RecursiveAction {

    // We fixed the threshold at 1000 in this example. In real-world code, its optimal value can be determined
    // by profiling and experimentation
    final int seqThreshold=1000;

    // Array to be accessed
    double[] data;

    // determines which part of data to process.
    int start, end;

    public SquareTransform(double[] values, int start, int end){
        data=values;
        this.start=start;
        this.end=end;
    }

    // This is the method in which parallel computation will occur.
    @Override
    protected void compute() {

        // if the number of elements is below the sequential threshold then process sequentially.
        if((end-start)<seqThreshold){
            //transform each element into its square root.
            for(int i=start;i<end;i++){
                System.out.println("before transformation Data value of "+i+" is: "+data[i]);
                data[i]= Math.sqrt(data[i]);
                System.out.println("after transformation Data value of "+i+" is: "+data[i]);
            }
        }
        //otherwise, continue to break the data into small pieces.
        else {
            // Find the midpoint to break the array into two new arrays
            int mid=(start+end)/2;

            // create two new sub task, wait them to finish, so we use invoke, not execute.
            invokeAll(new SquareTransform(data,start,mid),new SquareTransform(data,start,mid));
        }
    }
}
