package org.pengfei.Lesson01_Java_Standard_API.S02_Exploring_Java_lang.source;

import org.pengfei.Lesson00_Java_Basics.S07_Interfaces_Packages.source.B;

public class PrimitiveTypeWrapper {

    public static void exp1(){
        Float f1=new Float( 8.8);
        Float f2=Float.valueOf(8.8f);
        Float f3=Float.valueOf("8.8");

        System.out.println(f1.toString());
        System.out.println(f2.toString());
        System.out.println(f3.toString());
        System.out.println("f1 = f2: "+f1.equals(f2));
        System.out.println("f1 compareTo 6.6: "+ f1.compareTo(6.6f));
        System.out.println("f1 compareTo 8.8: "+ f1.compareTo(8.8f));
        System.out.println("f1 compareTo 9.6: "+ Float.compare(f1,9.6f));


        Double d1=Double.valueOf(8.8);
        Double d2=Double.valueOf("8.8");
        System.out.println(d1.toString());
        System.out.println(d2.toString());


    }

    public static void exp2(){
        Float f1=Float.valueOf("8.8");
        System.out.println(f1+" is NaN: "+f1.isNaN());
        System.out.println(f1+" is infinite: "+f1.isInfinite());

        Float f2=Float.valueOf((float)(1/0.));
        Float f3=Float.valueOf((float)(0/0.));
        System.out.println("f2 isInfinite: "+f2.isInfinite()+" f2 is NaN: "+f2.isNaN());
        System.out.println("f3 isInfinite: "+f3.isInfinite()+" f3 is NaN: "+f3.isNaN());
    }

    public static void exp3(){
        Integer i1=Integer.valueOf(19648);

        // Convert string to primitive types
        int i2 = Integer.parseInt("1");
        Long l1=Long.parseLong("10");

        System.out.println(i1+" in decimal "+ i1.toString());
        System.out.println(i1+" in binary "+ Integer.toBinaryString(i1));
        System.out.println(i1+" in Hex-decimal "+ Integer.toHexString(i1));
        System.out.println(i1+" in Octal "+ Integer.toOctalString(i1));
    }

    public static void exp4(){
        char a[]={'a','b','5','?','A',' '};

        // convert from char to Character and vice versa
        Character c1=Character.valueOf('a');
        char ch1=c1.charValue();

        for(int i=0;i<a.length;i++){
            if(Character.isDigit(a[i])) System.out.println(a[i]+ " is a digit");
            if(Character.isLetter(a[i])) System.out.println(a[i]+ " is a Letter");
            if(Character.isWhitespace(a[i])) System.out.println(a[i]+ " is white space");
            if(Character.isUpperCase(a[i])) System.out.println(a[i]+ " is uppercase");
            if(Character.isLowerCase(a[i])) System.out.println(a[i]+ " is lowercase");
        }
    }

    public static void exp5(){
        char ch1 = Character.forDigit(9, 10);
        char ch2 = Character.forDigit(10, 16);


        System.out.println("Character.forDigit(9, 10) has value: "+ch1);
        System.out.println("Character.forDigit(10, 16) has value: "+ch2);

    }

    public static void exp6(){
        Boolean b1=Boolean.valueOf(false);
        Boolean b2=Boolean.valueOf("True");

        //static field of class Boolean
        Class<Boolean> b3 = Boolean.TYPE;
        Boolean b4 = Boolean.TRUE;
        Boolean b5=Boolean.FALSE;

        System.out.println("b1 has value: "+b1);
        System.out.println("b2 has value: "+b2);
        System.out.println("b3 has value: "+b3);
        System.out.println("b4 has value: "+b4);
        System.out.println("b5 has value: "+b5);

        System.out.println("b2.compareTo(b1): "+b2.compareTo(b1));
        System.out.println("b1.compareTo(b2): "+b1.compareTo(b2));
        System.out.println("b2.compareTo(b4): "+b2.compareTo(b4));
    }


}
