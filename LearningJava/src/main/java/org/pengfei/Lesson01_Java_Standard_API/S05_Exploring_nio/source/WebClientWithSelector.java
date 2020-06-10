package org.pengfei.Lesson01_Java_Standard_API.S05_Exploring_nio.source;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class WebClientWithSelector {
    private String ip;
    private int port;
    private SocketChannel sc;


    public WebClientWithSelector(String ip, int port){
        this.ip=ip;
        this.port=port;
        try {
            sc =SocketChannel.open();
            sc.connect(new InetSocketAddress(ip,port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(){
        ByteBuffer readBuf=ByteBuffer.allocate(128);
        ByteBuffer writeBuf=ByteBuffer.allocate(128);
        writeBuf.put("Client says hello".getBytes());
        //use flip to set the limit
        writeBuf.flip();
        int i=0;
        while (i<10){
            writeBuf.rewind();
            try {
                //send data to server
                sc.write(writeBuf);
                //receive data from server
                readBuf.clear();
                sc.read(readBuf);
                readBuf.flip();
                StringBuilder message=new StringBuilder();
                while (readBuf.hasRemaining()){
                    message.append((char)readBuf.get());
                }
                System.out.println("Client：" + message);
                i++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
