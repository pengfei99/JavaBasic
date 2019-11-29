package org.pengfei.Lesson00_Java_Basics.S14_Modules.source.mymodapp.appsrc.appstart.appstart.mymodappdemo;

//import appfuncs.simplefuncs.SimpleMathFuncs;

import org.pengfei.Lesson00_Java_Basics.S14_Modules.source.mymodapp.appsrc.appfuncs.appfuncs.simplefuncs.SimpleMathFuncs;

public class MyModAppDemo{
    public static void main(String[] args){
        if(SimpleMathFuncs.isFactor(2,10))System.out.println("2 is a factor of 10");
        int a=35;
        int b=105;

        System.out.println("Smallest common factor of "+a+" and "+b+" is "+ SimpleMathFuncs.lcf(a,b));
        System.out.println("Largest common factor of "+a+" and "+b+" is "+ SimpleMathFuncs.gcf(a,b));
    }
}
