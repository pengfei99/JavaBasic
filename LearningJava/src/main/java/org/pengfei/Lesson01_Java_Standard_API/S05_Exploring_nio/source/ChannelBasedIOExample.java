package org.pengfei.Lesson01_Java_Standard_API.S05_Exploring_nio.source;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;

public class ChannelBasedIOExample {
    public static void exp1(String path) {
        int count;
        Path filePath = null;
        //1. create file path
        try {
            filePath = Path.of(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 2. open file and get channel
        try(SeekableByteChannel fChan=Files.newByteChannel(filePath);) {
            // 3. create a buffer
            ByteBuffer b = ByteBuffer.allocate(128);

            do{
                // read byte to buffer
                count = fChan.read(b);

                //stop when end of file is reached
                if(count !=-1) {
                    // rewind the buffer so that is can be read.
                    b.rewind();

                    // Read bytes from the buffer and show them on console
                    for(int i=0; i< count;i++) System.out.print((char)b.get());
                }
            }while (count != -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
