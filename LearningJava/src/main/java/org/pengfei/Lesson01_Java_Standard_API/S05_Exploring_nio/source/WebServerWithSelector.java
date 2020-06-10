package org.pengfei.Lesson01_Java_Standard_API.S05_Exploring_nio.source;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class WebServerWithSelector implements Runnable{
    private String ip;
    private int port;
    private boolean close=false;
    private ServerSocketChannel ssc;
    private Selector selector;
    private ByteBuffer readBuff = null;
    private ByteBuffer writeBuff = null;

    public WebServerWithSelector(String ip, int port) {
        this.ip = ip;
        this.port = port;

        readBuff = ByteBuffer.allocate(1024);
        writeBuff = ByteBuffer.allocate(128);
        writeBuff.put("Message received".getBytes());
        writeBuff.flip();


        try {
            //create server socket channel
            ssc=ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(ip,port));

            //register the channel to the selector
            ssc.configureBlocking(false);
            //create an instance of selector
            selector=Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int sessionId=0;
        while(!close){
            int registeredChannelNum=0;
            //get registered channel number
            try {
                 registeredChannelNum=selector.select();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // if no registered channel, go to the next loop cycle.
            if(registeredChannelNum==0) continue;

            // if find channel, get selectionKey
            Set<SelectionKey> keys = selector.selectedKeys();
            // here we use iterator instead of the for(SelectionKey key:keys) because we want do remove().
            Iterator<SelectionKey> keysIterator=keys.iterator();
            while (keysIterator.hasNext()){

                SelectionKey key=keysIterator.next();
                keysIterator.remove();

                // if the channel is acceptable, then its a ServerSocketChannel, we need to create a session for the
                // new client.
                if(key.isAcceptable()){
                    try {
                        SocketChannel sc=ssc.accept();
                        System.out.println("Sever: A client has connected to the server");
                        sc.configureBlocking(false);
                        // We want to receive data from client first.
                        sc.register(selector,SelectionKey.OP_READ);
                        sessionId++;
                    } catch (IOException e) {
                        System.out.println("Sever: The server is closed, and don't accept connexion anymore");
                    }
                }
                /* receive data from client */
                else if(key.isReadable()){
                    SocketChannel channel = (SocketChannel)key.channel();
                    readBuff.clear();
                    try {
                        channel.read(readBuff);
                        // change buffer to read mode and print content
                        readBuff.flip();
                        System.out.println("Sever says message received : " + new String(readBuff.array()));

                        // reset the channel to write. so server can send data to client
                        key.interestOps(SelectionKey.OP_WRITE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                /* send data to client */
                else if(key.isWritable()){
                    SocketChannel channel = (SocketChannel)key.channel();
                    // we set value in writeBuff in constructor, here we only set the pointer to 0
                    writeBuff.rewind();
                    try {
                        channel.write(writeBuff);
                        //after write, change channel to read mode
                        key.interestOps(SelectionKey.OP_READ);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }

        }
    }
}
