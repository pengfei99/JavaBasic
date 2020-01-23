package org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source;

import java.util.*;

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

    public static void exp3(){

        // Creating HashMap and IdentityHashMap objects
        Map hm = new HashMap();
        Map ihm = new IdentityHashMap();

        // Putting key and value in HashMap and IdentityHashMap Object
        hm.put("hmkey","hmvalue");
        hm.put(new String("hmkey"),"hmvalue1");
        ihm.put("ihmkey","ihmvalue");
        ihm.put(new String("ihmkey"),"ihmvalue1");

        // Print Size of HashMap and WeakHashMap Object
        //hm.size() will print 1 since it compares the objects logically
        // and both the keys are same
        System.out.println("Size of HashMap--"+hm.size());

        //ihm.size() will print 2 since it compares the objects by reference
        System.out.println("Size of IdentityHashMap--"+ihm.size());
    }

    public static void exp4(){
        // create an enum map instance
        EnumMap<Color,String> em=new EnumMap<Color, String>(Color.class);

        // add elements to the map
        em.put(Color.Yellow,"Yellow is great");
        em.put(Color.Blue, "Blue is nice");
        em.put(Color.Black,"Black is dark");

        // get all the keys in the map
        Set<Color> keys = em.keySet();
        for(Color c:keys){
            System.out.println("Key: "+c);
        }

        // get entrySet
        Set<Map.Entry<Color, String>> entries = em.entrySet();
        System.out.println(entries);

    }
}
