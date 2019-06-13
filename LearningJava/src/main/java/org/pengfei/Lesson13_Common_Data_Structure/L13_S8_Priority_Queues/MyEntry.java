package org.pengfei.Lesson13_Common_Data_Structure.L13_S8_Priority_Queues;

public interface MyEntry<K,V> {
    K getKey(); // return the key stored in MyEntry
    V getValue(); // return the value stored in MyEntry
    V setValue(V value);
}
