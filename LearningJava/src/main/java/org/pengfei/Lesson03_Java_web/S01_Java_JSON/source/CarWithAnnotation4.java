package org.pengfei.Lesson03_Java_web.S01_Java_JSON.source;



import com.fasterxml.jackson.annotation.JacksonInject;


public class CarWithAnnotation4 {

     String brand;

    int doors;

    //Indicate this field value will be injected by program not from json source file
    @JacksonInject
    String owner;

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
        return "brand: " + this.brand + ", doors: " + this.doors+", owner: "+owner;
    }
}
