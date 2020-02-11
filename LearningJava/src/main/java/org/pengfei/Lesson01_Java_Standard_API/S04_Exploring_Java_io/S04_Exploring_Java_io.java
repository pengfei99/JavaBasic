package org.pengfei.Lesson01_Java_Standard_API.S04_Exploring_Java_io;

import org.pengfei.Lesson01_Java_Standard_API.S04_Exploring_Java_io.source.AdvanceByteIOExp;
import org.pengfei.Lesson01_Java_Standard_API.S04_Exploring_Java_io.source.FileExp;

import java.io.File;

public class S04_Exploring_Java_io {

    /********************************************* 4.1 Introduction **************************************************/

    /*
    * In Lesson0-S09 Using IO, we have seen how to use io packages. In this section we will go further and do some advance
    * operations with IO. For full interface and class list
    * */

    /********************************************* 4.2 File **************************************************/

    /*
    * Most of the classes in java.io operate on streams, the File class does not. A File object is used to obtain
    * or manipulate the information associated with a disk file, such as the permissions, time, date, directory
    * path, and to navigate subdirectory hierarchies.
    *
    * File class provides four constructors:
    * - File(String dirPath):
    * - File(String dirPath, String fileName)
    * - File(File dirObj, String fileName)
    * - File(URI uriObj)
    *
    * dirPath is the path name of the file, fileName is the name of the file or subdirectory. dirObj is a File object
    * that specifies a directory, uriObj is a URI object that describes a file.
    *
    * Check FileExp.exp1(); File object provide many methods to retrieve basic file meta data.
    * If you want to get more advance meta data(e.g lastAccess time, creating time), you can use BasicFileAttributes
    * (java.nio). Check FileExp.exp2("/tmp/test2"); for example.
     * */

    /*
    * We can also modify the file with the following methods:
    * - boolean renameTo(File newName): It rename the file with newName, returns true if successful
    * - boolean delete(). It deletes the file, returns true if successful
    * - void deleteOnExit(): It removes the file associated with the invoking object when the jvm terminates.
    * - long getFreeSpace(): Returns the number of free bytes of storage available on the partition associated with the
    *                       invoking object.
    * - long getTotalSpace(): Returns the number of the storage capacity of the partition associated with the invoking
    *                       object.
    * - long getUsableSpace(): Returns the number of usable free bytes of storage available on the partition
    * - boolean isHidden(): Returns true if the invoking file is hidden.
    * - boolean setLastModified(long millisec): Sets the time stamp on the invoking file to that specified by millisec.
    * - boolean setReadOnly(): Sets the invoking file to read-only. similar methods for mark files as readable, writable
    *               executable.
    *
    * File implements the Comparable interface, the method compareTo() is also supported.
    * To work with classes in java.nio.file, toPath() method is provided, which transform a File object to Path object.
    * Check FileExp.exp2("/tmp/test2"), we convert File to Path.
    * */

/** 4.2.1 Directories
 * A directory is a File that contains a list of other files and directories. When a File object is a directory, you can
 * do some addition operations:
 * - String[] list(): list all files or directories in it
 * check FileExp.exp4("/tmp");
 *
 * You will often want to limit the number of files returned by the list() method. We have a overloaded version of list
 * - String[] list(FilenameFilter ffObj): ffObje is an object of a class that implements the FilenameFilter interface.
 * The FilenameFilter interface defines a single method, boolean accept(File directory, String fileName), it returns
 * true if the file should be included, otherwise false
 * check  FileExp.exp5("/tmp");
 *
 * We can do the same thing with listFiles() method:
 * - File[] listFiles(): returns an array of files instead of string, it can avoid create new File for each
 *               returned String.
 * - File[] listFiles(FilenameFilter ffObj):
 *
 * We can also create directories with methods:
 * - mkdir(): It creates a directory, returning true if success
 * - mkdirs(): It creates a directory and all the parents of the directory(mkdir -p)
 *
* */

    /*********************************** 4.3 AutoCloseable, Closeable, Flushable *********************************/

    /*
    * The AutoCloseable is packaged in java.lang. It provides support for the try-with-resources statement.
    *
    * The Closeable interface extends AutoCloseable, it allows recurrent close. For example, if we have a
    * fileInputStream fin, and s Scanner(fin). When we close the scanner, the fin is closed too. No need to call close
    * on fin.
    *
    * Objects of a class that implements Flushable can force buffered output to be written to the stream to which the
    * object is attached. It defines the flush() method, shown here: void flush() throws IOException.
    * Flushing a stream typically causes buffered output to be physically written to the underlying	device.	This
    * interface	is implemented by all of the I/O classes that write to a stream.
    *
    * Closeable and Flushable are packaged in java.io.
    * */

    /****************************************** 4.4 I/O Exceptions ********************************************/

    /*
    * Two exceptions play an important role in I/O handling.
    * - IOException: When I/O error occurs, an IOException is thrown. A subclass FileNotFoundException is used
    *            to handle file I/O
    * - SecurityException: If a security violation occurs when attempting to open a file, a SecurityException is
    *            thrown. By default, Java do not use a "security manager" ro run applications, so we will not
    *            see SecurityException examples in this section
    *   */

    /****************************************** 4.5 Stream classes ********************************************/

    /*
    * Java's stream-based I/O is built upon four abstract classes:
    * - InputStream: Byte based, often used when working with bytes or other binary objects.
    * - OutputSteam:
    * - Reader: Character based, often used when working with characters or strings.
    * - Writer: Character based
    * */

    /****************************************** 4.6 Byte Stream classes ********************************************/

    /*
    * The byte stream classes provide a rich environment for handling byte-oriented I/O. A byte stream can be
    * used with any type of object including binary data. InputStream and OutputStream is the top abstract class
    * that defines Java's model of streaming byte I/O. They are implement the Closeable, and AutoCloseable interface
    *
    * FileInputStream and FileOutputStream are two concrete implementation class which extends InputStream and
    * OutputStream for read/write byte from a file. They override several methods of the abstract class, but not
    * the mark() and reset() methods. So any attempt to use reset() on a File will generate an IOException.
    *
    * FileOutputStream can create a new file which does not exist yet. It will thrown an exception, if the file is
    * read only, it implements Flushable interface.
    *
    * Check AdvanceByteIOExp.exp1() for FileInputStream example
    * Check AdvanceByteIOExp.exp2(); for FileOutputStream example
    * */


/** 4.6.1 ByteArrayInputStream
 * ByteArrayInputStream is an implementation of an input stream that uses a byte array as the source. It has two
 * constructors:
 * - ByteArrayInputStream(byte array[])
 * - ByteArrayInputStream(byte array[], int start, int numBytes): It creates an InputStream from a subset of the
 *                   byte array that begins with start and numBytes long.
 * Here, array is the input source.
 *
 * The close() method has no effect on a ByteArrayInputStream. Therefore, it is not necessary to call close(), but
 * doing so is not error.
 *
 * A ByteArrayInputStream implements:
 * - mark(int readAheadLimit): It marks the current position of the input stream. It sets readlimit i.e. maximum number
 *                  of bytes that can be read before mark position becomes invalid.
 * - reset(): It resets the pointer position of the stream to the mark position, if no mark position, set pointer to the
 *            beginning of the stream.
 *  check AdvanceByteIOExp.exp3();
 * */

/** 4.6.2 ByteArrayOutputStream
 *
 * ByteArrayOutputStream is an implementation of an output stream that uses a byte array as the destination.
 * ByteArrayOutputStream has two constructors:
 * - ByteArrayOutputStream(): It creates an output stream with a buffer of 32 bytes.
 * - ByteArrayOutputStream(int numBytes): the buffer has the numBytes size.
 * The buffer size will be increased automatically, if needed. It has count filed, which stores the number of bytes
 * held by buffer. The close() method has no effect on a ByteArrayOutputStream too.
 *
 * - reset(): It clears the ByteArrayOutputStream buffer as a new empty buffer.
 * check AdvanceByteIOExp.exp4();
 * */


/** 4.6.3 Filtered Byte Streams
 * Filtered Byte Streams are the wrapper classes for the InputStream and OutputStreams. These streams add special
 * features and improves the performance. The filtered byte streams are FilterInputStream and FilterOutputStream.
 * Buffering I/o is very common performance optimization.
 *
 * Buffered Byte Streams
 * A buffered stream extends a filtered stream class by attaching a memory buffer to the I/O steam. This buffer allows
 * Java to do I/O operations on more than a byte at a time, thereby improving performance. Because the buffer, we can
 * do skipping, marking, and resetting of the stream. The buffered byte stream classes are:
 * - BufferedInputStream
 * - BufferedOutputStream
 * - PushbackInputStream
 *
 * BufferedInputStream
 * It allows you to "wrap" any InputStream into a buffered stream to improve performance. It has two constructors:
 * - BufferedInputStream(InputStream in): It creates a buffered stream with a default buffer size.
 * - BufferedInputStream(InputStream in, int bufSize): It creates a buffered stream with the bufSize. It's recommended
 *               the bufSize is multiples of a memory page or a disk block. This can have significant positive impact
 *               on performance.
 * A good buffer size is around 8,192 bytes, and attaching even a rather small buffer to an I/O stream is always a
 * good idea. That way, the low-level system can read blocks of data from the disk or network and store the
 * results in your buffer. Thus, even if you are reading the data a byte at a time out of the InputStream, you will be
 * manipulating fast memory most of the time.
 *
 *
 * */
    /***********************************************************************************************************
     * ******************************************* Code example ***********************************************
     * **********************************************************************************************************/
public static void main(String[] args){

    /** file */
    // create file object and get meta data
   // FileExp.exp1();
  //  FileExp.exp2("/tmp/test2");

    //delete rename file
  //  FileExp.exp3("/tmp/test3");

    // directories list
   // FileExp.exp4("/tmp");

    // find files with filenameFilter
   // FileExp.exp5("/tmp");

    /** byte IO*/
   // AdvanceByteIOExp.exp1("/tmp/kotlin-daemon.2020-01-22.09-48-54-150.00.log");
   // AdvanceByteIOExp.exp2();

    //ByteArrayInputStream
    // AdvanceByteIOExp.exp3();

    //ByteArrayOutputStream
    // AdvanceByteIOExp.exp4();

    //BufferedInputStream
    AdvanceByteIOExp.exp5();
}
}
