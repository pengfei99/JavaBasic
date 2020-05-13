package org.pengfei.Lesson13_Common_Data_Structure.L13_S8_Priority_Queues.source;

public interface MyPriorityQueue<K,V> {
    int size();
    boolean isEmpty();
    MyEntry<K,V> insert(K key, V value) throws IllegalArgumentException;
    MyEntry<K,V> min();
    MyEntry<K,V> removeMin();
}
