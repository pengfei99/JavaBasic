package org.pengfei.Lesson03_Java_web.S01_Java_JSON.source;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonAnnotationExample {

    /** Igor some fields*/
    public static void exp1(){

        ObjectMapper objectMapper=new ObjectMapper();
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5," +
                        "  \"engine\" : { \"model\" : \"s5\"," +
                        "                 \"power\" : 140} " +
                        "}";
        CarWithAnnotation1 car = null;
        try {
            car = objectMapper.readValue(carJson, CarWithAnnotation1.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(car.toString());
    }

    /** Deserialization annotations*/
    public static void exp2(){

        ObjectMapper objectMapper=new ObjectMapper();
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5," +
                        "  \"engine\" : { \"model\" : \"s5\"," +
                        "                 \"power\" : 140} " +
                        "}";
        CarWithAnnotation2 car = null;
        try {
            car = objectMapper.readValue(carJson, CarWithAnnotation2.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(car.toString());
    }

    /** Constructor mapping*/
    public static void exp3(){

        ObjectMapper objectMapper=new ObjectMapper();
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5" + "}";
        CarWithAnnotation3 car = null;
        try {
            car = objectMapper.readValue(carJson, CarWithAnnotation3.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(car.toString());
    }

    /** inject fields which are not in the json file*/
    public static void exp4(){
        ObjectMapper objectMapper=new ObjectMapper();
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5" + "}";
        CarWithAnnotation4 car = null;
        InjectableValues injectOwner = new InjectableValues.Std().addValue(String.class, "foobar");
        try {
            car = objectMapper.reader(injectOwner).forType(CarWithAnnotation4.class).readValue(carJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(car.toString());
    }

    /** use a custom deserializer to change value read from json file*/
    public static void exp5(){
        ObjectMapper objectMapper=new ObjectMapper();
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5," +
                        "  \"broken\" : 0 "+ "}";
        CarWithAnnotation5 car = null;

        try {
            car = objectMapper.readValue(carJson,CarWithAnnotation5.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(car.toString());
    }

    public static void exp6() {
        ObjectMapper objectMapper=new ObjectMapper();
        CarWithAnnotation6 car=new CarWithAnnotation6();
        car.setBrand("BMW");
        car.setDoors(4);
        // engine will not be exported in the json file because its null
        car.setEngine(null);

        try {
            String jsonStr = objectMapper.writeValueAsString(car);
            System.out.println(car.toString());
            System.out.println(jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    public static void exp7() {
        ObjectMapper objectMapper=new ObjectMapper();
        CarWithAnnotation7 car=new CarWithAnnotation7();
        car.setMyCarBrand("BMW");
        car.setMyCarDoors(4);
        // set raw value for driver and address, note driver value is not valid json, but address is.
        car.setDriver("****");
        car.setAddress("{ \"street\" : \"Wall Street\", \"no\":1}");
        // engine will not be exported in the json file because its null
        car.set("owner","foobar");
        car.set("date","01/01/2012");

        try {
            String jsonStr = objectMapper.writeValueAsString(car);
            System.out.println(car.toString());
            System.out.println(jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    public static void exp8(){
        ObjectMapper objectMapper=new ObjectMapper();
        CarWithAnnotation8 car=new CarWithAnnotation8("BMW",5);

        try {
            String jsonStr=objectMapper.writeValueAsString(car);
            System.out.println(jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void exp9(){
        ObjectMapper objectMapper=new ObjectMapper();
        CarWithAnnotation9 car=new CarWithAnnotation9();
        car.setBrand("BMW");
        car.setDoors(5);
        car.broken=false;

        try {
            String jsonStr=objectMapper.writeValueAsString(car);
            System.out.println(jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}


