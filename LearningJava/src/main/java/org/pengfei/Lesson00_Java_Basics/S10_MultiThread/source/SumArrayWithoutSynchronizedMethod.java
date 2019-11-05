package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source;

public class SumArrayWithoutSynchronizedMethod {
    private int sum;

    // this method is not synchronized
     int sumArray(int nums[]){
        sum=0;
        for (int i=0; i<nums.length;i++) {
            sum+=nums[i];
            // Get the current thread which called this method
            System.out.println("Running total for "+Thread.currentThread().getName()+" is "+sum);
            // allow thread task switch
            try{Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
            }
        }
        return sum;
    }
}
