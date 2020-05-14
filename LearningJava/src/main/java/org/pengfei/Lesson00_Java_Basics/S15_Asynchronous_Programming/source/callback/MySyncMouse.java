package org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source.callback;

public class MySyncMouse {

    private MouseClickedListener mcListener;

    public void registerMouseClickedLister(MouseClickedListener mcListener) {
        this.mcListener = mcListener;
    }

    public void doClick() {
        // perform any operation
        System.out.println("Performing callback before synchronous Task");

        // check if listener is registered.
        if (this.mcListener != null) {

            // invoke the callback method of class OpenNewWindow, and this blocks the execution of mouse
            mcListener.mouseClicked();
        }

        System.out.println("After the callback finished");
    }
}
