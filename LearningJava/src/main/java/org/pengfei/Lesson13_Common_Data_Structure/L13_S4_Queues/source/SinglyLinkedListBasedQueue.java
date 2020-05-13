package org.pengfei.Lesson13_Common_Data_Structure.L13_S4_Queues.source;

import org.pengfei.Lesson13_Common_Data_Structure.L13_S2_Linked_lists.MySinglyLinkedList;
import org.pengfei.Lesson13_Common_Data_Structure.L13_S4_Queues.source.Queue;

public class SinglyLinkedListBasedQueue<E> implements Queue<E> {

    private MySinglyLinkedList<E> data;

    public SinglyLinkedListBasedQueue(){
        this.data=new MySinglyLinkedList<E>();
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
