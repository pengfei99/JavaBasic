package org.pengfei.Lesson00_Java_Basics.S00_Fundamentals.source;


import java.util.ArrayList;
import java.util.List;

public class Car {
    private String name;
    private String engine;

    public static int numberOfCars;
    /* We can't put a static block which calls a static field before the static field declaration*/
    /*static {
        engineList.add("V4");
    }*/

    public static List<String> engineList=new ArrayList<>();

    static {
        engineList.add("V4");
        engineList.add("V6");
        engineList.add("V8");
    }

    /* We can have multiple static blocks*/
    static {

        engineList.add("V12");
        engineList.add("V24");
    }

    public Car(String name, String engine){
        this.name=name;
        this.engine=engine;
        numberOfCars++;
    }

    public static int getNumberOfCars(){
        return numberOfCars;
    }

    /** This method is supposed to be shared across all instances of the Car class.*/
    public static void setNumberOfCars(int numberOfCars){
        Car.numberOfCars=numberOfCars;
    }

    public static void main(String args[]){
        Car car1= new Car("Toto", "V12");
        Car car2= new Car("titi","v100");
        Car car3=new Car("tata",Car.engineList.get(1));

        // static field can only be accessed via Class name
        System.out.println(Car.numberOfCars);
        // We must use a getter to access field inside an object, you can noticed car1 and car2 share the same
        // numberOfCars value
        // You can notice, when we call a static method with object reference, we get a warning
        System.out.println(car1.getNumberOfCars());
        System.out.println(car2.getNumberOfCars());

        // reset the numberOfCars to 0
        Car.setNumberOfCars(0);
        System.out.println(Car.numberOfCars);

        // get the car3 engine name
        System.out.println(car3.engine);

    }
}
