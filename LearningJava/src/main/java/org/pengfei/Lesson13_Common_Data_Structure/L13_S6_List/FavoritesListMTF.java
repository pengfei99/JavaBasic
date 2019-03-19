package org.pengfei.Lesson13_Common_Data_Structure.L13_S6_List;

public class FavoritesListMTF<E> extends FavoritesList<E> {

    /**
     * Moves accessed item at Position p to the front of the list.
     */
    protected void moveUp(Position<Item<E>> p) {
        if (p != list.first())
            list.addFirst(list.remove(p)); // remove/reinsert item
    }

    /**
     * Returns an iterable collection of the k most frequently accessed elements.
     */
    public Iterable<E> getFavorites(int k) throws IllegalArgumentException {
        if (k < 0 || k > size())
            throw new IllegalArgumentException("Invalid k");

        // we begin by making a copy of the original list
        PositionalList<Item<E>> temp = new LinkedPositionalList<>();
        for (Object item : list)
            temp.addLast((Item<E>) item);

        // we repeated find, report, and remove element with largest count
        PositionalList<E> result = new LinkedPositionalList<>();
        for (int j = 0; j < k; j++) {
            Position<Item<E>> highPos = temp.first();
            Position<Item<E>> walk = temp.after(highPos);
            while (!list.lastElement(walk)) {
                if (count(walk) > count(highPos))
                    highPos = walk;
                walk = temp.after(walk);
            }  // we have now found element with highest count
            result.addLast(value(highPos));
            temp.remove(highPos);
        }
        return result;
    }
}
