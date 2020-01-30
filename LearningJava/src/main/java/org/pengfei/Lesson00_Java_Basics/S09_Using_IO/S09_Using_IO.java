package org.pengfei.Lesson00_Java_Basics.S09_Using_IO;

import org.pengfei.Lesson00_Java_Basics.S09_Using_IO.source.ByteIOExp;
import org.pengfei.Lesson00_Java_Basics.S09_Using_IO.source.CharacterIOExp;
import org.pengfei.Lesson00_Java_Basics.S09_Using_IO.source.CompareFiles;

import java.io.IOException;

public class S09_Using_IO {

    /******************************************** 9. Introduction *******************************************/

    /*
    * Java's I/O system is quite large, containing many classes, interfaces and methods. Java defines two complete
    * I/O systems:
    * 1. Byte I/O
    * 2. Character I/O.
    *
    * In this section, we only cover some important and commonly used features such as:
    * - Understand the stream and Know the difference between byte and character streams
    * - Know Java’s byte stream classes
    * - Know Java’s character stream classes
    * - Know the predefined streams
    * - Use byte streams
    * - Use byte streams for file I/O
    * - Automatically close a file by using try­with­resources (Linked with Section 8 Exception)
    * - Read and write binary data
    * - Use random­access files
    * - Use character streams
    * - Use character streams for file I/O
    * - Apply Java’s type wrappers to convert numeric strings
    * */

    /******************************************** 9.1 JAVA's streams *******************************************/

    /*
    * Java programs perform I/O through streams. An I/O stream is an abstraction that either produces or consumes
    * information. A stream represents a flow of data or a channel of communication.
    * A stream is linked to a physical device by the Java I/O system. All streams behave in the same manner,
    * even if the actual physical devices they are linked to differ. Thus, the same I/O classes and methods can be
    * applied to different types of devices. For example, the same methods that you use to write to the console can
    * also be used to write to a disk file. Java implements I/O streams within class hierarchies defined in the
    * java.io package.
    * */

    /** 9.1.1 Difference between byte and character streams
     * Modern versions of Java define two types of I/O streams: byte and character. (The original version of Java
     * defined only the byte stream, but character streams were quickly added.)
     *
     * Byte streams provide a convenient means for handling input and output of bytes. They are used, for example,
     * when reading or writing binary data. They are especially helpful when working with files.
     *
     * Character streams are designed for handling the input and output of characters. They use "Unicode" and,
     * therefore, can be internationalized. Also, in some cases, character streams are more efficient than byte
     * streams.
     *
     * At the lowest level, all I/O is still byte­oriented. The character­based streams simply provide a convenient
     * and efficient means for handling characters.
     * */

    /************************************* 9.2 The byte stream classes *******************************************/

    /**
     * Byte streams are defined by using two class hierarchies. At the top of these are two abstract classes:
     * InputStream and OutputStream. InputStream defines the characteristics common to byte input streams and
     * OutputStream describes the behavior of byte output streams.
     *
     * From InputStream and OutputStream are created several concrete subclasses that offer varying functionality
     * and handle the details of reading and writing to various devices, such as disk files. The byte stream classes
     * in java.io are shown in the following table:
     * - InputStream: Abstract class that describes stream inputs. It is the super class of all XInputStream
     *                class below
     * - OutputStream: Abstract class that describes stream outputs. It is the super class of all XOutputStream
     *                class below
     * - BufferedInputStream: Buffered input stream
     * - BufferedOutputStream: Buffered output stream
     * - ByteArrayInputStream: Input steam that reads from a byte array
     * - ByteArrayOutputStream: Output steam that writes to a byte array
     * - DataInputStream: An input stream that contains methods for reading the java standard data types.
     * - DataOutputStream: An output stream that contains methods for writing the java standard data types.
     * - FileInputStream: Input stream that reads from a file
     * - FileOutputStream: Output stream that writes to a file
     * - FilterInputStream: Implements InputStream
     * - FilterOutputStream: Implements OutputStream
     * - ObjectInputStream: Input stream that reads objects
     * - ObjectOutputStream: Output stream that writes objects
     * - PipedInputStream: input pipe
     * - PipedOutputStream: Output pipe
     * - PrintStream: Output stream that contains print and println()
     * - PushbackInputStream: Input stream that allows bytes to be returned to the stream
     * - SequenceInputStream: Input steam that is a combination of two or more input streams that will be read
     *                        sequentially, one after the other.
     *
     * For the complete java io package class hierarchy, go check my wiki id=employes:pengfei.liu:java:io
     * */

    /************************************** 9.3 The character stream classes ***************************************/

    /**
     * Character streams are defined by using two class hierarchies topped by these two abstract classes: Reader and
     * Writer. Reader is used for input, and Writer is used for output. Concrete classes derived from Reader and Writer
     * operate on Unicode character streams.
     *
     * From Reader and Writer are derived several concrete subclasses that handle various I/O situations. In general,
     * the character­based classes parallel the byte­based classes.
     *
     * The following table shows the character stream classes in java.io:
     * - Reader: Abstract class that describes character stream input. It is the super class of all the XReader
     *           class below
     * - Writer: Abstract class that describes character stream output. It is the super class of all the XWriter
     *           class below
     * - BufferedReader: Buffered input character stream
     * - BufferedWriter: Buffered output character stream
     * - CharArrayReader: Input stream that reads from a character array
     * - CharArrayWriter: Output stream that writes to a character array
     * - FileReader: Input stream that reads from a file
     * - FileWriter: Output stream that writes to a file
     * - FilterReader: filtered reader
     * - FilterWriter: filtered writer
     * - InputStreamReader: Input stream that translates bytes to characters
     * - OutputStreamWriter: Output stream that translates characters to bytes
     * - LineNumberReader: Input stream that counts lines
     * - PipedReader: Input pipe
     * - PipedWriter: output pipe
     * - PrintWriter: Output stream that contains print() and println()
     * - PushbackReader: Input stream that allows characters to be returned to the input stream.
     * - StringReader: Input steam that reads from a string
     * - StringWriter: Output stream that writes to a string
     *
     * For the complete java io package class hierarchy, go check my wiki id=employes:pengfei.liu:java:io
     * */

    /************************************** 9.4 The predefined streams ***************************************/

    /*
    * As you know, all Java programs automatically import the java.lang package. This package defines a class called
    * System, which encapsulates several aspects of the run­time environment. Among other things, it contains three
    * predefined stream variables,called:
    * - in (InputStream)
    * - out (PrintStream)
    * - err (PrintStream)
    * These fields are declared as public, final, and static within System. This means that they can be used by any
    * other part of your program and without reference to a specific System object.
    *
    * System.out refers to the standard output stream. By default, this is the console. System.in refers to standard
    * input, which is by default the keyboard. System.err refers to the standard error stream, which is also the
    * console by default. However, these streams can be redirected to any compatible I/O device.
    *
    * System.in is an object of type InputStream; System.out and System.err are objects of type PrintStream.
    * These are byte streams, even though they are typically used to read and write characters from and to the console.
    * The reason they are byte and not character streams is that the predefined streams were part of the original
    * specification for Java, which did not include the character streams. As you will see, it is possible to wrap
    * these within character­based streams if desired.
    * */

    /************************************** 9.5 Using the byte streams ***************************************/

    /*
    *  As explained, at the top of the byte stream hierarchy are the InputStream and OutputStream classes. So we will
    * start with the method in InputStream and OutputStream.
    * InputStream has the following method:
    * - int available(): Returns the number of bytes of input currently available for reading
    * - void close(): Closes the input source. Subsequent read attempts will generate an IOException
    * - void mark(int numBytes): Places a mark at the current point in the input stream that will remain valid until
    *                            numBytes bytes are read.
    * - boolean markSupported(): Returns true if mark()/reset() are supported by the invoking stream.
    * - static InputStream nullInputStream(): Returns an open, but null stream, which is a stream that contains no
    *                          data. Thus, the stream is always at the end of the stream and no input can be obtained.
    *                          The stream can, however, be closed. (Added by JDK 11.)
    * - int read(): Returns an integer representation of the next available byte of input.
    * */

    /** 9.5.1 Reading console input
     * Originally, the only way to perform console input was to use a byte stream, and much
     * Java code still uses the byte streams exclusively. Today, you can use byte or character
     * streams. For commercial code, the preferred method of reading console input is to use a
     * character­oriented stream. Doing so makes your program easier to internationalize and
     * easier to maintain. It is also more convenient to operate directly on characters rather
     * than converting back and forth between characters and bytes.
     *
     * Because System.in is an instance of InputStream, you automatically have access to
     * the methods defined by InputStream. This means that, for example, you can use the
     * read( ) method to read bytes from System.in. There are three versions of read( ),
     * which are shown here:
     * - int read() throws IOException
     * - int read(byte data[]) throws IOException
     * - int read(byte data[], int start, int max) throws IOException
     *
     * We have used the first version of read() in previous sections to read a single character from the
     * keyboard(System.in). It returns -1 when an attempt is made to read at the end of the stream.
     *
     * The second version reads bytes from the input stream and puts them into data until either the array is full, the
     * end of stream is reached, or an error occurs. It returns the number of bytes read, or -1 when an attempt is made
     * to read at the end of the stream.
     *
     * The third version reads input nto data beginning at the location specified by start. Up to max bytes are stored.
     * It returns the number of bytes read, or -1 when an attempt is made to read at the end of the stream.
     *
     * They are throw an IOException when an error occurs.
     *
     * Check ByteIOExp.exp1(); to how we use the second version of read to get input
     * */

    /** 9.5.2 Writing console output
     * Java originally provided only byte streams for console output. Java 1.1 added character streams. For the most
     * portable code, character streams are recommended. Because System.out is a byte stream, however, byte­based
     * console output is still widely used. In fact, all of the programs in this Lesson up to this point have
     * used it!
     *
     * Since PrintStream is an output stream derived from OutputStream, it also implements the low­level method
     * write(). Thus, it is possible to write to the console by using write(). The simplest form of write() defined by
     * PrintStream is shown here:
     * void write(int byteval)
     * This method writes the byte specified by byteval to the file. Although byteval is declared as an integer, only
     * the low­order 8 bits are written.
     *
     * PrintStream supplies two additional output methods: printf( ) and format( ). Both give you detailed control
     * over the precise format of data that you output. For example, you can specify the number of decimal places
     * displayed, a minimum field width, or the format of a negative value. Although we won’t be using these methods
     * in the examples in this section, they are features that you will want to look into as you advance in your
     * knowledge of Java.
     * */

    /************************************** 9.6 Using the byte streams for files ************************************/

    /*
    * Java provides a number of classes and methods that allow you to read and write files. Of course, the most common
    * types of files are disk files. In Java, all files are byte­oriented, and Java provides methods to read and write
    * bytes from and to a file. Thus, reading and writing files using byte streams is very common. However, Java
    * allows you to wrap a byte­oriented file stream within a character­based object, which is shown later in this
    * chapter.
    *
    * To create a byte stream linked to a file, use FileInputStream or FileOutputStream. To open a file, simply
    * create an object of one of these classes, specifying the name of the file as an argument to the constructor.
    * Once the file is open, you can read from or write to it.
    * */

    /** 9.6.1 Inputting from a File
     * A file is opened for input by creating a FileInputStream object. Here is a commonly used constructor:
     * FileInputStream(String fileName) throws FileNotFoundException
     * Here, fileName specifies the name of the file you want to open. If the file does not exist, then
     * FileNotFoundException is thrown. FileNotFoundException is a subclass of IOException.
     *
     * To read from a file, we use int read() throws IOException, each time it's called, read() reads a single byte
     * from the file and return it as an integer value. It returns -1 when the end of the file is encountered.
     * When you done with a file, you must close it by calling close(). Closing a file releases the system resources
     * allocated to the file, allowing them to be used by another file. Failure to close a file can result in
     * "memory leaks" because of unused resources remaining allocated.
     *
     * Check  ByteIOExp.exp3 to see how we read a file with byte stream.
     *
     * */

    /** 9.6.1 Writing to a File
     * To open a file for output, create a FileOutputStream object. Here are two commonly used constructors:
     * - FileOutputStream(String fileName) throws FileNotFoundException
     * - FileOutputStream(String fileName, boolean append) throws FileNotFoundException
     * If the file cannot be created, then FileNotFoundException is thrown. In the first form, when an output file is
     * opened, any preexisting file by the same name is destroyed. In the second form, if append is true, then output
     * is appended to the end of the file. Otherwise, the file is overwritten. To write to a file, we will use the
     * void write(int byteval) method. This method writes the byte specified by byteval to the file. Although byteval is
     * declared as an integer, only the low­order 8 bits are written to the file. If an error occurs during writing,
     * an IOException is thrown. Once you are done with an output file, you must close it using close()
     *
     *
     * */

    /************************************** 9.7 Automatically closing a file  ************************************/

    /*
    * In the previous example programs have made explicit calls to close() to close a file once it is no longer needed.
    * However, beginning with JDK 7, Java has included a feature that offers another, more streamlined way to manage
    * resources, such as file streams, by automating the closing process. It is based on another version of the try
    * statement called try­with­resources, and is sometimes referred to as automatic resource management.
    *
    * The principal advantage of try­with­resources is that it prevents situations in which a file (or other resource)
    * is inadvertently not released after it is no longer needed. As explained, forgetting to close a file can result
    * in memory leaks and could lead to other problems. The try­with­resources statement has this general form:
    * try (resource­specification) {
    * // use the resource
    * }
    * Often, resource­specification is a statement that declares and initializes a resource, such as a file. In this
    * case, it consists of a variable declaration in which the variable is initialized with a reference to the
    * object being managed. When the try block ends, the resource is automatically released. In the case of a file,
    * this means that the file is automatically closed. (Thus, there is no need to call close() explicitly.) A
    * try­with­resources statement can also include catch and finally clauses.
    *
    * Beginning with JDK 9, it is also possible for the resource specification of the try to consist of a variable
    * that has been declared and initialized earlier in the program. However, that variable must be effectively final,
    * which means that it has not been assigned a new value after being given its initial value.
    *
    * The try­with­resources statement can be used only with those resources that implement the AutoCloseable
    * interface defined by java.lang. This interface defines the close() method. AutoCloseable is inherited by the
    * Closeable interface defined in java.io. Both interfaces are implemented by the stream classes, including
    * FileInputStream and FileOutputStream. Thus, try­with­resources can be used when working with streams, including
    * file streams.
    *
    * Check ByteIOExp.exp5("/tmp/test.txt"); for our first try with resource example. In our case, the
    * resource-specification is declaring a fileInputStream. It is important to understand that a resource declared in
    * the try statement is implicitly final. This means that you can’t assign to the resource after it has been
    * created. Also,the scope of the resource is limited to the try­with­resources statement.
    *
    * With JDK 10, you can use local variable type inference to specify the type of the resource declared in a
    * try­with­resources statement. To do so, specify the type as var. When this is done, the type of the resource is
    * inferred from its initializer. Check ByteIOExp.exp5("/tmp/test.txt");
    * */

    /** 9.7.1 Multiple resource in try with resources
     * You can manage more than one resource within a single try statement. To do so, simply separate each resource
     * specification with a semicolon. Check ByteIOExp.exp7("/tmp/test.txt","/tmp/test1.txt"); we use two resources
     * in the same try statement.
     * */

/**
 * There is one other aspect to try­with­resources that needs to be mentioned. In general, when a try block executes,
 * it is possible that an exception inside the try block will lead to another exception that occurs when the resource
 * is closed in a finally clause. In the case of a “normal” try statement, the original exception is lost, being
 * preempted by the second exception. However, with a try­with­resources statement, the second exception
 * is suppressed. It is not, however, lost. Instead, it is added to the list of suppressed exceptions associated
 * with the first exception. The list of suppressed exceptions can be obtained by use of the getSuppressed() method
 * defined by Throwable.
 * */


    /************************************** 9.8 Reading and writing binary data  ************************************/

    /*
    * So far, we have just been reading and writing bytes containing ASCII characters, but it is possible—indeed,
    * common—to read and write other types of data. For example, you might want to create a file that contains ints,
    * doubles, or shorts. To read and write binary values of the Java primitive types, you will use DataInputStream and
    * DataOutputStream.
    *
    * DataOutputStream implements the DataOutput interface. This interface defines methods that write all of Java’s
    * primitive types to a file. It is important to understand that this data is written using its internal, binary
    * format, not its human­readable text form. Several commonly used output methods for Java’s primitive types are
    * shown in the following table:
    * - void writeBoolean(boolean value): Writes the boolean value
    * - void writeByte(int value): Writes the low-order byte specified by value
    * - void writeChar(int value):
    * - void writeDouble(double value)
    * - void writeFloat(float value)
    * - void writeInt(int value)
    *
    * The constructor of DataOutputStream built upon an instance of OutputStream.
    *
    * DataInputStream implements the DataInput interface, which provides methods for reading all of Java’s primitive
    * types. These method are shown in the following table
    * - boolean readBoolean()
    * - byte readByte()
    * - char readChar()
    * ...
    * DataInputStream uses an InputStream instance as its foundation, overlaying it with methods that read the various
    * Java data types. Remember that DataInputStream reads data in its binary format, not its human­readable form.
    * Check ByteIOExp.exp8("/tmp/test1.txt");
    * */

    /************************************ 9.9 Use byte steam to compare two files  **********************************/

    /*
    * This project develops a simple, yet useful file comparison utility. It works by opening both files to be
    * compared and then reading and comparing each corresponding set of bytes. If a mismatch is found, the files
    * differ. If the end of each file is reached at the same time and if no mismatches have been found, then the
    * files are the same.
    * */

    /************************************ 9.10 Random access files  **********************************/

    /**
     * Up to this point, we have been using sequential files, which are files that are accessed in a strictly linear
     * fashion, one byte after another. However, Java also allows you to access the contents of a file in random order.
     * To do this, you will use RandomAccessFile, which encapsulates a random­access file. RandomAccessFile is not
     * derived from InputStream or OutputStream. Instead, it implements the interfaces DataInput and DataOutput, which
     * define the basic I/O methods. It also supports positioning requests—that is, you can position the file pointer
     * within the file. The constructor that we will be using is shown here:
     * RandomAccessFile(String fileName, String access) throws FileNotFoundException
     * Here, the name of the file is passed in fileName and access determines what type of file access is permitted.
     * If it is "r", the file can be read but not written. If it is "rw", the file is opened in read­write mode.
     * (The access parameter also supports "rws" and "rwd", which (for local devices) ensure that changes to the file
     * are immediately written to the physical device.)
     *
     * The method seek( ), shown here, is used to set the current position of the file pointer within the file:
     * void seek(long newPos) throws IOException
     * Here, newPos specifies the new position, in bytes, of the file pointer from the beginning of the file.
     * After a call to seek(), the next read or write operation will occur at the new file position. Because
     * RandomAccessFile implements the DataInput and DataOutput interfaces, methods to read and write the
     * primitive types, such as readInt( ) and writeDouble( ), are available. The read( ) and write( ) methods
     * are also supported.
     *
     * Check ByteIOExp.exp9("/tmp/test3.txt");
     *
     * Notice how each value is located. Since each double value is 8 bytes long, each value starts on an 8­byte
     * boundary. Thus, the first value is located at zero, the second begins at byte 8, the third starts at byte 16,
     * and so on. Thus, to read the fourth value, the program seeks to location 24.
     * */

    /************************************ 9.11 The console class  **********************************/

    /*
    * The Console class was added by JDK 6, and it is used to read from and write to the console. Console is
    * primarily a convenience class because most of its functionality is available through System.in and System.out.
    * However, its use can simplify some types of console interactions, especially when reading strings from the
    * console. Console supplies no constructors. Instead, a Console object is obtained by calling System.console().
    *
    * If a console is available, then a reference to it is returned. Otherwise, null is returned. A console may not be
    * available in all cases, such as when a program runs as a background task. Therefore, if null is returned, no
    * console I/O is possible.
    *
    * Console defines several methods that perform I/O, such as readLine( ) and printf( ). It also defines a method
    * called readPassword( ), which can be used to obtain a password. It lets your application read a password without
    * echoing what is typed. You can also obtain a reference to the Reader and the Writer that are attached to the
    * console. In general, Console is a class that you may find useful for some types of applications.
    *
    *
    * */

    /************************************ 9.12 USING Java's character streams  **********************************/

    /*
    * Java’s byte streams are both powerful and flexible. However, they are not the ideal way to handle character­based
    * I/O. For this purpose, Java defines the character stream classes. At the top of the character stream hierarchy
    * are the abstract classes Reader and Writer.
    * The following table shows method in Reader:
    * - abstract void close(): It closes the stream and releases any system resources associated with it.
    * - void mark(int readAheadLimit): It marks the present position in the stream.
    * - boolean markSupported(): It tells whether this stream supports the mark() operation.
    * - int read(): It reads a single character.
    * - int	read(char[] cbuf): It reads characters into an array.
    * - abstract int read(char[] cbuf, int off, int len): It reads characters into a portion of an array.
    * - int read(CharBuffer target): It attempts to read characters into the specified character buffer.
    * - boolean	ready(): It tells whether this stream is ready to be read.
    * - void reset(): It resets the input pointer to the previously set mark.
    * - long skip(long n): It skips n characters of inputs, returning the number of characters actually skipped.
    * - long transferTo(Writer writer)
    *
    * The following table shows method in Writer:
    * - Writer append(char c): It appends c to the end of the invoking output steam. Returns a reference to the invoking
    *                          stream.
    * - Writer append(CharSequence c): It appends c to the end of the invoking output steam. Returns a reference to the
    *                         invoking. CharSequence is an interface that defines read-only operations on a sequence
    *                         of characters.
    * - Writer append(CharSequence csq, int start, int end): It appends a subsequence of the specified character
    *          sequence to this writer.
    * - abstract void close(): It closes the stream, flushing it first. Subsequent write attempts will generate an
    *                          IOException.
    * - abstract void flush(): It causes any output that has been buffered to be sent to its destination. That is, it
    *                          flushes the output buffer.
    * - void write(char[] cbuf): It writes an array of characters.
    * - abstract void write(char[] cbuf, int off, int len): It writes a portion of an array of characters.
    * - void write(int c): It writes a single character. Notice the parameter is an int, you don't need to cast it to char
    * - void write(String str): It writes a string.
    * - void write(String str, int off, int len): It writes a portion of a string.
    *
    * */

    /** 9.12.1 Console Input Using character streams
     * For code that will be internationalized, inputting from the console using Java’s character­based streams is a
     * better, more convenient way to read characters from the keyboard than is using the byte streams. However,
     * since System.in is a byte stream, you will need to wrap System.in inside some type of Reader. The best class for
     * reading console input is BufferedReader, which supports a buffered input stream. However, you cannot construct
     * a BufferedReader directly from System.in. Instead, you must first convert it into a character stream.
     *
     * The following code creates a BufferedReader that is connected to the keyboard.
     * BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
     *
     * We use InputStreamReader, which converts bytes to characters, which can link to System.in After this statement
     * executes, br will be a character­based stream that is linked to the console through System.in.
     *
     * */

    /** 9.12.2 Reading characters
     * To read characters, we can use the read() method which are provided by BufferedReader. There are three version:
     * - int read() throws IOException: It reads a single Unicode character. It returns –1 when an attempt is made to
     *                                 read at the end of the stream.
     * - int read(char data[]) throws IOException: It reads characters from the input stream and puts them into data
     *                         until either the array is full, the end of stream is reached, or an error occurs. It
     *                         returns the number of characters read or –1 when an attempt is made to read at the end
     *                         of the stream.
     * - int read(char data[], int start, int max) throws IOException : It reads input into data beginning at the
     *                        location specified by start. Up to max characters are stored. It returns the number of
     *                        characters read or –1 when an attempt is made to read at the end of the stream.
     *  check CharacterIOExp.exp1();
     * */

    /** 9.12.3 Reading Strings
     * To read a string from the keyboard, use the version of readLine( ) that is a member of the BufferedReader class.
     * Its general form is shown here:
     * String readLine( ) throws IOException
     * It returns a String object that contains the characters read. It returns null if an attempt is made to read
     * when at the end of the stream.
     *  check CharacterIOExp.exp2();
     * */

    /** 9.12.4 Console Output using character streams
     * For real­world programs, the preferred method of writing to the console when using Java is through a PrintWriter
     * stream. PrintWriter is one of the character­based classes. As explained, using a character­based class for console
     * output makes it easier to internationalize your program.
     *
     * PrintWriter defines several constructors. The one we will use is shown here:
     * PrintWriter(OutputStream outputStream, boolean flushingOn)
     * Here, outputStream is an object of type OutputStream and flushingOn controls whether Java flushes the output
     * stream every time a println( ) method (among others) is called. If flushingOn is true, flushing automatically
     * takes place. If false, flushing is not automatic.
     *
     * PrintWriter supports the print( ) and println( ) methods for all types including Object. Thus, you can use
     * these methods in just the same way as they have been used with System.out. If an argument is not a primitive
     * type, the PrintWriter methods will call the object’s toString( ) method and then print out the result.
     *
     * As System.out is an object of OutputStream, so we can use it directly to build a PrintWriter.
     *
     * The advantage of using PrintWriter (or character streams) is that make your real-world application easier to
     * internationalize (support all language special characters)
     * check CharacterIOExp.exp3();
     * */

    /************************************ 9.13 File I/O USING character streams  **********************************/

    /*
    * Although byte­oriented file handling is often the most common, it is possible to use character­based streams
    * for this purpose. The advantage to the character streams is that they operate directly on Unicode characters.
    * Thus, if you want to store Unicode text, the character streams are certainly your best option. In general, to
    * perform character­based file I/O, you will use the FileReader and FileWriter classes.
    * */

    /** 9.13.1 Using fileWriter
     * FileWriter creates a Writer that you can use to write to a file. Two commonly used constructors are shown here:
     * FileWriter(String fileName) throws IOException
     * FileWriter(String fileName, boolean append) throws IOException
     * If append is true, then output is appended to the end of the file. Otherwise, the file is overwritten.
     * Either throws an IOException on failure. FileWriter is derived from OutputStreamWriter and Writer. Thus, it
     * has access to the methods defined by these classes.
     *
     * Check CharacterIOExp.exp4("/tmp/test4.txt"), notice we use try with resource to close the file automatically.
     * If you don't close file, nothing will be written.
     *
     * */

    /** 9.13.2 Using a fileReader
     * The FileReader class creates a Reader that you can use to read the contents of a file. A commonly used
     * constructor is shown here: FileReader(String fileName) throws FileNotFoundException
     *
     *  Check CharacterIOExp.exp5("/tmp/test4.txt"); In this example, we use a BufferedReader to wrap the FileReader.
     *  This gives us access to the method readLine(). And remember always close the stream when you are done.
     * */

    /************************************ 9.14 NIO packages  **********************************/

    /**
     * NIO(New I/O) was added to Java by JDK 1.4. It supports a channel­based approach to I/O operations. The NIO
     * classes are contained in java.nio and its subordinate packages, such as java.nio.channels and java.nio.charset.
     * NIO is built on two foundational items:
     * - buffers
     * - channels.
     * A buffer holds data. A channel represents an open connection to an I/O device, such as a file or a socket.
     * In general, to use the new I/O system, you obtain a channel to an I/O device and a buffer to hold data. You
     * then operate on the buffer, inputting or outputting data as needed.
     *
     * Two other entities used by NIO are:
     * - charsets
     * - selectors.
     * A charset defines the way that bytes are mapped to characters. You can encode a sequence of characters into
     * bytes using an encoder. You can decode a sequence of bytes into characters using a decoder. A selector supports
     * key­based, non­blocking, multiplexed I/O. In other words, selectors enable you to perform I/O through multiple
     * channels. Selectors are most applicable to socket­backed channels.
     *
     * Beginning with JDK 7, NIO was substantially enhanced, so much so that the term NIO.2 is often used. The
     * improvements included three new packages:
     * - java.nio.file
     * - java.nio.file.attribute
     * - java.nio.file.spi
     * which contain several new classes, interfaces, and methods; and direct support for stream­based I/O. The
     * additions greatly expanded the ways in which NIO can be used, especially with files.
     *
     * It is important to understand that NIO does not replace the I/O classes found in java.io, which are discussed
     * in this section. Instead, the NIO classes are designed to supplement the standard I/O system, offering an
     * alternative approach, which can be beneficial in some circumstances.
     *
     * We will examine NIO in a separate section.
     * */

    /************************************ 9.15 Using Java's type wrappers  **********************************/

    /*
    * As you know, Java’s println() method provides a convenient way to output various types of data to the console,
    * including numeric values of the built­in types, such as int and double. Thus, println() automatically converts
    * numeric values into their human­readable form. However, methods like read() do not provide a parallel
    * functionality that reads and converts a string containing a numeric value into its internal, binary format.
    * For example, there is no version of read() that reads a string such as "100" and then automatically converts it
    * into its corresponding binary value that is able to be stored in an int variable. Instead, Java provides various
    * other ways to accomplish this task. Perhaps the easiest is to use one of Java’s type wrappers.
    *
    * Java’s type wrappers are classes that encapsulate, or wrap, the primitive types. Type wrappers are needed
    * because the primitive types are not objects. This limits their use to some extent. For example, a primitive
    * type cannot be passed by reference. To address this kind of need, Java provides classes that correspond to each
    * of the primitive types. The type wrappers are
    * - Double: static double parseDouble(String s) throws NumberFormatException
    * - Float: static float parseFloat(String s) throws NumberFormatException
    * - Long: static long parseLong(String s) throws NumberFormatException
    * - Integer: static int parseInt(String s) throws NumberFormatException
    * - Short: static short parseShort(String s) throws NumberFormatException
    * - Byte: static byte parseByte(String s) throws NumberFormatException
    * - Character: None
    * - Boolean: None
    * These classes offer a wide array of methods that allow you to fully integrate the primitive types into Java’s
    * object hierarchy. As a side benefit, the numeric wrappers also define methods that convert a numeric string into
    * its corresponding binary equivalent. Several of these conversion methods are shown here. Each returns a binary
    * value that corresponds to the string.
    *  check CharacterIOExp.exp6();
    * */

    /** 9.15.1 Convert primitive types to String
     * Sting Class provide a static method valueOf(). This method has the following variants, which depend on the
     * passed parameters. This method returns the string representation of the passed argument:
     * - valueOf(boolean b): Returns the string representation of the boolean argument.
     * - valueOf(char c): Returns the string representation of the char argument.
     * - valueOf(char[] data): Returns the string representation of the char array argument.
     * - valueOf(char[] data, int offset, int count): Returns the string representation of a specific subarray of
     *                           the char array argument.
     * - valueOf(double d): Returns the string representation of the double argument.
     * - valueOf(float f): Returns the string representation of the float argument.
     * - valueOf(int i): Returns the string representation of the int argument.
     * - valueOf(long l): Returns the string representation of the long argument.
     * - valueOf(Object obj): Returns the string representation of the Object argument.
     *
     * */

    /** 9.15.2 What else can the Primitive type wrapper classes do?
     * The primitive type wrappers provide a number of methods that help integrate the primitive types into the object
     * hierarchy. For example, various storage mechanisms provided by the Java library, including maps, lists,
     * and sets, work only with objects. Thus, to store an int, for example, in a list, it must be wrapped in
     * an object. Also, all type wrappers have a method called compareTo( ), which compares the value contained
     * within the wrapper; equals( ), which tests two values for equality; and methods that return the value of
     * the object in various forms. The topic of type wrappers is taken up again in section 11, when
     * autoboxing is discussed.
     * */

    /** 9.15.3 Scanner class
     * Another way to convert a numeric string into its internal, binary format is to use one of the methods defined
     * by the Scanner class, packaged in java.util. Scanner reads formatted (that is, human­readable) input and
     * converts it into its binary form. Scanner can be used to read input from a variety of sources,
     * including the console and files. Therefore, you can use Scanner to read a numeric string entered at the
     * keyboard and assign its value to a variable. Although Scanner contains far too many features to describe in
     * detail, the following illustrates its basic usage. To use Scanner to read from the keyboard, you must first
     * create a Scanner linked to console input. To do this, you will use the following constructor:
     * Scanner(InputStream from)
     * This creates a Scanner that uses the stream specified by from as a source for input. You can use this
     * constructor to create a Scanner linked to console input, as shown here:
     * Scanner scanner= new Scanner(System.in)
     * This works because System.in is an object of type InputStream. After this line executes, scanner can be used to
     * read input from the keyboard.
     *
     * Once you have created a Scanner, it is a simple matter to use it to read numeric input. Here is the general
     * procedure:
     * 1. Determine if a specific type of input is available by calling one of Scanner’s hasNextX methods, where X is
     *    the type of data desired.
     * 2. If input is available, read it by calling one of Scanner’s nextX methods. As the preceding indicates,
     *    Scanner defines two sets of methods that enable you to read input. The first are the hasNext methods.
     *    These include methods such as hasNextInt( ) and hasNextDouble( ), for example. Each of the hasNext methods
     *    returns true if the desired data type is the next available item in the data stream, and false otherwise.
     *    For example, calling hasNextInt( ) returns true only if the next item in the stream is the human­readable
     *    form of an integer. If the desired data is available, you can read it by calling one of Scanner’s next
     *    methods, such as nextInt( ) or nextDouble( ). These methods convert the human­readable form of the data
     *    into its internal, binary representation and return the result.
     *
     * Check CharacterIOExp.exp7();
     *
     * */
    public static void main(String[] args) throws IOException {

        /** 9.5.1 Reading console input*/
       // ByteIOExp.exp1();
        /** 9.5.2 Writing console output*/
        // ByteIOExp.exp2();
        // %s means first argument is string, %n means jump line
        // System.out.printf("Hello %s!%n", "World");

        /** 9.6.1 Inputting from a File*/
        // ByteIOExp.exp3("/tmp/test.txt");

        /** 9.6.2 Writing to a file*/
      // ByteIOExp.exp4("/tmp/test.txt","/tmp/test1.txt");

       /** 9.7 Automatically closing a file*/
       // ByteIOExp.exp5("/tmp/test.txt");
      //  ByteIOExp.exp6("/tmp/test.txt");
       // ByteIOExp.exp7("/tmp/test.txt","/tmp/test1.txt");

        /** 9.8 Reading and writing binary data */
       // ByteIOExp.exp8("/tmp/test1.txt");

        /** 9.9 Compare two file*/
       /* CompareFiles compareFiles=new CompareFiles("/tmp/test.txt","/tmp/test1.txt");
        boolean result=compareFiles.doCompare();
        System.out.println(result);

        //cleanCompare is from book.
        compareFiles.cleanCompare();*/

       /** 9.10 Random access file*/
       //ByteIOExp.exp9("/tmp/test3.txt");

       /** 9.11 Console class*/
        // We can't test it, because console class does not work in IDE.
      // ByteIOExp.exp10();

        /** 9.12.2 Read character */
       // CharacterIOExp.exp1();
      /** 9.12.3 Read string*/
      // CharacterIOExp.exp2();
      /** 9.12.4 Output characters*/
      // CharacterIOExp.exp3();
        /** 9.13.1 output using fileWriter*/
       // CharacterIOExp.exp4("/tmp/test4.txt");
       // CharacterIOExp.exp5("/tmp/test4.txt");

        /** 9.15 Java type's wrapper */
       // CharacterIOExp.exp6();
        // scanner class exmaple
CharacterIOExp.exp7();
    }
}
