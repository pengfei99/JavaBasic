package org.pengfei.Lesson13_Common_Data_Structure.L13_S6_List;

public interface Position<E> {
    /**
     * Returns the element stored at this position.
     *
     * @return the stored element
     * @throws IllegalStateException if position no longer valid
     */
    E getElement();
}
