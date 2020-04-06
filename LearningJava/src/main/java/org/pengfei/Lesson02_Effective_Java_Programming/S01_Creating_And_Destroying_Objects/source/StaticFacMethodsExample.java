package org.pengfei.Lesson02_Effective_Java_Programming.S01_Creating_And_Destroying_Objects.source;

public class StaticFacMethodsExample {
    public static void exp1(){
        /* We call the static factory method without creating the object */
        Boolean valueOfTrue = StaticFacMethodsDemo.valueOf(true);
        System.out.println("Value of true is :"+valueOfTrue);

        /* The name we give to method is more human readable*/
        Boolean reverseValueOfTrue = StaticFacMethodsDemo.reverseValueOf(true);
        System.out.println("Reverse Value of true is :"+reverseValueOfTrue);

    }

    public static void exp2(){
        // you can also set system property statically with "java -DServiceImplemetationClassName=MyServiceImplementation MyApp"
        System.setProperty("ServiceImplementationClassName","org.pengfei.Lesson02_Effective_Java_Programming.S01_Creating_And_Destroying_Objects.source.MyServiceImplementation");
        /* //check the property value
        String className = System.getProperty("ServiceImplemetationClassName");
        System.out.println("className value stored in the Property: "+className);*/

        /* With the getService method, when we deliver the lib, we don't need to provide the implementation of MyService
         * The client of the lib can implement the MyService locally and specify it when using the lib by setting system
         * property. */
        MyService myService=StaticFacMethodsDemo.getService();
        myService.doSomething();
    }
}
