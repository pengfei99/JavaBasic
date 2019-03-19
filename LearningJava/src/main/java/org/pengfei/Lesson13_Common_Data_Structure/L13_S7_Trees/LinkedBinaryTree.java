package org.pengfei.Lesson13_Common_Data_Structure.L13_S7_Trees;


import java.util.Comparator;

public class LinkedBinaryTree<E> extends MyAbstractBinaryTree<E> {

    /**
     * Factory function to create a new node storing element e.
     */
    protected TreeNode<E> createNode(E e, TreeNode<E> parent,
                                     TreeNode<E> left, TreeNode<E> right) {
        return new TreeNode<E>(e, parent, left, right);
    }

    protected TreeNode<E> root = null;
    private int size = 0;

    // constructor
    public LinkedBinaryTree() {
    } //constructs an empty binary tree

    // nonpublic utility
    /* validates the position and returns it as a node. */
    protected TreeNode<E> validate(TreePosition<E> p) throws IllegalArgumentException {
        if (!(p instanceof TreeNode))
            throw new IllegalArgumentException("Not valid position type");
        TreeNode<E> node = (TreeNode<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    /****************************************Get function ******************************************/
    public int size() {
        return size;
    }

    //method iterator which returns an iterator of the element stored in the tree is implemented in the abstract class
    //MyAbstractBinaryTree

    @Override
    public Iterable<TreePosition<E>> positions() {
        /* As we implemented four different tree traversals algorithm, here we can choose one which suits better our
         * needs. Below, we choose inorder traverse, because it's the most nature way to traverse a binary tree. */
        return inorder();
    }

    public TreePosition<E> root() {
        return root;
    }

    /**
     * Returns the Position of p's parent (or null if p is root).
     */
    public TreePosition<E> parent(TreePosition<E> p) throws IllegalArgumentException {
        TreeNode<E> node = validate(p);
        return node.getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     */
    public TreePosition<E> left(TreePosition<E> p) throws IllegalArgumentException {
        TreeNode<E> node = validate(p);
        return node.getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     */
    public TreePosition<E> right(TreePosition<E> p) throws IllegalArgumentException {
        TreeNode<E> node = validate(p);
        return node.getRight();
    }

    /******************************** Update methods *************************************************/
    /**
     * Places element e at the root of an empty tree and returns its new Position.
     */
    public TreePosition<E> addRoot(E e) throws IllegalStateException {
        if (!this.isEmpty()) throw new IllegalStateException("Tree is not empty");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }

    /**
     * Creates a new left child of Position p storing element e; return its Position.
     */
    public TreePosition<E> addLeft(TreePosition<E> p, E e) throws IllegalArgumentException {
        TreeNode<E> parentNode = validate(p);
        if (parentNode.getLeft() != null)
            throw new IllegalArgumentException("The giving position already has a left child");
        TreeNode<E> leftChild = createNode(e, parentNode, null, null);
        parentNode.setLeft(leftChild);
        size++;
        return leftChild;
    }

    /**
     * Creates a new right child of Position p storing element e; return its Position.
     */
    public TreePosition<E> addRight(TreePosition<E> p, E e) throws IllegalArgumentException {
        TreeNode<E> parentNode = validate(p);
        if (parentNode.getRight() != null)
            throw new IllegalArgumentException("The giving position already has a right child");
        TreeNode<E> rightChild = createNode(e, parentNode, null, null);
        parentNode.setRight(rightChild);
        size++;
        return rightChild;
    }

    /**
     * Replaces the element at Position p with e and returns the replaced element.
     */
    public E set(TreePosition<E> p, E e) throws IllegalArgumentException {
        TreeNode<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    /**
     * Attaches trees t1 and t2 as left and right subtrees of external p.
     */
    public void attach(TreePosition<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        TreeNode<E> parentNode = validate(p);
        if (isInternal(p)) throw new IllegalArgumentException("p must be a leaf");
        size += t1.size() + t2.size();
        // attach t1 as left subtree of the parent node
        if (!t1.isEmpty()) {
            t1.root.setParent(parentNode);
            parentNode.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }
        // attach t2 as right subtree of the parent node
        if (!t2.isEmpty()) {
            t2.root.setParent(parentNode);
            parentNode.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }

    /**
     * Removes the node at Position p and replaces it with its child, if any. We can't remove a position which has two
     * children
     */
    public E remove(TreePosition<E> p) throws IllegalArgumentException {
        TreeNode<E> currentNode = validate(p);
        //check p's children number
        if (numChildren(p) == 2) throw new IllegalArgumentException("p has two children");
        // get child if p has one
        TreeNode<E> childNode = (currentNode.getLeft() != null ? currentNode.getLeft() : currentNode.getRight());

        //child node is not null, the parent becomes the new parent of the child node
        if (childNode != null) childNode.setParent(currentNode.getParent());

        // if current node is root, after remove, child becomes root
        if (currentNode == root) root = childNode;
        else {
            TreeNode<E> parentNode = currentNode.getParent();
            if (currentNode == parentNode.getLeft())
                parentNode.setLeft(childNode);
            else
                parentNode.setRight(childNode);
        }
        size--;
        E temp = currentNode.getElement();
        // help garbage collection
        currentNode.setElement(null);
        currentNode.setLeft(null);
        currentNode.setRight(null);
        // our convention for invalidate treenode
        currentNode.setParent(currentNode);
        return temp;
    }

    /************************************* Nested TreeNode class *****************************************/
    /* We implement the TreePosition with a nested TreeNode class*/

    protected static class TreeNode<E> implements TreePosition<E> {

        private E element;
        private TreeNode<E> parent;
        private TreeNode<E> left;
        private TreeNode<E> right;

        public TreeNode(E e, TreeNode<E> parent, TreeNode<E> left, TreeNode<E> right) {
            this.element = e;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        @Override
        public E getElement() {
            return this.element;
        }

        @Override
        public TreeNode<E> getParent() {
            return this.parent;
        }

        @Override
        public TreeNode<E> getLeft() {
            return this.left;
        }

        @Override
        public TreeNode<E> getRight() {
            return this.right;
        }

        @Override
        public void setElement(E e) {
            this.element = e;
        }

        public void setParent(TreePosition<E> newParent) throws IllegalArgumentException {
            if (newParent instanceof TreeNode) {
                this.parent = (TreeNode<E>) newParent;
            } else throw new IllegalArgumentException("The parent you provide is not a valid TreeNode of the tree");

        }

        @Override
        public void setLeft(TreePosition<E> newLeft) throws IllegalArgumentException {
            if (newLeft instanceof TreeNode) this.left = (TreeNode<E>) newLeft;
            else throw new IllegalArgumentException("The left child you provide is not a valid TreeNode of the tree");
        }

        @Override
        public void setRight(TreePosition<E> newRight) throws IllegalArgumentException {
            if (newRight instanceof TreeNode) this.right = (TreeNode<E>) newRight;
            else throw new IllegalArgumentException("The right child you provide is not a valid TreeNode of the tree");

        }
    }

    /************************************* End Nested TreeNode class *****************************************/




}
