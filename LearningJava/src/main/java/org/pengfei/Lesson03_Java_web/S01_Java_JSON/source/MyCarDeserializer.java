package org.pengfei.Lesson03_Java_web.S01_Java_JSON.source;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class MyCarDeserializer extends StdDeserializer<Car> {
    public MyCarDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Car deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Car car = new Car();
        while(!parser.isClosed()){
            JsonToken jsonToken = parser.nextToken();

            if(JsonToken.FIELD_NAME.equals(jsonToken)){
                String fieldName = parser.getCurrentName();
                System.out.println(fieldName);

                jsonToken = parser.nextToken();

                if(fieldName.equals("brand")){
                    car.setBrand("Custom deserialize "+parser.getValueAsString());
                } else if (fieldName.equals("doors")){
                    car.setDoors(parser.getValueAsInt()+10);
                }
            }
        }
        return car;
    }
}
