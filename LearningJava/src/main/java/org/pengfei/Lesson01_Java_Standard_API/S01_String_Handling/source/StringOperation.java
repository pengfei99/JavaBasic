package org.pengfei.Lesson01_Java_Standard_API.S01_String_Handling.source;

import java.util.ArrayList;
import java.util.List;

public class StringOperation {
    public static void exp1(){
        char c[]={'a','b','c','d',' ','e','f','g',' '};

        String s1= new String(c);

        System.out.println("s1 has value: "+s1);
        System.out.println("length of s1 is: "+s1.length());
    }

    public static void exp2(){
        String age="8";
        String name="Great";
        String str="My name is "+name+" , and I'm "+age;

        System.out.println(str);
    }

    public static void exp3(){
        int age=8;
        //This works, because java automatically convert int to string
        String str="I'm "+age;

        System.out.println(str);

        // But be careful when you mix other types with string concatenation expression, str2 will print as four: 22.
        // Because "four: "+2 happens first, which will return a string, then the second + happens, which is another
        // string concatenation. If you want it print four: 4, you need to do "four: "+(2+2);
        String str2="four: "+2+2;
        System.out.println(str2);

        String str3="four: "+(2+2);
        System.out.println(str3);
    }


    public static void exp4(){
         Box b=new Box(8,8,8);
         System.out.println(b);
         String s="Box b:"+b;
         System.out.println(s);
    }

    public static void exp5(){

        // charAt demo
        String str="abcdef";
        char ch=str.charAt(0);
        System.out.println(ch);

        // getChars demo
        int start=2;
        int end=5;
        // must make sure target is large enough to hold the resulting char array.
        char[] target= new char[end-start];
        str.getChars(start,end,target,0);
        System.out.println(target);

        // getBytes demo
        byte[] targetBytes=str.getBytes();
        for(byte b:targetBytes){
            System.out.println((char)b);
        }

        // toCharArray()
        char[] chars=str.toCharArray();
        for(char c:chars){
            System.out.println((char)c);
        }

    }

    public static void exp6(){
        String s1="Hello";
        //note jvm optimize string literal, s1, s2 points to the same object
        String s2="Hello";
        // to demonstrate == we explicitly create a new string object.
        String s9=new String(s1);
        String s3="toto";
        String s4="HELLO";
        String s5="Hello toto";
        String s6="Hello titi";
        String s7="HELLo toto";
        String s8="what Hello";


        System.out.println(s1 + " equals "+ s2+ " -> "+s1.equals(s2));
        System.out.println(s1 + " == "+ s2+ " -> "+(s1==s2));
        System.out.println(s1 + " == "+ s9+ " -> "+(s1==s9));
        System.out.println(s1 + " equals "+ s3+ " -> "+s1.equals(s3));
        System.out.println(s1 + " equals "+ s4+ " -> "+s1.equals(s4));
        System.out.println(s1 + " equalsIgnoreCase "+ s4+ " -> "+s1.equalsIgnoreCase(s4));

        System.out.println(s5 + " match region "+ s6+ " -> "+s5.regionMatches(0,s6,0,5));
        System.out.println(s5 + " match region "+ s8+ " -> "+s5.regionMatches(0,s8,5,5));
        System.out.println(s5 + " match region "+ s7+ " -> "+s5.regionMatches(0,s7,0,5));
        System.out.println(s5 + " match region ignore case "+ s7+ " -> "+s5.regionMatches(true,0,s7,0,5));

        System.out.println(s5 + " starts with "+ s1+ " -> "+s5.startsWith(s1));
        System.out.println(s8 + " ends with "+ s1+ " -> "+s8.endsWith(s1));

        System.out.println(s5 + " starts with "+ s3+ " at position 5 -> "+s5.startsWith(s3,5));
        System.out.println(s5 + " starts with "+ s3+ " at position 6 -> "+s5.startsWith(s3,6));

        System.out.println(s1 + " compareTo "+ s2+ " -> "+s1.compareTo(s2));
        System.out.println(s1 + " compareTo "+ s3+ " -> "+s1.compareTo(s3));
        System.out.println(s1 + " compareTo "+ s4+ " -> "+s1.compareTo(s4));
    }

    public static void exp7(){
        String s="Now is the time for all good men to come to the aid of their country.";
        System.out.println(s);
        System.out.println("first index of t "+s.indexOf('t'));
        System.out.println("last index of t "+s.lastIndexOf('t'));
        System.out.println("first index of the "+s.indexOf("the"));
        System.out.println("last index of the "+s.lastIndexOf("the"));
        System.out.println("first indexOf(t,10) "+s.indexOf('t',10));
        System.out.println("last indexOf(t,60) "+s.lastIndexOf('t',60));
        System.out.println("first indexOf(the,10) "+s.indexOf("the",10));
        System.out.println("last indexOf(the,60) "+s.lastIndexOf("the",60));

    }

    public static void exp8(){
        String src="This is a test. This is, too.";
        String searchStr="is";
        String sub="was";
        String result="";
        int i;

        // replace all matching substring
        do{
            System.out.println(src);
            i=src.indexOf(searchStr);
            if(i!=-1){
                result = src.substring(0,i);
                result=result+sub;
                result=result+src.substring(i+searchStr.length());
                src = result;
            }
        } while(i!=-1);
    }

    public static void exp9(){
        String s1="hello";
        String s2="world";
        String s3=s1.concat(s2);
        String s4="toto";

        System.out.println(s3);
        System.out.println(s1+s2);

        System.out.println(s3.replace("h","H"));
        System.out.println(s3.replace("world","toto"));
    }

    public static void exp10(){
        String s1="        hello world       ";

        System.out.println(s1);
        System.out.println(s1.trim());

        System.out.println("stripLeading: "+s1.stripLeading());
        System.out.println("stripTrailing: "+s1.stripTrailing());
        System.out.println("strip: "+s1.strip());
    }

    public static void exp11(){
        double d=10.01;
        String str = String.valueOf(d);

        System.out.println(str);
    }

    public static void exp12(){
        String s1="This is a test";
        String s2=s1.toUpperCase();
        String s3=s1.toLowerCase();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }

    public static void exp13(){
        String s1=String.join(" ", "Hello", "world", "Java","is","Great");
        String s2=String.join("; ", "Hello", "world", "Java","is","Great");
        List<String> words=new ArrayList<String>();
        words.add("Hello");
        words.add("world");
        words.add("Java");
        words.add("is");
        words.add("great");
        String s3=String.join(" ",words);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

    }

    /** nested class for toString implementation demo*/
    public static class Box{
        double width;
        double height;
        double depth;

        public Box(double w, double h, double d){
            this.width=w;
            this.height=h;
            this.depth=d;
        }
        public String toString(){
            return "Box dimensions are "+ width+" by " + depth+" by "+ height+".";
        }
    }
}
