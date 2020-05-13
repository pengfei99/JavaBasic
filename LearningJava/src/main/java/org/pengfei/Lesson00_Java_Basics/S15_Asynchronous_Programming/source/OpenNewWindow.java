package org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source;

public class OpenNewWindow implements MouseClickedListener {

    @Override
    public void mouseClicked() {

        //Listener are called back by the mouse clicked event, which is from the mouse
        System.out.println("Performing callback after synchronous Task");

        // perform some routine operation, here, we open a new window in our app
        System.out.println("Opening window");

    }
}
