package org.pengfei.Lesson13_Common_Data_Structure.L13_S6_List;

/**
 * An interface for a position which is an abstraction for the
 * location at which a single element is stored in a positional
 * container.
 * */
public interface Position<E> {
    /**
     * Returns the element stored at this position.
     *
     * @return the stored element
     * @throws IllegalStateException if position no longer valid
     */
    E getElement();
}