package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source.print_order;


public class ThreadForPrinter extends Thread {

    private String threadName;

    // A static Foobar object which is shared by all instance of ThreadForFooBar
    private static Printer printer = new Printer();

    public ThreadForPrinter(String threadName) {
        super(threadName);
        this.threadName = threadName;
    }

    @Override
    public void run() {
        System.out.println("Thread: "+threadName+"starts");
        if (threadName.equals("first")) {
            try {
                printer.first();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if(threadName.equals("second")) {
            try {
                printer.second();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if(threadName.equals("third")) {
            try {
                printer.third();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else throw new IllegalArgumentException("Wrong thread name");

        System.out.println("Thread: "+threadName+"ends");
    }
}
