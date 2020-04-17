package org.pengfei.Lesson03_Java_web.S01_Java_JSON.source;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class MyCarSerializer extends StdSerializer<Car> {

    protected MyCarSerializer(Class<Car> t) {
        super(t);
    }

    /*
    * Notice that the second parameter passed to the serialize() method is a Jackson JsonGenerator instance. You can
    * use this instance to serialize the object - in this case a Car object.
    * */
    public void serialize(Car car, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("producer", car.getBrand());
        jsonGenerator.writeNumberField("doorCount", car.getDoors());
        jsonGenerator.writeEndObject();
    }
}
