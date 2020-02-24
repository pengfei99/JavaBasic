package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class SharedAtomicInt {
    //create a new atomic integer which is thread safe.
    static AtomicInteger number=new AtomicInteger(0);
}
