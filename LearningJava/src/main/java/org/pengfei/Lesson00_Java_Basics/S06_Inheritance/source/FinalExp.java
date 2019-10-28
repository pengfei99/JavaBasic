package org.pengfei.Lesson00_Java_Basics.S06_Inheritance.source;

public class FinalExp {

    // When we declare a constant, we use all cap letters.
    public final int MAX_QUEUE_SIZE;

    public FinalExp(int n){
        MAX_QUEUE_SIZE=n;

    }

    public int getMAX_QUEUE_SIZE() {
        return MAX_QUEUE_SIZE;
    }

    // We can't create setter for the final field.
}
