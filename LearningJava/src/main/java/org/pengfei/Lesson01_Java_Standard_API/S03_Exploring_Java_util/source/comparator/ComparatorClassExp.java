package org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source.comparator;

import org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source.Person;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

public class ComparatorClassExp {

    public static void exp1(){
        TreeSet<String> ts1=new TreeSet<>();
        TreeSet<String> ts2=new TreeSet<>(new MyComp());

        ts1.add("A");
        ts1.add("B");
        ts1.add("C");
        ts1.add("D");
        ts1.add("E");

        ts2.add("A");
        ts2.add("B");
        ts2.add("C");
        ts2.add("D");
        ts2.add("E");

        System.out.println("Contents of ts1: "+ts1);
        System.out.println("Contents of ts2: "+ts2);
    }

    public static void exp2(){
        // create a instance of MyComp
        MyComp mc=new MyComp();

        // ts1 uses mc as comparator
        TreeSet<String> ts1=new TreeSet<>(mc);

        // ts1 uses mc.reversed as comparator
        TreeSet<String> ts2=new TreeSet<>(mc.reversed());

        ts1.add("A");
        ts1.add("B");
        ts1.add("C");
        ts1.add("D");
        ts1.add("E");

        ts2.add("A");
        ts2.add("B");
        ts2.add("C");
        ts2.add("D");
        ts2.add("E");

        System.out.println("Contents of ts1: "+ts1);
        System.out.println("Contents of ts2: "+ts2);

    }

    public static void exp3(){
        //use lambda expression to declare a comparator
        Comparator<String> mc= (a,b)->a.compareTo(b);

        // ts1 uses mc as comparator
        TreeSet<String> ts1=new TreeSet<>(mc);

        // use lambda expression to create a anonymous comparator in the TreeSet constructor
        TreeSet<String> ts2=new TreeSet<>((a,b)->b.compareTo(a));

        ts1.add("A");
        ts1.add("B");
        ts1.add("C");
        ts1.add("D");
        ts1.add("E");

        ts2.add("A");
        ts2.add("B");
        ts2.add("C");
        ts2.add("D");
        ts2.add("E");

        System.out.println("Contents of ts1: "+ts1);
        System.out.println("Contents of ts2: "+ts2);
    }

    public static void exp4(){
        Comparator c=new PComp();
        //ts1 uses c as comparator
        TreeSet<Person> ts1=new TreeSet<>(c);

        ts1.add(new Person("John Doe", 12));
        ts1.add(new Person("titi",23));
        ts1.add(new Person("tata",34));
        ts1.add(new Person("foo",12));

        System.out.println("Contents of ts1: "+ts1);

    }

    public static void exp5(){
        // creat an instance of LastNameComp, not encapsulate in the Comparator interface
        LastNameComp lnComp=new LastNameComp();

        // creat the final comparator by chaining the lastName and firstName comparator together
        Comparator c=lnComp.thenComparing(new ThenFirstNameComp());

        // Creates a hash map with c
        TreeMap<String, Double> hm=new TreeMap<>(c);

        // put element
        hm.put("John Doe", 888.88);
        hm.put("foo bar", 888.68);
        hm.put("haha liu", 666.88);
        hm.put("zi xiao", -868.88);

        System.out.println("Initial contents of the map: "+hm);
    }
}
