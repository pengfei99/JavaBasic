package org.pengfei.Lesson13_Common_Data_Structure.L13_S8_Priority_Queues.source;

import org.pengfei.Lesson13_Common_Data_Structure.L13_S6_List.LinkedPositionalList;
import org.pengfei.Lesson13_Common_Data_Structure.L13_S6_List.Position;
import org.pengfei.Lesson13_Common_Data_Structure.L13_S6_List.PositionalList;

import java.util.Comparator;

public class MySortedPriorityQueue<K, V> extends MyAbstractPriorityQueue<K, V> {

    /**
     * Primary collection of priority queue entries
     */
    private PositionalList<MyEntry<K, V>> list = new LinkedPositionalList<>();

    /**
     * Creates an empty priority queue based on the natural ordering of its keys.
     */
    public MySortedPriorityQueue() {
        super();
    }

    /**
     * Creates an empty priority queue using the given comparator to order keys.
     */
    public MySortedPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Inserts a key-value pair and returns the Entry created.
     */
    public MyEntry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key); // auxiliary key-checking method (could throw exception)
        MyEntry<K, V> newest = new PQEntry<>(key, value);
        Position<MyEntry<K, V>> walk = list.last();
        // walk backward, looking for smaller key
        while (walk != null && compare(newest, walk.getElement()) < 0) {
            /* if we walk till to the first element and we still not find an entry has smaller key, we stop the loop,
             * add newest entry in the fist position of the list*/
            if (list.firstElement(walk) == true) break;
            walk = list.before(walk);
        }

        if (walk == null) list.addFirst(newest); // new key is smallest

        else if (list.firstElement(walk) == true)
            list.addFirst(newest); // new key is smallest


        else
            list.addAfter(walk, newest); // newest goes after walk


        return newest;
    }

    /**
     * Returns (but does not remove) an Entry with minimal key.
     */
    public MyEntry<K, V> min() {
        if (list.isEmpty()) return null;
        return list.first().getElement();
    }

    /**
     * Removes and returns an Entry with minimal key.
     */
    public MyEntry<K, V> removeMin() {
        if (list.isEmpty()) return null;
        return list.remove(list.first());
    }

    /**
     * Returns the number of items in the priority queue.
     */
    public int size() {
        return list.size();
    }
}
