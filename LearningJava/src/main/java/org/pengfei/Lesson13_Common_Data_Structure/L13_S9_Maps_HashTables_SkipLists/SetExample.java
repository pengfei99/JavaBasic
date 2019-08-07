package org.pengfei.Lesson13_Common_Data_Structure.L13_S9_Maps_HashTables_SkipLists;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.HashSet;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class SetExample {

    /* In Example 1, we use the HashSet implementation of the Set interface*/
    public void doExample1(){
        HashSet<String> mySet=new HashSet<>();
        String target="orange";
        mySet.add("toto");
        mySet.add("titi");
        mySet.add(target);
        mySet.add("banana");

        for(String item: mySet){
            System.out.println(item);
        }

        System.out.println("Is there a "+target+" in the set: "+mySet.contains("orange"));

        mySet.remove(target);

        System.out.println("Is there a "+target+" in the set: "+mySet.contains("orange"));
    }

    /* In example 2, we use the concurrentSkipListSet implementation */

    public void doExample2(){
        ConcurrentSkipListSet<String> mySet=new ConcurrentSkipListSet();

        String target="orange";
        mySet.add("toto");
        mySet.add("titi");
        mySet.add(target);
        mySet.add("banana");

        for(String item: mySet){
            System.out.println(item);
        }

        System.out.println("Is there a "+target+" in the set: "+mySet.contains("orange"));

        mySet.remove(target);

        System.out.println("Is there a "+target+" in the set: "+mySet.contains("orange"));
    }

/* In example 3, we use the TreeSet implementation */
    public void doExample3(){
        TreeSet<String> mySet= new TreeSet();

        String target="orange";
        mySet.add("toto");
        mySet.add("titi");
        mySet.add(target);
        mySet.add("banana");

        for(String item: mySet){
            System.out.println(item);
        }

        System.out.println("Is there a "+target+" in the set: "+mySet.contains("orange"));

        mySet.remove(target);

        System.out.println("Is there a "+target+" in the set: "+mySet.contains("orange"));

    }

    public void doSortedSetExample1(){
        SortedSet<String> mySortedSet= new TreeSet<String>();
        String target="orange";
        mySortedSet.add("d");
        mySortedSet.add("c");
        mySortedSet.add("a");
        mySortedSet.add("b");
        mySortedSet.add(target);
        mySortedSet.add("banana");

        for(String item: mySortedSet){
            System.out.println(item);
        }
        // get the first element
        System.out.println("fist element of the set: "+mySortedSet.first());

        // get the ceiling of banana, or

        System.out.println("ceiling of banana is: "+((TreeSet<String>) mySortedSet).ceiling("banana"));
        System.out.println("ceiling of or is: "+((TreeSet<String>) mySortedSet).ceiling("or"));

        System.out.println("Is there a "+target+" in the set: "+mySortedSet.contains("orange"));

        mySortedSet.remove(target);

        System.out.println("Is there a "+target+" in the set: "+mySortedSet.contains("orange"));

    }

    /* This example uses the google guava multiset to simulate a book store inventory */
    public void doMultiSetExample1(){
        Multiset<String> bookStore = HashMultiset.create();
        bookStore.add("GOT");
        bookStore.add("GOT");
        bookStore.add("GOT");
        bookStore.add("GOT");
        bookStore.add("GOT");
        bookStore.add("GOT");

        System.out.println("book store contains GOP: "+bookStore.contains("GOT"));
        System.out.println("book store contains GOP number: "+bookStore.count("GOT"));

        // we can set the count of a key instead of performing add operations
        // for example, we have received
        bookStore.setCount("GOT",18);
        System.out.println("book store contains GOP number: "+bookStore.count("GOT"));

        //multiset validates the count value. If we set it to negative, an illegalArgumentException is thrown.
        try{
            bookStore.setCount("GOT",-1);
        }
        catch (IllegalArgumentException e){
            System.out.println(e);
        }

        /* We can do the above operations with a map, there are two disadvantages:
        * - when we increment the book count by using map.put method, we need to know the previous book count value
        * - there is no value checks on the map put method, so you can easily enter null or negative values.
        * */

    }

}
