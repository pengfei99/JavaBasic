package org.pengfei.Lesson01_Java_Standard_API.S05_Exploring_nio;

public class S05_Exploring_nio {

    /********************************************* 5.1 Introduction **************************************************/

    /*
    * Beginning with version 1.4, Java has provided a second I/O system called NIO. It supports a buffer-oriented,
    * channel-based approach to I/O operations. With JDK7, the NIO system was greatly expanded, providing enhanced
    * support for file-handling and file system features. */

    /********************************************* 5.2 The NIO Classes ***********************************************/

    /*
    * The NIO classes are contained in the packages:
    * - java.nio: Top level packages fro the NIO system. Encapsulates various types of buffers that contain data
    *             operated upon the NIO system.
    * - java.nio.channels: Supports channels, which are essentially open I/O connections.
    * - java.nio.channels.spi: Supports service providers for channels.
    * - java.nio.charset: Encapsulates character sets. Also supports encoders and decoders that convert characters
    *              to bytes and bytes to characters.
    * - java.nio.charset.spi: Support service providers for character sets.
    * - java.nio.file: Provides support for files
    * - java.nio.file.attribute: Provides support for file attributes.
    * - java.nio.file.spi: Supports service provider for file system.
    *
    * Beginning with JDK 9, all above packages are included in the java.base module.
    * */

    /********************************************* 5.3 NIO Fundamentals ***********************************************/

    /* The NIO system is built on two foundational items:
    * - buffers
    * - channel
    * A buffer holds data. A channel represents an open connection to an I/O device, such as a file or a socket.
    * In general, to use the NIO system, you obtain a channel to an I/O device and a buffer to hold data.
    * You then operate on the buffer, inputting or outputting data as needed.
    * */

    /** 5.3.1 Buffers
     *
     * Buffers are defined in the java.nio package, which are all subclasses of the Buffer class. They all have the
     * core functionality such as:
     * - current position: is the index within the buffer at which the next read or write operation will take place.
     * - limit: is the minimum index value which past the last valid location in the buffer.
     * - capacity: is the number of elements that the buffer can hold.
     *
     * Often the limit equals the capacity of the buffer. Buffer also supports mark and reset method. It has the
     * following methods:
     * - abstract Object array(): Returns the array that backs this buffer (optional operation).
     * - abstract int arrayOffset(): Returns the offset within this buffer's backing array of the first element of
     *                         the buffer  (optional operation).
     * - final int capacity(): Returns this buffer's capacity.
     * - final Buffer clear(): Clears this buffer and returns a reference to the buffer.
     * - final Buffer flip(): Sets the invoking buffer's limit to the current position and resets the current position
     *                      to 0. returns a reference to the buffer.
     * - abstract boolean hasArray(): Returns true if the invoking buffer is backed by a read/write array.
     * - boolean hasRemaining(): Returns true if there are remaining element in the invoking buffer.
     * - abstract boolean isDirect(): Tells whether or not this buffer is direct. which means I/O operations act
     *                    directly upon it.
     * - abstract boolean isReadOnly(): Tells whether or not this buffer is read-only.
     * - int limit(): Returns this buffer's limit.
     * - Buffer limit(int newLimit): Sets this buffer's limit.
     * - Buffer mark(): Sets this buffer's mark at its position.
     * - int position(): Returns this buffer's position.
     * - Buffer position(int newPosition): Sets this buffer's position.
     * - int remaining(): Returns the number of elements between the current position and the limit.
     * - Buffer reset(): Resets this buffer's position to the previously-marked position.
     * - Buffer rewind(): Rewinds this buffer. Which means sets the position of invoking buffer to 0. Returns a
     *              reference to the buffer.
     * - abstract Buffer slice(): Returns a buffer that consists of the elements in the invoking buffer, beginning at
     *              the invoking buffer's current position. Thus, for the slice, both buffers will contain and refer
     *              to the same elements.
     *
     * From Buffer, the following specific buffer classes are derived, which hold the type of data that their name
     * imply:
     * - ByteBuffer
     * - CharBuffer
     * - DoubleBuffer
     * - FloatBuffer
     * - IntBuffer
     * - LongBuffer
     * - MappedByteBuffer: is a subclass of ByteBuffer and is used to map a file to a buffer
     * - ShortBuffer
     *
     * The above buffer all provides get()(read data from a buffer) and set()(write data to a buffer). When write to
     * read-only buffer, exceptions will be thrown.
     *
     * They also have important buffer operations:
     * - allocate(): allocate a buffer manually
     * - wrap(): wrap an array inside a buffer
     * - slice(): create a subsequence of a buffer.
     * */

    /** 5.3.2 Channels
     * Channels are defined in java.nio.channels. A channel represents an open connection to an I/O source or
     * destination. Channels implement the Channel interface. It extends Closeable, and it extends AutoCloseable.
     *
     * One way to obtain a channel is by calling getChannel() on an object that supports channels. For example, the
     * following class supports getChannel():
     * - DatagramSocket:
     * - FileInputStream:
     * - FileOutputStream:
     * - RandomAccessFile:
     * - ServerSocket:
     * - Socket:
     *
     * The specific type of channel returned depends upon the type of object getChannel() is called on. For	example,
     * when called on a FileInputStream, FileOutputStream, or RandomAccessFile, getChannel() returns a channel of
     * type	FileChannel. When called on a Socket, getChannel() returns a SocketChannel.
     *
     * Another way to obtain channel is to use one of the static methods defined by the Files class. For example, using
     * Files, you can obtain a byte channel by calling newByteChannel(). It returns a SeekableByteChannel, which is an
     * interface implemented by FileChannel.
     *
     * Channels	such as FileChannel and SocketChannel support various read() and write() methods that enable
     * you to perform I/O operations through the channel. For example:
     * - abstract int read(ByteBuffer bb): Reads bytes from the invoking channel into bb until the buffer is full or
     *              there is no more input. Returns the number of bytes actually read. Returns -1 when an attempt is
     *              made to read at the end of the file
     * - abstract int read(ByteBuffer bb, long start): Beginning at the file location specified by start, Reads bytes
     * - abstract int write(ByteBuffer bb): Writes the contents of bb to the invoking channel, starting at the current
     *             position. Returns the number of bytes written.
     * - abstract int write(ByteBuffer bb, long start): Beginning at the file location specified by start, writes
     *             contents of bb.
     * */

    /** 5.3.3 Charsets and Selectors
     *
     * Two other entities used by NIO are:
     * - charsets: A charset defines the way that bytes are mapped to characters. You can encode a sequence of
     *            characters into bytes using an encoder. You can decode a sequence of bytes into characters using a
     *            decoder. Charsets, encoders, and decoders are supported by classes defined in the java.nio.charset
     *            package. Because default encoders and decoders are provided, you will not often need to work
     *            explicitly with charsets.
     * - selectors: supports key-based, non-blocking, multiplexed I/O. In other words, selectors enable you to
     *             perform I/O through multiple channels. Selectors are supported by classes defined in the
     *             java.nio.channels package. Selectors are most applicable to socket-backed channels.
     * */

    /*************************************** 5.4 Enhancements added by NIO ******************************************/

    /**/

}
