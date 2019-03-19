package org.pengfei.Lesson13_Common_Data_Structure.L13_S2_Linked_lists;

public class L13_S2_Linked_list {


    /************************************************************************************************
    *******************************************13.2 Linked List *************************************
     ************************************************************************************************/
    public static void main(String[] args){


    /************************************13.2.1 Singly Linked List ***********************************/
    /* Singly linked list: Each node stores a reference to an object that is an element of the sequence, as well as
    * a reference to the "next" node of the list
    *
    * A linked list's representation relies on the collaboration of many objects. The list must keep a reference to
    * the first node of the list (aka. Head), The last node of the list (aka. tail). The tail has the null value on the
     * next reference (tail does not have next)
     *
     * A linked list does not have a predetermined fixed size. When using a single linked list, we can easily insert an
     * element at the head of the list in 3 steps:
     * 1. Get the current head of the list. | head=list.getHead()
     * 2. Create a node with the next reference point to the current head.| newNode=Node(e); newNode.next=head
     * 3. Change the current head to the newly created node. | list.setHead(newNode)
     *
     * Insert an element at the tail of the list 3 steps:
     * 1. Get the current tail. | tail=list.getTail()
     * 2. Create a node, set the tail next reference point to the newly created node. Set new node next reference
     *    point to null| newNode=Node(e); newNode.next=null; tail.next=newNode
     * 3. Change the current tail to the newly created node. | list.setTail(newNode)
     *
     * Removing an element from the head of a Singly linked list:
     * 1. Test if the list's head is null.    |
     * 2. If null, raise null list exception.
     * 3. if not null, set current head to head next reference.
     *
     * if head=null then "List is empty"
     * else
     *   head= head.next
     *   size=size-1
     *
     * We can't remove the tail in a singly linked list, because we can't access the node before the last node easily.
     * We need to start from the head of the list and search all the way through the list with the help of the list size.
     * To support such an operation efficiently, we need to make a doubly linked list
     *
     * A singly linked list implementation should support the following methods:
     * - size() : Return the number of the elements in the list
     * - isEmpty() : Return true if the list is empty, and false otherwise.
     * - first() : returns (but does not remove) the first element in the list
     * - last() : returns (but does not remove) the last element in the list
     * - addFirst(e) : adds a new element to the front of the list
     * - addLast(e) : adds a new element to the end of the list
     * - removeFirst(): Removes and returns the first element of the list
     *
     * As a list could be a list of any type, we need to use Java's "generics framework"
     * */

    MySinglyLinkedList<String> mySinglyLinkedList=new MySinglyLinkedList<String>();
    mySinglyLinkedList.addFirst("Hello");
    System.out.println("List size: "+mySinglyLinkedList.size());
    System.out.println("List head: "+mySinglyLinkedList.first());
    System.out.println("List tail: "+mySinglyLinkedList.last());
    mySinglyLinkedList.addFirst("First");
    mySinglyLinkedList.addLast("World");
    mySinglyLinkedList.addLast("Last");
    System.out.println("List size: "+mySinglyLinkedList.size());
    System.out.println("List head: "+mySinglyLinkedList.first());
    System.out.println("List tail: "+mySinglyLinkedList.last());
    mySinglyLinkedList.removeFirst();

    /************************************13.2.2 Circularly Linked List ***********************************/

    /* Circularly Linked list is a special singly linked list. All nodes has well-defined next node, but no fixed
    * beginning or end.
    *
    * Round-Robin Scheduling
    * Most operating systems allow processes to effectively share use of the CPUs, using some form of an algorithm
    * know as round-robin scheduling. A process is given a short turn to execute, known as a "time slice", but it
    * interrupted when the slice ends,even if its job is not yet complete. Each active process is given its own
    * time slice, taking turns in a cyclic order. New processes can be added to the system, and processes
    * that complete their work can be removed.
    *
    * A round-robin scheduler could be implemented with a traditional linked list, by repeatedly performing the
    * following steps on linked list L
    *
    * 1. process p= L.removeFirst()
    * 2. Give a time slice to proces p
    * 3. L.addLast(p)
    *
    * This is inefficient to repeatedly remove and the same node and change the list size for each add and remove
    * operation
    *
    * We could use a Circularly linked list to do the round-robin scheduler. Basicly, Circularly linked list is a singly
    * linked list which tail's next reference point to head instead of null. So the list became a circle.*/

    /************************************13.2.3 doubly Linked List ***********************************/

    /* Doubly linked list is very close to the singly linked list. Just Each Node has one more reference which is pointed
     * to the previous element of the current node. For the head, the previous reference is null
      *
      * The doubly linked list provide all method of the sinly linked list and the removeLast method
      *
      * Remove last element from the list become much easier.
      * 1. If the list is empty, return null
      * 2. else, Get the tail (result=tail), use tail.prev to get the element before tail
      * 3. Set tail=tail.prev and tail.next=null
      * 4. if the list is empty after remove, set head=null
      *
      * Header and Trailer Sentinels (vs head and tail in the singly linked list)
      *
      * In order to avoid some special cases when operating near the boundaries of a doubly linked list, it helps
      * to add special nodes at both ends of the list: a header node at the beginning of the list, and a trailer
      * node at the end of the list. These “dummy” nodes are known as sentinels (or guards), and they do not store
      * elements of the primary sequence.
      *
      * Inserting and Deleting with a Doubly Linked List
      * Every insertion into our doubly linked list representation will take place between a pair of existing nodes
      * when a new element is inserted at the front of the sequence, we will simply add the new node between the
      * header and the node that is currently after the header.
      *
      *
      * */

        /************************************13.2.4 Equivalence testing ***********************************/

        /* In many cases, we want to consider two variables as "equivalence" even if they do not actually refer to
        * the same instance of the class. For example, we consider two String instances to be equivalent if they
        * represent the identical sequence of characters.
        *
        * In java, all object types support a method named "equals". The "equals" method must satisfy the following
         * properties:
         * - Treatment of null : For any nonnull reference variable x, the call x.equals(null) should return false (
         *                       nothing equals null except null.)
         *
         * - Reflexivity: For any nonnull reference variable x, the call x.equals(x) should return true (an object should
         *                equal itself)
         *
         * - Symmetry: For any nonnull reference variable x and y, the calls x.equals(y) and y.equals(x) should return
         *             the same value.
         *
         * - Transitivity: For any nonnull reference variable x,y and z, if both x.equals(y) and y.equals(z) return true
         *                 then x.equals(z) must return true as well.
         */

        /************************************13.2.4.1  Equivalence testing for arrays ***********************************/


        /*  For arrays, there are three different equivalent treatment:
         *  - a==b : Test if a and b refer to the same underlying array instance.
         *  - a.equals(b): For arrays, this is identical to a==b, Because arrays are not a true class type and do not
         *                 override the Object.equals method
         *  - Arrays.equals(a,b): This provides a more intuitive notion of equivalence, return true if the arrays have
         *                        the same length and all pairs of corresponding elements are "equal" to each other.
         *                        If the array elements are primitives, then it uses the standard == to compare values.
         *                        If the elements are reference types. then it make pairwise comparisons a[k].equals(b[k])
         *  - Arrays.deepEquals(a,b) : Identical to Arrays.equals(a,b) except when the elements of a and b are
         *                             themselves arrays, in which case it calls Arrays.deepEquals(a[k],b[k]) for
         *                             corresponding entries, rather than a[k].equals(b[k]).
         *
         *                       */

        /************************************13.2.4.2  Equivalence testing for linked lists ***********************************/


    }
}