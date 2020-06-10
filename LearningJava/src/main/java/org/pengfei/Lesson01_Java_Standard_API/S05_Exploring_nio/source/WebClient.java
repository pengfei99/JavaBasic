package org.pengfei.Lesson01_Java_Standard_API.S05_Exploring_nio.source;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class WebClient {
    private String ip;
    private int port;
    private SocketChannel sc;
    public WebClient(String ip, int port){
        this.ip=ip;
        this.port=port;
        try {
            sc =SocketChannel.open();
            sc.connect(new InetSocketAddress(ip,port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void handleClientSession(){

        /** Send data to server */
        //create a write buffer
        ByteBuffer writeBuffer=ByteBuffer.allocate(128);
        writeBuffer.put("hello WebServer this is from WebClient".getBytes());
        // change pointer location, so it can be read by channel
        writeBuffer.flip();
        try {
            sc.write(writeBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /** Receive data from server */
        ByteBuffer readBuffer=ByteBuffer.allocate(128);
        try {
            sc.read(readBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // change buffer from write mode to read mode
        readBuffer.flip();
        StringBuilder message=new StringBuilder();
        while (readBuffer.hasRemaining()){
            message.append((char)readBuffer.get());
        }
        System.out.println("从服务端接收到的数据：" + message);

        /** close connection*/
        try {
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
