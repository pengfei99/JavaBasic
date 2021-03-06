package org.pengfei.Lesson01_Java_Standard_API.S05_Exploring_nio;

import org.pengfei.Lesson01_Java_Standard_API.S05_Exploring_nio.source.ChannelBasedIOExample;
import org.pengfei.Lesson01_Java_Standard_API.S05_Exploring_nio.source.PathFilesOperationExample;
import org.pengfei.Lesson01_Java_Standard_API.S05_Exploring_nio.source.StreamBasedIOExample;

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
     *             For more details of selector, please visit employes:pengfei.liu:java:all_io:nio_learning
     *
     *   check ChannelBasedIOExample.exp9(); for a collector example            
     * */

    /*************************************** 5.4 Enhancements added by NIO ******************************************/

    /*
    * Beginning with JDK 7, NIO has been enhanced with several new interfaces(e.g. Path), classes, and
    * three new packages have been added:
    * - java.nio.file
    * - java.nio.file.attribute
    * - java.nio.file.spi
    * We will see some of them in this section
    * */

    /** 5.4.1 The Path Interface
     *
     * Path interface encapsulates a path to a file. Path is the glue that binds together many of the NIO file-based
     * features. Path is packaged in java.nio.file, and it inherits the following interfaces:
     * - Watchable
     * - Iterable<Path>
     * - Comparable<Path>
     * - Watchable
     *
     * It defines a number of methods that operate on the path:
     * - int compareTo(Path p): Compares two abstract paths lexicographically.
     * - boolean endsWith(Path p): Returns true if the invoking path ends with the given path p.
     * - boolean endsWith(String p): constructed by converting the given path string, in exactly the manner
     *                     specified by the endsWith(Path) method.
     * - Path getFileName(): Returns the name of the file or directory denoted by this path as a Path object.
     * - FileSystem	getFileSystem(): Returns the file system that created this object.
     * - Path getName(int index): Returns a Path object that contains the name of the path element specified by index.
     *                      The leftmost element is at index 0, which is the element nearest the root. The rightmost
     *                      element is at getNameCount()-1
     * - int getNameCount(): Returns the number of name elements beyond the root directory in the invoking path.
     * - Path getParent(): Returns the parent path(entire path except for the name of the file), or null if this
     *                    path does not have a parent.
     * - Path getRoot(): Returns the root component of this path as a Path object, or null if this path does not have
     *                a root component.
     * - boolean isAbsolute(): Tells whether or not this path is absolute.
     * - Path normalize(): Returns a path that is this path with redundant name elements eliminated.
     * - Path relativize(Path other): Constructs a relative path between this path and a given path.
     * - Path resolve(Path p): if p is absolute, p is returned. Otherwise, if p does not contain a root, p is prefixed
     *                     by the root specified by the invoking Path and result is returned. If p is empty, the invoking
     *                     Path is returned.
     * - Path resolve(String p): Constructed by converting string to path, and resolves it against the invoking Path in
     *               the manner specified by the resolve method.
     * - Path resolveSibling(Path other): Resolves the given path against this path's parent path.
     * - Path resolveSibling(String other): Converts a given path string to a Path and resolves it against this path's
     *                   parent path in exactly the manner specified by the resolveSibling method.
     * - boolean startsWith(Path p): Returns true if the invoking path starts with the given path p.
     * - boolean startsWith(String p): Constructed by converting the given path string, in exactly the manner specified
     *                    by the startsWith(Path) method.
     * - Path subpath(int beginIndex, int endIndex): Returns a relative Path that is a subsequence of the name
     *                    elements of this path.
     * - Path toAbsolutePath(): Returns a Path object representing the absolute path of this path.
     * - File toFile(): Returns a File object representing this path.
     *
     * Pay attention to getName(), getNameCount(), resolve(), these method are very useful.
     *
     * Beginning with JDK 11, an important new static factory method called of() was added. It returns a Path instance
     * from either a path name of a URI:
     * - static Path of(String pathname, String ... parts): Returns a Path that encapsulates specified path. If the
     *             parts varargs parameter is not used, then the path must be specified in its entirety by pathname.
     *             Otherwise, the arguments parts are added to pathname(usually with an appropriate separator) to form
     *             the entire path. If the path specified is syntactically invalid, an InvalidPathException will occur.
     * - static Path of(URI uri): Returns a path corresponding to uri.
     * */

    /** 5.4.2 The Files Class
     * Many fo the actions that you perform on a file are provided by static methods within the Files class. The file
     * to be acted upon is specified by its Path. Thus, the Files methods use a Path to specify the file that is being
     * operated upon. The following method are mainly used:
     * - static Path copy(Path source, Path target, CopyOption... options): Copy a file to a target file. You can
     *                 specify
     * - static Path createDirectory(Path dir, FileAttribute<?>... attrs): Creates a new directory whose path is dir.
     *                 attrs specifies the attribute of directory.
     * - static Path createFile(Path path, FileAttribute<?>... attrs): Creates a new and empty file, failing if the
     *                file already exists.
     * - static void delete(Path path): Deletes a file.
     * - static boolean	deleteIfExists(Path path): Deletes a file if it exists.
     *
     * You can find the full method list https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html
     *
     * Notice that several methods take an argument of type OpenOption. This is an interface that describes how to
     * open a file. It is implemented by the StandardOpenOption class, which defines an enumeration:
     * - APPEND: Causes output to be written to the end of the file.
     * - CREATE: Creates the file if it does not already exists
     * - CREATE_NEW: Creates the file only if it does not already exists
     * - DELETE_ON_CLOSE: Deletes file when its closed.
     * - DSYNC: Causes changes to the file to be immediately written to the physical file. Normally, changes to a
     *             file are buffered by the file system in the interest of efficiency, being written to the
     *             file only as needed.
     * - READ: Opens the file for input operations.
     * - SPARSE: Indicates to the file system that the file is sparse, meaning that it may not be completely filled
     *           with data. If the file system does not support sparse files, this option is ignored.
     * - SYNC: Causes changes to the file or its metadata to be immediately written to the physical file. Normally,
     *         changes to a file are buffered by the file system in the interest of efficiency, being written to the
     *         file only as needed.
     * - TRUNCATE_EXISTING: Causes a preexisting file opened for output to be reduced to zero length.
     * - WRITE: Opens the file for output operations.
     * */

    /** 5.4.3 The Paths Class
     * Because Path is an interface, you can't create an instance of Path directly by using a constructor. You need to
     * call a method which can return one Path object. The Paths class provide two forms of get() method which return
     * a Path object:
     * - static Path get(String pathname, String ... parts): It builds a path in two ways. First, if parts is not used
     *               then the path must be specified in its entirety by pathname. Second, you can pass the path in
     *               pieces, pathname is the first part, parts are the subsequent elements.
     * - static Path get(URI uri): Returns a Path corresponding uri.
     *
     * But since JDK 11, the Paths.get() method are not recommended. We should use the new Path.of() methods which we
     * discussed in section 5.4.1.
     *
     * Note obtaining a Path to a file does not open or create a file. It simply creates an object that encapsulates
     * the file's directory path.
     *
     * */

    /** 5.4.4 The File Attribute Interfaces
     * Associated with a file is a set of attributes. These attributes include:
     * - file's creation time
     * - file's last modification time
     * - is directory or not
     * - size
     * - etc.
     *
     * These attributes are organized in a hierarchy of interfaces in java.nio.file.attribute. At the top is
     * BasicFileAttributes. It has the following methods which returns attributes of file system.
     * - FileTime creationTime(): Returns the creation time. If it's not provided by the file system, an implementation
     *                  dependent value is returned.
     * - Object	fileKey(): Returns an object that uniquely identifies the given file, or null if a file key is not
     *                 available.
     * - boolean isDirectory(): Tells whether the file is a directory.
     * - boolean isOther(): Tells whether the file is something other than a regular file, directory, or symbolic link.
     *                 returns true if it is a file, dir or symbolic link.
     * - boolean isRegularFile(): Returns true if the file is a regular file.
     * - boolean isSymbolicLink(): Returns true the file is a symbolic link.
     * - FileTime lastAccessTime(): Returns the time of last access.
     * - FileTime lastModifiedTime(): Returns the time of last modification.
     * - long size(): Returns the size of the file (in bytes).
     *
     * From BasicFileAttributes two interfaces are derived:
     * - DosFileAttributes: It describes those attributes related to the FAT file system as first defined by DOS. It
     *              has following methods:
     *              -- boolean isArchive(): Returns true if the file is flagged for archiving.
     *              -- boolean isHidden(): Returns true if the file is hidden.
     *              -- boolean isReadOnly(): Returns true if the file is read-only.
     *              -- boolean isSystem(): Returns true if the file is flagged as a system file.
     * - PosixFileAttributes: It encapsulates attributes defined by the POSIX(Portable Operating system interface)
     *                 standards. It has following methods:
     *              -- GroupPrincipal group(): Returns the group owner of the file.
     *              -- UserPrincipal owner(): Returns the owner of the file.
     *              -- Set<PosixFilePermission>	permissions(): Returns the permissions of the file.
     *
     * There are various ways to access a file's attributes. First, you can obtain an object that encapsulates a file's
     * attributes by calling readAttributes(Path path, Class<A> attrType, LinkOption ... opts), which is a static
     * method of Files class. This method returns a reference to an object that specifies the attributes associated
     * with the file passed in path. The type of attributes is specified by attrType. For example, to obtain basic file
     * attributes, pass BasicFileAttributes.class as attrType. For Dos attributes, use DosFileAttributes.class. Optional
     * link is optional, if not specified, symbolic links are followed.
     *
     * Second way is to call getFileAttributeView() defined by Files class. NIO defines several attribute view
     * interface, including:
     * - AttributeView
     * - BasicFileAttributeView
     * - DosFileAttributeView
     * - PosixFileAttributeView
     * Files class includes some static method which can return some attribute directly. For example, isHidden(),
     * isWriteable().
     *
     * Note, not all file systems support all possible attributes. For example, the DOS file attributes apply to
     * older FAT file system.
     *
     * */

    /** 5.4.5 The FileSystem, FileSystems and FileStore classes
     *
     * You can easily access the file system through the FileSystem and FileSystems classes packaged in java.nio.file.
     * You can even create a new file system by using newFileSystem() method defined by FileSystems class. The
     * FileStore class encapsulates the file storage system.
     * */

    /*************************************** 5.5 Using the NIO System ******************************************/

    /*
    * In the past, the primary purpose of NIO was channel-based I/O. Beginning with JDK 7, we can now use NIO for
    * steam-based I/O and for performing file-system operations.
    * */

    /** 5.5.1 Use NIO for channel based I/O
     *
     * An important use of NIO is to access a file via a channel and buffers.
     *
     * A. Reading a File via a Channel
     * To read a File via a Channel, we need to follow the below steps:
     * 1. Create a Path that describes the file
     * 2. Open the file with Path and establish a channel
     * 3. Allocate a buffer which will be used by the channel
     * 4. call read() on the channel, passing reference to the buffer. read() fills the buffer with data. Each call
     *    to read() reads the next buffer's worth of bytes from the channel. It returns -1 when there is nothing
     *    (end of file).
     *
     * Check  ChannelBasedIOExample.exp1("/tmp/test1"); and ChannelBasedIOExample.exp2("/tmp/test1");
     *
     * B. Reading a File by mapping it to a buffer.
     * The advantage of this solution is that the buffer automatically contains the contents of the file. No explicit
     * read operation is necessary. To do this, we need to follow below steps:
     * 1. Create a Path
     * 2. Obtain a channel with Files.newByteChannel().
     * 3. Map the channel to a buffer by calling map() on the channel.
     *
     * Note map() methods is defined by FileChannel, so we need to cast SeekableByteChannel to FileChannel. The
     * MappedByteBuffer map(FileChannel.MapMode mode, long pos, long size) throws IOException
     * method causes the data in the file to be mapped into a buffer in memory. The value in mode determines what type
     * operations are allowed. It can have three value:
     * - MapMode.READ_ONLY: Read a file
     * - MapMode.READ_WRITE: Read and write a file
     * - MapMode.PRIVATE: creates a private copy of the file, and changes to the buffer do not affect the underlying
     *                   file.
     * Argument pos specifies the start position in the file for mapping, size specify the number of bytes to be mapped.
     *
     * C. Writing to a File via a Channel
     * To write data to a file via a Channel, we need to follow below steps:
     * 1. Allocate a buffer
     * 2. Write data to the buffer
     * 3. Create a Path and open it with a Channel
     * 4. Perform an explicit write operation to write that data to a file.
     * Check ChannelBasedIOExample.exp4("/tmp/test1"); If the file which we write exist already and has data in it, this
     * program will overwrite only the first 18 byte of the file, the reset is unchanged.
     *
     *
     * Note, in all examples, we used rewind() to reset current position to 0, in some case, we could use flip() method
     * to reset current position to 0 and set the limit to the previous current position. In our example 4, when finish
     * writing the buffer, the current position of the buffer equals its limit, so flip() can replace rewind(). But
     * in some case, it can't. Check ChannelBasedIOExample.exp5(); for flip example
     *
     * D. Writing a file by mapping it to a buffer
     * The advantage of this solution is that the data written to the buffer will automatically be written to the
     * file. No explicit write operation is necessary. To do this, we need to follow below steps:
     * 1. Create a Path and open it with a Channel
     * 2. Map the channel to a buffer by calling map()
     * 3. write data to buffer
     *
     * Check ChannelBasedIOExample.exp6("/tmp/test1"); If the file which we write exist already and has data in it, this
     * program will overwrite only the first 18 byte of the file, the reset is unchanged.
     *
     * E. Copying a File Using NIO
     * NIO simplifies several types of file operations such as copy. Files class provide a static method:
     * - static Path copy(Path src, Path dest, CopyOption ... options) throws IOException.
     * The options are:
     * - COPY_ATTRIBUTES: Request that the file's attributes be copied
     * - NOFOLLOW_LINKS: Do not follow symbolic links.
     * - REPLACE_EXISTING: Overwrite a preexisting file
     * Other options may be supported, depending on the implementation.
     *
     * check ChannelBasedIOExample.exp7("/tmp/test","/tmp/test1");
     *
     * */


    /** 5.5.2 Use NIO for Stream based I/O
     * Beginning with JDK 7, we can use NIO to open an I/O stream. Once you have a Path object, you can call its two
     * static methods:
     * - static InputStream newInputStream(Path path, OpenOption ... options): Open a file for stream based input,
     *                if no options are specified, the StandardOpenOption.READ were passed. Once opened, you can
     *                use any of the methods defined by InputStream(e.g. read()).
     *                Check StreamBasedIOExample.exp1("/tmp/test1");
     *                We can also wrap the input stream to more advance stream such as a BufferedInputStream. So we
     *                can perform buffer operation such as mark() and reset().
     *                Check StreamBasedIOExample.exp2("/tmp/test1");
     * - static OutputStream newOutputStream(Path path, OpenOption ... options): Open a file for output. If options are
     *                not specified, default option StandardOpenOption.WRITE, StandardOpenOption.CREATE and
     *                StandardOpenOption.TRUNCATE_EXISTING will be applied.
     *
     */

    /** 5.5.3 Use NIO for Path and File system operations
     * Compare to File class which we have examined in section 4, NIO offer a better way to perform its functions. For
     * example:
     * - support for symbolic link
     * - better support for directory tree traversal
     * - improved handling of metadata
     * - etc.
     *
     * A. Obtain information about a Path and a File
     * To obtain information about a Path, we reply on methods of Path interface, such as:
     * - getName()
     * - getParent()
     * - toAbsolutePath()
     *
     * For file, we rely on methods of Files Class, such as:
     * - isExecutable()
     * - isHidden()
     * - isReadable()
     * - isWritable()
     * - exists()
     * Note some file attributes are obtained by requesting a list of attributes by calling Files.readAttributes().
     * Check  PathFilesOperationExample.exp1("/tmp/test");
     *
     * B. List the contents of a Directory
     * If a path describes a directory, then you can read the contents of that directory by using static methods
     * defined by Files.
     * 1. Create a directory stream by calling static DirectoryStream<Path> newDirectoryStream(Path path). It will throw
     *    an IOException if	an I/O error occurs and a NotDirectoryException (which is a subclass of IOException)
     *    if the specified path is not a directory. A SecurityException is also possible if access to the directory
     *    is not permitted. DirectoryStream<Path> implements AutoCloseable interface, so we can use try with resource.
     *    It also implements Iterable<Path>, we can get an iterator or use for each to loop over its contents.
     *    Check PathFilesOperationExample.exp2("/tmp"); to see how we get contents of a directory
     *
     *    We can also filter the content of a directory. we can use a wildcard option when creating the DirectoryStream.
     *    Check  PathFilesOperationExample.exp3("/tmp");
     *
     *    Another way to filter the content is to use DirectoryStream.Filter interface. It defines a method:
     *    boolean accept(T entry) throws IOException. In our case, T is Path. This offers the advantage of being able
     *    to filter a directory based on something other than a filename. For example, we can filter based on file
     *    size, creation date, etc. Check PathFilesOperationExample.exp4("/tmp");
     *
     * 2. Use walkFileTree() to List a Directory Tree
     *    The preceding examples have obtained the contents of only a single directory. But, sometimes you will want
     *    to obtain a list of files in a directory tree. We can use walkFileTree() method in Files class.
     *    - static Path walkFileTree(Path rootPath, FileVisitor<? super Path > fv) throws IOException.
     *    Here fv is an instance of FileVisitor which defines how the directory tree is traversed. fv also gives you
     *    access to the directory information.
     *
     *    FileVisitor is an interface, it defines following methods:
     *    - FileVisitResult postVisitDirectory(T dir, IOException exc): Called after a directory has been visited.
     *               The directory is passed in dir, and any IOException is passed in exc. If exc is null, no
     *               exception occurred. The result is returned.
     *    - FileVisitResult preVisitDirectory(T dir, BasicFileAttributes attrs): Called before a directory has been
     *               visited. The directory is passed in dir, and the attributes associated with the directory are
     *               passed in attrs. The result is returned. To examine the directory, return FileVisitResult.CONTINUE
     *    - FileVisitResult visitFile(T file, BasicFileAttributes attrs): Called when a file is visited. the file is
     *              passed in file, and the attributes associated with the file are passed in attrs. The result is
     *              returned.
     *    - FileVisitResult visitFileFailed(T file, IOException exc): Invoked for a file that could not be visited.
     *    Note all four methods returns a FileVisitResult, which has values:
     *    - CONTINUE: To continue traversing the directory and subdirectory.
     *    - SKIP_SIBLINGS: For preVisitDirectory(), it bypass the directory and its siblings and prevent
     *                postVisitDirectory() from being called.
     *    - SKIP_SUBTREE: It bypass the directory and sub-directories
     *    - TERMINATE: It stops the directory traversal.
     *    You can define your own FileVisitor, but it's easier to extends SimpleFileVisitor and overwrites the
     *    default implementation.
     *    Check PathFilesOperationExample.exp5("/tmp");
     *    Note, we can also watch a directory for changes by using java.nio.file.WatchService.
     * */

    /** 5.5.4 Use NIO for socket communication
     *
     *
     */


    public static void main(String[] args){
        /** NIO for channel based I/O*/
        // read file via channel
        // ChannelBasedIOExample.exp1("/tmp/test1");
        // ChannelBasedIOExample.exp2("/tmp/test1");

        // map file to buffer
        // ChannelBasedIOExample.exp3("/tmp/test1");

        // write data to file via channel
         // ChannelBasedIOExample.exp4("/tmp/test1");

        // flip example
       // ChannelBasedIOExample.exp5();

        // write data via buffer mapping
      //  ChannelBasedIOExample.exp6("/tmp/test1" );

        // copy example
        // ChannelBasedIOExample.exp7("/tmp/test","/tmp/test1");

        /** NIO for stream based I/O*/
        // read file via inputStream
       // StreamBasedIOExample.exp1("/tmp/test1");

        //read file via buffered input stream
        //StreamBasedIOExample.exp2("/tmp/test1");

        //write a file via buffered output stream
       // StreamBasedIOExample.exp3("/tmp/test");

        /** Path and file operation*/
        // get basic attributes of path and file
       // PathFilesOperationExample.exp1("/tmp/test");

        // iterate over a directory and display its contents
       // PathFilesOperationExample.exp2("/tmp");

        // filter the contents of a directory
       // PathFilesOperationExample.exp3("/tmp");
      //  PathFilesOperationExample.exp4("/tmp");

        // show directory contents with walkFileTree
       // PathFilesOperationExample.exp5("/tmp");

        /** NIO for server communication*/
       // ChannelBasedIOExample.exp8();

        /** NIO with selector */
        ChannelBasedIOExample.exp9();

    }
}
