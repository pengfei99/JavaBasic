package org.pengfei.Lesson00_Java_Basics.S11_Autoboxing_Enum_Annotation.source;

/** The following code declares an annotation called MyAnnotation. Notice the "@interface". This tells the compiler
 * that an annotation type is being declared. Just like interface, in annotation, we only declare method name not the
 * body. In our example, we declare two methods: description() and priority()
 *  */
public @interface MyAnnotation {
    String description();
    int priority();
}
