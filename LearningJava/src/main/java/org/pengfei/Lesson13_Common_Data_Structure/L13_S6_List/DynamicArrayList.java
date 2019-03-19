package org.pengfei.Lesson13_Common_Data_Structure.L13_S6_List;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArrayList<E> implements MyList<E> {
    private Object[] data;
    private static final int CAPACITY = 100;
    private int size;

    public DynamicArrayList() {
        this(CAPACITY);
    }

    public DynamicArrayList(int capacity) {
        data = new Object[capacity];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(1, size);
        return (E) data[i];
    }

    @Override
    public void set(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(1, size);
        data[i] = e;
    }

    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
        // check index
        checkIndex(1, size + 1);

        // check size to see if we need to increase array capacity two times or not
        if (size == data.length) resize(data.length * 2);

        //move all elements after i (including i) to the i+1
        for (int j = size; j > i; j--) {
            data[j] = data[j - 1];
        }
        data[i] = e;
        size++;

    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E result = (E) data[i];
        data[i] = null;
        size--;
        return result;
    }

    protected void resize(int newCapacity) {
        E[] temp = (E[]) new Object[newCapacity];

        for (int k = 0; k < size; k++) {
            temp[k] = (E) data[k];
        }

        data = temp;
    }

    private void checkIndex(int index, int size) throws IndexOutOfBoundsException {
        if (index > size || index < 0) throw new IndexOutOfBoundsException("The index is out of bounds");
    }

    /*---------------- nested ArrayIterator class ----------------*/

    /**
     * A (nonstatic) inner class. Note well that each instance contains an implicit
     * reference to the containing list, allowing it to access the list's members.
     */
    private class DArrayIterator implements Iterator<E> {
        private int j = 0; // index of the next element to report
        private boolean removable = false; // can remove be called at this time?

        /**
         * Tests whether the iterator has a next object.
         *
         * @return true if there are further objects, false otherwise
         */
        public boolean hasNext() {
            return j < size;
        } // size is field of outer instance(DynamicArrayList)

        /**
         * Returns the next object in the iterator.
         *
         * @return next object
         * @throws NoSuchElementException if there are no further elements
         */
        public E next() throws NoSuchElementException {
            if (j == size) throw new NoSuchElementException("No next element");
            removable = true; // this element can subsequently be removed
            return (E) data[j++]; // post-increment j, so it is ready for future call to next
        }

        /**
         * Removes the element returned by most recent call to next.
         *
         * @throws IllegalStateException if next has not yet been called
         * @throws IllegalStateException if remove was already called since recent next
         */
        public void remove() throws IllegalStateException {
            if (!removable) throw new IllegalStateException("nothing to remove");
            // call the outer instance remover method.
            DynamicArrayList.this.remove(j - 1); // that was the last one returned
            j--; // next element has shifted one cell to the left
            removable = false; // do not allow remove again until next is called
        }
    } //------------ end of nested ArrayIterator class ------------

    /**
     * Returns an iterator of the elements stored in the list.
     */
    public Iterator<E> iterator() {
        return new DArrayIterator(); // create a new instance of the inner class
    }
}
