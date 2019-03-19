package org.pengfei.Lesson13_Common_Data_Structure.L13_S2_Linked_lists;

public class MyDoublyLinkedList<E> {
    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;


    /**
     * Constructs a new empty list.
     */
    public MyDoublyLinkedList() {
        header = new Node<>(null, null, null); // create header
        trailer = new Node<>(null, header, null); // trailer is preceded by header
        header.setNext(trailer); // header is followed by trailer
    }

    /**
     * Returns the number of elements in the linked list.
     */
    public int size() {
        return size;
    }

    /**
     * Tests whether the linked list is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns (but does not remove) the first element of the list.
     */
    public E first() {
        if (isEmpty()) return null;
        return header.getNext().getElement(); // first element is beyond header
    }

    /**
     * Returns (but does not remove) the last element of the list.
     */
    public E last() {
        if (isEmpty()) return null;
        return trailer.getPrev().getElement(); // last element is before trailer
    }

    /****************************** Setters method *************************************/

    public void addFirst(E e){
        addBetween(e,header,header.getNext());
    }

    public void addLast(E e){
        addBetween(e, trailer.getPrev(),trailer);
    }

    public E removeFirst(){
        if(isEmpty()) return null;
        return remove(header.getNext());
    }

    public E removeLast(){
        if(isEmpty()) return null;
        return remove(trailer.getPrev());
    }

    /* addBetween and remove method will help us build the addFirst, addLast, etc. */
    private void addBetween(E e, Node<E> predecessor, Node<E> successor){
        //create and link a new node
        Node<E> newest=new Node<E>(e,successor,predecessor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    private E remove(Node<E> node){
        Node<E> predecessor = node.getPrev();
        Node<E> successor=node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getElement();
    }

}

