package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source.sender_receiver;

public class Sender implements Runnable {
    private String threadName;
    private DataPackets channel;
    private String[] messages;
    private Thread thread;


    private Sender(String threadName, DataPackets channel,String[] messages){
    this.threadName=threadName;
    this.channel=channel;
    this.messages=messages;
    thread=new Thread(this,threadName);
    }

    public static void createAndRun(String threadName, DataPackets channel, String[] messages){
        Sender sender = new Sender(threadName, channel,messages);
        sender.thread.start();
    }

    @Override
    public void run() {


        for(String packet:messages){
            try {
                System.out.println("Sender: "+threadName+" sends data "+packet);
                channel.send(packet);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Sender: "+threadName+" ends");
    }
}
