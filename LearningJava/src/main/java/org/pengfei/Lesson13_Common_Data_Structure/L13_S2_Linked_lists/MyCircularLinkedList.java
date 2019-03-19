package org.pengfei.Lesson13_Common_Data_Structure.L13_S2_Linked_lists;

public class MyCircularLinkedList<E> {
    // We no longer need to store head, because head is just tail.getNext()
    // private Node<E> head=null;
    private Node<E> tail=null;
    private int size=0;

    // MyCircularLinkedList public constructor, it builds an empty list at the beginning
    public MyCircularLinkedList(){}

    /* Getters for the list */
    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public E first() {
        if(isEmpty()) return null;
        return tail.getNext().getElement();
    }

    public E last() {
        if(isEmpty()) return null;
        return tail.getElement();
    }

    /* Setters for the list */
    public void addFirst(E e) {
        if (size==0) {
            tail=new Node(e,null);
            //tail's next is tail himself, because of only one node in the list
            tail.setNext(tail);
        }
       else {
            //Set head to newly created node
            Node<E> newNode = new Node(e, tail.getNext());
            // if null list, set tail to newly created node too
            tail.setNext(newNode);
        }
        // increment list size
        size++;
    }

    public void addLast(E e){
        // addLast in a circular linked list is just like a addFirst and change the tail to the newly added node
     addFirst(e);
     tail=tail.getNext();

    }

    public E removeFirst() {
        // Before removing, we need to check if the list is null
        if (size == 0) {
            return null;
        } else {
            Node<E> head=tail.getNext();
            E first = head.getElement();
            tail.setNext(head);
            size--;
            // After removing the first, we need to check if the list become null
            if(size==0) tail=null;
            return first;
        }
    }
    /* This method can do the job of round-robin algo, it's always the tail of the list in the cpu process */
    public void rotate(){
        //if empty list, do nothing
        //else the old head become tail
        if(tail!=null)
            tail =tail.getNext();

    }



}
