package org.pengfei.Lesson13_Common_Data_Structure.L13_S7_Trees;

import java.util.Iterator;

public abstract class MyAbstractTree<E> implements MyTree<E> {
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean isRoot(TreePosition<E> p) throws IllegalArgumentException {
        return p == root();
    }

    @Override
    public boolean isInternal(TreePosition<E> p) throws IllegalArgumentException {
        return numChildren(p) > 0;
    }

    @Override
    public boolean isExternal(TreePosition<E> p) throws IllegalArgumentException {
        return numChildren(p) == 0;
    }

    public int depth(TreePosition<E> p) {
        if (isRoot(p)) return 0;
        else return depth(parent(p)) + 1;
    }

    private int heightBad() {
        int h = 0;
        //loop over all position of the tree, and find the max depth
        for (TreePosition<E> p : positions()) {
            if (isExternal(p))
                h = Math.max(h, depth(p));
        }
        return h;
    }

    /**
     * This method returns the height of the subtree rooted at Position p.
     */
    public int height(TreePosition<E> p) {
        int h = 0;
        for (TreePosition<E> c : children(p)) {
            h = Math.max(h, 1 + height(c));
        }
        return h;
    }


}
