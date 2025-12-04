package model.collection;

/**
 * @author Vincent Velthuizen
 */
public interface Collection<E> {
    boolean add(E element);

    boolean contains(E element);

    boolean remove(E element);

    int size();

    void clear();
}
