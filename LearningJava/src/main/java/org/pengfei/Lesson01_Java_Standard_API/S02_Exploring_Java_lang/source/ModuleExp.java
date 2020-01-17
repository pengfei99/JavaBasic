package org.pengfei.Lesson01_Java_Standard_API.S02_Exploring_Java_lang.source;

public class ModuleExp {
    public static void exp1(){
        Module myMod=ModuleExp.class.getModule();

        System.out.println(" Module name is: "+myMod.getName());
        System.out.println("Package: ");
        for(String p: myMod.getPackages()){
            System.out.println(p);
        }

    }
}
