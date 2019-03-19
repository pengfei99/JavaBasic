package org.pengfei.Lesson13_Common_Data_Structure.L13_S3_Stacks;

import org.pengfei.Lesson13_Common_Data_Structure.L13_S2_Linked_lists.MySinglyLinkedList;

public class SinglyLinkedListBasedStack<E> implements Stack<E> {

    private MySinglyLinkedList myList=new MySinglyLinkedList();

    public SinglyLinkedListBasedStack(){}

    @Override
    public int size() {
        return myList.size();
    }

    @Override
    public boolean isEmpty() {
        return myList.isEmpty();
    }

    @Override
    public void push(E e) {
       myList.addFirst(e);
    }

    @Override
    public E top() {
        return (E)myList.first();

    }

    @Override
    public E pop() {
        return (E)myList.removeFirst();
    }


}
