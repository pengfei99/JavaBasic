package org.pengfei.Lesson13_Common_Data_Structure.L13_S8_Priority_Queues.source;

import java.util.Comparator;

public abstract class MyAbstractPriorityQueue<K, V> implements MyPriorityQueue<K, V> {

// instance variable for an AbstractPriorityQueue
    /**
     * The comparator defining the ordering of keys in the priority queue.
     */
    private Comparator<K> comp;

    /**
     * Creates an empty priority queue using the given comparator to order keys.
     */
    protected MyAbstractPriorityQueue(Comparator<K> c) {
        comp = c;
    }

    /**
     * Creates an empty priority queue based on the natural ordering of its keys.
     */
    protected MyAbstractPriorityQueue() {
        this(new DefaultComparator<K>());
    }

    /**
     * Method for comparing two entries according to key
     */
    protected int compare(MyEntry<K, V> a, MyEntry<K, V> b) {
        return comp.compare(a.getKey(), b.getKey());

    }

    /**
     * Determines whether a key is valid.
     */
    protected boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return (comp.compare(key, key) == 0); // see if key can be compared to itself

        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Incompatible key");

        }
    }

    /**
     * Tests whether the priority queue is empty.
     */
    public boolean isEmpty() {
        return size() == 0;
    }


    /***************************Nested PQEntry ************************************/
    protected static class PQEntry<K, V> implements MyEntry<K, V> {

        private K key;
        private V value;

        public PQEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return value;
        }

        // utilities not exposed as part of the Entry interface
        protected void setKey(K key) {
            this.key = key;
        }

        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }
    }
    /***************************End of Nested PQEntry ************************************/
}
