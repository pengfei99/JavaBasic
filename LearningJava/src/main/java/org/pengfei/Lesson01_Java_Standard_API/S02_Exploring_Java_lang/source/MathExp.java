package org.pengfei.Lesson01_Java_Standard_API.S02_Exploring_Java_lang.source;

public class MathExp {

    public static void exp1(){
        double theta=120.0;
        System.out.println(theta+ " degrees is "+ Math.toRadians(theta)+ " radians.");

        theta=1.312;
        System.out.println(theta+" radians is "+Math.toDegrees(theta)+" degrees");
    }
}
