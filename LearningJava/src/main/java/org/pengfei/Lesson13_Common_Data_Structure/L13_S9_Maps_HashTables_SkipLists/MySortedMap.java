package org.pengfei.Lesson13_Common_Data_Structure.L13_S9_Maps_HashTables_SkipLists;

import java.util.Map.Entry;
public interface MySortedMap<K, V> extends MyMap<K,V>{
    /**
     * Returns the entry having the least key (or null if map is empty).
     * @return entry with least key (or null if map is empty)
     */
    Entry<K,V> firstEntry();

    /**
     * Returns the entry having the greatest key (or null if map is empty).
     * @return entry with greatest key (or null if map is empty)
     */
    Entry<K,V> lastEntry();

    /**
     * Returns the entry with least key greater than or equal to given key
     * (or null if no such key exists).
     * @return entry with least key greater than or equal to given (or null if no such entry)
     * @throws IllegalArgumentException if the key is not compatible with the map
     */
    Entry<K,V> ceilingEntry(K key) throws IllegalArgumentException;

    /**
     * Returns the entry with greatest key less than or equal to given key
     * (or null if no such key exists).
     * @return entry with greatest key less than or equal to given (or null if no such entry)
     * @throws IllegalArgumentException if the key is not compatible with the map
     */
    Entry<K,V> floorEntry(K key) throws IllegalArgumentException;

    /**
     * Returns the entry with greatest key strictly less than given key
     * (or null if no such key exists).
     * @return entry with greatest key strictly less than given (or null if no such entry)
     * @throws IllegalArgumentException if the key is not compatible with the map
     */
    Entry<K,V> lowerEntry(K key) throws IllegalArgumentException;

    /**
     * Returns the entry with least key strictly greater than given key
     * (or null if no such key exists).
     * @return entry with least key strictly greater than given (or null if no such entry)
     * @throws IllegalArgumentException if the key is not compatible with the map
     */
    Entry<K,V> higherEntry(K key) throws IllegalArgumentException;

    /**
     * Returns an iterable containing all keys in the range from
     *
     * @return iterable with keys in desired range
     * @throws IllegalArgumentException if <code>fromKey</code> or <code>toKey</code> is not compatible with the map
     */
    Iterable<Entry<K,V>> subMap(K fromKey, K toKey) throws IllegalArgumentException;
}
