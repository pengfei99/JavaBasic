package org.pengfei.Lesson13_Common_Data_Structure.L13_S9_Maps_HashTables_SkipLists;

public class L13_S9_SkipLists {
    /************************************************************************************************
     * **************************************** 13.9.4 Skip Lists  ***********************************
     * **********************************************************************************************/

    /* In section 13.9.3 we saw that a sorted table will allow O(logn) time searches via the binary search algorithm.
    * But update operations on a sorted table have O(n) worst-case running time because of the need to shift elements.
    * With linked list, the update operations are very efficient. But we can't do a fast search with a standard
    * linked list.
    *
    * An interesting data structure for efficiently realize the sorted map ADT is the skip list. It provides a clever
    * compromise to efficiently support search and update operation. In java, they are implemented as the
    * java.util.ConcurrentSkipListMap class. A skip list S for a map M consists of a series of list {S0, S1,..., Sh}
    * Each list Si stores a subset of the entries of M sorted by increasing keys, plus entries with two sentinel keys
    * denoted -infinity, and +infinity, where - infinity is smaller than every possible key that can be inserted in M,
    * and +infinity is larger than every possible key. In addition, the lists in S satisfy the following:
    * - List S0 contains every entry of the map M(plus sentinels -infinity and +infinity)
    * - For i = 1, ..., h-1, list Si contains (in addition to -infinity and +infinity) a randomly generated subset of
    *   the entries in list Si-1
    * - List Sh contains only -infinity and +infinity
    *
    * See page 436, figure 10-10 for a skip list example.
    *
    * The number of list (h) is also called as the "height" of the skip list.
    *
    * As with the position abstraction used for lists and trees, we view a skip list as a two-dimensional collection
    * of positions arranged horizontally into levels and vertically into towers. Each level is a list Si and each
    * tower contains positions storing the same entry across consecutive lists. The positions in a skip list can be
    * traversed using the following operations:
    * - next(p): Returns the position following p on the same level.
    * - prev(p): Returns the position preceding p on the same level.
    * - above(p): Returns the position above p in the same tower.
    * - below(p): Returns the position below p in the same tower.
    *
    * We conventionally assume that these operations return null if the position requested does not exist. We can imagine
    * we can implement a skip list with a linked structure (each position has four link: next, prev, above, below). The
    * traversal methods each take O(1) time, given a skip-list position p. Such a linked structure is essentially a
    * collection of h doubly linked lists aligned at towers, which are also doubly linked lists.
    *
    * */

    /*********************** 13.9.4.1 Search and Update Operations in a Skip list **********************************/

    /* The skip-list structure affords simple map search and update algo. In fact, all of the skip-list search and
    * update algorithms are based on an elegant SkipSearch method that takes a key k and finds the position p of the
    * entry in list S0 that has the largest key ko <= k (less than or equal to k which is possibly -infinity)
    *
    * Searching in a Skip list
    * Suppose we have a key k, we begin the skipSearch method by setting a position variable p to the topmost, left
    * position in the skip list S, called the start position of S. That is, the start position is the position of Sh
    * storing the special entry with key -infinity. We then perform the following steps, where key(p) denotes the key
    * of the entry at position p:
    * 1. If S.below(p) is null, then the search terminates, (we are at the bottom and have located the entry in S with
    *    the largest key less than or equal to the search key k. Otherwise, we drop down to the next lower level in the
    *    present tower by setting p=S.below(p))
    * 2. Starting at position p, we move p forward until it is at the rightmost position on the present level such that
    *    key(p)<=k. We call this the scan forward step. Note that such a position always exists, since each level
    *    contains the keys -infinity and +infinity. It may be that starting p remains where it started after we perform
    *    such a forward scan for this level.
    * 3. Return to step 1.
    *
    * We can implement the above algorithm with a pseudocode description of the skip-list search algorithm, SkipSearch.
    * Given this method, we perform the map operation get(k) by computing p = SkipSearch(k) and testing whether or
    * not key(p) = k. If these two keys are equal, we return the associated value; otherwise, we return null.
    *
    *
    * Algo SkipSearch(K):
    *      Input: A search key k
    *      Output: Position p in the bottom list S0 with the largest key having key(p)<=k
    *      // s is the starting point
    *      p=s
    *      while below(p)!=null do
    *         p=below(p)
    *         while k>=key(next(p)) do
    *               p=next(p)
    *      return p
    *
    * As it turns out, the expected running time of algorithm SkipSearch on a skip list with n entries is O(logn).
    *
    * */

    /********************************** 13.9.4.2 Insertion in a Skip list **********************************/

    /* The execution of the map operation put(k, v) begins with a call to SkipSearch(k). This gives us the position p
    * of the bottom-level entry with the largest key less than or equal to k (note that p may hold the special entry
    * with key −infinity). If key(p) = k, the associated value is overwritten with v.
    *
    * Otherwise, we need to create a new tower for entry (k,v). We insert (k,v) immediately after position p within S0.
    * After inserting the new entry at the bottom level, we use randomization to decide the height of the tower for
    * the new entry.
    *
    * We can implement the insertion algorithm for a skip list S in pseudocode as below:
    *
    * Algo Insert(k, v):
    *      Input: Key k and value v
    *      Output: Topmost position of the entry inserted in the skip list
    *      //if we already have k in the skipList
    *        //find the position in bottom list with largest key less than or equal to k
    *      p=SkipSearch(k)
    *      if(key(p)=k)
    *         p.setValue(v)
    *         // we also need to update all his above node, if not null
    *         while above(p)!=null do
    *              p=above(p)
    *              p.setValue(v)
    *      //otherwise, we create a new position and insert it in the skip list, to do that
    *     // we call another algo SkipInsert(p, k, v)
    *     else SkipInsert(p, k, v)
    *
    * // p is the previous position of the inserting entry
    * Algo SkipInsert(p, k, v):
    *      Input: p is the position in bottom list with largest key strictly less than k, Key k and value v
    *      Output: Topmost position of the entry inserted in the skip list
    *      // q is the current node of new entry's tower started as null
    *      q = null
    *      // i the current height of the new entry's tower height started as -1
    *      i=-1
    *      repeat
    *         // increase the height of the new entry's tower, as i started as -1, and the first level is 0,
    *         i=i+1
    *         // if i is greater to the height of the tower, we need to add a new level in the tower.
    *         if i >= h then
    *            h=h+1
    *            // s is the start node of the skip list
    *            t=next(s)
    *            // grow leftmost tower
    *            s=insertAfterAbove(null,s,(-infinity,null))
    *            // grow rightmost tower
    *            insertAfterAbove(s,t,(+infinity,null))
    *            // add node to new entry's tower
    *            q=insertAfterAbove(p,q,(k,v))
    *            // when we go up to the tower, the old previous position may be not present in the higher lever, so
    *            // we need to go backwards and find the not null previous position to do the insertion
    *            while above(p)==null do
    *              p=prev(p)
    *            p=above(p)
    *            until coinFlip()==tails
    *            n=n+1
    *            return q
    * // This algorithm insert an entry (k,v) after position p with the above position q. It returns the new position r
    * // and setting internal references so that next, prev, above, and below methods will word correctly for p,q and r.
    * Algo insertAfterAbove(p,q,(k,v))
    *
    * // The coinFlip algorithm returns tails or head in a random manner, with a 50% probability.
    *
    *
    * */

    /********************************** 13.9.4.3 Removal in a Skip list **********************************/

    /* Like the search and insertion algorithms, we will begin by executing method SkipSearch(k). If the
    * returned position p stores an entry with key different from k, we return null. Otherwise, we remove
    * p and all the positions above p, which is easily accessed by using above operations to climb up the
    * tower of this entry in S starting at position p.
    *
    * Algo Remove(k)
    *      Input: k is the key of the entry
    *      Output: v is the value of the entry or null if k does not exist in skp list
    *      p=SkipSearch(k)
    *      // no match key, stop and return null
    *      if key(p)!=k return null
    *      // otherwise remove p and establish link between prev(p) and next(p), repeat for all above position of p.
    *      while above(p)==null do
    *        nextN=next(p)
    *        prevN=prev(p)
    *        nextN.setPrev(prevN)
    *        prevN.setNext(nextN)
    *        p=above(p)
    *
    *      */

    /********************************** 13.9.4.4 Optimization of a Skip list **********************************/

    /* There are two obvious optimization:
    * 1. We don't need to store references to values at the levels of the skip list above the bottom level.
    * 2. For the horizontal axes, it is possible to keep the list singly linked, storing only the next references.
    *    We can perform insertions and removals in strictly a top-down, scan-forward fashion
    *
    *  Neither of these optimizations improve the asymptotic performance of skip lists by more than a constant factor,
    *  but these improvements can, nevertheless, be meaningful in practice. In fact, experimental evidence suggests
    *  that optimized skip lists are faster in practice than AVL trees and other balanced search trees, which are
    *  discussed in the Lesson of search tree.
    *    */

    /********************************* 13.9.4.5 Bounding the height of a skip list *****************************/

    /* Let us begin by determining the expected value of the height h of a skp list S with n entries (assuming that
    * we do not terminate insertions early). The probability that a given entry has a tower of height i >=1 is equal
    * to the probability of getting i consecutive heads when flipping a coin, that is, the probability is 1/(2^i).
    * Hence, the probability Pi that level i has at least one position is at most: Pi<=n/(2^i), where n is the number
    * of entry in the skip list.
    *
    * The probability that the height h of S is larger than i is equal to the probability that level i has at least
    * one position, that is, it is no more than Pi. This means that h is larger than, say, 3logn with probability at
    * most P3logn<= n/(2^(3logn))=n/n^3=1/n^2
    *
    * For example, if n=1000, this probability is a one in a one million long shot. More generally, given a constant
    * c>1, h is larger than clogn with probability at most 1/n^(c-1). That is, the probability that h is smaller than
    * clogn is at least 1-(1/n^(c-1)). Thus, with high probability, the height h of S is O(logn)
    * */

    /****************************** 13.9.4.5 Analyzing Search Time in a skip list ********************************/

    /* The search in skip list S involves two nested while loops. The inner loop performs a scan forward on a level of
    * S as long as the next key is no greater than the search key k, and the outer loop drops down to the next level
    * and repeats the scan forward iteration. Since the height h os S is O(logn) with high probability, the number of
    * drop-down steps is O(logn) with high probability.
    *
    * Observe that, after the key at the starting position, each additional key examined in a scan-forward at level i
    * cannot also belong to level i+1. If any of these keys were on the previous level, we would have encountered them
    * in the previous scan-forward step. Thus, the probability that any key is counted in ni is 1/2. Therefore, the
    * expected value of ni is exactly equal to the expected number of times we must flip a fair coin before it comes
    * up heads. This expected value is 2. Thus, the scan-forward steps at level i(ni is the number of entry at level i) has
    * O(1) time complexity.
    *
    * Conclusion, the search time complexity in a skip list is O(logn). Below is the list of complexity of all methods
    *       Method    |   Running Time
    * size, isEmpty   |      O(1)
    * get             | O(logn) expected
    * put             | O(logn) expected
    * remove          | O(logn) expected
    * firstEntry, lastEntry | O(1)
    * ceilingEntry, floorEntry | O(logn) expected
    * lowerEntry, higherEntry  | O(logn) expected
    * subMap          | O(s+logn) expected, with s entries reported
    * entrySet, keySet, values | O(n)
    *
    *
    * */

    /***************************** 13.9.4.6 Space Usage in a Skip List *****************************************/

    /* Finally, let us turn to the space requirement of a skip list S with n entries. As we observed above, the
     * expected number of positions at level i is n/2i, which means that the expected total number of positions
     * in S is:
     *
     *   n+n/2+(n/(2^2))+...+(n/(2^h))=n{1+1/2+1/(2^2)+...+1/(2^h)}, where h is the height of the skip list, and n is
     *   then entry number.
     *
     *
     * Using the proposition on geometric summations, we have 1+1/2+1/(2^2)+...+1/(2^h)=2*(1-1/(2^(h+1)))<2 for all h>=0
     *
     * Hence, the expected space requirement of S is smaller than 2*O(n). We can denote O(n).
     * */

    /**************************** 13.9.4.7 JavaSkipList implementation example ********************************/

    /* */
            public static void main(String[] args){
                ConcurrentSkipListMapExample skipListExample=new ConcurrentSkipListMapExample();
                //skipListExample.doExample1();
                skipListExample.doExample2();
            }


}
