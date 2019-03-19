package org.pengfei.Lesson13_Common_Data_Structure.L13_S6_List;

public interface MyList<E> {
  int size();// Returns the number of elements in the list.
           boolean isEmpty(); // Returns a boolean indicating whether the list is empty.

    /*Returns the element of the list having index i; an error condition occurs if i is
     * not in range [0, size( )−1].*/
       E get(int i) throws IndexOutOfBoundsException;

    /* Replaces the element at index i with e, and returns the old element that was replaced;
     * an error condition occurs if i is not in range[0, size( )−1].*/
       void set(int i, E e) throws IndexOutOfBoundsException;

       /* Inserts a new element e into the list so that it has index i, moving all subsequent elements
     * one index later in the list; an error condition occurs if i is not in range [0, size( )].*/
         void add(int i,E e)throws IndexOutOfBoundsException;

    /* Removes and returns the element at index i, moving all subsequent elements one index earlier
     * in the list; an error condition occurs if i is not in range [0, size( )−1].*/
    E remove(int i)throws IndexOutOfBoundsException;
}
