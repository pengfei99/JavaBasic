package org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util;

import org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source.CollectionClassesExp;

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
    CollectionClassesExp.exp9();
}
}
