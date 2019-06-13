package org.pengfei.Lesson13_Common_Data_Structure.L13_S9_Maps_HashTables_SkipLists;

import java.util.Map.Entry;

import java.util.Iterator;


public abstract class MyAbstractMap<K,V> implements MyMap<K,V> {
    public boolean isEmpty(){
        return size()==0;
    }

    //----------- Begin of nested MapEntry class -----------
    protected static class MyMapEntry<K,V> implements Entry<K,V>{

        private K key;
        private V value;

        public MyMapEntry(K key, V value){
            this.key=key;
            this.value=value;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }


        public V setValue(V newValue) {
            V old=value;
            value=newValue;
            return old;
        }

        //setKey is not exposed as part of the Entry interface
        protected void setKey(K newKey){this.key=newKey;}
    }

    //----------- end of nested MapEntry class -----------


   /** Support for public keySet method*/
    private class KeyIterator implements Iterator<K> {
        // We use the entrySet method of the Map interface to get the iterator
        // The nested class can use the method of its nesting class
        private Iterator<Entry<K,V>> entries=entrySet().iterator();
        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }

        @Override
        public K next() {
            return entries.next().getKey();
        }

        @Override
        public void remove() { throw new UnsupportedOperationException();

        }
    }

    private class KeyIterable implements Iterable<K>{

        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }
    }

    public Iterable<K> keySet(){return new KeyIterable();}

    /* Support for public values method */

    private class ValueIterator implements Iterator<V> {

        private Iterator<Entry<K,V>> entries=entrySet().iterator();
        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }

        @Override
        public V next() {
            return entries.next().getValue();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    private class ValueIterable implements Iterable<V>{
        public Iterator<V> iterator() {return new ValueIterator();}
    }

    public Iterable<V> values(){
        return new ValueIterable();
    }

}
