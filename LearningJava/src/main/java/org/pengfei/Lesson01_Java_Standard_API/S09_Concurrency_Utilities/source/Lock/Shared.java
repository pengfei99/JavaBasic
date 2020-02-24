package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.Lock;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Shared {
    static int count = 0;
    static List<Integer> list= new CopyOnWriteArrayList<Integer>();
}
