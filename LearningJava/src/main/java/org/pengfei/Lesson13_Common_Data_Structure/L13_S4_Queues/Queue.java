package org.pengfei.Lesson13_Common_Data_Structure.L13_S4_Queues;

public interface Queue<E> {

    /** Inserts an element at the rear of the queue*/
    void enqueue(E e) ;

    /** Removes and returns the first element of the queue */
    E dequeue();

    /** Returns the size of the queue*/
    int size();

    /** Test whether the queue is empty*/
    Boolean isEmpty();

    /** Returns the first element of the queue without removing it. */
    E first();

}
