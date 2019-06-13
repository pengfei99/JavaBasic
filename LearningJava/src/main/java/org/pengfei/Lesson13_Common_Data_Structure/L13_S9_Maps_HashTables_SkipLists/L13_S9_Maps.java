package org.pengfei.Lesson13_Common_Data_Structure.L13_S9_Maps_HashTables_SkipLists;

public class L13_S9_Maps {
    public static void main(String[] args){
        /************************************************************************************************
         * ******************************************** 13.9.1 Maps  ***********************************
         * **********************************************************************************************/

        /* A map is an abstract data type designed to efficiently store and retrieve values based upon
        *  a uniquely identifying search key for each. Specifically, a map stores key-value pairs(k,v),
        *  which we call entries, where k is the key and v is its corresponding value. Keys are required
        *  to be unique, so that the association of keys to value defines a mapping.
        *
        *  Maps are also known as associative arrays, because the entry's key serves somewhat like an index into
        *  the map, in that it assists the map in efficiently locating the associated entry. However, a key of a
        *  map need not be numeric, and it does not directly designate a position within the structure. */

        /************************************* 13.9.1.1 The Map ADT ***********************************/

        /* A map ADT M supports the following methods:
        * - size(): Returns the number of entries in M.
        * - isEmpty(): Returns true if M is empty, else returns false.
        * - get(k): Returns the value v associated with key k, if such an entry exists, otherwise returns null
        * - put(k,v): If M does not have an entry with key equal to k, then adds entry(k,v) to M and returns null;
        *             else, replaces with v the existing value of the entry with key equal to k and returns the
        *             old value.
        * - remove(k): removes from M the entry with key equal to k, and returns its value; If M has no such entry
        *              then returns null.
        * - keySet(): Returns an iterable collection containing all the keys stored in M.
        * - values(): Returns an iterable collection containing all the values of entries stored in M (with repetition
        *              if multiple keys map to the same value)
        * - entrySet(): Returns an iterable collection containing all the key-value entries in M.
        *
        * A formal definition of a Java interface of the map ADT is given in MyMap interface.
        * */

        /*********************************** 13.9.1.2 Maps in the java.util Package ************************/
        /* Our definition of the map ADT is a simplified version of the java.util.Map interface. For the elements
        * of the iteration returned by entrySet, we will reply on the composite Entry interface introduced
        * in the Priority queue section.
        *
        * If our map allows null value, for example, we have entry(k,null) in map, the get(k) will return null, this is
        * ambiguise, because we don't know if the entry exist, or not, or just the value of entry is null. To avoid this,
        * We need to have a method containsKey(k) which returns boolean to check whether k exists as a key.*/

        /*********************************** 13.9.1.3 Maps applications: Word count  ************************/

        /* As a case study for using a map, consider the problem of counting the number of occurrences of words
        * in a document. This is a standard task when performing a statistical analysis of a document, for example,
        * when categorizing an email or news article. A map is an ideal data structure to use here, for we can use
        * words as keys and word counts as values.*/

        /*********************************** 13.9.1.4 An AbstractMap base class ***************************/

        /* In the rest of this chapter, we will present may different implementations of the map ADT using a variety
         * of data structures, each with its own trade-off of advantage and disadvantages. To have greater code reuse,
         * we will use a combination of interface, abstract and concrete class.
         *
         * - <interface> MyMap
         * - <interface> MySortedMap
         * -
         *  */

        /* We begin, by designing an AbstractMap base class that provides functionality that is shared by all of our map
        * implementations. More specificlly, it provides the following support:
        * - An implementation of the isEmpty method, based upon the presumed implementation of the size method.
        * - A nested MapEntry class that implements the public Entry interface, while providing a composite for
        *   storing key-value entries in a map data structure.
        * - Concrete implementations of the keySet and values methods, based upon an adaption to the entrySet method.
        *   In this way, concrete map classes need only implement the entrySet method to provide all three forms of
        *   iteration.
        *
        * The code can be found in the MyAbstractMap abstract class.
        * */

        /*********************************** 13.9.1.5 A Simple Unsorted Map Implementation ***************************/


        /* In this section, we implement MyUnsortedTableMap by using an ArrayList as the data storage core.*/
    }
}
