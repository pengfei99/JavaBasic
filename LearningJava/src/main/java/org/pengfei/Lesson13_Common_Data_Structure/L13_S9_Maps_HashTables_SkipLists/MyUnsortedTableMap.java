package org.pengfei.Lesson13_Common_Data_Structure.L13_S9_Maps_HashTables_SkipLists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class MyUnsortedTableMap<K,V> extends MyAbstractMap<K,V> {
    /* core storage for the map of entries*/
    private ArrayList<MyMapEntry<K,V>> table = new ArrayList<>();

    /* constructs an initially empty map */
    public MyUnsortedTableMap(){
    }
    /* private utility*/

    /* find the index of the entry which has equal key in the array list */
    private int findIndex(K key){
        int n=table.size();
        for(int j=0; j<n;j++){
            if(table.get(j).getKey().equals(key)) return j;
        }
        return -1;
    }

    @Override
    public int size() {
        return table.size();
    }

    @Override
    public V get(K key) {
        int index=findIndex(key);
        if (index==-1) return null;
        return table.get(index).getValue() ;
    }

    /** Associates given value with given key, replacing a previous value (if any). */
    @Override
    public V put(K key, V value) {
        int j=findIndex(key);
        if(j==-1) {
            table.add(new MyMapEntry<>(key,value));
            return null;
        }
        else return table.get(j).setValue(value);
    }

    /** Removes the entry with the specified key (if any) and returns its value. */
    @Override
    public V remove(K key) {
        int j=findIndex(key);
        if(j==-1) return null;
        else{
            V answer=table.get(j).getValue();
            // to avoid empty slot in the arraylist, we sawp the last element of the list to the deleted entry spot.
            int n=size();
            if(j!=n-1) {
                table.set(j,table.get(n-1));
                table.remove(n-1);
            }
            return answer;
        }

    }


    /* Support for public entrySet method*/

    private class EntryIterator implements Iterator<Entry<K,V>> {
        private int j=0;
        @Override
        public boolean hasNext() {
            return j<table.size();
        }

        @Override
        public Entry<K, V> next() {
            if(j==table.size()) throw new NoSuchElementException();
            //increment j and return the corresponding entry
            return table.get(j++);
        }

        @Override
        public void remove() {
           throw new UnsupportedOperationException();
        }
    }

    private class EntryIterable implements Iterable<Entry<K,V>>{

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }
    }

    public Iterable<Entry<K,V>> entrySet() {return new EntryIterable();}

}
