package org.pengfei.Lesson13_Common_Data_Structure.L13_S6_List;

import java.util.*;
import java.util.stream.Collectors;

public class L13_S6_List {

    public static void main(String[] args){

        L13_S6_List listExamples=new L13_S6_List();

        /************************************************************************************************
         * ********************************** 13.6 List  ****************************************
         * **********************************************************************************************/

        /*********************** 13.6.1  Introduction  **************************/
        /* We have seen the ADTs of Linked List, which we can only access the first or the last element of the collection.
        * To access other elements, we have to loop through the collection.
        *
        * In this Lesson, we explore several ADTs that represent a linear sequence of elements, but with more general
        * support for adding or removing elements at arbitrary positions.
        *
        * Java defines a general interface, java.util.List, that includes the following index-based methods:
        *
        * - size(): Returns the number of elements in the list.
        *
        * - isEmpty(): Returns a boolean indicating whether the list is empty.
        *
        * - get(i): Returns the element of the list having index i; an error condition occurs if i is
        *           not in range [0, size( )−1].
        *
        * - set(i, e): Replaces the element at index i with e, and returns the old element that was replaced;
        *              an error condition occurs if i is not in range[0, size( )−1].
        *
        * - add(i, e): Inserts a new element e into the list so that it has index i, moving all subsequent elements
        *              one index later in the list; an error condition occurs if i is not in range [0, size( )].
        *
        * - remove(i): Removes and returns the element at index i, moving all subsequent elements one index earlier
        *              in the list; an error condition occurs if i is not in range [0, size( )−1].
        *
        *  We note that the index of an existing element may change over time, as other elements are added or
        *  removed in front of it. We also draw attention to the fact that the range of valid indices for the
        *  add method includes the current size of the list, in which case the new element becomes the last.
        *
        *  */

        /*********************** 13.6.2  ArrayLists  **************************/

        /* An obvious choice for implementing the list ADT is to use an array A, where A[i] stores the element of index
        * i. We will begin by assuming that we have a fixed-capacity array. But, in this lesson, we will use a more
        * advanced technique that effectively allows an array-based list to have unbounded capacity. Such an unbounded
        * list is know as an "array list" in Java (or a vector in C++)
        *
        * The complexity of methods such as size(),isEmpty(),get(i),set(i,e) is o(1), method such as
        * add(i,e) and remove(i) are (o(n)), because we have to shifting elements up or down to maintain our rule
        * of always storing an element whose list index is i at index i of the array.
         *
         * To overcome the fix capacity problem, Java's ArrayList provides a more robust abstraction, which relies on
          * an algorithmic sleight of hand, that is known as a dynamic array.
          *
          * The principe is very simple, when user create a new array, the jvm reserve a little bit more space for
          * the array. User can easily add a small amount of elements into the array without changing anything.
          * If user want's to add larger number of elements, jvm will create a new array with larger capacity and copy
          * the elements in the small array to the bigger one, then dereference the small one. We can formalize these
          * steps with the following steps:
          *
          * 1. Allocate a new array B with larger capacity.
          * 2. Set B[k]=A[k], for k=0,...,n-1 where n is the current number of items in the List
          * 3. Set A = B . we henceforth use the new array to support the list.
          * 4. Insert the new element in the new array.
           * */

        /* We implement this design in the class DynamicArrayList. We decide that, when the array capacity is reached,
        * we will just double the array size. */
 //listExamples.DynamicArrayListExamples();



/*********************** 13.6.3  Positional Lists  **************************/

/* When working with array based sequence, integer indices provide an excellent means for describing the location
* of an element. However, numeric indices are not a good choice for describing position within a linked list, because
* the only way to reach a element by using its index, is to traverse the list incrementally from its begining or end by
* counting along the way.
*
* Furthermore, indices changes when insertion or deletions happen. We need an abstraction, in which there is some other
* means for describing a position. Our goal is to design an abstract data type that provides a user a way to refer to
* elements anywhere in a sequence, and to perform arbitrary insertions and deletions. This would allow us to
* efficiently describe actions such as a person deciding to leave the line before reaching the front, or allowing a
* friend to “cut” into line right behind him or her
*
* As another example, a text document can be viewed as a long sequence of characters. A word processor uses
* the abstraction of a cursor to describe a position within the document without explicit use of an integer index,
* allowing operations such as “delete the character at the cursor” or “insert a new character just after the cursor.”
*
* For these reasons, we temporarily forego the index-based methods of Java’s formal List interface, and instead develop
* our own abstract data type that we denote as a "positional list". Although a positional list is an abstraction, and
* need not rely on a linked list for its implementation, we certainly have a linked list in mind as we design the ADT,
* ensuring that it takes best advantage of particular capabilities of a linked list, such as O(1)-time insertions and
* deletions at arbitrary positions (something that is not possible with an array-based sequence).
*
* The core of a linked list is the manipulation of Node. We want to encapsulate the nodes of a linked list, for both
* our sake and for the benefit of users of our abstraction: 1st reason, we can provide a more robust data structure
* if we do not permit users to directly access or manipulate the nodes. We can then ensure that users do not invalidate
* the consistency of a list by mismanaging the linking of nodes. A more subtle problem arises if a user were allowed to
* call the addBetween or remove method of our DoublyLinkedList class, sending a node that does not belong to the given
* list as a parameter. (Go back and look at that code and see why it causes a problem! L13_S2).
* 2nd reason, by better encapsulating the internal details of our implementation, we have greater flexibility to
* redesign the data structure and improve its performance. In fact, with a well-designed abstraction, we can provide
* a notion of a nonnumeric position, even if using an array-based sequence. (See Exercise C-7.43.)
*
* Therefore, in defining the positional list ADT, we also introduce the concept of a position, which formalizes the
* intuitive notion of the “location” of an element relative to others in the list. (When we do use a linked list for
* the implementation, we will later see how we can privately use node references as natural manifestations of positions.)
*
* */

/*********************** 13.6.3.1  Positions  **************************/

/* To provide a general abstraction for the location of an element within a structure, we define a simple position
* abstract data type. A position supports the following single method:
* getElement( ): Returns the element stored at this position.
* A position acts as a marker or token within a broader positional list. A position p, which is associated with
* some element e in a list L, does not change, even if the index of e changes in L due to insertions or deletions
* elsewhere in the list. Nor does position p change if we replace the element e stored at p with another element. The
* only way in which a position becomes invalid is if that position (and its element) are explicitly removed from the
* list. Having a formal definition of a position type allows positions to serve as parameters to some methods and
* return values from other methods of the positional list ADT, which we next describe.*/


    /*********************** 13.6.3.2  Positional Lists ADT **************************/

    /*We now view a positional list as a collection of positions, each of which stores an element. The accessor
    * methods provided by the positional list ADT include the following, for a list L:
    * first( ): Returns the position of the first element of L (or null if empty).
    * last( ): Returns the position of the last element of L (or null if empty).
    * before(p): Returns the position of L immediately before position p (or null if p is the first position)
    * after(p): Returns the position of L immediately after position p (or null if p is the last position).
    * isEmpty( ): Returns true if list L does not contain any elements.
    * size( ): Returns the number of elements in list L.
    *
    * An error occurs if a position p, sent as a parameter to a method, is not a valid position for the list.
    *
    * Note well that the first( ) and last( ) methods of the positional list ADT return the associated positions,
    * not the elements. (This is in contrast to the corresponding first and last methods of the deque ADT.) The first
    * element of a positional list can be determined by subsequently invoking the getElement method on that position,
    * as first( ).getElement. The advantage of receiving a position as a return value is that we can subsequently use
    * that position to traverse the list.
    *
    * Updated Methods of a Positional List L:
    * addFirst(e): Inserts a new element e at the front of the list, returning the position of the new element.
    * addLast(e): Inserts a new element e at the back of the list, returning the position of the new element.
    * addBefore(p, e): Inserts a new element e in the list, just before position p, returning the position of the new element.
    * addAfter(p, e): Inserts a new element e in the list, just after position p, returning the position of the new element.
    * set(p, e): Replaces the element at position p with element e, returning the element formerly at position p.
    * remove(p): Removes and returns the element at position p in the list, invalidating the position.
    *
    * There may at first seem to be redundancy in the above repertoire of operations for the positional list ADT,
    * since we can perform operation addFirst(e) with addBefore(first( ), e), and operation addLast(e) with
    * addAfter(last( ), e). But these substitutions can only be done for a nonempty list.
    *
    * Method | Return Value | List Contents
    * addLast(8)|  p | (8p)
    * first( )| p | (8p)
    * addAfter(p, 5)|  q | (8p, 5q)
    * before(q) | p | (8p, 5q)
    * addBefore(q, 3)| r | (8p, 3r, 5q)
    * r.getElement( ) |3 |(8p, 3r, 5q)
    * after(p) |r |(8p, 3r, 5q)
    * before(p) |null |(8p, 3r, 5q)
    * addFirst(9) |s |(9s, 8p, 3r, 5q)
    * remove(last( )) |5 |(9s, 8p, 3r)
    * set(p, 7) |8 | (9s, 7p, 3r)
    * remove(q) |“error”| (9s, 7p, 3r)
    * */

    /* In the following example, we called our Linked Positional list implementation, we can notice that all methods in
    * this have complexity o(1). The memory space usage is o(n).
    * */

//listExamples.LinkedPositionalListExamples();

/* We can also use array to implement the positional list, a Position will be an object which stores the element and the
* index of the element in the array. When we do addLast, addFirst, addBetween, we need to update all index of the list.
* As a result, the complexity for these add method will be o(n). */

/**************************************** 13.6.4  Iterators  **********************************************/

/* An iterator is a software design pattern that abstracts the process of scanning through a sequence of elements,
* one element at a time. The underlying elements might be stored in a container class, streaming through a network,
* or generated by a series of computations.
*
* In order to unify the treatment and syntax for iterating objects in a way that is independent from a specific
* organization, Java defines the java.util.Iterator interface with the following two methods:
* hasNext( ): Returns true if there is at least one additional element in the sequence, and false otherwise.
* next( ): Returns the next element in the sequence.
*
* The iterator interface uses Java’s generic framework, So the next method can return a parameterized type. If the
* next( ) method of an iterator is called when no further elements are available, a NoSuchElementException is thrown.
* Of course, the hasNext( ) method can be used to detect that condition before calling next( ).
*
* The java.util.Iterator interface contains a third method, which is optionally supported by some iterators:
* remove( ): Removes from the collection the element returned by the most recent call to next( ). Throws an
*            IllegalStateException if next has not yet been called, or if remove was already called since the
*            most recent call to next.
* This method can be used to filter a collection of elements, for example to discard all negative numbers from a data set.
*/

/**************************** 13.6.4.1 The iterable interface  ****************************************/

/* A single iterator instance supports only one pass through a collection; calls to next can be made until all
* elements have been reported, but there is no way to “reset” the iterator back to the beginning of the sequence.
*
* However, a data structure that wishes to allow repeated iterations can support a method that returns a new iterator,
* each time it is called. To provide greater standardization, Java defines another parameterized interface,
* named Iterable, that includes the following single method:
* iterator( ): Returns an iterator of the elements in the collection.
*
* Java’s Iterable class plays a fundamental role in support of the “for-each” loop syntax. The following loop syntax,
* for (ElementType variable : collection) {
* loopBody // may refer to ”variable”
* }
* is supported for any instance, collection, of an iterable class. ElementType must be the type of object returned by
* its iterator, and variable will take on element values within the loopBody. Essentially, this syntax is shorthand
* for the following
*
* Iterator<ElementType> iter = collection.iterator( );
* while (iter.hasNext( )) {
* ElementType variable = iter.next( );
* loopBody // may refer to ”variable”
* }
*
*
* */

/*
* We note that the iterator’s remove method cannot be invoked when using the for-each loop syntax. Instead, we must
* explicitly use an iterator. As an example, the following loop can be used to remove all negative numbers from an
* ArrayList of floating-point values.
* ArrayList<Double> data; // populate with random numbers (not shown)
* Iterator<Double> walk = data.iterator( );
* while (walk.hasNext( ))
*   if (walk.next( ) < 0.0)
*   walk.remove( );
*
*   */

/**************************** 13.6.4.2 Implementing iterator  ****************************************/

/* There are two general styles for implementing iterators that differ in terms of what work is done when the iterator
* instance is first created, and what work is done each time the iterator is advanced with a call to next( ).
*
* snapshot iterator: maintains its own private copy of the sequence of elements, which is constructed at the time the
*                    iterator object is created. It effectively records a “snapshot” of the sequence of elements at
*                    the time the iterator is created, and is therefore unaffected by any subsequent changes to the
*                    primary collection that may occur. Implementing snapshot iterators tends to be very easy, as
*                    it requires a simple traversal of the primary structure. The downside of this style of iterator
*                    is that it requires O(n) time and O(n) auxiliary space, upon construction, to copy and store
*                    a collection of n elements.
*
* lazy iterator: is one that does not make an upfront copy, instead performing a piecewise traversal of the primary
*                structure only when the next( ) method is called to request another element. The advantage of this
*                style of iterator is that it can typically be implemented so the iterator requires only O(1) space
*                and O(1) construction time. One downside (or feature) of a lazy iterator is that its behavior is
*                affected if the primary structure is modified (by means other than by the iterator’s own remove method)
*                before the iteration completes. Many of the iterators in Java’s libraries implement a “fail-fast”
*                behavior that immediately invalidates such an iterator if its underlying collection is modified unexpectedly
*
* */

/* We implement an arrayList iterator(lazy) in L13_S6_List.DynamicArrayList as inner class. See the code at the end of
 * the class DynamicArrayList */

/* The iterator implementation of the positional list is a little bit complicate. First we need to decide if we want to
* iterator over the position or the element of the position. If we allow a user to iterate through all positions of
* the list, those positions could be used to access the underlying elements, so support for position iteration is more
* general. However, it is more standard for a container class to support iteration of the core elements, by default,
* so that the for-each loop syntax could be used to write code such as the following,
* for (String guest : waitlist) //assuming that variable waitlist has type LinkedPositionalList<String>.
*
* For maximum convenience, we will support both forms of iteration. We will have the standard iterator( ) method
* return an iterator of the elements of the list, so that our list class formally implements the Iterable interface
* for the declared element type.
*
* For those wishing to iterate through the positions of a list, we will provide a new method, positions( ). At first
* glance, it would seem a natural choice for such a method to return an Iterator. However, we prefer for the return
* type of that method to be an instance that is Iterable (and hence, has its own iterator( ) method that returns an
* iterator of positions). Our reason for the extra layer of complexity is that we wish for users of our class to be
* able to use a for-each loop with a simple syntax such as the following: for (Position<String> p : waitlist.positions())
* For this syntax to be legal, the return type of positions( ) must be Iterable.
* */


/**************************************** 13.6.5 Java collection framework  **********************************************/

/* The root interface in the Java collections framework is named Collection. This is a general interface for any data
* structure, such as a list, that represents a collection of elements. The Collection interface includes many methods,
* including some we have already seen (e.g., size( ), isEmpty( ), iterator( )). It is a superinterface for other
* interfaces in the Java Collections Framework that can hold elements, including the java.util interfaces Deque,
* List, and Queue, and other subinterfaces discussed later including Set and Map.
*
* The Java Collections Framework also includes concrete classes implementing various interfaces with a combination of
* properties and underlying representations. We summarize but a few of those classes in the following table:
*
*  Class Name            |  Interface  | Properties | Storage
* ArrayBlockingQueue: Queue | Capacity limit, thread safe, blocking | Array List
* LinkedBlockingQueue: Queue | Capacity limit, thread safe, blocking | Linked List
* ConcurrentLinkedQueue: Queue | thread safe | Array List
* ArrayDeque: Queue, Deque | null | Array List
* LinkedBlockingDeque: Queue, Deque | Capacity limit, thread safe, blocking | Linked List
* ConcurrentLinkedDeque: Queue, Deque | thread safe | Linked List
* ArrayList: List | null | Array List
* LinkedList: Queue, Deque, List | null | Linked List
*
* Note that classes which support concurrency, allowing multiple processes to share use of a data structure in a
* thread-safe manner. If the structure is designated as blocking, a call to retrieve an element from an empty
* collection waits until some other process inserts an element. Similarly, a call to insert into a full blocking
* structure must wait until room becomes available.
*
* */


/************************** 13.6.5.1 List Iterators **************************************/

/*
* The java.util.LinkedList class does not expose a position concept to users in its API, as we do in our linked
* positional list ADT. Instead, the preferred way to access and update a LinkedList object in Java, without using
* indices, is to use a ListIterator that is returned by the list’s listIterator( ) method. Such an iterator
* provides forward and backward traversal methods as well as local update methods. It views its current position
* as being before the first element, between two elements, or after the last element. That is, it uses a list
* cursor, much like a screen cursor is viewed as being located between two characters on a screen. Specifically,
* the java.util.ListIterator interface includes the following methods:
*
* add(e): Adds the element e at the current position of the iterator.
* hasNext( ): Returns true if there is an element after the current position of the iterator.
* hasPrevious( ): Returns true if there is an element before the current position of the iterator.
* previous( ): Returns the element e before the current position and sets the current position to be before e.
* next( ): Returns the element e after the current position and sets the current position to be after e.
* nextIndex( ): Returns the index of the next element.
* previousIndex( ): Returns the index of the previous element.
* remove( ): Removes the element returned by the most recent next or previous operation.
* set(e): Replaces the element returned by the most recent call to the next or previous operation with e.
*
* It is risky to use multiple iterators over the same list while modifying its contents. If insertions, deletions,
* or replacements are required at multiple “places” in a list, it is safer to use positions to specify these locations.
* But the java.util.LinkedList class does not expose its position objects to the user. So, to avoid the risks of
* modifying a list that has created multiple iterators, the iterators have a “fail-fast” feature that invalidates
* such an iterator if its underlying collection is modified unexpectedly. For example, if a java.util.LinkedList
* object L has returned five different iterators and one of them modifies L, a ConcurrentModificationException is
* thrown if any of the other four is subsequently used. That is, Java allows many list iterators to be traversing a
* linked list L at the same time, but if one of them modifies L (using an add, set, or remove method), then all the
* other iterators for L become invalid. Likewise, if L is modified by one of its own update methods, then all existing
* iterators for L immediately become invalid.
* */

// listExamples.JavaCollectionExamples();

/********************** 13.6.5.2 List-Based Algorithms in the Java Collections Framework ************************/

/*
* In addition to the classes that are provided in the Java Collections Framework, there are a number of simple
* algorithms that it provides as well. These algorithms are implemented as static methods in the java.util.Collections
* class (not to be confused with the java.util.Collection interface) and they include the following methods:
*
* copy(Ldest , Lsrc): Copies all elements of the Lsrc list into corresponding indices of the Ldest list.
* disjoint(C, D): Returns a boolean value indicating whether the collections C and D are disjoint.
* fill(L, e): Replaces each element of the list L with element e.
* frequency(C, e): Returns the number of elements in the collection C that are equal to e.
* max(C): Returns the maximum element in the collection C, based on the natural ordering of its elements.
* min(C): Returns the minimum element in the collection C, based on the natural ordering of its elements.
* replaceAll(L, e, f ): Replaces each element in L that is equal to e with element f.
* reverse(L): Reverses the ordering of elements in the list L.
* rotate(L, d): Rotates the elements in the list L by the distance d (which can be negative), in a circular fashion.
* shuffle(L): Pseudorandomly permutes the ordering of the elements in the list L.
* sort(L): Sorts the list L, using the natural ordering of its elements.
* swap(L, i, j): Swap the elements at indices i and j of list L.
*
*
* */

/*
* Converting Lists into Arrays
*
* Lists are a beautiful concept and they can be applied in a number of different contexts, but there are some
* instances where it would be useful if we could treat a list like an array. Fortunately, the java.util.Collection
* interface includes the following helpful methods for generating an array that has the same elements as the given
* collection:
* toArray( ): Returns an array of elements of type Object containing all the elements in this collection.
* toArray(A): Returns an array of elements of the same element type as A containing all the elements in this collection.
*
* If the collection is a list, then the returned array will have its elements stored in the
* same order as that of the original list. Thus, if we have a useful array-based method that we want to use on a
* list or other type of collection, then we can do so by simply using that collection’s toArray( ) method to produce
* an array representation of that collection.
*
* Converting Arrays into Lists
*
* In a similar vein, it is often useful to be able to convert an array into an equivalent list. Fortunately,
* the java.util.Arrays class includes the following method:
* asList(A): Returns a list representation of the array A, with the same element type as the elements of A.
* The list returned by this method uses the array A as its internal representation for the list. So this list is
* guaranteed to be an array-based list and any changes made to it will automatically be reflected in A. Because of
* these types of side effects, use of the asList method should always be done with caution, so as to avoid unintended
* consequences. But, used with care, this method can often save us a lot of work. For instance, the following code
* fragment could be used to randomly shuffle an array of Integer objects, arr:
* Integer[ ] arr = {1, 2, 3, 4, 5, 6, 7, 8}; // allowed by autoboxing
* List<Integer> listArr = Arrays.asList(arr);
* Collections.shuffle(listArr); // this has side effect of shuffling arr
* It is worth noting that the array A sent to the asList method should be a reference type (hence, our use of
* Integer rather than int in the above example). This is because the List interface is generic, and requires that
* the element type be an object.
*
* */
//listExamples.JavaCollectionListAlgoExamples();


/**************************************** 13.6.6 Sorting a positional list  ******************************************/

/* The detail of how to sort a positional list is in the class of SortingPositionalList.*/

/********************************** 13.6.7 Case Study: Maintaining access frequencies  *********************************/

/*Todo*/

        /* In this section, we will consider maintaining a collection of elements while keeping track of the number
        * of times each element is accessed. Keeping such information allows us to know which elements are among the
        * most popular. Examples of such scenarios include a web browser that keeps track of a user's most accessed
        * pages, or a music collection that maintains a list of the most frequently played songs for a user. We will
        * model this with a new favorites list ADT that supports the size and isEmpty methods as well as the following:
        * - access(e): Accesses the element e, adding it to the favorites list if it is not already present, and
        *              increments its access count.
        * - remove(e): Removes element e from the favorites list, if present.
        * - getFavorites(k): Returns an iterable collection of the k most accessed elements.*/

       /************************* 13.6.7.1 Using the composite pattern  ***********/

/*
* We wish to implement a favorites list by making use of a PositionalList for storage. If elements of the
* positional list were simply elements of the favorites list, we would be challenged to maintain access counts
* and to keep the proper count with the associated element as the contents of the list are reordered. We use a
* general object oriented design pattern, the "composition pattern", in which we define a single object that is
* composed of two or more other objects. Specifically, we define a nonpublic nested class, "Item", that stores
* the element and its access count as a single object. We then maintain our favorites list as a PositionalList
* of item instances, so that the access count for a user’s element is embedded alongside it in our representation.
* (An Item is never exposed to a user of a FavoritesList.)
*
* The implementation is given in the class FavoritesList. Call the below method to see an usage example.
* */

//listExamples.CompositionDPAccessFrequenciesExample();

        /************************* 13.6.7.2 Using the Move-to-Front Heuristic  ***********/

        /*
        *  The previous implementation of a favorites list performs the access(e) method in
        *  time proportional to the index of e in the favorites list. That is, if e is the k th most
        *  popular element in the favorites list, then accessing it takes O(k) time. In many
        *  real-life access sequences (e.g., Web pages visited by a user), once an element is
        *  accessed it is more likely to be accessed again in the near future. Such scenarios
        *  are said to possess "locality of reference".
        *
        *  A "heuristic", or rule of thumb, that attempts to take advantage of the locality of
        *  reference that is present in an access sequence is the move-to-front heuristic. To
        *  apply this heuristic, each time we access an element we move it all the way to the
        *  front of the list. Our hope, of course, is that this element will be accessed again in
        *  the near future. Consider, for example, a scenario in which we have n elements and
        *  the following series of n^2 accesses:
        *
        *  - element 1 is accessed n times. -> for each access, the complexity is O(1)(i.e. 1), so in total we need n
        *  - element 2 is accessed n times. -> O(2), so in total we need n*2
        *  - ... ...
        *  - element n is accessed n times. -> O(n), in total we need n*n
        *
        *  The sum of the above operations is n+n*2+...n*n=n(1+2+...+n)=(n*n(n+1))/2. So we can say the total complexity
        *  of the above operations is O(n^3)
        *
        *  If we use the Move-to-Front Heuristic, we will have below complexity
        *  - element 1 is accessed n times. -> for each access, the complexity is O(1)(i.e. 1), so in total we need n
        *  - element 2 is accessed n times. -> O(1), because we move element 2 to the first position
        *  - ... ...
        *  - element n is accessed n times. -> O(1).
        *
        *  So in total, n+...+n=n^2, so we can say the total complexity with move-to-front heuristic is O(n^2)
        *  */

        /* The trade-offs with the move-to-front heuristic
        * As we no longer maintain the elements of the list ordered by their access counts, when we are asked
        * to find the k most accessed elements, we need to search for them. So we need to modify the implementation
        * of the getFavorites(k) method as follows:
        * 1. We copy all entries of our favorites list into another list, named temp.
        * 2. We scan the temp list k times. In each scan, we find the entry with the largest access count, then remove
        *    this entry from temp, and add it to the results.
        *
        * The implementation is given in the class FavoritesListMTF
        * */
     listExamples.MTFAccessFrequenciesExample();
    }


    /*********************** 13.6.2  dynamic Array List examples  **************************/
    public void DynamicArrayListExamples(){
        DynamicArrayList<Integer> myList=new DynamicArrayList<>(5);

        for(int i=0;i<5;i++){
            myList.add(i,i);
            System.out.println("List content at position : "+i+ " is : "+myList.get(i)); ;
        }

        myList.add(2,13);

        for(int i=0;i<6;i++){
            System.out.println("List content at position : "+i+ " is : "+myList.get(i)); ;
        }
    }


    /************************* 13.6.3 Linked Positional List examples **************************/
    public void LinkedPositionalListExamples(){
        LinkedPositionalList<Integer> lpList=new LinkedPositionalList();
        for(int i=0;i<5;i++){
            lpList.addFirst(i);
        }
        Position<Integer> firstPosition = lpList.first();
        Position<Integer> itPosition=lpList.after(firstPosition);
        while(lpList.after(itPosition)!=null){
            Integer currentVal= itPosition.getElement();
            System.out.println("current value: "+currentVal);
            itPosition=lpList.after(itPosition);
        }
    }

    public void JavaCollectionExamples(){
        LinkedList<Integer> list=new LinkedList<>();
        for(int i=0;i<5;i++){
            // add
            list.add(i);
        }
        ListIterator<Integer> iterator = list.listIterator();
        Integer tmp;
        while(iterator.hasNext()){
            tmp=iterator.next();
            System.out.println("value: "+tmp);
        }

    }

    public void JavaCollectionListAlgoExamples(){

        /*************************************1. Copy list *******************************************/

        /**********************************Example 1.1 **********************************/
        /* You can notice that all the previous elements in the dest list were overwritten by source elements
        * because both lists have the same size.*/
        java.util.List<Integer> source = (java.util.List<Integer>)Arrays.asList(1,2,3);
        java.util.List<Integer> dest = (java.util.List<Integer>) Arrays.asList(4,5,6);
        Collections.copy(dest, source);
        System.out.println("****************************The start of example 1.1 **************************");
        for(Integer element:dest){
            System.out.println("value: "+element);
        }
        System.out.println("****************************The end of example 1.1 **************************");

        /*************************************** Example 1.2 ********************************************/
        /* You can notice, in this example, just the three first items were overwritten while the rest of the elements
        * in the list are conserved.*/
        java.util.List<Integer> source1 = (java.util.List<Integer>)Arrays.asList(1,2,3);
        java.util.List<Integer> dest1 = (java.util.List<Integer>) Arrays.asList(5, 6, 7, 8, 9, 10);
        Collections.copy(dest1, source1);
        System.out.println("****************************The start of example 1.2 **************************");
        for(Integer element:dest1){
            System.out.println("value: "+element);
        }
        System.out.println("****************************The end of example 1.2 **************************");


        /*******************************************Example 3 *******************************************/

        /* The following copy always fails, the copy operation IndexOutOfBoundsException: Source does not fit in dest*/
        /*java.util.List<Integer> aList1 = new ArrayList(5);
        java.util.List<Integer> aList2 = new ArrayList(5);

        LinkedList<Integer> lList=new LinkedList();
        for(int i=0;i<5;i++){
            // add
            aList1.add(i);
        }
        // Collections is the implementation of the interface Collection, we can't copy arrayList to linkedList or viceversa
        Collections.copy(aList2,aList1);

        for(Integer element:aList2){
            System.out.println("value: "+element);
        }*/

        /********************************************Example 4 ********************************************/

        /* In java 8, we can also use stream to copy list, the advantage of this copy, we can skip element or filter.
        * It’s possible to filter by the length of the String too or by comparing an attribute of our objects: */
        System.out.println("****************************The start of example 1.4 **************************");
/* The source list has 1,2,3 , because we skip 1, so the result should  be 2,3.*/
        java.util.List<Integer> skip1 = source.stream().skip(1).collect(Collectors.toList());
        skip1.forEach(x->System.out.println(x));

        java.util.List<String> strList= Arrays.asList("hello","nooob","foo","bar");
// we only take string which length is greater than 3. the result should be hello, nooob
        java.util.List<String> filterList = strList.stream()
                .filter(s -> s.length() > 3)
                .collect(Collectors.toList());

        filterList.forEach(x->System.out.println(x));
        /*********************************** 2. Disjoint *************************************************/
/* We say two lists are disjoint if there is no common element in the two list, source1 and dest1 are not disjoint,
* because they both have 3 as common element. disjointList1 and 2 are disjoint, no common element
* */
        java.util.List<Integer> disjointList1 = (java.util.List<Integer>)Arrays.asList(1,2,3);
        java.util.List<Integer> disjointList2 = (java.util.List<Integer>) Arrays.asList(4,5,6);
        System.out.println("****************************The start of example 2.1 **************************");
        boolean disjointFlag = Collections.disjoint(source1, dest1);
        System.out.println("source and dest list is disjoint: "+disjointFlag);
        System.out.println("disjointList1 and disjointList2 list is disjoint: "+Collections.disjoint(disjointList1,disjointList2));

        /*********************************** 3. fill *************************************************/
        /* fill the list with a given element, note that the list must be initialized with values. otherwise the fill
        * operation does nothing, in another word, fill only replace existing values. */
        System.out.println("****************************The start of example 3.1 **************************");
        java.util.List<Integer> fillList = Arrays.asList(1,2,3,4,5);
        Collections.fill(fillList,(Integer)10);
        fillList.forEach(x->System.out.println(x));

        /********************************** 4. replaceAll********************************************/
        /* replaceAll(L, e, f ): Replaces each element in L that is equal to e with element f.*/
        System.out.println("****************************The start of example 4.1 **************************");
        java.util.List<Integer> repList = Arrays.asList(1,2,3,4,5);
        Collections.replaceAll(repList,3,(Integer)10);
        repList.forEach(x->System.out.println(x));

        /**********************************5. frequency *********************************************/
        System.out.println("****************************The start of example 5.1 **************************");
        java.util.List<Integer> freqList = Arrays.asList(1,2,2,2,1);
        int frequency = Collections.frequency(freqList, 2);
        System.out.println("The frequency of 2 is: "+frequency);

        /*********************************6. min/max ***********************************************/
        System.out.println("****************************The start of example 6.1 **************************");
        int min=Collections.min(repList);
        int max=Collections.max(repList);
        System.out.println("The min of list is: "+min);
        System.out.println("The max of list is: "+max);

        /************************************7. Reverse/sort/shuffle/rotate**************************************/
        System.out.println("****************************The start of example 7.1 **************************");
        java.util.List<Integer> rawList = Arrays.asList(1,2,3,4,5);

        /*We reverse the element first*/
        Collections.reverse(rawList);
        System.out.println(rawList.toString());

        /* Then we shuffle it */
        Collections.shuffle(rawList);
        System.out.println(rawList.toString());

        /* then we sort the list*/
        Collections.sort(rawList);
        System.out.println(rawList.toString());

        /* rotate the list with a int distance, the distance can be negative, think it as modular, for example, the
        * input list is 1,2,3,4,5. If we rotate the list with a distance of 2, all the index of element will be
        * plus 2, as a result, element 1 now has index 2, 2 has the index 3, 4 has the index 5 (5 mod 5 will be 0) */
        Collections.rotate(rawList,2);
        System.out.println(rawList.toString());

        /************************************8. swap **************************************/
        System.out.println("****************************The start of example 8.1 **************************");
        java.util.List<Integer> swapList = Arrays.asList(1,2,3,4,5);

        /*We swap the element of the first index with the second index. */
        Collections.swap(swapList,0,4);
        System.out.println(swapList.toString());


    }

/******************* 13.6.7 Case Study: Maintaining access frequencies  **********/
public void CompositionDPAccessFrequenciesExample(){
    //Build a favorite list
    FavoritesList<String> myFList=new FavoritesList<>();

    //Add elements into the list
    myFList.addElement("Spark");
    myFList.addElement("Hadoop");
    myFList.addElement("Hive");
    myFList.addElement("Kafka");

    // access these elements
    myFList.access("Spark");
    myFList.access("Spark");
    myFList.access("Spark");

    myFList.access("Hadoop");
    myFList.access("Hadoop");

    myFList.access("Hive");
    //Get the most popular words
    Iterable<String> it1 = myFList.getFavorites(1);
    it1.forEach(x->System.out.println(x));
    //Get the two first most popular words
    Iterable<String> it2 = myFList.getFavorites(2);
    it2.forEach(x->System.out.println(x));

    //Get the three first most popular words
    Iterable<String> it3 = myFList.getFavorites(3);
    it3.forEach(x->System.out.println(x));

   //remove hive
    myFList.remove("Hive");

    System.out.println("After removing Hive");
    //Get the three first most popular words
    Iterable<String> it4 = myFList.getFavorites(3);
    it4.forEach(x->System.out.println(x));
}
    public void MTFAccessFrequenciesExample(){
        //Build a favorite list
        FavoritesListMTF<String> myMTFFList=new FavoritesListMTF<>();

        //Add elements into the list
        myMTFFList.addElement("Spark");
        myMTFFList.addElement("Hadoop");
        myMTFFList.addElement("Hive");
        myMTFFList.addElement("Kafka");

        // access these elements
        myMTFFList.access("Spark");
        myMTFFList.access("Spark");
        myMTFFList.access("Spark");

        myMTFFList.access("Hadoop");
        myMTFFList.access("Hadoop");

        myMTFFList.access("Hive");
        //Get the most popular words
        Iterable<String> it1 = myMTFFList.getFavorites(1);
        it1.forEach(x->System.out.println(x));
        //Get the two first most popular words
        Iterable<String> it2 = myMTFFList.getFavorites(2);
        it2.forEach(x->System.out.println(x));

        //Get the three first most popular words
        Iterable<String> it3 = myMTFFList.getFavorites(3);
        it3.forEach(x->System.out.println(x));

        //remove hive
        myMTFFList.remove("Hive");

        System.out.println("After removing Hive");
        //Get the three first most popular words
        Iterable<String> it4 = myMTFFList.getFavorites(3);
        it4.forEach(x->System.out.println(x));
    }

}
