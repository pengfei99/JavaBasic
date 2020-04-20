package org.pengfei.Lesson03_Java_web.S01_Java_JSON.source;

import com.fasterxml.jackson.annotation.JsonValue;

public class CarWithAnnotation8 {

    String brand=null;
    int doors=0;

    public CarWithAnnotation8(String brand, int doors) {
        this.brand = brand;
        this.doors = doors;
    }

    //This annotation indicates use this method to generate the json file, not the default getter and setter.
    //Note the string returned by this method is not a valid json object.
    @JsonValue
    public String toJson(){
        return "brand :"  + this.brand+ ", doors:"  + this.doors ;
    }
}
