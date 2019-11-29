package org.pengfei.Lesson00_Java_Basics.S14_Modules.source.mymodapp.appsrc.appfuncs.appfuncs.simplefuncs;

//package appfuncs.simplefuncs;

public class SimpleMathFuncs{

    // check if a is a factor of b
    public static boolean isFactor(int a,int b){
        if((b%a)==0) return true;
        else return false;
    }

    // return the smallest positive common factor of a and b
    public static int lcf(int a, int b){
        a=Math.abs(a);
        b=Math.abs(b);
        int min = a<b?a:b;
        for(int i=2;i<=min/2;i++){
            if(isFactor(i,a)&& isFactor(i,b)) return i;
        }
        return 1;
    }

    // return the largest positive common factor of a and b
    public static int gcf(int a, int b){
        a=Math.abs(a);
        b=Math.abs(b);
        int min = a<b?a:b;
        for(int i=min/2;i>=2;i--){
            if(isFactor(i,a)&& isFactor(i,b)) return i;
        }
        return 1;
    }
}
