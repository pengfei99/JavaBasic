package org.pengfei.Lesson13_Common_Data_Structure.L13_S9_Maps_HashTables_SkipLists;

import java.util.Collection;

public class L13_S9_Sets_Multisets {
    public static void main(String[] args){
        /************************************************************************************************
         * ************************* 13.9.5 Sets, Multisets, and Multimaps  ******************************
         * **********************************************************************************************/

        /************************************* 13.9.5.1 Introduction ****************************/

        /* In this tutorial, we present several additional abstractions that are closely related to the map ADT,
        * and that can be implemented using data structures similar to those for a map.
        *
        * - Set: is an unordered collection of elements, without duplicates, that typically supports efficient
        *        membership tests. In essence, elements of a set are like keys of a map, but without any auxiliary
        *        values.
        *
        * - Multiset (aka. bag): is a set-like container that allows duplicates.
        *
        * - Multimap: is similar to a traditional map, in that it associates values with keys; however, in a multimap
        *             the same key can be mapped to multiple values. For example, the index of a book maps a given
        *             term to one or more locations(page number) at which the term occurs elsewhere in the book.
        *
        *        */


        /********************************** 13.9.5.2 The Set ADT *************************************/

        /* The Java Collections Framework defines the java.util.Set interface, which includes the following fundamental
        *  methods:
        *  -add(e): Adds the element e to S (if not already present)
        *  -remove(e): Removes the element e from S (if it is present)
        *  -contains(e): Returns whether e is an element of S.
        *  -iterator(): Returns an iterator of the element of S.
        *
        *  The set interface also supports the traditional mathematical set operations of union, intersection and
        *  subtraction of two sets S and T:
        *  - S union T = {e: e is in S or in T}
        *  - S inter T = {e: e is in S and e is in T}
        *  - S sub T = {e: e is in S and e is not in T}
        *
        *  They are provided through the following methods, if executed on a set S:
        *  - s.addAll(t): Updates S to also include all elements of set T, effectively replacing S by S union T.
        *  - s.retainAll(t): Updates S so that it only keeps those element that are also elements of set T. effectively
        *                    replacing S by S inter T.
        *  - s.removeAll(t): Updates S by removing any of its elements that also occur in set T, effectively replacing
        *                    S by S sub T.
        *
        *
        *  */

        /********************************** 13.9.5.3 The Sorted Set *************************************/

        /*
        * For the standard set abstraction, there is no explicit notion of keys being ordered; all that is assumed
        * is that the equals method can detect equivalent elements. If, however, elements come from a Comparable
        * class (or a suitable Comparator object is provided), we can extend the notion of a set to define the sorted
        * set ADT, including the following additional methods:
        * - first( ): Returns the smallest element in S.
        * - last( ): Returns the largest element in S.
        * - ceiling(e): Returns the smallest element greater than or equal to e.
        * - floor(e): Returns the largest element less than or equal to e.
        * - lower(e): Returns the largest element strictly less than e.
        * - higher(e): Returns the smallest element strictly greater than e.
        * - subSet(e1, e2): Returns an iteration of all elements greater than or equal to e1, but strictly less than e2.
        * - pollFirst( ): Returns and removes the smallest element in S.
        * - pollLast( ): Returns and removes the largest element in S.
        *
        * In the java Collection Framework, the above methods are included in a combination of the java.util.SortedSet
        * and java.util.NavigableSet interfaces.
        *
        * */

        /********************************** 13.9.5.4 Implementing Set *************************************/

        /*
        *  Although a set is a completely different abstraction than a map, the techniques used to implement the two
        *  can be quite similar. In effect, a set is simply a map in which unique keys do not have associated values.
        *
        *  Therefore, any data structure used to implement a map can be modified to implement the set ADT with similar
        *  performance guarantees. The java Collections Framework includes the following set implementations, mirroring
        *  similar data structures used for maps:
        *  - java.util.HashSet provides an implementation of the (unordered) set ADT with a hash table.
        *  - java.util.concurrent.ConcurrentSkipListSet provides an implementation of the sorted set ADT using a skip
        *    list.
        *  - java.util.TreeSet provides an implementation of the sorted set ADT using a balanced search tree.
        *
        * For sorted set, the java collections API only has only one implementation java.util.TreeSet
        * */
        SetExample setExample=new SetExample();
        //setExample.doExample1();
        //setExample.doExample2();
        //setExample.doExample3();
        //setExample.doSortedSetExample1();

        /********************************** 13.9.5.5 The Multiset ADT *************************************/

        /* Before discussing models for a multiset abstraction, we must carefully consider the notion of “duplicate”
         * elements. Throughout the Java Collections Framework, objects are considered equivalent to each other based
         * on the standard equals method. For example, keys of a map must be unique, but the notion of uniqueness
         * allows distinct yet equivalent objects to be matched. This is important for many typical uses of maps.
         * For example, when strings are used as keys, the instance of the string "October" that is used when inserting
         * an entry may not be the same instance of "October" that is used when later retrieving the associated value.
         * The call get("October") will succeed in such a scenario because strings are considered equal to each other.
         *
         * In the context of multisets, if we represent a collection that appears through the notion of equivalence
         * as {a,a,a,a,b,c,c}, we must decide if we want a data structure to explicitly maintain each instance of a
         * (because each might be distinct though equivalent), or just that there exist four occurrences. In either
         * case, a multiset can be implemented by directly adapting a map. We can use one element from a group of
         * equivalent occurrences as the key in a map, with the associated value either a secondary container
         * containing all of the equivalent instances, or a count of the number of occurrences. Note that our
         * word-frequency application uses just such a map, associating strings with counts.
         *
         *
         * The Java Collections Framework does not include any form of a multiset. However, implementations exist in
         * several widely used, open source Java collections libraries. The apache Commons defines Bag(unsorted
         * multiset) and SortedBag (sorted multiset) interfaces.
         *
         * The google core libraries for Java (aka. Guava) includes Multiset and SortedMultiSet interfaces for these
         * abstractions. Both of those libraries take the approach of modeling a multiset as a collection of elements
         * having multiplicities, and both offer several concrete implementations using standard data structures. In
         * formalizing the abstract data type, the Multiset interface of the Guava library includes the following
         * methods:
         * - add(e): Adds a single occurrences of e to the multiset.
         * - contains(e): Returns true if the multiset contains an element equal to e.
         * - count(e): Returns the number of occurrences of e in the multiset.
         * - remove(e): Removes a single occurrence of e from the multiset.
         * - remove(e, n): Removes n occurrences of e from the multiset.
         * - size( ): Returns the number of elements of the multiset (including duplicates).
         * - iterator( ): Returns an iteration of all elements of the multiset (repeating those with multiplicity
         *                greater than one).
         *
         * The multiset ADT also includes the notion of an immutable Entry that represents an element and its count,
         * and the SortedMultiset interface includes additional methods such as firstEntry and lastEntry.
         *
         * */

        //setExample.doMultiSetExample1();

        /********************************** 13.9.5.6 The Multimap ADT *************************************/

        /* The multimap stores entries that are key-value pairs(k,v), where k is the key, but unlike in map(the key
        *  must be unique), in multimap, it allows multiple entries to have the same key. A dictionary is a perfect
        *  example, a word can have multiple definitions: (k,v),(k,v'),...
        *
        *  There are two standard approaches for representing a multimap as a variation of a traditional map. One is to
        *  redesign the underlying data structure to allow separate entries to be stored for pairs such as (k,v) and
        *  (k,v'). The other is to map key to a secondary container of all vaules associated with that key (e.g. k->{v,v'})
        *
        *  There is no formal abstraction(interface or implementation) for multiset in the java collection framework.
        *  To formalize the multimap abstract data type, we consider a simplified version of the Multimap interface
        *  included in Google’s Guava library. Among its methods are the following:
        *  - get(k): Returns a collection of all values associated with key k in the multimap.
        *  - put(k, v): Adds a new entry to the multimap associating key k with value v, without overwriting any
        *               existing mappings for key k.
        *  - remove(k, v): Removes an entry mapping key k to value v from the multimap (if one exists).
        *  - removeAll(k): Removes all entries having key equal to k from the multimap.
        *  - size( ): Returns the number of entries of the multiset (including multiple associations).
        *  - entries( ): Returns a collection of all entries in the multimap.
        *  - keys( ): Returns a collection of keys for all entries in the multimap (including duplicates for keys
        *             with multiple bindings).
        *  - keySet( ): Returns a non-duplicative collection of keys in the multimap.
        *  - values( ): Returns a collection of values for all entries in the multimap.
        *  */

        L13_S9_Sets_Multisets.MyHashMultimapExample();

    }

    public static void MyHashMultimapExample(){
        MyHashMultimap<String,String> myMap=new MyHashMultimap<String,String>();
        myMap.put("key1","apple");
        myMap.put("key1","orange");
        myMap.put("key1","banana");
        myMap.put("key2","beef");
        myMap.put("key2","chicken");
        myMap.put("key3","milk");

        //show all keys with duplicates
        Iterable<String> allKeys = myMap.keys();
        showList(allKeys);

        // show all keys without duplicates
        Iterable<String> strictKeys = myMap.keySet();
        showList(strictKeys);
        
        // show all values
        Iterable<String> allValues = myMap.values();
        showList(allValues);
    }

    public static void showList(Iterable<String> entries){
        for(String entry:entries){
            System.out.println(entry);
        }
    }
}
