package org.pengfei.Lesson01_Java_Standard_API.S04_Exploring_Java_io;

import org.pengfei.Lesson01_Java_Standard_API.S04_Exploring_Java_io.source.AdvanceByteIOExp;
import org.pengfei.Lesson01_Java_Standard_API.S04_Exploring_Java_io.source.AdvanceCharacterIOExp;
import org.pengfei.Lesson01_Java_Standard_API.S04_Exploring_Java_io.source.FileExp;
import org.pengfei.Lesson01_Java_Standard_API.S04_Exploring_Java_io.source.serialization.SerializationExp;

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
 * BufferedOutputStream
 * A BufferedOutputStream is similar to any OutputStream with the exception that the flush() method is used to ensure
 * that data buffers are written to the stream being buffered. Since the existence of BufferedOutputStream is to
 * improve performance by reducing the number of times the system actually writes data, you may need to call flush()
 * to cause any data that is in the buffer to be immediately written.
 *
 * It does not provide additional functionality compare to OutputStream, it's only to increase performance, It has
 * two constructors:
 * - BufferedOutputStream(OutputStream out): It creates a buffer stream with the default buffer size.
 * - - BufferedOutputStream(OutputStream out, int bufSize): It creates a buffer stream with the given buffer size.
 *
 * PushbackInputStream
 * Pushback is used on a an input stream to allow a byte to be read and then returned(aka. "pushed back") to the stream.
 * It provides a mechanism to "peek" at what is coming from an input stream without disrupting it. It has two
 * constructors:
 * - PushbackInputStream(InputStream in): It creates a stream object that allows one byte to be returned to the input
 *                stream.
 * - PushbackInputStream(InputStream in, int numBytes): It creates a stream that has a pushback buffer of numBytes long.
 *                This allows multiple bytes to be returned to the input stream.
 *
 * It has a special method unread() and has three overload version:
 * - void unread(int b): It pushes back the low-order byte of b. This will be teh next byte returned by a subsequent
 *                  call to read().
 * - void unread(byte[] buffer): It pushes back the bytes in buffer.
 * - void unread(byte buffer, int offset, int numBytes): It pushed back the bytes starts from offset with numBytes
 *                  length in buffer.
 *
 * Check AdvanceByteIOExp.exp6(); we use unread to push back byte into streams.
 * */

/** 4.6.4 SequenceInputStream
 * The SequenceInputStream class allows you to concatenate multiple InputStreams. The construction of it is different
 * from any other InputStream. It has two constructors:
 * - SequenceInputStream(InputStream first, InputStream second): It builds a steam which takes two input streams. It
 *               reads first inputStream until it runs out and then switches over to the second one.
 * - SequenceInputStream(Enumeration < ? extends InputStream> streamEnum): It takes an Enumeration of stream, It reads
 *               all steams one by one until the end of last one is reached.
 * Note, it uses the old api Enumeration, not the Collection framework. So we need to use old Class such as Vector
 * to make a list of input streams.
 *
 * Check  AdvanceByteIOExp.exp7(); first we create a Class InputStreamEnumerator to create an Enum of fileInputStream
 * Then we build a SequenceInputStream with the enum to read three files with one stream.
 * */

/** 4.6.5 PrintStream
 * The PrintStream class provides all of the output capabilities we have been using from the System file handle,
 * System.out. This makes PrintStream one of Java's most used classes. It implements the Appendable, AutoCloseable,
 * Closeable, and Flushable interfaces. It has following constructors:
 * - PrintStream(OutputStream out): It takes an output stream that will receive output.
 * - PrintStream(OutputStream out, boolean autoFlushingOn): autoFlushingOn controls whether the output buffer is
 *            automatically flushed every time a newline(\n) character or a byte array is written or when println()
 *            is called. If true, auto flush on. By default, auto flush is off.
 * - PrintStream(OutputStream out, boolean autoFlushingOn, String charSet): charSet specifies a character encoding.
 *
 * The next set of constuctors gives you an easy way to construct a PrintStream that writes its output to a file:
 * - PrintStream(File outputFile):
 * - PrintStream(File outputFile, String charSet):
 * - PrintStream(String outputFileName):
 * - PrintStream(String outputFileName, String charSet):
 * They will throw UnsupportedEncodingException, FileNotFoundException accordingly.
 *
 * It provides print() and println() for all types including object(toString() will be called).
 * It supports also Formatter class by calling printf() which has two general forms:
 * - PrintStream printf(String format, Object ... args):
 * - PrintStream printf(Local loc, String format, Object ... args): Use a special local for internationalization. The
 *               default local is the system local.
 *
 * System.out is a PrintStream.
 * */

/** 4.6.6 DataOutputSteam and DataInputStream
 * DataOutputStream and DataInputStream enable you to write or read primitive data to or from a stream. They implement
 * the DataOutput and DataInput interfaces, respectively. These interfaces define methods that convert primitive
 * values to or from a  sequence of bytes. It makes storing binary data(e.g. integers, floats,) in a file easy.
 *
 * DataOutputStream
 * DataOutputStream extends FilterOutputStream, which extends OutputStream. In addition to implement DataOutput, it
 * implements also AutoCloseable, Closeable and Flushable. It has one constructor:
 * - DataOutputStream(OutputStream out):
 * As it implements Closable interface, when we close a DataOutputStream, the underlying OutputStream is closed too.
 * It provide many methods which can convert primitive types into byte sequence and write them into underlying stream.
 * For example:
 * - final void writeDouble(double value) throws IOException:
 *
 * DataInputStream
 * DataInputStream is the complement of DataOutputStream. It extends FilterInputStream, which extends InputStream.
 * It implements DataInput, AutoCloseable, Closeable interfaces. It has one constructor:
 * - DataInputStream(InputStream in):
 * As it implements Closable interface, when we close a DataInputStream, the underlying InputStream is closed too.
 * It provides methods which read a sequence of bytes and convert them into values of a primitive type.
 * For example:
 * - final double readDouble() throws IOException:
 *
 *
 * */

/** 4.6.7 RandomAccessFile
 *
 * RandomAccessFile encapsulates a random-access file. It is not derived from InputStream or OutputStream. It implements
 * the interfaces DataInput and DataOutput, which define the basic I/O methods. It also implements AutoCloseable,
 * Closeable interface. It has two constructors:
 * - RandomAccessFile(File file, String access) throws FileNotFoundException:
 * - RandomAccessFile(String fileName, String access) throws FileNotFoundException:
 *  access can only has the following form:
 *  - r: read only
 *  - rw: read write
 *  - rws: read write, and all change to the file's data or metadata will be immediately written to the physical device.
 *  - rwd: read write, and only data change will be immediately written to the physical device.
 *
 * RandomAccessFile supports positioning requests, which means you can position the file pointer within the file. The
 * method void seek(long newPos) can set the current position of the file pointer within the file. Here, newPos
 * specifies the new position, in bytes, of the file pointer from the beginning of the file. After seek(), the next
 * read or write operation will occur at teh new file position.
 *
 * You can also set a length on RandomAccessFile with void setLength(long len), this can lengthen or shorten a file.
 * */

    /****************************************** 4.7 The chara Stream classes *****************************************/

/*
* While the byte stream classes provide sufficient functionality to handle any type of I/O operation, they cannot work
* directly with Unicode characters. Since one of the main purposes of Java is to support the "write once, run anywhere"
* philosophy, it was necessary to include direct I/O support for characters. In this section, several of the
* character I/O classes are discussed. As explained earlier, at the top of the character stream hierarchies are the
* Reader and Writer abstract classes. We will begin	with them.*/

/** 4.7.1 Reader
 * Reader is an abstract class that defines Java's model of streaming character input. It implements AutoCloseable,
 * Closeable, and Readable interfaces.
 * See Lesson00 Section 9.12 for all Reader methods definition. Check the mark(), reset() method, which means it has
 * underlying buffer.
 * */

/** 4.7.2 Writer
 * Writer is an abstract class that defines streaming character output. In implements AutoCloseable, Closeable,
 * Flushable and Appendable interfaces.
 * ee Lesson00 Section 9.12 for all Writer methods definition.
 * */

/** 4.7.3 FileReader
 * The FileReader creates a Reader that you can use to read the contents of a file. It has two commonly used
 * constructors:
 * - FileReader(String filePath):
 * - FileReader(File fileObj):
 *
 * check  AdvanceCharacterIOExp.exp1("/tmp/test1");
 * */

/** 4.7.4 FileWriter
 * It has four commonly used constructors:
 * - FileWriter(String filePath):
 * - FileWriter(String filePath, boolean append):
 * - FileWriter(File fileObj):
 * - FileWriter(File fileObj, boolean append):
 * If append is true, then output is appended to the end of the file. If the file does not exist, FileWriter will
 * create the file before opening it. If the file is a read only file, an IOException will be thrown.
 * check AdvanceCharacterIOExp.exp2();
 * */

/** 4.7.5 CharArrayReader
 * CharArrayReader is an implementation of an input stream that uses a character array as the source. It has two
 * constructors:
 * - CharArrayReader(char array[])
 * - CharArrayReader(char array[], int start, int numChars): It reads a subset of array from start with length numChars.
 *
 * check  AdvanceCharacterIOExp.exp3();
 * */

/** 4.7.6 CharArrayWriter
 * CharArrayWriter is an implementation of an output stream that uses an array as the destination. It has two
 * constructors:
 * - CharArrayWriter(): It creates a writer with a buffer of default size
 * - CharArrayWriter(int numChars): It creates a writer with a  buffer of size numChars.
 *
 * The buffer "buf" and character length inside buffer "count" are protected fields.
 * The close() method has no effect.
 * Check AdvanceCharacterIOExp.exp4("/tmp/test");
 * */

/** 4.7.7 BufferedReader
 * BufferedReader improves performance by buffering input, it has two constructors:
 * - BufferedReader(Reader in): It builds a bufferReader with a buffer of default size
 * - BufferedReader(Reader in, int bufSize): It builds a bufferReader with a buffer of size bufSize
 *
 * Closing a bufferReader also closes the underlying input stream(Reader for characters). Buffer allows us to implement
 * mark(), reset()
 * check   AdvanceCharacterIOExp.exp5();
 * */

/** 4.7.8 BufferedWriter
 * A BufferedWriter is a Writer that buffers output. Using a BufferedWriter can improve performance by reducing the
 * number of times data is actually physically written to the output device.
 * A BufferedWriter has these two constructors:
 * - BufferedWriter(Writer out): It creates a write with a buffer of default size
 * - BufferedWriter(Writer out, int bufSize): It creates a write with a buffer of buSize size
 * */

/** 4.7.9 PushbackReader
 *
 * The PushbackReader class allows one or more characters to be returned to the inputStream. It has two constructor:
 * - PushbackReader(Reader in):
 * - PushbackReader(Reader in, int bufSize):
 *
 * It provides unread() method, which returns one or more characters to the invoking input stream. It has three
 * overloading version:
 * - void unread(int ch) throws IOException: It pushes back character ch into invoking stream.
 * - void unread(char[] buffer) throws IOException: It pushes back an array of char buffer into invoking stream.
 * - void unread(char[] buffer, int offset, int numChars) throws IOException: It pushes back a sub array of buffer
 *                       starts from offset with length numChars.
 * check AdvanceCharacterIOExp.exp6();
 *
 * */

/** 4.7.10 PrintWriter
 * PrintWriter is essentially a character-oriented version of PrintStream. It implements the Appendable, AutoCloseable,
 * Closeable, and Flushable interfaces. It has the following common used constructors:
 * - PrintWriter(OutputStream out): It writes its output to a OutputStream.
 * - PrintWriter(OutputStream out, boolean autoFlushingOn): autoFlushingOn is true means the output buffer is
 *                automatically flushed every time println(), printf() or format() is called. By default, auto flush is
 *                off.
 * - PrintWriter(Writer out):
 * - PrintWriter(Writer out, boolean autoFlushingOn):
 *
 * It has constructors to write output into a file
 * - PrintWriter(File outputFile, String charSet):
 * - PrintWriter(String outputFileName, String charSet):
 * */

/** 4.7.11 The Console class
 *
 * The Console class is used to read from and write to the console, if one exists. It implements the Flushable
 * interface. Console is primarily a convenience class because most of its functionality is available through System.in
 * and System.out. However, its use can simplify some types of console interactions, especially when reading strings
 * from the console.
 *
 * Console class has no constructors, a Console object is obtained by calling System.console(). If no console is
 * available, null will be returned.
 *
 * Check AdvanceCharacterIOExp.exp7();
 * */

    /****************************************** 4.8 Serialization *****************************************/

    /*
    * Serialization is the process of writing the state of an object to a byte stream. This is useful when you want to
    * save the state of your program to a persistent storage area, such as a file. At a later time, you may restore
    * these objects by using the process of deserialization.
    *
    * Serialization is also needed to implement Remote Method Invocation (RMI). RMI allows a Java object on one
    * machine to invoke a method of a Java object on a different machine. An object may be supplied as an argument
    * to that remote method. The sending machine serializes the object and transmits it. The receiving machine
    * deserializes it.
    *
    * Assume that an object to be serialized has reference to other objects, which may have references to more
    * objects. This set of objects and the relationships among them form a graph(can be directed and circular).
    * If you attempt to serialize an object at the top of an object graph, all of the other referenced objects
    * are recursively located and serialized.
    *
    * It is important to note that serialization and deserialization can impact security, especially as it relates
    * to the deserialization of items that you do not trust. Consult the Java documentation for information about
    * this and about security in general.
    * */

    /** 4.8.1 Serializable interface
     *
     * Only objects that implement the Serializable interface can be saved and restored by the serialization facilities.
     * It has no method members, it's simply used to indicate that a class may be serialized. If a class is serializable,
     * all of its subclass are also serializable.
     *
     * Variables that are declared as "transient", and "static" are not saved by the serialization facilities.
     *
     * "transient" is a variables modifier used in serialization. At the time of serialization, if we don’t want to
     * save value of a particular variable in a file, then we use transient keyword. When JVM comes across transient
     * keyword, it ignores original value of the variable and save default value of that variable data type.
     *
     * transient keyword plays an important role to meet security constraints. There are various real-life examples
     * where we don’t want to save private data in file. Another use of transient keyword is not to serialize the
     * variable whose value can be calculated/derived using other serialized objects or system such as age of a
     * person, current date, etc.
     * */

    /** 4.8.2 Externalizable interface
     * The Java facilities for serialization and deserialization have been designed so that much of the work to save
     * and restore the state of an object occurs automatically. However, there are cases in which the programmer
     * may need to have control over these processes. For example, it may be desirable to use compression or encryption
     * techniques.
     *
     * The Externalizable interface is designed for these situations. It defines two methods:
     * - void readExternal(ObjectInput inStream)throws IOException, ClassNotFoundException: inStream is the byte
     *            stream from which the object is to be read.
     * - void writeExternal(ObjectOutput outStream) throws IOException: outStream is the byte stream to which
     *            the object is to be written.
     *
     * */

    /** 4.8.3 ObjectOutput interface
     *
     * The ObjectOutput interface extends the DataOutput and AutoClosable interfaces and supports object serialization.
     * It defines the following methods:
     * - void close(): Closes the invoking stream. Further write attempts will generate an IOException.
     * - void flush(): Finalizes the output state so any buffers are cleared. it flushes the output buffers of
     *                the stream.
     * void	write(byte[] b): Writes an array of bytes to the invoking stream.
     * void	write(byte[] b, int off, int len): Writes a sub array of bytes beginning at off with length len.
     * void	write(int b): Writes a byte.
     * void	writeObject(Object obj): Write an object to the underlying storage or stream. This is called to
     *               serialize an object.
     * */

    /** 4.8.4 ObjectOutputStream class
     * The ObjectOutputStream class extends the OutputStream class and implements the ObjectOutput interface.
     * It is responsible for writing objects to a stream. It has one constructor:
     * - ObjectOutputStream(OutputStream out) throws IOException: The argument out is the output stream to which
     *             serialized objects will be written. Closing an ObjectOutputStream automatically closes the
     *             underlying stream specified by out.
     *
     * It has an inner class called PutField. It facilitates the writing of persistent fields.
     * */

    /** 4.8.5 ObjectInput interface
     *
     * The ObjectInput interface extends the DataInput and AutoCloseable interfaces. It supports object serialization.
     * It defines the following methods:
     * - int available(): Returns the number of bytes that can be read without blocking(in the input buffer).
     * - void close(): Closes the input stream. Further read attempts will generate an IOException.
     * - int read(): Reads a byte of data. Returns an integer representation of the next available byte of input. -1 is
     *             returned when an attempt is made to read at the end of the stream.
     * - int read(byte[] b): Attempts to read up to b.length bytes into b, returning the number of bytes that
     *               were successfully read. -1 is returned when an attempt is made to read at the end of the stream.
     * - int read(byte[] b, int off, int len): Attempts to read up to len bytes starting at off into b, returning the
     *             number of bytes that were successfully read. -1 is returned when an attempt is made to read at
     *             the end of the stream.
     * - Object readObject(): Read and return an object. This is called to deserialize an object.
     * - long skip(long n): Skips n bytes in the invoking input stream.
     * */

    /** 4.8.6 ObjectInputStream class
     *
     * The ObjectInputStream class extends the InputStream class and implements the ObjectInput interface.
     * ObjectInputStream is responsible for reading objects from a stream. It has one constructor:
     * - ObjectInputStream(InputStream in) throws IOException: The argument in is the input stream from which
     *          serialized objects should be read. Closing an ObjectInputStream automatically closes the
     *          underlying stream specified by in.
     *
     * It has an inner class called GetField. It facilitates the reading of persistent fields.
     *
     * Beginning with JDK 9, ObjectInputStream includes the methods:
     * - getObjectInputFilter():
     * - setObjectInputFilter():
     * These methods support the filtering of object input streams through the use of ObjectInputFilter,
     * ObjectInputFilter.FilterInfo, ObjectInputFilter.Config and ObjectInputFilter.Status. Filtering gives you control
     * over deserialization.
     * */

    /** An example
     * Check SerializationExp.exp1("/tmp/serialization"); and SerializationExp.exp2("/tmp/serialization"); for a
     * code example, Note the object which we want to serialized must implement the Serializable interface.
     *
     * One last point: For classes that you intend to serialize, you will normally want them to define the static,
     * final, long constant serialVersionUID as a private member. Although Java will automatically define this value
     * (as is the case for MySerializedClass in the preceding example), for real world applications, it is far better
     * for you to define this value explicitly.
     * */

    /****************************************** 4.9 Stream benefits *****************************************/
    /*
    * The streaming interface to I/O in Java provides a clean abstraction for a complex and often cumbersome task.
    * The composition of the filtered stream classes allows you to dynamically build the custom streaming interface to
    * suit your data transfer requirements. Java programs written to adhere to the abstract, high-level InputStream,
    * OutputStream, Reader, and Writer classes will function properly in the future even when new and improved
    * concrete stream classes are invented. We will discuss the java.nio in the next section.
    *
    * Finally, serialization of objects plays an important role in many types of Java programs. Java’s serialization
    * I/O classes provide a portable solution to this sometimes tricky task.
    * */


    /***********************************************************************************************************
     * ******************************************* Code example ***********************************************
     * **********************************************************************************************************/
public static void main(String[] args){

    /**************************** file *********************************/
    // create file object and get meta data
   // FileExp.exp1();
  //  FileExp.exp2("/tmp/test2");

    //delete rename file
  //  FileExp.exp3("/tmp/test3");

    // directories list
   // FileExp.exp4("/tmp");

    // find files with filenameFilter
   // FileExp.exp5("/tmp");

    /**************************** byte IO *****************************/
   // AdvanceByteIOExp.exp1("/tmp/kotlin-daemon.2020-01-22.09-48-54-150.00.log");
   // AdvanceByteIOExp.exp2();

    //ByteArrayInputStream
    // AdvanceByteIOExp.exp3();

    //ByteArrayOutputStream
    // AdvanceByteIOExp.exp4();

    /** filtered byte stream */
    //BufferedInputStream
   // AdvanceByteIOExp.exp5();

    //PushBackInputStream
   // AdvanceByteIOExp.exp6();

    //DataInput/Output Stream
  //  AdvanceByteIOExp.exp9("/tmp/test");

    /** SequenceInputStream */
  //  AdvanceByteIOExp.exp7();

    // printStream
   // AdvanceByteIOExp.exp8();


    /*************************** character IO *****************************/
   // FileReader
    // AdvanceCharacterIOExp.exp1("/tmp/test1");

    // FileWriter
   // AdvanceCharacterIOExp.exp2("/tmp/f0","/tmp/f1","/tmp/f2");

    // CharArrayReader
    //AdvanceCharacterIOExp.exp3();

    // CharArrayWriter
  //  AdvanceCharacterIOExp.exp4("/tmp/test");

    // BufferReader
  //  AdvanceCharacterIOExp.exp5();

    //PushbackReader
   // AdvanceCharacterIOExp.exp6();

    //Console
   // AdvanceCharacterIOExp.exp7();

    /**************************** Serialization ****************************/
    // Serialize an object and write it in a file
    // SerializationExp.exp1("/tmp/serialization");
    // Read a file and Deserialize an object
    SerializationExp.exp2("/tmp/serialization");
}
}
