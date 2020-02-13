package org.pengfei.Lesson01_Java_Standard_API.S04_Exploring_Java_io.source.serialization;

import java.io.*;

public class SerializationExp {
    public static void exp1(String filePath){
        // create an ObjectOutputStream base on a fileOutputStream
        try(ObjectOutputStream objOut=new ObjectOutputStream(new FileOutputStream(filePath))){
            MySerializedClass testObj=new MySerializedClass("haha",88,180.00);
            System.out.println("Object to be serialized: "+testObj);

            //serialized the object
            objOut.writeObject(testObj);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp2(String filePath){
        try(ObjectInputStream objIn=new ObjectInputStream(new FileInputStream(filePath))) {
            //deserialized the object, we do a explicit cast
            MySerializedClass testObj = (MySerializedClass) objIn.readObject();

            // we can use auto inference of variable type, it works too
            // var testObj=objIn.readObject();
            System.out.println("Deserialized object: "+ testObj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
