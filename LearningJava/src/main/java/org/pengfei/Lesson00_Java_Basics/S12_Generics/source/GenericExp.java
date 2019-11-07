package org.pengfei.Lesson00_Java_Basics.S12_Generics.source;

import java.util.Arrays;

import static org.pengfei.Lesson00_Java_Basics.S12_Generics.source.GenericMethodDemo.arraysEqual;

public class GenericExp {

    public static void exp1(){
        SimpleGen<String> stringSimpleGen=new SimpleGen<String>("Test");
        SimpleGen<Integer> integerSimpleGen = new SimpleGen<Integer>(1);

        //show type
        stringSimpleGen.showType();
        integerSimpleGen.showType();

        //get value
        System.out.println("Value of stringSimpleGen: "+stringSimpleGen.getObject());
        System.out.println("Value of integerSimpleGen: "+integerSimpleGen.getObject());

    }

    public static void exp2(){
        TwoGen<String,Integer> twoGen=new TwoGen<>("test",1);
        twoGen.showType();
        //get value
        System.out.println("Value of ob1: "+twoGen.getOb1());
        System.out.println("Value of ob2: "+twoGen.getOb2());
    }

    public static void exp3(){
        //Integer is a subclass of Number, so it's correct
        NumericFunctions<Integer> iOb1=new NumericFunctions<>(8);
        System.out.println("Reciprocal of iOb1 is "+ iOb1.reciprocal());
        System.out.println("Fractional component of iOb1 is "+ iOb1.fraction());

        //Double is a subclass of Number, so it's correct
        NumericFunctions<Double> iOb2=new NumericFunctions<>(8.88);
        System.out.println("Reciprocal of iOb1 is "+ iOb2.reciprocal());
        System.out.println("Fractional component of iOb1 is "+ iOb2.fraction());

        //String is not a subclass of Number, so it will generate compile error
       // NumericFunctions<String> iOb3=new NumericFunctions<>("test");

    }

    public static void exp4(){
        Pair<Integer,Integer> x=new Pair<>(8,6);
        Pair<Number,Integer> y= new Pair<>(8.8,6);

        // The two construction below are wrong. because Integer is not the subclass of double, String is not the
        // subclass of Number
        // Pair<Double,Integer> z=new Pair<Double, Integer>(8.8,6);
        // Pair<Number,String> w=new Pair<Number, String>(8.8,"test");
    }


    public static void exp5() {

       NumericFunctions<Double> ob1=new NumericFunctions<Double>(8.8);
       NumericFunctions<Integer> ob2= new NumericFunctions<>(-8);
        // in java, by default a decimal number such as 8.8 is consider as double(double is a 64-bit precision IEEE
        // 754 floating point) by default. If you want to convert it to a Float(float is a 32-bit precision IEEE 754
        // floating point.), you need to put f after the number.
       NumericFunctions<Float> ob3=new NumericFunctions<Float>(Float.valueOf(-8.8f));
       NumericFunctions<Long> ob4=new NumericFunctions<>(8L);

       System.out.println(ob1.num.doubleValue()+" abs equals "+ ob2.num.doubleValue()+ " is "+ ob1.absEqual(ob2));
        System.out.println(ob1.num.doubleValue()+" abs equals "+ ob3.num.doubleValue()+ " is "+ ob1.absEqual(ob3));
        System.out.println(ob2.num.doubleValue()+" abs equals "+ ob3.num.doubleValue()+ " is "+ ob2.absEqual(ob3));
        System.out.println(ob2.num.doubleValue()+" abs equals "+ ob4.num.doubleValue()+ " is "+ ob2.absEqual(ob4));


    }

    public static void exp6(){
        SimpleGen<SuperClass> obS=new SimpleGen<>(new SuperClass());
        SimpleGen<SubClass1> ob1=new SimpleGen<>(new SubClass1());
        SimpleGen<SubClass2> ob2=new SimpleGen<>(new SubClass2());
        SimpleGen<D> ob3=new SimpleGen<>(new D());

        obS.boundedWildcardsArgsTest(obS);
        obS.boundedWildcardsArgsTest(ob1);
        obS.boundedWildcardsArgsTest(ob2);
        // compile error, because D is not a subclass of SuperClass
       // obS.boundedWildcardsArgsTest(ob3);

    }

    public static void exp7(){
        SimpleGen<Integer> x;
        Object o1=new SimpleGen<Integer>(1);
        Object o2=new SimpleGen<Long>(2L);
        SimpleGen<String> o3=new SimpleGen<String>("test");
        Object o4=new SimpleGen<String>("test");

        x=(SimpleGen<Integer>) o1;
        x.showType();

        x= (SimpleGen<Integer>) o2;
        x.showType();

        // this generate compile error, uncompilable type
        //x= (SimpleGen<Integer>) o3;


        x= (SimpleGen<Integer>) o4;
        x.showType();
    }

    public static void exp8(){
        SimpleGen<SuperClass> obS=new SimpleGen<>(new SuperClass());
        SimpleGen<SubClass1> ob1=new SimpleGen<>(new SubClass1());
        SimpleGen<SubClass2> ob2=new SimpleGen<>(new SubClass2());
        SimpleGen<D> ob3=new SimpleGen<>(new D());

        //These two works, because ObS is the super class of ob1
        obS.lowerBoundWildArgsTest(obS);
        obS.lowerBoundWildArgsTest(ob1);

        // These two compile error, because ob2,ob3 is not the super class of ob1
       // obS.lowerBoundWildArgsTest(ob2);
       // obS.lowerBoundWildArgsTest(ob3);
    }

    public static void exp9(){
        Integer nums1[] ={1,2,3,4,5};
        Integer nums2[] ={1,2,3,4,5};
        Integer nums3[] ={1,2,8,4,5};
        Integer nums4[] ={1,2,3,4,5,6};

        // check the static import
        System.out.println(" nums1 equals nums2: "+ arraysEqual(nums1,nums2));

        System.out.println(" nums1 equals nums3: "+ arraysEqual(nums1,nums3));

        System.out.println(" nums1 equals nums4: "+ arraysEqual(nums1,nums4));

        System.out.println(" nums1 equals nums1: "+ arraysEqual(nums1,nums1));

    }

    public static void exp10(){
        double arg=8.8;
        //autoboxing change double to Double
        GenericConstructorDemo gcDemo= new GenericConstructorDemo(8.8);
        System.out.println("Summation of "+arg+" is "+gcDemo.getSum());
    }

    public static void exp11(){
        Integer[] x={1,2,3,4,5};
        MyArray<Integer> myArray=new MyArray<>(x);
        Integer element1=3;
        Integer element2=12;
        System.out.println("My array contains the following elements: "+ Arrays.toString(x));
        System.out.println("Element "+ element1+ " is in My array: "+ myArray.contains(element1));
        System.out.println("Element "+ element2+ " is in My array: "+ myArray.contains(element2));
    }

    // demonstrate raw type
    public static void exp12(){
        // normal instance of a SimpleGen
        SimpleGen<String> strOb=new SimpleGen<>("Test");
        SimpleGen<Double> dOb=new SimpleGen<>(8.8);

        // declare a raw which does not contain any type argument for the generic type
        SimpleGen rawType=new SimpleGen(8);

        // You can notice, the getObject method does not return an Integer type, it returns an Object type.
        Object ob = rawType.getObject();

        // If we want a int , we need to do the cast
        int i=(int)rawType.getObject();
        System.out.println("Value of rawType in Integer : "+i);
        // no more type safety check, it will pass at compile time and generate error in runtime.
        strOb=rawType;
       // String str=strOb.getObject(); // run-time error

        // no more type safety check, but it works
        rawType=dOb;
        double d=(double) rawType.getObject();
        System.out.println("Double d value: "+d);

    }

    public static void exp13(){
        TwoGen<Integer,String> ob1=new TwoGen<>(88,"test");
        if(ob1.isSame(new TwoGen<>(88,"test"))) System.out.println("they are the same");
    }

    public static void exp14(){
        var strOb1=new SimpleGen<String>("test");

        // String type is inferred by parameter "test", when we call getObject, it returns String, unlike raw type
        // returns Object.
        var strOb2=new SimpleGen<>("test");
        String str = strOb1.getObject();
    }

    public static void exp15(){
        Integer nums[]={1,2,3,4,5};
        GenericRestriction<Integer> iOb=new GenericRestriction<>(50,nums);

        // can't create an array of type-specific generic references.
        // GenericRestriction<Integer> iObList[]=new GenericRestriction<Integer>[10];

        // wildcard type list is ok
        GenericRestriction<?> obList[]=new GenericRestriction<?>[10];
    }
}
