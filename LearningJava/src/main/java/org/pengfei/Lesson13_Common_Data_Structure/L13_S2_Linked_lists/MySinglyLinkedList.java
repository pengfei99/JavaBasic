package org.pengfei.Lesson13_Common_Data_Structure.L13_S2_Linked_lists;

public class MySinglyLinkedList<E> {
   /*
   * WE could declare the node as a nested class, but for reuse in other class, we declare a Node separatedly
   * *//****************************Nested Node class ***********************//*
    private static class Node<E>{
        private E element;
        private Node<E> next;
        //Node public constructor
        public Node(E e, Node<E> n){
            element=e;
            next=n;
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
    }*/

    private Node<E> head=null;
    private Node<E> tail=null;
    private int size=0;

    // MySinglyLinkedList public constructor, it builds an empty list at the beginning
    public MySinglyLinkedList(){}

    /* Getters for the list */
    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public E first() {
        return (E)head.getElement();
    }

    public E last() {
        return tail.getElement();
    }

    /* Setters for the list */
    public void addFirst(E e) {
        //Set head to newly created node
        Node<E> newNode=new Node(e,head);
        this.head = newNode;
        // if null list, set tail to newly created node too
        if (size==0) tail=newNode;
        // increment list size
        size++;
    }

   public void addLast(E e){
       Node<E> newNode=new Node(e,null);
       //if null list, set head, tail to new node
       if(size==0) {
           head=newNode;
       }
       else {
       //set current tail next reference to the new node
       tail.setNext(newNode);
       }
       //set current tail to new node
       tail=newNode;
       size++;


   }

    public E removeFirst() {
        // Before removing, we need to check if the list is null
        if (size == 0) {
            return null;
        } else {
            E first = head.getElement();
            head = head.getNext();
            size--;
            // After removing the first, we need to check if the list become null
            if(size==0) tail=null;
            return first;
        }
    }




}
