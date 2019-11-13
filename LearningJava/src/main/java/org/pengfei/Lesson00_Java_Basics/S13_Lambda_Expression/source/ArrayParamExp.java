package org.pengfei.Lesson00_Java_Basics.S13_Lambda_Expression.source;

public interface ArrayParamExp<T extends Number> {

    // The parameter definition in interface is normal
    void transform(T[] a);
}
