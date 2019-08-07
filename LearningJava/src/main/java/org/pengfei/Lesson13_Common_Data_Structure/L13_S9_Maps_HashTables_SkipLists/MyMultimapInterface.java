package org.pengfei.Lesson13_Common_Data_Structure.L13_S9_Maps_HashTables_SkipLists;

import java.util.Collection;
import java.util.Map;

public interface MyMultimapInterface<K,V> {
     int size();
     boolean isEmpty();
     Iterable<V> get(K key);
     void put(K key, V value);
     boolean remove(K key, V value);
     Iterable<V> removeAll(K key);
     Iterable<Map.Entry<K,V>> entries();
    Iterable<K> keys();
    Iterable<K> keySet();
    Iterable<V> values();

}
