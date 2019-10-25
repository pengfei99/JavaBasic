package org.pengfei.Lesson00_Java_Basics.S05_Advance_Classes_Methods.source;

public class VarArgsExp {

    public static void vaTest(int ... v) {
        System.out.println("Number of args: "+ v.length);
        System.out.println("Argument contents: ");

        for(int i=0;i<v.length;i++){
            System.out.println(" arg "+i+": "+v[i]);
        }
        System.out.println();
    }

    public static void vaTest1(String name, int ... salaryHistory){
        System.out.println("M. "+ name+" has made "+salaryHistory.length+" month salary");
        System.out.println("Salary history: ");
        for(int i=0;i<salaryHistory.length;i++){
            System.out.println(" Month "+i+": "+salaryHistory[i]);
        }
        System.out.println();
    }

    public static void vaTest(double ... v){
        System.out.println("Number of args in double: "+ v.length);
        System.out.println("Argument contents: ");

        for(int i=0;i<v.length;i++){
            System.out.println(" arg "+i+": "+v[i]);
        }
        System.out.println();
    }

    // ambiguous overloading
    /*public static void vaTest(boolean ... v){
        System.out.println("Number of args in boolean: "+ v.length);
        System.out.println("Argument contents: ");

        for(int i=0;i<v.length;i++){
            System.out.println(" arg "+i+": "+v[i]);
        }
        System.out.println();
    }*/

    // overload the vaTest by adding a normal int parameter, ambiguous overloading
   /* public static void vaTest(int a,int ... v) {
        System.out.println("Argument contents: ");

        for(int i=0;i<v.length;i++){
            System.out.println(" arg "+i+": "+v[i]);
        }
        System.out.println();
    }*/

}
