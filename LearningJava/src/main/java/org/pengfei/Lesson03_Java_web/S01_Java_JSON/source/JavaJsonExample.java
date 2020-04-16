package org.pengfei.Lesson03_Java_web.S01_Java_JSON.source;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class JavaJsonExample {
    public static void exp1() {
        // Read a json file
        String sourcePath = "/home/pliu/IdeaProjects/JavaBasic/LearningJava/src/main/java/org/pengfei/Lesson03_Java_web/S01_Java_JSON/source/data/car.json";
        StringBuilder jsonStr = null;
        try (var br = new BufferedReader(new FileReader(sourcePath))) {
            jsonStr = new StringBuilder();
            String tmp;
            while ((tmp = br.readLine()) != null) jsonStr.append(tmp);

            System.out.println(jsonStr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Map the json file to a java object by using objectMapper*/
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = null;
        try {
            car = objectMapper.readValue(jsonStr.toString(), Car.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        /* Check the car object's attributes*/
        System.out.println(car.toString());
    }

    public static void exp2() {
        ObjectMapper objectMapper = new ObjectMapper();

        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        Car car = null;
        try {
            car = objectMapper.readValue(carJson, Car.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(car.toString());
    }

    public static void exp3() {
        ObjectMapper objectMapper = new ObjectMapper();
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        StringReader stringReader = new StringReader(carJson);
        Car car = null;
        try {
            car = objectMapper.readValue(stringReader, Car.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(car.toString());
    }

    public static void exp4() {
        ObjectMapper objectMapper = new ObjectMapper();
        String sourcePath = "/home/pliu/IdeaProjects/JavaBasic/LearningJava/src/main/java/org/pengfei/Lesson03_Java_web/S01_Java_JSON/source/data/car.json";
        File jsonFile = new File(sourcePath);

        Car car = null;
        try {
            car = objectMapper.readValue(jsonFile, Car.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(car.toString());

    }

    public static void exp5() {
        ObjectMapper objectMapper = new ObjectMapper();
        //here we use a local file url. It can be a web url.
        String urlStr = "file:/home/pliu/IdeaProjects/JavaBasic/LearningJava/src/main/java/org/pengfei/Lesson03_Java_web/S01_Java_JSON/source/data/car.json";

        try {
            URL url = new URL(urlStr);
            Car car = objectMapper.readValue(url, Car.class);
            System.out.println(car.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp6() {
        ObjectMapper objectMapper = new ObjectMapper();
        String sourcePath = "/home/pliu/IdeaProjects/JavaBasic/LearningJava/src/main/java/org/pengfei/Lesson03_Java_web/S01_Java_JSON/source/data/car.json";
        try {
            FileInputStream fin = new FileInputStream(sourcePath);
            Car car = objectMapper.readValue(fin, Car.class);
            System.out.println(car.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp7() {
        ObjectMapper objectMapper = new ObjectMapper();
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        try {
            byte[] jsonBytes = carJson.getBytes("UTF-8");
            Car car = objectMapper.readValue(jsonBytes, Car.class);
            System.out.println(car.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void exp8() {
        ObjectMapper objectMapper = new ObjectMapper();
        String sourcePath = "/home/pliu/IdeaProjects/JavaBasic/LearningJava/src/main/java/org/pengfei/Lesson03_Java_web/S01_Java_JSON/source/data/a_lot_of_cars.json";
        try {
            FileInputStream fin = new FileInputStream(sourcePath);
            // Array is supported by default.
            Car[] cars = objectMapper.readValue(fin, Car[].class);
            for (Car car : cars) System.out.println(car.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp9() {
        ObjectMapper objectMapper = new ObjectMapper();
        String sourcePath = "/home/pliu/IdeaProjects/JavaBasic/LearningJava/src/main/java/org/pengfei/Lesson03_Java_web/S01_Java_JSON/source/data/a_lot_of_cars.json";
        try {
            File file = new File(sourcePath);
            // note, if we want to get a list, in readValue method, we need to specify the type reference.
            List<Car> cars = objectMapper.readValue(file, new TypeReference<List<Car>>() {
            });
            for (Car car : cars) System.out.println(car.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp10() {
        ObjectMapper objectMapper = new ObjectMapper();
        // one car
        String sourcePath = "/home/pliu/IdeaProjects/JavaBasic/LearningJava/src/main/java/org/pengfei/Lesson03_Java_web/S01_Java_JSON/source/data/car.json";

        // two cars will fail
        // String sourcePath = "/home/pliu/IdeaProjects/JavaBasic/LearningJava/src/main/java/org/pengfei/Lesson03_Java_web/S01_Java_JSON/source/data/a_lot_of_cars.json";

        try {
            File file = new File(sourcePath);
            // note, if we want to get a list, in readValue method, we need to specify the type reference.
            Map<String, Object> carMaps = objectMapper.readValue(file, new TypeReference<Map<String, Object>>() {
            });

            carMaps.forEach((k, v) -> System.out.println("key: " + k + ", value: " + v));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp11() {
        ObjectMapper objectMapper = new ObjectMapper();
        // one car
        String sourcePath = "/home/pliu/IdeaProjects/JavaBasic/LearningJava/src/main/java/org/pengfei/Lesson03_Java_web/S01_Java_JSON/source/data/complexCar.json";
        File file = new File(sourcePath);

        // This configuration tells jackson it's ok if not all fields are matched.
        // Try to comment this, and see what happens
        objectMapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            Car car = objectMapper.readValue(file, Car.class);
            System.out.println(car.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void exp12() {
        ObjectMapper objectMapper = new ObjectMapper();
        // one car
        String sourcePath = "/home/pliu/IdeaProjects/JavaBasic/LearningJava/src/main/java/org/pengfei/Lesson03_Java_web/S01_Java_JSON/source/data/carWithNull.json";
        File file = new File(sourcePath);

        // This configuration tells jackson to throw exception if fields of primitive types contains null value.
        // Try to comment this, and see what happens
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
        try {
            Car car = objectMapper.readValue(file, Car.class);
            System.out.println(car.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
