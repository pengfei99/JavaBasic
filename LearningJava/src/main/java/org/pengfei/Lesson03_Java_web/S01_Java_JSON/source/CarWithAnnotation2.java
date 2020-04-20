package org.pengfei.Lesson03_Java_web.S01_Java_JSON.source;


import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.HashMap;
import java.util.Map;

public class CarWithAnnotation2 {

     String myCarBrand = null;

    int myCarDoors = 0;

    private Map<String, Object> properties = new HashMap<>();

    //This annotation will map all fields of json file which can't find a mapping field in java object.
    @JsonAnySetter
    public void set(String fieldName, Object value){
        this.properties.put(fieldName, value);
    }

    public Object get(String fieldName){
        return this.properties.get(fieldName);
    }



    public String getMyCarBrand() {
        return myCarBrand;
    }
// The json file field name is brand, but java field name is myCarBrand. without this annotation, we will get exception.
    @JsonSetter("brand")
    public void setMyCarBrand(String myCarBrand) {
        this.myCarBrand = myCarBrand;
    }

    public int getMyCarDoors() {
        return myCarDoors;
    }

    //try to comment this annotation
    @JsonSetter("doors")
    public void setMyCarDoors(int myCarDoors) {
        this.myCarDoors = myCarDoors;
    }


    public String toString() {

        return "MyCarBrand: " + this.myCarBrand + ", MyCarDoors: " + this.myCarDoors+", other fields"+properties.toString();
    }
}
