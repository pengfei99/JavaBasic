package org.pengfei.Lesson03_Java_web.S01_Java_JSON.source;

import com.fasterxml.jackson.annotation.*;

// map private field to json file
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY )
//ignoreProperties ignores a list of fields
@JsonIgnoreProperties({"doors"})
public class CarWithAnnotation1 {
    // When we igor a field the java object will not read this field from the json file. It will use the default value
    // of the java object.

    // this ignore a single field
    //@JsonIgnore
    private String brand = null;

    int doors = 0;

    Engine engine=null;

    @JsonIgnoreType
    public static class Engine{
        static String model=null;
        static int power=0;

        public static String getModel() {
            return model;
        }

        public static void setModel(String model) {
            Engine.model = model;
        }

        public static int getPower() {
            return power;
        }

        public static void setPower(int power) {
            Engine.power = power;
        }
    }

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

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String toString() {
        return "brand: " + this.brand + ", doors: " + this.doors+", Engine:{"+"model: "+Engine.model+", power: "+Engine.power+"}";
    }
}
