package org.pengfei.Lesson13_Common_Data_Structure.L13_S10_Search_Trees;

import org.pengfei.Lesson13_Common_Data_Structure.L13_S7_Trees.TreePosition;

import java.util.AbstractMap;
import java.util.Map;

public class L13_S10_Binary_Search_Trees {

    public static void main(String[] args){

        /************************************************************************************************
         * ******************************** 13.10.1 Binary Search Trees  ********************************
         * **********************************************************************************************/
        /*
        * In section 7, we introduced the tree data structure and demonstrated a variety of applications. One
        * important use is as a search tree. In this section, we use a search-tree structure to efficiently
        * implement a sorted map.
        *
        * A map ADT has three most fundamental methods:
        * -get(k): Returns the value v associated with key k, if such an entry exists, otherwise return null.
        * -put(k,v): Associates value v with key k, replacing and returning any existing value if the map already
        *            contains an entry with key equal to k.
        * -remove(k): Removes and returns the entry with key equal to k, if one exists, otherwise return null.
        *
        * The sorted map ADT includes additional functionality, guaranteeing that an iteration reports keys in
        * sorted order, and supporting additional searches such as higherEntry(k) and subMap(k1,k2).
        *
        * In this section, we define a binary search tree as a proper binary tree such that each internal position
        * p stores a key value pair (k,v) such that:
        * - Keys stored in the left subtree of p are less than k.
        * - keys stored in the right subtree of p are greater than k.
        *
        * P460 Figure 11.1, Notice that the leaves of the tree serve only as "placeholders". We use them as sentinels
        * which can simplifies the presentation of several of our search and update algorithms. With care, they can be
        * represented as null references in practice, thereby reducing the number of nodes in half.
        *
        *         */

        /*************************** 13.10.1.1 Searching in Binary Search Trees  **************************/

        /* Quick Search is the most important structural property of a binary search tree. We can view it as a decision
        * tree, at each internal position (not leaves), we check if the desired key k is less than, equal to, or greater
        * than the key stored at position p, which we denote as key(p). If it's equal, we terminate the search
        * successfully. If the answer is greater, then the search continues in the right subtree. If the answer is less
        * then the search continues in the left subtree. Finally, if we reach a leaf, then the search terminates
        * unsuccessfully.
        *
        * We can write an Algo of the above procedure:
        *
        * // p is the current position in the tree, the init value is root of the tree. k is the key which we search
        * Algorithm TreeSearch(p,k):
        *    if p is external then
        *       return p                            // unsuccessful search
        *    else if k==key(p) then
        *       return p                           // successful search
        *    else if k<key(p) then
        *       return TreeSearch(left(p),k)      // recur on left subtree
        *    else // k > key(p)
        *       return TreeSearch(right(p),k)     // recur on right subtree
        *
        *  The time complexity of this algo is O(h) where h is the height of the tree. Admittedly, the height h of T
        *  can be as large as the number of entries, n, but we expect that it usually much smaller. Later in this section,
        *  we will show various strategies to maintain an upper bound of O(logn) on the height of a search tree T.
        *
        *
        * */

        /************************ 13.10.1.2 Insertions and Deletions in Binary Search Trees  ***********************/

        /* Insertion
        *
        * The map operation put(k, v) begins with a search for an entry with key k. If found, that entry’s existing
        * value is reassigned. Otherwise, the new entry can be inserted into the underlying tree by expanding the
        * leaf that was reached at the end of the failed search into an internal node. The binary search-tree property
        * is sustained by that placement (note that it is placed exactly where a search would expect it). Let us assume
        * a proper binary tree supports the following update operation:
        * expandExternal(p,e): Stores entry e at the external position p, and expands p to be internal, having two
        *                      new leaves as children.
        *
        *  Algorithm TreeInsert(k,v):
        *     Input: A search key k to be associated with value v
        *     p= TreeSearch(root(),k)
        *     if k==key(p) then
        *       change p's value to (v)
        *     else
        *       expandExternal(p,(k,v))
        *
        * */

        /* Deletion
        *
        * The deletion is more difficult, unlike insertions always happens at a leaf, deletion can happen at anywhere.
        * To delete an entry with key k, we begin by calling TreeSearch(root(),k) to find the position p storing an
        * entry with key equal to k. If the search returns an external node, then there is no entry to remove. Otherwise,
        * we distinguish between two cases:
        * - If the position p has only one internal child, the deletion of the entry is to delete the p and the external
        *   child of p, then let the internal child take the p's place. (P465, figure 11.5)
        *
        * - If the position p has two child, we need to proceed as follows:
        *      - We locate position r containing the entry having the greatest key that is strictly less than that of
        *        position p (its so-called predecessor in the ordering of keys). That predecessor will always be
        *        located in the rightmost internal position of the left subtree of position p.
        *      - We use r’s entry as a replacement for the one being deleted at position p. Because r has the
        *        immediately preceding key in the map, any entries in p’s right subtree will have keys greater than
        *        r and any other entries in p’s left subtree will have keys less than r. Therefore, the binary
        *        search-tree property is satisfied after the replacement.
        *      - Having used r’s entry as a replacement for p, we instead delete the node at position r from the tree.
        *        Fortunately, since r was located as the rightmost internal position in a subtree, r does not have an
        *        internal right child. Therefore, its deletion can be performed using the first (and simpler) approach.
        * */

        /********************************* 13.10.1.3 Java Implementation  *******************************/

        /* In the class MyTreeMap, we implement the sorted map ADT by using a binary search tree for storage. The
        * MyTreeMap class is declared as a sub class of AbstractSortedMap base class. As a result, it inherits the
        * support for performing comparisons based upon a given Comparator.
        *
        * The core data store is implemented in the class MyBalanceableBinaryTree, which extends the LinkedBinaryTree
        * class.
        *
        * The core of the MyBalanceableBinaryTree is the rotate and restructure method, which can transform a unbalanced
        * tree to a balanced one.
        *
        * When we insert, delete entry in the tree map, it might be change the balance of the tree. In the myTreeMap
        * implementation, we design the balance mechanism with flexibility so that it can be subclassed to implement
        * various forms of balanced search trees(e.g. rebalanceAccess, rebalanceInsert, and rebalanceDelete).
        * */

       /***************************** 13.10.1.4 Performance of a Binary Search Tree  *******************************/

       /***
        * Below list shows the time complexity of the binary search tree
        *    Method      |    Running Time
        * size, isEmpty  |      O(1)
        * get, put, remove |    O(h)
        * firstEntry, lastEntry |  O(h)
        * ceilingEntry, floorEntry, lowerEntry, higherEntry |  O(h)
        * subMap         |    O(s+h)
        * entrySet, keySet, values  | O(n)
        *
        * Almost all operations have a worst-case running time that depends on h, where h is the height of the
        * current tree. This is because most operations rely on traversing a path from the root of the tree, and
        * the maximum path length within a tree is proportional to the height of the tree.
        *
        * The recursive subMap implementation can be shown to run in O(s+h) worst-case bound for a call that
        * reports s results
        * */

        /************************************** 13.10.2.1 Balanced Search Trees  ************************************/

        /*
        * As we discussed before, the standard binary search tree supports O(logn) expected running times for the
        * basic map operations. However, we may only claim O(n) worst-case time, because some sequences of operations
        * may lead to an unbalanced tree with height proportional to n.  In the rest of this section, we will explore
        * four search-tree algorithms that provide stronger performance guarantees. Three of the four data structures
        * (e.g. AVL trees, splay trees, and red-black trees) are based on augmenting a standard binary search tree with
        * occasional operations to reshape the tree and reduce its height.
        *
        * Rotation
        * The primary operation to rebalance a binary search tree is known as a "rotation". During a rotation, we
        * “rotate” a child to be above its parent,
        *
        * For example, we rotate the x to be parent of y.
        * <pre>
        *             y                   x
        *           /  \                /  \
        *          x   T3      ->      T1   y
        *        /  \                     / \
        *       T1  T2                   T2 T3
        * </pre> figure rotation-13.10.2.1
        * To maintain the binary search-tree property through a rotation, we note that if position x was a left child
        * of position y prior to a rotation, then y becomes the right child of x after the rotation, and vice versa.
        * Furthermore, we must relink the subtree of entries with keys that lie between the keys of the two positions
        * that are being rotated.
        *
        * To rebalance a tree, we may need one or more rotation. One such compound operation is called trinode
        * restructuring. Below shows four different initial tree stat.
        *
        * * Assumes the nodes are in one of the following configurations:
         *<pre>
         *     z=a                 z=c           z=a               z=c
         *    /  \                /  \          /  \              /  \
         *   t0  y=b             y=b  t3       t0   y=c          y=a  t3
         *      /  \            /  \               /  \         /  \
         *     t1  x=c         x=a  t2            x=b  t3      t0   x=b
         *        /  \        /  \               /  \              /  \
         *       t2  t3      t0  t1             t1  t2            t1  t2
         *        (1)            (2)              (3)              (4)
         * </pre>
         * We need to rebalance them, so the subtree will be restructured so that the node with key b becomes its root.
         * The (1), (2) only need one rotation, the (3), (4) need two rotation. (3)-first rotation between b and c->(1)
         * -second rotation between b and a. For (4)->(2)
         *<pre>
         *           b
         *         /   \
         *       a       c
         *      / \     / \
         *     t0  t1  t2  t3
         *</pre>
         *
         * Caller should ensure that x has a grandparent.
        *
        * */

        /********************** 13.10.2.2 Java implementation for balancing search Trees  ***************************/

        /*
        * In class MyBalanceableBinaryTree, we implement the rotation and trinode restructuring algorithm. This class
        * is an extention of the class LinkedBinaryTree.
        *
        * To simplify the code, we define an additional relink utility that properly links parent and child nodes to
        * each other. The focus of the rotate method then becomes redefining the relation-ship between parent and child,
        * relinking a rotated node directly to its original grandparent, and shifting the “middle” subtree (that
        * labeled as T2 in figure rotation-13.10.2.1) between the rotated nodes.
        * */

       // L13_S10_Binary_Search_Trees.BalanceableTreeExample();

        /*
         * Specialized Nodes with an Auxiliary Data member
         * Many tree balancing strategies require that some form of auxiliary "balancing" information be stored at nodes
         * of a tree. To ease the burden on the balanced-tree subclass, we choose to add an auxiliary integer value to
         * every node within the MyBalanceableSearchTree class. This is accomplished by defining a new MyBSTNode class,
         * which extends MyTreeNode class
         *
         * Note, we use MyBSTNode in the MyBalanceableSearchTree class. But the method such as addLeft, addRight are
         * reside in the original LinkedBinaryTree which use the MyTreeNode type. We must make sure when we call addLeft
         * in MyBalanceableSearchTree, addLeft uses the MyBSTNode. To do so, we rely on a technique know as the
         * factory method design pattern. We override the createNode method in the MyBalanceableSearchTree class, this
         * createNode method in LinkedBinaryTree class returns MyTreeNode type. This override will force the methods
         * such as addLeft, etc. use the createNode method of the MyBalanceableSearchTree.
         * */


        /************************************** 13.10.3.1 AVL Trees  ************************************/

        /* In the previous section, we discussed how to use rotation techniques to balance a tree. But we don't
        * have any balancing strategies, which means how to choose a node to rotate. AVL trees use one of the
        * balancing strageties to control the height of the tree.
        * */

        /*
        * AVL tree, is named after the initials of its inventors: Adel'son-Vel'skii and Landis. Any Binary search tree
        * T that satisfies the height-balance property is said to be an AVL tree.
        *
        * Height-Balance Property: For every internal position p of T, the heights of the children of p differ by
        *                          at most 1.
        *
        * For example, in Figure(1), e,f,t0,t2,t3 has height of 1, t1 has height of 2, H(a)=3, H(c)=2, H(b)=4. It
        * respects the height-balance property. So it's an AVL
        *<pre>
        *               b
        *          /        \
        *        a          c
        *      /  \        / \
        *     t0  t1 h=2  t2  t3 h=1
        *        / \
        *       e  f h=1
        *            Figure(1)
        * </pre>
        * In figure (2), H(t0)=1, but H(b)=3, so the differ is 2, it does not respect the height-balance property.
         *<pre>
         *      a                 z=c           z=a               z=c
         *    /  \                /  \          /  \              /  \
         *   t0   b             y=b  t3       t0   y=c          y=a  t3
         *      /  \            /  \               /  \         /  \
         *     t1   c         x=a  t2            x=b  t3      t0   x=b
         *        /  \        /  \               /  \              /  \
         *       t2  t3      t0  t1             t1  t2            t1  t2
         *        (2)            (3)              (4)              (5)
         * </pre>
        *
        * With the height-Balance Property, we know that all sub tree of a AVL tree are also AVL trees. The height
        * of an AVL tree storing n entries is O(logn)
        * */

        /************************* 13.10.3.2 Update Operations in AVL Trees  *****************************/

        /*
        * Given a binary search tree T, we say that a position is balanced if the absolute value of the difference
        * between the heights of its children is at most 1, and we say that it is unbalanced otherwise. Thus, the
        * height-balance property characterizing AVL trees is equivalent to saying that every position is balanced.
        *
        * The insertion and deletion operations for AVL trees begin similarly to the corresponding operations for
        * (standard) binary search trees, but with post-processing for each operation to restore the balance of any
        * portions of the tree that are adversely affected by the change.
        *
        * Insertion
        * The insertion in the AVL trees may break the height-balance property. We also know that the insertion add 1
        * level to all the ancestors of the new inserted node. As a result, only these ancestors become unbalanced.
        * To make the subtree(the inserted node and all its ancestors) balance again, we need to restructure the sub
        * tree. For example, we have the following AVL tree:
        *
        *                     44
        *                 /       \
        *               17        78 (z)
        *              / \        / \
        *           nil  32     50  88
        *                      / \
        *                    48  62
        *
        *   Now, we insert the node 54
         *                     44
         *                 /       \
         *               17         78 (z)
         *              / \        /  \
         *           nil  32     50(y) 88
         *                      / \
         *                    48  62 (x)
        *                        /
        *                       54
        *  By the definition of the binary search tree, the node 54 will be added as the left child of the node 62.
        *  We note 62 as x, 50 as y, 78 as z, which are ancestor nodes of the inserted node 54.
        *  We can notice that the node 78 is the first ancestor node that violates the height-balance property.
        *
        *  By the definition of the AVL tree, we know if all sub trees are avl trees, then the tree is AVL. We know if
        *  we balance the sub tree (x,y,z), all its ancestors are balanced too. We will use the "trinode restructuring"
        *  method with x as the input node(e.g. restructure(x)).
        *
        *  Deletion
        *
        *
        *
        *
        * */

         /************************* 13.10.3.3 Performance of AVL Trees  *****************************/

  /*
   * Note that, with the AVL tree height-balance property, we can guarantee the height of an AVL tree with n entries is
   * O(logn). As a result, we can have the following method performance list:
   *
   * Method         |          Running Time
   * size, isEmpty  |             O(1)
   * get, put, remove  |        O(logn)
   * firstEntry, lastEntry |    O(logn)
   * ceilingEntry, floorEntry, lowerEntry, higherEntry |    O(logn)
   * subMap         |           O(s+logn)
   * entrySet, keySet, values |   O(n)
   *
   * */

        /************************* 13.10.3.4 Java Implementation of AVL Trees  *****************************/

        AVLTreeExample();

        /******************************************** 13.10.4.1 Splay Trees  ****************************************/

        /* The splay tree is conceptually quite different from the other balanced search trees we will discuss in this
        * section. A splay tree does not strictly enforce a logarithmic upper bound on the height of the tree.
        *
        * The efficiency of splay trees is due to a certain move-to-root operation, called splaying, that is performed
        * at the bottommost position p reached during every insertion, deletion, or even a search. Intuitively, a splay
        * operation causes more frequently accessed elements to remain nearer to the root, thereby reducing the typical
        * search times. The surprising thing about splaying is that it allows us to guarantee a logarithmic amortized
        * running time, for insertions, deletions, and searches
        *
        * Splaying
        * Given a node x of a binary search tree T, we splay x by moving x to the root of T through a sequence of
        * restructurings. The specific operation we perform to move x up depends upon the relative positions of x,
        * its parent y, and x’s grandparent z (if it exists). There are three cases that we will consider:
        *
        * zig-zig: The node x and its parent y are both left children or both right children. We promote x, making
        *          y a child of x and z a child of y, while maintaining the inorder relationships of the nodes in T.
        *
        *  <pre>
        *
        *             10(z)                   30
        *            / \                     / \
        *          T1   20(y)               20 T4
        *               / \                / \
        *              T2 30(x)          10  T3
        *                / \            / \
        *               T3  T4        T1  T2
        *       (1):Before splay     (2) After splay
        * </pre>
        *
        * zig-zag: One of x and y is a left child and the other is a right child. In this case, we promote x by making
        *          x have y and z as its children, while maintaining the inorder relationships of the nodes in T.
        *
         *  <pre>
         *
         *             10(z)                   20
         *            / \                     /  \
         *          T1   30(y)              10   30
         *               / \               / \   / \
         *           20(x) T4             T1 T2 T3 T4
         *            / \
         *          T2  T3
         *       (1):Before splay     (2) After splay
         * </pre>
         *
         * zig: x does not have a grandparent. In this case, we perform a single rotation to promote x over y, making
         *      y a child of x, while maintaining the relative inorder relationships of the nodes in T.
         *
         *  <pre>
         *
         *             10(y)                   20
         *            / \                     /  \
         *          T1   20(x)              10   T3
         *               / \               / \
         *             T2  T3             T1 T2
         *       (1):Before splay     (2) After splay
         * </pre>
         *
         * A splaying step consists of repeating these restructurings(zig-zig, zig-zag, zig) at x until x becomes the
         * root of T. An example of the splaying of a node is shown in Figures 11.18(P490) and 11.19(P491).
         *
         *
         * */

        /******************************************** 13.10.4.2 When to Splay  ****************************************/

        /** There are several rules that dictate when splaying is performed:
         *
         * 1. After searching: When searching for key k, if k is found at position p, we splay p, else we splay the
         *                     parent of the leaf position at which the search terminates unsuccessfully.
         *
         * 2. After inserting: When inserting key k, we splay the newly created internal node where k gets inserted.
         *
         * 3. After deletion: When deleting a key k, we splay the position p that is the parent of the removed node.
         *                    If the removed node does not have the parent(root), we need to splay the parent of the
         *                    replacing node which is the inorder predecessor.
         *
         * For example, if we have the following tree, and we want to delete 8.
         *
         *                         8
         *                       /   \
         *                      3    10
         *                    /  \
         *                  nil  4
         *                        \
         *                        6 (p)
         *                       / \
         *                      5  7 (w)
         *
         *  First step: The inorder predecessor of 8 is 7, so 7 is the replacing node w, and its parent is 6 (p).
         *  Second step: We splay 6 to the root of the tree. After splay, we will have the following tree.
         *
         *                         6
         *                       /   \
         *                      4    7
         *                    /  \    \
         *                   3   5    10
         *
         * */

        /******************************* 13.10.4.3 Splay Tree Java Implementation  *********************************/

    }

    public static void BalanceableTreeExample(){

        /* In this example, we first build a tree in (1), then we use rotate to transform it into (2)
        * There are not balanced, we just show how to rotate nodes.
        *
        * <pre>
        *
        *              a                 a
         *            / \               / \
         *          b   t3            c   t3
         *         / \                / \
         *        c  t2             t0   b
         *       / \                    / \
         *      t0  t1                 t1  t2
         *       (1)                   (2)
         * </pre>
         *
         *
        * */
        MyBalanceableBinaryTree<String,String> myBTree=new MyBalanceableBinaryTree<>();
        AbstractMap.SimpleEntry<String,String> a=new AbstractMap.SimpleEntry<>("a","val1");

        myBTree.addRoot(a);

        TreePosition<Map.Entry<String, String>> rootP = myBTree.root();

        TreePosition<Map.Entry<String, String>> b = (TreePosition<Map.Entry<String, String>>)myBTree.addLeft(rootP, new AbstractMap.SimpleEntry<String, String>("b", "val2"));
        TreePosition<Map.Entry<String, String>> t3 = myBTree.addRight(rootP, new AbstractMap.SimpleEntry<>("t3", "t3"));

        TreePosition<Map.Entry<String, String>> c=myBTree.addLeft(b,new AbstractMap.SimpleEntry<String,String>("c","val3"));
        TreePosition<Map.Entry<String, String>> t2 = myBTree.addRight(b, new AbstractMap.SimpleEntry<>("t2", "t2"));
        //myBTree.addRight(b,null);

        //myBTree.dump();

        //TreePosition<Map.Entry<String, String>> rTest = myBTree.root().getRight();
        //TreePosition<Map.Entry<String, String>> lTest = myBTree.root().getLeft();

        myBTree.addLeft(c,new AbstractMap.SimpleEntry<>("t0","t0"));

        myBTree.addRight(c,new AbstractMap.SimpleEntry<>("t1","t1"));

        //myBTree.addLeft(b,null);

        myBTree.dump();

        myBTree.rotate(c);

        myBTree.dump();
    }

    public static void AVLTreeExample(){
        /* In this example, we will use the put method of the TreeMap to insert nodes in the AVLTree, as the insert node
         * is balanced after the insert, we will have an AVL tree in the end.
         *
         * <pre>
         *
         *              a                 a
         *            / \               / \
         *          b   t3            c   t3
         *         / \                / \
         *        c  t2             t0   b
         *       / \                    / \
         *      t0  t1                 t1  t2
         *       (1)                   (2)
         * </pre>
         *
         *
         * */
        MyAVLTreeMap<Integer,String> avlTreeMap=new MyAVLTreeMap();
        avlTreeMap.put(3,"val3");
        avlTreeMap.dump();
        avlTreeMap.put(2,"val2");
        avlTreeMap.dump();
        avlTreeMap.put(1,"val1");


    }
}
