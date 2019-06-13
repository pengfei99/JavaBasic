package org.pengfei.Lesson13_Common_Data_Structure.L13_S9_Maps_HashTables_SkipLists;

public class L13_S9_HashTables {

    public static void main(String[] args) {


        /************************************************************************************************
         * **************************************** 13.9.2 Hash Tables  ***********************************
         * **********************************************************************************************/

        /* In this section, we introduce one of the most efficient data structures for implementing a map. This
        * structure is known as hash table
        *
        * Intuitively, a map M supports the abstraction of using keys as "addresses" that help locate an entry.
        * For example, if we have n entries which have keys that are in range from 0 to N-1, these keys are also
        * used as the index of the table. With the key, you can access the entry with O(1). This kind of table are
        * called lookup table.
        *
        * There are two challenges in extending this framework to the more general setting of a map. First, we may
        * not wish to devote an array of length N if it is the case that N>n. Second, we do not in general require
        * that a map's keys be integers.
        *
        * The hash tables use a hash function to map general keys to corresponding indices in a table. Ideally, keys
        * will be well distributed in the range from 0 to N-1 by a hash function, but in practice there may be two
        * or more distinct keys that get mapped to the same index. As a result, we will conceptualize our tables as
        * a bucket array, each slot in the array is a bucket which can store and manage a collection of entries that
        * are sent to a specific index by the hash function. To save space, an empty bucket may be replaced by a null
        * reference.
        * */

        /**************************************** 13.9.2.1 Hash Functions ************************************/

        /* The goal of a hash function is to map each key k to an integer in the range of [0,N-1], where N is the
        * capacity of the bucket array for a hash table. But as the N is limited, we will always have collision which
        * means two key has the same hash. We say that a hash function is "good" if it maps the keys in our map so
        * as to sufficiently minimize collisions.
        *
        * It is common to view the evaluation of a hash function, h(k), as consisting of two portions:
        * - hash code: that maps a key k to an integer
        * - compression function: that maps the hash code to an integer within a range of indices, [0,N −1]
        *
        * The advantage of separating the hash function into two such components is that the hash code portion of
        * that computation is independent of a specific hash table size. This allows the development of a general
        * hash code for each object that can be used for a hash table of any size; only the compression function
        * depends upon the table size.
        * */

        /************************************** 13.9.2.2 Hash Codes in Java **********************************/
        /*
        * The notion of hash codes are an integral part of the Java language. The Object class, which serves as
        * an ancestor of all object types, includes a default hashCode( ) method that returns a 32-bit integer of
        * type int, which serves as an object’s hash code. The default version of hashCode( ) provided by the Object
        * class is often just an integer representation derived from the object’s memory address.
        *
        * However, we must be careful if relying on the default version of hashCode( ) when authoring a class. For
        * hashing schemes to be reliable, it is imperative that any two objects that are viewed as “equal” to each
        * other have the same hash code. This is important because if an entry is inserted into a map, and a later
        * search is performed on a key that is considered equivalent to that entry’s key, the map must recognize
        * this as a match
        *
        * As an example, Java’s String class defines the equals method so that two instances are equivalent if they
        * have precisely the same sequence of characters. That class also overrides the hashCode method to provide
        * consistent behavior. In fact, the implementation of hash codes for the String class is excellent. If we
        * repeat the experiment from the previous page using Java’s implementation of hash codes, there are only 12
        * collisions among more than 230,000 words.
        * */

        /*********************************** 13.9.2.3 Java Hash Table Implementation ***************************/
        /* In this section, we develop two implementation of a hash table, one using separate chaining and the other
        * using open addressing with linear probing. While these approaches to collision resolution are quite different,
        * there are many higher-level commonalities to the two hashing algorithms.
        *
        * To avoid duplicate code, we extend the MyAbstractMap to define a new MyAbstractHashMap class, which provides
        * much of the functionality common to our two hash table.
        *
        * This abstract class does not provide any concrete representation of a table of "buckets".
        * With separate chaining, each bucket will be a secondary map.
        * With open addressing, however there is no tangible container for each bucket, the "buckets" are effectively
        * interleaved due to the probing sequences. The following method are considered as abstract method which needs
        * to be implemented by the concrete subclass:
        * - createTable(): This method should create an initially empty table having size equal to a designated
        *                 capacity instance variable.
        * - bucketGet(h,k): This method should mimic the semantics of the public get method, but for a key k that is
        *                   known to hash to bucket h.
        * - bucketPut(h,k,v): This method should mimic the semantics of the public put method, but for a key k that is
        *                    known to hash to bucket h.
        * - bucketRemove(h,k): This method should mimic the semantics of the public remove method, but for a key k that
        *                    is known to hash to bucket h.
        * - entrySet(): This standard map method iterates through all entries of the map. We do not delegate this on a
        *               per-bucket basis because "buckets" in open addressing are not inherently disjoint.
        * */


         /*
         *
         * A simple and efficient way for dealing with collisions is to have each bucket A[j] store its own secondary
         * container, holding all entries (k,v) such that h(k) = j. A natural choice for the secondary container is a
         * small map instance implemented using an unordered list. This collision resolution rule is known as separate
         * chaining
         *
         * In the chainHashMap class, we use the MyUnsortedTableMap as data storage core to implement a hash Map with
         * separate chaining for collision handling.*/
        // L13_S9_HashTables.chainHashMapExample();

         /*
         *
         * The separate chaining rule has many nice properties, such as affording simple implementations of map
         * operations, but it nevertheless has one slight disadvantage: It requires the use of an auxiliary data
         * structure to hold entries with colliding keys.
         *
         * If space is at a premium (for example, if we are writing a program for a small handheld device), then
         * we can use the alternative approach of storing each entry directly in a table slot. This approach saves
         * space because no auxiliary structures are employed, but it requires a bit more complexity to properly
         * handle collisions. There are several variants of this approach, collectively referred to as open addressing
         *
         * linear probing is a simple method for collision handling with open addressing. With this approach, if we
         * try to insert an entry (k,v) into a bucket A[j] that is already occupied, where j = h(k) and A is the hash
         * table, then we next try A[(j+1) mod N]. If A[(j+1) mod N] is also occupied, then we try A[( j+2) mod N],
         * and so on, until we find an empty bucket that can accept the new entry. Once this bucket is located, we
         * simply insert the entry there. Of course, this collision resolution strategy requires that we change
         * the implementation when searching for an existing key—the first step of all get, put, or remove operations.
         * In particular, to attempt to locate an entry with key equal to k, we must examine consecutive slots,
         * starting from A[h(k)], until we either find an entry with an equal key or we find an empty bucket.
         * The name “linear probing” comes from the fact that accessing a cell of the bucket array can be viewed as
         * a “probe,” and that consecutive probes occur in neighboring cells.
         *
         * In the MyProbeHashMap class that uses linear probing for collision resolution.
         * */
         L13_S9_HashTables.MyProbeHashMapExample();
    }

    public static void chainHashMapExample(){
        MyChainHashMap<String,String> myMap=new MyChainHashMap<String,String>();
        myMap.put("k1","my home");
        myMap.put("k2","my_car");

        String v1=myMap.get("k1");
        String v2=myMap.get("k2");

        System.out.println("V1 is: "+v1+"V2 is: "+v2);


    }

    public static void MyProbeHashMapExample(){
        MyProbeHashMap<String,String> myMap= new MyProbeHashMap<>();
        myMap.put("k1","my home");
        myMap.put("k2","my_car");
        myMap.put("k3","my_plane");

        String v1=myMap.get("k1");
        String v2=myMap.get("k2");
        String v3=myMap.get("k3");

        System.out.println("V1 is: "+v1+"V2 is: "+v2+"V3 is: "+v3);


    }
}