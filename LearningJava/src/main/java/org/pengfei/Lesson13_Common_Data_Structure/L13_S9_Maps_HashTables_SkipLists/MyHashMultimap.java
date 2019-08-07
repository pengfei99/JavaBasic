package org.pengfei.Lesson13_Common_Data_Structure.L13_S9_Maps_HashTables_SkipLists;

import java.util.*;

public class MyHashMultimap<K,V> implements MyMultimapInterface<K,V> {
    // core data set for storing all entries
    Map<K, List<V>> map=new HashMap<>();

    // size of entries
    int total=0;

    public MyHashMultimap(){

    }

    @Override
    public int size() {
        return total;
    }

    @Override
    public boolean isEmpty() {
        return total==0;
    }

    /* get method returns a (possible empty) iteration of all values associated with the key*/
    @Override
    public Iterable<V> get(K key) {
        List<V> secondary = map.get(key);
        if(secondary !=null) return secondary;
        else return new ArrayList<>();
    }

    @Override
    public void put(K key, V value) {
        List<V> secondary= map.get(key);
        if(secondary==null) {
            secondary=new ArrayList<>();
            map.put(key,secondary);
        }
        secondary.add(value);
        total++;
    }

    @Override
    public boolean remove(K key, V value) {
        boolean wasRemoved = false;
        List<V> secondary=map.get(key);
        if(secondary!=null){
            wasRemoved=secondary.remove(value);
            if(wasRemoved){
                total--;
                // if after remove the list is empty, we can remove the key from the map
                if(secondary.isEmpty())
                    map.remove(key);
            }
        }
        return wasRemoved;
    }

    @Override
    public Iterable<V> removeAll(K key) {
        List<V> secondary=map.get(key);
        if(secondary==null) secondary=new ArrayList<>();
        else {
            total-=secondary.size();
            map.remove(key);
        }
        return secondary;
    }

    @Override
    public Iterable<Map.Entry<K, V>> entries() {
        List<Map.Entry<K,V>> result=new ArrayList<>();
        for (Map.Entry<K,List<V>> entry: map.entrySet()){
            K key = entry.getKey();
            for(V value: entry.getValue()){
                result.add(new AbstractMap.SimpleEntry<K,V>(key,value));
            }
        }
        return result;
    }

    @Override
    public Iterable<K> keys() {
        List<K> result=new ArrayList<>();
        for (Map.Entry<K,List<V>> entry: map.entrySet()){
            K key=entry.getKey();
            for(int i=0;i<entry.getValue().size();i++){
                result.add(key);
            }
        }
        return result;
    }

    @Override
    public Iterable<K> keySet() {
        return map.keySet();
    }

    @Override
    public Iterable<V> values() {
        List<V> result=new ArrayList<>();
        for(List<V> valueList: map.values()){
            for(V value:valueList){
                result.add(value);
            }
        }
        return result;
    }
}
