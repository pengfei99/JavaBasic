package org.pengfei.Lesson13_Common_Data_Structure.L13_S7_Trees;

import java.util.Iterator;

public interface MyTree<E> extends Iterable<E> {
    TreePosition<E> root();

    TreePosition<E> parent(TreePosition<E> p) throws IllegalArgumentException;

    Iterable<TreePosition<E>> children(TreePosition<E> p) throws IllegalArgumentException;

    int numChildren(TreePosition<E> p) throws IllegalArgumentException;

    boolean isInternal(TreePosition<E> p) throws IllegalArgumentException;

    boolean isExternal(TreePosition<E> p) throws IllegalArgumentException;

    boolean isRoot(TreePosition<E> p) throws IllegalArgumentException;

    int size();

    boolean isEmpty();

    Iterator<E> iterator();

    Iterable<TreePosition<E>> positions();
}
