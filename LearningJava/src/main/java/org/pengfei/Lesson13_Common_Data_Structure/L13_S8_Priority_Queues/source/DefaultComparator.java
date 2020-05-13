package org.pengfei.Lesson13_Common_Data_Structure.L13_S8_Priority_Queues.source;

import java.util.Comparator;

public class DefaultComparator<E> implements Comparator<E> {
    public int compare(E a, E b) throws ClassCastException {
        return ((Comparable<E>) a).compareTo(b);
    }
}