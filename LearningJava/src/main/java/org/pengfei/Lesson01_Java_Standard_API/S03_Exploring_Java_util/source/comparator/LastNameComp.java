package org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source.comparator;

import java.util.Comparator;

public class LastNameComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int i,j;
        // find index of beginning of last name
        i=o1.lastIndexOf(' ');
        j=o2.lastIndexOf(' ');

        String lastN1=o1.substring(i);
        String lastN2=o2.substring(j);

        //get last name compare result
        return lastN1.compareToIgnoreCase(lastN2);
    }
}
