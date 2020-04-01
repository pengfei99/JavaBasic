package org.pengfei.Lesson01_Java_Standard_API.S11_Regular_Expression.source;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatchingExample {

    public static void exp1(){
        boolean found;
        // create a pattern by using factory method compile. Here, the pattern string is Java.
        Pattern myPattern=Pattern.compile("Java");


        // create a matcher based on myPattern. The input string sequence is the arg.
        Matcher matcher1=myPattern.matcher("Java");
        found=matcher1.matches();
        System.out.println("First matcher is a match: "+found);

        // note that, pattern matching is case sensitive
        Matcher matcher2=myPattern.matcher("java");
        found=matcher2.matches();
        System.out.println("Second matcher is a match: "+found);

        // matches() method returns true only if the whole string matches with the pattern.
        //  It will not return true just because a subsequence matches.
        Matcher matcher3=myPattern.matcher("Java is great. I love java");
        found=matcher3.matches();
        System.out.println("Third matcher is a match: "+found);

        // find() return true if a subsequence matches.
        found=matcher3.find();
        System.out.println("Find on Third matcher returns: "+found);


    }

    public static void exp2(){
           Pattern p=Pattern.compile("Java");
           Matcher m= p.matcher("Java is great. I love Java");
/*
* Notice the first match "Java" starts with index 0, ends with 3. But the end() returns 4. */
           while (m.find()){
               System.out.println("Find a Matching Subsequence ");
               System.out.println("Matched Subsequence staring index: "+m.start());
               System.out.println("Matched Subsequence ending index: "+m.end());

           }
    }

    public static void exp3(){
        Boolean found;
        Pattern p=Pattern.compile("Go+g.*e");

        // it returns true
        Matcher matcher1=p.matcher("Goge");
        found=matcher1.matches();
        System.out.println("First matcher is a match: "+found);

        Matcher matcher2=p.matcher("Gooooogle");
        found=matcher2.matches();
        System.out.println("Second matcher is a match: "+found);

        Matcher matcher3=p.matcher("Gogle");
        found=matcher3.matches();
        System.out.println("Third matcher is a match: "+found);

        Pattern p2=Pattern.compile("Go+?g.*e");
        Matcher matcher4=p2.matcher("Goge");
        found=matcher4.matches();
        System.out.println("Fourth matcher is a match: "+found);

    }

    public static void exp4(){
        Pattern p1=Pattern.compile("e.+?d");
        Matcher m1=p1.matcher("extend end lol this java ed");

        while (m1.find()){
           System.out.println("m1 Find subsequence: "+m1.group());
        }

        // we inverse the order of ? and +. we notice it found two end.
        Pattern p2=Pattern.compile("e.?+d");
        Matcher m2=p2.matcher("extend end lol this java ed");

        while (m2.find()){
            System.out.println("m2 Find subsequence: "+m2.group());
        }

        // in this pattern, we can find ed.
        Pattern p3=Pattern.compile("e.?d");
        Matcher m3=p3.matcher("extend end lol this java ed");

        while (m3.find()){
            System.out.println("m3 Find subsequence: "+m3.group());
        }

        // we can specify the range of characters by using {min, limit}, which matches min
        //times, up to limit times. Also supported are {min} and {min,} which match min
        //times, and min times but possibly more, respectively.
        // In this example, we use {0,3} to specify matches 0 up to 6
        Pattern p4=Pattern.compile("e.{0,6}d");
        Matcher m4=p4.matcher("extend end lol this java ed");

        while (m4.find()){
            System.out.println("m4 Find subsequence: "+m4.group());
        }
    }

    public static void exp5(){
        String str="this is java. This IS Java";
        //Matches all characters in lower case
        Pattern p1=Pattern.compile("[a-z]+");
        Matcher m1=p1.matcher(str);

        while(m1.find()) System.out.println("m1 find subsequence: "+m1.group());

        //Matches all characters
        Pattern p2=Pattern.compile("[a-zA-Z]+");
        Matcher m2=p2.matcher(str);

        while(m2.find()) System.out.println("m2 find subsequence: "+m2.group());
    }

    public static void exp6(){
        String str="John Johnathan toto foo bar";
        // here . means any characters. so it's important to have last space in "John.*? ".
        Pattern p=Pattern.compile("John.*? ");
        Matcher m= p.matcher(str);

        System.out.println("Origin string: "+str);
        System.out.println("Replaced string: "+m.replaceAll("Alex "));
    }

    public static void exp7(){
        String str="one two,three.four!five";
        // here we define space , . ! as separators
        Pattern p=Pattern.compile("[ ,.!]");
        String[] tokens=p.split(str);
        System.out.println(Arrays.toString(tokens));
    }

    public static void exp8(){
        //You can use the matches() method defined by Pattern to do a one time matching
        // first argument is the pattern, second is the string sequence to be tested.
        boolean m1=Pattern.matches("e.{0,6}d","extend");

        System.out.println("M1 result: "+m1);
        // you can also called the matcheds() method defined by String.
        String str="extend";
       boolean m2= str.matches("e.*d");

        System.out.println("M2 result: "+m2);

    }
}
