package org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source.comparator;

import java.util.Comparator;

public class MyComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        //reverse the comparing order
        return o2.compareTo(o1);
    }
}
