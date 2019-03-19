package org.pengfei.Lesson13_Common_Data_Structure.L13_S5_Double_Ended_Queue;

public interface Deque<E> {


    /** Insert a new element e at the front of the deque.*/
    void addFirst(E e);
    /** Insert a new element e at the back of the deque.*/
    void addLast(E e);
    /** Remove and return the first element of the deque (or null if the deque is empty)*/
    E removeFirst();
    /** Remove and return the last element of the deque (or null if the deque is empty)*/
    E removeLast();
    /** Returns the first element, without removing it (or null if the deque is empty)*/
    E first();
    /** Returns the last element, without removing it (or null if the deque is empty)*/
    E last();
    /** Returns the number of element in the deque*/
    int size();
    /** Returns a boolean indicating whether the deque is Empty*/
    boolean isEmpty();

}
