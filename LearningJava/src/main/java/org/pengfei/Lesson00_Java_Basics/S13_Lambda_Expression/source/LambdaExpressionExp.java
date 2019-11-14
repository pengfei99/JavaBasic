package org.pengfei.Lesson00_Java_Basics.S13_Lambda_Expression.source;

import org.pengfei.Lesson00_Java_Basics.S12_Generics.source.GenericConstructorDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Predicate;

import static java.lang.Math.*;

public class LambdaExpressionExp {

    public static void exp1(){
        // create a reference to a MyValue instance
        MyValue myValue;

        // use a LE in an assignment context, this works because, LE takes no parameter and return double
        myValue=()->88.8;

        // we can also write all in one line
        MyValue myValue1=()->88.8;

        System.out.println("First constant value: "+myValue.getValue());
        System.out.println("Second constant value: "+myValue1.getValue());
    }

    public static void exp2(double arg){
        // Use a LE with one parameter
        MyParamValue myValue=(n)-> 1.0/n;
        // We call the getValue with one arg, this arg will be passed to n in LE
        System.out.println("Reciprocal of "+arg+" is "+myValue.getValue(arg));
    }

    /** Implement the FI NumericTest with three different version of LE. */
    public static void exp3(int arg1, int arg2){

        // 1st LE, if we can't express all logic in one line, we can use {} to build a block.
        // We can use multiple parameters in LE. They must be separated by ,(comma).
        // In the block, we use return to return a value
        NumericTest isFactor=(int a, int b)->{if ((a % b)==0) return true; else return false;};
        System.out.println("4 can be evenly divided by 2 is "+isFactor.test(4,2));

        // 2nd LE, If the right side is an expression, the type of the expression must be the return type of the FI.
        NumericTest lessThan=(a,b)->a<b?true:false;
        System.out.println(arg1+" is less than "+arg2+ " is "+lessThan.test(arg1,arg2));

        // 3rd LE
        NumericTest absEqual=(a,b)->abs(a)==abs(b);
        int argAbs1=-8, argAbs2=4;
        System.out.println(argAbs1+" absolute value is equal to "+argAbs2+" is "+absEqual.test(argAbs1,argAbs2));
    }

    /** Implement the FI StringTest with two different version of LE*/
    public static void exp4(){
        String test="toto";
        String arg2="abcdef";
        String arg1="abcdefghi";
        // These two LE determines if b is part of a
        StringTest isIn=(a,b)->a.indexOf(b)!=-1;

        StringTest contains=(a,b)->b.contains(a);

        System.out.println(arg1+" is a part of "+arg2+ " is : "+isIn.test(arg1,arg2));

        System.out.println(test+" is a part of "+arg2+ " is : "+isIn.test(test,arg2));
    }

    public static void exp5(){
        // Notice the block must ends with ;
        NumericFunc smallestFac=(n)->{
            int result=1;
             //Get absolute value of n
            n=n<0?-n:n;
            for(int i=2;i<=n/i;i++)
                if((n%i)==0){
                    result=i;
                    break;
                }
            return result;
            };

        int n=12;
        int m=-11;
        System.out.println("Smallest factor of "+n +" is "+ smallestFac.func(n));
        System.out.println("Smallest factor of "+m +" is "+ smallestFac.func(m));
    }

    public static void exp6(){
        // this LE determines if one integer is a factor of another
        GenericTest<Integer> isFactor = (n, d)-> (n % d)==0;

        if(isFactor.test(10,2)) System.out.println("2 is a factor of 10");
        System.out.println();

        // this LE check if b is a part of a
        GenericTest<String> isIn=(a,b)->a.contains(b);
        String arg2="abcdef";
        String arg1="abcdefghi";
        System.out.println(arg2+" is a part of "+arg1+ " is : "+isIn.test(arg1,arg2));

    }


    public static void exp7(){
        String input="Lambda expression Expand Java";
        String output;

        /** In the first scenario, we use a LE to instantiate FI StringFunc and pass the reference to the
         * private utility method changeStr*/
        // Instantiate a lambda expression that reverses the contents of a string
        StringFunc sf=(a)->{
          String result="";
          for(int i=a.length()-1;i>=0;i--) result+=a.charAt(i);
          return result;
        };

        output=changeStr(sf,input);
        System.out.println(input +" has been reversed: "+ output);
        /** In the second scenario, we write directly the LE as argument of the method changeStr*/

        output=changeStr((a)->a.replace(' ','_'),input);
        System.out.println("Space in \""+input +"\" has been replaced by _: \""+ output+"\"");

    }

    // this method use a FI/LE as argument
    private static String changeStr(StringFunc sf,String s){
        return sf.func(s);
    }


    /** variable capture*/
    public static void exp8(){
        // A local variable in a upper scope which enclose the LE can be captured by LE.
        int num=10;

        NumericFunc myFunc=(a)-> {
            // local variable v inside the LE, has no restriction, it's not visible in the upper scope of LE.
            // we read num value, it's ok.
            int v=num+a;

            /* we try to modify num, it's illegal, compilation error, which says num must be final or effective
            * final (never modify the value, even without the keyword final)*/
           // num++;
            return v;
        };

        // we can not change the num value, when we use it in a LE once. Because variables captured by a LE turned the
        // variable into final or effective final.
        //num++;
    }

    /** throw exception in LE*/
    public static void exp9(){
        ExceptionDemo readJava=(input)->{
            BufferedReader br=new BufferedReader(new InputStreamReader(input));
            System.out.println("Enter Java");
            String str=br.readLine();
            if(str.equals("Java")) return true;
            else return false;
        };
       boolean result=false;
        try {
            result=readJava.compareInput(System.in);
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("Have you entered Java: "+result );
    }

    public static void exp10(){
        Double[] nums={1.0,2.0,3d,4d,5d,6d,7d,8d};
        System.out.println("Before square transformation: "+ Arrays.toString(nums));
        // the parameter in the LE, can't be the same as other variables in the same name space.
        ArrayParamExp<Double> square=(n)->{
          for(int i=0;i<n.length;i++) n[i]= Math.sqrt(n[i]);
        };
        // call the FI, which is instantiated by the above LE
       square.transform(nums);
        System.out.println("After square transformation: "+ Arrays.toString(nums));


        // We can declare the LE parameter with explicit type, but we gain nothing.
        ArrayParamExp<Double> power=(Double[] n)->{
            for(int i=0;i<n.length;i++) n[i]= Math.pow(n[i],2.0);
        };
         power.transform(nums);
        System.out.println("After square transformation: "+ Arrays.toString(nums));
    }

    public static void exp11(){
         // isPrime method (implementation of IntPredicate) is passed to numTest by using method reference
        int n=17;
        //Notice, we must start with classname then the method name
        boolean r1 = numTest(LambdaExpressionExp::isPrime, n);
        System.out.println(n+ " is Prime "+ r1);

        int m=12;
        boolean r2 = numTest(LambdaExpressionExp::isEven, m);
        System.out.println(m+ " is Even "+ r2);

        boolean r3 = numTest(LambdaExpressionExp::isPositive, n);
        System.out.println(n+ " is Positive "+ r3);

        // one line definition is also legal
        System.out.println(m+ " is Even "+ numTest(LambdaExpressionExp::isEven, m));

    }

    /******************* Utility method for exp11 **************************/

    /* The three method below is three different implementation of interface IntPredicate*/
    private static boolean isPrime(int n){
        if(n<2) return false;
        for(int i=2; i<=n/i;i++){
            if((n%i)==0);
            return false;
        }
        return true;
    }

    private static boolean isEven(int n){
        return (n%2)==0;
    }

    private static boolean isPositive(int n){
        return n>0;
    }
    // This method uses IntPredicate interface as parameter, so we can instantiate this method with
    // all implementation of the interface.
    private static boolean numTest(IntPredicate p, int v){
        return p.test(v);
    }
    /********************************* End ********************************/

    public static void exp12(){
        // create two instance of the MyIntNum class
        MyIntNum myNum1=new MyIntNum(12);
        MyIntNum myNum2=new MyIntNum(16);

        // use method reference to implement the FI IntPredicate with object myNum1
        IntPredicate ip=myNum1::isFactor;
        int n=3;
        System.out.println(n +" is a factor of "+ myNum1.getNumber()+" is : "+ip.test(n));

        // change the implementation to myNum2
        ip=myNum2::isFactor;
        System.out.println(n +" is a factor of "+ myNum2.getNumber()+" is : "+ip.test(n));
    }

    public static void exp13(){
        // create two instance of the MyIntNum class
        MyIntNum myNum1=new MyIntNum(12);
        MyIntNum myNum2=new MyIntNum(16);

        MyIntNumPredicate inp=MyIntNum::isFactor;
        int n=3;
        boolean r1=inp.test(myNum1,n);
        System.out.println(n +" is a factor of "+ myNum1.getNumber()+" is : "+r1);

        boolean r2=inp.test(myNum2,n);
        System.out.println(n +" is a factor of "+ myNum2.getNumber()+" is : "+r2);
    }

    /** generic in method reference*/
    public static void exp14(){
        // create an implementation of interface NumberPredicate by using method reference
        NumberPredicate<Integer> np=LambdaExpressionExp::isEqual;
        int m=1,n=1;
        System.out.println(m+ " is equal to "+n+" is : "+np.test(n,m));

    }

    private static <T> boolean isEqual(T x, T y){
        return x==y;
    }

    public static void exp15(){
        /* Create a reference to the ConstructorRefDemoClass constructor. Because func() in MyFunc takes an argument,
        * new refers to the parameterized constructor in ConstructorRefDemoClass not the default
        * */
        ConstructorRefDemo crd=ConstructorRefDemoClass::new;
        // create an object of ConstructorRefDemoClass by using the constructor reference
        // remember the crd(constructor reference) is an instance of FI ConstructorRefDemo

        ConstructorRefDemoClass constructorRefDemoClassObj = crd.func("Testing");

        // Use the created object of ConstructorRefDemoClass
        System.out.println("str in my object is "+ constructorRefDemoClassObj.getStr());

    }


    public static void exp16(){
        // create an constructor reference which constructs an array of the target class object
        ArrayConstructorRef acr=ConstructorRefDemoClass[]::new;

        //the following creates an array of ConstructorRefDemoClass objects, the array length is 3.
        //Notice the argument of the FI is no longer taken as the argument of the constructor.
        //It's taken as the length of the array.
        ConstructorRefDemoClass objList[]= acr.func(3);

        //Print array content before value initialization. returns null pointer exception.
        //It means, acr.func only create a reference of array of ConstructorRefDemoClass. It contains
        // zero element.
        /*for(var obj:objList){
            System.out.println(obj.getStr());
        }*/
        // As the array contains zero element. we need to create the object and gives each object an initial value.
        for(int i=0;i<objList.length;i++){
            objList[i]= new ConstructorRefDemoClass("Number "+i+" element");
        }

        for(var obj:objList){
            System.out.println(obj.getStr());
        }
    }

    public static void exp17(){
        //Create an constructor reference to an array of Thread
        GenericConstructorRef<Thread> genericConstructorRef=Thread[]::new;

        //Create an reference of array of threads.
        Thread[] threads=genericConstructorRef.func(5);

        for(int i=0;i<threads.length;i++){
            threads[i]=new Thread(new MyThread("Thread #"+i));
            threads[i].start();
           /* try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: "+e);
            }*/
        }


    }

    public static void exp18(){
        // Use LE to implement the predefined FI Predicate
        Predicate<Integer> isEven=(n)->(n%2)==0;

        // Use static method reference to implement the FI Predicate
        Predicate<Integer> isPositive=LambdaExpressionExp::isPositive;

        int n=4,m=-5;

        System.out.println(n+" is Even is: "+isEven.test(n));

        System.out.println(m+" is Positive is: "+isPositive.test(m));

    }
}
