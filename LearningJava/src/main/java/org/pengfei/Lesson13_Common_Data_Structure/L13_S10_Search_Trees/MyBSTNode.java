package org.pengfei.Lesson13_Common_Data_Structure.L13_S10_Search_Trees;


import org.pengfei.Lesson13_Common_Data_Structure.L13_S7_Trees.MyTreeNode;

public class MyBSTNode<E> extends MyTreeNode<E> {
    int aux=0;
    MyBSTNode(E e, MyTreeNode<E> parent, MyTreeNode<E> leftChild, MyTreeNode<E> rightChild) {
        super(e, parent, leftChild, rightChild);
    }
    public int getAux() { return aux; }
    public void setAux(int value) { aux = value; }
}

