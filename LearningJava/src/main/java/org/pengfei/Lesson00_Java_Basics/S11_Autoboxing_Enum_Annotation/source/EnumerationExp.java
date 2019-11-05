package org.pengfei.Lesson00_Java_Basics.S11_Autoboxing_Enum_Annotation.source;

import java.util.Arrays;

public class EnumerationExp {

    public static void exp1(){
        Transport tp=Transport.AIRPLANE;
        //Compare two enum constants
        System.out.println(tp==Transport.AIRPLANE);
        System.out.println(tp==Transport.BOAT);

        //switch case
        switch (tp){
            case CAR: System.out.println("It's a car"); break;
            case AIRPLANE: System.out.println("It's an airplane"); break;
            case BOAT: System.out.println("It's a boat"); break;
            case TRAIN: System.out.println("It's a train"); break;
            // case Transport.TRUCK: System.out.println("It's a truck"); break;
            default:System.out.println(tp);
        }

        // print enum value
        System.out.println(Transport.AIRPLANE);
    }

    // use method values and valueOf
    public static void exp2(){

        // use values
        System.out.println("Print all Transport constants");
        Transport[] allTrans=Transport.values();
        System.out.println(Arrays.toString(allTrans));


        // use valueOf
        // Notice the valueOf is case sensitive, the String input must be exactly the same as enum constant.
        // Transport tp1=Transport.valueOf("airplane");
        // System.out.println("tp1 contains : "+tp1);
        Transport tp2=Transport.valueOf("AIRPLANE");
        System.out.println("tp2 contains : "+tp2);

    }

    // use constructor, method of enumeration
    public static void exp3(){
        AdvTransport tp=AdvTransport.AIRPLANE;
        System.out.println(tp+ " has speed limit : "+tp.getSpeedLimit() + " miles per hour");

        System.out.println("All transport speed limit are: ");
        for(AdvTransport at:AdvTransport.values()){
            System.out.println(at+ " has speed limit : "+at.getSpeedLimit()+ " miles per hour");
        }
    }

    public static void exp4(){
        //show ordinal values of Transport
        for(Transport tp:Transport.values()){
            System.out.println(tp+ " has ordinal value "+tp.ordinal());
        }
    }

    public static void exp5(){
        /*
        * CAR has ordinal value 0
        * TRUCK has ordinal value 1
        * AIRPLANE has ordinal value 2
        * TRAIN has ordinal value 3
        * BOAT has ordinal value 4
        * */
        Transport tp1=Transport.CAR,tp2=Transport.AIRPLANE,tp3=Transport.CAR;

        if(tp1.compareTo(tp2)<0)
            System.out.println(tp1+ " is before  " +tp2);

        if(tp1.compareTo(tp3)==0)
            System.out.println(tp1+ " equals " +tp3);

        if(tp2.compareTo(tp3)>0)
            System.out.println(tp2+" is after "+tp3 );
    }

    // traffic light demo
    public static void exp6(){
        TrafficLight tl=TrafficLight.createWithColorAndStart("Traffic light #1",TrafficLightColor.GREEN);


  /*  for(int i=0;i<9;i++){
        System.out.println("Current traffic light color is: "+tl.getCurrentColor());
        tl.waitForChange();
    }

    tl.stopThread();*/


        try {
            System.out.println(tl.getName()+" Starting");
            //let the traffic light thread run for 40 secs
            Thread.sleep(40000);

            // suspend the traffic light for 20 secs
            tl.suspendThread();
            System.out.println(tl.getName()+" Suspending");
            Thread.sleep(20000);

            // resume thread, let it run for 40 secs
            tl.resumeThread();
            System.out.println(tl.getName()+" Resuming");
            Thread.sleep(40000);

            // stop thread
            tl.stopThread();
            System.out.println(tl.getName()+" Stopping");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}
