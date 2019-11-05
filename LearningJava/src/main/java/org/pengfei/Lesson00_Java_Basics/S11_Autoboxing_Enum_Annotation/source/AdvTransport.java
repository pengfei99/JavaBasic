package org.pengfei.Lesson00_Java_Basics.S11_Autoboxing_Enum_Annotation.source;

public enum AdvTransport {
    // the enumeration constant declaration must be on the first line
    // When an enumeration contains other members, the enumeration list must end in a semicolon.
    CAR(88),TRUCK(66),AIRPLANE(900), TRAIN(70),BOAT(25);

    // add an instance variable
    private int speedLimit;

    // add an constructor
    AdvTransport(int speedLimit){
        this.speedLimit=speedLimit;
    }

    // add a method
    public int getSpeedLimit(){
        return this.speedLimit;
    }


}
