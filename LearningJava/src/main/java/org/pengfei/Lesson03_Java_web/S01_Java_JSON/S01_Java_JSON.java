package org.pengfei.Lesson03_Java_web.S01_Java_JSON;

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

    /****************************************** 3.0 Jackson ObjectMapper ****************************************/
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
    * of the getter and setter methods, and converts the first character of the remaining name to lowercase.
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

    /** 3.4 */

    public static void main(String[] args){
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
    }
}
