package org.pengfei.Lesson13_Common_Data_Structure.L13_S4_Queues;

import org.pengfei.Lesson13_Common_Data_Structure.L13_S4_Queues.source.ArrayBasedQueue;
import org.pengfei.Lesson13_Common_Data_Structure.L13_S4_Queues.source.BasicQueueExample;
import org.pengfei.Lesson13_Common_Data_Structure.L13_S4_Queues.source.ListBasedCircularQueue;
import org.pengfei.Lesson13_Common_Data_Structure.L13_S4_Queues.source.SinglyLinkedListBasedQueue;
import org.pengfei.Lesson13_Common_Data_Structure.L13_S4_Queues.source.blocking_queue.BlockingQueueExample;

import java.util.concurrent.BlockingQueue;

public class L13_S4_Queues {

    public static void main(String[] args){

        /**********************************************************************************
         * *****************************13.4 Queues ************************************
         * *******************************************************************************/

        /*********************************13.4.1 Introduction **********************************/

        /* As stacks, queue is another fundamental data structure. A queue is a collection of objects that are
        * inserted and removed according to the first-in, first-out (FIFO).
        *
        * There are many nature examples, such as queue for buying tickets, queue for client service phone calls*/

        /* The Queue Abstract Data type
        *
        * Formally, the queue abstract data type defines a collection that keeps objects in a sequence, where element
        * access and deletion are restricted to the first element in the queue, and element insertion is restricted
        * to the back of the sequence. This restriction enforces the rule that items are inserted and deleted in a
        * queue according to the first-in, first-out (FIFO) principle. The queue abstract data type (ADT) supports the
        * following two update methods:
        *
        * - enqueue(e) : Adds element e to the back of queue
        * - dequeue() : Removes and returns the first element from the queue (or null if the queue is empty)
        *
        * It also includes the following accessor methods:
        *
        * - first() : Returns the first element of the queue, without removing it (or null if the queue is empty)
        * - size() : Returns the number of elements in the queue.
        * - isEmpty() : Returns a boolean indicating whether the queue is empty.
        *
        * */

        /******************************13.4.2 Queue Interface *********************************/

        /* In interface Queue, we defined a simple queue interface which contains the 5 above method.
        *
        * The java.util.Queue interface provides a interface which is similar to the traditional queue ADT.
        * But the documentation of the java.util.Queue interface does not insist that it support only the FIFO principle.
        * When supporting FIFO, it have the equivalences with the Queue ADT in this Lesson.
        *
        * The java.util.Queue interface supports two styles for most operations, which vary in the way how they treat
        * exceptions. When a queue is empty, the remove(), element() methods throw a NoSuchElementException. While the
        * corresponding method poll() and peek() return null. For implementations with a bounded capacity, the add
        * method will throw an illegalStateException when null, while the offer method ignores the new element and
        * returns false to signal that the element was not accepted.
        *
        * Queue ADT      | Interface java.util.Queue
        *                | throws exceptions  | returns special value
        * enqueue(e)     | add(e)             | offer(e)
        * dequeue( )     | remove( )          | poll( )
        * first( )       | element( )         | peek( )
        * size( )        |                size( )
        * isEmpty( )     |               isEmpty( )
        *
        * */

        /******************************13.4.3 Array Based Queue Implementation *********************************/

        /* To implement a Queue ADT by using Array, we need to resolve the problem of the moving head index. As we
        * remove elements from the queue, the index increase till the max size of the array. Then the index needs to
        * restart from 0.
        *
        * To have this behavior, we could use the modulo operator (% in java). As a result, the Queue constructor should
        * have three fileds:
        * - data : an Array of the elements
        * - f : the index of the first element of the queue
        * - size : the current size of the queue
        *
        * See the implementation in ArrayBasedQueue
        *
        * The complexity of all methods are O(1), so it's very efficient.
        * The drawn back of this implementation is that the queue has a fixed size.
        * */

        //BasicQueueExample.exp1();

        /******************************13.4.4 Singly linked list Based Queue Implementation ************************/

        /* Singly linked list ADT is very simular to the queue, so the singly linked list based queue implementation is
        * vert simple.
        *
        * The advantage of this implementation is that the queue does not have fixed size anymore.
        * The complexity of all methods are O(1), so it's very efficient.
         * */

        // BasicQueueExample.exp2();
        /******************************13.4.5 A circular queue  ************************************************/

        /* A circular queue offers a new method rotate(), which can rotate the front element of the queue to the
        * back of the queue. It does nothing if the queue is empty*/

      // BasicQueueExample.exp3();

        /******************************13.4.6 Blocking Queue  ************************************************/

        /*
        * The Java BlockingQueue interface, java.util.concurrent.BlockingQueue, represents a queue which is thread safe
        * to put elements into, and take elements out of from. In other words, multiple threads can be inserting and
        * taking elements concurrently from a Java BlockingQueue, without any concurrency issues arising.
        *
        * The term blocking queue comes from the fact that the Java BlockingQueue is capable of blocking the threads
        * that try to insert or take elements from the queue. For instance, if a thread tries to take an element and
        * there are none left in the queue, the thread can be blocked until there is an element to take.
        *
        * Note, blocking queue also provides non blocking features. Whether or not the calling thread is blocked
        * depends on what methods you call on the BlockingQueue.
        *
        * A BlockingQueue is typically used to have one thread produce objects, which another thread consumes.
        * */

        /** 13.4.6.1 BlockingQueue Methods
         *
         * The Java BlockingQueue interface has 4 different sets of methods for inserting, removing and examining
         * the elements in the queue. Each set of methods behaves differently in case the requested operation cannot
         * be carried out immediately. The 4 different sets of behaviour are shown below:
         * 1. Throws Exception: If the attempted operation is not possible immediately, an exception is thrown. Classic
         *        behaviour just like other normal queue. Three methods(e.g. add(o), remove(o), element()) have this
         *        behaviour.
         * 2. Special Value: If the attempted operation is not possible immediately, a special value is returned
         *       (often true / false). Three methods(e.g. offer(o), pull(), peek()) have this behaviour.
         * 3. Blocks: If the attempted operation is not possible immediately, the method call blocks until it is
         *       possible. Two methods(e.g. put(o), take()) have this behaviour.
         * 4. Blocks with Times Out: If the attempted operation is not possible immediately, the method call blocks
         *        until it is possible, but waits no longer than the given timeout. Returns a special value telling
         *        whether the operation succeeded or not (typically true / false). Two methods(e.g.
         *        offer(o, timeout, timeunit), pull(timeout, timeunit) have this behaviour.
         *
         * Important Note:
         * - BlockingQueue stores the elements internally in FIFO (First In, First Out) order. The head of the queue
         *   is the element which has been in queue the longest time, and the tail of the queue is the element which
         *   has been in the queue the shortest time.
         * - It's normal the examining methods does not block the caller, because if the queue is empty, it means
         *   the element does not exist, then return false. No need to block the caller.
         * - It is not possible to insert null into a BlockingQueue. If you try to insert null, the BlockingQueue will
         *   throw a NullPointerException.
         * - It is also possible to access all the elements inside a BlockingQueue, and not just the elements at the
         *   start and end. For instance, imagine you have queued an object for processing, but your application
         *   decides to cancel it. You can then call e.g. remove(o) to remove a specific object in the queue. However,
         *   this is not done very efficiently, so you should not use these Collection methods unless you really have to.
         * */

        /** 13.4.6.2 ArrayBlockingQueue, LinkedBlockingQueue
         *
         * The ArrayBlockingQueue class implements the BlockingQueue interface. It is a bounded, blocking queue that
         * stores the elements internally in an array. Here, bounded means that it cannot store unlimited amounts of
         * elements. There is a limit on the number of elements it can store at the same time. You set the upper
         * bound at instantiation time, and after that it cannot be changed.
         *
         *
         * The LinkedBlockingQueue keeps the elements internally in a linked structure (linked nodes). This linked
         * structure can optionally have an upper bound if desired. If no upper bound is specified,
         * Integer.MAX_VALUE is used as the upper bound.
         *
         * They are very similar when we use them, the difference is their internal implementation.
         *
         * In BlockingQueueExample.exp1(); we use 2 producer and 1 consumer to access the blocking queue.
         */
       // BlockingQueueExample.exp1();

        /** 13.4.6.4 PriorityQueue
         *
         * The PriorityBlockingQueue is an unbounded concurrent queue. It uses the same ordering rules as the
         * java.util.PriorityQueue class. You cannot insert null into this queue.
         *
         * All elements inserted into the PriorityBlockingQueue must fulfil one of the two options:
         * - Adding elements implement the java.lang.Comparable interface.
         * - Adding elements provide a Comparator
         * The elements thus order themselves according to whatever priority you decide in your Comparable
         * or Comparator implementation. The aim is to implement comparison logic in a way in which the highest
         * priority element is always ordered first. Then, when we remove an element from our queue, it will always
         * be the one with the highest priority.
         * For more details, please visit Lesson13_Section8
         *
         * Important Note:
         * - The PriorityBlockingQueue does not enforce any specific behaviour for elements that have equal
         *   priority (compare() == 0). So if two elements are equal in the queue, their ordering is random.
         * -
         *
         * In BlockingQueueExample.exp2(); we implement a PriorityQueueElement which use field priority to compare.
         *
         */
      //  BlockingQueueExample.exp2();

        /** 13.4.6.4 DelayQueue
         *
         * DelayQueue is a specialized Priority Queue that orders elements based on their delay time. It means that
         * only those elements can be taken from the queue whose time has expired. DelayQueue head contains the
         * element that has expired in the least time. If no delay has expired, then there is no head and the method
         * call such as poll() will return null. The elements must implement the interface java.util.concurrent.Delayed.
         *
         * Here is how the Delayed interface looks:
         * public interface Delayed extends Comparable<Delayed> {
         *     public long getDelay(TimeUnit timeUnit);
         * }
         *
         * The value returned by the getDelay() method should be the delay remaining before this element can be
         * released. If 0 or a negative value is returned, the delay will be considered expired, and the element
         * released at the next take() etc. call on the DelayQueue.
         *
         * If you don't know the TimeUnit enum, check Lesson01_9_5.
         *
         * The Delayed interface also extends the java.lang.Comparable interface, as you can see, which means that
         * Delayed objects can be compared to each other. This is used internally in the DelayQueue to order
         * the elements in the queue, so they are released ordered by their expiration time.
         *
         * Class Hierarchy:
         * java.lang.Object
         *   ↳ java.util.AbstractCollection<E>
         *     ↳ java.util.AbstractQueue<E>
         *       ↳ java.util.concurrent.DelayQueue<E>
         *
         * It has two constructors:
         * - DelayQueue(): This constructor is used to construct an empty DelayQueue.
         * - DelayQueue(Collection<E> c): This constructor is used to construct a DelayQueue with the elements of
         *                the Collection passed as the parameter.
         *
         *
         * In BlockingQueueExample.exp2(); we implement a DelayedQueueElement, and we use it to test a delayedQueue.
         * */

       //  BlockingQueueExample.exp3();

/** SynchronousQueue
 *
 * The SynchronousQueue is a queue that can only contain a single element internally(The submitted element is consumed
 * immediately by the other thread.). A thread inserting an element into the queue is blocked until another thread
 * takes that element from the queue. Likewise, if a thread tries to take an element and no element is currently
 * present, that thread is blocked until a thread insert an element into the queue.
 *
 * This implementation allows us to exchange information between threads in a thread-safe manner. Although the
 * SynchronousQueue has an interface of a queue, we should think about it as an exchange point for a single element
 * between two threads, in which one thread is handing off an element, and another thread is taking that element.
 *
 * The SynchronousQueue only has two supported operations: take() and put(), and both of them are blocking.
 *
 * In BlockingQueueExample.exp4(); we have two producer and one consumer. After one producer put message, the consumer
 * get the message, the other producer waits its turn. Without the SynchronousQueue, we will need lock or synchronizer
 * to implement this. It saves lots of code and easy to understand.
 * */
BlockingQueueExample.exp4();
    }

}
