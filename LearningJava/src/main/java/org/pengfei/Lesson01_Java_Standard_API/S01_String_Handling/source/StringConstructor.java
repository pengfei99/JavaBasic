package org.pengfei.Lesson01_Java_Standard_API.S01_String_Handling.source;

public class StringConstructor {

    public static void exp01(){
        char c[]={'a','b','c','d','e','f','g'};

        String s1= new String(c);
        String s2 = new String(c,2,3);
        String s3=new String(s1);
        //autoboxing
        String s4="toto";
        String s5=new String("toto");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s5);

    }

    public static void exp02(){
        byte ascii[]={65,66,67,68,69,70};
        String s1=new String(ascii);

        String s2 =new String(ascii,2,3);

        System.out.println(s1);
        System.out.println(s2);
    }

    public static void exp03(){
        int unicode[]={65,66,67,68,69,70};
        String s1=new String(unicode,0,6);

        String s2 =new String(unicode,2,3);

        System.out.println(s1);
        System.out.println(s2);
    }
}
