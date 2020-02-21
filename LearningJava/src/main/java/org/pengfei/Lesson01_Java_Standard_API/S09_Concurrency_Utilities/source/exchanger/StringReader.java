package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.exchanger;

import java.util.concurrent.Exchanger;

public class StringReader implements Runnable {
    private String name="StringReader";
    private Exchanger<String> ex;
private String result;
    public StringReader(Exchanger<String> ex){
        this.ex=ex;
    }

    @Override
    public void run() {
        System.out.println("Starting: "+name);

        for(int i=0;i<3;i++){
            try{
               //use empty string to exchange the result string
                result= ex.exchange(new String());
                System.out.println("Got: "+result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Ending: "+name);


    }
}
