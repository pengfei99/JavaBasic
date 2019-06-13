package org.pengfei.Lesson13_Common_Data_Structure.L13_S6_List;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*Todo, revsite this class iterator impelementation, have null return value when do loop with iterator*/
public class LinkedPositionalList<E> implements PositionalList<E> {
    private Node<E> trailer;
    private Node<E> header;
    private int size = 0;

    public LinkedPositionalList() {
        header = new Node<E>(null, null, null);
        trailer = new Node<E>(null, header, null);
        header.setNext(trailer);
    }

    /**
     * Validates the position and returns it as a node.
     */

    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Position)) throw new IllegalArgumentException("Not a valide postion");
        Node<E> node = (Node) p; //safe cast
        if (node.getNext()==null) throw new IllegalArgumentException("p is no longer in the list");
        return node;

    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public Position<E> first() {
        if (size>0) return header.getNext();
        else return null;
    }

    @Override
    public Position<E> last() {
        if (size>0) return trailer.getPrev();
        else return null;
    }

    @Override
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> result =  node.getPrev();
        //if the result is the header, throw an exception
        if (result.getPrev()==null) throw new IllegalArgumentException("The element befor your giving position is the header");
        else return (Position<E>) result;
    }

    @Override
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> result=node.getNext();
        //if the result is the trailer, throw an exception
        if (result.getNext()==null) throw new IllegalArgumentException("The element after your giving position is the trailer");
        else return (Position<E>) result;
    }

    /*private utilities. Adds element e to the linked list between the given nodes. */
    public Position<E> addBetween(E element, Node<E> pred, Node<E> succ) {
        Node<E> newest = new Node(element, pred, succ);
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;

    }
    /** Add a method to avoid take trailer as the last element of the list*/
    public boolean lastElement(Position<E> p) throws IllegalArgumentException {
        Node node=validate(p);
        if(node.getNext()==null) return true;
        else if (node.getNext().getElement()==null) return true;
        else return false;
    }

    /** Add a method to avoid take header as the first element of the list*/
    public boolean firstElement(Position<E> p) throws IllegalArgumentException {
        Node node=validate(p);
        if(node.getPrev()==null) return true;
        else if (node.getPrev().getElement()==null) return true;
        else return false;
    }

    @Override
    public Position<E> addFirst(E e) {

        return addBetween(e, header, header.getNext());
    }

    @Override
    public Position<E> addLast(E e) {
        return addBetween(e, trailer.getPrev(), trailer);
    }

    @Override
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node node = validate(p);
        return (Position) addBetween(e, node.getPrev(), node);
    }

    @Override
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node node = validate(p);
        return (Position) addBetween(e, node, node.getNext());
    }

    @Override
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    @Override
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        E answer = node.getElement();
        node.setElement(null); // help with garbage collection
        node.setNext(null); // and convention for defunct node
        node.setPrev(null);
        return answer;
    }


    /************************************************** nested Node class *************************************/
    private static class Node<E> implements Position<E> {

        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public E getElement() {
          // if (this.next == null) throw new IllegalStateException("Position no longer valide");
            return this.element;

        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    /************************************** nested PositionIterator class **************************************/
    private class PositionIterator implements Iterator<Position<E>> {
        private Position<E> cursor = first(); // position of the next element to report
        private Position<E> recent = null; // position of last reported element

        /**
         * Tests whether the iterator has a next object.
         */
        public boolean hasNext() {
            //Because our implementation of LinkedPostionalList has tail which is null as the last element,
            // we must also check the element after cursor is not tail. Because if the position after cursor is
            // tail, then the cursor is the last valide position, which does not havsNext is false.

            if(cursor==LinkedPositionalList.this.trailer) return false;
            else return true;
            // return (cursor != null && LinkedPositionalList.this.after(cursor)!=null);
        }

        /**
         * Returns the next position in the iterator.
         */
        public Position<E> next() throws NoSuchElementException {
            if (cursor == null) throw new NoSuchElementException("nothing left");
            recent = cursor; // element at this position might later be removed
            cursor = LinkedPositionalList.this.after(cursor);
            return recent;
        }

        /**
         * Removes the element returned by most recent call to next.
         */
        public void remove() throws IllegalStateException {
            if (recent == null) throw new IllegalStateException("nothing to remove");
            LinkedPositionalList.this.remove(recent); // remove from outer list
            recent = null; // do not allow remove again until next is called
        }
    }

    /*********************************** end of nested PositionIterator class *****************************************/

    /************************************nested PositionIterable class************************************/
    private class PositionIterable implements Iterable<Position<E>> {
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }
    /************************************ end of nested PositionIterable class************************************/

    /**
     * Returns an iterable representation of the list's positions.
     */
    public Iterable<Position<E>> positions() {
        return new PositionIterable(); // create a new instance of the inner class
    }

     /************************************nested ElementIterator class************************************/
    /* This class adapts the iteration produced by positions() to return elements. */
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = new PositionIterator();

        public boolean hasNext() {
            return posIterator.hasNext();
        }

        public E next() {
            return posIterator.next().getElement();
        } // return element!

        public void remove() {
            posIterator.remove();
        }
    }

    /**
     * Returns an iterator of the elements stored in the list.
     */
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

}
