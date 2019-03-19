package org.pengfei.Lesson13_Common_Data_Structure.L13_S5_Double_Ended_Queue;

public class CircularArrayBasedDeque<E> implements Deque<E> {

    private E[] data;
    private final static int CAPACITY=100;
    private int index; // index of the first element of the deque
    private int size;  // size of the deque

    public CircularArrayBasedDeque(int capacity){
        data=(E[])new Object[capacity];
    }

    public CircularArrayBasedDeque(){
        this(CAPACITY);
    }


    @Override
    public void addFirst(E e) throws IllegalStateException {
        if(size==data.length){throw new IllegalStateException("Deque is full");}
        /* The calculation of the new index is a little tricky. Because java does not support negative modulo
        * For example -1 % 3 in Java will return -1 instead of 2.
        * To avoid this problem we need to use equation (f-1)%N=(f-1+N)%N , so -1+3%3= 2*/
        int newIndex=(index-1+data.length)%data.length; // data.length is the size of the array which is the capacity
      data[newIndex]=e;
      index=newIndex;
      size++;
    }

    @Override
    public void addLast(E e) {
        if(size==data.length){throw new IllegalStateException("Deque is full");}
        data[(index+size)%data.length]=e;
        size++;
    }

    @Override
    public E removeFirst() {
        if(isEmpty()) return null;
        E result=data[index];
        data[index]=null; // dereference for GC
        index=(index+1)%data.length;
        size--;
        return result;
    }

    @Override
    public E removeLast() {
       if(isEmpty()) return null;
       E result=data[(index+size-1)%data.length];
        data[(index+size-1)%data.length]=null; // dereference for GC
        size--;
        return result;
    }

    @Override
    public E first() {
        return data[index];
    }

    @Override
    public E last() {
        return data[(index+size-1)%data.length];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size==0);
    }

    public void showDeque(){
        if(isEmpty()) System.out.println("The deque is empty");
        for(int i=index;i<index+size;i++){
            System.out.print(data[i%data.length]+",");
        }
        System.out.println("");
    }
}
