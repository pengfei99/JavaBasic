package org.pengfei.Lesson03_Java_web.S01_Java_JSON.source;


import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({"doors", "brand","date","owner"})
public class CarWithAnnotation7 {

    String myCarBrand = null;

    int myCarDoors = 0;

    @JsonRawValue
    String driver="****";

    @JsonRawValue
    String address="";

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    private Map<String, Object> properties = new HashMap<>();

    //This annotation will map all fields of json file which can't find a mapping field in java object.
    @JsonAnySetter
    public void set(String fieldName, Object value) {
        this.properties.put(fieldName, value);
    }

    @JsonAnyGetter
    public Map<String,Object> properties() {
        return this.properties;
    }

    // The json file field name is brand, but java field name is myCarBrand. without this annotation, we will get exception.
    @JsonGetter("brand")
    public String getMyCarBrand() {
        return myCarBrand;
    }

    // The json file field name is brand, but java field name is myCarBrand. without this annotation, we will get exception.
    @JsonSetter("brand")
    public void setMyCarBrand(String myCarBrand) {
        this.myCarBrand = myCarBrand;
    }

    @JsonGetter("doors")
    public int getMyCarDoors() {
        return myCarDoors;
    }

    //try to comment this annotation
    @JsonSetter("doors")
    public void setMyCarDoors(int myCarDoors) {
        this.myCarDoors = myCarDoors;
    }


    public String toString() {

        return "MyCarBrand: " + this.myCarBrand + ", MyCarDoors: " + this.myCarDoors + ", other fields" + properties.toString();
    }
}
