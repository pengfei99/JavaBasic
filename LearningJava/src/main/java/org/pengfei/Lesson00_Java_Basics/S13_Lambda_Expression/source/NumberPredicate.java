package org.pengfei.Lesson00_Java_Basics.S13_Lambda_Expression.source;

public interface NumberPredicate<T extends Number> {
    boolean test(T n, T m);
}
