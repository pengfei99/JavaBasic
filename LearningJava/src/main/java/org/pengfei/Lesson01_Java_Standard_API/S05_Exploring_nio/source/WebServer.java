package org.pengfei.Lesson01_Java_Standard_API.S05_Exploring_nio.source;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class WebServer implements Runnable{
    private String ip;
    private int port;
    private boolean close=false;

    ServerSocketChannel ssc;
    public WebServer(String ip, int port){
        this.ip=ip;
        this.port=port;
        try {
            //1.通过ServerSocketChannel 的open()方法创建一个ServerSocketChannel对象，open方法的作用：打开套接字通道
            ssc = ServerSocketChannel.open();
            //2.通过ServerSocketChannel绑定ip地址和port(端口号)
            ssc.socket().bind(new InetSocketAddress(ip, port));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


public void run(){
    int sessionId=0;
        while(!close){

            //通过ServerSocketChannelImpl的accept()方法创建一个SocketChannel对象用户从客户端读/写数据
            try {
                //accept() is a blocking method, server will wait if no client connects
                SocketChannel socketChannel = ssc.accept();
                System.out.println("A client has connected to the server");
                handleClientSession(sessionId,socketChannel);
                sessionId++;

            } catch (IOException e) {
              System.out.println("The server is closed, and don't accept connexion anymore");
            }
        }
}


public void handleClientSession(int sessionId,SocketChannel socketChannel){
    //3.创建写数据的缓存区对象
    ByteBuffer writeBuffer = ByteBuffer.allocate(128);
    writeBuffer.put(("Session "+sessionId+": hello WebClient this is from WebServer").getBytes());
    writeBuffer.flip();
    try {
        socketChannel.write(writeBuffer);
    } catch (IOException e) {
        e.printStackTrace();
    }


//创建读数据的缓存区对象
    ByteBuffer readBuffer = ByteBuffer.allocate(128);


//读取缓存区数据
    try {
        socketChannel.read(readBuffer);
    } catch (IOException e) {
        e.printStackTrace();
    }
    StringBuilder stringBuffer = new StringBuilder();


//4.将Buffer从写模式变为可读模式

    readBuffer.flip();


    while(readBuffer.hasRemaining()) {
        stringBuffer.append((char) readBuffer.get());
    }


    System.out.println("从客户端接收到的数据："+stringBuffer);

    try {
        socketChannel.close();
    } catch (IOException e) {
        e.printStackTrace();
    }


}

public void close(){
        close=true;
    try {
        ssc.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}



