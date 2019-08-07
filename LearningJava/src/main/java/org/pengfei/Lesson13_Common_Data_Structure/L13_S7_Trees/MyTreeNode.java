package org.pengfei.Lesson13_Common_Data_Structure.L13_S7_Trees;

public class MyTreeNode<E> implements TreePosition<E> {

    private E element;
    private MyTreeNode<E> parent;
    private MyTreeNode<E> left;
    private MyTreeNode<E> right;

    public MyTreeNode(E e, MyTreeNode<E> parent, MyTreeNode<E> left, MyTreeNode<E> right) {
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
    public MyTreeNode<E> getParent() {
        return this.parent;
    }

    @Override
    public MyTreeNode<E> getLeft() {
        return this.left;
    }

    @Override
    public MyTreeNode<E> getRight() {
        return this.right;
    }

    @Override
    public void setElement(E e) {
        this.element = e;
    }

    public void setParent(TreePosition<E> newParent) throws IllegalArgumentException {
        if (newParent instanceof MyTreeNode) {
            this.parent = (MyTreeNode<E>) newParent;
        } else throw new IllegalArgumentException("The parent you provide is not a valid MyTreeNode of the tree");

    }

    @Override
    public void setLeft(TreePosition<E> newLeft) throws IllegalArgumentException {
        if (newLeft instanceof MyTreeNode) this.left = (MyTreeNode<E>) newLeft;
        else throw new IllegalArgumentException("The left child you provide is not a valid MyTreeNode of the tree");
    }

    @Override
    public void setRight(TreePosition<E> newRight) throws IllegalArgumentException {
        if (newRight instanceof MyTreeNode) this.right = (MyTreeNode<E>) newRight;
        else throw new IllegalArgumentException("The right child you provide is not a valid MyTreeNode of the tree");

    }
}
