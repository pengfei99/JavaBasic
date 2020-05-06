package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source.volatile_exp;

public class MySharedDate {
    private int years;
    private int months;
    private volatile int days;


    /*
    * The udpate() method writes three variables, of which only days is volatile.
    * The full volatile visibility guarantee means, that when a value is written to days, then all variables
    * visible to the thread are also written to main memory. That means, that when a value is written to days,
    * the values of years and months are also written to main memory.
    * */
    public void update(int years, int months, int days){
        this.years  = years;
        this.months = months;
        this.days   = days;
    }

    /*
    * Notice the totalDays() method starts by reading the value of days into the total variable. When reading
    * the value of days, the values of months and years are also read into main memory. Therefore you are guaranteed
    * to see the latest values of days, months and years with the above read sequence.
    * */
    public int totalDays() {
        int total = this.days;
        total += months * 30;
        total += years * 365;
        return total;
    }
}
