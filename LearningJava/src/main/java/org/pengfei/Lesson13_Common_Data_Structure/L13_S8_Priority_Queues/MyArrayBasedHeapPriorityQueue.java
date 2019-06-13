package org.pengfei.Lesson13_Common_Data_Structure.L13_S8_Priority_Queues;

import java.util.ArrayList;
import java.util.Comparator;

public class MyArrayBasedHeapPriorityQueue<K,V> extends MyAbstractPriorityQueue<K,V>{
    /** core data storage of the heap*/
    protected ArrayList<MyEntry<K,V>> heap = new ArrayList<>();

    public MyArrayBasedHeapPriorityQueue(){
        super();
    }

    public MyArrayBasedHeapPriorityQueue(Comparator<K> comp){
        super(comp);
    }

    /** Utilities to navigate in the Heap*/
    protected int parent(int j){return (j-1)/2;}
    protected int left(int j){return 2*j+1;}
    protected int right(int j){return 2*j+2;}
    protected boolean hasLeft(int j){return left(j)<heap.size();}
    protected boolean hasRight(int j){return right(j)<heap.size();}

    /** Swap the entries at indices i and j of the array list.*/
    protected void swap(int i, int j){
        MyEntry<K,V> temp=heap.get(i);
        heap.set(i,heap.get(j));
        heap.set(j,temp);

    }

    /** upheap Move the entry at index j up in the heap to respect the heap-order property.*/
    protected void upheap(int j){
        while(j>0){
            int p= parent(j);
            // if the child value is greater or equal to the parent, heap-order is good, stop loop
            if (compare(heap.get(j),heap.get(p))>=0) break;
            // else swap child to parent, and parent to child
            else swap(j,p);
            // re-assign the value of index j, then continue with the loop
            j=p;
        }
    }

    /** downheap moves the entry at index j down to respect the heap-order property*/
    protected void downheap(int j){
        while(hasLeft(j)){
            int leftIndex=left(j);
            // Initiate the variable smallChildIndex first with the left child index
            int smallChildIndex=leftIndex;
            // if has right child, compare the value of the two child, and update the smallChildIndex value for the
            // smallest value entry index
            if(hasRight(j)){
                int rightIndex= right(j);
                if(compare(heap.get(leftIndex),heap.get(rightIndex))>0) {
                    smallChildIndex=rightIndex;
                }
            }
            // if child is greater than the parent, heap order property has been restored
            if(compare(heap.get(smallChildIndex),heap.get(j))>=0) break;
            swap(j,smallChildIndex);
            j=smallChildIndex;
        }
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public MyEntry<K, V> insert(K key, V value) throws IllegalArgumentException {
        //check if the key is comparable or not, if not throw IllegalArg exception
        checkKey(key);
        MyEntry<K,V> newest=new PQEntry<>(key, value);
        // add the newest entry to the list
        heap.add(newest);
        // upheap the newest element to respect the heap order
        upheap(heap.size()-1);
        return newest;
    }

    @Override
    public MyEntry<K, V> min() {
        if(heap.isEmpty()) return null;
        return heap.get(0);
    }

    @Override
    public MyEntry<K, V> removeMin() {
        if(heap.isEmpty()) return null;
        MyEntry<K,V> answer=heap.get(0);
        //put the minimum entry at the end
        swap(0,heap.size()-1);
        // remove the last entry
        heap.remove(heap.size()-1);
        // downheap the new root entry to respect the heap order
        downheap(0);
        return answer;
    }
}
