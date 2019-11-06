package org.pengfei.Lesson00_Java_Basics.S11_Autoboxing_Enum_Annotation.source;

public class AutoboxingExp {

    public static void exp1(){
        //create an object of Integer with a value, aka. boxing
        Integer iOb=Integer.valueOf(100);
        // extract the value from an object, aka. unboxing
        int a = iOb.intValue();
        System.out.println(a+" "+iOb);
    }


    public static void exp2(){
        //autoboxing
        Integer iOb=100;

        //autounboxing
        int a=iOb;

        System.out.println(a+" "+iOb);

    }

    public static void exp3(){
        // m1 take Integer as argument, but we give a in, no error because autoboxing
        m1(10);

        // m2 returns a int, but we assign it to a Integer object. no error because autoboxing
        Integer iOb=m2();
        System.out.println("Return value from method m2() is: "+iOb);
        // m3 returns a Integer, but we assign it to a int. no error because autounboxing
        int a=m3();
        System.out.println("Return value from method m3() is: "+a);
    }

    public static void exp4(){
        Integer iOb1,iOb2;
        int i;

        //autoboxing
        iOb1=9;
        System.out.println("The init value of iOb1: "+iOb1);

        // The following automatically unboxes iOb1, perform the arithmetic operation, then reboxes the result back
        // into iOb1
        ++iOb1;
        System.out.println("The value of iOb1 after ++iOb1: "+iOb1);

        iOb1+=10;
        System.out.println("The value of iOb1 after iOb1+=10: "+iOb1);

        iOb2=iOb1+(iOb1/3);
        System.out.println("The value of iOb2 : "+iOb2);

        // the result in not reboxed
        i=iOb1+(iOb1/3);
        System.out.println("The value of i : "+i);

        /** Thanks to autoboxing, we can use Integer object in switch condition expressions*/
        iOb1=2;
        switch (iOb1){
            case 1:System.out.println("one");break;
            case 2:System.out.println("two");break;
            default:System.out.println("error");

        }
    }

    /***************************************** Internal utilities methods ******************************************/

    //This method receives an Integer Object.
    private static void m1(Integer v){
        System.out.println("method m1() received "+v);
    }

    //This method returns a primitive int
    private static int m2(){
        return 10;
    }

    // This method returns an Integer object
    private static Integer m3(){
        // use autoboxing to convert int to Integer
        return 99;
    }
}
