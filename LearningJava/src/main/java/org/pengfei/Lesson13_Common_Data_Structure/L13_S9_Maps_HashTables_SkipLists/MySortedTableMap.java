package org.pengfei.Lesson13_Common_Data_Structure.L13_S9_Maps_HashTables_SkipLists;

import java.util.Map.Entry;

import java.util.ArrayList;
import java.util.Comparator;

public class MySortedTableMap<K,V> extends MyAbstractSortedMap<K,V> {
    // core data storage
    private ArrayList<Entry<K,V>> table= new ArrayList<Entry<K,V>>();

    public MySortedTableMap(){
        super();
    }

    public MySortedTableMap(Comparator<K> comp){
        super(comp);
    }

    /** Returns the smallest index for range table[low..high] inclusive storing an entry with a key greater than or
     *  equal to k (or else index high+1, by convention). */

    /* the findIndex version which uses binary search.*/
    private int findIndex(K key, int low, int high){
        if(high < low) return high+1;
        // As mid is an int, it only takes the int part(e.g. 9/2=4 not 4.5), so mid will be equal or less than the
        // real mid, as a result, the findIndex may not return the exact index of the key, so we need to compare the
        // key to make sure.
        int mid=(low+high)/2;
        int comp=compare(key, table.get(mid));
        // compare the key with mid of high and low
        // if it equals, return the index
        if(comp==0) return mid;
        // if key is smaller than the mid, go check the left hand side.
        else if(comp<0) return findIndex(key,low,mid-1);
        // if key is greater than the mid, go check the right hand side.
        else return findIndex(key,mid+1,high);
    }

    /* the findIndex version which search the entire table*/
    private int findIndex(K key){return findIndex(key,0,table.size()-1);}


    @Override
    public Entry firstEntry() {
        return safeEntry(0);
    }

    @Override
    public Entry lastEntry() {
        return safeEntry(table.size()-1);
    }

    /** Returns the entry with least key greater than or equal to given key(if any)*/
    @Override
    public Entry ceilingEntry(K key) throws IllegalArgumentException {
        return safeEntry(findIndex(key));
    }

    /** Returns the entry with greatest key less than or equal to given key(if any)*/
    @Override
    public Entry floorEntry(K key) throws IllegalArgumentException {
        int j=findIndex(key);
        if(j==size()|| !key.equals(table.get(j).getKey()))
            // look one earlier unless we had found a perfect match
            {j--;}
        return safeEntry(j);
    }

    /** Returns the entry with greatest key strictly less than given key (if any). */
    @Override
    public Entry lowerEntry(K key) throws IllegalArgumentException {
        // go strictky before the ceiling entry
        return safeEntry(findIndex(key)-1);
    }

    /** Returns the entry with least key strictly greater than given key (if any). */
    @Override
    public Entry higherEntry(K key) throws IllegalArgumentException {
        int j=findIndex(key);
        if(j<size()&&key.equals(table.get(j).getKey()))
            // go past exact match
            j++;
        return safeEntry(j);
    }


    @Override
    public int size() {
        return table.size();
    }

    @Override
    public V get(K key) {
        int j = findIndex(key);
        if(j==size()|| compare(key,table.get(j))!=0) return null;
        return table.get(j).getValue();
    }

    @Override
    public V put(K key, V value) {
        int j=findIndex(key);
        //if key exists, update the value
        if(j<size()&&compare(key,table.get(j))==0)
        return table.get(j).setValue(value);
        //otherwise create a new slot in the table, and return null
        table.add(j,new MyMapEntry<K,V>(key,value));
        return null;
    }
/** Removes the entry that having key k(if any) and returns its associated value. */
    @Override
    public V remove(K key) {
        int j=findIndex(key);
        //no match find, do nothing and return null
        if(j==size()|| compare(key,table.get(j))!=0) return null;
        //else, remove the entry
        return table.remove(j).getValue();
    }

    /** Utility returns the entry at index j, or else null if j is out of bounds. */
    private Entry<K,V> safeEntry(int j){
        if(j<0||j>=table.size()) return null;
        else return table.get(j);
    }

// support for snapshot iterators for entrySet() and subMap() follow
    // It takes a start index, and stop key, when the stop key is null, it goes to the end of the table
    private Iterable<Entry<K,V>>snapshot(int startIndex, K stop){
        ArrayList<Entry<K,V>> buffer=new ArrayList<>();
        int j=startIndex;
        while(j<table.size()&&(stop==null||compare(stop,table.get(j))>0)){
            buffer.add(table.get(j++));
        }
        return buffer;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return snapshot(0,null);
    }

    @Override
    public Iterable<Entry<K,V>> subMap(K fromKey, K toKey) throws IllegalArgumentException {
        return snapshot(findIndex(fromKey),toKey);
    }
}
