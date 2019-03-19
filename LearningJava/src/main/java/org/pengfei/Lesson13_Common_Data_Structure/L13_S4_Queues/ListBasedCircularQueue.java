package org.pengfei.Lesson13_Common_Data_Structure.L13_S4_Queues;

import org.pengfei.Lesson13_Common_Data_Structure.L13_S2_Linked_lists.MyCircularLinkedList;

public class ListBasedCircularQueue<E> implements CircularQueue<E>{

    MyCircularLinkedList<E> data;

    public ListBasedCircularQueue(){
        this.data=new MyCircularLinkedList<E>();
    }

    @Override
    public void rotate() {
        data.rotate();
    }

    @Override
    public void enqueue(E e) {
     data.addLast(e);
    }

    @Override
    public E dequeue() {
        if (data.isEmpty()) return null;
        return data.removeFirst();

    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public Boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public E first() {
        return data.first();
    }
}
