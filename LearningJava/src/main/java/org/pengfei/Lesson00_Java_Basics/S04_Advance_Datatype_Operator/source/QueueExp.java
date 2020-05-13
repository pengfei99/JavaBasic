package org.pengfei.Lesson00_Java_Basics.S04_Advance_Datatype_Operator.source;

import java.util.Arrays;

public class QueueExp {

private char[] data;
private int size,getHead=0,elementCount=0;

public QueueExp(int size){
    this.size=size;
    this.data=new char[size];
}

/** In the put method, I use the head of get and element size to determine the head of put. The getHead+elementCount
 * can be greater than size. because get method has been called during the put which has free spaces.*/
public void put(char element){
    //throw out of bounds exception
    if(elementCount>size-1){throw new IndexOutOfBoundsException(" queue is full");}
    int index=(getHead+elementCount)%size;
    data[index]=element;
    elementCount++;
}

public int get(){
    if(elementCount==0){throw new IndexOutOfBoundsException(" queue is empty");}
    char result=data[getHead];
    data[getHead]='0';
    getHead=(getHead+1)%size;
    elementCount--;
    return result;
}

public int size(){
    return elementCount;
}

public void showQueue(){
    System.out.println(Arrays.toString(data));
}


}
