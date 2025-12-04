package model.generics;

import java.util.List;

/**
 * @author Vincent Velthuizen
 * Purpose for the class
 */
public class IdentifiableBox<I extends Comparable<? super I>, T> implements Comparable<IdentifiableBox<I, ?>> {
    private final I identifier;
    private List<T> contents;

    public IdentifiableBox(I identifier) {
        this.identifier = identifier;
    }


    @Override
    public int compareTo(IdentifiableBox<I, ?> otherBox) {
        return identifier.compareTo(otherBox.identifier);
    }
}
