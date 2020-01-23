package org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source.comparator;

import org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source.Person;

import java.util.Comparator;

public class PComp implements Comparator<Person> {
    @Override

    // In this comparator, we first compare age, if age equal, then we compare name
    public int compare(Person o1, Person o2) {
        if(o1.getAge()>o2.getAge()) return 1;
        else if(o1.getAge()==o2.getAge()) return o1.getName().compareTo(o2.getName());
        else return -1;
    }
}
