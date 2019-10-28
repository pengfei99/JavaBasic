package org.pengfei.Lesson00_Java_Basics.S07_Interfaces_Packages.source;

public interface Series {
    int MAX_EPISODE_NUM=100;
    int getNext(); // return next number in series
    void reset(); //restart
    void setStart(int x); // set starting value
    default int getMaxEpisodeNum(){
        return MAX_EPISODE_NUM;
    }

    // This method returns an array that contains the next n elements in the series beyond the current element
    default int[] getNextArray(int n){
        int[] values=new int[n];
        for(int i=0;i<n;i++) values[i]=getNext();
        return values;
    }
}
