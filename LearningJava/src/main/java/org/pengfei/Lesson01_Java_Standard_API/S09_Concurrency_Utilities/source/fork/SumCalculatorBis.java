package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.fork;

import java.util.concurrent.RecursiveTask;

public class SumCalculatorBis extends RecursiveTask<Double> {
    private final int seqThreshold;

    private double[] data;

    private int start, end;

    public SumCalculatorBis(double[] data, int threshold, int start, int end){
        this.data=data;
        this.seqThreshold=threshold;
        this.start=start;
        this.end=end;
    }

    @Override
    protected Double compute() {
        double sum=0;
        // if the number of elements is below the sequential threshold, then process sequentially.
        if((end-start)<seqThreshold) {
            for(int i=start;i<end;i++){
                sum+=data[i];
            }
        }

        else {
            //otherwise continue to break the data into smaller pieces
            int mid = (start + end) / 2;

            // Invoke two new sub tasks, using the subdivided data.
            SumCalculator subTaskA = new SumCalculator(data, seqThreshold, start, mid);
            SumCalculator subTaskB = new SumCalculator(data, seqThreshold, mid, end);

            // we can use invoke to replace fork and join, and aggregate the results.
            //sum = subTaskA.invoke() + subTaskB.invoke();

            // we can also use compute directly
            sum=subTaskA.compute()+subTaskB.compute();
        }
        return sum;
    }
}
