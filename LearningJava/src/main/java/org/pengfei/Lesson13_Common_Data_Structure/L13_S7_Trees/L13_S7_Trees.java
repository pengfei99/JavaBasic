package org.pengfei.Lesson13_Common_Data_Structure.L13_S7_Trees;

public class L13_S7_Trees {
    public static void main(String[] args){
        /************************************************************************************************
         * ********************************** 13.7 Trees  ****************************************
         * **********************************************************************************************/

        /* Tree structures is one of the most important nonlinear data structures in computing. It allows us
        * to implement a host of algorithms much faster than when using linear data structures, such as arrays
        * or linked lists. Trees also provide a natural organization for data, and consequently become ubiquitous
        * structures in file system, graphical user interface, databases, websites, and many other computer systems.
        *
        * The relationships in a tree are hierarchical(vs linear), with some objects being "above" and some "below"
        * others. The main terminology of tree data structures comes from "family trees", with terms such as "Parent",
        * "Child", "Ancestor" and "Descendant".
        *
        * */

        /************************************************************************************************
         * ********************************** 13.7.1 General Tree definitions and Properties  ******************
         * **********************************************************************************************/

        /* A tree is an abstract data type that stores elements hierarchically. With the exception of the top
        * element, each element in a tree has a parent element and zero or more children elements. The top element is
        * often called 'root' of the tree.
        *
        * Formal Tree Definition
        * Formally, we define a tree T as a set of nodes storing elements such that the nodes have a parent-child
        * relationship that satisfies the following properties:
        * - If T is nonempty, it has a special node, called the root of T, that has no parent.
        * - Each node v of T different from the root has a unique parent node w; every node with parent w is a child of w.
        *
        * Note that according to our definition, a tree can be empty, meaning that it does not have any nodes. This
        * convention also allows us to define a tree recursively such that a tree T is either empty or consists of a
        * node r, called the root of T, and a (possibly empty) set of subtrees whose roots are the children of r.
        *
        * Other Node Relationships
        * Two nodes that are children of the same parent are "siblings". A node v is "external" if v has no children.
        * A node v is "internal" if it has one or more children. External nodes are also known as "leaves".
        *
        * Edges and Paths in Trees
        * An edge of tree T is a pair of nodes (u,v) such that u is the parent of v, or vice versa. A path of T
        * is a sequence of nodes such that any two consecutive nodes in the sequence form an edge. For example, in a
        * file system, the file path is the path of the tree (/home/users/pliu/Document). Document is the child of pliu
        * pliu is the child of users, and so on. Every two consecutive nodes form an edge.
        *
        * Ordered Trees
        * A tree is ordered if there is a meaningful linear order among the children of each node; that is, we
        * purposefully identify the children of a node as being the first, second, third, and so on. Such an order
        * is usually visualized by arranging siblings left to right, according to their order.
        * For example, a book is hierarchically organized as a ordered tree whose internal nodes are parts, chapters,
        * and sections, and whose leaves are paragraphs, tables, figures, and so on. In each node level, the siblings
        * are ordered from left to right (e.g. part1, part2, part3, etc.). chap1 under part1 and chap1 under part2 are
        * not comparable. But chap1, chap2, chap3, etc. under part1 are ordered from left to right.
        *
        * */

        /**************************************** 13.7.1.1 The Tree Abstract Data Type  ***********************/

        /* To build a tree ADT, we need to introduce an ADT position which satisfy parent-child relationships that
        * defines the tree structure. The element is stored at each position. A position object supports the following
         * method:
         *
         * - getElement( ): Returns the element stored at this position.
         *
         * The tree ADT then supports the following accessor methods, allowing a user to navigate the various
         * positions of a tree T:
         *
         * - root( ): Returns the position of the root of the tree (or null if empty).
         * - parent(p): Returns the position of the parent of position p (or null if p is the root).
         * - children(p): Returns an iterable collection containing the children of position p (if any).
         * - numChildren(p): Returns the number of children of position p.
         *
         * If a tree T is ordered, then children(p) reports the children of p in order. In addition to the above
         * fundamental accessor methods, a tree supports the following query methods:
         *
         * - isInternal(p): Returns true if position p has at least one child.
         * - isExternal(p): Returns true if position p does not have any children.
         * - isRoot(p): Returns true if position p is the root of the tree.
         *
         * These methods make programming with trees easier and more readable, since we can use them in the
         * conditionals of if statements and while loops. Trees support a number of more general methods, unrelated
         * to the specific structure of the tree. These include:
         *
         * - size( ): Returns the number of positions (and hence elements) that are contained in the tree.
         * - isEmpty( ): Returns true if the tree does not contain any positions (and thus no elements).
         * - iterator( ): Returns an iterator for all elements in the tree (so that the tree itself is Iterable).
         * - positions( ): Returns an iterable collection of all positions of the tree.
         *
         * Note two important things:
         * - If an invalid position is sent as a parameter to any method of a tree, then an IllegalArgumentException
         *   is thrown.
         * - The above ADT does not have any creation or modification method. Because we prefer to describe different
         *   tree update methods in conjunction with specific implementations of the tree interface, and specific
         *   applications of trees.
         *
         *  The tree interface implementation can be found in MyTree.
         * */

        /************************************* 13.7.1.2 An AbstractTree Base class  ************************/

        /* An abstract class is designed to serve as a base class, through inheritance, for one or more
        * concrete implementations of an interface. When some of the functionality of an interface is implemented
        * in an abstract class, less work remains to complete a concrete implementation. The standard Java libraries
        * include many such abstract classes, including several within the Java Collections Framework. To make
        * their purpose clear, those classes are conventionally named beginning with the word Abstract. For example,
        * there is an AbstractCollection class that implements some of the functionality of the Collection interface.
        *
        * In the case of our Tree interface, we will define an AbstractTree base class, demonstrating how many
        * tree-based algorithms can be described independently of the low-level representation of a tree data
        * structure. In fact, if a concrete implementation provides three fundamental methods "root( ), parent(p),
        * and children(p)" all other behaviors of the Tree interface can be derived within the AbstractTree
        * base class.
        *
        * The implementation can be found in MyAbstractTree.
        * */

        /************************************ 13.7.1.3 Computing depth and height  *************************/

        /*
         * Tree Depth:
         * Let p be a position within tree T. The depth of p is the number of ancestors of p, other than p itself.
         * For example, in a tree, we have the path (/home/users/pliu/Documents), the node storing "Documents" has
         * depth 3. Note that this definition implies that the depth of the root of T is 0. The depth of p can also
         * be recursively defined as follows:
         * - If p is the root, then the depth of p is 0.
         * - Otherwise, the depth of p is one plus the depth of the parent of p.
         *
         * With the above recursive definition, we can calculate the running time of depth(p) is O(dp+1), where dp
         * denotes the depth of p in the tree, because the algorithm performs a constant-time recursive step for each
         * ancestor of p. Thus, algorithm depth(p) runs in O(n) worst-case time, where n is the total number of
         * positions of T, because a position of T may have depth n−1 if all nodes form a single branch.
         *
         * Tree Height:
         * We next define the height of a tree to be equal to the maximum of the depths of its positions
         * (or zero, if the tree is empty). It is easy to see that the position with maximum depth must be a leaf.
         * Based on the above definitions, we know that "the height of the root of a nonempty tree T, according to the
         * ecursive definition, equals the maximum depth among all leaves of tree T".
         *
         * The height and depth method are implemented in the MyAbstractTree. For the height method, we propose two
         * different definitions. One is not very efficient, we declare it as private, so no one else can use it.
         * */

/************************************************************************************************
 * ********************************** 13.7.2 Binary Trees   **************************************
 * **********************************************************************************************/

/* A binary tree is an ordered tree with the following properties:
 * - Every node has at most two children.
 * - Each child node is labeled as being either a left child or a right child.
 * - A left child precedes a right child in the order of children of a node.
 *
 * The subtree rooted at a left or right child of an internal node v is called a left subtree or right subtree,
 * respectively, of v. A binary tree is proper if each node has either zero or two children. Some people also refer
 * to such trees as being full binary trees. Thus, in a proper binary tree, every internal node has exactly two
 * children. A binary tree that is not proper is improper.
 *
 * A Recursive Binary Tree Definition
 * Incidentally, we can also define a binary tree in a recursive way. In that case, a binary tree is either:
 * - An empty tree.
 * - A nonempty tree having a root node r, which stores an element, and two binary trees that are respectively
 *   the left and right subtrees of r. We note that one or both of those subtrees can be empty by this definition.
 *
 * */

/*********************************** 13.7.2.1 The Binary Tree ADT   ***************************************/

/* A binary tree is a specialization of a tree that supports three additional accessor methods:
* - left(p): Returns the position of the left child of p (or null if p has no left child).
* - right(p): Returns the position of the right child of p (or null if p has no right child).
* - sibling(p): Returns the position of the sibling of p (or null if p has no sibling).
*
* Based on the above ADT, we can write a java interface of the binary tree. You can find the implementation in
* MyBinaryTree. You can notice that, the MyBinaryTree extends interface MyTree. Because a binary tree is expected to
* support all the functionality that we defined for general trees(e.g. root, isExternal, etc.)
*
* We continue our use of abstract base classes to promote greater reusability within our code. The MyAbstractBinaryTree
* class implements three method
* - sibling(TreePosition<E> p): will return the p's sibling
* - numChildren(TreePosition<E> p): Returns the number of children of Position p.
* - children(TreePosition<E> p): Returns an iterable collection of the Positions representing p's children
* */

/*********************************** 13.7.2.2 The Binary Tree Properties   ***************************************/

/*
 * Binary tree height and node number relations:
 * Binary trees have several interesting properties dealing with relationships between their heights and number of
 * nodes. We denote the set of all nodes of a tree T at the same depth d as level d of T. In a binary tree, level 0
 * has at most one node (the root), level 1 has at most two nodes (the children of the root), level 2 has at most
 * four nodes, and so on. In general, level d has at most 2^d nodes.
 *
 * Suppose we have a binary tree T, and N is the number of the node in T. Ne is the number of the external node in T,
 * Ni is the number of the internal node in T, H is the height of T. Then T has the following properties:
 *
 * 1. H+1<= N <= 2^(H+1)-1
 * 2. 1<=Ne<=2^H
 * 3. H<=Ni<=2^H-1
 * 4. log(N+1)-1<=H<=N-1
 *
 * If T is a full binary tree (sometimes referred to as a proper or plane binary tree) is a tree in which every node
 * has either 0 or 2 children, then T has the following properties:
 *
 * 1. 2H+1 <= N <=2^(H+1)-1
 * 2. H+1<=Ne<=2^H
 * 3. H<=Ni<=2^H-1
 * 4. log(n+1)-1<=H<=(N-1)/2
 *
 * Relating internal nodes to external nodes in a full binary tree
 * In a nonempty  binary tree T, with Ne external nodes and Ni internal nodes, we have Ne = Ni +1.
 * */

/************************************************************************************************
 * ********************************** 13.7.3 Implementing Trees   *******************************
 * **********************************************************************************************/

/* In the previous sections of this Lesson, we have defined two interfaces and two abstract classes. But they can't be
 * directly instantiated. We need to implement the core structure of a tree, which is the node(i.e. TreePosition).
 *
 * To facilitate the implementation, we start with the case of a binary tree.
 *
 * */


/***************************** 13.7.3.1 Linked Structure for Binary Trees *************************/

/* A natural way to realize a binary tree T is to use a "linked structure", with a node that maintains references to
 * the element stored at a position P and to the nodes associated with such as Parent of P, children of P. If P is root
 * of the tree, then parent of P is null. Likewise, if p does not have a left child (respectively, right child),
 * the associated field is null. The tree itself maintains an instance variable storing a reference to the root node
 * (if any), and a variable, called size, that represents the overall number of nodes of T.
 *
 * For example, a node in binary tree has four core fields:
 * - parent : is the parent of the node
 * - left : is the left child of the node
 * - right : is the right child of the node
 * - element : is the element stored in the node.
 * */

/***************************** 13.7.3.2 Operations for updating a linked Binary Trees *************************/
/* The Tree and BinaryTree interfaces define a variety of methods for inspecting an existing tree, yet they do not
 * declare any update methods.
 *
 * Although the principle of encapsulation suggests that the outward behaviors of an abstract data type need not
 * depend on the internal representation, the efficiency of the operations depends greatly upon the representation.
 * We therefore prefer to have each concrete implementation of a tree class support the most suitable behaviors for
 * updating a tree. In the case of a linked binary tree, we suggest that the following update methods be supported:
 *
 * - addRoot(e): Creates a root for an empty tree, storing e as the element, and returns the position of that root;
 *               an error occurs if the tree is not empty.
 * - addLeft(p, e): Creates a left child of position p, storing element e, and returns the position of the new node;
 *               an error occurs if p already has a left child.
 * - addRight(p, e): Creates a right child of position p, storing element e, and returns the position of the new node;
 *               an error occurs if p already has a right child.
 * - set(p, e): Replaces the element stored at position p with element e, and returns the previously stored element.
 * - attach(p, T1, T2): Attaches the internal structure of trees T1 and T2 as the respective left and right subtrees
 *              of leaf position p and resets T1 and T2 to empty trees; an error condition occurs if p is not a leaf.
 * - remove(p): Removes the node at position p, replacing it with its child (if any), and returns the element that had
 *              been stored at p; an error occurs if p has two children.
 *
 * We have specifically chosen this collection of operations because each can be implemented in O(1) worst-case time
 * with our linked representation. The most complex of these are attach and remove, due to the case analyses involving
 * the various parent-child relationships and boundary conditions, yet there remains only a constant number of
 * operations to perform.
 * */


/************************* 13.7.3.2 Java implementation of a linked Binary Tree structure *************************/

/* We implement our first linked binary tree by extending our MyAbstractBinaryTree class. In the LinkedBinaryTree class,
* we also implemented a nested TreeNode class which implements the MyBinaryTree Interface.
*
* The run time complexcity of the implemented methods are shown below:
* - size, isEmpty -> O(1)
* - root, parent, left, right, sibling, children, numChildren -> O(1)
* - isInternal, isExternal, isRoot -> O(1)
* - addRoot, addLeft, addRight, set, attach, remove -> O(1)
* - depth(p) -> O(dp+1)
* - height -> O(n)
*
* The space usage is O(n). where n is the number of element stored in the binary tree.
* */

/************************* 13.7.3.3 Array Based representation of a binary tree *************************/
/* An alternative representation of a binary tree T is based on a way of numbering the positions of T. For every
 * position p of T, let f (p) be the integer defined as follows:
 * - If p is the root of T, then f(p) = 0.
 * - If p is the left child of position q, then f(p) = 2*f(q)+1.
 * - If p is the right child of position q, then f(p) = 2*f(q)+2.
 *
 * */

/* The numbering function f is known as a level numbering of the positions in a binary tree T, for it numbers the
 * positions on each level of T in increasing order from left to right. Note well that the level numbering is based
 * on potential positions within a tree, not the actual shape of a specific tree, so they are not necessarily
 * consecutive. For example, there will be no nodes with level numbering 13 or 14, if the node with level numbering 6
 * has no children. */

/*********************** 13.7.3.4 Linked structure for general trees *******************************/

/* When representing a binary tree with a linked structure, each node explicitly maintains fields left and right
 * as references to individual children. For a general tree, there is no a priori limit on the number of children
 * that a node may have. A natural way to realize a general tree T as a linked structure is to have each node store
 * a single "container" of references to its children. The container is often an implementation of collection which
 * could be a linked list.
 * */

/* If we use a collection to store the children of each position p, we can implement children(p) by simply iterating
* that collection. The following table summarizes the performance of the implementation of a general tree using a
* linked structure:
* Run time performance:
* - size, isEmpty -> O(1)
* - root, parent, isRoot, isInternal, isExternal -> O(1)
* - numChildren(p) -> O(1)
* - children(p) -> O(cp +1)
* - depth(p) -> O(dp +1)
* - height -> O(n)
*
* We let cp denote the number of children of a position p, and dp its depth. The space usage is O(n)
* */

/************************************************************************************************
 * ****************************** 13.7.4 Tree Traversal Algorithms   **************************
 * **********************************************************************************************/

/* A traversal of a tree T is a systematic way of accessing, or "visiting", all the positions of T. The specific
 * action associated with the “visit” of a position p depends on the application of this traversal, and could involve
 * anything from incrementing a counter to performing some complex computation for p.
 * */

/**************************** 13.7.4.1 Preorder and Postorder Traversals of General Trees ***************************/

/* Preorder traversal
* In a preorder traversal of a tree T, the root of T is visited first and then the sub-trees rooted at its children are
* traversed according to the order of the children. The pseudocode for the preorder traversal of the subtree rooted at
* a position p is shown below:
*
* Algo preorder(p):
*   perform the "visit" aciton for position p  // this happens before any recursion
*   for each child c in children(p) fo
*       preorder(c)  // recursively traverse the subtree rooted at c
*
*  for example, suppose we have the following tree T1
*                      1
*                     / \
*                    2  3
*                  / \  / \
*                 4  5 6  7
*
 *  for example, suppose we have the following tree T2 which represent a arithmetic expression.
 *                            /
 *                        /       \
 *                      *         +
 *                     / \      /  \
 *                   +    3    -    2
 *                  / \       / \
 *                 3  1      9  5
* When we do a preorder travse on T1, we will read the element in order 1,2,4,5,3,6,7
* When we do a preorder travse on T2, we will read the element in order /,*,+,3,1,3,+,-,9,5,2
*
* Postorder traversal
* In postorder traversal, it recursively traverses the subtrees rooted at the children of the root first, and then
* visits the root. The pseudocode for the preorder traversal of the subtree rooted at a position p is shown below:
*
* Algo postorder(p):
*  for each child c in children(p) do
*      postorder(c)  // recursively traverse the subtree rooted at c
*  perform the "visit" action for position p // this happens after any recursion
*
* If we do a postorder traversal on T1, we will read the element in order 4,5,2,6,7,3,1
* If we do a postorder traversal on T2, we will read the element in order 3,1,+,3,*,9,5,-,2,+,/
* Both preorder and postorder traversal algorithms are efficient ways to access all the positions of a tree.
* The analysis of either of these traversal algorithms is similar to that of algorithm height. At each position p,
* the nonrecursive part of the traversal algorithm requires time O(cp+1), where cp is the number of children of p,
* under the assumption that the “visit” itself takes O(1) time. the overall running time for the traversal of tree T
* is O(n), where n is the number of positions in the tree. This running time is asymptotically optimal since the
* traversal must visit all n positions of the tree.
* */

/**************************** 13.7.4.2 Breadth first tree Traversals of General Trees ***************************/

/* Although the preorder and postorder traversals are common ways of visiting the positions of a tree, another approach
 * is to traverse a tree so that we visit all the postions at depth d before we visit the position d+1. Such an
 * algorithm is known as a breadth-first traversal.
 *
 * Below code is the Pseudocode for a breadth-first traversal. The process is not recursive, since we are not
 * traversing entire subtrees at once. We use a queue to produce a FIFO (i.e., first-in first-out) semantics for the
 * order in which we visit nodes. The overall running time is O(n), due to the n calls to enqueue and n calls to dequeue.
 *
 * Algorithm breadthfirst( ):
 *   Initialize queue Q to contain root( )
 *   while Q not empty do
 *     p = Q.dequeue( ) { p is the oldest entry in the queue }
 *     perform the “visit” action for position p
 *     for each child c in children(p) do
 *       Q.enqueue(c) { add p’s children to the end of the queue for later visits }
 * */

/**************************** 13.7.4.3 Inorder Traversal of a Binary Tree ***************************/

/* The standard preorder, postorder, and breadth-first traversals that were introduced for general trees can be
 * directly applied to binary trees.
 *
 * In binary trees, we can have more efficient algo to do the tree traversal, "inorder traversal" is one of them. In
 * an inorder traversal, we visit a position between the recursive traversals of its left and right subtrees. The
 * inorder traversal of a binary tree T can be informally viewed as visiting the nodes of T "from left to right".
 *
 * For example, if we do an Inorder traversal on T1, we will read the element in order 4,2,5,1,6,3,7.
 *
 * Below code is the Pseudocode for an inorder traversal.
 * Algorithm inorder(p):
 *   if p has a left child lc then
 *     inorder(lc) { recursively traverse the left subtree of p }
 *   perform the “visit” action for position p
 *   if p has a right child rc then
 *     inorder(rc) { recursively traverse the right subtree of p }
 *
 * Inorder traversal applications:
 * The inorder traversal algorithm has several important applications. When using a binary tree to represent an
 * arithmetic expression, as in T2, the inorder traversal visits positions in a consistent order with the standard
 * representation of the expression, as in 3+1*3/9−5+2 (albeit without parentheses).
 *
 * Binary Search Trees:
 * An important application of the inorder traversal algorithm arises when we store an ordered sequence of elements
 * in a binary tree, defining a structure we call a binary search tree. Let S be a set whose unique elements have an
 * order relation. For example, S could be a set of integers. A binary search tree for S is a proper binary tree T such
 * that, for each internal position p of T:
 * - Position p stores an element of S, denoted as e(p).
 * - Elements stored in the left subtree of p (if any) are less than e(p).
 * - Elements stored in the right subtree of p (if any) are greater than e(p).
 *
 * The above properties assure that an inorder traversal of a binary search tree T visits the elements in non decreasing
 * order.
 *
 * For example, if we want to transform T1 to a binary Search Tree T3. The below map is the T3.
 *                      4
 *                     / \
 *                    3   5
 *                  / \  / \
 *                 1  2 6  7
 *
 * We can use a search tree T for set S to find whether a given search value v is in S, by traversing a path down the
 * tree T, starting at the root. At each internal position p encountered, we compare our search value v with the
 * element e(p) stored at p. If v < e(p), then the search continues in the left subtree of p. If v = e(p), then the
 * search terminates successfully. If v > e(p), then the search continues in the right subtree of p. Finally, if we
 * reach a leaf, the search terminates unsuccessfully.
 * */

/**************************** 13.7.4.4 Implementing Tree Traversals in Java ***************************/

/* In section 13.7.3.2, we defined the ADT of tree and implemented LinkedBinaryTree, we stated that tree T must include
 * the following supporting methods:
  * -iterator(): Returns an iterator for all elements in the tree.
  * -positions(): Returns an iterable collection of all positions of the tree.
  *
  * At that time, we did not make any assumption about the order in which these iterations report their results. In this
  * section, we will demonstrate how any of the tree traversal algorithms we have introduced before. As these
  * implementations can be reused in any tree implementations, we will implement the iterator() and positions() method
  * in the MyAbstractTree(Preorder, Postorder, Breadth-first) or MyAbstractBinaryTree(Inorder traversal). For the
  * simplicity of this lesson, all implementations will be written in MyAbstractBinaryTree.
  *
  * First, we note that an iteration of all elements of a tree can easily be produced if we have an iteration of all
  * positions of that tree. In MyAbstractTree, we add an nested ElementIterator class named "ElementIterator", it adapts
  * the iteration produced by positions() to return elements.
  *
  * So the core logic is implemented by the positions() method, we have a choice of tree traversal algorithms. We will
  * provide the implementation of the four algorithms in the abstract class, and allow user to chose when they extend
  * the abstract class by overwriting the positions() method. For example, if a user want to use preorder traverse, he
  * can overwrite like following code.
  * public Iterable<TreePosition<E>> positions() {return preorder();}
  *
  * Implementing preorder tree traversal
  * The objective is to provide a public method preorder(), as part of the MyAbstractBinaryTree. It returns an iterable
  * container of the positions of the tree in preorder. We begin by defining a private utility method, preorderSubtree,
  * which allows us to parameterize the recursive process with a specific position of the tree that serves as the root
  * of a subtree to traverse. (We also pass a list as a parameter that serves as a buffer to which “visited” positions
  * are added.)
  *
  * Implementing preorder tree traversal
  * The post order algorithm is very similar to the preorder, only one difference, in postorder, we add(p) into the
  * snapshot after we visit the subtree of p.
  *
  * Implementing breadth-first traversal
  * The breadth-first traversal algorithm is not recursive; it relies on a queue of positions to manage the traversal
  * process. We will use the SinglyLinkedListBasedQueue of Lesson 13, section 4, although any implementation of the
  * queue ADT would suffice.
  *
  * Implementing inorder traversal for binary trees.
  * Unlike preorder, postorder, breadth-fist, the inorder algorithm explicitly relies on the notion of a left and right
  * child of a node, which only applies to binary trees. So it must be implemented in MyAbstractBinaryTree
  *
  *
  * */

/************************************************************************************************
 * ****************************** 13.7.5 Application of Tree Traversal   **************************
 * **********************************************************************************************/

/* In this section, we demonstrate several representative applications of tree traversals, including some
 * customizations of the standard traversal algorithms.
 *
 * */

/****************** 13.7.5.1 Application 1:  Table of Contents  *********************/
/* When we use tree to represent the hierarchical structure of a document, a preorder traversal of the tree can be used
* to produce a table of contents for the document. For example,
*                                           Paper
*                                   /     |        |       \
*                               Title  Abstract  Chap1    Chap2
*                                                 /  \     /  \
*                                              Sec1 Sec2 Sec1 Sec2
*
*  A preorder traverse will go give the following result
*  Paper
*     Title
*     Abstract
*     Chap1
*       Sec1
*       Sec2
*     Chap2
*       Sec1
*       Sec2
*
* To Prints preorder representation of subtree of T rooted at p having depth d.
      public static <E> void printPreorderIndent(MyTree<E> T, TreePosition<E> p, int d) {
        System.out.println(spaces(2 * d) + p.getElement()); // indent based on d
        for (TreePosition<E> c : T.children(p))
            printPreorderIndent(T, c, d + 1); // child depth is d+1
    }

* We suppose that method spaces(n), produces a string of n spaces
*
* To make the table of contents more beautiful, we can add numbers as in the following example (E1).
* Electronics R’Us
* 1 R&D
* 2 Sales
*   2.1 Domestic
*   2.2 International
*       2.2.1 Canada
*       2.2.2 S. America
*
* The following code prints labeled representation of subtree of T rooted at p having depth d.
        public static <E> void printPreorderLabeled(MyTree<E> T, TreePosition<E> p, ArrayList<Integer> path) {
            int d = path.size(); // depth equals the length of the path
            System.out.print(spaces(2 * d)); // print indentation, then label
            for (int j = 0; j < d; j++) System.out.print(path.get(j) + (j == d - 1 ? " " : "."));
            System.out.println(p.getElement());
            path.add(1); // add path entry for first child
            for (TreePosition<E> c : T.children(p)) {
                printPreorderLabeled(T, c, path);
                path.set(d, 1 + path.get(d)); // increment last entry of path
            }
            path.remove(d); // restore path to its incoming state
        }
* */

/************************** 13.7.5.2 Application 2:  Computing disk space  *********************************/

/* When we use tree as a model for a file-system structure, internal nodes represents directories, external nodes
* represents files. The recursive computation of disk space is emblematic of a postorder traversal as we can not
* effectively compute the total space used by a directory until after we know the space that is used by its children
* directories and files. The following code shows how we calculate the disk usage
*
*  Returns total disk space for subtree of T rooted at p.
        public static int diskSpace(MyTree<Integer> T, TreePosition<Integer> p) {
            int subtotal = p.getElement(); // we assume element represents space usage
            for (TreePosition<Integer> c : T.children(p))
                subtotal += diskSpace(T, c);
            return subtotal;
        }
* */
/******************** 13.7.5.3 Application 3:  Parenthetic Representation of a tree  ****************************/

/* It is not possible to reconstruct a general tree, given only the preorder sequence of elements, we also need to
* know the hierarchy relations between elements. The use of indentation or numbered labels provides such information.
* However, there are more concise string representations of trees that are computer-friendly.
*
* In this section, we explore one such representation. The parenthetic string representation P(T) of tree T is
* recursively defined. If T consists of a single position p, then P(T)= p.getElement(). Otherwise, it is defined
* recursively as, P(T)=p.getElement()+"("+P(T1)+", "+ ··· +", "+P(Tk)+")", where T1,...Tk are the subtree of position p.
*
* Electronics R’Us (R&D, Sales (Domestic, International (Canada,S. America))) is an parenthetic representation of a tree,
* which represents the same tree as in E1.
*
* The below code shows how to print a tree in parenthetic representation
* Prints parenthesized representation of subtree of T rooted at p.

        public static <E> void parenthesize(MyTree<E> T, TreePosition<E> p) {
            System.out.print(p.getElement());
            if (T.isInternal(p)) {
                boolean firstTime = true;
                for (TreePosition<E> c : T.children(p)) {
                    System.out.print((firstTime ? " (" : ", ")); // determine proper punctuation
                    firstTime = false; // any future passes will get comma
                    parenthesize(T, c); // recur on child
                }
                System.out.print(")");
            }
        }
*
* */

/******************** 13.7.5.4 Application 4:  Using Inorder Traversal for Tree Drawing  **************************/

/* An inorder traversal can be applied to the problem of computing a graphical layout of a binary tree. We assume the
 * convention, common to computer graphics, that x-coordinates increase left to right and y-coordinates increase top
 * to bottom, so that the origin(0,0) is in the upper left corner of the drawing.
 *
 * The geometry is determined by an algorithm that assigns x- and y-coordinates to each position p of a binary tree
 * T using the following two rules:
 * - x(p) is the number of positions visited before p in an inorder traversal of T.
 * - y(p) is the depth of p in T.
 *
 * The following code provides an implementation of a recursive method that assigns x- and y-coordinates to positions
 * of a tree in this manner. Depth information is passed from one level of the recursion to another, as done in our
 * earlier example for indentation. To maintain an accurate value for the x-coordinate as the traversal proceeds,
 * the method must be provided with the value of x that should be assigned to the leftmost node of the current subtree,
 * and it must return to its parent a revised value of x that is appropriate for the first node drawn to the right of
 * the subtree.
 *
 * public static <E> int layout(MyBinaryTree<E> T, TreePosition<E> p, int d, int x) {
        if (T.left(p) != null)
            x = layout(T, T.left(p), d + 1, x); // resulting x will be increased
        p.getElement().setX(x++); // post-increment x
        p.getElement().setY(d);
        if (T.right(p) != null)
            x = layout(T, T.right(p), d + 1, x); // resulting x will be increased
        return x;
    }
   */

/************************************************************************************************
 * ************************************** 13.7.6  Euler Tours   *********************************
 * **********************************************************************************************/

/* The various applications described in Section 13.7.5 demonstrate the great power of recursive tree traversals,
* but they also show that not every application strictly fits the mold of a preorder, postorder, or inorder traversal.
* We can unify the tree-traversal algorithms into a single framework known as an Euler tour traversal. The Euler tour
* traversal of a tree T can be informally defined as a “walk” around T, where we start by going from the root toward
* its leftmost child, viewing the edges of T as being “walls” that we always keep to our left.
*
* The complexity of the walk is O(n), for a tree with n nodes, because it progresses exactly two times along each of
* the n−1 edges of the tree—once going downward along the edge, and later going upward along the edge. To unify the
* concept of preorder and postorder traversals, we can view there being two notable “visits” to each position p:
* - A “pre visit” occurs when first reaching the position, that is, when the walk passes immediately left of the node
*   in our visualization.
* - A “post visit” occurs when the walk later proceeds upward from that position, that is, when the walk passes to
*   the right of the node in our visualization.
*
* The process of an Euler tour can be naturally viewed as recursive. In between the “pre visit” and “post visit” of
* a given position will be a recursive tour of each of its subtrees.
*
* In the special case of a binary tree, we can designate the time when the walk passes immediately below a node as an
* “in visit” event. This will be just after the tour of its left subtree (if any), but before the tour of its right
* subtree (if any).
*
* The pseudocode for an Euler tour traversal of a subtree rooted at a position p is shown below:
* Algorithm eulerTour(T, p):
*    perform the “pre visit” action for position p
*    for each child c in T.children(p) do
*        eulerTour(T, c) { recursively tour the subtree rooted at c }
*    perform the “post visit” action for position p
*
* The Euler tour traversal extends the preorder and postorder traversals, but it can also perform other kinds of
* traversals. For example, suppose we wish to compute the number of descendants of each position p in an n-node binary
* tree. We start an Euler tour by initializing a counter to 0, and then increment the counter during the “pre visit”
* for each position. To determine the number of descendants of a position p, we compute the difference between the
* values of the counter from when the pre-visit occurs and when the post-visit occurs, and add 1 (for p). This simple
* rule gives us the number of descendants of p, because each node in the subtree rooted at p is counted between p’s
* visit on the left and p’s visit on the right. Therefore, we have an O(n)-time method for computing the number of
* descendants of each node.
*
* For the case of a binary tree, we can customize the algorithm to include an explicit “in visit” action. Below is the
* Algorithm eulerTourBinary for performing an Euler tour traversal of a subtree rooted at position p of a binary tree.
* Algorithm eulerTourBinary(T, p):
* perform the “pre visit” action for position p
* if p has a left child lc then
*    eulerTourBinary(T, lc) { recursively tour the left subtree of p }
* perform the “in visit” action for position p
* if p has a right child rc then
*    eulerTourBinary(T, rc) { recursively tour the right subtree of p }
* perform the “post visit” action for position p
*
* tree T2 which represent a arithmetic expression.
 *                            /
 *                        /       \
 *                      *         +
 *                     / \      /  \
 *                   +    3    -    2
 *                  / \       / \
 *                 3  1      9  5
* For example, a binary Euler tour can produce a traditional parenthesized arithmetic expression, such as
* "((((3+1)*3)/((9-5)+2)))" for the tree T2, as follows:
 * - “Pre visit” action: if the position is internal, print “(”.
 * - “In visit” action: print the value or operator stored at the position.
 * - “Post visit” action: if the position is internal, print “)”.
*
* */

    }
}
