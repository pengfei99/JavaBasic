package org.pengfei.Lesson00_Java_Basics.S07_Interfaces_Packages.source;

public interface AdvanceSeries {
    int getNext();

    // A private method that returns an array containing the next n elements
    private int[] getArray(int n){
        int[] values=new int[n];
        for(int i=0;i<n;i++){
            values[i]=getNext();
        }
        return values;
    }

    // A default method called the private method, returns an array that contains the
    // next n element in the series beyond the current element
    default int[] getNextArray(int n){
        return getArray(n);
    }

    // A default method called the private method, returns an array that contains the
    // next n element in the series after skipping the k element
    default int[] skipAndGetNextArray(int skip, int n){
        getArray(skip);
        return getArray(n);
    }
}
