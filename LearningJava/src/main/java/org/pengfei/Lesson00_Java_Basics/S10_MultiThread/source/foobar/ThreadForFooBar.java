package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source.foobar;

public class ThreadForFooBar extends Thread {

    private String threadName;

    // A static Foobar object which is shared by all instance of ThreadForFooBar
    private static FooBar fooBar = new FooBar(5);

    public ThreadForFooBar(String threadName) {
        super(threadName);
        this.threadName = threadName;
    }

    @Override
    public void run() {
        if (threadName.equals("foo")) {
            try {
                fooBar.foo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if(threadName.equals("bar")) {
            try {
                fooBar.bar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else throw new IllegalArgumentException("Wrong thread name");
    }
}
