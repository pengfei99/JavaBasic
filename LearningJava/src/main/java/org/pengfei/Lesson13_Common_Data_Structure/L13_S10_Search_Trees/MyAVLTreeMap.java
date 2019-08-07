package org.pengfei.Lesson13_Common_Data_Structure.L13_S10_Search_Trees;

import org.pengfei.Lesson13_Common_Data_Structure.L13_S7_Trees.TreePosition;

import java.util.Comparator;
import java.util.Map;

public class MyAVLTreeMap<K,V> extends MyTreeMap<K,V>{
    /** Constructs an empty map using the natural ordering of keys.*/
    public MyAVLTreeMap(){
        super();
    }
    /** Constructs an empty map using the given comparator to order keys. */
    public MyAVLTreeMap(Comparator<K> comp){
        super(comp);
    }

    /** Returns the height of the given tree position*/
    protected int height(TreePosition<Map.Entry<K,V>> p){
        return tree.getAux(p);
    }

    /** Recomputes the height of the given position based on its children's heights*/
    protected void recomputeHeight(TreePosition<Map.Entry<K,V>> p){
        tree.setAux(p,1+Math.max(height(left(p)),height(right(p))));
    }

    /** Returns whether a position has balance factor between -1 and 1 inclusive.*/
    protected boolean isBalanced(TreePosition<Map.Entry<K,V>> p){
        return Math.abs(height(left(p))-height(right(p)))<=1;
    }

    /** Returns a child of p with height no smaller than that of the other child.*/
    protected TreePosition<Map.Entry<K,V>> tallerChild(TreePosition<Map.Entry<K,V>> p){
         if (height(left(p))>height(right(p))) return left(p);
         else if (height(left(p))<height(right(p))) return right(p);
         // the height of left and right is the same
         else {
             // if p is root
             if (isRoot(p)) return left(p);
             // if p is not root, and p is the left child of its parent
             else if (p==left(parent(p))) return left(p);
             // if p is not root, and p is the right child of its parent
             else return right(p);
         }
    }

    /**
     * Utility used to rebalance after an insert or removal operation. This traverses the
     * path upward from p, performing a trinode restructuring when imbalance is found,
     * continuing until balance is restored.
     *
     * The argument p is the node which we inserted or deleted in the tree.
     */
protected void rebalance(TreePosition<Map.Entry<K,V>> p) {
    int oldHeight, newHeight;
         do {
              oldHeight = height(p); // not yet recalculated if internal
            if (!isBalanced(p)) { // imbalance detected
                // perform trinode restructuring, setting p to resulting root,
                // and recompute new local heights after the restructuring
                 p = restructure(tallerChild(tallerChild(p)));
                 recomputeHeight(left(p));
                 recomputeHeight(right(p));
                 }
             recomputeHeight(p);
             newHeight = height(p);
             p = parent(p);
             } while (oldHeight != newHeight && p != null);
         }


    /** Overrides the empty rebalanceInsert method of the MyTreeMap, this will trigger the rebalancing hook that
     * is called after an insertion(expandExternal). */
         protected void rebalanceInsert(TreePosition<Map.Entry<K,V>> p){
           // if(!isRoot(p)) {
                rebalance(p);
            //}
           //System.out.println("is Root");
         }

         /** Overrides the TreeMap rebalancing hook that is called after a deletion, p is the node which we will delete*/
         protected void rebalanceDelete(TreePosition<Map.Entry<K,V>> p){
             if(!isRoot(p)) rebalance(p);
         }

}
