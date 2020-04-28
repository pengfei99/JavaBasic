package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source.sender_receiver;

public class Receiver extends Thread {
    private String threadName;
    private DataPackets data;

    private Receiver(String threadName, DataPackets data){
        super(threadName);
        this.threadName=threadName;
        this.data=data;
    }

    public static void createAndRun(String threadName, DataPackets data){
        Receiver receiver=new Receiver(threadName, data);
        receiver.run();
    }

    public void run(){
        String message="END";
        do{
            try {
                message=data.receive();
                // do not print end, finish the loop
                if(message.equals("END")) break;
                System.out.println("Receiver: "+threadName+" receives "+message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while(!message.equals("END"));

        System.out.println("Receiver: "+threadName+" ends");
    }
}
