package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.collections;

import java.util.Iterator;
import java.util.List;

public class ListReader implements Runnable {
    private final String name="List Reader";
    private final List<Integer> list;

    public ListReader(List<Integer> list){
        this.list=list;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Iterator<Integer> it=list.iterator();
        // iterator of synchronized list is not thread safe, so we need to run iterator in a synchronized block.
        synchronized (list){
            System.out.println("The list contents: ");
            while(it.hasNext()) {
                System.out.print(it.next());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
        }
    }
}
