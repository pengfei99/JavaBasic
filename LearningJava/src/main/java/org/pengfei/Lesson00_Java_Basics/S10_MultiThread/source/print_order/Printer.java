package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source.print_order;

public class Printer {


        private boolean first=true,second=false,third=false;

        public Printer() {

        }

        public synchronized void first() throws InterruptedException {
            while(!first){
                wait();
            }
            System.out.println("first");
            first=false;
            second=true;
            third=false;
            notifyAll();
        }

        public synchronized void second() throws InterruptedException {
            while(!second){
                wait();
            }
            System.out.println("second");
            first=false;
            second=false;
            third=true;
            notifyAll();
        }

        public synchronized void third() throws InterruptedException {
            while(!third){
                wait();
            }

           System.out.println("third");
            first=false;
            second=false;
            third=false;
            notifyAll();
        }
    }

