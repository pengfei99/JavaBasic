package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.fork;

import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.executor.Factorial;

import java.util.concurrent.RecursiveAction;

public class FlexibleThresholdTransform extends RecursiveAction {

    private int seqThreshold;
    double[] data;
    int start, end;

    // we can set the threshold number in the constructor
    public FlexibleThresholdTransform(double[] data, int seqThreshold, int start, int end){
        this.data=data;
        this.seqThreshold=seqThreshold;
        this.start=start;
        this.end=end;
    }

    @Override
    protected void compute() {
      if((end-start)<seqThreshold){
          for(int i=start;i<end;i++){
              // if the value is even, then get the square root
              if ((data[i] % 2)==0) data[i]=Math.sqrt(data[i]);
              // otherwise, get the cubic root.
              else data[i]=Math.cbrt(data[i]);
          }
      }
      else{
          int mid=(start+end)/2;
          invokeAll(new FlexibleThresholdTransform(data,seqThreshold,start,mid),
                  new FlexibleThresholdTransform(data,seqThreshold,mid,end));
      }
    }
}
