package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source.sender_receiver;

public class SenderReceiverMain {
    public static void main(String[] args){
        DataPackets channel=new DataPackets();
        String packets[] = {
                "First packet",
                "Second packet",
                "Third packet",
                "Fourth packet",
                "END"
        };
        Sender.createAndRun("Sender1",channel,packets);
        Receiver.createAndRun("Receiver1", channel);


    }
}
