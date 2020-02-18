package org.pengfei.Lesson01_Java_Standard_API.S05_Exploring_nio.source;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.util.Arrays;

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
        /*
         * When we use newByteChannel to open a file with no arguments, the file is opened for
         * input	operations(read). SeekableByteChannel is an interface that describes a channel that can be used
         * for file operations. It is implemented by the FileChannel class. When the default file system is
         * used, the returned object can be cast to FileChannel. Since all channels, including FileChannel,
         * implement AutoCloseable, we can use try with resource.*/
        try (SeekableByteChannel fChan = Files.newByteChannel(filePath);) {
            // 3. create a buffer
            ByteBuffer b = ByteBuffer.allocate(128);

            do {
                // read byte to buffer
                count = fChan.read(b);

                //stop when end of file is reached
                if (count != -1) {
                    // rewind the buffer so that is can be read. It's essential, because the current position is
                    // at the end of the buffer after the call to read(). It must be reset to the start of the buffer,
                    // so that when we call b.get() it will start from the beginning of the buffer.
                    b.rewind();

                    // Read bytes from the buffer and show them on console
                    for (int i = 0; i < count; i++) System.out.print((char) b.get());
                }
            } while (count != -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Improved version of exp1, we streamlined path creation and byteChannel creation, so we reduce two try block
     * into one*/
    public static void exp2(String path) {
        int count;
        try (var fChan = Files.newByteChannel(Path.of(path))) {
            ByteBuffer b = ByteBuffer.allocate(128);
            do {
                count = fChan.read(b);
                if (count != -1) {
                    b.rewind();
                    for (int i = 0; i < count; i++) System.out.print((char) b.get());
                }
            } while (count != -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp3(String path) {
        try (FileChannel fChan = (FileChannel) Files.newByteChannel(Path.of(path))) {
            // Get the size of the file
            long fSize = fChan.size();

            // now Map the the file into a buffer
            MappedByteBuffer mBuf = fChan.map(FileChannel.MapMode.READ_ONLY, 0, fSize);

            // Read and display bytes from buffer
            for (int i = 0; i < fSize; i++) {
                //get() returns the byte of current pointer position, each call will move the pointer forward
                System.out.print((char) mBuf.get());
            }
            System.out.println();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /* write data to a file via channel*/
    public static void exp4(String path) {
        // obtain a channel to a file
        // we add two argument, StandardOpenOption.WRITE to indicate this channel is for output(if nothing is
        // specified, it will be input by default). StandardOpenOption.CREATE specifies if file does not exist,
        // create one.
        try (FileChannel fChan = (FileChannel) Files.newByteChannel(Path.of(path), StandardOpenOption.WRITE,
                StandardOpenOption.CREATE)) {
            // create a buffer of 88 byte
            ByteBuffer b = ByteBuffer.allocate(18);

            //write data to buffer, each call to put() advances the current position.
            for (int i = 0; i < 18; i++) b.put((byte) ('a' + i));

            //reset the buffer pointer to the beginning of the buffer
            b.rewind();

            // write the buffer to the output file
            fChan.write(b);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* flip example*/
    public static void exp5() {
        // create a buffer with size 4
        ByteBuffer b = ByteBuffer.allocate(4);

        // write two byte into the buffer
        b.put((byte) 18);
        b.put((byte) 18);

        //now current position is 2. if we use rewind(), position=0, limit=4,
        // if we use flip(), position=0, limit=2(previous current position)
        b.rewind();
        System.out.println("After rewind, Position is: " + b.position() + " Limit is: " + b.limit());

        // now set position index to 2
        ByteBuffer data = b.position(2);
        System.out.println("Set position to 2 returns a buffer: " + Arrays.toString(data.array()));
        System.out.println("After set position to 2, Position is: " + b.position() + " Limit is: " + b.limit());

        // flip
        b.flip();
        System.out.println("After flip, Position is: " + b.position() + " Limit is: " + b.limit());
    }

    /* write to a file via buffer mapping*/
    public static void exp6(String path) {
        // create a channel
        try (var fChan = (FileChannel) Files.newByteChannel(Path.of(path),
                StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE)) {
            // map the file into a buffer
            MappedByteBuffer mBuf = fChan.map(FileChannel.MapMode.READ_WRITE, 0, 26);

            // write some bytes to the buffer
            for(int i=0;i<26;i++) mBuf.put((byte)('A'+i));

            // now rewind the buffer and read the content
            mBuf.rewind();
            for(int i=0;i<26;i++) System.out.print((char)mBuf.get());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp7(String src, String dest){
        try{
            Path source=Path.of(src);
            Path destination= Path.of(dest);

            //copy the file
            Files.copy(source,destination, StandardCopyOption.REPLACE_EXISTING);
        }
        // to distinguish the exception of copy and path creation, we can add a catch to path creation.
        // its optional, because InvalidPathException extends IOException.
        catch (InvalidPathException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
