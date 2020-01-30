package org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source;

import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;

public class FormatterExp {
    public static void exp1(){
        Formatter f=new Formatter();


        // %s is called format conversion specifiers
        f.format("Formatting %s is easy %d %f","with java", 10,98.6);

        //use the toString method of Formatter
        System.out.println(f);
        f.close();
    }

    public static void exp2(){
        Formatter f=new Formatter();
        Calendar c= GregorianCalendar.getInstance();

        // display standard 12-hour time format
        f.format("%tr",c);
        System.out.println(f);
        f.close();

        // display complete time and date information
        f=new Formatter();
        f.format("%tc",c);
        System.out.println(f);
        f.close();

        // display just hour and minute
        f=new Formatter();
        f.format("%tl:%tM",c,c);
        System.out.println(f);
        f.close();

        // display month by name and number
        f=new Formatter();
        f.format("%tB %tb %tm",c,c,c);
        System.out.println(f);
        f.close();
    }

    public static void exp3(){
        Formatter f=new Formatter();
        f.format("Copying file %nTransfer is %d%% complete",88);
        System.out.println(f);
        f.close();
    }

    public static void exp4(){
        Formatter f= new Formatter();
        /*
        * %f: simple float
        * %f12f: float with 12 digit, pad with space
        * %012f: pad with 0*/
        f.format("|%f|%n|%12f|%n|%012f|",10.12345,10.12345,10.12345);
        System.out.println(f);
        f.close();
    }

    public static void exp5(){
        Formatter f;
        for(int i=1;i<=10;i++){
                f=new Formatter();
        f.format("%4d %4d %4d", i,i*i,i*i*i);
        System.out.println(f);
        f.close();
    }
    }

    public static void exp6(){
        Formatter f=new Formatter();

        // format 4 decimal(4 digit after .)
        f.format("%.4f",123.1234567);
        System.out.println(f);
        f.close();

        // format to 2 decimal places in a 16 character field
        f=new Formatter();
        f.format("%16.2e",123.1234567);
        System.out.println(f);
        f.close();

        // display at most 15 character in a string
        f=new Formatter();
        f.format("%.15s","Formatting with Java is now easy.");
        System.out.println(f);
        f.close();

    }

    public static void exp7(){
        Formatter f=new Formatter();

        //default right justified
        f.format("|%10.2f|",123.123);
        System.out.println(f);
        f.close();

        // now left
        f=new Formatter();
        f.format("|%-10.2f|",123.123);
        System.out.println(f);
        f.close();
    }

    public static void exp8(){
        Formatter f=new Formatter();

        //print negative
        f.format("% d",-100);
        System.out.println(f);
        f.close();

        // print positive
        f=new Formatter();
        f.format("% d",100);
        System.out.println(f);
        f.close();
    }

    public static void exp9(){
        Formatter f=new Formatter();

        //print negative in parentheses
        f.format("%(d",-100);
        System.out.println(f);
        f.close();

    }

    public static void exp10(){
        Formatter f=new Formatter();
        //large number with commas
        f.format("%,.2f",123456789.34);
        System.out.println(f);
        f.close();
    }

    public static void exp11(){
        Formatter f=new Formatter();

        // using argument index in default order
        f.format("%1$d, %2$d, %3$d", 1,2,3);
        System.out.println(f);
        f.close();

        f=new Formatter();
        // using argument index in reverse order
        f.format("%3$d, %2$d, %1$d", 1,2,3);
        System.out.println(f);
        f.close();


        f=new Formatter();
        // print three times the same arg
        f.format("%d, %1$d, %1$d", 1);
        System.out.println(f);
        f.close();


        f=new Formatter();
        // using the previous argument index
        f.format("%d, %<d, %<d", 1,2,3);
        System.out.println(f);
        f.close();
    }

    public static void exp12(){
        try(Formatter f=new Formatter()){
            f.format("Formatting %s is easy %d, %f","with Java",8,98.8);
            System.out.println(f);
        }
    }

    public static void exp13(){
            System.out.printf("Formatting %s is easy %d, %f","with Java",8,98.8);

    }
}
