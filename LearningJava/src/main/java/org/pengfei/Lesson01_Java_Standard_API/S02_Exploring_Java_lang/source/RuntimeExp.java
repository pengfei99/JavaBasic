package org.pengfei.Lesson01_Java_Standard_API.S02_Exploring_Java_lang.source;

import java.io.IOException;

public class RuntimeExp {
    public static void exp1(){
        Runtime runtime = Runtime.getRuntime();
        long mem1, mem2;
        Integer[] someValues= new Integer[1000];

        // get total memory of the current JVM
        System.out.println("Total memory is: "+ runtime.totalMemory());

        mem1=runtime.freeMemory();
        System.out.println("Initial free memory: "+mem1);

        // start the garbage collection
        runtime.gc();
        // get the available memory after GC.
        mem1=runtime.freeMemory();
        System.out.println("Free memory after garbage collection: "+mem1);

        //populate the Integer object array
        for(int i=0; i<1000; i++){
            someValues[i]=Integer.valueOf(i);
        }

        // get the available memory after populating the array
        mem2=runtime.freeMemory();
        System.out.println("Free memory after populating the array: "+mem2);
        System.out.println("Memory used by the integer array: "+(mem1-mem2));

        // empty the array and do a new GC
        for(int i=0; i<1000;i++){
            someValues[i]=null;
        }
        runtime.gc();

        // get available memory after deleting value and gc
        mem2=runtime.freeMemory();
        System.out.println("Total memory is: "+ runtime.totalMemory());
        System.out.println("Free memory after empty the array and GC: "+mem2);
        System.out.println("Memory used by other things after GC: "+(mem1-mem2));

    }

    public static void exp2(){
        Runtime r=Runtime.getRuntime();
        Process p=null;

        try{
            // call terminator
            p= r.exec("terminator");
            p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Terminator returned: "+ p.exitValue());

    }

    public static void exp3(){
        Runtime.Version ver = Runtime.version();

        // Display individual counters
        System.out.println("Feature release counter: "+ver.feature());
        System.out.println("Interim release counter: "+ver.interim());
        System.out.println("Update release counter: "+ver.update());
        System.out.println("Patch release counter: "+ver.patch());

        //you can obtain the build number, if present, by calling build().
        System.out.println("build number: : "+ver.build());

    }
}
