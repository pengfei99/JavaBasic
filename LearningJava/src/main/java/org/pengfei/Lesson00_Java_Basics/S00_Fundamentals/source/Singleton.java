package org.pengfei.Lesson00_Java_Basics.S00_Fundamentals.source;

/* This is the most widely used approach to create singleton objects.*/
public class Singleton {
    // constructor is private, can't be accessed
    private Singleton(){}

    public static Singleton getInstance(){
        return SingletonHolder.instance;
    }

    // static nested class is shared by all objects of Singleton
    private static class SingletonHolder{
        public static final Singleton instance=new Singleton();
    }
}
