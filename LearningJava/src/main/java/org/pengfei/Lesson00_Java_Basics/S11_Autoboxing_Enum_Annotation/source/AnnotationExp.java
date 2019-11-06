package org.pengfei.Lesson00_Java_Basics.S11_Autoboxing_Enum_Annotation.source;

public class AnnotationExp {

    @MyAnnotation(description = "Annotation usage example", priority = 8)
    public static void exp1(){
        System.out.println("Annotation test");
    }


    public static void exp2(){
        // you can notice the class and method which are annotated as deprecated.
        DeprecatedClass test=new DeprecatedClass("test");
        System.out.println(test.getMsg());
    }
}
