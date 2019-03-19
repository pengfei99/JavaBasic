package org.pengfei.Lesson13_Common_Data_Structure.L13_S7_Trees;

import org.pengfei.Lesson13_Common_Data_Structure.L13_S4_Queues.SinglyLinkedListBasedQueue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class MyAbstractBinaryTree<E> extends MyAbstractTree<E> implements MyBinaryTree<E> {
    /**
     * Returns the TreePosition of p's sibling (or null if no sibling exists).
     */
    public TreePosition<E> sibling(TreePosition<E> p) {
        TreePosition<E> parent = parent(p);
        if (parent == null) return null; // p must be the root
        if (p == left(parent)) // p is a left child
            return right(parent); // (right child might be null)
        else // p is a right child
            return left(parent); // (left child might be null)
    }

    /**
     * Returns the number of children of TreePosition p.
     */
    public int numChildren(TreePosition<E> p) {
        int count = 0;
        if (left(p) != null)
            count++;
        if (right(p) != null)
            count++;
        return count;
    }

    /**
     * Returns an iterable collection of the TreePositions representing p's children.
     */
    public Iterable<TreePosition<E>> children(TreePosition<E> p) {
        List<TreePosition<E>> snapshot = new ArrayList<>(2); // max capacity of 2
        if (left(p) != null)
            snapshot.add(left(p));
        if (right(p) != null)
            snapshot.add(right(p));
        return snapshot;
    }

    /*****************************************************************************************************************
     * **********************************Iterator implementations***************************************************
     * *************************************************************************************************************/

    //---------------- nested ElementIterator class ----------------
    /* This class adapts the iteration produced by positions() to return elements. */
    private class ElementIterator implements Iterator<E> {
        // MyAbstractTree.this calls a current MyAbstractTree(enclosing class) instance which nest the ElementIterator class.
        // positions().iterator method returns an iterator of the treePosition
        Iterator<TreePosition<E>> posIterator = MyAbstractBinaryTree.this.positions().iterator();

        public boolean hasNext() {
            return posIterator.hasNext();
        }

        public E next() {
            return posIterator.next().getElement();
        } // return element!

        public void remove() {
            posIterator.remove();
        }
    }

    /**
     * Returns an iterator of the elements stored in the tree.
     */
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    /**************************** Implements the preorder tree traversal **************************************/

    /**
     * Adds positions of the subtree rooted at Position p to the given snapshot.
     */
    private void preorderSubtree(TreePosition<E> p, List<TreePosition<E>> snapshot) {
        snapshot.add(p); // for preorder, we add position p before exploring subtrees
        for (TreePosition<E> c : children(p))
            preorderSubtree(c, snapshot);
    }

    /**
     * Returns an iterable collection of positions of the tree, reported in preorder.
     */
    public Iterable<TreePosition<E>> preorder() {
        List<TreePosition<E>> snapshot = new ArrayList<>();
        if (!isEmpty())
            preorderSubtree(root(), snapshot); // fill the snapshot recursively
        return snapshot;
    }

    /*************************** Implements the postorder tree traversal *************************************/
    /**
     * Adds positions of the subtree rooted at Position p to the given snapshot.
     */
    private void postorderSubtree(TreePosition<E> p, List<TreePosition<E>> snapshot) {
        for (TreePosition<E> c : children(p))
            postorderSubtree(c, snapshot);
        snapshot.add(p); // for postorder, we add position p after exploring subtrees
    }

    /**
     * Returns an iterable collection of positions of the tree, reported in postorder.
     */
    public Iterable<TreePosition<E>> postorder() {
        List<TreePosition<E>> snapshot = new ArrayList<>();
        if (!isEmpty())
            postorderSubtree(root(), snapshot); // fill the snapshot recursively
        return snapshot;
    }

    /************************* Implements the breadth first tree traversal **********************************/
    /**
     * Returns an iterable collection of positions of the tree in breadth-first order.
     */
    public Iterable<TreePosition<E>> breadthfirst() {
        List<TreePosition<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            SinglyLinkedListBasedQueue<TreePosition<E>> fringe = new SinglyLinkedListBasedQueue<>();
            fringe.enqueue(root()); // start with the root
            while (!fringe.isEmpty()) {
                TreePosition<E> p = fringe.dequeue(); // remove from front of the queue
                snapshot.add(p); // report this position
                for (TreePosition<E> c : children(p))
                    fringe.enqueue(c); // add children to back of queue
            }
        }
        return snapshot;
    }

    /************************* Implements the inorder tree traversal **********************************/
    /**
     * Adds positions of the subtree rooted at Position p to the given snapshot.
     */
    private void inorderSubtree(TreePosition<E> p, List<TreePosition<E>> snapshot) {
        if (left(p) != null)
            inorderSubtree(left(p), snapshot);
        snapshot.add(p);
        if (right(p) != null)
            inorderSubtree(right(p), snapshot);
    }

    /**
     * Returns an iterable collection of positions of the tree, reported in inorder.
     */
    public Iterable<TreePosition<E>> inorder() {
        List<TreePosition<E>> snapshot = new ArrayList<>();
        if (!isEmpty())
            inorderSubtree(root(), snapshot); // fill the snapshot recursively
        return snapshot;
    }

}
