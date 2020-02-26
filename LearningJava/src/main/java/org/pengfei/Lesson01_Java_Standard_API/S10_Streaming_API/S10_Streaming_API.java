package org.pengfei.Lesson01_Java_Standard_API.S10_Streaming_API;

import org.pengfei.Lesson01_Java_Standard_API.S10_Streaming_API.source.StreamExample;

public class S10_Streaming_API {

    /********************************************* 10.1 Introduction **************************************************/

    /*
    * Stream API and lambda expressions are the two most important features added to Java recently. The steam
    * API is designed with Lambda expressions in mind. It provides some of the most significant demonstrations
    * of the power that LE bring to java.
    *
    * The key aspect of the stream API is to perform very sophisticated operations that search, filter, map or
    * otherwise manipulate data. For example, using the stream API, you can construct sequences of actions
    * that resemble, in concept, the type of database queries for which you might use SQL. Furthermore, in many cases,
    * such actions can be performed in parallel, thus providing a high level of efficiency, especially when large
    * data sets are involved.
    * */

    /********************************************* 10.2 Stream Basics ***********************************************/

    /*
    * A stream is a conduit for data, which represents a sequence of objects. It can operate on a data source, such
    * as an array or a collection. A steam, itself, never provides storage for the data. It simply moves data,
    * possibly filtering, sorting, or otherwise operating on that data in the process. In general, a stream
    * does not change the data source.
    * */

    /** 10.2.1 Stream Interfaces
     *
     * The stream API defines several stream interfaces, which are packaged in java.util.stream and contained in
     * the java.base module.
     *
     * BaseStream interface
     * BaseStream is the core interface of the stream API, it defines the basic functionality available in all streams.
     * It has the following form:
     * - interface BaseStream<T, S extends BaseStream<T, S>>: T specifies the type of the element in the stream, and
     *          S specifies the type of stream that extends BaseStream.
     * BaseStream extends the AutoCloseable interface. Thus, a stream can be managed in a try with resources statement.
     * In general, only those streams whose data source requires closing(such as those connected to a file) will
     * need to be closed. In most cases, such as those in which the data source is a collection, there is no need
     * to close the stream.
     *
     * BaseStream defines the following methods:
     * - void close(): Closes the invoking stream, causing all close handlers for this stream pipeline to be called.
     * - boolean isParallel(): Returns true if the invoking stream is parallel. Returns false if the stream is
     *          sequential.
     * - Iterator<T> iterator(): Obtains an iterator for the elements of this stream and returns a reference to it. (T)
     * - S onClose(Runnable closeHandler): Returns a new stream with the close handler. This handler will be called
     *           when the stream is closed (I)
     * - S parallel(): Returns stream that is parallel based on the invoking stream. If the invoking stream is already
     *           parallel, then return that stream. (I)
     * - S sequential(): Returns stream that is sequential based on the invoking stream. If the invoking stream is already
     *              sequential, then return that stream. (I)
     * - Spliterator<T> spliterator(): Returns a spliterator for the elements of this stream. (T)
     * - S unordered(): Returns an unordered stream based on the invoking stream. If the invoking stream is already
     *      *              unordered, then return that stream. (I)
     *
     * From BaseStream are derived several types of stream interface
     * - Stream: Operates on object references. You can find some important methods:
     *           -- boolean allMatch(Predicate<? super T> predicate): Returns whether all elements of this stream
     *                       match the provided predicate.
     *           -- boolean anyMatch(Predicate<? super T> predicate): Returns whether any elements of this stream
     *                       match the provided predicate.
     *           -- <R,A> R	collect(Collector<? super T,A,R> collector): Collects elements into a container, which
     *                       is changeable, and returns the container. This is called a mutable reduction operation.
     *                       Here R specifies the type of the resulting container and T specifies the element type
     *                       of the invoking stream. A specifies the internal accumulated type. The collector
     *                       specifies how the collection process works. (T)
     *           -- long count(): Counts the number of elements in the stream and returns the result. (T)
     *           -- Stream<T> filter(Predicate<? super T> predicate): Produces a stream that contains those elements
     *                       from the invoking stream that satisfy the predicate. Predicate is a generic functional
     *                       interface declared in java.util.function. Its abstract method is boolean test(T objRef).
     *                       We usually use a lambda function to provide the implementation of test(). It returns
     *                       true if the obj satisfies the predicate, otherwise false.(I)
     *           -- void forEach(Consumer<? super T> action): Performs an action for each element of this stream.
     *                       Consumer is a generic functional interface declared in java.util.function. Its abstract
     *                       method is void accept(T objRef). We usually use a lambda function to provide the
     *                       implementation of accept(). (T)
     *           -- <R> Stream<R> map(Function<? super T,? extends R> mapper): Returns a stream consisting of the
     *                       results of applying the given function to the elements of this stream. (I)
     *           -- DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper): Applies mapper function to the
     *                       elements from the invoking stream. Returns a DoubleStream consisting of the results.
     *                       Similar methods as mapToInt(), mapToLong(). (I)
     *           -- Optional<T> max(Comparator<? super T> comparator): Returns the maximum element of this stream
     *                       according to the provided Comparator. (T)
     *           -- Optional<T> min(Comparator<? super T> comparator): Returns the minimum element of this stream
     *                       according to the provided Comparator. (T)
     *           -- Optional<T> reduce(BinaryOperator<T> accumulator): Performs a reduction on the elements of this
     *                       stream, using an associative accumulation function, and returns an Optional describing
     *                       the reduced value, if any. (T)
     *           For all methods, https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
     *
     * - DoubleStream: Similar to Stream, but operates on double
     * - IntStream: Similar to Stream, but operates on int.
     *
     *  Notice that we add (T) and (I) to the methods in BaseStream and Stream interface.
     *  - T stands for terminal: A terminal operation consumes the stream. It is used to produce a result, such as
     *             finding the minimum value in the stream. Once a stream has been consumed, it cannot be reused.
     *
     *  - I stands for intermediate: A intermediate operation produce a new stream. Thus, intermediate operations
     *             can be used to create a pipeline that performs a sequence of actions. And intermediate operations
     *             do not take place immediately. it waits when a terminal operation is executed on the new stream
     *             created by the intermediate operations. This	mechanism is referred to as lazy behavior, and the
     *             intermediate operations are referred to as lazy. This mechanism can improve the performance.
     *
     * Another key aspect of streams is that some intermediate operations are stateless and some are stateful.
     * In a stateless operation, each element is processed independently of the others. In a stateful operation,
     * the processing of an element may depend on aspects of the other elements. For example, sorting is a stateful
     * operation because an element’s order depends on the values of the other elements. Thus, the sorted() method
     * is stateful. However, filtering elements based on a stateless predicate is stateless because each element
     * is handled individually. Thus, filter() can (and should be) stateless. The difference between stateless and
     * stateful operations is especially important when parallel processing of a stream is desired because a stateful
     * operation may require more than one pass to complete.
     * */

/** 10.2.2 How to obtain a Stream
 *
 * You can obtain a stream in a number of ways. Perhaps the most common is when a stream is obtained for a collection.
 * Beginning with JDK 8, the Collection interface was expanded to include two methods that obtain a stream from a
 * collection:
 * - default Stream<E> stream(): It returns a sequential stream.
 * - default Stream<E> parallelStream(): It returns a parallel stream, if possible. If not possible, a sequential
 *                 stream may be returned instead. Parallel stream support parallel execution of stream operations.
 * Because Collection interface is implemented by every collection, these methods can be called by any collection
 * class.
 *
 * A stream can also be obtained from an array by use the stream() method of Arrays class.
 * - static<T> Stream<T> stream(T[] array): It returns a sequential stream ot the elements in array.
 * For the array of primitive types, we can use the overload version of stream(), which returns a stream of type
 * IntStream, DoubleStream or LongStream.
 *
 * Many stream operations returns a new stream, and a stream to an I/O source can be obtained by calling lines() on
 * a BufferedReader object.
 *
 * However a stream is obtained, it can be used in the same way as any other stream.
 * */

/** 10.2.3 A stream example
 *
 *
 *
 * */

public static void main(String[] args){
    StreamExample.exp1();
}
}
