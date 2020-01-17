package org.pengfei.Lesson01_Java_Standard_API.S02_Exploring_Java_lang.source.thread;

import java.text.SimpleDateFormat;
import java.util.Random;

public class ThreadLocalExample implements Runnable {

   /*
   * If you set values on a ThreadLocal object, the values are visible to the thread that set the value. No thread
   * can set an initial value on a ThreadLocal using set() method which is visible to all threads.
   * Instead you can specify an initial value for a ThreadLocal object by subclassing ThreadLocal and
   * overriding the initialValue().
   *
   * In the following code, we create an anonymous class extends ThreadLocal class and override the initialValue
   * method. In side this method we create a new MyLocalVar object.
   * */
   private final ThreadLocal<MyLocalVar> localVar = new ThreadLocal<MyLocalVar>(){
       @Override
       protected MyLocalVar initialValue()
       {
           return new MyLocalVar(0);
       }
   };


@Override
public void run() {
        System.out.println("Thread Name= "+Thread.currentThread().getName()+" local var value = "+localVar.get().getA());
        try {

        Thread.sleep(new Random().nextInt(10000));
            //MyLocalVar value is changed here by thread, but it won't reflect to other threads
            localVar.set(new MyLocalVar(1));
            System.out.println("Thread Name= "+Thread.currentThread().getName()+" local var value = "+localVar.get().getA());
            Thread.sleep(new Random().nextInt(10000));
            localVar.set(new MyLocalVar(2));
        } catch (InterruptedException e) {
        e.printStackTrace();
        }


        System.out.println("Thread Name= "+Thread.currentThread().getName()+" local var value = "+localVar.get().getA());
        }


        /* Nested class*/
        private class MyLocalVar{

            int a;
            MyLocalVar(int a){
                 this.a=a;
            }



            public int getA() {
                return a;
            }

            public void setA(int a) {
                this.a = a;
            }
        }


        /**  run the example*/
        public static void main(String[] args){
        ThreadLocalExample obj = new ThreadLocalExample();
        for(int i=0 ; i<5; i++){
        Thread t = new Thread(obj, "T"+i);
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t.start();
    }
        }
}


