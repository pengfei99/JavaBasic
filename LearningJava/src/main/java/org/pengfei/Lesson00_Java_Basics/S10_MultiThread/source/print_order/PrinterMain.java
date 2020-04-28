package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source.print_order;

public class PrinterMain {
    /* no matter in which order you run the three thread, it always print firstsecondthird*/
    public static void main(String[] args){
        ThreadForPrinter t1=new ThreadForPrinter("first");
        ThreadForPrinter t2=new ThreadForPrinter("second");
        ThreadForPrinter t3=new ThreadForPrinter("third");
        t3.start();
        t1.start();
        t2.start();

    }
}
