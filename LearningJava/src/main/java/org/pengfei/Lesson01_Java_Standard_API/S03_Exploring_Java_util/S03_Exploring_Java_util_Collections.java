package org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util;

import org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source.CollectionClassesExp;
import org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source.MapClassesExp;

public class S03_Exploring_Java_util_Collections {

    /********************************************* 01 Introduction **************************************************/

    /* In this section, we explore the java.util package. It is one of the most important package to learn in java.
    * For example, it has classes that generate pseudorandom numbers, manage date and time, observe events, manipulate
    * sets of bits, tokenize strings, and handle formatted data. In the first part of this section, we will focus
    * on the Collection framework.
    *
    * The Collections Framework	is a sophisticated hierarchy of	interfaces and classes that	provide state-of-the-art
    * technology for managing groups of	objects. Beginning	with JDK 9,	java.util is part of the java.base module.
    *
    * The Collection framework was designed to meet several goals:
    * - High-performance: The implementations for the fundamental collections (e.g. dynamic arrays, linked lists, trees
    *                   and hash tables) are highly efficient. You rarely need to implement these data structure manually.
    * - Coherence: The framework had to	allow different	types of collections to work in	a similar manner and with a
    *              high degree of interoperability.
    * - Simplicity:  Extending and/or adapting a collection	had	to be easy.	Toward this	end, the entire Collections
    *              Framework is	built upon a set of	standard interfaces. Several standard implementations (e.g. LinkedList,
    *              HashSet, and	TreeSet) of these interfaces are provided that you may use as-is. You may also implement
    *              your own	collection,	if you choose.
    *
    * Algorithms are another important part of the collection mechanism. Algorithms operate on collections and are
    * defined as static methods within the Collection class. Thus, they are available for all collections, so you don't
    * need to implement your own versions.
    *
    * The Iterator interface is important to the Collection, because iterator offers a general-purpose, standardized
    * way of accessing the elements within a collection, one at a time. In java, all collection classes provide an
    * iterator. Since JDK8, java added PrimitiveIterator and PrimitiveIterator.OfDouble interfaces to support primitive
    * types.
    *
    * Since JDK 8, java added another type of iterator called a spliterator. Java Spliterator interface is an internal
    * iterator that breaks the stream into the smaller parts. These smaller parts can be processed in parallel.
    * Spliterators mechanism are supported by Spliterator interface and several nested interfaces for primitive types.
    * (https://howtodoinjava.com/java/collections/java-spliterator/)
    *
    * In the Collection framework, we also have several map interfaces and classes. But maps are not "collections" in
    * the strict use of the term. You can obtain a collection-view of a map. Such a view contains the elements from
    * the map stored in a collection.
    * */

    /****************************************** 02 Collection interfaces ********************************************/

    /* The collection framework defines several core interfaces which determine the fundamental nature of the
    * collection classes. These core interfaces are:
    * - Collection: Enables you to work with groups of objects. It is the top interface of the collection hierarchy.
    * - List: Extends Collection interface to handle sequences (lists of objects).
    * - Queue: Extends Collection interface to handle special types of lists in which elements are removed only
    *          from the head(FIFO).
    * - Deque: Extends Queue to handle a double-ended queue(can add and remove element from end and start).
    * - Set: Extends Collection ot handle sets, which must contain unique element.
    * - SortedSet: Extends Set to handle sorted sets.
    * - NavigableSet: Extends SortedSet to handle retrieval of elements based on closest-match searches.
    *
    * In addition to the collection interfaces, classes in collections also implements other interfaces such as
    * - Comparator: defines how two objects are compared,
    * - RandomAccess: indicates the class which implements it support efficient, random access to its elements.
    * - Iterator, ListIterator, and Spliterator: enumerate the objects within a collection.
    *
    * To provide the greatest flexibility, the collection interfaces allow some methods to be optional. These optional
    * methods enable you to modify the contents of a collection. Collection classes which implements these method are
    * called modifiable collections, otherwise it's called unmodifiable collection. If we try to call these methods in
    * a unmodifiable collection, an UnsupportedOperationException is thrown.
    *
    * Note, all the built-in collections are modifiable.
    * */

    /** 2.1 The Collection Interface
     *
     * The Collection interface is the foundation of Collection framework, because it must be implemented by any
     * class that defines a collection.
     * - interface Collection<E>: here E specifies the type of objects that the collection will hold. As Collection
     *            interface extends Iterable interface. This means that all collections can be loop through by use
     *            of the for-each style for loop.
     *
     * The Collection interface defines the following methods:
     * - boolean add(E obj): Adds obj to the invoking collection. Returns true if obj was added, returns false if
     *          obj is already a member of the collection and the collection does not allow duplicates.
     * - boolean addAll(Collection < ? extends E > c): Adds all the elements of c to the invoking collection. Returns
     *          true if the elements were added. otherwise, return false.
     * - void clear(): Removes all elements from the invoking collection.
     * - boolean contains(Object obj): Returns true if obj is an element of the invoking collection. Otherwise, returns
     *               false.
     * - boolean containsAll(Collection< ? > c): Returns true if the invoking collection contains all elements of c.
     *               Otherwise, returns false.
     * - boolean equals(Object obj): Returns true if the invoking collection and obj are equal.
     * - int hashCode(): Returns the hash code of the invoking collection.
     * - boolean isEmpty(): Returns true if the invoking collection is empty.
     * - Iterator<E> iterator(): Returns an iterator for the invoking collection.
     * - default Stream<E> parallelStream(): Returns a stream that uses the invoking collection as its source for
     *              elements. If possible, the stream supports parallel operations.
     * - boolean remove(Object obj): Removes one instance of obj from the invoking collection. Returns true if the
     *              element was removed.
     * - boolean removeAll(Collection< ? > c): Removes all elements of c from the invoking collection. Returns true
     *              if elements were removed.
     * - boolean retainAll(Collection< ? > c): Removes all elements from the invoking collection except those in c.
     *              Returns treu If all elements were removed.
     * - int size(): Returns the number of elements held in hte invoking collection.
     * - default Spliterator<E> spliterator(): Returns a spliterator to the invoking collections.
     * - default Stream<E> steam(): Returns a steam that uses the invoking collection as its source for elements. The
     *                steam is sequential.
     * - default <T> T[] toArray(IntFunction<T[]> arrayGen): Returns an array of the elements from the invoking
     *                collection. The returned array is created by teh function arrayGen. An arrayStoreException is
     *                thrown if any collection element has a type that is not compatible with the array type.
     *                (Added by JDK 11.)
     * - Object[] toArray(): Returns an array of the elements(with type Object) from the invoking collection. Cast
     *               are needed if you want the element type to be more specific.
     * - <T> T[] toArray(T a[]): Returns an array of the elements from the invoking collection. If the size of
     *              a equals the number of elements, these are returned in a. If the size of a is less than the
     *              number of elements, a new array of the necessary size is allocated and returned. If the size
     *              of a is greater than the number of elements, the array element following the last collection
     *              element is set to null. An ArrayStoreException is thrown if any collection element has a type
     *              that is not compatible with the array type. This form is more convenient, by giving type
     *              explicitly, no cast is needed.
     *
     * Exceptions in the above methods:
     * A ClassCastException is generated when one object is incompatible with another, such as when an attempt is
     * made	to add an incompatible object to a collection. A NullPointerException is thrown if an attempt is made
     * to store a null object and null elements	are	not	allowed	in the collection. An IllegalArgumentException
     * is thrown if an invalid argument is used. An	IllegalStateException is thrown	if an attempt is made to add an
     * element to a fixed-length collection that is full.
     * */

    /** 2.2 The List Interface
     * The List interface extends Collection and declares the behavior of a collection that stores a sequence of
     * elements. Elements can be inserted or accessed by their position in the list, using a 0 based index. A list
     * may contain duplicate elements.
     *
     * As List extends Collection interface, List inherits all method of Collection, it also defines its own:
     * - void add(int index, E obj): Inserts obj into the invoking list at the position passed in index, Any
     *         pre-existing elements at or beyond the point of insertion are shifted up. Thus, no elements are
     *         overwritten.
     * - boolean addAll(int index, Collection< ? extends E> c): Inserts all elements of c into the invoking list
     *          at position passed in index. Any pre-existing elements at or beyond the point of insertion
     *          are shifted up. Thus, no elements are overwritten. Returns true if the invoking list changes.
     * - static <E> List<E> copyOf(Collection< ? extends E> from): Returns a list that contains the same elements
     *           as that specified in list from. The returned list is unmodifiable. Null values are not allowed.
     * - E get(int index): Returns the object stored at the specified index within the invoking collection.
     * - int indexOf(Object obj): Returns the index of the first instance of obj in the invoking list. If obj is
     *           not an element of the list, -1 is returned.
     * - int lastIndexOf(Object obj): Returns the index of the last instance of obj in teh invoking list. If obj is
     *           not an element of the list, -1 is returned.
     * - ListIterator<E> listIterator(): returns an iterator to the start of the invoking list.
     * - ListIterator<E> listIterator(int index): returns an iterator to the invoking list that begins at the
     *           specified index.
     * - static <E> List<E> of(parameter-list): Creates an unmodifiable list containing the elements specified in
     *             parameter-list. Null elements are not allowed. 12 overloaded versions are provided. For example,
     *             - static <E> List<E> of(): it creates an empty list of E.
     *             - static <E> List<E> of(E obj1)
     *             - static <E> List<E> of(E obj1, E obj2)
     *             ...
     *             - static <E> List<E> of(E obj1, E obj2, ..., E obj10)
     * - E remove(int index): Removes the element at position index from the invoking list and returns the deleted
     *               element. The resulting list is compacted. That is, the indexes of the subsequent elements are
     *               decremented by one.
     * - default void replaceAll(UnaryOperator<E> opToApply): Updates each element in the list with teh value obtained
     *               from the opToApply function.
     * - E set(int index, E Obj): Assigns object to the location specified by index within the invoking list. Returns
     *              the old value.
     * - default void sort(Comparator < ? super E> comp): Sorts the list using the comparator specified by comp.
     * - List<E> subList(int start, int end): Returns a list that includes elements from start to end-1 in the
     *             invoking list. Elements in the returned list are also referenced by the invoking object.
     *             For example, if we modify the object field value in the source list, the object in the
     *             returned sub list also changes. We only creates a copy of the reference not a clone of the object.
     *
     * Exceptions in above methods:
     * A ClassCastException is generated when one object is incompatible with another, such as when an attempt is
     * made	to add an incompatible object to a collection. A NullPointerException is thrown if an attempt is made
     * to store a null object and null elements	are	not	allowed	in the collection. An IllegalArgumentException
     * is thrown if an invalid argument is used. Several methods will throw an IndexOutOfBoundsException if an
     * invalid index is used.
     * */

    /** 2.3 The Set Interface
     * The Set interface extends Collection and specifies the behavior of a collection that does not allow duplicate
     * elements. It does not specify any additional methods of its own. It modify the add() method, it returns false
     * if an attempt is made to add duplicate elements to a set.
     *
     * Beginning with JDK9, Set includes the of() factory method, which has 12 overloads. Each version returns an
     * unmodifiable Set that is comprised of the arguments.
     *                - static <E> Set<E> of(): it creates an empty list of E.
     *                - static <E> Set<E> of(E obj1)
     *                - static <E> Set<E> of(E obj1, E obj2)
     *                ...
     *                - static <E> Set<E> of(E obj1, E obj2, ..., E obj10)
     *
     * Beginning with JDK10, Set includes the static copyOf() method:
     * - static <E> Set<E> copyOf(Collection < ? extends E> from) : It returns a set that contains the same
     *          elements as from. Null values are not allowed. The returned set is unmodifiable.
     * */

    /** 2.4 The SortedSet Interface
     * The SortedSet interface extends Set and declares the behavior of a set sorted in ascending order. In addition
     * to those methods provided by Set, the SortedSet interface declares the following methods:
     * - Comparator< ? super E> comparator(): Returns the invoking sorted set's comparator. If the natural ordering is
     *              used for this set, null is returned.
     * - E first(): Returns the first element in the invoking sorted set.
     * - SortedSet<E> headSet(E endObj): Returns a SortedSet containing those element less than endObj that are
     *              contained in the invoking sorted set. Elements in the returned sorted set also referenced by
     *               the invoking sorted set.
     * - E last(): Returns the last element in the invoking sorted set.
     * - SortedSet<E> subSet(E startObj, E endObj): Returns a SortedSet that includes those elements between startObj
     *             and endObj-1. Elements in the returned sorted set also referenced by the invoking sorted set.
     * - SortedSet<E> tailSet(E startObj): Returns a SortedSet containing those greater than or equal to startObj that are
     *             contained in the invoking sorted set. Elements in the returned sorted set also referenced by
     *             the invoking sorted set.
     * */

    /** 2.5 The navigableSet Interface
     *
     * The NavigableSet interface extends SortedSet and declares the behavior of a collection that supports the
     * retrieval of elements based on the closest match to a given value or values. In addition
     * to those methods provided by SortedSet, the NavigableSet interface declares the following methods:
     * - E celling(E obj): Searches the set for the smallest element e such that e >= obj. If such an element is
     *               found, it is returned, Otherwise null is returned.
     * - Iterator<E> descendingIterator(): Returns an iterator that moves from the greatest to the least. In other
     *               words, it returns a revers iterator. The resulting set is backed by the invoking set.
     * - NavigableSet<E> descendingSet(): Returns a NavigableSet that is the reverse of the invoking set.
     * - E floor(E obj): Searches the set for the largest element e such that e <= obj. Returns e if such element
     *              is founded, otherwise null is returned.
     * - NavigableSet<E> headSet(E upperBound, boolean include): Returns a Navigable that includes all elements
     *              from the invoking set that are less than upperBond. If include is true, then an element equal
     *              to upperBound is included. The resulting set is backed by the invoking set.
     * - E higher(E obj): Searches the set for the smallest element e such that e > obj. If not found return null
     * - E lower(E obj): Searches the set for the greatest element e such that e < obj. If not found return null
     * - E pollFirst(): Returns the first element(least value), removing the element in the process. null is returned
     *                 if the set is empty
     * - E pollLast(): Returns the last element(greatest value), removing the element in the process. null is returned
     *                 if the set is empty
     * - NavigableSet<E> tailSet(E lowerBound, boolean include): Returns a Navigable that includes all elements
     *                from the invoking set that are greater than lowerBond. If include is true, then an element equal
     *               to lowerBound is included. The resulting set is backed by the invoking set.
     * - NavigableSet<E> subSet(E lowerBound, boolean lInc, E upperBound, boolean uInc): Returns a Navigable that
     *                includes all elements from the invoking set that are < upperBond, and > lowerBound. If lInc is
     *                true, then an element equal to lowerBound is included. same for uInc. The resulting set is
     *                backed by the invoking set.
     *
     * */

    /** 2.6 The Queue Interface
     * The Queue interface extends Collection and declares the behavior of a queue(FIFO). Queue added the following
     * methods:
     * - E element(): returns the element at the head of the queue. The element is not removed. It throws
     *               NoSuchElementException if the queue is empty.
     * - boolean offer(E obj): Attempts to add obj to the queue, returns tree if obj was added.
     * - E peek(): Returns the element at the head of the queue. It returns null if the queue is empty. The
     *            element is not removed.
     * - E poll(): Returns the element at the head of the queue, removing the element. It returns null if the queue
     *            is empty.
     * - E remove(): Removes the element at the head of the queue, returning the removed object. It throws
     *            NoSuchElementException if the queue is empty.
     *
     *
     * */

    /** 2.7 The Deque Interface
     * The Deque interface extends Queue and declares the behavior of a double-ended queue. Double-ended queues can
     * function as standard queue (FIFO). or as stack(LIFO). A double-ended queue allow us to add and remove element
     * from the head and tail.
     *
     * In addition of Queue interface methods, Deque adds the following methods:
     * - void addFirst(E obj): Adds obj to the head of the deque. Throws an IllegalStateException if deque is out of
     *                         space(capacity restricted).
     * - void addLast(E obj): Adds obj to the tail of the deque. Throws an IllegalStateException if deque is out of
     *                         space(capacity restricted).
     * - Iterator<E> descendingIterator(): Returns an iterator that moves from hte tail to head of the deque.
     * - E getFirst(): Returns the first element in the deque. The object is not removed. It throws
     *                 NoSuchElementException if the deque is empty.
     * - E getLast(): Returns the last element in the deque. The object is not removed. It throws
     *                  NoSuchElementException if the deque is empty.
     * - boolean offerFirst(E obj): Attempts to add obj to the head of the deque. Returns true if obj was added and
     *                   false otherwise. If the deque is full, it returns false also, no exceptions is thrown.
     * - boolean offerLast(E obj): Attempts to add obj to the tail of the deque. Returns true if obj was added and
     *                  false otherwise. If the deque is full, it returns false also, no exceptions is thrown.
     * - E peekFirst(): Returns the element at the head of the deque, the obj is not removed. It returns null if
     *                  deque is empty.
     * - E peekLast(): Returns the element at the tail of the deque, the obj is not removed. It returns null if
     *                 deque is empty.
     * - E pollFirst(): Returns the element at the head of the deque, the obj is removed. It returns null if
     *                deque is empty.
     * - E pollLast(): Returns the element at the tail of the deque, the obj is removed. It returns null if
     *               deque is empty.
     * - E pop(): Returns the element at the head of the deque, the obj is removed. It throws NoSuchElementException if
     *              deque is empty.
     * - void push(E obj): Adds obj to the head of the deque. Throws an IllegalStateException if deque is out of
     *                        space(capacity restricted).
     * - E removeFirst(): Remove the first element of the deque, return the removed obj. It throws NoSuchElementException if
     *             deque is empty.
     * - E removeLast(): Remove the last element of the deque, return the removed obj. It throws NoSuchElementException if
     *            deque is empty.
     * - boolean removeFirstOccurrence(Object obj): Removes the first occurrence of obj from the deque. Returns true
     *             if successful and false if the deque does not contain obj.
     * - boolean removeLastOccurrence(Object obj): Removes the last occurrence of obj from the deque. Returns true
     *            if successful and false if the deque does not contain obj.
     *
     *  Note:
     *  - The push, pop methods enable a deque to functions as a stack.
     *  - A deque implementation can be capacity-restricted. When this is the case, an attempt to add an element to
     *    the deque can fail. addFirst() and addLast() will throw an IllegalStateException if this happens. offerFirst()
     *    offerLast() return false if this happens.
     *
     *
     * */

    /****************************************** 03 Collection classes ********************************************/

    /*
    * Now that you are familiar with the collection interfaces, you are ready to examine the standard classes that
    * implement them. Some of the classes provide full implementations that can be used as-is. Others are abstract,
    * providing skeletal implementations that are used as starting points for creating concrete collections. As a
    * general rule,	the	collection classes are not synchronized, but as you will see later in this section, it is
    * possible to obtain synchronized versions.
    * The core collection classes are summarized in the following table:
    * - AbstractCollection: Implements most of the Collection interface.
    * - AbstractList: Extends AbstractCollection and implements most the List interface.
    * - AbstractSequentialList: Extends AbstractList for use by a collection that uses sequential rather than
    *               random access of its elements.
    * - LinkedList: Implements a linked list by extending AbstractSequentialList.
    * - ArrayList: Implements a dynamic array by extending AbstractList
    *
    * - AbstractSet: Extends AbstractCollection and implements most of the Set interface.
    * - EnumSet: Extends AbstractSet for use with enum elements.
    * - HashSet: Extends AbstractSet for use with a hash table.
    * - TreeSet: Extends AbstractSet and implements a set stored in a tree.
    * - LinkedHashSet: Extends HashSet to allow insertion-order iteration.
    *
    * - AbstractQueue: Extends AbstractCollection and implements most the Queue interface
    * - ArrayDeque: Implements a dynamic double-ended queue by extending AbstractCollection and implements the
    *               Deque interface.
    * - PriorityQueue: Extends AbstractQueue to support a priority-based queue.
    * */

    /** 3.1 ArrayList
     *
     * ArrayList supports dynamic arrays that can grow as needed. In Java, standard arrays are of a fixed length. Check
     * L13_S6 to see how we implements dynamic arrays in Java. ArrayList has three constructors:
     * - ArrayList(): it builds an empty array list.
     * - ArrayList( Collection< ? extends E> c): it builds an array list that is initialized with the elements of the
     *          collection c.
     * - ArrayList(int capacity): It builds an empty array list with an initial capacity.
     *
     * Although	the	capacity of	an ArrayList object	increases automatically	as objects are stored in it, you can
     * increase	the	capacity of	an ArrayList object manually by	calling	ensureCapacity(	). You might want to do	this
     * if you know in advance that you will	be storing many	more items in the collection than it can currently hold.
     * By increasing its capacity once,	at the start, you can prevent several reallocations	later.	Because
     * reallocations are costly	in terms of time,
     *
     * Conversely, if you want to reduce the size of the array that underlies an ArrayList object so that it is
     * precisely as large as the number of items that it is currently holding, call trimToSize().
     * Check CollectionClassesExp.exp1(); for code example.
     *
     * Obtaining an Array from an ArrayList
     * Several reasons exist why you might want to convert a collection into an array, such as:
     * - To obtain faster processing times for certain operations
     * - To pass an array to a method that is not overloaded to accept a collection.
     * - To integrate collection-based code with legacy code that does not understand collections.
     *
     * To do the conversion, we need to call the toArray() method in Collection interface, which should be implemented
     * by all predefined collections classes. Check Collection interface of this section to see the three overload
     * version of the toArray() method.
     *
     * */

    /** 3.2 LinkedList class
     *
     * The LinkedList class extends AbstractSequentialList and implements the List, Deque(Queue by inheritance)
     * interfaces. It use a linked-list data structure. It has two constructors:
     * - LinkedList(): it builds an empty linked list.
     * - LinkedList(Collection< ? extends E> c): it builds a linked list that is initialized with the element
     *    of teh collection c.
     *
     * Because it implements Deque interface, you have access to the methods such as addFirst(), offerFirst(), pop(),
     * etc(Check 2.7).
     *
     * Check  CollectionClassesExp.exp3(); for code example
     * */

    /** 3.3 HashSet Class
     * HashSet extends AbstractSet and implements the Set interface. It uses a hash table for storage. The hash table
     * uses the hash of the object as the unique key to store the value which is the object. The hash code is
     * determined by the system. As a result, you cant directly index the hash table. The advantage of hash table
     * is that it allows the execution time of add(), contains(), remove(), and size() to remain constant
     * even for large data sets.
     * It provides four constructors:
     * - HashSet(): it builds an empty default set. The default capacity is 16.
     * - HashSet(Collection < ? extends E> c): it builds a set with the elements of c.
     * - HashSet(int capacity): it builds a set with the given capacity.
     * - HashSet(int capacity, float fillRatio):  it builds a set with the given capacity and set the fill ratio. For
     *            example, if we set capacity is 100, and fill ratio is 0.75 (default value if nothing is given). If
     *            we put 75, it's ok, when we add 76 element, the hashSet will start to expand the capacity.
     *
     * HashSet inherit all methods of Set interface, it does not add any method of its own. It's important to note,
     * HashSet does not guarantee the order of its elements, because hashing does not usually produce sorted index.
     * If you need sorted storage, then use TreeSet.
     *
     * Check CollectionClassesExp.exp4(); for code example
     * */

    /** 3.4 LinkedHashSet Class
     * LinkedHashSet extends HashSet and adds no members of its own. It maintains a linked list of entries in the set,
     * to store the insertion order of elements. This allows us to do insertion-order iteration over the set. When we
     * call toString() on the LinkedHashSet, the print order is also the insertion order of the returned string.
     * Check CollectionClassesExp.exp5(); for code example
     * */

    /** 3.5 TreeSet Class
     * TreeSet extends AbstractSet and implements the NavigableSet interface. It creates a collection that uses a tree
     * for storage. Elements are sorted in a TreeSet with ascending order. Access and retrieval times are quite fast,
     * which makes TreeSet an excellent choice when sorting large amount of sorted information that must be
     * found quickly.
     *
     * It provides four constructors:
     * - TreeSet(): It builds an empty treeSet that will be sorted in ascending order according to the natural order of
     *             its elements.
     * - TreeSet(Collection < ? extends E> c): it builds a tree set with the elements of c.
     * - TreeSet(Comparator < ? super E> comp): it builds an empty tree that will be sorted according to the comparator
     *                              specified by comp.
     * - TreeSet(SortedSet<E> c): it builds a tree set with the elements of c.
     *
     * When you call toString, the print order will use the sorted order of the set of the returned string. Because
     * TreeSet implements the NavigableSet interface, we can use all methods of the NavigableSet interface.
     * Check  CollectionClassesExp.exp6(); for code example
     * */

    /** 3.6 PriorityQueue Class
     * A PriorityQueue is used when the objects are supposed to be processed based on the priority. It is known that a
     * queue follows First-In-First-Out algorithm, but sometimes the elements of the queue are needed to be processed
     * according to the priority, that’s when the PriorityQueue comes into play. For example in a Integer priorityQueue
     * the priority is the nature order of the Integer, 1 has greater priority 2, even 1 is added after 2, 1 will be
     * out before 2.
     *
     * PriorityQueue extends AbstractQueue and implements the Queue interface. It creates a queue that is prioritized
     * based on the queue's comparator. It provides the following constructors:
     * - PriorityQueue(): It builds an empty queue. The default starting capacity is 11.
     * - PriorityQueue(int capacity): It builds an empty queue with the given capacity.
     * - PriorityQueue(Comparator < ? super E> comp): It specifies a comparator for the element priority sorts.
     * - PriorityQueue(int capacity, Comparator < ? super E> comp):  It specifies both comparator and capacity.
     * - PriorityQueue(Collection < ? extends E > c): It builds an queue with elements of c.
     * - PriorityQueue(PriorityQueue < ? extends E > c): It builds an queue with elements of c.
     * - PriorityQueue(SortedSet < ? extends E > c): It builds an queue with elements of c.
     *
     * The capacity we give in the constructor is the initial capacity, it will grow automatically as elements are
     * added. If no comparator is specified, then default comparator of the type of data stored in the queue is used.
     * The default comparator will order the queue in ascending order. Thus, head is the smallest value. However, by
     * providing a custom comparator, you can specify a different ordering scheme. For example, when storing items that
     * include a time stamp, you could prioritize the queue	such that the oldest items are first in the queue.
     *
     * You can obtain a reference to the comparator used by a PriorityQueue by calling its comparator() method. If
     * natural ordering is used for the invoking queue, null is returned.
     *
     * One word of caution: Although you can iterate through a PriorityQueue using an iterator, the order of that
     * iteration is undefined. To properly use a PriorityQueue, you must call methods such as offer() and poll(),
     * which are defined by the Queue interface.
     * Check CollectionClassesExp.exp7();
     * */

    /** 3.7 The ArrayDeque Class
     *
     * The ArrayDeque class extends AbstractCollection and implements the Deque interface. ArrayDeque creates a dynamic
     * array and has no capacity restrictions. It provides the following constructors:
     * - ArrayDeque(): It builds an empty deque with a default capacity of 16.
     * - ArrayDeque(int size): It specifies the capacity with size
     * - ArrayDeque(Collection < ? extends E> c): It builds a deque with the elements of c.
     *
     * ArrayDeque is a special kind of array that grows and allows users to add or remove an element from both the
     * sides of the queue. Few important features of ArrayDeque are as follows:
     * - Array deques have no capacity restrictions and they grow as necessary to support usage.
     * - They are not thread-safe which means that in the absence of external synchronization, ArrayDeque does not
     *    support concurrent access by multiple threads.
     * - Null elements are prohibited in the ArrayDeque.
     * - ArrayDeque class is likely to be faster than Stack when used as a stack.
     * - ArrayDeque class is likely to be faster than LinkedList when used as a queue.
     *
     * Check CollectionClassesExp.exp8();
     * */

/** 3.8 EnumSet Class
 *
 * EnumSet extends AbstractSet and implements Set. It is specifically ofr use with elements of an enum type. It defines
 * no constructors. Instead, it uses the factory methods to create instances. Check  CollectionClassesExp.exp9(); for
 * the main factory methods.
 *
 * Few important features of EnumSet are as follows:
 * - EnumSet class is not synchronized.
 * - It’s a high performance set implementation, much faster than HashSet.
 * - All of the elements in an enum set must come from a single enumeration type that is specified when the set is
 *   created either explicitly or implicitly.
 *
 * */

    /************************************ 04 Accessing a Collection via an Iterator ***********************************/

    /*
    * The easiest way to cycle through the element in a collection is to employ an iterator, which implements
    * either the Iterator or ListIterator interface. Iterator enables you to cycle through a collection, obtaining
    * or removing elements. ListIterator extends Iterator to allow bidirectional traversal of a list, and the
    * modification of elements
    *
    * The Iterator interface defines following methods:
    * - default void forEachRemaining(Consumer<? super E> action): The instruction specified by action is executed
    *              on each unprocessed element in the collection.
    * - boolean hasNext(): Returns true if there are more elements.
    * - E next(): returns next element, throws NoSuchElementException if there is not a next element.
    * - default void remove(): Removes the current element. Throws IllegalStateException if an attempt is
    *          made to call remove() that is not preceded by a call to next(). The default version throws an
    *          UnsupportedOperationException.
    *
    * The ListIterator interface defines the following methods:
    * - void add(E obj): Inserts obj into the list in front of the element that will be returned by the next().
    * - default void forEachRemaining(Consumer<? super E> action): The instruction specified by action is executed
    *              on each unprocessed element in the collection.
    * - boolean hasNext(): Returns true if there is a next element.
    * - boolean hasPrevious(): Returns true if there is a previous elements.
    * - E next(): returns next element, throws NoSuchElementException if there is not a next element.
    * - E previous(): returns the previous element, throws NoSuchElementException if there is not a previous element.
    * - int nextIndex(): Returns the index of the next element, if there is no next element, return the size of the list.
    * - int previousIndex(): Returns the index of the previous element, if there is no previous element, return -1.
    * - void remove(): Removes the current element. Throws IllegalStateException if an attempt is
    *          made to call remove() that is not preceded by a call to next() or previous().
    * - void set(E obj): Assigns obj to the current element position. This is the element position last returned by
    *           a call to either next() or previous().
    * */

     /** 4.1 Using an Iterator
      * To use iterator, you must obtain one from the collection first. All predefined Collection class provide
      * an implementation of the iterator() method that returns an iterator to the start of the collection. By
      * using this iterator object, you can access each element in the collection, one element at a time.
      *
      * For collections that implements List interface, you can also obtain an ListIterator object by calling
      * listIterator() method.
      *
      * Check CollectionClassesExp.exp10(); for code example
      * */

     /** 4.2 For-Each (Alternative to Iterator)
      * If you won't be modifying the content of a collection or obtaining elements in reverse order, then the
      * for-each version of the for loop is often a more convenient way to cycling through a collection.
      * And it works for all classes that implements the Iterable interface. As all classes in collection implements
      * this interface, so it works for all predefined collection class.
      *
      *
      * */

     /** 4.3 Spliterators
      *
      * JDK 8 added spliterator that implements the Spliterator interface. It cycles a collection like a iterator, but the
      * techniques required to use it differ. The most important aspect of it is its ability to provide support
      * for parallel iteration of portions of the sequence. Thus, Spliterator supports parallel programming. See Lessons
      * on concurrency and parallel programing.
      *
      * The Spliterator interface defines the following method:
      * - int characteristics(): Returns the characteristics of the invoking spliterator, encoded into an integer.
      * - long estimateSize(): Estimates the number of elements left to iterate and returns the result. Returns
      *              Long.MAX_VALUE if the count cannot be obtained for any reason.
      * - default void forEachRemaining(Consumer< ? super T> action): Applies action to each unprocessed element
      *                       in the data source.
      * - default Comparator< ? super T> getComparator(): Returns the comparator used by the invoking spliterator or
      *                    null if natural ordering is used. If the sequence is unordered, IllegalStateException is
      *                    thrown.
      * - default long getExactSizeIfKnown(): If the invoking spliterator is sized, returns the number of elements
      *                     left to iterate. returns -1 otherwise.
      * - default boolean hasCharacteristics(int val): Returns true if the invoking spliterator has the characteristics
      *                   passed in val. Returns false otherwise.
      * - boolean tryAdvance(Consumer< ? super T> action): Executes action on the next element in the iteration. Returns
      *                  true if there is a next element. Return false if no elements remain.
      * - Spliterator<T> trySplit(): If possible, splits the invoking spliterator, returning a reference to a new
      *                  spliterator for the partition. Otherwise, returns null. Thus, if successful, the original
      *                  spliterator iterates over one portion of the sequence and the returned spliterator iterates
      *                  over the other portion.
      *
      * Using spliterator for basic iteration tasks is quite easy: simply call tryAdvance() until it returns false.
      * If you will be applying the same action to each element in the sequence, forEachRemaining() offers a streamlined
      * option. Here, consumer is a functional interface that applies an action to an object. It's a generic interface
      * declared in java.util.function(Check the section on java.util.function.). The easiest way to implement Consumer
      * is by use of a lambda expression.
      *
      * Check  CollectionClassesExp.exp12(); for code example, you don't see the real power of spliterator in this
      * example, because no parallel processing in it.
      *
      * Often you won't need to access a spliterator's characteristics, but in some cases, they can aid in
      * creating efficient, resilient code.
      *
      * There are several nested subinterfaces of Spliterator designed for use with the primitive types double, int,
      * and	long. These	are	called Spliterator.OfDouble, Spliterator.OfInt,	and	Spliterator.OfLong. There is also a
      * generalized	version	called Spliterator.OfPrimitive(), which	offers additional flexibility and serves as a
      * superinterface of the aforementioned ones.
      * */

    /********************************* 05 Storing user defined class in a Collection *********************************/

    /*
     * In a collection, we can store any type of objects(not only the built in type such as Integer, etc.).
     * Check CollectionClassesExp.exp13();
     * */

    /********************************* 06 The RandomAccess Interface *********************************/

    /* The RandomAccess interface contains no members. However by implementing this interface, a collection indicates
    * that it supports efficient random access to its element. You can use instanceof to determine if
    * a class implements an interface or not(Check). RandomAccess is implemented by ArrayList but not LinkedList.
    * Although LinkedList supports allow random access to its elements, but its not efficient. You can notice that
    * when operating with large collections.
    *
    * A map is an object that stores associations between keys and values. Given a key, you can find its value.
    * Both keys and values are objects. The key must be unique, but the values may be duplicated. Some maps
    * can accept a null key and null value, other cannot.
    *
    * Note maps do not implement the Iterable interface, so the for-each does not works on maps, and you can't obtain
    * an iterator either. However, we can obtain a collection-view of a map, which does allow the use of for-each and
    * iterator.
    * */

    /** 6.1 The Map Interfaces
     * In java, we have four interfaces to define the nature and character of maps, they are:
     * - Map: Maps unique keys to values
     * - Map.Entry: Describes an element (a key/value pair) in a map. This is an inner class of Map
     * - SortedMap: Extends Map so that the keys are maintained in ascending order.
     * - NavigableMap: Extends SortedMap to handle the retrieval of entries based on closest-match searches.
     */

     /** 6.1.1 The Map interface
     * The Map interface contains the following methods:
     * - void clear(): Removes all key/value pairs from the invoking map.
     * - default V compute(K k, BiFunction< ? super K, ? super V, ? extends V> func): Calls func to construct a new
     *               value. If func returns non-null, the new key/value pair is added to the map, any preexisting
     *               pairing is removed, and the new value is returned. If func returns null, any preexisting pairing
     *               is removed, and null is returned.
     * - default V computeIfAbsent(K k, Function< ? super K, ? extends V> func): Returns the value associated with the
     *              key k. Otherwise, the value is constructed through a call to func and the pairing is entered into
     *             the map and the constructed value is returned. If no value can be constructed, null is returned.
     * - default V computeIfPresent(K k, Function< ? super K, ? extends V> func): If k is in the map, a new value
     *              is constructed through a call to func and the new value replaces the old value. In this case,
     *              new value is returned. If func returns null, the existing key and value are removed from the map
     *              and null is returned.
     * --- Object put(Object key, Object value): This method is used to insert an entry in this map.
     * --- void putAll(Map map): This method is used to insert the specified map in this map.
     * - Object remove(Object key): This method is used to delete an entry for the specified key.
     * --- Object get(Object key):This method is used to return the value for the specified key.
     * - boolean containsKey(Object k): Returns true if map contains key k.
     * - boolean containsValue(Object v): Returns true if map contains value v.
     * --- Set keySet(): This method is used to return the Set view containing all the keys.
     * --- Collection<V> values(): Returns a collection containing the values in the map.
     * - Set entrySet(): Returns a Set that contains all entries(has type May.Entry) of the invoking map. Thus this
     *              method provides a set-view(Collection) of the invoking map.
     * - static <K,V> Map<K,V> copyOf(Map< ? extends K, ? extends V> from): Returns a map that contains the same
     *              key/value pairs in Map from. The returned map is unmodifiable. Null keys or values are not allowed.
     * - static <K,V> Map.Entry<K,V> entry(K k, V v): Returns an unmodifiable map entry comprised of the specified key
     *              and value. A null key or value is not allowed.
     * - default void forEach(BiConsumer< ? super K, ? super V> action): Executes action on each element in the
     *             invoking map. A ConcurrentModificationException will be thrown if an element is removed during the
     *             process.
     * - default V merge(K k, V v, BiFunction< ? super V, ? super V, ? extends V> func): If k is not in the map, the
     *            pairing k,v is added, and v is returned. If k exists in the map, func returns a new value by using
     *            old and new v as argument for func, this value is returned by merge() method, and the key k is
     *            updated based on the func returned value. If func returns null, the existing key/value pair are
     *            removed and null is returned.
     *
     * Full methods list in table 19-11(P886)
     *
     * Beginning with JDK9, Map includes the of() factory method to create new Map based on list of key/value pairs. It
     * has the following overloads version:
     * - static<K,V> Map<K,V> of(K k1, V v1):
     * - static<K,V> Map<K,V> of(K k1, V v1, K k2, V v2):
     * - ...
     * - static<K,V> Map<K,V> of(K k1, V v1, ..., K k10, V v10):
     * Note, for all above version, null keys and/or values are not allowed. The Map implementation is unspecified.
     *
     * */

     /** 6.1.2 The SortedMap Interface
      * The SortedMap interface extends Map. It ensures that the entries are maintained in ascending order based on the
      * keys comparator. It defines the following method:
      * - Comparator< ? super K> comparator(): Returns the invoking sorted map's comparator. If natural ordering is
      *             used, return null.
      * - K firstKey(): Returns the first key in the invoking map
      * - SortedMap<K,V> headMap(K end): Returns a sorted map for those map entries with keys that are less than end.
      * - K lastKey(): Returns the last key in the invoking map
      * - SortedMap<K,V> subMap(K start, K end): Returns a sorted map for those map entries with keys that are greater than
      *                    or equal to start and less than end.
      * - SortedMap<K,V> tailMap(K start): Returns a sorted map for those map entries with keys that are greater than
      *                  start.
      * */

     /** 6.1.3 The NavigableMap Interface
      * The NavigableMap interface extends SortedMap and supports the retrial of entries based on the closest match
      * to a given key or keys. It defines the following methods:
      * - Map.Entry<K,V> cellingEntry(K obj): Searches the map for the smallest key k such that k>= obj. If k is found,
      *                   its entry is returned, otherwise null is returned.
      * - K ceilingKey(K obj): Searches the map for the smallest key k such that k>= obj. If k is found,
      *                    then return k, otherwise null is returned.
      * - NavigableSet<K> descendingKeySet(): Returns a NavigableSet that contains the keys in the invoking map in
      *                     reverse order. The resulting set is backed by the map.
      * - NavigableMap<K,V> descendingMap(): Returns a NavigableMap that is the reverse of the invoking map. The
      *                    resulting set is backed by the map
      * -  Map.Entry<K,V> firstEntry(K obj): Returns the first entry of the map, which has the least key
      * -
      * -
      * -
      * The full method list in table 19-13 (P891)
      *
      * */

     /** 6.1.4 The Map.Entry Interface
      * The	Map.Entry interface	enables you to work with a map entry. It defines the following methods:
      * - boolean equals(Object obj): Returns true if obj is a Map.Entry whose key and value are equal to the invoking
      *                     object.
      * - K getKey(): Returns the key for this entry
      * - V getValue(): Returns the value for this entry
      * - int hashCode(): Returns the hash code of this entry.
      * - V setValue(V v): Sets the value of this map entry to v. A ClassCastException is thrown if v is not the
      *          correct type. An IllegalArgumentException is thrown if there is a problem with v. A NullPointerException
      *          is thrown if v is null. An UnsupportedOperationException is thrown if the map cannot be changed.
      * */


     /** 6.2 The Map Classes
      * Several map implementation classes are provided:
      * - AbstractMap: Implements most of the Map interface
      * - EnumMap: Extends AbstractMap for use with enum keys
      * - HashMap: Extends AbstractMap and uses hash table to store data.
      * - TreeMap: Extends AbstractMap and uses a tree to store data.
      * - WeakHashMap: Extends AbstractMap and uses a hash table with weak keys to store data.
      * - LinkedHashMap: Extends HashMap to allow insertion-order iterations.
      * - IdentityHashMap: Extends AbstractMap and uses reference equality when comparing documents.
      * Notice, AbstractMap is the superClass for all map implementations. WeakHashMap implements a map that uses
      * "weak keys" which allows an element	in a map to	be garbage-collected when its key is otherwise unused.
      * */

     /** 6.2.1 HashMap class
      * The HashMap class extends AbstractMap and implements Map interface. It uses a hash table to store the map.
      * This allows the execution time of get() and put() to remain constant even for large sets. It provides the
      * following constructors:
      * - HashMap(): It builds an empty map with default capacity 16 and default fillRatio 0.75
      * - HashMap(Map< ? extends K, ? extends V> m): It builds a map which contains the element of m.
      * - HashMap(int capacity): Set the capacity
      * - HashMap(int capacity, float fillRatio): Set the capacity and fillRatio
      *
      *  Check MapClassesExp.exp1();
      * */

      /** 6.2.2 TreeMap Class
       *
       * The TreeMap class extends AbstractMap and implements NavigableMap interface. It stores entries in a tree
       * structure. Thus it provides an efficient means of storing key/value and allows rapid retrieval. Unlike
       * hashMap, a treeMap guarantees that its entries will be stored in ascending key order. It provides four
       * constructors:
       * - TreeMap(): It builds an empty tree map. Entries will be sorted by using natural order of its keys.
       * - TreeMap(Comparator < ? super K> comp): It builds an empty tree map. Entries will be sorted by using the
       *             comp Comparator.
       * - TreeMap(Map< ? extends K, ? extends V> m): It builds a tree with the entries of m, it uses natural order.
       * - TreeMap(SortedMap<K, ? extends V> sm): It builds a tree with the entries of sm, it uses the same order
       *                  as sm.
       *
       * TreeMap does not add methods, it only implements all methods of NavigableMap interface and AbstractMap class.
       * */

      /** 6.2.3 LinkedHashMap
       *
       * LinkedHashMap class is Hashtable and Linked list implementation of the Map interface, with predictable
       * iteration order. It inherits HashMap class and implements the Map interface.
       *
       * Points to remember
       * - Java LinkedHashMap contains values based on the key.
       * - Java LinkedHashMap contains unique elements.
       * - Java LinkedHashMap may have one null key and multiple null values.
       * - Java LinkedHashMap is non synchronized.
       * - Java LinkedHashMap maintains insertion order.
       * - The initial default capacity of Java HashMap class is 16 with a load factor of 0.75.
       * */

      /** 6.2.3 The IdentityHashMap
       *
       * IdentityHashMap implements Map, Serializable and Clonable interfaces and extends AbstractMap class.
       * This class is not a general-purpose Map implementation. While this class implements the Map interface,
       * it intentionally violates Map’s general contract, which mandates the use of the equals method when comparing
       * objects.
       *
       * This class is used when the user requires the objects to be compared via reference.
       * */

      /** 6.2.4 The EnumMap Class
       * EnumMap is specialized implementation of Map interface for enumeration types. It extends AbstractMap and
       * implements Map Interface in Java. Few important features of EnumMap are as follows:
       * - EnumMap class is a member of the Java Collections Framework & is not synchronized.
       * - EnumMap is ordered collection and they are maintained in the natural order of their keys
       *   (natural order of keys means the order on which enum constant are declared inside enum type )
       * - It’s a high performance map implementation, much faster than HashMap.
       * - All keys of each EnumMap instance must be keys of a single enum type.
       * - EnumMap doesn’t allow null key and throw NullPointerException, at same time null values are permitted.
       * */
    /***********************************************************************************************************
     * ******************************************* Code example ***********************************************
     * **********************************************************************************************************/
public static void main(String[] args){
    // array list
    //CollectionClassesExp.exp1();
   // CollectionClassesExp.exp2();

    // linked list
   // CollectionClassesExp.exp3();

    // hashSet
    // CollectionClassesExp.exp4();

     // LinkedHashSet
    // CollectionClassesExp.exp5();

    // TreeSet
   // CollectionClassesExp.exp6();

    // PriorityQueue
    // CollectionClassesExp.exp7();

    // ArrayDeque
   //  CollectionClassesExp.exp8();

    //EnumSet
    //CollectionClassesExp.exp9();

    // using iteration
   // CollectionClassesExp.exp10();

    // for each
   // CollectionClassesExp.exp11();

    //spliterator
   // CollectionClassesExp.exp12();

    // store user defined class
  //  CollectionClassesExp.exp13();

    //instanceof
  //  CollectionClassesExp.exp14();

    //hashMap
  //  MapClassesExp.exp1();

    // TreeMap
  //  MapClassesExp.exp2();
}
}
