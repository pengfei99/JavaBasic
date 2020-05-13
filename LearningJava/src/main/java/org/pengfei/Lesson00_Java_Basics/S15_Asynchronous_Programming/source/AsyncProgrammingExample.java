package org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source;

public class AsyncProgrammingExample {

    /*synchronous call back*/
    public static void exp1(){
        //create a mouse listener which open a new window
        MouseClickedListener mcListener=new OpenNewWindow();

        // create a mouse
        MySyncMouse mouse=new MySyncMouse();

        // register the listener to the mouse
        mouse.registerMouseClickedLister(mcListener);
        // click the mouse, generate an event, which starts the call back.
        mouse.doClick();
    }

    /*Asynchronous call back*/
    public static void exp2(){
        //create a mouse listener which open a new window
        MouseClickedListener mcListener=new OpenNewWindow();

        // create a async mouse
        MyAsyncMouse mouse=new MyAsyncMouse();

        // register the listener to the mouse
        mouse.registerMouseClickedLister(mcListener);
        // click the mouse, generate an event, which starts the call back.
        mouse.doClick();
        mouse.doClick();

        // we need to stop the mouse internal thread pool
        mouse.stop();
    }
}
