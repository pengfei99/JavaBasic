package org.pengfei.Lesson01_Java_Standard_API.S02_Exploring_Java_lang.source;

public class SystemExp {

    public static void exp1(){
        long start, end,nstart, nend;
        System.out.println("Timing a for loop from 0 to 100,000,000");
        start=System.currentTimeMillis();
        nstart=System.nanoTime();
        // just loop, no instruction inside the loop
        for(long i=1;i<100_000_000;i++);

        end=System.currentTimeMillis();
        nend=System.nanoTime();
        System.out.println("Loop elapsed time: "+(end-start)+" milli second");
        System.out.println("Loop elapsed time: "+(nend-nstart)+" nano second");
    }

    public static void exp2(){
        byte a[]={65,66,67,68,69,70,71,72,73,74};
        byte b[]={77,77,77,77,77,77,77,77,77,77};

        //String is an object, not primitive type, so no wrapper object needed, no valueOf constructor needed too.
        System.out.println("array a: "+ new String(a));
        System.out.println("array b: "+ new String(b));

        //copy a to b
        System.arraycopy(a,0,b,0,a.length);
        System.out.println("array a: "+ new String(a));
        System.out.println("array b: "+ new String(b));

        // shift a's element down by one except a[0], a[i]=a[i-1]
        System.arraycopy(a,0,a,1,a.length-1);
        System.out.println("array a: "+ new String(a));
        System.out.println("array b: "+ new String(b));

        // shift b's element up by one except the last element, b[i]=a[i+1]
        System.arraycopy(b,1,b,0,b.length-1);
        System.out.println("array a: "+ new String(a));
        System.out.println("array b: "+ new String(b));


    }

    public static void exp3(){
        System.out.println("java.library.path: "+System.getProperty("java.library.path"));
        System.out.println("java.class.path: "+System.getProperty("java.class.path"));
        System.out.println("os.name: "+System.getProperty("os.name"));
        System.out.println("user.home: "+System.getProperty("user.home"));
    }

}
