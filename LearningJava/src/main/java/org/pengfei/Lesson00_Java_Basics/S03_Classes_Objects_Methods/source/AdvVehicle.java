package org.pengfei.Lesson00_Java_Basics.S03_Classes_Objects_Methods.source;

public class AdvVehicle {

    private int maxPassengers;
    private int maxFuel;
    private int mpg;

    public AdvVehicle(int maxPassengers, int maxFuel, int mpg){
        this.maxPassengers=maxPassengers;
        this.maxFuel=maxFuel;
        this.mpg=mpg;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public int getMaxFuel() {
        return maxFuel;
    }

    public void setMaxFuel(int maxFuel) {
        this.maxFuel = maxFuel;
    }

    public int getMpg() {
        return mpg;
    }

    public void setMpg(int mpg) {
        this.mpg = mpg;
    }

    public int getMaxRange(){
        return maxFuel*mpg;
    }

    public double fuelConsumption(int miles){
        return (double)miles/mpg;
    }
}
