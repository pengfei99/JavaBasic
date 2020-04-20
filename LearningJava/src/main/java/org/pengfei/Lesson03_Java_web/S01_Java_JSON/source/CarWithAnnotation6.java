package org.pengfei.Lesson03_Java_web.S01_Java_JSON.source;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;



//@JsonInclude(JsonInclude.Include.NON_EMPTY)
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class CarWithAnnotation6 {

    private String brand = null;

    int doors = 0;

    Engine engine=null;


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
        return "brand: " + this.brand + ", doors: " + this.doors+", Engine:{"+"model: "+ Engine.model+", power: "+ Engine.power+"}";
    }
}
