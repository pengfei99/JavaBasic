package org.pengfei.Lesson00_Java_Basics.S05_Advance_Classes_Methods.source;

public class NestedClassExp {
    int[] nums;

    public NestedClassExp(int[] n) {
        nums = n;
    }

    public void analyze() {

        Inner inOb=new Inner();
        System.out.println("Minimum: "+inOb.getMin());
        System.out.println("Maximum: "+inOb.getMax());
        System.out.println("Average: "+inOb.getAvg());
    }

    private class Inner

    {
        int getMin () {
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        return min;

    }

     int getMax(){
         int max = nums[0];
         for (int i = 1; i < nums.length; i++) {
             if (nums[i] > max) {
                 max = nums[i];
             }
         }
         return max;
     }

     int getAvg(){
         int sum=0;
         for (int i = 0; i < nums.length; i++) {
           sum +=nums[i];
         }
         return sum/nums.length;
     }
    }
}
