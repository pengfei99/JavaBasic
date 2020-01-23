package org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source.comparator;

import java.util.Comparator;

public class ThenFirstNameComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        // if last equals, then compare first name
         return o1.compareToIgnoreCase(o2);
    }
}
