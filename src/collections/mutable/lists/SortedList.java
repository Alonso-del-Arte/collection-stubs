package collections.mutable.lists;

import collections.IntegerIndexedCollection;
import collections.mutable.ArrayBackedCollection;

import java.util.Iterator;

public class SortedList<E extends Comparable<E>> extends ArrayBackedCollection<E>
        implements IntegerIndexedCollection<E>, Iterable<E> {

    // TODO: Write tests for this
    @Override
    public boolean contains(E element) {
        return false;
    }

    // TODO: Write tests for this
    @Override
    public boolean isEmpty() {
        return false;
    }

    // TODO: Write tests for this
    @Override
    public int size() {
        return Integer.MIN_VALUE;
    }

    // TODO: Write tests for this
    @Override
    public boolean add(E element) {
        return false;
    }

    // TODO: Write tests for this
    @Override
    public int indexOf(E element) {
        return 0;
    }

    // TODO: Write tests for this
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                return null;
            }

        };
    }

}
