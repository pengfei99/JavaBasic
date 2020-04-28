package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source.zero_even_odd;

public class ZeroEvenOdd  {
    private boolean zero=true,even=false,odd=false;
    private int n;
    private int currentNum=1;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }


    public synchronized void zero() throws InterruptedException {
       do {
           if(currentNum>n) {notifyAll(); break;}
            while(!zero){
                wait();
            }
            System.out.println("zero prints: "+0);
            zero=false;
            //after print 0, we check the currentNumber if it is even, then wake even, if it is odd, then wake odd
            if((currentNum%2)==0) {even=true;odd=false;}
            else {even=false;odd=true;}
            notifyAll();
        }while(currentNum<n);
    }



    public synchronized void odd() throws InterruptedException {
       do{
            if(currentNum>n) {notifyAll(); break;}
            while(zero||even){
                wait();
            }
            System.out.println("odd prints: "+currentNum);
            zero=true;
            currentNum++;
            notifyAll();
        } while(currentNum<n);
    }

    public synchronized void even() throws InterruptedException {
        do{
            if(currentNum>n) {notifyAll(); break;}
            //if zero or odd true, even wait
            while(zero||odd){
                wait();
            }
            System.out.println("even prints: "+currentNum);
            zero=true;
            currentNum++;
            notifyAll();
        }while(currentNum<n);
    }
}