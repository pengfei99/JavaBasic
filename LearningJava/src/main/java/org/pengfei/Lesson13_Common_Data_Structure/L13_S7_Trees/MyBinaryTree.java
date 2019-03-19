package org.pengfei.Lesson13_Common_Data_Structure.L13_S7_Trees;

public interface MyBinaryTree<E> extends MyTree<E> {
    /**
     * Returns the TreePosition of p's left child (or null if no child exists).
     */
    TreePosition<E> left(TreePosition<E> p) throws IllegalArgumentException;

    /**
     * Returns the TreePosition of p's right child (or null if no child exists).
     */
    TreePosition<E> right(TreePosition<E> p) throws IllegalArgumentException;

    /**
     * Returns the TreePosition of p's sibling (or null if no sibling exists).
     */
    TreePosition<E> sibling(TreePosition<E> p) throws IllegalArgumentException;

}
