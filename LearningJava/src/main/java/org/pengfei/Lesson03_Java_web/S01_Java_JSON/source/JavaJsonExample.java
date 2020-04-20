package org.pengfei.Lesson03_Java_web.S01_Java_JSON.source;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.print.DocFlavor;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
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

    public static void exp13() {
        ObjectMapper objectMapper = new ObjectMapper();
        String sourcePath = "/home/pliu/IdeaProjects/JavaBasic/LearningJava/src/main/java/org/pengfei/Lesson03_Java_web/S01_Java_JSON/source/data/car.json";
        File file = new File(sourcePath);
        SimpleModule module =
                new SimpleModule("MyCarDeserializer", new Version(1, 0, 0, null, null, null));

        module.addDeserializer(Car.class, new MyCarDeserializer(Car.class));
        objectMapper.registerModule(module);

        try {
            Car car = objectMapper.readValue(file, Car.class);
            System.out.println(car.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp14() {
        ObjectMapper objMapper = new ObjectMapper();

        Car car = new Car();
        car.setBrand("BMW");
        car.setDoors(3);

        try {
            objMapper.writeValue(new FileOutputStream("/tmp/CarOut.json"), car);
            System.out.println("Done");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void exp15() {
        ObjectMapper objMapper = new ObjectMapper();

        Car car = new Car();
        car.setBrand("BMW");
        car.setDoors(3);

        try {
            String carString = objMapper.writeValueAsString(car);
            System.out.println(carString);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void exp16() {
        ObjectMapper objMapper = new ObjectMapper();

        Car car = new Car();
        car.setBrand("BMW");
        car.setDoors(3);

        try {
            byte[] carByte = objMapper.writeValueAsBytes(car);
            System.out.println(new String(carByte));
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void exp17() {
        // create my customize serializer
        MyCarSerializer serializer = new MyCarSerializer(Car.class);
        ObjectMapper objectMapper = new ObjectMapper();

        // create a module
        SimpleModule module =
                new SimpleModule("MyCarSerializer", new Version(1, 0, 0, null, null, null));
        // add my serializer to the module
        module.addSerializer(Car.class, serializer);

        // register module to objectMapper
        objectMapper.registerModule(module);

        Car car = new Car();
        car.setBrand("Mercedes");
        car.setDoors(5);

        try {
            String carJson = objectMapper.writeValueAsString(car);
            System.out.println(carJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void exp18() {
        ObjectMapper mapper = new ObjectMapper();

        CarWithDate car = new CarWithDate();
        car.setBrand("BMW");
        car.setDoors(5);
        car.setProductionDate(new Date());

        try {
            //the default mapper will convert date object to long
            String carJson = mapper.writeValueAsString(car);
            System.out.println(carJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void exp19() {
        ObjectMapper mapper = new ObjectMapper();

        CarWithDate car = new CarWithDate();
        car.setBrand("BMW");
        car.setDoors(5);
        car.setProductionDate(new Date());

        //set object mapper date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(dateFormat);

        try {
            //the default mapper will convert date object to long
            String carJson = mapper.writeValueAsString(car);
            System.out.println(carJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    public static void exp20() {
        ObjectMapper objectMapper = new ObjectMapper();
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

        try {
            // get the car object represented as JsonNode by using readValue. Note we use JsonNode.class to replace
            // Car.class
            JsonNode carNode = objectMapper.readValue(carJson, JsonNode.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void exp21() {
        ObjectMapper objectMapper = new ObjectMapper();
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

        try {
            // The readTree method will always return a JsonNode object, so no need to specify JsonNode.class
            JsonNode carNode = objectMapper.readTree(carJson);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void exp22() {
        ObjectMapper objectMapper = new ObjectMapper();
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

        try {
            // get a jsonNode object
            JsonNode carNode = objectMapper.readTree(carJson);

            // Write json node to a file
            objectMapper.writeValue(new FileOutputStream("/tmp/jsonNodeOutput.json"), carNode);
            System.out.println("Write file done");
            String jsonNodeStr = objectMapper.writeValueAsString(carNode);
            System.out.println("jsonNodeStr: " + jsonNodeStr);
            byte[] jsonNodeByte = objectMapper.writeValueAsBytes(carNode);
            System.out.println("jsonNodeByte: " + jsonNodeByte);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp23() {
        ObjectMapper mapper = new ObjectMapper();
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5," +
                        "  \"owners\" : [\"John\", \"Jack\", \"Jill\"]," +
                        "  \"engine\" : { \"model\" : \"s5\"," +
                        "                 \"power\" : 140} " +
                        "}";

        try {
            JsonNode car = mapper.readValue(carJson, JsonNode.class);
            //get all the first level fields
            JsonNode brand = car.get("brand");
            JsonNode doors = car.get("doors");
            JsonNode owners = car.get("owners");
            JsonNode engine = car.get("engine");

            /** Step1: For the simple field we can directly convert it to its primitive type*/
            String brandValue = brand.asText();
            System.out.println("Brand: " + brandValue);
            // you can give a default value, if the json file value is missing
            int doorsValue = doors.asInt(0);
            System.out.println("Doors: " + doorsValue);

            /** Step2: if the field is an array, you can use get(index) to get each value of the array*/
            int i = 0;
            do {
                JsonNode owner;
                if ((owner = owners.get(i)) != null) {
                    String ownerValue = owner.asText();
                    System.out.println("Owner " + i + ": " + ownerValue);
                    i++;
                }
            } while (owners.get(i) != null);

            /** Step3: if the field is a child object. you can consider it like a json object, and use get(fieldName) to
             * get all fields. Then repeat step1, step2, step3*/
            System.out.println("Engine: ");
            JsonNode engine_model = engine.get("model");
            System.out.println("engine_model: " + engine_model.asText());
            JsonNode engine_power = engine.get("power");
            // note If we use the asInt() method on a string field, a 0 will be returned no exception thrown.
            System.out.println("engine_power: " + engine_power.asInt());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void exp24() {
        ObjectMapper objectMapper = new ObjectMapper();
        String sourcePath = "/home/pliu/IdeaProjects/JavaBasic/LearningJava/src/main/java/org/pengfei/Lesson03_Java_web/S01_Java_JSON/source/data/finalCar.json";
        File file = new File(sourcePath);
        try {
            JsonNode car = objectMapper.readTree(file);

            // we can get engine power directly without using engine field
            JsonNode engine_power = car.at("/engine/power");
            System.out.println("engine_power: " + engine_power.asInt());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp25() {
        ObjectMapper objectMapper = new ObjectMapper();
        String sourcePath = "/home/pliu/IdeaProjects/JavaBasic/LearningJava/src/main/java/org/pengfei/Lesson03_Java_web/S01_Java_JSON/source/data/finalCar.json";

        try {
            JsonNode car = objectMapper.readTree(new FileInputStream(sourcePath));

            // we can convert the field to any type, and you can give a default value in case of failure
            JsonNode doors = car.get("doors");
            String doorStr = doors.asText("Default");
            int doorInt = doors.asInt(0);
            double doorDouble = doors.asDouble(0.0);
            long doorLong = doors.asLong(0L);

            // if you want to see the default value, try to modify the value of field doors to null.
            System.out.println("doorStr: " + doorStr);
            System.out.println("doorInt: " + doorInt);
            System.out.println("doorDouble: " + doorDouble);
            System.out.println("doorLong: " + doorLong);

            // get a field that does not exist in the json file, a null object is returned. you can't call asInt(), etc
            // on it. Try to uncomment the following two lines and see what happens.
            // JsonNode noSuchField=car.get("toto");
            // System.out.println(noSuchField.asText() );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp26() {
        ObjectMapper objectMapper = new ObjectMapper();
        String urlStr = "file:/home/pliu/IdeaProjects/JavaBasic/LearningJava/src/main/java/org/pengfei/Lesson03_Java_web/S01_Java_JSON/source/data/finalCar.json";

        try {
            URL url = new URL(urlStr);
            JsonNode car = objectMapper.readTree(url);
            JsonNode doors = car.get("doors");
            // try to change doors field into null in file finalCar.json.
            if (doors.isNull()) System.out.println("Field value is null");
            else System.out.println(doors.asText());
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp27() {

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonSource = "/home/pliu/IdeaProjects/JavaBasic/LearningJava/src/main/java/org/pengfei/Lesson03_Java_web/S01_Java_JSON/source/data/finalCar.json";

        try {
            JsonNode car = objectMapper.readTree(new File(jsonSource));
            // call the static method traverse
            JavaJsonExample.traverse("Car", car);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp28() {
        ObjectMapper objectMapper = new ObjectMapper();

        //create a new ObjectNode
        ObjectNode newCar = objectMapper.createObjectNode();

        /* We can add simple fields by using put method */
        newCar.put("brand", "BMW");
        newCar.put("doors", 5);

        /* We can add array fields by using putArray method */
        newCar.putArray("owners");

        // To assign the array filed value, we need to create an ArrayNode object
        ArrayNode owners = objectMapper.createArrayNode();
        owners.add("John");
        owners.add("Jack");
        owners.add("Jill");
        // We assign the array node object to the field.
        newCar.set("owners", owners);

        /*we can add object fields by using putObject method*/
        newCar.putObject("engine");
        ObjectNode engine = objectMapper.createObjectNode();
        engine.put("model", "s10");
        engine.put("power", 15);
        newCar.set("engine", engine);

        /* we can add an array of objects*/
        newCar.putArray("Users");

        //create two users object
        ObjectNode user1 = objectMapper.createObjectNode();
        user1.put("name", "pliu");
        user1.put("age", 18);

        ObjectNode user2 = objectMapper.createObjectNode();
        user2.put("name", "haha");
        user2.put("age", 38);

        //create an array object for users
        ArrayNode users = objectMapper.createArrayNode();
        users.add(user1);
        users.add(user2);

        newCar.set("Users", users);


        // check the created json object
        try {
            System.out.println("Before removing doors");
            System.out.println(objectMapper.writeValueAsString(newCar));


            newCar.remove("doors");
            System.out.println("After removing doors");
            System.out.println(objectMapper.writeValueAsString(newCar));

            // get all field name and values
            System.out.println("Get all field name and values");
            Iterator<String> fieldNames = newCar.fieldNames();
            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode field = newCar.get(fieldName);
                //note that, if the field is an array or object, we can't print it by using asText()
                System.out.println("field_name: " + fieldName + ", value: " + field.asText("Complex type"));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void exp29() {
        ObjectMapper objectMapper = new ObjectMapper();

        Car car = new Car();
        car.setBrand("BMW");
        car.setDoors(8);

        JsonNode carJsonNode = objectMapper.valueToTree(car);

        try {
            Car carObj = objectMapper.treeToValue(carJsonNode, Car.class);
            System.out.println("The jsonNode object: " + objectMapper.writeValueAsString(carJsonNode));
            System.out.println("The java car object: " + carObj.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    public static void exp30() {
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

        JsonFactory factory = new JsonFactory();
        JsonParser parser = null;
        try {
            // we use json factory to create a json parser
            parser = factory.createParser(carJson);

            // Note if no more token is found, parser.isClosed() will return false
            while (!parser.isClosed()) {
                JsonToken parsedToken = parser.nextToken();
                System.out.println("jsonToken = " + parsedToken);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void exp31() {
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

        JsonFactory factory = new JsonFactory();
        JsonParser parser = null;
        try {
            parser = factory.createParser(carJson);
            while (!parser.isClosed()) {
                // if the token is a field name, then we get the name of the field.
                JsonToken parsedToken = parser.nextToken();
                if (parsedToken != null && parsedToken.equals(JsonToken.FIELD_NAME)) {
                    String fieldName = parser.getCurrentName();
                    System.out.println("Field name: " + fieldName);

                    // now we get the field value
                    JsonToken valueToken = parser.nextToken();
                    // for different fields, the value type is different. so we need to determine the value is for
                    // which field to assign a correct type.
                    if (fieldName.equals("brand")) {
                        String brandValue = parser.getValueAsString();
                        System.out.println("Field value: " + brandValue);
                    } else if (fieldName.equals("doors")) {
                        int doorsValue = parser.getIntValue();
                        System.out.println("Field value: " + doorsValue);
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void exp32() {
        JsonFactory jsonFactory = new JsonFactory();
        File output = new File("/tmp/generator_export.json");
        try (JsonGenerator jsonGenerator = jsonFactory.createGenerator(output, JsonEncoding.UTF8)) {
            // a json file always starts with a start object, followed by a list of fields and ends with an ending
            // object.
            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField("firstName", "Duke");
            jsonGenerator.writeObjectField("lastName", "Java");
            jsonGenerator.writeObjectField("age", 18);
            jsonGenerator.writeObjectField("streetAddress", "100 Internet Dr");
            jsonGenerator.writeObjectField("city", "JavaTown");
            jsonGenerator.writeObjectField("state", "JA");
            jsonGenerator.writeObjectField("postalCode", "12345");
            // create an array of phone numbers
            jsonGenerator.writeFieldName("phoneNumbers");
            jsonGenerator.writeStartArray();
            // first element is mobile
            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField("Mobile", "111-111-1111");
            jsonGenerator.writeEndObject();

            //second element is home
            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField("Home", "222-222-2222");
            jsonGenerator.writeEndObject();
            jsonGenerator.writeEndArray();

            jsonGenerator.writeFieldName("numbers");
            jsonGenerator.writeStartArray();
            jsonGenerator.writeNumber(1);
            jsonGenerator.writeNumber(2);
            jsonGenerator.writeNumber(3);
            jsonGenerator.writeNumber(4);
            jsonGenerator.writeEndArray();
            jsonGenerator.writeEndObject();

            jsonGenerator.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }

    /**
     * This method and traverse all fields inside a JsonNode. If the root is the root of a json object. It can traverse
     * the whole json object and print all fields and their values.
     *
     * @param root is the root of json node which you want to traverse
     * @return void
     * @author Pengfei liu
     * @version 1.0
     * @since 2020-04-19
     */
    public static void traverse(String rootName, JsonNode root) {

        // if the field is a sub json object, for each field of the object call traverse()
        if (root.isObject()) {
            Iterator<String> fieldNames = root.fieldNames();

            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode fieldValue = root.get(fieldName);
                traverse(rootName + "/" + fieldName, fieldValue);
            }
            // if the field is an array traverse each element with the name of root
        } else if (root.isArray()) {
            ArrayNode arrayNode = (ArrayNode) root;
            for (int i = 0; i < arrayNode.size(); i++) {
                JsonNode arrayElement = arrayNode.get(i);
                traverse(rootName, arrayElement);
            }
            // if the field is a simple primitive type, print it as string.
        } else {
            System.out.println("field_name: " + rootName + ", field_value: " + root.asText());

        }
    }
}