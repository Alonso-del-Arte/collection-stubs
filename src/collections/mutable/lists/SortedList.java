package collections.mutable.lists;

import collections.IntegerIndexedCollection;
import collections.mutable.ArrayBackedCollection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedList<E extends Comparable<E>> extends ArrayBackedCollection<E>
        implements IntegerIndexedCollection<E>, Iterable<E> {

    private E[] elements;

    // TODO: Write tests for this
    @Override
    public boolean contains(E element) {
        return false;
    }

    // TODO: Write tests for this
    @Override
    public boolean isEmpty() {
        return true;
    }

    // TODO: Write tests for this
    @Override
    public int size() {
        return Integer.MIN_VALUE;
    }

    // TODO: Write tests for this
    @Override
    public boolean add(E element) {
        return true;
    }

    // TODO: Write tests for this
    @Override
    public int indexOf(E element) {
        return -1;
    }

    // TODO: Write tests for this
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return this.index < SortedList.this.elements.length;
            }

            @Override
            public E next() {
                return SortedList.this.elements[index++];
            }

        };
    }

    public SortedList() {
        //
    }

    SortedList(E[] originalElements) {
        this.elements = originalElements;
    }

}
