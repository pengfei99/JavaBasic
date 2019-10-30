package org.pengfei.Lesson00_Java_Basics.S09_Using_IO;

import org.pengfei.Lesson00_Java_Basics.S09_Using_IO.source.ByteIOExp;
import org.pengfei.Lesson00_Java_Basics.S09_Using_IO.source.CompareFiles;

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
    * A stream is linked to a physical device by theJava I/O system. All streams behave in the same manner,
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
     * - BufferedInputStream: Buffered input stream
     * - BufferedOutputStream: Buffered output stream
     * - ByteArrayInputStream: Input steam that reads from a byte array
     * - ByteArrayOutputStream: Output steam that writes to a byte array
     * - DataInputStream: An input stream that contains methods for writing the java standard data types.
     * - ...
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
    * - void reset(): It resets the stream.
    * - long skip(long n): It skips characters.
    *
    * The following table shows method in Writer
    * */

    public static void main(String[] args){

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

    }
}
