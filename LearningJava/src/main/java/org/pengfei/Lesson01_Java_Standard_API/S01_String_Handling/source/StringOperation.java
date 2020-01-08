package org.pengfei.Lesson01_Java_Standard_API.S01_String_Handling.source;

public class StringOperation {
    public static void exp1(){
        char c[]={'a','b','c','d',' ','e','f','g',' '};

        String s1= new String(c);

        System.out.println("s1 has value: "+s1);
        System.out.println("length of s1 is: "+s1.length());
    }

    public static void exp2(){
        String age="8";
        String name="Great";
        String str="My name is "+name+" , and I'm "+age;

        System.out.println(str);
    }

    public static void exp3(){
        int age=8;
        //This works, because java automatically convert int to string
        String str="I'm "+age;

        System.out.println(str);

        // But be careful when you mix other types with string concatenation expression, str2 will print as four: 22.
        // Because "four: "+2 happens first, which will return a string, then the second + happens, which is another
        // string concatenation. If you want it print four: 4, you need to do "four: "+(2+2);
        String str2="four: "+2+2;
        System.out.println(str2);

        String str3="four: "+(2+2);
        System.out.println(str3);
    }


    public static void exp4(){
         Box b=new Box(8,8,8);
         System.out.println(b);
         String s="Box b:"+b;
         System.out.println(s);
    }

    public static void exp5(){

        // charAt demo
        String str="abcdef";
        char ch=str.charAt(0);
        System.out.println(ch);

        // getChars demo
        int start=2;
        int end=5;
        // must make sure target is large enough to hold the resulting char array.
        char[] target= new char[end-start];
        str.getChars(start,end,target,0);
        System.out.println(target);

        // getBytes demo
        byte[] targetBytes=str.getBytes();
        for(byte b:targetBytes){
            System.out.println((char)b);
        }

    }


    /** nested class for toString implementation demo*/
    public static class Box{
        double width;
        double height;
        double depth;

        public Box(double w, double h, double d){
            this.width=w;
            this.height=h;
            this.depth=d;
        }
        public String toString(){
            return "Box dimensions are "+ width+" by " + depth+" by "+ height+".";
        }
    }
}
