package model.collection;

import java.util.Arrays;

/**
 * @author Vincent Velthuizen
 * Explain how hashset works
 */
public class HashSet<E> implements Set<E> {
    private static final int CAPACITY = 31;
    private E[] elements;
    private int size;

    public HashSet() {
        clear();
    }

    @Override
    public boolean add(E element) {
        int index = getIndex(element);
        elements[index] = element;
        size++;
//        System.out.printf("%s, %d, %d, %s\n", element, hashCode, index, Arrays.toString(elements));
        return true;
    }

    private int getIndex(E element) {
        int hashCode = element.hashCode();
        int index = hashCode % CAPACITY;
        return index;
    }

    @Override
    public boolean contains(E element) {
        int index = getIndex(element);
        E storedElement = elements[index];
        return element.equals(storedElement);
    }

    @Override
    public boolean remove(E element) {
        int index = getIndex(element);
        E storedElement = elements[index];

        if (element.equals(storedElement)) {
            elements[index] = null;
            size--;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {
        size = 0;
        elements = (E[]) new Object[CAPACITY];
    }
}
