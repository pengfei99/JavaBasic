package org.pengfei.Lesson13_Common_Data_Structure.L13_S4_Queues.source;

public class ArrayBasedQueue<E> implements Queue<E> {

    private E[] data;
    private static final int CAPACITY=100;
    private int f = 0; // index of the front element
    private int size = 0; // current number of elements in the queue

    public ArrayBasedQueue(){
        this(CAPACITY);
    }
    public ArrayBasedQueue(int capacity){
        this.data=(E[])new Object[capacity];
    }
    @Override
    public void enqueue(E e) throws IllegalStateException{
        //check if queue is full or not
        if(size>data.length) throw new IllegalStateException("THE queue is full.");
        int index=f+size%data.length; // use modular to find the end of the queue
        data[index]=e;
        size++;
    }

    @Override
    public E dequeue() {
        if(isEmpty()) return null;
        E result=data[f];
        data[f]=null; // dereference for garbage collector
        f=(f+1)%data.length;
        size--;
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Boolean isEmpty() {
        return (size==0);
    }

    @Override
    public E first() {
        if(isEmpty()) return null;
        return this.data[f];
    }
}
