package org.pengfei.Lesson13_Common_Data_Structure.L13_S9_Maps_HashTables_SkipLists;


import java.time.*;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListMapExample {

    
    ConcurrentNavigableMap<Integer, String> myMap;

    public ConcurrentSkipListMapExample(){

    }
    /* In example 1, we have a simple entry with int as key, so the default comparator is enough.*/
    public void doExample1() {
        myMap = new ConcurrentSkipListMap<Integer, String>();
        myMap.put(100, "Crunchify");
        myMap.put(250, "Google");
        myMap.put(300, "Apple");
        myMap.put(275, "Facebook");
        myMap.put(325, "Twitter"); // NOTE: these are sample numbers

        log("Let's get ceilingEntry: " + myMap.ceilingKey(200)); // Returns a key-value mapping associated with
        // the least key greater than or equal to the
        // given key
        log("Let's get firstKey: " + myMap.firstKey()); // Returns the first (lowest) key
        log("Let's get lastEntry: " + myMap.lastEntry()); // Returns greatest key
        log("Let's get floorEntry: " + myMap.floorKey(320)); // Returns the greatest key less than or equal to the
        // given key

        NavigableSet<Integer> crunchifyNavSet = myMap.descendingKeySet(); // Returns a reverse order NavigableSet
        // view of the keys contained in this map.

        log("\nHere is a Reverse order NavigableSet ~~~~~~~~~~~~~~~~ ");
        Iterator<Integer> crunchifyIterator = crunchifyNavSet.iterator(); // Standard Java Iterator
        while (crunchifyIterator.hasNext()) { // Returns true if the iteration has more elements
            Integer mapKey = crunchifyIterator.next();
            log(mapKey.toString());
        }

        log("\nLet's do some deugging ~~~~~~~~~~~~~~~~");
        log("pollFirstEntry: " + myMap.pollFirstEntry()); // Removes and returns a key-value mapping associated with
        // the least key in this map
        log("now firstEntry: " + myMap.firstEntry());
        log("pollLastEntry: " + myMap.pollLastEntry()); // Removes and returns a key-value mapping associated with
        // the greatest key in this map
        log("now lastEntry: " + myMap.lastEntry());

    }

    /* In example2, we will use timestamp as key, so the default comparator is not enough, we need to define our own
     * comparator.
     * */
    public void doExample2(){
        //todo
        ZonedDateTime time1 = ZonedDateTime.now(ZoneId.of("America/New_York"));
        ZonedDateTime time2 = time1.minusDays(30);
        Instant i1 = time1.toInstant();
        Instant i2 = time2.toInstant();
        log(i1.toString());
        log(i2.toString());
        ZonedDateTime.now(ZoneId.of("America/New_York")).minusDays(30).toInstant();
    }

    private static void log(String result) {
        System.out.println(result);
    }


}

