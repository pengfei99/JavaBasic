package org.pengfei.Lesson03_Java_web.S01_Java_JSON;

import org.pengfei.Lesson03_Java_web.S01_Java_JSON.source.JacksonAnnotationExample;
import org.pengfei.Lesson03_Java_web.S01_Java_JSON.source.JavaJsonExample;

public class S01_Java_JSON {
    /****************************************** 1.0 Java JSON Introduction ****************************************/

    /*
    * JSON is short for JavaScript Object Notation. JSON is a popular data exchange format between browsers and web
    * servers because the browsers can parse JSON into JavaScript objects natively. On the server, however, JSON needs
    * to be parsed and generated using JSON APIs. In this section, we focus on the various choices you have for
    * parsing and generating JSON in Java.
    *
    * When JSON became popular, Java did not have a standard JSON parser/generator implementation. Java
    * developers had to rely on open source Java JSON APIs. Since then Java has attempted to address the missing Java
    * JSON API in JSR 353. Keep in mind that JSR 353 is not yet an official standard.
    *
    * Some of the more well-known open source Java JSON APIs are:
    * - Jackson : Jackson is a Java JSON API which is one of the most popular Java JSON APIs. You can find Jackson here:
    *             https://github.com/FasterXML/jackson
    *             Jackson contains 2 different JSON parsers:
    *             -- The "Jackson ObjectMapper" which parses JSON into custom Java objects, or into a Jackson specific
    *                tree structure (tree model).
    *             -- The "Jackson JsonParser" which is Jackson's JSON pull parser, parsing JSON one token at a time.
    *             Jackson also contains two JSON generators:
    *             -- The "Jackson ObjectMapper" which can generate JSON from custom Java objects, or from a Jackson
    *                specific tree structure (tree model).
    *             -- The "Jackson JsonGenerator" which can generate JSON one token at a time.
    * - GSON : GSON is a Java JSON API from Google. That is where the G in GSON comes from. GSON is reasonably flexible,
    *          but the last time I saw a benchmark, Jackson was faster than GSON. Which you choose to use is up to you.
    *          You can find GSON here: https://github.com/google/gson
    * - Boon : Boon is a less known Java JSON API, but it is supposedly the fastest of them all (according to the
    *          last benchmark I saw). Boon is being used as the standard JSON API in Groovy. You can find Boon here:
    *          https://github.com/boonproject/boon Boon's API is very similar to Jackson's (so it is easy to switch).
    *          But, Boon is more than just a Java JSON API. Boon is a general purpose toolkit for working with data
    *          easily. This is handy e.g. inside REST services, file processing apps etc.
    *          Boon contains the following Java JSON parsers:
    *          -- The "Boon ObjectMapper" which can parse JSON into custom objects or Java Maps. Like in Jackson,
    *             the Boon ObjectMapper can also be used to generate JSON from custom Java objects.
    * - JSON.org: JSON.org also has an open source Java JSON API. This was one of the first Java JSON APIs available
    *             out there. It is reasonably easy to use, but not as flexible or fast as the other JSON APIs mentioned
    *             above. You can find JSON.org here: https://github.com/douglascrockford/JSON-java. As the Github
    *             repository also says - this is an old Java JSON API. Don't use it unless your project is already
    *             using it. Otherwise, look for one of the other, more up-to-date options.
    * - JSONP : JSONP is a JSR 353 compliant JSON API for Java. Being JSR 353 compliant means, that if you use the
    *           standard APIs it should be possible to exchange the JSONP implementation with another API in the
    *           future, without breaking your code. You can find JSONP here: https://jsonp.java.net/
    * I would also expect some Java application server vendors to provide JSR 353 compliant JSON APIs in the future
    * (if not already).
    * */

    /****************************************** 2.0 Jackson installation ****************************************/

    /*
    * Jackson consists of one core JAR file (project) and two other JAR files that use the core JAR file. The three
    * JAR files (projects) in the Jackson JSON API are:
    * - Jackson Core
    * - Jackson Annotations
    * - Jackson Databind
    *
    * These projects use each other in that sequence too. Jackson Annotation uses the Jackson Core features, and the
    * Jackson Databind uses Jackson Annotation. Jackson also has a few extra projects for parsing other data formats
    * than JSON. For instance, to read and write CBOR you can add the jackson-dataformat-cbor artifact to your
    * classpath too.
    *
    * In order to "install" Jackson in your Java application you need to add these JAR files to the classpath of your
    * application. There are several ways to do so. I will cover two in this section.
    *
    * Check my wiki page employes:pengfei.liu:java:interview_questions, question 4 shows details of classpath.
    *
    * */

    /** 2.1 Jackson maven dependencies
     * If you are using maven to build your project, you can add the following maven code example to your project
     * pom.xml file.
     *  <properties>
     *     ...
     *     <jackson.version> 2.10.3 </jackson.version>
     *   </properties>
     *  <!-- dependencies for jackson -->
     *     <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core -->
     *     <dependency>
     *       <groupId>com.fasterxml.jackson.core</groupId>
     *       <artifactId>jackson-core</artifactId>
     *       <version>${jackson.version}</version>
     *     </dependency>
     *     <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations -->
     *     <dependency>
     *       <groupId>com.fasterxml.jackson.core</groupId>
     *       <artifactId>jackson-annotations</artifactId>
     *       <version>${jackson.version}</version>
     *     </dependency>
     *
     *     <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
     *     <dependency>
     *       <groupId>com.fasterxml.jackson.core</groupId>
     *       <artifactId>jackson-databind</artifactId>
     *       <version>${jackson.version}</version>
     *     </dependency>
     *
     * Note, that both jackson-annotations and jackson-databind have "transitive dependencies" to jackson-core
     * (and jackson-databind to jackson-annotations). This means, that if you plan to use the jackson-databind project,
     * you just need to include that as dependency in your Maven POM file. Then it will transitively include the other
     * two projects as dependencies. Regardless, I like to add my dependencies explicitly so I can see what I am using
     * (but that's just me).
     *
     * In order to read and write CBOR encoded data with Jackson you will also need to add the Jackson CBOR Maven
     * dependency:
     * <dependency>
     *     <groupId>com.fasterxml.jackson.dataformat</groupId>
     *     <artifactId>jackson-dataformat-cbor</artifactId>
     *     <version>${jackson.version}</version>
     * </dependency>
     *
     * In order to read and write MessagePack encoded data with Jackson you will also need to add the Jackson
     * MessagePack Maven dependency:
     * <dependency>
     *     <groupId>org.msgpack</groupId>
     *     <artifactId>jackson-dataformat-msgpack</artifactId>
     *     <version>${jackson.version}</version>
     * </dependency>
     *
     * In order to read and write YAML encoded data with Jackson you will also need to add the Jackson YAML Maven
     * dependency:
     * <dependency>
     *     <groupId>com.fasterxml.jackson.dataformat</groupId>
     *     <artifactId>jackson-dataformat-yaml</artifactId>
     *     <version>2.9.0</version>
     * </dependency>
     *
     * */

    /** 2.2 Adding the JAR Files Directly to the Classpath
     *
     * Another option is to add the Jackson JAR files directly to the classpath of your application. To do so you must
     * first download the JAR files. You can download the finished JAR files via the central Maven repository. Go to:
     * http://search.maven.org
     *
     * Once the JAR files are downloaded, add them to the classpath of your project. You can do so within your IDE,
     * and / or within the scripts (bash or cmd) you use to run your application.
     *
     * For example java -cp /home/user/myprogram org.mypackage.HelloWorld. This command will run HelloWorld with all
     * the jar file under /myprogram as classpath jar file
     * here -cp means classpath not copy, it sets the path to the packages which are in the classpath.
     * org.mypackage.HelloWorld is the name of the main class of your application.
     *
     * You can also set a global classpath for your user session:
     * export CLASSPATH=$CLASSPATH:/home/user/myprogram
     *
     * */

    /************************************ 3.0 Jackson ObjectMapper deserialization ***********************************/
    /*
    * The Jackson ObjectMapper class (com.fasterxml.jackson.databind.ObjectMapper) is the simplest way to parse JSON
    * with Jackson. The Jackson ObjectMapper can parse JSON from a string, stream or file, and create a Java object or
    * object graph representing the parsed JSON. Parsing JSON into Java objects is also referred to as to deserialize
    * Java objects from JSON.
    *
    * The Jackson ObjectMapper can also create JSON from Java objects. Generating JSON from Java objects is also
    * referred to as to serialize Java objects into JSON.
    *
    * The Jackson Object mapper can parse JSON into objects of classes developed by you, or into objects of the
    * built-in JSON tree model explained later in this tutorial. By the way, the reason it is called ObjectMapper is
    * because it maps JSON into Java Objects (deserialization), or Java Objects into JSON (serialization).
    *
    * Check JavaJsonExample.exp1(); we read a json file and map it to a java object.
    *
    * By default Jackson maps the fields of a JSON object to fields in a Java object by matching the names of the JSON
    * field to the getter and setter methods in the Java object. Jackson removes the "get" and "set" part of the names
    * of the getter and setter methods, and converts the first character of the remaining name to lowercase. As a result,
    * the getter and setter methods of the java mapping object is essential, without them nothing can be mapped.
    *
    * In JavaJsonExample.exp1(), the JSON field named brand matches the Java getter and setter methods called
    * getBrand() and setBrand(). The JSON field named engineNumber would match the getter and setter named
    * getEngineNumber() and setEngineNumber(). If you need to match JSON object fields to Java object fields in a
    * different way, you need to either use a custom serializer and deserializer, or use some of the many Jackson
    * Annotations.
    * */

    /** 3.1 objectMapper.readValue to deserialize
     *
     * In JavaJsonExample.exp1(); we have seen a simple example which objectMapper called readValue method. This methods
     * has many overload versions which takes various types as input.
     *
     * For example:
     * - Read Object From JSON String: check JavaJsonExample.exp2();
     * - Read Object From JSON StringReader: JavaJsonExample.exp3();
     * - Read Object From JSON File: JavaJsonExample.exp4();
     * - Read Object From JSON via URL: JavaJsonExample.exp5();
     * - Read Object From JSON InputStream: JavaJsonExample.exp6();
     * - Read Object From JSON Byte Array: JavaJsonExample.exp7();
     *
     * If the json file contains a group of json objects. You can use the readValue method to return an array or a list
     * of Java objects.
     * For example:
     * - Get an array of objects from a json file: JavaJsonExample.exp8();
     * - Get a list of objects from a json file: JavaJsonExample.exp9();
     *
     * If you do not know ahead of time the exact JSON structure that you will be parsing, you may need to parse it to
     * a Map. Each field in the JSON object will become a key, value pair in the Java Map. Check
     * - JavaJsonExample.exp10();
     *
     * Note this will not work, if the json files has duplicate attribute name, because map does not allow duplicate key.
     * So this is only useful for parsing files which has only one json object in it.
     * */

    /** 3.2 Ignore some json field
     * Sometimes you have more fields in the JSON than you do in the Java object you want to read from the JSON. By
     * default Jackson throws an exception in that case, saying that it does not know field XYZ because it is not
     * found in the Java object.
     *
     * However, sometimes it should be allowed to have more fields in the JSON than in the corresponding Java object.
     * For instance, if you are parsing JSON from a REST service which contains much more data than you need. In that
     * case, Jackson enables you to ignore these extra fields with a Jackson configuration. Check
     * JavaJsonExample.exp11();
     * */

    /** 3.3 Fail on Null JSON Values for Primitive Types
     *
     * It is possible to configure the Jackson ObjectMapper to fail if a JSON string contains a field with its value
     * set to null, for a field which in the corresponding Java object is a primitive type (int, long, float, double etc.).
     * Check JavaJsonExample.exp12(); an exception will be thrown because of null value.
     * */

    /** 3.4 Custom deserializer
     *
     * Sometimes you might want to read a JSON string into a Java object in a way that is different from how the
     * Jackson ObjectMapper does this by default. You can add a custom deserializer to the ObjectMapper which can
     * perform the deserialization as you want it done.
     * Check JavaJsonExample.exp13(); We can control which field of the json file we want to match. We can also set
     * the field values of the generated java object.
     * */

    /************************************* 4.0 Jackson ObjectMapper serialization **********************************/

    /*
    * We can generate json file with a java object. ObjectMapper offers three methods to do such operation:
    * - writeValue(): This method generate a json object and write it to a file. JavaJsonExample.exp14();
    * - writeValueAsString(): This method generate a json object and return it as string. JavaJsonExample.exp15();
    * - writeValueAsBytes(): This method generate a json object and return it as byte. JavaJsonExample.exp16();
    *
    * */

    /** 4.1 Customize serialization
     *
     * Sometimes you want to serialize a Java object to JSON differently than what Jackson does by default. For
     * instance, you might want to use different field names in the JSON than in the Java object, or you might want
     * to leave out certain fields altogether.
     *
     * Jackson enables you to set a custom serializer on the ObjectMapper. This serializer is registered for a certain
     * class, and will then be called whenever the ObjectMapper is asked to serialize a Car object.
     *
     * Check JavaJsonExample.exp17(); we change the output json field name.
     * */

    /** 4.2 Jackson date format
     *
     * By default Jackson will serialize a java.util.Date object to its long value, which is the number of
     * milliseconds since January 1st 1970. However, Jackson also supports formatting dates as strings.
     * In JavaJsonExample.exp18() we use the default ObjectMapper date format
     * In JavaJsonExample.exp19() we create a customize date format.
     * */

    /************************************* 5.0 Jackson JSON Tree Model **********************************/

    /*
    * Jackson has a built-in tree model which can be used to represent a JSON object. Jackson's tree model is useful if
    * you don't know how the JSON you will receive looks, or if you for some reason cannot (or just don't want to)
    * create a class to represent it. The Jackson Tree Model is also useful if you need to manipulate the JSON before
    * using or forwarding it. All of these situations can easily occur in a Data Streaming scenario.
    *
    * The Jackson tree model is represented by the JsonNode class. The Jackson JsonNode class(com.fasterxml.jackson.
    * databind.JsonNode) is Jackson's tree model. You use the Jackson ObjectMapper to parse JSON into
    * a JsonNode tree model, and write a JsonNode out to JSON file or string, just like you would have done with your
    * own class.
    *
    * There are two ways to read json to a JsonNode object:
    * - use readValue(jsonSource, JsonNode.class): the jsonSource can be a string, file, inputStream, url etc. Check
    *               JavaJsonExample.exp20();
    * - use readTree(jsonSource): the jsonSource can be a string, file, inputStream, url etc. Check
    *               JavaJsonExample.exp21();
    *
    * There are three ways to write a JsonNode object just like you do with your own class:
    * - writeValue(): This method generate a json object and write it to a file.
    * - writeValueAsString(): This method generate a json object and return it as string.
    * - writeValueAsBytes(): This method generate a json object and return it as byte.
    *
    * Check JavaJsonExample.exp22();, we use the three above methods to write a JsonNode object
    * */

    /** 5.1 JsonNode vs. ObjectNode
     *
     * The Jackson JsonNode class is immutable. That means, that you cannot actually build an object graph of
     * JsonNode instances directly. Instead, you create an object graph of the JsonNode subclass ObjectNode. Being a
     * subclass of JsonNode you can use the ObjectNode everywhere you can use a JsonNode. You will see how to do
     * build ObjectNode graphs later in this section.
     *
     *
     * */

    /** 5.2 JsonNode fields
     * A json file can has three types of fields
     * - simple fields (field_name: value of primitive types)
     * - array fields(field_name: an array of values of primitive types)
     * - composite fields(field_name: {child_field1, child_field2,etc.})
     *
     * We can get all the above fields by using get("field_name") method. Note that, the get() method always returns a
     * JsonNode to represent the field even for simple fields.
     *
     * Check JavaJsonExample.exp23(); how we retrieve fields and their values.
     *
     * 5.2.1 Get a field by using its path
     * We can also get a field by giving the path of the fields, even if the field is a sub field of a field. Check
     * JavaJsonExample.exp24(); we use the at("field_path") method to get a sub field of a field directly.
     *
     * 5.2.2 Convert JsonNode Field
     * The Jackson JsonNode class contains a set of methods that can convert a field value to another data type. For
     * instance, convert a String field value to a long, or the other way around by using:
     * - asDouble()
     * - asText()
     * - asInt()
     * - asLong().
     *
     * The asDouble(), asInt() and asLong() methods can also take default parameter values, which are returned in case
     * the field you try to get the value from is null.
     *
     * Please note, that if the field is not explicitly set to null in the JSON, but is missing from the JSON, then
     * the call jsonNode.get("fieldName") will return a Java null value, on which you cannot call asInt(),
     * asDouble(), asLong() or asText(). If you try that, you will get a NullPointerException.
     *
     * Check JavaJsonExample.exp25();
     *
     * 5.2.3 Handling Null Field Values
     * There are two ways in which a field inside a JsonNode can be null.
     * 1. If the field is completely missing from the JSON from which the JsonNode was created. The jsonNode object
     *    you get by using objectMapper.get() is a null object. So you need to check if your jsonNode object is null or
     *    not
     * 2. The field exist but the value is null. In this case, your jsonNode object is not null. You can check if the
     *    value is null or not by using isNull() method of the jsonNode object.
     *
     * 5.2.4 Traverse a json node
     *
     * A JsonNode that represents a JSON object or JSON array can be traversed like any other object graph. You do so
     * by iterating its nested fields (or nested elements in case of an array).
     *
     * We write a method called traverse in JavaJsonExample class. In JavaJsonExample.exp27(), we use this method to
     * traverse the finalCar.json file.
     * */



    /** 5.3 Use ObjectNode to edit json node fields
     *
     * As mentioned earlier, the JsonNode class is immutable. So once a JsonNode object created, we cant edit it.
     * To edit a JsonNode object graph you must be able to mutate the JsonNode instances in the graph, e.g. setting
     * property values and child JsonNode instances etc.
     *
     * To overcome the immutable problem, we can use ObjectNode class which is a subclass of JsonNode, and it is mutable.
     * Check JavaJsonExample.exp28(); we create a json object with three types of fields(e.g. simple, array, object).
     *
     * Note the following methods:
     * - put("field_name",field_value): It adds a simple field to the json object
     * - putArray("field_name"): It adds an array field to the json object. The value is set by calling set() method
     * - putObject("field_name"): It adds a object field to the json object. The value is set by calling set() method
     * - remove(): It removes fields from the ObjectNode
     * - fieldNames(): It returns an Iterator that enables you to iterate all the field names of the JsonNode.
     * */

    /********************************* 6.0 Convert JsonNode to java object and vise-versa ********************************/

    /*
    * It is possible to use the Jackson ObjectMapper to convert a Java object to a JsonNode with the JsonNode being a
    * JSON representation of the Java object converted and vise-versa.
    * - ObjectMapper.valueToTree(): It converts a Java object to a JsonNode object.
    * - ObjectMapper.treeToValue(): It converts a JsonNode object to a Java object.
    * Check JavaJsonExample.exp29();
    * */

    /********************************* 7.0 Jackson JsonParser ********************************/

    /*
    * The Jackson JsonParser class is a low level JSON parser. It is similar to the Java StAX parser for XML, except
    * the JsonParser parses JSON and not XML.
    *
    * The Jackson JsonParser works at a lower level than the Jackson ObjectMapper. This makes the JsonParser faster
    * than the ObjectMapper, but also more cumbersome to work with.
    *
    * The way the JsonParser works is by breaking the JSON up into a sequence of tokens which you can iterate one by one.
    * Check JavaJsonExample.exp30(); We use a loop to show all the token returned by the parser. Note if no more token
    * is found, parser.isClosed() will return false
    *
    * Check JavaJsonExample.exp31(), We use the parser to get field name and values. Note, parser can only know it's
    * a field or a value. It does not know the name of the field and the type and value of the field value. So its
    * very complicate to use it to read large json file.
    * */

    /********************************* 8.0 Jackson  JsonGenerator ********************************/

    /*
    * The Jackson JsonGenerator is used to generate JSON from Java objects (or whatever data structure your code
    * generates JSON from).
    *
    * It's harder to use compare to ObjectNode, we must write a lots of code. For example, we need to use method
    * writeStartObject() to indicate the beginning of a json file. But is uses less resource than the ObjectNode.
    *
    * Check JavaJsonExample.exp32(); We write a json file by using JsonGenerator.
    * */

    /********************************* 9.0 Jackson Annotation ********************************/
    /*
    * The Jackson JSON toolkit contains a set of Java annotations which you can use to influence how JSON is read into
    * objects, or what JSON is generated from the objects.
    * */

    /** 9.1 Annotations for read and write fields
     * We have three annotations to ignore fields of a json file
     * - @JsonIgnore: Ignore a single field, apply on top of the java field declaration.
     * - @JsonIgnoreProperties: Ignore a list of field, apply on top of the java mapping class.
     * - @JsonIgnoreType: Ignore a whole type (class) everywhere that type is used.
     * - @JsonAutoDetect: It tells Jackson to include fields which are not public.
     * Check JacksonAnnotationExample.exp1();
     * */

    /** 9.2 Deserialize annotations
     *
     * When we read a json file and create a mapping java object, we can use these annotation to control how the field
     * mapping is done.
     *

     * - @JsonSetter: It tells Jackson that is should match the name of this setter method to a property name in the
     *                JSON data, when reading JSON into objects. This is useful if the property names used internally
     *                in your Java class is not the same as the property name used in the JSON file.
     * - @JsonAnySetter: It instructs Jackson to call the same setter method for all fields that are not already mapped
     *                to a property or setter method in the Java object.
     * - @JsonCreator: It tells Jackson that the Java object has a constructor (a "creator") which can match the
     *                fields of a JSON object to the fields of the Java object. It is useful in situations where
     *                the @JsonSetter annotation cannot be used. For instance, immutable objects do not have any
     *                setter methods, so they need their initial values injected into the constructor.
     * - @JacksonInject: It is used to inject values into the parsed objects, instead of reading those values from
     *                the JSON. For instance, imagine you are downloading person JSON objects from various different
     *                sources, and would like to know what source a given person object came from. The sources may not
     *                themselves contain that information, but you can have Jackson inject that into the Java objects
     *                created from the JSON objects.
     * - @JsonDeserialize: It is used to specify a custom de-serializer class for a given field in a Java object. For
     *                instance, imagine you wanted to optimize the on-the-wire formatting of the boolean values false
     *                and true to 0 and 1.
     * Check JacksonAnnotationExample.exp2(); for JsonSetter and JsonAnySetter example
     * Check JacksonAnnotationExample.exp3();, note we use @JsonProperty("brand") annotation in java constructor to
     * map argument with json file field.
     * Check JacksonAnnotationExample.exp4(); we use @JacksonInject to inject owner filed value into the java object.
     * Check JacksonAnnotationExample.exp5(); we write a MyBooleanDeserializer and use @JsonDeserialize(using =
     * MyBooleanDeserializer.class) to modify the value read from json file.
     * */

    /** 9.2 Serialize annotations
     * These annotations tells how Jackson serializes (writes) Java objects to JSON.
     * - @JsonInclude: It tells Jackson only to include properties under certain circumstances. For instance, that
     *                 properties should only be included if they are
     *                 -- non-null: JsonInclude.Include.NON_NULL
     *                 -- non-empty: JsonInclude.Include.NON_EMPTY
     *                 -- non-default values: JsonInclude.Include.NON_DEFAULT.
     * - @JsonGetter: It is used to tell Jackson that a certain field value should be obtained from calling a getter
     *                method instead of via direct field access.
     * - @JsonAnyGetter: It enables you to use a Map as container for properties that you want to serialize to JSON.
     *                 Note, it flatten the filed in the container as the same level as other field. So no field
     *                 hierarchy is showed.
     * - @JsonPropertyOrder: It can be used to specify in what order the fields of your Java object should be
     *                 serialized into JSON.
     * - @JsonRawValue: It tells Jackson that this property value should written directly as it is to the JSON output.
     *                If the property is a String Jackson would normally have enclosed the value in quotation marks,
     *                but if annotated with the @JsonRawValue property Jackson won't do that.
     * - @JsonValue: It tells Jackson that Jackson should not attempt to serialize the object itself by using setters and
     *               getters, but rather call a method on the object which serializes the object to a JSON string. Note
     *               that Jackson will escape any quotation marks inside the String returned by the custom serialization,
     *               so you cannot return e.g. a full JSON object. For that you should use @JsonRawValue instead.
     * - @JsonSerialize: It is used to specify a custom serializer for a field in a Java object
     *
     * Check JacksonAnnotationExample.exp6() and CarWithAnnotation6 class, We use @JsonInclude to control which filed to
     * export to the json file.
     * Check JacksonAnnotationExample.exp7(); We use @JsonGetter to map Java object field to a json file with different
     * names. We use @JsonAnyGetter to export the field list in properties. We use @JsonPropertyOrder to change order.
     * Note we can't change field order inside the properties, because they are not java object fields. They are
     * elements of a map. We use @JsonRawValue to let driver print raw value not as string. This is of course invalid
     * JSON, so why would you want that? A usefull example is the address property contained a JSON string then that
     * JSON string would be serialized into the final JSON object as part of the JSON object structure, and not just
     * into a string in the address field in the JSON object.
     * Check JacksonAnnotationExample.exp8(); We use @JsonValue to call a method toJson() to serialize the Car object.
     * Check JacksonAnnotationExample.exp9(); We use a custom serializer to modify the boolean output to 0 or 1.
     *
     */
    public static void main(String[] args){
        /** deserialize*/
        /* deserialize: convert json file to a java object */
        //JavaJsonExample.exp1();
        //JavaJsonExample.exp2();
       // JavaJsonExample.exp3();
       // JavaJsonExample.exp4();
       // JavaJsonExample.exp5();
       // JavaJsonExample.exp6();
       // JavaJsonExample.exp7();


        /* deserialize: convert json file to an array of java objects*/
        // JavaJsonExample.exp8();

        /* deserialize: convert json file to a list of java objects*/
       // JavaJsonExample.exp9();

        /* deserialize: convert json file to a java map*/
        // JavaJsonExample.exp10();

        /*Ignore some json fields*/
        // JavaJsonExample.exp11();

        /* fail on null value*/
       // JavaJsonExample.exp12();
       // JavaJsonExample.exp13();

        /** Serialization */
       // JavaJsonExample.exp14();
        // JavaJsonExample.exp15();
       // JavaJsonExample.exp16();
      //  JavaJsonExample.exp17();

        /* jackson date format*/
       // JavaJsonExample.exp18();
       // JavaJsonExample.exp19();

        /** Json tree model*/
        /* read json file to jsonNode*/
      //  JavaJsonExample.exp20();
       // JavaJsonExample.exp21();

        /* write jsonNode to json file*/
       // JavaJsonExample.exp22();

        /*read jsonNode field*/
       // JavaJsonExample.exp23();
       // JavaJsonExample.exp24();

        /* convert field type*/
       // JavaJsonExample.exp25();

        /* check null field*/
       // JavaJsonExample.exp26();

        /* traverse a json node*/
       // JavaJsonExample.exp27();

        /* edit json object with ObjectNode*/
      //  JavaJsonExample.exp28();

        /** Convert jsonNode object to java object and vise-versa*/
       // JavaJsonExample.exp29();

        /** json parser*/
        // JavaJsonExample.exp30();
        // JavaJsonExample.exp31();

        /** json generator*/
       //   JavaJsonExample.exp32();

        /** jackson annotation*/

        /* read, write annotations*/
       // JacksonAnnotationExample.exp1();

        /* read annotations (Deserialization)*/
       // JacksonAnnotationExample.exp2();
       // JacksonAnnotationExample.exp3();
       // JacksonAnnotationExample.exp4();
       // JacksonAnnotationExample.exp5();

        /* write annotations (Serialization)*/
       // JacksonAnnotationExample.exp6();
        // JacksonAnnotationExample.exp7();
        //JacksonAnnotationExample.exp8();
        JacksonAnnotationExample.exp9();
    }
}
