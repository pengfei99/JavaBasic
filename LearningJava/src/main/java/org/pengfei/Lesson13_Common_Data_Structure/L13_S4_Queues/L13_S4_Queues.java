package org.pengfei.Lesson13_Common_Data_Structure.L13_S4_Queues;

public class L13_S4_Queues {

    public static void main(String[] args){
        L13_S4_Queues queueExample=new L13_S4_Queues();
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
        // queueExample.ArrayBasedQueueExample();

        /******************************13.4.4 Singly linked list Based Queue Implementation ************************/

        /* Singly linked list ADT is very simular to the queue, so the singly linked list based queue implementation is
        * vert simple.
        *
        * The advantage of this implementation is that the queue does not have fixed size anymore.
        * The complexity of all methods are O(1), so it's very efficient.
         * */

        // queueExample.ListBasedQueueExample();
        /******************************13.4.5 A circular queue  ************************************************/

        /* A circular queue offers a new method rotate(), which can rotate the front element of the queue to the
        * back of the queue. It does nothing if the queue is empty*/

        // queueExample.CircularQueueExample();


    }

    public void ArrayBasedQueueExample(){
        ArrayBasedQueue<Integer> myQueue=new ArrayBasedQueue();
        myQueue.enqueue(3);
        myQueue.enqueue(5);
        int maxSize=myQueue.size();
        System.out.println("queue size is :"+maxSize);

        Integer firstElement=myQueue.dequeue();
        Integer secondElement=myQueue.dequeue();

        System.out.println("first element is :"+firstElement);
        System.out.println("Second element is :"+secondElement);
    }

    public void ListBasedQueueExample(){
        SinglyLinkedListBasedQueue<Integer> myQueue= new SinglyLinkedListBasedQueue<>();
        myQueue.enqueue(3);
        myQueue.enqueue(5);
        int maxSize=myQueue.size();
        System.out.println("queue size is :"+maxSize);

        Integer firstElement=myQueue.dequeue();
        Integer secondElement=myQueue.dequeue();

        System.out.println("first element is :"+firstElement);
        System.out.println("Second element is :"+secondElement);
    }

    public void CircularQueueExample(){
        ListBasedCircularQueue<Integer> myQueue= new ListBasedCircularQueue<>();
        for(int i=0;i<5;i++){
            myQueue.enqueue((Integer) i);
        }

        Integer beforRotate = myQueue.first();
        System.out.println("first element before rotate is :"+beforRotate);

        myQueue.rotate();
        Integer afterRotate=myQueue.first();
        System.out.println("first element after rotate is :"+afterRotate);

    }

}
