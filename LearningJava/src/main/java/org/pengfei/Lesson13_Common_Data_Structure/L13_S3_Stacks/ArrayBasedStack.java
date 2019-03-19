package org.pengfei.Lesson13_Common_Data_Structure.L13_S3_Stacks;

public class ArrayBasedStack<E> implements Stack<E>{
    public static final int CAPACITY=1000;
    private E[] data;
    private int t=-1;

    public ArrayBasedStack(){
        this(CAPACITY);
    }
    public ArrayBasedStack(int capacity){
        data=(E[])new Object[capacity];
    }

    @Override
    public int size() {
        return t+1;
    }

    @Override
    public boolean isEmpty() {
        return t==-1;
    }

    @Override
    public void push(E e) throws IllegalStateException{
        // the length of array is fixed at creation time, in our case, it's the CAPACITY
        // So if the size of the stack equals to the array data length, then the stack is full

       if(size()==data.length) {throw new IllegalStateException("Stack is full");}
       //increment stack index
       t++;
       // add element to array
       data[t]=e;
    }

    @Override
    public E top() {
        if (isEmpty()) return null;
        return data[t];
    }

    @Override
    public E pop() {
        if(isEmpty()) return null;
        E result=data[t];
        data[t]=null; // this looks useless, but dereference can help garbage collection
        t=t-1;
        return result;
    }
}
