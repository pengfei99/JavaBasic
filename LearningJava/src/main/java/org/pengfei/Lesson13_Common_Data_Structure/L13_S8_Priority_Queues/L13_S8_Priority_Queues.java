package org.pengfei.Lesson13_Common_Data_Structure.L13_S8_Priority_Queues;

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

        L13_S8_Priority_Queues.UnsortedPriorityQueueExample();
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
}
