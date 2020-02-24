package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.executor;

/* It computes the length of the hypotenuse of a right triangle given the length of its sides */

import java.util.concurrent.Callable;

public class Hypotenuse implements Callable<Double> {
    private final double side1,side2;

    public Hypotenuse(double side1,double side2){
        this.side1=side1;
        this.side2=side2;
    }
    /*a2+b2=c2 in a right triangle*/
    @Override
    public Double call() throws Exception {

        return Math.sqrt((Math.pow(side1,2))+Math.pow(side2,2));
    }
}
