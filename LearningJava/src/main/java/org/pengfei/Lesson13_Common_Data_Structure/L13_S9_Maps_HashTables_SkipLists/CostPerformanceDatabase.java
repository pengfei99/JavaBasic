package org.pengfei.Lesson13_Common_Data_Structure.L13_S9_Maps_HashTables_SkipLists;

import java.util.Map.Entry;

public class CostPerformanceDatabase {
    MySortedMap<Integer,Integer> map= new MySortedTableMap<>();

    public CostPerformanceDatabase(){

    }

    /** Returns the (cost, speed) entry with largest cost not exceeding c, (or null if no entry exist with cost c or less)*/
    public Entry<Integer,Integer> best(int cost){
        return map.floorEntry(cost);
    }

    /** Add a new entry with given cost c and performance p.*/
    public void add(int c, int p){
        // other is an existing entry in the map which is at least as cheap as entry with key c
        Entry<Integer,Integer> other = map.floorEntry(c);
        // if existing entry is still dominated(better speed), do nothing(ignore the new adding entry)
        if(other!=null&&other.getValue()>=p) return;
        // if new adding entry is dominated, then add entry into the map
        else map.put(c,p);
        // and now remove any entries that are dominated by the new one, which means cost much and has less speed
        other=map.higherEntry(c);
        while(other!=null && other.getValue()<=p){
            map.remove(other.getKey());
            other=map.higherEntry(c);
        }
    }
}
