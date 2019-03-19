package org.pengfei.Lesson13_Common_Data_Structure.L13_S5_Double_Ended_Queue;

public class L13_S5_Double_Ended_Queue {
    public static void main(String[] args){
        L13_S5_Double_Ended_Queue dequeExamples=new L13_S5_Double_Ended_Queue();
        /************************************************************************************************
         * ********************************** 13.5 Double-Ended queue  *******************************
         * **********************************************************************************************/

          /*********************** 13.5.1  Introduction  **************************/
        /* A queue like data structure that supports insertion and deletion at both the front and the back of the queue,
         * is called a double-ended queue, or deque (which is usually pronounced "deck" to avoid confusion with the
         * dequeue method of the regular queue ADT).
         * */

        /****************************** 13.5.2  The Deque Abstract Data type  *************************/

        /* To support the insertion and deletion at both the front and the back, the deque ADT must support the
        * following update methods:
        * - addFirst(e): Insert a new element e at the front of the deque.
        * - addLast(e): Insert a new element e at the back of the deque.
        * - removeFirst(): Remove and return the first element of the deque (or null if the deque is empty)
        * - removeLast(): Remove and return the last element of the deque (or null if the deque is empty)
        * and following accessors:
        * - first(): Returns the first element, without removing it (or null if the deque is empty)
        * - last(): Returns the last element, without removing it (or null if the deque is empty)
        * - size(): Returns the number of element in the deque
        * - isEmpty(): Returns a boolean indicating whether the deque is Empty
        *
        * */

        /******************* 13.5.3  Implementing the Deque with Circular Array  *********/

        /* The complexity of all the method is o(1) in this implementation,
        * The disadvantage is the fix size of the deque.*/
        dequeExamples.CircularArrayBasedDequeExample();

        /******************* 13.5.4  Implementing the Deque with DoublyLinkedList  *********/

        /* The implementation is quite simple, and the complexity of all method are also o(1).
        * And we don't have the fix size anymore*/


        /******************* 13.5.5  Deques in the Java Collection Framework  *********/

        /* The Java Collections Framework includes its own definition of a deque, as the java.util.Deque interface,
        * as well as several implementations of the interface including one based on use of a circular array
        * (java.util.ArrayDeque) and one based on use of a doubly linked list (java.util.LinkedList). So, if we need
        * to use a deque and would rather not implement one from scratch, we can simply use one of those
        * built-in classes.
        *
        * As is the case with the java.util.Queue class, the java.util.Deque provides duplicative methods that use
        * different techniques to signal exceptional cases. A summary of those methods is as below
        *
        * Deque ADT |        Interface java.util.Deque
        *                   throws exceptions     |   returns special value
        * first( )  |          getFirst( )        |     peekFirst( )
        * last( )   |           getLast( )        |     peekLast( )
        * addFirst(e) |         addFirst(e)       |    offerFirst(e)
        * addLast(e) |          addLast(e)        |     offerLast(e)
        * removeFirst() |      removeFirst()      |     pollFirst( )
        * removeLast() |       removeLast()       |     pollLast( )
        * size()     |                          size( )
        * isEmpty()  |                       isEmpty( )
        *
        *
        *
        * .*/
    }

    public void CircularArrayBasedDequeExample(){
        CircularArrayBasedDeque<Integer> deque=new CircularArrayBasedDeque<Integer>(10);
        deque.addLast(5); // 5
        deque.addFirst(3); // 3,5
        deque.addFirst(7); //7,3,5
        deque.showDeque();
        deque.removeLast(); // 7,3
        deque.showDeque();
        System.out.println(deque.size()); //2
        deque.removeLast(); // 7
        deque.removeFirst(); // ()
        deque.showDeque();
    }

}
