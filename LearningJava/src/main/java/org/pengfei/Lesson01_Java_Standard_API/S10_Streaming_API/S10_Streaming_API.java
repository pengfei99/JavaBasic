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
 *  check StreamExample.exp1(); we get a stream of a array list, we use various stream operation to get information
 *  or transform the list. Note, the stream operation has two type
 *  - terminal: consume the stream, after this kind of operation, the stream is no longer usable.
 *  - intermediate: produces a new stream based on the invoking stream. It's possible to chain intermediate operations
 *             together to form a data processing pipeline.
 *
 * */

    /********************************************* 10.3 Reduction Operations *****************************************/

    /* In the language of the stream API, reduction operations reduce a stream to a single value. So the operations such
    * as min, max, count are special cases of reduction operations. Because they perform a specific function. However,
    * the stream API generalizes this concept by providing the reduce() method. By using reduce(), you can return a
    * value from a stream based on any arbitrary criteria. By definition, all reduction operations are terminal
    * operations.
    *
    * Stream defines three versions of reduce(). The two we will use first are shown here:
    *
    * 1. Optional<T> reduce(BinaryOperator<T> accumulator): accumulator is a function that operates on two values and
    *               produces a result. We use optional class to encapsulate the result to avoid null pointer exception.
    * 2. T reduce(T identityVal, BinaryOperator<T> accumulator): identityVal is a value such that an accumulator
    *               operation involving identityVal and any element of the stream yields that element, unchanged.
    *               For example, if the operation is addition, then the identity value will be 0 because 0 + x is x.
    *               For multiplication, the value will be 1, because 1 * x is x.
    *
    * "BinaryOperator" is a functional interface declared in java.util.function that extends the BiFunction functional
    * interface. BiFunction defines this abstract method:
    * - R apply(T val, U val2): Here, R specifies the result type, T is the type of the first operand, and U is the
    *                type of second operand. Thus, apply( ) applies a function to its two operands (val and val2) and
    *                returns the result.
    *
    * When BinaryOperator extends BiFunction, it specifies the same type for all the type parameters. Thus, as it
    * relates to BinaryOperator, apply( ) looks like this:
    * - T apply(T val, T val2).
    * Furthermore, as it relates to reduce( ), val will contain the previous result and val2 will contain the next
    * element. In its first invocation, val will contain either the identity value or the first element, depending
    * on which version of reduce() is used.
    *
    *
    * */

    /** 10.3.1 Accumulator operation constraints
     *
     * It is important to understand that the accumulator operation must satisfy three constraints. It must be
     * - Stateless: stateless means that the operation does not rely on any state information. Thus, each element is
     *              processed independently
     * - Non-interfering: Non-interfering means that the data source is not modified by the operation.
     * - Associative: It means an associative operator used in a sequence of operations, it does not matter which pair
     *                of operands are processed first. For example, (1 + 2) + 3 yields the same result as 1 + (2 + 3).
     *
     * Check StreamExample.exp2(); We use different version of reduce to implement some operations.
     * */


    /********************************************* 10.4 Parallel Streams *****************************************/

    /*
    * The parallel execution of code via multicore processors can result in a substantial increase in performance.
    * One of the benefits that the stream library offers is the ability to easily—and reliably—parallel process
    * certain operations. Parallel processing of a stream is quite simple to request: just use a parallel stream.
    * As mentioned earlier, one way to obtain a parallel stream is to use the parallelStream( ) method defined by
    * Collection. Another way to obtain a parallel stream is to call the parallel( ) method on a sequential stream.
    * The parallel( ) method is defined by BaseStream. It returns a parallel stream based on the sequential stream that
    * invokes it. (If it is called on a stream that is already parallel, then the invoking stream is returned.).
    * Understand, of course, that even with a parallel stream, parallelism will be achieved only if the environment
    * supports it.
    *
    *
    * Once a parallel stream has been obtained, operations on the stream can occur in parallel(in different threads).
    * As a general rule, any operation applied to a parallel stream must be stateless. It should also be
    * non-interfering and associative. This ensures that the results obtained by executing operations on a parallel
    * stream are the same as those obtained from executing the same operations on a sequential stream.
    *
    * In parallelStream, we should use the third version of the reduce:
    * <U> U reduce(U identityVal, BiFunction<U, ? super T,U> accumulator, BinaryOperator<U> combiner): combiner defines
    *               the function that combines two values that have been produced by the accumulator function.
    *
    * Check StreamExample.exp3();
    * */

    /** 10.4.1 Element ordering in a parallel stream
     *
     * Streams can be either ordered or unordered. In general, if the data source is ordered, then the stream will
     * also be ordered. However, when using a parallel stream, a performance boost can sometimes be obtained by
     * allowing a stream to be unordered. When a parallel stream is unordered, each partition of the stream can be
     * operated on independently, without having to coordinate with the others. In cases in which the order of the
     * operations does not matter, it is possible to specify unordered behavior by calling the unordered() method,
     * shown here:
     * - S unordered( )
     * One other point: the forEach( ) method may not preserve the ordering of a parallel stream. If you want to
     * perform an operation on each element in a parallel stream while preserving the order, consider using
     * forEachOrdered(). It is used just like forEach().
     * */

    /** 10.4.2 Sequential, Parallel stream switch
     *
     * You can switch a parallel stream to sequential by calling the sequential() method, which is specified by
     * BaseStream. It is shown here:
     * - S sequential()
     * Or convert sequential stream to parallel by calling the parallel() method.
     * In general, a stream can be switched between parallel and sequential on an as-needed basis.
     * */

    /********************************************* 10.5 Map Operations *****************************************/
    /*
    * The map operations transform each element of a stream with a given function, then returns a new stream which
    * contains the transformed elements of the invoking stream. The most general mapping method is map():
    * - <R> Stream<R> map(Function<? super T, ? extends R> mapFunc): Here, R specifies the type of elements of the new
    *               stream; T is the type of elements of the invoking stream; and mapFunc is an instance of Function,
    *               which does the mapping.
    *
    * The map function must be:
    * - stateless
    * - non-interfering.
    * Since a new stream is returned, map() is an intermediate method.
    *
    * Function is a functional interface declared in java.util.function. It is declared as shown here:
    * - Function<T, R> As it relates to map( ), T is the element type and R is the result of the mapping.
    * Function has the abstract method shown here:
    * - R apply(T val)
    * Here, val is a reference to the object being mapped. The mapped result is returned.
    *
    * Check StreamExample.exp4(); we combine map with reduce to get the sum of power 2 of all elements
    *
    * Check StreamExample.exp5(); we map the information of NamePhoneEmail Stream to a NamePhone stream.
    * */

    /** 10.5.1 Primitive type map operation
     *
     * In addition to the version just described, three other versions of map() are provided. They return a primitive
     * stream, as shown here:
     *
     * - IntStream mapToInt(ToIntFunction<? super T> mapFunc)
     * - LongStream mapToLong(ToLongFunction<? super T> mapFunc)
     * - DoubleStream mapToDouble(ToDoubleFunction<? super T> mapFunc)
     *
     * Each mapFunc must implement the abstract method defined by the specified interface, returning a value of the
     * indicated type. For example, ToDoubleFunction specifies the applyAsDouble(T val ) method, which must
     * return the value of its parameter as a double.
     *
     * check StreamExample.exp6();. It then uses stream() followed by mapToInt() to create an IntStream that contains
     * the ceiling of each value.
     * */

    /** 10.5.2 FlatMap
     * The stream API also provides methods that support flat maps. These are flatMap(), flatMapToInt(),
     * flatMapToLong(), and flatMapToDouble(). The flat map methods are designed to handle situations in which each
     * element in the original stream is mapped to more than one element in the resulting stream.
     * Check StreamExample.exp7(); We have an array of arrays of string. We use flatMap to produce a stream of string
     * not a stream of array of string.
     * */

    /********************************************* 10.6 Collecting *****************************************/

    /*
    * It is possible (indeed, common) to obtain a stream from a collection. Sometimes it is desirable to obtain the
    * opposite: to obtain a collection from a stream. To perform such an action, the stream API provides the collect()
    * method. It has two forms:
    * - <R, A> R collect(Collector<? super T, A, R> collectorFunc): R specifies the type of the result, and T
    *              specifies the element type of the invoking stream. The internal accumulated type is specified by A.
    *              The collectorFunc specifies how the collection process works. The collect() method is a
    *              terminal operation.
    * - <R> R collect(Supplier<R> target, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner): target
    *              specifies how the object that holds the result is created. For example, to use a LinkedList as
    *              the result collection, you would specify its constructor. The accumulator function adds an
    *              element to the result and combiner combines two partial results. Thus, these functions work
    *              similarly to the way they do in reduce(). For both, they must be stateless, non-interfering and
    *              associative. Note that the target parameter is of type "Supplier". It is a functional interface
    *              declared in java.util.function. It specifies only the get() method, which has no parameters and,
    *              in this case, returns an object of type R. Thus, as it relates to collect(), get() returns a
    *              reference to a mutable storage object, such as a collection. Note also that the types of accumulator
    *              and combiner are "BiConsumer". This is a functional interface defined in java.util.function. It
    *              specifies the abstract method void accept(T obj, U obj2), this method performs some type of
    *              operation on obj and obj2. As it relates to accumulator, obj specifies the target collection, and
    *              obj2 specifies the element to add to that collection. As it relates to combiner, obj and obj2
    *              specify two collections that will be combined.
    *
    * The Collector interface
    * The Collector interface is declared in java.util.stream, as shown here:
    * - interface Collector<T, A, R>: R specifies the type of the result, and T specifies the element type of the
    *               invoking stream. The internal accumulated type is specified by A.
    *
    * Collector specifies several methods, but for the purposes of this chapter, we won’t need to implement them.
    * Instead, we will use two of the predefined collectors that are provided by the "Collectors" class, which is
    * packaged in java.util.stream.
    *
    * The Collectors class defines a number of static collector methods that you can use as-is. The two we will use
    * are toList() and toSet(), shown here:
    * - static <T> Collector<T, ?, List<T>> toList(): It returns a collector that can be used to collect elements into
    *                             a List
    * - static <T> Collector<T, ?, Set<T>> toSet(): It returns a collector that can be used to collect elements into
     *                             a Set
    *
    * Check StreamExample.exp8(); It uses the first version of collect
    * Check StreamExample.exp9(); It uses the second version of collect.
    *
    * The ability to move data from a collection to a stream, and then back to a collection again is a very powerful
    * attribute of the stream API. It gives you the ability to operate on a collection through a stream (in parallel),
    * but then repackage it as a collection.
    *
    * In the language of the stream API, the collect() method performs what is called a mutable reduction. This is
    * because the result of the reduction is a mutable (i.e., changeable) storage object, such as a collection.
    *  */


    /********************************************* 10.7 Iterators and Streams *****************************************/

    /*
    * Although a stream is not a data storage object, you can still use an iterator to cycle through its elements in
    * much the same way as you would use an iterator to cycle through the elements of a collection. The stream API
    * supports two types of iterators. The first is the traditional Iterator. The second is Spliterator, which was
    * added by JDK 8. It provides significant advantages in certain situations when used with parallel streams.
    *
    * Iterators are objects that implement the Iterator interface declared in java.util. Its two key methods are
    * hasNext() and next(). Check Lesson01-Section3 Collections for more details.
    *
    * To obtain an iterator to a stream, call iterator() on the stream. The version used by Stream is shown here.
    * - Iterator<T> iterator(): Here, T specifies the element type. (The primitive streams return iterators of the
    *                           appropriate primitive type.)
    *
    * Check StreamExample.exp10();
    * */

    /** 10.7.1 Spliterator
     *
     * Introduction of Spliterator can be found in Lesson03_Sec3.4. We focus on several methods of the Spliterator:
     * - boolean tryAdvance(Consumer<? super T> action): action specifies the action that is executed on the next
     *                 element in the iteration. tryAdvance( ) returns true if there is a next element. It returns
     *                 false if no elements remain. Consumer class declares one method called accept() that
     *                 receives an element of type T as an argument and returns void.
     * - default void forEachRemaining(Consumer<? super T> action): This method applies action to each unprocessed
     *                 element and then returns.
     * - Spliterator<T> trySplit(): It splits the elements being iterated in two, returning a new Spliterator to one
     *                 of the partitions. The other partition remains accessible by the original Spliterator.
     *
     * Check StreamExample.exp11(); for a simple example of tryAdvance()
     * StreamExample.exp12() for a simple example of trySplit() and forEachRemaining(). Although splitting the
     *                  Spliterator in this simple illustration is of no practical value, splitting can be of great
     *                  value when parallel processing over large data sets. However, in many cases, it is better to
     *                  use one of the other Stream methods in conjunction with a parallel stream(e.g. map, reduce).
     *                  Spliterator is primarily for the cases in which none of the predefined methods seems appropriate.
     *
     * */

    /************************************ 10.8 More to Explore in the Stream API *************************************/

    /* This section has discussed several key aspects of the stream API and introduced the techniques required to use
     * them, but the stream API has much more to offer. To begin, here are a few of the other methods provided by
     * Stream that you will find helpful:
     * - To determine if one or more elements in a stream satisfy a specified predicate, use allMatch(), anyMatch(),
     *                  or noneMatch().
     * - To obtain the number of elements in the stream, call count().
     * - To obtain a stream that contains only unique elements, use distinct().
     * - To create a stream that contains a specified set of elements, use of().
     *
     * One last point: the stream API is a powerful addition to Java. You will want to explore all of the capabilities
     * that java.util.stream has to offer.
     * */

    public static void main(String[] args){

    /**  A stream example*/
    // StreamExample.exp1();

      /** stream reduce example*/
    // StreamExample.exp2();

     /** parallel stream reduce*/
    // StreamExample.exp3();

        /** map operations */
        //StreamExample.exp4();
       // StreamExample.exp5();
       // StreamExample.exp6();
       // StreamExample.exp7();

        /** Collecting */
       // StreamExample.exp8();
        // StreamExample.exp9();

        /** Iterators and stream*/
       // StreamExample.exp10();
       // StreamExample.exp11();
        StreamExample.exp12();
}
}
