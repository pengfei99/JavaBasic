package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.collections;

import java.util.List;

public class ListWriter implements Runnable {
    private final String name;
    private final List<Integer> list;
    public ListWriter(String name, List<Integer> list){
        this.name=name;
        this.list=list;
    }
    @Override
    public void run() {
           for(int i=0;i<5;i++) {
               System.out.println(name+" adding "+i);
               list.add(i);
               try {
                   Thread.sleep(100);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }

    }
}
