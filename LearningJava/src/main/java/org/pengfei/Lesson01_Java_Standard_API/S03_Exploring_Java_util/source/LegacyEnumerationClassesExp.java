package org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source;

import java.io.*;
import java.util.*;

public class LegacyEnumerationClassesExp {

    public static void exp1() {
        Vector<Integer> v = new Vector<>(3, 2);
        System.out.println("Initial Vector Size: " + v.capacity());

        v.addElement(1);
        v.addElement(2);
        v.addElement(3);
        v.addElement(4);
        v.addElement(5);

        System.out.println("Vector Size after adding five elements: " + v.capacity());

        v.addElement(6);
        v.addElement(7);
        v.addElement(8);
        v.addElement(9);
        v.addElement(10);
        System.out.println("Vector Size after adding 10 elements: " + v.capacity());

        //get First/last element of the vector
        System.out.println("First element is: " + v.firstElement());
        System.out.println("Last element is: " + v.lastElement());

        // contains a element
        System.out.println("Vector contains 3: " + v.contains(3));

        // get enumeration(old iterator)
        Enumeration<Integer> vEnum = v.elements();

        //iterates with enumeration
        while (vEnum.hasMoreElements()) {
            System.out.print(vEnum.nextElement() + " ");
        }
        System.out.println();

        // get iterator from the vector
        Iterator<Integer> vIt = v.iterator();

        // iterates with iterator
        while (vIt.hasNext()) {
            System.out.print(vIt.next() + " ");
        }
        System.out.println();
    }

    public static void exp2() {
        // hashtable does not allow duplicate key, if a key is used many times, the newest value replace old value.

        Hashtable<Integer, String> ht = new Hashtable<Integer, String>();
        ht.put(101, " ajay");
        ht.put(101, "Vijay");
        ht.put(102, "Ravi");
        ht.put(103, "Rahul");
        ht.put(104, "Rahul");

        // show content with entrySet
        System.out.println("-------------Hash table entries -------------");
        for (Map.Entry m : ht.entrySet()) {
            System.out.println(m.getKey() + " " + m.getValue());
        }

        // show all keys, note keys() returns an enumeration, not iterator
        System.out.println("-------------Hash table keys --------------");
        Enumeration<Integer> keys = ht.keys();
        while (keys.hasMoreElements()) System.out.print(keys.nextElement() + " ");
        System.out.println();

        // show all keys with iterator
        Set<Integer> set = ht.keySet();
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        //get element by using a key
        int i = 103;
        var e = ht.get(i);
        System.out.println("Element with key " + i + " has value : " + e);

        // change 103 value to Haha
        ht.put(103, "Haha");
        e = ht.get(i);
        System.out.println("After change, Element with key " + i + " has value : " + e);
    }

    public static void exp3() {
        Properties capitals = new Properties();
        capitals.setProperty("China", "Beijing");
        capitals.setProperty("France", "Paris");
        capitals.setProperty("Japan", "Tokyo");

        //get a set views of keys
        Set<?> countries = capitals.keySet();

        for (Object country : countries) {
            Object city = capitals.get((String) country);
            System.out.println("The capital of " + country + " is " + city);
        }

        // if you are not sure the key you search is in the Properties, you can give a default value in case no key is
        // found
        String country = "USA";
        String city = capitals.getProperty(country, "Not Found");
        System.out.println("The capital of " + country + " is " + city);
    }

    public static void exp4(String filePath){

        Properties p= new Properties();
       BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String name=null, number=null;
        FileInputStream fin=null;
        boolean changed=false;

        //try to open phonebook.db file
        try{
            fin=new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // if file exist, use property to the file content.
        if(fin!=null){
            try {
                p.load(fin);
                fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        // add new names and numbers
        do {
            System.out.println("Enter new name "+ " or q to stop");
            try {
                name=br.readLine();
                //if sees q, quit the loop
                if(name.equals("q")) continue;

                System.out.println("Enter number ");
                number=br.readLine();
                // put the new name and number into properties
                p.setProperty(name,number);
                changed=true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }while (!name.equals("q"));

        // if the properties changed, save it  to the disk

        if(changed) {
            //try with resource
            try(FileOutputStream fout=new FileOutputStream(filePath);) {
                p.store(fout,"Phone book");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // search a number with a given name
        do{
            System.out.println("Enter a name to find his number, or q to quit");
            try {
                name=br.readLine();
                if(name.equals("q")) continue;

                number=p.getProperty(name,"Not found");

                System.out.println("The number of "+name+" is "+number);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }while(!name.equals("q"));
    }

}
