package org.pengfei.Lesson13_Common_Data_Structure.L13_S10_Search_Trees;

import org.pengfei.Lesson13_Common_Data_Structure.L13_S7_Trees.LinkedBinaryTree;
import org.pengfei.Lesson13_Common_Data_Structure.L13_S7_Trees.MyTreeNode;
import org.pengfei.Lesson13_Common_Data_Structure.L13_S7_Trees.TreePosition;

import java.util.Map;

/**
 * A specialized version of the LinkedBinaryTree class with
 * additional mutators to support binary search tree operations, and
 * a specialized node class that includes an auxiliary instance
 * variable for balancing data.
 */
public class MyBalanceableBinaryTree<K,V> extends LinkedBinaryTree<Map.Entry<K,V>> {

    // positional-based methods related to aux field
    public int getAux(TreePosition<Map.Entry<K,V>> p) {
        return ((MyBSTNode<Map.Entry<K,V>>) p).getAux();
    }

    public void setAux(TreePosition<Map.Entry<K,V>> p, int value) {
        ((MyBSTNode<Map.Entry<K,V>>) p).setAux(value);
    }

    // Override node factory function to produce a BSTNode (rather than a Node)

    protected MyTreeNode<Map.Entry<K,V>> createNode(Map.Entry<K,V> e, MyTreeNode<Map.Entry<K,V>> parent,
                                                    MyTreeNode<Map.Entry<K,V>> left, MyTreeNode<Map.Entry<K,V>> right) {
        return new MyBSTNode<>(e, parent, left, right);
    }

    /** Relinks a parent node with its oriented child node. */
    private void relink(MyTreeNode<Map.Entry<K,V>> parent, MyTreeNode<Map.Entry<K,V>> child,
                        boolean makeLeftChild) {
        child.setParent(parent);
        if (makeLeftChild)
            parent.setLeft(child);
        else
            parent.setRight(child);
    }

    /**
     * Rotates Position p above its parent.  Switches between these
     * configurations, depending on whether p is a or p is b.
     *<pre>
     *          b                  a
     *         / \                / \
     *        a  t2             t0   b
     *       / \                    / \
     *      t0  t1                 t1  t2
     *</pre>
     *  Caller should ensure that p is not the root.
     */
    public void rotate(TreePosition<Map.Entry<K,V>> p) {
        MyTreeNode<Map.Entry<K,V>> x = validate(p);
        MyTreeNode<Map.Entry<K,V>> y = x.getParent();        // we assume this exists
        MyTreeNode<Map.Entry<K,V>> z = y.getParent();        // grandparent (possibly null)
        if (z == null) {
            root = x;                                // x becomes root of the tree
            x.setParent(null);
        } else
            relink(z, x, y == z.getLeft());          // x becomes direct child of z
        // now rotate x and y, including transfer of middle subtree
        if (x == y.getLeft()) {
            relink(y, x.getRight(), true);           // x's right child becomes y's left
            relink(x, y, false);                     // y becomes x's right child
        } else {
            relink(y, x.getLeft(), false);           // x's left child becomes y's right
            relink(x, y, true);                      // y becomes left child of x
        }
    }

    /**
     *
     * Returns the Position that becomes the root of the restructured subtree.
     *
     * Assumes the nodes are in one of the following configurations:
     *<pre>
     *     z=a                 z=c           z=a               z=c
     *    /  \                /  \          /  \              /  \
     *   t0  y=b             y=b  t3       t0   y=c          y=a  t3
     *      /  \            /  \               /  \         /  \
     *     t1  x=c         x=a  t2            x=b  t3      t0   x=b
     *        /  \        /  \               /  \              /  \
     *       t2  t3      t0  t1             t1  t2            t1  t2
     *</pre>
     * The subtree will be restructured so that the node with key b becomes its root.
     *<pre>
     *           b
     *         /   \
     *       a       c
     *      / \     / \
     *     t0  t1  t2  t3
     *</pre>
     * Caller should ensure that x has a grandparent.
     */
    public TreePosition<Map.Entry<K,V>> restructure(TreePosition<Map.Entry<K,V>> x) {
        TreePosition<Map.Entry<K,V>> y = parent(x);
        TreePosition<Map.Entry<K,V>> z = parent(y);
        if ((x == right(y)) == (y == right(z))) {   // matching alignments
            rotate(y);                                // single rotation (of y)
            return y;                                 // y is new subtree root
        } else {                                    // opposite alignments
            rotate(x);                                // double rotation (of x)
            rotate(x);
            return x;                                 // x is new subtree root
        }
    }

    /** Prints textual representation of tree structure (for debug purpose only). */
    protected void dump() {
        dumpRecurse(root(), 0);
    }

    /** This exists for debugging only */
    private void dumpRecurse(TreePosition<Map.Entry<K,V>> p, int depth) {
        String indent = (depth == 0 ? "" : String.format("%" + (2*depth) + "s", ""));
        if (p==null)
            System.out.println(indent + "leaf");
        else {
            System.out.println(indent + p.getElement());
            dumpRecurse(left(p), depth+1);
            dumpRecurse(right(p), depth+1);
        }
    }
}
