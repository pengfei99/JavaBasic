package org.pengfei.Lesson13_Common_Data_Structure.L13_S9_Maps_HashTables_SkipLists;


import java.util.ArrayList;
import java.util.Map.Entry;

public class MyChainHashMap<K,V> extends MyAbstractHashMap<K,V>{
    // a fixed capacity array of UnsortedTableMap that serve as buckets
    private MyUnsortedTableMap<K,V>[] table;   // initialized within createTable

    // provide same constructors as base class
    /** Creates a hash table with capacity 11 and prime factor 109345121. */
    public MyChainHashMap() { super(); }

    /** Creates a hash table with given capacity and prime factor 109345121. */
    public MyChainHashMap(int cap) { super(cap); }

    /** Creates a hash table with the given capacity and prime factor.*/
    public MyChainHashMap(int cap, int p) { super(cap, p); }

    /** Creates an empty table having length equal to current capacity. */
    @Override
    @SuppressWarnings({"unchecked"})
    protected void createTable() {
        table = (MyUnsortedTableMap<K,V>[]) new MyUnsortedTableMap[capacity];
    }

    /**
     * Returns value associated with key k in bucket with hash value h.
     * If no such entry exists, returns null.
     * @param h  the hash value of the relevant bucket
     * @param k  the key of interest
     * @return   associate value (or null, if no such entry)*/

    @Override
    protected V bucketGet(int h, K k) {
        MyUnsortedTableMap<K,V> bucket = table[h];
        if (bucket == null) return null;
        return bucket.get(k);
    }

    /**
     * Associates key k with value v in bucket with hash value h, returning
     * the previously associated value, if any.
     * @param h  the hash value of the relevant bucket
     * @param k  the key of interest
     * @param v  the value to be associated
     * @return   previous value associated with k (or null, if no such entry)*/

    @Override
    protected V bucketPut(int h, K k, V v) {
        MyUnsortedTableMap<K,V> bucket = table[h];
        if (bucket == null)
            bucket = table[h] = new MyUnsortedTableMap<>();
        int oldSize = bucket.size();
        V answer = bucket.put(k,v);
        n += (bucket.size() - oldSize);   // size may have increased
        return answer;
    }

    /**
     * Removes entry having key k from bucket with hash value h, returning
     * the previously associated value, if found.
     * @param h  the hash value of the relevant bucket
     * @param k  the key of interest
     * @return   previous value associated with k (or null, if no such entry)*/

    @Override
    protected V bucketRemove(int h, K k) {
        MyUnsortedTableMap<K,V> bucket = table[h];
        if (bucket == null) return null;
        int oldSize = bucket.size();
        V answer = bucket.remove(k);
        n -= (oldSize - bucket.size());   // size may have decreased
        return answer;
    }

    /**
     * Returns an iterable collection of all key-value entries of the map.
     *
     * @return iterable collection of the map's entries*/

    @Override
    public Iterable<Entry<K,V>> entrySet() {
        ArrayList<Entry<K,V>> buffer = new ArrayList<>();
        for (int h=0; h < capacity; h++)
            if (table[h] != null)
                for (Entry<K,V> entry : table[h].entrySet())
                    buffer.add(entry);
        return buffer;
    }
}