package org.pengfei.Lesson00_Java_Basics.S05_Advance_Classes_Methods.source;

public class PassObjToMethods {

    /*Primitive type are passed to method by using call-by-value*/

    static class Test{
        public static void change(int i, int j){
            i=i+j;
            j=-j;
        }
    }

    public static void exp1(){
        int a=15,b=20;
        System.out.println("Before change, a value: "+a+" b value: "+b);
        Test.change(a,b);
        System.out.println("After change, a value: "+a+" b value: "+b);
    }


    /* Object type are passed to method by using call-by-reference*/
   static class Test1{
       int a,b;
      Test1(int a, int b){
          this.a=a;
          this.b=b;
      }
       public static void change(Test1 ob){
           ob.a=ob.a+ob.b;
           ob.b=-ob.b;

       }
    }

    public static void exp2(){
       Test1 t1=new Test1(1,2);
        System.out.println("Before change, a value: "+t1.a+" b value: "+t1.b);
        Test1.change(t1);
        System.out.println("After change, a value: "+t1.a+" b value: "+t1.b);
    }


    public static void exp3(){
       int i=3,j=4;
       Integer a=new Integer(i);
       Integer b=new Integer(j);
       System.out.println("a has value: "+a.toString()+" b has value: "+b.toString());
       System.out.println("a is greater than b: "+a.compareTo(b));
    }

}
