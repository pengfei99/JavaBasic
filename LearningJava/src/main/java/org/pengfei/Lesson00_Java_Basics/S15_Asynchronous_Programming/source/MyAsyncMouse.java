package org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyAsyncMouse {

    private MouseClickedListener mcListener;

    private ExecutorService es= Executors.newFixedThreadPool(3);

    public void registerMouseClickedLister(MouseClickedListener mcListener) {
        this.mcListener = mcListener;
    }

    public void doClick() {

        //In async case, each time you click the mouse, a new task is created and submitted to the thread pool
        //So, the callback is running in another thread, which does not block the mouse thread.
        es.submit(() -> {
            // check if listener is registered.
            if (this.mcListener != null) {

                // invoke the callback method of class OpenNewWindow, and this blocks the execution of mouse
                mcListener.mouseClicked();
            }
        });


        System.out.println("After the callback finished");
    }

    public void stop(){
        es.shutdown();
    }
}
