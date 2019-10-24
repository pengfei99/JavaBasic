package org.pengfei.Lesson00_Java_Basics.S04_Advance_Datatype_Operator.source;

public class LocalVarTypeInferExp {

    public static void exp1(){
        Object o;
        var avg=10.0;
        o=avg;
        // here can't use avg.getClass, because avg is float which is a primitive type, not an object.
        // So we need to use an object to call the getClass
        System.out.println("avg has type: "+o.getClass().getName().toString());
        System.out.println("avg has value: "+avg);

        // here, var is an user-defined variable identifier, not an keyword
        int var=1;

        // In thi statement, first var is the type infer, second var is a variable.
        var v=-var;
        o=v;
        System.out.println("v has type: "+o.getClass().getName().toString());
        System.out.println("v has value: "+v);
    }

    public static void exp2(){
        /*
        * notice that neither var nor myArray has brackets. Furthermore, you cannot use brackets on the left side of a var
        * declaration. Thus, both of these declarations are invalid:
        * var[] myArray
        * var myArray[]
        */
        var myArray=new int[10];
        System.out.println("myArray has type: "+myArray.getClass().getName().toString());

    }

    public static void exp3(){
        var me=new Person();
        System.out.println("My name is "+me.getName()+" and my age is "+me.getAge());
    }

    static class Person{
        private static String name="Pengfei";
        private static int age=30;

        public int getAge(){
            return age;
        }
        public String getName(){
            return name;
        }
    }


    public static void exp4(){
        int[] nums={1,2,3,4,5,6};

        for(var v:nums){
            System.out.print(v+" ");
        }
    }
}
