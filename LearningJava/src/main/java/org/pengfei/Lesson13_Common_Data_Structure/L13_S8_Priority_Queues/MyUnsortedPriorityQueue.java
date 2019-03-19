package org.pengfei.Lesson13_Common_Data_Structure.L13_S8_Priority_Queues;

import org.pengfei.Lesson13_Common_Data_Structure.L13_S6_List.LinkedPositionalList;
import org.pengfei.Lesson13_Common_Data_Structure.L13_S6_List.Position;
import org.pengfei.Lesson13_Common_Data_Structure.L13_S6_List.PositionalList;

import java.util.Comparator;

public class MyUnsortedPriorityQueue<K, V> extends MyAbstractPriorityQueue<K, V> {
    /**
     * primary collection of priority queue entries
     */
    private PositionalList<MyEntry<K, V>> list = new LinkedPositionalList<>();

    /**
     * Creates an empty priority queue based on the natural ordering of its keys.
     */
    public MyUnsortedPriorityQueue() {
        super();
    }

    /**
     * Creates an empty priority queue using the given comparator to order keys.
     */
    public MyUnsortedPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Returns the Position of an entry having minimal key.
     */
    private Position<MyEntry<K, V>> findMin() { // only called when nonempty
        Position<MyEntry<K, V>> small = list.first();
        for (Position<MyEntry<K, V>> walk : list.positions()){
            System.out.println("walk value"+walk.toString());
            System.out.println("walk value"+walk.getElement());
            if (walk==null) break;

            else if (compare(walk.getElement(), small.getElement()) < 0)
                small = walk; // found an even smaller key
             }
        return small;
    }

    /**
     * Inserts a key-value pair and returns the entry created.
     */
    public MyEntry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key); // auxiliary key-checking method (could throw exception)
        MyEntry<K, V> newest = new PQEntry<>(key, value);
        list.addLast(newest);
        return newest;
    }

    /**
     * Returns (but does not remove) an entry with minimal key.
     */
    public MyEntry<K, V> min() {
        if (list.isEmpty()) return null;
        return findMin().getElement();
    }

    /**
     * Removes and returns an entry with minimal key.
     */
    public MyEntry<K, V> removeMin() {
        if (list.isEmpty()) return null;
        return list.remove(findMin());
    }

    /**
     * Returns the number of items in the priority queue.
     */
    public int size() {
        return list.size();
    }

}
