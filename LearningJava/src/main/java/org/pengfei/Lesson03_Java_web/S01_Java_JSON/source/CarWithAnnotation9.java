package org.pengfei.Lesson03_Java_web.S01_Java_JSON.source;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;


public class CarWithAnnotation9 {

     String brand;

    int doors;

    @JsonSerialize(using = MyBooleanSerializer.class)
    boolean broken;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public String toString() {
        return "brand: " + this.brand + ", doors: " + this.doors+", broken: "+broken;
    }



}
