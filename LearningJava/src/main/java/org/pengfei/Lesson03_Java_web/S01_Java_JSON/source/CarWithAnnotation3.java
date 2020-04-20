package org.pengfei.Lesson03_Java_web.S01_Java_JSON.source;



import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class CarWithAnnotation3 {

    private final String myCarBrand;

    private final int myCarDoors;

    @JsonCreator
    public CarWithAnnotation3(@JsonProperty("brand") String myCarBrand,  @JsonProperty("doors") int myCarDoors) {
        this.myCarBrand = myCarBrand;
        this.myCarDoors = myCarDoors;
    }

    public String toString() {
        return "MyCarBrand: " + this.myCarBrand + ", MyCarDoors: " + this.myCarDoors;
    }
}
