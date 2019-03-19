package org.pengfei.Lesson13_Common_Data_Structure.L13_S7_Trees;



public interface TreePosition<E> {
    /*private E element;
    private TreePosition<E> parent;
    private java.util.List<TreePosition> children;

    public TreePosition(E element, TreePosition parent, List<TreePosition> children){
        this.element=element;
        this.parent=parent;
        this.children=children;
    }*/

/* Accessor methods */
   public E getElement();
   public TreePosition<E> getParent();
   public TreePosition<E> getLeft();
   public TreePosition<E> getRight();

/* Update methods */
    public void setElement(E e);
    public void setParent(TreePosition<E> newParent) throws IllegalArgumentException;
    public void setLeft(TreePosition<E> newLeft) throws IllegalArgumentException;
    public void setRight(TreePosition<E> newRight) throws IllegalArgumentException;
}
