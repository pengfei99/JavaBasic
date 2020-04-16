package org.pengfei.Lesson03_Java_web.S01_Java_JSON.source;

public class Car {

    private String brand = null;
    private int doors = 0;

    //When marsherlling with jackson, we can't have constructor in the mapping object.

    /*public Car(String brand, int doors) {
        this.brand = brand;
        this.doors = doors;
    }*/

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getDoors() {
        return this.doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public String toString(){
        return "brand: "+this.brand+", doors: "+this.doors;
    }
}
