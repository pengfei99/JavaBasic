package org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source;

import org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source.TimerTask.MyTimerTask;

import java.text.SimpleDateFormat;
import java.util.*;

public class UtilityClassesExp {
    public static void exp1(){
         String str="title=Java: The best programing language;"+
                 "author= pengfei;"+
                 "date=27/01/2020;";

        StringTokenizer st=new StringTokenizer(str,"=;");
        while(st.hasMoreElements()){
            String key=st.nextToken();
            String val=st.nextToken();
            System.out.println(key+"\t"+val);
        }

    }

    public static void exp2(){
        BitSet bits1=new BitSet(16);
        BitSet bits2=new BitSet(16);

        for(int i=0; i<16; i++){
            //set will turn the bit to true
            if((i%2)==0) bits1.set(i);
            if((i%5)!=0) bits2.set(i);
        }
        // toString of bitset will print all index where bit is true
        System.out.println("Initial content in bits1: "+ bits1);
        System.out.println("\nInitial content in bits2: "+ bits2);

        // And bits
        bits2.and(bits1);
        System.out.println("After bits2 And bits1, bits2 value: "+bits2);

        // Or bits
        bits2.or(bits1);
        System.out.println("After bits2 or bits1, bits2 value: "+bits2);

        // xOr bits
        bits2.xor(bits1);
        System.out.println("After bits2 xor bits1, bits2 value: "+bits2);
    }

    public static void exp3(){
        Optional<String> noVal=Optional.empty();
        Optional<String> hasVal=Optional.of("ABCDEF");

        //check if Optional val has value or not
        System.out.println("noVal.isPresent returns value: "+noVal.isPresent());
        System.out.println("hasVal.isPresent returns value: "+hasVal.isPresent());

        //get value from optional val with orElse,
        String str1=noVal.orElse("Default String");
        String str2=hasVal.orElse("Default String");

        System.out.println("str1 value: "+str1);
        System.out.println("str2 value: "+str2);
    }

    public static void exp4(){
        // get current date
        Date date=new Date();

        //display time and date by using toString() of Date
        System.out.println(date);

        // get the millisec of current date
        long milli=date.getTime();
        System.out.println("Millis seconds since Jan.1. 1970 GMT = "+milli);
    }

    public static void exp5(){
        Calendar c= Calendar.getInstance();

        //Display current time and date information
        // note c.get(Calendar.MONTH) will return a int between 0 and 11, 0->Jan
        System.out.println("Date: "+c.get(Calendar.MONTH)+ " "+c.get(Calendar.DATE)+" "+ c.get(Calendar.YEAR));

        System.out.println("Time: "+c.get(Calendar.HOUR)+ ":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND));


        //You can also set the time and date of a calendar
        c.set(Calendar.HOUR,12);
        System.out.println("Updated Time: "+c.get(Calendar.HOUR)+ ":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND));
    }

    public static void exp6(){
        GregorianCalendar gc=new GregorianCalendar();
        System.out.println("Date: "+gc.get(Calendar.MONTH)+ " "+gc.get(Calendar.DATE)+" "+ gc.get(Calendar.YEAR));

        System.out.println("Time: "+gc.get(Calendar.HOUR)+ ":"+gc.get(Calendar.MINUTE)+":"+gc.get(Calendar.SECOND));

        // test current year is a leap year or not
        int year=gc.get(Calendar.YEAR);
        System.out.println(year+" is a leap year: "+gc.isLeapYear(year));

        // reset year to 2018
        gc.set(Calendar.YEAR,2018);
        year=gc.get(Calendar.YEAR);
        System.out.println(year+" is a leap year: "+gc.isLeapYear(year));
    }

    public static void exp7(){
        SimpleTimeZone tz1 = new SimpleTimeZone(0, "MyTimeZone");


        SimpleTimeZone tz2 = new SimpleTimeZone(0, "MyTimeZone",
                /* DST start day: August, 1, 0:00 */
                Calendar.AUGUST, 1, 0, 0,
                /* DST end day: January, 1, 0:00 (wall-clock)*/
                Calendar.JANUARY, 1, 0, 0,
                60 * 60 * 1000);

        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        System.out.println("Current time in GMT:"+cal);

        // setting a date using GMT zone just after the end rule of tz zone
        cal.clear();
        cal.set(Calendar.ERA, GregorianCalendar.AD);
        cal.set(1998, Calendar.DECEMBER, 31, 23, 01, 00);
        System.out.println("Updated time in GMT:"+cal);

        Date date = cal.getTime();

        int millis = cal.get(Calendar.HOUR_OF_DAY) * 3600000
                + cal.get(Calendar.MINUTE) * 60000
                + cal.get(Calendar.SECOND) * 1000
                + cal.get(Calendar.MILLISECOND);
        /* we must use standard local time */
        millis += tz2.getRawOffset();

        int offset = tz2.getOffset(cal.get(Calendar.ERA),
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DATE),
                cal.get(Calendar.DAY_OF_WEEK),
                millis);

        if (offset != 0) {
            SimpleDateFormat format = new SimpleDateFormat("dd MMM HH:mm:ss zzz",
                    Locale.US);
            format.setTimeZone(tz2);
            System.out.println("Wrong DST transition: " + tz2
                    + "\na date just after DST = " + format.format(date)
                    + "\ngetOffset = " + offset);
        }
    }

    //random
    public static void exp8(){
        Random r=new Random();
        double val,sum=0;
        int bell[]=new int[10];

        for(int i=0;i<100;i++){
            // generate 100 random gaussian number
            val=r.nextGaussian();
            sum+=val;
            double t=-2;

            // put the generated number into 10 categories, each categories has 0.5 distance
            for(int x=0;x<10;x++, t+=0.5){
                // when find a new number for the categories, increment the category value by 1.
                if(val<t){
                    bell[x]++;
                    break;
                }
            }

        }
        System.out.println("Average of values: "+(sum/100));

        // display the bell curve, sideways
        // loop for each categories
        for(int j=0;j<10;j++){
            // for each element of a category print a start. for example bell[3]=5 then print 5 starts
            for(int x=bell[j];x>0;x--) System.out.print("*");
            System.out.println();
        }

    }


    public static void exp9(){

        MyTimerTask myTask= new MyTimerTask();

        Timer myTimer=new Timer();

        /*
        * Set an initial delay of 10 sec, then repeat the task every second*/
        myTimer.schedule(myTask,10000,1000);

        //stop the main thread for 15 sec, and see the timer task run on its own
        try{
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //stop all task in timer
        myTimer.cancel();
    }

    public static void exp10(){
        // Currency is strongly linked with Locale instance. here we create a currency based on China and US locale.
        Currency c1=Currency.getInstance(Locale.CHINA);
        Currency c2=Currency.getInstance(Locale.US);
        System.out.println(c1.getDisplayName()+" has Symbol: "+c1.getSymbol()+" default fractional digits: "+c1.getDefaultFractionDigits());
        System.out.println(c2.getDisplayName()+" has Symbol: "+c2.getSymbol()+" default fractional digits: "+c2.getDefaultFractionDigits());
    }
}
