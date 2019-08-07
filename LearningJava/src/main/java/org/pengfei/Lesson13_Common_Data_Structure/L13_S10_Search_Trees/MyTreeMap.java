package org.pengfei.Lesson13_Common_Data_Structure.L13_S10_Search_Trees;

import org.pengfei.Lesson13_Common_Data_Structure.L13_S7_Trees.TreePosition;
import org.pengfei.Lesson13_Common_Data_Structure.L13_S9_Maps_HashTables_SkipLists.MyAbstractSortedMap;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class MyTreeMap<K,V> extends MyAbstractSortedMap<K,V> {

    // To represent the underlying tree structure, we use a specialized subclass of the
    // linkedBinary Tree class that we name BalanceableBinaryTree

    protected MyBalanceableBinaryTree<K,V> tree = new MyBalanceableBinaryTree<>();
    /** Constructs an empty map using the natural ordering of keys*/
    public MyTreeMap(){
        super();
        tree.addRoot(null);
    }
    /** Constructs an empty map using the given comparator to order key*/
    public MyTreeMap(Comparator<K> comp){
        super(comp);
        tree.addRoot(null);
    }

/** Returns the number of entries in the map*/
    @Override
    public int size() {
        // remove the external nodes (null entries)
        return (tree.size()-1)/2;
    }

    /** Utility used when inserting a new entry at a leaf of the tree.*/
    private void expandExternal(TreePosition<Map.Entry<K,V>> p, Map.Entry<K,V> entry) {
        tree.set(p, entry);            // store new entry at p which is the leaf
        tree.addLeft(p, null);         // add new sentinel leaves as children
        tree.addRight(p, null);
    }

    /**
     * Returns the position in p's subtree having the given key (or else the terminal leaf).
     * @param key  a target key
     * @param p  a position of the tree serving as root of a subtree
     * @return Position holding key, or last node reached during search
     */
    private TreePosition<Map.Entry<K,V>> treeSearch(TreePosition<Map.Entry<K,V>> p, K key) {
        if (isExternal(p))
            return p;                          // key not found; return the final leaf
        int comp = compare(key, p.getElement());
        if (comp == 0)
            return p;                          // key found; return its position
        else if (comp < 0)
            return treeSearch(left(p), key);   // search left subtree
        else
            return treeSearch(right(p), key);  // search right subtree
    }

    /**
     * Returns position with the minimal key in the subtree rooted at Position p.
     * @param p  a Position of the tree serving as root of a subtree
     * @return Position with minimal key in subtree
     */
    protected TreePosition<Map.Entry<K,V>> treeMin(TreePosition<Map.Entry<K,V>> p) {
        TreePosition<Map.Entry<K,V>> walk = p;
        while (isInternal(walk))
            walk = left(walk);
        return parent(walk);              // we want the parent of the leaf
    }

    /**
     * Returns the position with the maximum key in the subtree rooted at p.
     * @param p  a Position of the tree serving as root of a subtree
     * @return Position with maximum key in subtree
     */
    protected TreePosition<Map.Entry<K,V>> treeMax(TreePosition<Map.Entry<K,V>> p) {
        TreePosition<Map.Entry<K,V>> walk = p;
        while (isInternal(walk))
            walk = right(walk);
        return parent(walk);              // we want the parent of the leaf
    }

    /**
     * Returns the value associated with the specified key, or null if no such entry exists.
     * @param key  the key whose associated value is to be returned
     * @return the associated value, or null if no such entry exists
     */
    @Override
    public V get(K key) throws IllegalArgumentException {
        checkKey(key);                          // may throw IllegalArgumentException
        TreePosition<Map.Entry<K,V>> p = treeSearch(root(), key);
        rebalanceAccess(p);                     // hook for balanced tree subclasses
        if (isExternal(p)) return null;         // unsuccessful search
        return p.getElement().getValue();       // match found
    }

    /**
     * Associates the given value with the given key. If an entry with
     * the key was already in the map, this replaced the previous value
     * with the new one and returns the old value. Otherwise, a new
     * entry is added and null is returned.
     * @param key    key with which the specified value is to be associated
     * @param value  value to be associated with the specified key
     * @return the previous value associated with the key (or null, if no such entry)
     */
    @Override
    public V put(K key, V value) throws IllegalArgumentException {
        checkKey(key);                          // may throw IllegalArgumentException
        Map.Entry<K,V> newEntry = new AbstractMap.SimpleEntry<>(key, value);
        // if the root is null (tree is empty), we set the new adding entry as the root, no rebalance needed
      /*  if(this.root()==null){
            tree.addRoot(newEntry);
            return value;
        }*/
        // if the root is not null, we add entry and rebalance it.
        TreePosition<Map.Entry<K,V>> p = treeSearch(root(), key);
        if (isExternal(p)) {                    // key is new
            expandExternal(p, newEntry);
            rebalanceInsert(p);                   // hook for balanced tree subclasses
            return null;
        } else {                                // replacing existing key
            V old = p.getElement().getValue();
            set(p, newEntry);
            rebalanceAccess(p);                   // hook for balanced tree subclasses
            return old;
        }
    }

    /**
     * Removes the entry with the specified key, if present, and returns
     * its associated value. Otherwise does nothing and returns null.
     * @param key  the key whose entry is to be removed from the map
     * @return the previous value associated with the removed key, or null if no such entry exists
     */
    @Override
    public V remove(K key) throws IllegalArgumentException {
        checkKey(key);                          // may throw IllegalArgumentException
        TreePosition<Map.Entry<K,V>> p = treeSearch(root(), key);
        if (isExternal(p)) {                    // key not found
            rebalanceAccess(p);                   // hook for balanced tree subclasses
            return null;
        } else {
            V old = p.getElement().getValue();
            if (isInternal(left(p)) && isInternal(right(p))) { // both children are internal
                TreePosition<Map.Entry<K,V>> replacement = treeMax(left(p));
                set(p, replacement.getElement());
                p = replacement;
            } // now p has at most one child that is an internal node
            TreePosition<Map.Entry<K,V>> leaf = (isExternal(left(p)) ? left(p) : right(p));
            TreePosition<Map.Entry<K,V>> sib = sibling(leaf);
            remove(leaf);
            remove(p);                            // sib is promoted in p's place
            rebalanceDelete(sib);                 // hook for balanced tree subclasses
            return old;
        }
    }


    // additional behaviors of the SortedMap interface
    /**
     * Returns the entry having the least key (or null if map is empty).
     * @return entry with least key (or null if map is empty)
     */
    @Override
    public Map.Entry<K,V> firstEntry() {
        if (isEmpty()) return null;
        return treeMin(root()).getElement();
    }

    /**
     * Returns the entry having the greatest key (or null if map is empty).
     * @return entry with greatest key (or null if map is empty)
     */
    @Override
    public Map.Entry<K,V> lastEntry() {
        if (isEmpty()) return null;
        return treeMax(root()).getElement();
    }

    /**
     * Returns the entry with least key greater than or equal to given key
     * (or null if no such key exists).
     * @return entry with least key greater than or equal to given (or null if no such entry)
     * @throws IllegalArgumentException if the key is not compatible with the map
     */
    @Override
    public Map.Entry<K,V> ceilingEntry(K key) throws IllegalArgumentException {
        checkKey(key);                              // may throw IllegalArgumentException
        TreePosition<Map.Entry<K,V>> p = treeSearch(root(), key);
        if (isInternal(p)) return p.getElement();   // exact match
        while (!isRoot(p)) {
            if (p == left(parent(p)))
                return parent(p).getElement();          // parent has next greater key
            else
                p = parent(p);
        }
        return null;                                // no such ceiling exists
    }

    /**
     * Returns the entry with greatest key less than or equal to given key
     * (or null if no such key exists).
     * @return entry with greatest key less than or equal to given (or null if no such entry)
     * @throws IllegalArgumentException if the key is not compatible with the map
     */
    @Override
    public Map.Entry<K,V> floorEntry(K key) throws IllegalArgumentException {
        checkKey(key);                              // may throw IllegalArgumentException
        TreePosition<Map.Entry<K,V>> p = treeSearch(root(), key);
        if (isInternal(p)) return p.getElement();   // exact match
        while (!isRoot(p)) {
            if (p == right(parent(p)))
                return parent(p).getElement();          // parent has next lesser key
            else
                p = parent(p);
        }
        return null;                                // no such floor exists
    }


    /**
     * Returns the entry with greatest key strictly less than given key
     * (or null if no such key exists).
     * @return entry with greatest key strictly less than given (or null if no such entry)
     * @throws IllegalArgumentException if the key is not compatible with the map
     */
    @Override
    public Map.Entry<K,V> lowerEntry(K key) throws IllegalArgumentException {
        checkKey(key);                              // may throw IllegalArgumentException
        TreePosition<Map.Entry<K,V>> p = treeSearch(root(), key);
        if (isInternal(p) && isInternal(left(p)))
            return treeMax(left(p)).getElement();     // this is the predecessor to p
        // otherwise, we had failed search, or match with no left child
        while (!isRoot(p)) {
            if (p == right(parent(p)))
                return parent(p).getElement();          // parent has next lesser key
            else
                p = parent(p);
        }
        return null;                                // no such lesser key exists
    }

    /**
     * Returns the entry with least key strictly greater than given key
     * (or null if no such key exists).
     * @return entry with least key strictly greater than given (or null if no such entry)
     * @throws IllegalArgumentException if the key is not compatible with the map
     */
    @Override
    public Map.Entry<K,V> higherEntry(K key) throws IllegalArgumentException {
        checkKey(key);                               // may throw IllegalArgumentException
        TreePosition<Map.Entry<K,V>> p = treeSearch(root(), key);
        if (isInternal(p) && isInternal(right(p)))
            return treeMin(right(p)).getElement();     // this is the successor to p
        // otherwise, we had failed search, or match with no right child
        while (!isRoot(p)) {
            if (p == left(parent(p)))
                return parent(p).getElement();           // parent has next lesser key
            else
                p = parent(p);
        }
        return null;                                 // no such greater key exists
    }

// Support for iteration
    /**
     * Returns an iterable collection of all key-value entries of the map.
     *
     * @return iterable collection of the map's entries
     */
    @Override
    public Iterable<Map.Entry<K,V>> entrySet() {
        ArrayList<Map.Entry<K,V>> buffer = new ArrayList<>(size());
        for (TreePosition<Map.Entry<K,V>> p : tree.inorder())
            if (isInternal(p)) buffer.add(p.getElement());
        return buffer;
    }

    /**
     * Returns an iterable containing all entries with keys in the range from
     * <code>fromKey</code> inclusive to <code>toKey</code> exclusive.
     * @return iterable with keys in desired range
     * @throws IllegalArgumentException if <code>fromKey</code> or <code>toKey</code> is not compatible with the map
     */
    @Override
    public Iterable<Map.Entry<K,V>> subMap(K fromKey, K toKey) throws IllegalArgumentException {
        checkKey(fromKey);                                // may throw IllegalArgumentException
        checkKey(toKey);                                  // may throw IllegalArgumentException
        ArrayList<Map.Entry<K,V>> buffer = new ArrayList<>(size());
        if (compare(fromKey, toKey) < 0)                  // ensure that fromKey < toKey
            subMapRecurse(fromKey, toKey, root(), buffer);
        return buffer;
    }

    // utility to fill subMap buffer recursively (while maintaining order)
    private void subMapRecurse(K fromKey, K toKey, TreePosition<Map.Entry<K,V>> p,
                               ArrayList<Map.Entry<K,V>> buffer) {
        if (isInternal(p))
            if (compare(p.getElement(), fromKey) < 0)
                // p's key is less than fromKey, so any relevant entries are to the right
                subMapRecurse(fromKey, toKey, right(p), buffer);
            else {
                subMapRecurse(fromKey, toKey, left(p), buffer); // first consider left subtree
                if (compare(p.getElement(), toKey) < 0) {       // p is within range
                    buffer.add(p.getElement());                      // so add it to buffer, and consider
                    subMapRecurse(fromKey, toKey, right(p), buffer); // right subtree as well
                }
            }
    }

// Stubs for balanced search tree operations (subclasses can override)
    /**
     * Rebalances the tree after an insertion of specified position.  This
     * version of the method does not do anything, but it can be
     * overridden by subclasses.
     * @param p the position which was recently inserted
     */
    protected void rebalanceInsert(TreePosition<Map.Entry<K,V>> p) { }

    /**
     * Rebalances the tree after a child of specified position has been
     * removed.  This version of the method does not do anything, but it
     * can be overridden by subclasses.
     * @param p the position of the sibling of the removed leaf
     */
    protected void rebalanceDelete(TreePosition<Map.Entry<K,V>> p) { }

    /**
     * Rebalances the tree after an access of specified position.  This
     * version of the method does not do anything, but it can be
     * overridden by a subclasses.
     * @param p the Position which was recently accessed (possibly a leaf)
     */
    protected void rebalanceAccess(TreePosition<Map.Entry<K,V>> p) { }

    // remainder of class is for debug purposes only

    /** Prints textual representation of tree structure (for debug purpose only). */
    protected void dump() {
        dumpRecurse(root(), 0);
    }

    /** This exists for debugging only */
    private void dumpRecurse(TreePosition<Map.Entry<K,V>> p, int depth) {
        String indent = (depth == 0 ? "" : String.format("%" + (2*depth) + "s", ""));
        if (isExternal(p))
            System.out.println(indent + "leaf");
        else {
            System.out.println(indent + p.getElement());
            dumpRecurse(left(p), depth+1);
            dumpRecurse(right(p), depth+1);
        }
    }

    // Some notational shorthands for brevity (yet not efficiency)
    protected TreePosition<Map.Entry<K,V>> root() { return tree.root(); }
    protected TreePosition<Map.Entry<K,V>> parent(TreePosition<Map.Entry<K,V>> p) { return tree.parent(p); }
    protected TreePosition<Map.Entry<K,V>> left(TreePosition<Map.Entry<K,V>> p) { return tree.left(p); }
    protected TreePosition<Map.Entry<K,V>> right(TreePosition<Map.Entry<K,V>> p) { return tree.right(p); }
    protected TreePosition<Map.Entry<K,V>> sibling(TreePosition<Map.Entry<K,V>> p) { return tree.sibling(p); }
    protected boolean isRoot(TreePosition<Map.Entry<K,V>> p) { return tree.isRoot(p); }
    protected boolean isExternal(TreePosition<Map.Entry<K,V>> p) { return tree.isExternal(p); }
    protected boolean isInternal(TreePosition<Map.Entry<K,V>> p) { return tree.isInternal(p); }
    protected void set(TreePosition<Map.Entry<K,V>> p, Map.Entry<K,V> e) { tree.set(p, e); }
    protected Map.Entry<K,V> remove(TreePosition<Map.Entry<K,V>> p) { return tree.remove(p); }
    protected void rotate(TreePosition<Map.Entry<K,V>> p) { tree.rotate(p); }
    protected TreePosition<Map.Entry<K,V>> restructure(TreePosition<Map.Entry<K,V>> x) { return tree.restructure(x); }
}
