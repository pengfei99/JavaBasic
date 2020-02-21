package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.exchanger;

import java.util.concurrent.Exchanger;

public class StringMaker implements Runnable {
    private final String name = "StringMaker";
    //define an exchanger reference
    private final Exchanger<String> ex;

    private String buffer;

    public StringMaker(Exchanger<String> exchanger) {
        this.ex = exchanger;
        buffer = new String();
    }

    @Override
    public void run() {

        System.out.println("Starting: " + name);
        char c = 'A';

        for (int i = 0; i < 3; i++) {
            // fill the buffer
            for (int j = 0; j < 5; j++) buffer += c++;

            try {
                //Exchange a full buffer for an empty one.
                buffer = ex.exchange(buffer);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Ending: " + name);
    }
}
