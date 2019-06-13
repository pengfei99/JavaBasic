package org.pengfei.Lesson13_Common_Data_Structure.L13_S9_Maps_HashTables_SkipLists;


import java.util.Map.Entry;

import java.util.ArrayList;
import java.util.Random;


public abstract class MyAbstractHashMap<K,V> extends MyAbstractMap<K,V> {
    //number of entries in the dictionary
    protected int n=0;
    // length of the table
    protected int capacity;
    //prime factor
    private int prime;
    //the shift and scaling factors
    private long scale, shift;

    /* primary constructor*/
    public MyAbstractHashMap(int cap, int p){
        prime=p;
        capacity=cap;
        Random rand=new Random();
        scale = rand.nextInt(prime-1)+1;
        shift = rand.nextInt(prime);
        createTable();
    }
    /* constructor with default prime*/
    public MyAbstractHashMap(int cap){
        this(cap,109345121);
    }

    /* constructor with default capacity*/
    public MyAbstractHashMap(){
        this(17);
    }

    /* public methods*/
    public int size(){return n;}
    public V get(K key){return bucketGet(hashValue(key),key);}
    public V put(K key, V value){
        V answer = bucketPut(hashValue(key),key,value);
        // keep load factor <=0.5 or find a nearby prime
        if(n>capacity/2) resize(2*capacity-1);
        return answer;
    }
    public V remove(K key){return bucketRemove(hashValue(key),key);}


    /* private utilities*/
    private int hashValue(K key){
        return (int) (((Math.abs(key.hashCode( )*scale + shift) % prime) % capacity));
    }

    /* put all entry in a ArrayList buffer, then create a new hashTable with the new size, at
    * last copy all entry into the new table*/
    private void resize(int newCap){
        ArrayList<Entry<K,V>> buffer = new ArrayList<>(n);
        for(Entry<K,V> e: entrySet()){
            buffer.add(e);
        }
            capacity=newCap;

            //create a new table based on the updated capacity
            createTable();
            // reinitialize the number of entries in the dict
            n=0;
            for(Entry<K,V> e: buffer) put(e.getKey(),e.getValue());

    }

    /* protected abstract methods to be implemented by concrete subclass*/
    protected abstract void createTable();
    protected abstract V bucketGet(int h, K k);
    protected abstract V bucketPut(int h, K k, V v);
    protected abstract V bucketRemove(int h, K k);
}
