package org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MapClassesExp {
    public static void  exp1(){
        // Creates a hash map
        HashMap<String, Double> hm=new HashMap<>();

        // put element
        hm.put("John Doe", 888.88);
        hm.put("foo bar", 888.68);
        hm.put("haha liu", 666.88);
        hm.put("zi xiao", -868.88);

        System.out.println("Initial contents of the map: "+hm);

        // get a set of the entries
        Set<Map.Entry<String, Double>> set = hm.entrySet();

        // as set is a collection which implement Iterable interface, so we can use for-each to cycle it
        for(Map.Entry<String,Double> e:set){
            System.out.println("Entry key: "+e.getKey()+"; Entry value: "+e.getValue());

        }

        // modify value of a key
        String key="haha liu";
        Double val=hm.get(key);

        hm.put(key,val+8888.88);

        System.out.println("The new value of "+key+" is : "+hm.get(key));

    }

    public static void  exp2(){
        // Creates a hash map
        TreeMap<String, Double> hm=new TreeMap<>();

        // put element
        hm.put("John Doe", 888.88);
        hm.put("foo bar", 888.68);
        hm.put("haha liu", 666.88);
        hm.put("zi xiao", -868.88);

        System.out.println("Initial contents of the map: "+hm);

        // get a set of the entries
        Set<Map.Entry<String, Double>> set = hm.entrySet();

        // as set is a collection which implement Iterable interface, so we can use for-each to cycle it
        for(Map.Entry<String,Double> e:set){
            System.out.println("Entry key: "+e.getKey()+"; Entry value: "+e.getValue());

        }

        // modify value of a key
        String key="haha liu";
        Double val=hm.get(key);

        hm.put(key,val+8888.88);

        System.out.println("The new value of "+key+" is : "+hm.get(key));

        // call the navigable methods
        String ceilingKey = hm.ceilingKey("toto");
        System.out.println("The ceiling key of toto is: "+ceilingKey);

        String floorKey = hm.floorKey("toto");
        System.out.println("The floor key of toto is: "+floorKey);

    }
}
