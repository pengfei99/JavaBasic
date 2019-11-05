package org.pengfei.Lesson00_Java_Basics.S11_Autoboxing_Enum_Annotation.source;

public class TrafficLight extends Thread{
    private TrafficLightColor currentColor;
    private boolean suspended=false;
    private boolean stopped=false;
    private boolean changed=false;

    private TrafficLight(String name,TrafficLightColor tlColor){
        super(name);
        currentColor=tlColor;
    }

    private TrafficLight(String name){
        this(name,TrafficLightColor.RED);
    }

    // factory method for two arg constructor
    public static TrafficLight createWithColorAndStart(String name,TrafficLightColor initColor){
        TrafficLight tl=new TrafficLight(name,initColor);
        tl.start();
        return tl;
    }

    // factory method for one arg constructor
    public static TrafficLight createWithoutColorAndStart(String name){
        TrafficLight tl=new TrafficLight(name);
        tl.start();
        return tl;
    }

    //entry point of the thread
    public void run(){
        while(!stopped){
            //business logic
            try{
                switch (currentColor){
                    //when current color is green, wait for 10 secs
                    case GREEN: Thread.sleep(10000); break;
                    case YELLOW: Thread.sleep(2000); break; // when yellow, wait for 2 secs
                    case RED: Thread.sleep(12000); break; // when red, wait for 12 secs
                }
            } catch (InterruptedException e) {
                System.out.println("Traffic light thread is interrupted "+e);
            }
            // after wait, change current color to next link color green->yellow->red->green
            changeColor();

            // thread suspend, stop control logic
            synchronized (this){
            // suspend thread
            while(suspended){
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("Traffic light thread is interrupted "+e);
                }
            }
            // stop thread
            if(stopped) break;
            }
        }
    }

    // The state of the traffic light is the shared resource, all methods which access it must be synchronized.
    private synchronized void changeColor() {
        switch (currentColor){
            // if current color is red, change it to green
            case RED: currentColor=TrafficLightColor.GREEN; System.out.println("Change color to Green");break;
            case YELLOW: currentColor=TrafficLightColor.RED; System.out.println("Change color to RED"); break; // if yellow, change it to red
            case GREEN: currentColor=TrafficLightColor.YELLOW;System.out.println("Change color to Yellow"); break; //
        }
        changed =true;
        notify();
    }

    // Need to check this method, why it does not create a deadlock?
    public synchronized void waitForChange(){
       try{
           //suspend thread, if no changes
         while (!changed) wait();
         changed=false;
       } catch (InterruptedException e) {
           System.out.println(e);
       }
    }

    public TrafficLightColor getCurrentColor(){
        return currentColor;
    }


    /***************************************Thread control method ****************************************/
    public synchronized void suspendThread(){
        suspended=true;
    }
    public synchronized void resumeThread(){
        suspended=false;
        // need to notify waits thread
        notify();
    }
    public synchronized void stopThread(){
        stopped=true;

        //also stop the suspended thread
        suspended=false;
        notify();
    }
}
