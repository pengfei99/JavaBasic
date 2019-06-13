package org.pengfei.Lesson13_Common_Data_Structure.L13_S9_Maps_HashTables_SkipLists;

import java.util.Map.Entry;

import java.util.ArrayList;


public class MyProbeHashMap<K,V> extends MyAbstractHashMap<K,V> {
    // the core data storage, is a fixed array of entries (all initially null)
    private MyMapEntry<K,V>[] table;

    //sentinel
    private MyMapEntry<K,V> DEFUNCT = new MyMapEntry<K,V>(null,null);

    public MyProbeHashMap(){
        super();
    }
    public MyProbeHashMap(int cap){
        super(cap);
    }
    public MyProbeHashMap(int cap, int p){
        super(cap,p);
    }

/** Creates an empty table having length equal to current capacity */
    @Override
    protected void createTable() {
      table=(MyMapEntry<K,V>[])new MyMapEntry[capacity];
    }

    /** Returns true if location is either empty or the "defunct" sentinel*/
    private boolean isAvailable(int j){
        return (table[j]==null || table[j]==DEFUNCT);
    }

    private int findSlot(int h, K k){
        int avail=-1;
        int j=h;
        do{
            // check if the slot is available(empty or defunct) or not,
            if(isAvailable(j)){
                // get the first available slot which starts from j
                if(avail==-1) avail=j;
                // if empty, slot does not exist, search finished, break the loop
                if(table[j]==null) break;
            }
            // if the slot contains key k, then search finish
            else if(table[j].getKey().equals(k)) {return j;}
            j=(j+1)%capacity;
        } while (j!=h);
        // we need to loop over the table one time to locate the slot, the starting index is the hash
        // of the key, stop if we return to start
        // if no entry find, return the first available slot index after h as index, where h is the hash.
        return -(avail+1);
    }
/** Returns value associated with key k in bucket with hash value h, or else null.*/
    @Override
    protected V bucketGet(int h, K k) {
        int j = findSlot(h,k);
        if (j<0) return null;
        else return table[j].getValue();
    }

    @Override
    protected V bucketPut(int h, K k, V v) {
        int j = findSlot(h,k);
        // if the entry with k already exists in the table, return the old key and replace
        // it with the new value
        if(j>0) { return table[j].setValue(v);}
        // if the entry does not exist, use the first available slot index returned by findSlot, convert it to positive
        table[-(j+1)]=new MyMapEntry<>(k,v);
        // increase the entry number counter
        n++;
        return null;
    }

    @Override
    protected V bucketRemove(int h, K k) {
        int j=findSlot(h,k);
        // nothing to remove
        if(j<0) return null;
        V answer= table[j].getValue();
        // make this slot as deactivated
        table[j]=DEFUNCT;
        // decrease the entry number counter
        n--;
        return answer;
    }
/** Returns an iterable collection of all key-value entries of the map*/
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K,V>> buffer=new ArrayList<>();
        for(int h=0;h<capacity;h++){
            if(!isAvailable(h)) buffer.add(table[h]);
        }
        return buffer;
    }
}
