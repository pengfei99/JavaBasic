package org.pengfei.Lesson00_Java_Basics.S05_Advance_Classes_Methods.source;

public class RecursionExp {

   public static int factRecursive(int n){
        int result;
        if(n==1) return 1;
        result=factRecursive(n-1)*n;
        System.out.println("Result :"+ result);
        return result;

    }

   public static int factIteration(int n){
        int i, result=1;
        for(i=1;i<=n;i++){
            result *=i;
        }
       System.out.println("Result :"+ result);
       return result;
    }
}
