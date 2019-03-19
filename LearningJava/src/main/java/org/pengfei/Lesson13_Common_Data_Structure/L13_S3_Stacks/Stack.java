package org.pengfei.Lesson13_Common_Data_Structure.L13_S3_Stacks;

public interface Stack<E> {
    int size();
    boolean isEmpty();
    void push(E e);
    E top();
    E pop();
}
