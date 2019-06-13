package org.pengfei.Lesson13_Common_Data_Structure.L13_S9_Maps_HashTables_SkipLists;

import org.pengfei.Lesson13_Common_Data_Structure.L13_S8_Priority_Queues.MyEntry;

import java.util.Map.Entry;

public class L13_S9_SortedMaps {
    public static void main(String[] args){

        /************************************************************************************************
         * **************************************** 13.9.3 Sorted Maps  ***********************************
         * **********************************************************************************************/

        /*
        * The traditional map ADT allows a user to look up the value associated with a given key, but the search
        * for that key is a form known as an exact search. In this section, we will introduce an extension known as
        * the sorted map ADT that includes all behaviors of the standard map, plus the following:
        * - firstEntry(): Returns the entry with smallest key's value (or null, if the map is empty)
        * - lastEntry(): Returns the entry with largest key's value (or null, if the map is empty)
        * - ceilingEntry(): Returns the entry with the least key's value greater than or equal to k (or null, if no
        *                  such entry exists)
        * - floorEntry(): Returns the entry with the greatest key's value less than or equal to k (or null, if no
        *                  such entry exists)
        * - lowerEntry(): Returns the entry with the greatest key's value strictly less than k (or null, if no such
        *                 entry exists)
        * - higherEntry(): Returns the entry with the least key's value strictly greater than k (or null, if no such
        *                  entry exists)
        * - subMap(k1, k2) : Returns an iteration of all entries with key greater than or equal to k1, but strictly
        *                  less than k2.
        *
        * We note that the above methods are included within the java.util.NavigableMap interface which extends the
        * simpler java.util.SortedMap interface
        *
        * To motivate the use of a sorted map, consider a computer system that maintains information about events
        * that have occurred (such as financial transactions), with a time stamp marking the occurrence of each event.
        * If the time stamps were unique for a particular system, we could organize a map with a time stamp serving as
        * a key, and a record about the event that occurred at that time as the value. A particular time stamp could
        * serve as a reference ID for an event, in which case we can quickly retrieve information about that event from
        * the map. However, the (unsorted) map ADT does not provide any way to get a list of all events ordered by the
        * time at which they occur, or to search for which event occurred closest to a particular time. In fact,
        * hash-based implementations of the map ADT intentionally scatter keys that may seem very “near” to each other
        * in the original domain, so that they are more uniformly distributed in a hash table.
         *  */

        /************************************** 13.9.3.1 Sorted Search Tables *************************************/

        /* Several data structures can efficiently support the sorted map ADT, and we will examine some advanced
        * techniques in the following section(e.g. skip lists, search trees). In this section, we will begin by
        * exploring a simple implementation of a sorted map. We store the map's entries in an array list A, so
        * that they are in increasing order of their keys. We refer to this implementation as a sorted search
        * table.
        *
        * As was the case with the unsorted table map, the sorted search table has a space requirement that is O(n).
        * The primary advantage of this representation, and our reason for insisting that A be array-based, is that
        * it allows us to use the binary search algorithm for a variety of efficient operations.
        * */

        /* Binary Search and inexact searches
        *
        * A Binary search is used for detecting whether a given target is stored within a sorted sequence, it returns
        * true or false to designate whether the desired target was found. The important realization is that, while
        * performing a binary search, we can instead return the index at or near where a target might be found. During
        * a successful search, the standard implementation determines the precise index at which the target is found.
        * During an unsuccessful search, although the target is not found, the algorithm will effectively determine a
        * pair of indices designating elements of the collection that are just less than or just greater than the
        * missing target.
        *
        * To implement the MySortedTableMap, we need first define the interface MySortedMap which defines all methods
        * of the sorted map ADT(e.g. firstEntry, lastEntry, etc.), then we define a MyAbstractSortedMap class which
        * extends MyAbstractMap<K,V> and implements MySortedMap<K,V>. The abstract class MyAbstractSortedMap implements
        * two constructors, first with an comparator as argument, second with no argument, but a default comparator is
        * used in the constructor. It implements several methods of compare (e.g. entry to entry, key to entry,
        * entry to key, key to key).
        *
        * Performance of each method in MySortedTableMap
        * Method    | Running Time
        *   size    |   O(1)
        *   get     | O(logn)
        *   put     |   O(n); O(logn) if map has entry with given key
        *   remove  |   O(n)
        *   firstEntry, lastEntry | O(1)
        *   ceilingEntry, floorEntry, | O(logn)
        *   lowerEntry, higherEntry   | O(logn)
        *   subMap   |  O(s+logn) where s items are reported
        *   entrySet, keySet, values  | O(n)
        *
        * In conclusion, sorted tables are primarily used in situations where we expect many searches but relatively
        * few updates. Because update method (e.g. put, remove) has O(n)
         * */
L13_S9_SortedMaps.mySortedTableMapExample();

/************************************** 13.9.3.2 Two applications of sorted Maps *************************************/

/* In this section, we explore applications in which there is a particular advantage to using a sorted map rather than
* a unsorted map. To apply a sorted map, keys must come from a domain that is "totally ordered".
*
* Application  1. Flight Databases
*
* There are several websites on the Internet that allow users to perform queries on flight databases to find flights
* between various cities. To make a query, a user specifies origin and destination cities, a departure date and time.
* To support such queries, we can model the flight database as a map, where keys are Flight objects that contain
* fields corresponding of four parameters k=(origin,destination,date,time).
*
* Additional information about a flight, such as the flight number, the number of seats still available in first(F) and
* coach(Y), the flight duration, and the fare, can be stored in the value object.
*
* Finding a requested flight is not simply a matter of finding an exact match for a requested query. Although a user
* typically wants to exactly match the origin and destination cities, he or she may have flexibility for the departure
* date, and certainly will have some flexibility for the departure time on a specific day.
*
* We can handle such a query by ordering our keys lexicographically. For example, given a user query key k, we could all
* ceilingEntry(k) to return the first flight between the desired cities, having a departure date and time matching the
* desired query or later. With well-constructed keys, we could use subMap(k1,k2) to find all flights within a given
* range of times. For example, if k1 = (ORD, PVD, 05May, 09:30), and k2 = (ORD, PVD, 05May, 20:00), a respective
* call to subMap(k1, k2) might result in the following sequence of key-value pairs:
* (ORD, PVD, 05May, 09:53) : (AA 1840, F5, Y15, 02:05, $251),
* (ORD, PVD, 05May, 13:29) : (AA 600, F2, Y0, 02:16, $713),
* (ORD, PVD, 05May, 17:39) : (AA 416, F3, Y9, 02:09, $365),
* (ORD, PVD, 05May, 19:50) : (AA 1828, F9, Y25, 02:13, $186)
*
*
* Application 2. Maxima Sets
*
* We can model a trade-off problem by using a key-value pair to model the two parameters that we trading off, in this
* example, we use the pair (cost, speed) for each car. Notice that some cars are strictly better than other cars using
* this measure. For example, a car with cost-speed pair (30000,100) is strictly better than a car with cost-speed pair
* (40000,90). At the same time, there are some cars that are not strictly dominated by another car. For example, a
* car with cost-speed pair (30000,100) may be better or worse than a car with cost-speed pair (40000,120), depending
* on how much money we have to spend.
*
* Formally, we say a cost-performance pair (a,b) dominates pair (c,d) 6= (a,b) if a ≤ c and b ≥ d, that is, if the
* first pair has no greater cost and at least as good performance. A pair (a,b) is called a maximum pair if it is
* not dominated by any other pair. We are interested in maintaining the set of maxima of a collection of
* cost-performance pairs. That is, we would like to add new pairs to this collection (for example, when a new car is
* introduced), and to query this collection for a given dollar amount, d, to find the fastest car that costs no more
* than d dollars.
*
* We can store the set of maxima pairs in a sorted map so that the cost is the key field and speed(performance) is the
* value. We can then implement operations add(c,p), and best(c), which returns the entry having best performance(speed)
* of those with cost at most c. We implement the code in class CostPerformanceDatabase
*
* */
    }

    public static void mySortedTableMapExample(){
        /* class MySortedTableComparator<String> implements Comparator<String>{


             @Override
             public int compare(String o1, String o2) {
                 return Integer.valueOf(o1)-Integer.valueOf(o2);
             }
         }*/

        MySortedTableMap<Integer,String> mySortedTableMap=new MySortedTableMap<>();
        mySortedTableMap.put(0,"health");
        mySortedTableMap.put(1,"money");
        mySortedTableMap.put(2,"fame");
        mySortedTableMap.put(3,"love");

        Entry first = mySortedTableMap.firstEntry();
        System.out.println("first entry has key: "+first.getKey()+" has value: "+first.getValue());
        Entry last = mySortedTableMap.lastEntry();
        System.out.println("last entry has key: "+last.getKey()+" has value: "+last.getValue());

        Entry higher=mySortedTableMap.higherEntry(2);
        System.out.println("higher entry has key: "+higher.getKey()+" has value: "+higher.getValue());

    }
}
