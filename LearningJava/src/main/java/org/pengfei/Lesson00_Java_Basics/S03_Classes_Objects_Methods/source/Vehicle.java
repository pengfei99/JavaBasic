package org.pengfei.Lesson00_Java_Basics.S03_Classes_Objects_Methods.source;

public class Vehicle {
   public int maxPassengers;
   public int maxFuel;
   public int mpg;

    public Vehicle(int maxPassengers, int maxFuel, int mpg){
        this.maxPassengers=maxPassengers;
        this.maxFuel=maxFuel;
        this.mpg=mpg;
    }

    public int getMaxRange(){
        return maxFuel*mpg;
    }
}
