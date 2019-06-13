package org.pengfei.Lesson13_Common_Data_Structure.L13_S8_Priority_Queues;

import java.util.AbstractMap.SimpleEntry;
import java.util.Comparator;
import java.util.PriorityQueue;

public class L13_S8_Priority_Queues {
    public static void main(String[] args){
        /************************************************************************************************
         * ********************************** 13.8 Priority Queues  ************************************
         * **********************************************************************************************/

        /***************************** 13.8.1.1 The Priority queue introduction ***********************************/

        /* In lesson13,section 4. we have seen the ADT of queue, which is a collection of objects that are
        * added and removed according to the first-in, first-out (FIFO). In practice, there are many applications
        * in which a queue-like structure is used to manage objects that must be processed in some way, but for which
        * the first-in, first-out policy does not suffice.
        *
        * For example, an air-traffic control center that has to decide which flight to clear for landing from among
        * many approaching the airport. It's unlikely that the landing decision are based purely on a FIFO policy.
        * Because we may have incidents, which we need to change order. And sometime, the "first come, first serve"
        * policy might seem reasonable.
        *
        * In this lesson, we introduce a new abstract data type known as a priority queue. This is a collection of
        * prioritized elements that allows arbitrary element insertion, and allows the removal of the element that
        * has first priority. When an element is added to a priority queue, the user designates its priority by
        * providing an associated key. The element with the minimal key will be the next to be removed from the queue
        * (thus, an element with key 1 will be given priority over an element with key 2). Although it is quite common
        * for priorities to be expressed numerically, any Java object may be used as a key, as long as there exists
        * means to compare any two instances a and b, in a way that defines a natural order of the keys. With such
        * generality, applications may develop their own notion of priority for each element. For example, different
        * financial analysts may assign different ratings (i.e., priorities) to a particular asset, such as a share
        * of stock.
        * */

        /***************************** 13.8.1.2 The Priority queue ADT ***********************************/

        /* We model an element and its priority as a key-value composite known as an entry(We will give the definition
        * of entry in section 13.8.2 ). The key is the priority, the value is the target element.
        *
        * The following methods are the primary method of a priority queue:
        * - insert(k, v): Creates an entry with key k and value v in the priority queue.
        * - min( ): Returns (but does not remove) a priority queue entry (k,v) having minimal key; returns null if
        *           the priority queue is empty.
        * - removeMin( ): Removes and returns an entry (k,v) having minimal key from the priority queue; returns null
        *                 if the priority queue is empty.
        * - size( ): Returns the number of entries in the priority queue.
        * - isEmpty( ): Returns a boolean indicating whether the priority queue is empty.
        *
        * A priority queue may have multiple entries with equivalent keys, in which case methods min and removeMin may
        * report an arbitrary choice among those entry having minimal key. Values may be any type of object. In our
        * initial model for a priority queue, we assume that an element’s key remains fixed once it has been added to
        * a priority queue. In Section 13.8.5, we consider an extension that allows a user to update an element’s key
        * within the priority queue.
        *
        * */

        /************************************************************************************************
         * **************************** 13.8.2 Implementing a priority Queues  *****************************
         * **********************************************************************************************/

        /* In this section, we discuss several technical issues involving the implementation of the priority queue
        * ADT in Java, and we define an abstract base class that provides functionality that is shared by all priority
        * queue implementations in this chapter. We then provide two implementations of priority queue by using
        * a positional list L for storage. They differ in whether or not entries are maintained in sorted order
        * according to their keys.
        * */


        /****************************** 13.8.2.1 The entry composite  **************************/

        /* The first challenge in implementing a priority queue is that we must keep track of both an element and its
        * key, even as entries are relocated within a data structure. To do that, we need to introduce the composition
         * design pattern, defining an Item class that paired each element with its associated count in our primary
         * data structure. For priority queues, we use composition to pair a key k and a value v as a single object.
         * To formalize this, we define the public interface, MyEntry
         *
         * Based on the MyEntry interface, we can write the MyPriorityQueue interface which represent the ADT of
         * priority queue.
         *
         * */

        /****************************** 13.8.2.2 Comparing Keys with Total Orders **************************/

        /* In defining the priority queue ADT, we can allow any type of object to serve as a key, but we must be
        * able to compare keys to each other in a meaningful way. More so, the results of the comparisons must not
        * be contradictory. For a comparison rule, which we denote by ≤, to be self-consistent, it must define a
        * total order relation, which is to say that it satisfies the following properties for any keys k1, k2, and k3:
        * - Comparability property: k1 ≤ k2 or k2 ≤ k1.
        * - Antisymmetric property: if k1 ≤ k2 and k2 ≤ k1, then k1 = k2.
        * - Transitive property: if k1 ≤ k2 and k2 ≤ k3, then k1 ≤ k3.
        *
        * The comparability property states that comparison rule is defined for every pair of keys. Note that this
        * property implies the following one:
        * - Reflexive property: k ≤ k.
        *
        * A comparison rule, ≤, that defines a total order relation will never lead to a contradiction. Such a rule
        * defines a linear ordering among a set of keys; hence, if a (finite) set of elements has a total order
        * defined for it, then the notion of a minimal key, kmin, is well defined, as a key in which kmin ≤ k,
        * for any other key k in our set.
        *
        * The Comparable interface
        * Java provides two means for defining comparisons between object types. The first of these is that a class
        * may define what is known as the natural ordering of its instances by formally implementing the
        * java.lang.Comparable interface, which includes a single method, compareTo. The syntax a.compareTo(b) must
        * return an integer i with the following meaning:
        * - i < 0 designates that a < b.
        * - i = 0 designates that a = b.
        * - i > 0 designates that a > b.
        * For example, the compareTo method of the String class defines the natural ordering of strings to be
        * lexicographic, which is a case-sensitive extension of the alphabetic ordering to Unicode.
        *
        * The Comparator Interface
        * In some applications, we may want to compare objects according to some notion other than their natural
        * ordering. For example, we might be interested in which of two strings is the shortest, or in defining
        * our own complex rules for judging which of two stocks is more promising. To support generality, Java
        * defines the java.util.Comparator interface. A comparator is an object that is external to the class of
        * the keys it compares. It provides amethod with the signature compare(a, b) that returns an integer with
        * similar meaning to the compareTo method described above.
        *
        * As a concrete example, the following code defines a comparator that evaluates strings based on their length
        * (rather than their natural lexicographic order).
        * public class StringLengthComparator implements Comparator<String> {
        // Compares two strings according to their lengths.
        public int compare(String a, String b) {
            if (a.length() < b.length()) return -1;
            else if (a.length() == b.length()) return 0;
            else return 1;
        }
    }
        * */

        /* Comparators and the priority queue ADT
        * For a general and reusable form of a priority queue, we allow a user to choose any key type and to send an
        * appropriate comparator instance as a parameter to the priority queue constructor. The priority queue will
        * use that comparator anytime it needs to compare two keys to each other. For convenience, we also allow a
        * default priority queue to instead rely on the natural ordering for the given keys (assuming those keys
        * come from a comparable class). In that case, we build our own instance of a DefaultComparator class, shown
        * in the following Code:
        * public class DefaultComparator<E> implements Comparator<E> {
        public int compare(E a, E b) throws ClassCastException {
            return ((Comparable<E>) a).compareTo(b);
        }
    }
        * */

        /****************************** 13.8.2.3 The AbstractPriorityQueue Base class **************************/
        /* To manage technical issues common to all our priority queue implementations, we define an abstract base
        * class named MyAbstractPriorityQueue. In the MyAbstractPriorityQueue class, we provide a nested PQEntry
        * class that composes a key and a value into a single object, and support for managing a comparator. For
        * convenience, we also provide an implementation of isEmpty based on a presumed size method.
        *
        * */

        /********************** 13.8.2.4 Implementing a Priority queue with an Unsorted list **********************/
        /*
        * In our first concrete implementation of a priority queue, we store entries withing an unsorted linked list.
        *
        * */

       // L13_S8_Priority_Queues.UnsortedPriorityQueueExample();

        /********************** 13.8.2.5 Implementing a Priority queue with a sorted list **********************/

        /* The implementation with a sorted list is easier compare to the unsorted one. The implementation of min and
         * removeMin does not need the help of findMin anymore, because the first element in the list has the minimal
          * key. All the difficulty is in the insert method, when we insert a new Entry, we need to make sure the list
          * is still sorted.
          *
          * The implementation is given in class MySortedPriorityQueue. */

         L13_S8_Priority_Queues.SortedPriorityQueueExample();

         /*
         * Comparing the two implementation
         * Method | Unsorted List | Sorted List
         *  size  |     O(1)      |    O(1)
         * isEmpty|     O(1)      |    O(1)
         * insert |     O(1)      |    O(n)
         * min    |     O(n)      |    O(1)
         * removeMin |  O(n)      |    O(1)
         *
         * With the above comparision, we could conclude an unsorted list implementation supports fast insertion but
         * Slow queries and deletion. A sorted list implementation supports fast queries and deletion, but slow insertion.
         *
         * */

        /********************** 13.8.2.6 Implementing a Priority queue with a binary heap **********************/

        /* A binary heap data structure allows us to perform both insertions and removals in logarithmic time, which is
        * a significant improvement over the list-based implementations. The fundamental way the heap achieves this
        * improvement is to use the structure of a binary tree to find a compromise between elements being entirely
        * unsorted and perfectly sorted.
        *
        * Note, the heap data structure defined here has nothing to do with the memory heap used in the runtime
        * environment supporting a programming language like Java.
        */

        /* The Heap Data Structure
        *
        * A heap is a binary tree T that stores entries at its positions, and that satisfies two additional properties:
        * - A relational property defined in terms of the way keys are stored in T (Heap-Order Property).
        * - A structural property defined in terms of the shape of T itself (Complete Binary Tree Property).
        *
        * Heap-Order Property:
        * In a heap T, for every position p other than the root, the key stored at p is greater than or equal to the key
        * stored at p's parent.
        * As a consequence of the heap order property, the keys encountered on a path from root to a leaf of T are in
        * non-decreasing order. Also, a minimal key is always stored at the root of T.
        *
        * Complete Binary Tree Property:
        * A heap T with height h is a complete binary tree if levels 0,1,2,...,h-1 of T have the maximal number of nodes
        * possible(namely, level i has 2^i nodes, for 0<=i<=h-1) and the remaining nodes at level h reside in the
        * leftmost possible positions at that level.
        *
        * For example, the below tree is complete
        *                                              (4,C)
        *                                         /            \
        *                                      (5,A)          (6,Z)
        *                                     /    \          /   \
        *                               (15,K)  (9,F)       (7,Q)  (20,B)
        *                             /   \     /   \       /    \
        *                        (16,X)(25,J)(14,E)(13,H)(11,S)(12,W)
        *
        * In formalizing what we mean by the leftmost possible positions, we refer to the discussion of level numbering
        * from Section 13.7.3.3 (L13_S7_Trees), in the context of an array-based representation of a binary tree.
        * A complete binary tree with n elements is one that has positions with level numbering 0 through n−1.
        * For example, in an array-based representation of the above tree, its 13 entries would be stored consecutively
        * from A[0] to A[12].
        *
        * The Height of a Heap
        * Let h denote the height of T. Insisting that T be complete also has an important consequence, as shown in the
        * following Proposition:
        *
        * A heap T storing n entries has height h = ⌊log n⌋.
        *
        * Justification: From the fact that T is complete, we know that the number of nodes in levels 0 through h−1 of
        * T is precisely 1+2+4+···+2h−1 = 2h−1, and that the number of nodes in level h is at least 1 and at most 2h.
        * Therefore n ≥ 2h−1+1 = 2h and n ≤ 2h−1+2h = 2h+1−1. By taking the logarithm of both sides of inequality n ≥ 2h,
        * we see that height h≤log n. By rearranging terms and taking the logarithm of both sides of inequality
        * n ≤ 2h+1 −1, we see that h ≥ log(n+1) −1. Since h is an integer, these two inequalities imply that h = ⌊log n⌋.
        *
        * */

        /** 13.8.2.6.1 Implementing the main method **/

        /*
        * - size :
        * - isEmpty :
        * - min : The root entry of the Heap is the min, guaranteed by the heap-order
        *
        * - insert : After insert, the heap must maintain the complete binary tree property and the heap-order property.
        *            To do that, the new node should be placed at a position P just beyond the rightmost node at the
        *            bottom level, or as the leftmost position of a new level, if the bottom level is already full.
        *            Suppose the parent of P is Q, Kp >= Kq. If Kp < Kq, we need to restore the heap-order.
        *
        *            The upward movement of the newly inserted entry by means of swaps is conventionally called up-heap
        *            bubbling. A swap either resolves the violation of the heap-order or propagates it one level up in
        *            the heap. The number of of swaps performed in the execution of method insert is equal to the height
        *            of T (worst case), which is log(n).
        *
        *            The up-heap bubbling operation compares target node with his parent, if the key of the target is
        *            smaller than its parent, then swap the target with its parent, else stop the operation
        *
        * - removeMin : We know the root entry has the min key, but after removing it, we need to reshape the heap to
        *               respect the complete binary tree property. To do that, we need to replace the root with the last
        *               position of the Heap (i.e. the rightmost position at the bottommost level of the Heap).
        *
        *               Then, we need to use a down-heap bubbling to preserve the heap-order property.
        *
        *               The down-heap bubbling has three case:
        *               1. If the target entry(root entry initially) has no child, then do nothing
        *               2. If the target entry P has no right child C, compare target entry key with the child key,
        *                  if Pk > Ck, then we need to swap the Pk and Ck.
        *               3. If the target entry P has two children, let C be the child with minimal key. Then, compare
        *                  target entry key with the child key, if Pk > Ck, then we need to swap the Pk and Ck.
        *
        *
        * */

        /** 13.8.2.6.2 Representing a Heap(Complete binary tree) with Array **/

        /* The array-based representation of a binary tree is especially suitable for a complete binary tree.
         * We recall that in this implementation, the elements of the tree are stored in an array-based list A such
         * that the element at position p is stored in A with index equal to the level number f (p) of p,
         * defined as follows:
         * • If p is the root, then f (p) = 0.
         * • If p is the left child of position q, then f(p) = 2 f(q)+1.
         * • If p is the right child of position q, then f(p) = 2 f(q)+2.
         *
         * For a tree with of size n, the elements have contiguous indices in the range [0,n−1] and the last
         * position of is always at index n−1.
         *
         * The array based heap representation avoids some complexities of a linked tree structure. Specifically,
         * methods like insert and removeMin depend on locating the last position of a heap. In an array based heap,
         * the last position in a heap is simply at index n-1. In a linked tree, it requires more effort to locate
         * the last position.
         *
         * If the size of a priority queue is not known in advance, use of an array-based representation does introduce
         * the need to dynamically resize the array on occasion, as done with a Java ArrayList. The space usage of such
         * an array-based representation of a complete binary tree with n nodes is O(n), and the time bounds of methods
         * for adding or removing elements become amortized.
         * */

        // L13_S8_Priority_Queues.ArrayBasedHeapPQExample();

        /* The complexity of each method :
        * - size, isEmpty : O(1)
        * - min : O(1)
        * - insert : O(log n)∗ amortized, if using dynamic array
        * - removeMin : O(log n)∗ amortized, if using dynamic array
        *
        * The space requirement is O(n)
        *
        * We conclude that the heap data structure is a very efficient realization of the priority queue ADT,
        * independent of whether the heap is implemented with a linked structure or an array. The heap-based
        * implementation achieves fast running times for both insertion and removal, unlike the implementations
        * that were based on using an unsorted or sorted list.
        * */

        /** 13.8.2.6.3 Using the java.util.PriorityQueue Class **/

        /* There is no priority queue interface built into Java, but Java does include a class, java.util.PriorityQueue,
        * which implements the java.util.Queue interface. Instead of adding and removing elements according to the
        * standard FIFO policy used by most queues, the java.util.PriorityQueue class processes its entries according
        * to a priority. The “front” of the queue will always be a minimal element, with priorities based either on the
        * natural ordering of the elements, or in accordance with a comparator object sent as a parameter when
        * constructing the priority queue.
        *
        * The most notable difference between the java.util.PriorityQueue class and our own priority queue ADT is
        * the model for managing keys and values. Whereas our public interface distinguishes between keys and values,
        * the java.util.PriorityQueue class relies on a single element type. That element is effectively treated
        * as a key.
        *
        * If a user wishes to insert distinct keys and values, the burden is on the user to define and insert
        * appropriate composite objects, and to ensure that those objects can be compared based on their keys.
        * (The Java Collections Framework does include its own entry interface, java.util.Map.Entry, and a concrete
        * implementation in the java.util.AbstractMap.SimpleEntry class
        *
        * Our Priority Queue ADT | java.util.PriorityQueue Class
        *      insert(k,v)       |  add(new SimpleEntry(k,v))
        *         min( )         |  peek( )
        *         removeMin( )   |  remove( )
        *                size( ) | size( )
        *             isEmpty( ) | isEmpty( )
        * */

        L13_S8_Priority_Queues.NativeJavaHeapPQExample();

    }

    public static void UnsortedPriorityQueueExample(){
        MyUnsortedPriorityQueue<Integer,String> myQueue=new MyUnsortedPriorityQueue<>();
        myQueue.insert(5,"Spark");
        myQueue.insert(4,"hadoop");
        myQueue.insert(2,"Hive");
        myQueue.insert(1,"Kafka");

        MyEntry<Integer, String> firstEntry = myQueue.min();
        System.out.println("The key is:"+firstEntry.getKey().toString()+" the value is: "+firstEntry.getValue().toString());
    }

    public static void SortedPriorityQueueExample(){
        MySortedPriorityQueue<Integer,String> myQueue=new MySortedPriorityQueue<>();
        myQueue.insert(5,"Spark");
        myQueue.insert(4,"hadoop");
        myQueue.insert(2,"Hive");
        myQueue.insert(1,"Kafka");

        MyEntry<Integer, String> firstEntry = myQueue.min();
        System.out.println("The key is:"+firstEntry.getKey()+" the value is: "+firstEntry.getValue());
    }

    public static void ArrayBasedHeapPQExample(){
        MyArrayBasedHeapPriorityQueue<Integer,String> myPQ=new MyArrayBasedHeapPriorityQueue<Integer, String>();
        myPQ.insert(5,"Spark");
        myPQ.insert(4,"hadoop");
        myPQ.insert(2,"Hive");


        MyEntry<Integer, String> minEntry = myPQ.min();

        System.out.println("Min entry has key: "+minEntry.getKey()+ " value: "+minEntry.getValue());

        myPQ.insert(1,"Kafka");

        minEntry = myPQ.min();

        System.out.println("Min entry has key: "+minEntry.getKey()+ " value: "+minEntry.getValue());

    }
    public static void NativeJavaHeapPQExample(){
        SimpleEntry<Integer, String> entry1=new SimpleEntry<Integer, String>(5,"Spark");
        SimpleEntry entry2=new SimpleEntry<Integer, String>(4,"Hadoop");
        SimpleEntry entry3=new SimpleEntry<Integer, String>(2,"Hive");
        SimpleEntry entry4=new SimpleEntry<Integer, String>(1,"Kafka");

        /* build a nested comparator for the SimpleEntry*/
        Comparator<SimpleEntry<Integer,String>> sEntryComparator = new Comparator<SimpleEntry<Integer,String>>() {
            @Override
            public int compare(SimpleEntry<Integer,String> s1, SimpleEntry<Integer,String> s2) {
                return s1.getKey() - s2.getKey();
            }
        };

        PriorityQueue<SimpleEntry<Integer,String>> myPQ=new PriorityQueue<SimpleEntry<Integer,String>>(sEntryComparator);
        myPQ.add(entry1);
        myPQ.add(entry2);
        myPQ.add(entry3);
        myPQ.add(entry4);

        SimpleEntry minEntry= (SimpleEntry) myPQ.peek();
        System.out.println("Min entry has key: "+minEntry.getKey()+ " value: "+minEntry.getValue());

        myPQ.remove();
        minEntry= (SimpleEntry) myPQ.peek();
        System.out.println("Min entry has key: "+minEntry.getKey()+ " value: "+minEntry.getValue());

    }
}
