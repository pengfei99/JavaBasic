package org.pengfei.Lesson00_Java_Basics.S10_MultiThread.source.sender_receiver;

public class DataPackets {
    private String data;
    // True if receiver should wait
    // False if sender should wait
    private boolean transfer = true;

    public synchronized void send(String packet) throws InterruptedException {
        // receiving packet, sender Thread wait
        while(!transfer){
            // This wait() will suspend all threads who try to call the send method
            wait();
            // avoid loop so quick
            Thread.sleep(100);
        }

        // receiving packet finished, sender Thread awake. Here we use notifyAll to wake all threads that waits on the
        // DataPackets lock.

        notify();
        data=packet;
        //System.out.println("Sending data: "+packet);
        //finish sending data, change transfer to false
        transfer=false;

    }

    public synchronized String receive() throws InterruptedException {
        //sending packet, receiver waits
        while(transfer){
            // This wait() will suspend all threads who try to call the receive method
            wait();
        }
        // sending finished, wake receiver
        notifyAll();
        // we need to change transfer to true before return
        transfer=true;
        return data;
    }
}
