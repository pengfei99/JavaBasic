package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.cyclicbarrier;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class SharedList {
    // here we need to use a thread safe list, if we use normal arrayList, we may lose some elements randomly.
    static CopyOnWriteArrayList<Integer> data=new CopyOnWriteArrayList<>();
     static int sum;
}
