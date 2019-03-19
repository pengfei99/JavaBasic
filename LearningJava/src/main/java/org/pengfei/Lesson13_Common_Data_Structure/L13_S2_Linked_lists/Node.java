package org.pengfei.Lesson13_Common_Data_Structure.L13_S2_Linked_lists;


/****************************Nested Node class ***********************/
public class Node<E>{
    private E element;
    private Node<E> prev;

    public Node<E> getPrev() {
        return prev;
    }

    public void setPrev(Node<E> prev) {
        this.prev = prev;
    }

    private Node<E> next;
    //Node public constructor with one reference (next)
    public Node(E e, Node<E> n){
        element=e;
        next=n;
    }
    //Node public constructor with two reference (next, prev)
    public Node(E e, Node<E> n,Node<E> p){
        element=e;
        next=n;
        prev=p;
    }
    public E getElement(){
        return element;
    }
    public Node<E> getNext(){
        return next;
    }
    public void setNext(Node<E> n) {
        next=n;
    }
}