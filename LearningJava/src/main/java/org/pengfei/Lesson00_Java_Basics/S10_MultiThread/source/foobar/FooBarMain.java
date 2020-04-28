package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source.foobar;

public class FooBarMain {
    public static void main(String[] args){
        ThreadForFooBar foo=new ThreadForFooBar("foo");
        foo.start();

        ThreadForFooBar bar=new ThreadForFooBar("bar");
        bar.start();
    }
}
